import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

public class ventana extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JPanel prediccion;
    private JPanel recorridos;
    private JPanel estructura;
    private JLabel lblPregunta;
    private JTextArea textAreaPrediccion;
    private JTextArea textAreaRecorridos;
    private JTextArea textAreaEstructura;
    private JButton siButton;
    private JButton noButton;
    private JButton reiniciarButton;
    private JButton preordenButton;
    private JButton inordenButton;
    private JButton postordenButton;
    private JButton nivelesButton;
    private JButton mostrarEstructuraButton;

    private ArbolDecision arbol;

    public ventana() {
        arbol = new ArbolDecision();

        if (panel1 == null) {
            crearComponentes();
        }

        configurarVentana();
        actualizarPregunta();

        siButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arbol.responderSi();
                actualizarPregunta();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arbol.responderNo();
                actualizarPregunta();
            }
        });

        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arbol.reiniciar();
                siButton.setEnabled(true);
                noButton.setEnabled(true);
                actualizarPregunta();
            }
        });

        preordenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaRecorridos.setText("RECORRIDO PREORDEN\nProcesa primero el nodo actual, luego izquierda y derecha.\n\n" + arbol.preOrden());
            }
        });

        inordenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaRecorridos.setText("RECORRIDO INORDEN\nPrimero izquierda, luego procesa el nodo y finalmente derecha.\n\n" + arbol.inOrden());
            }
        });

        postordenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaRecorridos.setText("RECORRIDO POSTORDEN\nPrimero izquierda, luego derecha y al final procesa el nodo.\n\n" + arbol.postOrden());
            }
        });

        nivelesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarNiveles();
            }
        });

        mostrarEstructuraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaEstructura.setText(arbol.estructuraTexto());
            }
        });
    }

    private void configurarVentana() {
        setContentPane(panel1);
        setTitle("Árbol de Decisión - Programación III");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarPregunta() {
        if (arbol.esResultadoActual()) {
            lblPregunta.setText("Resultado encontrado");
            textAreaPrediccion.setText(arbol.obtenerTextoActual());
            siButton.setEnabled(false);
            noButton.setEnabled(false);
        } else {
            lblPregunta.setText(arbol.obtenerTextoActual());
            textAreaPrediccion.setText("Responde con los botones Sí o No para recorrer el árbol.\n\n" +
                    "En este programa el lado izquierdo representa Sí y el lado derecho representa No.");
            siButton.setEnabled(true);
            noButton.setEnabled(true);
        }
    }

    private void mostrarNiveles() {
        String texto = "RECORRIDO POR NIVELES\nSe usa una cola, porque la cola trabaja con lógica FIFO.\n\n";

        try {
            Queue<Nodo> cola = arbol.niveles();
            for (Nodo n : cola) {
                texto = texto + n.getTexto() + "\n";
            }
        } catch (Exception ex) {
            texto = ex.getMessage();
        }

        textAreaRecorridos.setText(texto);
    }

    private void crearComponentes() {
        panel1 = new JPanel(new BorderLayout());
        tabbedPane1 = new JTabbedPane();
        prediccion = new JPanel(new BorderLayout());
        recorridos = new JPanel(new BorderLayout());
        estructura = new JPanel(new BorderLayout());

        lblPregunta = new JLabel("Pregunta actual", SwingConstants.CENTER);
        lblPregunta.setFont(new Font("Arial", Font.BOLD, 16));

        textAreaPrediccion = new JTextArea();
        textAreaRecorridos = new JTextArea();
        textAreaEstructura = new JTextArea();

        textAreaPrediccion.setEditable(false);
        textAreaRecorridos.setEditable(false);
        textAreaEstructura.setEditable(false);

        siButton = new JButton("Sí");
        noButton = new JButton("No");
        reiniciarButton = new JButton("Reiniciar");
        preordenButton = new JButton("Preorden");
        inordenButton = new JButton("Inorden");
        postordenButton = new JButton("Postorden");
        nivelesButton = new JButton("Niveles");
        mostrarEstructuraButton = new JButton("Mostrar estructura");

        JPanel panelBotonesPrediccion = new JPanel(new GridLayout(1, 3, 5, 5));
        panelBotonesPrediccion.add(siButton);
        panelBotonesPrediccion.add(noButton);
        panelBotonesPrediccion.add(reiniciarButton);

        prediccion.add(lblPregunta, BorderLayout.NORTH);
        prediccion.add(new JScrollPane(textAreaPrediccion), BorderLayout.CENTER);
        prediccion.add(panelBotonesPrediccion, BorderLayout.SOUTH);

        JPanel panelBotonesRecorridos = new JPanel(new GridLayout(1, 4, 5, 5));
        panelBotonesRecorridos.add(preordenButton);
        panelBotonesRecorridos.add(inordenButton);
        panelBotonesRecorridos.add(postordenButton);
        panelBotonesRecorridos.add(nivelesButton);

        recorridos.add(panelBotonesRecorridos, BorderLayout.NORTH);
        recorridos.add(new JScrollPane(textAreaRecorridos), BorderLayout.CENTER);

        estructura.add(mostrarEstructuraButton, BorderLayout.NORTH);
        estructura.add(new JScrollPane(textAreaEstructura), BorderLayout.CENTER);

        tabbedPane1.addTab("Predicción", prediccion);
        tabbedPane1.addTab("Recorridos", recorridos);
        tabbedPane1.addTab("Estructura", estructura);
        panel1.add(tabbedPane1, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new ventana();
    }
}
