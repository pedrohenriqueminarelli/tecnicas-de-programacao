package coisasDeJava.simulado_1;

public interface PadraoDeFila<X>{
    public void guardeItem(X valor) throws Exception;

    public X getItem() throws Exception;

    public void removaItem()throws Exception;
}