package ThongKe;

import Dao.procDao;
import Utils.XDate;
import Utils.fNum;
import Utils.msg;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MonBanChay extends javax.swing.JPanel {

    class DateChose {

        public Type type;

        public static enum Type {
            DAY, MONTH, YEAR
        }
    }

    private DateChose chose = new DateChose();

    public MonBanChay() {
        initComponents();
        chose.type = MonBanChay.DateChose.Type.DAY;
        Date now = new Date();
        txtDate.setDate(now);
       
        btnNgay.setSelected(true);
    }

    private void adjustDATE(int adjust) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(txtDate.getDate());

        if (chose.type == MonBanChay.DateChose.Type.DAY) {
            calendar.add(Calendar.DATE, adjust);
        } else if (chose.type == MonBanChay.DateChose.Type.MONTH) {
            calendar.add(Calendar.MONTH, adjust);
        } else {
            calendar.add(Calendar.YEAR, adjust);
        }

        txtDate.setDate(calendar.getTime());

    }

    public void fillTable() {

        procDao dao = new procDao();
        List<Object[]> l;

        if (chose.type == MonBanChay.DateChose.Type.DAY) {
            
            l = dao.GetSalesReport(txtDate.getDate(), txtDate.getDate());

        } else if (chose.type == MonBanChay.DateChose.Type.MONTH) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(txtDate.getDate());
            // Thiết lập ngày đầu tiên của tháng
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date firstDayOfMonth = calendar.getTime();

            // Thiết lập ngày cuối cùng của tháng
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date lastDayOfMonth = calendar.getTime();

            l = dao.GetSalesReport(firstDayOfMonth, lastDayOfMonth);

        } else {
           
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(txtDate.getDate());
            // Thiết lập ngày đầu tiên của năm
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            Date firstDayOfYear = calendar.getTime();

            // Thiết lập ngày cuối cùng của năm
            calendar.add(Calendar.YEAR, 1);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            Date lastDayOfYear = calendar.getTime();

            l = dao.GetSalesReport(firstDayOfYear, lastDayOfYear);
           
        }

       
        fill(l);

    }

    public void fill(List<Object[]> l) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        int i = 1;
        for (Object[] row : l) {
            model.addRow(new Object[]{
                i++,
                row[0],
                row[1],
                row[2],
                fNum.parseString((int) row[3]) +"đ"});

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNgay = new javax.swing.JButton();
        btnNam = new javax.swing.JButton();
        btnThang = new javax.swing.JButton();
        btnGiam = new javax.swing.JButton();
        btnTang = new javax.swing.JButton();
        btnXuatFile = new button.Button();
        txtDate = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new UI.Compoment.CustomTable.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setOpaque(false);

        btnNgay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNgay.setText("Ngày");
        btnNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayActionPerformed(evt);
            }
        });

        btnNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNam.setText("Năm");
        btnNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNamActionPerformed(evt);
            }
        });

        btnThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThang.setText("Tháng");
        btnThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThang, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNam, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnNam, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnThang, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnGiam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGiam.setText("<");
        btnGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiamActionPerformed(evt);
            }
        });

        btnTang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTang.setText(">");
        btnTang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTangActionPerformed(evt);
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

        txtDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDatePropertyChange(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TOP", "Mã món", "Tên món", "SL bán ra", "Doanh thu"
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
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(600, 600, 600)
                .addComponent(btnGiam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGiam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayActionPerformed
        btnNgay.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNgay.setForeground(Color.BLUE);

        btnThang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnThang.setForeground(Color.BLACK);
        btnNam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnNam.setForeground(Color.BLACK);
        txtDate.setDateFormatString("d, MMM, y");
        chose.type = DateChose.Type.DAY;


        btnNgay.setSelected(true);
        btnThang.setSelected(false);
        btnNam.setSelected(false);
        
        fillTable();
    }//GEN-LAST:event_btnNgayActionPerformed

    private void btnNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNamActionPerformed
        btnNam.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNam.setForeground(Color.BLUE);

        btnThang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnThang.setForeground(Color.BLACK);
        btnNgay.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnNgay.setForeground(Color.BLACK);
        txtDate.setDateFormatString("yyyy");
        chose.type = DateChose.Type.YEAR;
    

        btnNam.setSelected(true);
        btnThang.setSelected(false);
        btnNgay.setSelected(false);
        
        fillTable();
    }//GEN-LAST:event_btnNamActionPerformed

    private void btnThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThangActionPerformed
        btnThang.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnThang.setForeground(Color.BLUE);

        btnNgay.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnNgay.setForeground(Color.BLACK);
        btnNam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnNam.setForeground(Color.BLACK);

        txtDate.setDateFormatString("MMM, yyyy");
        chose.type = DateChose.Type.MONTH;
     

        btnThang.setSelected(true);
        btnNam.setSelected(false);
        btnNgay.setSelected(false);
        
        
        fillTable();
    }//GEN-LAST:event_btnThangActionPerformed

    private void btnGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiamActionPerformed
        adjustDATE(-1);
    }//GEN-LAST:event_btnGiamActionPerformed

    private void btnTangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTangActionPerformed
        adjustDATE(1);
    }//GEN-LAST:event_btnTangActionPerformed

    private void btnXuatFileMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatFileMousePressed
        // TODO add your handling code here:
        exportToExcel();
    }//GEN-LAST:event_btnXuatFileMousePressed

    private void btnXuatFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXuatFileActionPerformed

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed

    }//GEN-LAST:event_tableMousePressed

    private void txtDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDatePropertyChange
        fillTable();
    }//GEN-LAST:event_txtDatePropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiam;
    private javax.swing.JButton btnNam;
    private javax.swing.JButton btnNgay;
    private javax.swing.JButton btnTang;
    private javax.swing.JButton btnThang;
    private button.Button btnXuatFile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private UI.Compoment.CustomTable.Table table;
    private com.toedter.calendar.JDateChooser txtDate;
    // End of variables declaration//GEN-END:variables


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
}
