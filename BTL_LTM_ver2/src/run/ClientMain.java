/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import controller.LoginController;
import view.GUI_Login;

/**
 *
 * @author admin
 */
public class ClientMain {
    public static void main(String[] args) {
        LoginController loginController = new LoginController(new GUI_Login());
    }
}
