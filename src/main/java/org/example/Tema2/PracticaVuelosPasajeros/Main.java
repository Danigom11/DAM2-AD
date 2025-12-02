package org.example.Tema2.PracticaVuelosPasajeros;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main extends JFrame {

    // La región norte para poner los tres botones
    JPanel regionNorte;
    // La región central que muestra todos los datos de la base de datos
    JPanel regionCentro;
    // La región sur para el resto de acciones a mostrar
    JPanel regionSur;

    // Botones para las bases de datos
    JButton botonOracle;
    JButton botonMySQL;
    JButton botonSQLite;

    // Un área de texto que muestre toda la información en la zona central
    JTextArea textAreaDatos;
    JScrollPane scrollPane;
    static String resultado = "";

    // Ver la información de empleados de un departamento
    JLabel labelDepartamento;
    JTextField textDepartamento;
    JButton botonVerDepartamento;

    // Insertar un departamento cuyos valores se pasan por parámetros
    JLabel labelInsertarDepartamento;
    JTextField textInsertarDepartamento;
    JButton botonInsertarDepartamento;

    // Borrar un departamento por su número
    JLabel labelNumeroDepartamento;
    JTextField textNumeroDepartamento;
    JButton botonBorrarDepartamento;

    // incrementar el salario de los empleados una cantidad
    JLabel labelIncrementoSalario;
    JTextField textIncrementoSalario;
    JButton botonIncrementarSalario;

    // Constructor
    public Main() {
        // Configuración general ventana
        setTitle("Conexiones a las bases de datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1000, 600);

        // Zona norte con los 3 botones
        regionNorte = new JPanel();
        regionNorte.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // top, left, bottom, right
        botonOracle = new JButton("ORACLE");
        botonMySQL = new JButton("MySQL");
        botonSQLite = new JButton("SQLite");

        botonOracle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultado = conectarVerBaseDatosOracle();
                textAreaDatos.setText(resultado);
                regionSur.setVisible(true);
                regionSur.revalidate();
                regionSur.repaint();
            }
        });
        botonMySQL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultado = conectarVerBaseDatosMySQL();
                textAreaDatos.setText(resultado);
                regionSur.setVisible(false);
                regionSur.revalidate();
                regionSur.repaint();
            }
        });
        botonSQLite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultado = conectarVerBaseDatosSQLite();
                textAreaDatos.setText(resultado);
                regionSur.setVisible(false);
                regionSur.revalidate();
                regionSur.repaint();
            }
        });
        regionNorte.add(botonOracle);
        regionNorte.add(botonMySQL);
        regionNorte.add(botonSQLite);
        add(regionNorte, BorderLayout.NORTH);

        // Zona centro con la información
        regionCentro = new JPanel(new BorderLayout());
        regionCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // top, left, bottom, right
        textAreaDatos = new JTextArea("Selecciona una base de datos para conectar");
        textAreaDatos.setEditable(false);
        textAreaDatos.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textAreaDatos.setLineWrap(true);
        textAreaDatos.setWrapStyleWord(true);
        scrollPane = new JScrollPane(textAreaDatos);
        regionCentro.add(scrollPane, BorderLayout.CENTER);
        add(regionCentro, BorderLayout.CENTER);

        // Zona sur con las opciones
        // Solo visible al pulsar al botón oracle
        regionSur = new JPanel(new GridLayout(4, 2, 10,10));
        regionSur.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // top, left, bottom, right

        // Ver la información de empleados de un departamento
        labelDepartamento = new JLabel("Número de departamento");
        textDepartamento = new JTextField( 20);
        botonVerDepartamento = new JButton("Ver");
        botonVerDepartamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultado = verDepartamento();
                textAreaDatos.setText(resultado);
            }
        });

        // Insertar un departamento cuyos valores se pasan por parámetros
        labelInsertarDepartamento = new JLabel("Datos departamento");
        textInsertarDepartamento = new JTextField(20);
        botonInsertarDepartamento = new JButton("Insertar");
        botonInsertarDepartamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultado = insertarDepartamento();
                textAreaDatos.setText(resultado);
            }
        });

        // Borrar un departamento por su número
        labelNumeroDepartamento = new JLabel("Número de departamento a borrar");
        textNumeroDepartamento = new JTextField(20);
        botonBorrarDepartamento = new JButton("Borrar");
        botonBorrarDepartamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultado = borrarDepartamento();
                textAreaDatos.setText(resultado);
            }
        });

        // incrementar el salario de los empleados una cantidad
        labelIncrementoSalario = new JLabel("Incremento salario");
        textIncrementoSalario = new JTextField(20);
        botonIncrementarSalario = new JButton("Incrementar");
        botonIncrementarSalario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultado = incrementarSalario();
                textAreaDatos.setText(resultado);
            }
        });

        // Añadirlo todo a la región sur
        regionSur.add(labelDepartamento);
        regionSur.add(textDepartamento);
        regionSur.add(botonVerDepartamento);
        regionSur.add(labelInsertarDepartamento);
        regionSur.add(textInsertarDepartamento);
        regionSur.add(botonInsertarDepartamento);
        regionSur.add(labelNumeroDepartamento);
        regionSur.add(textNumeroDepartamento);
        regionSur.add(botonBorrarDepartamento);
        regionSur.add(labelIncrementoSalario);
        regionSur.add(textIncrementoSalario);
        regionSur.add(botonIncrementarSalario);

        // Hacer invisible
        regionSur.setVisible(false);
        // Insertar la región sur en su sitio
        add(regionSur, BorderLayout.SOUTH);

    }

    private String incrementarSalario() {
        resultado = "";
        try {
            Connection conexionOracle = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "dani", "1234");
            Statement statement = conexionOracle.createStatement();
            String sql = "UPDATE emp SET sal = sal + " + textIncrementoSalario.getText().trim();
            int filas = statement.executeUpdate(sql);
            resultado = "Salarios actualizados correctamente. Filas afectadas: " + filas;
            statement.close();
            conexionOracle.close();
        } catch (SQLException e){
            resultado = "Problema al ejecutar el comando: " + e.getMessage();
        }
        return resultado;
    }

    private String borrarDepartamento() {
        resultado = "";
        try {
            Connection conexionOracle = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "dani", "1234");
            Statement statement = conexionOracle.createStatement();
            String sql = "DELETE FROM dept WHERE deptno = " + textNumeroDepartamento.getText().trim();
            int filas = statement.executeUpdate(sql);
            resultado = "Filas afectadas: " + filas;
            statement.close();
            conexionOracle.close();
        } catch (SQLException e){
            resultado = "Problema al ejecutar el comando: " + e.getMessage();
        }
        return resultado;
    }

    private String insertarDepartamento() {
        resultado = "";
        try {
            Connection conexionOracle = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "dani", "1234");
            Statement statement = conexionOracle.createStatement();
            String sql = "INSERT INTO dept VALUES (" + textInsertarDepartamento.getText().trim() + ")";
            int filas = statement.executeUpdate(sql);
            resultado = "Filas insertadas: " + filas;

            statement.close();
            conexionOracle.close();
        } catch (SQLException e){
            resultado = "Problema al ejecutar el comando: " + e.getMessage();
        }
        return resultado;
    }

    private String verDepartamento() {
        resultado = "";
        try {
            Connection conexionOracle = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "dani", "1234");
            String sql = "SELECT * FROM dept WHERE deptno = " + textDepartamento.getText().trim();
            Statement statement = conexionOracle.createStatement();
            ResultSet resul = statement.executeQuery(sql);
            while (resul.next()){
                resultado = resul.getInt(1) + " " + resul.getString(2) + " " + resul.getString(3);
            }
            statement.close();
            conexionOracle.close();
        } catch (SQLException e){
            resultado = "Problema al ejecutar el comando: " + e.getMessage();
        }
        return resultado;
    }

    public static String conectarVerBaseDatosOracle() {
        resultado = "";
        try {
            // ORACLE
            Connection conexionOracle = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "dani", "1234");
            resultado += "Conectado a la base de datos de ORACLE\n";
            resultado += mostrarBaseDatosOracle(conexionOracle);

            conexionOracle.close();
        } catch (SQLException e) {
            resultado += "Error al conectar a Oracle:\n" + e.getMessage() + "\n";
        }
        return resultado;
    }

    public static String conectarVerBaseDatosMySQL() {
        resultado = "";
        try {
            // MySQL
            Connection conexionMySQL = DriverManager.getConnection("jdbc:mysql://localhost:3307/empresa", "root", "");
            resultado += "Conectado a la base de datos de MySQL\n";
            resultado += mostrarBaseDatosMySQL(conexionMySQL);
            conexionMySQL.close();
        } catch (SQLException e) {
            resultado += "Error al conectar a MySQL:\n" + e.getMessage() + "\n";
        }
        return resultado;
    }

    public static String conectarVerBaseDatosSQLite() {
        resultado = "";
        try {
            // SQLITE
            Connection conexionSQLite = DriverManager.getConnection("jdbc:sqlite:C:/Users/danig/Downloads/SQLite/bbdd_conectores.db");
            resultado += "Conectado a la base de datos de MySQLite\n";
            resultado += mostrarBaseDatosSQLite(conexionSQLite);
            conexionSQLite.close();
        } catch (SQLException e) {
            resultado += "Error al conectar a SQLite:\n" + e.getMessage() + "\n";
        }
        return resultado;
    }
    private static String mostrarBaseDatosSQLite(Connection conexionSQLite) {
        String datos = "";
        try {
            Statement sentencia = conexionSQLite.createStatement();
            String sql = "SELECT * FROM DEPT";
            ResultSet resul = sentencia.executeQuery(sql);
            // DEPT
            /*
            DEPTNO INT NOT NULL PRIMARY KEY,
            DNAME VARCHAR(14),
            LOC VARCHAR(13)
             */
            datos += "---- BASE DE DATOS DEPT CON SQLITE ----\n";
            while (resul.next()){
                datos += String.format("%d, %s, %s\n",
                        resul.getInt(1),
                        resul.getString(2),
                        resul.getString(3));
            }

            // EMP
            /*
            EMPNO INT NOT NULL PRIMARY KEY,
            ENAME VARCHAR(10),
            JOB VARCHAR(9),
            MGR INT,
            HIREDATE DATE,
            SAL REAL,
            COMM REAL,
            DEPTNO INT,
             */
            sql = "SELECT * FROM EMP";
            resul = sentencia.executeQuery(sql);
            datos += "---- BASE DE DATOS EMP CON SQLITE----\n";
            while (resul.next()) {
                // SQLite almacena fechas como texto, usar getString en lugar de getDate
                String fecha = resul.getString(5);
                if (fecha == null) fecha = "NULL";

                datos += String.format("%d, %s, %s, %d, %s, %.2f, %.2f, %d\n",
                        resul.getInt(1),
                        resul.getString(2),
                        resul.getString(3),
                        resul.getInt(4),
                        fecha,
                        resul.getDouble(6),
                        resul.getDouble(7),
                        resul.getInt(8)
                );
            }

            resul.close();
            sentencia.close();
        } catch (SQLException e) {
            datos += ("Problema con la base de datos SQL: " + e.getMessage() + "\n");
        }
        return datos;
    }

    private static String mostrarBaseDatosMySQL(Connection conexionMySQL) {
        String datos = "";
        try {
            Statement sentencia = conexionMySQL.createStatement();
            String sql = "SELECT * FROM DEPT";
            ResultSet resul = sentencia.executeQuery(sql);
            // DEPT
            /*
            DEPTNO INT NOT NULL PRIMARY KEY,
            DNAME VARCHAR(14),
            LOC VARCHAR(13)
             */
            datos += "---- BASE DE DATOS DEPT CON MYSQL ----\n";
            while (resul.next()){
                datos += String.format("%d, %s, %s\n",
                        resul.getInt(1),
                        resul.getString(2),
                        resul.getString(3));
            }

            // EMP
            /*
            EMPNO INT NOT NULL PRIMARY KEY,
            ENAME VARCHAR(10),
            JOB VARCHAR(9),
            MGR INT,
            HIREDATE DATE,
            SAL REAL,
            COMM REAL,
            DEPTNO INT,
             */
            sql = "SELECT * FROM EMP";
            resul = sentencia.executeQuery(sql);
            datos += "---- BASE DE DATOS EMP CON MYSQL----\n";
            while (resul.next()) {
                // SQLite almacena fechas como texto, usar getString en lugar de getDate
                String fecha = resul.getString(5);
                if (fecha == null) fecha = "NULL";

                datos += String.format("%d, %s, %s, %d, %s, %.2f, %.2f, %d\n",
                        resul.getInt(1),
                        resul.getString(2),
                        resul.getString(3),
                        resul.getInt(4),
                        fecha,
                        resul.getDouble(6),
                        resul.getDouble(7),
                        resul.getInt(8)
                );
            }

            resul.close();
            sentencia.close();
        } catch (SQLException e) {
            datos += ("Problema con la base de datos SQL: " + e.getMessage() + "\n");
        }
        return datos;
    }

    public static String mostrarBaseDatosOracle(Connection conexionOracle){
        String datos = "";
        try {
            Statement sentencia = conexionOracle.createStatement();
            String sql = "SELECT * FROM DEPT";
            ResultSet resul = sentencia.executeQuery(sql);
            // DEPT
            /*
            DEPTNO INT NOT NULL PRIMARY KEY,
            DNAME VARCHAR(14),
            LOC VARCHAR(13)
             */
            datos += "---- BASE DE DATOS DEPT CON ORACLE ----\n";
            while (resul.next()){
                datos += String.format("%d, %s, %s\n",
                        resul.getInt(1),
                        resul.getString(2),
                        resul.getString(3));
            }

            // EMP
            /*
            EMPNO INT NOT NULL PRIMARY KEY,
            ENAME VARCHAR(10),
            JOB VARCHAR(9),
            MGR INT,
            HIREDATE DATE,
            SAL REAL,
            COMM REAL,
            DEPTNO INT,
             */
            sql = "SELECT * FROM EMP";
            resul = sentencia.executeQuery(sql);
            datos += "---- BASE DE DATOS EMP CON ORACLE----\n";
            while (resul.next()) {
                // SQLite almacena fechas como texto, usar getString en lugar de getDate
                String fecha = resul.getString(5);
                if (fecha == null) fecha = "NULL";

                datos += String.format("%d, %s, %s, %d, %s, %.2f, %.2f, %d\n",
                        resul.getInt(1),
                        resul.getString(2),
                        resul.getString(3),
                        resul.getInt(4),
                        fecha,
                        resul.getDouble(6),
                        resul.getDouble(7),
                        resul.getInt(8)
                );
            }

            resul.close();
            sentencia.close();
        } catch (SQLException e) {
            datos += ("Problema con la base de datos SQL: " + e.getMessage() + "\n");
        }
        return datos;
    }

    public static void main(String[] args) {
        Main ventana = new Main();
        ventana.setVisible(true);
    }
}