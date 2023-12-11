package HangHoa;

import Dao.ProductsDao;
import Entity.Products;
import Utils.Auth;
import Utils.msg;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class KiemHang extends javax.swing.JPanel {

    int row = 0;
    DefaultTableModel model;
    ProductsDao dao = new ProductsDao();
    Products products = new Products();
    private double soLuong = 0;

    public KiemHang() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        txtThucTe.setEditable(false);
        btnOk.setEnabled(false);
    }

    // xóa form
    public void clear() {
        txtMaHang.setText("");
        txtTenHang.setText("");
        txtThucTe.setText("");
    }
    
    //Chọn sp
    public void chonSanPham(String id){
        txtMaHang.setText(id);
        tim();
    }

    // tạo biến instance để lưu giá trị số lượng tạm thời
    public void TamThoi() {
        try {
            soLuong = Double.parseDouble(txtThucTe.getText().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            msg.Error("Vui lòng nhập mã số lượng là số.");
            return;
        }

        // Lấy mã và tên từ JTextField
        String maHang = txtMaHang.getText().trim();
        String tenHang = txtTenHang.getText().trim();

        // Tìm kiếm thông tin sản phẩm
        Products product = dao.SearchFirst(maHang, tenHang);
        
         boolean check = false;
        if (product != null) {

            for (int i = 0; i < table.getRowCount(); i++) {
                String ID = (String) table.getValueAt(i, 1);
                if (product.getID_product().equals(ID)) {
                    double sl_Old = (Double) table.getValueAt(i, 6);
                    soLuong = soLuong + sl_Old;
                    table.setValueAt(soLuong, i, 6);
                    check = true;
                    break;
                }
            }

            if(!check){
                // Tạo một mảng đối tượng để thêm vào bảng
                int SL;
                 
                    Object[] rowData = new Object[]{
                        model.getRowCount() + 1,
                        product.getID_product(),
                        product.getName(),
                        product.getUnit(),
                        product.getPrice(),
                        (Auth.isMG() ? product.getQuantity() : "?"),
                        soLuong, // Số lượng mới nhập
                        "Xóa"
                    };
                    // Thêm dòng mới vào bảng
                    model.addRow(rowData);
            }
            // Clear các JTextField
            txtMaHang.setText("");
            txtTenHang.setText("");
            txtThucTe.setText("");
        } else {
            msg.Warning("Không tìm thấy sản phẩm!");
        }
    }

    // Lưu
    public void Luu() {
        try {
            int rowCount = table.getRowCount();

            if (rowCount == 0) {
                msg.Info("Bảng không có dữ liệu để cập nhật.");
                return;
            }

            for (int i = 0; i < rowCount; i++) {
                String maHang = table.getValueAt(i, 1).toString();
                double soLuong;

                try {
                    soLuong = Double.parseDouble(table.getValueAt(i, 6).toString());
                } catch (NumberFormatException ex) {
                    System.out.println(ex.getMessage());
                    msg.Error("Dòng " + (i + 1) + ": Vui lòng nhập số lượng là một số.");
                    return;
                }

                Products product = dao.selectById(maHang);

                if (product != null) {
                    // Cập nhật số lượng trong bảng Products
                    product.setQuantity(soLuong);
                    dao.update(product);
                }
            }
            msg.Info("Cập nhật số lượng thành công!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            msg.Error("Lỗi khi cập nhật số lượng.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtThucTe = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenHang = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnTim = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new UI.Compoment.CustomTable.Table();
        btnLuu = new button.Button();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thông tin kiểm hàng");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kiểm hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã hàng :");

        txtMaHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaHang.setForeground(new java.awt.Color(255, 153, 0));
        txtMaHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHangActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Thực tế : ");

        txtThucTe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên hàng : ");

        txtTenHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnOk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnTim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("Chọn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenHang, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(txtThucTe)
                    .addComponent(txtMaHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTim, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                    .addComponent(btnOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtThucTe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOk))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hàng", "Tên hàng", "Đơn vị", "Giá", "Số lượng", "Thực tế", "Thao tác"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
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

        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/download.png"))); // NOI18N
        btnLuu.setText("Lưu");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(255, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(268, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(57, 57, 57)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        // TODO add your handling code here:
        TamThoi();
    }//GEN-LAST:event_btnOkActionPerformed

    public void tim(){
         // TODO add your handling code here:
        String maHang = txtMaHang.getText().trim();
        String tenHang = txtTenHang.getText().trim();

        // Kiểm tra xem có cung cấp mã sản phẩm hoặc tên sản phẩm không
        if (!maHang.isEmpty() || !tenHang.isEmpty()) {
            //đây là sản phẩm trả về làm gì với nó thì làm
            Products p = dao.SearchFirst(maHang, tenHang);
            if (p != null) {
                txtThucTe.setEditable(true);
                btnOk.setEnabled(true);
                txtMaHang.setText(p.getID_product());
                txtTenHang.setText(p.getName());
            } else {
                msg.Warning("Không tìm thấy mặt hàng!");
            }
        } else {
            msg.Warning("Vui lòng nhập mã hàng hoặc tên hàng!");
        }
    }
    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        tim();
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLuuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMousePressed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            Luu();
            model.setRowCount(0);
            clear();
            txtThucTe.setEditable(false);
            btnOk.setEnabled(false);
        }
    }//GEN-LAST:event_btnLuuMousePressed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed

    private void txtMaHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHangActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DsHangHoa f = new DsHangHoa(new JFrame(), true);
        f.setModal(true);
        f.form = this;
        f.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed
       int selectRow = table.getSelectedRow();
        int selectCol = table.getSelectedColumn();
        if(selectCol == 7){
            if(msg.Yes_no("Xóa?")){
                model.removeRow(selectRow);
            }
        }
    }//GEN-LAST:event_tableMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnLuu;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private UI.Compoment.CustomTable.Table table;
    private javax.swing.JTextField txtMaHang;
    private javax.swing.JTextField txtTenHang;
    private javax.swing.JTextField txtThucTe;
    // End of variables declaration//GEN-END:variables
}
