package com.example.bugmanager.repository;

import com.example.bugmanager.model.Programmer;
import com.example.bugmanager.utils.JDBCUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammerRepository implements Repository<Integer, Programmer> {

    private JDBCUtils jdbcUtils = new JDBCUtils();

    public Integer getLastId(){
        Integer id = 1;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from programmer ORDER BY id DESC")) {
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

    public Programmer findOne(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("User id can't be null!\n");
        }
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from programmer where id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int projectId = resultSet.getInt("projectId");

                Programmer programmer = new Programmer(username,password,projectId);
                return programmer;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    @Override
    public void add(Programmer entity) {
        String query = "INSERT INTO Programmer(username,password,projectid,id) values (?,?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setInt(3, entity.getProjectId());
            preparedStatement.setInt(4,getLastId()+1);
            int result = preparedStatement.executeUpdate();

        }
        catch (SQLException ex)
        {
            System.err.println("Error DB"+ex);
        }
    }

    @Override
    public void update(Integer id, Programmer elem) {
        if (id == null) {
            throw new IllegalArgumentException("User id can't be null!\n");
        }
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Programmer SET username = ?, password = ?, projectid = ? WHERE id = ?")) {
            preparedStatement.setString(1, elem.getUsername());
            preparedStatement.setString(2, elem.getPassword());
            preparedStatement.setInt(3, elem.getProjectId());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    public int FindByUserAndPass(String user,String pass){

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from programmer where username = ?, password = ?")) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(1, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int projectId = resultSet.getInt("projectId");

                Programmer programmer = new Programmer(username,password,projectId);
                return 1;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;

    }

    public Iterable<Programmer> findAll() {
        List<Programmer> programmers = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from programmer")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int projectId = resultSet.getInt("projectId");
                int id = resultSet.getInt("id");


                Programmer programmer = new Programmer(username,password,projectId);
                programmer.setId(id);
                programmers.add(programmer);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return programmers;
    }

    public void delete(Integer id)
    {
        if (id == null) {
            throw new IllegalArgumentException("User id can't be null!\n");
        }
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE from programmer where id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
