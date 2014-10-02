package state;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.Servlet;
import model.Componente;
import model.Usuario;
import org.hibernate.Session;
import util.HibernateUtil;

public class Login implements State {

    private Servlet servlet;
    private String url;
    private HttpSession session;
    private HttpServletRequest request;
    private String nameOfLogger = Login.class.getName();
    private Logger myLogger = Logger.getLogger(nameOfLogger);

    public Login(Servlet servlet) {
        this.servlet = servlet;
    }

    // ****** Operacoes permitidas *****
    @Override
    public void logar(String login, String password) {
        // ----- procura no banco de dados pelo usuario -----
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Usuario u = (Usuario) hSession.createQuery("from Usuario as " + "c where c.login=:login").setParameter("login", login).uniqueResult();
        hSession.getTransaction().commit();

        myLogger.info("Login: " + login + " password " + password);

        // trocar para a pagina de erro
        url = "login.jsp";
        // ----- checa se o password esta correto -----
        if (u != null && u.getPassword().equals(password)) {
            url = "home.jsp";
            session.setAttribute("currentUser", u);

            // ----- recupera a lista de produtos do banco de dados -----
            hSession = HibernateUtil.getSessionFactory().getCurrentSession();
            hSession.beginTransaction();
            ArrayList<Componente> listaProdutos = (ArrayList) hSession.createQuery("from Ingrediente").list();
            hSession.getTransaction().commit();

            // ----- salva na sessao -----
            session.setAttribute("produtos", listaProdutos);

            servlet.setState(servlet.getHomeState());
        }
        System.out.println("Login - logar url: " + url);
    }

    @Override
    public void cadastro() {
        //session.setAttribute("currentUser", "");
        request.setAttribute("mensagem", "Cadastro de Novo Usuario");
        request.setAttribute("voltar", "login.jsp");
        url = "cadastro.jsp";
    }

    // ****** Operacoes nao permitidas *****
    @Override
    public void deslogar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void inserirNoCarrinho() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removerDoCarrinho() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void finalizarCompra() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void detalhes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cadastrarProduto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void descadastrarProduto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void descadastrarUsuario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRequest(HttpServletRequest request) {
        this.request = request;
        this.session = request.getSession();
    }

    @Override
    public String url() {
        System.out.println("URL " + url);
        return url;
    }
}
