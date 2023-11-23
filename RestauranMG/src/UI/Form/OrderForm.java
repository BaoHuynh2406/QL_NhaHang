
package UI.Form;

import UI.Compoment.tableItem;
import UI.Model.Model_Table;
import java.awt.Dimension;
import Dao.TablesdDao;
import Dao.AreasDao;
import Dao.MenuCategoriesDao;
import Dao.procDao;
import Entity.Areas;
import Entity.MenuCategories;
import Entity.Tables;
import UI.Compoment.EventOrder;
import Utils.msg;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;

public class OrderForm extends javax.swing.JPanel {

    private EventOrder event;

    public void addEventOrder(EventOrder event) {
        this.event = event;
    }
    
    
   TablesdDao table_DAO = new TablesdDao();
   Tables tableSelected;
   procDao proDao = new procDao();
   MenuCategoriesDao loaiMenuDao = new MenuCategoriesDao();
   int selectedLoai;
   
   
   
    public OrderForm() {
        initComponents();
    }
    
    public void setTable(int ID_table){
        try {
        tableSelected = table_DAO.selectById(ID_table);
        txtTableName.setText("Bàn: "+tableSelected.getTableName());
        fillLoaiMenu();
        } catch (Exception e) {
            msg.Error("Bàn không hợp lệ");
        }
        
    }

    private void fillTable(int ID_area) {
        PanelTable.removeAll();
        PanelTable.revalidate();
        PanelTable.repaint();
        //Cập nhật trạng thái của bàn

        //Truy vấn tất cả và lấy thông tin cần thiết tên bàn,mã bàn, tổng tiền, số khách
        List<Object[]> t = proDao.GetTableSummary(ID_area);
        if (t == null || t.isEmpty()) {
            return;
        }

        int numRows = t.size() / 5; // Số hàng cần thiết
        int remainder = t.size() % 5; // Số item còn lại sau khi chia hết cho 5
        int canThiet = numRows + (remainder > 0 ? 1 : 0);
        PanelTable.setLayout(new GridLayout((canThiet > 5 ? canThiet : 5), 5, 20, 10)); // GridLayout với số hàng tính được, có thể cộng thêm 1 hàng nếu còn item thừa
        for (Object[] row : t) {
            tableItem item;
            
            int ID = (Integer) row[0];
            String TableName = (String) row[1];
            int total = 0, numGust = 0;
            try {
                total = (Integer) row[2];
                
            } catch (Exception e) {
            }
            try {
                numGust = (Integer) row[3]; 
            } catch (Exception e) {
            }
            if(row[3] == null){
                 item = new tableItem(new Model_Table(ID, TableName));
            }else{
                
                item = new tableItem(new Model_Table(ID, TableName, total, numGust));
            }
            // Bắt sự kiện
            
            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    item.setBackground(new Color(255, 241, 190));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    item.setColor();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    msg.Info("Đang chọn bàn " + TableName + " Mã " + ID);
                    System.out.println(item.getName());
                }
                
                
            });

            PanelTable.add(item);
        }

        // Thêm các ô trống nếu còn dư item
        if (t.size() < 25) {
            for (int i = 0; i < 25 - t.size(); i++) {
                PanelTable.add(new JLabel());
            }
        }
    }




    
    
    private List<JButton> buttons = new ArrayList<>();

    private void fillLoaiMenu() {


        pnLoaiMenu.removeAll();
        //Loại menu
        buttons.clear();
        List<MenuCategories> k = loaiMenuDao.selectAll();
        if (k == null) {
            return;
        }  
        JButton b = new JButton("Tất cả");
        b.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        b.setName("0");
        buttons.add(b);
        pnLoaiMenu.add(b);
        b.addActionListener(e -> {
            selectedLoai = 0;
            updateSelectLoai();
        });


        
        for (MenuCategories lMenu : k) {
            JButton button = new JButton(lMenu.getCategoryName());
            button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            button.setName(lMenu.getID_Category() + "");
            buttons.add(button); // Thêm JButton vào danh sách
            button.addActionListener(e -> {
                selectedLoai = lMenu.getID_Category();
                updateSelectLoai();
            });
            pnLoaiMenu.add(button);
        }

        selectedLoai = 0;
        updateSelectLoai();
    }

    private void updateSelectLoai() {
        for (JButton button : buttons) {
            if (button.getName().equals(String.valueOf(selectedLoai))) {
                button.setFont(new Font("Segoe UI", Font.BOLD, 14));
                button.setForeground(new Color(22, 72, 99));
//                fillTable(selectedLoai);
            } else {
                button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                button.setForeground(Color.BLACK);

            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScroolTable = new javax.swing.JScrollPane();
        PanelTable = new javax.swing.JPanel();
        pnController = new javax.swing.JPanel();
        txtTableName = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtGustNum = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        pnLoaiMenu = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 204, 255));

        ScroolTable.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ScroolTable.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ScroolTable.setPreferredSize(new java.awt.Dimension(850, 600));

        PanelTable.setBackground(new java.awt.Color(255, 255, 255));
        PanelTable.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));

        javax.swing.GroupLayout PanelTableLayout = new javax.swing.GroupLayout(PanelTable);
        PanelTable.setLayout(PanelTableLayout);
        PanelTableLayout.setHorizontalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 867, Short.MAX_VALUE)
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 707, Short.MAX_VALUE)
        );

        ScroolTable.setViewportView(PanelTable);

        pnController.setBackground(new java.awt.Color(232, 232, 232));
        pnController.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnController.setPreferredSize(new java.awt.Dimension(44, 50));

        txtTableName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTableName.setForeground(new java.awt.Color(0, 102, 102));
        txtTableName.setText("Bàn: ABC");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Số khách");

        txtGustNum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGustNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGustNum.setText("0");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.setText("Tìm kiếm");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/search.png"))); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("Quay Lại");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnControllerLayout = new javax.swing.GroupLayout(pnController);
        pnController.setLayout(pnControllerLayout);
        pnControllerLayout.setHorizontalGroup(
            pnControllerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnControllerLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(txtTableName)
                .addGap(72, 72, 72)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtGustNum, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(26, 26, 26))
        );
        pnControllerLayout.setVerticalGroup(
            pnControllerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnControllerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnControllerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGustNum)
                    .addComponent(txtTableName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(155, 190, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Món đã gọi");

        jSeparator1.setForeground(new java.awt.Color(153, 153, 153));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnLoaiMenu.setBackground(new java.awt.Color(232, 232, 232));
        pnLoaiMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnLoaiMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnController, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
                    .addComponent(ScroolTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 919, Short.MAX_VALUE)
                    .addComponent(pnLoaiMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnController, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnLoaiMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ScroolTable, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        event.GoBack();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelTable;
    private javax.swing.JScrollPane ScroolTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel pnController;
    private javax.swing.JPanel pnLoaiMenu;
    private javax.swing.JTextField txtGustNum;
    private javax.swing.JButton txtTableName;
    // End of variables declaration//GEN-END:variables
}
