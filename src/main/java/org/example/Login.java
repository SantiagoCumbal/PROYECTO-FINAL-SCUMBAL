package org.example;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Login {
    public JPanel MainPanel;
    private JComboBox comboBox1;
    private JTextField usuarioT;
    private JPasswordField contraseñaT;
    private JButton iniciarSesionButton;
    private JButton registrarmeButton;
    private JLabel Error;
    String url="jdbc:mysql://localhost:3306/Futbolito";
    String usuario = "root";
    String contraseña= "12345";
    Administradores admin = new Administradores();
    Encargados enc = new Encargados();
    Jugadores jug= new Jugadores();

    public Login() {
        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try(Connection connection= DriverManager.getConnection(url,usuario,contraseña)){
                    System.out.println("CONEXION EXITOSA");
                    String opcion=comboBox1.getSelectedItem().toString();
                    if(opcion.equals("Administrador")){
                        System.out.println(comboBox1.getSelectedItem());
                        String query="select * from administrador";
                        Statement statement=connection.createStatement();
                        ResultSet resultSet=statement.executeQuery(query);
                        admin.setCorreo(usuarioT.getText());
                        admin.setCedula(contraseñaT.getText());

                        while(resultSet.next()){
                            if(admin.getCorreo().equals(resultSet.getString("correo")) && admin.getCedula().equals(resultSet.getString("cedula"))){
                                System.out.println("Ingreso exitoso");
                                JFrame frame = new JFrame();
                                frame.setContentPane(new form1().MainPanel);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(300,300);
                                frame.setVisible(true);
                                ((JFrame)SwingUtilities.getWindowAncestor(iniciarSesionButton)).dispose();
                            }else{
                                Error.setText("ERROR DE INGRESO");
                                usuarioT.setText("");
                                contraseñaT.setText("");
                            }
                        }
                    } else if (opcion.equals("Jugador")) {
                        System.out.println(comboBox1.getSelectedItem());
                        String query="select * from jugadores";
                        Statement statement=connection.createStatement();
                        ResultSet resultSet=statement.executeQuery(query);
                        jug.setCorreo(usuarioT.getText());
                        jug.setCedula(contraseñaT.getText());

                        while(resultSet.next()){
                            if(jug.getCorreo().equals(resultSet.getString("correo")) && jug.getCedula().equals(resultSet.getString("cedula"))){
                                System.out.println("Ingreso exitoso");
                                JFrame frame = new JFrame();
                                frame.setContentPane(new form1().MainPanel);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                        System.out.println(comboBox1.getSelectedItem());
                        String query="select * from encargado";
                        Statement statement=connection.createStatement();
                        ResultSet resultSet=statement.executeQuery(query);
                        enc.setCorreo(usuarioT.getText());
                        enc.setCedula(contraseñaT.getText());

                        while(resultSet.next()){
                            if(enc.getCorreo().equals(resultSet.getString("correo")) && enc.getCedula().equals(resultSet.getString("cedula"))){
                                System.out.println("Ingreso exitoso");
                                JFrame frame = new JFrame();
                                frame.setContentPane(new form1().MainPanel);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}
