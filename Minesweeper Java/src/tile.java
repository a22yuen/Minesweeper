import java.awt.*;

public class tile extends square{


    public void setIconnum(int i){iconnum = i;}
    public int getIconnum(){return iconnum;}
    public int getValue(){return value;}
    public void setValue(int n){
        value = n;
        iconnum = 0;
        icons[0] = Toolkit.getDefaultToolkit().getImage("tile.jpg");
        if(value > 0) {
            icons[1] = Toolkit.getDefaultToolkit().getImage(value + ".jpg");
        }
        else icons[1] = Toolkit.getDefaultToolkit().getImage("empty.jpg");
        icons[2] = Toolkit.getDefaultToolkit().getImage("flag.jpg");
        icons[3] = Toolkit.getDefaultToolkit().getImage("wrong.jpg");
    }
    public tile(int col, int row){
        value = 0;
        x = col*40+25;
        y = row*40+100;

    }
    public Image drawIcon(){
        return icons[iconnum];
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


    public String toString(){
        return Integer.toString(value);
    }

}
