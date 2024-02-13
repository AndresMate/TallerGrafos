package Interfaz;


import Logica.ControlOfRoute;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Window extends JFrame {

    /**
     * Attributes
     */
    private final Color PANELS_COLOR = new Color(255, 0, 31);
    private final Color BUTTONS_COLOR = new Color(0, 255, 220);
    private ControlOfRoute control = new ControlOfRoute();
    /**
     * Components for main window
     */
    private JPanel panelOptions = new JPanel();
    private JPanel panelBtn = new JPanel();
    private JPanel panelInformationText = new JPanel();

    private JButton btnGenerate = new JButton("Generar ruta");

    String[] arrayCities = {"Bogotá", "Medellín", "Cali", "Barranquilla", "Cartagena", "Bucaramanga", "Cúcuta", "Santa Marta",
            "Pereira", "Villavicencio"};
    private JComboBox comboCityOrigin = new JComboBox(arrayCities);
    private JComboBox comboCityDestiny = new JComboBox(arrayCities);

    private JTextArea areaInformation = new JTextArea(8, 27);
    private JScrollPane scrollInformation = new JScrollPane(areaInformation, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    public Window() {
        super("Cities");
        setSize(350, 250);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(PANELS_COLOR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        launchPanels();
    }

    public void launchPanels() {
        optionsMenu();
        watchInformation();
    }

    private void optionsMenu() {
        configPanel(panelOptions, new FlowLayout());
        panelOptions.add(comboCityOrigin);
        panelOptions.add(comboCityDestiny);
        panelOptions.add(btnGenerate);

        configPanel(panelBtn, new GridLayout(1, 1));
        addButtonToPanel(btnGenerate, panelBtn, e -> {
            areaInformation.setText(control.showRoute(comboCityOrigin.getSelectedIndex(), comboCityDestiny.getSelectedIndex()));
        });

        this.add(panelBtn, BorderLayout.SOUTH);
        this.add(panelOptions, BorderLayout.NORTH);
    }

    private void watchInformation() {
        configPanel(panelInformationText, new FlowLayout());
        areaInformation.setText("");
        areaInformation.setLineWrap(true);
        areaInformation.setEditable(false);
        panelInformationText.add(scrollInformation);
        this.add(panelInformationText, BorderLayout.CENTER);
    }

    /**
     * Configura un panel con un layout específico y lo hace visible.
     *
     * @param panel  El panel que se va a configurar.
     * @param layout El layout que se va a aplicar al panel.
     */
    private void configPanel(JPanel panel, LayoutManager layout) {
        panel.setLayout(layout);
        panel.setBackground(PANELS_COLOR);
    }

    /**
     * Configura los botones para mostrarlos con un color de fondo específico y
     * un color de fuente blanco.
     *
     * @param button El botón que se va a configurar.
     */
    private void configButtons(JButton button) {
        button.setBackground(BUTTONS_COLOR);
        button.setForeground(Color.WHITE);
    }

    /**
     * Agrega un botón a un panel con un listener de acción específico y lo
     * configura con colores y fuente específicos.
     *
     * @param button   El botón que se va a agregar.
     * @param panel    El panel al que se va a agregar el botón.
     * @param listener El listener de acción que se va a asignar al botón.
     */
    private void addButtonToPanel(JButton button, JPanel panel, ActionListener listener) {
        configButtons(button);
        button.addActionListener(listener);
        panel.add(button);
    }
}
