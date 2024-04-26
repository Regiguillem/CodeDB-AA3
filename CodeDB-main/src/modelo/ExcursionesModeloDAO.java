package modelo;

import util.DataBaseConnection;
import vista.ExcursionesVista;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ExcursionesModeloDAO {
    private Connection connection;

    private ExcursionesVista vistaEx;

    public ExcursionesModeloDAO(Connection connection) {
        this.connection = connection;
        this.vistaEx = new ExcursionesVista();
    }

    public void agregarExcursion(ExcursionesModelo excursion) throws SQLException {
        String sql = "INSERT INTO Excursiones (codigo, descripcion, fecha, n_dias, precio) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, excursion.getCodigo());
            statement.setString(2, excursion.getDescripcion());
            statement.setDate(3, java.sql.Date.valueOf(excursion.getFecha()));
            statement.setInt(4, excursion.getN_dias());
            statement.setDouble(5, excursion.getPrecio());
            statement.executeUpdate();
        }
    }

    public void filtrarExcursion(LocalDate fechaIni, LocalDate fechaFin) throws SQLException {
        String sql = "SELECT * FROM Excursiones WHERE fecha BETWEEN ? AND ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(fechaIni));
            statement.setDate(2, java.sql.Date.valueOf(fechaFin));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String codigo = resultSet.getString("codigo");
                    String descripcion = resultSet.getString("descripcion");
                    LocalDate fecha = resultSet.getDate("fecha").toLocalDate();
                    int n_dias = resultSet.getInt("n_dias");
                    double precio = resultSet.getDouble("precio");
                    ExcursionesModelo excursion = new ExcursionesModelo(codigo, descripcion, fecha, n_dias, precio);
                    vistaEx.mostrarExcursion(excursion);
                }
            }
        }
    }
}
