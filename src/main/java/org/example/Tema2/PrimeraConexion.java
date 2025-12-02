package org.example.Tema2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PrimeraConexion {
    public static void main(String[] args) {

        try {
            // url
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/basedatos", "root", "1234");
            System.out.println("Conectado a la base de datos");
            conexion.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}