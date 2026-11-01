package preparacao_para_prova.ex2claude;

public interface PadraoDeListaOrdenada <P> {
    public void inclua(P valor) throws Exception;
    public P getElemento(int posicao) throws Exception;
    public void remova(P valor) throws Exception;
}
