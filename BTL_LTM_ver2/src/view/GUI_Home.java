/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ServerController.ClientHandler;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author admin
 */
public class GUI_Home extends javax.swing.JFrame {
   private ArrayList<ClientHandler> clientHandlers;
    /**
     * Creates new form GUI_Home
     */
    public GUI_Home() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    public void setLblUsername(String s) {
        this.lblUsername.setText(s);
    }
    
    public JTable getTblOnlineList() {
        return tblOnlineList;
    }

    public JLabel getLblMessage() {
        return lblMessage;
    }

    public JLabel getLblOpp() {
        return lblOpp;
    }
         
    public ArrayList<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        JLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblOpp = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOnlineList = new javax.swing.JTable();
        btnChallange = new javax.swing.JButton();
        btnScore = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        lblUsername = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Home");

        JLabel.setText("Welcome");

        jLabel2.setText("Player selected: ");

        lblOpp.setText("-None-");

        jLabel3.setText("Player Online");

        tblOnlineList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username"
            }
        ));
        jScrollPane1.setViewportView(tblOnlineList);

        btnChallange.setText("Challange");

        btnScore.setText("High Score");

        btnUpdate.setText("Update list");

        lblUsername.setText("name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(196, 196, 196))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30)
                                .addComponent(lblOpp))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnChallange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnScore, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))))
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLabel)
                            .addComponent(lblUsername)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(lblOpp))
                                .addGap(40, 40, 40)
                                .addComponent(btnChallange)
                                .addGap(18, 18, 18)
                                .addComponent(btnScore)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(lblMessage)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabel;
    private javax.swing.JButton btnChallange;
    private javax.swing.JButton btnScore;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblOpp;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable tblOnlineList;
    // End of variables declaration//GEN-END:variables

    public void addUpdateListener(ActionListener al) {
        btnUpdate.addActionListener(al);
    }

    public void addTableListener(MouseAdapter ma) {
        this.getTblOnlineList().addMouseListener(ma);
    }

    public void addChallengeListener(ActionListener al) {
        btnChallange.addActionListener(al);
    }

    public void addScoreBoardListener(ActionListener al) {
        btnScore.addActionListener(al);
    }
    
    public void addWindowClosingListener(WindowAdapter wa) {
        this.addWindowListener(wa);
    }
    
    public void setTblOnline(ArrayList<ClientHandler> data) {
        this.clientHandlers = data;
        String columnNames[] = {"Username"};
//        System.out.println(clientHandlers.size());
        int n = data.size();
        String dataArr[][] = new String[n][1];
        for (int i = 0; i < n; i++) {
            dataArr[i][0] = data.get(i).getPlayer().getUsername();
        }

        tblOnlineList.setModel(new javax.swing.table.DefaultTableModel(
                dataArr,
                columnNames
        ) {
            Class[] types = new Class[]{
                java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblOnlineList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }
    
}