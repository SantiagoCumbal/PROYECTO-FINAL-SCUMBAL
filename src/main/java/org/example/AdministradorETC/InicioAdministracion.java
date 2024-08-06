package org.example.AdministradorETC;

import javax.swing.*;
import java.awt.*;
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
                JFrame frame = new JFrame();
                frame.setContentPane(new AgregarEncargados(nombre).MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(300,300);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(agregarEncargadosButton)).dispose();
            }
        });
        rergistrarCanchasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new RegistrarCanchas(nombre).MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(300,300);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(agregarEncargadosButton)).dispose();
            }
        });
        monitorioDeEncargadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new MonitoreoEncargados().MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(300,300);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(agregarEncargadosButton)).dispose();
            }
        });
    }



}
