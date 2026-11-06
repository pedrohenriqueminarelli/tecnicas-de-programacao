package preparacao_para_prova.ex4claude;

public class HistoricoConsultavel<T> extends Historico<T>{
    public HistoricoConsultavel(int tamanhoInicial)throws Exception{
        super(tamanhoInicial);
    }
    public double getDuracaoMedia(){
        int soma= 0;
        for(int i =0; i<this.topo+1; i++){
            soma+=this.acessos[i].getDuracao();
        }
        return soma/(topo+1);
    }
    public 
}