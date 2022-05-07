//d oberle, 2020
//Graphics demo with mouse and sound where there is a board and pieces on the board that can be picked up and moved
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
//the color for slowed zombies is #107ad1
public class MyGridExample extends JPanel implements MouseListener, MouseMotionListener
{
   boolean t = true;
   private ImageIcon black = new ImageIcon("graphics/blackPiece.gif");//change these to plants.
   private ImageIcon white = new ImageIcon("graphics/whitePiece.gif");
   private ImageIcon blueTile = new ImageIcon("graphics/images_1.jpg");
   private ImageIcon redTile = new ImageIcon("graphics/images_1.jpg");      //JPG images can not have transparency
   private ImageIcon peaShooter = new ImageIcon("graphics/peaShooter.jpg");
   private ImageIcon peaShooter1 = new ImageIcon("graphics/peaShooter2.gif");
   private ImageIcon iceShooter = new ImageIcon("graphics/iceShooter.gif");
   private ImageIcon iceShooter1 = new ImageIcon("graphics/iceShooter1.gif");
   private ImageIcon snowPeaProjectile = new ImageIcon("graphics/snowPeaProjectile.gif");
   private ImageIcon coconutCannonIcon = new ImageIcon("graphics/coconutcannonIcon.gif");
   private ImageIcon coconutCannon = new ImageIcon("graphics/CoconutCannon.gif");
   private ImageIcon coconutProjectile = new ImageIcon("graphics/CoconutProjectile.gif");
   private ImageIcon explosion = new ImageIcon("graphics/explosionEffect.gif");
   private ImageIcon wallNut = new ImageIcon("graphics/wallNut.jpg");//buy menu image
   private ImageIcon wallNut1 = new ImageIcon("graphics/Walnut1.gif");
   private ImageIcon wallNut2 = new ImageIcon("graphics/Walnut2.gif");
   private ImageIcon wallNut3 = new ImageIcon("graphics/Walnut3.gif");
   private ImageIcon zombie = new ImageIcon("graphics/zombie2.gif");	
   private ImageIcon zombieWalking1 = new ImageIcon("graphics/zombieWalking1.gif");	
   private ImageIcon zombieWalking2 = new ImageIcon("graphics/zombieWalking2.gif");	
   private ImageIcon zombieWalking3 = new ImageIcon("graphics/zombieWalking3.gif");
   private ImageIcon zombieWalking4 = new ImageIcon("graphics/zombieWalking4.gif");
   private ImageIcon icezombieWalking1 = new ImageIcon("graphics/iceZombieWalking1.gif");	
   private ImageIcon icezombieWalking2 = new ImageIcon("graphics/iceZombieWalking2.gif");	
   private ImageIcon icezombieWalking3 = new ImageIcon("graphics/iceZombieWalking3.gif");
   private ImageIcon icezombieWalking4 = new ImageIcon("graphics/iceZombieWalking4.gif");		
   private ImageIcon iceZombie = new ImageIcon("graphics/iceZombie.gif");	
   private ImageIcon zombieEating = new ImageIcon("graphics/zombieEating.gif");	
   private ImageIcon iceZombieEating = new ImageIcon("graphics/iceZombieEating.gif");	
   private ImageIcon dyingZombie = new ImageIcon("graphics/dyingZombie.gif");	
   private ImageIcon iceDyingZombie = new ImageIcon("graphics/iceDyingZombie.gif");	
   private ImageIcon incineratedZombie = new ImageIcon("graphics/IncineratedZombie.gif");	
   private ImageIcon sun = new ImageIcon("graphics/sun.gif");
   private ImageIcon sunFlower = new ImageIcon("graphics/sunFlower.gif");
   private ImageIcon sunFlowerIcon = new ImageIcon("graphics/sunFlowerIcon.gif");


   private static final int SIZE=60;	//size of cell being drawn
 
   //This array will be represented graphically on the screen
   private static int[][] board,       //the background 0-blueTile, 1-redTile 
                          pieces;	   //the pieces 0-none, 1-black chip, 2-white chip
   private static Timer timer;
   private static plant[][] plantBoard;  
   private static plant[] buyMenu;  
   private static ArrayList<projectile> projectiles;
   private static ArrayList<zombie> zombies;  
   private static ArrayList<explosion> explosions; 
   private static ArrayList<dyingZombie> dyingZombies;
   private static ArrayList<incineratedZombie> incineratedZombies;
   private static ArrayList<sun> suns;
   private static int playerR;			//start row for the player selection tool
   private static int playerC;			//start col for the player selection tool
   private static int selected;        //value of the piece selected (0-none, 1-black chip, 2-white chip)
   private static plant selectedPlant; //holds onto the plant Object that is selected (backend)
   protected static int mouseX;			//locations for the mouse pointer
   protected static int mouseY;
   private int money;
   private double rateIncrease = 0;
   public int sunFlowerDelay = 2000;
   public MyGridExample()
   {
      addMouseListener( this );
      addMouseMotionListener( this );
      mouseX = 0;
      mouseY = 0;
      money = 10000;
      board = new int[5][10];      //background
      pieces = new int[5][10];     //pieces that go on top of the background
      //timer
      timer = new Timer(0, new Listener());      
      //
      //plant logic starts here
      plantBoard = new plant[5][10];//new plant board to control the backend
      buyMenu = new plant[10]; // can change this value as more types of plants are added
      buyMenu[1] = new plant(0, 0, 7000, 0, 0);//wallnut is 1
      buyMenu[2] = new plant(0, 0, 500, 20, 500);//peashooter is 2
      buyMenu[3] = new plant(0, 0, 500, 25, 500);//iceShooter is 3
      buyMenu[4] = new plant(0,0, 500, 500, 5000);//coconut cannon is 4
      buyMenu[5] = new plant(0, 0, 500, 0, sunFlowerDelay);
      //plant logic ends here
      //projectile logic begins here
      projectiles = new ArrayList<projectile>();
      //projectile logic ends here
      //zombie logic begins here
      zombies = new ArrayList<zombie>();
      //zombie logic ends here
      //explosions
      explosions = new ArrayList<explosion>();
      //explosions end
      //normal dying zombie
      dyingZombies = new ArrayList<dyingZombie>();
      //normal dying zombies end
      //incinerated zombies
      incineratedZombies = new ArrayList<incineratedZombie>();
      //incinerated zombies end
      //sun logic
      suns = new ArrayList<sun>();
      //sun end
      selected = 0; //holds onto the image of the plant selected
      int nextValue = 0;            //to assign alternating values to the board (0,1)
      for(int r=0;r<board.length;r++)	
      {
         for(int c=0;c<board[0].length;c++)
         {
            board[r][c] = nextValue;
            nextValue = (nextValue+1)%2;           //alternate between 0 and 1
           /* if(Math.random() < 0.25)               //put some random pieces on the board
               pieces[r][c] = (int)(Math.random()*3);
               */
         }
         nextValue = (nextValue + 1) % 2;
      }
     
      playerR = board.length/2;							//start player position in the middle
      playerC = board[0].length/2;	
      Sound.initialize();
      timer.start();
   }


	//post:  shows different pictures on the screen in grid format depending on the values stored in the array board
	//			0-blueTile, 1-redTile
   //       shows pieces on the board and the crosshair
   //       0-no piece, 1-black chip, 2-white chip
   public void showBoard(Graphics g)	
   {
      g.drawImage(peaShooter.getImage(), 20, 20, SIZE, SIZE, null); // go to line  if(mouseC == 0 && mouseR == 0){ around 173
      g.drawImage(wallNut.getImage(), 100, 20, SIZE, SIZE, null);
      g.drawImage(iceShooter.getImage(), 180, 20, SIZE, SIZE, null);
      g.drawImage(coconutCannonIcon.getImage(), 260, 20, SIZE, SIZE, null);
      g.drawImage(sunFlowerIcon.getImage(), 340, 20, SIZE, SIZE, null);
   
      int x =0, y = 100;	//if y is ever changed, go to int mouseR = ((mouseY-100)/SIZE); and change its value  //upper left corner location of where image will be drawn
      int q = 0, w = 100;
      for(int r=0;r<board.length;r++)
      {
         x = 0;						            //reset the row distance
         for(int c=0;c<board[0].length;c++)
         {
            if(board[r][c]==0)               //draw the tile background
               g.drawImage(blueTile.getImage(), x, y, SIZE, SIZE, null);  
            else //if(board[r][c]==1)
               g.drawImage(redTile.getImage(), x, y, SIZE, SIZE, null);  
               
            if(pieces[r][c]==1)              //draw the player chips
               g.drawImage(wallNut1.getImage(), x, y, SIZE, SIZE, null);  
            else if(pieces[r][c]==2)
               g.drawImage(peaShooter1.getImage(), x, y, SIZE, SIZE, null);   
            else if(pieces[r][c]==3)
               g.drawImage(iceShooter1.getImage(), x, y, SIZE, SIZE, null);   
            else if(pieces[r][c] == 4)
               g.drawImage(coconutCannon.getImage(), x, y, SIZE+5, SIZE+5, null);   
            else if(pieces[r][c] == 5)
               g.drawImage(sunFlower.getImage(), x, y, SIZE, SIZE, null);      
            /*if(r==playerR && c==playerC)	   //draw the crosshair on the board after the cell has been drawn
            {
               if(selected == 0)             //no piece has been selected
                  g.drawImage(crossHair.getImage(), x, y, SIZE, SIZE, null);  
               else if(selected==1)          //black chip selected
                  g.drawImage(black.getImage(), x, y, SIZE, SIZE, null);  
               else //if(selected==2)        //white chip selected
                  g.drawImage(white.getImage(), x, y, SIZE, SIZE, null);  
             }*/
            /* for(int i = 0; i < plantBoard.length; i++){
               for(int j = 0; j < plantBoard[0].length; j++){
                  if(plantBoard[i][j] != null){
                     if(plantBoard[i][j].getOriginalHp() > 2500){//only a wallnut could have this much hp
                        if(plantBoard[i][j].getHealth() < plantBoard[i][j].getOriginalHp() * (Double)1.0/3){
                           g.drawImage(wallNut3.getImage(), i, j, SIZE, SIZE, null);  
                        }else if(plantBoard[i][j].getHealth() < plantBoard[i][j].getOriginalHp() * (Double)2.0/3){
                           g.drawImage(wallNut2.getImage(), i+550, j+250, SIZE, SIZE, null);  
                        }
                     }
                  }                  
               }
            }*/
            x+=SIZE;
         }
         y+=SIZE;
         
      }
      for(projectile proj : projectiles){
         if(proj.getColor().equals("blue")){//checks if a projectile is shot from the iceShooter
            g.setColor(Color.blue);//projectile color
            if(proj != null){
               g.drawImage(snowPeaProjectile.getImage(), (int)(proj.getX()*SIZE + SIZE), proj.getY()*SIZE + SIZE + 50, 25, 25, null);
            }
         }else if(proj.getColor().equals("brown")){
            g.drawImage(coconutProjectile.getImage(), (int)(proj.getX()*SIZE + SIZE), proj.getY()*SIZE + SIZE + 50, 40, 40, null);
         
         }else{
            g.setColor(Color.green);//projectile color
            if(proj != null){
               g.fillOval((int)(proj.getX()*SIZE + SIZE), proj.getY()*SIZE + SIZE + 50, 15, 15);
            }
         }
         
      }

      for(explosion ex : explosions){
         if(ex != null){
            g.drawImage(ex.getPicture().getImage(), (int)(ex.getX()*SIZE + SIZE) - 70, ex.getY()*SIZE + SIZE, 150, 150, null);
         }
      }

      for(dyingZombie dz : dyingZombies){
         if(dz != null){
            if(dz.isFrozen()){
               g.drawImage(dz.getPicture().getImage(), (int)(dz.getX()*SIZE + SIZE) - 80, dz.getY()*SIZE + SIZE +15, SIZE+60, SIZE+30, null);
            }else{
               g.drawImage(dz.getPicture().getImage(), (int)(dz.getX()*SIZE + SIZE) - 80, dz.getY()*SIZE + SIZE +15, SIZE+60, SIZE+30, null);
            }
         }
      }

      for(incineratedZombie ic : incineratedZombies){
         g.drawImage(ic.getPicture().getImage(), (int)(ic.getX()*SIZE + SIZE) - 55, ic.getY()*SIZE + SIZE +15, SIZE, SIZE+20, null);

      }
   
      for(zombie z : zombies){
         if(z != null){
            if(z.getMovementSpeed() == 0.0002 && z.isEating()){//checks if the zombie is considered slowed
               g.drawImage(iceZombieEating.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE +15, SIZE+60, SIZE+30, null); 
               //creates the image of a slowed zombie
            }else if(z.isEating()){
               g.drawImage(zombieEating.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE + 15, SIZE+60, SIZE+30, null); 
            }else{
               int randomState = z.getRandomState();
               if(z.getMovementSpeed() < 0.0005){
                  switch (randomState){
                     case 1: g.drawImage(iceZombie.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE + 15, SIZE+60, SIZE+30, null);  
                     break;
                     case 2: g.drawImage(icezombieWalking1.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE + 15, SIZE+60, SIZE+30, null);  
                     break;
                     case 3: g.drawImage(icezombieWalking3.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE + 15, SIZE+60, SIZE+30, null); 
                     break;
                     case 4: g.drawImage(icezombieWalking4.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE + 15, SIZE+60, SIZE+30, null);  
                     break;  
                     case 5:  g.drawImage(icezombieWalking2.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE + 15, SIZE+60, SIZE+30, null);                     
                  }  
               }else{
                  switch (randomState){
                     case 1: g.drawImage(zombie.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE + 15, SIZE+60, SIZE+30, null);  
                     break;
                     case 2: g.drawImage(zombieWalking1.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE + 15, SIZE+60, SIZE+30, null);  
                     break;
                     case 3: g.drawImage(zombieWalking3.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE + 15, SIZE+60, SIZE+30, null); 
                     break;
                     case 4: g.drawImage(zombieWalking4.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE + 15, SIZE+60, SIZE+30, null);  
                     break;
                     case 5: g.drawImage(zombieWalking2.getImage(), (int)(z.getX()*SIZE + SIZE) - 80, z.getY()*SIZE + SIZE + 15, SIZE+60, SIZE+30, null);           
                  }
               }
               
               //draw zombie here
            }
         }
      }
      
      for(sun s : suns){
         g.drawImage(sun.getImage(),  (int)(s.getX()*SIZE), (int)s.getY()*SIZE, 70, 70, null);
      }
      
      updateMouse(g);
      updateMoney(g);
   }
   //updating methods
   public void updateMouse(Graphics g){
      if(selected == 2){
         g.drawImage(peaShooter1.getImage(), mouseX-SIZE/2, mouseY-SIZE/2, SIZE, SIZE, null);
      } 
      if(selected == 1){
         g.drawImage(wallNut1.getImage(), mouseX-SIZE/2, mouseY-SIZE/2, SIZE, SIZE, null);
      }
      if(selected == 3){
         g.drawImage(iceShooter1.getImage(), mouseX-SIZE/2, mouseY-SIZE/2, SIZE, SIZE, null);      
      }
      if(selected == 4){
         g.drawImage(coconutCannon.getImage(), mouseX-SIZE/2, mouseY-SIZE/2, SIZE, SIZE, null);
      }
      if(selected == 5){
         g.drawImage(sunFlower.getImage(), mouseX-SIZE/2, mouseY-SIZE/2, SIZE, SIZE, null);
      }
   }

   public void updateMoney(Graphics g){
      String moneyString = Integer.toString(money);      
      g.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
      g.setColor(Color.white);
      g.drawString("Suns:" + moneyString, 450, 25);
      g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
      g.drawString("$100", 20, 20);
      g.drawString("$50", 100, 20);
      g.drawString("$150", 180, 20);
      g.drawString("$400", 260, 20);
      g.drawString("$50", 340, 20);
   }

   public double distanceOf(double x1, double y1, double x2, double y2){
      return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
   }

	//THIS METHOD IS ONLY CALLED THE MOMENT A KEY IS HIT - NOT AT ANY OTHER TIME
	//pre:   k is a valid keyCode
	//post:  changes the players position depending on the key that was pressed (sent from the driver)
	//			keeps the player in the bounds of the size of the array board
   public void processUserInput(int k)
   {
      if(k==KeyEvent.VK_Q || k==KeyEvent.VK_ESCAPE)					//End the program	
         System.exit(1);
      if(k==KeyEvent.VK_UP && playerR>0)
         playerR--;
      else 
         if(k==KeyEvent.VK_DOWN && playerR<board.length-1)
            playerR++;
         else
            if(k==KeyEvent.VK_LEFT && playerC>0)
               playerC--;
            else 
               if(k==KeyEvent.VK_RIGHT && playerC<board[0].length-1)
                  playerC++;
               else 
                  if(k==KeyEvent.VK_SPACE)	//try to pick up or place a piece
                  {
                     if (selected == 0 && pieces[playerR][playerC] != 0)
                     {                       //pick up a piece that is there
                        selected = pieces[playerR][playerC];
                        pieces[playerR][playerC] = 0;
                        Sound.click();
                     }
                     else if (selected != 0 && pieces[playerR][playerC] == 0)
                     {                       //put down a piece you selected
                        pieces[playerR][playerC] = selected;
                        selected = 0;
                        Sound.click();
                     }
                     else                    //illegal move
                     {
                        Sound.randomNote();
                     }
                  }
      repaint();			//refresh the screen
   }

   public void paintComponent(Graphics g)
   {
      super.paintComponent(g); 
      g.setColor(Color.gray);		//draw a gray boarder around the board
      g.fillRect(0, 0, (board[0].length*SIZE), (board.length*SIZE));
      showBoard(g);					//draw the contents of the array board on the screen
   }
   public int count = 0;
	 //***BEGIN MOUSE STUFF***
   private class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)	//this is called for each timer iteration
      {
         //for zombies
         count++;
         boolean spawnRate = (Math.random() < 0.0025);//controls the rate at which zombies are randomly spawned
         boolean sunSpawnRate = count > 5000;
         if(spawnRate){
            int random = (int)(Math. random()*(4-0+1))+0;//controls the lane that the zombie is spawned in
            int randomHealth = (int)(Math.random()*(500-100+1)) + 100;
            zombies.add(new zombie(9.2, random, randomHealth, 1, false));  
         }
         int randomY = 2;
         if(t){
            zombies.add(new zombie(9.2, 3, 500, 1, false)); 
            int randomX = (int)(Math.random()*(board.length - 0+1))+0;            
            suns.add(new sun(randomX, (double) randomY, 0.001));
            t = false;
         }
         
         if(sunSpawnRate){
            int randomX = (int)(Math.random()*(board.length - 0+1))+0;
            count = 0;
            suns.add(new sun(randomX, (double) randomY, 0.001));
         }
         
        

         for(int i = 0; i < suns.size(); i++){
            if(suns.get(i).getY() > 7){
               suns.remove(i);
               i--;
            }else if(suns.get(i).isRemoved()){
               suns.remove(i);
               i--;
            }else{
               suns.get(i).decreaseY();
               
            }

         }

         rateIncrease += 0.00001;
         for(int index = 0; index < zombies.size(); index++){
            zombies.get(index).incrementX();
            zombies.get(index).incrementFrame();
            //System.out.println("zombie x:" + zombies.get(index).getX() + "zombie y:" + zombies.get(index).getY());
            if(zombies.get(index).getX() < 0){
               System.out.println("The Zombies ate your brains");
               zombies.remove(index);
               index--;
            }
         }
      
         //for projectiles
         for(int i = 0; i < projectiles.size(); i++){
            projectiles.get(i).incrementX();
            //it seems that the more zombies there are in one lane, the faster the projectile moves.
            //System.out.println("projectile X:" + projectiles.get(i).getX() + "projectile Y:" + projectiles.get(i).getY());
            if(projectiles.get(i).getX() > 11.0){
               projectiles.remove(i);
               i--;
            }            
         }
         //removing explosions
         for(int i = 0; i < explosions.size(); i++){
            explosions.get(i).incrementFrame();           
            if(explosions.get(i).isRemoved()){
               explosions.remove(i);
               i--;
            }            
         }
         //removing dead zombie animation
         for(int i = 0; i < dyingZombies.size(); i++){
            dyingZombies.get(i).incrementFrame();            
            if(dyingZombies.get(i).isRemoved()){
               dyingZombies.remove(i);
               i--;
            }            
         }
         //removing dead incinerated zombies animation
         for(int i = 0; i < incineratedZombies.size(); i++){
            incineratedZombies.get(i).incrementFrame();            
            if(incineratedZombies.get(i).isRemoved()){
               incineratedZombies.remove(i);
               i--;
            }            
         }
         //for plants
         for(int i = 0; i < plantBoard.length; i++){
            for(int j = 0; j < plantBoard[0].length; j++){
               if(plantBoard[i][j] != null){
                  plantBoard[i][j].incrementFrame();//increments all plant's frames in plantBoard by 1
                  //only for sunflowers
                  if(plantBoard[i][j].getDelay() == sunFlowerDelay && plantBoard[i][j].getDamage() == 0 && plantBoard[i][j].getCurrentFrame() > sunFlowerDelay){
                     suns.add(new sun(plantBoard[i][j].getX(), plantBoard[i][j].getY()+1, 0));
                     plantBoard[i][j].setCurrentFrame(0);
                     continue;
                  }
                  projectile temp = plantBoard[i][j].shoot(); 
                  if(temp != null && temp.getDamage() != 0){//wall nuts or plants that do not do damage are not given a projectile.
                     for(zombie z : zombies){//checks if a zombie is in the same row as plant, then the plant will shoot.
                        if(z.getY() == i){
                           if(plantBoard[i][j].getDamage() == 25){//this damage is unqiue to iceShooter
                              temp.setColor("blue");//this color is unique to the ice projectile
                              //the projectile color is set to blue, all zombies hit by it will have their ms slowed
                           }else if(plantBoard[i][j].getDamage() == 500){
                              temp.setColor("brown");
                           }
                           projectiles.add(temp);
                           break;
                        }
                     }
                     //System.out.println("added a projectile");
                  }
               }
            }
         }
         
         //to check if a projectile collides with a zombie
            for(int j = 0; j < projectiles.size(); j++){
               boolean collides = false;
               int index = -1;
               for(int i = 0; i < zombies.size(); i++){     
                  if(Math.abs(zombies.get(i).getX() - projectiles.get(j).getX()) < 0.5 && Math.abs(zombies.get(i).getY() - projectiles.get(j).getY()) < 1){
                     collides = true;
                     index = i;
                  }
               }
               if(collides){
               //coconutbomb logic
                  if(projectiles.get(j).getColor().equals("brown")){
                     //zombies.get(i).deductHp(projectiles.get(j).getDamage());
                     explosions.add(new explosion(projectiles.get(j).getX(), projectiles.get(j).getY(), 200));
                        for(int k = 0; k < zombies.size(); k++){
                           //System.out.println(distanceOf(zombies.get(k).getX(), zombies.get(k).getY(), projectiles.get(j).getX(), projectiles.get(j).getY()) + " the x of zombie: " + zombies.get(k).getX() + "the y of zombie: " + zombies.get(k).getY());
                           if(distanceOf(zombies.get(k).getX(), zombies.get(k).getY(), projectiles.get(j).getX(), projectiles.get(j).getY()) < 2){
                              int damageFallOffCalculation = (int)(((2 - distanceOf(zombies.get(k).getX(), zombies.get(k).getY(), projectiles.get(j).getX(), projectiles.get(j).getY()))/2 + 0.35) * projectiles.get(j).getDamage());
                              //^damage calculation: zombies hit in the center are dealt 135% of the bomb's damage, falls off based on distance, at farthest distance zombie is dealt 35% damage.
                              zombies.get(k).deductHp(damageFallOffCalculation);
                              if(zombies.get(k).getHealth() <= 0){//check if a zombie died. If so, zombies is removed 
                                 incineratedZombies.add(new incineratedZombie(zombies.get(k).getX(), zombies.get(k).getY(), 300));
                                 zombies.remove(k);
                                 k--;
                                 money += 50;
                              //System.out.println("zombie has been removed");
                              }
                           //projectile is removed regardless.
                           //System.out.println("projectile has been removed");
                           }
                        }
                        projectiles.remove(j);
                        j--;
                     
                  } else {
                  
                     zombies.get(index).deductHp(projectiles.get(j).getDamage());
                     if(projectiles.get(j).getColor().equals("blue")){
                        zombies.get(index).setMovementSpeed(0.0002);
                        zombies.get(index).setFrozen(true);
                           if(zombies.get(index).getHealth() <= 0){//check if a zombie died. If so, zombies is removed 
                              dyingZombies.add(new dyingZombie(zombies.get(index).getX(), zombies.get(index).getY(), 300, true));
                              zombies.remove(index);
                              money += 50;
                           }
                        }else{
                           if(zombies.get(index).getHealth() <= 0){//check if a zombie died. If so, zombies is removed
                              dyingZombies.add(new dyingZombie(zombies.get(index).getX(), zombies.get(index).getY(), 200, false)); 
                              zombies.remove(index);
                              money += 50;
                        }                        
                        //System.out.println("zombie has been removed");
                        }
                                          
                     projectiles.remove(j);//projectile is removed regardless.
                  //System.out.println("projectile has been removed");
                  j--;
                  }
               }
            
            }
         //check if zombie is ontop of a plant
         for(int i = 0; i < plantBoard.length; i++){
            for(int j = 0; j < plantBoard[0].length; j++){
               for(int k = 0; k < zombies.size(); k++){
                  //below fixes all zombies that are stuck eating but aren't actually eating any plants.
                  if(plantBoard[i][j] == null && zombies.get(k).isEating() && zombies.get(k).getY() == i && Math.abs(zombies.get(k).getX() - j) < 0.1) 
                     zombies.get(k).setIsEating(false);
                  if(plantBoard[i][j] != null){
                     if(zombies.get(k).getY() == i && Math.abs(zombies.get(k).getX() - j) < 0.1){//if a zombie overlaps a plant
                        zombies.get(k).setIsEating(true);//zombie is then halted to eat the plant
                        plantBoard[i][j].deductHp(zombies.get(k).getDamage());//zombie inflicts damage each frame
                        if(plantBoard[i][j].getHealth() <= 0){//remove the plant if its HP is reduced under 0
                           zombies.get(k).setIsEating(false);
                           plantBoard[i][j] = null;
                           pieces[i][j] = 0;
                        }
                     }
                  }
               }
            }
         }
         
         repaint();
      }
   }

   public void mouseClicked( MouseEvent e )
   {
      
      int button = e.getButton();
      if(button == MouseEvent.BUTTON1 || button == MouseEvent.BUTTON3)
      {
         if(mouseX < board[0].length*SIZE && mouseY > 50){
            int mouseC = (mouseX/SIZE);
            int mouseR = (mouseY/SIZE);
            for(int i = 0; i < suns.size(); i++){
               //System.out.println("The mouse location is x: " + mouseC + " y: " + mouseR + "The sun location is x: " + suns.get(i).getX() + " y: " + suns.get(i).getY());
               if(distanceOf(mouseC, mouseR, suns.get(i).getX(), suns.get(i).getY()) < 2){
                  money+=50;
                  suns.remove(i);
                  i--;
                  repaint();
               }
            }
         }
         if(mouseX < board[0].length*SIZE && mouseY < 100){
            int mouseC = (mouseX/SIZE);
            int mouseR = (mouseY/SIZE);
           
            //System.out.println(mouseC + " " + mouseR);
            if(mouseC == 0 && mouseR == 0){//for peashooter
               if(money >= 100){//cost
                  selected = 2;
                  selectedPlant = buyMenu[2].copy(); 
                  money -= 100;
                  Sound.click();                  
               }else{
                  System.out.println("Not enough money");                  
               }
               repaint();
            }
            else if(mouseC == 2 && mouseR == 0){//for wallnut
               if(money >= 50){//cost
                  selected = 1;
                  selectedPlant = buyMenu[1].copy(); 
                  money -= 50;
                  Sound.click();
               }else{
                  System.out.println("Not enough money");
               }
               repaint();
            }
            else if(mouseC == 3 && mouseR == 0){//for iceShooter
               if(money >= 150){
                  selected = 3;
                  selectedPlant = buyMenu[3].copy();
                  money -= 150;
               }else{
                  System.out.println("Not enough money");
               }
               repaint();
            }
            else if(mouseC == 4 && mouseR == 0){
               if(money >= 400){
                  selected = 4;
                  selectedPlant = buyMenu[4].copy();
                  money -= 400;
               }else{
                  System.out.println("Not enough money");
               }
               repaint();
            }
            else if(mouseC == 6 && mouseR == 0){
               if(money >= 50){
                  selected = 5;
                  selectedPlant = buyMenu[5].copy();
                  money -= 50;
               }else{
                  System.out.println("Not enough money");
               }
               repaint();
            }
            //mouseC is the plant index
            //make an array of plants
            //each index of the array of plants is represented by a tile, if a mouse is inbetween a certain bound and pressed, select that plant and convert mouse crosshair to plant image.
            //
         }else{
            int mouseR = ((mouseY-100)/SIZE);
            int mouseC = (mouseX/SIZE);
         
            if(mouseR >=0 && mouseC >= 0 && mouseR < board.length && mouseC < board[0].length)
            {     //if the mouse is in bounds of the board
               playerR = mouseR;
               playerC = mouseC;
               if (selected == 0 && pieces[playerR][playerC] == 0)//this probably doesnt matter
               {  //pick up a piece that is there               
                  selected = pieces[playerR][playerC];
                  if(selected == 2 && money >= 100){
                     money -= 100;
                     pieces[playerR][playerC] = 0;
                     Sound.click();
                  
                  } else if(selected == 1 && money >= 50){
                     money -= 50;
                     pieces[playerR][playerC] = 0;
                     Sound.click();
                  }
               
               }//the above probably doesnt matter idk.
               else if (selected != 0 && pieces[playerR][playerC] == 0 && selectedPlant != null)//to put stuff down
               {  //put down a piece you selected
                  pieces[playerR][playerC] = selected;
                  selectedPlant.setX(playerC);
                  selectedPlant.setY(playerR);
                  plantBoard[playerR][playerC] = selectedPlant;
                  selected = 0;
                  selectedPlant = null;
                  Sound.click();
               }
               else//illegal move
               {
                  Sound.randomNote();
               }
            }
            else
            {     //reset the player into the center
               playerR = board.length/2;
               playerC = board[0].length/2;
            }
         
         } 
      }
      repaint();
   }

   public void mousePressed( MouseEvent e )
   {}

   public void mouseReleased( MouseEvent e )
   {}

   public void mouseEntered( MouseEvent e )
   {}

   public void mouseMoved( MouseEvent e)
   {
      mouseX = e.getX();
      mouseY = e.getY();
      
      int mouseR = (mouseY/SIZE);
      int mouseC = (mouseX/SIZE);
     // System.out.println(mouseR+":"+mouseC);
      if(mouseR >=0 && mouseC >= 0 && mouseR < board.length && mouseC < board[0].length)
      {
         playerR = mouseR;
         playerC = mouseC;
      }
      else
      {
         playerR = board.length/2;
         playerC = board[0].length/2;
      
      }
      repaint();			//refresh the screen
   }

   public void mouseDragged( MouseEvent e)
   {}

   public void mouseExited( MouseEvent e )
   {}

}
