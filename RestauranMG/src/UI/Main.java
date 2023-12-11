
package UI;

import Controller.EventMenuSelected;
import Controller.EventOrder;
import Controller.EventTableSelected;
import Dao.procDao;
import HangHoa.QLKhoHangForm;
import ThongKe.DoanhThu;
import ThongKe.ThongKeForm;
import UI.Compoment.MainMenu.MainMenu;
import UI.Form.CaiDat.CaiDatNhaHang;
import UI.Form.GioiThieu;
import UI.Form.Login;
import UI.Form.OrderForm;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import javax.swing.JFrame;
import UI.Form.SpalshScreen;
import UI.Form.TableForm;
import Utils.Auth;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import Utils.IMG;
import Utils.msg;
import UI.Form.QLNV;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.Border;

 
public class Main extends javax.swing.JFrame {

    
    public Main() {
        //Full màn hình
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        initComponents();
        runClock();
        RunSpalshScreen(); //Chạy màn hình loading
        RunLogin(); //chạy màn hình login
        init();
    }
    
    public void init(){
        //Thiết lập icon
        this.setIconImage(new ImageIcon("src/IMG/logo512.png").getImage());
        //Bắt sự kiện nút close
        btnClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnHide.setIcon(new ImageIcon("src/IMG/-Dot.png"));
                btnClose.setIcon(new ImageIcon("src/IMG/xDot.png"));
                btnFull.setIcon(new ImageIcon("src/IMG/zDot.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnHide.setIcon(new ImageIcon("src/IMG/ordot.png"));
                btnClose.setIcon(new ImageIcon("src/IMG/redDot.png"));
                btnFull.setIcon(new ImageIcon("src/IMG/reenDot.png"));
            }
       });
        
        //Bắt sự kiện nút hide
        btnHide.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnHide.setIcon(new ImageIcon("src/IMG/-Dot.png"));
                btnClose.setIcon(new ImageIcon("src/IMG/xDot.png"));
                btnFull.setIcon(new ImageIcon("src/IMG/zDot.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
               btnHide.setIcon(new ImageIcon("src/IMG/ordot.png"));
                btnClose.setIcon(new ImageIcon("src/IMG/redDot.png"));
                btnFull.setIcon(new ImageIcon("src/IMG/reenDot.png"));
            }
       });
        
         //Bắt sự kiện nút Full
        btnFull.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnHide.setIcon(new ImageIcon("src/IMG/-Dot.png"));
                btnClose.setIcon(new ImageIcon("src/IMG/xDot.png"));
                btnFull.setIcon(new ImageIcon("src/IMG/zDot.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
              btnHide.setIcon(new ImageIcon("src/IMG/ordot.png"));
                btnClose.setIcon(new ImageIcon("src/IMG/redDot.png"));
                btnFull.setIcon(new ImageIcon("src/IMG/reenDot.png"));
            }
       });
        
        
        
        //Sự kiên mở lại cửa sổ
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeiconified(WindowEvent e) {
                setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }

   
    
    private void RunSpalshScreen (){
        SpalshScreen sps = new SpalshScreen(this, true);
        sps.setVisible(true);
    }
    
    private void RunLogin() {
        Login lg = new Login(this, true);
        lg.setVisible(true);
        if (Auth.isLogin()) {
            if (Auth.getRole().equals("MG")) {
                lbChucVu.setText("Quản lý:");
                lbUserName.setText(Auth.user.getFullName());
                menu.MG();
                
                menu.addEventMenuSelected(new EventMenuSelected() {
                    @Override
                    public void selected(int index) {
                        if (index == 0) {
                            formTable();
                        }
                        if(index == 1){
                            formHangHoa();
                        }
                        if (index == 2) {
                            nvForm();
                        }
                        if(index == 3){
                            caiDatForm();
                        }
                        
                        if(index == 4){
                            formThongKe();
                        }
                        
                        if(index == 5){
                            FormGioThieu();
                        }
                    }
                });
                formTable();
            }
           
            if(Auth.getRole().equals("SV")){
                lbChucVu.setText("Phục vụ:");
                lbUserName.setText(Auth.user.getFullName());
                menu.SV();
              
                menu.addEventMenuSelected(new EventMenuSelected() {
                    @Override
                    public void selected(int index) {
                        if (index == 0) {
                            formTable();
                        }
                        if(index == 1){
                            FormGioThieu();
                        }
                        
                    }
                });
                formTable();
            }
            
            if(Auth.getRole().equals("KS")){
                lbChucVu.setText("Bếp:");
                lbUserName.setText(Auth.user.getFullName());
                menu.KS();
   
                menu.addEventMenuSelected(new EventMenuSelected() {
                    @Override
                    public void selected(int index) {
                        if (index == 0) {
                            formHangHoa();
                        }
                        if(index == 1){
                            FormGioThieu();
                        }
                        
                    }
                });
                formHangHoa();
            }
            
            if(Auth.getRole().equals("CS")){
                lbChucVu.setText("Thu ngân:");
                lbUserName.setText(Auth.user.getFullName());
                menu.CS();
         
                menu.addEventMenuSelected(new EventMenuSelected() {
                    @Override
                    public void selected(int index) {
                        if (index == 0) {
                            formTable();
                        }
                        if(index == 1){
                           formThongKe();
                        }
                        if (index == 2) {
                           FormGioThieu();
                        }
                        
                    }
                });
                formTable();
            }
            
            
            this.setVisible(true);
        } else {
            msg.Error("Có lỗi xảy ra trong quá trình đăng nhập!. Vui lòng khởi động lại");
            System.exit(0);
        }
    }
 
    private void logOut(){
        Auth.user = null;
        this.dispose();
        RunLogin();
    }
    //Đồng hồ
    public void runClock() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat f = new SimpleDateFormat("hh:mm a");
        new Timer(1000, new ActionListener() {
            String ngayHienTai = dateFormat.format(new Date());
            @Override
            public void actionPerformed(ActionEvent e) {
                lblClock.setText(f.format(new Date()));
                lblDate.setText(ngayHienTai);
            }
        }).start();
    }
    
    
    //Form Table
    public void formTable(){
        procDao.DeleteOrdersWithoutDetails();
        TableForm tableForm = new TableForm(this); //Form Hiển thị các danh sách bàn

        pnDashboard.removeAll();
        pnDashboard.add(tableForm, BorderLayout.CENTER);
        pnDashboard.repaint();
        pnDashboard.revalidate();
        
        tableForm.addEventTableSelected(new EventTableSelected() {
             @Override
             public void selected(int index) {
                 setFormOrder(index);
             }
        });
    }
    
    
    //Form order
    private void setFormOrder(int ID){
        OrderForm orderForm = new OrderForm(this); //Form order
        pnDashboard.removeAll();
        orderForm.setTable(ID);
        pnDashboard.add(orderForm,BorderLayout.CENTER);
        pnDashboard.repaint();
        pnDashboard.revalidate();
        
        orderForm.addEventOrder(new EventOrder() {
            @Override
            public void selected(int index) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void GoBack() {
                formTable();
            }
        });
    }
    
    //Form Hàng hóa
    public void formHangHoa(){
        QLKhoHangForm f = new QLKhoHangForm();
        pnDashboard.removeAll();
        pnDashboard.add(f, BorderLayout.CENTER);
        pnDashboard.repaint();
        pnDashboard.revalidate();
    }
    
    //Form Nhân viên
    public void nvForm(){
        QLNV nvForm = new QLNV(); //Form Hiển thị nhân viên
        pnDashboard.removeAll();
        pnDashboard.add(nvForm, BorderLayout.CENTER);
        pnDashboard.repaint();
        pnDashboard.revalidate();
    }
    
    //Form cài đặt nhà hàng
    public void caiDatForm() {

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                pnDashboard.removeAll();
                ImageIcon loadingIcon = new ImageIcon("src/IMG/ld.gif"); 
                JLabel ic = new JLabel(loadingIcon);
                pnDashboard.add(ic, BorderLayout.CENTER);
                revalidate();
                repaint();
                CaiDatNhaHang f = new CaiDatNhaHang();
                pnDashboard.removeAll();
                pnDashboard.add(f, BorderLayout.CENTER);
                pnDashboard.repaint();
                pnDashboard.revalidate();
                return null;
            }

            @Override
            protected void done() {
                revalidate();
            }
        };

        worker.execute();

    }
    
                
    //Form thống kê
    public void formThongKe(){
       ThongKeForm f = new ThongKeForm();
        pnDashboard.removeAll();
        pnDashboard.add(f, BorderLayout.CENTER);
        pnDashboard.repaint();
        pnDashboard.revalidate();
    }
 
        
    //Form giới thiệu
    public void FormGioThieu(){
        GioiThieu f = new GioiThieu();
        pnDashboard.removeAll();
        pnDashboard.add(f, BorderLayout.CENTER);
        pnDashboard.repaint();
        pnDashboard.revalidate();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnClose = new javax.swing.JLabel();
        btnFull = new javax.swing.JLabel();
        btnHide = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbChucVu = new javax.swing.JLabel();
        btnOut = new javax.swing.JLabel();
        lbUserName = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblClock = new javax.swing.JLabel();
        bg = new javax.swing.JPanel();
        menu = new UI.Compoment.MainMenu.MainMenu();
        pnDashboard = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Restaurant MG");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(22, 72, 99));
        jPanel1.setPreferredSize(new Dimension(screenWidth, 50)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(224, 224, 224));
        jLabel1.setText("3 Chàng Trai");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/logoNH64x38.png"))); // NOI18N

        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/redDot.png"))); // NOI18N
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCloseMousePressed(evt);
            }
        });

        btnFull.setBackground(new java.awt.Color(255, 255, 255));
        btnFull.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnFull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/reenDot.png"))); // NOI18N
        btnFull.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFull.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFullMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnFullMousePressed(evt);
            }
        });

        btnHide.setBackground(new java.awt.Color(255, 255, 255));
        btnHide.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/ordot.png"))); // NOI18N
        btnHide.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHideMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHideMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHideMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 554, Short.MAX_VALUE)
                .addComponent(btnFull, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHide, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(btnFull, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHide, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(22, 72, 99));
        jPanel2.setPreferredSize(new Dimension(screenWidth, 50)
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/user46x38.png"))); // NOI18N

        lbChucVu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbChucVu.setForeground(new java.awt.Color(255, 153, 0));
        lbChucVu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbChucVu.setText("Quản lý: ");

        btnOut.setBackground(new java.awt.Color(255, 255, 255));
        btnOut.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnOut.setForeground(new java.awt.Color(255, 51, 51));
        btnOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnOut.setText("Đăng Xuất");
        btnOut.setToolTipText("");
        btnOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnOutMousePressed(evt);
            }
        });

        lbUserName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbUserName.setForeground(new java.awt.Color(218, 218, 218));
        lbUserName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbUserName.setText("Admin001");

        lblDate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDate.setText("16/11/2023");
        lblDate.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblClock.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblClock.setForeground(new java.awt.Color(255, 255, 255));
        lblClock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblClock.setText("00:00 CH");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOut, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 455, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClock, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnOut, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblClock, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lbUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        bg.setBackground(new java.awt.Color(255, 255, 255));

        pnDashboard.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
            .addComponent(pnDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(bg, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCloseMousePressed

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnFullMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFullMouseClicked
        this.setExtendedState(JFrame.HIDE_ON_CLOSE);
    }//GEN-LAST:event_btnFullMouseClicked

    private void btnFullMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFullMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFullMousePressed

    private void btnHideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHideMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHideMouseClicked

    private void btnHideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHideMousePressed
       this.setExtendedState(JFrame.HIDE_ON_CLOSE);
    }//GEN-LAST:event_btnHideMousePressed

    private void btnHideMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHideMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHideMouseReleased

    private void btnOutMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOutMousePressed
        logOut();
    }//GEN-LAST:event_btnOutMousePressed

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnFull;
    private javax.swing.JLabel btnHide;
    private javax.swing.JLabel btnOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbChucVu;
    private javax.swing.JLabel lbUserName;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblDate;
    private UI.Compoment.MainMenu.MainMenu menu;
    private javax.swing.JPanel pnDashboard;
    // End of variables declaration//GEN-END:variables
    //Lấy độ dài và rộng của màn hình
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenWidth = (int) screenSize.getWidth();
    private int screenHight = (int) screenSize.getHeight();
}
