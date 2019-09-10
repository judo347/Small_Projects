package utils;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class EventHandlerMouse implements MouseWheelListener {
    public EventHandlerMouse() {
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println(e.getScrollAmount());
    }
}
