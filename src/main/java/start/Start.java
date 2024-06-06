package start;

import dao.AbstractDAO;
import model.Client;
import model.Order;
import model.Produs;
import presentation.GUI;
import validators.ClientBLL;
import validators.OrderBLL;
import validators.ProdusBLL;

import java.sql.SQLException;
import java.util.logging.Logger;

public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());
    public static void main(String[] args)
    {
        GUI gui = new GUI();
        gui.guiStart();

        OrderBLL orderBLL = new OrderBLL();
        Order order = orderBLL.findById(2);
        OrderBLL.bill(order);

    }
}
