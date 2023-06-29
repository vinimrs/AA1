package br.ufscar.dc.dsw.dao;

import br.ufscar.dc.dsw.domain.*;
import br.ufscar.dc.dsw.domain.Locacao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LocacaoDAO extends GenericDAO {

    public void insert(Locacao locacao) {

        String sql = "INSERT INTO Locacao(data_locacao, horario_locacao, cpf_cliente, cnpj_locadora) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

            statement = conn.prepareStatement(sql);

            statement.setDate(1, Date.valueOf(locacao.getData()));
            statement.setTime(2, Time.valueOf(locacao.getHora()));
            statement.setString(3, locacao.getCliente().getCpf());
            statement.setString(4, locacao.getLocadora().getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Locacao> getAll() {

        List<Locacao> Locacoes = new ArrayList<>();

        String sql = "SELECT * from Locacao l";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                LocalDate dataLocacao = LocalDate.parse(resultSet.getString("data_locacao"));
                LocalTime horarioLocacao = LocalTime.parse(resultSet.getString("horario_locacao"));
                Cliente cliente = new ClienteDAO().getByCpf(resultSet.getString("cpf_cliente"));
                Locadora locadora = new LocadoraDAO().getByCnpj(resultSet.getString("cnpj_locadora"));

                Locacao locacao = new Locacao(id, horarioLocacao, dataLocacao, cliente, locadora);
                Locacoes.add(locacao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Locacoes;
    }


    public void delete(Locacao locacao) {
        String sql = "DELETE FROM Locacao where id = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, locacao.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Locacao locacao) {
        String sql = "UPDATE Locacao SET data_locacao = ?, horario_locacao = ?, cnpj_locadora = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setDate(1, Date.valueOf(locacao.getData()));
            statement.setTime(2, Time.valueOf(locacao.getHora()));
            statement.setString(3, locacao.getLocadora().getCnpj());
            statement.setLong(4, locacao.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Locacao get(Long id) {
        Locacao locacao = null;

        String sql = "SELECT * from Locacao WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                LocalDate dataLocacao = LocalDate.parse(resultSet.getString("data_locacao"));
                LocalTime horarioLocacao = LocalTime.parse(resultSet.getString("horario_locacao"));
                Cliente cliente = new ClienteDAO().getByCpf(resultSet.getString("cpf_cliente"));
                Locadora locadora = new LocadoraDAO().get(id);

                locacao = new Locacao(id, horarioLocacao, dataLocacao, cliente, locadora);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locacao;
    }

    public List<Locacao> getAllFromClient(Usuario usuario) {
        List<Locacao> Locacoes = new ArrayList<>();

        String sql = "SELECT * from Locacao WHERE cpf_cliente = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            Cliente cliente = new ClienteDAO().get(usuario.getId());
            String cpfCliente = cliente.getCpf();
            statement.setString(1, cpfCliente);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                LocalDate dataLocacao = LocalDate.parse(resultSet.getString("data_locacao"));
                LocalTime horarioLocacao = LocalTime.parse(resultSet.getString("horario_locacao"));
                Locadora locadora = new LocadoraDAO().getByCnpj(resultSet.getString("cnpj_locadora"));

                Locacao locacao = new Locacao(id, horarioLocacao, dataLocacao, cliente, locadora);
                Locacoes.add(locacao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Locacoes;
    }

    public List<Locacao> getAllFromLocadora(Usuario usuario) {
        List<Locacao> Locacoes = new ArrayList<>();

        String sql = "SELECT * from Locacao WHERE cnpj_locadora = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            Locadora locadora = new LocadoraDAO().get(usuario.getId());
            String cnpjLocadora = locadora.getCnpj();
            statement.setString(1, cnpjLocadora);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                LocalDate dataLocacao = LocalDate.parse(resultSet.getString("data_locacao"));
                LocalTime horarioLocacao = LocalTime.parse(resultSet.getString("horario_locacao"));
                Cliente cliente = new ClienteDAO().getByCpf(resultSet.getString("cpf_cliente"));

                Locacao locacao = new Locacao(id, horarioLocacao, dataLocacao, cliente, locadora);
                Locacoes.add(locacao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Locacoes;
    }
}