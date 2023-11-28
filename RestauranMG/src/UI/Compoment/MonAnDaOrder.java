
package UI.Compoment;

import UI.Model.Model_Mon_Da_Goi;
import Utils.fNum;
import java.awt.Dimension;


public class MonAnDaOrder extends javax.swing.JPanel {


    public MonAnDaOrder(Model_Mon_Da_Goi data) {
        initComponents();
        
        if(data.getType() == Model_Mon_Da_Goi.ItemType.ChuaGoi){
            Name.setText(data.getName());
            Price.setText(fNum.parseString(data.getPrice())+"đ");
            SL.setText(data.getSl()+"");
            
        }else if(data.getType() == Model_Mon_Da_Goi.ItemType.DaGoi){
            Tang.setVisible(false);
            Giam.setVisible(false);
            Name.setText(data.getName());
            Price.setText(fNum.parseString(data.getPrice())+"đ");
            SL.setText(""+data.getSl());
            
            
        }else{
            Name.setText("");
            Price.setText("___ Đang gọi ___");
            Tang.setVisible(false);
            Giam.setVisible(false);
            SL.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Name = new javax.swing.JLabel();
        SL = new javax.swing.JLabel();
        Price = new javax.swing.JLabel();
        Tang = new javax.swing.JLabel();
        Giam = new javax.swing.JLabel();

        setOpaque(false);

        Name.setBackground(new java.awt.Color(255, 255, 255));
        Name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Name.setText("Gà hấp xả");

        SL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        SL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SL.setText("11");

        Price.setBackground(new java.awt.Color(255, 255, 255));
        Price.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Price.setText("149.000đ");

        Tang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Tang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tang.setText("+");
        Tang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        Giam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Giam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Giam.setText("-");
        Giam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Price, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tang, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SL, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Giam, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SL, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(Price, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(Tang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Giam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Giam;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Price;
    private javax.swing.JLabel SL;
    public javax.swing.JLabel Tang;
    // End of variables declaration//GEN-END:variables
}
