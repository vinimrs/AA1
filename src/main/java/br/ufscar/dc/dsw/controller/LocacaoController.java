package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.EmailUtility;
import br.ufscar.dc.dsw.util.Erro;
import org.checkerframework.checker.units.qual.C;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet(urlPatterns = "/locacoes/*")
public class LocacaoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LocacaoDAO dao;

    private String host;
    private String port;
    private String user;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
        this.dao = new LocacaoDAO();
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


        if(usuario == null) {
            erros.add("Usuário não logado.");
            request.setAttribute("mensagens", erros);
            response.sendRedirect("/app/login.jsp");
            return;
        }

        String action = request.getPathInfo();
        if (action == null || action.equals("/")) {
            if(usuario.getPapel().equals("ADMIN")) {
                action = "/gerenciamento";
            } else {
                action = "/";
            }
        }

        try {
            switch (action) {
                case "/cadastro":
                    if(!usuario.getPapel().equals("CLIENTE")) {
                        encaminhaNaoAutorizado("CLIENTE", request, response, erros);
                        return;
                    }

                    apresentaFormCadastro(request, response);
                    break;
                case "/insercao":
                    if(!usuario.getPapel().equals("CLIENTE")) {
                        encaminhaNaoAutorizado("CLIENTE", request, response, erros);
                        return;
                    }
                    insere(request, response);
                    break;
                case "/remocao":
                    if(!usuario.getPapel().equals("ADMIN")) {
                        encaminhaNaoAutorizado("ADMIN", request, response, erros);
                        return;
                    }
                    remove(request, response);
                    break;
                case "/edicao":
                    if(!usuario.getPapel().equals("ADMIN")) {
                        encaminhaNaoAutorizado("ADMIN", request, response, erros);
                        return;
                    }
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    if(!usuario.getPapel().equals("ADMIN")) {
                        encaminhaNaoAutorizado("ADMIN", request, response, erros);
                        return;
                    }
                    atualize(request, response);
                    break;
                case "/gerenciamento":
                    if(!usuario.getPapel().equals("ADMIN")) {
                        encaminhaNaoAutorizado("ADMIN", request, response, erros);
                        return;
                    }
                    gerencia(request, response);
                    break;
                default:
                    if(usuario.getPapel().equals("CLIENTE")) {
                        listaLocacoesClientes(request, response);
                    }
                    if (usuario.getPapel().equals("LOCADORA")) {
                        listaLocacoesLocadora(request, response);
                    }
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void encaminhaNaoAutorizado(String papeis, HttpServletRequest request, HttpServletResponse response, Erro erros) throws ServletException, IOException {
        erros.add("Acesso não autorizado!");
        erros.add("Apenas Papel [" + papeis + "] tem acesso a essa página");
        request.setAttribute("mensagens", erros);
        RequestDispatcher rd = request.getRequestDispatcher("/noAuth.jsp");
        rd.forward(request, response);
    }


    private void listaLocacoesClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        List<Locacao> listaLocacoes = dao.getAllFromClient(usuario.getId());

        request.setAttribute("listaLocacoes", listaLocacoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locacao/listaLocacoesCliente.jsp");
        dispatcher.forward(request, response);
    }

    private void listaLocacoesLocadora(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        List<Locacao> listaLocacoes = dao.getAllFromLocadora(usuario.getId());

        request.setAttribute("listaLocacoes", listaLocacoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locacao/listaLocacoesLocadora.jsp");
        dispatcher.forward(request, response);
    }

    private void gerencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Locacao> listaLocacoes = dao.getAll();
        request.setAttribute("listaLocacoes", listaLocacoes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locacao/lista.jsp");
        dispatcher.forward(request, response);
    }


    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Locadora> listaLocadoras = new LocadoraDAO().getAll();
        request.setAttribute("listaLocadoras", listaLocadoras);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locacao/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Locacao locacao = dao.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/logado/locacao/formulario.jsp");
        request.setAttribute("locacao", locacao);
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        LocalDate dataLocacao = LocalDate.parse(request.getParameter("data"));
        LocalTime horarioLocacao = LocalTime.parse(request.getParameter("hora"));
        String cpfCliente = request.getParameter("cpf");
        String cnpjLocadora = request.getParameter("cnpj");

        String emailCliente = request.getParameter("emailCliente");

        Locadora locadora = new LocadoraDAO().getByCnpj(cnpjLocadora);
        String emailLocadora = locadora.getEmail();

        Cliente cliente = new ClienteDAO().getByCpf(cpfCliente);

        try {
            Locacao locacao = new Locacao(horarioLocacao, dataLocacao, new Cliente(cpfCliente), new Locadora(cnpjLocadora));

            dao.insert(locacao);

            String emailDoSistema = "locadoradebicicletasdsw1@gmail.com";
            String assuntoCliente = "Seu aluguel foi aprovado!";
            String conteudoCliente = "Aproveite a sua bicicleta alugada na data " + dataLocacao + " às " + horarioLocacao +
                    " na locadora " + locadora.getNome() + "!";

            String assuntoLocadora = "Um aluguel foi aprovado!";
            String conteudoLocadora = "O cliente " + cliente.getNome() + " alugou uma bicicleta na data " + dataLocacao + " às " + horarioLocacao +
                    " na sua locadora!";

            String resultMessage = "";

            try {
                EmailUtility.sendEmail(host, port, user, pass, emailCliente, assuntoCliente,
                        conteudoCliente, emailDoSistema);
                EmailUtility.sendEmail(host, port, user, pass, emailLocadora, assuntoLocadora,
                        conteudoLocadora, emailDoSistema);
                resultMessage = "A sua locacao foi cadastrada com sucesso!";
                request.setAttribute("message", resultMessage);
            } catch (Exception ex) {
                Erro erros = new Erro();
                erros.add("Ocorreu um erro: " + ex.getMessage());

                request.setAttribute("erros", erros);
            }

            response.sendRedirect("lista");
        } catch (RuntimeException e) {
            Erro erros = new Erro();
            erros.add("Formulário com dados inválidos!");
            erros.add("Não é possivel alugar mais de uma bicicleta no mesmo horário.");
            request.setAttribute("mensagens", erros);
            RequestDispatcher rd = request.getRequestDispatcher("/invalidForm.jsp");
            rd.forward(request, response);
        }

    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        LocalDate dataLocacao = LocalDate.parse(request.getParameter("dat"));
        LocalTime horarioLocacao = LocalTime.parse(request.getParameter("horario"));
        String cpfCliente = request.getParameter("cpf");
        String cnpjLocadora = request.getParameter("cnpj");
        Long id = Long.parseLong(request.getParameter("id"));

        Locacao locacao = new Locacao(id, horarioLocacao, dataLocacao, new Locadora(cnpjLocadora));

        dao.update(locacao);
        response.sendRedirect("gerenciamento");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Locacao locacao = new Locacao(id);
        dao.delete(locacao);
        response.sendRedirect("gerenciamento");
    }
}