package sk.stuba.fei.uim.oop.shapes;

import java.awt.*;

public class House extends MyShape {
    public House(int x, int y, Color color) {
        super(x, y, color);
    }

    @Override
    public void draw(Graphics g) {
        Color color = g.getColor();
        g.setColor(this.color);
        g.fillRect(
                this.x + this.size / 4,
                this.y + this.size / 2,
                this.size / 2,
                this.size / 2
        );
        g.fillPolygon(
                new int[] {this.x + this.size / 2, this.x + this.size / 4 - 1, this.x + this.size * 3 / 4},
                new int[] {this.y, this.y + this.size / 2, this.y + this.size / 2},
                3
        );
        g.setColor(color);
    }
}
