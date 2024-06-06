package presentation;

import com.mysql.cj.xdevapi.Table;
import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class ComboBoxListener implements ActionListener {
    JFrame frameClient = new JFrame("Client Add");
    JFrame oldFrameClient;
    JPanel initialConentPane;
    public ComboBoxListener(JFrame oldFrameClient)
    {
        this.oldFrameClient = oldFrameClient;
        //this.initialConentPane = (JPanel) frameClient.getContentPane();
    }
    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox) e.getSource();
        String operatie = (String) cb.getSelectedItem();

        if(operatie.equals("Add"))
        {
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
            ClientDAO clientDAO = new ClientDAO();

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
        if(operatie.equals("Edit"))
        {
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
            ClientDAO clientDAO = new ClientDAO();

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
        if(operatie.equals("Delete"))
        {
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
            ClientDAO clientDAO = new ClientDAO();

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

        if(operatie.equals("View all"))
        {
            frameClient.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frameClient.setSize(500, 730);

            JPanel vpanel0 = new JPanel();
            JPanel vpanel1 = new JPanel();
            JPanel vpanel4 = new JPanel();
            JPanel finalPanelViewAllClient = new JPanel();

            JLabel vlabel0 = new JLabel("View All Clients");
            vlabel0.setHorizontalAlignment(SwingConstants.CENTER);
            vlabel0.setVerticalAlignment(SwingConstants.CENTER);
            vpanel0.add(vlabel0);


            JButton backButtonViewAllClient = new JButton("Back");
            ButtonListenerBackAddClient listenerBack = new ButtonListenerBackAddClient(oldFrameClient,frameClient, initialConentPane);
            backButtonViewAllClient.addActionListener(listenerBack);
            vpanel1.add(backButtonViewAllClient);


            //JTable

            String[] columnNames = {"Id", "Name", "Address"};
            String [][] data = new String[20][3];
            ClientDAO clientDAO = new ClientDAO();
            data = clientDAO.findAll();


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

            frameClient.setContentPane(finalPanelViewAllClient);
            frameClient.setVisible(true);
            frameClient.show();
        }

    }

}
