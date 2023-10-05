package org.example.app.repository;

import org.example.app.database.СonnectionManager;
import org.example.app.entity.Contacts;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ContactsRepository implements AppRepository<Contacts> {


    @Override
    public List<Contacts> fetchAll() {
        List<Contacts> list = new ArrayList<>();
        try (Connection connection = СonnectionManager.open()){
            Statement statement = connection.createStatement();
            String sql = "Select * From contacts";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null){
                while (resultSet.next()){
                    list.add(new Contacts(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("phone")));
                }
                return list;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void create(Contacts obj) {
        try (Connection connerction = СonnectionManager.open()){
            String sql = "Insert Into contacts (name, phone) Values(?, ?)";
            PreparedStatement preparedStatement = connerction.prepareStatement(sql);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getPhone());

            int updateCount = preparedStatement.executeUpdate();

            if (updateCount > 0){
                System.out.println("Contact was successfully added to the database ");
            } else{
                System.out.println("Contact was not added to the database ");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Contacts fetchById(int id) {
        Contacts contacts = null;
        try (Connection connection = СonnectionManager.open()) {
            String sql = "SELECT * FROM contacts WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userID = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int phone = resultSet.getInt("phone");
                contacts = new Contacts(userID, name, phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public void update(int id, Contacts obj) {
        try (Connection connection = СonnectionManager.open()) {
            String sql = "UPDATE contacts SET name = ?, phone = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(1, obj.getPhone());
            preparedStatement.setInt(3, id);

            int updateCount = preparedStatement.executeUpdate();

            if (updateCount > 0) {
                System.out.println("User successfully update!");
            } else {
                System.out.println("User was not update!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try (Connection connection = СonnectionManager.open()) {
            String sql = "DELETE FROM contacts WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int updateCount = preparedStatement.executeUpdate();
            if (updateCount > 0) {
                System.out.println("Contact successfully deleted!");
            } else {
                System.out.println("Contact was not deleted!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUserById(int id){
        boolean match = false;
        try (Connection connection = СonnectionManager.open()){
            String sql = "SELECT 1 FROM contacts WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            match = resultSet.next();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return match;
    }
}
