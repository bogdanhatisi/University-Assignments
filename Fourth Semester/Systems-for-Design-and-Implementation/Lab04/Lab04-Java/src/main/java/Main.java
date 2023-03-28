import domain.Participant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.ParticipantDBRepository;
import repository.ParticipantRepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {


        Logger logger= LogManager.getLogger();
        logger.info("test");

        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        ParticipantRepository participantRepo = new ParticipantDBRepository(props);

        participantRepo.add(new Participant("Bogdan",21));

        System.out.println("Toati participantii din db");
        for(Participant part:participantRepo.findAll())
            System.out.println(part.getNume());

        System.out.println("Hello world!");
    }
}