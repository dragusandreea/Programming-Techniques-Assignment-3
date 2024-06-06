package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerPrincipalOrder implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frameOrder = new JFrame("Order");
        frameOrder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameOrder.setSize(500, 730);

        JPanel opanel0 = new JPanel();
        JPanel opanel1 = new JPanel();
        JPanel opanel2 = new JPanel();
        JPanel opanel3 = new JPanel();

        JPanel finalPanelOrder = new JPanel();

        JLabel olabel0 = new JLabel("Order");
        olabel0.setHorizontalAlignment(SwingConstants.CENTER);
        olabel0.setVerticalAlignment(SwingConstants.CENTER);
        opanel0.add(olabel0);
        String [] operatiiOrder = {"Add", "Edit","Delete", "View all"};
        JComboBox operatieOrder = new JComboBox(operatiiOrder);
        ComboBoxListenerOrder cb = new ComboBoxListenerOrder(frameOrder);
        operatieOrder.addActionListener(cb);
        opanel1.add(operatieOrder);

        finalPanelOrder.add(opanel0);
        finalPanelOrder.add(opanel1);

        finalPanelOrder.setLayout(new BoxLayout(finalPanelOrder, BoxLayout.Y_AXIS));
        frameOrder.setContentPane(finalPanelOrder);
        frameOrder.setContentPane(finalPanelOrder);
        frameOrder.setVisible(true);
        frameOrder.show();
    }
}
