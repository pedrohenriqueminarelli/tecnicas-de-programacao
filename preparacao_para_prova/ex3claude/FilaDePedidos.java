package preparacao_para_prova.ex3claude;
 
public class FilaDePedidos<T> implements PadraoDeFilaDePedidos<T>, Cloneable
{
    protected Pedido<T>[] pedidos;
    protected int         ultimo = -1;
 
    // necessário para o removaPedido saber até onde pode diminuir o vetor
    private int tamanhoInicial;
 
    public FilaDePedidos (int tamanho) throws Exception
    {
        if (tamanho <= 0)
            throw new Exception ("Tamanho invalido");
 
        this.pedidos        = (Pedido<T>[]) new Pedido [tamanho];
        this.tamanhoInicial = tamanho;
    }
 
    private void redimensioneSe (int novoTamanho)
    {
        Pedido<T>[] novo = (Pedido<T>[]) new Pedido [novoTamanho];
 
        for (int i = 0; i <= this.ultimo; i++)
            novo[i] = this.pedidos[i];
 
        this.pedidos = novo;
    }
 
    @Override
    public void guardePedido (T valor, int quantidade) throws Exception
    {
        // o construtor do Pedido já valida valor nulo e quantidade <= 0
        Pedido<T> novo = new Pedido<T> (valor, quantidade);
 
        if (this.ultimo == this.pedidos.length - 1)
            this.redimensioneSe (2 * this.pedidos.length);
 
        this.ultimo++;
        this.pedidos[this.ultimo] = novo;
    }
 
    @Override
    public T getPedido () throws Exception
    {
        if (this.ultimo == -1)
            throw new Exception ("Fila vazia");
 
        return this.pedidos[0].getValor();
    }
 
    @Override
    public void removaPedido () throws Exception
    {
        if (this.ultimo == -1)
            throw new Exception ("Fila vazia");
 
        for (int i = 0; i < this.ultimo; i++)
            this.pedidos[i] = this.pedidos[i + 1];
 
        this.pedidos[this.ultimo] = null;
        this.ultimo--;
 
        if (this.pedidos.length / 2 >= this.tamanhoInicial &&
            this.ultimo + 1 <= (int)(0.25 * this.pedidos.length))
            this.redimensioneSe (this.pedidos.length / 2);
    }
 
    @Override
    public String toString ()
    {
        String ret = "[";
 
        for (int i = 0; i <= this.ultimo; i++)
        {
            ret += this.pedidos[i];
            if (i < this.ultimo) ret += ", ";
        }
 
        return ret + "]";
    }
 
    @Override
    public boolean equals (Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
 
        FilaDePedidos<T> fila = (FilaDePedidos<T>) obj;
 
        if (this.ultimo != fila.ultimo) return false;
        if (this.tamanhoInicial != fila.tamanhoInicial) return false;
 
        for (int i = 0; i <= this.ultimo; i++)
            if (!this.pedidos[i].equals(fila.pedidos[i]))
                return false;
 
        return true;
    }
 
    @Override
    public int hashCode ()
    {
        int ret = 1;
 
        ret = 13 * ret + Integer.valueOf(this.ultimo).hashCode();
        ret = 13 * ret + Integer.valueOf(this.tamanhoInicial).hashCode();
 
        for (int i = 0; i <= this.ultimo; i++)
            ret = 13 * ret + this.pedidos[i].hashCode();
 
        if (ret < 0) ret = -ret;
        return ret;
    }
 
    // construtor de cópia
    public FilaDePedidos (FilaDePedidos<T> modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");
 
        this.ultimo         = modelo.ultimo;
        this.tamanhoInicial = modelo.tamanhoInicial;
        this.pedidos        = (Pedido<T>[]) new Pedido [modelo.pedidos.length];
 
        // Pedido tem setters, então cada um é copiado para não ficar compartilhado
        for (int i = 0; i <= this.ultimo; i++)
            this.pedidos[i] = new Pedido<T> (modelo.pedidos[i]);
    }
 
    @Override
    public Object clone ()
    {
        FilaDePedidos<T> ret = null;
 
        try
        {
            ret = new FilaDePedidos<T> (this);
        }
        catch (Exception erro)
        {}
 
        return ret;
    }
}