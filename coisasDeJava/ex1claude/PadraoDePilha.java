package coisasDeJava.ex1claude;

public interface PadraoDePilha <X>{
    public void empilhe(X valor)throws Exception; 
    public X getTopo() throws Exception;
    public void desempilhe() throws Exception;
}
