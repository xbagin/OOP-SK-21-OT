package sk.stuba.fei.uim.oop.logic;

import lombok.Getter;
import sk.stuba.fei.uim.oop.gui.MyPanel;
import sk.stuba.fei.uim.oop.shapes.House;
import sk.stuba.fei.uim.oop.shapes.Line;
import sk.stuba.fei.uim.oop.shapes.MyShape;
import sk.stuba.fei.uim.oop.shapes.Tree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.Objects;

public class EventHandler extends UniversalAdapter {
    @Getter
    private final JLabel label;
    private Modes mode;
    //private final static String[] MODES = new String[] {"Tree", "House", "Road"};

    public EventHandler() {
        super();
        this.label = new JLabel();
        this.label.setHorizontalAlignment(JLabel.CENTER);
        this.label.setOpaque(true);
        this.mode = Modes.TREE;  // EventHandler.MODES[0];
        this.label.setText(this.mode.name());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        this.setMode(e);
        this.label.setText(this.mode.name());
        this.label.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (e.getSource() instanceof MyPanel) {
            MyPanel canvas = (MyPanel) e.getSource();
            if (this.modeIs(Modes.TREE) || this.modeIs(Modes.HOUSE)) {
                this.label.setBackground(canvas.nextColor());
                this.label.repaint();
                canvas.addShape(canvas.getShape());
                if (this.modeIs(Modes.TREE)) {
                    canvas.setShape(new Tree(e.getX(), e.getY(), canvas.getColor()));
                }
                if (this.modeIs(Modes.HOUSE)) {
                    canvas.setShape(new House(e.getX(), e.getY(), canvas.getColor()));
                }
                canvas.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (e.getSource() instanceof MyPanel) {
            MyPanel canvas = (MyPanel) e.getSource();
            if (this.modeIs(Modes.ROAD)) {
                MyShape shape1 = canvas.findMostFrontShapeAt(e.getX(), e.getY());
                if (shape1 != null) {
                    canvas.setLine(new Line(shape1, e.getX(), e.getY()));
                    canvas.repaint();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (e.getSource() instanceof MyPanel) {
            MyPanel canvas = (MyPanel) e.getSource();
            if (this.modeIs(Modes.ROAD)) {
                MyShape shape2 = canvas.findMostFrontShapeAt(e.getX(), e.getY());
                if (shape2 != null) {
                    if (canvas.getLine().finish(shape2)) {
                        canvas.addLine(canvas.getLine());
                    }
                }
                canvas.setLine(null);
                canvas.repaint();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (e.getSource() instanceof MyPanel) {
            MyPanel canvas = (MyPanel) e.getSource();
            if (this.modeIs(Modes.ROAD)) {
                Line line = canvas.getLine();
                if (line != null) {
                    line.setEnd(e.getX(), e.getY());
                    canvas.repaint();
                }
            }
            this.moveShape(e, canvas);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        if (e.getSource() instanceof MyPanel) {
            MyPanel canvas = (MyPanel) e.getSource();
            // throws exception, but if all strings are in Modes, it's OK ... better THAN numbers !!!
            //if (Objects.equals(Modes.valueOf(this.mode.toUpperCase(Locale.ROOT)), Modes.TREE)) {//this.modeIs(Modes.TREE)) {
            if (this.modeIs(Modes.TREE)) {
                canvas.setShape(new Tree(e.getX(), e.getY(), canvas.getColor()));
                canvas.repaint();
            }
            if (this.modeIs(Modes.HOUSE)) {
                canvas.setShape(new House(e.getX(), e.getY(), canvas.getColor()));
                canvas.repaint();
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        if (e.getSource() instanceof MyPanel) {
            MyPanel canvas = (MyPanel) e.getSource();
            if (this.modeIs(Modes.TREE) || this.modeIs(Modes.HOUSE)) {
                canvas.setShape(null);
                canvas.repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        if (e.getSource() instanceof MyPanel) {
            this.moveShape(e, (MyPanel) e.getSource());
        }
    }

    private void setMode(ActionEvent e) {
        /*-for (String mode : EventHandler.MODES) {
            if (this.commandIs(e, mode)) {
                this.mode = mode;
                break;
            }
        }*/
        try {
            this.mode = Modes.valueOf(e.getActionCommand().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ignored) { }
    }

    private void moveShape(MouseEvent e, MyPanel canvas) {
        if (this.modeIs(Modes.HOUSE) || this.modeIs(Modes.TREE)) {
            MyShape shape = canvas.getShape();
            if (shape != null) {
                shape.setCoordinates(e.getX(), e.getY());
                canvas.repaint();
            }
        }
    }
/* not necessary
    private boolean commandIs(ActionEvent e, String command) {
        return Objects.equals(e.getActionCommand(), command);
    }
*/
    private boolean modeIs(Modes mode) {
        //-return Objects.equals(EventHandler.MODES[mode.pos], this.mode);
        //return Objects.equals(Modes.valueOf(this.mode.toUpperCase(Locale.ROOT)), mode);
        return Objects.equals(this.mode, mode);
    }
}
