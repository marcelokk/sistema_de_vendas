package control;

/*
 * consertar o componente zero e o quartro que esta na lista da home
 * colocar o iterator
 * fazer a sugestao do dia
 */
import java.io.IOException;
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
import state.Historico;
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
    private State historico;
    private String url;
    private HttpSession session;
    private String titulo = "Sistema de Vendas - Login";
    private String nome_do_site = "Sistema de Vendas";

    /// Getters do State Design Pattern
    public void setState(State state) {
        this.state = state;
    }

    public State getEstoque() {
        return estoque;
    }

    public State getHomeState() {
        return home;
    }

    public State getUsuarios() {
        return usuarios;
    }

    public State getLoginState() {
        return login;
    }

    public State getCadastroState() {
        return cadastro;
    }

    public State getCarrinhoState() {
        return carrinho;
    }

    public State getCadastrarProdutos() {
        return cadastrarProdutos;
    }

    public State getEditarDadosPessoaisState() {
        return editarDadosPessoais;
    }

    public State getEditarProduto() {
        return editarProduto;
    }

    public State getFinalizarCompra() {
        return finalizarCompra;
    }

    public State getHistorico() {
        return historico;
    }

    // Metodos do Servlet
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
                historico = new Historico(this);

                session.setAttribute("titulo", titulo);
                session.setAttribute("nome_do_site", nome_do_site);

                state = login;
                url = "login.jsp";
            } // pagina de cadastro de novo usuario
            else if (acao.equals("cadastro")) {
                state.setRequest(request);
                state.cadastro();
                url = state.url();
            } // usuario clica no botao voltar de qualquer pagina 
            else if (acao.equals("voltar")) {
                state.setRequest(request);
                state.voltar();
                url = state.url();
            } // usuario clica no botao deslogar da home
            else if (acao.equals("deslogar")) {
                state.setRequest(request);
                state.deslogar();
                url = state.url();
            } // usuario clica no botao carrinho de compras da home
            else if (acao.equals("carrinho")) {
                state.setRequest(request);
                state.carrinho();
                url = state.url();
            } // administrador clica no botao cadastrar produtos
            else if (acao.equals("cadastrar_produtos")) {
                state.setRequest(request);
                state.cadastrarProduto();
                url = state.url();
            } // administrador clica no botao de estoque
            else if (acao.equals("estoque")) {
                state.setRequest(request);
                state.estoque();
                url = state.url();
            } // usuario clica para remover um produto do carrinho de compras
            else if (acao.equals("remover")) {
                state.setRequest(request);
                state.removerDoCarrinho();
                url = state.url();
            } // administrador clica no botao de descadastrar um produto na pagina de estoque
            else if (acao.equals("descadastrar_produto")) {
                state.setRequest(request);
                state.descadastrarProduto();
                url = state.url();
            } // administrador clica no botao de alterar na pagina de estoque 
            else if (acao.equals("alterar")) {
                state.setRequest(request);
                state.cadastrarProduto();
                url = state.url();
            } // administrador clica no botao usuario na home 
            else if (acao.equals("usuarios")) {
                state.setRequest(request);
                state.descadastrarUsuario();
                url = state.url();
            } // administrador remove um usuario 
            else if (acao.equals("removeUsuario")) {
                state.setRequest(request);
                state.descadastrarUsuario();
                url = state.url();
            } // usuario clica no botao de historico de compras na home 
            else if (acao.equals("historico")) {
                state.setRequest(request);
                state.detalhes();
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

        // usuario clica no botao logar na pagina de login
        if (acao.equals("logar")) {
            state.setRequest(request);
            state.logar((String) request.getParameter("login"), (String) request.getParameter("password"));
            url = state.url();
        } // usuario clica no botao de cadastro na pagina de login 
        else if (acao.equals("cadastrar")) {
            state.setRequest(request);
            state.cadastro();
            url = state.url();
        } // usuario monta um produto e insere no carrinho 
        else if (acao.equals("comprar")) {
            state.setRequest(request);
            state.inserirNoCarrinho();
            url = state.url();
        } // administrador cadastra um produto 
        else if (acao.equals("cadastrar_produtos")) {
            state.setRequest(request);
            state.cadastrarProduto();
            url = state.url();
        } // administrador cadastra uma sugestao 
        else if (acao.equals("sugestao")) {
            state.setRequest(request);
            //state.cadastrarSugestao();
            url = state.url();
        } // usuario clica para finalizar a sua compra 
        else if (acao.equals("compra_finalizada")) {
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
