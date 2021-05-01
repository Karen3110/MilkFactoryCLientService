//package am.basic.springdemo1.repository.impl;
//
//import am.basic.springdemo1.model.CollectorAccessModel;
//import am.basic.springdemo1.model.CollectorModel;
//import am.basic.springdemo1.repository.CollectorAccessRepository;
//import util.ConnectToDataBase;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.LinkedList;
//import java.util.List;
//
//public class CollectorAccessRepositoryImpl implements CollectorAccessRepository {
//    @Override
//    public void add(CollectorAccessModel collectorAccessModel) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = null;
//
//            pstmt = connection.prepareStatement("INSERT INTO user_access(id,collector_id,login,password) values(?,?,?,?) ");
//
//            pstmt.setInt(1, 0);
//            pstmt.setInt(2, collectorAccessModel.getCollectorID());
//            pstmt.setString(3, collectorAccessModel.getLogin());
//            pstmt.setString(4, collectorAccessModel.getPassword());
//            int result = pstmt.executeUpdate();
//
//            System.out.println("query was executed " + result + " rows were affected");
//            pstmt.close();
//            connection.close();
//        } catch (SQLException exception) {
//            System.out.println("Add user_access failed with reason " + exception.getMessage());
//        }
//    }
//
//    @Override
//    public void update(CollectorAccessModel collectorAccessModel) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = connection.prepareStatement("UPDATE user_access set collector_id = ? ,login = ?, password=? where id = ? ");
//
//            pstmt.setInt(1, collectorAccessModel.getCollectorID());
//            pstmt.setString(2, collectorAccessModel.getLogin());
//            pstmt.setString(3, collectorAccessModel.getPassword());
//            pstmt.setInt(4, collectorAccessModel.getId());
//            int result = pstmt.executeUpdate();
//
//            System.out.println("query was executed " + result + " rows were affected");
//            pstmt.close();
//            connection.close();
//        } catch (SQLException exception) {
//            System.out.println("Update user_access failed with reason " + exception.getMessage());
//        }
//    }
//
//    @Override
//    public void delete(int id) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = connection.prepareStatement("DELETE  FROM user_access  where id = ?");
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
//    public CollectorAccessModel getById(int id) {
//        CollectorAccessModel collectorAccessModel = null;
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_access WHERE id = ?");
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                collectorAccessModel = mapModel(resultSet);
//            }
//
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        return collectorAccessModel;
//    }
//
//    @Override
//    public CollectorAccessModel getByLoginAndPassword(String username, String password) {
//        CollectorAccessModel collectorAccessModel = null;
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_access WHERE login = ? and password = ?");
//
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                collectorAccessModel = mapModel(resultSet);
//            }
//
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        return collectorAccessModel;
//    }
//
//    @Override
//    public List<CollectorAccessModel> getAll() {
//        List<CollectorAccessModel> collectors = new LinkedList<>();
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_access");
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
//    CollectorAccessModel mapModel(ResultSet resultSet) throws SQLException {
//        CollectorAccessModel collectorAccessModel = new CollectorAccessModel();
//        collectorAccessModel.setId(resultSet.getInt("id"));
//        collectorAccessModel.setCollectorID(resultSet.getInt("collector_id"));
//        collectorAccessModel.setLogin(resultSet.getString("login"));
//        collectorAccessModel.setPassword(resultSet.getString("password"));
//
//        return collectorAccessModel;
//    }
//}
