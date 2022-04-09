import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class Yem extends JLabel {

  public int mGenislik = 20;
  Yem()// constructor
  {
    setPosition(300, 300);
  }

  @Override
  public void paint(Graphics g)// yılanın özelliklerini biçimlendirme
  {
    // Random rand = new Random();
    // float rr = rand.nextFloat();
    // float gg = rand.nextFloat();
    // float bb = rand.nextFloat();

    // Color randColor = new Color(rr, gg, bb);
    
    Graphics2D yilanOzellikleri = (Graphics2D) g;// graphics nesnesini graphics2D ye dönüşüm yaptı
    yilanOzellikleri.setColor(java.awt.Color.black);// çerçeve rengi
    yilanOzellikleri.setStroke(new BasicStroke(2));// çerçeve kalınlığı
    yilanOzellikleri.drawRect(1, 1, mGenislik-2, mGenislik-2);// kalemin kalınlığına göre çerçeveyi ortalandı
    yilanOzellikleri.setColor(Color.red);
  }

  public void setPosition(int positionX, int positionY)
  {
    setBounds(positionX, positionY, mGenislik, mGenislik);
  }
}
