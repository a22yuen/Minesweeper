import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Menu implements MouseListener {

    JFrame frame;
    JButton ez = new JButton("Easy");
    JButton med = new JButton("Medium");
    JButton hard = new JButton("Hard");
    JButton tutorial = new JButton("How To Play");
    ImageIcon bg = new ImageIcon(getClass().getResource("bg.jpg"));
    ImageIcon t1 = new ImageIcon(getClass().getResource("htp1.jpg"));
    ImageIcon t2 = new ImageIcon(getClass().getResource("htp22.jpg"));
    JLabel label = new JLabel();

    public Menu () {
        label.setIcon(bg);
        frame = new JFrame("Minesweeper");
        frame.setPreferredSize(new Dimension(800, 685));
        frame.setLocation(400, 100);

        ez.addMouseListener(this);
        med.addMouseListener(this);
        hard.addMouseListener(this);
        tutorial.addMouseListener(this);
        ez.setBounds(180, 350, 150, 25);
        med.setBounds(180, 390, 150, 25);
        hard.setBounds(180, 430, 150, 25);
        tutorial.setBounds(180, 500, 150, 25);

        label.add(ez);
        label.add(med);
        label.add(hard);
        label.add(tutorial);



        frame.add(label);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main (String [] args){
        new Menu();
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(tutorial)){
            JOptionPane.showMessageDialog(null,"",
                    "Tutorial", JOptionPane.INFORMATION_MESSAGE, t1);
            JOptionPane.showMessageDialog(null,"",
                    "Tutorial", JOptionPane.INFORMATION_MESSAGE, t2);
        }
        if(e.getSource().equals(ez)){
            new Game(1);
            frame.dispose();
        }
        else if(e.getSource().equals(med)){
            new Game(2);
            frame.dispose();
        }
        else if(e.getSource().equals(hard)){
            new Game(3);
            frame.dispose();
        }
    }


    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
