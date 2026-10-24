package window;

import connection.DatabaseConnection;
import entity.Abiturient;
import entity.Specialization;
import service.impl.AbiturientServiceImpl;
import service.impl.SpecializationServiceImpl;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Main window of the program
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class MainWindow extends JFrame {
    private ArrayList<Abiturient> abiturients;
    private ArrayList<Specialization> specializations;
    private AbiturientServiceImpl abiturientService = AbiturientServiceImpl.getInstance();
    private SpecializationServiceImpl specializationService = SpecializationServiceImpl.getInstance();
    private DefaultTableModel tableModel;
    private JTable tblAbiturients;
    private JButton jbtAddAbiturient, jbtEditAbiturient;
    private JButton jbtRefreshTable, jbtSortAbiturients, jbtSaveToFile, jbtOutputToTable ;
//    private JPanel jplAbiturientsTable =new JPanel();
    private JTextField jtfPoit, jtfSapr, jtfIsit, jtfAvtomat, jtfPriv, jtfRob;
    private Connection connection;
    private Abiturient abiturient;
    List<Abiturient> abiturientsList;

    /**
     * Сreating tables in a database for data entry
     */
    {
        try {
            DatabaseConnection.createTableIfNotExists();
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Statement statement;
    private PreparedStatement preparedStatement;

    /**
     * Default constructor without parameters to create graphic interface of window
     */
    public MainWindow() {
        /**
         * Creating components of the window
         */
        JPanel pnlInput = new JPanel(new GridLayout(7, 2));
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Количество мест");
        pnlInput.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                titledBorder
        ));

        //Сreate lists of all specialties and applicants
        try {
             specializations = new ArrayList<>(specializationService.findAllSpecializations()) ;
             abiturients = new ArrayList<>(abiturientService.findAllAbiturients());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JMenuBar jmbMenu = new JMenuBar();
        JMenu jmenuFile = new JMenu("Файл");
        JMenu jmenuAuthor = new JMenu("Об авторе");
        JMenu jmenuProgramm = new JMenu("О программе");
        JMenuItem jmiSave = new JMenuItem("Cохранить");
        JMenuItem jmiClose = new JMenuItem("Выход");


        JLabel lblPoit = new JLabel("Программное обеспечение информационных технологий");
        pnlInput.add(lblPoit);
        for(Specialization specialization : specializations) {
            if (lblPoit.getText().equals( specialization.getSpecializationName())) {
                jtfPoit = new JTextField(String.valueOf(specialization.getNumberOfPlaces()));
                pnlInput.add(jtfPoit);
            }
        }

        JLabel lblSapr = new JLabel("Информационные системы и технологии (в проектировании и производстве)");
        pnlInput.add(lblSapr);
        for(Specialization specialization : specializations) {
            if (lblSapr.getText().equals( specialization.getSpecializationName())) {
                jtfSapr = new JTextField(String.valueOf(specialization.getNumberOfPlaces()));
                pnlInput.add(jtfSapr);
            }
        }


        JLabel lblIsit = new JLabel("Информационные системы и технологии (в обработке и представлении информации)");
        pnlInput.add(lblIsit);
        for(Specialization specialization : specializations) {
            if (lblIsit.getText().equals( specialization.getSpecializationName())) {
                jtfIsit= new JTextField(String.valueOf(specialization.getNumberOfPlaces()));
                pnlInput.add(jtfIsit);
            }
        }

        JLabel lblAvtomat = new JLabel("Автоматизация технологических процессов и производств (по направлениям)");
        pnlInput.add(lblAvtomat);
        System.out.println(lblAvtomat.getText());
        for(Specialization specialization : specializations) {
            if (lblAvtomat.getText().equals( specialization.getSpecializationName())) {
                jtfAvtomat= new JTextField(String.valueOf(specialization.getNumberOfPlaces()));
                pnlInput.add(jtfAvtomat);
            }
        }

        JLabel lblPriv = new JLabel("Автоматизированные электроприводы");
        pnlInput.add(lblPriv);
        for(Specialization specialization : specializations) {
            if (lblPriv.getText().equals( specialization.getSpecializationName())) {
                jtfPriv= new JTextField(String.valueOf(specialization.getNumberOfPlaces()));
                pnlInput.add(jtfPriv);
            }
        }

        JLabel lblRob = new JLabel("Промышленные роботы и робототехнические комплексы");
        pnlInput.add(lblRob);
        for(Specialization specialization : specializations) {
            if (lblRob.getText().equals( specialization.getSpecializationName())) {
                jtfRob= new JTextField(String.valueOf(specialization.getNumberOfPlaces()));
                pnlInput.add(jtfRob);
            }
        }

        jbtRefreshTable = new JButton("Обновить");
        jbtSortAbiturients = new JButton("Составить списки");
        jbtOutputToTable = new JButton("Предварительный список");
        jbtSaveToFile = new JButton("Сохранить списки");

        JPanel pnlButton = new JPanel(new GridLayout(1, 4, 10, 10));
        pnlButton.add(jbtRefreshTable);
        pnlButton.add(jbtSortAbiturients);
        pnlButton.add(jbtOutputToTable);
        pnlButton.add(jbtSaveToFile);
        pnlInput.add(pnlButton);

        tableModel = new DefaultTableModel(new String[]{"Id", "Итоговая специальность", "Номер паспорта",
                "Фамилия", "Имя", "Отчество","Год рождения", "Балл по математике", "Балл по физике",
                "Балл по русскому языку", "Балл аттестата", "Общий балл", "Уровень льготы"}, 0);
        tblAbiturients = new JTable(tableModel);
        refreshAbiturientsTable();
        JScrollPane scrTableScrolePane = new JScrollPane(tblAbiturients);

        /**
         * Creating buttons to manage the list of abiturients
         */
        JPanel pnlAction = new JPanel(new GridLayout(1, 3, 10, 10));
        jbtAddAbiturient = new JButton("Добавить");
        jbtEditAbiturient = new JButton("Редактировать");
        pnlAction.add(jbtAddAbiturient);
        pnlAction.add(jbtEditAbiturient);
        setLayout(new BorderLayout());
        add(pnlInput, BorderLayout.NORTH);
        add(scrTableScrolePane, BorderLayout.CENTER);
        add(pnlAction, BorderLayout.SOUTH);

        /**
         * Creating menu and settings of it
         */
        jmenuFile.add(jmiSave);
        jmenuFile.add(jmiClose);
        jmbMenu.add(jmenuFile);
        jmbMenu.add(jmenuProgramm);
        jmbMenu.add(jmenuAuthor);
        jmenuProgramm.setToolTipText("Нажмите, чтобы получить руководоство по программe");
        jmenuAuthor.setToolTipText("Нажмите, чтобы получить информацию об авторе");
        jmiSave.setToolTipText("Нажмите, чтобы сохрание данные в файл");
        jmiClose.setToolTipText("Нажмите, чтобы закрыть программу");

        jmenuFile.setBorder(BorderFactory.createEtchedBorder());
        jmenuAuthor.setBorder(BorderFactory.createEtchedBorder());
        jmenuProgramm.setBorder(BorderFactory.createEtchedBorder());
        jmiSave.setBorder(BorderFactory.createEtchedBorder());
        jmiClose.setBorder(BorderFactory.createEtchedBorder());

        jbtAddAbiturient.setToolTipText("Нажмите, чтобы добавить абитуриента в список");
        /**
         * Add mouse listener to the add button
         * when activated, opens the window for adding an abiturient
         */
        jbtAddAbiturient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddAbiturientWindow();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /**
         * Add mouse listener to the edit button
         * when activated, opens the abiturient search window for subsequent editing
         */
        jbtEditAbiturient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchAbiturientsWindow();
            }
        });

        /**
         * Add mouse listener to the JMenuProgram
         * if mouse clicked  AboutProgramWindow will open
         */
        jmenuProgramm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                new AboutProgramWindow();
            }
        });

        /**
         * Add mouse listener to the JMenuAuthor
         * if mouse clicked AboutAuthorWindow will open
         */
        jmenuAuthor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                new AboutAuthorWindow();
            }
        });


        /**
         * Add listener to the JMenuProgram
         * if mouse clicked  AboutProgramWindow will open
         */
        jmiSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    new OutputSpecializationWindow();

            }
        });

        /**
         * Add mouse listener to the refreshTable button
         * when activated, updates the table on the frame
         */
        jbtRefreshTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // обновляем данные в таблице
                refreshAbiturientsTable();
            }
        });

        /**
         * Add mouse listener to the OutputToTable button
         * when activated, fills in the table on the frame with the winning abiturients
         */
        jbtOutputToTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listOutputToTable();
            }
        });

        /**
         * Add mouse listener to the SortAbiturients button
         * when activated, conducts a competition between abiturients and records the results in the database
         */
        jbtSortAbiturients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numberOfPlaces[] = new int[]{Integer.parseInt(jtfPoit.getText()),Integer.parseInt(jtfSapr.getText()),
                            Integer.parseInt(jtfIsit.getText()), Integer.parseInt(jtfAvtomat.getText()),
                            Integer.parseInt(jtfPriv.getText()), Integer.parseInt(jtfRob.getText())};
                    specializationService.setSpecializationsNumberOfPlaces(numberOfPlaces);
                    abiturientService.SortAbiturientsBySpecializations(numberOfPlaces);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /**
         * Add mouse listener to the SortAbiturients button
         * when activated, opens the OutputSpecializationWindow
         */
        jbtSaveToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OutputSpecializationWindow();
                // сохраняем списки в файл
            }
        });

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
                    dispose();
                }
            }
        });
        /**
         * Settings of the frame
         */
        setJMenuBar(jmbMenu);
        setTitle("Список абитуриентов");
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    /**
     * Method for displaying all applicants in the table
     */
    public void refreshAbiturientsTable() {
        tableModel.setRowCount(0);
            try {
                abiturients = new ArrayList<>(abiturientService.findAllAbiturients());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            for (Abiturient abiturient : abiturients) {
                String resultSpecialization;
                if(abiturient.getFinalSpecialization() == null){
                    resultSpecialization = "-";
                }else{
                    resultSpecialization = abiturient.getFinalSpecialization().getSpecializationName();
                }
                Object[] rowData = new Object[]{abiturient.getId(), resultSpecialization,
                        abiturient.getPassportNumber(), abiturient.getSurname(), abiturient.getFirstName(),
                        abiturient.getMiddleName(),abiturient.getYearOfBirth(), abiturient.getMathScore(),
                        abiturient.getPhysScore(), abiturient.getRusScore(), abiturient.getGradePointAverage(),
                        abiturient.getTotalScore(), abiturient.getExemptionLevel()
                };
                tableModel.addRow(rowData);
            }
    }

    /**
     * Method for displaying all applicants in the table, sorted by specialyzations and scores
     */
    public void listOutputToTable() {
        tableModel.setRowCount(0);
              for(int j = 0; j<specializations.size(); j++) {
                  try {
                      abiturientsList = new ArrayList<>(abiturientService.sortAbiturientsByTotalScoreInFinalSpecialization(specializations.get(j).getId()));
                  } catch (Exception e) {
                      throw new RuntimeException(e);
                  }
                  for (Abiturient abiturient : abiturientsList) {
                      String resultSpecialization;
                      if(abiturient.getFinalSpecialization() == null){
                          resultSpecialization = "-";
                      }else{
                          resultSpecialization = abiturient.getFinalSpecialization().getSpecializationName();
                      }
                      Object[] rowData = new Object[]{abiturient.getId(), resultSpecialization,
                              abiturient.getPassportNumber(), abiturient.getSurname(), abiturient.getFirstName(),
                              abiturient.getMiddleName(),abiturient.getYearOfBirth(), abiturient.getMathScore(),
                              abiturient.getPhysScore(), abiturient.getRusScore(), abiturient.getGradePointAverage(),
                              abiturient.getTotalScore(), abiturient.getExemptionLevel()
                      };
                      tableModel.addRow(rowData);
              }
        }
    }
}