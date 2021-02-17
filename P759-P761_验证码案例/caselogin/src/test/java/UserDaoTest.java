import com.sirtomato.Dao.UserDao;
import com.sirtomato.Domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void testLogin(){
        User loginUser = new User();
        loginUser.setUserName("lufy");
        loginUser.setPassWord("123");
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        System.out.println(user);
        //User{id=1, username='lufy', password='123', insertTime=2020-12-17 21:00:07.0}
    }
}
