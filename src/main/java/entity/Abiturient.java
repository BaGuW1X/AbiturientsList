package entity;

/**
 * Class represents abiturient entity
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class Abiturient {
    private int id;
    private Specialization finalSpecialization;
    private String passportNumber;
    private String surname;
    private String firstName;
    private String middleName;
    private int yearOfBirth;
    private int mathScore;
    private int physScore;
    private int rusScore;
    private int gradePointAverage;
    private int exemptionLevel;
    private int totalScore;
    private String[] selectedSpecializations;

    public Abiturient(String[] selectedSpecializations, String passportNumber, String surname, String firstName,
                      String middleName, int yearOfBirth, int mathScore, int physScore, int rusScore,
                      int gradePointAverage, int exemptionLevel) {
        this.selectedSpecializations = selectedSpecializations;
        this.passportNumber = passportNumber;
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.yearOfBirth = yearOfBirth;
        this.mathScore = mathScore;
        this.physScore = physScore;
        this.rusScore = rusScore;
        this.gradePointAverage = gradePointAverage;
        this.exemptionLevel = exemptionLevel;
        totalScore = mathScore + physScore + rusScore + gradePointAverage;
    }

    public Abiturient(int id, String[] selectedSpecializations,Specialization finalSpecialization, String passportNumber,
                      String surname, String firstName, String middleName, int yearOfBirth, int mathScore, int physScore,
                      int rusScore, int gradePointAverage, int exemptionLevel) {
        this.id = id;
        this.selectedSpecializations = selectedSpecializations;
        this.finalSpecialization = finalSpecialization;
        this.passportNumber = passportNumber;
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.yearOfBirth = yearOfBirth;
        this.mathScore = mathScore;
        this.physScore = physScore;
        this.rusScore = rusScore;
        this.gradePointAverage = gradePointAverage;
        this.exemptionLevel = exemptionLevel;
        totalScore = mathScore + physScore + rusScore + gradePointAverage;
    }

    public Abiturient(int id, String[] selectedSpecializations, String passportNumber, String surname, String firstName,
                      String middleName, int yearOfBirth, int mathScore, int physScore, int rusScore,
                      int gradePointAverage, int exemptionLevel) {
        this.id = id;
        this.selectedSpecializations = selectedSpecializations;
        this.passportNumber = passportNumber;
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
        this.yearOfBirth = yearOfBirth;
        this.mathScore = mathScore;
        this.physScore = physScore;
        this.rusScore = rusScore;
        this.gradePointAverage = gradePointAverage;
        this.exemptionLevel = exemptionLevel;
        totalScore = mathScore + physScore + rusScore + gradePointAverage;
    }


    public Specialization getFinalSpecialization() {
        return finalSpecialization;
    }

    public void setFinalSpecialization(Specialization finalSpecialization) {
        this.finalSpecialization = finalSpecialization;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String[] getSelectedSpecializations() {
        return selectedSpecializations;
    }

    public void setSelectedSpecializations(String[] selectedSpecializations) {
        this.selectedSpecializations = selectedSpecializations;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

    public int getPhysScore() {
        return physScore;
    }

    public void setPhysScore(int physScore) {
        this.physScore = physScore;
    }

    public int getRusScore() {
        return rusScore;
    }

    public void setRusScore(int rusScore) {
        this.rusScore = rusScore;
    }

    public int getGradePointAverage() {
        return gradePointAverage;
    }

    public void setGradePointAverage(int gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }

    public int getExemptionLevel() {
        return exemptionLevel;
    }

    public void setExemptionLevel(int exemptionLevel) {
        this.exemptionLevel = exemptionLevel;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }


}

