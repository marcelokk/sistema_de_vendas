package singleton;

import decorator.Produto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Set;
import model.Acai;
import model.Componente;
import model.Sugestao;
import model.Item;
import model.Usuario;
import org.hibernate.Query;
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

        // se nao tem nenhum componente, cria 4 default
        if (listaComponentes != null && listaComponentes.isEmpty()) {
            hSession = HibernateUtil.getSessionFactory().getCurrentSession();
            hSession.beginTransaction();
            hSession.save(new Componente(1, "componente1", 10, 1.0, 1, "descricao do componente 1"));
            hSession.save(new Componente(2, "componente2", 20, 1.5, 1, "descricao do componente 2"));
            hSession.save(new Componente(3, "componente3", 25, 2.0, 1, "descricao do componente 3"));
            hSession.save(new Componente(4, "componente4", 30, 1.0, 1, "descricao do componente 4"));
            hSession.getTransaction().commit();
        }
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

    public Componente getComponente(int id) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Componente c = (Componente) hSession.createQuery("from Componente as "
                + "c where c.id=:id").setParameter("id", id).uniqueResult();
        hSession.getTransaction().commit();
        return c;
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
        ArrayList<Componente> listaComponentes = (ArrayList) hSession.createQuery("from Componente").list();
        hSession.getTransaction().commit();
        return listaComponentes;
    }
    // metodos de banco de dados

    public ArrayList getListaSugestao() {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        ArrayList<Produto> listaSugestao = (ArrayList) hSession.createQuery("from Sugestao").list();
        hSession.getTransaction().commit();
        return listaSugestao;
    }

    public ArrayList getListaUsuarios() {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        ArrayList<Usuario> listaUsuarios = (ArrayList) hSession.createQuery("from Usuario").list();
        hSession.getTransaction().commit();
        return listaUsuarios;
    }

    public ArrayList getListaTransacoes(Usuario u) {
        /*
         ArrayList<Transacao> listaTransacoes = new ArrayList();

         Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
         hSession.beginTransaction();
         ArrayList<Sugestao> listaCompras = (ArrayList) hSession.createQuery("from Compra as "
         + "c where c.usuario=:id").setParameter("id", u.getId()).list();
         hSession.getTransaction().commit();

         for (Sugestao c : listaCompras) {
         Transacao t = new Transacao(c, c.getItens());
         listaTransacoes.add(t);
         }

         return listaTransacoes;
         */
        return null;
    }

    public ArrayList getListaAcai() {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        ArrayList<Acai> listaAcai = (ArrayList) hSession.createQuery("from Acai").list();
        hSession.getTransaction().commit();
        return listaAcai;
    }

    public Acai getAcai(String descricao) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Acai acai = (Acai) hSession.createQuery("select * from Acai a where a.descricao = " + descricao).uniqueResult();
        hSession.getTransaction().commit();
        return acai;
    }    
    
    public Componente getComponente(String descricao) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Componente c = (Componente) hSession.createQuery("select * from componente c where c.descricao = " + descricao).uniqueResult();
        hSession.getTransaction().commit();
        return c;
    }      
    
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

    public void addComponente(Componente c) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        hSession.save(c);
        hSession.getTransaction().commit();
    }

    public void removeComponente(int index) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Query q = hSession.createQuery("delete from Componente as x where x.id=:id").setParameter("id", index);
        q.executeUpdate();
        hSession.getTransaction().commit();
    }

    public void removeUsuario(int index) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Query q = hSession.createQuery("delete from Usuario as x where x.id=:id").setParameter("id", index);
        q.executeUpdate();
        hSession.getTransaction().commit();
    }
    
    public void removeSugestao(int index) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Query q = hSession.createQuery("delete from Sugestao as x where x.id=:id").setParameter("id", index);
        q.executeUpdate();
        hSession.getTransaction().commit();        
    }

    public Sugestao getCompra(int id) {
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Sugestao c = (Sugestao) hSession.createQuery("from Compra as"
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

    public void updateUsuario(Usuario u) {
        // ----- muda os dados do banco de dados -----                
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Usuario u2 = (Usuario) hSession.load(Usuario.class, u.getId());

        u2.setPassword(u.getPassword());
        u2.setNome(u.getNome());
        u2.setTelefone(u.getTelefone());
        u2.setCidade(u.getCidade());
        u2.setEstado(u.getEstado());

        hSession.getTransaction().commit();
    }

    public void updateComponente(Componente c) {
        // ----- muda os dados do banco de dados -----                
        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Componente c2 = (Componente) hSession.load(Componente.class, c.getId());

        c2.setDescricao(c.getDescricao());
        c2.setNome(c.getNome());
        c2.setQuantidade(c.getQuantidade());
        c2.setStatus(c.getStatus());
        c2.setValor(c.getValor());

        hSession.getTransaction().commit();
    }

    public void finalizarCompra(ArrayList<Produto> listaProdutos, Usuario u) {
        /*
         Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
         hSession.beginTransaction();

         Sugestao c = new Sugestao();
         Set set = c.getItens();

         for (Produto p : listaProdutos) {
         Item i = new Item();
         i.setDescricao(p.descricao());
         i.setValor(p.custo());
         set.add(i);
         }
         hSession.getTransaction().commit();


         hSession = HibernateUtil.getSessionFactory().getCurrentSession();
         hSession.beginTransaction();

         Set set2 = u.getCompras();
         set2.add(c);

         hSession.getTransaction().commit();
         */
    }

    public Sugestao salvaSugestao(String nome, Produto p) {
        Sugestao s = new Sugestao();

        Double valor = 0.0;
        String descricao = "";
        String componentes = p.getComponentes();
        for (int i = 1; i < componentes.length(); i++) {
            int id = componentes.charAt(i) - '0';

            Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
            hSession.beginTransaction();

            Componente c = (Componente) hSession.createQuery("from Componente as "
                    + "c where c.id=:id").setParameter("id", id).uniqueResult();
            hSession.getTransaction().commit();

            valor += c.getValor();
            if ("".equals(descricao)) {
                descricao = c.getDescricao();
            } else {
                descricao = c.getDescricao() + " + " + descricao;
            }

            /*
             Item item = new Item();
             System.out.println("Descricao " + c.getDescricao());
             System.out.println("Nome " + c.getNome());
             System.out.println("Valor " + c.getValor());
             System.out.println("ID " + s.getId());
            
             item.setDescricao(c.getDescricao());
             item.setNome(c.getNome());
             item.setValor(c.getValor());
             item.setSugestao_id(s.getId());

             hSession = HibernateUtil.getSessionFactory().getCurrentSession();
             hSession.beginTransaction();
             hSession.save(item);
             hSession.getTransaction().commit();
             */
        }

        int id = componentes.charAt(0) - '0';

        Session hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        Acai c = (Acai) hSession.createQuery("from Acai as "
                + "c where c.id=:id").setParameter("id", id).uniqueResult();
        hSession.getTransaction().commit();

        valor += c.getValor();
        descricao = c.getDescricao() + " + " + descricao;

        s.setNome(nome);
        s.setValor(valor);
        s.setDescricao(descricao);

        hSession = HibernateUtil.getSessionFactory().getCurrentSession();
        hSession.beginTransaction();
        hSession.save(s);
        hSession.getTransaction().commit();
        return s;
    }

}
