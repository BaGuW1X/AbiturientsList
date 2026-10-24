package window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AboutProgramWindow extends JDialog {
    /**
     * Default constructor to create graphic interface of window
     */
    public AboutProgramWindow() {

        /**
         * Creating contents of the window
         */
        String header = "<html><h1>О программе</h1>" +
                "<font face='Serif' size=4>" +
                "На этой странице вы можете узнать: что это за программа </br>" +
                "и как с ней работать.</html>";

        String textInfo = "<html><h2>Что это за программа?<h2>" +
                "<font face='Serif' size=4>"+
                "Эта программа предназначена для составления списков</br>"+
                "абитуриентов по группам. Присутствует возможность редактирования, </br>" +
                "удаления и добавления абитуриентов. Программа подключается к базе данных на вашем" +
                "компьютере со следующими настройками:" +
                " Название БД : List ," +
                " Логин: mysql" +
                " Пароль: mysql  </br>" +
                "Есть возможность вывести готовый список абитуриентов, прошедших конкурс," +
                " для любой существующей  группы .</html>";

        String howToUseHeader = "<html><h2>Использование программы<h2>" +
                "<font face='Serif' size=4></html>";
        String howToUseAdd = "<html><h3>Добавление абитуриентов<h3>" +
                "<font face='Serif' size=4>" +
                "Для добавление контакта или группы контактов нажмите кнопку Добавить. </br>" +
                "Далее в появившемся окне заполните все необходимую информацию</br>" +
                "и нажмите Сохранить.</br></html>";

        String howToUseEdit = "<html><h3>Редактирование<h3>" +
                "<font face='Serif' size=4>" +
                "Для того, чтобы редактировать контакт, нажмите кнопку Редактировать</br>" +
                " В появившемся окне Вы можете найти любого абитуриента в таблице,. </br>" +
                " используя комбобокс, где нужно выбрать параметр, по которому Вы желаете </br>" +
                " найти абитуриента. В поле для ввода слева внизу Вам нужно ввести id абитуриента </br>" +
                " которого Вы хотите редактировать и нажать кнопку Редактировать </br>" +
                " Внесите новые данные и нажмите Сохранить.</br></html>";

        String howToUseDelete = "<html><h3>Удаление<h3>" +
                "<font face='Serif' size=4>" +
                "Для того, чтобы удалить контакт, проделайте те же действия,.</br>" +
                " что и при редактировании, но в конце нажмите кнопку Удалить </br></html>";



        String howToUseSave = "<html><h3>Сохранение<h3>" +
                "<font face='Serif' size=4>" +
                "Для того, чтобы сохранить список в Word файл, нажмите Файл->Сохранить. Выберите специальность,</br>" +
                " список для которой вы хотите составить .Далее выберите директорию </br>" +
                " сохранить файл и нажмите кнопку OK. Файл сохранится с именем studentslist </br></html>";


        /**
         * Creating components of the window
         */
        JLabel lblHeader = new JLabel("<html><div style='text-align: center;'>" + header + "</div></html>");
        JLabel lblInfo = new JLabel("<html><div style='text-align: center;'>" + textInfo + "</div></html>");
        JLabel lblHowToUseHeader = new JLabel(howToUseHeader, SwingConstants.CENTER);
        JLabel lblHowToUseAdd = new JLabel("<html><div style='text-align: center;'>" + howToUseAdd + "</div></html>");
        JLabel lblHowToUseDelete = new JLabel("<html><div style='text-align: center;'>" + howToUseDelete + "</div></html>");
        JLabel lblHowToUseEdit = new JLabel("<html><div style='text-align: center;'>" + howToUseEdit + "</div></html>");
        JLabel lblHowToUseSave = new JLabel("<html><div style='text-align: center;'>" + howToUseSave + "</div></html>");
        JPanel jplMain = new JPanel();

        /**
         * Setting of the main panel
         */
        jplMain.setLayout(new BoxLayout(jplMain, BoxLayout.Y_AXIS));
        jplMain.setPreferredSize(new Dimension(400,1150));

        /**
         * Adding components to the main panel
         */
        jplMain.add(lblHeader);
        jplMain.add(lblInfo);
        jplMain.add(lblHowToUseHeader);
        jplMain.add(lblHowToUseAdd);
        jplMain.add(lblHowToUseDelete);
        jplMain.add(lblHowToUseEdit);
        jplMain.add(lblHowToUseSave);

        /**
         * Adding main panel to the JScrollPane
         */
        JScrollPane jspMain = new JScrollPane(jplMain);
        jspMain.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jspMain.setPreferredSize(new Dimension(400,400));


        /**
         * Setting of the frame
         */
        setContentPane(jspMain);
        setTitle("О программе");
        setSize(500, 500);
        setVisible(true);
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                dispose();
            }
        });


    }
}
