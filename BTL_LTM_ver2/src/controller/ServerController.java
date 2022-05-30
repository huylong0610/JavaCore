/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import model.Matrix;
import model.Message;
import model.Player;

/**
 *
 * @author admin
 */
public class ServerController {
    private ServerSocket socket;
    private final int port = 8888;


    
    public ServerController() {
        openServer(port);
        listening();
    }

    private void openServer(int port) {
        try {
            socket = new ServerSocket(port);
            System.out.println("Sever is running...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listening() {
        while (!socket.isClosed()) {
            try {
                Socket Serversocket = socket.accept();
                ClientHandler clientHandler = new ClientHandler(Serversocket);
                System.out.println(clientHandler.getPlayer().getUsername() + " has entered the system!");
                System.out.println("Number of clients: " + ClientHandler.clientHandlers.size());

                clientHandler.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //ClientHandler
    public static class ClientHandler extends Thread implements Serializable {

        private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
        private Player player;
        private transient Socket socket;
        private transient ObjectOutputStream oos;
        private transient ObjectInputStream ois;
        private transient Connection con;
        private int playerid;
        private int oppid;

        private boolean requestStart;
        private volatile boolean requestFinish;
        private ArrayList<Object> matchResult;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            this.requestStart = false;
            this.requestFinish = false;
            this.matchResult = null;

            clientHandlers.add(this);

            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());

                //read Client
                Object obj = ois.readObject();
                if (obj instanceof Message) {
                    Message msg = (Message) obj;
                    if (msg.getMessage().equals("Client")) {
                        this.player = (Player) msg.getData();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            listenForMessage();
//            System.out.println("closed");
        }

        public void listenForMessage() {
//        System.out.println(ClientHandler.getClientHandlers().size());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (socket.isConnected()) {
                        try {
                            Object o = ois.readObject();
                            if (o instanceof Message) {
                                Message msgReceived = (Message) o;
                                System.out.println(msgReceived.getMessage()); //print Message
                                sendMessage(msgReceived);
                            }
                        } catch (Exception e) {
                            closeEverything(socket, ois, oos);
                        }
                    }
                }
            }).start();
        }

        private void RequestOnlineClient(Message msgReceived) throws IOException {
            if (msgReceived.getMessage().equals("RequestOnlineClient")) {
                //System.out.println(player.isPlaying());
                ArrayList<ClientHandler> clientHandlers_clone = new ArrayList<>();
                for (ClientHandler clientHandler : clientHandlers) {
                    clientHandlers_clone.add(clientHandler);
                }
                Message msgSend = new Message("OnlineClient", clientHandlers_clone);
                System.out.println("OnlineClient: " + clientHandlers_clone.size()); //clone the static clientHandlers because it cant be serialized
                oos.writeObject(msgSend);
                oos.flush();
            }
        }

        private void RequestInvite(Message msgReceived) throws IOException {
            if (msgReceived.getMessage().equals("RequestInvite")) { //send to a clientHandler
                Player playerInvite = this.getPlayer(); //player who sends invite
                Player Invitedplayer = (Player) msgReceived.getData(); //player who receives invite

                for (ClientHandler clientHandler : clientHandlers) {
                    String player = clientHandler.getPlayer().getUsername();
                    if (player.equals(Invitedplayer.getUsername())) {
                        Message msg = new Message("Invite", playerInvite);
                        clientHandler.sendMessage(msg);
                        break;
                    }
                }
            }
            if (msgReceived.getMessage().equals("Invite")) { //send to the corresponding client
                oos.writeObject(msgReceived);
                oos.flush();
            }
        }

        private void RespondRefuse(Message msgReceived) throws IOException {
            if (msgReceived.getMessage().equals("RespondRefuse")) { //send to a clientHandler
                Player playerRefuse = this.getPlayer(); //player who refuses
                Player playerInvite = (Player) msgReceived.getData(); //player who sends invite

                for (ClientHandler clientHandler : clientHandlers) {
                    String player = clientHandler.getPlayer().getUsername();
                    if (player.equals(playerInvite.getUsername())) {
                        Message msg = new Message("Refuse", playerRefuse);
                        clientHandler.sendMessage(msg);
                        break;
                    }
                }
            }
            if (msgReceived.getMessage().equals("Refuse")) { //send to the corresponding client
                oos.writeObject(msgReceived);
                oos.flush();
            }
        }

        private void RespondAccept(Message msgReceived) throws IOException {
            if (msgReceived.getMessage().equals("RespondAccept")) { //send to a clientHandler
                Player playerAccept = this.getPlayer(); //player who accepts
                Player playerInvite = (Player) msgReceived.getData(); //player who sends invite
              
                for (ClientHandler clientHandler : clientHandlers) {
                    String player = clientHandler.getPlayer().getUsername();
                    if (player.equals(playerInvite.getUsername())) {
                        Message msg = new Message("Accept", playerAccept);
                        clientHandler.sendMessage(msg);
                        oppid =  playerAccept.getId();
                        playerid = playerInvite.getId(); 
                        break;
                    }
                }
            }
            
            if (msgReceived.getMessage().equals("Accept")) { //send to the corresponding client
                oos.writeObject(msgReceived);
                oos.flush();
            }
        }

        private void RequestStartGame(Message msgReceived) throws IOException {
            if (msgReceived.getMessage().equals("RequestStartGame")) {
                Player opponent = (Player) msgReceived.getData();
//                    System.out.println(opponent);
                this.player.setPlaying(true);
                this.requestStart = true;
                for (ClientHandler clientHandler : clientHandlers) {
                    String player = clientHandler.getPlayer().getUsername();
                    if (player.equals(opponent.getUsername())) {
//                            System.out.println("opponent is ready: "+clientHandler.isRequestStart());
                        while (!clientHandler.isRequestStart()) {
                            //do nothing
//                                System.out.println("waiting...");
                        }
                        int size = 20;
                        Matrix matrix = new Matrix(size);
                        System.out.println("");
                        int[][] ma = new int[size][size];
 
                        ArrayList<Integer> list = new ArrayList<Integer>(size);
                        for(int i = 1; i <= size*size; i++) {
                            list.add(i);
                        }
 
                        Random rand = new Random();
                        for(int i=0; i<size; i++){
                            for(int j=0; j<size; j++){
                                int index = rand.nextInt(list.size());
                                ma[i][j] = list.remove(index);
                                System.out.print(ma[i][j] + " "); 
                            }
                            System.out.println("");     
                        }
                        matrix.setMatrix(ma);
                        Message msg = new Message("StartGame",matrix);
                        oos.writeObject(msg.getData());
                        clientHandler.sendMessage(msg);
                        this.sendMessage(msg);
                        break;
                    }
                }
            }
            if (msgReceived.getMessage().equals("StartGame")) {
                oos.writeObject(msgReceived);
                oos.flush();
            }
        }

        private void RequestExitGame(Message msgReceived) throws IOException {
            if (msgReceived.getMessage().equals("RequestExitGame")) {
                Player opponent = (Player) msgReceived.getData();
                this.player.setPlaying(false);
                this.requestStart = false;

                for (ClientHandler clientHandler : clientHandlers) {
                    String player = clientHandler.getPlayer().getUsername();
                    if (player.equals(opponent.getUsername())) {
                        clientHandler.getPlayer().setPlaying(false);
                        clientHandler.setRequestStart(false);
                        Message msg = new Message("ExitGame");
                        clientHandler.sendMessage(msg);
                        break;
                    }
                }
            }
            
            if (msgReceived.getMessage().equals("ExitGame")) {
                this.player.setPlaying(false);
                this.requestStart = false;
                oos.writeObject(msgReceived);
                oos.flush();
            }
        }

        private void sendMessage(Message msgReceived) {
            try {
                RequestOnlineClient(msgReceived);
                if (msgReceived.getMessage().equals("RemoveOnlineClient")) {
                    closeEverything(socket, ois, oos);
                }
                RequestInvite(msgReceived);
                RespondRefuse(msgReceived);
                RespondAccept(msgReceived);
                RequestStartGame(msgReceived);
                //RequestExitGame(msgReceived);

                if (msgReceived.getMessage().equals("RequestFinishGame")) {
                    // do nothing
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void getDBConnection(String dbName, String username, String password) {
            String dbUrl = "jdbc:mysql://localhost:3306/ltm?autoReconnect=true&useSSL=false";
            try {
                con = DriverManager.getConnection(dbUrl, "root", "");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void removeClientHandler() {
            clientHandlers.remove(this);
        }
        
        public void closeEverything(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) {
//            System.out.println(player.getUsername() + " has left the system!");
            removeClientHandler();
            try {
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Player getPlayer() {
            return player;
        }

        public static ArrayList<ClientHandler> getClientHandlers() {
            return clientHandlers;
        }

        public boolean isRequestStart() {
            return requestStart;
        }

        public void setRequestStart(boolean requestStart) {
            this.requestStart = requestStart;
        }

        public boolean isRequestFinish() {
            return requestFinish;
        }

        public void setRequestFinish(boolean requestFinish) {
            this.requestFinish = requestFinish;
        }

        public ArrayList<Object> getMatchResult() {
            return matchResult;
        }
    }
}
