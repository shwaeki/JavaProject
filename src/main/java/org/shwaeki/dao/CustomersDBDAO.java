package org.shwaeki.dao;

import org.shwaeki.jdbc.ConnectionPool;
import org.shwaeki.models.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomersDBDAO implements CustomersDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public boolean isCustomerExists(String email, String password) {
        String SQL = "SELECT * FROM customer WHERE email = ? AND password = ?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setString(1, email);
            pstat.setString(2, password);
            ResultSet rs = pstat.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void addCustomer(Customer customer) {
        String SQL = "INSERT INTO `customer`(`id`, `firstName`,`lastName`, `email`, `password`) VALUES (?,?,?,?,?)";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, customer.getId());
            pstat.setString(2, customer.getFirstName());
            pstat.setString(3, customer.getFirstName());
            pstat.setString(4, customer.getEmail());
            pstat.setString(5, customer.getPassword());
            pstat.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateCustomer(Customer customer) {
        String SQL = "UPDATE `customer` SET `id`=?,`firstName`=?,`lastName`=?,`email`=?,`password`=? WHERE `id`=?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, customer.getId());
            pstat.setString(2, customer.getFirstName());
            pstat.setString(3, customer.getFirstName());
            pstat.setString(4, customer.getEmail());
            pstat.setString(5, customer.getPassword());
            pstat.setInt(6, customer.getId());
            pstat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(int customerID) {
        String SQL = "DELETE FROM `customers` WHERE `id`=?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, customerID);
            pstat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Customer> gatAllCustomer() {
        String SQL = "SELECT * FROM `customer`";

        ArrayList<Customer> customer = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String password = rs.getString("password");

                customer.add(new Customer(id, firstName, lastName, email, password));
                System.out.println(firstName);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customer;
    }

    @Override
    public Customer gatOneCustomer(int customerID) {
        String SQL = "SELECT * FROM `customer` WHERE `id` = ?";
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, customerID);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String password = rs.getString("password");
                return new Customer(id, firstName, lastName, email, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
