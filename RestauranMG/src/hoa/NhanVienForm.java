
package hoa;

import Dao.EmployeesDao;
import Entity.Employees;
import Utils.msg;
import button.EventCallBack;
import button.EventTextField;
import java.awt.Color;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class NhanVienForm extends javax.swing.JPanel {
    EmployeesDao dao = new EmployeesDao();
    int row = 0;
    DefaultTableModel model;
    
    public NhanVienForm() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        loading();
        fillTable();
    }
    // loading
    private void loading() {
    txtSearch.addEvent(new EventTextField() {
        @Override
        public void onPressed(EventCallBack call) {
            try {
                // Kiểm tra xem đã nhập thông tin tìm kiếm chưa
                if (txtSearch.getText().isEmpty()) {
                    msg.Info("Vui lòng nhập thông tin tìm kiếm");
                } else {
                    // Giả sử bạn muốn delay 2 giây để simulating loading
                    Thread.sleep(2000);

                    // Thực hiện tìm kiếm theo tên
                    searchByName(txtSearch.getText());

                    if (table.getRowCount() == 0) {
                        msg.Info("Không tìm thấy thông tin");
                    }
                }

                call.done();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        @Override
        public void onCancel() {
            // Không có hành động cần thiết cho onCancel
        }
    });
}


    // fill dữ liệu
    public void fillTable(){
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
    // search theo ten và ký 1 ký tự có trong đó
    private void searchByName(String name) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        // Thiết lập bộ lọc để tìm kiếm theo tên
        RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter("(?i)" + name, 2);
        sorter.setRowFilter(filter);
    }

    public void ChiTiet(){
        System.out.println("ChiTiet() is called.");
        NVChiTiet chiTietForm = new NVChiTiet();

    }
    
    private void showChiTietForm() {
        System.out.println("showChiTietForm() is called.");

        // Lấy chỉ số dòng được chọn
        int selectedRow = table.getSelectedRow();
        System.out.println("Selected Row: " + selectedRow);

        // Kiểm tra xem có dòng nào được chọn không
        if (selectedRow != -1) {
            // Lấy thông tin từ dòng được chọn
            int idEmployee = (int) table.getValueAt(selectedRow, 1); // Giả sử cột 1 là Mã nhân viên
            System.out.println("ID Employee: " + idEmployee);

            // Lấy thông tin nhân viên từ cơ sở dữ liệu hoặc danh sách đã có
            Employees selectedEmployee = dao.selectById(idEmployee);
            System.out.println("Selected Employee: " + selectedEmployee);

            // Hiển thị form NVChiTiet và truyền thông tin
            NVChiTiet chiTietForm = new NVChiTiet();
            chiTietForm.setForm(selectedEmployee);

        } else {
            // Thông báo nếu không có dòng nào được chọn
            msg.Info("Vui lòng chọn một nhân viên trước khi xem chi tiết.");
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new UI.Compoment.Table();
        txtSearch = new button.Search();
        jLabel1 = new javax.swing.JLabel();
        btnThem = new button.Button();

        setBackground(new java.awt.Color(255, 255, 255));

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(206, 151, 32));
        jLabel1.setText("Danh Sách Nhân Viên");

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add-user.png"))); // NOI18N
        btnThem.setText("Thêm nhân viên");
        btnThem.setBorderColor(new java.awt.Color(0, 102, 102));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE))
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed
        showChiTietForm();
    }//GEN-LAST:event_tableMousePressed

    private void btnThemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMousePressed
        ChiTiet();
    }//GEN-LAST:event_btnThemMousePressed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private UI.Compoment.Table table;
    private button.Search txtSearch;
    // End of variables declaration//GEN-END:variables
}
