package org.example.EncargadoETC;

import org.example.Canchas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;

public class DatosCanchas {
    public JPanel MainPanel;
    private JTextField buscarT;
    private JButton buscarButton;
    private JButton cancelarButton;
    private JButton regresarButton;
    private JLabel estadoT;
    private JLabel codigoT;
    private JLabel imagenP;
    private JLabel nombreCanchaT;
    private JLabel ubicacionT;
    private JLabel capacidadT;
    private String nombreAdmin;

    String url="jdbc:mysql://localhost:3306/Futbolito";
    String usuario = "root";
    String contraseña= "12345";
    Canchas cancha=new Canchas();

    public DatosCanchas(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
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
                            estadoT.setText(resultSet.getString("estado"));
                            ubicacionT.setText(resultSet.getString("ubicacion"));
                            capacidadT.setText(resultSet.getString("capacidad"));

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
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new InicioEncargado(nombreAdmin).MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(400, 400);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(regresarButton)).dispose();

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarT.setText("");
                codigoT.setText("");
                nombreCanchaT.setText("");
                ubicacionT.setText("");
                capacidadT.setText("");
                estadoT.setText("");
                imagenP.setIcon(null);
            }
        });
    }
    public boolean verificarCodigo(String codigo){
        String verficar = "^[0-9]{4}$";
        return codigo.matches(verficar);
    }
}
