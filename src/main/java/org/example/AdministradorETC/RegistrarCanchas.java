package org.example.AdministradorETC;

import org.example.Canchas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFileChooser;

public class RegistrarCanchas {
    public JPanel MainPanel;
    private JTextField codigoT;
    private JTextField nombreCanchaT;
    private JTextField ubicacionT;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JLabel Error;
    private JRadioButton a22JugadoresRadioButton;
    private JRadioButton a10JugadoresRadioButton;
    private JRadioButton a14JugadoresRadioButton;
    private JButton cargarImagenButton;
    private JLabel ImagenP;

    public File selectFile;

    String url="jdbc:mysql://localhost:3306/Futbolito";
    String usuario = "root";
    String contraseña= "12345";
    Canchas cancha = new Canchas();
    String sql="INSERT INTO canchas (codigo, nombre_cancha, imagen, ubicacion, estado, capacidad) VALUES (?,?,?,?,?,?)";

    public RegistrarCanchas() {
        ImagenP.setPreferredSize(new Dimension(200, 200)); // Adjust the size as needed
        ImagenP.setOpaque(true);
        a14JugadoresRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a10JugadoresRadioButton.setSelected(false);
                a22JugadoresRadioButton.setSelected(false);
            }
        });

        a22JugadoresRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a10JugadoresRadioButton.setSelected(false);
                a14JugadoresRadioButton.setSelected(false);
            }
        });

        a10JugadoresRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a14JugadoresRadioButton.setSelected(false);
                a22JugadoresRadioButton.setSelected(false);
            }
        });


        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection connection = DriverManager.getConnection(url,usuario,contraseña)){
                    if(codigoT.getText().equals("")||nombreCanchaT.getText().equals("")||ubicacionT.getText().equals("")|| (a14JugadoresRadioButton.isSelected() == false && a10JugadoresRadioButton.isSelected() == false && a22JugadoresRadioButton.isSelected() == false )){
                        Error.setText("NO SE PUDO GUARDAR POR FAVOR INGRESE BIEN LOS PARAMETROS");
                    } else{
                        if (verificarCodigo(codigoT.getText())){
                            cancha.setCodigo(codigoT.getText());
                            cancha.setNombreCancha(nombreCanchaT.getText());
                            cancha.setEstado("Activo");
                            cancha.setUbicacion(ubicacionT.getText());
                            if (a14JugadoresRadioButton.isSelected() == true) {
                                cancha.setCapacidad("14 jugadores");
                            } else if (a10JugadoresRadioButton.isSelected() == true) {
                                cancha.setCapacidad("10 jugadores");
                            } else if (a22JugadoresRadioButton.isSelected() == true) {
                                cancha.setCapacidad("22 jugadores");
                            }
                            PreparedStatement ps=connection.prepareStatement(sql);
                            ps.setString(1, cancha.getCodigo());
                            ps.setString(2, cancha.getNombreCancha());
                            ps.setString(3, cancha.getUbicacion());
                            ps.setString(4, cancha.getEstado());
                            ps.setString(4, cancha.getCapacidad());
                            ps.executeUpdate();
                            Error.setText("GUARDADO CON EXITO");
                        }else{
                            Error.setText("Codigo invalido");
                        }
                    }
                }catch (SQLException e1){
                    System.out.println(e1.getMessage());
                    Error.setText("Ya existe una cancha con ese codigo o nombre");
                }
            }
        });

        cargarImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int resultado = fileChooser.showOpenDialog(null);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    selectFile=fileChooser.getSelectedFile();
                    try  {

                        Image img = ImageIO.read(selectFile);
                        ImageIcon originalIcon = new ImageIcon(img);

                        int imgWidth = ImagenP.getWidth();
                        int imgHeight = ImagenP.getHeight();

                        Image scaledImage = originalIcon.getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
                        ImagenP.setIcon(new ImageIcon(scaledImage));

                    } catch (Exception e1) {
                        Error.setText("Error al cargar la imagen");
                    }
                }
            }
        });
    }
    public boolean verificarCodigo(String codigo){
        String verficar = "^[0-9]{4}$";
        return codigo.matches(verficar);
    }

}
