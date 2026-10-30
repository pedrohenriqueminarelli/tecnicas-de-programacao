package coisasDeJava.ex1claude;

import coisasDeJava.clonador.Clonador;

public class Pilha<X> implements PadraoDePilha<X>, Cloneable
{
    protected X[] elemento;
    protected int topo = -1;

    // necessário para o desempilhe saber até onde pode diminuir o vetor
    private int tamanhoInicial;

    public Pilha (int tamanho) throws Exception
    {
        if (tamanho <= 0)
            throw new Exception ("Tamanho invalido");

        this.elemento       = (X[]) new Object [tamanho];
        this.tamanhoInicial = tamanho;
    }

    private void redimensioneSe (int novoTamanho)
    {
        X[] novo = (X[]) new Object [novoTamanho];

        for (int i = 0; i <= this.topo; i++)
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
    public void empilhe (X valor) throws Exception
    {
        if (valor == null)
            throw new Exception ("Valor ausente");

        if (this.topo == this.elemento.length - 1)
            this.redimensioneSe (2 * this.elemento.length);

        this.topo++;
        this.elemento[this.topo] = this.meuCloneDe(valor);
    }

    @Override
    public X getTopo () throws Exception
    {
        if (this.topo == -1)
            throw new Exception ("Pilha vazia");

        return this.meuCloneDe(this.elemento[this.topo]);
    }

    @Override
    public void desempilhe () throws Exception
    {
        if (this.topo == -1)
            throw new Exception ("Pilha vazia");

        this.elemento[this.topo] = null;
        this.topo--;

        if (this.elemento.length / 2 >= this.tamanhoInicial &&
            this.topo + 1 <= (int)(0.25 * this.elemento.length))
            this.redimensioneSe (this.elemento.length / 2);
    }

    @Override
    public String toString ()
    {
        String ret = "[";

        for (int i = 0; i <= this.topo; i++)
        {
            ret += this.elemento[i];
            if (i < this.topo) ret += ", ";
        }

        return ret + "]";
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Pilha<X> pilha = (Pilha<X>) obj;

        if (this.topo != pilha.topo) return false;
        if (this.tamanhoInicial != pilha.tamanhoInicial) return false;

        for (int i = 0; i <= this.topo; i++)
            if (!this.elemento[i].equals(pilha.elemento[i]))
                return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret = 1;

        ret = 13 * ret + Integer.valueOf(this.topo).hashCode();
        ret = 13 * ret + Integer.valueOf(this.tamanhoInicial).hashCode();

        for (int i = 0; i <= this.topo; i++)
            ret = 13 * ret + this.elemento[i].hashCode();

        if (ret < 0) ret = -ret;
        return ret;
    }

    // construtor de cópia
    public Pilha (Pilha<X> modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");

        this.topo           = modelo.topo;
        this.tamanhoInicial = modelo.tamanhoInicial;
        this.elemento       = (X[]) new Object [modelo.elemento.length];

        for (int i = 0; i <= this.topo; i++)
            this.elemento[i] = modelo.elemento[i];
    }

    @Override
    public Object clone ()
    {
        Pilha<X> ret = null;

        try
        {
            ret = new Pilha<X> (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}