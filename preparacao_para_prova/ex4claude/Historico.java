package preparacao_para_prova.ex4claude;

public class Historico<T> implements PadraoDeHistorico<T>, Cloneable
{
    protected Acesso<T>[] acessos;
    protected int         topo = -1;

    // necessário para o volte saber até onde pode diminuir o vetor
    private int tamanhoInicial;

    public Historico (int tamanho) throws Exception
    {
        if (tamanho <= 0)
            throw new Exception ("Tamanho invalido");

        this.acessos        = (Acesso<T>[]) new Acesso [tamanho];
        this.tamanhoInicial = tamanho;
    }

    private void redimensioneSe (int novoTamanho)
    {
        Acesso<T>[] novo = (Acesso<T>[]) new Acesso [novoTamanho];

        for (int i = 0; i <= this.topo; i++)
            novo[i] = this.acessos[i];

        this.acessos = novo;
    }

    @Override
    public void empilheAcesso (T pagina, int duracao) throws Exception
    {
        // o construtor do Acesso já valida pagina nula e duracao <= 0
        Acesso<T> novo = new Acesso<T> (pagina, duracao);

        if (this.topo == this.acessos.length - 1)
            this.redimensioneSe (2 * this.acessos.length);

        this.topo++;
        this.acessos[this.topo] = novo;
    }

    @Override
    public T getPaginaAtual () throws Exception
    {
        if (this.topo == -1)
            throw new Exception ("Historico vazio");

        return this.acessos[this.topo].getPagina();
    }

    @Override
    public void volte () throws Exception
    {
        if (this.topo == -1)
            throw new Exception ("Historico vazio");

        this.acessos[this.topo] = null;
        this.topo--;

        if (this.acessos.length / 2 >= this.tamanhoInicial &&
            this.topo + 1 <= (int)(0.25 * this.acessos.length))
            this.redimensioneSe (this.acessos.length / 2);
    }

    @Override
    public String toString ()
    {
        String ret = "[";

        for (int i = 0; i <= this.topo; i++)
        {
            ret += this.acessos[i];
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

        Historico<T> hist = (Historico<T>) obj;

        if (this.topo != hist.topo) return false;
        if (this.tamanhoInicial != hist.tamanhoInicial) return false;

        for (int i = 0; i <= this.topo; i++)
            if (!this.acessos[i].equals(hist.acessos[i]))
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
            ret = 13 * ret + this.acessos[i].hashCode();

        if (ret < 0) ret = -ret;
        return ret;
    }

    // construtor de cópia
    public Historico (Historico<T> modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");

        this.topo           = modelo.topo;
        this.tamanhoInicial = modelo.tamanhoInicial;
        this.acessos        = (Acesso<T>[]) new Acesso [modelo.acessos.length];

        // Acesso tem setters, então cada um é copiado para não ficar compartilhado
        for (int i = 0; i <= this.topo; i++)
            this.acessos[i] = new Acesso<T> (modelo.acessos[i]);
    }

    @Override
    public Object clone ()
    {
        Historico<T> ret = null;

        try
        {
            ret = new Historico<T> (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}