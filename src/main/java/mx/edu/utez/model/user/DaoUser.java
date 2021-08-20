package mx.edu.utez.model.user;

import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.model.game.BeanGames;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DaoUser {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final private Logger CONSOLE = LoggerFactory.getLogger(DaoUser.class);

    public boolean createSession(String email, String password) {
        boolean flag = false;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM user WHERE email = ? AND password = ?");
            cstm.setString(1, email);
            cstm.setString(2, password);
            rs = cstm.executeQuery();

            if (rs.next()) {
                flag = true;
            }
        }catch (SQLException e) {
            System.out.println("DaoUser error: "+e.getMessage());
        }finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return flag;
    }
    /*
    public boolean create(){
        boolean flag = false;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_()}");
            flag = cstm.execute();
        } catch (SQLException e) {
            System.out.println("DaoUser error: "+e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }
    public boolean update(){
        boolean flag = false;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_()}");
            flag = cstm.execute();
        } catch (SQLException e) {
            System.out.println("DaoUser error: "+e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }
    public boolean delete(){
        boolean flag = false;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_()}");
            flag = cstm.execute();
        } catch (SQLException e) {
            System.out.println("DaoUser error: "+e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm);
        }
        return flag;
    }
    public List<BeanGames> findAll(){
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_}");
            cstm.execute();
            while (rs.next()) {
            }
        }catch (SQLException e) {
            System.out.println("DaoUser error: "+e.getMessage());
        }finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return;
    }
    public BeanGames findById(){
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_()}");
            rs = cstm.executeQuery();
            if (rs.next()) {
            }
        } catch (SQLException e) {
            System.out.println("DaoGames error: "+e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return;
    }
    */
}
