package preparacao_para_prova.ex5claude;

public class EstruturaBase<X> implements PadraoDeEstrutura <X>, Cloneable {
    protected X elemento[];
    protected int qtd =0;
    private int tamanhoInicial;
    
    public EstruturaBase(int tamanhoInicial)throws Exception{
        
    }
}
