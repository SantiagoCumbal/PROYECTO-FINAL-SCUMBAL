package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Login {
    public JPanel MainPanel;
    private JComboBox comboBox1;
    private JTextField usuarioT;
    private JPasswordField contraseñaT;
    private JButton iniciarSesionButton;
    private JLabel Error;
    String url="jdbc:mysql://localhost:3306/Futbolito";
    String usuario = "root";
    String contraseña= "12345";
    Administradores admin = new Administradores();
    Encargados enc = new Encargados();

    public Login() {
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection connection= DriverManager.getConnection(url,usuario,contraseña)){
                    System.out.println("CONEXION EXITOSA");
                    String opcion=comboBox1.getSelectedItem().toString();
                    if(opcion.equals("Administrador")){
                        String query="select * from administrador";
                        Statement statement=connection.createStatement();
                        ResultSet resultSet=statement.executeQuery(query);
                        admin.setCorreo(usuarioT.getText());
                        String Contraseña = contraseñaT.getText();
                        String contraseñaDe = generateHash(Contraseña);
                        admin.setContraseña(contraseñaDe);

                        while(resultSet.next()){
                            if(admin.getCorreo().equals(resultSet.getString("correo")) && admin.getContraseña().equals(resultSet.getString("contraseña"))){
                                admin.setNombre(resultSet.getString("nombre"));
                                JFrame frame = new JFrame();
                                frame.setContentPane(new InicioAdministracion(admin.getNombre()).MainPanel);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                                frame.setSize(300,300);
                                frame.setVisible(true);
                                ((JFrame)SwingUtilities.getWindowAncestor(iniciarSesionButton)).dispose();
                            }else{
                                Error.setText("ERROR DE INGRESO");
                                usuarioT.setText("");
                                contraseñaT.setText("");
                            }
                        }

                    } else if (opcion.equals("Encargado")) {
                        String query="select * from encargado";
                        Statement statement=connection.createStatement();
                        ResultSet resultSet=statement.executeQuery(query);
                        enc.setCorreo(usuarioT.getText());
                        enc.setCedula(contraseñaT.getText());

                        while(resultSet.next()){
                            if(enc.getCorreo().equals(resultSet.getString("correo")) && enc.getCedula().equals(resultSet.getString("cedula"))){
                                JFrame frame = new JFrame();
                                frame.setContentPane(new InicioAdministracion(admin.getNombre()).MainPanel);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                                frame.setSize(300,300);
                                frame.setVisible(true);
                                ((JFrame)SwingUtilities.getWindowAncestor(iniciarSesionButton)).dispose();
                            }else{
                                Error.setText("ERROR DE INGRESO");
                                usuarioT.setText("");
                                contraseñaT.setText("");
                            }
                        }
                    }

                }catch(SQLException e1){
                    System.out.println(e1.getMessage());
                }
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

