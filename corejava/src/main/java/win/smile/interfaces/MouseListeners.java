package win.smile.interfaces;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * @author smilewei on 2018/7/5.
 * @since 1.0.0
 */
public class MouseListeners implements MouseWheelListener,MouseIn{


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println("aa");
    }
}
