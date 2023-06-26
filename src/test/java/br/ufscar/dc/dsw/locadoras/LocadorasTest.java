package br.ufscar.dc.dsw.locadoras;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocadorasTest {
    LocadorasPage paginaDeLocadoras;
    FormularioLocadoraPage paginaDeFormularioLocadora;
//    Locadora locadora;

    public LocadorasTest() {
        // cnpj, nome, email, senha, cidade
//        this.locadora = new Locadora(cpf, nome, email, senha, telefone, sexo, dataNascimento);
    }

    @BeforeEach
    public void beforeEach() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioLogin("admin", "admin");
//        this.paginaDeLocadoras = paginaDeLogin.efetuarLoginLocadora();
//        this.paginaDeLocadoras.removerClienteComEmailOuCpfIgual(locadora.getEmail(), locadora.getCpf());

    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLocadoras.fechar();
    }

//    @Test
//    public void deveriaAdicionarUmaLocadoraNoSistema() {
//
//        this.paginaDeFormularioLocadora = paginaDeLocadoras.carregarFormulario();
//        this.paginaDeLocadoras = paginaDeFormularioLocadora.cadastrarCliente(locadora);
//
//        assertTrue(this.paginaDeLocadoras.isClienteListado(locadora.getEmail()));
//    }
//
//    @Test
//    public void deveriaAtualizarUmaLocadoraNoSistema() {
//
//        this.paginaDeFormularioLocadora = paginaDeLocadoras.carregarFormulario();
//        this.paginaDeLocadoras = paginaDeFormularioLocadora.cadastrarCliente(locadora);
//
//        assertTrue(this.paginaDeLocadoras.isClienteListado(locadora.getEmail()));
//
//        this.paginaDeFormularioLocadora = paginaDeLocadoras.carregarFormularioDeAtualizacaoDoCliente(locadora);
//
//        String novoEmail = locadora.getEmail() + ".br";
//        String novoTelefone = "82988888888";
//        String novaSenha = "12345";
//
//        this.paginaDeLocadoras = paginaDeFormularioLocadora.atualizarCliente(novoEmail, novoTelefone, novaSenha);
//
//        assertTrue(this.paginaDeLocadoras.isClienteListado(novoEmail));
//    }
//
//    @Test
//    public void deveriaRemoverUmaLocadoraNoSistema() {
//        this.paginaDeFormularioLocadora = paginaDeLocadoras.carregarFormulario();
//        String cpf = "111.111.000-00";
//        String nome = "Joaquin das Neves";
//        String email = "joaquin11111@email.com";
//        String senha = "12345678";
//        String telefone = "(82)99183-9092";
//        String sexo = "Masculino";
//        String dataNascimento = "18/02/2004";
//        this.paginaDeLocadoras.removerClienteComEmailOuCpfIgual(email, cpf);
//
//
//        this.paginaDeLocadoras = paginaDeFormularioLocadora.cadastrarCliente(locadora);
//
//        this.paginaDeLocadoras.deletarClienteDaLista(locadora);
//
//        assertFalse(this.paginaDeLocadoras.isClienteListado(locadora.getEmail()));
//    }
}