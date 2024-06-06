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

public class ButtonListenerDeleteOrder implements ActionListener {
    private JTextField txtId;

    private JFrame frameProduct;

    private JFrame oldFrameProduct;
    private JPanel initialConentPane;

    private int id;

    private Order toBeDeleted;
    private String [][] data = new String[20][5];

    boolean successfullyDeleted = false;

    public ButtonListenerDeleteOrder(JTextField txtId, JFrame frameProduct, JFrame oldFrameProduct) {
        this.txtId = txtId;
        this.frameProduct = frameProduct;
        this.oldFrameProduct = oldFrameProduct;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        id = Integer.parseInt(txtId.getText());

        OrderBLL orderBLL = new OrderBLL();
        int id1 = -1;

       orderBLL.deleteOrder(id);
       successfullyDeleted = true;
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
       // OrderBLL orderBLL = new OrderBLL();
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
