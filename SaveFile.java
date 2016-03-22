/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Odz
 */
public class SaveFile extends OlahFile {

    public SaveFile() {
        // tidak bisa benar-benar menyimpan dalam format gif
        pilihFile.setDialogTitle("Save File");
    }
    
    public void save(java.awt.image.BufferedImage gbrGray, String direktori) {
        pilihFile.setCurrentDirectory(new java.io.File(direktori));
        if(pilihFile.showSaveDialog(null) == javax.swing.JFileChooser.APPROVE_OPTION) {
            gambar = pilihFile.getSelectedFile();
            System.out.println(gambar);
            if(gambar.getPath().endsWith(".png") || gambar.getPath().endsWith(".PNG"))
                renderCitra(gbrGray, "png");
            else if(gambar.getPath().endsWith(".jpg") || gambar.getPath().endsWith(".JPG"))
                renderCitra(gbrGray, "jpg");
            else if(gambar.getPath().endsWith(".bmp") || gambar.getPath().endsWith(".BMP"))
                renderCitra(gbrGray, "bmp");
            else {
                gambar=null;
                javax.swing.JOptionPane.showMessageDialog(pilihFile, "inputkan ekstensi");
                save(gbrGray, pilihFile.getSelectedFile().getPath());
            }
        }
    }
    
    void renderCitra(java.awt.image.BufferedImage citra, String ekstensi) {
        System.out.println(ekstensi);
        try {
            javax.imageio.ImageIO.write(citra, ekstensi, gambar);
        } catch(Exception err) {
            err.printStackTrace();
        }
    }
    
    public String getPath() {
        return gambar.getPath();
    }
    
    public void setPath(java.io.File path) {
        this.gambar = path;
    }
    
}
