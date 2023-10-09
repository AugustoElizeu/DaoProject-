package Interfaces;

import Entidades.Departamento;
import Entidades.Vendedor;
import Connection.Connect;
import Connection.DBException;
import java.sql.*;
import java.util.List;

public class VendedorJDBCIMPL implements VendedorDao{

    private Connection conn;
    public VendedorJDBCIMPL(Connection conn){
        this.conn = conn;
    }
    public VendedorJDBCIMPL(){

    }
    @Override
    public void insert(Vendedor obj) {

    }

    @Override
    public void update(Vendedor obj) {

    }

    @Override
    public void deleteById(Integer id) {

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
        Departamento dep =new Departamento();
        dep.setId(rs.getInt("DepartamentID"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Vendedor> findAll() {
        return null;
    }

    @Override
    public List<Vendedor> findByDepartament(Departamento dep){
        return null;
    }
}
