package com.example.bugmanager.repository;

import com.example.bugmanager.model.Programmer;
import com.example.bugmanager.model.Verifier;
import com.example.bugmanager.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VerifierRepository implements Repository<Integer,Verifier> {
    private JDBCUtils jdbcUtils = new JDBCUtils();

    public Integer getLastId(){
        Integer id = 1;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from verifier ORDER BY id DESC")) {
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

    public Verifier findOne(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("User id can't be null!\n");
        }
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from verifier where id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");


                Verifier verifier = new Verifier(username,password);
                return verifier;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    @Override
    public void add(Verifier entity) {
        String query = "INSERT INTO verifier(username,password,id) values (?,?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1, entity.getUsername());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setInt(3,getLastId()+1);
            int result = preparedStatement.executeUpdate();

        }
        catch (SQLException ex)
        {
            System.err.println("Error DB"+ex);
        }
    }

    @Override
    public void update(Integer id, Verifier elem) {
        if (id == null) {
            throw new IllegalArgumentException("User id can't be null!\n");
        }
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Programmer SET username = ?, password = ? WHERE id = ?")) {
            preparedStatement.setString(1, elem.getUsername());
            preparedStatement.setString(2, elem.getPassword());
            preparedStatement.setLong(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }


    public Iterable<Verifier> findAll() {
        List<Verifier> verifiers = new ArrayList<>();
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from verifier")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                int id = resultSet.getInt("id");


                Verifier verifier = new Verifier(username,password);
                verifier.setId(id);
                verifiers.add(verifier);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return verifiers;
    }

    public void delete(Integer id)
    {
        if (id == null) {
            throw new IllegalArgumentException("User id can't be null!\n");
        }
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE from verifier where id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
