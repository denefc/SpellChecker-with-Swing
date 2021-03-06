import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class TextArea extends JTextArea {
    JScrollPane scrollPane;
    TextArea(JFrame frame){
        scrollPane=new JScrollPane(this,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane);
    }

    public void undo(){
        //burda document objesininin remove methodunu kullanabilmek için document objesinden faydalandım
        Document document=this.getDocument();
        if (document.getLength()>0){
            try {
                document.remove(document.getLength()-1,1);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
    public void search(){
        String search=JOptionPane.showInputDialog("Please type the word to search");
        //aranacak kelimenin text'imin içindeki indexini buluyorum
        int where=this.getText().indexOf(search);
        if (where==-1){
            JOptionPane.showMessageDialog(null,"Can't find!");
        }else //Jtextarea'nın hazır select methoduyla kelimemi belirginleştirmek için seçiyorum
            this.select(where,where+search.length());
    }
    public void replace(){
        String search=JOptionPane.showInputDialog("Please type the word to change");
        //aranacak kelimenin text'imin içindeki indexini buluyorum
        int where=this.getText().indexOf(search);
        if (where==-1){
            JOptionPane.showMessageDialog(null,"Can't find!");
        }else{
            //Jtextarea'nın hazır select methoduyla kelimemi belirginleştirmek için seçiyorum
            this.select(where,where+search.length());
            String replaceWith=JOptionPane.showInputDialog("Please type the new word");
            this.replaceRange(replaceWith,where,where+search.length());
        }
    }
}
