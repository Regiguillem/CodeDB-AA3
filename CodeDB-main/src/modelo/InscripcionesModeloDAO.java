package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import controlador.ExcursionesControlador;


public class InscripcionesModeloDAO {

    private Datos datos = Datos.getInstance();
    private Connection connection;

    public InscripcionesModeloDAO(Connection connection) {
        this.connection = connection;
    }

    //METODO PARA AGREGAR INSCRIPCIONES
    public void agregarInscripcion(InscripcionesModelo inscripcion) throws SQLException {
        String sql = "INSERT INTO inscripciones (n_inscripcion, n_socio, codigo_excursion, fecha_inscripcion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, inscripcion.getN_inscripcion());
            statement.setInt(2, inscripcion.getSocio().getN_socio());
            statement.setString(3, inscripcion.getExcursion().getCodigo());
            statement.setDate(4, java.sql.Date.valueOf(inscripcion.getFechaInscripcion()));
            statement.executeUpdate();
        }
    }

    //METODO PARA OBTENER TODAS LAS INSCRIPCIONES
    public ArrayList<InscripcionesModelo> obtenerTodasLasInscripciones() throws SQLException {
        ArrayList<InscripcionesModelo> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripciones";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int n_inscripcion = resultSet.getInt("n_inscripcion");
                // Aquí obtienes el ID del socio y el código de la excursión desde la tabla Inscripciones
                int idSocio = resultSet.getInt("n_socio");
                String codigoExcursion = resultSet.getString("codigo_excursion");
                LocalDate fechaInscripcion = resultSet.getDate("fecha_inscripcion").toLocalDate();
                // Ahora, necesitas obtener el socio y la excursión usando los ID y códigos recuperados
                SociosModelo socio = obtenerSocioPorId(idSocio);
                ExcursionesModelo excursion = obtenerExcursionPorCodigo(codigoExcursion);
                // Una vez que tienes socio y excursión, puedes crear la inscripción
                InscripcionesModelo inscripcion = new InscripcionesModelo(n_inscripcion, socio, excursion, fechaInscripcion);
                inscripciones.add(inscripcion);
            }
        }
        return inscripciones;
    }

    // METODO PARA OBTENER INSCRIPCIONES POR SOCIO
    public ArrayList<InscripcionesModelo> obtenerInscripcionesPorSocio(SociosModelo socio) throws SQLException {
        ArrayList<InscripcionesModelo> inscripciones = new ArrayList<>();
        String sql = "SELECT * FROM inscripciones WHERE n_socio = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, socio.getN_socio());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int n_inscripcion = resultSet.getInt("n_inscripcion");
                    String codigoExcursion = resultSet.getString("codigo_excursion");
                    LocalDate fechaInscripcion = resultSet.getDate("fecha_inscripcion").toLocalDate();
                    ExcursionesModelo excursion = obtenerExcursionPorCodigo(codigoExcursion);
                    InscripcionesModelo inscripcion = new InscripcionesModelo(n_inscripcion, socio, excursion, fechaInscripcion);
                    inscripciones.add(inscripcion);
                }
            }
        }
        return inscripciones;
    }

    //METODO PARA OBTENER INSCRIPCIONESPORNUMERO
    public InscripcionesModelo obtenerInscripcionPorNumero(int numeroInscripcion) throws SQLException {
        String sql = "SELECT * FROM Inscripciones WHERE n_inscripcion = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, numeroInscripcion);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int n_inscripcion =resultSet.getInt("n_inscripcion");
                    int idSocio = resultSet.getInt("n_socio");
                    String codigoExcursion = resultSet.getString("codigo_excursion");
                    LocalDate fechaInscripcion = resultSet.getDate("fecha_inscripcion").toLocalDate();
                    SociosModelo socio = obtenerSocioPorId(idSocio);
                    ExcursionesModelo excursion = obtenerExcursionPorCodigo(codigoExcursion);
                    return new InscripcionesModelo(n_inscripcion, socio, excursion, fechaInscripcion);
                }
            }
        }
        return null;
    }

    // METODO PARA BUSCAR INSCRIPCIONES POR CODIGO Y ELIMINARLAS
    public InscripcionesModelo buscarInscripcionPorCodigo(int codigo) {
        InscripcionesModelo inscripcion = null;
        String sql = "SELECT * FROM inscripciones WHERE n_inscripcion = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                // Construye un objeto InscripcionesModelo con los datos del resultado de la consulta
                inscripcion = new InscripcionesModelo(rs.getInt("n_inscripcion"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo adecuado de la excepción
        }
        return inscripcion;
    }




    //METODO PARA ELIMINAR INSCRIPCIONES
    public void eliminarInscripcion(InscripcionesModelo inscripcion) throws SQLException {
        String sql = "DELETE FROM inscripciones WHERE n_inscripcion = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, inscripcion.getN_inscripcion());
            statement.executeUpdate();
        }
    }


    private SociosModelo obtenerSocioPorId(int idSocio) throws SQLException {
        // FALTARÍA IMPLEMENTAR EL MÉTODO OBTENERSOCIOPORID (ADRI)
        return null;
    }

    public ExcursionesModelo obtenerExcursionPorCodigo(String codigo) {
        ArrayList<ExcursionesModelo> excursiones = datos.getExcursiones();
        for (ExcursionesModelo excursion : excursiones) {
            if (excursion.getCodigo().equals(codigo)) {
                return excursion;
            }
        }
        return null;
    }

}

