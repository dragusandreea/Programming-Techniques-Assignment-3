package validators;

import dao.OrderDAO;
import dao.ProdusDAO;
import model.Client;
import model.Order;
import model.Produs;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.NoSuchElementException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

public class OrderBLL {
    private OrderDAO orderDAO = new OrderDAO();
    private static int orderNr = 0;
    public int insertOrder(Order order)
    {
        int id_produs = order.getId_produs();
        int cantitate = order.getCantitate();

        ProdusBLL produsBLL = new ProdusBLL();
        Produs produs = produsBLL.findProdusById(id_produs);

        ClientBLL clientBLL = new ClientBLL();
        Client client = clientBLL.findClientById(order.getId_client());

        if(produs != null && client != null) {

            if (cantitate > produs.getCantitate_stoc()) {
                throw new NoSuchElementException("The product with id =" + id_produs + "has not enough stock for order with id=" + order.getId_comanda());
            } else {
                orderDAO.insertOrder(order);
                produsBLL.updateCantitate(id_produs, produs.getCantitate_stoc() - order.getCantitate());

            }
        }
        return 1;
    }

    public void updateCantitate(int id_comanda, int newCantitate)
    {
        OrderBLL orderBLL = new OrderBLL();
        Order order = orderBLL.findById(id_comanda);

        int id_produs = order.getId_produs();
        int cantitate = order.getCantitate();

        ProdusBLL produsBLL = new ProdusBLL();
        Produs produs = produsBLL.findProdusById(id_produs);

        if(newCantitate > produs.getCantitate_stoc())
        {
            throw new NoSuchElementException("The product with id =" + id_produs + "has not enough stock for update of order with id="+order.getId_comanda());
        }
        else
        {
            orderDAO.updateCantitateOrder(id_comanda, newCantitate);
            produsBLL.updateCantitate(id_produs,produs.getCantitate_stoc() - newCantitate);
        }
    }

    public void updateIdProdusOrder(int id_comanda, int id_produs)
    {
        OrderBLL orderBLL = new OrderBLL();
        Order order = orderBLL.findById(id_comanda);

        ProdusBLL produsBLL = new ProdusBLL();
        Produs produs = produsBLL.findProdusById(id_produs);

        if(order.getCantitate() > produs.getCantitate_stoc())
        {
            throw new NoSuchElementException("The product with id =" + id_produs + "has not enough stock for update of order with id="+order.getId_comanda());
        }
        else
        {
            orderDAO.updateIdProdusOrder(id_comanda, id_produs);
            produsBLL.updateCantitate(id_produs, produs.getCantitate_stoc() - order.getCantitate());
        }
    }

    public void updatePretOrder(int id_comanda, int newPret)
    {
        OrderDAO.updatePretOrder(id_comanda,newPret);
    }

    public void deleteOrder(int id_comanda)
    {
        OrderBLL orderBLL = new OrderBLL();
        Order o = orderBLL.findById(id_comanda);

        if(o == null)
        {
            throw new NoSuchElementException("The order with id =" + id_comanda+ " was not found so it cannot be deleted!");
        }
        else
        {
            OrderDAO.deleteOrder(id_comanda);
        }

    }

    public Order findById(int id_comanda)
    {
        Order o = orderDAO.findById(id_comanda);
        if( o == null)
        {
            throw new NoSuchElementException("The order with id =" + id_comanda+ " was not found!");
        }
        else
        {
            return o;
        }
    }

    public static void bill(Order order)  {
        try {

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("Bill_for_Order_" + order.getId_comanda()+ ".pdf"));
            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.BLACK);
            Chunk chunk_bill = new Chunk(order.toString(), font);
            document.add(chunk_bill);
            document.close();
          }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
