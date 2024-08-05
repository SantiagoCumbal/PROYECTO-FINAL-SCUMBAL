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
    private JRadioButton a22JugadoresRadioButton;
    private JRadioButton a10JugadoresRadioButton;
    private JRadioButton a14JugadoresRadioButton;

    public RegistrarCanchas() {
        a14JugadoresRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a10JugadoresRadioButton.setSelected(false);
                a22JugadoresRadioButton.setSelected(false);
            }
        });

        a22JugadoresRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a10JugadoresRadioButton.setSelected(false);
                a14JugadoresRadioButton.setSelected(false);
            }
        });

        a10JugadoresRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a14JugadoresRadioButton.setSelected(false);
                a22JugadoresRadioButton.setSelected(false);
            }
        });


    }
}
