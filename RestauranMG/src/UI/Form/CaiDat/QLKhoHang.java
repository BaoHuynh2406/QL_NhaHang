
package UI.Form.CaiDat;

import Dao.ProductCategoriesDao;
import Dao.ProductsDao;
import Entity.ProductCategories;
import Entity.Products;
import Utils.msg;
import button.EventCallBack;
import button.EventTextField;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

 class QLKhoHang extends javax.swing.JPanel {

  int row = 0;
    DefaultTableModel model;
    ProductsDao dao = new ProductsDao();
    Products products = new Products();
    ProductCategoriesDao pdao = new ProductCategoriesDao();
    public QLKhoHang() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        loading();
        fillTable(dao.selectAll());
        fillComboBox();
    }

   
     // loading
    private void loading() {
        search.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    // Giả sử bạn muốn delay 2 giây để simulating loading
                    Thread.sleep(500);

                    // Thực hiện tìm kiếm theo tên hoặc mã
                    
                    fillTable(dao.Search(search.getText()));

                    call.done();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread interrupted: " + e);
                } catch (Exception e) {
                    System.err.println("Unexpected exception: " + e);
                }
            }

            @Override
            public void onCancel() {
                // Không có hành động cần thiết cho onCancel
            }
        });
    }
    
    public void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
        model.removeAllElements();

        model.addElement("Tất cả");

        // Thêm các sản phẩm từ cơ sở dữ liệu vào JComboBox
        pdao.selectAll().forEach(model::addElement);
    }

   

    public void ChonComboBox() {
        Object selectedObject = cbb.getSelectedItem();

        if ("Tất cả".equals(selectedObject)) {
            fillTable(dao.selectAll());
        } else {
            model.setRowCount(0);

            if (selectedObject instanceof ProductCategories) {
                ProductCategories selectedCategory = (ProductCategories) selectedObject;
                List<Products> products = dao.getProductsByCategory(selectedCategory.getCategoryName());
                fillTable(products);
                
            }
        }
    }

    // fill dữ liệu theo tên hoặc mã
     public void fillTable(List<Products> l) {
         model.setRowCount(0);
         try {
             int i = 1;
             for (Products p : l) {
                 ProductCategories e = new ProductCategoriesDao().selectById(p.ID_Catory);
                 Object[] row = {i++,
                     p.getID_product(),
                     e.getCategoryName(),
                     p.getName(),
                     p.getUnit(),
                     p.getPrice()};
                 model.addRow(row);
             }
         } catch (Exception e) {
             System.out.println(e.getMessage());
             msg.Error("Có lỗi trong quá trình truy xuất dữ liệu!");
         }
     }
    
    public void showFormChiTiet(Products p){
        ChiTietHangHoa f = new ChiTietHangHoa(new JFrame(), true, p);
        f.Parant = this;
        f.setVisible(true);
        fillTable(dao.selectAll());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new UI.Compoment.CustomTable.Table();
        cbb = new javax.swing.JComboBox<>();
        search = new button.Search();

        jPanel9.setBackground(new java.awt.Color(102, 102, 0));
        jPanel9.setForeground(new java.awt.Color(66, 125, 157));
        jPanel9.setPreferredSize(new java.awt.Dimension(707, 60));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Quản lí hàng hóa");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 1231, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton8.setText("+ Thêm mặt hàng mới");
        jButton8.setPreferredSize(new java.awt.Dimension(75, 25));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hàng", "Loại hàng", "Tên hàng", "Đơn vị", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        cbb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbItemStateChanged(evt);
            }
        });

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1231, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1219, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1231, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbItemStateChanged
        // TODO add your handling code here:
        ChonComboBox();
    }//GEN-LAST:event_cbbItemStateChanged

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        showFormChiTiet(new Products());
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed
        int row = table.getSelectedRow();
        String id = (String) table.getValueAt(row, 1);
        Products e = dao.selectById(id);
        showFormChiTiet(e);
    }//GEN-LAST:event_tableMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbb;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private button.Search search;
    private UI.Compoment.CustomTable.Table table;
    // End of variables declaration//GEN-END:variables
}
