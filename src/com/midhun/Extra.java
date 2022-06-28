package com.midhun;

import java.sql.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Extra {

    static void insertGrades(){
        String url = "jdbc:mysql://localhost:xxxx/students";
        String username = "yourusernamehere";
        String password = "yourpasswordhere";
        PreparedStatement p = null;
        ResultSet rs = null;
        try (Connection connection = DriverManager.getConnection(url, username, password)) {


            String sql = "select * from students  ";
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();



                // Condition check
                while (rs.next()) {


                    String name = rs.getString("Name");
                    int adm = rs.getInt("Adno");
                    float phy = rs.getFloat("Physics");
                    float che = rs.getFloat("Chemistry");
                    float math = rs.getFloat("Maths");

                    failCheck(name,adm,phy,che,math);

               }
        }
        catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!");
        }
    }
    static String failCheck(String name,int adm,float phy, float che, float math){
        String pg = Grade.phyGrade(phy);
        String cg = Grade.cheGrade(che);
        String mg = Grade.mathGrade(math);
        String check;
        if(pg=="D" || pg == "E1" || pg == "E2" ){
            sendEmail(name,adm,phy,che,math);
            check = "Fail";
        }
        else if(cg=="D" || cg == "E1" || cg == "E2" ){
            sendEmail(name,adm,phy,che,math);
            check = "Fail";
        }
        else if(mg=="D" || mg == "E1" || mg == "E2" ){
            sendEmail(name,adm,phy,che,math);
            check = "Fail";
        }
        else check = "Pass";

        return check;

    }

    static void sendEmail(String name, int adm , float phy, float che, float math) {

        String to = name+"@gmail.com";

        String from = "yourgmailid";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("yourmailid", "yourpassword");

            }

        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(adm+" - Failed!");

            // Now set the actual message
            message.setText("Hi "+name+",\r\n You failed in one/more subjects with a grade lower than or equal to D." +
                    " The mark list is shown below: \r\n  \r\n" +
                    "Name :"+ name+"\r\n" +
                    "Admission No :"+ adm+ "\r\n" +
                    "Physics :"+phy+ "\r\n" +
                    "Chemistry :"+che+ "\r\n" +
                    "Maths :"+math);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully to "+ name);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }


    }
}
