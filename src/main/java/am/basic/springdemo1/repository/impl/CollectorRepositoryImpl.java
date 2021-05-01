//package am.basic.springdemo1.repository.impl;
//
//import am.basic.springdemo1.model.CollectorModel;
//import am.basic.springdemo1.repository.CollectorRepository;
//import util.ConnectToDataBase;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.LinkedList;
//import java.util.List;
//
//public class CollectorRepositoryImpl implements CollectorRepository {
//    @Override
//    public void add(CollectorModel collector) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = null;
//
//            pstmt = connection.prepareStatement("INSERT INTO collector(id,name,surname,village_id) values(?,?,?,?) ");
//
//            pstmt.setInt(1, 0);
//            pstmt.setString(2, collector.getName());
//            pstmt.setString(3, collector.getSurname());
//            pstmt.setInt(4, collector.getVillageID());
//
//            int result = pstmt.executeUpdate();
//
//            System.out.println("query was executed " + result + " rows were affected");
//            pstmt.close();
//            connection.close();
//        } catch (SQLException exception) {
//            System.out.println("Add user failed with reason " + exception.getMessage());
//        }
//    }
//
//    @Override
//    public void update(CollectorModel collector) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = connection.prepareStatement("UPDATE collector set name = ? ,surname = ?, village_id = ? where id = ? ");
//
//            pstmt.setString(1, collector.getName());
//            pstmt.setString(2, collector.getSurname());
//            pstmt.setInt(3, collector.getVillageID());
//            pstmt.setInt(4, collector.getId());
//            int result = pstmt.executeUpdate();
//
//            System.out.println("query was executed " + result + " rows were affected");
//            pstmt.close();
//            connection.close();
//        } catch (SQLException exception) {
//            System.out.println("Update user failed with reason " + exception.getMessage());
//        }
//    }
//
//    @Override
//    public void delete(int id) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = connection.prepareStatement("DELETE  FROM collector  where id = ?");
//            pstmt.setLong(1, id);
//
//            int result = pstmt.executeUpdate();
//
//            pstmt.close();
//            connection.close();
//        } catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//        }
//    }
//
//    @Override
//    public CollectorModel getById(int id) {
//        CollectorModel collector = null;
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM collector WHERE id = ?");
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                collector = mapModel(resultSet);
//            }
//
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        return collector;
//    }
//
//    @Override
//    public CollectorModel getByNameAndSurname(String name, String surname) {
//        CollectorModel collector = null;
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM collector WHERE username = ? and password = ?");
//
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, surname);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                collector = mapModel(resultSet);
//            }
//
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        return collector;
//    }
//
//    @Override
//    public List<CollectorModel> getAll() {
//        List<CollectorModel> collectors = new LinkedList<>();
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM collector");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                collectors.add(mapModel(resultSet));
//            }
//            preparedStatement.close();
//            connection.close();
//
//        } catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//        }
//        return collectors;
//    }
//
//    @Override
//    public List<CollectorModel> getCollectorByVillageId(int id) {
//        List<CollectorModel> collectors = new LinkedList<>();
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM collector WHERE village_id =?");
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                collectors.add(mapModel(resultSet));
//            }
//            preparedStatement.close();
//            connection.close();
//
//        } catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//        }
//        return collectors;
//    }
//
//
//    CollectorModel mapModel(ResultSet resultSet) throws SQLException {
//        CollectorModel collector = new CollectorModel();
//        collector.setId(resultSet.getInt("id"));
//        collector.setName(resultSet.getString("name"));
//        collector.setSurname(resultSet.getString("surname"));
//        collector.setVillageID(resultSet.getInt("village_id"));
//        return collector;
//    }
//}
