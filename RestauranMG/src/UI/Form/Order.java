
package UI.Form;

import UI.Model.Model_Card_for_Table;


public class Order extends javax.swing.JPanel {

   
    public Order() {
        initComponents();
        listTable1.setOpaque(false);
        
    }

    private void init(){
        listTable1.addItem(new Model_Card_for_Table("A01", 0, 0, Model_Card_for_Table.TableType.NULL));        listTable1.addItem(new Model_Card_for_Table("A01", 0, 0, Model_Card_for_Table.TableType.NULL));
        listTable1.addItem(new Model_Card_for_Table("A02", 0, 0, Model_Card_for_Table.TableType.NULL));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listTable1 = new UI.Compoment.ListTable<>();

        setBackground(new java.awt.Color(204, 204, 255));

        listTable1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private UI.Compoment.ListTable<String> listTable1;
    // End of variables declaration//GEN-END:variables
}
