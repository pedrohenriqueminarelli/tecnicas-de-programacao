package coisasDeJava.simulado_2;
 
public class Item<T> implements Cloneable
{
    private T   valor;
    private int prioridade;
 
    public Item (T valor, int prioridade) throws Exception
    {
        this.setValor      (valor);
        this.setPrioridade (prioridade);
    }
 
    public T getValor ()
    {
        return this.valor;
    }
 
    public int getPrioridade ()
    {
        return this.prioridade;
    }
 
    public void setValor (T valor) throws Exception
    {
        if (valor == null)
            throw new Exception ("Valor ausente");
 
        this.valor = valor;
    }
 
    public void setPrioridade (int prioridade)
    {
        this.prioridade = prioridade;
    }
 
    @Override
    public String toString ()
    {
        return "(" + this.valor + ", prioridade " + this.prioridade + ")";
    }
 
    @Override
    public boolean equals (Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
 
        Item<T> item = (Item<T>) obj;
 
        if (this.prioridade != item.prioridade) return false;
        if (!this.valor.equals(item.valor))     return false;
 
        return true;
    }
 
    @Override
    public int hashCode ()
    {
        int ret = 1;
 
        ret = 13 * ret + Integer.valueOf(this.prioridade).hashCode();
        ret = 13 * ret + this.valor.hashCode();
 
        if (ret < 0) ret = -ret;
        return ret;
    }
 
    // construtor de cópia
    public Item (Item<T> modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");
 
        this.valor      = modelo.valor;
        this.prioridade = modelo.prioridade;
    }
 
    @Override
    public Object clone ()
    {
        Item<T> ret = null;
 
        try
        {
            ret = new Item<T> (this);
        }
        catch (Exception erro)
        {}
 
        return ret;
    }
}