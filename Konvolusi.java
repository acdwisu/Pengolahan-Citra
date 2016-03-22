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
public class Konvolusi extends Proses {
    
    private ArrayList<ArrayList<int[]>> matriksCitraAsli;   // matriks dinamis untuk menampung warna2 dari citra
    
    public Konvolusi() {
    }
    
    @Override
    public void proses(BufferedImage imgAsli) {
        
        this.matriksCitraAsli = super.citraToMatriks(imgAsli);
        ArrayList<ArrayList<int[]>> matriksCitraManipulasi = super
            .copyMatriks(matriksCitraAsli, imgAsli.getHeight(), imgAsli.getWidth());

        for(short i=1; i<imgAsli.getHeight()-1; i++) {
            for(short j=1; j<imgAsli.getWidth()-1; j++) {
                // mengkonvolusi masing-masing warna dengan matriks mask
                int warna[] = {0,0,0};
                for(int x=i-1, a=0; x<=i+1; x++, a++) {
                    for(int y=j-1, b=0; y<=j+1; y++, b++) {
                        warna[Proses.Red] += GantiKernel.kernel[a][b] * matriksCitraAsli.get(x).get(y)[0];  // red
                        warna[Proses.Green] += GantiKernel.kernel[a][b] * matriksCitraAsli.get(x).get(y)[1];  // green
                        warna[Proses.Blue] += GantiKernel.kernel[a][b] * matriksCitraAsli.get(x).get(y)[2];  // blue
                    }
                }
                warna[Proses.Red] = (int) (warna[Proses.Red]);
                warna[Proses.Green] = (int) (warna[Proses.Green]);
                warna[Proses.Blue] = (int) (warna[Proses.Blue]);
                
                netralisirWarna(warna);
                matriksCitraManipulasi.get(i).set(j, warna);
            }
        }
        super.citra = super.matriksToCitra(matriksCitraManipulasi, imgAsli.getHeight(), imgAsli.getWidth());
        Citra.manipulated = true;
        
    }    
    
/*
    short[] warnaDikaliMask(ArrayList<ArrayList<short[]>> matriks, int baris, int kolom, short maskAsli[][]) {  //0:red - 1:green - 2:blue
        short hasil[] = {0,0,0};
        for(int i=baris-1, a=0; i<=baris+1; i++, a++) {
            for(int j=kolom-1, b=0; j<=kolom+1; j++, b++) {
                hasil[0] += maskAsli[a][b] * matriks.get(i).get(j)[0];
            }
        }
        
        return hasil;
    }
*/
     
    void netralisirWarna(int warna[]) {
        for(short i=0; i<3; i++) {
            if(warna[i] < 0) warna[i] = 0;
            else if(warna[i] > 225) warna[i] = 255;
        }
    }
    
    @Override
    public void bersihkan() {
        super.bersihkan();
        this.matriksCitraAsli.clear();
        System.out.println("matriks dibersihkan, size = " +matriksCitraAsli.size());
    }
}
