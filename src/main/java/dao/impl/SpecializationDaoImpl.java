package dao.impl;

import connection.DatabaseConnection;
import dao.SpecializationDao;
import entity.Abiturient;
import entity.Specialization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class provides access to db
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class SpecializationDaoImpl implements SpecializationDao {

    /**
     * Method returns a list of all specializations from the database
     * @return list of objects of type Specialization
     * @throws Exception when a request execution error occurs
     */
    @Override
    public List<Specialization> findAllSpecializations() throws Exception {
        Connection connection;
        List<Specialization> specializations = new ArrayList<>();
        String FIND_ALL_SPECIALIZATIONS_QUERY = "SELECT * FROM specializations";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_SPECIALIZATIONS_QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String specializationsName = resultSet.getString("SpecializationName");
                String specializationKey = resultSet.getString("SpecializationKey");
                int numberOfPlaces = resultSet.getInt("numberOfPlaces");
                Specialization specialization = new Specialization(id,specializationsName, specializationKey, numberOfPlaces);
                specializations.add(specialization);
            }
            return specializations;
        } catch (SQLException e) {
            throw new Exception("Error executing query " + FIND_ALL_SPECIALIZATIONS_QUERY, e);
        }
    }

    /**
     * Method returns the specialization with the specified id from the database
     * @return object of type Specialization
     * @throws Exception when a request execution error occurs
     */
    @Override
    public Specialization findSpecializationById(int id) throws Exception {
        Connection connection;
        String FIND_SPECIALIZATION_BY_ID_QUERY = "SELECT * FROM specializations WHERE id=?";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_SPECIALIZATION_BY_ID_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String specializationName = resultSet.getString("SpecializationName");
                String specializationKey = resultSet.getString("SpecializationKey");
                int numberOfPlaces = resultSet.getInt("numberOfPlaces");
                Specialization specialization = new Specialization(id, specializationName,
                        specializationKey,numberOfPlaces);
                connection.close();
                return specialization;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error executing query " + FIND_SPECIALIZATION_BY_ID_QUERY, e);
        }
    }

    @Override
    public Specialization findSpecializationBySpecializationName(String specializationName) throws Exception {
            Connection connection;
            String FIND_Specialization_BY_SPECIALIZATION_NAME_QUERY = "SELECT * FROM specializations WHERE specializationName=?";

            try {
                connection = DatabaseConnection.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_Specialization_BY_SPECIALIZATION_NAME_QUERY);
                statement.setString(1, specializationName);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String specializationKey = resultSet.getString("SpecializationKey");
                    int numberOfPlaces = resultSet.getInt("numberOfPlaces");
                    Specialization specialization = new Specialization(id, specializationName, specializationKey,numberOfPlaces);
                   // connection.close();
                    return specialization;
                } else {
                    return null;
                }

            } catch (SQLException e) {
                throw new Exception("Error executing query " + FIND_Specialization_BY_SPECIALIZATION_NAME_QUERY, e);
            }
    }

    @Override
    public List<Abiturient> findAllAbiturientsBySpecialization(int specializationId) throws Exception {
        List<Abiturient> abiturients = new ArrayList<>();
        Connection connection;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            String sql = "SELECT * FROM Абитуриенты WHERE id_специальности = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, specializationId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int specId = resultSet.getInt("id_специальности");
                String[] specializations = new String[]{resultSet.getString("firstSpecialization"),
                        resultSet.getString("secondSpecialization"),
                        resultSet.getString("thirdSpecialization")};
                String passportNumber = resultSet.getString("PassportNumber");
                String surname = resultSet.getString("Surname");
                String firstName = resultSet.getString("FirstName");
                String middleName = resultSet.getString("MiddleName");
                int yearOfBirth = resultSet.getInt("YearOfBirth");
                int mathScore = resultSet.getInt("MathScore");
                int physScore = resultSet.getInt("PhysScore");
                int rusScore = resultSet.getInt("RusScore");
                int gPA = resultSet.getInt("GradePointAverage");
                int exemptionLevel = resultSet.getInt("ExemptionLevel");

                Abiturient abiturient = new Abiturient(id, specializations,
                        passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                        exemptionLevel);
                abiturients.add(abiturient);
            }
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return abiturients;
    }

    @Override
    public void setSpecializationsNumberOfPlaces(int[] numberOfPlaces) throws Exception {
        Connection connection;
        final String UPDATE_SPECIALIZATIONS_PLACES_QUERY = "UPDATE `specializations` SET `numberOfPlaces`=? WHERE id=?";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            for (int i = 1; i < numberOfPlaces.length + 1; i++) {
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SPECIALIZATIONS_PLACES_QUERY);
                preparedStatement.setInt(1, numberOfPlaces[i - 1]);
                preparedStatement.setInt(2, i);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}