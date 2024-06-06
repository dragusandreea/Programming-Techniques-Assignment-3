package presentation;

import dao.ClientDAO;
import dao.ProdusDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComboBoxListenerProduct implements ActionListener {
    JFrame frameProduct = new JFrame("Product Add");
    JFrame oldFrameProduct;
    JPanel initialConentPane;

    public ComboBoxListenerProduct(JFrame oldFrameProduct)
    {
        this.oldFrameProduct = oldFrameProduct;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String operatie = (String) cb.getSelectedItem();

        if(operatie.equals("Add"))
        {
            frameProduct.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frameProduct.setSize(500, 730);

            JPanel cpanel0 = new JPanel();
            JPanel cpanel1 = new JPanel();
            JPanel cpanel2 = new JPanel();
            JPanel cpanel3 = new JPanel();
            JPanel cpanel4 = new JPanel();
            JPanel cpanel5 = new JPanel();
            JPanel finalPanelAddProduct = new JPanel();

            JLabel clabel0 = new JLabel("Add Product");
            clabel0.setHorizontalAlignment(SwingConstants.CENTER);
            clabel0.setVerticalAlignment(SwingConstants.CENTER);
            cpanel0.add(clabel0);

            JLabel clabel1 = new JLabel("Product id");
            JTextField tf1 = new JTextField(20);
            cpanel1.add(clabel1);
            cpanel1.add(tf1);

            JLabel clabel2 = new JLabel("Product name");
            JTextField tf2 = new JTextField(20);
            cpanel2.add(clabel2);
            cpanel2.add(tf2);

            JLabel clabel3 = new JLabel("Product price");
            JTextField tf3 = new JTextField(20);
            cpanel3.add(clabel3);
            cpanel3.add(tf3);

            JLabel clabel5 = new JLabel("Quantity in stock");
            JTextField tf5 = new JTextField(20);
            cpanel5.add(clabel5);
            cpanel5.add(tf5);

            JButton backButtonAddClient = new JButton("Back");
            ButtonListenerBackAddClient listenerBack = new ButtonListenerBackAddClient(oldFrameProduct,frameProduct, initialConentPane);
            backButtonAddClient.addActionListener(listenerBack);
            //backButtonAddClient.setHorizontalAlignment(SwingConstants.CENTER);
            //backButtonAddClient.setVerticalAlignment(SwingConstants.CENTER);

            JButton addButtonProduct = new JButton("Add");
            ButtonListenerAddProductInTable listenerAdd = new ButtonListenerAddProductInTable(tf1,tf2,tf3,tf5, cpanel4, oldFrameProduct, frameProduct);
            addButtonProduct.addActionListener(listenerAdd);

            //JTable

            String[] columnNames = {"Id", "Name", "Price", "Quantity in stock"};
            String [][] data = new String[20][4];
            ProdusDAO produsDAO = new ProdusDAO();

            if (listenerAdd.isInsertedSuccesfully() == true)
            {
                data = listenerAdd.getData();
            }
            else
            {
                data = produsDAO.findAll();
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);

            JScrollPane jps = new JScrollPane(table);
            cpanel4.add(jps);

            cpanel4.add(addButtonProduct);
            cpanel4.add(backButtonAddClient);

            finalPanelAddProduct.add(cpanel0);
            finalPanelAddProduct.add(cpanel1);
            finalPanelAddProduct.add(cpanel2);
            finalPanelAddProduct.add(cpanel3);
            finalPanelAddProduct.add(cpanel5);
            finalPanelAddProduct.add(cpanel4);

            finalPanelAddProduct.setLayout(new BoxLayout(finalPanelAddProduct, BoxLayout.Y_AXIS));

            frameProduct.setContentPane(finalPanelAddProduct);
            frameProduct.setVisible(true);
            frameProduct.show();

        }

        if(operatie.equals("Edit"))
        {
            frameProduct.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frameProduct.setSize(500, 730);

            JPanel cpanel0 = new JPanel();
            JPanel cpanel1 = new JPanel();
            JPanel cpanel2 = new JPanel();
            JPanel cpanel3 = new JPanel();
            JPanel cpanel4 = new JPanel();
            JPanel cpanel5 = new JPanel();
            JPanel finalPanelAddProduct = new JPanel();

            JLabel clabel0 = new JLabel("Edit Product");
            clabel0.setHorizontalAlignment(SwingConstants.CENTER);
            clabel0.setVerticalAlignment(SwingConstants.CENTER);
            cpanel0.add(clabel0);

            JLabel clabel1 = new JLabel("Product id");
            JTextField tf1 = new JTextField(20);
            cpanel1.add(clabel1);
            cpanel1.add(tf1);

            JLabel clabel2 = new JLabel("Product name");
            JTextField tf2 = new JTextField(20);
            cpanel2.add(clabel2);
            cpanel2.add(tf2);

            JLabel clabel3 = new JLabel("Product price");
            JTextField tf3 = new JTextField(20);
            cpanel3.add(clabel3);
            cpanel3.add(tf3);

            JLabel clabel5 = new JLabel("Quantity in stock");
            JTextField tf5 = new JTextField(20);
            cpanel5.add(clabel5);
            cpanel5.add(tf5);

            JButton backButtonAddClient = new JButton("Back");
            ButtonListenerBackAddClient listenerBack = new ButtonListenerBackAddClient(oldFrameProduct,frameProduct, initialConentPane);
            backButtonAddClient.addActionListener(listenerBack);
            //backButtonAddClient.setHorizontalAlignment(SwingConstants.CENTER);
            //backButtonAddClient.setVerticalAlignment(SwingConstants.CENTER);

            JButton updateButtonProduct = new JButton("Update");
            ButtonListenerUpdateProduct listenerUpdate = new ButtonListenerUpdateProduct(tf1,tf2,tf3,tf5, frameProduct, oldFrameProduct);
            updateButtonProduct.addActionListener(listenerUpdate);

            //JTable

            String[] columnNames = {"Id", "Name", "Price", "Quantity in stock"};
            String [][] data = new String[20][4];
            ProdusDAO produsDAO = new ProdusDAO();

            if (listenerUpdate.isSuccessfullyUpdated() == true)
            {
                data = listenerUpdate.getData();
            }
            else
            {
                data = produsDAO.findAll();
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);

            JScrollPane jps = new JScrollPane(table);
            cpanel4.add(jps);

            cpanel4.add(updateButtonProduct);
            cpanel4.add(backButtonAddClient);

            finalPanelAddProduct.add(cpanel0);
            finalPanelAddProduct.add(cpanel1);
            finalPanelAddProduct.add(cpanel2);
            finalPanelAddProduct.add(cpanel3);
            finalPanelAddProduct.add(cpanel5);
            finalPanelAddProduct.add(cpanel4);

            finalPanelAddProduct.setLayout(new BoxLayout(finalPanelAddProduct, BoxLayout.Y_AXIS));

            frameProduct.setContentPane(finalPanelAddProduct);
            frameProduct.setVisible(true);
            frameProduct.show();

        }
        if(operatie.equals("Delete"))
        {
            frameProduct.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frameProduct.setSize(500, 730);

            JPanel cpanel0 = new JPanel();
            JPanel cpanel1 = new JPanel();
            JPanel cpanel2 = new JPanel();
            JPanel cpanel3 = new JPanel();
            JPanel cpanel4 = new JPanel();
            JPanel cpanel5 = new JPanel();
            JPanel finalPanelAddProduct = new JPanel();

            JLabel clabel0 = new JLabel("Delete Product");
            clabel0.setHorizontalAlignment(SwingConstants.CENTER);
            clabel0.setVerticalAlignment(SwingConstants.CENTER);
            cpanel0.add(clabel0);

            JLabel clabel1 = new JLabel("Product id");
            JTextField tf1 = new JTextField(20);
            cpanel1.add(clabel1);
            cpanel1.add(tf1);

            JButton backButtonAddClient = new JButton("Back");
            ButtonListenerBackAddClient listenerBack = new ButtonListenerBackAddClient(oldFrameProduct,frameProduct, initialConentPane);
            backButtonAddClient.addActionListener(listenerBack);

            JButton deleteButtonProduct = new JButton("Delete");
            ButtonListenerDeleteProduct listenerDelete = new ButtonListenerDeleteProduct(tf1, frameProduct, oldFrameProduct);
            deleteButtonProduct.addActionListener(listenerDelete);

            String[] columnNames = {"Id", "Name", "Price", "Quantity in stock"};
            String [][] data = new String[20][4];
            ProdusDAO produsDAO = new ProdusDAO();

            if (listenerDelete.isSuccessfullyDeleted() == true)
            {
                data = listenerDelete.getData();
            }
            else
            {
                data = produsDAO.findAll();
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);

            JScrollPane jps = new JScrollPane(table);
            cpanel4.add(jps);

            cpanel4.add(deleteButtonProduct);
            cpanel4.add(backButtonAddClient);

            finalPanelAddProduct.add(cpanel0);
            finalPanelAddProduct.add(cpanel1);
            finalPanelAddProduct.add(cpanel2);
            finalPanelAddProduct.add(cpanel3);
            finalPanelAddProduct.add(cpanel5);
            finalPanelAddProduct.add(cpanel4);

            finalPanelAddProduct.setLayout(new BoxLayout(finalPanelAddProduct, BoxLayout.Y_AXIS));

            frameProduct.setContentPane(finalPanelAddProduct);
            frameProduct.setVisible(true);
            frameProduct.show();

        }
        if(operatie.equals("View all"))
        {
            frameProduct.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frameProduct.setSize(500, 730);

            JPanel vpanel0 = new JPanel();
            JPanel vpanel1 = new JPanel();
            JPanel vpanel4 = new JPanel();
            JPanel finalPanelViewAllClient = new JPanel();

            JLabel vlabel0 = new JLabel("View All Products");
            vlabel0.setHorizontalAlignment(SwingConstants.CENTER);
            vlabel0.setVerticalAlignment(SwingConstants.CENTER);
            vpanel0.add(vlabel0);


            JButton backButtonViewAllClient = new JButton("Back");
            ButtonListenerBackAddClient listenerBack = new ButtonListenerBackAddClient(oldFrameProduct,frameProduct, initialConentPane);
            backButtonViewAllClient.addActionListener(listenerBack);
            vpanel1.add(backButtonViewAllClient);


            //JTable

            String[] columnNames = {"Id", "Name", "Price","Quantity In Stock"};
            String [][] data = new String[20][4];
            ProdusDAO produsDAO = new ProdusDAO();
            data = produsDAO.findAll();


            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);

            JScrollPane jps = new JScrollPane(table);
            vpanel4.add(jps);


            finalPanelViewAllClient.add(vpanel0);
            finalPanelViewAllClient.add(vpanel4);
            finalPanelViewAllClient.add(vpanel1);

            finalPanelViewAllClient.setLayout(new BoxLayout(finalPanelViewAllClient, BoxLayout.Y_AXIS));

            frameProduct.setContentPane(finalPanelViewAllClient);
            frameProduct.setVisible(true);
            frameProduct.show();
        }

    }
}
