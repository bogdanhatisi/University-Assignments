package repository;
import domain.Participant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ParticipantDBRepository implements ParticipantRepository {

    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public ParticipantDBRepository(Properties props) {
        logger.info("Initializing ParticipantDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public void add(Participant elem) {
        logger.traceEntry("saving task{}", elem);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStnt=con.prepareStatement("Insert into Participant(nume,varsta) values (?,?)"))
        {
            preStnt.setString(1,elem.getNume());
            preStnt.setInt(2,elem.getVarsta());

            int result=preStnt.executeUpdate();
            logger.trace("Saved {} instances", result);
        }
        catch (SQLException ex)
        {
            logger.error(ex);
            System.err.println("Error DB"+ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Integer integer, Participant elem) {

    }

    @Override
    public Iterable<Participant> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Participant> participants = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Participant"))
        {try(ResultSet result=preStmt.executeQuery()) {
            while (result.next())
            {
                int id = result.getInt("id");
                String nume = result.getString("nume");
                int varsta = result.getInt("varsta");
                Participant participant = new Participant(nume,varsta);
                participant.setId(id);
                participants.add(participant);
            }
        }

        } catch(SQLException e)
        {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(participants);
        return participants;
    }
}
