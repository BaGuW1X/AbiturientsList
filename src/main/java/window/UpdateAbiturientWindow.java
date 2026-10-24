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
 * Сlass for creating an edit window
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class UpdateAbiturientWindow extends JFrame {
    private Abiturient abiturient;
    private AbiturientServiceImpl abiturientService = AbiturientServiceImpl.getInstance();
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
     * Сonstructor for editing data about the applicant and saving changes in the database
     * @param abiturientId id of the abiturient whose data is being edited
     * @throws Exception when a request execution error occurs
     */
    public UpdateAbiturientWindow(int abiturientId) throws Exception {
        super("Редактирование информации об абитуриенте");

        /**
         * Creating components of the window
         */
        JPanel pnlAddAbiturient = new JPanel(new GridLayout(14, 2));
        Abiturient abiturient = abiturientService.findAbiturientById(abiturientId);

        JLabel lblSurname = new JLabel("Фамилия:");
        pnlAddAbiturient.add(lblSurname);
        jtfSurname = new JTextField(abiturient.getSurname());
        pnlAddAbiturient.add(jtfSurname);

        JLabel lblFirstName = new JLabel("Имя:");
        pnlAddAbiturient.add(lblFirstName);
        jtfFirstName = new JTextField(abiturient.getFirstName());
        pnlAddAbiturient.add(jtfFirstName);

        JLabel lblMiddleName = new JLabel("Отчество:");
        pnlAddAbiturient.add(lblMiddleName);
        jtfMiddleName = new JTextField(abiturient.getMiddleName());
        pnlAddAbiturient.add(jtfMiddleName);

        JLabel lblYearOfBirth = new JLabel("Год рождения:");
        pnlAddAbiturient.add(lblYearOfBirth);
        jtfYearOfBirth = new JTextField(String.valueOf(abiturient.getYearOfBirth()));
        pnlAddAbiturient.add(jtfYearOfBirth);


        JLabel lblPassportNumber = new JLabel("Номер паспорта:");
        pnlAddAbiturient.add(lblPassportNumber);
        jtfPassportNumber = new JTextField(abiturient.getPassportNumber());
        pnlAddAbiturient.add(jtfPassportNumber);

        JLabel lblMathScore = new JLabel("Балл по математике:");
        pnlAddAbiturient.add(lblMathScore );
        jtfMathScore = new JTextField(String.valueOf(abiturient.getMathScore()));
        pnlAddAbiturient.add(jtfMathScore);

        JLabel lblPhysScore = new JLabel("Балл по физике:");
        pnlAddAbiturient.add(lblPhysScore);
        jtfPhysScore = new JTextField(String.valueOf(abiturient.getPhysScore()));
        pnlAddAbiturient.add(jtfPhysScore);

        JLabel lblRusScore = new JLabel("Балл по русскому языку:");
        pnlAddAbiturient.add(lblRusScore);
        jtfRusScore = new JTextField(String.valueOf(abiturient.getRusScore()));
        pnlAddAbiturient.add(jtfRusScore);

        JLabel lblGPA = new JLabel("Балл аттестата:");
        pnlAddAbiturient.add(lblGPA);
        jtfGPA = new JTextField(String.valueOf(abiturient.getGradePointAverage()));
        pnlAddAbiturient.add(jtfGPA);

        JLabel lblExemptionLevel = new JLabel("Уровень льготы:");
        pnlAddAbiturient.add(lblExemptionLevel);
        String[] exemptions = {"0","1","2","3","4","5"};
        jcbExemptions = new JComboBox<>(exemptions);
        jcbExemptions.setSelectedItem(abiturient.getExemptionLevel());
        pnlAddAbiturient.add(jcbExemptions);

        JLabel lblFirstSpecialization = new JLabel("Выберите 1-ую специальность:");
        pnlAddAbiturient.add(lblFirstSpecialization);
        jcbFirstSpecialization = new JComboBox<>(specializations);
        jcbFirstSpecialization.setSelectedItem(abiturient.getSelectedSpecializations()[0]);
        pnlAddAbiturient.add(jcbFirstSpecialization);

        JLabel lblSecondSpecialization = new JLabel("Выберите 2-ую специальность:");
        pnlAddAbiturient.add(lblSecondSpecialization);
        jcbSecondSpecialization = new JComboBox<>(specializations);
        jcbSecondSpecialization.setSelectedItem(abiturient.getSelectedSpecializations()[1]);
        pnlAddAbiturient.add(jcbSecondSpecialization);

        JLabel lblThirdSpecialization = new JLabel("Выберите 3-ю специальность:");
        pnlAddAbiturient.add(lblThirdSpecialization);
        jcbThirdSpecialization = new JComboBox<>(specializations);
        jcbThirdSpecialization.setSelectedItem(abiturient.getSelectedSpecializations()[2]);
        pnlAddAbiturient.add(jcbThirdSpecialization);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jbtSaveAbiturient = new JButton("Сохранить");
        pnlButtons.add(jbtSaveAbiturient);

        jbtSaveAbiturient.setToolTipText("Нажмите, чтобы обновить данные в списке");
        jbtSaveAbiturient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                     updateAbiturient(abiturientId);
                } catch (Exception ex) {
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
     * Method for saving modified data in the database
     * @param abiturientId id of the abiturient whose data is being edited
     * @throws Exception when a request execution error occurs
     */
    private void updateAbiturient(int abiturientId) throws Exception {

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

        abiturient = new Abiturient(abiturientId, specializations, jtfPassportNumberText,
                jtfSurnameText, jtfFirstNameText, jtfMiddleNameText, jtfYearOfBirthText, jtfMathScoreText,
                jtfPhysScoreText, jtfRusScoreText, jtfGPAText, Integer.parseInt(jcbExemptions.getSelectedItem().toString()));
        abiturientService.updateAbiturient(abiturient);

    }
}





