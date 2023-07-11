package com.example.webapp.repositorios;



import com.example.webapp.modelos.Cliente;
import com.example.webapp.util.conexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorioImpl implements Repositorio<Cliente> {
    private Connection getConnection() throws SQLException {
        return conexionBaseDatos.getInstance();
    }

    @Override
    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Clientes")) {
            while(rs.next()){
                Cliente cliente = crearUsuario(rs);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }

    @Override
    public Cliente porId(Long id) {
        Cliente cliente = null;
        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT FROM Clientes WHERE idCliente = ?")) {
            ps.setLong(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = crearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cliente;
    }

    @Override
    public void guardar(Cliente cliente) {
        String sql;
        if (cliente.getId() != null && cliente.getId() > 0) {
            sql = "UPDATE Clientes SET Nombre = ?, CorreoElectronico = ?, contrasenia = ?, ApellidoMaterno = ?, ApellidoPaterno = ?, idDireccion = ? WHERE idCliente = ?";
        } else {
            sql = "INSERT INTO Clientes(nombre, CorreoElectronico, contrasenia, ApellidoMaterno, ApellidoPaterno, IdDireccion) VALUES (?,?,?,?,?,?)";
        }
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getContrasenia());
            stmt.setString(4, cliente.getPrimerApellido());
            stmt.setString(5, cliente.getSegundoApellido());
            stmt.setString(6, String.valueOf(cliente.getIdDireccion()));

            if (cliente.getId() != null && cliente.getId() > 0) {
                stmt.setLong(7, cliente.getId());
            }else{}

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Clientes WHERE idCliente = ?")){
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente crearUsuario(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setNombre(rs.getString("Nombre"));
        cliente.setEmail(rs.getString("CorreoElectronico"));
        cliente.setPrimerApellido(rs.getString("ApellidoMaterno"));
        cliente.setSegundoApellido(rs.getString("ApellidoPaterno"));
        cliente.setContrasenia(rs.getString("contrasenia"));
        cliente.setId(rs.getLong("idCliente"));
        cliente.setIdDireccion(rs.getLong("idDireccion"));
        return cliente;
    }
}
