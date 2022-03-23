import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;


public class Yilan extends JLabel {

  public YilanKutusu head = new YilanKutusu();

  public Timer mTimer = null;

  public Yem mYem = new Yem();
  public Random mRandom = null;//yeml rastgele bir koordinata yerleştirilmeli
  public ArrayList<YilanKutusu> KutuListesi = new ArrayList<YilanKutusu>();



  @Override
  public void paint(Graphics g) {
    super.paint(g);// default çizim işlemlelrini java yapsın

    Graphics2D oyunAlanCizgisi = (Graphics2D) g;// graphics nesnesini graphics2D ye dönüşüm yaptı
    oyunAlanCizgisi.setColor(java.awt.Color.black);// çerçeve rengi
    oyunAlanCizgisi.setStroke(new BasicStroke(10));// çerçeve kalınlığı
    oyunAlanCizgisi.drawRect(5, 5, getWidth()-10, getHeight()-10);// kalemin kalınlığına göre çerçeveyi ortalandı
  }

  Yilan() {// constructor
    mRandom = new Random(System.currentTimeMillis());
    addKeyListener(new KlavyeKontrol());// tuşlarla yılan arasında bağ kuruldu
    setFocusable(true);// tuşa bastıgımızda bu bilgiyi hangi pencerinin alacağına karar verir
    mTimer = new Timer(100, new TimerKontrol());//100 ms de bir çağrılcak, kullanacağı listenerı veririz
    mTimer.start();

    KutuListesi.add(head);// listeye headi ekledik çünkü ilk eleman o 
    for(int i = 0; i<7; i++)
    {
      kuyrukEkle();
    }
    add(mYem);
    add(head);
  }

  public void kuyrukEkle()
  {
    YilanKutusu k = KutuListesi.get(KutuListesi.size()-1).YeniYilanKutusu();//get ile son eleman alındı, get'in parantezindeki işlemler ile son elemanın indeksi alındı ve yeni kutu oluşturuldu
    KutuListesi.add(k);
    add(k);
  }

  public void yemEkle()// sadece yemin yerini değiştirir ve yılanın kuyruğuna bir eleman daha ekler.
  {
    int Width = getWidth()-30-mYem.mGenislik;//rastgele gelecek yem koordinatı 10 ile bu değer arasında olmalı.10 olma sebebi duvvar kalınlığı
    int Height = getHeight()-30-mYem.mGenislik;

    int positionX = 10 + Math.abs(mRandom.nextInt()) % Width;// negatif olmaması için matematik kütüphanesindeki abs metodunu çağırdık
    int positionY = 10 + Math.abs(mRandom.nextInt()) % Height;

    positionX = positionX - positionX%20;// yem koordinatı 20 nin katı olmalı
    positionY = positionY - positionY%20;

    for(int i=0; i<KutuListesi.size(); i++)// yılanın herhangi bir koordinatı yem ile aynı mı kontrolü
    {
      if((positionX == KutuListesi.get(i).getX()) && (positionY == KutuListesi.get(i).getY()))
      {
        yemEkle();
        return;
      }
    }

    mYem.setPosition(positionX, positionY);
  }

  public void hepsiniHareketEttir()
  {
    for(int i = KutuListesi.size()-1; i>0 ;i--)
    {
      YilanKutusu onceki = KutuListesi.get(i-1);
      YilanKutusu sonraki = KutuListesi.get(i);

      KutuListesi.get(i).hareket();
      sonraki.mYon = onceki.mYon;
    }
    head.hareket();

  }

  public Boolean carpismaVarMi()
  {// yılanın başının koordinatları ile duvarın koordinatları çakışıyor mu kontrolü
    int duvar = 10;

    int genislik = getWidth();
    int yukseklik = getHeight();

    if(head.getX() <= 10 || head.getX()+head.mGenislik >= genislik-duvar )
    return true;

    if(head.getY() <= 10 || head.getY()+head.mGenislik >= yukseklik-duvar )
    return true;

    for(int i=1; i<KutuListesi.size(); i++)// yılanın kendi içinden geçmemesi için yapıldı
    {
      int X = KutuListesi.get(i).getX();
      int Y = KutuListesi.get(i).getY();

      if((X==head.getX()) && (Y==head.getY()))
      return true;
      
    }

    if(mYem.getX()== head.getX() && (mYem.getY()==head.getY()))
    {
      kuyrukEkle();
      yemEkle();
    }

    return false;
  }
  class KlavyeKontrol implements KeyListener// klavye işlemleri sınıfı
  {

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
      
    }
// tuş komut fonksiyonları YilanKutusu sınıfnda
    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {// basılan tuş e parametresine yüklenir
      if(e.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT)
      {
        if(head.mYon != YON.SAG)// eğer yılanın başı sağa gitmiyorsa sola gidebilir
          head.mYon = YON.SOL;
      }

      if(e.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT)
      {
        if(head.mYon != YON.SOL)
          head.mYon = YON.SAG;
      }

      if(e.getKeyCode() == java.awt.event.KeyEvent.VK_UP)
      {
        if(head.mYon != YON.ASAGI)
          head.mYon = YON.YUKARI;
      }

      if(e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN)
      {
        if(head.mYon != YON.YUKARI)
          head.mYon = YON.ASAGI;
      }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
      
    }

  }

  class TimerKontrol implements ActionListener//yılanın sürekli hareket etmesi için gerekli class
  {

    @Override
    public void actionPerformed(ActionEvent e) {
      hepsiniHareketEttir();  
      if(carpismaVarMi())
      mTimer.stop();
    }

  }

}
