package state;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Home implements State {
    
    private String url;
    private HttpSession session;
    private HttpServletRequest request;    
    
    // ****** Operacoes permitidas *****
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
    public void cadastro() {
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
    
    // ****** Operacoes nao permitidas *****    
    @Override
    public void logar(String login, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    

    @Override
    public void descadastrarUsuario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
