package Interfaces;

import Entidades.Departamento;
import Entidades.Vendedor;
import Connection.Connect;
import Connection.DBException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendedorJDBCIMPL implements VendedorDao{

    private Connection conn;
    public VendedorJDBCIMPL(Connection conn){
        this.conn = conn;
    }
    public VendedorJDBCIMPL(){

    }
    @Override
    public void insert(Vendedor obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("Insert into seller"
                    + "(Name,Email,BaseSalaray,DepartmentId)"
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1,obj.getName());
            st.setString(2,obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getData().getTime()));
            st.setDouble(4,obj.getSalaray());
            st.setInt(5,obj.getDep().getId());
            int rowsaffected = st.executeUpdate();
            if(rowsaffected>0){
                ResultSet rs= st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setID(id);
                }
                Connect.closeResultSet(rs);
            }else{
                throw new DBException("Deu ruim");
            }
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            Connect.closeStatement(st);
        }

    }

    @Override
    public void update(Vendedor obj) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE seller \n" +
                            "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? \n" +
                            "WHERE Id = ?",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1,obj.getName());
            st.setString(2,obj.getEmail());
            st.setDate(3,new java.sql.Date(obj.getData().getTime()));
            st.setDouble(4,obj.getSalaray());
            st.setInt(5,obj.getDep().getId());
            st.setInt(6,obj.getID());
            st.executeUpdate();

        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            Connect.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("DELETE FROM seller \n" + "WHERE Id = ?",
                    Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,id);
            st.executeUpdate();

        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            Connect.closeStatement(st);
        }

    }

    @Override
    public Vendedor findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName \n" +
                    "FROM seller INNER JOIN department \n" +
                    "ON seller.DepartmentId = department.Id \n" +
                    "WHERE seller.Id = ?");
            st.setInt(1,id);
            rs= st.executeQuery();
            if (rs.next()){
                Departamento dep = instantiateDepartament(rs);
                Vendedor obj = instantiateVendedor(rs, dep);
                return obj;
            }
            return null;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            Connect.closeStatement(st);
            Connect.closeResultSet(rs);
        }
    }

    private Vendedor instantiateVendedor(ResultSet rs, Departamento dep) throws SQLException {
        Vendedor obj = new Vendedor();
        obj.setID(rs.getInt("ID"));
        obj.setName(rs.getString("Nome"));
        obj.setEmail(rs.getString("Email"));
        obj.setData(rs.getDate("Data"));
        obj.setSalaray(rs.getDouble("Salario"));
        obj.setDep(dep);
        return obj;
    }
    private Departamento instantiateDepartament(ResultSet rs) throws SQLException {
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Vendedor> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department"
                            + "ON seller.DepartmentId = department.Id"
                            + "WHERE DepartmentId = ?"
                            + "ORDER BY Name");
            rs= st.executeQuery();

            List<Vendedor> list = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()){
                Departamento derp = map.get(rs.getInt("DepartamentID"));
                if(derp==null){
                    derp = instantiateDepartament(rs);
                    map.put(rs.getInt("DepartamentID"),derp);
                }
                Vendedor obj = instantiateVendedor(rs, derp);
                list.add(obj);
            }
            return null;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            Connect.closeStatement(st);
            Connect.closeResultSet(rs);
        }


    }
    public List<Vendedor> findByDepartament(Departamento dep){
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            st = conn.prepareStatement(
                    "SELECT seller.*,department.Name as DepName "
                            + "FROM seller INNER JOIN department"
                            + "ON seller.DepartmentId = department.Id"
                            + "WHERE DepartmentId = ?"
                            + "ORDER BY Name");
            st.setInt(1,dep.getId());
            rs= st.executeQuery();

            List<Vendedor> list = new ArrayList<>();
            Map<Integer, Departamento> map = new HashMap<>();

            while (rs.next()){
                Departamento derp = map.get(rs.getInt("DepartamentID"));
                if(derp==null){
                    derp = instantiateDepartament(rs);
                    map.put(rs.getInt("DepartamentID"),derp);
                }
                Vendedor obj = instantiateVendedor(rs, derp);
                list.add(obj);
            }
            return null;
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            Connect.closeStatement(st);
            Connect.closeResultSet(rs);
        }
    }
}
