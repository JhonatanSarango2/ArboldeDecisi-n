public class Nodo {
    private String texto;
    private boolean resultado;
    private Nodo izquierdo;
    private Nodo derecho;

    public Nodo(String texto) {
        this.texto = texto;
        this.resultado = false;
        this.izquierdo = null;
        this.derecho = null;
    }

    public Nodo(String texto, boolean resultado) {
        this.texto = texto;
        this.resultado = resultado;
        this.izquierdo = null;
        this.derecho = null;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    @Override
    public String toString() {
        return texto;
    }
}
