
package UI.Form;

import UI.Compoment.tableItem;
import UI.Model.Model_Table;
import java.awt.Dimension;
import Dao.TablesdDao;
import Dao.AreasDao;
import Dao.procDao;
import Entity.Areas;
import Entity.Tables;
import Controller.EventTableSelected;
import UI.Main;
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

public class TableForm extends javax.swing.JPanel {
    private EventTableSelected event;

    public void addEventTableSelected(EventTableSelected event) {
        this.event = event;
    }
    
    
    
   TablesdDao table_DAO = new TablesdDao();
   procDao proDao = new procDao();
   AreasDao areas_DAO = new AreasDao();
   int selectedArea;
   private Main main;
    public TableForm(Main main) {
        this.main = main;
        initComponents();
         ScroolTable.getVerticalScrollBar().setUnitIncrement(30);
        fillKhuVuc();
        
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

        int numRows = t.size() / 4; // Số hàng cần thiết
        int remainder = t.size() % 4; // Số item còn lại sau khi chia hết cho 5
        int canThiet = numRows + (remainder > 0 ? 2 : 0);
       
        PanelTable.setPreferredSize(new Dimension(5*300, canThiet*155));
        PanelTable.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 20)); // GridLayout với số hàng tính được, có thể cộng thêm 1 hàng nếu còn item thừa
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
                  item.setPreferredSize(new Dimension(300,170));
            }else{
                
                item = new tableItem(new Model_Table(ID, TableName, total, numGust));
                 item.setPreferredSize(new Dimension(300,170));
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
                    event.selected(ID);
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

    private void fillKhuVuc() {
        pnKhuVuc.removeAll();
        buttons.clear();
        List<Areas> k = areas_DAO.selectAll();
        if (k == null) {
            return;
        }  
        JButton b = new JButton("Tất cả");
        b.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        b.setName("-1");
        buttons.add(b);
        pnKhuVuc.add(b);
        b.addActionListener(e -> {
            selectedArea = -1;
            update();
        });

        for (Areas area : k) {
            JButton button = new JButton(area.getAreaName());
            button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            button.setName(area.getID_Area() + "");
            buttons.add(button); // Thêm JButton vào danh sách
            button.addActionListener(e -> {
                selectedArea = area.getID_Area();
                update();
            });
            pnKhuVuc.add(button);
        }
        selectedArea = -1;
        update(); // Cập nhật giao diện ban đầu
    }

    private void update() {
        for (JButton button : buttons) {
            if (button.getName().equals(String.valueOf(selectedArea))) {
                button.setFont(new Font("Segoe UI", Font.BOLD, 14));
                button.setForeground(new Color(22, 72, 99));
                fillTable(selectedArea);
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
        pnKhuVuc = new javax.swing.JPanel();

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
            .addGap(0, 798, Short.MAX_VALUE)
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );

        ScroolTable.setViewportView(PanelTable);

        pnKhuVuc.setBackground(new java.awt.Color(232, 232, 232));
        pnKhuVuc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnKhuVuc.setPreferredSize(new java.awt.Dimension(44, 50));
        pnKhuVuc.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnKhuVuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ScroolTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnKhuVuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ScroolTable, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelTable;
    private javax.swing.JScrollPane ScroolTable;
    private javax.swing.JPanel pnKhuVuc;
    // End of variables declaration//GEN-END:variables
}
