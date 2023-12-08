
package UI.Form.CaiDat;

import Dao.MenuCategoriesDao;
import Entity.MenuCategories;
import Utils.msg;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ThemLoaiMon extends javax.swing.JDialog {

    DefaultTableModel model;
    public ThemLoaiMon(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        model = (DefaultTableModel) tbl.getModel();
        fill();
    }
    
    public void newRow(){
        btnNew.setEnabled(false);
        model.addRow(new Object[]{"","","Xóa"});
        tbl.changeSelection(tbl.getRowCount() - 1, 1, false, false);
        tbl.editCellAt(tbl.getRowCount()-1, 1);
        
    }
    
    
     MenuCategoriesDao mDao = new MenuCategoriesDao();
    public void fill(){
        
        model.setRowCount(0);
        List<MenuCategories> l = mDao.selectAll();
        for(MenuCategories e : l){
            model.addRow(new Object[]{
                e.getID_Category(),
                e.getCategoryName(),
                "Xóa"
            });
        }
    }
    
    public void Save(){
        for(int i = 0; i < tbl.getRowCount(); i++){
            MenuCategories m = new MenuCategories();
            try {
                m.setID_Category((int) tbl.getValueAt(i, 0));
            } catch (Exception e) {
                m.setID_Category(0);
            }
            
            m.setCategoryName((String) tbl.getValueAt(i, 1));
            
            if(m.getID_Category() != 0){
                mDao.update(m);
            }else{
                if(!m.getCategoryName().isBlank()){
                    mDao.insert(m);
                }
            }
            
            
        }
        msg.Info("Lưu Thành Công!");
        this.dispose();
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnLuu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new UI.Compoment.CustomTable.Table();
        btnNew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thêm Loại");
        setModal(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(0, 102, 51));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save.png"))); // NOI18N
        btnLuu.setText("   Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tên Loại", "Thao tác"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);
        if (tbl.getColumnModel().getColumnCount() > 0) {
            tbl.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        btnNew.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add-button.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        Save();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
       newRow();
    }//GEN-LAST:event_btnNewActionPerformed

    private void tblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMousePressed
       int selectRow = tbl.getSelectedRow();
        int selectCol = tbl.getSelectedColumn();
        if(selectCol == 2){
            if(msg.Yes_no("Xóa?")){ 
                try {
                    mDao.delete((int) tbl.getValueAt(selectRow, 0));
                    msg.Info("Xóa thành công!");
                     model.removeRow(selectRow);
                    this.dispose();
                } catch (Exception e) {
                    msg.Info("Bạn không thể xóa khi có item liên kết với loại này!");
                }
                
            }
        }
    }//GEN-LAST:event_tblMousePressed

   
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
            java.util.logging.Logger.getLogger(ThemLoaiMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemLoaiMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemLoaiMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemLoaiMon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemLoaiMon dialog = new ThemLoaiMon(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnNew;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private UI.Compoment.CustomTable.Table tbl;
    // End of variables declaration//GEN-END:variables
}
