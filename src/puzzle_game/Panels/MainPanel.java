/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle_game.Panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import puzzle_game.Frames.Game;
import puzzle_game.Frames.Login;
import puzzle_game.Frames.Register;
import puzzle_game.PuzzleImage;


/**
 *
 * @author Erhan Özdoğan
 */
public class MainPanel extends javax.swing.JPanel {
    String puzzle_path;
    BufferedImage puzzle_img=null;
    puzzle_game.PuzzleImage image;
    JPanel beforePlay;
    JLabel beforePlayWarning;
    boolean Login=false;
    String User;
    public String path=System.getProperty("user.dir");
    

    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
        initComponents();
        visible_settings();
        setBorders();
        
    }
    public void visible_settings(){
        jLabel3.setVisible(false);
        imagePanel.setVisible(false);
        
       
    }
    public void setBorders(){

    }
    public void login(String user){
        User=user;
        imagePanel.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        sidePanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        loginPanel = new javax.swing.JPanel();
        loginButPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        RegisterButPan = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        guestButPan = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        imagePanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        p_path = new javax.swing.JLabel();
        browseButPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        headerPanel.setBackground(new java.awt.Color(110, 89, 222));
        headerPanel.setPreferredSize(new java.awt.Dimension(693, 93));

        jLabel1.setBackground(new java.awt.Color(54, 33, 89));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(54, 33, 89));
        jLabel1.setText("Welcome  To Puzzle Game");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(54, 33, 89));
        jLabel9.setText("X");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(66, 66, 66)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
        );

        sidePanel.setBackground(new java.awt.Color(54, 33, 89));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(220, 220, 220));
        jLabel4.setText("Login");

        loginPanel.setOpaque(false);

        loginButPanel1.setBackground(new java.awt.Color(85, 65, 118));
        loginButPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButPanel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButPanel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButPanel1MouseExited(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icons/icons8-enter-40.png"))); // NOI18N

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("Login");

        javax.swing.GroupLayout loginButPanel1Layout = new javax.swing.GroupLayout(loginButPanel1);
        loginButPanel1.setLayout(loginButPanel1Layout);
        loginButPanel1Layout.setHorizontalGroup(
            loginButPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginButPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginButPanel1Layout.setVerticalGroup(
            loginButPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginButPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        RegisterButPan.setBackground(new java.awt.Color(85, 65, 118));
        RegisterButPan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        RegisterButPan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegisterButPanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RegisterButPanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RegisterButPanMouseExited(evt);
            }
        });

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icons/icons8-registration-40.png"))); // NOI18N

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Register");

        javax.swing.GroupLayout RegisterButPanLayout = new javax.swing.GroupLayout(RegisterButPan);
        RegisterButPan.setLayout(RegisterButPanLayout);
        RegisterButPanLayout.setHorizontalGroup(
            RegisterButPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegisterButPanLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel14)
                .addGap(28, 28, 28)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        RegisterButPanLayout.setVerticalGroup(
            RegisterButPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegisterButPanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        guestButPan.setBackground(new java.awt.Color(85, 65, 118));
        guestButPan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guestButPan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guestButPanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                guestButPanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                guestButPanMouseExited(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icons/icons8-account-40.png"))); // NOI18N

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("Play as Guest");

        javax.swing.GroupLayout guestButPanLayout = new javax.swing.GroupLayout(guestButPan);
        guestButPan.setLayout(guestButPanLayout);
        guestButPanLayout.setHorizontalGroup(
            guestButPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(guestButPanLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        guestButPanLayout.setVerticalGroup(
            guestButPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(guestButPanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(guestButPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loginButPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(RegisterButPan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(loginButPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RegisterButPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(guestButPan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(237, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        imagePanel.setOpaque(false);
        imagePanel.setPreferredSize(new java.awt.Dimension(768, 179));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(54, 33, 89));
        jLabel2.setText("Choose an Image:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(54, 33, 89));
        jLabel3.setText("Choosen Image:");

        p_path.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_path.setForeground(new java.awt.Color(54, 33, 89));

        browseButPanel.setBackground(new java.awt.Color(85, 65, 118));
        browseButPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        browseButPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                browseButPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                browseButPanelMouseEntered(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icons/icons8-image-file-40.png"))); // NOI18N

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Browse");

        javax.swing.GroupLayout browseButPanelLayout = new javax.swing.GroupLayout(browseButPanel);
        browseButPanel.setLayout(browseButPanelLayout);
        browseButPanelLayout.setHorizontalGroup(
            browseButPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(browseButPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        browseButPanelLayout.setVerticalGroup(
            browseButPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(browseButPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(browseButPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(p_path, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(imagePanelLayout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(browseButPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(p_path, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(756, 756, 756)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(imagePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(310, 310, 310)
                .addComponent(jLabel10)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    
    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void browseButPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseButPanelMouseClicked
        JFileChooser jfc=new JFileChooser(path+"\\src\\Assets\\Puzzle Samples");
        jfc.setDialogTitle("Choose an puzzle");
        jfc.setMultiSelectionEnabled(false);                                                                                        // No Multiple Selection only one puzzle image
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Only JPEG or PNG","jpg","png");                                  // Only Jpg or png
        jfc.addChoosableFileFilter(filter);                                                                                         // add filter to file chooser
        jfc.setAcceptAllFileFilterUsed(false);                                                                                      // all files filter diasble
        jfc.showDialog(this,"Select");                                                                                              // open file chooser

        image=new PuzzleImage(jfc.getSelectedFile().getAbsolutePath());                                                            // created a image from PuzzleImage clas with image path
        image.imageName=jfc.getSelectedFile().getName();
        
        p_path.setText(jfc.getSelectedFile().getAbsolutePath());
        jLabel3.setVisible(true);
        System.out.println(User);
        Game g=new Game(image,this.User);
    }//GEN-LAST:event_browseButPanelMouseClicked

    private void browseButPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_browseButPanelMouseEntered
               
    }//GEN-LAST:event_browseButPanelMouseEntered

    private void guestButPanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guestButPanMouseClicked

        login("Guest");
    }//GEN-LAST:event_guestButPanMouseClicked

    private void RegisterButPanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterButPanMouseClicked
        Register rg=new Register();
        rg.setVisible(true);
    }//GEN-LAST:event_RegisterButPanMouseClicked

    private void loginButPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButPanel1MouseClicked
        Login lg=new Login(this);
        lg.setVisible(true);
    }//GEN-LAST:event_loginButPanel1MouseClicked

    
    
    
    private void loginButPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButPanel1MouseEntered
        loginButPanel1.setBackground(new Color(110,89,222) );
    }//GEN-LAST:event_loginButPanel1MouseEntered

    private void loginButPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButPanel1MouseExited
       loginButPanel1.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_loginButPanel1MouseExited

    private void RegisterButPanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterButPanMouseEntered
       RegisterButPan.setBackground(new Color(110,89,222) );
    }//GEN-LAST:event_RegisterButPanMouseEntered

    private void RegisterButPanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterButPanMouseExited
        RegisterButPan.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_RegisterButPanMouseExited

    private void guestButPanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guestButPanMouseEntered
        guestButPan.setBackground(new Color(110,89,222) );
    }//GEN-LAST:event_guestButPanMouseEntered

    private void guestButPanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guestButPanMouseExited
        guestButPan.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_guestButPanMouseExited

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel RegisterButPan;
    private javax.swing.JPanel browseButPanel;
    private javax.swing.JPanel guestButPan;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel loginButPanel1;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JLabel p_path;
    private javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables
}
