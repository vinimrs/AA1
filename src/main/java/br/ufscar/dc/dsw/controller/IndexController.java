package br.ufscar.dc.dsw.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.AdminDAO;
import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.dao.LocacaoDAO;
import br.ufscar.dc.dsw.dao.LocadoraDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.util.Erro;

@WebServlet(name = "Index", urlPatterns = { "/index.jsp", "/logout.jsp" })
public class IndexController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Erro erros = new Erro();
        if (request.getParameter("bOK") != null) {
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            if (login == null || login.isEmpty()) {
                erros.add("Login não informado!");
            }
            if (senha == null || senha.isEmpty()) {
                erros.add("Senha não informada!");
            }
            if (!erros.isExisteErros()) {
                ClienteDAO clienteDAO = new ClienteDAO();
                Usuario usuario = clienteDAO.getByEmail(login);

                if(usuario == null) {
                    AdminDAO adminDAO = new AdminDAO();
                    usuario = adminDAO.getByLogin(login);
                }

                if(usuario == null) {
                    LocadoraDAO locadoraDAO = new LocadoraDAO();
                    usuario = locadoraDAO.getByEmail(login);
                }

                if (usuario != null) {
                    if (usuario.getSenha().equalsIgnoreCase(senha)) {
                        request.getSession().setAttribute("usuarioLogado", usuario);
                        String contextPath = request.getContextPath().replace("/", "");
                        request.getSession().setAttribute("contextPath", contextPath);
                        System.out.println(usuario.getPapel());
                        if (usuario.getPapel().equals("ADMIN")) {
                            response.sendRedirect("clientes/");
                        } else if (usuario.getPapel().equals("CLIENTE")){
                            response.sendRedirect("locacoes/");
                        } else if (usuario.getPapel().equals("LOCADORA")){
                            response.sendRedirect("locacoes/");
                        }
                        return;
                    } else {
                        erros.add("Senha inválida!");
                    }
                } else {
                    erros.add("Usuário não encontrado!");
                }
            }
        }
        request.getSession().invalidate();

        request.setAttribute("mensagens", erros);

        String URL = "/login.jsp";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}