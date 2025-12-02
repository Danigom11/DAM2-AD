package org.example.Tema2.SimulacroExamen.Ejercicio3;
import java.sql.*;

public class ProductoDAO {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/tienda";
        String usuario = "usuario";
        String pass = "pass";

        try (Connection conexion = DriverManager.getConnection(url, usuario, pass)) {
            String sqlInsert = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";

            try (PreparedStatement psInsert = conexion.prepareStatement(sqlInsert)) {
                psInsert.setString(1, "Laptop");
                psInsert.setDouble(2, 899.99);
                psInsert.setInt(3, 10);
                psInsert.executeUpdate();

                psInsert.setString(1, "Mouse");
                psInsert.setDouble(2, 25.50);
                psInsert.setInt(3, 50);
                psInsert.executeUpdate();

                psInsert.setString(1, "Teclado");
                psInsert.setDouble(2, 45.00);
                psInsert.setInt(3, 30);
                psInsert.executeUpdate();
            }

            conexion.setAutoCommit(false);
            try {
                String sqlUpdate = "UPDATE productos SET stock = stock + ? WHERE id = ?";

                try (PreparedStatement psUpdate = conexion.prepareStatement(sqlUpdate)) {
                    psUpdate.setInt(1, -5);
                    psUpdate.setInt(2, 1);
                    int filasAfectadas1 = psUpdate.executeUpdate();
                    System.out.println("Stock del producto id=1 reducido en 5 unidades. Filas afectadas: " + filasAfectadas1);

                    psUpdate.setInt(1, 10);
                    psUpdate.setInt(2, 2);
                    int filasAfectadas2 = psUpdate.executeUpdate();
                    System.out.println("Stock del producto id=2 aumentado en 10 unidades. Filas afectadas: " + filasAfectadas2);
                }
                conexion.commit();
                System.out.println("Transacción confirmada.");

            } catch (SQLException e) {
                // Si ocurre error, deshacer cambios
                System.out.println("Error en transacción: " + e.getMessage());
                conexion.rollback();
                System.out.println("Transacción revertida.");
            } finally {
                conexion.setAutoCommit(true);
            }

            String sqlSelect = "SELECT id, nombre, stock FROM productos";

            try (PreparedStatement psSelect = conexion.prepareStatement(sqlSelect);
                ResultSet rs = psSelect.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int stock = rs.getInt("stock");

                    System.out.printf("%d\t%-15s\t%d%n", id, nombre, stock);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión o ejecución: " + e.getMessage());
        }
        // Los recursos se cierran automáticamente gracias a try-with-resources
    }
}