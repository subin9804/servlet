package ch06;

import ch06.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceDAO {

    Connection conn = null;
    PreparedStatement pstmt;
    final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final String JDBC_URL = "jdbc:mysql://localhost:3306/jwbook?serverTimezone=Asia/Seoul";

    public void open() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(JDBC_URL, "root", "1111");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void close() {
        try {
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
//        open();
        try {
            pstmt = conn.prepareStatement("select * from jwbook.product");
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                Product p = new Product();
                p.setId(rs.getString("id"));
                p.setName(rs.getString("name"));
                p.setMaker(rs.getString("maker"));
                p.setPrice(rs.getInt("price"));
                p.setDate(rs.getString("date"));

                products.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        finally {
//            close();
//        }

        return products;
    }

    public Product findById(String id) {
        Product p = new Product();
        try {
            pstmt = conn.prepareStatement("SELECT * FROM product WHERE id = ?");
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

            rs.next();
            p.setId(rs.getString("id"));
            p.setName(rs.getString("name"));
            p.setMaker(rs.getString("maker"));
            p.setPrice(rs.getInt("price"));
            p.setDate(rs.getString("date"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    public void insert(Product product) {
        try {
            pstmt = conn.prepareStatement("INSERT INTO product VALUES(?, ?, ?, ?, ?);");
            pstmt.setString(1, product.getId());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getMaker());
            pstmt.setInt(4, product.getPrice());
            pstmt.setString(5, product.getDate());
            int res = pstmt.executeUpdate();
            if(res == 1) {
                System.out.println("등록 완료");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update (String id, int price) {
        try {
            pstmt = conn.prepareStatement("UPDATE product SET price=? WHERE id=?");
            pstmt.setInt(1, price);
            pstmt.setString(2, id);
            int res = pstmt.executeUpdate();
            if(res == 1) {
                System.out.println("수정 완료");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void delete(String id) {
        try {
            pstmt = conn.prepareStatement("DELETE FROM product WHERE id = ?");
            pstmt.setString(1, id);
            int res = pstmt.executeUpdate();
            if(res == 1) {
                System.out.println("삭제 완료");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public ProductServiceDAO() {

    }
}
