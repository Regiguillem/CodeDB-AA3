package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FederacionesModeloDAO {
    private Connection connection;

    public FederacionesModeloDAO(Connection connection) {
        this.connection = connection;
    }

    public FederacionesModelo buscarFederacionPorCodigo(String codigoFederacion) {
        try {
            String query = "SELECT * FROM Federaciones WHERE codigoid_federacion = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, codigoFederacion);
            ResultSet resultado = statement.executeQuery();
            if (resultado.next()) {
                return new FederacionesModelo(resultado.getString("codigoid_federacion"), resultado.getString("nombre"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
