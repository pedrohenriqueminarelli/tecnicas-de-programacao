package coisasDeJava.ex2claude;

import coisasDeJava.clonador.Clonador;

public class ListaOrdenada<X extends Comparable<X>> implements PadraoDeListaOrdenada<X>, Cloneable
{
    protected X[] elemento;
    protected int qtd = 0;

    
    private int tamanhoInicial;

    public ListaOrdenada (int tamanho) throws Exception
    {
        if (tamanho <= 0)
            throw new Exception ("Tamanho invalido");

        this.elemento       = (X[]) new Comparable [tamanho];
        this.tamanhoInicial = tamanho;
    }

    private void redimensioneSe (int novoTamanho)
    {
        X[] novo = (X[]) new Comparable [novoTamanho];

        for (int i = 0; i < this.qtd; i++)
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
    public void inclua (X valor) throws Exception
    {
        if (valor == null)
            throw new Exception ("Valor ausente");

        if (this.qtd == this.elemento.length)
            this.redimensioneSe (2 * this.elemento.length);

        
        int posicao = 0;
        while (posicao < this.qtd && this.elemento[posicao].compareTo(valor) <= 0)
            posicao++;

        
        for (int i = this.qtd - 1; i >= posicao; i--)
            this.elemento[i + 1] = this.elemento[i];

        this.elemento[posicao] = this.meuCloneDe(valor);
        this.qtd++;
    }

    @Override
    public X getElemento (int posicao) throws Exception
    {
        if (posicao < 0 || posicao >= this.qtd)
            throw new Exception ("Posicao invalida");

        return this.meuCloneDe(this.elemento[posicao]);
    }

    @Override
    public void remova (X valor) throws Exception
    {
        if (valor == null)
            throw new Exception ("Valor ausente");

        int posicao = -1;
        for (int i = 0; i < this.qtd; i++)
            if (this.elemento[i].equals(valor))
            {
                posicao = i;
                break;
            }

        if (posicao == -1)
            throw new Exception ("Valor inexistente");

        for (int i = posicao; i < this.qtd - 1; i++)
            this.elemento[i] = this.elemento[i + 1];

        this.qtd--;
        this.elemento[this.qtd] = null;

        if (this.elemento.length / 2 >= this.tamanhoInicial &&
            this.qtd <= (int)(0.25 * this.elemento.length))
            this.redimensioneSe (this.elemento.length / 2);
    }

    @Override
    public String toString ()
    {
        String ret = "[";

        for (int i = 0; i < this.qtd; i++)
        {
            ret += this.elemento[i];
            if (i < this.qtd - 1) ret += ", ";
        }

        return ret + "]";
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        ListaOrdenada<X> lista = (ListaOrdenada<X>) obj;

        if (this.qtd != lista.qtd) return false;
        if (this.tamanhoInicial != lista.tamanhoInicial) return false;

        for (int i = 0; i < this.qtd; i++)
            if (!this.elemento[i].equals(lista.elemento[i]))
                return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret = 1;

        ret = 13 * ret + Integer.valueOf(this.qtd).hashCode();
        ret = 13 * ret + Integer.valueOf(this.tamanhoInicial).hashCode();

        for (int i = 0; i < this.qtd; i++)
            ret = 13 * ret + this.elemento[i].hashCode();

        if (ret < 0) ret = -ret;
        return ret;
    }

    
    public ListaOrdenada (ListaOrdenada<X> modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");

        this.qtd            = modelo.qtd;
        this.tamanhoInicial = modelo.tamanhoInicial;
        this.elemento       = (X[]) new Comparable [modelo.elemento.length];

        for (int i = 0; i < this.qtd; i++)
            this.elemento[i] = modelo.elemento[i];
    }

    @Override
    public Object clone ()
    {
        ListaOrdenada<X> ret = null;

        try
        {
            ret = new ListaOrdenada<X> (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}