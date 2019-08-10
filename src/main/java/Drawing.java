import javax.swing.*;
import java.awt.*;

public class Drawing extends JPanel {
    Drawing() {
        repaint();
    }

    public void paint(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.black);

        g.fillRect(0, 0, 800, 800);

        g.setColor(Color.white);

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (Snake.snake[x][y][0] > 0) {
                    g.fillRect(40 * x, 40 * y, 40, 40);
//                    System.out.println("Painted");
                }
            }
        }
    }
}
