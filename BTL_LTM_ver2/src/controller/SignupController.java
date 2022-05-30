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
import java.security.interfaces.RSAKey;
import java.sql.DriverManager;
import view.GUI_Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Player;
import view.GUI_Signup;

/**
 *
 * @author admin
 */
public class SignupController {
    private GUI_Signup gui_signup;
    private GUI_Login gui_login;
    Connection con;
    
    private final String host = "localhost";
    private final int port = 8888;

    public SignupController(GUI_Signup gui_signup) {
        this.gui_signup = gui_signup;
        gui_signup.addSignupListener(new SignupListener());
        gui_signup.setVisible(true);
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
    
    public class SignupListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Player p = null;

        //1. get user info
        String username = gui_signup.getTxtUsername().getText();
        String password = String.valueOf(gui_signup.getTxtPassword().getPassword());
        
        System.out.println(username + " "+ password);
        
        String sql = "SELECT Id FROM ltm.users\n"
                    + "WHERE Username = ?\n";
        String sql2 = "INSERT INTO ltm.users (username, password, state, totalScore, totalMatch, totalSpareTime, totalWin) VALUES (?, ?, 'online', 0, 0, 0, 0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            //ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Username is unavailable!");
            }   else{
                rs.close();
                ps.close();
                ps = con.prepareStatement(sql2);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();
                //p = new Player();
                //p.setUsername(rs.getString("username"));
                //p.setPassword(rs.getString("password"));
                System.out.println("Success!");
                JOptionPane.showMessageDialog(null, "Signup Success!");
                gui_signup.setVisible(false);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}


