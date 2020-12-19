import java.awt.*;

public class bomb extends square {

    public String toString(){
        return "b";
    }

    public void setIconnum(int i){iconnum = i;}
    public int getIconnum(){return iconnum;}
    public int getValue(){return value;}
    public void setValue(int n){}

    public Image drawIcon(){
        return icons[iconnum];
    }

    public bomb(int col, int row){
        value = -1;
        x = col*40+25;
        y = row*40+100;
        iconnum = 0;
        icons[0] = Toolkit.getDefaultToolkit().getImage("tile.jpg");
        icons[1] = Toolkit.getDefaultToolkit().getImage("bomb.jpg");
        icons[2] = Toolkit.getDefaultToolkit().getImage("flag.jpg");
        icons[3] = Toolkit.getDefaultToolkit().getImage("bad.jpg");
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void flagged() {
        if (flag) {
            iconnum = 0;
            flag = false;
        } else {
            iconnum = 2;
            flag = true;
        }
    }

    public boolean isFlagged() {
        return flag;
    }

}
