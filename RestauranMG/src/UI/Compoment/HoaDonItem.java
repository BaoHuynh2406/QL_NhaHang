
package UI.Compoment;

import UI.Model.Model_Mon_Da_Goi;
import Utils.fNum;


public class HoaDonItem extends javax.swing.JPanel {

      public HoaDonItem(Model_Mon_Da_Goi data) {
        initComponents();
        Name.setText(data.getName());
        Price.setText(fNum.parseString(data.getPrice())+"đ");
        SL.setText(data.getSl()+"");
        Total.setText(fNum.parseString(data.getTotal())+"đ");
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Name = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        SL = new javax.swing.JLabel();
        Price = new javax.swing.JLabel();

        setOpaque(false);

        Name.setBackground(new java.awt.Color(255, 255, 255));
        Name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Name.setText("Gà hấp xả");

        Total.setBackground(new java.awt.Color(255, 255, 255));
        Total.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Total.setForeground(new java.awt.Color(255, 102, 0));
        Total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Total.setText("149.000đ");

        SL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SL.setText("11");

        Price.setBackground(new java.awt.Color(255, 255, 255));
        Price.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Price.setText("149.000đ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                .addComponent(SL, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Total, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(SL, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(Price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Price;
    private javax.swing.JLabel SL;
    private javax.swing.JLabel Total;
    // End of variables declaration//GEN-END:variables
}
