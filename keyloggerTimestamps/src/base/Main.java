package base;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements KeyListener{

    private char key;

    public static void main(String[] args) {

        keyLis

        while(true){
            System.out.println();
        }

        System.out.println();
    }


    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyChar();
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
