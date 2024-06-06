package dao;

import connection.ConnectionFactory;
import model.Order;
import model.Produs;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orders_management.order(id_comanda,id_client,id_produs,cantitate,pret)"
            + " VALUES (?,?,?,?,?)";
    private final static String findStatementString = "SELECT * FROM orders_management.order where id_comanda = ?";
    private final static String updateCantitateStatementString="UPDATE orders_management.order SET cantitate =? WHERE id_comanda =?";
    private final static String updatePretStatementString="UPDATE orders_management.order SET pret =? WHERE id_comanda =?";
    private final static String updateIdClientStatementString="UPDATE orders_management.order SET id_client =? WHERE id_comanda =?";
    private final static String updateIdProdusStatementString="UPDATE orders_management.order SET id_produs =? WHERE id_comanda =?";
    private final static String deleteStatementString = "DELETE FROM orders_management.order where id_comanda = ?";
    private final static String findAllStatementString = " SELECT * FROM orders_management.order";

    public static String[][] findAll() {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement selectAllStatement = null;
        ResultSet result = null;

        String[][] data = new String[20][5];
        try {
            selectAllStatement = dbConnection.prepareStatement(findAllStatementString);
            result = selectAllStatement.executeQuery();

            int i = 0;
            while (result.next()) {
                int id_comanda = result.getInt("id_comanda");
                int id_client = result.getInt("id_client");
                int id_produs = result.getInt("id_produs");
                int cantitate = result.getInt("cantitate");
                int pret = result.getInt("pret");

                //System.out.println(id + " " + name + " " + address);
                data[i][0]= id_comanda + "";
                data[i][1] = id_client + "";
                data[i][2] = id_produs + "";
                data[i][3] = cantitate + "";
                data[i][4] = pret + "";
                i++;
            }

            return data;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(selectAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return null;
    }

    public Order findById(int id_comanda) {
        Order toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, id_comanda);
            rs = findStatement.executeQuery();
            rs.next();

            int id_client = rs.getInt("id_client");
            int id_produs = rs.getInt("id_produs");
            int cantitate = rs.getInt("cantitate");
            int pret= rs.getInt("pret");


            toReturn = new Order(id_comanda,id_client,id_produs,cantitate,pret);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    public static int insertOrder(Order order) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getId_comanda());
            insertStatement.setInt(2, order.getId_client());
            insertStatement.setInt(3, order.getId_produs());
            insertStatement.setInt(4, order.getCantitate());
            ProdusDAO produsDAO = new ProdusDAO();
            Produs produs = produsDAO.findById(order.getId_produs());
            insertStatement.setInt(5, produs.getPret() * order.getCantitate());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static int deleteOrder(int id)
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
            LOGGER.log(Level.WARNING, "OrderDAO:delete " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }

        return deletedId;
    }

    public static int updateCantitateOrder(int id_comanda, int cantitate)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateCantitateStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1,cantitate);
            updateStatement.setInt(2, id_comanda);
            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }
            OrderDAO orderDAO = new OrderDAO();
            Order order = orderDAO.findById(id_comanda);

            ProdusDAO produsDAO = new ProdusDAO();
            Produs produs = produsDAO.findById(order.getId_produs());

            orderDAO.updatePretOrder(order.getId_comanda(),produs.getPret() * order.getCantitate());

        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, "OrderDAO:updateCantitate " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return updatedId;
    }

    public static int updatePretOrder(int id_comanda, int pret)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;

        try
        {
            updateStatement = dbConnection.prepareStatement(updatePretStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1,pret);
            updateStatement.setInt(2, id_comanda);
            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }

        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, "OrderDAO:updatePret " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return updatedId;
    }
    public static int updateIdProdusOrder(int id_comanda, int id_produs)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updatedId = -1;

        try
        {
            updateStatement = dbConnection.prepareStatement(updateIdProdusStatementString, Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1,id_produs);
            updateStatement.setInt(2, id_comanda);
            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }
            OrderDAO orderDAO = new OrderDAO();
            Order order = orderDAO.findById(id_comanda);

            ProdusDAO produsDAO = new ProdusDAO();
            Produs produs = produsDAO.findById(order.getId_produs());

            orderDAO.updatePretOrder(order.getId_comanda(),produs.getPret() * order.getCantitate());

        }
        catch(SQLException e)
        {
            LOGGER.log(Level.WARNING, "OrderDAO:updateIdProdus " + e.getMessage());
        }
        finally
        {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return updatedId;
    }



}
