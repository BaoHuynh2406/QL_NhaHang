
package UI.Form.CaiDat;

import Dao.MenuCategoriesDao;
import Dao.MenuItemDetailDao;
import Dao.MenuItemsDao;
import Dao.ProductsDao;
import Entity.MenuCategories;
import Entity.MenuItemDetail;
import Entity.MenuItems;
import Entity.Products;
import Utils.IMG;
import Utils.fNum;
import Utils.msg;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class ChiTietMenu extends javax.swing.JDialog {

    private MenuItems mnu = new MenuItems();
    QLMenu Parent = new QLMenu();
    public ChiTietMenu(java.awt.Frame parent, boolean modal,MenuItems mnu, QLMenu Parent) {
        super(parent, modal);
        this.Parent = Parent;
        initComponents();
        fillCombox();
        this.mnu = mnu;
        if(mnu.getItemName() == null){
            btnXoa.setEnabled(false);
            return;
        }
        
        txtId.setText(mnu.getID_Item()+"");
        txtName.setText(mnu.getItemName());
        txtGia.setText(mnu.getPrice()+"");
        MenuCategories m = mDao.selectById(mnu.getID_Category());
        cbLoai.setSelectedItem(m);
        if(mnu.isAvailable()){
            cbTrangThai.setSelectedIndex(0);
        }else{
            cbTrangThai.setSelectedIndex(1);
        }
        
        lbAnh.setIcon(IMG.resize("src/IMG/Food/"+mnu.getPhoto(), 300, 160));
        lbPath.setText(mnu.getPhoto());
        fillTableCongThuc();
    }
    
    
    MenuCategoriesDao mDao = new MenuCategoriesDao();
     //Hàm fill lên combobox
    public void fillCombox(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbLoai.getModel();
        model.removeAllElements();
       List<MenuCategories> l = mDao.selectAll();
       for (MenuCategories a : l){
           model.addElement(a); 
       }
    }
    
    public MenuItems getForm(){
        MenuItems e = new MenuItems();
        try {
            e.setID_Item(Integer.valueOf(txtId.getText()));
        } catch (NumberFormatException l) {
            e.setID_Item(0);
        }
        
        e.setItemName(txtName.getText());
        e.setPrice(Integer.valueOf(txtGia.getText()));
        e.setAvailable(cbTrangThai.getSelectedIndex() == 0);
        e.setPhoto(lbPath.getText());
        MenuCategories m = (MenuCategories) cbLoai.getSelectedItem();
        e.setID_Category(m.getID_Category());
        return e;
    }
    
    public void clear(){
        btnXoa.setEnabled(false);
        txtId.setText("");
        txtName.setText("");
        txtGia.setText("");
        lbPath.setText("");
        cbLoai.setSelectedIndex(0);
        cbTrangThai.setSelectedIndex(0);
        lbAnh.setIcon(new ImageIcon());
   }
    
    MenuItemsDao mnuDao = new MenuItemsDao();
    public void Save() {
        mnu = getForm();
        if (mnu.getID_Item() == 0) {
            //Nếu null thì insert
            mnuDao.insert(getForm());
            getTable();
            msg.Info("Thêm mới thành công!");
            clear();
        } else {
            mnuDao.update(mnu);
            getTable();
            msg.Info("Cập nhật thành công!");
        }
        
        Parent.selectTuCombobox();

    }

    
    //Hàm fill Bảng công thức món
    private DefaultTableModel model;
    public void fillTableCongThuc(){
        MenuItemDetailDao mndDao = new MenuItemDetailDao();
        List<MenuItemDetail> detail = mndDao.selectBySql("select * from MenuItemDetail where ID_Item = ?", mnu.getID_Item());
        
        model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        if(detail.isEmpty()) return;
        
        
        ProductsDao pDao = new ProductsDao();
        Products product = new Products();
        DecimalFormat df = new DecimalFormat("#.#");
        for(MenuItemDetail item : detail){
            product = pDao.selectById(item.getID_Product());
            model.addRow(new Object[]{
            item.getID_Product(),
            product.getName(),
            product.getUnit(),
            df.format(item.getQuantity()).replace(",", "."),
            "Xóa"
            });
        }
    }
    
    
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbLoai = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbTrangThai = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        lbAnh = new javax.swing.JLabel();
        lbPath = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new UI.Compoment.CustomTable.Table();
        txtProductID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtProductName = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTieuHao = new javax.swing.JTextField();
        txtDVT = new javax.swing.JLabel();
        btnNhap = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chi tiết menu");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ID:");

        txtName.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txtName.setForeground(new java.awt.Color(51, 0, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tên món:");

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtId.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Loại:");

        cbLoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Thêm loại");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Giá:");

        txtGia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Trạng thái:");

        cbTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Còn món", "Hết món" }));

        lbAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbAnhMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
        );

        lbPath.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbPath.setText("Abc.jpg");

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(204, 51, 0));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/trash.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(0, 102, 51));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save.png"))); // NOI18N
        btnLuu.setText("   Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 153));
        jButton2.setText("Mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Công thức", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Hàng hóa", "Tên hàng", "Đơn vị tính", "Tiêu hao", "Thao tác"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl);

        txtProductID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtProductID.setForeground(new java.awt.Color(255, 153, 51));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("ID Hàng hóa:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tên hàng:");

        txtProductName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtProductName.setEnabled(false);

        btnFind.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFind.setText("Tìm");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tiêu hao:");

        txtTieuHao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTieuHao.setEnabled(false);

        txtDVT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDVT.setText("ĐVT");

        btnNhap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNhap.setForeground(new java.awt.Color(0, 153, 102));
        btnNhap.setText("Nhập");
        btnNhap.setEnabled(false);
        btnNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("Chọn");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtProductID, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFind))
                            .addComponent(jLabel6))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtTieuHao, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDVT, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addComponent(btnNhap)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProductName)
                    .addComponent(btnFind)
                    .addComponent(txtTieuHao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDVT)
                    .addComponent(btnNhap)
                    .addComponent(jButton3))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtGia))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtId))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtName)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbPath, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jLabel3)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbPath)))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
//        Delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        Save();
    }//GEN-LAST:event_btnLuuActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void lbAnhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnhMousePressed
       String path = selectAnh();
       if(path == null){
           return;
       }
       if( !IMG.save(path, "src/IMG/Food/")){
           msg.Error("Có lỗi trong quá trình truy xuất ảnh!");
           return;
       }
       File selectedFile = new File(path);
       String icPath = "src/IMG/Food/"+selectedFile.getName();
       lbPath.setText(selectedFile.getName());
       lbAnh.setIcon(IMG.resize(icPath, 300, 160));
    }//GEN-LAST:event_lbAnhMousePressed

    public void chonSanPham(String id){
        txtProductID.setText(id);
        find();
    }
    
    private Products find(){
        String id = txtProductID.getText();
        ProductsDao pDao = new ProductsDao();
        Products product = pDao.SearchFirst(id, id);
        if (product == null) {
            msg.Warning("Không có");
            clearInputFields();
            return null;
        }
        txtProductID.setText(product.getID_product());
        txtProductName.setText(product.getName());
        txtTieuHao.setEnabled(true);
        txtDVT.setText(product.getUnit());
        btnNhap.setEnabled(true);
        return product;
    }
    
    private void Nhap() {
        Products product = find();
        double tieuHao = 0;

        try {
            tieuHao = Double.parseDouble(txtTieuHao.getText());
        } catch (NumberFormatException e) {
            try {
                tieuHao = (double) Integer.valueOf(txtTieuHao.getText());
            } catch (NumberFormatException e1) {
                msg.Error("Lỗi định dạng");
                 return;
            }  
        }

        for (int i = 0; i < model.getRowCount(); i++) {
            String id = (String) model.getValueAt(i, 0);
            double tieuHaocu = 0;

            try {
                tieuHaocu = Double.parseDouble((String) model.getValueAt(i, 3));
            } catch (Exception e) {
                msg.Error(e.getMessage());
                return;
            }
            if (id.equals(product.getID_product())) {
                double soMoi = tieuHao + tieuHaocu;
                model.setValueAt(soMoi, i, 3);
                clearInputFields();
                return;
            }
        }
        
        model.addRow(new Object[]{
            product.getID_product(),
            product.getName(),
            product.getUnit(),
            txtTieuHao.getText(),
            "Xóa"
        });

        txtTieuHao.setEnabled(false);
        btnNhap.setEnabled(false);
        clearInputFields();
    }

// Method to clear input fields
    private void clearInputFields() {
        txtTieuHao.setEnabled(false);
        btnNhap.setEnabled(false);
        txtProductID.setText("");
        txtProductName.setText("");
        txtTieuHao.setText("");
        txtDVT.setText("ĐVT");
    }
    
    
    public void getTable(){
        //Xóa hết các công thức cũ
        MenuItemDetailDao dao = new MenuItemDetailDao();
        dao.deleteAll(mnu.getID_Item());
        //Thêm lại từng hàng trong bảng vào database
        for(int i=0; i<tbl.getRowCount(); i++){
            MenuItemDetail mnuD = new MenuItemDetail();
            mnuD.setID_Item(mnu.getID_Item());
            mnuD.setID_Product((String) tbl.getValueAt(i, 0));
            try {
                BigDecimal b = new BigDecimal((String) tbl.getValueAt(i, 3));
                b = b.setScale(1, RoundingMode.HALF_UP);
                mnuD.setQuantity(b.doubleValue());
            } catch (Exception e) {
                mnuD.setQuantity(-1);
            }
            
            dao.insert(mnuD);
        }
    }
    
    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        find();
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapActionPerformed
        Nhap();
    }//GEN-LAST:event_btnNhapActionPerformed

    private void tblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMousePressed
        int selectRow = tbl.getSelectedRow();
        int selectCol = tbl.getSelectedColumn();
        if(selectCol == 4){
            if(msg.Yes_no("Xóa?")){
                model.removeRow(selectRow);
            }
        }
    }//GEN-LAST:event_tblMousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       DsHangHoa f = new DsHangHoa(new JFrame(), true);
       f.setModal(true);
       f.form = this;
       f.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChiTietMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MenuItems mnu = new MenuItems();
                QLMenu m = new QLMenu();
                ChiTietMenu dialog = new ChiTietMenu(new javax.swing.JFrame(), true, mnu, m);
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
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnNhap;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbLoai;
    private javax.swing.JComboBox<String> cbTrangThai;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbAnh;
    private javax.swing.JLabel lbPath;
    private UI.Compoment.CustomTable.Table tbl;
    private javax.swing.JLabel txtDVT;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtProductID;
    private javax.swing.JTextField txtProductName;
    private javax.swing.JTextField txtTieuHao;
    // End of variables declaration//GEN-END:variables

public String selectAnh(){
       JFileChooser fileChooser = new JFileChooser();
       //Bộ lọc
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hình ảnh", "png", "jpg");
        fileChooser.setFileFilter(filter);
        // Hiển thị hộp thoại chọn tệp
        int returnValue = fileChooser.showOpenDialog(null);
        
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // Lấy đường dẫn tệp được chọn
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            return filePath;
        } else {
            // Người dùng đã hủy bỏ việc chọn tệp hoặc xảy ra lỗi
            return null;
        }
    }
}
