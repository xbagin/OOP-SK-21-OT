package sk.stuba.fei.uim.oop.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MyButton extends JButton {
    public MyButton(String text, ActionListener listener) {
        super(text);
        this.addActionListener(listener);
    }
}
