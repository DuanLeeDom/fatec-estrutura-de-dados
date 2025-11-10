import java.security.InvalidParameterException;

public class Dado implements Comparable<Dado> {
   private int valor;

   public Dado(int valor) {
    this.valor = valor;
   }

   public int getValor() {
    return valor;
   }

   public void setValor(int valor) {
    this.valor = valor;
   }

   @Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + valor;
    return result;
}

   @Override
   public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Dado other = (Dado) obj;
    if (valor != other.valor)
        return false;
    return true;
   }

   @Override
   public int compareTo(Dado o) {
        int resultado = 0;
        if (this == o)
            return 0;
        if (o == null)
            throw new InvalidParameterException("Conteúdo nulo.");
        if (getClass() != o.getClass())
            throw new InvalidParameterException("Objetos de classes diferentes");
        
        resultado = this.hashCode() - o.hashCode();
        return resultado;
   }

   @Override
   public String toString() {
    return "Dado [valor=" + valor + "] ";
   }

   
   
}
