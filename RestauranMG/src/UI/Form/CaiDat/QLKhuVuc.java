
package UI.Form.CaiDat;

import Dao.AreasDao;
import Dao.TablesdDao;
import Entity.Areas;
import Entity.Tables;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;


public class QLKhuVuc extends javax.swing.JPanel {


    public QLKhuVuc() {
        initComponents();
        fillAllKhuVuc();
        fillComboxBan();
        fillBangBan(-1);
    }

     //Hàm fill lên bảng khu vực
    AreasDao areaDao = new AreasDao();
    public void fillAllKhuVuc(){
        List<Areas> l = areaDao.selectAll();
        if(l.isEmpty()) return;
        DefaultTableModel model = (DefaultTableModel) tblKhuVuc.getModel();
        model.setRowCount(0);
        int i = 1;
        for(Areas e : l){
            model.addRow(new Object[]{i, e.getID_Area(),
                e.getAreaName(), 
                areaDao.getTableCount(e.getID_Area())            
            });
            i++;
        }
        
    }
    
    
       //
    //----------------FORM QL Bàn --------------------------------------
    //
    
    
    //Hàm fill lên combobox
    public void fillComboxBan(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboBan.getModel();
        model.removeAllElements();
        Areas e = new Areas();
        e.setAreaName("Tất cả");
        e.setID_Area(-1);
        model.addElement(e);
       List<Areas> l = areaDao.selectAll();
       for (Areas a : l){
           model.addElement(a);
           
       }
    }
    
    public void selectBanTuCombobox() {
        Object selectedObject = cboBan.getSelectedItem();
       
        if (selectedObject instanceof Areas) {
            Areas selectedArea = (Areas) selectedObject;
            fillBangBan(selectedArea.getID_Area());
        } else if (selectedObject instanceof String && selectedObject.equals("Tất cả")) {
            fillBangBan(-1);
        }
    }
    
    //Hàm fill dữ liệu lên bảng bàn theo mã khu vực
    private TablesdDao tableDao = new TablesdDao();
    public void fillBangBan(int ID_Area){
        DefaultTableModel model = (DefaultTableModel) tblBan.getModel();
        model.setRowCount(0);
        
        List<Tables> l = tableDao.selectByArea(ID_Area);
        if(l.isEmpty()) return;
        
        int i = 1;
        for(Tables e : l){
            Areas a = areaDao.selectById(e.getID_Area());
            model.addRow(new Object[]{i,
                e.getID_Table(), 
                e.getTableName(),
                a.getAreaName()           
            });
            i++;
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblKhuVuc = new UI.Compoment.CustomTable.Table();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        cboBan = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblBan = new UI.Compoment.CustomTable.Table();
        jLabel3 = new javax.swing.JLabel();

        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(66, 125, 157));
        jPanel6.setForeground(new java.awt.Color(66, 125, 157));
        jPanel6.setPreferredSize(new java.awt.Dimension(707, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quản lí khu vực");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setText("+ Thêm khu vực");
        jButton5.setPreferredSize(new java.awt.Dimension(75, 25));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        tblKhuVuc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã khu vực", "Tên khu vực", "Tổng số bàn"
            }
        ));
        tblKhuVuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblKhuVucMousePressed(evt);
            }
        });
        jScrollPane5.setViewportView(tblKhuVuc);
        if (tblKhuVuc.getColumnModel().getColumnCount() > 0) {
            tblKhuVuc.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1238, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1232, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Khu vực          ", jPanel4);

        jPanel7.setBackground(new java.awt.Color(153, 153, 0));
        jPanel7.setForeground(new java.awt.Color(66, 125, 157));
        jPanel7.setPreferredSize(new java.awt.Dimension(707, 60));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Quản lí Bàn");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setText("+ Thêm bàn mới");
        jButton6.setPreferredSize(new java.awt.Dimension(75, 25));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        cboBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboBan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboBanItemStateChanged(evt);
            }
        });
        cboBan.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
                cboBanPopupMenuCanceled(evt);
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cboBanPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        tblBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã bàn", "Tên bàn", "Khu vực"
            }
        ));
        tblBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBanMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblBan);
        if (tblBan.getColumnModel().getColumnCount() > 0) {
            tblBan.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Khu vực:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1238, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboBan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboBan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane2.addTab("Bàn", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane2)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboBanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboBanItemStateChanged

    }//GEN-LAST:event_cboBanItemStateChanged

    private void cboBanPopupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboBanPopupMenuCanceled

    }//GEN-LAST:event_cboBanPopupMenuCanceled

    private void cboBanPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboBanPopupMenuWillBecomeInvisible
        selectBanTuCombobox();
    }//GEN-LAST:event_cboBanPopupMenuWillBecomeInvisible

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Areas a = new Areas();
        ChitietKhuVuc f = new ChitietKhuVuc(new javax.swing.JFrame(), true, a);
        f.setModal(true);
        f.setVisible(true);
        fillAllKhuVuc();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tblKhuVucMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuVucMousePressed
       int idArea = (int) tblKhuVuc.getValueAt(tblKhuVuc.getSelectedRow(), 1);
       Areas a = areaDao.selectById(idArea);
        ChitietKhuVuc f = new ChitietKhuVuc(new javax.swing.JFrame(), true, a);
        f.setModal(true);
        f.setVisible(true);
        fillAllKhuVuc();
    }//GEN-LAST:event_tblKhuVucMousePressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       Tables t = new Tables();
        ChiTietBan f = new ChiTietBan(new javax.swing.JFrame(), true, t);
        f.setModal(true);
        f.setVisible(true);
        fillBangBan(-1);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tblBanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBanMousePressed
      int idTable = (int) tblBan.getValueAt(tblBan.getSelectedRow(), 1);
       Tables tb = tableDao.selectById(idTable);
       ChiTietBan f = new ChiTietBan(new javax.swing.JFrame(), true, tb);
        f.setModal(true);
        f.setVisible(true);
        fillBangBan(-1);
    }//GEN-LAST:event_tblBanMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboBan;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane2;
    private UI.Compoment.CustomTable.Table tblBan;
    private UI.Compoment.CustomTable.Table tblKhuVuc;
    // End of variables declaration//GEN-END:variables
}
