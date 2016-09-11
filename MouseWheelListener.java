import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Shivi on 22-Aug-16.
 */
public class MouseWheelListener implements NativeMouseWheelListener {

    FileWriter fw;
    Integer rotation;

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
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nativeMouseWheelEvent) {
        // getWheelRotation returns integer
        //System.out.println("Mosue Wheel Moved: " + nativeMouseWheelEvent.getWheelRotation());
        rotation = new Integer(nativeMouseWheelEvent.getWheelRotation());
        try {
            fw.write(rotation.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
