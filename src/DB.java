import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DB {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/UserDB";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    
    private static Connection conn = null;

    public static Connection getInstance() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean InsertUser(String username, String password) {
        try {
            String query = "INSERT INTO Users (Username, Password, Level, Experience, Easy, Normal, Hard, Impossible, Levels) VALUES (?, ?, 1, 0, 0, 0, 0, 0, 0)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User getUserByUsername(String username) {
        User user = null;
        try {
            String query = "SELECT * FROM Users WHERE Username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                    resultSet.getInt("ID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getInt("Level"),
                    resultSet.getInt("Experience"),
                    resultSet.getInt("Easy"),
                    resultSet.getInt("Normal"),
                    resultSet.getInt("Hard"),
                    resultSet.getInt("Impossible"),
                    resultSet.getInt("Levels")
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Users";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                    resultSet.getInt("ID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getInt("Level"),
                    resultSet.getInt("Experience"),
                    resultSet.getInt("Easy"),
                    resultSet.getInt("Normal"),
                    resultSet.getInt("Hard"),
                    resultSet.getInt("Impossible"),
                    resultSet.getInt("Levels")
                );
                userList.add(user);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static void updateUser(User user) {
        try {
            String query = "UPDATE Users SET Password = ?, Level = ?, Experience = ?, Easy = ?, Normal = ?, Hard = ?, Impossible = ?, Levels = ? WHERE ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getLevel());
            preparedStatement.setInt(3, user.getExperience());
            preparedStatement.setInt(4, user.getEasy());
            preparedStatement.setInt(5, user.getNormal());
            preparedStatement.setInt(6, user.getHard());
            preparedStatement.setInt(7, user.getImpossible());
            preparedStatement.setInt(8, user.getLevels());
            preparedStatement.setInt(9, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    
