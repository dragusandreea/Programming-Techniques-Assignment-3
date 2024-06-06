package presentation;

import dao.ClientDAO;
import dao.ProdusDAO;
import model.Client;
import model.Produs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerAddProductInTable implements ActionListener {
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtQuantityInStock;

    private JPanel cpanel4;
    private JFrame oldFrameProduct;
    private JFrame frameProduct;
    private JPanel initialConentPane;

    private int id;
    private String name;
    private int price;
    private int quantityInStock;

    private Produs toBeInserted;
    private boolean insertedSuccesfully = false;
    private String [][] data = new String[20][4];

    public ButtonListenerAddProductInTable(JTextField txtId, JTextField txtName, JTextField txtPrice, JTextField txtQuantityInStock, JPanel cpanel4, JFrame oldFrameProduct, JFrame frameProduct) {
        this.txtId = txtId;
        this.txtName = txtName;
        this.txtPrice = txtPrice;
        this.txtQuantityInStock = txtQuantityInStock;

        this.cpanel4 = cpanel4;
        this.oldFrameProduct = oldFrameProduct;
        this.frameProduct = frameProduct;

    }
    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        id = Integer.parseInt(txtId.getText());
        name = txtName.getText();
        price = Integer.parseInt(txtPrice.getText());
        quantityInStock = Integer.parseInt(txtQuantityInStock.getText());

        toBeInserted = new Produs(id,name,price,quantityInStock);
        ProdusDAO produsDAO = new ProdusDAO();
        int insertedId = produsDAO.insertProdus(toBeInserted);

        if(insertedId != -1)
        {
            insertedSuccesfully = true;
            data = produsDAO.findAll();

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
        ButtonListenerAddProductInTable listenerAdd = new ButtonListenerAddProductInTable(tf1,tf2,tf3,tf5, cpanel4, frameProduct, oldFrameProduct);
        addButtonProduct.addActionListener(listenerAdd);

        //JTable

        String[] columnNames = {"Id", "Name", "Price", "Quantity in stock"};
        String [][] data = new String[20][4];
        //ProdusDAO produsDAO = new ProdusDAO();

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
