import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static void main(String[] args) {
        myFrame frame = new myFrame();
        myPanal panal = new myPanal();
        frame.add(panal);
        frame.setVisible(true);
    }
}

class myFrame extends JFrame {
    public myFrame() {
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
    }
}

class myPanal extends JPanel {
    private int startX = -1;
    private int startY = -1;
    private int endX = -1;
    private int endY = -1;

    public myPanal() {
        setSize(800, 800);


        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                if (startX == -1 && startY == -1) {

                    startX = e.getX();
                    startY = e.getY();
                } else {

                    endX = e.getX();
                    endY = e.getY();

                    drawLine();
                    startX = -1;
                    startY = -1;
                    endX = -1;
                    endY = -1;
                }

            }
        });
    }

    private void drawLine() {
        // DDA Line Algorithm
        int dx = endX - startX;
        int dy = endY - startY;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xIncrement = (float) dx / steps;
        float yIncrement = (float) dy / steps;

        float x = startX;
        float y = startY;

        Graphics g = getGraphics();
        g.setColor(Color.BLACK);

        for (int i = 0; i <= steps; i++) {
            g.drawRect(Math.round(x), Math.round(y), 1, 1);
            x += xIncrement;
            y += yIncrement;
        }

        g.dispose();
    }
}