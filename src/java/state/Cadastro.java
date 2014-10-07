package state;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import control.Servlet;
import model.Usuario;
import singleton.Banco;

public class Cadastro implements State {

    private Servlet servlet;
    private String url = "cadastro.jsp";
    private HttpSession session;
    private HttpServletRequest request;

    public Cadastro(Servlet servlet) {
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
        // ----- recupera os dados do request -----
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // ----- select -----
        Usuario usuario = Banco.getInstantance().getUsuario(login);

        // ----- checa se o usuario ja existe no banco de dados -----
        boolean existe = false;
        if (usuario != null) {
            existe = true;
        }
        if (existe) {
            request.setAttribute("mensagem", "O e-mail selecionado já está em uso, por favor, tente outro");
            request.setAttribute("voltar", "inicio.jsp");
            url = "nao_autorizado.jsp";
        } else {
            Usuario u = new Usuario();
            u.setLogin(request.getParameter("login"));
            u.setPassword(request.getParameter("password"));
            u.setNome(request.getParameter("nome"));
            u.setTelefone(request.getParameter("telefone"));
            u.setCidade(request.getParameter("cidade"));
            u.setEstado(request.getParameter("estado"));

            // ----- adiciona o usuario no banco de dados -----
            Banco.getInstantance().addUsuario(u);
            servlet.setState(servlet.getLoginState());
        }
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
        servlet.setState(servlet.getLoginState());
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
