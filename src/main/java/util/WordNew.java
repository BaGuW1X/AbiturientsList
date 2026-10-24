package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import entity.Abiturient;
import entity.Specialization;
import org.apache.poi.xwpf.usermodel.*;
import service.impl.AbiturientServiceImpl;

/**
 * Сlass for creating a Word document
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class WordNew {
    private Specialization specialization;
    private List<Abiturient> abiturients;
    private AbiturientServiceImpl abiturientService = AbiturientServiceImpl.getInstance();
    XWPFTable table;
    public WordNew() {
    }
    public void generateWordDoc(Specialization specialization, String savePath) {
        try {
            // Создание заголовочной строки таблицы и добавление заголовков колонок
            XWPFDocument document = new XWPFDocument();
            createOneBoldSting14("БЕЛОРУССКИЙ НАЦИОНАЛЬНЫЙ ТЕХНИЧЕСКИЙ УНИВЕРСИТЕТ", document);
            createOneBoldSting12("СВОДНАЯ ЭКЗАМЕНАЦИОННАЯ ВЕДОМОСТЬ ВСТУПИТЕЛЬНЫХ ИСПЫТАНИЙ" , document);
            createOneStingWithGap("Факультет информационных технологий и робототехники", document );
            String specializationFullName = specialization.getSpecializationKey() +  "«" + specialization.getSpecializationName() + "»";
            createOneStingWith2Parts("Специальность (направление специальности):  ", specializationFullName,
                    document);
            createOneSting("Форма получения образования дневная, срок обучения полный", document);
            createOneSting("на основе общего среднего образования, среднего специального образования",document);

            table = document.createTable();
            table.setWidth("100%");
            table.removeRow(0); // удаление первой строки таблицы (в которой автоматически создаются ячейки)

            /**
             * Creating a table header row and adding column headers
             */
            XWPFTableRow headerRow = table.createRow();
            headerRow.createCell().setText("№");
            headerRow.createCell().setText("Фамилия, имя, отчество");
            headerRow.createCell().setText("Балл по русскому языку");
            headerRow.createCell().setText("Балл по матема- тике");
            headerRow.createCell().setText("Балл по физике");
            headerRow.createCell().setText("Средний балл документа об образовании");
            headerRow.createCell().setText("Сумма набранных баллов");
            headerRow.createCell().setText("Вид конкурса");
            headerRow.createCell().setText("Сведения о льготах");
            headerRow.createCell().setText("Решение приемной комиссии");
            String[][] data;
            abiturients = new ArrayList<>(abiturientService.sortAbiturientsByTotalScoreInFinalSpecialization(specialization.getId()));

            /**
             * Creating an example for column headers
             */
            XWPFTableRow footnoteRow = table.createRow();
            XWPFTableCell cell0 = footnoteRow.getCell(0);
            XWPFTableCell cell1 = footnoteRow.getCell(1);
            cell0.setText("");
            cell1.setText("");

            /**
             *  Column numbering
             */
            for (int i = 2; i < headerRow.getTableCells().size(); i++) {
                footnoteRow.getCell(i).setText(String.valueOf(i - 1));
            }
            int i ;
            for(int y = 0; y<abiturients.size(); y++){
                i = 1;
                XWPFTableRow dataRow = table.createRow();
                dataRow.getCell(0).setText(String.valueOf(i));
                dataRow.getCell(1).setText(abiturients.get(y).getSurname() + " "
                        + abiturients.get(y).getFirstName() + " " + abiturients.get(y).getMiddleName());
                dataRow.getCell(2).setText(String.valueOf(abiturients.get(y).getRusScore()));
                dataRow.getCell(3).setText(String.valueOf(abiturients.get(y).getMathScore()));
                dataRow.getCell(4).setText(String.valueOf(abiturients.get(y).getPhysScore()));
                dataRow.getCell(5).setText(String.valueOf(abiturients.get(y).getGradePointAverage()));
                dataRow.getCell(6).setText(String.valueOf(abiturients.get(y).getTotalScore()));
                dataRow.getCell(7).setText("по конкурсу");
                if(abiturients.get(y).getExemptionLevel() == 0)
                {
                    dataRow.getCell(8).setText("");
                }
                else{
                    dataRow.getCell(8).setText("п.26.1");
                }

                dataRow.getCell(9).setText("зачислить");
                i++;
            }
            createOneSting(" ", document);
            createOneStingWithGap("Ответственный секретарь", document);
            createOneStingWithLowSubString("приемной комиссии                                                      ",
                    "_______", " А.И. Сафонов", document);
            createOneSting("_______", document);
            createOneSting(" ",document);
            createOneStingWithLowSubString("Декан ФИТР                                                                      ",
                    "_______", " А.М. Авсиевич", document);
            createOneStingWithGap("Заместитель ответственного ", document);
            createOneStingWithLowSubString("секретаря по факультету                                               ",
                    "_______", " С.А. Павлюковец", document);

            FileOutputStream fos = new FileOutputStream(new File(savePath + "\\studentslist.docx"));

            /**
             * Saving a document and closing streams
             */
            document.write(fos);
            fos.flush();
            fos.close();
            document.close();

            System.out.println("Документ успешно сохранён по пути " + savePath);
        } catch(IOException e) {
            System.out.println("Ошибка при создании документа: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for creating a string with a bold font and font size 14
     * @param text entered text
     * @param document document to record
     */
    private  void createOneBoldSting14(String text, XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setFontFamily("Times New Roman");
        run.setFontSize(14);
        run.setBold(true);
        run.setText(text);
    }

    /**
     * Method for creating a string with a bold font and font size 12
     * @param text entered text
     * @param document document to record
     */
    private  void createOneBoldSting12(String text, XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setText(text);
    }

    /**
     * Method for creating a string of two components
     * @param text entered text
     * @param data entered text
     * @param document document to record
     */
    private  void createOneStingWith2Parts(String text, String data, XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run = paragraph.createRun();
        XWPFRun run1 = paragraph.createRun();
        run.setFontFamily("Times New Roman");
        run1.setFontFamily("Times New Roman");
        run.setFontSize(12);
        run1.setFontSize(12);
        run.setText(text);
        run1.setText(data);
    }

    /**
     * Method for creating a row with a vertical gap
     * @param text entered text
     * @param document document to record
     */
    private  void createOneStingWithGap(String text, XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.createRun().addBreak();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    /**
     * Method for creating a string with a font size 12
     * @param text entered text
     * @param document document to record
     */
    private  void createOneSting(String text, XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        //run.setBold(true);
        run.setText(text);
    }

    /**
     * Method for creating a line with the bottom line
     * @param text0 entered text
     * @param text entered text
     * @param text1 entered text
     * @param document document to record
     */
    private  void createOneStingWithLowSubString(String text0, String text, String text1, XWPFDocument document) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun run0 = paragraph.createRun();
        XWPFRun run = paragraph.createRun();
        XWPFRun run2 = paragraph.createRun();
        run2.setFontSize(12);
        run0.setFontSize(12);
        run.setFontSize(12);
        run0.setText(text0);
        run.setText(text);
        run2.setText(text1);

    }
}