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
import Dao.InvoicesDao;
import Dao.MenuItemsDao;
import Dao.OrderDetailDao;
import Dao.OrdersDao;
import Entity.MenuItems;
import Entity.OrderDetail;
import Entity.Orders;
import UI.Compoment.MonAnDaOrder;
import UI.Compoment.MonAnItem;
import UI.Main;
import UI.Model.Model_Item_Menu;
import UI.Model.Model_Mon_Da_Goi;
import Utils.Auth;
import Utils.fNum;
import Utils.msg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private Main main;
    public OrderForm(Main main) {
        this.main = main;
        initComponents();
        ScroolTable.getVerticalScrollBar().setUnitIncrement(20);
        updateStatus();

    }

    private void updateStatus(){
        if(dsDaGoiMap.isEmpty()){
            btnThanhToan.setEnabled(false);
        }else{
            btnThanhToan.setEnabled(true);
        }
        
        if(dsMonDangGoiMap.isEmpty()){
            btnGoiMon.setEnabled(false);
        }else{
            btnGoiMon.setEnabled(true);
        } 
    }
    
    private Orders DonHang = new Orders();

    private void updateThongTinDonHang() {
        DonHang.setID_Employee(Auth.user.getID_Employee());
        DonHang.setIsPaid(false);
        DonHang.setNumberOfGuests(Integer.valueOf(txtGustNum.getText()));
        DonHang.setOrderDate(new Date());
    }

    String tenBan;
    public void setTable(int ID_table) {
        try {
            DonHang.setID_Table(ID_table);
            tableSelected = table_DAO.selectById(ID_table);
            tenBan = "Bàn: " + tableSelected.getTableName();
            txtTableName.setText(tenBan);
            fillLoaiMenu();
            fillDsDaGoi();

            //Nếu có đơn hàng
            if (!dsDaGoiMap.isEmpty()) {
                Iterator<Model_Mon_Da_Goi> iterator = dsDaGoiMap.values().iterator();
                if (iterator.hasNext()) {
                    DonHang.setID_Order(iterator.next().ID_Order);
                    OrdersDao dt = new OrdersDao();
                    Orders d = dt.selectById(DonHang.getID_Order());
                    txtGustNum.setText(d.getNumberOfGuests()+"");
                }
                updateThongTinDonHang();
                //Nếu đơn hàng đang được thanh toán -> chuyển qua màn hình thanh toán
                InvoicesDao d = new InvoicesDao();
                if(d.getID(DonHang.getID_Order())!=-1){
                    callThanhToan();
                }
                
                
            } else {
                DonHang.setID_Order(-1);
                updateThongTinDonHang();
            }

        } catch (Exception e) {
            msg.Error("Bàn không hợp lệ");
        }

    }

    //Nhóm chức năng cho phần món đã gọi
    private Map<Integer, Model_Mon_Da_Goi> dsDaGoiMap = new HashMap<>();
    private Map<Integer, Model_Mon_Da_Goi> dsMonDangGoiMap = new HashMap<>();

    //Lấy dữ liệu đã gọi từ database nếu có
    private void fillDsDaGoi() {
        int ID_table = DonHang.getID_Table();
        dsDaGoiMap = proDao.GetGetItemsByTableID(ID_table);
        if (!dsDaGoiMap.isEmpty()) {
            refreshMonAnOrder();
        }

    }

    // Các phương thức để thêm sản phẩm vào dsDaGoiMap và dsMonDangGoiMap
    private void addDanhSachGoiMon(Model_Mon_Da_Goi data, Map<Integer, Model_Mon_Da_Goi> map) {
        int idItem = data.getId();

        if (map.containsKey(idItem)) {
            // Nếu sản phẩm đã tồn tại trong map, cập nhật số lượng
            Model_Mon_Da_Goi existingData = map.get(idItem);
            existingData.setSl(existingData.getSl() + 1);
        } else {
            // Nếu sản phẩm chưa tồn tại trong map, thêm mới vào map
            map.put(idItem, data);
        }

        // Refresh danh sách hiển thị
        refreshMonAnOrder();
    }

    // Các phương thức để giảm và tăng số lượng sản phẩm trong dsDaGoiMap và dsMonDangGoiMap
    private void adjustQuantity(int idItem, int quantityAdjustment, Map<Integer, Model_Mon_Da_Goi> map) {
        if (map.containsKey(idItem)) {
            Model_Mon_Da_Goi data = map.get(idItem);
            int newQuantity = data.getSl() + quantityAdjustment;
            if (newQuantity >= 0) { // Kiểm tra số lượng không âm
                data.setSl(newQuantity);
                refreshMonAnOrder();
            } else {
                // Xử lý khi số lượng âm (nếu cần)
            }
        }
    }

    // Các phương thức để tăng và giảm số lượng sản phẩm
    public void increaseQuantity(int idItem, Map<Integer, Model_Mon_Da_Goi> map) {
        adjustQuantity(idItem, 1, map); // Tăng số lượng lên 1
    }

    public void decreaseQuantity(int idItem, Map<Integer, Model_Mon_Da_Goi> map) {
        adjustQuantity(idItem, -1, map); // Giảm số lượng đi 1
    }

    private void refreshMonAnOrder() {
        updateStatus();
        pnMonAnOrder.removeAll();
        int total = 0;
        if (dsDaGoiMap != null) {
            total += fillToScreenDSMon(dsDaGoiMap, false);
            MonAnDaOrder item = new MonAnDaOrder(new Model_Mon_Da_Goi(Model_Mon_Da_Goi.ItemType.TieuDe));
            pnMonAnOrder.add(item);
        }

        total += fillToScreenDSMon(dsMonDangGoiMap, true);

        lblTotal.setText(fNum.parseString(total) + "đ");
    }

    //Hàm fill lên màn hình
    private int fillToScreenDSMon(Map<Integer, Model_Mon_Da_Goi> map, boolean edit) {
        int width = (dsDaGoiMap.size() + dsMonDangGoiMap.size()) * 50 + 40;
        pnMonAnOrder.setPreferredSize(new Dimension(370, width));
        int total = 0;
        for (Model_Mon_Da_Goi data : map.values()) {
            total += data.getTotal();
            if(!Auth.user.getID_role().equals("MG") && !Auth.user.getID_role().equals("CS") && edit == false){
                data.setType(Model_Mon_Da_Goi.ItemType.DaGoi);
            }
            MonAnDaOrder item = new MonAnDaOrder(data);
            item.Giam.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    decreaseQuantity(data.getId(), map);
                }
            });

            item.Tang.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    increaseQuantity(data.getId(), map);
                }
            });

            pnMonAnOrder.add(item);
        }

        pnMonAnOrder.revalidate();
        pnMonAnOrder.repaint();
        return total;
    }

    //Thuật toán so sánh hai mảng và thực hiện chức năng khi ấn submit
    //So sánh 2 mảng nếu sản phẩm chưa tồn tại? đẩy lên database
    //Nếu sản phẩm đã tồn tại cộng lại và update lên databse 
    //Nếu số lượng sản phẩm là 0 thì xóa sản phẩm 
    private void sync() {
        for (Model_Mon_Da_Goi item1 : dsDaGoiMap.values()) {
            for (Model_Mon_Da_Goi item2 : dsMonDangGoiMap.values()) {
                if (item1.getId() == item2.getId()) {
                    int newSL = item1.getSl() + item2.getSl();
                    item1.setSl(newSL);
                    item2.setSl(0);
                }
            }
        }

        Iterator<Model_Mon_Da_Goi> iterator = dsDaGoiMap.values().iterator();
        while (iterator.hasNext()) {
            Model_Mon_Da_Goi item = iterator.next();
            if (item.getSl() == 0) {
                iterator.remove();
                OrderDetailDao dt = new OrderDetailDao();
                dt.delete(item.getId(), item.ID_Order);
            }
        }

        iterator = dsMonDangGoiMap.values().iterator();
        while (iterator.hasNext()) {
            Model_Mon_Da_Goi item = iterator.next();
            if (item.getSl() == 0) {
                iterator.remove();
            }
        }

        if (DonHang.getID_Order() == -1) {
            OrdersDao d = new OrdersDao();
            updateThongTinDonHang();
            d.insert(DonHang);
            DonHang.setID_Order(d.GetID());
        }
        OrderDetailDao dt = new OrderDetailDao();
        for (Model_Mon_Da_Goi item : dsDaGoiMap.values()) {
            
            OrderDetail e = new OrderDetail();
            e.setID_Item(item.getId());
            e.setID_Order(DonHang.getID_Order());
            e.setPrice(item.getPrice());
            e.setQuantity(item.getSl());
            e.setTotalPrice();
            dt.update(e);
        }

        for (Model_Mon_Da_Goi i : dsMonDangGoiMap.values()) {
          
            OrderDetail e = new OrderDetail();
            e.setID_Item(i.getId());
            e.setID_Order(DonHang.getID_Order());
            e.setPrice(i.getPrice());
            e.setQuantity(i.getSl());
            e.setTotalPrice();
            dt.insert(e);
        }
        
        msg.Info("Gọi món thành công!");
        dsMonDangGoiMap.clear();
        fillDsDaGoi();
        updateDonHang();
        refreshMonAnOrder();
    }

    
    public void updateDonHang(){
        OrdersDao od = new OrdersDao();
        try {
            DonHang.setNumberOfGuests(Integer.valueOf(txtGustNum.getText()));
            od.update(DonHang);
        } catch (Exception e) {
            DonHang.setNumberOfGuests(0);
            txtGustNum.setText(0+"");
        }
    }
    
    
    private void callThanhToan(){
        
        ThanhToanForm f = new ThanhToanForm(DonHang, tenBan, main);
        this.removeAll();
        this.repaint();
        this.setLayout(new BorderLayout());
        this.add(f,BorderLayout.CENTER);
        this.repaint();
        this.validate();
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
        btnGoiMon = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
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
        txtGustNum.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGustNumCaretUpdate(evt);
            }
        });
        txtGustNum.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtGustNumInputMethodTextChanged(evt);
            }
        });
        txtGustNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGustNumKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGustNumKeyTyped(evt);
            }
        });

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

        btnGoiMon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGoiMon.setText("Gọi món");
        btnGoiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoiMonActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(51, 0, 204));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnGoiMon, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
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
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGoiMon, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
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
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
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
                    .addComponent(ScroolTable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
                    .addComponent(pnController, javax.swing.GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE))
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

    private void btnGoiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoiMonActionPerformed
        sync();
    }//GEN-LAST:event_btnGoiMonActionPerformed

    private void txtGustNumInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtGustNumInputMethodTextChanged

    }//GEN-LAST:event_txtGustNumInputMethodTextChanged

    private void txtGustNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGustNumKeyTyped
      
    }//GEN-LAST:event_txtGustNumKeyTyped

    private void txtGustNumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGustNumKeyPressed
        
    }//GEN-LAST:event_txtGustNumKeyPressed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
      if(!dsMonDangGoiMap.isEmpty()){
            if(
            !msg.Yes_no("Bạn chưa ấn order món mới bạn có muốn tiếp tục thanh toán không?")){
                return;
            }
        }
        if(!msg.Yes_no("Khi ấn thanh toán sẽ không thể quay lại bước này!")){
            return;
        }
        
        callThanhToan();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtGustNumCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGustNumCaretUpdate
        
    }//GEN-LAST:event_txtGustNumCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelTable;
    private javax.swing.JScrollPane ScroolTable;
    private javax.swing.JButton btnGoiMon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton jButton1;
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
        PanelTable.setLayout(new GridLayout((canThiet > 3 ? canThiet : 3), 3, 15, 15)); // GridLayout với số hàng tính được, có thể cộng thêm 1 hàng nếu còn item thừa
        for (MenuItems row : dsItem) {
            MonAnItem item;
            if (row.isAvailable()) {
                item = new MonAnItem(new Model_Item_Menu(row.getItemName(), row.getID_Item(),
                        row.getPrice(), row.getPhoto(), Model_Item_Menu.MenuType.AVAiLABLE));

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
                        addDanhSachGoiMon(data, dsMonDangGoiMap);
                    }
                });
            } else {
                item = new MonAnItem(new Model_Item_Menu(row.getItemName(), row.getID_Item(),
                        row.getPrice(), row.getPhoto(), Model_Item_Menu.MenuType.UNAVAiLABLE));
            }

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

    private void fillItemInBackground(int ID_area) {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Thực hiện công việc tải dữ liệu trong background
                // Gọi hàm fillItem từ SwingWorker
                fillItem(ID_area);
                PanelTable.revalidate();
                for (MonAnItem i : dsMonAn) {
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
