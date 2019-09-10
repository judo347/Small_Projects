package utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventListener implements KeyListener {
    public KeyEventListener() {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int id = e.getID();
        if (id == 400) {
            char c = e.getKeyChar();
            System.out.println(c);
            System.out.println(e.getKeyCode());
        }

    }

    public void keyReleased(KeyEvent e) {
    }
}