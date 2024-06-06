package presentation;

import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerUpdateClient implements ActionListener {
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtAddress;
    private JFrame frameClient;

    private JFrame oldFrameClient;
    private JPanel initialConentPane;

    private int id;
    private String name;
    private String address;

    private Client toBeUpdated;
    private String [][] data = new String[20][3];

    boolean successfullyUpdated = false;

    public ButtonListenerUpdateClient(JTextField txtId, JTextField txtName, JTextField txtAddress, JFrame frameClient, JFrame old) {
        this.txtId = txtId;
        this.txtName = txtName;
        this.txtAddress = txtAddress;
        this.frameClient = frameClient;
        this.oldFrameClient = old;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        id = Integer.parseInt(txtId.getText());
        name = txtName.getText();
        address = txtAddress.getText();

        ClientDAO clientDAO = new ClientDAO();

        int id1 = -1;
        int id2 = -1;

        if(!name.equals(""))
        {
             id1 = clientDAO.updateClientName(id,name);
        }
        if(!address.equals(""))
        {
            id2 = clientDAO.updateClientAddress(id,address);
        }

        if(id1 != -1 || id2 != -1)
        {
            setSuccessfullyUpdated(true);
            data = clientDAO.findAll();
        }

        frameClient.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frameClient.setSize(500, 730);

        JPanel upanel0 = new JPanel();
        JPanel upanel1 = new JPanel();
        JPanel upanel2 = new JPanel();
        JPanel upanel3 = new JPanel();
        JPanel upanel4 = new JPanel();
        JPanel finalPanelUpdateClient = new JPanel();

        JLabel clabel0 = new JLabel("Update Client");
        clabel0.setHorizontalAlignment(SwingConstants.CENTER);
        clabel0.setVerticalAlignment(SwingConstants.CENTER);
        upanel0.add(clabel0);

        JLabel ulabel1 = new JLabel("Client id");
        JTextField utf1 = new JTextField(20);
        upanel1.add(ulabel1);
        upanel1.add(utf1);

        JLabel ulabel2 = new JLabel("Client name");
        JTextField utf2 = new JTextField(20);
        upanel2.add(ulabel2);
        upanel2.add(utf2);

        JLabel ulabel3 = new JLabel("Client address");
        JTextField utf3 = new JTextField(20);
        upanel3.add(ulabel3);
        upanel3.add(utf3);

        JButton backButtonUpdateClient = new JButton("Back");
        ButtonListenerBackAddClient listenerBack = new ButtonListenerBackAddClient(oldFrameClient,frameClient, initialConentPane);
        backButtonUpdateClient.addActionListener(listenerBack);
        //backButtonAddClient.setHorizontalAlignment(SwingConstants.CENTER);
        //backButtonAddClient.setVerticalAlignment(SwingConstants.CENTER);

        JButton updateButtonClient = new JButton("Update");
        ButtonListenerUpdateClient listenerUpdate = new ButtonListenerUpdateClient(utf1,utf2,utf3,frameClient, oldFrameClient);
        updateButtonClient.addActionListener(listenerUpdate);

        //JTable

        String[] columnNames = {"Id", "Name", "Address"};
        String [][] data = new String[20][3];
       // ClientDAO clientDAO = new ClientDAO();

        if(listenerUpdate.isSuccessfullyUpdated())
        {
            data = listenerUpdate.getData();
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
        upanel4.add(jps);

        upanel4.add(updateButtonClient);
        upanel4.add(backButtonUpdateClient);

        finalPanelUpdateClient.add(upanel0);
        finalPanelUpdateClient.add(upanel1);
        finalPanelUpdateClient.add(upanel2);
        finalPanelUpdateClient.add(upanel3);
        finalPanelUpdateClient.add(upanel4);

        finalPanelUpdateClient.setLayout(new BoxLayout(finalPanelUpdateClient, BoxLayout.Y_AXIS));

        frameClient.setContentPane(finalPanelUpdateClient);
        frameClient.setVisible(true);
        frameClient.show();



    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Client getToBeUpdated() {
        return toBeUpdated;
    }

    public void setToBeUpdated(Client toBeUpdated) {
        this.toBeUpdated = toBeUpdated;
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
