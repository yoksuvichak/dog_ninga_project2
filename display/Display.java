package display;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Display extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Dimension size = new Dimension(1000, 600);
    private JButton start = new JButton("Game Start");
    public Display() {
        this.setting();
    }

    private void setting() {
        this.setTitle("Dog ninja");
        this.setSize(size);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(280, 100);
        start.addActionListener(this);
        this.add(start);
        this.setVisible(true);
    }

    private void removeContent() {
        this.getContentPane().removeAll();
        this.getContentPane().repaint();
    }

    public void endGame(long point) {
        removeContent();
        this.getContentPane().add(new Menu(point, this));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("restart")) {
            removeContent();
            Game game = new Game();
            this.getContentPane().add(game);
            game.requestFocus();
        }
        if (e.getSource() == start){
            this.remove(start);
            this.getContentPane().add(new Game());
        }
    }

}
