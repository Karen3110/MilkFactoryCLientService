//package am.basic.springdemo1.repository.impl;
//
//import am.basic.springdemo1.model.AdminModel;
//import am.basic.springdemo1.repository.AdminRepository;
//import util.ConnectToDataBase;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//
//public class AdminRepositoryImpl implements AdminRepository {
//    @Override
//    public void add(AdminModel adminModel) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = null;
//
//            pstmt = connection.prepareStatement("INSERT INTO admin(id,name,surname,username,password) values(?,?,?,?,?) ");
//
//            pstmt.setInt(1, 0);
//            pstmt.setString(2, adminModel.getName());
//            pstmt.setString(3, adminModel.getSurname());
//            pstmt.setString(4, adminModel.getUsername());
//            pstmt.setString(5, adminModel.getPassword());
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
//    public void update(AdminModel adminModel) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = connection.prepareStatement("UPDATE user set name = ? ,surname = ?, username = ?, password=?where id = ? ");
//
//            pstmt.setString(1, adminModel.getName());
//            pstmt.setString(2, adminModel.getSurname());
//            pstmt.setString(3, adminModel.getUsername());
//            pstmt.setString(4, adminModel.getPassword());
//            pstmt.setInt(5, adminModel.getId());
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
//            PreparedStatement pstmt = connection.prepareStatement("DELETE  FROM admin  where id = ?");
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
//    public AdminModel getByID(int id) {
//        AdminModel adminModel = null;
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE id = ?");
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                adminModel = mapModel(resultSet);
//            }
//
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        return adminModel;
//    }
//
//    @Override
//    public AdminModel getByUsernamePassword(String username, String password) {
//        AdminModel adminModel = null;
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM admin WHERE username = ? and password = ?");
//
//            preparedStatement.setString(1, username);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                adminModel = mapModel(resultSet);
//            }
//
//            resultSet.close();
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        return adminModel;
//    }
//
//
//
//    AdminModel mapModel(ResultSet resultSet) throws SQLException {
//        AdminModel adminModel = new AdminModel();
//        adminModel.setId(resultSet.getInt("id"));
//        adminModel.setName(resultSet.getString("name"));
//        adminModel.setSurname(resultSet.getString("surname"));
//        adminModel.setUsername(resultSet.getString("username"));
//        adminModel.setPassword(resultSet.getString("password"));
//
//        return adminModel;
//    }
//}
