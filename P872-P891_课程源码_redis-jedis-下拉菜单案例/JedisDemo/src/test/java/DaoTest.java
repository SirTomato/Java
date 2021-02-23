import Dao.Impl.ProvincesDaoImpl;
import Dao.ProvincesDao;
import Domain.Province;
import org.junit.Test;

import java.util.List;

public class DaoTest {
    @Test
    public void ProvincesDaoTest(){

        ProvincesDao dao = new ProvincesDaoImpl();
        List<Province> list = dao.findAll();
        System.out.println(list);

    }
}
