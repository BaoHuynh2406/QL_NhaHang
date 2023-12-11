package UI.Compoment.MainMenu;

import Controller.EventMenuSelected;
import UI.Model.Model_Menu;

public class MainMenu extends javax.swing.JPanel {

    public EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        listMenu1.addEventMenuSelected(event);
    }

    public MainMenu() {
        initComponents();
        listMenu1.setOpaque(false);
    }

    //QUản lý
    public void MG() {
        listMenu1.Clear();
        listMenu1.addItem(new Model_Menu("Order", "Gọi món", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("Product", "Hàng hóa", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("nhanvien", "QL Nhân viên", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("caidat", "QL Nhà hàng", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("thongke", "Báo cáo & TK", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("info", "Giới thiệu", Model_Menu.MenuType.MENU));
       
    }

    //Phục vụ
    public void SV() {
        listMenu1.Clear();
        listMenu1.addItem(new Model_Menu("Order", "Gọi món", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("info", "Giới thiệu", Model_Menu.MenuType.MENU));
     
    }

    //Thu Ngân
    public void CS() {
        listMenu1.Clear();
        listMenu1.addItem(new Model_Menu("Order", "Gọi món", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("thongke", "Báo cáo & TK", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("info", "Giới thiệu", Model_Menu.MenuType.MENU));
    }

    //Bếp
    public void KS() {
        listMenu1.Clear();
        listMenu1.addItem(new Model_Menu("Product", "Hàng hóa", Model_Menu.MenuType.MENU));
        listMenu1.addItem(new Model_Menu("info", "Giới thiệu", Model_Menu.MenuType.MENU));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listMenu1 = new UI.Compoment.MainMenu.ListMenu<>();

        setBackground(new java.awt.Color(155, 190, 200));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private UI.Compoment.MainMenu.ListMenu<String> listMenu1;
    // End of variables declaration//GEN-END:variables
}
