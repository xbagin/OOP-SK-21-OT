package sk.stuba.fei.uim.oop.shapes;

import java.awt.*;
import java.util.Objects;

public class Line implements Drawable {
    private final MyShape shape1;
    private int x2;
    private int y2;

    public Line(MyShape shape1, int x2, int y2) {
        this.shape1 = shape1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setEnd(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

    public boolean finish(MyShape shape2) {
        if (this.isCompatibleShape(shape2)) {
            this.setEnd(shape2.getX() + shape2.getSize() / 2, shape2.getY() + shape2.getSize() / 2);
            return true;
        }
        return false;
    }

    @Override
    public void draw(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.BLACK);
        int size = this.shape1.getSize();
        g.drawLine(this.shape1.getX() + size / 2, this.shape1.getY() + size / 2, this.x2, this.y2);
        g.setColor(color);
    }

    private boolean isCompatibleShape(MyShape shape) {
        return shape != null && !(Objects.equals(shape.getClass(), this.shape1.getClass()));
    }
}
