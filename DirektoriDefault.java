
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Odz
 */
public class DirektoriDefault {
    private JFileChooser pilihDirektori;
    
    public DirektoriDefault() {
        pilihDirektori = new JFileChooser("D:\\");
        pilihDirektori.setDialogTitle("Pilih Direktori Default");
        pilihDirektori.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        pilihDirektori.setAcceptAllFileFilterUsed(false);

        if (pilihDirektori.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            OlahFile.direktoriDefault = pilihDirektori.getSelectedFile().toString();
            System.out.println(OlahFile.direktoriDefault);
        } 
    }
}
