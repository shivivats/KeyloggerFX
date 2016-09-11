import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Shivi on 22-Aug-16.
 */
public class MouseListener implements NativeMouseInputListener {

    FileWriter fw;
    Integer button;

    public FileWriter getFw() {
        return fw;
    }

    public void setFw(FileWriter fw) {
        this.fw = fw;
    }

    public void closeFw() throws IOException {
        try {
            this.fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        // getClickCount returns integer
        //System.out.println("Mouse Clicked: " + nativeMouseEvent.getClickCount());
        button = new Integer(nativeMouseEvent.getClickCount());
        try {
            fw.write(button.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        // getButton returns integer
        //System.out.println("Mouse Pressed: " + nativeMouseEvent.getButton());
        /*button = new Integer(nativeMouseEvent.getButton());
        try {
            fw.write(button.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        //System.out.println("Mouse Released: " + nativeMouseEvent.getButton());
       /* button = new Integer(nativeMouseEvent.getButton());
        try {
            fw.write(button.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {
        // do nothing
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
        // do nothing
    }
}
