package state;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import control.Servlet;

public class CadastroProdutos implements State {

    private Servlet servlet;
    private String url = "cadastro_produtos.jsp";
    private HttpSession session;
    private HttpServletRequest request;    
    
    public CadastroProdutos(Servlet servlet) {
        this.servlet = servlet;
    }
    
    @Override
    public void logar(String login, String senha) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deslogar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void cadastro() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void descadastrarUsuario() {
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
    public void setRequest(HttpServletRequest request) {
        this.request = request;
        this.session = request.getSession();
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public void voltar() {
        servlet.setState(servlet.getHomeState());
    }

    @Override
    public void estoque() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void carrinho() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
