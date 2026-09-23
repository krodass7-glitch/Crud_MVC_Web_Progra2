package mvc.dao;

import mvc.config.ConexionBD;
import mvc.modelo.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO implements crudDAO<Marca> {

    @Override
    public boolean insertar(Marca objeto) {

        String sql = "INSERT INTO marcas (id_marca, nombre) VALUES (?, ?)";
        try {
            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, objeto.getId_marca());
            ps.setString(2, objeto.getNombre_marca());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar marca: ");
            return false;
        }
    }

    @Override
    public boolean actualizar(Marca objeto) {

        String sql = "UPDATE marcas SET nombre = ? WHERE id_marca = ?";
        try {
            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, objeto.getNombre_marca());
            ps.setInt(2, objeto.getId_marca());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar marca: ");
            return false;
        }

    }

    @Override
    public boolean eliminar(Marca id) {

        String sql = "DELETE FROM marcas WHERE id_marca = ?";
        try {
            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id.getId_marca());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar marca: ");
            return false;
        }

    }

    @Override
    public Marca buscarPorId(int id) {

        String sql = "SELECT * FROM marcas WHERE id_marca = ?";
        try {
            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearMarca(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar marca por ID: ");
        }
        return null;

    }

    @Override
    public List<Marca> listarTodos() {
        List<Marca> marcas = new ArrayList<>();
        String sql = "SELECT * FROM marcas";
        try {
            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                marcas.add(mapearMarca(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar marcas: ");
        }
        return marcas;

    }

    private Marca mapearMarca(ResultSet rs) throws SQLException {
       
        return new Marca(
            rs.getInt("id_marca"),
            rs.getString("nombre")
        );

    }


}
