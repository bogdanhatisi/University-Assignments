package repository;

import domain.Participant;
import domain.ProbeParticipant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProbeParticipantDBRepository implements ProbeParticipantRepository{

    private JdbcUtils dbUtils;



    private static final Logger logger= LogManager.getLogger();

    public ProbeParticipantDBRepository(Properties props) {
        logger.info("Initializing ProbeParticipantDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public void add(ProbeParticipant elem) {
        logger.traceEntry("saving task{}", elem);
        Connection con = dbUtils.getConnection();

        try(PreparedStatement preStnt=con.prepareStatement("Insert into ProbeParticipant(participant_id,proba_id) values (?,?)"))
        {
            preStnt.setInt(1,elem.getParticipant().getId());
            preStnt.setInt(2,elem.getProba().getId());

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
    public void update(Integer integer, ProbeParticipant elem) {

    }

    @Override
    public Iterable<ProbeParticipant> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<ProbeParticipant> participants = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from ProbeParticipant inner join Proba p on p.id_proba = p.id inner join Participant pa on pa.id_participant = pa.id"))
        {try(ResultSet result=preStmt.executeQuery()) {
            while (result.next())
            {
                int id = result.getInt("id");
                // Adauga cod
                String nume = result.getString("nume");
                int varsta = result.getInt("varsta");
//                Participant participant = new Participant(nume,varsta);
//                participant.setId(id);
//                participants.add(participant);
            }
        }

        } catch(SQLException e)
        {
            logger.error(e);
            System.err.println("Error DB"+e);
        }
        logger.traceExit(participants);
        return participants;
        return null;
    }
}
