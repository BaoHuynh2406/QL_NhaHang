package UI.Form.CaiDat;

import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

public class CaiDatNhaHang extends javax.swing.JPanel {

    public CaiDatNhaHang() {
        initComponents();
        formKhuVuc();
        loadFormHangHoa();
    }

    private void loadFormMenu() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                formMenu();
                return null;
            }

            @Override
            protected void done() {

            }
        };

        worker.execute();
    }

    private void loadFormHangHoa() {
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                formHangHoa();
                return null;
            }

            @Override
            protected void done() {

                loadFormMenu();
            }
        };

        worker.execute();
    }

    private void formKhuVuc() {
        QLKhuVuc khuVuc = new QLKhuVuc();
        tab.add(khuVuc);
        tab.setTitleAt(0, "QL Khu vực - Bàn     ");
        tab.setIconAt(0, new ImageIcon("src/IMG/round-table.png"));
    }

    private void formMenu() {
        QLMenu menu = new QLMenu();
        tab.add(menu);
        tab.setTitleAt(2, "QL Menu     ");
        tab.setIconAt(2, new ImageIcon("src/IMG/m.png"));
    }

    private void formHangHoa() {
        QLKhoHang khoHang = new QLKhoHang();
        tab.add(khoHang);
        tab.setTitleAt(1, "QL Kho Hàng    ");
        tab.setIconAt(1, new ImageIcon("src/IMG/box.png"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new javax.swing.JTabbedPane();

        tab.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tab;
    // End of variables declaration//GEN-END:variables
}
