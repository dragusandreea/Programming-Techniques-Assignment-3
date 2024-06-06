package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerPrincipalProduct implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frameProdus = new JFrame("Product");
        frameProdus.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frameProdus.setSize(400, 230);

        JPanel cpanel0 = new JPanel();
        JPanel cpanel1 = new JPanel();
        JPanel finalPanelClient = new JPanel();

        JLabel clabel0 = new JLabel("Product");
        clabel0.setHorizontalAlignment(SwingConstants.CENTER);
        clabel0.setVerticalAlignment(SwingConstants.CENTER);
        cpanel0.add(clabel0);

        JLabel clabel1 = new JLabel("Product Operation");
        String [] operatiiProdus = {"Add", "Edit","Delete", "View all"};
        JComboBox operatieProdus = new JComboBox(operatiiProdus);
        ComboBoxListenerProduct comboBoxListenerProduct = new ComboBoxListenerProduct(frameProdus);
        operatieProdus.addActionListener(comboBoxListenerProduct);

        cpanel1.add(clabel1);
        cpanel1.add(operatieProdus);

        finalPanelClient.add(cpanel0);
        finalPanelClient.add(cpanel1);

        finalPanelClient.setLayout(new BoxLayout(finalPanelClient, BoxLayout.Y_AXIS));

        frameProdus.setContentPane(finalPanelClient);
        frameProdus.setVisible(true);
        frameProdus.show();
    }
}
