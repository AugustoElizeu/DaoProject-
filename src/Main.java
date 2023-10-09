import Entidades.Departamento;
import Entidades.Vendedor;
import Interfaces.DaoFactory;
import Interfaces.VendedorDao;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        VendedorDao vendedorDao = DaoFactory.CVD();
        System.out.println("=====TESTE====");
        Vendedor ven = vendedorDao.findById(3);
        System.out.println(ven);
        System.out.println("=====TESTE====");
        Departamento dep = new Departamento(2,null);
        List<Vendedor> list = vendedorDao.findByDepartament(dep);
        for(Vendedor obj : list){
            System.out.println(obj);
        }
        System.out.println("=====TESTE====");
        list = vendedorDao.findAll();
        for(Vendedor obj : list){
            System.out.println(obj);
        }
        System.out.println("=====TESTE====");
        Vendedor NovVen = new Vendedor(null,"Greg","greg@gmail.com",new Date(),4000.0,dep);
        vendedorDao.insert(NovVen);
        System.out.println("Funcionou new Id " + NovVen.getID());
    }
}