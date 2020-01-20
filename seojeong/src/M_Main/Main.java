package M_Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main extends JPanel implements Runnable {
    Player player;
    private Thread th;
    private final int width = 800;
    private final int height = 400;
//    private Image dbImage;
//    private Graphics dbg;
    //배경화면 관련 변수
    String bgOriginalPath="C:/Users/seojeong/Documents/seojeong/src/Image/sky.jpg"; //원본 이미지 파일명
    String bgTargetPath="C:/Users/seojeong/Documents/seojeong/src/Image/sky_resize.jpg"; //새 이미지 파일 명
    String bgFormat = "jpg";    //새 이미지 포맷 jpg
    String mainPosition = "W";  //W:넚이 중심 H:높이 중심
    private Image bgImage;
    int w;
    int h;

    public Main() {
//        setBackground(Color.YELLOW);
        setPreferredSize(new Dimension(width,height));
        player = new Player((int)(width*0.03),height/2,10,width-20,10,height-20);
        addKeyListener(new ShipControl());
        setFocusable(true);
    }

    public void start() {
        th = new Thread(this);
        th.start();
    }

    public class ShipControl implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    player.setPlayerMoveUp(true);
                    //System.out.println("upSet");
                    break;
                case KeyEvent.VK_DOWN:
                    player.setPlayerMoveDown(true);
                    //System.out.println("downSet");
                    break;
                case KeyEvent.VK_RIGHT:
                    player.setPlayerMoveRight(true);
                    //System.out.println("rightSet");
                    break;
                case KeyEvent.VK_LEFT:
                    player.setPlayerMoveLeft(true);
                    //System.out.println("leftSet");
                    break;
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    player.setPlayerMoveUp(false);
                    //System.out.println("upRock");
                    break;
                case KeyEvent.VK_DOWN:
                    player.setPlayerMoveDown(false);
                    //System.out.println("downRock");
                    break;
                case KeyEvent.VK_RIGHT:
                    player.setPlayerMoveRight(false);
                    //System.out.println("rightRock");
                    break;
                case KeyEvent.VK_LEFT:
                    player.setPlayerMoveLeft(false);
                    //System.out.println("leftRock");
                    break;
            }
        }
    }
    @Override
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        /*
        * Player Move
        * */
        while(true) {
            if(player.isPlayerMoveUp() && player.isPlayerMoveDown()){
                //System.out.println("stop");
            }
            else if(player.isPlayerMoveRight() &&  player.isPlayerMoveLeft()){
                //System.out.println("stop");
            }
            else if(player.isPlayerMoveUp() && player.isPlayerMoveRight()){
                player.moveY(player.getPlayerUpSpeed());
                player.moveX(player.getPlayerRightSpeed());
            }
            else if(player.isPlayerMoveUp() && player.isPlayerMoveLeft()){
                player.moveY(player.getPlayerUpSpeed());
                player.moveX(player.getPlayerLeftSpeed());
            }
            else if(player.isPlayerMoveDown() && player.isPlayerMoveRight()){
                player.moveY(player.getPlayerDownSpeed());
                player.moveX(player.getPlayerRightSpeed());
            }
            else if(player.isPlayerMoveDown() && player.isPlayerMoveLeft()){
                player.moveY(player.getPlayerDownSpeed());
                player.moveX(player.getPlayerLeftSpeed());
            }
            else if(player.isPlayerMoveUp()) {
                player.moveY(player.getPlayerUpSpeed());
            }
            else if (player.isPlayerMoveDown()) {
                player.moveY(player.getPlayerDownSpeed());
            }
            else if (player.isPlayerMoveRight()){
                player.moveX(player.getPlayerRightSpeed());
            }
            else if(player.isPlayerMoveLeft()){
                player.moveX(player.getPlayerLeftSpeed());
            }
            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {

            }

            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }

    public void paintComponent(Graphics g) {
        g.fillRect(0,0,this.getSize().width,this.getSize().height);
        //bgImage = new ImageIcon("C:/Users/seojeong/Documents/seojeong/src/Image/sky.jpg").getImage();//todo background image location route set
        //Image scaleBGImage = bgImage.getScaledInstance(800,400,Image.SCALE_DEFAULT);

        //g.drawImage(scaleBGImage,0,0,this);
        try{
            bgImage = ImageIO.read(new File(bgOriginalPath));

            w = bgImage.getWidth(null);
            h = bgImage.getHeight(null);


            Image resizeImage = bgImage.getScaledInstance(w,h,Image.SCALE_SMOOTH);

            BufferedImage newImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
            Graphics newG = newImage.getGraphics();
            newG.drawImage(resizeImage, 0, 0, null);
            newG.dispose();
            ImageIO.write(newImage,bgFormat,new File(bgTargetPath));

        }catch (Exception e){
            e.printStackTrace();
        }
        player.drawPlayer(g);
    }

    /*private void initImage(Graphics g) {
        if(dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        dbg.setColor(getBackground());
        dbg.fillRect(0,0,this.getSize().width,this.getSize().height);//

        dbg.setColor(getForeground());
        g.drawImage(dbImage,0,0,this);
    }*/

    public static void main(String[] args) {
        JFrame frame = new JFrame("Shooting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main main1 = new Main();
        frame.getContentPane().add(main1);
        frame.pack();
        frame.setVisible(true);
        main1.start();
    }
}
