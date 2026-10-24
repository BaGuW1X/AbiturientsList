package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents specialization entity
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class Specialization {
    private int id;
    private String specializationName;
    private List<Abiturient> abiturients = new ArrayList<>();
    int numberOfPlaces;
    String educationForm;
    String specializationKey;

    public Specialization(int id, String specializationName,int numberOfPlaces, List<Abiturient> abiturients) {
        this.id = id;
        this.specializationName = specializationName;
        this.numberOfPlaces = numberOfPlaces;
        this.abiturients = new ArrayList<>();

    }
    public Specialization(int id, String specializationName) {
        this.id = id;
        this.specializationName = specializationName;
    }
    public Specialization(int id, String specializationName,String specializationKey, int numberOfPlaces) {
        this.id = id;
        this.specializationName = specializationName;
        this.specializationKey = specializationKey;
        this.numberOfPlaces = numberOfPlaces;
    }

    public void setAbiturients(List<Abiturient> abiturients) {
        this.abiturients = abiturients;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public String getEducationForm() {
        return educationForm;
    }

    public void setEducationForm(String educationForm) {
        this.educationForm = educationForm;
    }

    public String getSpecializationKey() {
        return specializationKey;
    }

    public void setSpecializationKey(String specializationKey) {
        this.specializationKey = specializationKey;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public List<Abiturient> getAbiturients() {
        return abiturients;
    }


}
