package window;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class SplashWindow extends JFrame {
    Image image;
    {
        try {
            image =  ImageIO.read(getClass().getResource("/images/test.jpg"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private final ImageIcon studentsImage = new ImageIcon(image);

    /**
     * Constructor creates a new splash window and set its content.
     */
    public SplashWindow() {
        String university = "Белорусский Национальный Технический Университет";
        String faculty = "Факультет информационных технологий и робототехники";
        String department = "Кафедра программного обеспечения информационных систем и технологий";
        String courseWork = "Курсовая работа";
        String discipline = "по дисциплине \"Программирование на языке Java\"";
        String topic = "Список абитуриентов";
        String student = "Выполнил: студент группы 10702420";
        String studentName = "Тищенко Святослав Николаевич";
        String lecturer = "Преподаватель: к.ф.-м.н., доц.";
        String lecturerName = "Сидорик Валерий Владимирович";
        String year = "Минск, 2023";

        /*
         * Creating fonts to the contents of the window
         */
        Font smallFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        Font regularFont = new Font(Font.SERIF, Font.PLAIN, 14);
        Font bigFont = new Font(Font.SANS_SERIF, Font.BOLD,16);

        JPanel pnlMain = new JPanel();
        JPanel pnlImageAndInfo = new JPanel(new FlowLayout());
        JPanel pnlHeader = new JPanel();
        JPanel pnlBottom = new JPanel();
        JPanel pnlInfo = new JPanel();
        JPanel pnlButtons = new JPanel(new FlowLayout());
        JLabel lblUniversity = new JLabel(university);
        JLabel lblFaculty = new JLabel(faculty);
        JLabel lblDepartment = new JLabel(department);
        JLabel lblCourseWork = new JLabel(courseWork);
        JLabel lblDiscipline = new JLabel(discipline);
        JLabel lblTopic = new JLabel(topic);
        JLabel lblStudentInfo = new JLabel(student);
        JLabel lblStudentFullName = new JLabel(studentName);
        JLabel lblProfessorInfo = new JLabel(lecturer);
        JLabel lblProfessorFullName = new JLabel(lecturerName);
        JLabel lblStudentsImage = new JLabel(studentsImage);
        JLabel lblCityAndYear = new JLabel(year);
        JButton jbtNext = new JButton("Далее");
        JButton jbtClose = new JButton("Выход");


        jbtClose.setToolTipText("Выход из прораммы");
        jbtNext.setToolTipText("Нажмите для перехода в основное окно программы");
        jbtNext.setPreferredSize(new Dimension(130,35));
        jbtClose.setPreferredSize(new Dimension(130,35));

        lblCityAndYear.setAlignmentX(CENTER_ALIGNMENT);
        lblUniversity.setAlignmentX(CENTER_ALIGNMENT);
        lblFaculty.setAlignmentX(CENTER_ALIGNMENT);
        lblDepartment.setAlignmentX(CENTER_ALIGNMENT);
        lblCourseWork.setAlignmentX(CENTER_ALIGNMENT);
        lblDiscipline.setAlignmentX(CENTER_ALIGNMENT);
        lblTopic.setAlignmentX(CENTER_ALIGNMENT);


        lblUniversity.setFont(regularFont);
        lblFaculty.setFont(regularFont);
        lblDepartment.setFont(regularFont);
        lblDiscipline.setFont(regularFont);
        lblCourseWork.setFont(bigFont);
        lblTopic.setFont(bigFont);
        lblStudentInfo.setFont(regularFont);
        lblStudentFullName.setFont(regularFont);
        lblProfessorInfo.setFont(regularFont);
        lblProfessorFullName.setFont(regularFont);
        lblCityAndYear.setFont(smallFont);

        pnlHeader.setLayout(new BoxLayout(pnlHeader, BoxLayout.Y_AXIS));
        pnlHeader.add(lblUniversity);
        pnlHeader.add(Box.createVerticalStrut(20));
        pnlHeader.add(lblFaculty);
        pnlHeader.add(lblDepartment);
        pnlHeader.add(Box.createVerticalStrut(50));
        pnlHeader.add(lblCourseWork);
        pnlHeader.add(Box.createVerticalStrut(20));
        pnlHeader.add(lblDiscipline);
        pnlHeader.add(Box.createVerticalStrut(20));
        pnlHeader.add(lblTopic);
        pnlHeader.add(Box.createVerticalStrut(20));

        pnlInfo.setLayout(new BoxLayout(pnlInfo, BoxLayout.Y_AXIS));
        pnlInfo.add(lblStudentInfo);
        pnlInfo.add(Box.createVerticalStrut(5));
        pnlInfo.add(lblStudentFullName);
        pnlInfo.add(Box.createVerticalStrut(20));
        pnlInfo.add(lblProfessorInfo);
        pnlInfo.add(Box.createVerticalStrut(5));
        pnlInfo.add(lblProfessorFullName);

        pnlBottom.add(lblCityAndYear);
        pnlButtons.add(jbtNext);
        pnlButtons.add(Box.createHorizontalStrut(250));
        pnlButtons.add(jbtClose);

        pnlImageAndInfo.add(lblStudentsImage);
        pnlImageAndInfo.add(Box.createHorizontalStrut(120));
        pnlImageAndInfo.add(pnlInfo);

        /*
         * Adding panels to the main panel
         */
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.add(pnlHeader);
        pnlMain.add(pnlImageAndInfo);
        pnlMain.add(pnlBottom);
        pnlMain.add(pnlButtons);

        /*
         * Adding timer to close the program
         */
        Timer timer = new Timer(60000, e -> {
            if(isDisplayable()) {
                System.exit(0);
            }
        });
        timer.restart();

        /*
         * Adding action listener to the next button
         * if action performed window.MainWindow will open
         */
        jbtNext.addActionListener(e -> {
            dispose();
            new MainWindow();
            timer.stop();

        });

        /*
         * Adding action listener to the close button
         * if action performed program will exit
         */
        jbtClose.addActionListener(e -> System.exit(0));

        /*
         * Settings of the frame
         */
        setContentPane(pnlMain);
        setSize(900, 750);
        setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setIconImage(image);
        setTitle("ApplicantsListApp");
    }

}
