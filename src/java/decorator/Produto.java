package decorator;

public abstract class Produto {    
    protected Double custo;
    protected String descricao;
    protected int id;
    
    public abstract void setCusto(Double valor);
    public abstract Double custo();
    public abstract String descricao();
    public abstract String getComponentes();
    public abstract void setDescricao(String descricao);
    public abstract int getId();
    public abstract void setId(int id);
}
