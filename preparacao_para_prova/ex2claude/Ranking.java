package preparacao_para_prova.ex2claude;

public class Ranking <X extends Comparable<X>> extends ListaOrdenada<X> implements Comparable<Ranking<X>> {

    protected String titulo; 

    public Ranking(int tamanho, String titulo)throws Exception{
        super(tamanho);
        this.setTitulo(titulo);
}
    public void setTitulo(String titulo) throws Exception{
        if(titulo==null || titulo==" ") throw new Exception("O título não pode ser nulo ou vazio");
        this.titulo= titulo;
    }
    public String getTitulo() throws Exception{
        return this.titulo;
    }
    public X getMenor() throws Exception{
        if(this.qtd==0) throw new Exception("A lista está vazia, portanto não é possível pegar o menor item");
        return getElemento(0);
    }
    public X getMaior() throws Exception{
        if(this.qtd==0) throw new Exception("A lista está vazia, portanto não é possível pegar o último elemento");
        return getElemento(this.qtd-1);
    }
    }
    
