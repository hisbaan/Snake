import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Snake implements ActionListener, KeyListener, WindowListener {
    JFrame mainMenuFrame = new JFrame("Main Menu");
    JPanel mainMenuBottomPannel = new JPanel();
    JButton startGameButton = new JButton("Star Game");

    JFrame gameFrame = new JFrame("Snake");
    JPanel gameBottomPannel = new JPanel();
    JButton gameBackButton = new JButton("Back");
    Drawing board = new Drawing();

    char direction = ' ';
    static int[][] snake = new int[20][20];

    Timer movement;

    public static void main(String[] args) {
        new Snake();
    }

    Snake() {
        movement = new Timer(100, e -> {
            move();
            board.validate();
            board.repaint();
        });
        //setup timer for movement
        mainMenu();
    }

    public void mainMenu() {
        mainMenuFrame.setSize(800, 800);
        mainMenuFrame.setLayout(new BorderLayout());
        mainMenuFrame.setResizable(false);

        if (mainMenuFrame.getWindowListeners().length < 1) mainMenuFrame.addWindowListener(this);

        mainMenuFrame.add(mainMenuBottomPannel, BorderLayout.SOUTH);
        mainMenuBottomPannel.setLayout(new GridLayout(1, 3));
        mainMenuBottomPannel.add(startGameButton);

        if (startGameButton.getActionListeners().length < 1) startGameButton.addActionListener(this);

        mainMenuFrame.setVisible(true);
        gameFrame.setVisible(false);
    }

    public void startGame() {
        gameFrame.setSize(800, 800);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setResizable(false);

        if (gameFrame.getWindowListeners().length < 1) gameFrame.addWindowListener(this);

        gameFrame.add(gameBottomPannel, BorderLayout.SOUTH);

        gameBottomPannel.setLayout(new GridLayout(1, 3));
        gameBottomPannel.add(gameBackButton);
        if (gameBackButton.getActionListeners().length < 1) gameBackButton.addActionListener(this);

        if (gameFrame.getKeyListeners().length < 1) gameFrame.addKeyListener(this);

        gameFrame.setFocusable(true);
        gameFrame.add(board, BorderLayout.CENTER);
        /*
         * array that tracks the snake's movement
         * find a way to add the the end of the array
         * maybe notation of how many turns it has been and set a disappear timer
         * 3d array (x, y and time existed in cycles)
         * if you pick up an apple, increase the time required before disappearing.  */

        snake[10][10] = 1;

        movement.start();

        mainMenuFrame.setVisible(false);
        gameFrame.setVisible(true);
    }

    public void move() {
        switch (direction) {
            case 'n':
                north();
                break;
            case 's':
                south();
                break;
            case 'e':
                east();
                break;
            case 'w':
                west();
                break;
            default:
                break;
        }
    }

    public void north() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (snake[x][y] > 0) {
                    snake[x][y - 1] = 1;
                    snake[x][y] = 0;

                    System.out.println("north");
                }
            }
        }
    }

    public void south() {
        for (int y = 19; y >= 0; y--) {
            for (int x = 0; x < 20; x++) {
                if (snake[x][y] > 0) {
                    snake[x][y + 1] = 1;
                    snake[x][y] = 0;

                    System.out.println("south");
                }
            }
        }
    }

    public void east() {
        for (int y = 0; y < 20; y++) {
            for (int x = 19; x >= 0; x--) {
                if (snake[x][y] > 0) {
                    snake[x + 1][y] = 1;
                    snake[x][y] = 0;

                    System.out.println("east");
                }
            }
        }
    }

    public void west() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (snake[x][y] > 0) {
                    snake[x - 1][y] = 1;
                    snake[x][y] = 0;

                    System.out.println("west");
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGameButton) {
            startGame();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            direction = 'n';
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            direction = 's';
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 'e';
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = 'w';
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
