package window;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AboutAuthorWindow extends JDialog {


    /**
     * Default constructor to create graphic interface of window
     */
    public AboutAuthorWindow() {
        /**
         * Creating contents of the window
         */
        String student = "Студент группы 10702420";
        String fullName = "Тищенко Святослав Николаевич";
        String number = "+375 44 543-26-52";
        String mail = "ent.bag777@gmail.com";

        Image imageAuthor = null;

        {
            try {
                imageAuthor =  ImageIO.read(getClass().getResource("/images/authorAvatar.jpg"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ImageIcon iconMe = new ImageIcon(imageAuthor);


        /**
         * Creating components of the window
         */
        JLabel lblStudent = new JLabel(student);
        JLabel lblFullName = new JLabel(fullName);
        JLabel lblNumber = new JLabel(number);
        JLabel lblImage = new JLabel(iconMe);
        JLabel lblMail = new JLabel(mail);
        JPanel jplImage = new JPanel();

        JPanel jplMain = new JPanel();
        JPanel jplTextInfo = new JPanel();

        /**
         * Settings of the image and added to the panel
         */
        jplImage.setLayout(new BoxLayout(jplImage, BoxLayout.Y_AXIS));
        jplImage.add(lblImage);
        jplImage.setAlignmentX(CENTER_ALIGNMENT);

        /**
         * Settings of the panel with text info and adding elements to it
         */
        jplTextInfo.setLayout(new BoxLayout(jplTextInfo, BoxLayout.Y_AXIS));
        jplTextInfo.setAlignmentX(CENTER_ALIGNMENT);
        jplTextInfo.add(lblStudent);
        jplTextInfo.add(lblFullName);
        jplTextInfo.add(lblNumber);
        jplTextInfo.add(lblMail);

        /**
         * Setting of the main panel and adding elements to it
         */
        jplMain.setLayout(new BoxLayout(jplMain, BoxLayout.Y_AXIS));
        jplMain.add(Box.createVerticalStrut(10));
        jplMain.add(jplImage);
        jplMain.add(Box.createVerticalStrut(5));
        jplMain.add(jplTextInfo);

        /**
         * Settings of the frame
         */
        setTitle("Об авторе");
        setModal(true);
        setContentPane(jplMain);
        setSize(400,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

