package br.ufscar.dc.dsw.locacoes;

import br.ufscar.dc.dsw.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocacoesTest {
  private LocacoesPage paginaDeLocacoes;
  private CadastroLocacaoPage paginaDeCadastro;

  @BeforeEach
  public void beforeEach() {
    LoginPage paginaDeLogin = new LoginPage();
    paginaDeLogin.preencheFormularioLogin("fulano", "pass");
    this.paginaDeLocacoes = paginaDeLogin.efetuarLoginCliente();
    this.paginaDeCadastro = paginaDeLocacoes.carregarFormulario();
  }

  @AfterEach
  public void afterEach() {
    this.paginaDeLocacoes.fechar();
  }

  @Test
  public void deveriaCadastrarLocacao() {

    String data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    String hora = "0100PM";

    this.paginaDeLocacoes = paginaDeCadastro.cadastrarLocacao(data, hora);

    assertTrue(paginaDeLocacoes.isLocacaoCadastrada(data, hora));
  }

  @Test
  public void deveriaValidarCadastroDeLocacao() {
    this.paginaDeLocacoes = paginaDeCadastro.cadastrarLocacao("", "");

    assertFalse(this.paginaDeCadastro.isPaginaAtual());
    assertTrue(this.paginaDeCadastro.isMensagensDeValidacoesVisiveis());
  }
}
