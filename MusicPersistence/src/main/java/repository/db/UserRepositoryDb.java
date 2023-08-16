package repository.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import music.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.UserRepository;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class UserRepositoryDb implements UserRepository {

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

    @Autowired
    public UserRepositoryDb(Properties props) {
        logger.info("Initializing UserRepositoryDb with properties: {} ",props);
        this.dbUtils = new JdbcUtils(props);
    }

    @Override
    public User add(User elem) {
        logger.traceEntry("saving user {}", elem);
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("INSERT INTO users (first_name,last_name,birthday,username,email,password) VALUES (?,?,?,?,?,?)")){
            preStmt.setString(1,elem.getFirst_name());
            preStmt.setString(2,elem.getLast_name());
            preStmt.setDate(3, new java.sql.Date(elem.getBirthday().getTime()));
            preStmt.setString(4, elem.getUsername());
            preStmt.setString(5,elem.getEmail());
            preStmt.setString(6,elem.getPassword());

            int result=preStmt.executeUpdate();
            logger.trace("Saved {} instances", result);
            if(result > 0){
                ResultSet rs = preStmt.getGeneratedKeys();
                if (rs.next()){
                    Integer id=rs.getInt(1);
                    elem.setId(id);
                    logger.trace("Generated id {} ",id);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ ex);
        }
        return logger.traceExit(elem);
    }

    @Override
    public void delete(User elem) {
        logger.traceEntry("delete user {}", elem);
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("DELETE FROM users WHERE username=?")){

            preStmt.setString(1,elem.getUsername());
            int result=preStmt.executeUpdate();
            logger.trace("Delete {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(User elem, Integer id) {
        logger.traceEntry("update user {}", elem);
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("UPDATE users SET first_name=? , last_name=?, birthday=?, username=?, email=?, password=? WHERE id=?")){

            preStmt.setString(1,elem.getFirst_name());
            preStmt.setString(2,elem.getLast_name());
            preStmt.setString(3, elem.getBirthday().toString());
            preStmt.setDate(3, new java.sql.Date(elem.getBirthday().getTime()));
            preStmt.setString(4, elem.getUsername());
            preStmt.setString(5,elem.getEmail());
            preStmt.setString(6,elem.getPassword());
            preStmt.setInt(7,id);

            int result=preStmt.executeUpdate();
            logger.trace("Update {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ ex);
        }
        logger.traceExit();
    }

    @Override
    public User findById(Integer id) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        String sql = "SELECT * FROM users WHERE id=?";
        try(PreparedStatement preStmt=con.prepareStatement(sql)){
            preStmt.setInt(1,id);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer idNew = result.getInt("id");
                    String first_name = result.getString("first_name");
                    String last_name = result.getString("last_name");
                    Date birthday = result.getDate("birthday");
                    String username = result.getString("username");
                    String email = result.getString("email");
                    String password = result.getString("password");
                    User user = new User(first_name,last_name,birthday,username,email,password);
                    user.setId(idNew);
                    return user;
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error BD "+ex);
        }
        logger.traceExit();
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try(PreparedStatement preStmt=con.prepareStatement(sql)){
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer id = result.getInt("id");
                    String first_name = result.getString("first_name");
                    String last_name = result.getString("last_name");
                    Date birthday = result.getDate("birthday");
                    String username = result.getString("username");
                    String email = result.getString("email");
                    String password = result.getString("password");
                    User user = new User(first_name,last_name,birthday,username,email,password);
                    user.setId(id);
                    users.add(user);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error BD "+ex);
        }
        logger.traceExit(users);
        return users;
    }

    @Override
    public void deleteById(Integer id) {
        logger.traceEntry("delete user {}", id);
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt = con.prepareStatement("DELETE FROM users WHERE id=?")){

            preStmt.setInt(1,id);
            int result=preStmt.executeUpdate();
            logger.trace("Delete {} instances", result);
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error DB "+ ex);
        }
        logger.traceExit();
    }

    @Override
    public User findByUsernamePassword(String username, String password) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        String sql = "SELECT * FROM users WHERE username=? AND password=?";
        try(PreparedStatement preStmt=con.prepareStatement(sql)){
            preStmt.setString(1,username);
            preStmt.setString(2,password);

            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer id = result.getInt("id");
                    String first_name = result.getString("first_name");
                    String last_name = result.getString("last_name");
                    Date birthday = result.getDate("birthday");
                    String email = result.getString("email");
                    User user = new User(first_name,last_name,birthday,username,email,password);
                    user.setId(id);
                    return user;
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error BD "+ex);
        }
        logger.traceExit();
        return null;
    }
}
