package dao;

import connection.ConnectionFactory;
import model.Client;
import model.Produs;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdusDAO {
    protected static final Logger LOGGER = Logger.getLogger(ProdusDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO produs (id,nume,pret,cantitate_stoc)"
            + " VALUES (?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM produs where id = ?";
    private final static String updateStatementPretString="UPDATE produs SET pret =? WHERE id =?";
    private final static String updateStatementCantitateStocString="UPDATE produs SET cantitate_stoc =? WHERE id =?";
    private final static String updateStatementNumeString="UPDATE produs SET nume =? WHERE id =?";
    private final static String deleteStatementString = "DELETE FROM produs where id = ?";
    private final static String findAllStatementString = " SELECT * FROM produs";

    public static String[][] findAll() {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement selectAllStatement = null;
        ResultSet result = null;

        String[][] data = new String[20][4];
        try {
            selectAllStatement = dbConnection.prepareStatement(findAllStatementString);
            result = selectAllStatement.executeQuery();

            int i = 0;
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("nume");
                int pret = result.getInt("pret");
                int cantitate_stoc = result.getInt("cantitate_stoc");
                //System.out.println(id + " " + name + " " + address);
                data[i][0]= id + "";
                data[i][1] = name;
                data[i][2] = pret + "";
                data[i][3] = cantitate_stoc + "";
                i++;
            }

            return data;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProdusDAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(selectAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public Produs findById(int produsId) {
        Produs toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, produsId);
            rs = findStatement.executeQuery();
            rs.next();

            String nume = rs.getString("nume");
            int pret = rs.getInt("pret");
            int cantitate_stoc = rs.getInt("cantitate_stoc");

            toReturn = new Produs(produsId,nume,pret,cantitate_stoc);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProdusDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static int insertProdus(Produs produs) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, produs.getId());
            insertStatement.setString(2, produs.getNume());
            insertStatement.setInt(3, produs.getPret());
            insertStatement.setInt(4, produs.getCantitate_stoc());
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

    public static int deleteProdus(int id)
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

    public static int updatePretProdus(int id, int pretNou)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementPretString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1,pretNou);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }

        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, "ProdusDAO:updatePret " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return updatedId;
    }
    public static int updateCantiateStocProdus(int id, int cantitate_stoc)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementCantitateStocString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1,cantitate_stoc);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }

        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, "ProdusDAO:updateCantitateStoc " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return updatedId;
    }

    public static int updateNumeProdus(int id, String newName)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateStatementNumeString, Statement.RETURN_GENERATED_KEYS);
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
            LOGGER.log(Level.WARNING, "ProdusDAO:updateNume " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return updatedId;
    }


}
