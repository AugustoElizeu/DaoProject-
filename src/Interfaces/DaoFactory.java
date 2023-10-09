package Interfaces;

import Connection.Connect;

public class DaoFactory {
    public static VendedorDao CVD(){
        return new VendedorJDBCIMPL(Connect.getConnection());
    }
    public static DepartamentoDao createDepartmentDao() {
        return new DepartmentDaoJDBC(Connect.getConnection());
    }
}
