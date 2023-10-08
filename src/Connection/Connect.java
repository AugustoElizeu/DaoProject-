package Connection;
import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Connect {
    public static Connection conn = null;
    //ABRE CONEXÃO
    public static Connection getConnection(){
        if(conn==null){
            try{
                Properties prop = loadProperties();
                String url = prop.getProperty("bdurl");
                conn = DriverManager.getConnection(url,prop);
            }catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
        return conn;
    }
    //FECHA CONEXÃO
    public static void closeConnection(){
        if (conn!=null){
            try{
                conn.close();
            }
            catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }
    //CARREGA OS DADOS DO BANGO PRESENTE NO DB.PROPERTYS
    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.propertys")){
            Properties prop = new Properties();
            prop.load(fs);
            return prop;
        }catch (IOException e){
            throw new DBException(e.getMessage());
        }
    }
    public static void closeStatement(Statement st){
        if(st!=null){
            try{
                st.close();
            }catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }
    public static void closeResultSet(ResultSet rs){
        if(rs!=null){
            try{
                rs.close();
            }catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }

}
