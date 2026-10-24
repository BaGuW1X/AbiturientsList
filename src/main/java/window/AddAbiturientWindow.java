package window;

import entity.Abiturient;
import service.impl.AbiturientServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Сlass for creating an add abiturients window
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class AddAbiturientWindow extends JFrame {

    private Abiturient abiturient;
    private AbiturientServiceImpl abiturientService = AbiturientServiceImpl.getInstance();
    private JTextField jtfSpecialization;
    private JTextField jtfSurname;
    private JTextField jtfFirstName;
    private JTextField jtfMiddleName;
    private JTextField jtfYearOfBirth;
    private JTextField jtfMathScore;
    private JTextField jtfPhysScore;
    private JTextField jtfRusScore;
    private JTextField jtfGPA;
    private JTextField jtfPassportNumber;
    private String jtfSurnameText, jtfFirstNameText, jtfMiddleNameText, jtfPassportNumberText;
    private int jtfPhysScoreText, jtfRusScoreText, jtfGPAText, jtfMathScoreText, jtfYearOfBirthText;
    private JComboBox<String> jcbExemptions, jcbFirstSpecialization, jcbSecondSpecialization, jcbThirdSpecialization;
    String[] specializations = {"-","Программное обеспечение информационных технологий", "Информационные системы" +
            " и технологии (в проектировании и производстве)", "Информационные системы и технологии (в обработке" +
            " и представлении информации)", "Автоматизация технологических процессов и производств (по направлениям)",
            "Автоматизированные электроприводы", "Промышленные роботы и робототехнические комплексы"};

    /**
     * Default constructor without parameters to create graphic interface of window
     */
    public AddAbiturientWindow() {
        super("Добавление абитуриента");

        /**
         * Creating components of the window
         */
        JPanel pnlAddAbiturient = new JPanel(new GridLayout(13, 2));
        JLabel lblSurname = new JLabel("Фамилия:");
        pnlAddAbiturient.add(lblSurname);
        jtfSurname = new JTextField();
        pnlAddAbiturient.add(jtfSurname);

        JLabel lblFirstName = new JLabel("Имя:");
        pnlAddAbiturient.add(lblFirstName);
        jtfFirstName = new JTextField();
        pnlAddAbiturient.add(jtfFirstName);

        JLabel lblMiddleName = new JLabel("Отчество:");
        pnlAddAbiturient.add(lblMiddleName);
        jtfMiddleName = new JTextField();
        pnlAddAbiturient.add(jtfMiddleName);

        JLabel lblYearOfBirth = new JLabel("Год рождения:");
        pnlAddAbiturient.add(lblYearOfBirth);
        jtfYearOfBirth = new JTextField();
        pnlAddAbiturient.add(jtfYearOfBirth);


        JLabel lblPassportNumber = new JLabel("Номер паспорта:");
        pnlAddAbiturient.add(lblPassportNumber);
        jtfPassportNumber = new JTextField();
        pnlAddAbiturient.add(jtfPassportNumber);

        JLabel lblMathScore = new JLabel("Балл по математике:");
        pnlAddAbiturient.add(lblMathScore );
        jtfMathScore = new JTextField();
        pnlAddAbiturient.add(jtfMathScore);

        JLabel lblPhysScore = new JLabel("Балл по физике:");
        pnlAddAbiturient.add(lblPhysScore);
        jtfPhysScore = new JTextField();
        pnlAddAbiturient.add(jtfPhysScore);

        JLabel lblRusScore = new JLabel("Балл по русскому языку:");
        pnlAddAbiturient.add(lblRusScore);
        jtfRusScore = new JTextField();
        pnlAddAbiturient.add(jtfRusScore);

        JLabel lblGPA = new JLabel("Балл аттестата:");
        pnlAddAbiturient.add(lblGPA);
        jtfGPA = new JTextField();
        pnlAddAbiturient.add(jtfGPA);

        JLabel lblExemptionLevel = new JLabel("Уровень льготы:");
        pnlAddAbiturient.add(lblExemptionLevel);
        String[] exemptions = {"0","1","2","3","4","5"};
        jcbExemptions = new JComboBox<>(exemptions);
        pnlAddAbiturient.add(jcbExemptions);

        JLabel lblFirstSpecialization = new JLabel("Выберите 1-ую специальность:");
        pnlAddAbiturient.add(lblFirstSpecialization);

        jcbFirstSpecialization = new JComboBox<>(specializations);
        pnlAddAbiturient.add(jcbFirstSpecialization);

        JLabel lblSecondSpecialization = new JLabel("Выберите 2-ую специальность:");
        pnlAddAbiturient.add(lblSecondSpecialization);
        jcbSecondSpecialization = new JComboBox<>(specializations);
        pnlAddAbiturient.add(jcbSecondSpecialization);

        JLabel lblThirdSpecialization = new JLabel("Выберите 3-ю специальность:");
        pnlAddAbiturient.add(lblThirdSpecialization);
        jcbThirdSpecialization = new JComboBox<>(specializations);
        pnlAddAbiturient.add(jcbThirdSpecialization);

        // Создание кнопок для сохранения и выхода
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jbtSaveAbiturient = new JButton("Сохранить");
        pnlButtons.add(jbtSaveAbiturient);

        jbtSaveAbiturient.setToolTipText("Нажмите, чтобы добавить абитуриента в список");

        /**
         * Add mouse listener to the save button
         * when activated, saves the abiturient to the database or opens an error window
         */
        jbtSaveAbiturient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    addStudent();
                    JOptionPane.showMessageDialog(null, "Абитуриент успешно добавлен!",
                            "Успешно", JOptionPane.INFORMATION_MESSAGE);
                    new AddAbiturientWindow();
                    dispose();
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Заполните все поля!",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(pnlAddAbiturient, BorderLayout.CENTER);
        add(pnlButtons, BorderLayout.SOUTH);

        /**
         * Add mouse listener to the window close button
         * when activated, it requests confirmation of closing the window
         */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Вы уверены, что хотите выйти? Несохраненные данные будут утеряны.",
                        "Подтверждение", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    //System.exit(0);
                    dispose();
                }
            }
        });

        /**
         * Settings of the frame
         */
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Ьethod for adding an abiturient to the database
     * @throws Exception
     */
    private void addStudent() throws Exception {
        jtfPassportNumberText = jtfPassportNumber.getText();
        jtfSurnameText = jtfSurname.getText();
        jtfFirstNameText = jtfFirstName.getText();
        jtfMiddleNameText = jtfMiddleName.getText();
        jtfYearOfBirthText = Integer.parseInt(jtfYearOfBirth.getText());
        jtfMathScoreText = Integer.parseInt(jtfMathScore.getText());
        jtfPhysScoreText = Integer.parseInt(jtfPhysScore.getText());
        jtfRusScoreText = Integer.parseInt(jtfRusScore.getText());
        jtfGPAText = Integer.parseInt(jtfGPA.getText());
        String[] specializations = new String[]{(String) jcbFirstSpecialization.getSelectedItem(),
                (String)jcbSecondSpecialization.getSelectedItem(),(String)jcbThirdSpecialization.getSelectedItem()};

            abiturient = new Abiturient(specializations, jtfPassportNumberText,
                    jtfSurnameText, jtfFirstNameText, jtfMiddleNameText, jtfYearOfBirthText, jtfMathScoreText,
                    jtfPhysScoreText, jtfRusScoreText, jtfGPAText, Integer.parseInt(jcbExemptions.getSelectedItem().toString()));
            abiturientService.addAbiturient(abiturient);
    }
}

