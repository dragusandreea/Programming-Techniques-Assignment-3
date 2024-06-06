package validators;

import dao.ClientDAO;
import model.Client;

import java.util.NoSuchElementException;

public class ClientBLL {
    private ClientDAO clientDAO;
    public ClientBLL()
    {
        clientDAO = new ClientDAO();
    }
    public int insert(Client client)
    {
     return ClientDAO.insertClient(client);
    }

    public int delete(int id)
    {
        return ClientDAO.deleteClient(id);
    }

    public int updateAddress(int id, String newAddress)
    {
        return ClientDAO.updateClientAddress(id, newAddress);
    }

    public int updateName(int id, String newName)
    {
        return ClientDAO.updateClientName(id, newName);
    }

    public Client findClientById(int id)
    {

        Client c = clientDAO.findById(id);
        if( c == null)
        {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }

        return c;
    }
}
