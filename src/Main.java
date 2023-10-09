import Entidades.Departamento;
import Entidades.Vendedor;
import Interfaces.DaoFactory;
import Interfaces.VendedorDao;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        VendedorDao vendedorDao = DaoFactory.CVD();
        System.out.println("=====TESTE====");
        Vendedor ven = VendedorDao.findById(3);
        System.out.println(ven);
    }
}