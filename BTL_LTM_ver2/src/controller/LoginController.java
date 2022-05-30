/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.sql.DriverManager;
import view.GUI_Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Player;

/**
 *
 * @author admin
 */
public class LoginController {
    private GUI_Login gui_login;
    Connection con;
    
    private final String host = "localhost";
    private final int port = 8888;

    public LoginController(GUI_Login gui_login) {
        this.gui_login = gui_login;
        gui_login.addLoginListener(new LoginListener());
        gui_login.setVisible(true);
        this.getDBConnection("ltm", "root", "");
    }

    private void getDBConnection(String dbName, String username, String password) {
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?zeroDateTimeBehavior=CONVERT_TO_NULL";
        try {
            con = DriverManager.getConnection(dbUrl, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public class LoginListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Player p = null;

        //1. get user info
        String username = gui_login.getTxtUsername().getText();
        String password = String.valueOf(gui_login.getTxtPassword().getPassword());
        
        System.out.println(username + " "+ password);

        String sql = "SELECT * FROM ltm.users\n"
                    + "WHERE Username = ?\n"
                    + "AND Password = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p = new Player();
                p.setId(rs.getInt("id"));
                p.setUsername(rs.getString("username"));
                p.setPassword(rs.getString("password"));
                p.setState("online"); //Set state to online
                p.setTotalScore(rs.getFloat("totalScore"));
                p.setTotalMatch(rs.getInt("totalMatch"));
                p.setTotalSpareTime(rs.getFloat("totalSpareTime"));
                p.setTotalWin(rs.getInt("totalWin"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (p != null) {
            try {
                Socket socket = new Socket(host, port);
                System.out.println("New socket");
                ClientController clientController = new ClientController(p, socket);
                clientController.start();
                System.out.println("Success!");    
                gui_login.setVisible(false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
               // System.out.println("false!");
            }
        }
    }
}


