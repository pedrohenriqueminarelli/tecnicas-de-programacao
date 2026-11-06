package ex3claude;

public interface PadraoDeFilaDePedidos<T> {
     public void guardePedido(T valor, int quantidade) throws Exception;
     public T getPedido() throws Exception;
     public void removaPedido() throws Exception;
}
