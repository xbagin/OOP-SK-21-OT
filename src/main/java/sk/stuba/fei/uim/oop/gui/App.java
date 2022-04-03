package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.logic.EventHandler;

import javax.swing.*;
import java.awt.*;

public class App {
    public App() {
        JFrame frame = new JFrame("OT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        EventHandler handler = new EventHandler();
        MyPanel canvas = new MyPanel(handler);
        handler.getLabel().setBackground(canvas.getColor());

        MyButton treeButton = new MyButton("Tree", handler);
        MyButton houseButton = new MyButton("House", handler);
        MyButton roadButton = new MyButton("Road", handler);

        JPanel topPanel = new JPanel(new GridLayout(1, 4));
        topPanel.add(treeButton);
        topPanel.add(houseButton);
        topPanel.add(roadButton);
        topPanel.add(handler.getLabel());

        frame.add(topPanel, BorderLayout.PAGE_START);
        frame.add(canvas, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
