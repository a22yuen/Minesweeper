import java.awt.*;

public abstract class square {

    protected int value;                      //amount of bombs around it, -1 means bomb
    protected boolean flag;
    protected int x;
    protected int y;
    protected int iconnum;
    protected Image[] icons = new Image[4]; //1 = tile, 2 = bomb/value, 3 = flag, 4 = clicked bomb/wrong flag bomb


    public abstract void setValue(int n);
    public abstract Image drawIcon();
    public abstract int getX();
    public abstract int getY();
    public abstract void flagged();
    public abstract boolean isFlagged();
    public abstract int getValue();
    public abstract int getIconnum();
    public abstract void setIconnum(int i);
}

