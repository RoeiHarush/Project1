import java.sql.*;
import java.util.*;

public class DbZooManager {
    final String url = "jdbc:postgresql://localhost:5432/zoo";
    final String username = "postgres";
    final String password = "roei";
    Connection connection;


    public DbZooManager(String url, String username, String password) {
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

    public void insertAnimal(Animal animal) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {

            String sql = "INSERT INTO zoo (name, kind, points) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, animal.getName());
                pstmt.setString(2, animal.getKind().toString());
                pstmt.setInt(3, animal.getPoints());
                pstmt.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePoints(String animalName, int points) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement("UPDATE zoo SET points = ? WHERE name = ?")) {
            stmt.setInt(1,points);
            stmt.setString(2,animalName);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating animal points: " + e.getMessage());
        }



    }

    public void retrieveAnimals(List<Animal> animals) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, username ,password);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM zoo");
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                AnimalType kind = AnimalType.valueOf(rs.getString("kind"));
                int points = rs.getInt("points");
                Animal animal = new Animal(kind, name);
                animal.points = points;
                System.out.println(animal.getAnimalInfo());
                animals.add(animal);
            }
        }
    }
}



