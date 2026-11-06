package ex3claude;

public class FilaDePedidosConsultavel<T> extends FilaDePedidos<T> {
    public FilaDePedidosConsultavel(int tamanho) throws Exception{
        super(tamanho);
    }
    public int getQuantidadeTotal(){
        int quantidadeTotal=0;
        for(int i=0; i<this.ultimo+1; i++){
            quantidadeTotal += this.pedidos[i].getQuantidade();
        }
        return quantidadeTotal;
    }
    public int getQuantidadeDoPrimeiro() throws Exception{
        if(this.ultimo==-1) throw new Exception("Não possui pedidos");
        return pedidos[0].getQuantidade();
    }
    public T getPedidoDeMaiorQuantidade() throws Exception{
        if(ultimo==-1) throw new Exception("Não possui nenhum pedido!");
        Pedido<T> maior= pedidos[0];
        for(int i=0; i<ultimo+1;i++){
            if(this.pedidos[i].getQuantidade()>maior.getQuantidade()){
                maior=this.pedidos[i];
            }
        }
        return maior.getValor();
    }
    public boolean contem(T valor) throws Exception {
        if(valor==null) throw new Exception("O valor não pode ser nulo!");
        if(ultimo==-1) return false;
        for(int i=0; i<ultimo+1;i++){
            if(pedidos[i].getValor().equals(valor)) return true;
        }
        return false;
    }
    public int getPosicaoDe(T valor) throws Exception{
        if(valor==null) throw new Exception("Valor não pode ser nulo!");
        int posicao=1;
        for(int i=0; i<ultimo+1; i++){
            if(this.pedidos[i].getValor().equals(valor)){
                return posicao;
            }
            posicao++;
        }
        throw new Exception("Esse valor não está contido nos pedidos!");
    }
    public FilaDePedidosConsultavel(FilaDePedidosConsultavel<T> modelo) throws Exception{
        super(modelo);
    }
    @Override
    public Object clone(){
        FilaDePedidosConsultavel<T> ret = null;
        try{
            ret = new FilaDePedidosConsultavel<T>(this);
        }catch(Exception erro){

        }
        return ret;
    }
}

