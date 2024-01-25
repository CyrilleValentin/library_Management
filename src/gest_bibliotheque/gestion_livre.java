/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gest_bibliotheque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cyrille
 */
public class gestion_livre extends javax.swing.JFrame {

    PreparedStatement pst = null;

    /**
     * Creates new form Enregistrement_livre
     */
    public gestion_livre() {
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(750, 500);
        initComponents();
        show_table();
        show_models() ;
    }
      private void show_models() {

        try {
            Connexion con = new Connexion();
            pst = con.con.prepareStatement("SELECT nom FROM categorie");
            ResultSet Rs = pst.executeQuery();
            Vector<String> categories = new Vector<>();
            while (Rs.next()) {
                String categoryName = Rs.getString("nom");
                categories.add(categoryName);
                
            }
             DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(categories);
             comboCategorie.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(gestion_categories.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void show_table() {
        int CC;

        try {
            Connexion con = new Connexion();
            pst = con.con.prepareStatement("SELECT *FROM livres");
            ResultSet Rs = pst.executeQuery();
            ResultSetMetaData RSMD = Rs.getMetaData();
            CC = RSMD.getColumnCount();
            DefaultTableModel DFT = (DefaultTableModel) jTable1.getModel();
            DFT.setRowCount(0);
            while (Rs.next()) {
                Vector v2 = new Vector();
                for (int i = 1; i <= CC; i++) {
                    v2.add(Rs.getString("nom"));
                    v2.add(Rs.getString("description"));
                      v2.add(Rs.getString("quantite"));
                    v2.add(Rs.getString("auteur"));
                    v2.add(Rs.getString("annee"));
                  
                }
                DFT.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(gestion_livre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAuteur = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtAnnee = new javax.swing.JTextField();
        txtQuantite = new javax.swing.JTextField();
        btnSupprimer = new javax.swing.JButton();
        comboCategorie = new javax.swing.JComboBox<>();
        btnModifier = new javax.swing.JButton();
        btnEnregistrer = new javax.swing.JButton();
        btnRetour = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel1.setText("Formulaire d'enregistrement des livres");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 20, 810, 50);

        txtAuteur.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        txtAuteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAuteurActionPerformed(evt);
            }
        });
        jPanel1.add(txtAuteur);
        txtAuteur.setBounds(520, 160, 260, 40);

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel4.setText("Auteur");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(460, 160, 50, 30);

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel5.setText("Nom");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(90, 110, 40, 30);

        jLabel6.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel6.setText("Description");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(50, 160, 90, 30);

        txtName.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        txtName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });
        jPanel1.add(txtName);
        txtName.setBounds(140, 100, 260, 40);

        txtDescription.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        txtDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescriptionActionPerformed(evt);
            }
        });
        jPanel1.add(txtDescription);
        txtDescription.setBounds(140, 160, 260, 40);

        jLabel8.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel8.setText("Quantité");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(60, 230, 70, 30);

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel9.setText("Année");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(460, 230, 46, 23);

        jLabel10.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel10.setText("Catégories");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(430, 110, 76, 20);

        txtAnnee.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        txtAnnee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnneeActionPerformed(evt);
            }
        });
        jPanel1.add(txtAnnee);
        txtAnnee.setBounds(520, 220, 260, 40);

        txtQuantite.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        txtQuantite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantiteActionPerformed(evt);
            }
        });
        jPanel1.add(txtQuantite);
        txtQuantite.setBounds(140, 220, 260, 40);

        btnSupprimer.setBackground(new java.awt.Color(255, 51, 0));
        btnSupprimer.setFont(new java.awt.Font("Calibri Light", 1, 24)); // NOI18N
        btnSupprimer.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprimer.setText("Supprimer");
        btnSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerActionPerformed(evt);
            }
        });
        jPanel1.add(btnSupprimer);
        btnSupprimer.setBounds(490, 280, 140, 40);

        comboCategorie.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jPanel1.add(comboCategorie);
        comboCategorie.setBounds(520, 100, 260, 40);

        btnModifier.setFont(new java.awt.Font("Calibri Light", 1, 24)); // NOI18N
        btnModifier.setText("Modifier");
        jPanel1.add(btnModifier);
        btnModifier.setBounds(330, 280, 120, 40);

        btnEnregistrer.setBackground(new java.awt.Color(51, 204, 0));
        btnEnregistrer.setFont(new java.awt.Font("Calibri Light", 1, 24)); // NOI18N
        btnEnregistrer.setForeground(new java.awt.Color(255, 255, 255));
        btnEnregistrer.setText("Enregistrer");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerActionPerformed(evt);
            }
        });
        jPanel1.add(btnEnregistrer);
        btnEnregistrer.setBounds(140, 280, 150, 40);

        btnRetour.setFont(new java.awt.Font("Calibri Light", 1, 24)); // NOI18N
        btnRetour.setText("Retour");
        btnRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetourActionPerformed(evt);
            }
        });
        jPanel1.add(btnRetour);
        btnRetour.setBounds(670, 280, 110, 40);

        jTable1.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nom", "Description", "Quantité", "Auteur", "Année"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 350, 840, 250);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAuteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAuteurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAuteurActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescriptionActionPerformed

    private void txtAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnneeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnneeActionPerformed

    private void txtQuantiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantiteActionPerformed

    private void btnRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetourActionPerformed
        gestion_livre.super.dispose();
        Menu menu = new Menu();
        menu.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnRetourActionPerformed

    private void btnSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerActionPerformed
        try {
            // TODO add your handling code here:

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            int selectedIndex = jTable1.getSelectedRow();
            String id = model.getValueAt(selectedIndex, 0).toString();
            Connexion con = new Connexion();
            pst = con.con.prepareStatement("Delete from livres where nom = ?");

            pst.setString(1, id);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Enregistrement Supprimé");
            show_table();

            txtName.setText("");
            txtDescription.setText("");
            txtAuteur.setText("");
            txtAnnee.setText("");
            txtQuantite.setText("");

        } catch (SQLException ex) {
            Logger.getLogger(gestion_livre.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupprimerActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        String nom, description, auteur, annee, quantite;

        nom = txtName.getText();
        description = txtDescription.getText();
        auteur = txtAuteur.getText();
        annee = txtAnnee.getText();
        quantite = txtQuantite.getText();
        if (nom.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer votre nom");
        } else if (description.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer votre prénoms");
        } else if (auteur.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer votre age");
        } else if (annee.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer votre adresse");
        } else if (quantite.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer votre adresse");
        } else {

            try {
                Connexion con = new Connexion();
                pst = con.con.prepareStatement("insert into livres (nom, description, auteur, annee, quantite)values(?,?,?,?,?)");

                pst.setString(1, nom);
                pst.setString(2, description);
                pst.setString(3, auteur);
                pst.setString(4, annee);
                pst.setString(5, quantite);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Livre ajouté avec succès");

                show_table();
                txtName.setText("");
                txtDescription.setText("");
                txtAuteur.setText("");
                txtAnnee.setText("");
                txtQuantite.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(gestion_livre.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(gestion_livre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gestion_livre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gestion_livre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gestion_livre.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gestion_livre().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnRetour;
    private javax.swing.JButton btnSupprimer;
    private javax.swing.JComboBox<String> comboCategorie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtAnnee;
    private javax.swing.JTextField txtAuteur;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtQuantite;
    // End of variables declaration//GEN-END:variables
}
