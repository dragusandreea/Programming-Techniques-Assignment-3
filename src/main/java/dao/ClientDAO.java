package dao;

import connection.ConnectionFactory;
import model.Client;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDAO {

    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO client (id,nume,adresa)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM client where id = ?";
    private final static String updateAddressStatementString="UPDATE client SET adresa =? WHERE id =?";
    private final static String updateNameStatementString="UPDATE client SET nume =? WHERE id =?";
    private final static String deleteStatementString = "DELETE FROM client where id = ?";
    private final static String findAllStatementString = " SELECT * FROM client";

    public static String[][] findAll() {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement selectAllStatement = null;
        ResultSet result = null;

        String[][] data = new String[20][3];
        try {
            selectAllStatement = dbConnection.prepareStatement(findAllStatementString);
            result = selectAllStatement.executeQuery();

            int i = 0;
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("nume");
                String address = result.getString("adresa");
                //System.out.println(id + " " + name + " " + address);
                data[i][0]= id + "";
                data[i][1] = name;
                data[i][2] = address;
                i++;
            }

            return data;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(selectAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public Client findById(int clientId) {
        Client toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, clientId);
            rs = findStatement.executeQuery();
            rs.next();

            String nume = rs.getString("nume");
            String adresa = rs.getString("adresa");
            toReturn = new Client(clientId, nume, adresa);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static int insertClient(Client client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, client.getId());
            insertStatement.setString(2, client.getNume());
            insertStatement.setString(3, client.getAdresa());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int deleteClient(int id)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int deletedId = -1;

        try
        {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString, Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();

            ResultSet rs = deleteStatement.getGeneratedKeys();
            if (rs.next()) {
                deletedId = rs.getInt(1);
            }

        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

        return deletedId;
    }

    public static int updateClientAddress(int id, String newAddress)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateAddressStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1,newAddress);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }

        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, "ClientDAO:updateAddress " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return updatedId;
    }

    public static int updateClientName(int id, String newName)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateNameStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1,newName);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }

        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, "ClientDAO:updateName" + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return updatedId;
    }


}
