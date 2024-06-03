import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DB {

    private static Properties properties = new Properties();
    private static Connection conn = null;

    public DB() { // Constructor to load the properties file
        loadProperties();
    }

    static { // Static block to load the properties file (found on reddit a solution to my problem)
        loadProperties();
    }

    private static void loadProperties() { // Loads database configuration properties from a file
        try (InputStream input = DB.class.getClassLoader().getResourceAsStream("dbconfig.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find dbconfig.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getInstance() { // Returns the database connection instance
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String dbUrl = properties.getProperty("db.url");
                String user = properties.getProperty("db.user");
                String password = properties.getProperty("db.password");
                conn = DriverManager.getConnection(dbUrl, user, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection() { // Closes the database connection
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean InsertUser(String username, String password) { // Inserts a new user into the database
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

    public static User getUserByUsername(String username) {  // Retrieves a user by username from the database
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

    public static List<User> getAllUsers() { // Retrieves all users from the database
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

    public static void updateUser(User user) { // Updates user information in the database
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

    
