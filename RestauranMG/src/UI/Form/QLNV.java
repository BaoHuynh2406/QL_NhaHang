package UI.Form;

import Dao.EmployeesDao;
import Dao.RoleDao;
import Entity.Employees;
import Entity.Role;
import Utils.IMG;
import Utils.Validate;
import Utils.XDate;
import Utils.msg;
import button.EventCallBack;
import button.EventTextField;
import java.awt.Color;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.naming.ldap.Rdn;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class QLNV extends javax.swing.JPanel {

    int row = -1;
    DefaultTableModel model;
    EmployeesDao dao = new EmployeesDao();
    RoleDao cvdao = new RoleDao();
    Employees employees = new Employees();

    public QLNV() {
        initComponents();
        txtMaNV.setEditable(false);
        model = (DefaultTableModel) table.getModel();
        loading();
        fillTable();
        fillComboBox();

    }

    // loading
    private void loading() {
        txtSearch.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    // Kiểm tra xem đã nhập thông tin tìm kiếm chưa

                    // Giả sử bạn muốn delay 2 giây để simulating loading
                    Thread.sleep(500);

                    // Thực hiện tìm kiếm theo tên
                    
                    fillTable(txtSearch.getText());

                    call.done();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Thread interrupted: " + e);
                } catch (Exception e) {
                    System.err.println("Unexpected exception: " + e);
                }
            }

            @Override
            public void onCancel() {
                // Không có hành động cần thiết cho onCancel
            }
        });
    }

    // fill dữ liệu
    public void fillTable() {
        model.setRowCount(0);
        try {
            List<Employees> list = dao.selectAll();
            int i = 1;
            for (Employees nv : list) {
                Object[] row = {i,
                    nv.getID_Employee(),
                    nv.getFullName(),
                    nv.getID_role(),
                    nv.getPhoneNumber()};
                model.addRow(row);
                i++;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            msg.Error("Có lỗi trong quá trình truy xuất dữ liệu!");
        }
    }
    
    // fill dữ liệu
    public void fillTable(String key) {
        model.setRowCount(0);
        try {
            List<Employees> list = dao.Search(key);
            int i = 1;
            for (Employees nv : list) {
                Object[] row = {i,
                    nv.getID_Employee(),
                    nv.getFullName(),
                    nv.getID_role(),
                    nv.getPhoneNumber()};
                model.addRow(row);
                i++;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            msg.Error("Có lỗi trong quá trình truy xuất dữ liệu!");
        }
    }


   

    // chọn ảnh
    public String selectAnh() {
        JFileChooser fileChooser = new JFileChooser();
        // Đặt đường dẫn mặc định khi mở hộp thoại chọn tệp
        File defaultDirectory = new File("D:/Photos");
        fileChooser.setCurrentDirectory(defaultDirectory);
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


    //fill comboBox
    public void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbChuVu.getModel();
        model.removeAllElements();
        List<Role> list = cvdao.selectAll();
        for (Role cv : list) {
            model.addElement(((String) cv.getID_role()));
        }
    }

    public void setForm(Employees employees) {
        txtMaNV.setText(employees.getID_Employee() + "");
        txtTenNV.setText(employees.getFullName());
        rdoNam.setSelected(employees.isSex());
        rdoNu.setSelected(!employees.isSex());

        try {
            txtNgaySinh.setText(XDate.convertToDMY(employees.getBirthday()));
        } catch (Exception e) {
            txtNgaySinh.setText("");
        }

        txtSDT.setText(employees.getPhoneNumber());
        txtEmail.setText(employees.getEmail());
        txtDiaChi.setText(employees.getAddress());

        cbbChuVu.setSelectedItem(employees.getID_role());

        String path = employees.getPhoto();
        lblAnh.setIcon(IMG.setAvatar(path));
    }

    public void edit() {
       
        if (this.row >= 0) {
            int maNV = (Integer) table.getValueAt(this.row, 1);
            employees = dao.selectById(maNV);
            this.setForm(employees);
            jTabbedPane1.setSelectedIndex(1);
            this.updateStatus();
        }
    }

    public Employees getForm() {
        Employees employees = new Employees();
        try {
            int maNV = Integer.parseInt(txtMaNV.getText());
            employees.setID_Employee(maNV);
            employees.setFullName(txtTenNV.getText());
            employees.setPassword(new String(txtPass.getPassword()));
            employees.setSex(rdoNam.isSelected());
            employees.setBirthday(XDate.convertToYMD(txtNgaySinh.getText()));
            employees.setPhoneNumber(txtSDT.getText());
            employees.setEmail(txtEmail.getText());
            employees.setAddress(txtDiaChi.getText());
            employees.setPhoto(lblDuongDan.getText());
            employees.setID_role((String) cbbChuVu.getSelectedItem());
        } catch (NumberFormatException e) {
            msg.Error("Lỗi chuyển đổi dữ liệu! Vui lòng kiểm tra lại.");
            System.out.println(e.getMessage());
        }
        return employees;
    }

    public void updateStatus() {
        boolean edit = (this.row >= 0);
        // Trạng thái form
        txtMaNV.setEnabled(!edit);
        btnThem.setEnabled(!edit);
        btnXoa.setEnabled(edit);
        btnLuu.setEnabled(true);
    }

    private void clear() {
        // Thực hiện các bước để làm mới form, ví dụ:
        txtMaNV.setText(dao.getNewID()+"");
        txtTenNV.setText("");
        txtPass.setText("");
        txtNgaySinh.setText("");
        txtSDT.setText("");
        txtEmail.setText("");
        lblDuongDan.setText("Chưa chọn ảnh!");
        rdoNam.setSelected(true);
        lblAnh.setIcon(IMG.setAvatar(null));
        String defaultRole = "MG"; // Giả sử "MG" là mã chức vụ mặc định
        cbbChuVu.setSelectedItem(defaultRole);
        row = -1;
        // Cập nhật trạng thái
        updateStatus();
    }

    public void insert() {
         if(Validate()){
            return;
        }
        Employees employees = getForm();
        try {
            dao.insert(employees);
            clear();
            row = -1; // Cập nhật giá trị của row
            msg.Info("Thêm mới thành công!");
            fillTable();
            jTabbedPane1.setSelectedIndex(0);
        } catch (Exception e) {
            System.out.println("-> "+e.getMessage());
            msg.Error("Có lỗi trong quá trình thêm mới nhân viên!");
        }
    }

    // Bắt lỗi
    private boolean Validate() {
        if (Validate.isNotEmpty(txtTenNV, txtTenNV.getText(), "Tên Nhân Viên")
                && Validate.isDateDDMMYYYY(txtNgaySinh, txtNgaySinh.getText())
                && Validate.isPhoneNumber(txtSDT, txtSDT.getText())
                && Validate.isEmail(txtEmail, txtEmail.getText()))
                 {
            return false;
        } if(!new String(txtPass.getPassword()).isEmpty()) {
            if (Validate.isLength(txtPass, new String(txtPass.getPassword()), 6, "Mật khẩu")) {  
                return false;
            }
        }
        return true;
    }
    public void update() {
        if(Validate()){
            return;
        }
        Employees employees = getForm();
        try {
            if (employees.getPassword().isEmpty()) {
                dao.updateNotPass(employees);
            } else {
                dao.update(employees);

            }
            row = -1; // Cập nhật giá trị của row
            fillTable();
            jTabbedPane1.setSelectedIndex(0);
            msg.Info("Cập nhật thành công!");
        } catch (Exception e) {
            msg.Error("Có lỗi trong quá trình cập nhật nhân viên!");
        }
    }

    // xoa
    public void delete() {
        
       int manv;
        try {
            manv = Integer.parseInt(txtMaNV.getText());
        } catch (NumberFormatException e) {
            msg.Error("Lỗi chuyển đổi dữ liệu!");
            System.out.println(e.getMessage());
            return;
        }

        if (msg.Yes_no("Bạn thực sự muốn xóa nhân viên này?")) {
            try {
                System.out.println("delete " + manv);
                dao.delete(manv);
                clear();
                fillTable();
                jTabbedPane1.setSelectedIndex(0);
                msg.Info("Đã xóa");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msg.Error("Lỗi!, vui lòng thử lại sau.");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new UI.Compoment.CustomTable.Table();
        txtSearch = new button.Search();
        jLabel1 = new javax.swing.JLabel();
        btnThem = new button.Button();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        cbbChuVu = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnThem1 = new button.Button();
        btnXoa = new button.Button();
        btnLuu = new button.Button();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        lblDuongDan = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã nhân viên", "Tên nhân viên", "Chức vụ", "SĐT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        txtSearch.setMinimumSize(new java.awt.Dimension(150, 40));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Danh Sách Nhân Viên");

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add-user.png"))); // NOI18N
        btnThem.setText("Thêm nhân viên");
        btnThem.setBorderColor(new java.awt.Color(0, 102, 102));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.setMaximumSize(new java.awt.Dimension(150, 40));
        btnThem.setMinimumSize(new java.awt.Dimension(150, 40));
        btnThem.setRadius(30);
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThemMousePressed(evt);
            }
        });
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1400, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Danh Sách", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã nhân viên");

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtEmailMousePressed(evt);
            }
        });
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tên nhân viên");

        txtTenNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtTenNV.setForeground(new java.awt.Color(255, 102, 0));
        txtTenNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTenNVMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Mật khẩu");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("SĐT");

        txtMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Email");

        txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNgaySinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtNgaySinhMousePressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Địa chỉ");

        txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSDTMousePressed(evt);
            }
        });
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Chức vụ");

        lblAnh.setBackground(new java.awt.Color(255, 255, 255));
        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblAnhMousePressed(evt);
            }
        });

        txtPass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPassMousePressed(evt);
            }
        });

        cbbChuVu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbbChuVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbChuVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChuVuActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Giới tính");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Ngày sinh");

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });

        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add-user.png"))); // NOI18N
        btnThem1.setText("Mới");
        btnThem1.setBorderColor(new java.awt.Color(0, 153, 153));
        btnThem1.setRadius(30);
        btnThem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThem1MousePressed(evt);
            }
        });
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 204, 204));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setBorderColor(new java.awt.Color(204, 51, 0));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.setRadius(30);
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXoaMousePressed(evt);
            }
        });
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(51, 204, 0));
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/download.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setBorderColor(new java.awt.Color(0, 102, 102));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLuu.setRadius(30);
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLuuMousePressed(evt);
            }
        });
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        txtDiaChi.setColumns(20);
        txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDiaChi.setRows(5);
        jScrollPane2.setViewportView(txtDiaChi);

        lblDuongDan.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblDuongDan.setForeground(new java.awt.Color(234, 72, 72));
        lblDuongDan.setText("Chưa chọn ảnh ?");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông tin chi tiết");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDuongDan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(17, 17, 17)))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPass)
                                    .addComponent(txtNgaySinh)
                                    .addComponent(txtSDT)
                                    .addComponent(txtEmail)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(cbbChuVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(89, 89, 89))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(100, 100, 100)
                                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rdoNam))
                                            .addComponent(jLabel6))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaNV)
                                            .addComponent(txtTenNV))))))
                        .addGap(257, 257, 257))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDuongDan)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel9))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbbChuVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(276, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Chi tiết", jPanel2);

        add(jTabbedPane1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed
        clear();
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_btnThemMousePressed

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed
        this.row = table.getSelectedRow();
        edit();
    }//GEN-LAST:event_tableMousePressed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (row >= 0) {
            // Nếu row >= 0, tức là đang cập nhật
            update();
        } else {
            // Ngược lại, đang thêm mới
            insert();
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        clear();
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void cbbChuVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChuVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbChuVuActionPerformed

    private void lblAnhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMousePressed
        String path = selectAnh();
        if (path == null) {
            return;
        }
        if (!IMG.save(path, "src/IMG/")) {
            msg.Error("Có lỗi trong quá trình truy xuất ảnh!");
            return;
        }
        File selectedFile = new File(path);
        lblDuongDan.setText(selectedFile.getName());
        lblAnh.setIcon(IMG.setLogo(selectedFile.getName()));
    }//GEN-LAST:event_lblAnhMousePressed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnThem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MousePressed
        clear();
    }//GEN-LAST:event_btnThem1MousePressed

    private void btnXoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMousePressed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaMousePressed

    private void btnLuuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMousePressed
        // TODO add your handling code here:
        if (row >= 0) {
            // Editing mode
            update();
        } else {
            // Adding mode
            insert();
        }
    }//GEN-LAST:event_btnLuuMousePressed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void txtTenNVMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenNVMousePressed
       txtTenNV.setBackground(Color.white);
    }//GEN-LAST:event_txtTenNVMousePressed

    private void txtPassMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPassMousePressed
        txtPass.setBackground(Color.white);

    }//GEN-LAST:event_txtPassMousePressed

    private void txtNgaySinhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgaySinhMousePressed
        txtNgaySinh.setBackground(Color.white);

    }//GEN-LAST:event_txtNgaySinhMousePressed

    private void txtSDTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSDTMousePressed
        txtSDT.setBackground(Color.white);

    }//GEN-LAST:event_txtSDTMousePressed

    private void txtEmailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmailMousePressed
       txtEmail.setBackground(Color.white);

    }//GEN-LAST:event_txtEmailMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnLuu;
    private button.Button btnThem;
    private button.Button btnThem1;
    private button.Button btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChuVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblDuongDan;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private UI.Compoment.CustomTable.Table table;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtSDT;
    private button.Search txtSearch;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
