
package puzzle_game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class PuzzleImage {
    public String path=System.getProperty("user.dir");
    public BufferedImage img;
    int img_width;
    int img_height;
    public BufferedImage[] images=new BufferedImage[16];
    public BufferedImage resim;
    public int sayac=0;
    public double score=0;
    public boolean r_start=false;
    public boolean[] Trues=new boolean[16];
    public BufferedImage combined;
    public String imageName;

    
  public PuzzleImage(String path){
        
        try {
            img=ImageIO.read(new File(path));
            while(img.getWidth()>1500 || img.getHeight()>930){
               img=resize(img, img.getWidth()-200, img.getHeight()-(int)Math.round((double)img.getHeight()/img.getWidth()*200));  
                System.out.println(((double)img.getHeight()/img.getWidth()));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(PuzzleImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        img_width=img.getWidth();
        img_height=img.getHeight();
    }
    
    public BufferedImage getimg(){
        return this.img;
    }
    public int getimg_width(){
        return this.img_width;
    }
    public int getimg_height(){
        return this.img_height;
    }
    public double getPieceWidth(){
        return img_width/4.0;
    }
    public double getPieceHeight(){
        return  img_height/4.0;
        
    }
    public void SubImages(){
        int k=0,x,y,subimgw=0,subimgh=0;
        for(int i=0;i<4;i++){   
            for(int j=0;j<4;j++){
                //Resmin boyutlarını aşmaması için 
                x=(int)Math.floor(j*getPieceWidth());
                y=(int)Math.floor(i*getPieceHeight());
                if(x>img_width || y>img_height){
                    x=(int)Math.floor(j*getPieceWidth());
                    y=(int)Math.floor(i*getPieceHeight());   
                }
                
               images[k]=img.getSubimage(x, y, (int)Math.round(getPieceWidth()), (int)Math.round(getPieceHeight()));
               k++;
            }
               subimgw=subimgw+images[i].getWidth();
               subimgh=subimgh+images[i].getHeight();
               System.out.println(subimgw+"x"+subimgh+"-----"+images[i].getWidth()+"x"+images[i].getHeight());
        }
        
    }
    
    
    public int checkPieces(){
        resim=this.combined;
        int x,y;
        boolean esitlik=false;
        sayac=0;
        
        //---------------------------1. satır--------------------------
        
            for (x = 0; x < (int)Math.floor(img_width/4); x++) {
                for (y = 0; y < (int)Math.floor(img_height/4); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[0]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[0]=true;
                sayac++;
            }
            
            
           
           for (x = (int)Math.floor(img_width/4); x < (int)Math.floor(img_width/2); x++) {
                for (y = 0; y < (int)Math.floor(img_height/4); y++) {
                    
                    
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }

                }
                if(esitlik==false){
                    Trues[1]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[1]=true;
                sayac++;
            }
            
            
            for (x = (int)Math.floor(img_width/2); x < (int)Math.floor(img_width*3/4); x++) {
                for (y = 0; y < (int)Math.floor(img_height/4); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[2]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[2]=true;
                sayac++;
            }
            
            for (x = (int)Math.floor(img_width*3/4); x < (int)Math.floor(img_width); x++) {
                for (y = 0; y < (int)Math.floor(img_height/4); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[3]=false;
                    break;
                }
            }
            if(esitlik==true){
                Trues[3]=true;
                sayac++;
            }
            
            
            //---------------------------2. satır--------------------------
            for (x = 0; x < (int)Math.floor(img_width/4); x++) {
                for (y = (int)Math.floor(img_height/4); y < (int)Math.floor(img_height/2); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[4]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[4]=true;
                sayac++;
            }
                       
           for (x = (int)Math.floor(img_width/4); x < (int)Math.floor(img_width/2); x++) {
                for (y = (int)Math.floor(img_height/4); y < (int)Math.floor(img_height/2); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }

                }
                if(esitlik==false){
                    Trues[5]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[5]=true;
                sayac++;
            }
            
            
            for (x = (int)Math.floor(img_width/2); x < (int)Math.floor(img_width*3/4); x++) {
                for (y = (int)Math.floor(img_height/4); y < (int)Math.floor(img_height/2); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[6]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[6]=true;
                sayac++;
            }
            
            for (x = (int)Math.floor(img_width*3/4); x < (int)Math.floor(img_width); x++) {
                for (y = (int)Math.floor(img_height/4); y < (int)Math.floor(img_height/2); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[7]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[7]=true;
                sayac++;
            }
            
            //---------------------------3. satır--------------------------
            for (x = 0; x < (int)Math.floor(img_width/4); x++) {
                for (y = (int)Math.floor(img_height/2); y < (int)Math.floor(img_height*3/4); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[8]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[8]=true;
                sayac++;
            }
                       
           for (x = (int)Math.floor(img_width/4); x < (int)Math.floor(img_width/2); x++) {
                for (y = (int)Math.floor(img_height/2); y < (int)Math.floor(img_height*3/4); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }

                }
                if(esitlik==false){
                    Trues[9]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[9]=true;
                sayac++;
            }
            
            
            for (x = (int)Math.floor(img_width/2); x < (int)Math.floor(img_width*3/4); x++) {
                for (y = (int)Math.floor(img_height/2); y < (int)Math.floor(img_height*3/4); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[10]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[10]=true;
                sayac++;
            }
            
            for (x = (int)Math.floor(img_width*3/4); x < (int)Math.floor(img_width); x++) {
                for (y = (int)Math.floor(img_height/2); y < (int)Math.floor(img_height*3/4); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[11]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[11]=true;
                sayac++;
            }
            
            
            //---------------------------4. satır--------------------------
            for (x = 0; x < (int)Math.floor(img_width/4); x++) {
                for (y = (int)Math.floor(img_height*3/4); y < (int)Math.floor(img_height); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[12]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[12]=true;
                sayac++;
            }
            
           for (x = (int)Math.floor(img_width/4); x < (int)Math.floor(img_width/2); x++) {
                for (y = (int)Math.floor(img_height*3/4); y < (int)Math.floor(img_height); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }

                }
                if(esitlik==false){
                    Trues[13]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[13]=true;
                sayac++;
            }
            
            for (x = (int)Math.floor(img_width/2); x < (int)Math.floor(img_width*3/4); x++) {
                for (y = (int)Math.floor(img_height*3/4); y < (int)Math.floor(img_height); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[14]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[14]=true;
                sayac++;
            }
            
            for (x = (int)Math.floor(img_width*3/4); x < (int)Math.floor(img_width); x++) {
                for (y = (int)Math.floor(img_height*3/4); y < (int)Math.floor(img_height); y++) {
                    if(resim.getRGB (x,y)!=img.getRGB (x,y)){
                        esitlik=false;
                        break;
                    }
                    else{
                        esitlik=true;
                    }
                }
                if(esitlik==false){
                    Trues[15]=false;
                    break;
                }
            }
            
            if(esitlik==true){
                Trues[15]=true;
                sayac++;
            }


            
            System.out.println("Sonuc: "+sayac);
            
            return sayac;

        
    }
    
    public  BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        System.out.println(dimg.getWidth()+" x "+dimg.getHeight());
    return dimg;
}  
}

