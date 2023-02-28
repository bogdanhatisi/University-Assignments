package socialnetwork.repository.database;


import socialnetwork.domain.Friendship;
import socialnetwork.repository.Repository;
import socialnetwork.utils.DoubleKeyLong;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FriendshipDbRepository implements Repository<DoubleKeyLong, Friendship> {
    private String url;
    private String databaseUsername;
    private String password;


    public FriendshipDbRepository(String url, String username, String password) {
        this.url = url;
        this.databaseUsername = username;
        this.password = password;

    }

    @Override
    public Optional<Friendship> findOne(DoubleKeyLong doubleKeyLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<Friendship> findAll() {
        Set<Friendship> friendships = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, databaseUsername, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long firstId = resultSet.getLong("first_id");
                Long secondId = resultSet.getLong("second_id");

                Friendship friendship = new Friendship(firstId,secondId);
                friendships.add(friendship);
            }
            return friendships;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public Optional<Friendship> save(Friendship entity) {
        String sql = "insert into friendships (first_id,second_id) values (?, ?)";

        try (Connection connection = DriverManager.getConnection(url,databaseUsername,password);
             PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setLong(1,entity.getId().getFirstKey());
            ps.setLong(2,entity.getId().getSecondKey());


            ps.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void addFriendship(Long firstId, Long secondId)
    {
        Friendship friendship = new Friendship(firstId,secondId);

        try (Connection connection = DriverManager.getConnection(url, databaseUsername, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships WHERE first_id ='"+firstId+"' AND second_id ='"+secondId+"'");
             ResultSet resultSet = statement.executeQuery())
        {
            if(!resultSet.next())
            {
                Friendship newFriendship = new Friendship(firstId,secondId);
                this.save(newFriendship);
            }

        }

     catch (SQLException e) {
        e.printStackTrace();
    }

    }
    @Override
    public Optional<Friendship> delete(DoubleKeyLong doubleKeyLong) {
        String sql = "delete from friendships where first_id= ? and second_id = ?";

        try (Connection connection = DriverManager.getConnection(url,databaseUsername,password);
             PreparedStatement ps = connection.prepareStatement(sql))
        {
            ps.setLong(1,doubleKeyLong.getFirstKey());
            ps.setLong(2,doubleKeyLong.getSecondKey());

            ps.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void removeFriendship(Long firstId, Long secondId) {
       this.delete(new DoubleKeyLong(firstId,secondId));

    }

    public void removeFriends(Long firstId)
    {
        try (Connection connection = DriverManager.getConnection(url, databaseUsername, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friendships WHERE first_id ='"+firstId+"'");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long first = resultSet.getLong("first_id");
                Long secondId = resultSet.getLong("second_id");
                this.removeFriendship(first,secondId);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Optional<Friendship> update(Friendship entity) {
        return Optional.empty();
    }
}
