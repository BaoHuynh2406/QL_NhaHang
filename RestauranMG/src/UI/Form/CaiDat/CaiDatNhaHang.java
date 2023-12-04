
package UI.Form.CaiDat;

import Dao.AreasDao;
import Dao.TablesdDao;
import Entity.Areas;
import Entity.Tables;
import Utils.msg;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;


public class CaiDatNhaHang extends javax.swing.JPanel {

   
    public CaiDatNhaHang() {
        initComponents();
        
    }

    
   
    
    
 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        qLKhuVuc1 = new UI.Form.CaiDat.QLKhuVuc();
        qLMenu1 = new UI.Form.CaiDat.QLMenu();
        qLKhoHang1 = new UI.Form.CaiDat.QLKhoHang();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTabbedPane1.addTab("QL Khu vực - Bàn          ", qLKhuVuc1);
        jTabbedPane1.addTab("QL Menu         ", qLMenu1);
        jTabbedPane1.addTab("QL Hàng hóa", qLKhoHang1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    private UI.Form.CaiDat.QLKhoHang qLKhoHang1;
    private UI.Form.CaiDat.QLKhuVuc qLKhuVuc1;
    private UI.Form.CaiDat.QLMenu qLMenu1;
    // End of variables declaration//GEN-END:variables
}
