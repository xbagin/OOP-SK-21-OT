package sk.stuba.fei.uim.oop.shapes;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public abstract class MyShape implements Drawable {
    @Getter
    protected int x;
    @Getter
    protected int y;
    @Getter
    protected final int size;
    @Getter @Setter
    protected Color color;
    public final static int SIZE = 50;

    public MyShape(int x, int y, Color color) {
        this.size = MyShape.SIZE;
        this.x = x - this.size / 2;
        this.y = y - this.size / 2;
        this.color = color;
    }

    public boolean isInCoordinates(int x, int y) {
        boolean inBoundsX = (this.x <= x) && (this.x + this.size >= x);
        boolean inBoundsY = (this.y <= y) && (this.y + this.size >= y);
        return inBoundsX && inBoundsY;
    }

    public void setCoordinates(int x, int y) {
        this.x = x - this.size / 2;
        this.y = y - this.size / 2;
    }
}
