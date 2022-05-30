/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import model.Matrix;
import model.Message;
import model.Player;
import view.GUI_Game;
import view.GUI_HighScore;
import view.GUI_Home;

/**
 *
 * @author admin
 */
public class ClientController extends Thread {

    private Player player;
    private transient GUI_Home gui_home;
    private transient GUI_Game gui_game;
    private transient GUI_HighScore gui_highscore;
    private transient ScoreboardController scoreboardController;
    private Player opponent;
    private int oppID;
    private Player currentPlayer;
    //private transient final Object lock;
    private boolean isPlaying;

    private transient Socket socket;
    private transient ObjectOutputStream oos;
    private transient ObjectInputStream ois;
    private final String host = "localhost";
    private final int port = 8888;
    
    private int matrix[][] = new int[20][20];
    private String status;
    JButton btn[][] = new JButton[20][20];
    boolean check[][] = new boolean [20][20];
    Stack<Integer> stack = new Stack<Integer>();

    public ClientController(Player player, Socket socket) {
        this.player = player;
        this.socket = socket;
        this.currentPlayer = null;


        gui_home = new GUI_Home();

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        init();
    }

    private void init() {
        gui_home.setLblUsername(player.getUsername());
        gui_home.addWindowClosingListener(new windowClosingListener());
        gui_home.addUpdateListener(new UpdateListener());
        gui_home.addTableListener(new TableListener());
        gui_home.addChallengeListener(new ChallengeListener());
        gui_home.addScoreBoardListener(new ScoreBoardListener());
        gui_home.setVisible(true);

        Message msg = new Message("Client", player);
        sendMessage(msg);
    }

    private void sendMessage(Message msg) {
        try {
            oos.writeObject(msg);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
//            closeEverything(mySocket, oos, ois);
        }
    }

    private void listening() {
        while (!socket.isClosed()) {
            try {
                Object o = ois.readObject();
                if (o instanceof Message) {
                    Message msgReceived = (Message) o;
                    System.out.println(msgReceived.getMessage());

                    if (msgReceived.getMessage().equals("OnlineClient")) { //
                        ArrayList<ServerController.ClientHandler> clientHandlers;
                        clientHandlers = (ArrayList<ServerController.ClientHandler>) msgReceived.getData();
//                        System.out.println(clientHandlers.size());
                        gui_home.setTblOnline(clientHandlers);
                    }

                    if (msgReceived.getMessage().equals("Invite")) { //receive invite
                        Player playerInvite = (Player) msgReceived.getData(); //player who sends invite

                        if (player.isPlaying()) {
                            Message msg = new Message("RespondRefuse", playerInvite);
                            sendMessage(msg);
                        } else {
                            String[] options = {"ACCEPT", "REFUSE"};
                            int x = JOptionPane.showOptionDialog(null, playerInvite.getUsername() + " challenges you",
                                    "",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                            if (x == 0) { //ACCEPT CHALLENGE from player who sends invite
                                Message msg = new Message("RespondAccept", playerInvite);
                                opponent = playerInvite;
                                oppID = opponent.getId();
                                //System.out.println(oppID+"");
                                sendMessage(msg);
                                //start game
                                //startGame();
                                createG(player, opponent);
                            } else { //REFUSE CHALLENGE from player who sends invite
                                Message msg = new Message("RespondRefuse", playerInvite);
                                sendMessage(msg);
                            }
                        }
                    }

                    if (msgReceived.getMessage().equals("Accept")) { //receives opponent's ACCEPT CHALLENGE
                        Player playerAccept = (Player) msgReceived.getData(); //player who accepts
                        gui_home.getLblMessage().setText(playerAccept.getUsername() + " has accepted the challenge!");
                        
                        //start game
                        createG(player, opponent);
                        //startGame();
                    }

                    if (msgReceived.getMessage().equals("Refuse")) { //receives opponent's REFUSE CHALLENGE
                        Player playerRefuse = (Player) msgReceived.getData(); //player who refuses
                         gui_home.getLblMessage().setText(playerRefuse.getUsername() + " has refused the challenge/is playing!");
                        opponent = null;
                    }
                    
                    if (msgReceived.getMessage().equals("ExitGame")) {
                        player.setPlaying(false);
                        opponent = null;
                        gui_game.dispose();
                    }
                    
                    if (msgReceived.getMessage().equals("StartGame")) {
                        Matrix ma = (Matrix) msgReceived.getData();
                        ma.print();
                         
                        int a[][] = new int[20][20];
                        a = ma.getMatrix();
                        
                        initGame(a);
                        //System.out.println("hold /n/n");
                        //for(int i=0; i<20; i++){
                        //   for(int j=0; j<20; j++){
                        //       btn[i][j] = new JButton();//khởi tạo bộ nhớ cho từng jbutton
                        //       btn[i][j].setSize(200, 200);
                        //       btn[i][j].setBackground(Color.white);
                        //       btn[i][j].setText(Integer.toString(a[i][j]));
                        //       System.out.print(btn[i][j].getText().toString() + " ");
                        //   }
                        //   System.out.println("");
                        //}
                    }
                }
            } catch (Exception e) {
                try {
                    closeEverything(socket, ois, oos);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
  
    /*public void startGame() {
        gui_game = new GUI_Game(player.getUsername());
        player.setPlaying(true);
        JButton btn[][] = new JButton[20][20];
        gui_game.isPlaying = true;
        String status = null;
        int hour = 0;
        int min = 0;
        int sec = 0;
            
        boolean check[][] = new boolean [20][20];
        Stack<Integer> stack = new Stack<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>(400);
        for(int i=1; i<=400;i++){
            list.add(i);
        }
        Random ran = new Random();
        stack.add(0);
        
        gui_game.getpGame().setLayout(new GridLayout(20,20));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                btn[i][j] = new JButton();//khởi tạo bộ nhớ cho từng jbutton
                btn[i][j].setSize(200, 200);
                btn[i][j].setBackground(Color.white);
                int index = ran.nextInt(list.size());
                btn[i][j].setText(Integer.toString(list.remove(index)));
                check[i][j] = false;
                //System.out.print(btn[i][j].getText().toString() + " ");
                
                gui_game.getpGame().add(btn[i][j]);//add giá trị vào phần tử trỏ chuột tương ứng
                btn[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       int col = 0;
                       int row = 0;
                       for (int i = 0; i < 20; i++) {
                           for (int j = 0; j < 20; j++) {
                            if (e.getSource() == btn[i][j]) {
                               col = i;
                               row = j;
                              }
                           }
                       }
                       if(check[col][row] == false && Integer.parseInt(btn[col][row].getText().toString()) == (stack.peek()+1)){
                            btn[col][row].setBackground(Color.gray);
                             stack.add(Integer.parseInt(btn[col][row].getText().toString()));
                            check[col][row] = true;
                        }else{
                           JOptionPane.showMessageDialog(null, "Tro choi ket thuc");
                           stack.add(500);
                           Message msg = new Message("RequestExitGame");
                           sendMessage(msg);
                           gui_game.isPlaying = false;
                           }
                        }
                    }
                );
            }
            //System.out.println("");
        }
        for(int i=0; i<20; i++){
            for(int j=0; j<20; j++){
                if(check[i][j] != true && stack.peek() != 400){
                    status = "lose";
                }
                else{
                    status = "check";
                    hour = Integer.parseInt(gui_game.getLblHour().toString());
                    min = Integer.parseInt(gui_game.getLblMin().toString());
                    sec = Integer.parseInt(gui_game.getLblSec().toString());
                }
            }
        }
        gui_game.getpGame().setVisible(true);
        gui_game.setVisible(true);
    }
  */
    @Override
    public void run() {
        listening();
    }
    
    public class ScoreBoardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gui_highscore = new GUI_HighScore();
            scoreboardController = new ScoreboardController(gui_highscore);
            gui_highscore.setVisible(true);
        }
    }
    
    public class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            jButton.setEnabled(false);

            Message msg = new Message("RequestStartGame", opponent);
            sendMessage(msg);
        }

    }

    public class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Message msg = new Message("RequestOnlineClient");
            sendMessage(msg);
        }

    }

    public class gameClosingListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            //if both finish the game, or both are not ready
            System.out.println("RequestExitGame");
            player.setPlaying(false);
            //quizFrm.setGameEnd(true);
            Message msg = new Message("RequestExitGame", opponent);
            sendMessage(msg);
            opponent = null;
            e.getWindow().dispose();
        }
    }

    public class windowClosingListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Closing...");
            Message msg = new Message("RemoveOnlineClient");
            sendMessage(msg);
            e.getWindow().dispose();
       }
   }
  
    public class TableListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent evt) {
//            System.out.println(player.isPlaying());
            JTable tblOnline = gui_home.getTblOnlineList();
            JLabel lblOpp = gui_home.getLblOpp();

            int column =  tblOnline.getColumnModel().getColumnIndexAtX(evt.getX()); // get the coloum of the button;
            int row = evt.getY() /  tblOnline.getRowHeight(); // get row 

//         *Checking the row or column is valid or not
            if (row <  tblOnline.getRowCount() && row >= 0 && column < tblOnline.getColumnCount() && column >= 0) {
                currentPlayer = new Player();
                currentPlayer = gui_home.getClientHandlers().get(row).getPlayer();
                lblOpp.setText(currentPlayer.getUsername());
            }
        }
    }

    public class ChallengeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(player.getUsername() + " challenges " + currentPlayer.getUsername());
            if (currentPlayer == null || currentPlayer.getUsername().equals(player.getUsername())) {
                gui_home.getLblMessage().setText("Select a valid player!");
            } else {
                gui_home.getLblMessage().setText("Sending invite to the selected player...");
                opponent = currentPlayer;
                Message msg = new Message("RequestInvite", opponent);
                sendMessage(msg);
            }
        }
    }
    
    public void closeEverything(Socket socket, ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        Message msg = new Message("RemoveOnlineClient");
        oos.writeObject(msg);
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
    
    public void createG(Player player, Player oppent){
        gui_game = new GUI_Game(player.getUsername());
        gui_game.addStartListener(new StartListener());
        //gui_game.isPlaying = true;
        gui_game.setVisible(true);
        gui_game.isPlaying = false;
        //boolean st1 = player.isPlaying();
        //boolean st2 = opponent.isPlaying();
        
        //System.out.println(st1 + " " + st2);
    }
    
    public void initGame(int[][] a){
        gui_game = new GUI_Game(player.getUsername());
        player.setPlaying(true);
        JButton btn[][] = new JButton[20][20];
        gui_game.isPlaying = true;
        //int hour = 0;
        //int min = 0;
        //int sec = 0;
            
        Random ran = new Random();
        stack.add(0);
        
        gui_game.getpGame().setLayout(new GridLayout(20,20));
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                btn[i][j] = new JButton();//khởi tạo bộ nhớ cho từng jbutton
                btn[i][j].setSize(200, 200);
                btn[i][j].setBackground(Color.white);
                btn[i][j].setText(Integer.toString(a[i][j]));
                check[i][j] = false;
                //System.out.print(btn[i][j].getText().toString() + " ");
                
                gui_game.getpGame().add(btn[i][j]);//add giá trị vào phần tử trỏ chuột tương ứng
                btn[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       int col = 0;
                       int row = 0;
                       for (int i = 0; i < 20; i++) {
                           for (int j = 0; j < 20; j++) {
                            if (e.getSource() == btn[i][j]) {
                               col = i;
                               row = j;
                              }
                           }
                       }
                       if(check[col][row] == false && Integer.parseInt(btn[col][row].getText().toString()) == (stack.peek()+1)){
                            btn[col][row].setBackground(Color.gray);
                             stack.add(Integer.parseInt(btn[col][row].getText().toString()));
                            check[col][row] = true;
                        }else{
                           gui_game.isPlaying = false;
                           JOptionPane.showMessageDialog(null, "Tro choi ket thuc");
                           stack.add(500);
                           System.out.println(check(player, opponent));
                           //Message msg = new Message("RequestExitGame");
                           //sendMessage(msg);
                           }
                        }
                    }
                );
            }
            //System.out.println("");
        }
        gui_game.getpGame().setVisible(true);
        gui_game.setVisible(true);
    }
    
    public String check(Player player, Player opponent){
        // dieu kien thang thua hoa.
        if(!player.isPlaying() && opponent.isPlaying()){
            return "u1 false";
        }
        if(player.isPlaying() && !opponent.isPlaying()){
            return "u2 false";
        }
        return null;
    }
}