package HangHoa;

import Dao.ProductsDao;
import Entity.Products;
import Utils.msg;
import button.EventCallBack;
import button.EventTextField;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class KhoHang extends javax.swing.JPanel {

    int row = 0;
    DefaultTableModel model;
    ProductsDao dao = new ProductsDao();
    Products products = new Products();

    public KhoHang() {
        initComponents();
        model = (DefaultTableModel) table.getModel();
        loading();
        fillTable();
        fillComboBox();
    }

    // loading
    private void loading() {
        search.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    // Giả sử bạn muốn delay 2 giây để simulating loading
                    Thread.sleep(500);

                    // Thực hiện tìm kiếm theo tên hoặc mã
                    fillTable(search.getText());

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

    public void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbb.getModel();
        model.removeAllElements();

        model.addElement("Tất cả");

        // Thêm các sản phẩm từ cơ sở dữ liệu vào JComboBox
        dao.selectAll().forEach(model::addElement);
    }

    public void fillTable() {
        model.setRowCount(0);
        try {
            int i = 1;
            for (Products p : dao.selectAll()) {
                Object[] row = {i++,
                    p.getID_product(),
                    p.getName(),
                    p.getUnit(),
                    p.getQuantity(),
                    p.getPrice()};
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            msg.Error("Có lỗi trong quá trình truy xuất dữ liệu!");
        }
    }

    public void ChonComboBox() {
        Object selectedObject = cbb.getSelectedItem();

        if ("Tất cả".equals(selectedObject)) {
            fillTable();
        } else {
            model.setRowCount(0);
            if (selectedObject instanceof Products) {
                Products selectedProduct = (Products) selectedObject;
                model.addRow(new Object[]{
                    1,
                    selectedProduct.getID_product(),
                    selectedProduct.getName(),
                    selectedProduct.getUnit(),
                    selectedProduct.getQuantity(),
                    selectedProduct.getPrice()
                });
            }
        }
    }

    // fill dữ liệu theo tên hoặc mã
    public void fillTable(String key) {
        model.setRowCount(0);
        try {
            int i = 1;
            for (Products p : dao.Search(key)) {
                Object[] row = {i++,
                    p.getID_product(),
                    p.getName(),
                    p.getUnit(),
                    p.getQuantity(),
                    p.getPrice()};
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            msg.Error("Có lỗi trong quá trình truy xuất dữ liệu!");
        }
    }

    // xuất file Excel
    public void exportToExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn vị trí để lưu");

        int luaChon = fileChooser.showSaveDialog(null);
        if (luaChon == JFileChooser.APPROVE_OPTION) {
            try {
                File fileLuu = fileChooser.getSelectedFile();
                String duongDan = fileLuu.getAbsolutePath() + ".xlsx"; // Thêm phần mở rộng .xlsx

                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Dữ liệu ChuyenDe");

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int soHang = model.getRowCount();
                int soCot = model.getColumnCount();

                // Tạo tiêu đề
                Row dongTieuDe = sheet.createRow(0);
                for (int cot = 0; cot < soCot; cot++) {
                    Cell cell = dongTieuDe.createCell(cot);
                    cell.setCellValue(model.getColumnName(cot));
                }

                // Đổ dữ liệu
                for (int hang = 0; hang < soHang; hang++) {
                    Row dongExcel = sheet.createRow(hang + 1);
                    for (int cot = 0; cot < soCot; cot++) {
                        Cell cell = dongExcel.createCell(cot);
                        cell.setCellValue(model.getValueAt(hang, cot).toString());
                    }
                }

                try (FileOutputStream outputStream = new FileOutputStream(duongDan)) {
                    workbook.write(outputStream);
                }

                msg.Info("Xuất Excel thành công!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msg.Error("Lỗi khi xuất Excel: " + e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new UI.Compoment.CustomTable.Table();
        search = new button.Search();
        btnXuatFile = new button.Button();
        cbb = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hàng", "Tên hàng", "Đơn vị", "Số lượng", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
        }

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        btnXuatFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/excel.png"))); // NOI18N
        btnXuatFile.setText("Xuất File");
        btnXuatFile.setRadius(40);
        btnXuatFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXuatFileMousePressed(evt);
            }
        });
        btnXuatFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatFileActionPerformed(evt);
            }
        });

        cbb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(199, 161, 69));
        jLabel1.setText("Thông Tin Kho Hàng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 955, Short.MAX_VALUE)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbb, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void cbbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbItemStateChanged
        // TODO add your handling code here:
        ChonComboBox();
    }//GEN-LAST:event_cbbItemStateChanged

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void btnXuatFileMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatFileMousePressed
        // TODO add your handling code here:
        exportToExcel();
    }//GEN-LAST:event_btnXuatFileMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btnXuatFile;
    private javax.swing.JComboBox<String> cbb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private button.Search search;
    private UI.Compoment.CustomTable.Table table;
    // End of variables declaration//GEN-END:variables
}
