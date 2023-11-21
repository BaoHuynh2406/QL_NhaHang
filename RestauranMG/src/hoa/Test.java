
package hoa;

import button.EventCallBack;
import button.EventTextField;
import java.awt.Color;

public class Test extends javax.swing.JFrame {
    public Test() {
        initComponents();
        init();
        loading();
        getContentPane().setBackground(new Color(240, 240, 240));
    }
    private void init(){
        table.fixTable(jScrollPane1);
        table.addRow(new Object[]{"1", "NV001", "NgoNgocNhan", "Admin", "0987126543", "Chi tiết"});
        table.addRow(new Object[]{"2", "NV001", "NgoNgocNhan", "Admin", "0987126543", "Chi tiết"});
        table.addRow(new Object[]{"3", "NV001", "NgoNgocNhan", "Admin", "0987126543", "Chi tiết"});
        table.addRow(new Object[]{"4", "NV001", "NgoNgocNhan", "Admin", "0987126543", "Chi tiết"});
        table.addRow(new Object[]{"5", "NV001", "NgoNgocNhan", "Admin", "0987126543", "Chi tiết"});
        table.addRow(new Object[]{"6", "NV001", "NgoNgocNhan", "Admin", "0987126543", "Chi tiết"});
        table.addRow(new Object[]{"7", "NV001", "NgoNgocNhan", "Admin", "0987126543", "Chi tiết"});
        table.addRow(new Object[]{"8", "NV001", "NgoNgocNhan", "Admin", "0987126543", "Chi tiết"});
        table.addRow(new Object[]{"9", "NV001", "NgoNgocNhan", "Admin", "0987126543", "Chi tiết"});
        table.addRow(new Object[]{"10", "NV001", "NgoNgocNhan", "Admin", "0987126543", "Chi tiết"});
        table.addRow(new Object[]{"11", "NV001", "NgoNgocNhan", "Admin", "0987126543", "Chi tiết"});
        
    }
    private void loading(){
        txt.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                //  Test
                try {
                    for (int i = 1; i <= 100; i++) {
                        lbl.setText("Loading " + i);
                        Thread.sleep(10);
                    }
                    call.done();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button1 = new button.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new hoa.Table();
        txt = new button.Search();
        lbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add-user.png"))); // NOI18N
        button1.setText("Thêm nhân viên");
        button1.setBorderColor(new java.awt.Color(255, 255, 255));
        button1.setMaximumSize(new java.awt.Dimension(150, 40));
        button1.setMinimumSize(new java.awt.Dimension(150, 40));
        button1.setRadius(20);
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nhân viên", "Tên nhân viên", "Chức vụ", "SĐT", "Thông tin"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        txt.setMinimumSize(new java.awt.Dimension(150, 40));

        lbl.setFont(new java.awt.Font("Segoe UI", 2, 8)); // NOI18N
        lbl.setForeground(new java.awt.Color(165, 112, 112));
        lbl.setText("Loading ... ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(206, 151, 32));
        jLabel1.setText("Danh Sách Nhân Viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                        .addGap(26, 26, 26)
                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE))
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addComponent(lbl)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl;
    private hoa.Table table;
    private button.Search txt;
    // End of variables declaration//GEN-END:variables
}
