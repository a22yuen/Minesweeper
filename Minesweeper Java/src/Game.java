import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Game extends JPanel implements MouseListener {
    int ncol,nrow,nbomb,diff,ccol,crow,flags;
    JFrame frame;
    JLabel labelbomb = new JLabel();
    int loser,firstClick,winner = 0;
    Image smile = Toolkit.getDefaultToolkit().getImage("smile.jpg");
    Image gg = Toolkit.getDefaultToolkit().getImage("gg.jpg");
    Image cool = Toolkit.getDefaultToolkit().getImage("cool.jpg");
    Image out = Toolkit.getDefaultToolkit().getImage("out.jpg");

    ArrayList<ArrayList<square>> board = new ArrayList<>();


    public void checkValue(square n, int col, int row){
        n.setValue(checkValue(col-1,row) + checkValue(col-1,row - 1) + checkValue(col-1,row+1) +
                checkValue(col+1,row-1) + checkValue(col+1,row) + checkValue(col+1,row+1) +
                checkValue(col,row+1) + checkValue(col,row-1));
    }

    public int checkValue(int col, int row){
        if(col >= 0 && row >= 0 && col < ncol && row < nrow){
            if (board.get(col).get(row) instanceof bomb){
                return 1;
            }
        }
        return 0;
    }

    public void GenerateMap(int dif){
        board = new ArrayList<>();
        if(dif == 1){                                       //grid initialization
            ncol = 10;
            nrow = 8;
            nbomb = 10;
            diff = 1;
        }
        else if(dif == 2){
            ncol = nrow = 16;
            nbomb = 40;
            diff = 2;

        }
        else if(dif == 3){
            ncol = 30;
            nrow = 16;
            nbomb = 99;
            diff = 3;
        }
        flags = nbomb;
        frame.setPreferredSize(new Dimension((ncol*40)+60, (nrow*40)+160)); // E:450,420  M:690,740     H:1250,740
        frame.setLocation(5000/ncol,50);
        setPreferredSize(new Dimension(ncol*40,nrow*40));

        for(int i = 0; i < ncol; i++){                              //tile generation
            board.add(new ArrayList<>());
            for(int j = 0; j < nrow; j++){
                board.get(i).add(j,new tile(i,j));
            }
        }

        for(int i = 0; i < nbomb; i++){                       //bomb generation
            int x = (int) (Math.random() * (ncol-1) + 1);
            int y = (int) (Math.random() * (nrow-1) + 1);
            while(board.get(x).get(y) instanceof bomb){
                x = (int) (Math.random() * (ncol-1) + 1);
                y = (int) (Math.random() * (nrow-1) + 1);
            }
            board.get(x).remove(y);
            board.get(x).add(y,new bomb(x,y));

        }

        for(int i = 0; i < nrow; i++){                          //value generation
            for(int j = 0; j < ncol; j++){
                if(board.get(j).get(i) instanceof tile){
                    checkValue(board.get(j).get(i),j,i);
                }
            }
        }
        repaint();
    }

    public void nobomb(int c, int r){                           //no bombs first click
        if (board.get(c).get(r).getValue() != 0 ){
            GenerateMap(diff);
            nobomb(c,r);
        }
        else{
            click(1,c,r);
        }
    }

    public void click(int button, int c, int r){                //clicking the screen with left or right click
        if (button == 1) {
            if(!board.get(c).get(r).isFlagged()) {
                reveal(c,r);
            }
        } else if (button == 2 && board.get(c).get(r).getIconnum() != 1) {
            board.get(c).get(r).flagged();
            if(board.get(c).get(r).isFlagged()){
                flags--;
            }
            else{
                flags++;
            }
            labelbomb.setText("Flags Left: " + flags);
            repaint();
        }
    }

    public void reveal(int c, int r){
        if(c >= 0 && c < ncol && r >= 0 && r < nrow) {
            if (board.get(c).get(r) instanceof bomb) {
                board.get(c).get(r).setIconnum(3);
                loser++;
            }
            else if (board.get(c).get(r) instanceof tile) {
                if(board.get(c).get(r).getIconnum() == 0) {
                    board.get(c).get(r).setIconnum(1);
                    if (board.get(c).get(r).getValue() == 0 ){
                        reveal(c - 1, r - 1);
                        reveal(c - 1, r);
                        reveal(c - 1, r + 1);
                        reveal(c + 1, r - 1);
                        reveal(c + 1, r);
                        reveal(c + 1, r + 1);
                        reveal(c, r - 1);
                        reveal(c,r + 1);
                        reveal(c + 1, r + 1);
                    }
                }
            }
            repaint();
        }
    }

    public void paintComponent (Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0,0,2000,1000);
        for (int i = 0; i < ncol; i++) {
            for (int j = 0; j < nrow; j++) {
                g.drawImage(board.get(i).get(j).drawIcon(), board.get(i).get(j).getX(), board.get(i).get(j).getY(), 40, 40, this);
            }
        }
        g.drawImage(smile, (ncol * 10), 35, 40, 40, this);
        g.drawImage(out, (ncol * 33), 35, 40, 40, this);
        if(loser == 1){
            g.drawImage(gg, (ncol * 10), 35, 40, 40, this);
            for(int i = 0;i < nrow; i++){
                for(int j = 0; j<ncol; j++){
                    if(board.get(j).get(i) instanceof bomb){
                        if (board.get(j).get(i).getIconnum() != 3) {
                            board.get(j).get(i).setIconnum(1);
                        }
                    }
                    if(board.get(j).get(i) instanceof tile){
                        if(board.get(j).get(i).getIconnum() == 2){
                            board.get(j).get(i).setIconnum(3);
                        }
                    }
                }
            }
            loser++;
         }
        if (loser == 2){
            g.drawImage(gg, (ncol * 10), 35, 40, 40, this);
            repaint();
        }
        if(winner == 1){
            g.drawImage(cool, (ncol * 10), 35, 40, 40, this);
            repaint();
        }
    }

    public void win(){
        for(int i = 0;i < nrow; i++){
            for(int j = 0; j<ncol; j++){
                if(board.get(j).get(i) instanceof tile && board.get(j).get(i).getIconnum() != 1){
                    return;
                }
            }
        }
        winner++;
        repaint();
    }

    public Game(int dif){
        frame = new JFrame("Minesweeper");
        GenerateMap(dif);
        labelbomb.setForeground(Color.BLACK);
        labelbomb.setText("Flags Left: " + flags);
        add(labelbomb);
        addMouseListener(this);
        frame.add (this);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mousePressed(MouseEvent e) {
        ccol = (e.getX()-25)/40;
        crow = (e.getY()+20)/40 - 3;
        if(ccol >= 0 && ccol < ncol && crow >= 0 && crow < nrow && loser == 0 && winner == 0) {
            if (e.getButton() == MouseEvent.BUTTON1) { //left click
                if (firstClick == 0) {
                    nobomb(ccol, crow);
                    firstClick++;
                } else {
                    click(1, ccol, crow);
                    win();
                }
            } else if (e.getButton() == MouseEvent.BUTTON3 && firstClick != 0) { // right click
                click(2, ccol, crow);
            }
        }
        if(e.getX() >= (ncol*10) && e.getX() <= (ncol*10)+40 && e.getY() >= 35 && e.getY() <= 75){
            firstClick = 0;
            winner = 0;
            loser = 0;
            flags = nbomb;
            GenerateMap(diff);
            labelbomb.setText("Flags Left: " + flags);
        }
        if(e.getX() >= (ncol*33) && e.getX() <= (ncol*33)+40 && e.getY() >= 35 && e.getY() <= 75){
            frame.dispose();
            new Menu();
        }
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
}
