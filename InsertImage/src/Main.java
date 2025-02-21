import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException{
        String url = "jdbc:mysql://localhost:3306/school";
        String username = "root";
        String password = "@EnterYourMysqlPassword";
//        String imgpath = "C:\\Users\\abhis\\Downloads\\image1.jpg";
//        String query = "insert into img_table(image_data) values(?)";

        String file_path = "C:\\Users\\abhis\\Downloads\\New folder\\";
         String query = "SELECT image_data FROM img_table WHERE image_id = ?";
        ;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("Connection established successfully");

//            FileInputStream fileInputStream = new FileInputStream(imgpath);
//            byte[] imagedata = new byte[fileInputStream.available()];
//            fileInputStream.read(imagedata);
//
//            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setBytes(1,imagedata);
//            int rows=preparedStatement.executeUpdate();
//            if (rows>0){
//                System.out.println("Insertion successfully");
//            }else {
//                System.out.println("Insertion failed");
//            }


            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                byte[] image_data = resultSet.getBytes("image_data");
                String image_path = file_path + "ExtractedImage.jpg"; // Use proper file extension


                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(image_data);
            }else {
                System.out.println("Image not found");
            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
