package dao;

import java.sql.Connection;
import modelo.Producto;
import config.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ProductosDAO implements crudDAO<Producto> {

    @Override
    public boolean insertar(Producto objeto) {

        String sql = "INSERT INTO productos (id_productos, id_marca, nombre, precio, stock) VALUES (?, ?, ?, ?)";
        try {

            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, objeto.getId_producto());
            ps.setInt(2, objeto.getId_marca());
            ps.setString(3, objeto.getNombre());
            ps.setDouble(4, objeto.getPrecio());
            ps.setInt(5, objeto.getStock());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Producto objeto) {

        String sql = "UPDATE productos SET id_productos = ?, id_marca = ?, nombre = ?, precio = ?, stock = ? WHERE id_productos = ?";
        try {

            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, objeto.getId_producto());
            ps.setInt(2, objeto.getId_marca());
            ps.setString(3, objeto.getNombre());
            ps.setDouble(4, objeto.getPrecio());
            ps.setInt(5, objeto.getStock());
            ps.setInt(6, objeto.getId_producto());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println("Error al actualizar producto: " + e.getMessage());
            return false;
        }

    }

    @Override
    public boolean eliminar(Producto id) {

        String sql = "DELETE FROM productos WHERE id_productos = ?";
        try {

            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id.getId_producto());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {

            System.out.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Producto buscarPorId(int id) {

        String sql = "SELECT * FROM productos WHERE id_productos = ?";
        try {

            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                return mapearProducto(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Producto> listarTodos() {

        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try {

            Connection conn = ConexionBD.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                productos.add(mapearProducto(rs));
            
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }

        return productos;

    }

    private Producto mapearProducto(ResultSet rs) throws SQLException {

        return new Producto(
            rs.getInt("id_productos"),
            rs.getInt("id_marca"),
            rs.getString("nombre"),
            (float)rs.getDouble("precio"),
            rs.getInt("stock")
        );
    }

}
