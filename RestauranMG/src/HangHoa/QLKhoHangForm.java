
package HangHoa;


public class QLKhoHangForm extends javax.swing.JPanel {

   
    public QLKhoHangForm() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        khoHang1 = new HangHoa.KhoHang();
        kiem1 = new HangHoa.KiemHang();
        nhap1 = new HangHoa.NhapHang();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTabbedPane1.addTab("  QL Kho Hàng  ", khoHang1);
        jTabbedPane1.addTab("   Kiễm hàng  ", kiem1);
        jTabbedPane1.addTab("  Nhập hàng  ", nhap1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private HangHoa.KhoHang khoHang1;
    private HangHoa.KiemHang kiem1;
    private HangHoa.NhapHang nhap1;
    // End of variables declaration//GEN-END:variables
}
