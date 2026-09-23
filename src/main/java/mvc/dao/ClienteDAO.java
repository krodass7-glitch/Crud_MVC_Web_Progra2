package mvc.dao;

import mvc.config.ConexionBD;
import mvc.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO implements crudDAO<Cliente> {

    @Override
    public boolean insertar(Cliente objeto) {

        String sql = "INSERT INTO clientes (nit, nombre, apellidos, email) VALUES (?, ?, ?, ?)";
        try {

            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, objeto.getNit());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getApellidos());
            ps.setString(4, objeto.getEmail());

            return ps.executeUpdate() > 0;

        }catch(SQLException e) {
            System.out.println("Error al insertar cliente: ");
            return false;
        }

    }

    @Override
    public boolean actualizar (Cliente objeto) {

        String sql = "UPDATE clientes SET nit = ?, nombre = ?, apellidos = ?, email = ? WHERE id_cliente = ?";
        try {

            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, objeto.getNit());
            ps.setString(2, objeto.getNombre());
            ps.setString(3, objeto.getApellidos());
            ps.setString(4, objeto.getEmail());
            ps.setString(5, objeto.getNit());

            return ps.executeUpdate() > 0;

        }catch(SQLException e) {
            System.out.println("Error al actualizar cliente: "+ e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean eliminar (Cliente objeto) {

        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        try {

            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, objeto.getId_cliente());

            ps.executeUpdate();
            return true;

        }catch(SQLException e) {
            System.out.println("Error al eliminar cliente: ");
            return false;
        }

    }

    @Override
    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        try {
            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearCliente(rs);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }

    @Override 
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try {
            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                clientes.add(mapearCliente(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return clientes;
    }

    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("id_cliente"),
            rs.getString("nit"),
            rs.getString("nombre"),
            rs.getString("apellidos"),
            rs.getString("email")
        );
    }

}
