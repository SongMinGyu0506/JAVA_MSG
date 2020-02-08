package M_Main;

import M_Main.Shoot.Shoot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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

    //Enemy
    private ArrayList enemies;
    private final int enemyMaxDownSpeed = 1;
    private int enemySize;
    private Random rand;
    private javax.swing.Timer timer;
    private final int enemyTimeGap = 1000;
    private final int maxEnemySize = 20;
    private final int enemyMaxHorizonSpeed = 1;
    private float enemyMaxDownSpeedInc = 0.3f;

    //Shoot
    private final int enemyShootCoolTime=200;
    private final int enemyShootMinCoolTime=100;
    private ArrayList<Shoot> shoots;

    //image
    ImageIcon icon;


    public Main() {
        //setBackground(Color.YELLOW);
        setPreferredSize(new Dimension(width,height));
        player = new Player((int)(width*0.03),height/2,10,width-20,10,height-20);
        addKeyListener(new ShipControl());
        setFocusable(true);

        //Enemy
        enemies = new ArrayList();
        enemySize = 0;
        rand = new Random(1);
        timer = new javax.swing.Timer(enemyTimeGap, new addANewEnemy());
        timer.start();

        //Shoot
        shoots =new ArrayList();

    }

    public void start() {
        th = new Thread(this);
        th.start();
    }

    /*
     *   Enemy 생성 클래스,
     *   Random 함수 이용하여 Enemy 속도와 등장 위치 지정
     *   ArrayList 활용하여 등장 Enemy 지정
     * */
    private class addANewEnemy implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(++enemySize <= maxEnemySize) {
                float downspeed;
                do {
                    downspeed = rand.nextFloat() * enemyMaxDownSpeed;
                } while(downspeed == 0);

                float horspeed = rand.nextFloat() * 2 * enemyMaxHorizonSpeed - enemyMaxHorizonSpeed;
                Enemy newEnemy = new Enemy(width,(int)(rand.nextFloat() * height),horspeed,downspeed,width,height,enemyMaxDownSpeedInc);
                newEnemy.InitCoolTime(rand.nextInt(enemyShootCoolTime)+enemyShootMinCoolTime);
                enemies.add(newEnemy);
            } else {
                timer.stop();
            }
        }
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
                case KeyEvent.VK_SPACE:
                    shoots.add(player.generateShoot());
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

            Iterator enemyList = enemies.iterator();
            while(enemyList.hasNext()) {
                Enemy enemy = (Enemy) enemyList.next();
                enemy.move();
                if(enemy.countCoolTime()<0){
                    shoots.add(enemy.generateShoot());
                    enemy.InitCoolTime(rand.nextInt(enemyShootCoolTime)+enemyShootMinCoolTime);
                }
            }

            //총알 움직임
            for(int i=0;i<shoots.size();i++){
                Shoot shoot=shoots.get(i);
                shoot.move();
                if(shoot.getName().equals("PlayerShoot")) {
                    if (shoot.getXPos() > 800)
                        shoots.remove(shoot);
                }
                else if(shoot.getName().equals("EnemyShoot")){
                    if(shoot.getXPos() < 0)
                        shoots.remove(shoot);
                }
            }

            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {

            }
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }
    private int image1X=0, image2X=800;
    public void paintComponent(Graphics g) {
        g.fillRect(0,0,this.getSize().width,this.getSize().height);
        icon = new ImageIcon("./sky.jpg");
        Image originImg = icon.getImage();
        Image changedImg = originImg.getScaledInstance(800,400,Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(changedImg);
        if(image1X > -800) {
            g.drawImage(newIcon.getImage(),image1X-=2,0,null);
            g.drawImage(newIcon.getImage(),image2X-=2,0,null);
        }
        else{
            g.drawImage(newIcon.getImage(),image1X = 0,0,null);
            g.drawImage(newIcon.getImage(),image2X = 800,0,null);
        }


        setOpaque(false);
        super.paintComponent(g);
        /*try{
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
        }*/
        player.drawPlayer(g);

        Iterator enemyList = enemies.iterator();
        while(enemyList.hasNext()) {
            Enemy enemy = (Enemy) enemyList.next();
            enemy.draw(g);
            //if(enemy.isCollidedWithShot(shoots))
            if(enemy.isCollidedWithPlayer(player)) {
                enemyList.remove();
                System.exit(0);
            }
        }

        Iterator shootIt=shoots.iterator();
        while(shootIt.hasNext()){
            Shoot shoot= (Shoot) shootIt.next();
            shoot.draw(g);
        }
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
        JFrame frame = new JFrame("Shooting"); //todo  Hi Intellij
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main main1 = new Main();
        frame.getContentPane().add(main1);
        frame.pack();
        frame.setVisible(true);
        main1.start();
    }
}
