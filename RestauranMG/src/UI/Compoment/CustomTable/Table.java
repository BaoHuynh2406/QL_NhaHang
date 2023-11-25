package UI.Compoment.CustomTable;

import UI.Compoment.CustomTable.TableHeader;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {
    public Table() {
        
        setShowHorizontalLines(true);
        
        setGridColor(new Color(230, 230, 230));
        
        setRowHeight(40);
        
        getTableHeader().setReorderingAllowed(false);
        
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                return header;
            }
        });

        // Thiết lập DefaultTableCellRenderer để canh giữa văn bản trong ô
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                com.setBackground(Color.WHITE);
                setBorder(noFocusBorder);

                // Thiết lập canh giữa văn bản trong ô
                setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
                setFont(new Font("Segoe UI", Font.PLAIN, 15));
                if (selected) {
                    com.setForeground(SystemColor.MAIN_COLOR_1);
                } else {
                    com.setForeground(new Color(102, 102, 102));
                }
                return com;
            }
        });
   
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
    
     
}
