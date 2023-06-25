package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Admin;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO extends GenericDAO {

    public void insert(Admin cliente) {

        String sql = "INSERT INTO Admin(nome,login,senha) VALUES (?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getLogin());
            statement.setString(3, cliente.getSenha());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Admin> getAll() {

        List<Admin> listaClientes = new ArrayList<>();

        String sql = "SELECT * from Admin c";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");

                Admin cliente = new Admin(id, nome, login, senha);
                listaClientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public void delete(Admin cliente) {
        String sql = "DELETE FROM Admin where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Admin cliente) {
        String sql = "UPDATE Admin SET nome = ?, login = ?, senha = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getLogin());
            statement.setString(3, cliente.getSenha());
            statement.setLong(4, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Admin get(Long id) {
        Admin cliente = null;

        String sql = "SELECT * from Admin WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");

                cliente = new Admin(id, nome, login, senha);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public Admin getByLogin(String login) {
        Admin cliente = null;

        String sql = "SELECT * from Admin WHERE login = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                Long id = resultSet.getLong("id");

                cliente = new Admin(id, nome, login, senha);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

}