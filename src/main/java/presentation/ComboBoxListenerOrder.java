package presentation;

import dao.OrderDAO;
import dao.ProdusDAO;
import model.Order;
import start.ReflectionExample;
import validators.OrderBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.List;

public class ComboBoxListenerOrder implements ActionListener {
    JFrame frameProduct = new JFrame("Order Add");
    JFrame oldFrameProduct;
    JPanel initialConentPane;

    public ComboBoxListenerOrder(JFrame oldFrameProduct)
    {
        this.oldFrameProduct = oldFrameProduct;
    }
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

            JLabel clabel0 = new JLabel("Add Order");
            clabel0.setHorizontalAlignment(SwingConstants.CENTER);
            clabel0.setVerticalAlignment(SwingConstants.CENTER);
            cpanel0.add(clabel0);

            JLabel clabel1 = new JLabel("Order id");
            JTextField tf1 = new JTextField(20);
            cpanel1.add(clabel1);
            cpanel1.add(tf1);

            JLabel clabel2 = new JLabel("Client id");
            JTextField tf2 = new JTextField(20);
            cpanel2.add(clabel2);
            cpanel2.add(tf2);

            JLabel clabel3 = new JLabel("Product id");
            JTextField tf3 = new JTextField(20);
            cpanel3.add(clabel3);
            cpanel3.add(tf3);

            JLabel clabel5 = new JLabel("Quantity");
            JTextField tf5 = new JTextField(20);
            cpanel5.add(clabel5);
            cpanel5.add(tf5);

            /*
            JLabel clabel6 = new JLabel("Quantity");
            JTextField tf6 = new JTextField(20);
            cpanel5.add(clabel5);
            cpanel5.add(tf5);
            */
            JButton backButtonAddClient = new JButton("Back");
            ButtonListenerBackAddClient listenerBack = new ButtonListenerBackAddClient(oldFrameProduct,frameProduct, initialConentPane);
            backButtonAddClient.addActionListener(listenerBack);
            //backButtonAddClient.setHorizontalAlignment(SwingConstants.CENTER);
            //backButtonAddClient.setVerticalAlignment(SwingConstants.CENTER);

            JButton addButtonOrder = new JButton("Add");
            ButtonListenerAddOrderInTable listenerAdd = new ButtonListenerAddOrderInTable(tf1,tf2,tf3,tf5, oldFrameProduct, frameProduct);
            addButtonOrder.addActionListener(listenerAdd);

            //JTable
            Order o = new Order(1,1,1,1,10);
            List<String> columns = ReflectionExample.retrieveProperties(o);
            String[] columnNames = new String[columns.size()];

            for(int i = 0; i < columns.size(); i++)
            {
                columnNames[i] = columns.get(i);
            }
            String [][] data = new String[20][5];
            OrderBLL orderBLL = new OrderBLL();
            OrderDAO orderDAO = new OrderDAO();

            if (listenerAdd.isInsertedSuccesfully() == true)
            {
                data = listenerAdd.getData();
            }
            else
            {
                data = orderDAO.findAll();
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);

            JScrollPane jps = new JScrollPane(table);
            cpanel4.add(jps);

            cpanel4.add(addButtonOrder);
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

            JLabel clabel0 = new JLabel("Add Order");
            clabel0.setHorizontalAlignment(SwingConstants.CENTER);
            clabel0.setVerticalAlignment(SwingConstants.CENTER);
            cpanel0.add(clabel0);

            JLabel clabel1 = new JLabel("Order id");
            JTextField tf1 = new JTextField(20);
            cpanel1.add(clabel1);
            cpanel1.add(tf1);

            JLabel clabel2 = new JLabel("Client id");
            JTextField tf2 = new JTextField(20);
            cpanel2.add(clabel2);
            cpanel2.add(tf2);

            JLabel clabel3 = new JLabel("Product id");
            JTextField tf3 = new JTextField(20);
            cpanel3.add(clabel3);
            cpanel3.add(tf3);

            JLabel clabel5 = new JLabel("Quantity");
            JTextField tf5 = new JTextField(20);
            cpanel5.add(clabel5);
            cpanel5.add(tf5);

            JButton backButtonAddClient = new JButton("Back");
            ButtonListenerBackAddClient listenerBack = new ButtonListenerBackAddClient(oldFrameProduct,frameProduct, initialConentPane);
            backButtonAddClient.addActionListener(listenerBack);
            //backButtonAddClient.setHorizontalAlignment(SwingConstants.CENTER);
            //backButtonAddClient.setVerticalAlignment(SwingConstants.CENTER);

            JButton addButtonOrder = new JButton("Update");
            ButtonListenerUpdateOrder listenerUpdate = new ButtonListenerUpdateOrder(tf1,tf2,tf3,tf5, oldFrameProduct, frameProduct);
            addButtonOrder.addActionListener(listenerUpdate);

            //JTable
            Order o = new Order(1,1,1,1,10);
            List<String> columns = ReflectionExample.retrieveProperties(o);
            String[] columnNames = new String[columns.size()];

            for(int i = 0; i < columns.size(); i++)
            {
                columnNames[i] = columns.get(i);
            }
            String [][] data = new String[20][5];
            OrderBLL orderBLL = new OrderBLL();
            OrderDAO orderDAO = new OrderDAO();

            if (listenerUpdate.isSuccessfullyUpdated() == true)
            {
                data = listenerUpdate.getData();
            }
            else
            {
                data = orderDAO.findAll();
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);

            JScrollPane jps = new JScrollPane(table);
            cpanel4.add(jps);

            cpanel4.add(addButtonOrder);
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

            JLabel clabel0 = new JLabel("Delete Order");
            clabel0.setHorizontalAlignment(SwingConstants.CENTER);
            clabel0.setVerticalAlignment(SwingConstants.CENTER);
            cpanel0.add(clabel0);

            JLabel clabel1 = new JLabel("Order id");
            JTextField tf1 = new JTextField(20);
            cpanel1.add(clabel1);
            cpanel1.add(tf1);

            JButton backButtonAddClient = new JButton("Back");
            ButtonListenerBackAddClient listenerBack = new ButtonListenerBackAddClient(oldFrameProduct,frameProduct, initialConentPane);
            backButtonAddClient.addActionListener(listenerBack);

            JButton deleteButtonProduct = new JButton("Delete");
            ButtonListenerDeleteOrder listenerDelete = new ButtonListenerDeleteOrder(tf1, frameProduct, oldFrameProduct);
            deleteButtonProduct.addActionListener(listenerDelete);

            //JTable
            Order o = new Order(1,1,1,1,10);
            List<String> columns = ReflectionExample.retrieveProperties(o);
            String[] columnNames = new String[columns.size()];

            for(int i = 0; i < columns.size(); i++)
            {
                columnNames[i] = columns.get(i);
            }
            String [][] data = new String[20][5];
            OrderBLL orderBLL = new OrderBLL();
            OrderDAO orderDAO = new OrderDAO();

            if (listenerDelete.isSuccessfullyDeleted() == true)
            {
                data = listenerDelete.getData();
            }
            else
            {
                data = orderDAO.findAll();
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

            Order o = new Order(1,1,1,1,10);
            List<String> columns = ReflectionExample.retrieveProperties(o);
            String[] columnNames = new String[columns.size()];

            for(int i = 0; i < columns.size(); i++)
            {
                columnNames[i] = columns.get(i);
            }
            String [][] data = new String[20][5];

            OrderDAO orderDAO = new OrderDAO();
            data = orderDAO.findAll();
            /*
            Document doument = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

            document.open();
            */
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
