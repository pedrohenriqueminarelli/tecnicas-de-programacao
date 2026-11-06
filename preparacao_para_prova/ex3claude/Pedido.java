package preparacao_para_prova.ex3claude;
 
public class Pedido<T> implements Cloneable
{
    private T   valor;
    private int quantidade;
 
    public Pedido (T valor, int quantidade) throws Exception
    {
        this.setValor      (valor);
        this.setQuantidade (quantidade);
    }
 
    public T getValor ()
    {
        return this.valor;
    }
 
    public int getQuantidade ()
    {
        return this.quantidade;
    }
 
    public void setValor (T valor) throws Exception
    {
        if (valor == null)
            throw new Exception ("Valor ausente");
 
        this.valor = valor;
    }
 
    public void setQuantidade (int quantidade) throws Exception
    {
        if (quantidade <= 0)
            throw new Exception ("Quantidade invalida");
 
        this.quantidade = quantidade;
    }
 
    @Override
    public String toString ()
    {
        return this.quantidade + "x " + this.valor;
    }
 
    @Override
    public boolean equals (Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
 
        Pedido<T> pedido = (Pedido<T>) obj;
 
        if (this.quantidade != pedido.quantidade) return false;
        if (!this.valor.equals(pedido.valor))     return false;
 
        return true;
    }
 
    @Override
    public int hashCode ()
    {
        int ret = 1;
 
        ret = 13 * ret + Integer.valueOf(this.quantidade).hashCode();
        ret = 13 * ret + this.valor.hashCode();
 
        if (ret < 0) ret = -ret;
        return ret;
    }
 
    // construtor de cópia
    public Pedido (Pedido<T> modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");
 
        this.valor      = modelo.valor;
        this.quantidade = modelo.quantidade;
    }
 
    @Override
    public Object clone ()
    {
        Pedido<T> ret = null;
 
        try
        {
            ret = new Pedido<T> (this);
        }
        catch (Exception erro)
        {}
 
        return ret;
    }
}