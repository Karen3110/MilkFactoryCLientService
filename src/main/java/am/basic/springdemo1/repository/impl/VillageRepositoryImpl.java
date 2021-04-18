package am.basic.springdemo1.repository.impl;

import am.basic.springdemo1.model.VillageModel;
import am.basic.springdemo1.repository.VillageRepository;
import util.ConnectToDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class VillageRepositoryImpl implements VillageRepository {
    @Override
    public void add(VillageModel village) {
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement pstmt = null;

            pstmt = connection.prepareStatement("INSERT INTO village(id,village_name) values(?,?) ");

            pstmt.setInt(1, 0);
            pstmt.setString(2, village.getVillageName());

            int result = pstmt.executeUpdate();

            System.out.println("query was executed " + result + " rows were affected");
            pstmt.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println("Add village failed with reason " + exception.getMessage());
        }
    }

    @Override
    public void update(VillageModel village) {
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement pstmt = null;

            pstmt = connection.prepareStatement("UPDATE  village SET village_name = ? where id = ?");

            pstmt.setString(1, village.getVillageName());
            pstmt.setInt(2, village.getId());

            int result = pstmt.executeUpdate();

            System.out.println("query was executed " + result + " rows were affected");
            pstmt.close();
            connection.close();

        } catch (Exception exception) {
            System.out.println("Update Village data was failed with reason" + exception.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE  FROM village  where id = ?");
            pstmt.setLong(1, id);

            int result = pstmt.executeUpdate();
            pstmt.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public VillageModel getByID(int id) {
        VillageModel village = null;
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM village WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                village = mapModel(resultSet);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return village;
    }

    @Override
    public List<VillageModel> getAll() {
        List<VillageModel> villageModels = new LinkedList<>();
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM village");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                villageModels.add(mapModel(resultSet));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return villageModels;
    }

    VillageModel mapModel(ResultSet resultSet) throws SQLException {

        VillageModel village = new VillageModel();
        village.setId(resultSet.getInt("id"));
        village.setVillageName(resultSet.getString("village_name"));

        return village;

    }
}