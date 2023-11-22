
package UI.Compoment;

import UI.Model.Model_Table;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class tableItem extends javax.swing.JPanel {

//    private boolean selected;
//    private boolean over;
    private Model_Table data;
    
    public tableItem(Model_Table data) {
        initComponents();
        setOpaque(true);
        this.data = data;
        if(data.getType() == Model_Table.TableType.NULL){
            lbGuest.setText("");
            lbName.setText("");
            lbTitle.setText(data.getName());
            this.setBackground(new Color(217,217,217));
        }else{
            lbGuest.setText(data.getGuestNum()+" Khách");
            lbName.setText(data.getName());
            lbTitle.setText(data.getTotal()+"đ");
            this.setBackground(new Color(155,190,200));

        }
        this.setPreferredSize(new Dimension(200,120));
        
    }
    
    public void setColor(){
         if(data.getType() == Model_Table.TableType.NULL){
            this.setBackground(new Color(217,217,217));
        }else{
            this.setBackground(new Color(155,190,200));

        }
    }
    
//    public void setOver(boolean over) {
//        this.over = over;
//        repaint();
//    }
//
//    public void setSelected(boolean selected) {
//        this.selected = selected;
//        repaint();
//    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        lbTitle = new javax.swing.JLabel();
        lbGuest = new javax.swing.JLabel();

        setBackground(new java.awt.Color(155, 190, 200));
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMaximumSize(new java.awt.Dimension(200, 120));

        lbName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbName.setText("A01");

        lbTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("900.000đ");

        lbGuest.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbGuest.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGuest.setText("2 Khách");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
            .addComponent(lbGuest, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbGuest)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {  
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (data.getType() == Model_Table.TableType.NOTNULL) {
            g2.setColor(new Color(155, 190, 200));
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbGuest;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbTitle;
    // End of variables declaration//GEN-END:variables
}
