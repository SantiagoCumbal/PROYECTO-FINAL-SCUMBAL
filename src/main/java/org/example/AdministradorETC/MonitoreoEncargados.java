package org.example.AdministradorETC;

import org.example.Encargados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class MonitoreoEncargados {
    public JPanel MainPanel;
    private JTextField buscarT;
    private JButton buscarButton;
    private JTextField direccionT;
    private JTextField telefonoT;
    private JTextField correoT;
    private JButton actualizarButton;
    private JButton cancelarButton;
    private JLabel cedulaT;
    private JLabel nombreT;
    private JLabel edadT;
    private JButton regresarButton;
    private JTextField contraseñaN;
    private String nombreAdmin;
    String url="jdbc:mysql://localhost:3306/Futbolito";
    String usuario = "root";
    String contraseña= "12345";
    Encargados enc = new Encargados();

    public MonitoreoEncargados(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try(Connection connection = DriverManager.getConnection(url,usuario,contraseña)) {
                    System.out.println("CONEXION EXITOSA");
                    String query="select * from encargados where cedula= '"+buscarT.getText()+"'";
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(query);

                    if(buscarT.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Ingrese una cedula");
                        buscarT.setText("");
                    }
                    if(verificarCedula(buscarT.getText())){
                        if (resultSet.next() == false) {
                            JOptionPane.showMessageDialog(null, "La cédula no existe en la base de datos");
                        } else {
                            cedulaT.setText(resultSet.getString("cedula"));
                            nombreT.setText(resultSet.getString("nombre"));
                            edadT.setText(resultSet.getString("edad"));
                            correoT.setText(resultSet.getString("correo"));
                            telefonoT.setText(resultSet.getString("numero_telefono"));
                            direccionT.setText(resultSet.getString("direccion"));
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "La cedula no es valida");
                    }

                }catch (SQLException e1){
                    System.out.println(e1.getMessage());

                }
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection connection = DriverManager.getConnection(url,usuario,contraseña)) {
                    String updateQuery = "UPDATE encargados SET correo=?, numero_telefono=?, direccion=? WHERE cedula=?";
                    PreparedStatement ps = connection.prepareStatement(updateQuery);
                    if(correoT.getText().equals("")||telefonoT.getText().equals("")||direccionT.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Los parametros deben estar llenos");
                    }else{
                        if(verificarCorreo(correoT.getText()) && verificarTelefono(telefonoT.getText())){
                            enc.setCorreo(correoT.getText());
                            enc.setTelefono(telefonoT.getText());
                            enc.setDireccion(direccionT.getText());
                            enc.setCedula(cedulaT.getText());
                            ps.setString(1, enc.getCorreo());
                            ps.setString(2, enc.getTelefono());
                            ps.setString(3, enc.getDireccion());
                            ps.setString(4, enc.getCedula());
                            int actualizacion = ps.executeUpdate();
                            if (actualizacion > 0) {
                                JOptionPane.showMessageDialog(null, "Información actualizada con éxito");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se pudo actualizar la información");
                            }

                            if (!contraseñaN.getText().equals("")){
                                String updateQuery1 = "UPDATE encargados SET contraseña=? WHERE cedula=?";
                                PreparedStatement ps1 = connection.prepareStatement(updateQuery1);
                                String Contraseña = contraseñaN.getText();
                                String contraseñaDe = generateHash(Contraseña);
                                enc.setContraseña(contraseñaDe);
                                ps1.setString(1, enc.getContraseña());
                                ps1.setString(2, enc.getCedula());
                                int actualizacionC = ps1.executeUpdate();
                                if (actualizacionC > 0) {
                                    JOptionPane.showMessageDialog(null, "Contraseña Actualizada");
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se pudo actualizar la contraseña");
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Parametros invalidos: \n* Correo invalido \n* Telefono invalido");
                        }
                    }
                }catch(SQLException e1){
                    System.out.println(e1.getMessage());
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cedulaT.setText("");
                nombreT.setText("");
                edadT.setText("");
                correoT.setText("");
                telefonoT.setText("");
                direccionT.setText("");
                contraseñaN.setText("");
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new InicioAdministracion(nombreAdmin).MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(500,300);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(regresarButton)).dispose();
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

    public boolean verificarCorreo(String correo){
        String verficar = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return correo.matches(verficar);
    }

    public boolean verificarCedula(String cedula){
        String verficar = "^[0-9]{10}$";
        return cedula.matches(verficar);
    }

    public boolean verificarTelefono(String telefono){
        String verficar = "^[0-9]{10}$";
        return telefono.matches(verficar);
    }

}
