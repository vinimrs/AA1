package br.ufscar.dc.dsw.locacoes;

import br.ufscar.dc.dsw.login.LoginPage;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocacoesTest {
  private LocacoesPage paginaDeLocacoes;
  private ListaDeLocacoesClientePage paginaListaLocacoesCliente;
  private ListaDeLocacoesLocadoraPage paginaListaLocacoesLocadora;
  private CadastroLocacaoPage paginaDeCadastro;

//  @BeforeEach
//  public void beforeEach() {
//    LoginPage paginaDeLogin = new LoginPage();
//    paginaDeLogin.preencheFormularioLogin("fulano", "pass");
//    this.paginaListaLocacoesCliente = paginaDeLogin.efetuarLoginCliente();
//    this.paginaDeCadastro = paginaDeLocacoes.carregarFormulario();
//  }

//  @AfterEach
//  public void afterEach() {
//    if (this.paginaListaLocacoesCliente != null) {
//      this.paginaListaLocacoesCliente.fechar();
//    }
//  }

  private void entrarComoCliente() {
    LoginPage paginaDeLogin = new LoginPage();
    paginaDeLogin.preencheFormularioLogin("vini@vini.com", "12345");
    this.paginaListaLocacoesCliente = paginaDeLogin.efetuarLoginCliente();
  }

  private void entrarComoLocadora() {
    LoginPage paginaDeLogin = new LoginPage();
    paginaDeLogin.preencheFormularioLogin("vini@email.com", "12345");
    this.paginaListaLocacoesLocadora = paginaDeLogin.efetuarLoginLocadora();
  }

  // R6: Listagem de todas as locacoes de um cliente
  @Test
  public void deveriaListarLocacoesDeUmCliente() {
    this.entrarComoCliente();

    this.paginaDeCadastro = paginaListaLocacoesCliente.carregarFormulario();

    String data = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    // escolher uma hora livre
    String hora = "800PM";

    this.paginaListaLocacoesCliente = paginaDeCadastro.cadastrarLocacao(data, hora);

    assertTrue(this.paginaListaLocacoesCliente.isLocacaoCadastrada(data, hora));
  }

  // R5: Locacao de uma bicicleta, deve enviar emails
  public void deveriaEnviarEmailQuandoUmaLocacoesDeUmClienteForFeita() {

  }

  // R7: Listagem de todas as locacoes de um cliente
  @Test
  public void deveriaListarLocacoesDeUmaLocadora() {
    this.entrarComoCliente();

    this.paginaDeCadastro = paginaListaLocacoesCliente.carregarFormulario();

    String data = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
//     escolher uma hora livre
    String hora = "1000PM";

    this.paginaListaLocacoesCliente = paginaDeCadastro.cadastrarLocacao(data, hora);

    assertTrue(this.paginaListaLocacoesCliente.isLocacaoCadastrada(data, hora));

    this.paginaListaLocacoesCliente.fazerLogout();

    this.entrarComoLocadora();

    assertTrue(this.paginaListaLocacoesLocadora.isLocacaoCadastrada(data, hora));
  }
}
