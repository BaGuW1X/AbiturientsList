package connection;
import java.sql.*;

/**
 * The DatabaseConnection class represents a connection to a MySQL database.
 * This class contains the methods to retrieve a connection instance and to create a new database connection.
 *
 * @author S.N.Tishchenko
 * @version 1.0
 **/
public class DatabaseConnection {

    Connection connection;
    String dbDriver = "com.mysql.cj.jdbc.Driver";
    final String dbUrl = "jdbc:mysql://localhost:3306/list";
    final String dbUser = "mysql";
    final String dbPassword = "mysql";
    private static DatabaseConnection instance = null;

    /**
     * Retrieves a singleton instance of the DatabaseConnection class.
     *
     * @return the database connection instance.
     * @throws SQLException if a database error occurs.
     */
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null)
            instance = new DatabaseConnection();
        return instance;
    }

    /**
     * Private constructor for the DatabaseConnection class.
     * Creates a new database connection with the specified driver, URL, username and password.
     *
     * @throws SQLException if a database error occurs.
     */
     private DatabaseConnection() throws SQLException {
         try {
             Class.forName(dbDriver);
             connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
         }catch (ClassNotFoundException ex){
             ex.printStackTrace();
         }
     }

    /**
     * Method to get database connection
     * @return database connection
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Method creates the required tables in the database if they do not already exist.
     *
     * @throws SQLException if a database error occurs.
     */
    public static void createTableIfNotExists() throws SQLException {
        try {
            Statement statement = DatabaseConnection.getInstance().getConnection().createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `specializations` (`id` INT PRIMARY KEY AUTO_INCREMENT," +
                    " `SpecializationName` VARCHAR(100), `SpecializationKey` VARCHAR(20),`numberOfPlaces` INT,`Abiturients` VARCHAR(100));");
            //PreparedStatement preparedStatement = initializeDatabase().prepareStatement("INSERT INTO specializations (`SpecializationName`) VALUES (`Программное обеспечение информационных технологий`);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `abiturients` (`id` INT PRIMARY KEY AUTO_INCREMENT," +
                    "finalSpecializationId INT, FOREIGN KEY (finalSpecializationId ) REFERENCES specializations(id)," +
                    " `FirstSpecialization` VARCHAR(100),`SecondSpecialization` VARCHAR(100),`ThirdSpecialization` VARCHAR(100)," +
                    " `PassportNumber` VARCHAR(100), `Surname` VARCHAR(100)," +
                    " `FirstName` VARCHAR(100), `MiddleName` VARCHAR(100),`YearOfBirth` INT," +
                    " `MathScore` INT, `PhysScore` INT, `RusScore` INT, `GradePointAverage` INT," +
                    "`TotalScore` INT, `ExemptionLevel` INT);");
           /* statement.executeUpdate("INSERT INTO specializations (SpecializationName,SpecializationKey) VALUES " +
                    "('Программное обеспечение информационных технологий', '1-40 01 01'), " +
                    " ('Информационные системы и технологии (в проектировании и производстве)', '1-40 05 01-01')," +
                    "('Информационные системы и технологии (в обработке и представлении информации)','1-40 05 01-04' )," +
                    "('Автоматизация технологических процессов и производств (по направлениям)', '1-53 01 01')," +
                    "('Автоматизированные электроприводы', '1-53 01 05')," +
                    "('Промышленные роботы и робототехнические комплексы', '1-53 01 06') ;");*/
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}