
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Odz
 */

public class FilterFile extends javax.swing.filechooser.FileFilter {

    private String ekstensi;
    private String deskripsi;
    public FilterFile(String ekstensi, String deskripsi) {
        this.deskripsi = deskripsi;
        this.ekstensi = ekstensi;
    }
    
    @Override
    public boolean accept(File pathname) {
        if(pathname.isDirectory()) {
            return true;
        }
        return pathname.getName().endsWith(ekstensi);
    }
    
    @Override
    public String getDescription() {
        return deskripsi + String.format(" (*%s)", ekstensi);
    }
}
