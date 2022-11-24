package socialnetwork.ui;

import socialnetwork.exceptions.RepositoryException;
import socialnetwork.service.Service;

import java.util.Scanner;

public class UserConsole {

    private final Service service;

    private final Scanner scanner=new Scanner(System.in);

    private final String username;

    public UserConsole(Service service, String username) {
        this.service = service;
        this.username = username;
    }

    private void displayMenu()
    {
        System.out.println("~~~~~Menu~~~~~");
        System.out.println("0) Go back");
        System.out.println("1) Add friend.");
        System.out.println("2) Delete friend.");
        System.out.println("~~~~~~~~~~~~~~");
    }

    private void addFriend()
    {
        System.out.print("Add friend by username: ");
        this.scanner.nextLine();
        String receiverUsername = this.scanner.nextLine();

        try
        {
            this.service.addFriend(username, receiverUsername);
            System.out.println("Congratiulations, you have a new friend!");
        }
        catch (Error error) {
            System.out.println("An error has occured" + error.getMessage());
        }
    }

    private void removeFriend()
    {
        System.out.print("Remove friend by username: ");
        this.scanner.nextLine();
        String removedUsername=this.scanner.nextLine();

        try
        {
            this.service.removeFriend(username, removedUsername);
            System.out.println("You removed a friend :( !");
        }
        catch (RepositoryException error) {
            System.out.println("An error has occured" + error.getMessage());
        }
    }

    public void runUserVersion()
    {


        int command;
        boolean run = true;
        while(run)
        {
            this.displayMenu();
            command = this.scanner.nextInt();
            if(command == 0)
            {
                System.out.println("Goodbye, "+username);
                run = false;

            } else if (command == 1) {
                this.addFriend();

            } else if (command == 2) {
                this.removeFriend();
            }
            else
            {
                System.out.println("Enter a valid action(0-2)");

            }
        }
    }
}
