package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerPrincipalClient implements ActionListener {
    public ButtonListenerPrincipalClient()
    {
        //this.jfClient = jfClient;
    }
    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JFrame frameClient = new JFrame("Client");
        frameClient.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frameClient.setSize(500, 730);

        JPanel cpanel0 = new JPanel();
        JPanel cpanel1 = new JPanel();
        JPanel finalPanelClient = new JPanel();

        JLabel clabel0 = new JLabel("Client");
        clabel0.setHorizontalAlignment(SwingConstants.CENTER);
        clabel0.setVerticalAlignment(SwingConstants.CENTER);
        cpanel0.add(clabel0);

        JLabel clabel1 = new JLabel("Client Operation");
        String [] operatiiClient = {"Add", "Edit","Delete", "View all"};
        JComboBox operatieClient = new JComboBox(operatiiClient);
        ComboBoxListener comboBoxListener = new ComboBoxListener(frameClient);
        operatieClient.addActionListener(comboBoxListener);

        cpanel1.add(clabel1);
        cpanel1.add(operatieClient);

        finalPanelClient.add(cpanel0);
        finalPanelClient.add(cpanel1);

        finalPanelClient.setLayout(new BoxLayout(finalPanelClient, BoxLayout.Y_AXIS));

        frameClient.setContentPane(finalPanelClient);
        frameClient.setVisible(true);
        frameClient.show();
    }
}
