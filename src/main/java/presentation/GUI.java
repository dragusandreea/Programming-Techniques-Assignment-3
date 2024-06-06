package presentation;

import javax.swing.*;

public class GUI {
    public static void guiStart()
    {
        //frame Client

        //frame Product
        JFrame frameProduct = new JFrame("Product");
        frameProduct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameProduct.setSize(500, 730);

        JPanel ppanel0 = new JPanel();
        JPanel ppanel1 = new JPanel();
        JPanel finalPanelProduct = new JPanel();

        JLabel plabel0 = new JLabel("Product");
        plabel0.setHorizontalAlignment(SwingConstants.CENTER);
        plabel0.setVerticalAlignment(SwingConstants.CENTER);
        ppanel0.add(plabel0);

        JLabel plabel1 = new JLabel("Product Operation");
        String [] operatiiProduct = {"Add", "Edit","Delete", "View all"};
        JComboBox operatieProduct = new JComboBox(operatiiProduct);

        ppanel1.add(plabel1);
        ppanel1.add(operatieProduct);

        finalPanelProduct.add(ppanel0);
        finalPanelProduct.add(ppanel1);

        finalPanelProduct.setLayout(new BoxLayout(finalPanelProduct, BoxLayout.Y_AXIS));
        frameProduct.setContentPane(finalPanelProduct);

        //frame Order
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

        JLabel olabel1 = new JLabel("Product id");
        JTextField otf1 = new JTextField(10);
        opanel1.add(olabel1);
        opanel1.add(otf1);

        JLabel olabel2 = new JLabel("Client id");
        JTextField otf2 = new JTextField(10);
        opanel2.add(olabel2);
        opanel2.add(otf2);

        JLabel olabel3 = new JLabel("Quantity");
        JTextField otf3 = new JTextField(10);
        opanel3.add(olabel3);
        opanel3.add(otf3);

        finalPanelOrder.add(opanel0);
        finalPanelOrder.add(opanel1);
        finalPanelOrder.add(opanel2);
        finalPanelOrder.add(opanel3);

        finalPanelOrder.setLayout(new BoxLayout(finalPanelOrder, BoxLayout.Y_AXIS));
        frameOrder.setContentPane(finalPanelOrder);

        //frame Principal
        JFrame frame = new JFrame ("Orders Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 730);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel finalPanel = new JPanel();

        JLabel label1 = new JLabel("Orders Management");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setVerticalAlignment(SwingConstants.CENTER);
        panel1.add(label1);

        JButton buttonClient = new JButton("Client");
        ButtonListenerPrincipalClient listenerClient = new ButtonListenerPrincipalClient();
        buttonClient.addActionListener(listenerClient);
        panel2.add(buttonClient);

        JButton buttonProduct = new JButton("Product");
        ButtonListenerPrincipalProduct listenerProduct = new ButtonListenerPrincipalProduct();
        buttonProduct.addActionListener(listenerProduct);
        panel2.add(buttonProduct);

        JButton buttonOrder = new JButton("Order");
        ButtonListenerPrincipalOrder listenerOrder = new ButtonListenerPrincipalOrder();
        buttonOrder.addActionListener(listenerOrder);
        panel2.add(buttonOrder);

        finalPanel.add(panel1);
        finalPanel.add(panel2);

        finalPanel.setLayout(new BoxLayout(finalPanel, BoxLayout.Y_AXIS));
        frame.setContentPane(finalPanel);
        frame.setVisible(true);

    }
}
