package state;

import control.Servlet;
import decorator.Base;
import decorator.Componente1;
import decorator.Produto;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Acai;
import model.Componente;
import model.Item;
import model.Sugestao;
import model.Usuario;
import prototype.Prototype;
import prototype.PrototypePattern;
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
        Produto p = montaProduto();
        if (p == null) {
            return;
        }

        ArrayList<Produto> carrinho = (ArrayList) session.getAttribute("listaCompras");
        if (carrinho == null) {
            carrinho = new ArrayList();
        }
        carrinho.add(p);
        session.setAttribute("listaCompras", carrinho);
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
        /*
         Usuario u = (Usuario) session.getAttribute("currentUser");

         ArrayList<Transacao> lista = Banco.getInstantance().getListaTransacoes(u);
         ArrayList<String> listaProdutos = new ArrayList();
         ArrayList<Double> listaValores = new ArrayList();

         for (Transacao t : lista) {
         Iterator it = t.createIterator();
         while (it.hasNext()) {
         Item item = (Item) it.next();
         listaProdutos.add(item.getDescricao());
         listaValores.add(item.getValor());
         }
         }

         request.setAttribute("produtos", listaProdutos);
         request.setAttribute("valores", listaValores);

         servlet.setState(servlet.getHistorico());
         //* */
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
        int i = Integer.parseInt(request.getParameter("index"));
        Banco.getInstantance().removeComponente(i);
        // ----- recupera a lista de produtos do banco de dados -----
        ArrayList<Componente1> listaProdutos = Banco.getInstantance().getListaComponentes();
        // ----- salva na sessao -----
        session.setAttribute("produtos", listaProdutos);
        servlet.setState(servlet.getHomeState());
    }

    @Override
    public void cadastro() {
        request.setAttribute("mensagem", "Editar Dados Pessoais");
        request.setAttribute("disabled", "1");
        request.setAttribute("botao", "Atualizar");
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
        ArrayList<Usuario> lista = Banco.getInstantance().getListaUsuarios();
        Usuario u = (Usuario) session.getAttribute("currentUser");
        lista.remove(u);
        request.setAttribute("usuarios", lista);
        servlet.setState(servlet.getUsuarios());
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
        if (lista == null || lista.isEmpty()) {
            session.setAttribute("aux", 0);
        } else {
            session.setAttribute("aux", 1);
        }
        Double total = 0.0;
        for (Produto p : lista) {
            total += p.custo();
        }
        session.setAttribute("valor", total.toString());
        servlet.setState(servlet.getCarrinhoState());
    }

    @Override
    public void cadastrarSugestao() {
        Produto p = montaProduto();

        ArrayList<Sugestao> sugestoes = (ArrayList) session.getAttribute("listaSugestoes");
        if (sugestoes == null) {
            sugestoes = new ArrayList();
        }
        Sugestao s = Banco.getInstantance().salvaSugestao((String) request.getParameter("nome"), p);
        sugestoes.add(s);
        session.setAttribute("listaSugestoes", sugestoes);
        servlet.setState(servlet.getHomeState());
    }

    private Produto montaProduto() {
        Produto p = new Base();

        ArrayList<Acai> listaAcai = Banco.getInstantance().getListaAcai();
        if (request.getParameter("acai") != null) {
            int i = Integer.parseInt(request.getParameter("acai"));
            p.setCusto(listaAcai.get(i).getValor());
            p.setDescricao(listaAcai.get(i).getDescricao());
            p.setId(listaAcai.get(i).getId());
        } else {
            servlet.setState(servlet.getHomeState());
            return null;
        }

        ArrayList<Componente> lista = Banco.getInstantance().getListaComponentes();
        for (int i = 0; i < lista.size(); i++) {
            if (request.getParameter("checkbox" + lista.get(i).getId()) != null) {
                p = new Componente1(p);
                p.setCusto(lista.get(i).getValor());
                p.setDescricao(lista.get(i).getDescricao());
                p.setId(lista.get(i).getId());
            }
        }
        return p;
    }

    @Override
    public void removerSugestao() {
        Banco.getInstantance().removeSugestao(Integer.parseInt(request.getParameter("id")));
        ArrayList<Sugestao> sugestoes = Banco.getInstantance().getListaSugestao();
        session.setAttribute("lista_sugestoes", sugestoes);
        servlet.setState(servlet.getHomeState());
    }

    @Override
    public void addSugestao() {
        ArrayList<Sugestao> sugestoes = (ArrayList) session.getAttribute("lista_sugestoes");

        for (Sugestao s : sugestoes) {
            if (s.getId() == Integer.parseInt(request.getParameter("id"))) {
                PrototypePattern p = new Prototype(s);
                PrototypePattern p2 = p.clonar();
                Produto produto = p2.getProduto();

                if (produto == null) {
                    return;
                }
                
                ArrayList<Produto> carrinho = (ArrayList) session.getAttribute("listaCompras");
                if (carrinho == null) {
                    carrinho = new ArrayList();
                }
                carrinho.add(produto);
                session.setAttribute("listaCompras", carrinho);
                break;
            }
        }
        servlet.setState(servlet.getHomeState());
    }
}
