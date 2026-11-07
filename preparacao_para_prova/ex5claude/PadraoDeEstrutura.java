package preparacao_para_prova.ex5claude;

public interface PadraoDeEstrutura<X>{
    public void guarde(X valor) throws Exception;
    public X recupere() throws Exception;
    public void remova() throws Exception;
    public int getQuantidade();
}