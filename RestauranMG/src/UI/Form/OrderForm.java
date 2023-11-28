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
import Controller.EventOrder;
import Dao.MenuItemsDao;
import Entity.MenuItems;
import UI.Compoment.MonAnDaOrder;
import UI.Compoment.MonAnItem;
import UI.Model.Model_Item_Menu;
import UI.Model.Model_Mon_Da_Goi;
import Utils.fNum;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class OrderForm extends javax.swing.JPanel {

    private EventOrder event;


    public void addEventOrder(EventOrder event) {
        this.event = event;
        //abc
    }

    //Các DAO
    TablesdDao table_DAO = new TablesdDao();
    MenuItemsDao menu_Dao = new MenuItemsDao();
    Tables tableSelected;
    procDao proDao = new procDao();
    MenuCategoriesDao loaiMenuDao = new MenuCategoriesDao();
    int selectedLoai;

    public OrderForm() {
        initComponents();
        ScroolTable.getVerticalScrollBar().setUnitIncrement(20);

    }
    
   

    public void setTable(int ID_table) {
        try {
            tableSelected = table_DAO.selectById(ID_table);
            txtTableName.setText("Bàn: " + tableSelected.getTableName());
            fillLoaiMenu();
        } catch (Exception e) {
            msg.Error("Bàn không hợp lệ");
        }

    }
    public List<MonAnItem> dsMonAn = new ArrayList<>();
    
    private void fillItem(int ID_area) {
        dsMonAn.clear();
        PanelTable.removeAll();
        
        //Lấy toàn bộ menu
        List<MenuItems> dsItem = menu_Dao.selectCatory(ID_area);
        if (dsItem == null || dsItem.isEmpty()) {
            return;
        }

        int numRows = dsItem.size() / 3; // Số hàng cần thiết
        int remainder = dsItem.size() % 3; // Số item còn lại sau khi chia hết cho 3
        int canThiet = numRows + (remainder > 0 ? 1 : 0);
        PanelTable.setLayout(new GridLayout((canThiet > 3 ? canThiet : 3), 4, 30, 30)); // GridLayout với số hàng tính được, có thể cộng thêm 1 hàng nếu còn item thừa
        for (MenuItems row : dsItem) {
            MonAnItem item;
            if (row.isAvailable()) {
                item = new MonAnItem(new Model_Item_Menu(row.getItemName(), row.getID_Item(),
                         row.getPrice(), row.getPhoto(), Model_Item_Menu.MenuType.AVAiLABLE));
            } else {
                item = new MonAnItem(new Model_Item_Menu(row.getItemName(), row.getID_Item(),
                         row.getPrice(), row.getPhoto(), Model_Item_Menu.MenuType.UNAVAiLABLE));
            }
            // Bắt sự kiện

            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    item.setColor(new Color(245, 255, 135, 200));
                    item.setIcon("src/IMG/plus.png");
                    PanelTable.repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    item.setColor(new Color(228, 253, 255, 200));
                    item.setIcon("src/IMG/plus1.png");
                    PanelTable.repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    Model_Mon_Da_Goi data = new Model_Mon_Da_Goi(row.getID_Item(), row.getItemName(),
                            row.getPrice(), 1, Model_Mon_Da_Goi.ItemType.ChuaGoi);
                    fillDanhSachGoiMon(data);
                }

            });
           PanelTable.add(item);
           PanelTable.revalidate();
           dsMonAn.add(item);
        }

        // Thêm các ô trống nếu còn dư item
        if (dsItem.size() < 12) {
            for (int i = 0; i < 12 - dsItem.size(); i++) {
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
            refereshItem();
        });

        for (MenuCategories lMenu : k) {
            JButton button = new JButton(lMenu.getCategoryName());
            button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            button.setName(lMenu.getID_Category() + "");
            buttons.add(button); // Thêm JButton vào danh sách
            button.addActionListener(e -> {
                selectedLoai = lMenu.getID_Category();
                refereshItem();
            });
            pnLoaiMenu.add(button);
        }

        selectedLoai = 0;
        refereshItem();
    }

    private void refereshItem() {
        for (JButton button : buttons) {
            if (button.getName().equals(String.valueOf(selectedLoai))) {
                button.setFont(new Font("Segoe UI", Font.BOLD, 14));
                button.setForeground(new Color(22, 72, 99));

                fillItemInBackground(selectedLoai);

            } else {
                button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                button.setForeground(Color.BLACK);

            }
        }
    }

    
    
    //Nhóm chức năng cho phần món đã gọi
    
    
    
    private LinkedList<Model_Mon_Da_Goi> dsMonDangGoi = new LinkedList<>();
    
    private void fillDanhSachGoiMon(Model_Mon_Da_Goi data) {
        int idItem = data.getId();

        boolean existed = false;
        for (Model_Mon_Da_Goi existingData : dsMonDangGoi) {
            if (existingData.getId() == idItem) {
                // Nếu món đã tồn tại trong danh sách, tăng quantity và đặt lại vị trí cuối cùng
                existingData.setSl(existingData.getSl() + 1);
                dsMonDangGoi.remove(existingData);
                dsMonDangGoi.addLast(existingData);
                existed = true;
                break;
            }
        }

        if (!existed) {
            // Nếu món chưa tồn tại trong danh sách, thêm mới vào cuối danh sách
            dsMonDangGoi.addLast(data);
        }

        // Refresh danh sách hiển thị
        refreshMonAnOrder();
    }
    
    private void adjustQuantity(int idItem, int quantityAdjustment) {
        for (Model_Mon_Da_Goi data : dsMonDangGoi) {
            if (data.getId() == idItem) {
                int newQuantity = data.getSl() + quantityAdjustment;
                if (newQuantity >= 0) { // Kiểm tra số lượng không âm
                    data.setSl(newQuantity);
                    refreshMonAnOrder();
                    return; // Kết thúc việc điều chỉnh số lượng
                } else {
                    
                    return; 
                }
            }
        }
       
    }
    
     // Phương thức để tăng số lượng món
    public void increaseQuantity(int idItem) {
        adjustQuantity(idItem, 1); // Tăng số lượng lên 1
    }

    // Phương thức để giảm số lượng món
    public void decreaseQuantity(int idItem) {
        adjustQuantity(idItem, -1); // Giảm số lượng đi 1
    }
    

    

    private void refreshMonAnOrder() {
        pnMonAnOrder.removeAll();

        int width = dsMonDangGoi.size() * 50 + 40;
        pnMonAnOrder.setPreferredSize(new Dimension(370, width));
        int total = 0;
        for (Model_Mon_Da_Goi data : dsMonDangGoi) {
           total += data.getTotal();
            MonAnDaOrder item = new MonAnDaOrder(data);
            item.Giam.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    decreaseQuantity(data.getId());
                }
            });
            
            item.Tang.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    increaseQuantity(data.getId());
                }
            });
            
            lblTotal.setText(fNum.parseString(total)+"đ");
            pnMonAnOrder.add(item);
        }
        
        

        pnMonAnOrder.revalidate();
        pnMonAnOrder.repaint();
       
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
        pnMonAnOrder = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
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
            .addGap(0, 791, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jScrollPane1.setBackground(new java.awt.Color(155, 190, 200));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pnMonAnOrder.setBackground(new java.awt.Color(155, 190, 200));
        pnMonAnOrder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));
        jScrollPane1.setViewportView(pnMonAnOrder);

        jPanel3.setBackground(new java.awt.Color(231, 231, 231));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 102, 51));
        jLabel7.setText("Tạm tính:");

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotal.setText("0đ");

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Gọi món");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(51, 0, 204));
        jButton3.setText("Thanh toán");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SL");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Tên món");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Đơn giá");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 277, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
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
                    .addComponent(pnLoaiMenu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ScroolTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
                    .addComponent(pnController, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnController, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnLoaiMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ScroolTable, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE))
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnController;
    private javax.swing.JPanel pnLoaiMenu;
    private javax.swing.JPanel pnMonAnOrder;
    private javax.swing.JTextField txtGustNum;
    private javax.swing.JButton txtTableName;
    // End of variables declaration//GEN-END:variables

    private void fillItemInBackground(int ID_area) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Thực hiện công việc tải dữ liệu trong background
                // Gọi hàm fillItem từ SwingWorker
                fillItem(ID_area);
                PanelTable.revalidate();
                for(MonAnItem i : dsMonAn){
                    i.setIMG();
                }
                return null;
            }

            @Override
            protected void done() {
                // Cập nhật giao diện sau khi công việc hoàn thành
                PanelTable.revalidate();
                
            }
        };

        worker.execute(); // Bắt đầu thực hiện công việc trong luồng phụ
    }

}
