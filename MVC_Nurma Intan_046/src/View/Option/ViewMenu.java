package View.Option;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ViewMenu extends JFrame {
    JLabel header = new JLabel("Silakan Pilih Daftar yang ingin Ditampilkan : ");
    JButton tombolDosen = new JButton("Daftar Dosen");
    JButton tombolMahasiswa = new JButton("Daftar Mahasiswa");

    public ViewMenu(){
        setTitle("View Menu");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(300, 200);
        setLocationRelativeTo(null);

        add(header);
        add(tombolDosen);
        add(tombolMahasiswa);

        header.setBounds(20, 10, 440, 20);
        tombolDosen.setBounds(65, 50, 150, 40);
        tombolMahasiswa.setBounds(65, 105, 150, 40);

        tombolDosen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Dosen.ViewData();
            }
        });

        tombolMahasiswa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new View.Mahasiswa.ViewData();
            }
        });
    }
}
