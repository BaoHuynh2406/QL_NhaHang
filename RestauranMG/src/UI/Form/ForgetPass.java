package UI.Form;

import Dao.EmployeesDao;
import Entity.Employees;
import Utils.EmailSender;
import Utils.Validate;
import Utils.msg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ForgetPass extends javax.swing.JDialog {

    public ForgetPass(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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

    public boolean Validate() {
        boolean check = true;
        int UserName = Integer.valueOf(txtUserName.getText());
        String Email = txtEmail.getText();

        try {
            int UsernameINT = Integer.valueOf(UserName);
            if (!Validate.isLength(txtUserName, UserName + "", 6, "User name")) {
                txtUserName.setBackground(Color.YELLOW);
                check = false;
            }
        } catch (Exception e) {
            msg.Warning("Vui lòng nhập user name là các số.");
            txtUserName.setBackground(Color.YELLOW);
            check = false;
        }

        if (!Validate.isEmail(txtEmail, Email)) {
            check = false;
        }
        return check;
    }

    public void submit() {
        if (Validate()) {

            //Thực hiện kiễm tra và gửi maill
            EmployeesDao dao = new EmployeesDao();
            int UserName = Integer.valueOf(txtUserName.getText());
            Employees nv = dao.selectById(UserName);
            String randomNumber = "";

            if (nv != null && txtEmail.getText().equalsIgnoreCase(nv.getEmail())) {
                Random random = new Random();

                for (int i = 0; i < 6; i++) {
                    int digit = random.nextInt(10); // Tạo số ngẫu nhiên từ 0 đến 9
                    randomNumber += digit; // Ghép số vào chuỗi
                }
                EmailSender.sendEmail(nv.getEmail(), "Restaurant MG - Cấp lại mật khẩu",
                        "Xin chào " + nv.getFullName()
                        + ".\nChúng tôi gửi cho bạn mã xác nhận này để khôi phục mật khẩu, vui lòng không chia sẽ cho bất kỳ ai!"
                        + "\nMã xác nhận của bạn là: " + randomNumber);
            } else {
                msg.Error("Sai tên đăng nhập hoặc Email!!!");
                return;
            }

            // Loại bỏ thành phần cũ
            this.setLayout(new BorderLayout());
            this.remove(pnBg);
            JPanel pn = new resetPassForm(nv.getFullName(), nv.getID_Employee(), randomNumber, this);
            this.add(pn);
            // Cập nhật giao diện
            this.revalidate();
            this.repaint();

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        pnBg = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtUserName = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSupmit = new javax.swing.JButton();
        btnClose = new javax.swing.JLabel();
        btnBack = new javax.swing.JLabel();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pnBg.setBackground(new java.awt.Color(0, 102, 102));

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên đăng nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txtUserName.setBackground(new java.awt.Color(229, 229, 229));
        txtUserName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUserName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUserNameMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtUserName)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Email", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        txtEmail.setBackground(new java.awt.Color(229, 229, 229));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtEmailMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtEmail)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logo128.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Khôi phục tài khoản");

        btnSupmit.setBackground(new java.awt.Color(27, 219, 115));
        btnSupmit.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnSupmit.setText("Xác nhận");
        btnSupmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupmitActionPerformed(evt);
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

        btnBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/back.png"))); // NOI18N
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBackMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnBgLayout = new javax.swing.GroupLayout(pnBg);
        pnBg.setLayout(pnBgLayout);
        pnBgLayout.setHorizontalGroup(
            pnBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBgLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnBgLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel1))
                    .addComponent(btnSupmit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBgLayout.createSequentialGroup()
                .addContainerGap(136, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(126, 126, 126))
        );
        pnBgLayout.setVerticalGroup(
            pnBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBgLayout.createSequentialGroup()
                .addGroup(pnBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBgLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnBgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnBgLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addComponent(btnSupmit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnBg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnBg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMousePressed
        System.exit(0);
    }//GEN-LAST:event_btnCloseMousePressed

    private void btnBackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBackMousePressed
        this.setVisible(false);
    }//GEN-LAST:event_btnBackMousePressed

    private void btnSupmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupmitActionPerformed

        submit();

    }//GEN-LAST:event_btnSupmitActionPerformed

    private void txtUserNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUserNameMousePressed
         txtUserName.setBackground(Color.white);
    }//GEN-LAST:event_txtUserNameMousePressed

    private void txtEmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmailMousePressed
        txtEmail.setBackground(Color.white);
    }//GEN-LAST:event_txtEmailMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForgetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ForgetPass dialog = new ForgetPass(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JLabel btnBack;
    javax.swing.JLabel btnClose;
    javax.swing.JButton btnSupmit;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JPanel jPanel2;
    javax.swing.JPanel jPanel3;
    javax.swing.JToggleButton jToggleButton1;
    javax.swing.JPanel pnBg;
    javax.swing.JTextField txtEmail;
    javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
