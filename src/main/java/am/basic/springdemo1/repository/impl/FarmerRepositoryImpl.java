package am.basic.springdemo1.repository.impl;


import am.basic.springdemo1.model.FarmerModel;
import am.basic.springdemo1.repository.FarmerRepository;
import util.ConnectToDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FarmerRepositoryImpl implements FarmerRepository {
    @Override
    public void add(FarmerModel farmer) {
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement pstmt = null;

            pstmt = connection.prepareStatement("INSERT INTO farmer(id,name,surname,village_id,collector_id) values(?,?,?,?,?) ");

            pstmt.setInt(1, 0);
            pstmt.setString(2, farmer.getName());
            pstmt.setString(3, farmer.getSurname());
            pstmt.setInt(4, farmer.getVillageID());
            pstmt.setInt(5, farmer.getCollectorID());

            int result = pstmt.executeUpdate();

            System.out.println("query was executed " + result + " rows were affected");
            pstmt.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println("Add Farmer failed with reason " + exception.getMessage());
        }
    }

    @Override
    public void update(FarmerModel farmer) {
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement pstmt = null;

            pstmt = connection.prepareStatement("UPDATE  farmer SET name = ?, surname = ?, village_id = ?, collector_id = ? where id = ?");

            pstmt.setString(1, farmer.getName());
            pstmt.setString(2, farmer.getSurname());
            pstmt.setInt(3, farmer.getVillageID());
            pstmt.setInt(4, farmer.getCollectorID());
            pstmt.setInt(5, farmer.getId());

            int result = pstmt.executeUpdate();

            System.out.println("query was executed " + result + " rows were affected");
            pstmt.close();
            connection.close();

        } catch (Exception exception) {
            System.out.println("Update Farmer data was failed with reson" + exception.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE  FROM farmer  where id = ?");
            pstmt.setLong(1, id);

            int result = pstmt.executeUpdate();

            pstmt.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public FarmerModel getByID(int id) {
        FarmerModel farmer = null;
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM farmer WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                farmer = mapModel(resultSet);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return farmer;
    }

    @Override
    public FarmerModel getByNameSurname(String name, String surname, int villageID) {

        FarmerModel farmer = null;
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM farmer WHERE name = ? and surname = ? and village_id = ?");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setInt(3, villageID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                farmer = mapModel(resultSet);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return farmer;
    }

    @Override
    public List<FarmerModel> getFarmersByVillageID(int villageID) {

        List<FarmerModel> farmers = new LinkedList<>();
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM farmer where village_id = ?");
            preparedStatement.setInt(1, villageID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                farmers.add(mapModel(resultSet));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }


        return farmers;
    }

    @Override
    public List<FarmerModel> getFarmersByCollectorID(int collectorID) {
        List<FarmerModel> farmers = new LinkedList<>();
        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM farmer where collector_id = ?");
            preparedStatement.setInt(1, collectorID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                farmers.add(mapModel(resultSet));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return farmers;
    }

    @Override
    public List<FarmerModel> getFarmersByCollectorIDAndVillageID(int collectorID, int villageID) {
        List<FarmerModel> data = new LinkedList<>();

        try {
            Connection connection = ConnectToDataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM farmer where collector_id = ? and village_id = ?");
            preparedStatement.setInt(1, collectorID);
            preparedStatement.setInt(2, villageID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                data.add(mapModel(resultSet));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return  data;
    }


    FarmerModel mapModel(ResultSet resultSet) throws SQLException {

        FarmerModel farmer = new FarmerModel();
        farmer.setId(resultSet.getInt("id"));
        farmer.setName(resultSet.getString("name"));
        farmer.setSurname(resultSet.getString("surname"));
        farmer.setVillageID(resultSet.getInt("village_id"));
        farmer.setCollectorID(resultSet.getInt("collector_id"));
        return farmer;

    }

}
