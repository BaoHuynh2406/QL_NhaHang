
package UI.Form;

import Dao.EmployeesDao;
import Entity.Employees;
import Utils.Encryption;
import Utils.Validate;
import Utils.msg;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;


public class resetPassForm extends javax.swing.JPanel {
    JDialog d = new JDialog();
   String code;
   int ID;
    public resetPassForm(String name,int ID, String code, JDialog d) {
        initComponents();
        lbUser.setText(name);
        this.code = code;
        this.ID = ID;
        this.d = d;
        //Bắt sự kiện nút exit
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnClose.setIcon(new ImageIcon("src/IMG/xDot.png")); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClose.setIcon(new ImageIcon("src/IMG/redDot.png"));
            }
       });
        
        
        //Bắt sự kiện nút exit
        btnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnBack.setIcon(new ImageIcon("src/IMG/backHover.png")); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnBack.setIcon(new ImageIcon("src/IMG/back.png"));
            }
       });
    }

    
    public boolean Check(){
        if(!Validate.isLength(txtCode, txtCode.getText(), 6)) return false;
        try{
            int code = Integer.valueOf(txtCode.getText());
        }catch(Exception e){
            msg.Error("Mã xác nhận không đúng định dạng!");
            return false;
        }
        
        if(!Validate.isLength(txtPass, new String(txtPass.getPassword()), 6)) return false;
        
        
        if(!code.equals(txtCode.getText())){
            msg.Error("Mã xác nhận không chính xác!. Vui lòng thử lại");
            return false;
        }
        
        return true;
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSupmit = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtPass = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        txtCode = new javax.swing.JTextField();
        lbUser = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();
        btnClose = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 102, 102));

        btnSupmit.setBackground(new java.awt.Color(27, 219, 115));
        btnSupmit.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSupmit.setText("Xác nhận");
        btnSupmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupmitActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mật khẩu mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txtPass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPassMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtPass)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã xác nhận 6 số", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txtCode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtCodeMousePressed(evt);
            }
        });
        txtCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtCode, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        lbUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbUser.setForeground(new java.awt.Color(255, 255, 255));
        lbUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbUser.setText("Tên tài khoản A");

        btnBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/back.png"))); // NOI18N
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBackMousePressed(evt);
            }
        });

        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/redDot.png"))); // NOI18N
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCloseMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 100, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnSupmit, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(lbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnSupmit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodeActionPerformed

    private void btnBackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMousePressed
        this.setVisible(false);
        d.setVisible(true);
    }//GEN-LAST:event_btnBackMousePressed

    private void btnCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMousePressed
        System.exit(0);
    }//GEN-LAST:event_btnCloseMousePressed

    private void btnSupmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupmitActionPerformed
        if(Check()){
            try {
                EmployeesDao dao = new EmployeesDao();
                Employees nv = dao.selectById(ID);
                nv.setPassword(Encryption.hashPassword(new String(txtPass.getPassword())));
                System.out.println(nv.toString());
                dao.update(nv);
                msg.Info("Đã thay đổi mật khẩu!");
                d.setVisible(false);
            } catch (Exception e) {
                msg.Error("Lỗi! "+e.getMessage());
            }
        }
    }//GEN-LAST:event_btnSupmitActionPerformed

    private void txtCodeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCodeMousePressed
        txtCode.setBackground(Color.white);
    }//GEN-LAST:event_txtCodeMousePressed

    private void txtPassMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMousePressed
         txtPass.setBackground(Color.white);
    }//GEN-LAST:event_txtPassMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnBack;
    private javax.swing.JLabel btnClose;
    private javax.swing.JButton btnSupmit;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbUser;
    private javax.swing.JTextField txtCode;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}
