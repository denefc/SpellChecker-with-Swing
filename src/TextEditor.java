import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class TextEditor extends JFrame implements ActionListener {
    String dosyaAdi=null;
    Menu menu;
    TextArea textArea;
    KeyWords readWords;
    //frame'imizin close butonunun davranışını,ekranın ortasında açılmasını, size'ını ayarlıyoruz
    TextEditor(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setTitle(dosyaAdi);

        textArea=new TextArea(this);
        //
        menu=new Menu(this);
        menu.createMenu();
        menu.addMenuItem();
        menu.addEventListener(this);

        this.setVisible(true);
        readWords=new KeyWords();
    }
    public void newFile(){
        //textareamızın boş olup olmamasına göre kaydetme işlemi yapıyoruz
        if (!textArea.getText().equals("")){
            int cevap=JOptionPane.showConfirmDialog(rootPane,"Would you want to save?");
            if (cevap==0){
                save();
            }else if (cevap==1){
                dosyaAdi="new.txt";
                setTitle(dosyaAdi);
                textArea.setText(null);
            }
        }else{
            dosyaAdi="new.txt";
            setTitle(dosyaAdi);
            textArea.setText(null);
        }
    }
    public void open(){
        JFileChooser fileChooser=new JFileChooser(".");
        //kolaylık olması açısından txleri filtreliyoruz
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text","txt"));
        int response=fileChooser.showOpenDialog(null);
        if (response==JFileChooser.APPROVE_OPTION){
            File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
            dosyaAdi=file.getName();
            setTitle(dosyaAdi);
            //dosyayımızı okuyup textimizi yazdırma işlemi
            Scanner inFile;
            try{
                inFile=new Scanner(file);
                if (file.isFile()){
                    setTitle(fileChooser.getSelectedFile().getName());
                    while (inFile.hasNextLine()){
                        String satir=inFile.nextLine()+"\n";
                        textArea.append(satir);
                    }
                }
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void save(){
        JFileChooser fileChooser= new JFileChooser(".");
        int response=fileChooser.showSaveDialog(null);
        if(response==JFileChooser.APPROVE_OPTION){
            File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
            dosyaAdi=file.getName();
            setTitle(dosyaAdi);
            try{
                //burda da dosyamızı yazma işlemini yapıyoruz kaydedebilmek için
                BufferedWriter writer=new BufferedWriter(new FileWriter(file));
                writer.write(textArea.getText());
                writer.close();
            }catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==menu.newOption){
            newFile();
        }
        if (e.getSource()==menu.openOption){
            open();
        }
        if (e.getSource()==menu.saveOption){
            save();
        }
        if (e.getSource()==menu.searchOption){
            textArea.search();
        }
        if (e.getSource()==menu.editOption){
            readWords.edit(textArea);
        }
        if(e.getSource()==menu.undoOption){
            textArea.undo();
        }
        if (e.getSource()==menu.replaceOption){
            textArea.replace();
        }

    }

}
