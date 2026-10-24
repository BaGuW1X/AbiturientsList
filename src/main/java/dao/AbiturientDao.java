package dao;

import entity.Abiturient;
import java.util.List;

/**
 * @author S.N.Tishchenko
 * @version 1.0
 */
public interface AbiturientDao {
    List<Abiturient> findAllAbiturients() throws Exception;
    Abiturient findAbiturientById(int id)throws Exception;
    Abiturient findAbiturientByPassportNumber(String passportNumber)throws Exception;
    List<Abiturient> findAbiturientBySurname(String surname)throws Exception;
    List<Abiturient> findAbiturientByFullName(String surname, String firstName, String middleName)throws Exception;
    void addAbiturient(Abiturient abiturient) throws Exception;
    boolean deleteAbiturient(int id) throws Exception;
    boolean deleteAbiturient(Abiturient abiturient) throws Exception;
    Abiturient updateAbiturient(Abiturient abiturient) throws Exception;
    List<Abiturient> findAbiturientsBySpecialization(String specialization) throws Exception;
    List<Abiturient> findAbiturientsByFinalSpecialization(int finalSpecialization) throws Exception;
    List<Abiturient> sortAbiturientsByTotalScore(String specialization) throws Exception;
    List<Abiturient> sortAbiturientsByTotalScoreInFinalSpecialization(int finalSpecializationId) throws Exception;
    void SortAbiturientsBySpecializations(int numberOfPlaces[]) throws Exception;
    void setSpecializationNull() throws Exception;
}
