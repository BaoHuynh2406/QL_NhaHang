
package UI.Form;

import Dao.AreasDao;
import Dao.InvoicesDao;
import Dao.OrdersDao;
import Dao.TablesdDao;
import Dao.procDao;
import Entity.Areas;
import Entity.Invoices;
import Entity.Orders;
import Entity.Tables;
import UI.Compoment.HoaDonItem;
import UI.Main;
import UI.Model.Model_Mon_Da_Goi;
import Utils.Auth;
import Utils.fNum;
import Utils.msg;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.geom.Area;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;


public class ThanhToanForm extends javax.swing.JPanel {

    int ID_Order = -1;
    private Main main;
    public ThanhToanForm(Orders donHang, String tenBan, Main main) {
        this.main = main;
        this.ID_Order = donHang.getID_Order();
        initComponents();
        fillSc();
        txtSoKhach.setText("Số khách: "+donHang.getNumberOfGuests());
        txtTenBan.setText(tenBan);
        taoHoaDon();
    }
    
    
    
    
    int total = 0;
    public void fillSc(){
        procDao dao = new procDao();
        List<Object[]> l = dao.getDonHang(ID_Order);
        pn.removeAll();
        int width = l.size() * 50 + 40;
        pn.setPreferredSize(new Dimension(370, width));
        
        for(Object[] ob : l){
            Model_Mon_Da_Goi item = new Model_Mon_Da_Goi();
            item.setId((int) ob[0]);
            item.setName((String) ob[1]);
            item.setPrice((int) ob[3]);
            item.setSl((int) ob[2]);
            total += (int) ob[4];
            pn.add(new HoaDonItem(item));
        }
        txtTotal.setText(fNum.parseString(total)+"đ");
        
                
    }

    int ID_Invoice = -1;
    //Tạo hóa đơn
    private void taoHoaDon(){
        //Kiễm tra nếu đã có háo đơn thì không tạo
        InvoicesDao d = new InvoicesDao();
        Invoices e = new Invoices();
        ID_Invoice = d.getID(ID_Order);
        if(ID_Invoice != -1){
            return;
        }
        
        
        e.setID_Employee(Auth.user.getID_Employee());
        e.setID_Order(ID_Order);
        e.setPaid(false);
        e.setID_Tax(1);
        e.setID_Method(1);
        e.setTaxAmount(0);
        e.setTotalAmount(total);
        d.insert(e);
        ID_Invoice = d.getNewID();
    }
    ThanhToanThanhCong p = new ThanhToanThanhCong(main);
    //Hàm cập nhật đơn hàng thành trạng thái đã thanh toán
    public void chuyenTrangThaiDonHangThanhDaThanhToan(int ID_Order){
        //Kiễm tra xem Hóa đơn thanh toán thành công chưa
        InvoicesDao d = new InvoicesDao();
        if(d.getID(ID_Order) != -1){
            return;
        }
        OrdersDao od = new OrdersDao();
        od.updateStatus(ID_Order, true);
        this.removeAll();
        
        this.setLayout(new BorderLayout());
        
        setHoaDon(ID_Order);
        this.add(p, BorderLayout.CENTER);
        this.revalidate();
    }
    
    public void setHoaDon(int ID_Invoice){
        Invoices invoice = new InvoicesDao().selectById(ID_Invoice);
        Orders order = new OrdersDao().selectById(ID_Order);
        Tables tables = new TablesdDao().selectById(order.getID_Table());
        Areas area = new AreasDao().selectById(tables.getID_Area());
        
        
        p.area = area;
        p.invoice = invoice;
        p.order = order;
        p.tables = tables;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pn = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnTienMat = new javax.swing.JButton();
        txtTotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenBan = new javax.swing.JLabel();
        txtSoKhach = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(255, 251, 225));
        jPanel1.setAutoscrolls(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Xác Nhận Hóa Đơn");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pn.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(pn);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Chuyển khoản");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnTienMat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTienMat.setText("Tiền mặt");
        btnTienMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienMatActionPerformed(evt);
            }
        });

        txtTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(255, 0, 0));
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtTotal.setText("100.000đ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("Tổng thanh toán:");

        txtTenBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenBan.setText("Bàn: A02");

        txtSoKhach.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoKhach.setText("Số khách: 5");

        jPanel2.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Tên món");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Giá");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Số lượng");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Tổng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addGap(131, 131, 131)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(116, 116, 116)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 60, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTenBan))
                        .addContainerGap(60, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(txtTenBan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSoKhach)
                .addGap(54, 54, 54)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal)
                    .addComponent(jLabel3))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTienMat, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(180, 180, 180))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ThanhToanQR f = new ThanhToanQR(new javax.swing.JFrame(), true);
        f.tongTien = total;
        f.ID_Invoice = ID_Invoice;
        f.CreateQR();
        f.setVisible(true);
        chuyenTrangThaiDonHangThanhDaThanhToan(ID_Order);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnTienMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienMatActionPerformed
        ThanhToanTienMat f = new ThanhToanTienMat(new javax.swing.JFrame(), true);
        f.tongTien = total;
        f.ID_Invoice = ID_Invoice;
        f.capNhatTienThua();
        f.setVisible(true);
        chuyenTrangThaiDonHangThanhDaThanhToan(ID_Order);
    }//GEN-LAST:event_btnTienMatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTienMat;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn;
    private javax.swing.JLabel txtSoKhach;
    private javax.swing.JLabel txtTenBan;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
