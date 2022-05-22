package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.logic.UniversalAdapter;
import sk.stuba.fei.uim.oop.shapes.Line;
import sk.stuba.fei.uim.oop.shapes.MyShape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel {
    private final List<MyShape> shapes;
    @Getter @Setter
    private MyShape shape;
    private final List<Line> lines;
    @Getter @Setter
    private Line line;
    private final Color[] colors;
    @Getter
    private Color color;
    private int colorIndex;

    public MyPanel(UniversalAdapter listener) {
        super();
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        this.shapes = new ArrayList<>();
        this.shape = null;
        this.lines = new ArrayList<>();
        this.line = null;
        this.colors = new Color[] {Color.RED, Color.BLUE, Color.GREEN};
        this.colorIndex = 0;
        this.color = this.colors[this.colorIndex];
    }

    public void addLine(Line line) {
        this.lines.add(line);
        this.line = null;
    }

    public void addShape(MyShape shape) {
        this.shapes.add(shape);
    }

    public MyShape findMostFrontShapeAt(int x, int y) {
        MyShape foundShape = null;
        for (MyShape shape : this.shapes) {
            if (shape.isInCoordinates(x, y)) {
                foundShape = shape;
            }
        }
        return foundShape;
    }

    public Color nextColor() {
        this.colorIndex = (this.colorIndex + 1) % this.colors.length;
        this.color = this.colors[this.colorIndex];
        return this.color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.shapes.forEach(shape -> shape.draw(g));
        this.lines.forEach(line -> line.draw(g));
        if (this.shape != null) {
            Color color = this.shape.getColor();
            this.shape.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 127));
            this.shape.draw(g);
            this.shape.setColor(color);
        }
        if (this.line != null) {
            this.line.draw(g);
        }
    }
}
