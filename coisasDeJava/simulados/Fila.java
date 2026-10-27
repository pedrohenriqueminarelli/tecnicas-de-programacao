package coisasDeJava.simulados;

   import coisasDeJava.clonador.Clonador;

public class Fila<X> implements PadraoDeFila<X>, Cloneable
{
    protected X[] elemento;
    protected int ultimo = -1;

    // necessário para o removaItem saber até onde pode diminuir o vetor
    private int tamanhoInicial;

    public Fila (int tamanho) throws Exception
    {
        if (tamanho <= 0)
            throw new Exception ("Tamanho invalido");

        this.elemento       = (X[]) new Object [tamanho];
        this.tamanhoInicial = tamanho;
    }

    private void redimensioneSe (int novoTamanho)
    {
        X[] novo = (X[]) new Object [novoTamanho];

        for (int i = 0; i <= this.ultimo; i++)
            novo[i] = this.elemento[i];

        this.elemento = novo;
    }

    private X meuCloneDe (X x)
    {
        if (x instanceof Cloneable)
            return new Clonador<X>().clone(x);
        return x;
    }

    @Override
    public void guardeItem (X valor) throws Exception
    {
        if (valor == null)
            throw new Exception ("Valor ausente");

        if (this.ultimo == this.elemento.length - 1)
            this.redimensioneSe (2 * this.elemento.length);

        this.ultimo++;
        this.elemento[this.ultimo] = this.meuCloneDe(valor);
    }

    @Override
    public X getItem () throws Exception
    {
        if (this.ultimo == -1)
            throw new Exception ("Fila vazia");

        return this.meuCloneDe(this.elemento[0]);
    }

    @Override
    public void removaItem () throws Exception
    {
        if (this.ultimo == -1)
            throw new Exception ("Fila vazia");

        // FIFO: sai o primeiro, todos andam uma casa para a esquerda
        for (int i = 0; i < this.ultimo; i++)
            this.elemento[i] = this.elemento[i + 1];

        this.elemento[this.ultimo] = null;
        this.ultimo--;

        if (this.elemento.length / 2 >= this.tamanhoInicial &&
            this.ultimo + 1 <= (int)(0.25 * this.elemento.length))
            this.redimensioneSe (this.elemento.length / 2);
    }

    @Override
    public String toString ()
    {
        String ret = "[";

        for (int i = 0; i <= this.ultimo; i++)
        {
            ret += this.elemento[i];
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

        Fila<X> fila = (Fila<X>) obj;

        if (this.ultimo != fila.ultimo) return false;
        if (this.tamanhoInicial != fila.tamanhoInicial) return false;

        for (int i = 0; i <= this.ultimo; i++)
            if (!this.elemento[i].equals(fila.elemento[i]))
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
            ret = 13 * ret + this.elemento[i].hashCode();

        if (ret < 0) ret = -ret;
        return ret;
    }

    // construtor de cópia
    public Fila (Fila<X> modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");

        this.ultimo         = modelo.ultimo;
        this.tamanhoInicial = modelo.tamanhoInicial;
        this.elemento       = (X[]) new Object [modelo.elemento.length];

        for (int i = 0; i <= this.ultimo; i++)
            this.elemento[i] = modelo.elemento[i];
    }

    @Override
    public Object clone ()
    {
        Fila<X> ret = null;

        try
        {
            ret = new Fila<X> (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}