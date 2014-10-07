package singleton;

import java.util.ArrayList;
import model.Componente;
import model.Compra;
import model.Usuario;
import org.hibernate.Session;
import util.HibernateUtil;

public class Banco {

    private static Banco estoque;

    private Banco() {
        // cria o banco de dados
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();

        hSession.beginTransaction();
        ArrayList<Usuario> listaUsuarios = (ArrayList) hSession.createQuery("from Usuario").list();
        hSession.getTransaction().commit();

        // se nao tem nenhum usuario no banco, cria pelo menos o administrador e um usuario
        if (listaUsuarios != null && listaUsuarios.isEmpty()) {
            hSession = HibernateUtil.getSessionFactory().getCurrentSession();
            hSession.beginTransaction();
            hSession.save(new Usuario("admin@a.com", "Admin1!", 1, "ADMINISTRADOR", "Sao Carlos", "SP", "(00) 1234-1234"));
            hSession.save(new Usuario("user1@a.com", "User1!", 0, "USUARIO1", "Sao Carlos", "SP", "(11) 4321-4321"));
            hSession.getTransaction().commit();
        }

        // ----- recupera a lista de produtos do banco de dados -----
        hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        ArrayList<Componente> listaComponentes = (ArrayList) hSession.createQuery("from Componente").list();
        hSession.getTransaction().commit();

        if (listaComponentes != null && listaComponentes.isEmpty()) {
            hSession = HibernateUtil.getSessionFactory().getCurrentSession();
            hSession.beginTransaction();
            hSession.save(new Componente(1, "componente1", 10, 1.0, 1, "descricao do componente 1"));
            hSession.save(new Componente(2, "componente2", 20, 1.5, 1, "descricao do componente 2"));
            hSession.save(new Componente(3, "componente3", 25, 2.0, 1, "descricao do componente 3"));
            hSession.save(new Componente(4, "componente4", 30, 1.0, 1, "descricao do componente 4"));
            hSession.getTransaction().commit();
        }     
        
        /*
         hSession = HibernateUtil.getSessionFactory().getCurrentSession();
         hSession.beginTransaction();
         Query q = hSession.createQuery("delete from Compra");
         q.executeUpdate();
         hSession.getTransaction().commit();        
         */
    }

    public static Banco getInstantance() {
        if (estoque == null) {
            estoque = new Banco();
        }
        return estoque;
    }

    public int getQuantidadeComponente(int id_do_componente) {
        // ----- select -----
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Componente c = (Componente) hSession.createQuery("from Componente as c where c.id=:id").setParameter("id", id_do_componente).uniqueResult();
        hSession.getTransaction().commit();
        return c.getQuantidade();
    }

    public double getValorComponente(int id_do_componente) {
        // ----- select -----
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Componente c = (Componente) hSession.createQuery("from Componente as c where c.id=:id").setParameter("id", id_do_componente).uniqueResult();
        hSession.getTransaction().commit();
        return c.getValor();
    }

    public void setQuantidadeComponente(int id_do_componente, int quantidade) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Componente c = (Componente) hSession.load(Componente.class, id_do_componente);
        c.setQuantidade(quantidade);
        hSession.getTransaction().commit();
    }

    public void setValorComponente(int id_do_componente, double valor) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Componente c = (Componente) hSession.load(Componente.class, id_do_componente);
        c.setValor(valor);
        hSession.getTransaction().commit();
    }

    public ArrayList getListaComponentes() {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        ArrayList<Usuario> listaComponentes = (ArrayList) hSession.createQuery("from Componente").list();
        hSession.getTransaction().commit();
        return listaComponentes;
    }
    // metodos de banco de dados

    public Usuario getUsuario(String login) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Usuario u = (Usuario) hSession.createQuery("from Usuario as "
                + "c where c.login=:login").setParameter("login", login).uniqueResult();
        hSession.getTransaction().commit();
        return u;
    }

    public void addUsuario(Usuario u) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();        
        hSession.beginTransaction();
        hSession.save(u);
        hSession.getTransaction().commit();
    }
    
    public Compra getCompra(int id) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Compra c = (Compra) hSession.createQuery("from Compra as" 
                + "c where c.id=:id").setParameter("id", id).uniqueResult();
        hSession.getTransaction().commit();
        return c;
    }
    
    public Usuario getUsuario(int id) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Usuario u = (Usuario) hSession.createQuery("from Usuario as "
                + "c where c.id=:id").setParameter("id", id).uniqueResult();
        hSession.getTransaction().commit();
        return u;
    }    
}
