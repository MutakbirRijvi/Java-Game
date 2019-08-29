import java.awt.*;
import java.awt.event.*;
 import javax.swing.*;
import javax.swing.border.LineBorder;


/**
 *
 * @author Rijvi
 */
public class Main {

  
    public static void main(String[] args) {
        JFrame A=new JFrame("Snake");
        //JPanel B=new JPanel();
        //B.setSize()
        JPanel C=new JPanel();
        C.setSize(60,60);
        C.setBorder(new LineBorder(Color.black,2));
        //A.setLayout(new BorderLayout());
       
        A.setSize(617,700);
        A.add(new NPanel());
      //  A.add(B,BorderLayout.EAST);
        //C.setLocation(0, 610);
        A.add(C);
        
        A.setLocationRelativeTo(null);
       
        A.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        A.setVisible(true);
    }
   
}
class NPanel extends JPanel
{
    int block=20;
    int X=20;
    int Y=0;
    int n=KeyEvent.VK_RIGHT,t;
    int length;
    int point_x,point_y;
    String p="0";
    boolean b=true,win=true;
    int[][] Map=new int[800][800];
     int[] ip_X=new int[1500];
     int[] ip_Y=new int[1500];
     Timer T=new Timer(500,new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            move();
           repaint();
        }
       
    });
    
    NPanel()
    {
    initialize();
    
    setSize(602,700);
    //setBorder(new LineBorder(Color.cyan,2));
    setBackground(Color.DARK_GRAY);
    
    setFocusable(true);
    //
    //
    
     //System.out.println("HERE");
     addKeyListener(new KeyAdapter(){

         @Override
        public void keyPressed(KeyEvent e) {
            int t=e.getKeyCode();
            if(win==true){
            //System.out.println("HERE");
           if(t==KeyEvent.VK_UP)
             { if(n!=KeyEvent.VK_DOWN) {T.stop();n=t;X=0;Y=-20;if(move()==true)
             { T.start();}}}
           else if(t==KeyEvent.VK_RIGHT)
             { if(n!=KeyEvent.VK_LEFT) {T.stop();n=t;X=20;Y=0;if(move()==true)
             { T.start();}}}
           else if(t==KeyEvent.VK_DOWN)
             { if(n!=KeyEvent.VK_UP) {T.stop();n=t;X=0;Y=20;if(move()==true)
             { T.start();}}}
           else if(t==KeyEvent.VK_LEFT)
             { if(n!=KeyEvent.VK_RIGHT) {T.stop();n=t;X=-20;Y=0;if(move()==true)
             { T.start();}}}
           else if(t==KeyEvent.VK_SPACE)
               {
          if(b==true) {T.stop(); b=false;}
              else{T.start();b=true;}
        }
           repaint();}
            
        }

     });
    
      
           T.start();
      addMouseListener(new MouseAdapter(){

        @Override
        public void mousePressed(MouseEvent me) {
         initialize();
         T.start();
         win=true;
        }

    });
    

    }
    @Override
   protected void paintComponent(Graphics g)
   {
       int i,j;
       super.paintComponent(g);
       g.setColor(Color.cyan);
       for(i=1;i<=length;i++)
       {
           g.drawRoundRect(ip_X[i],ip_Y[i],block,block,5,5);
           
       //   System.out.print(ip_Y[i]+" ");
       }
       g.setColor(Color.ORANGE);
       
       g.drawOval(point_x, point_y, block, block);
        g.setColor(Color.black);
       g.fillRect(0, 600, 605, 70);
       g.setColor(Color.cyan);
      // g.
       g.drawRect(0,0, 600, 600);
       //g.fillRect(0, 650, 600, 7);
      g.setColor(new Color(250,150,150));
       g.setFont(new Font("San serif",Font.BOLD,50));
       g.drawString("Score : ", 150, 652);
      
       g.drawString(p , 400, 652);
       if(win==false)
       {
           g.setColor(Color.black);
           g.fillRect(140, 270, 290, 120);
           g.setColor(Color.RED);
           g.drawString("Game Over", 152, 320);
           g.setColor(Color.yellow);
           
        g.setFont(new Font("San serif",Font.BOLD,40));
          g.drawString("Click to restart", 145, 370);
       }
       //System.out.println();
   }
   boolean move()
   {
       int a,b,i;
       a=ip_X[1]+X;
       b=ip_Y[1]+Y;
       if(Map[a+20][b+20]==-1) {T.stop();return win=false; }
       if(Map[a+20][b+20]==9){
           length++;
           i=length-3;
           p=String.valueOf(i);
           
           
           if(length==900) return false;
           point();
       }
      Map[ip_X[length]+20][ip_Y[length]+20]=0;
            for(i=length;i>=2;i--)
            {
                ip_X[i]=ip_X[i-1];
                ip_Y[i]=ip_Y[i-1];
                
            }
            
            ip_X[1]=a;
            ip_Y[1]=b;
            Map[a+20][b+20]=-1;
            return true;
   }
   void point()
   {
       while(1==1)
       {
           point_x=((int) (0+Math.random()*29))*20;
            point_y=((int) (0+Math.random()*29))*20;
            if(Map[point_x+20][point_y+20]==0) {Map[point_x+20][point_y+20]=9; break; }
       }
   }
   void initialize()
   {
       int i=0,j,k=620;
       p="0";
       for(j=0;j<620;j+=block)
       {
           Map[i][j]=-1;
           Map[j][i]=-1;
           Map[k][j]=-1;
           Map[j][k]=-1;
       }
         ip_X[1]=120;
      ip_Y[1]=280;
      ip_X[2]=100;
      ip_Y[2]=280;
      ip_X[3]=80;
      ip_Y[3]=280;
      //ip_X[4]=60;
      //ip_Y[4]=280;
      Map[120][300]=-1;
      Map[100][300]=-1;
      Map[140][300]=-1;
      //Map[80][300]=-1;
     /* for(i=4;i<1500;i++)
          ip_X[i]=ip_Y[i]=0;
   */  point();
      X=20;
      Y=0;
      length=3;
       
   }
  /* class ct
{
    public int x;
    public int y;
    /*void ini(){
        x=0;y=0;
    }
    void val(int a,int b)
    {
        x=a;y=b;
    }
}*/
}
