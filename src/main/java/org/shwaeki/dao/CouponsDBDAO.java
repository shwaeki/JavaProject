package org.shwaeki.dao;

import org.shwaeki.jdbc.ConnectionPool;
import org.shwaeki.models.Coupon;

import java.sql.*;
import java.util.ArrayList;

public class CouponsDBDAO implements CouponsDAO {

    private final ConnectionPool connectionPool = ConnectionPool.getInstance();


    @Override
    public void addCoupon(Coupon coupon) {
        String SQL = "INSERT INTO `coupon` (`id`, `company_id`, `category_id`, `title`, `description`, `startDate`, `endDate`, `amount`, `price`, `image`) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, coupon.getId());
            pstat.setInt(2, coupon.getCompanyID());
            pstat.setInt(3, coupon.getCompanyID());
            pstat.setString(4, coupon.getTitle());
            pstat.setString(5, coupon.getDescription());
            pstat.setDate(6, (Date) coupon.getStartDate());
            pstat.setDate(7, (Date) coupon.getEndDate());
            pstat.setInt(8, coupon.getAmount());
            pstat.setDouble(9, coupon.getPrice());
            pstat.setString(10, coupon.getImage());
            pstat.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateCoupon(Coupon coupon) {
        String SQL = "UPDATE `coupons` SET `id`=?,`company_id`=?,`category_id`=?,`title`=?,`description`=?,`startDate`=? `endDate`=?,`amount`=?,`price`=?,`image`=? WHERE `id`=?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, coupon.getId());
            pstat.setInt(2, coupon.getCompanyID());
            pstat.setInt(3, coupon.getCompanyID());
            pstat.setString(4, coupon.getTitle());
            pstat.setString(5, coupon.getDescription());
            pstat.setDate(6, (Date) coupon.getStartDate());
            pstat.setDate(7, (Date) coupon.getEndDate());
            pstat.setInt(8, coupon.getAmount());
            pstat.setDouble(9, coupon.getPrice());
            pstat.setString(10, coupon.getImage());
            pstat.setInt(11, coupon.getId());
            pstat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCoupon(int couponID) {
        String SQL = "DELETE FROM `coupons` WHERE `id`=?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, couponID);
            pstat.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Coupon> gatAllCoupon() {
        String SQL = "SELECT * FROM `coupon`";

        ArrayList<Coupon> coupon = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                int id = rs.getInt("id");
                int company_id = rs.getInt("company_id");
                int category_id = rs.getInt("category_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                int amount = rs.getInt("amount");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                coupon.add(new Coupon(id, company_id, title, description, startDate, endDate, amount, price, image));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return coupon;
    }

    @Override
    public Coupon gatOneCoupon(int couponID) {
        String SQL = "SELECT * FROM `coupon` WHERE `id` = ?";
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, couponID);
            ResultSet rs = pstat.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int company_id = rs.getInt("company_id");
                int category_id = rs.getInt("category_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                int amount = rs.getInt("amount");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                return new Coupon(id, company_id, title, description, startDate, endDate, amount, price, image);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) {
        String SQL = "INSERT INTO `coustomer_vs_coupons`(`customer_id`, `coupon_id`) VALUES (?,?)";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, customerID);
            pstat.setInt(2, couponID);
            pstat.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCouponPurchase(int customerID, int couponID) {
        String SQL = "DELETE FROM `coustomer_vs_coupons` WHERE `customer_id` =? AND `coupon_id` =?";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement pstat = connection.prepareStatement(SQL);
            pstat.setInt(1, customerID);
            pstat.setInt(2, couponID);
            pstat.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
