package org.example.AdministradorETC;

import org.example.Canchas;
import org.example.Login;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;

public class AsignacionHorarios {
    public JPanel MainPanel;
    private JTextField buscarT;
    private JButton buscarButton;
    private JLabel codigoT;
    private JLabel nombreCanchaT;
    private JLabel imagenP;
    private JTable table1;
    private JButton realizarReservaButton;
    private JButton cancelarReservaButton;
    private JButton regresarButton;
    private JTextField cedulaT;
    private String nombreAdmin;
    private String horarioSeleccionado;
    String url="jdbc:mysql://localhost:3306/Futbolito";
    String usuario = "root";
    String contraseña= "12345";
    Canchas cancha=new Canchas();

    public AsignacionHorarios(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
        imagenP.setPreferredSize(new Dimension(400, 200));
        imagenP.setOpaque(true);
        String[] filas = {"Horario", "Estado"};
        Object[][] horario = {
                {"8:00 - 10:00", "Disponible"},
                {"10:00 - 12:00", "Disponible"},
                {"12:00 - 14:00", "Disponible"},
                {"14:00 - 16:00", "Disponible"},
                {"16:00 - 18:00", "Disponible"},
                {"18:00 - 20:00", "Disponible"}
        };
        DefaultTableModel modelo = new DefaultTableModel(horario, filas);
        table1.setModel(modelo);
        table1.getSelectionModel().addListSelectionListener(e -> {
            int fila = table1.getSelectedRow();
            if (fila != -1) {
                horarioSeleccionado = table1.getValueAt(fila, 0).toString();
            }
        });

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
                    if ("Inactivo".equals(resultSet.getString("estado"))) {
                        JOptionPane.showMessageDialog(null, "La cancha está inactiva.");
                        String[] filas = {"Horario", "Estado"};
                        Object[][] horario = {
                                {"8:00 - 10:00", resultSet.getString("horario_8")},
                                {"10:00 - 12:00", resultSet.getString("horario_10")},
                                {"12:00 - 14:00", resultSet.getString("horario_12")},
                                {"14:00 - 16:00", resultSet.getString("horario_14")},
                                {"16:00 - 18:00", resultSet.getString("horario_16")},
                                {"18:00 - 20:00", resultSet.getString("horario_18")}
                        };
                        DefaultTableModel modelo = new DefaultTableModel(horario, filas);
                        table1.setModel(modelo);
                    }else{
                        String[] filas = {"Horario", "Estado"};
                        Object[][] horario = {
                                {"8:00 - 10:00", resultSet.getString("horario_8")},
                                {"10:00 - 12:00", resultSet.getString("horario_10")},
                                {"12:00 - 14:00", resultSet.getString("horario_12")},
                                {"14:00 - 16:00", resultSet.getString("horario_14")},
                                {"16:00 - 18:00", resultSet.getString("horario_16")},
                                {"18:00 - 20:00", resultSet.getString("horario_18")}
                        };
                        DefaultTableModel modelo = new DefaultTableModel(horario, filas);
                        table1.setModel(modelo);
                    }

                }catch (SQLException e1){
                    System.out.println(e1.getMessage());

                }
            }
        });
        realizarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = table1.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione un horario para reservar.");
                    return;
                }
                String estadoHorario = table1.getValueAt(fila, 1).toString();
                if ("Ocupado".equals(estadoHorario)) {
                    JOptionPane.showMessageDialog(null, "No se puede realizar la reserva. El horario está ocupado.");
                    return;
                }

                String horarioSeleccionado = table1.getValueAt(fila, 0).toString();


                try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
                    String query = "select * from canchas where codigo= '" + codigoT.getText() + "'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    if (resultSet.next()) {
                        String estado = resultSet.getString("estado");
                        if (estado.equals("Inactivo")) {
                            JOptionPane.showMessageDialog(null, "La cancha está inactiva. No se puede realizar la reserva.");
                        }
                        if (estado.equals("Activo")) {

                            if(cedulaT.getText().equals("")){
                                JOptionPane.showMessageDialog(null, "Llenar la cedula");
                            }else{
                                if(verificarCedula(cedulaT.getText())){
                                    String updateQuery = "UPDATE canchas SET " +
                                            "horario_8 = CASE WHEN ? = '8:00 - 10:00' THEN 'Ocupado' ELSE horario_8 END, " +
                                            "cedula_reserva1 = CASE WHEN ? = '8:00 - 10:00' THEN ? ELSE cedula_reserva1 END, " +
                                            "horario_10 = CASE WHEN ? = '10:00 - 12:00' THEN 'Ocupado' ELSE horario_10 END, " +
                                            "cedula_reserva2 = CASE WHEN ? = '10:00 - 12:00' THEN ? ELSE cedula_reserva2 END, " +
                                            "horario_12 = CASE WHEN ? = '12:00 - 14:00' THEN 'Ocupado' ELSE horario_12 END, " +
                                            "cedula_reserva3 = CASE WHEN ? = '12:00 - 14:00' THEN ? ELSE cedula_reserva3 END, " +
                                            "horario_14 = CASE WHEN ? = '14:00 - 16:00' THEN 'Ocupado' ELSE horario_14 END, " +
                                            "cedula_reserva4 = CASE WHEN ? = '14:00 - 16:00' THEN ? ELSE cedula_reserva4 END, " +
                                            "horario_16 = CASE WHEN ? = '16:00 - 18:00' THEN 'Ocupado' ELSE horario_16 END, " +
                                            "cedula_reserva5 = CASE WHEN ? = '16:00 - 18:00' THEN ? ELSE cedula_reserva5 END, " +
                                            "horario_18 = CASE WHEN ? = '18:00 - 20:00' THEN 'Ocupado' ELSE horario_18 END, " +
                                            "cedula_reserva6 = CASE WHEN ? = '18:00 - 20:00' THEN ? ELSE cedula_reserva6 END " +
                                            "WHERE codigo = ?";

                                        try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {

                                            ps.setString(1, horarioSeleccionado);
                                            ps.setString(2, horarioSeleccionado);
                                            ps.setString(3, cedulaT.getText());

                                            ps.setString(4, horarioSeleccionado);
                                            ps.setString(5, horarioSeleccionado);
                                            ps.setString(6, cedulaT.getText());

                                            ps.setString(7, horarioSeleccionado);
                                            ps.setString(8, horarioSeleccionado);
                                            ps.setString(9, cedulaT.getText());

                                            ps.setString(10, horarioSeleccionado);
                                            ps.setString(11, horarioSeleccionado);
                                            ps.setString(12, cedulaT.getText());

                                            ps.setString(13, horarioSeleccionado);
                                            ps.setString(14, horarioSeleccionado);
                                            ps.setString(15, cedulaT.getText());

                                            ps.setString(16, horarioSeleccionado);
                                            ps.setString(17, horarioSeleccionado);
                                            ps.setString(18, cedulaT.getText());


                                            ps.setString(19, codigoT.getText());

                                            int guardado= ps.executeUpdate();

                                            if (guardado > 0) {
                                                JOptionPane.showMessageDialog(null, "Reserva guardada con éxito.");
                                                String query1 = "select * from canchas where codigo= '"+codigoT.getText()+"'";
                                                try (PreparedStatement statement1 = connection.prepareStatement(query1)) {
                                                    ResultSet resultSet1 = statement1.executeQuery();
                                                    if (resultSet1.next()) {
                                                        String[] columnas = {"Horario", "Estado"};
                                                        Object[][] horarios = {
                                                                {"8:00 - 10:00", resultSet1.getString("horario_8")},
                                                                {"10:00 - 12:00", resultSet1.getString("horario_10")},
                                                                {"12:00 - 14:00", resultSet1.getString("horario_12")},
                                                                {"14:00 - 16:00", resultSet1.getString("horario_14")},
                                                                {"16:00 - 18:00", resultSet1.getString("horario_16")},
                                                                {"18:00 - 20:00", resultSet1.getString("horario_18")}
                                                        };

                                                        DefaultTableModel modelo = new DefaultTableModel(horarios, columnas);
                                                        table1.setModel(modelo);
                                                    }
                                                } catch (SQLException ex) {
                                                    JOptionPane.showMessageDialog(null, "Error al actualizar la tabla: " + ex.getMessage());
                                                }

                                            } else {
                                                JOptionPane.showMessageDialog(null, "No se pudo guardar la reserva.");
                                            }
                                        } catch (SQLException e1) {
                                            JOptionPane.showMessageDialog(null, "Error al guardar la reserva: " + e1.getMessage());
                                        }

                                    }else{
                                        JOptionPane.showMessageDialog(null, "Cedula Invalida");
                                    }
                                }

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró la cancha.");
                    }

                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Error al verificar el estado de la cancha: ");
                }
            }
        });
        cancelarReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int fila = table1.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione un horario para cancelar la reserva.");
                    return;
                }

                String estadoHorario = table1.getValueAt(fila, 1).toString();
                if ("Disponible".equals(estadoHorario)) {
                    JOptionPane.showMessageDialog(null, "El horario seleccionado no está reservado.");
                    return;
                }

                String horarioSeleccionado = table1.getValueAt(fila, 0).toString();

                try (Connection connection = DriverManager.getConnection(url, usuario, contraseña)) {
                    String query = "SELECT * FROM canchas WHERE codigo = '" + codigoT.getText() + "'";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    if (resultSet.next()) {
                        String estado = resultSet.getString("estado");
                        if ("Inactivo".equals(estado)) {
                            JOptionPane.showMessageDialog(null, "La cancha está inactiva. No se puede cancelar la reserva.");
                        } else if ("Activo".equals(estado)) {
                            String updateQuery = "UPDATE canchas SET " +
                                    "horario_8 = CASE WHEN ? = '8:00 - 10:00' THEN 'Disponible' ELSE horario_8 END, " +
                                    "cedula_reserva1 = CASE WHEN ? = '8:00 - 10:00' THEN NULL ELSE cedula_reserva1 END, " +
                                    "horario_10 = CASE WHEN ? = '10:00 - 12:00' THEN 'Disponible' ELSE horario_10 END, " +
                                    "cedula_reserva2 = CASE WHEN ? = '10:00 - 12:00' THEN NULL ELSE cedula_reserva2 END, " +
                                    "horario_12 = CASE WHEN ? = '12:00 - 14:00' THEN 'Disponible' ELSE horario_12 END, " +
                                    "cedula_reserva3 = CASE WHEN ? = '12:00 - 14:00' THEN NULL ELSE cedula_reserva3 END, " +
                                    "horario_14 = CASE WHEN ? = '14:00 - 16:00' THEN 'Disponible' ELSE horario_14 END, " +
                                    "cedula_reserva4 = CASE WHEN ? = '14:00 - 16:00' THEN NULL ELSE cedula_reserva4 END, " +
                                    "horario_16 = CASE WHEN ? = '16:00 - 18:00' THEN 'Disponible' ELSE horario_16 END, " +
                                    "cedula_reserva5 = CASE WHEN ? = '16:00 - 18:00' THEN NULL ELSE cedula_reserva5 END, " +
                                    "horario_18 = CASE WHEN ? = '18:00 - 20:00' THEN 'Disponible' ELSE horario_18 END, " +
                                    "cedula_reserva6 = CASE WHEN ? = '18:00 - 20:00' THEN NULL ELSE cedula_reserva6 END " +
                                    "WHERE codigo = ?";

                            try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {
                                ps.setString(1, horarioSeleccionado);
                                ps.setString(2, horarioSeleccionado);

                                ps.setString(3, horarioSeleccionado);
                                ps.setString(4, horarioSeleccionado);

                                ps.setString(5, horarioSeleccionado);
                                ps.setString(6, horarioSeleccionado);

                                ps.setString(7, horarioSeleccionado);
                                ps.setString(8, horarioSeleccionado);

                                ps.setString(9, horarioSeleccionado);
                                ps.setString(10, horarioSeleccionado);

                                ps.setString(11, horarioSeleccionado);
                                ps.setString(12, horarioSeleccionado);

                                ps.setString(13, codigoT.getText());

                                int rowsUpdated = ps.executeUpdate();
                                if (rowsUpdated > 0) {
                                    JOptionPane.showMessageDialog(null, "La reserva ha sido cancelada.");
                                    table1.setValueAt("Disponible", fila, 1);
                                } else {
                                    JOptionPane.showMessageDialog(null, "No se pudo cancelar la reserva.");
                                }
                            }
                        }
                    }

                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new InicioAdministracion(nombreAdmin).MainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/Imagenes/logo.jpg"));
                frame.setSize(400, 400);
                frame.setVisible(true);
                ((JFrame)SwingUtilities.getWindowAncestor(regresarButton)).dispose();
            }
        });
    }
    public boolean verificarCodigo(String codigo){
        String verficar = "^[0-9]{4}$";
        return codigo.matches(verficar);
    }

    public boolean verificarCedula(String codigo){
        String verficar = "^[0-9]{10}$";
        return codigo.matches(verficar);
    }
}
