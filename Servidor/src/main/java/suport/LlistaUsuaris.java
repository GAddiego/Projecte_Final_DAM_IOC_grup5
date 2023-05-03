package suport;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import objectes.Usuari;

public class LlistaUsuaris extends JFrame {
    private JPanel contentPane;
    private JList<Usuari> listUsuaris;

    public LlistaUsuaris(List<Usuari> usuaris) {
        setTitle("Llista d'usuaris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        listUsuaris = new JList<>(usuaris.toArray(new Usuari[0]));
        JScrollPane scrollPane = new JScrollPane(listUsuaris);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        listUsuaris.setCellRenderer(new UsuariCellRenderer());
    }

    private class UsuariCellRenderer extends JLabel implements ListCellRenderer<Usuari> {
        public UsuariCellRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        public Component getListCellRendererComponent(JList<? extends Usuari> list, Usuari usuari, int index, boolean isSelected, boolean cellHasFocus) {
            setText(usuari.getNom() + " " + usuari.getPrimerCognom() + " " + usuari.getSegonCognom());

            BufferedImage img = null;
            try {
                img = ImageIO.read(new ByteArrayInputStream(usuari.getImageData()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            setIcon(new ImageIcon(img));

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }
}

