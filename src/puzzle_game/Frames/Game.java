/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle_game.Frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.FloatControl;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import jdk.internal.util.xml.impl.Input;
import puzzle_game.PuzzleImage;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class Game extends javax.swing.JFrame implements Runnable{
    puzzle_game.PuzzleImage img;
     JPanel GamePanel;
     JPanel panel=new JPanel();
     JButton[] Buttons;
     JFrame hint_frame=new JFrame();
     JLabel hintlabel=new JLabel();
     public Component first;
     public Component second;
     public int count=0;
     public boolean isDone=false;
     Thread tr1;
     String User;
     Border brdr=null;
     AudioStream muzik;
     boolean muted=false;
     Long gameBegin,gameEnd;
     
    
    
    public Game(puzzle_game.PuzzleImage image,String Userr) {
        initComponents();
        img=image;
        User=Userr;
        getHighScore();
        setGameArea();
        setButtons();
        setHint();
        tr1=new Thread(this);
        tr1.start(); 
        setVisible(true);
        saveButton.setVisible(false);
        
        InputStream ses = null;
        try {
            ses = new FileInputStream(img.path+"\\src\\sounds\\backround.wav");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        muzik = null;
        try {
            muzik = new AudioStream(ses);
        } catch (IOException ex) {
            System.out.println("oynatılamadı");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioPlayer.player.start(muzik);
        
       

        
        
        
    }
    //Kaydetme kısmı çıkarıldı büyük resimlerde yavaşlatıyor puzzleimage classındaki combined değiştiriliyor
    public void savePieces(){
        
        BufferedImage[] new_images=new BufferedImage[16];
        img.combined = new BufferedImage(img.getimg_width(), img.getimg_height(), BufferedImage.TYPE_INT_RGB);
        
         for(int i=0; i<16; i++){    //  Yeni parçaları new_images dizisine atar
            BufferedImage bi = new BufferedImage(Buttons[i].getIcon().getIconWidth(),Buttons[i].getIcon().getIconHeight(),BufferedImage.TYPE_INT_RGB);
            
            new_images[i]=bi.getSubimage(0, 0, (int)Math.round(img.getPieceWidth()), (int)Math.round(img.getPieceHeight()));
            Graphics g = new_images[i].createGraphics();
            
            Buttons[i].getIcon().paintIcon(null, g, 0,0); //Butonun iconunu bi deki grafiğer aktar

            g.dispose();
            
        }
        Graphics gr = img.combined.getGraphics();
        
        int k=0,x,y,subimgw=0,subimgh=0;
        for(int i=0;i<4;i++){   
            for(int j=0;j<4;j++){
                //Resmin boyutlarını aşmaması için 
                x=(int)Math.floor(j*img.getPieceWidth());
                y=(int)Math.floor(i*img.getPieceHeight());
                if(x>img.getimg_width() || y>img.getimg_height()){
                    x=(int)Math.floor(j*img.getPieceWidth());
                    y=(int)Math.floor(i*img.getPieceHeight());   
                }  
               gr.drawImage(new_images[k], x, y, null);   //new_images dizisindeki parçalardan yeni resme yerleştirir 
               k++;
            }
               subimgw=subimgw+img.images[i].getWidth();
               subimgh=subimgh+img.images[i].getHeight();    
        }
              
             /*File outputfile1 = new File(img.path+"\\Puzzle_Game\\src\\imageCache\\new.png");
             
            try {
                ImageIO.write(img.combined, "png", outputfile1);
            } catch (IOException ex) {
                System.out.println("Yeni resim kaydedilemedi!\n");
            }*/
    }
    
    public double calculateScore(int old_c, int counter){
        
        if(old_c<counter || old_c>counter)
            img.score+=(counter-old_c)*7.14;
        else
            img.score-=14.28;
        if(img.score>100)
            img.score = 100;
        if(!img.r_start && img.score<0)
            img.score=0;
        
        return (double)Math.round(img.score*1000.0d)/1000.0d;
    }
    
    public void writeScore(double scr){    
        String score=Double.toString((double)Math.round(scr*1000.0d)/1000.0d);
        FileWriter fw=null;
        File file2 = new File(img.path+"\\src\\Assets\\HighScores.txt");
        
        try {
        fw = new FileWriter(file2,true);
        BufferedWriter bw=new BufferedWriter(fw);
        bw.write(User+" "+score+" "+img.imageName+" "+(gameEnd-gameBegin)/1000.0);
        bw.newLine();
        bw.close();
        
        } 
        catch (IOException e){          
            e.printStackTrace();
        }
    }
    
    public void getHighScore(){
       File file= new File(img.path+"\\src\\Assets\\HighScores.txt");
       String read;
       double hs=Double.NEGATIVE_INFINITY;
       double time=0;
        try {
            FileReader fr=new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            while ((read = br.readLine()) != null) {
                 String[] values = read.split(" ");
                 if(values[2].equals(img.imageName) && Double.parseDouble(values[1])>=hs){
                    if(Double.parseDouble(values[1]) > hs){
                        hs=Double.parseDouble(values[1]);
                        time=Double.parseDouble(values[3]);
                     }
                    else if(Double.parseDouble(values[1])== hs && Double.parseDouble(values[3])<time){
                        hs=Double.parseDouble(values[1]);
                        time=Double.parseDouble(values[3]);
                     }

                     
                 }     
            }
            if(hs==Double.NEGATIVE_INFINITY)
                hs=0;
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        HScoreLabel.setText(Double.toString(hs)+"/"+time);

    }
    public void setGameArea(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        GamePanel =new JPanel();
        GamePanel.setLayout(new GridLayout(4, 4));
        GamePanel.setBounds((((jPanel1.getWidth())-img.getimg_width())/2),((950-img.getimg_height())/2),img.getimg_width(),img.getimg_height());
        GamePanel.setBackground(Color.white);
        GamePanel.setVisible(false);
        jPanel1.add(GamePanel);    
        GameLog.append("GamePanel is Ready !  "+sdf.format(cal.getTime())+"\n");
        jLabel6.setText(User);
        System.out.println(User);
        Status.setVisible(false);



        
    }
    public void setButtons(){
        Buttons=new JButton[16];
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        
        for(int i=0;i<16;i++){
            Buttons[i]=new JButton();
            Buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            GamePanel.add(Buttons[i]);   
        }
        GameLog.append("Pieces is Ready !   "+sdf.format(cal.getTime())+"\n");
        
        
    }
    public void setHint(){
        int img_width=img.getimg_width();
        int img_height=img.getimg_height();
        
        hint_frame.setBounds((2208-img_width)/2,(1015-img_height)/2 , img_width, img_height);
        hint_frame.setLayout(new GridLayout());
        hint_frame.setUndecorated(true);
        
        hintlabel.setIcon(new ImageIcon(img.getimg()));
        hint_frame.add(hintlabel);

    }
    public void trueMark(){
        for(int i=0;i<16;i++){
            if(img.Trues[i]){
                Buttons[i].setBorder(BorderFactory.createLineBorder(Color.green,2));
                Buttons[i].setEnabled(false);
                Buttons[i].setDisabledIcon(Buttons[i].getIcon());
            }
            else{
                Buttons[i].setBorder(new JButton().getBorder());
            }
        }
        
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        InfoPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        GameLog = new javax.swing.JTextArea();
        Mix = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        scores = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        HScoreLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ScoreLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        hint = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Status = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        saveButton = new javax.swing.JLabel();
        muteBut = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(60, 35));
        setUndecorated(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        InfoPanel.setBackground(new java.awt.Color(54, 33, 89));
        InfoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InfoPanelMouseClicked(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(85, 65, 118));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(204, 204, 204));
        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(85, 65, 118)));
        jScrollPane1.setEnabled(false);
        jScrollPane1.setFocusable(false);
        jScrollPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jScrollPane1.setOpaque(false);
        jScrollPane1.setRequestFocusEnabled(false);
        jScrollPane1.setVerifyInputWhenFocusTarget(false);

        GameLog.setEditable(false);
        GameLog.setBackground(new java.awt.Color(85, 65, 118));
        GameLog.setColumns(20);
        GameLog.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        GameLog.setForeground(new java.awt.Color(204, 204, 204));
        GameLog.setLineWrap(true);
        GameLog.setRows(5);
        GameLog.setBorder(null);
        GameLog.setFocusable(false);
        GameLog.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(GameLog);

        Mix.setBackground(new java.awt.Color(85, 65, 118));
        Mix.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Mix.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MixMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MixMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MixMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                MixMouseReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Mix Pieces");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icons/icons8-variation-filled-50.png"))); // NOI18N

        javax.swing.GroupLayout MixLayout = new javax.swing.GroupLayout(Mix);
        Mix.setLayout(MixLayout);
        MixLayout.setHorizontalGroup(
            MixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MixLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addGap(37, 37, 37)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MixLayout.setVerticalGroup(
            MixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MixLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        scores.setBackground(new java.awt.Color(110, 89, 222));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("High Score:");

        HScoreLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        HScoreLabel.setForeground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Your Score:");

        ScoreLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ScoreLabel.setForeground(new java.awt.Color(204, 204, 204));
        ScoreLabel.setText("0");

        javax.swing.GroupLayout scoresLayout = new javax.swing.GroupLayout(scores);
        scores.setLayout(scoresLayout);
        scoresLayout.setHorizontalGroup(
            scoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(scoresLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ScoreLabel))
                    .addGroup(scoresLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        scoresLayout.setVerticalGroup(
            scoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(HScoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(scoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ScoreLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        HScoreLabel.getAccessibleContext().setAccessibleDescription("");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("PluwJin");

        hint.setBackground(new java.awt.Color(85, 65, 118));
        hint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hintMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hintMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hintMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hintMouseReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("For Hint");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icons/icons8-hint-50.png"))); // NOI18N

        javax.swing.GroupLayout hintLayout = new javax.swing.GroupLayout(hint);
        hint.setLayout(hintLayout);
        hintLayout.setHorizontalGroup(
            hintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hintLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel8)
                .addGap(37, 37, 37)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        hintLayout.setVerticalGroup(
            hintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hintLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        Status.setEditable(false);
        Status.setBackground(new java.awt.Color(200, 255, 200));
        Status.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Status.setText("Status");
        Status.setBorder(null);
        Status.setCaretColor(new java.awt.Color(255, 255, 255));
        Status.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Status.setSelectionColor(new java.awt.Color(0, 0, 0));
        Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InfoPanelLayout = new javax.swing.GroupLayout(InfoPanel);
        InfoPanel.setLayout(InfoPanelLayout);
        InfoPanelLayout.setHorizontalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Mix, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scores, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(hint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(InfoPanelLayout.createSequentialGroup()
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InfoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(InfoPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(Status)
        );
        InfoPanelLayout.setVerticalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Mix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(hint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(40, 40));

        jLabel4.setBackground(new java.awt.Color(54, 33, 89));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(54, 33, 89));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("X");
        jLabel4.setToolTipText("");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icons/icons8-save-35.png"))); // NOI18N
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonMouseClicked(evt);
            }
        });

        muteBut.setBackground(new java.awt.Color(204, 204, 204));
        muteBut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/Icons/icons8-mute-40.png"))); // NOI18N
        muteBut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        muteBut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                muteButMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(muteBut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(496, 496, 496)
                        .addComponent(jLabel9)
                        .addGap(0, 990, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(muteBut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(918, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(InfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        
    }//GEN-LAST:event_formMouseClicked

    private void InfoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InfoPanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_InfoPanelMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        this.setVisible(false);
        AudioPlayer.player.stop(muzik);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void MixMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MixMouseEntered
        Mix.setBackground(new Color(110, 89, 222));
    }//GEN-LAST:event_MixMouseEntered

    private void MixMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MixMouseExited
        Mix.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_MixMouseExited

    private void MixMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MixMouseClicked
         int random;
        
        img.sayac=0;
        img.score=0;
        img.r_start=false;
        gameBegin=System.currentTimeMillis();  
        getHighScore();
        ArrayList<Integer> RandomNums =new ArrayList<Integer>(); // Random sayıları tutan arraylist aynı sayıların kullanılmasını engeller
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        img.SubImages();                                                //Resimleri Ayır
        
        for(int i=0;i<16;i++){
            random=(int)Math.round(Math.random()*15);
            while(RandomNums.contains(random))
            {
                random=(int)Math.round(Math.random()*15); 
            }
            RandomNums.add(random);
            System.out.println(random);
            Buttons[random].setIcon( new ImageIcon(img.images[i]));             //Her bir ayrı resim random butonlara aktarıldı.
            
        }
        savePieces();
        ScoreLabel.setText(Double.toString(calculateScore(img.sayac,img.checkPieces())));


        
        GamePanel.setVisible(true);
        saveButton.setVisible(false);
        GameLog.append("Puzzle is mixed !   "+sdf.format(cal.getTime())+"\n");
        Status.setText("Puzzle is Mixed !");
        Status.setVisible(true);
        Mix.setForeground(Color.white);
        
        //Eğer Oyun bittikten sonra basıldıysa butonları tekrar aktif eder

            for(int i=0;i<16;i++){
                Buttons[i].setEnabled(true);
            }
            isDone=false;
                    
        //Kontrol sonrası doğru olanlar işaretlenir
        trueMark();
      
        
        
        //----------------------------------Ses----------------------------------------------
        InputStream ses = null;
        try {
            ses = new FileInputStream(img.path+"\\src\\sounds\\mix.wav");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioStream muzik = null;
        try {
            muzik = new AudioStream(ses);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioPlayer.player.start(muzik);
        //-------------------------------------------------------------------------------------
        
    }//GEN-LAST:event_MixMouseClicked

    private void MixMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MixMouseReleased
        Mix.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_MixMouseReleased

    
    
    private void hintMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hintMouseEntered
        hint.setBackground(new Color(110, 89, 222));
    }//GEN-LAST:event_hintMouseEntered

    private void hintMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hintMouseExited
         hint.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_hintMouseExited

    private void hintMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hintMouseReleased
        hint.setForeground(new Color(204,204,204));
        hint_frame.setVisible(false);
    }//GEN-LAST:event_hintMouseReleased

    private void hintMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hintMousePressed
       hint_frame.setVisible(true);
       hint.setForeground(Color.white);
       
       InputStream ses = null;
        try {
            ses = new FileInputStream(img.path+"\\src\\sounds\\hint.wav");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioStream muzik = null;
        try {
            muzik = new AudioStream(ses);
        } catch (IOException ex) {
            System.out.println("oynatılamadı");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioPlayer.player.start(muzik);
    }//GEN-LAST:event_hintMousePressed

    private void StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StatusActionPerformed

    private void saveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonMouseClicked

        writeScore(img.score);
        saveButton.setVisible(false);
        GameLog.append("Score Saved !!\n");
                
        //----------------------------------Ses-----------------------------------------
        InputStream ses = null;
        try {
            ses = new FileInputStream(img.path+"\\src\\sounds\\save.wav");
        } 
        catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioStream muzik = null;
        try {
            muzik = new AudioStream(ses);
        }
        catch (IOException ex) {
            System.out.println("oynatılamadı");
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioPlayer.player.start(muzik);
        //-----------------------------------------------------------------------------
    }//GEN-LAST:event_saveButtonMouseClicked

    private void muteButMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_muteButMouseClicked
        
        if(AudioPlayer.player.isAlive() && !muted){
            AudioPlayer.player.stop(muzik);
            muteBut.setIcon(new ImageIcon(img.path+"\\src\\Assets\\Icons\\unmute.png"));
            muted=true;
        }
        else{
            AudioPlayer.player.start(muzik);
            muteBut.setIcon(new ImageIcon(img.path+"\\src\\Assets\\Icons\\icons8-mute-40.png"));
            muted=false;
        }
    }//GEN-LAST:event_muteButMouseClicked

    
    
    
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
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("aa");
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea GameLog;
    private javax.swing.JLabel HScoreLabel;
    private javax.swing.JPanel InfoPanel;
    private javax.swing.JPanel Mix;
    private javax.swing.JLabel ScoreLabel;
    private javax.swing.JTextField Status;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel hint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel muteBut;
    private javax.swing.JLabel saveButton;
    private javax.swing.JPanel scores;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        System.out.println("aa");
         for(int i=0;i<16;i++){
            Buttons[i].addMouseListener(new MouseAdapter()
            {
                public void mouseClicked(MouseEvent e)
                {
                    System.out.println(count);
                    if(isDone!=true && e.getComponent().isEnabled() && img.sayac>1){
                    count++;
                    if(count%2==1){
                        first=e.getComponent();    
                        JButton jb2=(JButton)first;
                        brdr=jb2.getBorder();
                        jb2.setBorder(BorderFactory.createLineBorder(Color.green,5,false));
                    }
                    else if(count%2==0){
                        second=e.getComponent();
                        JButton jb2=(JButton)second;
                        JButton jb1=(JButton)first;
                        
                        if(jb2!=jb1){ // Basılan butonların aynı olmasını engeller aynı butonsa else ile eski haline getirilir.
                            
                            jb2.setBorder(BorderFactory.createLineBorder(Color.green,5,false));
                            jb1.setBorder(BorderFactory.createLineBorder(Color.green,5,false));
                            
                            Icon ic2=jb2.getIcon();
                            Icon ic1=jb1.getIcon();

                            //yer değiştirdikleri için jb2 ilk tıklananı temsil ediyor jb1 ikinci tıklananı temsil ediyor
                            jb2.setIcon(ic1);
                            jb1.setIcon(ic2);

                            jb1.setBorder(new JButton().getBorder());
                            jb2.setBorder(new JButton().getBorder());

                            img.r_start=true;
                            
                            //Kontol
                            savePieces();
                            System.out.println("Bitti");
                            Status.setVisible(true);
                            int s=img.sayac;
                            int y=img.checkPieces();
                            
                            //Eğer Eski Sayac yeni sayaç tan küçükse doğru hamle yapılmıştır
                            if(s<y){           
                                Status.setText("Right move!");
                                Status.setBackground(new Color(200, 255, 200));

                                ScoreLabel.setText(Double.toString(calculateScore(s,y)));
                                

                                //--------------------------------Ses----------------------------------------
                                InputStream ses = null;
                                try {
                                    ses = new FileInputStream(img.path+"\\src\\sounds\\true.wav");
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                AudioStream muzik = null;
                                try {
                                    muzik = new AudioStream(ses);
                                } catch (IOException ex) {
                                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                AudioPlayer.player.start(muzik);
                                //---------------------------------------------------------------------------

                            }
                            else{
                                Status.setText("Wrong move!");
                                Status.setBackground(new Color(255, 200, 200));

                                ScoreLabel.setText(Double.toString(calculateScore(s,y)));
                                
                                

                                //---------------------------------------Ses------------------------------------
                                InputStream ses = null;
                                try {
                                    ses = new FileInputStream(img.path+"\\src\\sounds\\false.wav");
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                AudioStream muzik = null;
                                try {
                                    muzik = new AudioStream(ses);
                                } catch (IOException ex) {
                                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                AudioPlayer.player.start(muzik);
                                //-------------------------------------------------------------------------------
                            }
                            //Oyun Bittiyse Kaydet Butonu Aktif et
                            if(img.sayac==16){
                                GameLog.append("Puzzle is Completed !!\n");   
                                Status.setText("Puzzle is Completed !!");
                                saveButton.setVisible(true);
                                for (int i=0;i<16;i++) {
                                    Buttons[i].setBorder(new JButton().getBorder());
                                    Buttons[i].setDisabledIcon(Buttons[i].getIcon());
                                    Buttons[i].setEnabled(false);
                                    
                                }
                                isDone=true;
                                gameEnd=System.currentTimeMillis();
                                 GameLog.append("Your Score: "+img.score+"/"+(gameEnd-gameBegin)/1000.0+"\n");
                            }
                            else{
                                trueMark();
                            }
                        }
                         
                        //Aynı Butonsa butonun borderını düzelt
                        else{
                            jb2.setBorder(brdr);
                        }
                    }
  
                }
                    else{
                        System.out.println("Puzzle is Done");
                    }
                }
            });
            
        }
       
    }
     
}
