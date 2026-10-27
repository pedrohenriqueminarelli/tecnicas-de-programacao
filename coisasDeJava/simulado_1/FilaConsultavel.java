package coisasDeJava.simulado_1;

public class FilaConsultavel <X> extends Fila<X>{

    public FilaConsultavel(int tamanho) throws Exception{
        super(tamanho);
    }
    public int getQuantidade(){
        return this.ultimo+1;
    }
    public boolean isVazia(){
        if(this.ultimo==-1) return true;
        return false;
    }
    public boolean isCheia(){
        if(getQuantidade()==elemento.length) return true;
        return false;
    }
    public FilaConsultavel(FilaConsultavel <X> modelo) throws Exception{
        super(modelo);
    }
    @Override 
    public Object clone(){
        FilaConsultavel<X> ret = null;
        try{
            ret = new FilaConsultavel<X>(this);
        }catch(Exception erro){

        }
        return ret;
    }
}