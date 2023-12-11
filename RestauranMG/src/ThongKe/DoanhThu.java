package ThongKe;

import Dao.OrdersDao;
import Dao.procDao;
import Utils.Auth;
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
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.formula.ptg.TblPtg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DoanhThu extends javax.swing.JPanel {

    private DateChose chose = new DateChose();

    public DoanhThu() {
        initComponents();
        chose.type = DateChose.Type.DAY;
        Date now = new Date();
        txtDate.setDate(now);

        btnNgay.setSelected(true);
        if (!Auth.isMG()) {
            btnNgay.setEnabled(false);
            btnThang.setEnabled(false);
            btnNam.setEnabled(false);
            btnGiam.setEnabled(false);
            btnTang.setEnabled(false);
            txtDate.setEnabled(false);

        }
    }

    private void adjustDATE(int adjust) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(txtDate.getDate());

        if (chose.type == DateChose.Type.DAY) {
            calendar.add(Calendar.DATE, adjust);
        } else if (chose.type == DateChose.Type.MONTH) {
            calendar.add(Calendar.MONTH, adjust);
        } else {
            calendar.add(Calendar.YEAR, adjust);
        }

        txtDate.setDate(calendar.getTime());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        cbb = new javax.swing.JComboBox<>();
        search = new button.Search();
        lblTong = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        btnNgay = new javax.swing.JButton();
        btnNam = new javax.swing.JButton();
        btnThang = new javax.swing.JButton();
        btnGiam = new javax.swing.JButton();
        btnTang = new javax.swing.JButton();
        btnXuatFile = new button.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new UI.Compoment.CustomTable.Table();
        lbSoKhach = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        cbb.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cả ngày", "Ca sáng", "Ca tối" }));
        cbb.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbbPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        search.setToolTipText("Nhập mã hóa đơn");

        lblTong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTong.setForeground(new java.awt.Color(255, 102, 102));
        lblTong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTong.setText("Doanh Thu: ");

        txtDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtDatePropertyChange(evt);
            }
        });

        jPanel1.setOpaque(false);

        btnNgay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNgay.setText("Ngày");
        buttonGroup1.add(btnNgay);
        btnNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNgayActionPerformed(evt);
            }
        });

        btnNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNam.setText("Năm");
        buttonGroup1.add(btnNam);
        btnNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNamActionPerformed(evt);
            }
        });

        btnThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThang.setText("Tháng");
        buttonGroup1.add(btnThang);
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
                .addComponent(btnNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnThang, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnNam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số bàn", "Mã hóa đơn", "Ngày", "Giờ ra", "Tổng"
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

        lbSoKhach.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbSoKhach.setForeground(new java.awt.Color(51, 0, 204));
        lbSoKhach.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbSoKhach.setText("Số khách:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                        .addComponent(btnGiam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cbb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTang)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTong, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbSoKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(btnGiam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(cbb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTong)
                    .addComponent(lbSoKhach))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnXuatFile, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbbPopupMenuWillBecomeInvisible
        fillTable();
    }//GEN-LAST:event_cbbPopupMenuWillBecomeInvisible

    private void tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMousePressed
        int row = table.getSelectedRow();
        int id_hd = (int) table.getValueAt(row, 1);
        ChiTietHoaDon f = new ChiTietHoaDon(new JFrame(), true, id_hd);
        f.time = (java.sql.Time) table.getValueAt(row, 3);
        f.fillSc(id_hd);
        f.setVisible(true);
    }//GEN-LAST:event_tableMousePressed

    private void txtDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtDatePropertyChange
        fillTable();
    }//GEN-LAST:event_txtDatePropertyChange

    private void btnThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThangActionPerformed
        btnThang.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnThang.setForeground(Color.BLUE);

        btnNgay.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnNgay.setForeground(Color.BLACK);
        btnNam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnNam.setForeground(Color.BLACK);

        txtDate.setDateFormatString("MMM, yyyy");
        chose.type = DateChose.Type.MONTH;
        cbb.setEnabled(false);

        btnThang.setSelected(true);
        btnNam.setSelected(false);
        btnNgay.setSelected(false);
        fillTable();
    }//GEN-LAST:event_btnThangActionPerformed

    private void btnNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNgayActionPerformed
        btnNgay.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNgay.setForeground(Color.BLUE);

        btnThang.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnThang.setForeground(Color.BLACK);
        btnNam.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnNam.setForeground(Color.BLACK);
        txtDate.setDateFormatString("d, MMM, y");
        chose.type = DateChose.Type.DAY;
        cbb.setEnabled(true);

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
        cbb.setEnabled(false);

        btnNam.setSelected(true);
        btnThang.setSelected(false);
        btnNgay.setSelected(false);

        fillTable();
    }//GEN-LAST:event_btnNamActionPerformed

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

    public void fillTable() {

        procDao dao = new procDao();
        List<Object[]> l;

        if (chose.type == DateChose.Type.DAY) {

            if (cbb.getSelectedItem().equals("Cả ngày")) {
                l = dao.GetInvoiceDetailsByDateTimeRange(txtDate.getDate(), 0, 24);
            } else if (cbb.getSelectedItem().equals("Ca sáng")) {
                l = dao.GetInvoiceDetailsByDateTimeRange(txtDate.getDate(), 0, 17);
            } else {
                l = dao.GetInvoiceDetailsByDateTimeRange(txtDate.getDate(), 17, 24);
            }

        } else if (chose.type == DateChose.Type.MONTH) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(txtDate.getDate());
            // Thiết lập ngày đầu tiên của tháng
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date firstDayOfMonth = calendar.getTime();

            // Thiết lập ngày cuối cùng của tháng
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date lastDayOfMonth = calendar.getTime();

            l = dao.GetInvoiceDetailsByBetwentTime(firstDayOfMonth, lastDayOfMonth);

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

            l = dao.GetInvoiceDetailsByBetwentTime(firstDayOfYear, lastDayOfYear);
        }

        fill(l);

    }

    public void fill(List<Object[]> l) {
        int total = 0;
        int soKhach = 0;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Object[] row : l) {
            model.addRow(new Object[]{
                row[0],
                row[1],
                XDate.convertToDMY((Date) row[2]),
                row[3],
                fNum.parseString((int) row[4]) + "đ"
            });

            total += (int) row[4];
            soKhach += new OrdersDao().selectById((int) row[1]).getNumberOfGuests();
        }

        lblTong.setText("Doanh thu: " + fNum.parseString(total) + "đ");
        lbSoKhach.setText("Số khách: " + fNum.parseString(soKhach));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiam;
    private javax.swing.JButton btnNam;
    private javax.swing.JButton btnNgay;
    private javax.swing.JButton btnTang;
    private javax.swing.JButton btnThang;
    private button.Button btnXuatFile;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbb;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbSoKhach;
    private javax.swing.JLabel lblTong;
    private button.Search search;
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

    class DateChose {

        public Type type;

        public static enum Type {
            DAY, MONTH, YEAR
        }
    }
}
