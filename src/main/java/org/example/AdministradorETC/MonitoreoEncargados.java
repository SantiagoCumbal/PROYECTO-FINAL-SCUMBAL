package org.example.AdministradorETC;

import org.example.Encargados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel Error;
    private JLabel cedulaT;
    private JLabel nombreT;
    private JLabel edadT;
    private JButton regresarButton;
    String url="jdbc:mysql://localhost:3306/Futbolito";
    String usuario = "root";
    String contraseña= "12345";
    Encargados enc = new Encargados();

    public MonitoreoEncargados() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try(Connection connection = DriverManager.getConnection(url,usuario,contraseña)) {
                    System.out.println("CONEXION EXITOSA");
                    String query="select * from encargados where cedula= '"+buscarT.getText()+"'";
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(query);

                    if(buscarT.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Ingrese un texto");
                        buscarT.setText("");
                    }
                    if(verificarCedula(buscarT.getText())){
                        if (resultSet.next() == false) {
                            JOptionPane.showMessageDialog(null, "La cédula no existe en la base de datos", "Información", JOptionPane.INFORMATION_MESSAGE);
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
                    String updateQuery = "UPDATE encargados SET correo=?, edad=?, numero_telefono=? WHERE cedula=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                }catch(SQLException e1){
                    System.out.println(e1.getMessage());
                }
            }
        });
    }
    public boolean verificarCedula(String cedula){
        String verficar = "^[0-9]{10}$";
        return cedula.matches(verficar);
    }
}
