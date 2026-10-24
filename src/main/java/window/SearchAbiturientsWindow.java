package window;

import entity.Abiturient;
import service.impl.AbiturientServiceImpl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Сlass for creating a search window for abiturients
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class SearchAbiturientsWindow {
    private Abiturient abiturient;
    private ArrayList<Abiturient> abiturients;
    private AbiturientServiceImpl abiturientService = AbiturientServiceImpl.getInstance();
    private static final String[] searchCriteria = {"Номеру паспорта", "Полному имени","Фамилии"};
    private static final String[] columnNames = {"Id", "Специальность", "Номер паспорта",
            "Фамилия", "Имя", "Отчество", "Балл по математике", "Балл по физике", "Балл по русскому языку",
            "Балл аттестата", "Уровень льготы"};
    private static JTextField jtfSearch;
    private static JComboBox<String> jcbSearch = new JComboBox<>();
    private static JTable tblSearchResult;
    private DefaultTableModel tableModel;
    static JButton jbtEdit;
    static JButton jbtDelete;

    /**
     * Default constructor without parameters to create graphic interface of window
     */
    public SearchAbiturientsWindow(){
        JFrame frame = new JFrame("Поиск и редактирование");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1366, 768);
        // Создаем панель для поиска
        JPanel pnlSearch = new JPanel();
        JLabel lblSearch = new JLabel("Поиск студента по: ");
        jcbSearch = new JComboBox<>(searchCriteria);
        jtfSearch = new JTextField(20);
        JButton jbtSearch = new JButton("Найти");

        /**
         * Adding an action listener to the search button.
         * if the action is performed, the search will be performed according to the specified parameter
         */
        jbtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    searchAbiturients();
                }catch(NullPointerException exception){
                    JOptionPane.showConfirmDialog(null, "Пользователь не найден","Ошибка ввода!",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                } catch (Exception exception){
                JOptionPane.showConfirmDialog(null, "Некорректный ввод","Ошибка ввода!",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            }

            }
        });

        pnlSearch.add(lblSearch);
        pnlSearch.add(jcbSearch);
        pnlSearch.add(jtfSearch);
        pnlSearch.add(jbtSearch);


        tableModel = new DefaultTableModel(new String[]{"Id", "Специальность", "Номер паспорта",
                "Фамилия", "Имя", "Отчество","Год рождения", "Балл по математике", "Балл по физике",
                "Балл по русскому языку", "Балл аттестата", "Общий балл", "Уровень льготы"}, 0);
        tblSearchResult = new JTable(tableModel);

        Object[] rowData = new Object[]{};
        refreshAbiturientsTable();

        JScrollPane scrollPane = new JScrollPane(tblSearchResult);
        frame.add(scrollPane);
        JPanel pnlTable = new JPanel(new BorderLayout());
        pnlTable.add(tblSearchResult.getTableHeader(), BorderLayout.NORTH);
        pnlTable.add(tblSearchResult, BorderLayout.CENTER);

        JLabel lblInputId = new JLabel("Введите id нужного абитуриента: ");
        JTextField jtfInputId = new JTextField();
        jbtEdit = new JButton("Изменить выбранного абиутриента");

        /**
         * Adding an action listener to the edit button.
         * if the action is completed, the editing window of the selected abiturient will open
         */
        jbtEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new UpdateAbiturientWindow(Integer.parseInt(jtfInputId.getText()));
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Абитуриент с таким id не найден",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Поле для ввода id пустое!",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        jbtDelete = new JButton("Удалить выбранного абиутриента");
        /**
         * Adding an action listener to the delete button.
         * If the action is performed , the selected abiturient will be deleted
         * from the database
         */
        jbtDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    abiturientService.deleteAbiturient(Integer.parseInt(jtfInputId.getText()));
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Абитуриент с таким id не найден",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Поле для ввода id пустое!",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ошибка",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });

        /**
         * Add mouse listener to the window close button
         * when activated, it requests confirmation of closing the window
         */
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Вы уверены, что хотите выйти? Несохраненные данные будут утеряны.",
                        "Подтверждение", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    //System.exit(0);
                    frame.dispose();
                }
            }
        });

        /**
         * Create a window and add panels to it
         */
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(pnlSearch, BorderLayout.NORTH);
        contentPane.add(new JScrollPane(pnlTable), BorderLayout.CENTER);
        JPanel pnlInput = new JPanel(new GridLayout(1,2));
        jtfInputId.setSize(20,20);
        pnlInput.add(lblInputId);
        pnlInput.add(jtfInputId);
        pnlInput.add(jbtEdit);
        pnlInput.add(jbtDelete);
        contentPane.add(pnlInput, BorderLayout.SOUTH);
        frame.setContentPane(contentPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Method for filling the table with search results
     */
    public void refreshAbiturientsTable() {
        try {
            abiturients = new ArrayList<>(abiturientService.findAllAbiturients());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        tableModel.setRowCount(0);
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
     * Method for searching for an entrant by a given parameter from the combobox
     */
    public void searchAbiturients() {

        /**
         *  Search by passport number
         */
        if(jcbSearch.getSelectedItem().equals("Номеру паспорта")){
            try {
                abiturient = abiturientService.findAbiturientByPassportNumber(jtfSearch.getText());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            tableModel.setRowCount(0);
            String resultSpecializations;
            if(abiturient.getFinalSpecialization() != null){
                resultSpecializations = abiturient.getFinalSpecialization().getSpecializationName();
            }else {
                resultSpecializations = "-";
            }
                Object[] rowData = new Object[]{abiturient.getId(),  resultSpecializations,
                        abiturient.getPassportNumber(), abiturient.getSurname(), abiturient.getFirstName(),
                        abiturient.getMiddleName(),abiturient.getYearOfBirth(), abiturient.getMathScore(),
                        abiturient.getPhysScore(), abiturient.getRusScore(), abiturient.getGradePointAverage(),
                        abiturient.getTotalScore(), abiturient.getExemptionLevel()};

                tableModel.addRow(rowData);

        }
        /**
         *  Search by full name
         */
        else if(jcbSearch.getSelectedItem().equals("Полному имени")){
            String splitInputWords[] = jtfSearch.getText().split(" ");


            try {
                abiturients = new ArrayList<>(abiturientService.findAbiturientByFullName(splitInputWords[0],
                        splitInputWords[1], splitInputWords[2]));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            tableModel.setRowCount(0);
            for (Abiturient abiturient : abiturients) {
                String resultSpecializations;
                if(abiturient.getFinalSpecialization() != null){
                    resultSpecializations = abiturient.getFinalSpecialization().getSpecializationName();
                }else {
                    resultSpecializations = "-";
                }
                Object[] rowData = new Object[]{abiturient.getId(),resultSpecializations ,
                        abiturient.getPassportNumber(), abiturient.getSurname(), abiturient.getFirstName(),
                        abiturient.getMiddleName(),abiturient.getYearOfBirth(), abiturient.getMathScore(), abiturient.getPhysScore(),
                        abiturient.getRusScore(), abiturient.getGradePointAverage(), abiturient.getTotalScore(),
                        abiturient.getExemptionLevel()};
                tableModel.addRow(rowData);
            }
        }

        /**
         *  Search by surname
         */
        else if(jcbSearch.getSelectedItem().equals("Фамилии")){
            try {
                abiturients = new ArrayList<>(abiturientService.findAbiturientBySurname(jtfSearch.getText()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            tableModel.setRowCount(0);
            for (Abiturient abiturient : abiturients) {
                String resultSpecializations;
                if(abiturient.getFinalSpecialization() != null){
                    resultSpecializations = abiturient.getFinalSpecialization().getSpecializationName();
                }else {
                    resultSpecializations = "-";
                }
                Object[] rowData = new Object[]{abiturient.getId(),resultSpecializations ,
                        abiturient.getPassportNumber(), abiturient.getSurname(), abiturient.getFirstName(),
                        abiturient.getMiddleName(),abiturient.getYearOfBirth(), abiturient.getMathScore(), abiturient.getPhysScore(),
                        abiturient.getRusScore(), abiturient.getGradePointAverage(), abiturient.getTotalScore(),
                        abiturient.getExemptionLevel()};
                tableModel.addRow(rowData);
            }
        }

    }

}