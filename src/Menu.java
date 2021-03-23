import javax.swing.*;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar {
    JMenu fileMenu,editMenu;
    JMenuItem newOption,openOption,saveOption,searchOption,editOption,undoOption,replaceOption;

    Menu(JFrame frame){
        fileMenu =new JMenu("File");
        editMenu=new JMenu("Edit");
        this.add(fileMenu);
        this.add(editMenu);
        frame.setJMenuBar(this);
    }
    public void createMenu(){
        //Dosya ve düzenle menülerimizin içlerindeki seçenekleri oluşturma
        newOption=new JMenuItem("New");
        openOption = new JMenuItem("Open");
        saveOption = new JMenuItem("Save");
        searchOption = new JMenuItem("Find");
        editOption=new JMenuItem("Control&Edit");
        undoOption=new JMenuItem("Undo");
        replaceOption=new JMenuItem("Change ");
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
