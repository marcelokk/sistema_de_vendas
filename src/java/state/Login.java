package state;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import control.Servlet;
import decorator.Produto;
import java.util.ArrayList;
import model.Acai;
import model.Componente;
import model.Sugestao;
import model.Usuario;
import singleton.Banco;

public class Login implements State {

    private Servlet servlet;
    private String url = "login.jsp";
    private HttpSession session;
    private HttpServletRequest request;

    public Login(Servlet servlet) {
        this.servlet = servlet;
    }

    // ****** Operacoes permitidas *****
    @Override
    public void logar(String login, String password) {
        // ----- procura no banco de dados pelo usuario -----
        Usuario u = Banco.getInstantance().getUsuario(login);

        // trocar para a pagina de erro
        url = "login.jsp";

        // ----- checa se o password esta correto -----
        if (u != null && u.getPassword().equals(password)) {
            session.setAttribute("currentUser", u);

            // ----- recupera a lista de produtos do banco de dados -----
            ArrayList<Componente> listaProdutos = Banco.getInstantance().getListaComponentes();
            ArrayList<Sugestao> listaSugestoes = Banco.getInstantance().getListaSugestao();
            ArrayList<Acai> listaAcai = Banco.getInstantance().getListaAcai();
            
            System.out.println("lista sugestao tem " + listaSugestoes.size());
            for(Sugestao s : listaSugestoes) {
                System.out.println(s.getDescricao());
            }
            
            // ----- salva na sessao -----
            session.setAttribute("produtos", listaProdutos);
            session.setAttribute("lista_acai", listaAcai);
            session.setAttribute("lista_sugestoes", listaSugestoes);
            
            ArrayList<Produto> lista = new ArrayList();
            session.setAttribute("listaCompras", lista);

            servlet.setState(servlet.getHomeState());
        }
    }

    @Override
    public void cadastro() {
        //session.setAttribute("currentUser", "");
        request.setAttribute("mensagem", "Cadastro de Novo Usuario");
        request.setAttribute("disabled", "0");
        request.setAttribute("botao", "Cadastrar");        
        Usuario u = new Usuario();
        session.setAttribute("currentUser", u);
        servlet.setState(servlet.getCadastroState());
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

    @Override
    public void voltar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void estoque() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void carrinho() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cadastrarSugestao() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removerSugestao() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addSugestao() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
