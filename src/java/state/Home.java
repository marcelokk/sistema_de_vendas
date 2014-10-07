package state;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import control.Servlet;
import model.Componente;

public class Home implements State {

    private Servlet servlet;
    private String url = "home.jsp";
    private HttpSession session;
    private HttpServletRequest request;

    public Home(Servlet servlet) {
        this.servlet = servlet;
    }

    // ****** Operacoes permitidas *****
    @Override
    public void deslogar() {
        servlet.setState(servlet.getLoginState());
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
        Componente c = new Componente();
        session.setAttribute("currentProduto", c);
        request.setAttribute("mensagem", "Cadastrar Novo Produto");
        servlet.setState(servlet.getCadastrarProdutos());
    }

    @Override
    public void descadastrarProduto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cadastro() {
        request.setAttribute("mensagem", "Editar Dados Pessoais");
        servlet.setState(servlet.getEditarDadosPessoaisState());
    }

    @Override
    public void setRequest(HttpServletRequest request) {
        this.request = request;
        this.session = request.getSession();
    }

    @Override
    public String url() {
        return url;
    }

    // ****** Operacoes nao permitidas *****    
    @Override
    public void logar(String login, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void descadastrarUsuario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void voltar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void estoque() {
        servlet.setState(servlet.getEstoque());        
    }

    @Override
    public void carrinho() {
        servlet.setState(servlet.getCarrinhoState());        
    }
}
