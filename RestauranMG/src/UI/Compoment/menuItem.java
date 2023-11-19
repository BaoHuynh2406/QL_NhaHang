
package UI.Compoment;

import UI.Model.Model_Menu;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class menuItem extends javax.swing.JPanel {

    private boolean selected;
    private boolean over;
    
    public menuItem(Model_Menu data) {
        initComponents();
        setOpaque(false);
        if(data.getType() == Model_Menu.MenuType.MENU){
            lblIcon.setIcon(data.toIcon());
            lblName.setText(data.getName());
        }else if(data.getType() == Model_Menu.MenuType.TITLE){
            lblIcon.setText(data.getName());
            lblIcon.setFont(new Font("sansserif", 1, 12));
            lblName.setVisible(false);
        }else{
            lblName.setText(" ");
        }
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    public void setOver(boolean over) {
        this.over = over;
        repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIcon = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(200, 200, 200));

        lblIcon.setBackground(new java.awt.Color(255, 255, 255));
        lblIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblName.setBackground(new java.awt.Color(51, 51, 51));
        lblName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblName.setForeground(new java.awt.Color(51, 51, 51));
        lblName.setText("Menu name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(lblIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (selected || over) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (selected) {
                g2.setColor(new Color(66, 125, 157));
                lblName.setForeground(Color.WHITE);
                lblName.setFont(new Font("Segoe UI", Font.BOLD, 18) {
                });
            } else {
                g2.setColor(new Color(66, 125, 157, 80));
            }
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        }
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblName;
    // End of variables declaration//GEN-END:variables
}
