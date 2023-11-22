
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
    private void loading(){
        txtSearch.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    for (int i = 1; i <= 100; i++) {
                        lbl.setText("Loading " + i);
                        Thread.sleep(10);
                    }
                    // Sau khi tải xong, thực hiện tìm kiếm theo tên
                    searchByName(txtSearch.getText());

                    //kiểm tra kết quả
                    if (table.getRowCount() == 0) {
                        msg.Info("Không tìm thấy thông tin");
                    }
                    call.done();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

            @Override
            public void onCancel() {

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
        ChiTietNhanVienForm chiTietForm = new ChiTietNhanVienForm();
        chiTietForm.setVisible(true);
    }
    
    private void showChiTietForm() {
        // Lấy chỉ số dòng được chọn
        int selectedRow = table.getSelectedRow();

        // Kiểm tra xem có dòng nào được chọn không
        if (selectedRow != -1) {
            // Lấy thông tin từ dòng được chọn
            int idEmployee = (int) table.getValueAt(selectedRow, 1); // Giả sử cột 1 là Mã nhân viên

            // Lấy thông tin nhân viên từ cơ sở dữ liệu hoặc danh sách đã có
            Employees selectedEmployee = dao.selectById(idEmployee);

            // Hiển thị form ChiTiet và truyền thông tin
            ChiTietNhanVienForm chiTietForm = new ChiTietNhanVienForm();
            chiTietForm.setForm(selectedEmployee);
            chiTietForm.setVisible(true);
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
        lbl = new javax.swing.JLabel();
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

        lbl.setFont(new java.awt.Font("Segoe UI", 2, 8)); // NOI18N
        lbl.setForeground(new java.awt.Color(165, 112, 112));
        lbl.setText("Loading ... ");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(206, 151, 32));
        jLabel1.setText("Danh Sách Nhân Viên");

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add-user.png"))); // NOI18N
        btnThem.setText("Thêm nhân viên");
        btnThem.setBorderColor(new java.awt.Color(255, 255, 255));
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE))
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addComponent(lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl;
    private UI.Compoment.Table table;
    private button.Search txtSearch;
    // End of variables declaration//GEN-END:variables
}
