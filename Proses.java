import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Odz
 */
public abstract class Proses {
    
    protected BufferedImage citra;
    protected static int Red=0;
    protected static int Green=1;
    protected static int Blue=2;
    protected static int Gray=3;
    
    protected Proses() {  
        Citra.manipulated = false;
        citra = null;        
    }
    
    //konversi citra ke matriks 2 dimensi
    protected ArrayList<ArrayList<int[]>> citraToMatriks(BufferedImage imgAsli) {
        ArrayList<ArrayList<int[]>> matriks = new ArrayList();
        
//        System.out.println("warna asli");
        
        for(short i=0; i<imgAsli.getHeight(); i++) {   
            ArrayList<int[]> nilaiWarna = new ArrayList();
            for(short j=0; j<imgAsli.getWidth(); j++) {
                java.awt.Color warnaAsli = new java.awt.Color(imgAsli.getRGB(j, i));
                                
//                System.out.printf("%d,%d rgb = %d\n", i, j, imgAsli.getRGB(j, i));
//                System.out.printf("%d,%d     = %d | %d | %d\n", i, j, warnaAsli.getRed(), warnaAsli.getGreen(), warnaAsli.getBlue());
                
                
                int color[] = {warnaAsli.getRed(), warnaAsli.getGreen(), warnaAsli.getBlue()};
                nilaiWarna.add(color);  
            }
            matriks.add(nilaiWarna);
        }
        return matriks;
    }
    
    //konversi matriks ke citra
    protected BufferedImage matriksToCitra(ArrayList<ArrayList<int[]>> matriks, int height, int width) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
//        System.out.println("warna olahan");    
        
        for(short i=0; i<height; i++) {
            for(short j=0; j<width; j++) {
                int color[] = matriks.get(i).get(j);
                int warna = (color[Proses.Red] << 16) + (color[Proses.Green] << 8) + color[Proses.Blue];
                
//                System.out.printf("%d,%d rgb = %d\n", i, j, warna);
//                System.out.printf("%d,%d     = %d | %d | %d\n", i, j, color[0], color[1], color[2]);
                
                img.setRGB(j, i, warna);
            }
        }
        
        return img;
    }
    
    //untuk mengcopy matriks dalam dalam bentuk di bawah
    protected ArrayList<ArrayList<int[]>> copyMatriks(ArrayList<ArrayList<int[]>> ori, int height, int width) {
        ArrayList<ArrayList<int[]>> copian = new ArrayList();
        for(short i=0; i<height; i++) {
            ArrayList<int[]> copianPart = new ArrayList();
            for(short j=0; j<width; j++) {
                copianPart.add(ori.get(i).get(j));
            }
            copian.add(copianPart);
        }
        return copian;
    }
    
    public abstract void proses(BufferedImage imgAsli); 
    
    public BufferedImage getCitra() {
        return citra;
    }
    
    public void bersihkan() {
        citra = null;
    }
}
