import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class hWindow extends JFrame {
    static JTextPane rusTextPane;
    static JTextPane engTextPane;


    hWindow(String name){
        super(name);
        setSize(600,340);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JMenuBar jMenuBar = new JMenuBar();
        JMenu jmenuAbout = new JMenu("Разработчик");
        jmenuAbout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(hWindow.this,"Разработчик: Чиркунов Александр","Информация о разработчике",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jMenuBar.add(jmenuAbout,BorderLayout.NORTH);


        JPanel labelPanel = new JPanel();
        labelPanel.setBounds(10,10,560,30);
        labelPanel.setLayout(new GridLayout(1,2));
        labelPanel.add(new JLabel("Неверная раскладка"));
        labelPanel.add(new JLabel("Исправленный текст"));

        engTextPane = new JTextPane();
        JScrollPane engTextPaneScroll = new JScrollPane(engTextPane);

        rusTextPane = new JTextPane();
        JScrollPane rusTextPaneScroll = new JScrollPane(rusTextPane);

        engTextPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changeEnToRu(engTextPane.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeEnToRu(engTextPane.getText());
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