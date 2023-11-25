
package HangHoa;

public class Header extends javax.swing.JPanel {

    public Header() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button1 = new button.Button();
        button2 = new button.Button();
        button3 = new button.Button();

        setBackground(new java.awt.Color(188, 203, 245));

        button1.setBackground(new java.awt.Color(204, 204, 204));
        button1.setText("Nhập hàng");
        button1.setBorderColor(new java.awt.Color(0, 0, 0));
        button1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        button1.setRadius(30);

        button2.setText("Kiểm hàng");
        button2.setBorderColor(new java.awt.Color(0, 0, 0));
        button2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        button2.setRadius(30);

        button3.setText("Xem kho hàng");
        button3.setBorderColor(new java.awt.Color(0, 0, 0));
        button3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        button3.setRadius(30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button button1;
    private button.Button button2;
    private button.Button button3;
    // End of variables declaration//GEN-END:variables
}
