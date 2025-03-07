package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("HighSchoolGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel gamePanel = new Panel();

        frame.add(gamePanel);
        gamePanel.startThread();
        frame.pack();

        frame.setLocationRelativeTo(null); // puts the window at the center
        frame.setVisible(true);


    }
}