//package am.basic.springdemo1.repository.impl;
//
//import am.basic.springdemo1.model.EveryDayMilkModel;
//import am.basic.springdemo1.repository.EverydayMilkRepository;
//import util.ConnectToDataBase;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.LinkedList;
//import java.util.List;
//
//public class EverydayMilkRepositoryImpl implements EverydayMilkRepository {
//
//    @Override
//    public void add(EveryDayMilkModel bill) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO (id,date,shift,farmer_id,collector_id,count_ml,count_kg,price,calculated) values(?,?,?,?,?,?,?,?,?)");
//
//            preparedStatement.setInt(1, 0);
//            preparedStatement.setString(2, bill.getDate());
//            preparedStatement.setString(3, bill.getShift());
//            preparedStatement.setInt(4, bill.getFarmerID());
//            preparedStatement.setInt(5, bill.getCollectorID());
//            preparedStatement.setFloat(6, bill.getCountMilliLiter());
//            preparedStatement.setFloat(7, bill.getCountKiloGram());
//            preparedStatement.setFloat(8, bill.getPrice());
//            preparedStatement.setBoolean(9, bill.isCalculated());
//
//            int result = preparedStatement.executeUpdate();
//            System.out.println("query was executed " + result + " rows were affected");
//            preparedStatement.close();
//            connection.close();
//        } catch (SQLException sqlException) {
//            System.out.println("Adding Every day milk purchase was failed with reason" + sqlException.getMessage());
//        }
//
//    }
//
//    @Override
//    public void update(EveryDayMilkModel bill) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = connection.prepareStatement("UPDATE  everyday_milk_purchase SET date = ?, shift = ?, farmer_id = ?, collector_id = ?, count_ml = ?, count_kg = ?, price = ?, calculated = ? where id = ?");
//
//            pstmt.setString(1, bill.getDate());
//            pstmt.setString(2, bill.getShift());
//            pstmt.setInt(3, bill.getFarmerID());
//            pstmt.setInt(4, bill.getCollectorID());
//            pstmt.setFloat(5, bill.getCountMilliLiter());
//            pstmt.setFloat(6, bill.getCountKiloGram());
//            pstmt.setFloat(7, bill.getPrice());
//            pstmt.setBoolean(8, bill.isCalculated());
//            pstmt.setInt(9, bill.getId());
//
//            int result = pstmt.executeUpdate();
//
//            System.out.println("query was executed " + result + " rows were affected");
//            pstmt.close();
//            connection.close();
//
//        } catch (SQLException sqlException) {
//            System.out.println("Update Every day milk purchase data was failed with reson" + sqlException.getMessage());
//        }
//    }
//
//    @Override
//    public void delete(int id) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM  WHERE id = ?");
//            pstmt.setInt(1, id);
//
//            int result = pstmt.executeUpdate();
//            System.out.println("query was executed " + result + " rows were affected");
//            pstmt.close();
//            connection.close();
//        } catch (SQLException sqlException) {
//            System.out.println("Delete Every day milk purchase data by ID was failed with reson" + sqlException.getMessage());
//        }
//    }
//
//    @Override
//    public List<EveryDayMilkModel> getByFarmerId(int farmerID) {
//        List<EveryDayMilkModel> farmerData = new LinkedList<>();
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM  WHERE farmer_id = ?");
//            pstmt.setInt(1, farmerID);
//
//            ResultSet resultSet = pstmt.executeQuery();
//
//            while (resultSet.next()) {
//                farmerData.add(mapModel(resultSet));
//            }
//
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//        return farmerData;
//    }
//
//    @Override
//    public List<EveryDayMilkModel> getFarmerListBeginEndDate(int farmerID, String dateBegin, String dateEnd) {
//        List<EveryDayMilkModel> farmerData = new LinkedList<>();
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM everyday_milk_purchase  WHERE farmer_id = ? and date BETWEEN ? and ?");
//            pstmt.setInt(1, farmerID);
//            pstmt.setString(2, dateBegin);
//            pstmt.setString(3, dateEnd);
//
//            ResultSet resultSet = pstmt.executeQuery();
//
//            while (resultSet.next()) {
//                farmerData.add(mapModel(resultSet));
//            }
//
//        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//        return farmerData;
//    }
//
//    @Override
//    public void calculateMilkBeginEnd(int farmerID, String dateBegin, String dateEnd, boolean flag) {
//        try {
//            Connection connection = ConnectToDataBase.getConnection();
//            PreparedStatement pstmt = connection.prepareStatement("UPDATE  everyday_milk_purchase SET calculated = ? where  farmer_id = ? AND date BETWEEN ? and ?");
//
//            pstmt.setBoolean(1, flag);
//            pstmt.setInt(2, farmerID);
//            pstmt.setString(3, dateBegin);
//            pstmt.setString(4, dateEnd);
//
//            int result = pstmt.executeUpdate();
//
//            System.out.println("query was executed " + result + " rows were affected");
//            pstmt.close();
//            connection.close();
//
//        } catch (SQLException sqlException) {
//            System.out.println("Update Every day milk purchase data was failed with reson" + sqlException.getMessage());
//        }
//    }
//
//
//    EveryDayMilkModel mapModel(ResultSet resultSet) throws SQLException {
//        EveryDayMilkModel data = new EveryDayMilkModel();
//        data.setDate(resultSet.getString("date"));
//        data.setShift(resultSet.getString("shift"));
//        data.setFarmerID(resultSet.getInt("farmer_id"));
//        data.setCollectorID(resultSet.getInt("collector_id"));
//        data.setCountMilliLiter(resultSet.getFloat("count_ml"));
//        data.setCountKiloGram(resultSet.getFloat("count_kg"));
//        data.setPrice(resultSet.getFloat("price"));
//        data.setCalculated(resultSet.getBoolean("calculated"));
//        return data;
//
//    }
//}
