package capaPresentacio.views;
import capaPresentacio.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author linhu
 */
public class mainView extends javax.swing.JFrame {
    
    /**
     * Creates new form mainView
     */
    CardLayout cardLayout;
    public mainView() {
        initComponents();

        cardLayout = (CardLayout) (mainPanel.getLayout());
        //jocPanel
        mainPanel.add(new jocPanel(cardLayout, mainPanel), "jocCard");
        //generator
        mainPanel.add(new generatorView(), "generatorCard");
        //ranking
        mainPanel.add(new rankingView(), "rankingCard");
        //users
        mainPanel.add(new usersPanel(), "usuariCard");
        //default view
        cardLayout.show(mainPanel, "jocCard");

        // Make the window fullscreen
        setExtendedState(MAXIMIZED_BOTH);
    }

    //comprova si estas en la pantalla de partida
    private boolean comprovaJocEnCurs() {
        Component visibleComponent = null;
        for (Component comp : mainPanel.getComponents()) {
            if (comp.isVisible()) {
                visibleComponent = comp;
            }
        }

        return visibleComponent instanceof partidaPanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boxPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        jocButton = new javax.swing.JButton();
        crearKenkenButton = new javax.swing.JButton();
        rankingButton = new javax.swing.JButton();
        usuariButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kenken");
        setMinimumSize(new java.awt.Dimension(1700, 1000));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        boxPanel.setBackground(new java.awt.Color(237, 224, 212));

        headerPanel.setBackground(new java.awt.Color(237, 224, 212));
        headerPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        headerPanel.setLayout(new java.awt.GridLayout(1, 4, 10, 0));

        jocButton.setBackground(new java.awt.Color(111, 94, 83));
        jocButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jocButton.setForeground(new java.awt.Color(237, 224, 212));
        jocButton.setText("Joc");
        jocButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jocButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jocButtonActionPerformed(evt);
            }
        });
        headerPanel.add(jocButton);

        crearKenkenButton.setBackground(new java.awt.Color(111, 94, 83));
        crearKenkenButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        crearKenkenButton.setForeground(new java.awt.Color(237, 224, 212));
        crearKenkenButton.setText("Crear Kenken");
        crearKenkenButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        crearKenkenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearKenkenButtonActionPerformed(evt);
            }
        });
        headerPanel.add(crearKenkenButton);

        rankingButton.setBackground(new java.awt.Color(111, 94, 83));
        rankingButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rankingButton.setForeground(new java.awt.Color(237, 224, 212));
        rankingButton.setText("Ranking");
        rankingButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rankingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rankingButtonActionPerformed(evt);
            }
        });
        headerPanel.add(rankingButton);

        usuariButton.setBackground(new java.awt.Color(111, 94, 83));
        usuariButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        usuariButton.setForeground(new java.awt.Color(237, 224, 212));
        usuariButton.setText("Usuari");
        usuariButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        usuariButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariButtonActionPerformed(evt);
            }
        });
        headerPanel.add(usuariButton);

        mainPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout boxPanelLayout = new javax.swing.GroupLayout(boxPanel);
        boxPanel.setLayout(boxPanelLayout);
        boxPanelLayout.setHorizontalGroup(
            boxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
            .addGroup(boxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(boxPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(boxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        boxPanelLayout.setVerticalGroup(
            boxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
            .addGroup(boxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(boxPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        getContentPane().add(boxPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //inicialitzacions abans de canviar de pantalla
    private void canviarCard(String card) {
        switch (card) {
            case "jocCard":
                for (Component comp : mainPanel.getComponents()) {
                    if (comp instanceof jocPanel) {
                        ((jocPanel) comp).updateListKenkens();
                        jocPanel.updateListJocs();
                        break;
                    }
                }
                break;
            case "generatorCard":
                //no hi ha res
                break;
            case "rankingCard":
                //no hi ha res
                break;
            case "usuariCard":
                usersPanel.showStats();
                break;
        }
        cardLayout.show(mainPanel, card);
    }

    private void rankingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankingButtonActionPerformed
        if (comprovaJocEnCurs()) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas a punt de sortir del joc, vols guardar abans de sortir?", "Confirmació", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                CtrlPresentacio.guardarJoc();
            }
        }
        canviarCard("rankingCard");
    }//GEN-LAST:event_rankingButtonActionPerformed

    private void jocButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jocButtonActionPerformed
        if (comprovaJocEnCurs()) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas a punt de sortir del joc, vols guardar abans de sortir?", "Confirmació", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                CtrlPresentacio.guardarJoc();
            }
        }
        canviarCard("jocCard");
    }//GEN-LAST:event_jocButtonActionPerformed

    private void usuariButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariButtonActionPerformed
        if (comprovaJocEnCurs()) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas a punt de sortir del joc, vols guardar abans de sortir?", "Confirmació", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                CtrlPresentacio.guardarJoc();
            }
        }
        canviarCard("usuariCard");

    }//GEN-LAST:event_usuariButtonActionPerformed

    private void crearKenkenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearKenkenButtonActionPerformed
        if (comprovaJocEnCurs()) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Estas a punt de sortir del joc, vols guardar abans de sortir?", "Confirmació", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                CtrlPresentacio.guardarJoc();
            }
        }
        canviarCard("generatorCard");
    }//GEN-LAST:event_crearKenkenButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try {
            // Set system look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boxPanel;
    private javax.swing.JButton crearKenkenButton;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jocButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton rankingButton;
    private javax.swing.JButton usuariButton;
    // End of variables declaration//GEN-END:variables
}