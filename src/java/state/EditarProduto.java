package state;

import control.Servlet;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Componente;
import model.Usuario;
import singleton.Banco;

public class EditarProduto implements State {

    private Servlet servlet;
    private String url = "cadastro_produtos.jsp";
    private HttpSession session;
    private HttpServletRequest request;

    public EditarProduto(Servlet servlet) {
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

        // ----- select -----
        Componente c = (Componente) request.getAttribute("currentProduto");

        // ----- checa se o usuario ja existe no banco de dados -----
        boolean existe = false;
        if (c != null) {
            existe = true;
        }
        if (existe) {

            c.setDescricao(request.getParameter("descricao"));
            c.setNome(request.getParameter("nome"));
            c.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
            c.setStatus(Integer.parseInt(request.getParameter("status")));
            c.setValor(Double.parseDouble(request.getParameter("valor")));

            Banco.getInstantance().updateComponente(c);

            // ----- recupera a lista de produtos do banco de dados -----
            ArrayList<Componente> listaProdutos = Banco.getInstantance().getListaComponentes();
            // ----- salva na sessao -----
            session.setAttribute("produtos", listaProdutos);
        } else {
            // erro aqui
        }
        servlet.setState(servlet.getEstoque());
    }

    @Override
    public void descadastrarProduto() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void estoque() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setRequest(HttpServletRequest request) {
        this.request = request;
        this.session = request.getSession();
    }

    @Override
    public void carrinho() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public void voltar() {
        servlet.setState(servlet.getEstoque());
    }
}
