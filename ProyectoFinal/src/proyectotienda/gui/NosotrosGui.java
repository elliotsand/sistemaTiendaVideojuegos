package proyectotienda.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NosotrosGui extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JLabel lblTienda;
    private JLabel lblAutores;
    private JLabel Nombre_uno;
    private JButton btnCerrar;


    public NosotrosGui() {
        setTitle("Nosotros");
        setBounds(100, 100, 440, 355);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblTienda = new JLabel("VIDEO GAMES STORE STOP");
        lblTienda.setHorizontalAlignment(SwingConstants.CENTER);
        lblTienda.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblTienda.setBounds(30, 11, 350, 41);
        contentPane.add(lblTienda);

        ImageIcon imagen = new ImageIcon("ProyectoFinal/img/consolas.jpg");
        JLabel etiqueta2 = new JLabel();
        etiqueta2.setBounds(140, 60, 150, 150);
        etiqueta2.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(etiqueta2.getWidth(), etiqueta2.getHeight(), Image.SCALE_SMOOTH)));
        contentPane.add(etiqueta2);

        lblAutores = new JLabel("Autores");
        lblAutores.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblAutores.setHorizontalAlignment(SwingConstants.CENTER);
        lblAutores.setBounds(173, 220, 64, 14);
        contentPane.add(lblAutores);

        Nombre_uno = new JLabel("Ramiro Blas Galicia");
        Nombre_uno.setHorizontalAlignment(SwingConstants.CENTER);
        Nombre_uno.setBounds(135, 240, 146, 14);
        contentPane.add(Nombre_uno);

        Nombre_uno = new JLabel("Emilio Lopez Caceres");
        Nombre_uno.setHorizontalAlignment(SwingConstants.CENTER);
        Nombre_uno.setBounds(135, 265, 146, 14);
        contentPane.add(Nombre_uno);

        Nombre_uno = new JLabel("Andrea Altamirano Basurco");
        Nombre_uno.setHorizontalAlignment(SwingConstants.CENTER);
        Nombre_uno.setBounds(120, 290, 170, 14);
        contentPane.add(Nombre_uno);

        this.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        TiendaGui a = new TiendaGui();
        a.setVisible(true);
    }
}