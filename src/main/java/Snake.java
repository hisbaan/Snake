import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Snake implements ActionListener, KeyListener, WindowListener {
    JFrame mainMenuFrame = new JFrame("Main Menu");
    JPanel mainMenuBottomPannel = new JPanel();
    JButton startGameButton = new JButton("Star Game");

    JFrame gameFrame = new JFrame("Snake");
    JPanel gameBottomPannel = new JPanel();
    JButton gameBackButton = new JButton("Back");
    Drawing board = new Drawing();

    public int length = 5;
    char direction = 'n';
    static int[][][] snake = new int[20][20][2];
    static int[][] apple = new int[20][20];

    Timer movement;

    public static void main(String[] args) {
        new Snake();
    }

    Snake() {
        movement = new Timer(100, e -> {
            board.validate();
            board.repaint();
            move();
            countAge();
            cleanUp();
            checkApple();
            checkSnakeCollision();
        });
        //setup timer for movement
        mainMenu();
    }

    public void mainMenu() {
        clearBoard();

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
        gameFrame.setSize(800, 850);
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

        snake[10][10][0] = 2;

        newApple();

        movement.start();

        mainMenuFrame.setVisible(false);
        gameFrame.setVisible(true);
    }

    public void countAge() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (snake[x][y][0] == 1) {
                    snake[x][y][1]++;
                } else if (snake[x][y][0] == 0) {
                    snake[x][y][1] = 0;
                }
            }
        }
    }

    public void cleanUp() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (snake[x][y][0] == 1 && snake[x][y][1] > length) {
                    snake[x][y][0] = 0;
                }
            }
        }
    }

    public void checkSnakeCollision() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                try {
                    if (direction == 'n') {
                        if (snake[x][y][0] == 2 && snake[x][y - 1][0] == 1) {
                            gameOver();
                        }
                    }
                    if (direction == 's') {
                        if (snake[x][y][0] == 2 && snake[x][y + 1][0] == 1) {
                            gameOver();
                        }
                    }
                    if (direction == 'e') {
                        if (snake[x][y][0] == 2 && snake[x + 1][y][0] == 1) {
                            gameOver();
                        }
                    }
                    if (direction == 'w') {
                        if (snake[x][y][0] == 2 && snake[x - 1][y][0] == 1) {
                            gameOver();
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }
    }

    public void checkApple() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (snake[x][y][0] == 2 && apple[x][y] == 1) {
                    length++;
                    newApple();
                }
            }
        }
    }

    public void newApple() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                apple[x][y] = 0;
            }
        }

        Random random = new Random();

        apple[random.nextInt(20)][random.nextInt(20)] = 1;
    }

    public void move() {
        try {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            gameOver();
        }
    }

    public void north() throws ArrayIndexOutOfBoundsException {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (snake[x][y][0] == 2) {
                    snake[x][y - 1][0] = 2;
                    snake[x][y][0] = 1;

                    System.out.println("north");
                }
            }
        }
    }

    public void south() throws ArrayIndexOutOfBoundsException {
        for (int y = 19; y >= 0; y--) {
            for (int x = 0; x < 20; x++) {
                if (snake[x][y][0] == 2) {
                    snake[x][y + 1][0] = 2;
                    snake[x][y][0] = 1;

                    System.out.println("south");
                }
            }
        }
    }

    public void east() throws ArrayIndexOutOfBoundsException {
        for (int y = 0; y < 20; y++) {
            for (int x = 19; x >= 0; x--) {
                if (snake[x][y][0] == 2) {
                    snake[x + 1][y][0] = 2;
                    snake[x][y][0] = 1;

                    System.out.println("east");
                }
            }
        }
    }

    public void west() throws ArrayIndexOutOfBoundsException {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (snake[x][y][0] == 2) {
                    snake[x - 1][y][0] = 2;
                    snake[x][y][0] = 1;

                    System.out.println("west");
                }
            }
        }
    }

    public void clearBoard() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                snake[x][y][0] = 0;
            }
        }

        length = 5;
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(gameFrame, "Game Over\nScore: " + (length - 5), "Game Over", JOptionPane.PLAIN_MESSAGE, null);
        mainMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGameButton) {
            startGame();
        }
        if (e.getSource() == gameBackButton) {
            for (int y = 0; y < 20; y++) {
                for (int x = 0; x < 20; x++) {
                    snake[x][y][0] = 0;
                }
            }

            gameFrame.dispose();
            movement.stop();
            mainMenu();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (direction == 'n') {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                direction = 'e';
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                direction = 'w';
            }
        }

        if (direction == 's') {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                direction = 'e';
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                direction = 'w';
            }
        }

        if (direction == 'e') {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                direction = 'n';
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                direction = 's';
            }
        }

        if (direction == 'w') {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                direction = 'n';
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                direction = 's';
            }
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
