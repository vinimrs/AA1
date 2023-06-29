package br.ufscar.dc.dsw.locadoras;

import br.ufscar.dc.dsw.PageObject;
import br.ufscar.dc.dsw.domain.Locadora;
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

    public boolean isLocadoraListada(Locadora locadora) {
      List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-locadoras tr:not(:first-child)"));

      for (WebElement l: linhas) {
        String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
        String cnpjLinha = l.findElement(By.cssSelector("td:nth-child(4)")).getText();

        if(emailLinha.equals(locadora.getEmail()) && cnpjLinha.equals(locadora.getCnpj())) {
          return true;
        }
      }

      return false;
    }


  public FormularioLocadoraPage carregarFormularioDeAtualizacaoDaLocadora(Locadora locadora) {
    List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-locadoras tr:not(:first-child)"));

    for (WebElement l: linhas) {
      String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String cnpjLinha = l.findElement(By.cssSelector("td:nth-child(4)")).getText();

      if(emailLinha.equals(locadora.getEmail()) || cnpjLinha.equals(locadora.getCnpj())) {
        WebElement colunaActions = l.findElement(By.cssSelector("td:nth-child(6)"));
        WebElement acaoDeAtualizar = colunaActions.findElement(By.cssSelector("a:nth-child(1)"));
        acaoDeAtualizar.click();

        break;
      }
    }

    return new FormularioLocadoraPage(browser);
  }

  public Locadora deletarLocadoraDaLista(Locadora locadora) {
    List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-locadoras tr:not(:first-child)"));

    for (WebElement l: linhas) {
      String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String cnpjLinha = l.findElement(By.cssSelector("td:nth-child(4)")).getText();

      if(emailLinha.equals(locadora.getEmail()) || cnpjLinha.equals(locadora.getCnpj())) {
        WebElement colunaActions = l.findElement(By.cssSelector("td:nth-child(6)"));
        WebElement acaoDeDeletar = colunaActions.findElement(By.cssSelector("a:nth-child(2)"));
        acaoDeDeletar.click();

        browser.switchTo().alert().accept();

        break;
      }
    }

    return locadora;
  }

  public boolean isPaginaDeLocadoras() {
      return browser.getCurrentUrl().equals(URL_LOCADORAS);
  }


    public void removerLocadoraIgual(Locadora locadora) {
            List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-locadoras tr:not(:first-child)"));

        for (WebElement l: linhas) {
            String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String cnpjLinha = l.findElement(By.cssSelector("td:nth-child(4)")).getText();

            if(emailLinha.equals(locadora.getEmail()) || cnpjLinha.equals(locadora.getCnpj())) {
                WebElement colunaActions = l.findElement(By.cssSelector("td:nth-child(6)"));
                WebElement acaoDeDeletar = colunaActions.findElement(By.cssSelector("a:nth-child(2)"));
                acaoDeDeletar.click();

                browser.switchTo().alert().accept();

                break;
            }
        }
    }

//  public void removerLocadoraComEmailOuCpfIgual(String email, String cpf) {
//    List<WebElement> linhas = this.browser.findElements(By.cssSelector("#tabela-locadoras tr:not(:first-child)"));
//
//    for (WebElement l: linhas) {
//      String emailLinha = l.findElement(By.cssSelector("td:nth-child(2)")).getText();
//      String cnpjLinha = l.findElement(By.cssSelector("td:nth-child(4)")).getText();
//
//      if(emailLinha.equals(email) || cnpjLinha.equals(cpf)) {
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