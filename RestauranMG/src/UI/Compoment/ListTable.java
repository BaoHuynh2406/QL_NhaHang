//package UI.Compoment;
//
//import UI.Model.Model_Table;
//import java.awt.Component;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionAdapter;
//import javax.swing.BorderFactory;
//import javax.swing.DefaultListCellRenderer;
//import javax.swing.DefaultListModel;
//import javax.swing.JList;
//import javax.swing.ListCellRenderer;
//import javax.swing.SwingUtilities;
//
//public class ListTable<E extends Object> extends JList<E> {
//
//    private final DefaultListModel model;
//    private int selectedIndex = -1;
//    private int overIndex = -1;
//    private EventTableSelected event;
//
//    public void addEventTableSelected(EventTableSelected event) {
//        this.event = event;
//    }
//
//    public ListTable() {
//        model = new DefaultListModel();
//        setModel(model);
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent me) {
//                if (SwingUtilities.isLeftMouseButton(me)) {
//                    int index = locationToIndex(me.getPoint());
//                    Object o = model.getElementAt(index);
//                    if (o instanceof Model_Table) {
//                        Model_Table table = (Model_Table) o;
//                        selectedIndex = index;
//                        if (event != null) {
//                            event.selected(index);
//                        }
//                    } else {
//                        selectedIndex = index;
//                    }
//                    repaint();
//                }
//            }
//
//            @Override
//            public void mouseExited(MouseEvent me) {
//                overIndex = -1;
//                repaint();
//            }
//        });
//        addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent me) {
//                int index = locationToIndex(me.getPoint());
//                if (index != overIndex) {
//                    Object o = model.getElementAt(index);
//                    if (o instanceof Model_Table) {
//                        Model_Table table = (Model_Table) o;
//                        overIndex = index;
//                    }
//                    repaint();
//                }
//            }
//        });
//
//    }
//
//    @Override
//    public ListCellRenderer<? super E> getCellRenderer() {
//        return new DefaultListCellRenderer() {
//            
//            @Override
//            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected, boolean focus) {
//                Model_Table data;
//                if (o instanceof Model_Table) {
//                    data = (Model_Table) o;
//                } else {
//                    data = new Model_Table("", 0,0, Model_Table.TableType.NULL);
//                }
//                tableItem item = new tableItem(data);
//                item.setSelected(selectedIndex == index);
//                item.setOver(overIndex == index);
//                
//               
//                
//                return item;
//            }
//
//        };
//    }
//    
//
//    public void addItem(Model_Table data) {
//        model.addElement(data);
//    }
//}
