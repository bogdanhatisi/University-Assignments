package com.example.bugmanager.repository;

import com.example.bugmanager.model.Bug;
import com.example.bugmanager.model.Verifier;
import com.example.bugmanager.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BugRepository implements Repository<Integer, Bug> {

    private JDBCUtils jdbcUtils = new JDBCUtils();


    public Integer getLastId(){
        Integer id = 1;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from bug ORDER BY id DESC")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                id = resultSet.getInt("id");

                return id;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return id;
    }



    @Override
    public void add(Bug entity) {
        String query = "INSERT INTO bug(description,status,programmerid,id) values (?,?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setString(2, entity.getStatus());
            preparedStatement.setInt(3,entity.getProgrammerId());
            preparedStatement.setInt(4,getLastId()+1);
            int result = preparedStatement.executeUpdate();

        }
        catch (SQLException ex)
        {
            System.err.println("Error DB"+ex);
        }
    }

    @Override
    public void update(Integer id, Bug entity) {
        if (id == null) {
            throw new IllegalArgumentException("User id can't be null!\n");
        }
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE bug SET description = ?, status = ?, programmerid = ? WHERE id = ?")) {
            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setString(2, entity.getStatus());
            preparedStatement.setInt(3,entity.getProgrammerId());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("User id can't be null!\n");
        }
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE from bug where id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public Bug findOne(Integer integer) {

       return null;
    }

    @Override
    public Iterable<Bug> findAll() {
        List<Bug> bugs = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from bug")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                int id = resultSet.getInt("id");
                int programmerid = resultSet.getInt("programmerid");


                Bug bug = new Bug(id,description,status,programmerid);
                bugs.add(bug);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return bugs;
    }
}
