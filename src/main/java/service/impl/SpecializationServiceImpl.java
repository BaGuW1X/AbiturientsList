package service.impl;

import dao.impl.SpecializationDaoImpl;
import entity.Abiturient;
import entity.Specialization;
import service.SpecializationService;
import java.util.List;

/**
 * Implementation of SpecializationService interface.
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class SpecializationServiceImpl implements SpecializationService {
    private static SpecializationServiceImpl instance = null;
    private SpecializationDaoImpl specializationDao = new SpecializationDaoImpl();

    public static SpecializationServiceImpl getInstance(){
        if (instance == null)
            instance = new SpecializationServiceImpl();
        return instance;
    }
    private SpecializationServiceImpl(){

    }
    @Override
    public List<Specialization> findAllSpecializations() throws Exception {
        return specializationDao.findAllSpecializations();
    }

    @Override
    public Specialization findSpecializationById(int id) throws Exception {
        return specializationDao.findSpecializationById(id);
    }

    @Override
    public List<Abiturient> findAllAbiturientsBySpecialization(int specializationId) throws Exception {
        return specializationDao.findAllAbiturientsBySpecialization(specializationId);
    }

    @Override
    public Specialization findSpecializationBySpecializationName(String specializationName) throws Exception {
        return specializationDao.findSpecializationBySpecializationName(specializationName);
    }

    @Override
    public void setSpecializationsNumberOfPlaces(int[] numberOfPlaces) throws Exception {
        specializationDao.setSpecializationsNumberOfPlaces(numberOfPlaces);
    }
}
