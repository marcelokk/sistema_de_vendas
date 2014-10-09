package control;

/*
 * consertar a alteracao de produtos
 * consertar a colocar produtos no carrinho
 * colocar o iterator
 * fazer a sugestao do dia
 */
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import state.Cadastro;
import state.CadastroProdutos;
import state.Carrinho;
import state.EditarDadosPessoais;
import state.EditarProduto;
import state.Estoque;
import state.FinalizarCompra;
import state.Home;
import state.Login;
import state.State;
import state.Usuarios;

// .sqlite my.db < arq.sql
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    // Estados
    private State home;
    private State login;
    private State state;
    private State cadastro;
    private State editarDadosPessoais;
    private State usuarios;
    private State carrinho;
    private State cadastrarProdutos;
    private State estoque;
    private State editarProduto;
    private State finalizarCompra;
    private String url;
    private HttpSession session;
    private String titulo = "Sistema de Vendas - Login";
    private String nome_do_site = "Sistema de Vendas";
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

    public State getCadastroState() {
        return cadastro;
    }

    public State getEditarDadosPessoaisState() {
        return editarDadosPessoais;
    }

    public State getCarrinhoState() {
        return carrinho;
    }

    public State getCadastrarProdutos() {
        return cadastrarProdutos;
    }

    public State getEstoque() {
        return estoque;
    }

    public State getEditarProduto() {
        return editarProduto;
    }

    public State getUsuarios() {
        return usuarios;
    }
    
    public State getFinalizarCompra() {
        return finalizarCompra;
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
                home = new Home(this);
                login = new Login(this);
                cadastro = new Cadastro(this);
                carrinho = new Carrinho(this);
                editarDadosPessoais = new EditarDadosPessoais(this);
                cadastrarProdutos = new CadastroProdutos(this);
                estoque = new Estoque(this);
                editarProduto = new EditarProduto(this);
                usuarios = new Usuarios(this);
                finalizarCompra = new FinalizarCompra(this);
                
                session.setAttribute("titulo", titulo);
                session.setAttribute("nome_do_site", nome_do_site);

                state = login;
                url = "login.jsp";
            } // pagina de cadastro de novo usuario
            else if (acao.equals("cadastro")) {
                state.setRequest(request);
                state.cadastro();
                url = state.url();
            } else if (acao.equals("voltar")) {
                state.setRequest(request);
                state.voltar();
                url = state.url();
            } else if (acao.equals("deslogar")) {
                state.setRequest(request);
                state.deslogar();
                url = state.url();
            } else if (acao.equals("carrinho")) {
                state.setRequest(request);
                state.carrinho();
                url = state.url();
            } else if (acao.equals("cadastrar_produtos")) {
                state.setRequest(request);
                state.cadastrarProduto();
                url = state.url();
            } else if (acao.equals("estoque")) {
                state.setRequest(request);
                state.estoque();
                url = state.url();
            } else if (acao.equals("remover")) {
                state.setRequest(request);
                state.removerDoCarrinho();
                url = state.url();
            } else if (acao.equals("descadastrar_produto")) {
                state.setRequest(request);
                state.descadastrarProduto();
                url = state.url();
            } else if (acao.equals("alterar")) {
                state.setRequest(request);
                state.cadastrarProduto();
                url = state.url();
            } else if (acao.equals("usuarios")) {
                state.setRequest(request);
                state.descadastrarUsuario();
                url = state.url();
            } else if (acao.equals("removeUsuario")) {
                state.setRequest(request);
                state.descadastrarUsuario();
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
        } else if (acao.equals("cadastrar")) {
            state.setRequest(request);
            state.cadastro();
            url = state.url();
        } else if (acao.equals("comprar")) {
            state.setRequest(request);
            state.inserirNoCarrinho();
            url = state.url();
        } else if (acao.equals("cadastrar_produtos")) {
            state.setRequest(request);
            state.cadastrarProduto();
            url = state.url();
        } else if (acao.equals("sugestao")) {
            state.setRequest(request);
            //state.cadastrarSugestao();
            url = state.url();
        } else if (acao.equals("compra_finalizada")) {
            state.setRequest(request);
            state.finalizarCompra();
            url = state.url();
        }
        if (!"".equals(url)) {
            System.out.println("Servlet URL " + url + " " + state.url());
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
