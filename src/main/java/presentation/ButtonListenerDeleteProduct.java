package presentation;

import dao.ClientDAO;
import dao.ProdusDAO;
import model.Produs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerDeleteProduct implements ActionListener {
    private JTextField txtId;

    private JFrame frameProduct;

    private JFrame oldFrameProduct;
    private JPanel initialConentPane;

    private int id;

    private Produs toBeDeleted;
    private String [][] data = new String[20][4];

    boolean successfullyDeleted = false;

    public ButtonListenerDeleteProduct(JTextField txtId, JFrame frameProduct, JFrame oldFrameProduct) {
        this.txtId = txtId;
        this.frameProduct = frameProduct;
        this.oldFrameProduct = oldFrameProduct;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        id = Integer.parseInt(txtId.getText());
        ProdusDAO produsDAO = new ProdusDAO();

        int id1 = -1;

        id1 = produsDAO.deleteProdus(id);

        if(id != -1)
        {
            successfullyDeleted = true;
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
        //ProdusDAO produsDAO = new ProdusDAO();

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

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public boolean isSuccessfullyDeleted() {
        return successfullyDeleted;
    }

    public void setSuccessfullyDeleted(boolean successfullyDeleted) {
        this.successfullyDeleted = successfullyDeleted;
    }
}
