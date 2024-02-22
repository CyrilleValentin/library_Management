/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gest_bibliotheque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Cyrille
 */
public class Dash extends javax.swing.JFrame {

    PreparedStatement pst = null;

    /**
     * Creates new form Dash
     */
    public Dash() {

        initComponents();
        show_models();
        this.setResizable(false);
        show_table_categorie();
        this.setLocationRelativeTo(null);
        showlivres();
        showlivresEm();
        showHistoriqueEmp();

    }

    private void showHistoriqueEmp() {
        int CC;

        try {
            Connexion con = new Connexion();
            pst = con.con.prepareStatement("SELECT *FROM emprunts where etat=false");
            ResultSet Rs = pst.executeQuery();
            ResultSetMetaData RSMD = Rs.getMetaData();
            CC = RSMD.getColumnCount();
            DefaultTableModel DFT = (DefaultTableModel) jTable4.getModel();
            DFT.setRowCount(0);
            while (Rs.next()) {
                Vector v2 = new Vector();
                for (int i = 0; i <= CC; i++) {
                    v2.add(Rs.getString("id_emprunt"));
                    v2.add(Rs.getString("nom_etu"));
                    v2.add(Rs.getString("filiere_etu"));
                    v2.add(Rs.getString("nom_livre"));
                    v2.add(Rs.getString("quantite_emprunt"));
                    v2.add(Rs.getString("bibliothecaire"));
                    v2.add(Rs.getString("date_debut"));
                    v2.add(Rs.getString("date_fin"));

                }
                DFT.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showlivresEm() {
        int CC;

        try {
            Connexion con = new Connexion();
            pst = con.con.prepareStatement("SELECT *FROM livres");
            ResultSet Rs = pst.executeQuery();
            ResultSetMetaData RSMD = Rs.getMetaData();
            CC = RSMD.getColumnCount();
            DefaultTableModel DFT = (DefaultTableModel) jTable5.getModel();
            DFT.setRowCount(0);
            while (Rs.next()) {
                Vector v2 = new Vector();
                for (int i = 0; i <= CC; i++) {
                    v2.add(Rs.getString("id_livre"));
                    v2.add(Rs.getString("nom"));
                    v2.add(Rs.getString("description"));
                    v2.add(Rs.getString("quantite"));
                    v2.add(Rs.getString("auteur"));
                    v2.add(Rs.getString("annee"));

                }
                DFT.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showlivres() {
        int CC;

        try {
            Connexion con = new Connexion();
            pst = con.con.prepareStatement("SELECT *FROM livres");
            ResultSet Rs = pst.executeQuery();
            ResultSetMetaData RSMD = Rs.getMetaData();
            CC = RSMD.getColumnCount();
            DefaultTableModel DFT = (DefaultTableModel) jTable2.getModel();
            DFT.setRowCount(0);
            while (Rs.next()) {
                Vector v2 = new Vector();
                for (int i = 0; i <= CC; i++) {
                    v2.add(Rs.getString("id_livre"));
                    v2.add(Rs.getString("nom"));
                    v2.add(Rs.getString("description"));
                    v2.add(Rs.getString("quantite"));
                    v2.add(Rs.getString("auteur"));
                    v2.add(Rs.getString("annee"));

                }
                DFT.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getQuantite(String nomLivre) {
        int quantite = 0; // Initialiser à -1 pour indiquer que la catégorie n'a pas été trouvée
        try {
            Connexion con = new Connexion();
            PreparedStatement pstmt = con.con.prepareStatement("SELECT quantite FROM livres WHERE nom = ?");
            pstmt.setString(1, nomLivre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                quantite = rs.getInt("quantite");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantite;
    }

    private int getIdCategorie(String nomCategorie) {
        int idCategorie = -1; // Initialiser à -1 pour indiquer que la catégorie n'a pas été trouvée
        try {
            Connexion con = new Connexion();
            PreparedStatement pstmt = con.con.prepareStatement("SELECT id_categorie FROM categorie WHERE nom = ?");
            pstmt.setString(1, nomCategorie);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                idCategorie = rs.getInt("id_categorie");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idCategorie;
    }

    private void show_table_categorie() {
        int CC;

        try {
            Connexion con = new Connexion();
            pst = con.con.prepareStatement("SELECT *FROM categorie");
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
                    v2.add(Rs.getString("rangee"));

                }
                DFT.addRow(v2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
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
        tabbedPaneCustom1 = new raven.tabbed.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        txtDescription1 = new javax.swing.JTextField();
        txtQuantite = new javax.swing.JTextField();
        btnEnregistrer1 = new javax.swing.JButton();
        btnModifier = new javax.swing.JButton();
        btnSupprimer1 = new javax.swing.JButton();
        btnAnnuler = new javax.swing.JButton();
        txtAnnee = new javax.swing.JTextField();
        txtAuteur = new javax.swing.JTextField();
        comboCategorie = new javax.swing.JComboBox<>();
        txtName = new javax.swing.JTextField();
        txtRechercher = new javax.swing.JTextField();
        btnRechercher = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNomEtu = new javax.swing.JTextField();
        txtPenomEtu = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtNomLivre = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCategorieLivre = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtQuantDispo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtFiliereEtu = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        dateEmprunt = new com.toedter.calendar.JDateChooser();
        comboNameBiblio = new javax.swing.JComboBox<>();
        dateRet = new com.toedter.calendar.JDateChooser();
        jLabel20 = new javax.swing.JLabel();
        txtQuantEmprunt = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        btnEnregisterEm = new javax.swing.JButton();
        btnAnn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        txtDescription = new javax.swing.JTextField();
        cboRangee = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEnregistrer = new javax.swing.JButton();
        btn_Supprimer = new javax.swing.JButton();
        btn_Retour = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        txtLivre = new javax.swing.JTextField();
        txtQuant = new javax.swing.JTextField();
        btnValider = new javax.swing.JButton();
        txtNameRetour = new javax.swing.JTextField();
        txtBiblio = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedPaneCustom1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(50, 169, 231));
        tabbedPaneCustom1.setUnselectedColor(new java.awt.Color(176, 215, 235));

        txtDescription1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtDescription1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescription1ActionPerformed(evt);
            }
        });

        txtQuantite.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtQuantite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantiteActionPerformed(evt);
            }
        });

        btnEnregistrer1.setBackground(new java.awt.Color(51, 204, 0));
        btnEnregistrer1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEnregistrer1.setForeground(new java.awt.Color(255, 255, 255));
        btnEnregistrer1.setText("Enregistrer");
        btnEnregistrer1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnEnregistrer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrer1ActionPerformed(evt);
            }
        });

        btnModifier.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnModifier.setText("Modifier");
        btnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifierActionPerformed(evt);
            }
        });

        btnSupprimer1.setBackground(new java.awt.Color(255, 51, 0));
        btnSupprimer1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSupprimer1.setForeground(new java.awt.Color(255, 255, 255));
        btnSupprimer1.setText("Supprimer");
        btnSupprimer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimer1ActionPerformed(evt);
            }
        });

        btnAnnuler.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAnnuler.setText("Retour");
        btnAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerActionPerformed(evt);
            }
        });

        txtAnnee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAnnee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnneeActionPerformed(evt);
            }
        });

        txtAuteur.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtAuteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAuteurActionPerformed(evt);
            }
        });

        comboCategorie.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtName.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtRechercher.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        txtRechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRechercherActionPerformed(evt);
            }
        });
        txtRechercher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRechercherKeyReleased(evt);
            }
        });

        btnRechercher.setBackground(new java.awt.Color(51, 51, 255));
        btnRechercher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRechercher.setForeground(new java.awt.Color(255, 255, 255));
        btnRechercher.setText("Rechercher");
        btnRechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRechercherActionPerformed(evt);
            }
        });

        jTable2.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nom", "Description", "Quantité", "Auteur", "Année"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable2.getColumnModel().getColumn(0).setHeaderValue("id");
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(12);
            jTable2.getColumnModel().getColumn(1).setHeaderValue("Nom");
            jTable2.getColumnModel().getColumn(2).setHeaderValue("Description");
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(10);
            jTable2.getColumnModel().getColumn(3).setHeaderValue("Quantité");
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(15);
            jTable2.getColumnModel().getColumn(4).setHeaderValue("Auteur");
            jTable2.getColumnModel().getColumn(5).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(10);
            jTable2.getColumnModel().getColumn(5).setHeaderValue("Année");
        }

        jLabel10.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel10.setText("Catégories");

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel5.setText("Auteur");

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel9.setText("Année");

        jLabel6.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel6.setText("Nom");

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel7.setText("Description");

        jLabel8.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel8.setText("Quantité");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnEnregistrer1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnModifier)
                        .addGap(37, 37, 37)
                        .addComponent(btnSupprimer1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(txtRechercher, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRechercher)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRechercher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnRechercher, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAuteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnModifier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSupprimer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAnnuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEnregistrer1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(352, 352, 352))))
        );

        tabbedPaneCustom1.addTab("Gestion Livre", jPanel2);

        jLabel1.setText("Nom Etudiant");

        txtNomEtu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomEtuActionPerformed(evt);
            }
        });

        txtPenomEtu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPenomEtuActionPerformed(evt);
            }
        });

        jLabel11.setText("Prénoms Etudiant");

        txtNomLivre.setEditable(false);

        jLabel12.setText("Nom  Livre");

        txtCategorieLivre.setEditable(false);

        jLabel13.setText("Date Emprunt");

        txtQuantDispo.setEditable(false);

        jLabel14.setText("bibliothécaire");

        jLabel15.setText("Quant. emprunt");

        jLabel16.setText("Filière Etudiant");

        jLabel17.setText("Catégorie Livre");

        jLabel18.setText("Date Retour");

        comboNameBiblio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Agent Koffi", "Agent Abalo", "Agent Ali", "Agent Ama" }));
        comboNameBiblio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNameBiblioActionPerformed(evt);
            }
        });

        jLabel20.setText("Quant. dispo");

        jTable5.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nom", "Description", "Quantité", "Auteur", "Année"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(0).setResizable(false);
            jTable5.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable5.getColumnModel().getColumn(1).setResizable(false);
            jTable5.getColumnModel().getColumn(1).setPreferredWidth(12);
            jTable5.getColumnModel().getColumn(3).setResizable(false);
            jTable5.getColumnModel().getColumn(3).setPreferredWidth(10);
            jTable5.getColumnModel().getColumn(4).setResizable(false);
            jTable5.getColumnModel().getColumn(4).setPreferredWidth(15);
            jTable5.getColumnModel().getColumn(5).setResizable(false);
            jTable5.getColumnModel().getColumn(5).setPreferredWidth(10);
        }

        btnEnregisterEm.setText("Enregistrer");
        btnEnregisterEm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregisterEmActionPerformed(evt);
            }
        });

        btnAnn.setText("Annuler");
        btnAnn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(88, 88, 88)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNomEtu, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPenomEtu, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(comboNameBiblio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNomLivre, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCategorieLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(dateEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnEnregisterEm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAnn)))
                        .addGap(97, 97, 97)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(16, 16, 16))
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQuantDispo, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFiliereEtu, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateRet, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(966, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNomEtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPenomEtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txtFiliereEtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCategorieLivre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17)
                                .addComponent(txtQuantDispo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(txtNomLivre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel20)
                                .addComponent(txtQuantEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14)
                                .addComponent(comboNameBiblio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dateEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateRet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnn)
                    .addComponent(btnEnregisterEm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        tabbedPaneCustom1.addTab("Gestion Emprunt", jPanel3);

        jPanel6.setBackground(new java.awt.Color(51, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel8.setBackground(new java.awt.Color(51, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(51, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(51, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(51, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(51, 255, 255));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(51, 255, 255));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(51, 255, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 163, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(256, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Historique", jPanel5);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setText("Description");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("Rangée");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setText("Nom");

        txtNom.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        txtDescription.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        cboRangee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F" }));

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Rangée", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnEnregistrer.setBackground(new java.awt.Color(0, 204, 0));
        btnEnregistrer.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnEnregistrer.setForeground(new java.awt.Color(255, 255, 255));
        btnEnregistrer.setText("Enregistrer");
        btnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnregistrerActionPerformed(evt);
            }
        });

        btn_Supprimer.setBackground(new java.awt.Color(255, 0, 0));
        btn_Supprimer.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btn_Supprimer.setForeground(new java.awt.Color(255, 255, 255));
        btn_Supprimer.setText("Supprimer");
        btn_Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SupprimerActionPerformed(evt);
            }
        });

        btn_Retour.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btn_Retour.setText("Retour");
        btn_Retour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RetourActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addComponent(btnEnregistrer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_Retour)
                                .addGap(110, 110, 110)
                                .addComponent(btn_Supprimer)))
                        .addGap(47, 47, 47)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboRangee, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboRangee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Supprimer)
                    .addComponent(btn_Retour)
                    .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Gestion Catégorie", jPanel4);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num", "Nom Etudiant", "Filière", "Titre Livre", "Quantité", "Bibliothécaire", "Date Emprunt", "Date Retour"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setResizable(false);
            jTable4.getColumnModel().getColumn(0).setPreferredWidth(3);
            jTable4.getColumnModel().getColumn(4).setResizable(false);
            jTable4.getColumnModel().getColumn(5).setResizable(false);
        }

        txtLivre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLivreActionPerformed(evt);
            }
        });

        btnValider.setBackground(new java.awt.Color(51, 204, 0));
        btnValider.setForeground(new java.awt.Color(255, 255, 255));
        btnValider.setText("Valider");
        btnValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValiderActionPerformed(evt);
            }
        });

        jLabel19.setText("Nom Etudiant");

        jLabel21.setText("Titre Livre");

        jLabel22.setText("Quant.Emprunt");

        jLabel23.setText("Bibliothécaire");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(txtNameRetour, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLivre, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(txtQuant, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(txtBiblio, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnValider)
                .addGap(137, 137, 137))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnValider))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNameRetour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21)
                            .addComponent(txtLivre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtQuant, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(txtBiblio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Gestion Retour", jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 1168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        String nom, description, rangee;

        nom = txtNom.getText();
        description = txtDescription.getText();
        rangee = (String) cboRangee.getSelectedItem();
        System.out.println(rangee);
        if (nom.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer le nom");
        } else if (description.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer la  description");
        } else if (rangee.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer la rangée");
        } else {

            try {
                Connexion con = new Connexion();
                pst = con.con.prepareStatement("insert into categorie (nom, description, rangee)values(?,?,?)");

                pst.setString(1, nom);
                pst.setString(2, description);
                pst.setString(3, rangee);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Catégorie ajouté avec succès");

                show_table_categorie();
                txtNom.setText("");
                txtDescription.setText("");
                cboRangee.setSelectedItem("");
            } catch (SQLException ex) {
                Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void btn_SupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SupprimerActionPerformed
        int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ?", "Confirmation de Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            try {
                // TODO add your handling code here:

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                int selectedIndex = jTable1.getSelectedRow();
                String id = model.getValueAt(selectedIndex, 0).toString();
                Connexion con = new Connexion();
                pst = con.con.prepareStatement("Delete from categorie where nom = ?");

                pst.setString(1, id);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Catégorie Supprimé");
                show_table_categorie();

                txtNom.setText("");
                txtDescription.setText("");
                cboRangee.setSelectedItem("");
            } catch (SQLException ex) {
                Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_SupprimerActionPerformed

    private void btn_RetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RetourActionPerformed

    }//GEN-LAST:event_btn_RetourActionPerformed

    private void txtDescription1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescription1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescription1ActionPerformed

    private void txtQuantiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantiteActionPerformed

    private void btnEnregistrer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrer1ActionPerformed
        String nom, description, auteur, annee, quantite;

        nom = txtName.getText();
        description = txtDescription1.getText();
        auteur = txtAuteur.getText();
        annee = txtAnnee.getText();
        quantite = txtQuantite.getText();
        if (nom.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer le nom");
        } else if (description.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer la description");
        } else if (auteur.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer l'auteur ");
        } else if (annee.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer l'année");
        } else if (quantite.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer votre quantité");
        } else if (comboCategorie.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une catégorie");
        } else {
            String categorieNom = comboCategorie.getSelectedItem().toString();
            int idCategorie = getIdCategorie(categorieNom);

            if (idCategorie == -1) {
                JOptionPane.showMessageDialog(null, "La catégorie sélectionnée n'existe pas");
                return;
            }
            try {
                Connexion con = new Connexion();
                pst = con.con.prepareStatement("insert into livres (nom, description, auteur, annee, quantite, id_categorie) values (?, ?, ?, ?, ?, ?)");

                pst.setString(1, nom);
                pst.setString(2, description);
                pst.setString(3, auteur);
                pst.setString(4, annee);
                pst.setString(5, quantite);
                pst.setInt(6, idCategorie); // Insérer l'ID de la catégorie

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Livre ajouté avec succès");

                showlivres();
                showlivresEm();
                txtName.setText("");
                txtDescription1.setText("");
                txtAuteur.setText("");
                txtAnnee.setText("");
                txtQuantite.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEnregistrer1ActionPerformed

    private void btnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifierActionPerformed
        String nom, description, auteur, annee, quantite;

        nom = txtName.getText();
        description = txtDescription1.getText();
        auteur = txtAuteur.getText();
        annee = txtAnnee.getText();
        quantite = txtQuantite.getText();
        if (nom.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer votre nom");
        } else if (description.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer votre description");
        } else if (auteur.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer l'auteur");
        } else if (annee.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer  l'année");
        } else if (quantite.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer la quantité");
        } else {
            try {
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                int selectedIndex = jTable2.getSelectedRow();
                txtName.setText(model.getValueAt(selectedIndex, 0).toString());

                int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
                Connexion con = new Connexion();

                pst = con.con.prepareStatement("update livres set nom =?, description=?, auteur =?, annee =?, quantite = ? where id_livre =?");

                pst.setString(1, nom);
                pst.setString(2, description);
                pst.setString(3, auteur);
                pst.setString(4, annee);
                pst.setString(5, quantite);
                pst.setInt(6, id);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Livre modifié avec succès");

                showlivres();
                txtName.setText("");
                txtDescription1.setText("");
                txtAuteur.setText("");
                txtAnnee.setText("");
                txtQuantite.setText("");

            } catch (SQLException ex) {
                Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnModifierActionPerformed

    private void btnSupprimer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimer1ActionPerformed
        int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ?", "Confirmation de Suppression", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            try {
                // TODO add your handling code here:

                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                int selectedIndex = jTable2.getSelectedRow();
                int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());
                Connexion con = new Connexion();
                pst = con.con.prepareStatement("Delete from livres where id_livre = ?");

                pst.setInt(1, id);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Livre supprimé avec succès");
                showlivres();

                txtName.setText("");
                txtDescription1.setText("");
                txtAuteur.setText("");
                txtAnnee.setText("");
                txtQuantite.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupprimer1ActionPerformed

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        txtName.setText("");
        txtDescription1.setText("");
        txtAuteur.setText("");
        txtAnnee.setText("");
        txtQuantite.setText("");              // TODO add your handling code here:
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void txtAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnneeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnneeActionPerformed

    private void txtAuteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAuteurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAuteurActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtRechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRechercherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercherActionPerformed

    private void btnRechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRechercherActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        jTable2.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtRechercher.getText()));
    }//GEN-LAST:event_btnRechercherActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int selectedIndex = jTable2.getSelectedRow();
        txtName.setText(model.getValueAt(selectedIndex, 1).toString());
        txtDescription1.setText(model.getValueAt(selectedIndex, 2).toString());
        txtAuteur.setText(model.getValueAt(selectedIndex, 4).toString());
        txtAnnee.setText(model.getValueAt(selectedIndex, 5).toString());
        txtQuantite.setText(model.getValueAt(selectedIndex, 3).toString());

    }//GEN-LAST:event_jTable2MouseClicked

    private void txtRechercherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRechercherKeyReleased
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        jTable2.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(txtRechercher.getText()));        // TODO add your handling code here:
    }//GEN-LAST:event_txtRechercherKeyReleased

    private void txtNomEtuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomEtuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomEtuActionPerformed

    private void txtPenomEtuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPenomEtuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPenomEtuActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
        int selectedIndex = jTable5.getSelectedRow();
        txtNomLivre.setText(model.getValueAt(selectedIndex, 1).toString());
        txtQuantDispo.setText(model.getValueAt(selectedIndex, 3).toString());
        txtCategorieLivre.setText(model.getValueAt(selectedIndex, 2).toString());

    }//GEN-LAST:event_jTable5MouseClicked

    private void comboNameBiblioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNameBiblioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboNameBiblioActionPerformed

    private void btnAnnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnnActionPerformed

    private void btnEnregisterEmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregisterEmActionPerformed
        String nomEtu, prenomEtu, filiereEtu, nomLivre, categorieLivre, quantEmprun, biblio, dateEmp, dateRetour;

        nomEtu = txtNomEtu.getText();
        prenomEtu = txtPenomEtu.getText();
        filiereEtu = txtFiliereEtu.getText();
        nomLivre = txtNomLivre.getText();
        categorieLivre = txtCategorieLivre.getText();
        biblio = (String) comboNameBiblio.getSelectedItem();
        quantEmprun = txtQuantEmprunt.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateEmp = sdf.format(dateEmprunt.getDate());
        dateRetour = sdf.format(dateRet.getDate());
        if (nomEtu.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer le nom de l'étudiant");
        } else if (prenomEtu.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer le prénom de l'étudiant");
        } else if (filiereEtu.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer a filière de l'étudiant ");
        } else if (quantEmprun.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer la quantité empruntée");
        } else if (biblio.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer le nom du bibliothécaire");
        } else if (nomLivre.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer le nom du Livre");
        } else if (categorieLivre.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une catégorie");
        } else if (dateEmp.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une date emprunt");
        } else if (dateRetour.equals("")) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une date emprunt");
        } else {
            int dispo = Integer.parseInt(txtQuantDispo.getText());
            int emprunt = Integer.parseInt(txtQuantEmprunt.getText());
            int restant = dispo - emprunt;
            if (dispo < emprunt) {
                JOptionPane.showMessageDialog(dateEmprunt, "Quantité insuffisante", "", 0);
            } else {

                try {
                    Connexion con = new Connexion();
                    pst = con.con.prepareStatement("insert into emprunts (nom_etu, prenom_etu, filiere_etu, nom_livre, categorie_livre,bibliothecaire, quantite_emprunt,date_debut,date_fin) values (?, ?, ?, ?, ?, ?,?,?,?)");
                    int id = jTable5.getSelectedRow();
                    pst.setString(1, nomEtu);
                    pst.setString(2, prenomEtu);
                    pst.setString(3, filiereEtu);
                    pst.setString(4, nomLivre);
                    pst.setString(5, categorieLivre);
                    pst.setString(6, biblio);
                    pst.setString(7, quantEmprun);
                    pst.setString(8, dateEmp);
                    pst.setString(9, dateRetour);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Emprunt ajouté avec succès");
                    pst = con.con.prepareStatement("update livres set quantite = ? where id_livre =?");
                    pst.setInt(1, restant);
                    pst.setInt(2, id);
                    pst.executeUpdate();
                    showlivresEm();
                    showHistoriqueEmp();
                    txtNomEtu.setText("");
                    txtPenomEtu.setText("");
                    txtFiliereEtu.setText("");
                    txtNomLivre.setText("");
                    txtCategorieLivre.setText("");
                    comboNameBiblio.getSelectedItem();
                    txtQuantDispo.setText("");
                    txtQuantEmprunt.setText("");
                    dateEmprunt.setDate(null);
                    dateRet.setDate(null);

                } catch (SQLException ex) {
                    Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnEnregisterEmActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked

        DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
        int selectedIndex = jTable4.getSelectedRow();
        txtNameRetour.setText(model.getValueAt(selectedIndex, 1).toString());
        txtLivre.setText(model.getValueAt(selectedIndex, 3).toString());
        txtQuant.setText(model.getValueAt(selectedIndex, 4).toString());
        txtBiblio.setText(model.getValueAt(selectedIndex, 5).toString());

    }//GEN-LAST:event_jTable4MouseClicked

    private void txtLivreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLivreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLivreActionPerformed

    private void btnValiderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValiderActionPerformed
        try {
            String nom = txtLivre.getText();
            Connexion con = new Connexion();
            pst = con.con.prepareStatement("update emprunts set etat = ? where nom_livre =?");
            pst.setInt(1, 1);
            pst.setString(2, nom);
            pst.executeUpdate();
            String quantiteStr = txtLivre.getText();

            int emprunt = Integer.parseInt(txtQuant.getText());
            int qua = getQuantite(quantiteStr) + emprunt;
            pst = con.con.prepareStatement("update livres set quantite = ? where nom =?");
            pst.setInt(1, qua);
            pst.setString(2, quantiteStr);
            pst.executeUpdate();
            showlivresEm();
            showHistoriqueEmp();
            JOptionPane.showMessageDialog(null, "Retour validé avec succès");
            txtNameRetour.setText("");
            txtLivre.setText("");
            txtQuant.setText("");
            txtBiblio.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_btnValiderActionPerformed

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
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnn;
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnEnregisterEm;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnEnregistrer1;
    private javax.swing.JButton btnModifier;
    private javax.swing.JButton btnRechercher;
    private javax.swing.JButton btnSupprimer1;
    private javax.swing.JButton btnValider;
    private javax.swing.JButton btn_Retour;
    private javax.swing.JButton btn_Supprimer;
    private javax.swing.JComboBox<String> cboRangee;
    private javax.swing.JComboBox<String> comboCategorie;
    private javax.swing.JComboBox<String> comboNameBiblio;
    private com.toedter.calendar.JDateChooser dateEmprunt;
    private com.toedter.calendar.JDateChooser dateRet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private raven.tabbed.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JTextField txtAnnee;
    private javax.swing.JTextField txtAuteur;
    private javax.swing.JTextField txtBiblio;
    private javax.swing.JTextField txtCategorieLivre;
    private javax.swing.JTextField txtDescription;
    private javax.swing.JTextField txtDescription1;
    private javax.swing.JTextField txtFiliereEtu;
    private javax.swing.JTextField txtLivre;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNameRetour;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtNomEtu;
    private javax.swing.JTextField txtNomLivre;
    private javax.swing.JTextField txtPenomEtu;
    private javax.swing.JTextField txtQuant;
    private javax.swing.JTextField txtQuantDispo;
    private javax.swing.JTextField txtQuantEmprunt;
    private javax.swing.JTextField txtQuantite;
    private javax.swing.JTextField txtRechercher;
    // End of variables declaration//GEN-END:variables
}
