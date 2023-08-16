package repository.db;

import music.model.Content;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.ContentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class ContentRepositoryDb implements ContentRepository {

    private JdbcUtils dbUtils;
    private static final Logger logger= LogManager.getLogger();

    @Autowired
    public ContentRepositoryDb(Properties props) {
        logger.info("Initializing ContentRepositoryDb with properties: {} ",props);
        this.dbUtils = new JdbcUtils(props);
    }

    @Override
    public Content add(Content elem) {
        return null;
    }

    @Override
    public void delete(Content elem) {

    }

    @Override
    public void update(Content elem, Integer id) {

    }

    @Override
    public Content findById(Integer id) {
        return null;
    }

    @Override
    public Iterable<Content> findAll() {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Content> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";
        try(PreparedStatement preStmt=con.prepareStatement(sql)){
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer id = result.getInt("id");
                    String text = result.getString("text");
                    int nb_coursNew = result.getInt("nb_cours");
                    int partNew = result.getInt("part");
                    Content content = new Content(text,nb_coursNew,partNew);
                    content.setId(id);
                    courses.add(content);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error BD "+ex);
        }
        logger.traceExit(courses);
        return courses;
    }


    @Override
    public Content findByCoursPart(int nb_cours, int part) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        String sql = "SELECT * FROM courses WHERE nb_cours=? AND part=?";
        try(PreparedStatement preStmt=con.prepareStatement(sql)){
            preStmt.setInt(1,nb_cours);
            preStmt.setInt(2,part);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                      Integer id = result.getInt("id");
                      String text = result.getString("text");
                      int nb_coursNew = result.getInt("nb_cours");
                      int partNew = result.getInt("part");
                      Content content = new Content(text,nb_coursNew,partNew);
                      content.setId(id);
                      return content;
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
    public List<Content> findByCours(int nb_cours) {
        logger.traceEntry();
        Connection con = dbUtils.getConnection();
        List<Content> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE nb_cours=?";
        try(PreparedStatement preStmt=con.prepareStatement(sql)){
            preStmt.setInt(1,nb_cours);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer id = result.getInt("id");
                    String text = result.getString("text");
                    int nb_coursNew = result.getInt("nb_cours");
                    int partNew = result.getInt("part");
                    Content content = new Content(text,nb_coursNew,partNew);
                    content.setId(id);
                    courses.add(content);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
            System.err.println("Error BD "+ex);
        }
        logger.traceExit(courses);
        return courses;
    }
}
