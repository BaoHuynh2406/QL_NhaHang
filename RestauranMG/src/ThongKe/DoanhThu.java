
package ThongKe;

import Dao.procDao;
import Utils.XDate;
import Utils.fNum;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.formula.ptg.TblPtg;


public class DoanhThu extends javax.swing.JPanel {

    
    public DoanhThu() {
        initComponents();
        Date now = new Date();
        txtDate.setDate(now);
        fillTable();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbb = new javax.swing.JComboBox<>();
        search = new button.Search();
        lblTong = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new UI.Compoment.CustomTable.Table();
        txtDate = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(255, 255, 255));

        cbb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cả ngày", "Ca sáng", "Ca tối" }));
        cbb.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbbPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        lblTong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTong.setForeground(new java.awt.Color(255, 102, 102));
        lblTong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTong.setText("Doanh Thu: ");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số bàn", "Mã hóa đơn", "Ngày", "Giờ ra", "Tổng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        txtDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDatePropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(lblTong, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTong)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbbPopupMenuWillBecomeInvisible
        fillTable();
    }//GEN-LAST:event_cbbPopupMenuWillBecomeInvisible

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed
        int row = table.getSelectedRow();
        int id_hd = (int) table.getValueAt(row, 1);
        ChiTietHoaDon f = new ChiTietHoaDon(new JFrame(), true, id_hd);  
        f.time = (java.sql.Time) table.getValueAt(row, 3);
        f.fillSc(id_hd);
        f.setVisible(true);
    }//GEN-LAST:event_tableMousePressed

    private void txtDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDatePropertyChange
        fillTable();
    }//GEN-LAST:event_txtDatePropertyChange

    
    public void fillTable(){
        int total = 0;
        procDao dao = new procDao();
        List<Object[]> l;
        if(cbb.getSelectedItem().equals("Cả ngày")){
            l = dao.GetInvoiceDetailsByDateTimeRange(txtDate.getDate(), 0, 24);    
        }else if(cbb.getSelectedItem().equals("Ca sáng")){
            l = dao.GetInvoiceDetailsByDateTimeRange(txtDate.getDate(), 6, 17); 
        }else{
            l = dao.GetInvoiceDetailsByDateTimeRange(txtDate.getDate(), 17, 24); 
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(Object[] row : l){
            model.addRow(new Object[]{
            row[0],
            row[1],
            XDate.convertToDMY((Date) row[2]),
            row[3],
            fNum.parseString((int)row[4]) + "đ"
            });
            total += (int) row[4];
        }
        
        lblTong.setText("Doanh thu: "+ fNum.parseString(total)+"đ");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbb;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTong;
    private button.Search search;
    private UI.Compoment.CustomTable.Table table;
    private com.toedter.calendar.JDateChooser txtDate;
    // End of variables declaration//GEN-END:variables
}
