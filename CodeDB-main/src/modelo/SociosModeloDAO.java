/*package modelo;

import util.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SociosModeloDAO (Connection connection){
    private static final String SELECT_ALL = "SELECT * FROM SociosModelo";
    private static final String SELECT_FEDERADO = "SELECT * FROM SociosModelo WHERE tipo = 'Federado'";
    private static final String SELECT_ESTANDAR = "SELECT * FROM SociosModelo WHERE tipo = 'Estandar'";
    private static final String SELECT_INFANTIL = "SELECT * FROM SociosModelo WHERE tipo = 'Infantil'";
    private static final String INSERT = "INSERT INTO SociosModelo VALUES (?, ?, ?)";


    public SociosModelo obtenerPorId(String n_socio_SociosModelo) {
        SociosModelo SociosModelo = null;
        String query = "SELECT * FROM SociosModelo WHERE n_socio_SociosModelo = ?";

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, n_socio_SociosModelo);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    SociosModelo = mapearDesdeResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el Socios por numero de socio", e);
        }

        return SociosModelo;
    }

    public SociosModelo obtenerPorNombre(String nombre) {
        SociosModelo SociosModelo = null;
        String query2 = "SELECT * FROM SociosModelo WHERE nombre = ?";

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query2)) {

            preparedStatement.setString(1, nombre);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    SociosModelo = mapearDesdeResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener el Socios por nombre", e);
        }

        return SociosModelo;
    }

    public List<SociosModelo> obtenerTodos() {
        List<SociosModelo> SociosModelo = new ArrayList<>();

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                SociosModelo.add(mapearDesdeResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener todos los Socios", e);
        }

        return SociosModelo;
    }

    public List<SociosModelo> obtenerSociosFederados() {
        List<SociosModelo> SociosFederadosModelo = new ArrayList<>();

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_FEDERADO);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tipoSocio = resultSet.getString("tipo");

                if ("Federado".equalsIgnoreCase(tipoSocio)) {
                    SociosFederadosModelo.add(new SociosFederadosModelo(
                            resultSet.getString("n_socio"),//no se si ponerlo ya que si que esta en la clase super pero no en las demas
                            resultSet.getString("nombre"),
                            resultSet.getString("nif"),
                            resultSet.getString("federacion"),
                            resultSet.getString("descuento_cuota"),
                            resultSet.getString("descuento_exc"),
                            )
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener Socios Federados", e);
        }

        return SociosFederadosModelo;
    }

    public List<SociosModelo> obtenerSocioEstandarModelo() {
        List<SociosModelo> SocioEstandarModelo = new ArrayList<>();

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ESTANDAR);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tipoSocio = resultSet.getString("tipo");

                if ("estandar".equalsIgnoreCase(tipoSocio)) {
                    SocioEstandarModelo.add(new SocioEstandarModelo(
                            resultSet.getString("n_socio"),
                            resultSet.getString("nombre"),
                            resultSet.getString("nif"),
                            resultSet.getString("seguro"),

                            ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener Socios estándar", e);
        }

        return SocioEstandarModelo;
    }

    public List<SociosModelo> obtenerSocioInfantilModelo() {
        List<SociosModelo> SocioInfantilModelo = new ArrayList<>();

        DataBaseConnection DatabaseConection = null;
        try (Connection connection = DatabaseConection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_INFANTIL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tipoSocio = resultSet.getString("tipo");

                if ("estandar".equalsIgnoreCase(tipoSocio)) {
                    SocioInfantilModelo.add(new SocioInfantilModelo(
                            resultSet.getString("n_socio"),
                            resultSet.getString("nombre"),
                            resultSet.getString("n_socioPadreMadre"),
                            resultSet.getString("descuento_cuota"),
                            ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener Socios Infantiles", e);
        }

        return SocioInfantilModelo;
    }


    private SociosModelo mapearDesdeResultSet(ResultSet resultSet) throws SQLException {
        String tipoSocio = resultSet.getString("tipo");

        if ("Federado".equalsIgnoreCase(tipoSocio)) {
            return new SociosFederadosModelo(
                    resultSet.getString("n_socio"),
                    resultSet.getString("nombre"),
                    resultSet.getString("nif"),
                    resultSet.getString("federacion"),
                    resultSet.getString("descuento_cuota"),
                    resultSet.getString("descuento_exc"),

                    );
        } if ("Estandar".equalsIgnoreCase(tipoSocio)) {
            return new SocioEstandarModelo(
                    resultSet.getString("n_socio"),
                    resultSet.getString("nombre"),
                    resultSet.getString("nif"),
                    resultSet.getString("seguro"),

                    );
        }if ("Infantil".equalsIgnoreCase(tipoSocio)) {
            return new SocioInfantilModelo(
                    resultSet.getString("n_socio"),
                    resultSet.getString("nombre"),
                    resultSet.getString("n_socioPadreMadre"),
                    resultSet.getString("descuento_cuota"),

                    );
        }
    }

    public void insertar(SociosModelo socio) {
        DataBaseConnection DatabaseConection;
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {

            mapearAStatement(statement, socio);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar el socio", e);
        }
    }
    private void mapearAStatement(PreparedStatement statement, SociosModelo Socio) throws SQLException {
        statement.setString(1, Socio.getN_socio());
        statement.setString(2, Socio.getNombre());
        statement.setDouble(3, Socio.getCuotaMensual());


    }

}
*/
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SociosModeloDAO {
    private static Connection connection;

    public SociosModeloDAO(Connection connection) {
        this.connection = this.connection;
    }

    // Método para agregar un nuevo socio a la base de datos
    public void agregarSocio(SociosModelo socio) {
        // Lógica para insertar un nuevo socio en la base de datos
        try {
            // Preparar la consulta SQL para insertar un nuevo socio
            String consulta = "INSERT INTO Socios (nombre, cuota_mensual, tipo_socio, nif, federacion_codigo, seguro) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement consultaPreparada = connection.prepareStatement(consulta);
            // Establecer los valores para los parámetros de la consulta
            consultaPreparada.setString(1, socio.getNombre());
            consultaPreparada.setDouble(2, socio.getCuotaMensual());
            consultaPreparada.setString(3, socio.getTipoSocio());
            consultaPreparada.setString(4, socio.getNif());

             consultaPreparada.setString(5, socio.getFederacion().getCodigo());
             consultaPreparada.setInt(6, socio.getSeguro().getId());
            consultaPreparada.executeUpdate();
            System.out.println("Socio agregado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al agregar el socio.");
        }
    }

    public void eliminarSocio(int numeroSocio) {
        try {
            String consulta = "DELETE FROM Socios WHERE n_socio = ?";
            PreparedStatement consultaPreparada = connection.prepareStatement(consulta);
            consultaPreparada.setInt(1, numeroSocio);
            consultaPreparada.executeUpdate();
            System.out.println("Socio eliminado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el socio.");
        }
    }


    public ArrayList<SociosModelo> obtenerTodosLosSocios() {
        ArrayList<SociosModelo> listaSocios = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM Socios";
            PreparedStatement consultaPreparada = connection.prepareStatement(consulta);
            ResultSet resultado = consultaPreparada.executeQuery();
            while (resultado.next()) {
                SociosModelo socio = new SociosModelo(resultado.getString("nombre"));

                 socio.setCuotaMensual(resultado.getDouble("cuota_mensual"));
                 socio.setTipoSocio(resultado.getString("tipo_socio"));
                 socio.setNif(resultado.getString("nif"));
                // socio.setFederacion(resultado.getFederacion("federacion"));
                 //socio.setSeguro(resultado.getSeguro().getId());
                listaSocios.add(socio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaSocios;
    }

    public double calcularFacturaMensual(int numSocio) {
        try {
            String query = "SELECT importe FROM Facturas WHERE id_socio = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, numSocio);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                return resultado.getDouble("importe");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }





    // Este método puede ser utilizado para actualizar el tipo de seguro de un socio existente
    public boolean actualizarTipoSeguro(int numeroSocio, int idSeguro) {
        String consulta = "UPDATE Socios SET seguro = ? WHERE n_socio = ?";
        try (PreparedStatement stmt = connection.prepareStatement(consulta)) {
            stmt.setInt(1, idSeguro);
            stmt.setInt(2, numeroSocio);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public SociosModelo buscarSocioPorNumero(int numeroSocio) {
        try {
            String sql = "SELECT * FROM Socios WHERE n_socio = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, numeroSocio);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String nombre = result.getString("nombre");
                String tipoSocio = result.getString("tipo_socio");
                String nif = result.getString("nif");
                return new SociosModelo(numeroSocio, nombre, tipoSocio, nif);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void agregarSocioEst(String nombre, String nif, SeguroModelo seguro) {
        try {
            String consulta = "INSERT INTO Socios (nombre, nif, tipo_socio, seguro) VALUES (?, ?, ?, ?)";
            PreparedStatement consultaPreparada = connection.prepareStatement(consulta);
            consultaPreparada.setString(1, nombre);
            consultaPreparada.setString(2, nif);
            consultaPreparada.setString(3, "Estándar");
            consultaPreparada.setInt(4, seguro.getId()); // Suponiendo que getId() devuelve el ID del seguro

            // Ejecutar la consulta
            consultaPreparada.executeUpdate();

            System.out.println("Socio Estándar agregado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean agregarSocioFederado(String nombre, String nif, int opcionFederacion) {
        try {

            String query = "INSERT INTO Socios (nombre, nif, tipo_socio, federacion_codigo) VALUES (?, ?, 'Federado', ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, nif);
            statement.setInt(3, opcionFederacion);
            int filasInsertadas = statement.executeUpdate();

            return filasInsertadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarSocioInfantil(String nombre, String nif, int numSocioPadreMadre) {
        try {

            String query = "INSERT INTO Socios (nombre, nif, tipo_socio, num_socio_padre_madre) VALUES (?, ?, 'Infantil', ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, nif);
            statement.setInt(3, numSocioPadreMadre);
            int filasInsertadas = statement.executeUpdate();

            return filasInsertadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<SociosModelo> obtenerSociosEst() {
        ArrayList<SociosModelo> sociosEst = new ArrayList<>();
        try {
            String query = "SELECT * FROM Socios WHERE tipo_socio = 'Estándar'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                SociosModelo socio = new SociosModelo(resultado.getInt("n_socio"), resultado.getString("nombre"),
                        resultado.getDouble("cuota_mensual"), resultado.getString("tipo_socio"),
                        resultado.getString("nif"), resultado.getString("federacion_codigo"), resultado.getInt("seguro"));
                sociosEst.add(socio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sociosEst;
    }

    public ArrayList<SociosModelo> obtenerSociosFed() {
        ArrayList<SociosModelo> sociosFed = new ArrayList<>();
        try {
            String query = "SELECT * FROM Socios WHERE tipo_socio = 'Federado'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                SociosModelo socio = new SociosModelo(resultado.getInt("n_socio"), resultado.getString("nombre"),
                        resultado.getDouble("cuota_mensual"), resultado.getString("tipo_socio"),
                        resultado.getString("nif"), resultado.getString("federacion_codigo"), resultado.getInt("seguro"));
                sociosFed.add(socio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sociosFed;
    }

    public ArrayList<SociosModelo> obtenerSociosInf() {
        ArrayList<SociosModelo> sociosInf = new ArrayList<>();
        try {
            String query = "SELECT * FROM Socios WHERE tipo_socio = 'Infantil'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultado = statement.executeQuery();
            while (resultado.next()) {
                SociosModelo socio = new SociosModelo(resultado.getInt("n_socio"), resultado.getString("nombre"),
                        resultado.getDouble("cuota_mensual"), resultado.getString("tipo_socio"),
                        resultado.getString("nif"), resultado.getString("federacion_codigo"), resultado.getInt("seguro"));
                sociosInf.add(socio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sociosInf;
    }
    public SociosModelo obtenerSocioPorCodigo(String codigoSocio) {
        try {
            String query = "SELECT * FROM Socios WHERE n_socio = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, codigoSocio);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                return new SociosModelo(resultado.getInt("n_socio"), resultado.getString("nombre"), resultado.getDouble("cuota_mensual"), resultado.getString("tipo_socio"), resultado.getString("nif"), resultado.getString("federacion_codigo"), resultado.getInt("seguro"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
