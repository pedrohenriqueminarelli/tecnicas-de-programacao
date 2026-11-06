package preparacao_para_prova.ex4claude;

public class Acesso<T> implements Cloneable
{
    private T   pagina;
    private int duracao;

    public Acesso (T pagina, int duracao) throws Exception
    {
        this.setPagina  (pagina);
        this.setDuracao (duracao);
    }

    public T getPagina ()
    {
        return this.pagina;
    }

    public int getDuracao ()
    {
        return this.duracao;
    }

    public void setPagina (T pagina) throws Exception
    {
        if (pagina == null)
            throw new Exception ("Pagina ausente");

        this.pagina = pagina;
    }

    public void setDuracao (int duracao) throws Exception
    {
        if (duracao <= 0)
            throw new Exception ("Duracao invalida");

        this.duracao = duracao;
    }

    @Override
    public String toString ()
    {
        return this.pagina + " (" + this.duracao + "s)";
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Acesso<T> acesso = (Acesso<T>) obj;

        if (this.duracao != acesso.duracao)      return false;
        if (!this.pagina.equals(acesso.pagina))  return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret = 1;

        ret = 13 * ret + Integer.valueOf(this.duracao).hashCode();
        ret = 13 * ret + this.pagina.hashCode();

        if (ret < 0) ret = -ret;
        return ret;
    }

    // construtor de cópia
    public Acesso (Acesso<T> modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");

        this.pagina  = modelo.pagina;
        this.duracao = modelo.duracao;
    }

    @Override
    public Object clone ()
    {
        Acesso<T> ret = null;

        try
        {
            ret = new Acesso<T> (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}