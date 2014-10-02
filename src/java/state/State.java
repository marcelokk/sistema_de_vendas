package state;

import javax.servlet.http.HttpServletRequest;

public interface State {
    public void logar(String login, String senha);
    public void deslogar();
    
    public void cadastro();
    public void descadastrarUsuario();
    
    public void inserirNoCarrinho();
    public void removerDoCarrinho();
    
    public void finalizarCompra();
    public void detalhes();
    
    public void cadastrarProduto();
    public void descadastrarProduto();
    
    public void setRequest(HttpServletRequest request);
    
    public String url();
}
