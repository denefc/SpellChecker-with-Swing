import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class KeyWords {
    ArrayList<String>words;
    //flag'i kontrol amaçlı koydum
    boolean flag=false;
    KeyWords(){
    }
    public ArrayList<String> readWords(){
        //words.txt dosyamızı okuma ve kelimeleri words adlı listeye atma işlemi
        try {
            words=new ArrayList<>();
            BufferedReader reader=new BufferedReader(new FileReader("dictionary.txt"));
            String satır=reader.readLine();
            while (satır!=null){
                words.add(satır);
                satır=reader.readLine();
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
    boolean areSame(String str1,String str2){
        //bu methodla karşılaştıracağım kelimelerin aynı boyda olup olmamalarına,aynı harf içerip içermemelerine bakıyorum

        //stringlerin boylarını alıyorum
        int n1=str1.length();
        int n2=str2.length();


        /*boylarının eşitlik kontrolü ve singletransposition'a bakacağımız ve en az 3 harflik kelimemiz olduğu için
         ya baştaki ya sondaki harfler birbirinin aynısı mı diye de kontrol ediyorum mesela default ve faulted kelimeleri aynı
          sayıda aynı harfleri içeren aynı uzunlukta iki kelime bu kontrol ile onları da eliyoruz*/
        if ((n1==n2)&&((str1.charAt(0)==str2.charAt(0))||(str1.charAt(n1-1)==str2.charAt(n2-1)))) {
            //Sortla a'dan zye bi sıralayıp indexleri karşılaştırıp bakıyoruz harfler ve sayıları aynı mı diye
            char[] charArray1 = str1.toCharArray();
            char[] charArray2 = str2.toCharArray();
            Arrays.sort(charArray1);
            Arrays.sort(charArray2);

            //burda da karşılaştırma işlemini yapıyoruz
            boolean result=Arrays.equals(charArray1,charArray2);

            return result;
        }
        return false;
    }
    public void edit(TextArea textArea){
        //önce words.txt'yi okuyorum
        readWords();
        //esit olmayan indexlerin hangileri olduğunu tutmak için esitolmayanlar adlı listemizi oluşturup ona atıyoruz esit olmayan indexleri
        ArrayList<Integer>notEquals=new ArrayList<>();
        //en sonda yeni textimiz olarak bunu bastıracağız
        String newText="";
        //burda da textimizdeki kelimeleri ayırıp lowercase yapıyoruz ki büyük küçük harflerine takılmayalım
        String[]strings=textArea.getText().toLowerCase().split("[\\s.,;:]+");//regular expressionla bize verilebilcek olan karakterlere göre ayırıyoruz
        //textin içindeki her kelimeyi words.txtdeki kelimelerle karşılaştırıyorum
        for (String string:strings){
            flag=false;
            //flag'ı false yapmamımın nedeni aşşağıdaki iflere girişlerini kontrol edebilmek
            for (String sozluk:words){
                if (areSame(string,sozluk)){
                    //eğer kelimeler aynı harf sayısı ve uzunluğa sahipse hangi harflerinin yerleri farklı onu buluyorum
                    char[]stringHarf=string.toCharArray();
                    char[]sozlukHarf=sozluk.toCharArray();
                    for (int i=0;i<string.length();i++){
                        if (stringHarf[i]!=sozlukHarf[i]){
                            //burda hangi indexteki harfler yanlış onları listeme ekliyorum
                            notEquals.add(i);
                        }
                    }
                    //single transpositionları kontrol etceğimiz için yani 2 harfin yeri farklı olacak
                    if (notEquals.size()==2){
                        //harflerimin yerlerini değiştiriyorum ve textime ekliyorum
                        stringHarf[notEquals.get(0)]=sozlukHarf[notEquals.get(0)];
                        stringHarf[notEquals.get(1)]=sozlukHarf[notEquals.get(1)];
                        newText+=new String(stringHarf)+" ";
                        notEquals.clear();
                        flag=true;
                    }
                }
            }
            if (notEquals.isEmpty()&&!flag){
                newText+=string+" ";
            }
            else if (!notEquals.isEmpty()&&(notEquals.size()!=2)){
                notEquals.clear();
                newText+=string+" ";
            }

        }

        textArea.setText(newText);
    }
}
