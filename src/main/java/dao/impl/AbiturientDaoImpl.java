package dao.impl;
import dao.AbiturientDao;
import connection.DatabaseConnection;
import entity.Abiturient;
import entity.Specialization;
import java.sql.*;
import java.util.*;

/**
 * Class provides access to db
 * @author S.N.Tishchenko
 * @version 1.0
 */
public class AbiturientDaoImpl implements AbiturientDao {
    SpecializationDaoImpl specializationDao = new SpecializationDaoImpl();

    /**
     * Method returns a list of all abiturients from the database
     * @return list of objects of type Abiturient
     * @throws Exception when a request execution error occurs
     */
    @Override
    public List<Abiturient> findAllAbiturients() throws Exception {
        Connection connection;
        List<Abiturient> abiturients = new ArrayList<>();
        String FIND_ALL_ABITURIENTS_QUERY = "SELECT * FROM abiturients";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_ABITURIENTS_QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String[] specializations = new String[]{resultSet.getString("firstSpecialization"),
                        resultSet.getString("secondSpecialization"),
                        resultSet.getString("thirdSpecialization")};
                /*String firstSpecialization[0] = resultSet.getString("firstSpecialization");
                String secondSpecialization = resultSet.getString("secondSpecialization");
                String thirdSpecialization = resultSet.getString("thirdSpecialization");*/
                int y=0;
                Specialization finalSpecialization;
                int specializationId = resultSet.getInt("finalSpecializationId");
                if(specializationId != 0) {
                    List<Specialization> specializationsList = new ArrayList<>(specializationDao.findAllSpecializations());
                    for (y = 0; y < specializationsList.size(); y++) {
                        if (specializationsList.get(y).getId() == (specializationId)) {
                            int index = y;
                            break;
                        }
                    }
                    finalSpecialization = specializationsList.get(y);
                }
                else{
                    finalSpecialization = null;
                }
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

                if(finalSpecialization == null){
                    Abiturient abiturient = new Abiturient(id, specializations,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                    abiturients.add(abiturient);
                }
                else {
                    Abiturient abiturient = new Abiturient(id, specializations, finalSpecialization,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                    abiturients.add(abiturient);
                }
            }
            return abiturients;
        } catch (SQLException e) {
            throw new Exception("Error executing query " + FIND_ALL_ABITURIENTS_QUERY, e);
        }
    }

    /**
     * Method returns the abiturient with the specified id from the database
     * @return object of type Abiturient
     * @throws Exception when a request execution error occurs
     */
    @Override
    public Abiturient findAbiturientById(int id) throws Exception {
        Connection connection;
        String FIND_ABITURIENT_BY_ID_QUERY = "SELECT * FROM abiturients WHERE id=?";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ABITURIENT_BY_ID_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                //int id = resultSet.getInt("id");
                String[] specializations = new String[]{resultSet.getString("firstSpecialization"),
                        resultSet.getString("secondSpecialization"),
                        resultSet.getString("thirdSpecialization")};
                int y=0;
                Specialization finalSpecialization;
                int specializationId = resultSet.getInt("finalSpecializationId");
                if(specializationId != 0) {
                    List<Specialization> specializationsList = new ArrayList<>(specializationDao.findAllSpecializations());
                    for (y = 0; y < specializationsList.size(); y++) {
                        if (specializationsList.get(y).getId() == (specializationId)) {
                            int index = y;
                            break;
                        }
                    }
                    finalSpecialization = specializationsList.get(y);
                }
                else{
                    finalSpecialization = null;
                }
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

                Abiturient abiturient;
                if(finalSpecialization == null){
                    abiturient = new Abiturient(id, specializations,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                }
                else {
                    abiturient = new Abiturient(id, specializations, finalSpecialization,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                }
                return abiturient;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error executing query " + FIND_ABITURIENT_BY_ID_QUERY, e);
        }
    }
    @Override
    public void addAbiturient(Abiturient abiturient) throws Exception {
        Connection connection;
        System.out.println(abiturient.getSelectedSpecializations()[0]);
        final String ADD_ABITURIENT_QUERY = "INSERT INTO `abiturients` (`FirstSpecialization`," +
                "`SecondSpecialization`, `ThirdSpecialization`," +
                "`PassportNumber`, `Surname`, `FirstName`, `MiddleName`, `YearOfBirth`,`MathScore`, `PhysScore`," +
                " `RusScore`, `GradePointAverage`, `TotalScore`, `ExemptionLevel`)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ABITURIENT_QUERY);
            preparedStatement.setString(1, abiturient.getSelectedSpecializations()[0]);
            preparedStatement.setString(2, abiturient.getSelectedSpecializations()[1]);
            preparedStatement.setString(3, abiturient.getSelectedSpecializations()[2]);
            preparedStatement.setString(4, abiturient.getPassportNumber());
            preparedStatement.setString(5, abiturient.getSurname());
            preparedStatement.setString(6, abiturient.getFirstName());
            preparedStatement.setString(7, abiturient.getMiddleName());
            preparedStatement.setInt(8, abiturient.getYearOfBirth());
            preparedStatement.setInt(9, abiturient.getMathScore());
            preparedStatement.setInt(10, abiturient.getPhysScore());
            preparedStatement.setInt(11, abiturient.getRusScore());
            preparedStatement.setInt(12, abiturient.getGradePointAverage());
            preparedStatement.setInt(13, abiturient.getTotalScore());
            preparedStatement.setInt(14, abiturient.getExemptionLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error executing query " + ADD_ABITURIENT_QUERY, e);
        }
    }

    @Override
    public boolean deleteAbiturient(int id) throws Exception {
        final String DELETE_ABITURIENT_QUERY = "DELETE FROM abiturients WHERE id=?";
        PreparedStatement statement;
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_ABITURIENT_QUERY);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new Exception(e);
        }

        return true;
    }

    @Override
    public boolean deleteAbiturient(Abiturient abiturient) throws Exception {
        PreparedStatement statement;
        final String DELETE_ABITURIENT_QUERY = "DELETE FROM abiturients" +
                " WHERE (id = ?)";
        try {
            Connection connection = DatabaseConnection.getInstance().getConnection();
            statement = connection.prepareStatement(DELETE_ABITURIENT_QUERY);
            statement.setInt(1, abiturient.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new Exception(e);
        }

        return true;
    }

    @Override
    public Abiturient updateAbiturient(Abiturient abiturient) throws Exception {
        Connection connection;
        final String UPDATE_ABITURIENT_QUERY = "UPDATE `abiturients` SET FirstSpecialization=?," +
                "SecondSpecialization=?, ThirdSpecialization=?," +
                "PassportNumber=?, Surname=?, FirstName=?, MiddleName=?,YearOfBirth=?,MathScore=?, PhysScore=?," +
                "RusScore=?, GradePointAverage=?, TotalScore=?, ExemptionLevel=? WHERE id=?";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ABITURIENT_QUERY);
            preparedStatement.setString(1, abiturient.getSelectedSpecializations()[0]);
            preparedStatement.setString(2, abiturient.getSelectedSpecializations()[1]);
            preparedStatement.setString(3, abiturient.getSelectedSpecializations()[2]);
            preparedStatement.setString(4, abiturient.getPassportNumber());
            preparedStatement.setString(5, abiturient.getSurname());
            preparedStatement.setString(6, abiturient.getFirstName());
            preparedStatement.setString(7, abiturient.getMiddleName());
            preparedStatement.setInt(8, abiturient.getYearOfBirth());
            preparedStatement.setInt(9, abiturient.getMathScore());
            preparedStatement.setInt(10, abiturient.getPhysScore());
            preparedStatement.setInt(11, abiturient.getRusScore());
            preparedStatement.setInt(12, abiturient.getGradePointAverage());
            preparedStatement.setInt(13, abiturient.getTotalScore());
            preparedStatement.setInt(14, abiturient.getExemptionLevel());
            preparedStatement.setInt(15,abiturient.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Error executing query " + UPDATE_ABITURIENT_QUERY, e);
        }
        return abiturient;
    }



    @Override
    public List<Abiturient> findAbiturientBySurname(String surname) throws Exception {
        Connection connection;
        List<Abiturient> abiturients = new ArrayList<>();
        String FIND_ABITURIENT_BY_SURNAME_QUERY = "SELECT * FROM abiturients WHERE surname=?";

        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ABITURIENT_BY_SURNAME_QUERY);
            statement.setString(1, surname);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String[] specializations = new String[]{resultSet.getString("firstSpecialization"),
                        resultSet.getString("secondSpecialization"),
                        resultSet.getString("thirdSpecialization")};
                int y=0;
                Specialization finalSpecialization;
                int specializationId = resultSet.getInt("finalSpecializationId");
                if(specializationId != 0) {
                    List<Specialization> specializationsList = new ArrayList<>(specializationDao.findAllSpecializations());
                    for (y = 0; y < specializationsList.size(); y++) {
                        if (specializationsList.get(y).getId() == (specializationId)) {
                            int index = y;
                            break;
                        }
                    }
                    finalSpecialization = specializationsList.get(y);
                }
                else{
                    finalSpecialization = null;
                }
                String passportNumber = resultSet.getString("PassportNumber");
                String firstName = resultSet.getString("FirstName");
                String middleName = resultSet.getString("MiddleName");
                int yearOfBirth = resultSet.getInt("YearOfBirth");
                int mathScore = resultSet.getInt("MathScore");
                int physScore = resultSet.getInt("PhysScore");
                int rusScore = resultSet.getInt("RusScore");
                int gPA = resultSet.getInt("GradePointAverage");
                int exemptionLevel = resultSet.getInt("ExemptionLevel");

                if(finalSpecialization == null){
                    Abiturient abiturient = new Abiturient(id, specializations,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                    abiturients.add(abiturient);
                }
                else {
                    Abiturient abiturient = new Abiturient(id, specializations, finalSpecialization,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                    abiturients.add(abiturient);
                }
            }
            return abiturients;
        } catch (SQLException e) {
            throw new Exception("Error executing query " + FIND_ABITURIENT_BY_SURNAME_QUERY, e);
        }
    }

    @Override
    public Abiturient findAbiturientByPassportNumber(String passportNumber) throws Exception {
        Connection connection;
        String FIND_ABITURIENT_BY_PASSPORT_NUMBER_QUERY = "SELECT * FROM abiturients WHERE PassportNumber=?";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ABITURIENT_BY_PASSPORT_NUMBER_QUERY);
            statement.setString(1, passportNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String[] specializations = new String[]{resultSet.getString("firstSpecialization"),
                        resultSet.getString("secondSpecialization"),
                        resultSet.getString("thirdSpecialization")};
                int y=0;
                Specialization finalSpecialization;
                int specializationId = resultSet.getInt("finalSpecializationId");
                if(specializationId != 0) {
                    List<Specialization> specializationsList = new ArrayList<>(specializationDao.findAllSpecializations());
                    for (y = 0; y < specializationsList.size(); y++) {
                        if (specializationsList.get(y).getId() == (specializationId)) {
                            int index = y;
                            break;
                        }
                    }
                    finalSpecialization = specializationsList.get(y);
                }
                else{
                    finalSpecialization = null;
                }
                String surname = resultSet.getString("Surname");
                String firstName = resultSet.getString("FirstName");
                String middleName = resultSet.getString("MiddleName");
                int yearOfBirth = resultSet.getInt("YearOfBirth");
                int mathScore = resultSet.getInt("MathScore");
                int physScore = resultSet.getInt("PhysScore");
                int rusScore = resultSet.getInt("RusScore");
                int gPA = resultSet.getInt("GradePointAverage");
                int exemptionLevel = resultSet.getInt("ExemptionLevel");

                Abiturient abiturient;
                if(finalSpecialization == null){
                    abiturient = new Abiturient(id, specializations,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                }
                else {
                    abiturient = new Abiturient(id, specializations, finalSpecialization,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                }
                return abiturient;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new Exception("Error executing query " + FIND_ABITURIENT_BY_PASSPORT_NUMBER_QUERY, e);
        }
    }



    @Override
    public List<Abiturient> findAbiturientByFullName(String surname, String firstName, String middleName) throws Exception {
        Connection connection;
        List<Abiturient> abiturients = new ArrayList<>();
        String FIND_ABITURIENT_BY_FULL_NAME_QUERY = "SELECT * FROM abiturients WHERE surname=? AND firstname=?" +
                " AND middlename=?";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ABITURIENT_BY_FULL_NAME_QUERY);
            statement.setString(1, surname);
            statement.setString(2, firstName);
            statement.setString(3, middleName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String[] specializations = new String[]{resultSet.getString("firstSpecialization"),
                        resultSet.getString("secondSpecialization"),
                        resultSet.getString("thirdSpecialization")};
                String passportNumber = resultSet.getString("PassportNumber");
                int y=0;
                Specialization finalSpecialization;
                int specializationId = resultSet.getInt("finalSpecializationId");
                if(specializationId != 0) {
                    List<Specialization> specializationsList = new ArrayList<>(specializationDao.findAllSpecializations());
                    for (y = 0; y < specializationsList.size(); y++) {
                        if (specializationsList.get(y).getId() == (specializationId)) {
                            int index = y;
                            break;
                        }
                    }
                    finalSpecialization = specializationsList.get(y);
                }
                else{
                    finalSpecialization = null;
                }
                int yearOfBirth = resultSet.getInt("YearOfBirth");
                int mathScore = resultSet.getInt("MathScore");
                int physScore = resultSet.getInt("PhysScore");
                int rusScore = resultSet.getInt("RusScore");
                int gPA = resultSet.getInt("GradePointAverage");
                int exemptionLevel = resultSet.getInt("ExemptionLevel");

                Abiturient abiturient;
                if(finalSpecialization == null){
                    abiturient = new Abiturient(id, specializations,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                    abiturients.add(abiturient);
                }
                else {
                    abiturient = new Abiturient(id, specializations, finalSpecialization,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                    abiturients.add(abiturient);
                }
            }
            return abiturients;
        } catch (SQLException e) {
            throw new Exception("Error executing query " + FIND_ABITURIENT_BY_FULL_NAME_QUERY, e);
        }
    }

    @Override
    public List<Abiturient> findAbiturientsBySpecialization(String specialization) throws Exception {
        Connection connection;
        List<Abiturient> abiturients = new ArrayList<>();
        String FIND_ABITURIENTS_BY_SPECIALIZATION_QUERY = "SELECT * FROM abiturients WHERE firstSpecialization=?";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ABITURIENTS_BY_SPECIALIZATION_QUERY);
            statement.setString(1, specialization);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String[] specializations = new String[]{resultSet.getString("firstSpecialization"),
                        resultSet.getString("secondSpecialization"),
                        resultSet.getString("thirdSpecialization")};
                int y=0;
                Specialization finalSpecialization;
                int specializationId = resultSet.getInt("finalSpecializationId");
                if(specializationId != 0) {
                    List<Specialization> specializationsList = new ArrayList<>(specializationDao.findAllSpecializations());
                    for (y = 0; y < specializationsList.size(); y++) {
                        if (specializationsList.get(y).getId() == (specializationId)) {
                            int index = y;
                            break;
                        }
                    }
                    finalSpecialization = specializationsList.get(y);
                }
                else{
                    finalSpecialization = null;
                }
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
                Abiturient abiturient;
                if(finalSpecialization == null){
                    abiturient = new Abiturient(id, specializations,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                    abiturients.add(abiturient);
                }
                else {
                    abiturient = new Abiturient(id, specializations, finalSpecialization,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                    abiturients.add(abiturient);
                }
            }
            return abiturients;
        } catch (SQLException e) {
            throw new Exception("Error executing query " + FIND_ABITURIENTS_BY_SPECIALIZATION_QUERY, e);
        }
    }

    @Override
    public List<Abiturient> sortAbiturientsByTotalScore(String specialization) throws Exception {
        List<Abiturient> abiturients = new ArrayList<>();
        abiturients = findAbiturientsBySpecialization(specialization);

        Collections.sort(abiturients, new Comparator<Abiturient>() {
            @Override
            public int compare(Abiturient abiturient1, Abiturient abiturient2) {
                int result = Double.compare(abiturient2.getTotalScore(), abiturient1.getTotalScore());
                if (result == 0) {
                    result = Integer.compare(abiturient2.getExemptionLevel(), abiturient1.getExemptionLevel());
                    if (result == 0) {
                        result = Integer.compare(abiturient2.getMathScore(), abiturient1.getMathScore());
                        if (result == 0) {
                            result = Integer.compare(abiturient2.getPhysScore(), abiturient1.getPhysScore());
                            if (result == 0) {
                                result = Integer.compare(abiturient2.getRusScore(), abiturient1.getRusScore());
                            }
                        }
                    }
                }
                return result;
            }
        });
        return abiturients;
    }

    public void SortAbiturientsBySpecializations( int[] places) throws Exception {
        List<Specialization> specializations = new ArrayList<>(specializationDao.findAllSpecializations());
        List<Abiturient> abiturients = new ArrayList<>(findAllAbiturients());

        Collections.sort(abiturients, (a1, a2) -> Integer.compare(a2.getTotalScore(), a1.getTotalScore()));
        List<Abiturient> abiturientsInSpecializations = new ArrayList<>();
        int u;
        for (Abiturient abiturient : abiturients) {
            boolean added = false;
            for (int i = 0; i < 3; i++) {
                Specialization specialization = specializationDao.findSpecializationBySpecializationName(abiturient.getSelectedSpecializations()[i]);

                if (specialization != null) {
                    for (u = 0; u < specializations.size(); u++) {
                        if (specializations.get(u).getId() == (specialization.getId())) {
                            int index = u;
                            break;
                        }
                    }
                    abiturientsInSpecializations = specializations.get(u).getAbiturients();
                     if (abiturientsInSpecializations.size() < specialization.getNumberOfPlaces()) {
                        abiturientsInSpecializations.add(abiturient);
                        specializations.get(u).setAbiturients(abiturientsInSpecializations);
                        abiturient.setFinalSpecialization(specialization);
                        added = true;
                        break;
                    }
                }
            }
            if (!added) {
                abiturient.setFinalSpecialization(null);
            }
        }
        updateFinalSpecialization(abiturients);
    }

    public void updateFinalSpecialization(List<Abiturient> abiturients) {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            for (Abiturient abiturient : abiturients) {
                int id = abiturient.getId();

                if(abiturient.getFinalSpecialization() == null){
                    String updateSql = "UPDATE Abiturients SET finalSpecializationId = null WHERE id = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                    updateStmt.setInt(1, id);
                    updateStmt.executeUpdate();
                }
                else{
                    Specialization finalSpecialization = abiturient.getFinalSpecialization();
                    String updateSql = "UPDATE Abiturients SET finalSpecializationId = ? WHERE id = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                    updateStmt.setInt(1,
                            specializationDao.findSpecializationBySpecializationName(   finalSpecialization.getSpecializationName()).getId() );
                    updateStmt.setInt(2, id);
                    updateStmt.executeUpdate();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Abiturient> sortAbiturientsByTotalScoreInFinalSpecialization(int finalSpecializationId) throws Exception {
        List<Abiturient> abiturients = new ArrayList<>();
        abiturients = findAbiturientsByFinalSpecialization(finalSpecializationId);

        Collections.sort(abiturients, new Comparator<Abiturient>() {
            @Override
            public int compare(Abiturient abiturient1, Abiturient abiturient2) {
                int result = Double.compare(abiturient2.getTotalScore(), abiturient1.getTotalScore());
                if (result == 0) {
                    result = Integer.compare(abiturient2.getExemptionLevel(), abiturient1.getExemptionLevel());
                    if (result == 0) {
                        result = Integer.compare(abiturient2.getMathScore(), abiturient1.getMathScore());
                        if (result == 0) {
                            result = Integer.compare(abiturient2.getPhysScore(), abiturient1.getPhysScore());
                            if (result == 0) {
                                result = Integer.compare(abiturient2.getRusScore(), abiturient1.getRusScore());
                            }
                        }
                    }
                }
                return result;
            }
        });
        return abiturients;
    }

    @Override
    public List<Abiturient> findAbiturientsByFinalSpecialization(int finalSpecializationId) throws Exception {
        Connection connection;
        List<Abiturient> abiturients = new ArrayList<>();
        String FIND_ABITURIENTS_BY_SPECIALIZATION_QUERY = "SELECT * FROM abiturients WHERE finalSpecializationId=?";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ABITURIENTS_BY_SPECIALIZATION_QUERY);
            statement.setInt(1, finalSpecializationId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                //String specialization = resultSet.getString("Specialization");
                String[] specializations = new String[]{resultSet.getString("firstSpecialization"),
                        resultSet.getString("secondSpecialization"),
                        resultSet.getString("thirdSpecialization")};
                int y=0;
                Specialization finalSpecialization;
                int specializationId = resultSet.getInt("finalSpecializationId");
                if(specializationId != 0) {
                    List<Specialization> specializationsList = new ArrayList<>(specializationDao.findAllSpecializations());
                    for (y = 0; y < specializationsList.size(); y++) {
                        if (specializationsList.get(y).getId() == (specializationId)) {
                            int index = y;
                            break;
                        }
                    }
                    finalSpecialization = specializationsList.get(y);
                }
                else{
                    finalSpecialization = null;
                }
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
                Abiturient abiturient;
                if(finalSpecialization == null){
                    abiturient = new Abiturient(id, specializations,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                    abiturients.add(abiturient);
                }
                else {
                    abiturient = new Abiturient(id, specializations, finalSpecialization,
                            passportNumber, surname, firstName, middleName, yearOfBirth, mathScore, physScore, rusScore, gPA,
                            exemptionLevel);
                    abiturients.add(abiturient);
                }
            }
            return abiturients;
        } catch (SQLException e) {
            throw new Exception("Error executing query " + FIND_ABITURIENTS_BY_SPECIALIZATION_QUERY, e);
        }
    }

    @Override
    public void setSpecializationNull() throws Exception {
        Connection connection;
        final String UPDATE_SPECIALIZATIONS_PLACES_QUERY = "UPDATE abiturients SET finalspecializationId = NULL;";
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SPECIALIZATIONS_PLACES_QUERY);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
