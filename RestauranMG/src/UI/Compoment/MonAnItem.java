
package UI.Compoment;

import UI.Model.Model_Item_Menu;
import Utils.IMG;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class MonAnItem extends javax.swing.JPanel {
    private ImageIcon imageIcon;
    private Color color;
    private String path;
    
    
    public MonAnItem(Model_Item_Menu data) {
        initComponents();
         setOpaque(false);
         this.setPreferredSize(new Dimension(300,200));
         if(data.getType() == Model_Item_Menu.MenuType.AVAiLABLE){
             hetMon.setVisible(false);
         }else{
             hetMon.setVisible(true);
         }
         
         lbName.setText(data.getName());
         lbCoast.setText(data.getCoast()+"đ");
         this.setName(data.getID()+"");
        path = data.getPath();
        
         
        
        
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        pn.setColor(color);
    }

    public void setIcon(String path){
        ic.setIcon(new ImageIcon(path)); 
    }

    public void setIMG(){
        imageIcon = IMG.resize("src/IMG/Food/"+this.path,null, 300, 200);
        this.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pn = new UI.Compoment.panelCustom();
        ic = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbCoast = new javax.swing.JLabel();
        hetMon = new UI.Compoment.panelCustom();
        jLabel4 = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/plus1.png"))); // NOI18N

        lbName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbName.setText("Gà hấp xả");

        lbCoast.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbCoast.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCoast.setText("900.000đ");

        javax.swing.GroupLayout pnLayout = new javax.swing.GroupLayout(pn);
        pn.setLayout(pnLayout);
        pnLayout.setHorizontalGroup(
            pnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(ic, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbCoast, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnLayout.setVerticalGroup(
            pnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ic, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(lbCoast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        hetMon.setColor(new Color(0 , 82,141,200));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Hết món");

        javax.swing.GroupLayout hetMonLayout = new javax.swing.GroupLayout(hetMon);
        hetMon.setLayout(hetMonLayout);
        hetMonLayout.setHorizontalGroup(
            hetMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hetMonLayout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(29, 29, 29))
        );
        hetMonLayout.setVerticalGroup(
            hetMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hetMonLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(hetMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 42, Short.MAX_VALUE)
                .addComponent(hetMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(pn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private UI.Compoment.panelCustom hetMon;
    private javax.swing.JLabel ic;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbCoast;
    private javax.swing.JLabel lbName;
    private UI.Compoment.panelCustom pn;
    // End of variables declaration//GEN-END:variables
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs.create();

        // Vẽ đường viền bo góc
        int borderThickness = 3; // Độ dày của đường viền
        g2.setColor(Color.GRAY); // Màu sắc của đường viền
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Tạo hình chữ nhật cong để làm đường viền
        RoundRectangle2D borderRect = new RoundRectangle2D.Double(
                borderThickness / 2.0, // Độ dày của viền sẽ là một nửa
                borderThickness / 2.0,
                getWidth() - borderThickness,
                getHeight() - borderThickness,
                20, // Bán kính cong của góc
                20 // Bán kính cong của góc
        );
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(borderThickness));
        g2.draw(borderRect);
        g2.setStroke(oldStroke);

        if (imageIcon != null) {
            // Vẽ hình chữ nhật cong để làm nền có border radius
            RoundRectangle2D roundedRect = new RoundRectangle2D.Double(
                    borderThickness, // Để làm cho hình ảnh không bị che khuất bởi đường viền
                    borderThickness,
                    getWidth() - 2 * borderThickness,
                    getHeight() - 2 * borderThickness,
                    20, 20);
            g2.setClip(roundedRect);

            // Vẽ hình ảnh bên trong hình chữ nhật cong
            g2.drawImage(imageIcon.getImage(), borderThickness, borderThickness, getWidth() - 2 * borderThickness, getHeight() - 2 * borderThickness, this);
        } else {
            // Nếu không tìm thấy hình ảnh, vẽ một màu đơn để thay thế
            g2.setColor(Color.GRAY);
            g2.fill(new RoundRectangle2D.Double(borderThickness, borderThickness, getWidth() - 2 * borderThickness, getHeight() - 2 * borderThickness, 20, 20));
        }

        super.paintComponent(grphcs);
    }

}
