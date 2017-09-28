package test.com.bank.dao.impl.mysql.mybatis;

import com.bank.dao.UserDAO;
import com.bank.dao.impl.mysql.mybatis.MyBatisUserDAOImpl;
import com.bank.po.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * MyBatisUserDAOImpl Tester.
 *
 * @author YiJie
 * @version 1.0
 * @since <pre>09/28/2017</pre>
 */
public class MyBatisUserDAOImplTest {

    private UserDAO userDAO;

    @Before
    public void before() throws Exception {
        userDAO = new MyBatisUserDAOImpl();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: testAdd()
     */
    @Test
    public void testTestAdd() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: addUser(User user)
     */
    @Test
    public void testAddUser() throws Exception {
        assertEquals(userDAO.addUser(new User("y", "123")), 1);
        assertEquals(userDAO.addUser(new User("yj", "123")), 0);
        assertEquals(userDAO.addUser(new User("yj")), 0);
        assertEquals(userDAO.addUser(new User("123")), 0);
        assertEquals(userDAO.addUser(new User()), 0);
        assertEquals(userDAO.addUser(null), 0);
    }

    /**
     * Method: updateUser(User old, User user)
     */
    @Test
    public void testUpdateUser() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: deleteUser(User user)
     */
    @Test
    public void testDeleteUser() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: queryUser(User user)
     */
    @Test
    public void testQueryUser() throws Exception {
//TODO: Test goes here... 
    }


} 
