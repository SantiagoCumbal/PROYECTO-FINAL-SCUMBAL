package org.example.AdministradorETC;

import org.example.Canchas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;

public class MonitoreoCanchas {
    public JPanel MainPanel;
    private JTextField buscarT;
    private JButton buscarButton;
    private JButton actualizarButton;
    private JButton cancelarButton;
    private JButton regresarButton;
    private JComboBox estadoT;
    private JTextField nombreCanchaT;
    private JTextField ubicacionT;
    private JRadioButton a22JugadoresRadioButton;
    private JRadioButton a10JugadoresRadioButton;
    private JRadioButton a14JugadoresRadioButton;
    private JLabel codigoT;
    private JLabel imagenP;
    String url="jdbc:mysql://localhost:3306/Futbolito";
    String usuario = "root";
    String contraseña= "12345";
    Canchas cancha=new Canchas();

    public MonitoreoCanchas() {
        imagenP.setPreferredSize(new Dimension(400, 200)); // Adjust the size as needed
        imagenP.setOpaque(true);
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection connection = DriverManager.getConnection(url,usuario,contraseña)) {
                    System.out.println("CONEXION EXITOSA");
                    String query="select * from canchas where codigo= '"+buscarT.getText()+"'";
                    Statement statement=connection.createStatement();
                    ResultSet resultSet=statement.executeQuery(query);

                    if(buscarT.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Ingrese un codigo");
                        buscarT.setText("");
                    }
                    if(verificarCodigo(buscarT.getText())){
                        if (resultSet.next() == false) {
                            JOptionPane.showMessageDialog(null, "El codigo no existe en la base de datos");
                        } else {
                            codigoT.setText(resultSet.getString("codigo"));
                            nombreCanchaT.setText(resultSet.getString("nombre_cancha"));

                            String opcion=resultSet.getString("estado");
                            if(opcion.equals("Activo")){
                                estadoT.setSelectedIndex(0);
                            } else if (opcion.equals("Inactivo")) {
                                estadoT.setSelectedIndex(1);
                            }

                            ubicacionT.setText(resultSet.getString("ubicacion"));

                            String capacidad = resultSet.getString("capacidad");
                            if (capacidad.equals("14 jugadores")) {
                                a14JugadoresRadioButton.setSelected(true);
                                a10JugadoresRadioButton.setSelected(false);
                                a22JugadoresRadioButton.setSelected(false);

                            } else if (capacidad.equals("10 jugadores")) {
                                a10JugadoresRadioButton.setSelected(true);
                                a14JugadoresRadioButton.setSelected(false);
                                a22JugadoresRadioButton.setSelected(false);

                            } else if (capacidad.equals("22 jugadores")) {
                                a22JugadoresRadioButton.setSelected(true);
                                a10JugadoresRadioButton.setSelected(false);
                                a14JugadoresRadioButton.setSelected(false);
                            }

                            byte[] imagenBytes = resultSet.getBytes("imagen");
                            if (imagenBytes != null) {
                                try {
                                    Image img = ImageIO.read(new ByteArrayInputStream(imagenBytes));
                                    ImageIcon originalIcon = new ImageIcon(img);

                                    int imgWidth = imagenP.getWidth();
                                    int imgHeight = imagenP.getHeight();

                                    Image scaledImage = originalIcon.getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
                                    imagenP.setIcon(new ImageIcon(scaledImage));
                                } catch (IOException ioException) {
                                    JOptionPane.showMessageDialog(null, "Error al cargar imagen");
                                }
                            } else {
                                imagenP.setIcon(null);
                            }
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "El codigo no es valida");
                    }

                }catch (SQLException e1){
                    System.out.println(e1.getMessage());

                }
            }
        });
    }
    public boolean verificarCodigo(String codigo){
        String verficar = "^[0-9]{4}$";
        return codigo.matches(verficar);
    }
}
