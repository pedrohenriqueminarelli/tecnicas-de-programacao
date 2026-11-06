package preparacao_para_prova.ex4claude;

public interface PadraoDeHistorico<T> {
    public void empilheAcesso(T pagina, int duracao)throws Exception;
    public T getPaginaAtual() throws Exception;
    public void volte()throws Exception;
}
