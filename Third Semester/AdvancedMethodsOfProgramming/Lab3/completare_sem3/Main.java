package socialnetwork;

import socialnetwork.exceptions.RepositoryException;
import socialnetwork.repository.memory.FriendshipRepository;
import socialnetwork.repository.memory.UserRepository;
import socialnetwork.service.Service;
import socialnetwork.ui.Console;
import socialnetwork.validators.UserValidator;
//import socialnetwork.repository.Repository;
//import socialnetwork.repository.file.UtilizatorFile;


public class Main {
    public static void main(String[] args) {

        UserValidator userValidator = new UserValidator();
        UserRepository userRepository = new UserRepository("users.csv");
        FriendshipRepository friendshipRepository = new FriendshipRepository("friendships.csv");

        try
        {
            Service service = new Service(userRepository,friendshipRepository, userValidator);
            Console console = new Console(service);
            console.run();
        }
        catch (RepositoryException e) {
            System.out.println("Ooops, an error has occured: " + e.getMessage());
        }


    }
}


