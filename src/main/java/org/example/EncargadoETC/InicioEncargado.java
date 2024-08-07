package org.example.EncargadoETC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioEncargado {
    public JPanel MainPanel;
    private JLabel name;
    private JButton reservarCanchasButton;
    private JButton datosDeCanchasButton;
    private JButton salirButton;

    public InicioEncargado(String nombre) {
        name.setText("Sr/Sra "+nombre);
        reservarCanchasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new ReservasCanchas(nombre).MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(400, 400);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(reservarCanchasButton)).dispose();
            }
        });
        datosDeCanchasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new DatosCanchas(nombre).MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(400, 400);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(datosDeCanchasButton)).dispose();
            }
        });
    }
}
