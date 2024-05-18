package Controller;

import Model.Dosen.*;
import View.Dosen.*;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerDosen {

    ViewData halamanTable;
    InputData halamanInput;
    EditData halamanEdit;

    InterfaceDAODosen daoDosen;

    List<ModelDosen> daftarDosen;

    public ControllerDosen(ViewData halamanTable) {
        this.halamanTable = halamanTable;
        this.daoDosen= new DAODosen();
    }
    
    public ControllerDosen(InputData halamanInput) {
        this.halamanInput = halamanInput;
        this.daoDosen= new DAODosen();
    }
    
    public ControllerDosen(EditData halamanEdit) {
        this.halamanEdit = halamanEdit;
        this.daoDosen= new DAODosen();
    }

    public void showAllDosen() {
        daftarDosen= daoDosen.getAll();
        ModelTable table = new ModelTable(daftarDosen);
        halamanTable.getTableDosen().setModel(table);
    }

    public void insertDosen() {
        try {
            ModelDosen dosenBaru = new ModelDosen();
           
            String nama = halamanInput.getInputNama();
            String nidn = halamanInput.getInputNidn();

            if ("".equals(nama) || "".equals(nidn)) {
                throw new Exception("Nama atau NIDN gabole kosong!");
            }

            dosenBaru.setNama(nama);
            dosenBaru.setNidn(nidn);

            daoDosen.insert(dosenBaru);
            JOptionPane.showMessageDialog(null, "Yey dosen baru berhasil ditambahkan !");

            halamanInput.dispose();
            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editDosen(int id) {
        try {
            ModelDosen gantiDosen = new ModelDosen();
            String nama = halamanEdit.getInputNama();
            String nidn = halamanEdit.getInputNidn();

            if ("".equals(nama) || "".equals(nidn)) {
                throw new Exception("Nama atau NIDN gabole kosong!");
            }

            gantiDosen.setId(id);
            gantiDosen.setNama(nama);
            gantiDosen.setNidn(nidn);

            daoDosen.update(gantiDosen);

            JOptionPane.showMessageDialog(null, "Data dosennya udah diubah.");

            halamanEdit.dispose();
            new ViewData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void deleteDosen(Integer baris) {
        Integer id = (int) halamanTable.getTableDosen().getValueAt(baris, 0);
        String nama = halamanTable.getTableDosen().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Dosen",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoDosen.delete(id);

            JOptionPane.showMessageDialog(null, "Oke Udah Dihapus.");

            showAllDosen();
        }
    }
}
