package com.example.bugmanager.repository;

import com.example.bugmanager.model.Bug;
import com.example.bugmanager.model.Project;
import com.example.bugmanager.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRepository implements Repository<Integer, Project> {

    private JDBCUtils jdbcUtils = new JDBCUtils();


    public Integer getLastId(){
        Integer id = 1;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from project ORDER BY id DESC")) {
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
    public void add(Project entity) {
        String query = "INSERT INTO bug(description,name,id) values (?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3,getLastId()+1);
            int result = preparedStatement.executeUpdate();

        }
        catch (SQLException ex)
        {
            System.err.println("Error DB"+ex);
        }
    }

    @Override
    public void update(Integer id, Project entity) {
        if (id == null) {
            throw new IllegalArgumentException("Project id can't be null!\n");
        }
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE project SET description = ?, name = ? WHERE id = ?")) {
            preparedStatement.setString(1, entity.getDescription());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3,entity.getId());
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
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE from project where id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    public Project findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<Project> findAll() {
        return null;
    }
}
