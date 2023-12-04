
package UI.Form.CaiDat;

import Dao.MenuCategoriesDao;
import Dao.MenuItemsDao;
import Dao.TablesdDao;
import Entity.MenuCategories;
import Entity.MenuItems;
import Utils.fNum;
import java.awt.Component;
import java.awt.Image;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class QLMenu extends javax.swing.JPanel {

    public QLMenu() {
        initComponents();
        fillComboxBan();
        selectTuCombobox();
        // Thiết lập renderer cho cột "Ảnh"
        tblMenu.getColumnModel().getColumn(5).setCellRenderer(new ImageRenderer());
    
    }
    
     // custom TableCellRenderer để hiển thị hình ảnh trong cột "Ảnh"
    class ImageRenderer extends DefaultTableCellRenderer {
        JLabel lbl = new JLabel();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            lbl.setIcon((Icon) value); // Thiết lập icon cho JLabel
            lbl.setHorizontalAlignment(JLabel.CENTER); // Canh lề để hình ảnh nằm ở giữa ô
            return lbl;
        }
    }

    
    MenuCategoriesDao cDao = new MenuCategoriesDao();
            
    //Hàm fill lên combobox
    public void fillComboxBan(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbo.getModel();
        model.removeAllElements();
        MenuCategories e = new MenuCategories();
        e.setCategoryName("Tất cả");
        e.setID_Category(0);
        model.addElement(e);
       List<MenuCategories> l = cDao.selectAll();
       for (MenuCategories a : l){
           model.addElement(a); 
       }
    }
    
    //Hàm fill dữ liệu lên bảng bàn theo mã khu vực
    private MenuItemsDao mDao = new MenuItemsDao();
    public void fillTable(int ID_Area){
        DefaultTableModel model = (DefaultTableModel) tblMenu.getModel();
        model.setRowCount(0);
        
        List<MenuItems> l = mDao.selectCatory(ID_Area);
        if(l.isEmpty()) return;
        
        int i = 1;
        for(MenuItems e : l){
            MenuCategories a = cDao.selectById(e.getID_Category());
            String path = "src/IMG/Food/"+e.getPhoto();
            ImageIcon imageIcon = new ImageIcon(path); 
            Image image = imageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH); 
            ImageIcon scaledIcon = new ImageIcon(image); 
            model.addRow(new Object[]{
                i,
                e.getID_Item(), 
                e.getItemName(),
                a.getCategoryName(),
                fNum.parseString(e.getPrice())+"đ",
                scaledIcon, 
                (e.isAvailable() ? "Còn món" : "Hết món")      
            });
            i++;
        }
    }
    
     public void selectTuCombobox() {
        Object selectedObject = cbo.getSelectedItem();
       
        if (selectedObject instanceof MenuCategories) {
            MenuCategories selectedArea = (MenuCategories) selectedObject;
            fillTable(selectedArea.getID_Category());
        } else if (selectedObject instanceof String && selectedObject.equals("Tất cả")) {
            fillTable(0);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        search1 = new button.Search();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMenu = new UI.Compoment.CustomTable.Table();
        cbo = new javax.swing.JComboBox<>();

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton7.setText("+ Thêm món mới");
        jButton7.setPreferredSize(new java.awt.Dimension(75, 25));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(66, 125, 157));
        jPanel8.setForeground(new java.awt.Color(66, 125, 157));
        jPanel8.setPreferredSize(new java.awt.Dimension(707, 60));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Quản lí Menu");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        tblMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "ID", "Tên món", "Loại", "Giá", "Ảnh", "Trạng thái"
            }
        ));
        tblMenu.setRowHeight(80);
        tblMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMenuMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblMenu);
        if (tblMenu.getColumnModel().getColumnCount() > 0) {
            tblMenu.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        cbo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cboPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1238, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(search1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1238, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboPopupMenuWillBecomeInvisible
       selectTuCombobox();
    }//GEN-LAST:event_cboPopupMenuWillBecomeInvisible

    private void tblMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMenuMousePressed
      int id = (int) tblMenu.getValueAt(tblMenu.getSelectedRow(), 1);
      MenuItems e = mDao.selectById(id);
      ChiTietMenu f = new ChiTietMenu(new javax.swing.JFrame(), true, e);
      f.setModal(true);
      f.setVisible(true);
      selectTuCombobox();
    }//GEN-LAST:event_tblMenuMousePressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      MenuItems e = new MenuItems();
      ChiTietMenu f = new ChiTietMenu(new javax.swing.JFrame(), true, e);
      f.setModal(true);
      f.setVisible(true);
      selectTuCombobox();
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbo;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private button.Search search1;
    private UI.Compoment.CustomTable.Table tblMenu;
    // End of variables declaration//GEN-END:variables
}
