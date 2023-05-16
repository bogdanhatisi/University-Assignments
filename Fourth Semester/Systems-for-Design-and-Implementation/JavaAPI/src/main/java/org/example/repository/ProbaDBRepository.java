package org.example.repository;

import org.example.domain.Proba;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
public class ProbaDBRepository  implements  ProbaRepository{
    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();


    @Autowired
    public ProbaDBRepository(Properties props) {
        logger.info("Initializing ProbaDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }
    @Override
    public Proba add(Proba elem) {
        logger.traceEntry("saving task{}", elem);
        Connection con = dbUtils.getConnection();


        try(PreparedStatement preStnt=con.prepareStatement("Insert into Proba(stil,distanta) values (?,?)"))
        {

            preStnt.setString(1,elem.getStil());
            preStnt.setInt(2,elem.getDistanta());

            int result=preStnt.executeUpdate();

            logger.trace("Saved {} instances", result);
            logger.traceExit();
            return elem;
        }
        catch (SQLException ex)
        {
            logger.error(ex);
            System.err.println("Error DB"+ex);
            logger.traceExit();
            return null;
        }

    }

    @Override
    public Proba findOne(Integer id) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Proba> probe = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Proba WHERE id = ?"))
        {
            preStmt.setInt(1,id);
            try(ResultSet result=preStmt.executeQuery()) {
            if (result.next())
            {

                String stil = result.getString("stil");
                int distanta = result.getInt("distanta");
                Proba proba = new Proba(stil,distanta);
                proba.setId(id);
                return proba;
            }
        }

        } catch(SQLException e)
        {
            logger.error(e);
            System.err.println("Error DB"+e);
            return null;
        }
        logger.traceExit(probe);
        return null;
    }


    @Override
    public Proba update(Integer id, Proba elem) {
        logger.traceEntry("saving task{}", elem);
        Connection con = dbUtils.getConnection();


        try(PreparedStatement preStnt=con.prepareStatement("UPDATE Proba SET stil = ?, distanta = ? WHERE id = ? "))
        {
            preStnt.setString(1,elem.getStil());
            preStnt.setInt(2,elem.getDistanta());
            preStnt.setInt(3,id);

            int result=preStnt.executeUpdate();

            logger.trace("Updated {} instances", result);
            logger.traceExit();
            return elem;
        }
        catch (SQLException ex)
        {
            logger.error(ex);
            System.err.println("Error DB"+ex);
            logger.traceExit();
            return null;
        }

    }


    @Override
    public Proba delete(Integer id){
        Connection con = dbUtils.getConnection();
        Proba proba = findOne(id);

        try(PreparedStatement preStnt=con.prepareStatement("DELETE FROM Proba WHERE id = ? "))
        {
            preStnt.setInt(1,id);


            int result=preStnt.executeUpdate();

            logger.trace("Updated {} instances", result);
            logger.traceExit();
            return proba;
        }
        catch (SQLException ex)
        {
            logger.error(ex);
            System.err.println("Error DB"+ex);
            logger.traceExit();
            return null;
        }
    }



    @Override
    public Iterable<Proba> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Proba> probe = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Proba"))
        {try(ResultSet result=preStmt.executeQuery()) {
            while (result.next())
            {
                int id = result.getInt("id");
                String stil = result.getString("stil");
                int distanta = result.getInt("distanta");
                Proba proba = new Proba(stil,distanta);
                proba.setId(id);
                probe.add(proba);
            }
        }

        } catch(SQLException e)
        {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(probe);
        return probe;
    }
}