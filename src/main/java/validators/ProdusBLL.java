package validators;

import dao.ClientDAO;
import dao.ProdusDAO;
import model.Client;
import model.Produs;

import java.util.NoSuchElementException;

public class ProdusBLL {
    private ProdusDAO produsDAO;
    public ProdusBLL()
    {
        produsDAO = new ProdusDAO();
    }

    public int insert(Produs produs)
    {
        return ProdusDAO.insertProdus(produs);
    }

    public int delete(int id)
    {
        return ProdusDAO.deleteProdus(id);
    }

    public int updatePret(int id, int pretNou)
    {
        return ProdusDAO.updatePretProdus(id, pretNou);
    }

    public int updateNume(int id, String numeNou)
    {
        return ProdusDAO.updateNumeProdus(id,numeNou);
    }

    public int updateCantitate(int id, int cantitate)
    {
        return ProdusDAO.updateCantiateStocProdus(id,cantitate);
    }

    public Produs findProdusById(int id)
    {

        Produs p = produsDAO.findById(id);
        if( p == null)
        {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }

        return p;
    }
}
