package presentation;

import dao.OrderDAO;
import dao.ProdusDAO;
import model.Order;
import model.Produs;
import start.ReflectionExample;
import validators.OrderBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonListenerUpdateOrder implements ActionListener {
    private JTextField txtIdComanda;
    private JTextField txtIdClient;
    private JTextField txtIdProdus;
    private JTextField txtQuantity;


    private JPanel cpanel4;
    private JFrame oldFrameProduct;
    private JFrame frameProduct;
    private JPanel initialConentPane;

    private int id_comanda;
    private int id_client;
    private int id_produs;
    private int cantitate;
    private int pret;


    private Order toBeUpdated;
    private String [][] data = new String[20][5];

    boolean successfullyUpdated = false;

    public ButtonListenerUpdateOrder(JTextField txtIdComanda, JTextField txtIdClient, JTextField txtIdProdus, JTextField txtQuantity, JFrame oldFrameProduct, JFrame frameProduct) {
        this.txtIdComanda = txtIdComanda;
        this.txtIdClient = txtIdClient;
        this.txtIdProdus = txtIdProdus;
        this.txtQuantity = txtQuantity;
        this.oldFrameProduct = oldFrameProduct;
        this.frameProduct = frameProduct;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        OrderBLL orderBLL = new OrderBLL();
        OrderDAO orderDAO = new OrderDAO();

        int id1 = -1;
        int id2 = -1;
        int id3 = -1;
        int id4 = -1;

        String id_comandaa = txtIdComanda.getText();
        if(!id_comandaa.equals(""))
        {
            id_comanda = Integer.parseInt(txtIdComanda.getText());
            id1 = 1;

        }
        String q;
        q = txtIdClient.getText();
        if(!q.equals(""))
        {
            id_client = Integer.parseInt(txtIdClient.getText());
            id2 = 1;
        }

        q = txtIdProdus.getText();
        if(!q.equals(""))
        {
            id_produs = Integer.parseInt(txtIdProdus.getText());
            orderBLL.updateIdProdusOrder(id_comanda,id_produs);
            id3 = 1;
        }

        q = txtQuantity.getText();
        if(!q.equals(""))
        {
            cantitate = Integer.parseInt(txtQuantity.getText());
            orderBLL.updateCantitate(id_comanda,cantitate);
            id4 = 1;
        }



        if(id1 != -1 || id2 != -1 || id3 != -1 || id4!=-1)
        {
            setSuccessfullyUpdated(true);
            data = orderDAO.findAll();
        }

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
        //OrderBLL orderBLL = new OrderBLL();
        //OrderDAO orderDAO = new OrderDAO();

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

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public boolean isSuccessfullyUpdated() {
        return successfullyUpdated;
    }

    public void setSuccessfullyUpdated(boolean successfullyUpdated) {
        this.successfullyUpdated = successfullyUpdated;
    }
}
