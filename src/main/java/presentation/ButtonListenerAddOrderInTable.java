package presentation;

import dao.OrderDAO;
import dao.ProdusDAO;
import model.Order;
import model.Produs;
import start.ReflectionExample;
import validators.OrderBLL;
import validators.ProdusBLL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonListenerAddOrderInTable implements ActionListener {
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

    private Order toBeInserted;
    private boolean insertedSuccesfully = false;
    private String [][] data = new String[20][5];

    public ButtonListenerAddOrderInTable(JTextField txtIdComanda, JTextField txtIdClient, JTextField txtIdProdus, JTextField txtQuantity, JFrame oldFrameProduct, JFrame frameProduct) {
        this.txtIdComanda = txtIdComanda;
        this.txtIdClient = txtIdClient;
        this.txtIdProdus = txtIdProdus;
        this.txtQuantity = txtQuantity;
        this.oldFrameProduct = oldFrameProduct;
        this.frameProduct = frameProduct;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        id_comanda = Integer.parseInt(txtIdComanda.getText());
        id_client =  Integer.parseInt(txtIdClient.getText());
        id_produs = Integer.parseInt(txtIdProdus.getText());
        cantitate = Integer.parseInt(txtQuantity.getText());

        ProdusBLL produsBLL = new ProdusBLL();
        Produs p = produsBLL.findProdusById(id_produs);

        toBeInserted = new Order(id_comanda,id_client,id_produs,cantitate,cantitate * p.getPret());
        OrderBLL orderBLL = new OrderBLL();

        int insertedId = orderBLL.insertOrder(toBeInserted);

        OrderDAO orderDAO = new OrderDAO();

        if(insertedId != -1)
        {
            insertedSuccesfully = true;
            data =orderDAO .findAll();

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
        //OrderBLL orderBLL = new OrderBLL();
        //OrderDAO orderDAO = new OrderDAO();

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

    public boolean isInsertedSuccesfully() {
        return insertedSuccesfully;
    }

    public void setInsertedSuccesfully(boolean insertedSuccesfully) {
        this.insertedSuccesfully = insertedSuccesfully;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
