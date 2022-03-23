import javax.swing.JLabel;
import java.awt.*;

public class YilanKutusu extends JLabel {

  public int mYon = YON.SAG;// kutunun nasıl genişlik değişkeni varsa kendie ait bir yön değişkeni tutmalı
  public int mGenislik = 20;

  YilanKutusu()// constructor
  {
    setBounds(100, 100, mGenislik, mGenislik);// yılanın başlangıç konumu
  }

  @Override
  public void paint(Graphics g)// yılanın özelliklerini biçimlendirme
  {
    Graphics2D yilanOzellikleri = (Graphics2D) g;// graphics nesnesini graphics2D ye dönüşüm yaptı
    yilanOzellikleri.setColor(Color.black);// çerçeve rengi
    yilanOzellikleri.setStroke(new BasicStroke(2));// çerçeve kalınlığı
    yilanOzellikleri.drawRect(1, 1, getWidth() - 2, getHeight() - 2);// kalemin kalınlığına göre çerçeveyi ortalandı
    yilanOzellikleri.setColor(Color.red);
  }


  // Yilan sınıfındaki tuş komutların fonksiyonları
  // yılan kendi genişliği kadar hareket edecek
  public void solaGit() {
    int positionX = getX();// yılanın x koordinatını alma
    int positionY = getY();

    positionX = positionX - mGenislik;// yılanın x koordinatından yılanın genişliği çıkarılıyor ve o kadar hareket ediyor
    setBounds(positionX, positionY, mGenislik, mGenislik);
  }

  public void sagaGit() {
    int positionX = getX();
    int positionY = getY();

    positionX = positionX + mGenislik;
    setBounds(positionX, positionY, mGenislik, mGenislik);
  }

  public void yukariGit() {
    int positionX = getX();
    int positionY = getY();

    positionY = positionY - mGenislik;
    setBounds(positionX, positionY, mGenislik, mGenislik);
  }

  public void asagiGit() {
    int positionX = getX();
    int positionY = getY();

    positionY = positionY + mGenislik;
    setBounds(positionX, positionY, mGenislik, mGenislik);
  }

  public YilanKutusu YeniYilanKutusu() {
    YilanKutusu k = new YilanKutusu();

    int X = getX();
    int Y = getY();

    k.setBounds(X, Y, mGenislik, mGenislik);
    k.mYon = -mYon;// yılanın aslı ile eksi yön verildi sonuna ekleyebilmek için
    k.hareket();// 1 birim gters yönde hareket etti
    k.mYon = mYon;// artık yılan ile aynı yönde hareket edebilir ve kuyruk oluşmuş olur

    return k;
  }

  public void hareket()// timera yön verecek
  {
    if (mYon == YON.SOL)
      solaGit();
    else if (mYon == YON.SAG)
      sagaGit();
    else if (mYon == YON.ASAGI)
      asagiGit();
    else
      yukariGit();
  }

}
