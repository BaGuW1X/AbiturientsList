package service.impl;

import dao.impl.AbiturientDaoImpl;
import entity.Abiturient;
import service.AbiturientService;
import java.util.List;

/**
 * Implementation of AbiturientService interface.
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class AbiturientServiceImpl implements AbiturientService {

    private static AbiturientServiceImpl instance = null;
    private AbiturientDaoImpl abiturientDao = new AbiturientDaoImpl();


    /**
     * Returns the single instance of AbiturientServiceImpl class.
     * @return the instance of AbiturientServiceImpl class
     */
    public static AbiturientServiceImpl getInstance(){
        if (instance == null)
            instance = new AbiturientServiceImpl();
        return instance;
    }

    /**
     * Private constructor to prevent creating multiple instances of the class.
     */
    private AbiturientServiceImpl(){
    }

    @Override
    public List<Abiturient> findAllAbiturients() throws Exception {
        return abiturientDao.findAllAbiturients();
    }

    @Override
    public Abiturient findAbiturientById(int id) throws Exception {
        return abiturientDao.findAbiturientById(id);
    }

    @Override
    public Abiturient findAbiturientByPassportNumber(String passportNumber) throws Exception {
        return abiturientDao.findAbiturientByPassportNumber(passportNumber);
    }

    @Override
    public List<Abiturient> findAbiturientBySurname(String surname) throws Exception {
        return abiturientDao.findAbiturientBySurname(surname);
    }

    @Override
    public List<Abiturient> findAbiturientByFullName(String surname, String firstName, String middleName) throws Exception {
        return abiturientDao.findAbiturientByFullName(surname, firstName, middleName);
    }

    @Override
    public void addAbiturient(Abiturient abiturient) throws Exception {
        abiturientDao.addAbiturient(abiturient);
    }

    @Override
    public Abiturient updateAbiturient(Abiturient abiturient)throws Exception {
        try {
            return abiturientDao.updateAbiturient(abiturient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean deleteAbiturient(int id) throws Exception {
        try {
            return abiturientDao.deleteAbiturient(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAbiturient(Abiturient abiturient) throws Exception {
        try {
            return abiturientDao.deleteAbiturient(abiturient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Abiturient> findAbiturientsBySpecialization(String specialization) throws Exception {
        return abiturientDao.findAbiturientsBySpecialization(specialization);
    }

    @Override
    public List<Abiturient> sortAbiturientsByTotalScore(String specialization) throws Exception {
        return abiturientDao.sortAbiturientsByTotalScore(specialization);
    }

    @Override
    public void SortAbiturientsBySpecializations(int numberOfPlaces[]) throws Exception {
        abiturientDao.SortAbiturientsBySpecializations(numberOfPlaces);
    }

    @Override
    public List<Abiturient> findAbiturientsByFinalSpecialization(int finalSpecialization) throws Exception {
        return abiturientDao.findAbiturientsByFinalSpecialization(finalSpecialization);
    }

    @Override
    public List<Abiturient> sortAbiturientsByTotalScoreInFinalSpecialization(int finalSpecializationId) throws Exception {
        return abiturientDao.sortAbiturientsByTotalScoreInFinalSpecialization(finalSpecializationId);
    }

    @Override
    public void setSpecializationNull() throws Exception{
        abiturientDao.setSpecializationNull();
    }
}