package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioAdministracion {
    public JPanel MainPanel;
    private JLabel name;
    private JButton rergistrarCanchasButton;
    private JButton agregarEncargadosButton;
    private JButton asignacionDeHorariosButton;
    private JButton monitorioDeEncargadosButton;

    public InicioAdministracion(String nombre) {
        name.setText("Sr/Sra "+nombre);
        agregarEncargadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }



}
