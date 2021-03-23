import javax.swing.*;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar {
    JMenu fileMenu,editMenu;
    JMenuItem newOption,openOption,saveOption,searchOption,editOption,undoOption,replaceOption;

    Menu(JFrame frame){
        fileMenu =new JMenu("Dosya");
        editMenu=new JMenu("Düzenle");
        this.add(fileMenu);
        this.add(editMenu);
        frame.setJMenuBar(this);
    }
    public void createMenu(){
        //Dosya ve düzenle menülerimizin içlerindeki seçenekleri oluşturma
        newOption=new JMenuItem("Yeni");
        openOption = new JMenuItem("Aç");
        saveOption = new JMenuItem("Kaydet");
        searchOption = new JMenuItem("Bul");
        editOption=new JMenuItem("KontrolEt Düzenle");
        undoOption=new JMenuItem("Geri Al");
        replaceOption=new JMenuItem("Değiştir");
    }
    public void addMenuItem(){
        fileMenu.add(newOption);
        fileMenu.add(openOption);
        fileMenu.add(saveOption);
        editMenu.add(searchOption);
        editMenu.add(undoOption);
        editMenu.add(replaceOption);
        editMenu.add(editOption);
    }
    public void addEventListener(JFrame frame){
        newOption.addActionListener((ActionListener) frame);
        openOption.addActionListener((ActionListener) frame);
        saveOption.addActionListener((ActionListener) frame);
        searchOption.addActionListener((ActionListener) frame);
        editOption.addActionListener((ActionListener) frame);
        undoOption.addActionListener((ActionListener) frame);
        replaceOption.addActionListener((ActionListener) frame);
    }

}
