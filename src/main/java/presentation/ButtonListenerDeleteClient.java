package presentation;

import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerDeleteClient implements ActionListener {
    private JTextField txtId;
    private JFrame frameClient;

    private JFrame oldFrameClient;
    private JPanel initialConentPane;

    private int id;

    private Client toBeDeleted;
    private String [][] data = new String[20][3];

    boolean successfullyDeleted = false;

    public ButtonListenerDeleteClient(JTextField txtId, JFrame frameClient, JFrame old) {
        this.txtId = txtId;
        this.frameClient = frameClient;
        this.oldFrameClient = old;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        id = Integer.parseInt(txtId.getText());
        ClientDAO clientDAO = new ClientDAO();

        int id1 = -1;

        id1 = clientDAO.deleteClient(id);

        if(id != -1)
        {
            successfullyDeleted = true;
        }

        frameClient.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frameClient.setSize(500, 730);

        JPanel dpanel0 = new JPanel();
        JPanel dpanel1 = new JPanel();
        JPanel dpanel2 = new JPanel();
        JPanel dpanel3 = new JPanel();
        JPanel dpanel4 = new JPanel();
        JPanel finalPanelDeleteClient = new JPanel();

        JLabel dlabel0 = new JLabel("Delete Client");
        dlabel0.setHorizontalAlignment(SwingConstants.CENTER);
        dlabel0.setVerticalAlignment(SwingConstants.CENTER);
        dpanel0.add(dlabel0);

        JLabel dlabel1 = new JLabel("Client id");
        JTextField dtf1 = new JTextField(20);
        dpanel1.add(dlabel1);
        dpanel1.add(dtf1);

        JButton backButtonDeleteClient = new JButton("Back");
        ButtonListenerBackAddClient listenerBack = new ButtonListenerBackAddClient(oldFrameClient,frameClient, initialConentPane);
        backButtonDeleteClient.addActionListener(listenerBack);

        JButton deleteButtonClient = new JButton("Delete");
        ButtonListenerDeleteClient listenerDelete = new ButtonListenerDeleteClient(dtf1,frameClient, oldFrameClient);
        deleteButtonClient.addActionListener(listenerDelete);

        //JTable

        String[] columnNames = {"Id", "Name", "Address"};
        String [][] data = new String[20][3];
        //ClientDAO clientDAO = new ClientDAO();

        if(listenerDelete.isSuccessfullyDeleted())
        {
            data = listenerDelete.getData();
        }
        else
        {
            data = clientDAO.findAll();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);

        JScrollPane jps = new JScrollPane(table);
        dpanel4.add(jps);

        dpanel4.add(deleteButtonClient);
        dpanel4.add(backButtonDeleteClient);

        finalPanelDeleteClient.add(dpanel0);
        finalPanelDeleteClient.add(dpanel1);
        finalPanelDeleteClient.add(dpanel4);

        finalPanelDeleteClient.setLayout(new BoxLayout(finalPanelDeleteClient, BoxLayout.Y_AXIS));

        frameClient.setContentPane(finalPanelDeleteClient);
        frameClient.setVisible(true);
        frameClient.show();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSuccessfullyDeleted() {
        return successfullyDeleted;
    }

    public void setSuccessfullyDeleted(boolean successfullyDeleted) {
        this.successfullyDeleted = successfullyDeleted;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
