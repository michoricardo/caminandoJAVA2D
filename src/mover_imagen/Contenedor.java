
package mover_imagen;


  

import javax.swing.JComponent;

import javax.swing.JPanel;

import javax.swing.ImageIcon;

import java.awt.Graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage; //micho
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Contenedor extends JComponent 
//https://www.youtube.com/watch?v=XHLUROIh2rw 
{
     static JPanel panel;
    
     static int columna = 3;
     
     static int fila = 195;
     
     static int numero = 1;
    
  Contenedor(JPanel panel)
  {
      this.panel = panel;
      
      setBounds(0, 0,panel.getWidth() , panel.getHeight());
         
  }
  BufferedImage bi; //para la imagen de fondo
  public void paint(Graphics g)
  {

		  

	try { //tiene que ser un try catch por si no encuentra la imagen de fondo
	bi = ImageIO.read(new File("C:\\Users\\DELL\\Downloads\\Mover_imagen\\src\\mover_imagen\\imagenes\\fondo.jpg"));
	}catch(IOException ex){
	}
    g.drawImage(bi,0,0,getWidth(),getHeight(),null); //inicia en 0,0 y se adapta al contenedor
    
    //Imagenes PNG que saqué de internet y quité el fondo en powerpoint
	ImageIcon imagen =new ImageIcon(new ImageIcon(getClass().getResource("imagenes/"+numero+".png")).getImage());
    g.setColor(Color.GRAY); //Color de la calle
    g.fillRect(0, 500, 1000, 255); //x,y,width, height
    
    //líneas punteadas
    g.setColor(Color.WHITE);
    Graphics2D g2d = (Graphics2D) g.create();
    Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{40}, 0); //40 de espacio
    g2d.setStroke(dashed);
    g2d.drawLine(0, 540, 1000, 540); //x1,y1,x2,y2

//    g2d.setColor(Color.RED);
//    g2d.fill(new Rectangle2D.Double(300, 400, 400, 100));//X,Y, WIDTH,,HEIGTH
//    g2d.fill(new Rectangle2D.Double(150, 75, 100, 50));
//    g2d.fill(new Polygon(new int[] {185, 150, 215}, new int[] {150, 200, 200}, 3));

    g2d.dispose();
    

    
    

     
     g.drawImage(imagen.getImage(), columna, fila, 206, 356, null); 
     
       g.setColor(Color.WHITE); //NUBES
       
       g.fillOval(35,40 ,100, 30);
       g.fillOval(70,45 ,150, 50);
       g.fillOval(160,55 ,100, 30);
       g.fillOval(320,45 ,100, 25);
       g.fillOval(340,40 ,100, 25);
       g.fillOval(700,40 ,100, 30);
       g.fillOval(780,40 ,100, 30);
       g.fillOval(610,50 ,150, 30);
       
       g.setColor(Color.ORANGE); //SOL   
       g.fillOval(480,30 ,100,100);

       


       
       }
  
  static Thread hilo = new Thread()
    {
       @Override
        public void run()
        {
            try
            {
                while(true)
                {
                  numero++;
                  
                  if(numero==5) //SON 5 IMAGENES, SE REPITE SI SE LLEGA A LA 5
                  {
                    numero=1;
                  }
                  
                 panel.repaint();  
                 
                 columna+=10;
                 
                  hilo.sleep(100);
                }
                
            } catch (java.lang.InterruptedException ex) {
                                                           System.out.println(ex.getMessage()); 
                                                         }
        }
    };
   
   public static void mover()
  {
     if(!hilo.isAlive())
    {
      hilo.start();
    }
      columna = 3;
    }
}










