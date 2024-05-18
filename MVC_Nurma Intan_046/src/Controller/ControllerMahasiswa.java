package Controller;

import Model.Mahasiswa.*;
import View.Mahasiswa.*;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerMahasiswa {

    ViewData halamanTable;
    InputData halamanInput;
    EditData halamanEdit;

    InterfaceDAOMahasiswa daoMahasiswa;

    List<ModelMahasiswa> daftarMahasiswa;

    public ControllerMahasiswa(ViewData halamanTable) {
        this.halamanTable = halamanTable;
        this.daoMahasiswa = new DAOMahasiswa();
    }
    
    public ControllerMahasiswa(InputData halamanInput) {
        this.halamanInput = halamanInput;
        this.daoMahasiswa = new DAOMahasiswa();
    }
    
    public ControllerMahasiswa(EditData halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoMahasiswa = new DAOMahasiswa();
    }

    public void showAllMahasiswa() {
 
        daftarMahasiswa = daoMahasiswa.getAll();

        ModelTable table = new ModelTable(daftarMahasiswa);

        halamanTable.getTableMahasiswa().setModel(table);
    }

    public void insertMahasiswa() {
        try {

            ModelMahasiswa mahasiswaBaru = new ModelMahasiswa();

            String nama = halamanInput.getInputNama();
            String nim = halamanInput.getInputNIM();

            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM gabole kosong!");
            }

            mahasiswaBaru.setNama(nama);
            mahasiswaBaru.setNim(nim);

            daoMahasiswa.insert(mahasiswaBaru);

            JOptionPane.showMessageDialog(null, "Data mahasiswanya udah ditambahin.");

            halamanInput.dispose();
            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editMahasiswa(int id) {
        try {
            ModelMahasiswa ubahMhs = new ModelMahasiswa();

            String nama = halamanEdit.getInputNama();
            String nim = halamanEdit.getInputNIM();

            if ("".equals(nama) || "".equals(nim)) {
                throw new Exception("Nama atau NIM gabole kosong!");
            }

            ubahMhs.setId(id);
            ubahMhs.setNama(nama);
            ubahMhs.setNim(nim);

            daoMahasiswa.update(ubahMhs);

            JOptionPane.showMessageDialog(null, "Data mahasiswanya udah diubah.");

            halamanEdit.dispose();
            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteMahasiswa(Integer baris) {
        Integer id = (int) halamanTable.getTableMahasiswa().getValueAt(baris, 0);
        String nama = halamanTable.getTableMahasiswa().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Mahasiswa",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoMahasiswa.delete(id);

            JOptionPane.showMessageDialog(null, "Oke Udah Dihapus.");

            showAllMahasiswa();
        }
    }
}
