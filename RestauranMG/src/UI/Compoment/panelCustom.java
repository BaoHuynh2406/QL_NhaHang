
package UI.Compoment;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author mtsst
 */
public class panelCustom extends javax.swing.JPanel {
    
    private Color color;
    
    
    public panelCustom() {
        initComponents();
        setOpaque(false);
        color = new Color(228, 253, 255, 200);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    protected void paintComponent(Graphics grphcs) {
         super.paintComponent(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs.create();

        g2.setColor(color); // Đặt màu cho vùng vẽ trước khi vẽ hình tròn
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Vẽ hình tròn

        g2.dispose();
    }

}
