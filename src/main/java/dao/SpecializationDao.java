package dao;

import entity.Abiturient;
import entity.Specialization;
import java.util.List;

/**
 * @author S.N.Tishchenko
 * @version 1.0
 */
public interface SpecializationDao {

    List<Specialization> findAllSpecializations() throws Exception;
    Specialization findSpecializationById(int id) throws Exception;
    List<Abiturient> findAllAbiturientsBySpecialization(int specializationId) throws Exception;
    Specialization findSpecializationBySpecializationName(String specializationName) throws Exception;
    void setSpecializationsNumberOfPlaces(int[] numberOfPlaces) throws Exception;

}
