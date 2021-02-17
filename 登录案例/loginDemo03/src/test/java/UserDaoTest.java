import dao.UserDao;
import domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void testLogin(){
        User loginUser = new User();
//        loginUser.setUsername("瓜皮");
//        loginUser.setPassword("guapi");
        loginUser.setUsername("lufy");
        loginUser.setPassword("123");
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        System.out.println(user);
        //User{id=1, username='lufy', password='123', insertTime=2020-12-17 21:00:07.0}
    }
}
