package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerBackAddClient implements ActionListener {
    JFrame frameClient;
    JFrame newFrameClient;
    JPanel initialContentPane;
    private boolean backWasPressed = false;

    public ButtonListenerBackAddClient(JFrame oldframeClient,JFrame newFrameClient, JPanel initialContentPane) {
         this.frameClient = oldframeClient;
         this.newFrameClient = newFrameClient;

    }
    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        //frameClient.setContentPane(initialContentPane);
        backWasPressed = true;
        newFrameClient.setVisible(false);
        frameClient.setVisible(true);
        frameClient.show();
    }

    public boolean isBackWasPressed() {
        return backWasPressed;
    }

    public void setBackWasPressed(boolean backWasPressed) {
        this.backWasPressed = backWasPressed;
    }
}
