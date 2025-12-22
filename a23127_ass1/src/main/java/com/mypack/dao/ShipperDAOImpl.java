package com.mypack.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import org.springframework.stereotype.Repository;

import com.mypack.entity.Shippers;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class ShipperDAOImpl {

    @Autowired
    private DataSource dataSource;

    public List<Shippers> findAll() {
        List<Shippers> list = new ArrayList<>();
        try {
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Shippers");
            while (rs.next()) {
                Shippers c = new Shippers();
                c.setShipperID(rs.getInt("ShipperID"));
                c.setShipperName(rs.getString("ShipperName"));
                c.setPhone(rs.getString("Phone"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public Shippers findById(int id) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Shippers WHERE ShipperID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Shippers c = new Shippers();
                c.setShipperID(rs.getInt("ShipperID"));
                c.setShipperName(rs.getString("ShipperName"));
                c.setPhone(rs.getString("Phone"));
                return c;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;

    }

    public Shippers insert(Shippers c) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Shippers(ShipperName, Phone) VALUES (?, ?)");
            ps.setString(1, c.getShipperName());
            ps.setString(2, c.getPhone());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return c;
    }

    public Shippers update(Shippers c) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE Shippers SET ShipperName=?, Phone=? WHERE ShipperID=?");
            ps.setString(1, c.getShipperName());
            ps.setString(2, c.getPhone());
            ps.setInt(3, c.getShipperID());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return c;
    }

    public void delete(int id) {
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Shippers WHERE ShipperID = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
