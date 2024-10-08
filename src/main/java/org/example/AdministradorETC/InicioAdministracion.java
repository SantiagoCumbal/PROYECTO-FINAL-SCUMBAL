package org.example.AdministradorETC;

import org.example.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioAdministracion {
    public JPanel MainPanel;
    private JLabel name;
    private JButton rergistrarCanchasButton;
    private JButton agregarEncargadosButton;
    private JButton monitoreoDeCanchasButton;
    private JButton monitorioDeEncargadosButton;
    private JButton asignacionDeHorariosButton;
    private JButton salirButton;

    public InicioAdministracion(String nombre) {
        name.setText("Sr/Sra "+nombre);
        agregarEncargadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new AgregarEncargados(nombre).MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(500,500);
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
                frame.setSize(700,700);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(rergistrarCanchasButton)).dispose();
            }
        });
        monitorioDeEncargadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new MonitoreoEncargados(nombre).MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(500,500);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(monitorioDeEncargadosButton)).dispose();
            }
        });
        monitoreoDeCanchasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new MonitoreoCanchas(nombre).MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(700,700);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(monitoreoDeCanchasButton)).dispose();
            }
        });
        asignacionDeHorariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new AsignacionHorarios(nombre).MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(700,700);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(asignacionDeHorariosButton)).dispose();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new Login().MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(400, 400);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(salirButton)).dispose();
            }
        });
    }



}
