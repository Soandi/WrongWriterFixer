import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class hWindow extends JFrame {
    static JTextPane rusTextPane;
    static JTextPane engTextPane;
    static JLabel textSleva;
    static JLabel textSprava;

    hWindow(String name){
        super(name);
        setSize(600,340);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());




        JRadioButton jRaskladka = new JRadioButton("Исправить раскладку",true);
        jRaskladka.setFocusPainted(false);
        jRaskladka.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1){
                    textSleva.setText("Неверная раскладка");
                    textSprava.setText("Исправленный текст");
                }
            }
        });

        JRadioButton jSumma = new JRadioButton("Посчитать сумму",false);
        jSumma.setFocusPainted(false);
        jSumma.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1){
                    textSleva.setText("Введите все числа для сложения");
                    textSprava.setText("Полученная сумма");
                }
            }
        });

        JRadioButton jSummaWords = new JRadioButton("Посчитать слова в тексте",false);
        jSummaWords.setFocusPainted(false);
        jSummaWords.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == 1){
                    textSleva.setText("Введите текст");
                    textSprava.setText("Количество слов");
                }
            }
        });

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jmenuSettings = new JMenu("Настройки");

        ButtonGroup bGroup = new ButtonGroup();
        bGroup.add(jRaskladka);
        bGroup.add(jSumma);
        bGroup.add(jSummaWords);


        jmenuSettings.add(jRaskladka);
        jmenuSettings.add(jSumma);
        jmenuSettings.add(jSummaWords);


        JMenu jmenuAbout = new JMenu("Разработчик");
        jmenuAbout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(hWindow.this,"Разработчик: Чиркунов Александр","Информация о разработчике",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jMenuBar.add(jmenuSettings,BorderLayout.NORTH);
        jMenuBar.add(jmenuAbout,BorderLayout.NORTH);

        textSleva = new JLabel("Неверная раскладка");
        textSprava = new JLabel("Исправленный текст");


        JPanel labelPanel = new JPanel();
        labelPanel.setBounds(10,10,560,30);
        labelPanel.setLayout(new GridLayout(1,2));
        labelPanel.add(textSleva);
        labelPanel.add(textSprava);

        engTextPane = new JTextPane();
        JScrollPane engTextPaneScroll = new JScrollPane(engTextPane);

        rusTextPane = new JTextPane();
        JScrollPane rusTextPaneScroll = new JScrollPane(rusTextPane);

        engTextPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(jRaskladka.isSelected()) {
                    changeEnToRu(engTextPane.getText());
                }
                if(jSumma.isSelected()) {
                    summAll(engTextPane.getText());
                }
                if(jSummaWords.isSelected()) {
                    summWords(engTextPane.getText());
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(jRaskladka.isSelected()) {
                    changeEnToRu(engTextPane.getText());
                }
                if(jSumma.isSelected()) {
                    summAll(engTextPane.getText());
                }
                if(jSummaWords.isSelected()) {
                    summWords(engTextPane.getText());
                }
            }
        });

        //JButton myButton = new JButton("Конвертировать");

        JPanel panel = new JPanel();
        panel.setBounds(10,40,560,240);
        panel.setLayout(new GridLayout(1,2));
        panel.setBorder(BorderFactory.createEtchedBorder());



        panel.add(engTextPaneScroll);
        panel.add(rusTextPaneScroll);

        JPanel forAll = new JPanel();
        forAll.setBounds(10,10,550,300);
        forAll.setLayout(new BorderLayout());
        forAll.add(labelPanel,BorderLayout.NORTH);
        forAll.add(panel,BorderLayout.CENTER);

        add(jMenuBar, BorderLayout.NORTH);
        add(forAll, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    static void summWords(String text){
        String sumWords;
        int znakovWithSpaces = text.length();
        String nospaces = text;
        nospaces = nospaces.replace("\r","");
        nospaces = nospaces.replace("\r\n","");
        nospaces = nospaces.replace("\n","");

        String[] b = nospaces.split(" ");

        nospaces = nospaces.replace("\s","");

        int znakovWithoutSpaces = nospaces.length();

        sumWords = "Знаков с пробелами: " + znakovWithSpaces + "\r\n" + "Знаков без пробелов: " + znakovWithoutSpaces + "\r\n" + "Количество слов: " + b.length;
        rusTextPane.setText(sumWords);
    }

    static void summAll(String text){
        String[] splitSums = text.split("\r\n");
        int vsyaSumma = 0;
        for (String splitSum : splitSums) {
            if(splitSum.equals("") || splitSum.equals(" ") || splitSum == null){}
            else{
                splitSum = splitSum.replace(" ","");
                try {
                    vsyaSumma += Integer.parseInt(splitSum.trim());

                }catch(Exception e){
                    System.out.println("Обнаружена ошибка работы с данными");
                }
            }
        }
        String chislo = String.valueOf(vsyaSumma);
        rusTextPane.setText(chislo);
    }

   static void changeEnToRu(String text){
        String[] qwerty = {"Q","W","E","R","T","Y","U","I","O","P","{","}","A","S","D","F","G","H","J","K","L",":","\"","Z","X","C","V","B","N","M","<",">","q","w","e","r","t","y","u","i","o","p","[","]","a","s","d","f","g","h","j","k","l",";","'","z","x","c","v","b","n","m",",","."};
        String[] qwertyRu = {"Й","Ц","У","К","Е","Н","Г","Ш","Щ","З","Х","Ъ","Ф","Ы","В","А","П","Р","О","Л","Д","Ж","Э","Я","Ч","С","М","И","Т","Ь","Б","Ю","й","ц","у","к","е","н","г","ш","щ","з","х","ъ","ф","ы","в","а","п","р","о","л","д","ж","э","я","ч","с","м","и","т","ь","б","ю"};

        String letterFromText = "";
        String proposal = "";

        for (int i = 0; i < text.length(); i++) {
            boolean smena = false;
            letterFromText = String.valueOf(text.charAt(i));
            int lenPass = 0;
            for (String string : qwerty) {
                if(string.equals(letterFromText)){
                    proposal += qwertyRu[lenPass];
                    smena = true;
                }
                lenPass++;
            }

            if(!smena){
                proposal += letterFromText;
            }

            rusTextPane.setText(proposal);
        }
    }
}



class Main{
    public static void main(String[] args) {
        new hWindow("Программа для исправления раскладки");
    }
}