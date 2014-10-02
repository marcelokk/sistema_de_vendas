package logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.hibernate.Session;
import state.Home;
import state.Login;
import state.State;
import util.HibernateUtil;

// .sqlite my.db < arq.sql

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    private State home;
    private State login;
    private State state;
    private String url;
    private HttpSession session;
    private String titulo = "Pastoreiro - Login";
    private String nome_do_site = "Pastoreiro";

    private String nameOfLogger = Login.class.getName();
    private Logger myLogger = Logger.getLogger(nameOfLogger);
    
    public void setState(State state) {
        this.state = state;
    }
    
    public State getLoginState() {
        return login;
    }
    
    public State getHomeState() {
        return home;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession();
        String acao = (String) request.getParameter("acao");
        url = "";

        if (acao != null) {

            // pagina da home
            if (acao.equals("login")) {
                System.out.println("processRequest - login");
                home = new Home();
                login = new Login(this);
                session.setAttribute("titulo", titulo);
                session.setAttribute("nome_do_site", nome_do_site);

                state = login;                
                url = "login.jsp";
            } // pagina de cadastro de novo usuario
            else if (acao.equals("cadastro")) {
                state.setRequest(request);
                state.cadastro();
                url = state.url();
            } else if (acao.equals("deslogar")) {
                state.setRequest(request);
                state.deslogar();
                url = state.url();
            }

            // .. resto dos elseifs

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession();
        String acao = (String) request.getParameter("acao");
        url = "";

        if (acao.equals("logar")) {
            state.setRequest(request);
            state.logar((String) request.getParameter("login"), (String) request.getParameter("password"));
            url = state.url();
        }
        if(!"".equals(url)) {
            //PrintWriter out = response.getWriter();  
            //out.println("URL" + url); 
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
