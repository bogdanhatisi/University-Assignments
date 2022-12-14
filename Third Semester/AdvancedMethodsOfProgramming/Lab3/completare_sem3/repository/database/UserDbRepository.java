package socialnetwork.repository.database;


import socialnetwork.domain.User;

import socialnetwork.exceptions.RepositoryException;
import socialnetwork.repository.Repository;
import socialnetwork.utils.DoubleKeyLong;

import java.sql.*;
import java.util.*;

public class UserDbRepository implements Repository<Long, User> {
    private String url;
    private String databaseUsername;
    private String password;

    private Long currentId;


    public UserDbRepository(String url, String databaseUsername, String password) {
        this.url = url;
        this.databaseUsername = databaseUsername;
        this.password = password;
        this.currentId = Long.valueOf(0);

    }
    @Override
    public Optional<User> findOne(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<User> findAll() {
        Set<User> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, databaseUsername, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String userUsername = resultSet.getString("username");

                User utilizator = new User(id, userUsername,firstName, lastName);
                utilizator.setId(id);
                users.add(utilizator);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean userIs(String uName) {
        try (Connection connection = DriverManager.getConnection(url, databaseUsername, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users WHERE username ='"+uName+"'");
             ResultSet resultSet = statement.executeQuery()) {
            if(resultSet.next())
            {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }

    public Long getUserIdByUsername(String uName) {
        try (Connection connection = DriverManager.getConnection(url, databaseUsername, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users WHERE username ='"+uName+"'");
             ResultSet resultSet = statement.executeQuery()) {
            if(resultSet.next())
            {
                Long id = resultSet.getLong("id");
                return id;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public Optional<User> save(User entity) {

        String sql = "insert into users (id,first_name,last_name, username) values (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url,databaseUsername,password);
             PreparedStatement ps = connection.prepareStatement(sql))
     {
        ps.setLong(1,entity.getId());
        ps.setString(2,entity.getFirstName());
        ps.setString(3,entity.getLastName());
        ps.setString(4,entity.getUsername());

        ps.executeUpdate();

     }
     catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Optional<User> addUser(String username, String firstName, String lastName) throws RepositoryException
    {
        this.currentId = this.currentId + 1;
        Long id = this.currentId;
        User user = new User(id,username,firstName,lastName);

        if(this.userIs(username))
        {
            throw new RepositoryException("Username already exists!");
        }

        this.save(user);
        return null;
    }

    @Override
    public Optional<User> delete(Long id) {
        String sql = "delete from users where id= ?";

        try (Connection connection = DriverManager.getConnection(url,databaseUsername,password);
             PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setLong(1,id);

            ps.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;

    }

    public void removeUser(Long id) throws RepositoryException
    {
        this.delete(id);
    }

    @Override
    public Optional<User> update(User entity) {
        return Optional.empty();
    }

    public void logUser(String uName) throws RepositoryException {
        Long idUser = this.getUserIdByUsername(uName);

        if (idUser == null)
        {
            throw new RepositoryException("Invalid username!");
        }
    }

    public Map.Entry<Long,Long> getFriendsId(String senderUsername, String receiverUsername){

        Long senderId = this.getUserIdByUsername(senderUsername);
        Long receiverId = this.getUserIdByUsername(receiverUsername);


        return new AbstractMap.SimpleEntry<Long,Long>(senderId,receiverId);
    }
}
