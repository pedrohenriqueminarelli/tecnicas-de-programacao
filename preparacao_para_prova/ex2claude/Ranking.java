package preparacao_para_prova.ex2claude;

public class Ranking <X extends Comparable<X>> extends ListaOrdenada<X> implements Comparable<Ranking<X>> {

    protected String titulo; 

    public Ranking(int tamanho, String titulo)throws Exception{
        super(tamanho);
        this.setTitulo(titulo);
}
    public void setTitulo(String titulo) throws Exception{
        if(titulo==null || titulo.trim().isEmpty()) throw new Exception("O título não pode ser nulo ou vazio");
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
    public int getQuantidadeMaioresQue(X valor) throws Exception{
        if(valor ==null) throw new Exception("O valor recebido não pode ser nulo!");
        int qtdmaiores=0;
        for(int i =0 ; i<this.qtd; i++){
            if(this.elemento[i].compareTo(valor)>0){
                qtdmaiores++;
            }
        }
        return qtdmaiores;
    }
    @Override 
    public int compareTo(Ranking<X> valor){
        if(valor==null) throw new NullPointerException("O valor recebido não pode ser nulo!");
        if(valor.qtd<this.qtd) return 666;
        if(valor.qtd>this.qtd) return -666;
        return 0;
    }
    @Override
    public String toString(){
        String ret = super.toString();
        ret+=" Título: "+ titulo;
        return ret; 
    }
    @Override
    public boolean equals(Object obj){
        if(!super.equals(obj)) return false;
        Ranking<X> T = (Ranking<X>) obj;
        if(!titulo.equals(T.titulo)) return false;
        return true;
    }
    @Override
    public int hashCode(){
        int ret = super.hashCode();
        ret = ret*2 + titulo.hashCode();
        if(ret<0) ret=-ret;
        return ret;
    }
    public Ranking(Ranking <X> modelo) throws Exception{ 
        super(modelo);
        this.titulo= modelo.titulo;
    }
    @Override
    public Object clone(){
        Ranking<X> ret= null;
        try{
            ret = new Ranking<X>(this);
        }catch(Exception erro){

        }
        return ret;
    }
    }
    
