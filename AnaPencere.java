import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class AnaPencere extends JFrame {

  private int mGenislik = 800;
  private int mYukseklik = 800;

  private static AnaPencere mPencere = null;// static fonksiyon içinde sadece static nesne elemanlarına erişilebilir.

  private AnaPencere(){
    setDefaultCloseOperation(EXIT_ON_CLOSE);// pencere kapatıldığında programında kapatılması için
    setPosition(mGenislik, mYukseklik);// pencere ölçüleri
    setResizable(false);// pencerenin genişliği ile oynanmaması için 
    
    Yilan yilan = new Yilan();
    add(yilan);// yilan sınıfındaki özellikleri ana pencereye ekledik
  }

  public static AnaPencere PencereGetir()// anaPencere sınıfını private tanımladığım için nesne oluşturulmuyıor. anaPencere türünde bir static fonksiyon oluşturduk.
  {//static tanımlama sebebi sınıf oluşturmadan da fonksiyonu kullanabilmek  
    if(mPencere==null)//daha önce pencere oluşturuldu mu kontrolü
    mPencere = new AnaPencere();

    return mPencere;
  }

  public void setPosition(int genislik, int yukseklik){// ekranin çözünürlüğü alarak çerçeveyi ortala
    Dimension pos = Toolkit.getDefaultToolkit().getScreenSize();//ekranın genişligi

    int positionX = 0;
    int positionY = 0;

    if(mGenislik+100 > pos.width)
    mGenislik = pos.width - 100;
    if(mYukseklik+100 > pos.height)
    mYukseklik = pos.height - 100;

    positionX = (pos.width-mGenislik)/2; // ekran genisliginden pencere genisligini cikarip ikiye bolunce x pozisyonu bulunur
    positionY = (pos.height-mYukseklik)/2;

    setBounds(positionX, positionY, mGenislik, mYukseklik);// aktif hale getirmek için setbounds kullanıldı


  }
  
}
