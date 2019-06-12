package utils;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class EventHandlerMouse implements MouseWheelListener {
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println(e.getScrollAmount());
    }
}
