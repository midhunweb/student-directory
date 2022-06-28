package com.midhun;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.json.simple.*;


import java.util.*;
import java.lang.Object;
import java.io.*;

public class Main {
    private static FileWriter file;

    public static void main(String[] args) {

        excelToDatabase();
        Extra.insertGrades();
        String str = null;
        while(str != "exit"){

            System.out.println("Welcome to Student Directory\n 1.Search by Name\n 2.Search by Admission Number \n 3.Exit");
            System.out.print("Enter your choice:");
            Scanner sc = new Scanner(System.in);
            String ch = sc.nextLine();
            ChoicePanel cp = new ChoicePanel();
            str = cp.choiceData(ch);

        }
    }

    public static void excelToDatabase() {
            String url = "jdbc:mysql://localhost:3306/students";
            String username = "root";
            String password = "100%Discount";

            System.out.println("Connecting database...");
            ReadExcel rc = new ReadExcel();

            Workbook wbook = null;

            try {
                //reading data from a file in the form of bytes
                FileInputStream fis = new FileInputStream("E:\\python-file\\data.xlsx");
                //creates an XSSFWorkbook object by buffering the whole stream into the memory
                wbook = new XSSFWorkbook(fis);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Sheet sheet = wbook.getSheetAt(0);
            int r = sheet.getLastRowNum() + 1;


            JSONArray ja = new JSONArray();
            List<JSONObject> myJsonObjects = new ArrayList<JSONObject>();
            for (int i = 1; i < r; i++) {
                JSONObject obj = new JSONObject();
                obj.put("name", rc.ReadCellData(i, 0));
                obj.put("admissionno", rc.ReadAdmData(i, 1));
                obj.put("physics", rc.ReadPhyData(i, 2));
                obj.put("chemistry", rc.ReadCheData(i, 3));
                obj.put("maths", rc.ReadMathData(i, 4));

                myJsonObjects.add(obj);

                ja.add(obj);
                try {

                    // Constructs a FileWriter given a file name, using the platform's default charset
                    file = new FileWriter("E:\\python-file\\Json.txt");
                    file.write(ja.toJSONString());


                } catch (IOException e) {
                    e.printStackTrace();

                } finally {

                    try {
                        file.flush();
                        file.close();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }


            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                System.out.println("Database connected!");
                Statement statement = connection.createStatement();
                statement.executeUpdate("TRUNCATE TABLE students");
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO students values (?, ?, ?, ?, ? )");

                for (Object object : ja) {
                    JSONObject record = (JSONObject) object;

                    String Name = (String) record.get("name");

                    int a = (Integer) record.get("admissionno");
                    float b = (Float) record.get("physics");
                    float c = (Float) record.get("chemistry");
                    float d = (Float) record.get("maths");

                    pstmt.setString(1, Name);
                    pstmt.setInt(2, a);
                    pstmt.setFloat(3, b);

                    pstmt.setFloat(4, c);
                    pstmt.setFloat(5, d);
                    pstmt.executeUpdate();
                }


            } catch (SQLException e) {
                System.out.println(e.toString());
                System.out.println("Exit the system");


            }
        }

}


class ReadExcel {

    //method defined for reading a cell
    public String ReadCellData(int vRow, int vColumn) {
        String value = null; //variable for storing the cell value

        Workbook wbook = null; //initialize Workbook null
        try {
            //reading data from a file in the form of bytes
            FileInputStream fis = new FileInputStream("E:\\python-file\\data.xlsx");
            //creates an XSSFWorkbook object by buffering the whole stream into the memory
            wbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Sheet sheet = wbook.getSheetAt(0);
        //getting the XSSFSheet object at given index
        Row row = sheet.getRow(vRow);
        //returns the logical row
        Cell cell = row.getCell(vColumn);
        //getting the cell representing the given column

        if (vColumn == 0) {
            value = cell.getStringCellValue();
            //getting cell value

        }

        return value;

    }

    public int ReadAdmData(int vRow, int vColumn) {
        //variable for storing the cell value
        String valu = null;
        int intVal = 0;
        Workbook wbook = null; //initialize Workbook null
        try {
            //reading data from a file in the form of bytes
            FileInputStream fis = new FileInputStream("E:\\python-file\\data.xlsx");
            //creates an XSSFWorkbook object by buffering the whole stream into the memory
            wbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Sheet sheet = wbook.getSheetAt(0);
        //getting the XSSFSheet object at given index
        Row row = sheet.getRow(vRow);
        //returns the logical row
        Cell cell = row.getCell(vColumn);
        //getting the cell representing the given column

        //returns the cell value
        if (vColumn == 1) {
            valu = String.valueOf(cell.getNumericCellValue());

            intVal = Integer.parseInt(0 + valu.replaceAll("\\D+0", ""));


        }

        return intVal;

    }

    public float ReadPhyData(int vRow, int vColumn) {
        float phyVal = 0;
        String val = null;
        Workbook wbook = null; //initialize Workbook null
        try {
            //reading data from a file in the form of bytes
            FileInputStream fis = new FileInputStream("E:\\python-file\\data.xlsx");
            //creates an XSSFWorkbook object by buffering the whole stream into the memory
            wbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Sheet sheet = wbook.getSheetAt(0);
        //getting the XSSFSheet object at given index
        Row row = sheet.getRow(vRow);
        //returns the logical row

        //getting the cell representing the given column


        Cell cell = row.getCell(vColumn);
        val = String.valueOf(cell.getNumericCellValue());
        phyVal = Float.parseFloat(val);
        //getting cell value


        return phyVal;


    }

    public float ReadCheData(int vRow, int vColumn) {
        float cheVal = 0;
        String val = null;
        Workbook wbook = null; //initialize Workbook null
        try {
            //reading data from a file in the form of bytes
            FileInputStream fis = new FileInputStream("E:\\python-file\\data.xlsx");
            //creates an XSSFWorkbook object by buffering the whole stream into the memory
            wbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Sheet sheet = wbook.getSheetAt(0);
        //getting the XSSFSheet object at given index
        Row row = sheet.getRow(vRow);
        //returns the logical row

        //getting the cell representing the given column


        Cell cell = row.getCell(vColumn);
        val = String.valueOf(cell.getNumericCellValue());
        cheVal = Float.parseFloat(val);
        //getting cell value


        return cheVal;


    }

    public float ReadMathData(int vRow, int vColumn) {
        float mathVal = 0;
        String val = null;
        Workbook wbook = null; //initialize Workbook null
        try {
            //reading data from a file in the form of bytes
            FileInputStream fis = new FileInputStream("E:\\python-file\\data.xlsx");
            //creates an XSSFWorkbook object by buffering the whole stream into the memory
            wbook = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Sheet sheet = wbook.getSheetAt(0);
        //getting the XSSFSheet object at given index
        Row row = sheet.getRow(vRow);
        //returns the logical row

        //getting the cell representing the given column


        Cell cell = row.getCell(vColumn);
        val = String.valueOf(cell.getNumericCellValue());
        mathVal = Float.parseFloat(val);
        //getting cell value


        return mathVal;


    }


}