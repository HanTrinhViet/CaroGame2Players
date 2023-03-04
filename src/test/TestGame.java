/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import init.login.LoginFrm;
import init.playerdata.Player;
import javax.swing.JFrame;
import maingame.view.CaroView;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author admin
 */
public class TestGame extends JFrame {

    public static void main(String[] args) {
        LoginFrm loginFrm = new LoginFrm();
        Player player1 = new Player("trinhviethan", "123");
        Player player2 = new Player("trantuanhoang", "123");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new CaroView(loginFrm, player1, player2);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
