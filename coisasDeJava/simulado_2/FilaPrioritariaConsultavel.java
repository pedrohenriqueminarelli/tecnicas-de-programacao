package coisasDeJava.simulado_2;

public class FilaPrioritariaConsultavel<X> extends FilaPrioritaria<X> {
   public FilaPrioritariaConsultavel(int tamanho ) throws Exception{
    super(tamanho);
   } 
   public int getPrioridadeDoPrimeiro()throws Exception{
    if(this.ultimo==-1 ) throw new Exception("A lista está vazia!");
    return this.elementos[0].getPrioridade();
   }
   public X getItemDeMaiorPrioridade() throws Exception{
    return this.getItem();
   }
   public int getQuantidadeDePrioridade(int prioridade) throws Exception{
    int qtd=0;
    for(int i= 0; i<this.ultimo+1; i++){
        if(prioridade==this.elementos[i].getPrioridade()) qtd++;
    }
    return qtd;
   }
   public FilaPrioritariaConsultavel(FilaPrioritariaConsultavel<X> modelo) throws Exception{
    super(modelo);
   }
   @Override 
   public Object clone(){
    FilaPrioritariaConsultavel <X> ret= null;
    try{
        ret= new FilaPrioritariaConsultavel <X> (this);
    }catch(Exception erro){

    }
    return ret;
   }
}
