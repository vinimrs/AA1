package br.ufscar.dc.dsw.login;

import br.ufscar.dc.dsw.PageObject;
import br.ufscar.dc.dsw.clientes.ClientesPage;
import br.ufscar.dc.dsw.locacoes.LocacoesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

// page_url = http://localhost:8080/app/
public class LoginPage extends PageObject {

  public LoginPage() {
    super();
    this.browser.navigate().to(BASE_URL);
  }

  public boolean isPaginaDeLogin() {
    return browser.getCurrentUrl().equals(BASE_URL);
  }

  public boolean contemTexto(String texto) {
    return browser.getPageSource().contains(texto);
  }

  public void navegarParaPaginaDeLocadoras() {
    this.browser.navigate().to(BASE_URL+ "locadoras/" );
  }

  public void navegarParaPaginaDeClientes() {
    this.browser.navigate().to(BASE_URL+ "clientes/" );
  }

  public void preencheFormularioLogin(String login, String password) {
    browser.findElement(By.id("login")).sendKeys(login);
    browser.findElement(By.id("password")).sendKeys(password);
  }

  public boolean isPaginaDeLoginComDadosInvalidos() {
    return browser.getPageSource().contains("Usuário não encontrado!");
  }

  public LocacoesPage efetuarLoginCliente() {
    browser.findElement(By.id("input-submit")).click();
    return new LocacoesPage(browser);
  }

  public ClientesPage efetuarLoginAdmin() {
    browser.findElement(By.id("input-submit")).click();
    return new ClientesPage(browser);
  }
}