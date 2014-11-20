package state;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import control.Servlet;
import decorator.Produto;
import java.util.ArrayList;
import java.util.Set;
import model.Sugestao;
import model.Item;
import model.Usuario;
import singleton.Banco;

public class Carrinho implements State {

    private Servlet servlet;
    private String url = "carrinho_de_compras.jsp";
    private HttpSession session;
    private HttpServletRequest request;

    public Carrinho(Servlet servlet) {
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
        int i = Integer.parseInt(request.getParameter("index"));
        
        ArrayList<Produto> lista = (ArrayList) session.getAttribute("listaCompras");
        
        lista.remove(i);
        
        session.setAttribute("listaCompras", lista);  
        
        if(lista == null || lista.isEmpty()) {
            session.setAttribute("aux", 0);
        } else {
            session.setAttribute("aux", 1);
        }
        Double total = 0.0;
        for(Produto p : lista) {
            total += p.custo();
        }
        session.setAttribute("valor", total.toString());               
        servlet.setState(servlet.getCarrinhoState());
    }

    @Override
    public void finalizarCompra() {
        ArrayList<Produto> lista = (ArrayList) session.getAttribute("listaCompras");
        
        Usuario u = (Usuario) session.getAttribute("currentUser");
        Banco.getInstantance().finalizarCompra(lista, u);
        
        lista = new ArrayList();
        session.setAttribute("listaCompras", lista);
        servlet.setState(servlet.getFinalizarCompra());
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
