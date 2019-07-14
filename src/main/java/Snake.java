//File: Snake.java
//Created: 14/07/2019
//Finished: 14/07/2019
//Name: Hisbaan Noorani
//
//This program 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Snake implements ActionListener {
    JFrame mainMenuFrame = new JFrame("Main Menu");
    JPanel bottomPannel = new JPanel();
    JButton startGameButton = new JButton("Star Game");

    public static void main(String[] args) {
        new Snake();
    }

    Snake() {
        //setup timer for movement
        mainMenu();
    }

    public void mainMenu() {
        mainMenuFrame.setSize(800, 800);
        mainMenuFrame.setLayout(new BorderLayout());
        mainMenuFrame.setResizable(false);

        mainMenuFrame.add(bottomPannel, BorderLayout.SOUTH);
        bottomPannel.setLayout(new GridLayout(1, 3));
        bottomPannel.add(startGameButton);

    }

    public void startGame() {
        /*
        * array that tracks the snake's movement
        * find a way to add the the end of the array
        * maybe notation of how many turns it has been and set a disappear timer
        * 3d array (x, y and time existed)
        * if you pick up an apple, increase the time required before disappearing.  */
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startGameButton) {
            startGame();
        }
    }
}
