package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args){

        //Window settings

        JFrame window  = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventure Game");

        Panel panel = new Panel();
        window.add(panel);


        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);




    }
}
