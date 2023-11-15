
package UI.SplashScreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.basic.BasicProgressBarUI;


public class ProgressBar extends JProgressBar{
    private Color ColorString = new Color(200,200,200);
    private int borderRadius = 20;
    private Color borderColor = new Color(200, 200, 200);
    
    public Color getColorString() {
        return ColorString;
    }

    public void setColorString(Color ColorString) {
        this.ColorString = ColorString;
    }
   
    
    
    public ProgressBar() {
        setStringPainted(true);
        setPreferredSize(new Dimension(100,15));
        setOpaque(false); // Đặt thành không có màu nền
        setBackground(new Color(255, 255, 255, 0)); // Màu nền trong suốt
        setBorderPainted(false);
//        setBackground(new Color(255,255,255));
        setForeground(new Color(69,124,235));
        setUI(new BasicProgressBarUI() {
            @Override
            protected void paintDeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g.create();
                
                // Vẽ border với border radius
                g2d.setColor(borderColor);
                g2d.fillRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, borderRadius, borderRadius);

                // Vẽ thanh tiến trình bên trong border
                g2d.setColor(getForeground());
                int width = (int) ((c.getWidth() - 2) * getPercentComplete());
                g2d.fillRoundRect(1, 1, width, c.getHeight() - 2, borderRadius, borderRadius);

                g2d.dispose();
            }
            
            @Override
            protected void paintString(Graphics g, int i, int i1, int i2, int i3, int i4, Insets insets) {
                g.setColor(ColorString);
                super.paintString(g, i, i1, i2, i3, i4, insets);
            }

            
        });
    }
    
}
