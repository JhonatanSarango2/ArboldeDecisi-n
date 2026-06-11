import java.util.LinkedList;
import java.util.Queue;

public class ArbolDecision {
    private Nodo raiz;
    private Nodo actual;

    public ArbolDecision() {
        raiz = null;
        actual = null;
        predefinir();
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public Nodo getActual() {
        return actual;
    }

    public void predefinir() {
        Nodo pregunta1 = new Nodo("¿El problema se resuelve tomando decisiones por condiciones?");
        Nodo pregunta2 = new Nodo("¿Las respuestas se pueden dividir en Sí o No?");
        Nodo pregunta3 = new Nodo("¿Las reglas se pueden escribir manualmente?");
        Nodo pregunta4 = new Nodo("¿El objetivo principal es ordenar o buscar datos?");

        Nodo resultado1 = new Nodo("Resultado: Se recomienda usar un árbol de decisión manual.", true);
        Nodo resultado2 = new Nodo("Resultado: Se recomienda usar un árbol entrenado con datos históricos.", true);
        Nodo resultado3 = new Nodo("Resultado: Se recomienda usar una estructura simple como if, else o switch.", true);
        Nodo resultado4 = new Nodo("Resultado: Se recomienda usar un árbol binario de búsqueda.", true);
        Nodo resultado5 = new Nodo("Resultado: Se recomienda usar una lista, pila o cola.", true);

        pregunta1.setIzquierdo(pregunta2);  // Respuesta Sí
        pregunta1.setDerecho(pregunta4);    // Respuesta No

        pregunta2.setIzquierdo(pregunta3);  // Respuesta Sí
        pregunta2.setDerecho(resultado3);   // Respuesta No

        pregunta3.setIzquierdo(resultado1); // Respuesta Sí
        pregunta3.setDerecho(resultado2);   // Respuesta No

        pregunta4.setIzquierdo(resultado4); // Respuesta Sí
        pregunta4.setDerecho(resultado5);   // Respuesta No

        raiz = pregunta1;
        actual = raiz;
    }

    public void reiniciar() {
        actual = raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public boolean esResultadoActual() {
        return actual != null && actual.isResultado();
    }

    public String obtenerTextoActual() {
        if (actual == null) {
            return "No existe una pregunta actual.";
        }
        return actual.getTexto();
    }

    public void responderSi() {
        if (actual != null && actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
    }

    public void responderNo() {
        if (actual != null && actual.getDerecho() != null) {
            actual = actual.getDerecho();
        }
    }

    public String preOrden() {
        if (raiz == null) {
            return "No hay elementos en el árbol.";
        }
        return preOrden(raiz);
    }

    private String preOrden(Nodo actual) {
        if (actual != null) {
            return actual.getTexto() + "\n" +
                    preOrden(actual.getIzquierdo()) +
                    preOrden(actual.getDerecho());
        }
        return "";
    }

    public String inOrden() {
        if (raiz == null) {
            return "No hay elementos en el árbol.";
        }
        return inOrden(raiz);
    }

    private String inOrden(Nodo actual) {
        if (actual != null) {
            return inOrden(actual.getIzquierdo()) +
                    actual.getTexto() + "\n" +
                    inOrden(actual.getDerecho());
        }
        return "";
    }

    public String postOrden() {
        if (raiz == null) {
            return "No hay elementos en el árbol.";
        }
        return postOrden(raiz);
    }

    private String postOrden(Nodo actual) {
        if (actual != null) {
            return postOrden(actual.getIzquierdo()) +
                    postOrden(actual.getDerecho()) +
                    actual.getTexto() + "\n";
        }
        return "";
    }

    public Queue<Nodo> niveles() throws Exception {
        if (raiz == null) {
            throw new Exception("No hay elementos en el árbol.");
        }

        Queue<Nodo> cola1 = new LinkedList<Nodo>();
        Queue<Nodo> cola2 = new LinkedList<Nodo>();
        Nodo aux;

        cola1.add(raiz);

        while (!cola1.isEmpty()) {
            aux = cola1.poll();

            if (aux.getIzquierdo() != null) {
                cola1.add(aux.getIzquierdo());
            }

            if (aux.getDerecho() != null) {
                cola1.add(aux.getDerecho());
            }

            cola2.add(aux);
        }

        return cola2;
    }

    public String nivelesTexto() {
        String texto = "";

        try {
            Queue<Nodo> recorrido = niveles();
            for (Nodo n : recorrido) {
                texto = texto + n.getTexto() + "\n";
            }
        } catch (Exception e) {
            texto = e.getMessage();
        }

        return texto;
    }

    public String estructuraTexto() {
        return "ESTRUCTURA DEL ÁRBOL DE DECISIÓN\n\n" +
                "En este ejemplo, cada nodo guarda una pregunta o un resultado.\n" +
                "El hijo izquierdo representa la respuesta Sí.\n" +
                "El hijo derecho representa la respuesta No.\n\n" +
                "Raíz: " + raiz.getTexto() + "\n\n" +
                "Relaciones principales:\n" +
                "1. Raíz -> Sí: " + raiz.getIzquierdo().getTexto() + "\n" +
                "2. Raíz -> No: " + raiz.getDerecho().getTexto() + "\n";
    }
}