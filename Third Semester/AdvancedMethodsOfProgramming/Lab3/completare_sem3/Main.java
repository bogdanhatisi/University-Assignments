package socialnetwork;

import socialnetwork.domain.User;
import socialnetwork.repository.memory.FriendshipRepository;
import socialnetwork.repository.memory.UserRepository;
import socialnetwork.service.Service;
import socialnetwork.ui.Console;
//import socialnetwork.repository.Repository;
//import socialnetwork.repository.file.UtilizatorFile;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository("users.csv");
        FriendshipRepository friendshipRepository = new FriendshipRepository("friendships.csv");

        try
        {
            Service service = new Service(userRepository,friendshipRepository);
            Console console = new Console(service);
            console.run();
        }
        catch (Error error)
        {
            System.out.println("Ooops, an error has occured!");
        }


       
    }
}


