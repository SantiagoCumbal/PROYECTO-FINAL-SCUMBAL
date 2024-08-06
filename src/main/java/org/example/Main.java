package org.example;
import org.example.AdministradorETC.AgregarEncargados;
import org.example.AdministradorETC.MonitoreoCanchas;
import org.example.AdministradorETC.MonitoreoEncargados;
import org.example.AdministradorETC.RegistrarCanchas;

import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new MonitoreoCanchas().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
        frame.setSize(400, 400);
        frame.setVisible(true);
    }
}
