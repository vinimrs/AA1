package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/locadoras/*")
public class LocadoraController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LocadoraDAO dao;

    @Override
    public void init() {
        dao = new LocadoraDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
        Erro erros = new Erro();

        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        if (action.equals("/")) {
            lista(request, response);
        } else if(usuario == null) {
            erros.add("Usuário não logado.");
            request.setAttribute("mensagens", erros);
            lista(request, response);
        }  else if (!usuario.getPapel().equals("ADMIN")) {
            erros.add("Acesso não autorizado!");
            erros.add("Apenas Papel [ADMIN] tem acesso a essa página");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
            rd.forward(request, response);
            return;
        }

        try {
            switch (action) {
                case "/cadastro":
                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                case "/gerenciamento":
                    gerencia(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Locadora> listaLocadoras = dao.getAll();
        String filtroCidade = request.getParameter("cidade");
        if(filtroCidade != null) {
            listaLocadoras = dao.getAllByCity(filtroCidade);
            request.setAttribute("filtroCidade", filtroCidade);
        }
        request.setAttribute("listaLocadoras", listaLocadoras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaLocadoras.jsp");
        dispatcher.forward(request, response);
    }

    private void gerencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Locadora> listaLocadoras = dao.getAll();

        request.setAttribute("listaLocadoras", listaLocadoras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadora/lista.jsp");
        dispatcher.forward(request, response);
    }


    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadora/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Locadora locadora = dao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locadora/formulario.jsp");
        request.setAttribute("locadora", locadora);
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        String cidade = request.getParameter("cidade");
//        Long id = Long.parseLong(request.getParameter("id"));
        try {
            Locadora locadora = new Locadora(cnpj, nome, email, senha, cidade);

            dao.insert(locadora);
            response.sendRedirect("gerenciamento");
        } catch (RuntimeException e) {
            Erro erros = new Erro();
            erros.add("CNPJ já cadastrado.");
            request.setAttribute("mensagens", erros);
            apresentaFormCadastro(request, response);
        }
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String cnpj = request.getParameter("cnpj");
        String nome = request.getParameter("nome");
        String senha = request.getParameter("senha");
        String email = request.getParameter("email");
        String cidade = request.getParameter("cidade");
        Long id = Long.parseLong(request.getParameter("id"));

        Locadora locadora = new Locadora(id, cnpj, nome, email, senha, cidade);

        dao.update(locadora);
        response.sendRedirect("gerenciamento");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        new LocacaoDAO().removeAllLocacoesFromLocadora(id);
        Locadora locadora = new Locadora(id);
        dao.delete(locadora);
        response.sendRedirect("gerenciamento");
    }
}