package org.example.AdministradorETC;

import org.example.Administradores;
import org.example.Encargados;
import javax.swing.*;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AgregarEncargados {
    private JTextField cedulaT;
    private JTextField nombreT;
    private JSpinner edadS;
    private JTextField telefonoT;
    private JTextField direccionT;
    private JTextField correoT;
    private JPasswordField contraseñaT;
    private JButton guardarButton;
    private JButton cancelarButton;
    public JPanel MainPanel;
    private JLabel Error;
    String url="jdbc:mysql://localhost:3306/Futbolito";
    String usuario = "root";
    String contraseña= "12345";
    Encargados enc = new Encargados();
    String sql="INSERT INTO encargados (cedula, correo, nombre, edad, contraseña, numero_telefono, direccion) VALUES (?,?,?,?,?,?,?)";

    public AgregarEncargados() {
        int inicial=18;
        int minimo=18;
        int maximo=70;
        SpinnerNumberModel base = new SpinnerNumberModel(inicial,minimo,maximo,1);
        edadS.setModel(base);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection connection = DriverManager.getConnection(url,usuario,contraseña)){

                    if(cedulaT.getText().equals("")||correoT.getText().equals("")||nombreT.getText().equals("")||contraseñaT.getText().equals("")||telefonoT.getText().equals("")||direccionT.getText().equals("")){
                        Error.setText("NO SE PUDO GUARDAR POR FAVOR INGRESE BIEN LOS PARAMETROS");
                    }else{
                        enc.setCedula(cedulaT.getText());
                        enc.setCorreo(correoT.getText());
                        enc.setNombre(nombreT.getText());
                        enc.setEdad(Integer.parseInt(edadS.getValue().toString()));
                        String Contraseña = contraseñaT.getText();
                        String contraseñaDe = generateHash(Contraseña);
                        enc.setContraseña(contraseñaDe);
                        enc.setTelefono(telefonoT.getText());
                        enc.setDireccion(direccionT.getText());
                        PreparedStatement ps=connection.prepareStatement(sql);
                        ps.setString(1,enc.getCedula());
                        ps.setString(2,enc.getCorreo());
                        ps.setString(3,enc.getNombre());
                        ps.setInt(4,enc.getEdad());
                        ps.setString(5, enc.getContraseña());
                        ps.setString(6,enc.getTelefono());
                        ps.setString(7,enc.getDireccion());
                        ps.executeUpdate();
                        Error.setText("GUARDADO CON EXITO");
                    }
                }catch (SQLException e1){
                    System.out.println(e1.getMessage());
                    Error.setText("Ya existe un encargado con esa cedula o correo");
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cedulaT.setText("");
                correoT.setText("");
                nombreT.setText("");
                edadS.setValue(18);
                telefonoT.setText("");
                direccionT.setText("");
                contraseñaT.setText("");
                Error.setText("");
            }
        });
    }
    public static String generateHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(input.getBytes());
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
