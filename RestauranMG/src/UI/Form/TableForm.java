
package UI.Form;

import UI.Compoment.tableItem;
import UI.Model.Model_Table;
import java.awt.Dimension;
import Dao.TablesdDao;
import Dao.AreasDao;
import Entity.Areas;
import Entity.Tables;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class TableForm extends javax.swing.JPanel {

   TablesdDao table_DAO = new TablesdDao();
   AreasDao areas_DAO = new AreasDao();
   int selectedArea;
    public TableForm() {
        initComponents();
        fillKhuVuc();
        
    }

    private void fillTable(int ID_area){
        PanelTable.removeAll();
        List<Tables> t = new ArrayList<>();
        t = table_DAO.selectByArea(ID_area);
        
        if(t == null) return;
        for(Tables e : t){
            tableItem item;
            if(e.isIsOccupied()){
                item = new tableItem(new Model_Table(e.getTableName(), 0, 0, Model_Table.TableType.NOTNULL));
            }else{
                item = new tableItem(new Model_Table(e.getTableName()));
            }
            //Bắt sự kiện
            item.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                   item.setBackground(new Color(255, 241, 190));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    item.setColor();
                }
            });
            PanelTable.add(item);
        }
    }
    
    private List<JButton> buttons = new ArrayList<>();

    private void fillKhuVuc() {
        List<Areas> k = areas_DAO.selectAll();
        pnKhuVuc.removeAll();
        if (k == null) {
            return;
        }  
        for (Areas area : k) {
            JButton button = new JButton(area.getAreaName());
            button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            button.setName(area.getID_Area() + "");
            buttons.add(button); // Thêm JButton vào danh sách
            button.addActionListener(e -> {
                selectedArea = area.getID_Area();
                updateButtonsAppearance(); // Cập nhật giao diện của các nút khi có nút được chọn
            });
            pnKhuVuc.add(button);
        }

        updateButtonsAppearance(); // Cập nhật giao diện ban đầu
    }

    private void updateButtonsAppearance() {
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
        PanelTable.setLayout(new java.awt.GridLayout(0, 4, 30, 20));
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
