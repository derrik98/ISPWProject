package it.ispw.daniele.backpacker.boundary;

import javax.swing.*;
import java.awt.*;
import java.text.AttributedCharacterIterator;

public class ScrollString extends JPanel {

    private int x;
    private int y;
    private final String text;
    private final Label l;

    public ScrollString(){
        x = -45;
        y = 150;
        text = "iniziale ID safsdncsncdndslaoincdiscnodsicdscic";
        l = new Label("ciao");
        setSize(400, 300);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, 400, 300);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(l), x, y);
        System.out.println(x + " " + y);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("ScrollString");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ScrollString scrollString = new ScrollString();
        frame.getContentPane().add(scrollString);
        frame.setSize(400, 300);
        frame.setVisible(true);
        scrollString.start();
    }

    public void start() throws InterruptedException {
        while (true){
            while (x <= getWidth() - 50){
                x++;
                y = getHeight()/2;
                repaint();
                Thread.sleep(10);
            }
            while (x >= 0){
                x--;
                y = getHeight()/2;
                repaint();;
                Thread.sleep(10);
            }
        }
    }
}
