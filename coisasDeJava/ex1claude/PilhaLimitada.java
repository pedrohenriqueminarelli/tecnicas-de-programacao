package coisasDeJava.ex1claude;

public class PilhaLimitada<X> extends Pilha <X> {
    public PilhaLimitada(int tamanho, int limite ) throws Exception{
        super(tamanho);
        this.setLimite(limite);
    }
    public PilhaLimitada(PilhaLimitada <X> modelo) throws Exception{
        super(modelo);
        this.limite= modelo.limite;
    }
    protected int limite;
    public void setLimite(int limite) throws Exception{
        if(limite<topo+1) throw new Exception("Limite não pode ser maior que o tamanho da lista!");
        this.limite=limite;
    }
    public int getEspacoRestante() throws Exception{
        return limite-(topo+1);
    }
    @Override 
    public void empilhe(X valor)throws Exception{
        if(this.topo+1==this.limite) throw new Exception("Tamanho limite excedido!");
        super.empilhe(valor);
    }
    @Override 
    public String toString(){
        String ret= super.toString();
        ret+=" limte: "+ this.limite;
        return ret;
    }
    @Override
    public boolean equals(Object obj){
        if(!super.equals(obj)) return false;
        PilhaLimitada <X> p= (PilhaLimitada<X>) obj;
        if(this.limite!=p.limite) return false;
        return true;
    }
    @Override
    public int hashCode(){
        int ret=super.hashCode();
        ret = 2*ret+((Integer)this.limite).hashCode(); 
        if (ret<0) ret=-ret;
        return ret;
    }
    @Override 
    public Object clone(){
        PilhaLimitada <X> retorno= null;
        try{
            retorno= new PilhaLimitada<X>(this);
        }catch(Exception erro){

        }
        return retorno;
    }
}
