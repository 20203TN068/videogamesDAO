package mx.edu.utez.model.category;

import mx.edu.utez.model.games.BeanGames;
import mx.edu.utez.model.games.DaoGames;
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

public class DaoCategory {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final private Logger CONSOLE = LoggerFactory.getLogger(DaoGames.class);
    public List<BeanCategory> findAllCategories() {
        List<BeanCategory> categoryList = new ArrayList<>();

        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_findCategories}");
            rs = cstm.executeQuery();

            while (rs.next()) {
                BeanCategory beanCategory = new BeanCategory();
                beanCategory.setIdCategory(rs.getInt("idCategory"));
                beanCategory.setName(rs.getString("name"));
                categoryList.add(beanCategory);
            }
        }catch (SQLException e) {
            System.out.println("DaoGames error: "+e.getMessage());
        }finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return categoryList;
    }
}
