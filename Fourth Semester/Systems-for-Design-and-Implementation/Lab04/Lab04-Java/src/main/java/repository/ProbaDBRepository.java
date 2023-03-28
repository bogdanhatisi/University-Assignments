package repository;

import domain.Participant;
import domain.Proba;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProbaDBRepository  implements  ProbaRepository{
    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public ProbaDBRepository(Properties props) {
        logger.info("Initializing ProbaDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }
    @Override
    public void add(Proba elem) {

    }

    @Override
    public void update(Integer integer, Proba elem) {

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
