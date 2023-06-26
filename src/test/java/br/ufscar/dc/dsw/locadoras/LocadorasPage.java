package br.ufscar.dc.dsw.locadoras;

import br.ufscar.dc.dsw.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

// page_url = http://localhost:8080/locadoras/
public class LocadorasPage extends PageObject {
    private static final String URL_LOCADORAS = BASE_URL + "locadoras/";
    public LocadorasPage(WebDriver browser) {
        super(browser);
    }

    public FormularioLocadoraPage carregarFormulario() {
        browser.findElement(By.id("create-locadora-button")).click();
        return new FormularioLocadoraPage(browser);
    }

//    public boolean isLocadoraListado(String email) {
//      List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-locadora tr:not(:first-child)"));
//
//      for (WebElement l: linhas) {
//        String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
//
//        if(emailLinha.equals(email)) {
//          return true;
//        }
//      }
//
//      return false;
//    }
//  public FormularioLocadoraPage carregarFormularioDeAtualizacaoDoLocadora(Locadora cliente) {
//    List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-clientes tr:not(:first-child)"));
//
//    for (WebElement l: linhas) {
//      String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
//      String cpfLinha = l.findElement(By.cssSelector("td:nth-child(4)")).getText();
//
//      if(emailLinha.equals(cliente.getEmail()) || cpfLinha.equals(cliente.getCpf())) {
//        WebElement colunaActions = l.findElement(By.cssSelector("td:nth-child(6)"));
//        WebElement acaoDeAtualizar = colunaActions.findElement(By.cssSelector("a:nth-child(1)"));
//        acaoDeAtualizar.click();
//
//        break;
//      }
//    }
//
//    return new FormularioLocadoraPage(browser);
//  }

//  public Locadora deletarLocadoraDaLista(Locadora cliente) {
//    List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-clientes tr:not(:first-child)"));
//
//    for (WebElement l: linhas) {
//      String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
//      String cpfLinha = l.findElement(By.cssSelector("td:nth-child(4)")).getText();
//
//      if(emailLinha.equals(cliente.getEmail()) || cpfLinha.equals(cliente.getCpf())) {
//        WebElement colunaActions = l.findElement(By.cssSelector("td:nth-child(6)"));
//        WebElement acaoDeDeletar = colunaActions.findElement(By.cssSelector("a:nth-child(2)"));
//        acaoDeDeletar.click();
//
//        browser.switchTo().alert().accept();
//
//        break;
//      }
//    }
//
//    return cliente;
//  }

  public boolean isPaginaDeLocadoras() {
      return browser.getCurrentUrl().equals(URL_LOCADORAS);
  }

//  public void removerLocadoraComEmailOuCpfIgual(String email, String cpf) {
//    List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-clientes tr:not(:first-child)"));
//
//    for (WebElement l: linhas) {
//      String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
//      String cpfLinha = l.findElement(By.cssSelector("td:nth-child(4)")).getText();
//
//      if(emailLinha.equals(email) || cpfLinha.equals(cpf)) {
//        WebElement colunaActions = l.findElement(By.cssSelector("td:nth-child(6)"));
//        WebElement acaoDeDeletar = colunaActions.findElement(By.cssSelector("a:nth-child(2)"));
//        acaoDeDeletar.click();
//
//        browser.switchTo().alert().accept();
//
//        break;
//      }
//    }
//  }

}