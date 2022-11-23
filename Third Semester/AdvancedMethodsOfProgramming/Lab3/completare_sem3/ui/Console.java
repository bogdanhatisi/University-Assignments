package socialnetwork.ui;

import socialnetwork.service.Service;

import java.util.Scanner;

public class Console {
    private final Service service;

    private final Scanner scanner = new Scanner(System.in);

    public Console(Service service) {
        this.service = service;
    }

    private void displayMenu() {
        System.out.println("~~~~~Menu~~~~~");
        System.out.println("0) Close the program");
        System.out.println("1) Add user.");
        System.out.println("2) Delete user.");
        System.out.println("3) Enter username for more actions");
        System.out.println("~~~~~~~~~~~~~~");
    }


    private void addUser() {
        System.out.println("Enter the following data to add a new user:");

        System.out.print("Username: ");
        this.scanner.nextLine();
        String username = this.scanner.nextLine();

        System.out.print("First Name: ");
        String firstName = this.scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = this.scanner.nextLine();

        try {
            this.service.addUser(username, firstName, lastName);
            System.out.println("User added successfully!");
        } catch (Error error) {
            System.out.println("An error has occured" + error.getMessage());
        }
    }


    private void removeUser() {
        System.out.print("Enter the desired id(to be removed): ");
        this.scanner.nextLine();

        Long idUser = this.scanner.nextLong();
        try {
            this.service.removeUser(idUser);
            System.out.println("User with id" + idUser + "successfully removed!\n");
        } catch (Error error) {
            System.out.println("An error has occured" + error.getMessage());
        }
    }


    private void userLogId() {
        System.out.println("Enter username: ");
        System.out.println("Username: ");
        this.scanner.nextLine();
        String username = this.scanner.nextLine();
        try {
            this.service.logUser(username);
            UserConsole userConsole = new UserConsole(this.service, username);
            userConsole.runUserVersion();
        } catch (Error error) {
            System.out.println("An error has occured" + error.getMessage());
        }
    }

    public void run() {


        int command;
        boolean run = true;
        while (run) {
            this.displayMenu();
            command = this.scanner.nextInt();
            if (command == 0) {
                System.out.println("Goodbye, have a nice day!");
                run = false;

            } else if (command == 1) {
                this.addUser();

            } else if (command == 2) {
                this.removeUser();
            } else if (command == 3) {
                this.userLogId();
            } else {
                System.out.println("Enter a valid action(0-3)");

            }
        }
    }
}
