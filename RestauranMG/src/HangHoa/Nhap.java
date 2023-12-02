package HangHoa;

import Dao.ProductsDao;
import Dao.PurchaseOrdersDao;
import Entity.Products;
import Entity.PurchaseOrders;
import Utils.Auth;
import Utils.msg;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;

public class Nhap extends javax.swing.JPanel {

    int row = 0;
    DefaultTableModel model;
    ProductsDao dao = new ProductsDao();

    PurchaseOrders po = new PurchaseOrders();
    private int soLuong = 0;
    private boolean hasSearched = false;

    public Nhap() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        txtsoLuong.setEditable(false);
        btnOK.setEnabled(false);
        date();
        txtmaNV.setEditable(false);
    }

    // hiện thị ngày tháng năm hiện tại
    private void date() {
        // Lấy ngày tháng năm hiện tại
        LocalDate currentDate = LocalDate.now();

        // Định dạng ngày tháng năm
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Chuyển định dạng và đưa vào ô txtDate
        txtDate.setText(currentDate.format(dateFormatter));
        txtDate.setEditable(false);
    }

    // fill dữ liệu theo tên hoặc mã
    public void fillTable(String key) {
    model.setRowCount(0);
    try {
        System.out.println("Key: " + key);
        int i = 1;
        for (Products p : dao.Search(key)) {
            model.addRow(new Object[]{i++,
                p.getID_product(),
                p.getName(),
                p.getUnit(),
                p.getPrice(),
                p.getQuantity()});
        }

        // Lấy dữ liệu từ hàng đầu tiên (nếu có) và đặt vào ô mã và tên
        if (model.getRowCount() > 0) {
            txtmaHang.setText(model.getValueAt(0, 1).toString());
            txttenHang.setText(model.getValueAt(0, 2).toString());
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
        msg.Error("Có lỗi trong quá trình truy xuất dữ liệu!");
    }
}


    // nhấn tìm thì hiện thị tên với mã
    public void fill(String key) {
        model.setRowCount(0);
        try {
            System.out.println("key : " + key);
            if (model.getRowCount() > 0) {
                txtmaHang.setText(model.getValueAt(0, 1).toString());
                txttenHang.setText(model.getValueAt(0, 2).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            msg.Error("Có lỗi trong quá trình truy xuất dữ liệu!");
        }
    }

    public void TamThoi() {
        try {
            soLuong = Integer.parseInt(txtsoLuong.getText().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            msg.Error("Vui lòng nhập mã số lượng là số nguyên !");
            return;
        }

        // Lấy hàng đang được chọn (nếu có)
        int selectRow = table.getSelectedRow();

        // Nếu có hàng được chọn, sử dụng nó. Nếu không, sử dụng hàng đầu tiên trong bảng.
        int rowToUse = (selectRow >= 0) ? selectRow : 0;

        String maHang = table.getValueAt(rowToUse, 1).toString();
        table.setValueAt(txtsoLuong.getText(), rowToUse, 6);
    }

    public void luu() {
        try {
            // Kiểm tra xem đã nhập đủ thông tin chưa
            String maNH = txtmaNH.getText().trim();
//        String maNV = Auth.user.getID_Employee()+"";
            String maNV = "1000000";

            if (maNH.isEmpty() || maNV.isEmpty()) {
                msg.Warning("Vui lòng nhập đủ thông tin!");
                return;
            }

            // Bước 1: Thêm thông tin mới vào bảng PurchaseOrders
            int idNhanVien = Integer.parseInt(maNV);
            PurchaseOrdersDao purchaseOrdersDao = new PurchaseOrdersDao();
            PurchaseOrders newPurchaseOrder = new PurchaseOrders();
            newPurchaseOrder.setID_Employee(idNhanVien);
            purchaseOrdersDao.insert(newPurchaseOrder);

            // Bước 2: Cập nhật số lượng trong bảng Products
            for (int i = 0; i < model.getRowCount(); i++) {
                String maHang = model.getValueAt(i, 1).toString();
                int soLuongNhap;
                try {
                    soLuongNhap = Integer.parseInt(model.getValueAt(i, 5).toString());
                } catch (NumberFormatException e) {
                    msg.Error("Vui lòng nhập số lượng là số nguyên !");
                    return;
                }

                // Cập nhật số lượng trong bảng Products
                int updatedQuantity = dao.updateQuantity(maHang, soLuongNhap);
                if (updatedQuantity < 0) {
                    msg.Error("Có lỗi khi cập nhật số lượng trong bảng Products!");
                    return;
                }
            }

            msg.Info("Nhập hàng thành công!");
            // Sau khi thêm, bạn có thể làm một số công việc khác nếu cần.
        } catch (NumberFormatException e) {
            msg.Error("Lỗi định dạng số nguyên: " + e.getMessage());
        } catch (Exception e) {
            msg.Error("Lỗi xảy ra: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtmaNH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtmaNV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtmaHang = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttenHang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnTim = new javax.swing.JButton();
        txtsoLuong = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new UI.Compoment.CustomTable.Table();
        button1 = new button.Button();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(199, 161, 69));
        jLabel1.setText("Bảng nhập hàng");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã nhập hàng : ");

        txtmaNH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã nhân viên : ");

        txtmaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ngày nhập : ");

        txtDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtmaNH, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(txtmaNV)
                    .addComponent(txtDate))
                .addGap(30, 30, 30))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtmaNH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhập hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Mã hàng : ");

        txtmaHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tên hàng : ");

        txttenHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Số lượng nhập vào : ");

        btnTim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        txtsoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnOK.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOK.setText("OK");
        btnOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnOKMousePressed(evt);
            }
        });
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txttenHang)
                                .addGap(18, 18, 18)
                                .addComponent(btnTim))
                            .addComponent(txtmaHang)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtsoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOK, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtmaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTim)
                    .addComponent(txttenHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtsoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        table.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hàng", "Tên hàng", "Đơn vị", "Giá", "Số lượng", "Nhập vào"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/download.png"))); // NOI18N
        button1.setText("Lưu");
        button1.setBorderColor(new java.awt.Color(0, 51, 51));
        button1.setRadius(30);
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button1MousePressed(evt);
            }
        });
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jLabel1)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        String maHang = txtmaHang.getText().trim();
        String tenHang = txttenHang.getText().trim();

        // Kiểm tra xem có cung cấp mã sản phẩm hoặc tên sản phẩm không
        if (!maHang.isEmpty() || !tenHang.isEmpty()) {
            //đây là sản phẩm trả về làm gì với nó thì làm
            Products p = dao.SearchFirst(maHang, tenHang);
            if(p != null){
                txtsoLuong.setEditable(true);
                btnOK.setEnabled(true);
                txtmaHang.setText(p.getID_product());
                txttenHang.setText(p.getName());
            }else{
                msg.Warning("Không tìm thấy mặt hàng!");
            }
        } else {
            msg.Warning("Vui lòng nhập mã hàng hoặc tên hàng!");
        }
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnOKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOKMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOKMousePressed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        TamThoi();
    }//GEN-LAST:event_btnOKActionPerformed

    private void button1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MousePressed
        // TODO add your handling code here:
        luu();
    }//GEN-LAST:event_button1MousePressed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnTim;
    private button.Button button1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private UI.Compoment.CustomTable.Table table;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtmaHang;
    private javax.swing.JTextField txtmaNH;
    private javax.swing.JTextField txtmaNV;
    private javax.swing.JTextField txtsoLuong;
    private javax.swing.JTextField txttenHang;
    // End of variables declaration//GEN-END:variables
}
