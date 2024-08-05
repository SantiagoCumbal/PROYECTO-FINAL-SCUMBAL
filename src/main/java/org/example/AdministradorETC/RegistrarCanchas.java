package org.example.AdministradorETC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarCanchas {
    public JPanel MainPanel;
    private JTextField codigoT;
    private JTextField nombreCanchaT;
    private JTextField ubicacionT;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JLabel Error;
    private JComboBox estadoT;
    private JSpinner capacidad1;

    public RegistrarCanchas() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
