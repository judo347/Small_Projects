package utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int id = e.getID();

        if(id == KeyEvent.KEY_TYPED){

            char c = e.getKeyChar();

            System.out.println(c);
            System.out.println(e.getKeyCode());

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
