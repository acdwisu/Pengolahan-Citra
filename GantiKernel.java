/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Odz
 */
public class GantiKernel extends javax.swing.JFrame {

    /**
     * Creates new form Konvolusi_setMask
     */
    
    // berisi kernel-kernel citra yang dapat digunakan
    private final double kernelBlur[][] = {
     //<editor-fold defaultstate="collapsed" desc=" kernel citra "> 
        {0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}
    };
    
    private final double kernelBottomSobel[][] = {
        {-1, -2, -1},
        {0, 0, 0},
        {1, 2, 1}
    };
    
    private final double kernelBoxBlur[][] = {
        {0.111, 0.111, 0.111},
        {0.111, 0.111, 0.111},
        {0.111, 0.111, 0.111}
    };
    
    private final double kernelEdgeDetection1[][] = {
        {1, 0, -1},
        {0, 0, 0},
        {-1, 0, 1}
    };
    
    private final double kernelEdgeDetection2[][] = {
        {0, 1, 0},
        {1, -4, 1},
        {0, 1, 0}
    };
    
    private final double kernelEdgeDetection3[][] = {
        {-1, -1, -1},
        {-1, 8, -1},
        {-1, -1, 1}
    };
    
    private final double kernelEmboss[][] = {
        {-2, -1, 0},
        {-1, 1, 1},
        {0, 1, 2}
    };
    
    private final double kernelLeftSobel[][] = {
        {1, 0, -1},
        {2, 0, -2},
        {1, 0, -1}
    };
    
    private final double kernelOutline[][] = {
        {-1, -1, -1},
        {-1, 8, -1},
        {-1, -1, -1}
    };
    
    private final double kernelRightSobel[][] = {
        {-1, 0, 1},
        {-2, 0, 2},
        {-1, 0, 1}
    };
    
    private final double kernelSharpen[][] = {
        {0, -1, 0},
        {-1, 5, -1},
        {0, -1, 0}
    };
    
    private final double kernelTopSobel[][] = {
        {1, 2, 1},
        {0, 0, 0},
        {-1, -2, -1}
    };
    //</editor-fold>
    
    // inisilisasi kernel default yang dipakai di tableKernel
    public static double kernel[][] = {
    //<editor-fold>
        {0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}
    };
    //</editor-fold>
    
    // sebagai penyimpan sementara(pengingat) matriks kernel karena
    // secara tidak dikendedaki akan berubaha nilainya pada X_X, -a warn dude
    private static double tempKernel[][] = new double[3][3];        
            
    // mensinkronkan matriks kernel yang dipilih melalui combobox(cmbBoxKernel)
    // defaultnya adalah kernel blur
    // mereturn indeks dari kernel yang dipilih melalui comBoxKernel
    private short syncKernel() {                                                
        short pilihanKernel = (short) this.cmbBoxKernels.getSelectedIndex();
        switch(pilihanKernel) {
            case 0:
                copyKernel(this.kernelBlur,kernel);
                break;
            case 1:
                copyKernel(this.kernelBottomSobel,kernel);
                break;
            case 2:
                copyKernel(this.kernelBoxBlur,kernel);
                break;
            case 3:
                copyKernel(this.kernelEdgeDetection1,kernel);
                break;               
            case 4:
                copyKernel(this.kernelEdgeDetection2,kernel);
                break;
            case 5:
                copyKernel(this.kernelEdgeDetection3,kernel);
                break;
            case 6:
                copyKernel(this.kernelEmboss,kernel);
                break;
            case 7:
                copyKernel(this.kernelLeftSobel,kernel);
                break;
            case 8:
                copyKernel(this.kernelOutline,kernel);
                break;
            case 9:
                copyKernel(this.kernelRightSobel,kernel);
                break;
            case 10:
                copyKernel(this.kernelSharpen,kernel);
                break;
            case 11:
                copyKernel(this.kernelTopSobel,kernel);
                break;
        }
        return pilihanKernel;
    }       
                                                   
    // mensinkronkan tableKernel terhadap matriks kernel 
    private void syncTable() {                              
        for(short i=0; i<3; i++) {
            for(short j=0; j<3; j++) {
                this.tableKernel.setValueAt(kernel[i][j], i, j);
            }    
        }       
    } 
   
    // method untuk mengcopy matriks kernel(3x3)
    private void copyKernel(double copyfrom[][], double copyto[][]) {
        for(short i=0; i<3; i++) {
            for(short j=0; j<3; j++) {
                copyto[i][j] = copyfrom[i][j];
            }
        }
    }
    
    private static short sekarangTerpilih=0;
    private static boolean isCustom=false;
    
    public GantiKernel() {
        this.initComponents();          // menginisialisasi komponen2
        
        short temp = sekarangTerpilih;              // menyimpan sementara nilai dari sementaraTerpilih karena secara tidak
                                                    // dikendedaki akan berubaha nilainya pada tanda X_X, -a warn dude
        this.copyKernel(kernel, tempKernel);
        
        String[] OptionKernelCitraName = {                          // list kernel2 yang akan di tambahkan ke
        "Blur", "Bottom Sobel", "Box Blur", "Edge Detection #1",    // combobox(cmbBoxKernel)
         "Edge Detection #2", "Edge Detection #3", "Emboss",
         "Left Sobel", "Outline", "Right Sobel", "Sharpen", "Top Sobel", /*"custom"*/
        };
        
        //menambahkan list kernel2 di atas ke comboBoxKernel
        for (String item : OptionKernelCitraName) {
            this.cmbBoxKernels.addItem(item);           // X_X
        }
        
        // mengassignkan kembali nilai sekarangTerpilih
        sekarangTerpilih = temp;
        
        //cek nilai matriks kernel
        //<editor-fold>
//        System.out.println("dari awal konstruktor");
//        for(short i=0; i<3; i++) {
//            for(short j=0; j<3; j++) {
//                System.out.print(kernel[i][j] + "   ");
//            } System.out.println("");
//        } </editor-fold>
        
        //cek nilai dari sekarangTerpilih
//        System.out.println(sekarangTerpilih);
        
        //menset cmbBoxKernel
        this.cmbBoxKernels.setSelectedIndex(sekarangTerpilih);
        
        if(!isCustom){
            sekarangTerpilih = this.syncKernel();
            this.cekBoxCustom.setSelected(false);
            this.tableKernel.setEnabled(false);
        } else {
            this.cekBoxCustom.setSelected(true);
            this.tableKernel.setEnabled(true);
        }
        
        this.copyKernel(tempKernel, kernel);
        //test
        //<editor-fold>
//        System.out.println("dari akhir konstruktor");
//        for(short i=0; i<3; i++) {
//            for(short j=0; j<3; j++) {
//                System.out.print(kernel[i][j] + "   ");
//            } System.out.println("");
//        } </editor-fold>
        
        this.syncTable(); 
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelMatriksMask = new javax.swing.JLabel();
        tableKernel = new javax.swing.JTable();
        btnOk = new javax.swing.JButton();
        cmbBoxKernels = new javax.swing.JComboBox();
        cekBoxCustom = new javax.swing.JCheckBox();

        setTitle("Ganti Matriks Mask");
        setAlwaysOnTop(true);

        labelMatriksMask.setText("Matriks Kernel :");

        tableKernel.setBackground(new java.awt.Color(249, 249, 249));
        tableKernel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        tableKernel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tableKernel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableKernel.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tableKernel.setAutoscrolls(false);
        tableKernel.setEnabled(false);
        tableKernel.setRowHeight(22);
        tableKernel.getTableHeader().setReorderingAllowed(false);

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        cmbBoxKernels.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBoxKernelsActionPerformed(evt);
            }
        });

        cekBoxCustom.setText("Custom");
        cekBoxCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekBoxCustomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelMatriksMask, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableKernel, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addComponent(cmbBoxKernels, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cekBoxCustom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(btnOk)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMatriksMask)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableKernel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(cmbBoxKernels, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk)
                    .addComponent(cekBoxCustom))
                .addContainerGap())
        );

        tableKernel.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tableKernel.getColumnModel().getColumnCount() > 0) {
            tableKernel.getColumnModel().getColumn(0).setResizable(false);
            tableKernel.getColumnModel().getColumn(1).setResizable(false);
            tableKernel.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:  
        
        // kondisi khusus jika custom diaktifkan
        // mengcopy nilai dari tabel ke kernel
        if(this.cekBoxCustom.isSelected()) {
            for(short i=0; i<3; i++) {
                for(short j=0; j<3; j++) {
                    kernel[i][j] = Double.parseDouble(this.tableKernel.getValueAt(i, j).toString()) ;
                }
            }
        }
        this.dispose();
    }//GEN-LAST:event_btnOkActionPerformed

    private void cmbBoxKernelsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBoxKernelsActionPerformed
        // TODO add your handling code here:
        
        //cek nilai dari kernel 
        //<editor-fold>
//        System.out.println("dari awal combo box");
//        for(short i=0; i<3; i++) {
//            for(short j=0; j<3; j++) {
//                System.out.print(kernel[i][j] + "   ");
//            } System.out.println("");
//        }//</editor-fold>
        
        sekarangTerpilih = this.syncKernel();
        this.syncTable();
        
        //cek nilai dari kernel 
        //<editor-fold>
//        System.out.println("dari akhir combo box");
//        for(short i=0; i<3; i++) {
//            for(short j=0; j<3; j++) {
//                System.out.print(kernel[i][j] + "   ");
//            } System.out.println("");
//        }//</editor-fold>
        
//        System.out.println(sekarangTerpilih);
    }//GEN-LAST:event_cmbBoxKernelsActionPerformed

    private void cekBoxCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekBoxCustomActionPerformed
        // TODO add your handling code here:
        if(this.cekBoxCustom.isSelected()) {
            this.tableKernel.setEnabled(true);
            isCustom = true;
        } else {
            this.tableKernel.setEnabled(false);
            isCustom = false;
        }    
    }//GEN-LAST:event_cekBoxCustomActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JCheckBox cekBoxCustom;
    private javax.swing.JComboBox cmbBoxKernels;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelMatriksMask;
    private javax.swing.JTable tableKernel;
    // End of variables declaration//GEN-END:variables
}
