package state;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import control.Servlet;
import model.Usuario;
import org.hibernate.Session;
import singleton.Banco;
import util.HibernateUtil;

public class EditarDadosPessoais implements State {

    private Servlet servlet;
    private String url = "cadastro.jsp";
    private HttpSession session;
    private HttpServletRequest request;

    public EditarDadosPessoais(Servlet servlet) {
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
        
        System.out.println("##### EDITANDO DADOS ##### " + login);
        
        // ----- checa se o usuario ja existe no banco de dados -----
        boolean existe = false;
        if (usuario != null) {
            existe = true;
        }
        if (existe) {
            servlet.setState(servlet.getHomeState());
            System.out.println("##### EDITANDO DADOS #####");
            
            usuario.setLogin(request.getParameter("login"));
            usuario.setPassword(request.getParameter("password"));
            usuario.setNome(request.getParameter("nome"));
            usuario.setTelefone(request.getParameter("telefone"));
            usuario.setCidade(request.getParameter("cidade"));
            usuario.setEstado(request.getParameter("estado"));
            Banco.getInstantance().updateUsuario(usuario);
            session.setAttribute("currentUser", usuario);
        } else {
            // erro aqui
            servlet.setState(servlet.getHomeState());
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
