package socialnetwork;

import socialnetwork.domain.Friendship;
import socialnetwork.domain.User;
import socialnetwork.domain.validators.UtilizatorValidator;
import socialnetwork.exceptions.RepositoryException;
import socialnetwork.repository.Repository;
import socialnetwork.repository.database.FriendshipDbRepository;
import socialnetwork.repository.database.UserDbRepository;
import socialnetwork.repository.memory.FriendshipRepository;
import socialnetwork.repository.memory.UserRepository;
import socialnetwork.service.Service;
import socialnetwork.ui.Console;
import socialnetwork.utils.DoubleKeyLong;
import socialnetwork.validators.UserValidator;
//import socialnetwork.repository.Repository;
//import socialnetwork.repository.file.UtilizatorFile;


public class Main {
    public static void main(String[] args) throws RepositoryException {

        String username="postgres";
        String password="admin";
        String url="jdbc:postgresql://localhost:5432/socialnetwork";
        UserDbRepository userRepository =
                new UserDbRepository(url,username, password);

        FriendshipDbRepository friendshipRepository = new FriendshipDbRepository(url,username,password);






        UserValidator userValidator = new UserValidator();

        try
        {
            Service service = new Service(userRepository,friendshipRepository, userValidator);
            Console console = new Console(service);
            console.run();
        }
        catch (Error e) {
            System.out.println("Ooops, an error has occured: " + e.getMessage());
        }


    }
}


