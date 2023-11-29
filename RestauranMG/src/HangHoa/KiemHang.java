package HangHoa;

import Dao.ProductsDao;
import Entity.Products;
import Utils.msg;
import javax.swing.table.DefaultTableModel;

public class KiemHang extends javax.swing.JFrame {

    int row = 0;
    DefaultTableModel model;
    ProductsDao dao = new ProductsDao();
    Products products = new Products();
    private int soLuong = 0;

    public KiemHang() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
    }

    // fill dữ liệu theo tên hoặc mã
    public void fillTable(String key) {
        model.setRowCount(0);
        try {
            int i = 1;
            for (Products p : dao.Search(key)) {
                Object[] row = {i++,
                    p.getID_product(),
                    p.getName(),
                    p.getUnit(),
                    p.getQuantity(),
                    p.getPrice()};
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            msg.Error("Có lỗi trong quá trình truy xuất dữ liệu!");
        }
    }

    // xóa form
    public void clear() {
        txtMaHang.setText("");
        txtTenHang.setText("");
        txtThucTe.setText("");
    }

    // tạo biến instance để lưu giá trị số lượng tạm thời
    public void TamThoi() {
        try {
            soLuong = Integer.parseInt(txtThucTe.getText().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            msg.Error("Vui lòng nhập mã số lượng là số nguyên !");
            return;
        }

        int selectRow = table.getSelectedRow();
        if (selectRow >= 0) {
            String maHang = table.getValueAt(selectRow, 1).toString();
            table.setValueAt(soLuong, selectRow, 4);
            Products product = dao.selectById(maHang);

            if (product != null) {
                // Cập nhật số lượng tạm thời trong bảng Products
                product.setQuantity(soLuong);
            }
        } else {
            msg.Error("Vui lòng chọn một hàng từ bảng trước khi nhập số lượng");
        }
    }

    // Lưu
    public void Luu() {
        try {
            for (int i = 0; i < table.getRowCount(); i++) {
                String maHang = table.getValueAt(i, 1).toString();
                int soLuong = Integer.parseInt(table.getValueAt(i, 4).toString());
                Products product = dao.selectById(maHang);

                if (product != null) {
                    // Cập nhật số lượng trong bảng Products
                    product.setQuantity(soLuong);
                    dao.update(product);
                }
            }
            msg.Info("Cập nhật số lượng thành công !");
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
            msg.Error("Vui lòng nhập số lượng là một số nguyên.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            msg.Error("Lỗi");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new UI.Compoment.CustomTable.Table();
        jLabel1 = new javax.swing.JLabel();
        btnLuu = new button.Button();
        jLabel2 = new javax.swing.JLabel();
        txtMaHang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenHang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        btnOk = new javax.swing.JButton();
        txtThucTe = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ma Hang", "Ten Hang", "Don Vi", "So Luong"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Thông tin kiểm hàng");

        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/download.png"))); // NOI18N
        btnLuu.setText("Luu");
        btnLuu.setBorderColor(new java.awt.Color(0, 0, 0));
        btnLuu.setRadius(30);
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLuuMousePressed(evt);
            }
        });
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã hàng :");

        jLabel3.setText("Tên hàng : ");

        jLabel4.setText("Thực tế : ");

        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtThucTe, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(29, 29, 29)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(48, 48, 48)
                                            .addComponent(btnTim))
                                        .addComponent(btnOk)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addComponent(jLabel1)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(btnOk)
                    .addComponent(txtThucTe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        String maHang = txtMaHang.getText().trim();
        String tenHang = txtTenHang.getText().trim();
        fillTable(maHang.isEmpty() ? tenHang : maHang);
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        TamThoi();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnLuuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMousePressed
        // TODO add your handling code here:
        Luu();
    }//GEN-LAST:event_btnLuuMousePressed

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
            java.util.logging.Logger.getLogger(KiemHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KiemHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KiemHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KiemHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KiemHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnLuu;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnTim;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private UI.Compoment.CustomTable.Table table;
    private javax.swing.JTextField txtMaHang;
    private javax.swing.JTextField txtTenHang;
    private javax.swing.JTextField txtThucTe;
    // End of variables declaration//GEN-END:variables
}
