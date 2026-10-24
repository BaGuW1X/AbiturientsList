package window;

import entity.Specialization;
import service.impl.SpecializationServiceImpl;
import util.WordNew;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Сlass for choosing a specialty to save the list to a file
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class OutputSpecializationWindow extends JFrame implements ActionListener {
    private SpecializationServiceImpl specializationService = SpecializationServiceImpl.getInstance();
    private Specialization specialization;
    private JLabel titleLabel;
    private JRadioButton[] specialtyButtons;
    private JLabel pathLabel;
    private JTextField pathField;
    private JButton jbtBrowse;
    private JButton jbtOK;
    private JPanel pnlButton;
    private String specializations[] = {"Программное обеспечение информационных технологий", "Информационные системы" +
            " и технологии (в проектировании и производстве)", "Информационные системы и технологии (в обработке" +
            " и представлении информации)", "Автоматизация технологических процессов и производств (по направлениям)",
            "Автоматизированные электроприводы", "Промышленные роботы и робототехнические комплексы"};
    private String selectedSpecialization = "";

    /**
     * Default constructor without parameters to create graphic interface of window
     */
    public OutputSpecializationWindow() {
        super("Выберите специальность и путь для сохранения");

        // Создаем заглавный лейбл
        titleLabel = new JLabel("Выберите специальность для вывода списка");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        /**
         * We create radiobuttons for choosing a specialization
         */
        JPanel specialtiesPanel = new JPanel(new GridLayout(6, 1,4,2));
        specialtyButtons = new JRadioButton[6];
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < 6; i++) {
            specialtyButtons[i] = new JRadioButton(specializations[i]);
            specialtyButtons[i].addActionListener(this);
            group.add(specialtyButtons[i]);
            specialtiesPanel.add(specialtyButtons[i]);
        }
        add(specialtiesPanel, BorderLayout.CENTER);

        /**
         * Creating a label and a button to select a path
         */
        JPanel pathPanel = new JPanel(new FlowLayout());
        pathLabel = new JLabel("Выберите путь для сохранения:");
        pathPanel.add(pathLabel);
        pathField = new JTextField(40);
        pathPanel.add(pathField);
        jbtBrowse = new JButton("Обзор...");
        jbtBrowse.addActionListener(this);
        pathPanel.add(jbtBrowse);
        add(pathPanel, BorderLayout.SOUTH);

        jbtOK = new JButton("Ок");
        /**
         * Add mouse listener to the OK button
         * when activated, file will be saved in the selected path
         */
        jbtOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < specialtyButtons.length; i++) {
                    if (specialtyButtons[i].isSelected()) {
                        selectedSpecialization = specializations[i];
                        break;
                    }
                }
                try {
                    specialization = specializationService.findSpecializationBySpecializationName(selectedSpecialization);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                new WordNew().generateWordDoc(specialization, pathField.getText());
            }
        });

        add(jbtOK, BorderLayout.EAST);
        pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlButton.add(jbtOK);
        pathPanel.add(jbtOK,BorderLayout.EAST);

        // добавляем listener на закрытие окна
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Вы уверены, что хотите выйти? Несохраненные данные будут утеряны.",
                        "Подтверждение", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        /**
         * Settings of the frame
         */
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Add mouse listener to the path selection button
     * when activated, asks you to choose a path to save the file
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtBrowse) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                pathField.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        } else if (e.getSource() == jbtOK) {
        }
    }
}
