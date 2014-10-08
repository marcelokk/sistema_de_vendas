package state;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import control.Servlet;
import decorator.Base;
import decorator.Componente1;
import decorator.Componente2;
import decorator.Componente3;
import decorator.Produto;
import java.util.ArrayList;
import model.Componente;
import singleton.Banco;

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
        Produto p = new Base();
        ArrayList<Componente> lista = Banco.getInstantance().getListaComponentes();
        for (int i = 0; i < lista.size(); i++) {
            if (request.getParameter("checkbox" + i) != null) {

                if (i == 1) {
                    p = new Componente1(p);
                } else if (i == 2) {
                    p = new Componente2(p);
                } else if (i == 3) {
                    p = new Componente3(p);
                }
            }
            
            ArrayList<Produto> carrinho = (ArrayList) session.getAttribute("listaCompras");
            if(carrinho == null) {
                carrinho = new ArrayList();
            }
            carrinho.add(p);
            session.setAttribute("listaCompras", carrinho);
        }
        servlet.setState(servlet.getHomeState());
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
        ArrayList<Produto> lista = (ArrayList) session.getAttribute("listaCompras");
        if(lista == null || lista.isEmpty()) {
            session.setAttribute("aux", 0);
        } else {
            session.setAttribute("aux", 1);
        }
        Double total = 0.0;
        //for(Produto p : lista) {
        //    total += p.custo();
       // }
        session.setAttribute("valor", total.toString());        
        servlet.setState(servlet.getCarrinhoState());
    }
}
