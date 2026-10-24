import window.SplashWindow;
import java.sql.SQLException;

/**
 * Main application class
 *
 * @author S.N.Tishchenko
 * @version 1.0
 **/
public class Main {
    /**
     * Method <code>main</code> creates splash window of program
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new SplashWindow();
    }

}

