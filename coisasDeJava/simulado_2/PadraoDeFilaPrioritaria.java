package coisasDeJava.simulado_2;

public interface PadraoDeFilaPrioritaria<T> {
    public void guardeItem(T valor, int prioridade) throws Exception;
    public T getItem() throws Exception;
    public void removaItem() throws Exception;
}
