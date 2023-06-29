package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.Cliente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends GenericDAO {

    public void insert(Cliente cliente) {

        String sql = "INSERT INTO Cliente(cpf,nome,email,senha,telefone, sexo,data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getCpf());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getSenha());
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getSexo());
            statement.setDate(7, Date.valueOf(cliente.getDataNascimento()));
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getAll() {

        List<Cliente> listaClientes = new ArrayList<>();

        String sql = "SELECT * from Cliente c";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                LocalDate dataNascimento = LocalDate.parse(resultSet.getString("data_nascimento"));

                Cliente cliente = new Cliente(id, cpf, nome, email, senha, telefone, sexo, dataNascimento);
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

    public void delete(Cliente cliente) {
        String sql = "DELETE FROM Cliente where id = ?";

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

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, email = ?, senha = ?, telefone = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, cliente.getSenha());
            statement.setString(4, cliente.getTelefone());
            statement.setLong(5, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente get(Long id) {
        Cliente cliente = null;

        String sql = "SELECT * from Cliente WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                LocalDate dataNascimento = LocalDate.parse(resultSet.getString("data_nascimento"));

                cliente = new Cliente(id, cpf, nome, email, senha, telefone, sexo, dataNascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public Cliente getByEmail(String email) {
        Cliente cliente = null;

        String sql = "SELECT * from Cliente WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                Long id = resultSet.getLong("id");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                LocalDate dataNascimento = LocalDate.parse(resultSet.getString("data_nascimento"));
//                LocalDate dataNascimento = LocalDate.of(resultSet.getDate("data_nascimento").getYear(), resultSet.);

                cliente = new Cliente(id, cpf, nome, email, senha, telefone, sexo, dataNascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }

    public Cliente getByCpf(String cpf) {
        Cliente cliente = null;

        String sql = "SELECT * from Cliente WHERE cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String nome = resultSet.getString("nome");
                Long id = resultSet.getLong("id");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                LocalDate dataNascimento = LocalDate.parse(resultSet.getString("data_nascimento"));

                cliente = new Cliente(id, cpf, nome, email, senha, telefone, sexo, dataNascimento);
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