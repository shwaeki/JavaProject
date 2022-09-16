package org.shwaeki.dao;

import org.shwaeki.jdbc.ConnectionPool;
import org.shwaeki.models.Company;

import java.sql.*;
import java.util.ArrayList;

public class CompaniesDBDAO implements CompaniesDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public boolean isCompanyExists(String email, String password) {
        String SQL = "SELECT * FROM companies WHERE email = ? AND password = ?";

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
    public void addCompany(Company company) {
        String SQL = "INSERT INTO `companies`(`id`, `name`, `email`, `password`) VALUES (?,?,?,?)";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, company.getId());
            pstat.setString(2, company.getName());
            pstat.setString(3, company.getEmail());
            pstat.setString(4, company.getPassword());
            pstat.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateCompany(Company company) {
        String SQL = "UPDATE `companies` SET `id`=?,`name`=?,`email`=?,`password`=? WHERE `id`=?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, company.getId());
            pstat.setString(2, company.getName());
            pstat.setString(3, company.getEmail());
            pstat.setString(4, company.getPassword());
            pstat.setInt(5, company.getId());
            pstat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCompany(int companyID) {
        String SQL = "DELETE FROM `companies` WHERE `id`=?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, companyID);
            pstat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Company> gatAllCompany() {
        String SQL = "SELECT * FROM `companies`";

        ArrayList<Company> companies = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");

                companies.add(new Company(id, name, email, password));
                System.out.println(name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return companies;
    }

    @Override
    public Company gatOneCompany(int companyID) {
        String SQL = "SELECT * FROM `companies` WHERE `id` = ?";
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, companyID);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                return new Company(id, name, email, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
