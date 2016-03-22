/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Odz
 */

class HistogramProses extends Proses {
    
    private ArrayList<ArrayList<int[]>> matriksCitra;
    float[][] histWarna;
    float[] histGray;
    private boolean isGrayscaled;
    
    public HistogramProses(BufferedImage imgAsli, boolean isGrayscaled) {
        this.isGrayscaled = isGrayscaled;
        if(this.isGrayscaled) histGray = new float[256];
        else histWarna = new float[3][256];
        proses(imgAsli);
    }
    
    @Override
    public final void proses(BufferedImage imgAsli) {
        citra = imgAsli;
        this.matriksCitra = super.citraToMatriks(imgAsli);  //konversi citra ke bentuk matriks
        int n = imgAsli.getHeight() * imgAsli.getHeight();
        
        //inisialisasi isi array
        for(int i=0; i<256; i++) {
            if(isGrayscaled) histGray[i] = 0;
            else histWarna[Proses.Red][i] = histWarna[Proses.Green][i] = histWarna[Proses.Blue][i] = 0;
        }
        
        //mendapatkan intensitas masing2 warna
        for(int i=0; i<imgAsli.getHeight(); i++) {
            for(int j=0; j<imgAsli.getWidth(); j++) {
                if(isGrayscaled) {
                    histGray[matriksCitra.get(i).get(j)[Proses.Blue]]++; // apabila citra grayscale, nilai r/g/b sama saja mungkin
                } else {
                    histWarna[Proses.Red][matriksCitra.get(i).get(j)[Proses.Red]]++;
                    histWarna[Proses.Green][matriksCitra.get(i).get(j)[Proses.Green]]++;
                    histWarna[Proses.Blue][matriksCitra.get(i).get(j)[Proses.Blue]]++;
                }
            }
        }
        
        //normalisasi histogram
        for(int i=0; i<256; i++) {
            if(isGrayscaled) {
                histGray[i] /= (float) n;
            } else {
                histWarna[Proses.Red][i] /= (float) n;
                histWarna[Proses.Green][i] /= (float) n;
                histWarna[Proses.Blue][i] /= (float) n;
            }
        }
    }

    @Override
    public void bersihkan() {
        super.bersihkan();
        this.matriksCitra.clear();
    }
}

//---------------------------------------------------------------------------------------
// class untuk menampung histogram dan combobox
public class Histogram extends JFrame {
    Histogram(BufferedImage citra, boolean isGrayscaled) {
        HistogramPanel panel = new HistogramPanel(citra, isGrayscaled);
        JComboBox warna = new JComboBox();

        setTitle("Histogram");
        setSize(300,250);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        if(isGrayscaled) {
            warna.addItem("Gray");
            warna.setEnabled(false);
        } else {
            warna.addItem("Red");
            warna.addItem("Green");
            warna.addItem("Blue");
            warna.setEnabled(true);
        }
        
        add(warna, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        
        warna.addActionListener((ActionEvent e) -> {
            int x = warna.getSelectedIndex();
            panel.tampilHistogram(x);
        });
    }
}

//---------------------------------------------------------------------------------------
//class untuk panel yang menampung histogram
class HistogramPanel extends JPanel {   
    HistogramProses proses;
    int warnaIndex;
    Color warna;
    private boolean isGrayscaled;
    
    HistogramPanel(BufferedImage citra, boolean isGrayscaled) {
        this.isGrayscaled = isGrayscaled;
        proses = new HistogramProses(citra, this.isGrayscaled);
    }
    
    void tampilHistogram(int warna) {
        this.warnaIndex = warna;
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        final int tinggi = getHeight();
        final int lebar = getWidth();
        final int x=20;                     // posisi x bar pertama
        float maksGray=0;                   // intensitas warna abu maksimal
        float[] maksWarna = {0,0,0};        // intensitas warna r/g/b maksimal
        
        System.out.println(isGrayscaled);
        
        //cari intensitas warna tertinggi
        for(int i=0; i<256; i++) {
            if(isGrayscaled) {
                if(proses.histGray[i] > maksGray) maksGray = proses.histGray[i];
            }
            else {
                if(proses.histWarna[Proses.Red][i] > maksWarna[Proses.Red]) 
                    maksWarna[Proses.Red] = proses.histWarna[Proses.Red][i];
                if(proses.histWarna[Proses.Green][i] > maksWarna[Proses.Green]) 
                    maksWarna[Proses.Green] = proses.histWarna[Proses.Green][i];
                if(proses.histWarna[Proses.Blue][i] > maksWarna[Proses.Blue]) 
                    maksWarna[Proses.Blue] = proses.histWarna[Proses.Blue][i];
            }
        }
        
        //menggambar garis bawah sebagai tanda
        g.setColor(Color.black);
        g.fillRect(x-10, tinggi-10, lebar-20, 3);
        
        //menggambar bar masing-masing warna
        if(isGrayscaled) {
            warna = Color.GRAY;
            g.setColor(warna);
            for(int i=0; i<256; i++) {
                int tinggiBar = (int)((proses.histGray[i] / maksGray)* tinggi-15);
                if(tinggiBar<=0) tinggiBar=1;
                g.drawRect(x+i, tinggi-10, 1, -tinggiBar);
//                g.drawLine(x+i, tinggi-11, x+i, tinggi-11-tinggiBar);   
            }
        } else {
            switch(warnaIndex) {
                case 0: warna=Color.RED;break;
                case 1: warna=Color.GREEN;break;
                case 2: warna=Color.BLUE;break;
            }
            g.setColor(warna);
            for(int i=0; i<256; i++) {
                int tinggiBar = (int)((proses.histWarna[warnaIndex][i] / maksWarna[warnaIndex])* tinggi-15);
                if(tinggiBar<=0) {
//                    System.out.println("intensitas "+i+' '+proses.histWarna[warnaIndex][i]);
//                    System.out.println("maks "+maksWarna[warnaIndex]);
//                    System.out.println("tinggi "+tinggiBar);
                    tinggiBar=1;
                }
                g.drawRect(x+i, tinggi-10, 1, -tinggiBar);
//                g.drawLine(x+i, tinggi-11, x+i, tinggi-11-tinggiBar);  
            }
        }
        proses.bersihkan();
    }
}