package coisasDeJava.simulado_2;
 
import coisasDeJava.clonador.Clonador;
 
public class FilaPrioritaria<T> implements PadraoDeFilaPrioritaria<T>, Cloneable
{
    protected Item<T>[] elementos;
    protected int       ultimo = -1;
 
    // necessário para o removaItem saber até onde pode diminuir o vetor
    private int tamanhoInicial;
 
    public FilaPrioritaria (int tamanho) throws Exception
    {
        if (tamanho <= 0)
            throw new Exception ("Tamanho invalido");
 
        this.elementos      = (Item<T>[]) new Item [tamanho];
        this.tamanhoInicial = tamanho;
    }
 
    private void redimensioneSe (int novoTamanho)
    {
        Item<T>[] novo = (Item<T>[]) new Item [novoTamanho];
 
        for (int i = 0; i <= this.ultimo; i++)
            novo[i] = this.elementos[i];
 
        this.elementos = novo;
    }
 
    private T meuCloneDe (T x)
    {
        if (x instanceof Cloneable)
            return new Clonador<T>().clone(x);
        return x;
    }
 
    // devolve a posição do item de maior prioridade (menor número);
    // em caso de empate, o mais antigo (o que está mais à esquerda)
    private int posicaoDoPrioritario ()
    {
        int pos = 0;
 
        for (int i = 1; i <= this.ultimo; i++)
            if (this.elementos[i].getPrioridade() < this.elementos[pos].getPrioridade())
                pos = i;
 
        return pos;
    }
 
    @Override
    public void guardeItem (T valor, int prioridade) throws Exception
    {
        if (valor == null)
            throw new Exception ("Valor ausente");
 
        if (this.ultimo == this.elementos.length - 1)
            this.redimensioneSe (2 * this.elementos.length);
 
        this.ultimo++;
        this.elementos[this.ultimo] = new Item<T> (this.meuCloneDe(valor), prioridade);
    }
 
    @Override
    public T getItem () throws Exception
    {
        if (this.ultimo == -1)
            throw new Exception ("Fila vazia");
 
        return this.meuCloneDe (this.elementos[this.posicaoDoPrioritario()].getValor());
    }
 
    @Override
    public void removaItem () throws Exception
    {
        if (this.ultimo == -1)
            throw new Exception ("Fila vazia");
 
        int pos = this.posicaoDoPrioritario();
 
        // os que estão depois andam uma casa para a esquerda (mantém a ordem de chegada)
        for (int i = pos; i < this.ultimo; i++)
            this.elementos[i] = this.elementos[i + 1];
 
        this.elementos[this.ultimo] = null;
        this.ultimo--;
 
        if (this.elementos.length / 2 >= this.tamanhoInicial &&
            this.ultimo + 1 <= (int)(0.25 * this.elementos.length))
            this.redimensioneSe (this.elementos.length / 2);
    }
 
    @Override
    public String toString ()
    {
        String ret = "[";
 
        for (int i = 0; i <= this.ultimo; i++)
        {
            ret += this.elementos[i];
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
 
        FilaPrioritaria<T> fila = (FilaPrioritaria<T>) obj;
 
        if (this.ultimo != fila.ultimo) return false;
        if (this.tamanhoInicial != fila.tamanhoInicial) return false;
 
        for (int i = 0; i <= this.ultimo; i++)
            if (!this.elementos[i].equals(fila.elementos[i]))
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
            ret = 13 * ret + this.elementos[i].hashCode();
 
        if (ret < 0) ret = -ret;
        return ret;
    }
 
    // construtor de cópia
    public FilaPrioritaria (FilaPrioritaria<T> modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");
 
        this.ultimo         = modelo.ultimo;
        this.tamanhoInicial = modelo.tamanhoInicial;
        this.elementos      = (Item<T>[]) new Item [modelo.elementos.length];
 
        // Item tem setters, então cada um é copiado para não ficar compartilhado
        for (int i = 0; i <= this.ultimo; i++)
            this.elementos[i] = new Item<T> (modelo.elementos[i]);
    }
 
    @Override
    public Object clone ()
    {
        FilaPrioritaria<T> ret = null;
 
        try
        {
            ret = new FilaPrioritaria<T> (this);
        }
        catch (Exception erro)
        {}
 
        return ret;
    }
}