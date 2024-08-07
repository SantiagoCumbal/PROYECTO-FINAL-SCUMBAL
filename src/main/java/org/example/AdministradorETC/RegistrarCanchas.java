package org.example.AdministradorETC;

import org.example.Canchas;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
    private JRadioButton a22JugadoresRadioButton;
    private JRadioButton a10JugadoresRadioButton;
    private JRadioButton a14JugadoresRadioButton;
    private JButton cargarImagenButton;
    private JLabel ImagenP;
    private JButton regresarButton;
    private String nombreAdmin;
    public File selectFile;

    String url="jdbc:mysql://localhost:3306/Futbolito";
    String usuario = "root";
    String contraseña= "12345";
    Canchas cancha = new Canchas();
    String sql="INSERT INTO canchas (codigo, nombre_cancha, imagen, ubicacion, estado, capacidad, horario_8, horario_10, horario_12, horario_14, horario_16, horario_18) " +
            "VALUES (?, ?, ?, ?, ?, ?, 'Disponible', 'Disponible', 'Disponible', 'Disponible', 'Disponible', 'Disponible')";

    public RegistrarCanchas(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
        ImagenP.setPreferredSize(new Dimension(400, 200)); // Adjust the size as needed
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
                    if(codigoT.getText().equals("")||nombreCanchaT.getText().equals("")||ubicacionT.getText().equals("")|| (a14JugadoresRadioButton.isSelected() == false && a10JugadoresRadioButton.isSelected() == false && a22JugadoresRadioButton.isSelected() == false ) || selectFile == null){
                        JOptionPane.showMessageDialog(null, "NO SE PUDO GUARDAR POR FAVOR INGRESE BIEN LOS PARAMETROS");
                    } else{
                        if (verificarCodigo(codigoT.getText())){
                            cancha.setCodigo(codigoT.getText());
                            cancha.setNombreCancha(nombreCanchaT.getText());
                            cancha.setEstado("Activo");
                            cancha.setUbicacion(ubicacionT.getText());

                            byte[] imagenBytes = readImageFile(selectFile.toPath());
                            if (imagenBytes != null) {
                                cancha.setImagen(imagenBytes);
                            } else {
                                JOptionPane.showMessageDialog(null, "Error: la imagen no se pudo leer correctamente");
                                return;
                            }

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
                            ps.setBytes(3, cancha.getImagen());
                            ps.setString(4, cancha.getUbicacion());
                            ps.setString(5, cancha.getEstado());
                            ps.setString(6, cancha.getCapacidad());
                            ps.executeUpdate();
                            JOptionPane.showMessageDialog(null, "GUARDADO CON EXITO");
                        }else{
                            JOptionPane.showMessageDialog(null, "Codigo invalido");
                        }
                    }
                }catch (SQLException e1){
                    System.out.println(e1.getMessage());
                    JOptionPane.showMessageDialog(null, "Ya existe una cancha con ese codigo o nombre");
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
                        JOptionPane.showMessageDialog(null, "Error al cargar la imagen");
                    }
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigoT.setText("");
                nombreCanchaT.setText("");
                ubicacionT.setText("");
                a14JugadoresRadioButton.setSelected(false);
                a10JugadoresRadioButton.setSelected(false);
                a22JugadoresRadioButton.setSelected(false);
                ImagenP.setIcon(null);
                selectFile = null;
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
    public byte[] readImageFile(Path filePath) {
        try {
            return Files.readAllBytes(filePath);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return null;
        }
    }
    public boolean verificarCodigo(String codigo){
        String verficar = "^[0-9]{4}$";
        return codigo.matches(verficar);
    }

}
