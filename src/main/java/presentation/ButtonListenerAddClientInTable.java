package presentation;

import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListenerAddClientInTable implements ActionListener {
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtAddress;

    private JPanel cpanel4;
    private JFrame oldFrameClient;
    private JFrame frameClient;
    private JPanel initialConentPane;

    private int id;
    private String name;
    private String address;

    private Client toBeInserted;
    private boolean insertedSuccesfully = false;
    private String [][] data = new String[20][3];

    public ButtonListenerAddClientInTable(JTextField id, JTextField name, JTextField address, JPanel cpanel4, JFrame frameClient, JFrame old) {
        this.txtId = id;
        this.txtName = name;
        this.txtAddress = address;
        this.cpanel4 = cpanel4;
        this.frameClient = frameClient;
        this.oldFrameClient = old;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       id = Integer.parseInt(txtId.getText());
       name = txtName.getText();
       address = txtAddress.getText();

       toBeInserted = new Client(id,name,address);
       ClientDAO clientDAO = new ClientDAO();
       int insertedId = clientDAO.insertClient(toBeInserted);

       if(insertedId != -1)
       {
           insertedSuccesfully = true;
           data = clientDAO.findAll();

       }

        frameClient.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frameClient.setSize(500, 730);

        JPanel cpanel0 = new JPanel();
        JPanel cpanel1 = new JPanel();
        JPanel cpanel2 = new JPanel();
        JPanel cpanel3 = new JPanel();
        JPanel cpanel4 = new JPanel();
        JPanel finalPanelAddClient = new JPanel();

        JLabel clabel0 = new JLabel("Add Client");
        clabel0.setHorizontalAlignment(SwingConstants.CENTER);
        clabel0.setVerticalAlignment(SwingConstants.CENTER);
        cpanel0.add(clabel0);

        JLabel clabel1 = new JLabel("Client id");
        JTextField tf1 = new JTextField(20);
        cpanel1.add(clabel1);
        cpanel1.add(tf1);

        JLabel clabel2 = new JLabel("Client name");
        JTextField tf2 = new JTextField(20);
        cpanel2.add(clabel2);
        cpanel2.add(tf2);

        JLabel clabel3 = new JLabel("Client address");
        JTextField tf3 = new JTextField(20);
        cpanel3.add(clabel3);
        cpanel3.add(tf3);



        JButton backButtonAddClient = new JButton("Back");
        ButtonListenerBackAddClient listenerBack = new ButtonListenerBackAddClient(oldFrameClient,frameClient, initialConentPane);
        backButtonAddClient.addActionListener(listenerBack);
        //backButtonAddClient.setHorizontalAlignment(SwingConstants.CENTER);
        //backButtonAddClient.setVerticalAlignment(SwingConstants.CENTER);

        JButton addButtonClient = new JButton("Add");
        ButtonListenerAddClientInTable listenerAdd = new ButtonListenerAddClientInTable(tf1,tf2,tf3, cpanel4, frameClient, oldFrameClient);
        addButtonClient.addActionListener(listenerAdd);

        //JTable

        String[] columnNames = {"Id", "Name", "Address"};
        String [][] data = new String[20][3];
        //ClientDAO clientDAO = new ClientDAO();

        if (listenerAdd.isInsertedSuccesfully() == true)
        {
            data = listenerAdd.getData();
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
        cpanel4.add(jps);



        cpanel4.add(addButtonClient);
        cpanel4.add(backButtonAddClient);

        finalPanelAddClient.add(cpanel0);
        finalPanelAddClient.add(cpanel1);
        finalPanelAddClient.add(cpanel2);
        finalPanelAddClient.add(cpanel3);
        finalPanelAddClient.add(cpanel4);

        finalPanelAddClient.setLayout(new BoxLayout(finalPanelAddClient, BoxLayout.Y_AXIS));

        frameClient.setContentPane(finalPanelAddClient);
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

    public Client getToBeInserted() {
        return toBeInserted;
    }

    public void setToBeInserted(Client toBeInserted) {
        this.toBeInserted = toBeInserted;
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
