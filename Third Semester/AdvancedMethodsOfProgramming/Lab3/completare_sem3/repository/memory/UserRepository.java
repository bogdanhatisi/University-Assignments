package socialnetwork.repository.memory;

import socialnetwork.domain.User;
import socialnetwork.domain.validators.Validator;

import java.io.*;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;

public class UserRepository extends InMemoryRepository0<Long, User> {

    private final String filePath;
    private Long currentId;

    public UserRepository(String filePath) {
        super();
        this.filePath = filePath;
        this.currentId = Long.valueOf(0);
        this.readUsers();
    }

    public User getUserFromString(String[] userFields) {
        Long id = Long.parseLong(userFields[0]);
        String username = userFields[1];
        String firstName = userFields[2];
        String lastName = userFields[3];

        return new User(id, username, firstName, lastName);
    }

    public void readUsers() {

        try {
            String splitBy = ",";
            BufferedReader bufUser = new BufferedReader(new FileReader(this.filePath));

            String userLine = "";

            long lastId = -1;

            userLine = bufUser.readLine();

            while (userLine != null) {
                String[] userInfo = userLine.split(splitBy);

                User user = this.getUserFromString(userInfo);
                this.save(user);

                lastId = user.getId();


                userLine = bufUser.readLine();
            }
            this.currentId = lastId;
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private void appendFile(User user) {
        try {
            BufferedWriter bufUser = new BufferedWriter(new FileWriter(this.filePath, true));
            PrintWriter printUser = new PrintWriter(bufUser);
            printUser.append(String.valueOf(user.getId()) + "," + user.getUsername() + "," + user.getFirstName() + "," + user.getLastName() + "\n");
            printUser.close();

        } catch (IOException error) {
            error.printStackTrace();
        }
    }


    private void writeAllUsers() {
        try {
            BufferedWriter bufUser = new BufferedWriter(new FileWriter(this.filePath));
            PrintWriter printUser = new PrintWriter(bufUser);

            for (Map.Entry<Long, User> entry : this.getAll()) {
                User user = entry.getValue();
                printUser.write(String.valueOf(user.getId()) + "," + user.getUsername() + "," + user.getFirstName() + "," + user.getLastName() + "\n");
            }
            printUser.close();

        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private boolean userIs(String username)
    {
        for (Map.Entry<Long, User> entry : this.getAll()) {
            if (Objects.equals(entry.getValue().getUsername(), username)) {
                return true;
            }
        }
        return false;
    }

    private Long getUserIdByUsername(String username)
    {
        for (Map.Entry<Long, User> entry : this.entities.entrySet()) {
            if (Objects.equals(entry.getValue().getUsername(), username)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void addUser(String username, String firstName, String lastName)
    {
        this.currentId = this.currentId + 1;
        Long id = this.currentId;
        User user = new User(id,username,firstName,lastName);

        if(this.userIs(username))
        {
            throw new Error("Username already exists!");
        }

        this.save(user);
        this.appendFile(user);
    }

    public void removeUser(Long id) {
        if (this.delete(id) == null) {
            throw new Error("Username invalid!");
        } else {
            for (Map.Entry<Long, User> entry : this.getAll()) {
                entry.getValue().getFriends().removeIf(user -> Objects.equals(user.getId(), id));
                this.writeAllUsers();
            }
        }
    }

    public void addFriendbyId(Long firstId,Long secondId)
    {
        User firstUser = this.getEntity(firstId);
        User secondUser = this.getEntity(secondId);

        if(firstUser == null)
        {
            throw new Error("User invalid!");
        }

        if(secondUser == null)
        {
            throw new Error("User invalid!");
        }

        this.getEntity(firstId).getFriends().add(this.getEntity(secondId));
    }

    public Map.Entry<Long,Long> getFriendsId(String senderUsername, String receiverUsername){

        Long senderId = this.getUserIdByUsername(senderUsername);
        Long receiverId = this.getUserIdByUsername(receiverUsername);


        return new AbstractMap.SimpleEntry<Long,Long>(senderId,receiverId);
    }

    public void addFriendInList(String senderUsername, String receiverUsername) {
        Long senderId = this.getUserIdByUsername(senderUsername);
        Long receiverId = this.getUserIdByUsername(receiverUsername);

        User sender = this.entities.get(senderId);
        User receiver = this.entities.get(receiverId);

        sender.getFriends().add(receiver);
        receiver.getFriends().add(sender);

    }

    public Map.Entry<Long,Long> getRemovedFriendsIdPair(String senderUsername, String receiverUsername){

        Long senderId = this.getUserIdByUsername(senderUsername);
        Long receiverId = this.getUserIdByUsername(receiverUsername);

        User sender = this.entities.get(senderId);
        User receiver = this.entities.get(receiverId);

        if (!sender.getFriends().removeIf(user -> (Objects.equals(user.getUsername(), receiverUsername)))) {
            throw new Error("Can't remove friend since friendship doesn't exist!\n");
        }
        receiver.getFriends().removeIf(user -> (Objects.equals(user.getUsername(), senderUsername)));

        return new AbstractMap.SimpleEntry<Long,Long>(senderId,receiverId);
        //this.friendships.removeIf(friendship -> ((Objects.equals(friendship.getUser1(), sender) && Objects.equals(friendship.getUser2(), receiver)) || (Objects.equals(friendship.getUser1(), receiver) && Objects.equals(friendship.getUser2(), sender))));
    }

    public void logUser(String username)
    {
        Long idUser = this.getUserIdByUsername(username);

        if (idUser == null)
        {
            throw new Error("Invalid username!");
        }
    }


}
