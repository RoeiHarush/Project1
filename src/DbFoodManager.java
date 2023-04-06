import java.sql.*;
import java.util.List;

public class DbFoodManager {
    public String url = "jdbc:postgresql://localhost:5432/zoo";
    public String username = "postgres";
    public String password = "roei";
    Connection connection;

    public DbFoodManager(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection to PostgresSQL database established!");
        } catch (SQLException e) {
            System.err.println("Connection to PostgresSQL database failed: " + e.getMessage());
        }
    }


    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection to PostgresSQL database closed!");
        } catch (SQLException e) {
            System.err.println("Failed to close connection to PostgresSQL database: " + e.getMessage());
        }
    }
    public void insertFood(Food food)
    {

        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "INSERT INTO foods (type, quantity) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, food.getName());
                pstmt.setInt(2, food.getQuantity());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void retrieveFoods(List<Food> foods) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, username ,password);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM foods");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                FoodType name = FoodType.valueOf(rs.getString("type"));
                int quantity = rs.getInt("quantity");
                Food food = new Food(name,quantity);
                System.out.println(food.getFoodInfo());
                foods.add(food);
            }
        }
    }
    public void updateQuantity(String foodType, int quantity) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement("UPDATE foods SET quantity = ? WHERE type = ?")) {
            stmt.setInt(1, quantity);
            stmt.setString(2, foodType);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating food quantity: " + e.getMessage());
        }


    }

    }


