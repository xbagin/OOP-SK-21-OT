package sk.stuba.fei.uim.oop.shapes;

import java.awt.*;

public class Tree extends MyShape {
    public Tree(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public void draw(Graphics g) {
        Color color = g.getColor();
        g.setColor(this.color);
        g.fillRect(
                this.x + this.size / 3,
                this.y + this.size / 3,
                this.size / 3,
                this.size * 2 / 3
        );
        g.fillOval(this.x, this.y, this.size, this.size * 2 / 3);
        g.setColor(color);
    }
}
