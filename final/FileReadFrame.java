import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class FileReadFrame extends JFrame implements ActionListener {
   private JScrollPane scrollPane;       // Container adds scroll bar
   private JTextArea outputArea;         // Holds file output
   private JLabel selectedFileLabel;     // Label for file name
   private JLabel outputLabel;           // Label for file contents
   private JTextField selectedFileField; // Holds name of file
   private JFileChooser fileChooser;     // Enables user to select file
   private JButton search;       
   private JButton student;
   private JButton teacher;
   private JTextField target;
   private boolean stuMode=false;
   private boolean teaMode=false;
   ArrayList<studentData> sData=new ArrayList<studentData>();
   ArrayList<teacherData> tData=new ArrayList<teacherData>();
   /* Constructor creates GUI components and adds GUI components
      using a GridBagLayout. */
   FileReadFrame() {
      GridBagConstraints layoutConst = null; // GUI component layout

      // Set frame's title
      setTitle("File reader");

      outputLabel = new JLabel("File contents:");
      selectedFileLabel = new JLabel("Selected file:");

      selectedFileField = new JTextField(20);
      selectedFileField.setEditable(false);
      selectedFileField.setText("...");

      outputArea = new JTextArea(10, 25);
      scrollPane = new JScrollPane(outputArea);
      outputArea.setEditable(false);
      student= new JButton("student");  
      student.addActionListener(this);
      teacher= new JButton("teacher");
      teacher.addActionListener(this);
      search=new JButton("search");
      search.addActionListener(this);  
      target=new JTextField(15);
      target.setEditable(true);
      target.setText("0");
      //target.setColumns(15);
      fileChooser = new JFileChooser();

      // Add components using GridBagLayout
      setLayout(new GridBagLayout());


      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 3;
      layoutConst.gridy = 1;
      add(search, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 4;
      layoutConst.gridy = 1;
      add(target, layoutConst);
      //student button
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 1;
      layoutConst.gridy = 1;
      add(student, layoutConst);
      //teacher button
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 2;
      layoutConst.gridy = 1;
      add(teacher, layoutConst);
      //file field
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 5, 5, 1);
      layoutConst.anchor = GridBagConstraints.LINE_END;
      layoutConst.gridx = 1;
      layoutConst.gridy = 0;
      add(selectedFileLabel, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 1, 5, 10);
      layoutConst.fill = GridBagConstraints.LINE_END;
      layoutConst.gridx = 2;
      layoutConst.gridy = 0;
      layoutConst.gridwidth = 2;
      layoutConst.gridheight = 1;
      add(selectedFileField, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(5, 10, 0, 0);
      layoutConst.fill = GridBagConstraints.LINE_END;
      layoutConst.gridx = 0;
      layoutConst.gridy = 1;
      add(outputLabel, layoutConst);

      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(1, 10, 10, 10);
      layoutConst.fill = GridBagConstraints.LINE_END;
      layoutConst.gridx = 0;
      layoutConst.gridy = 2;
      layoutConst.gridheight = 2;
      layoutConst.gridwidth = 4;
      add(scrollPane, layoutConst);
   }

   /* Called when openFileButton is pressed. */
   @Override
   public void actionPerformed(ActionEvent event) {
       //student button
       if(event.getSource()==student){
          teacher.setVisible(false);
          stuMode=true;
          BufferedReader readBuffer = null; // File input stream
          Scanner inFS = null;                   // Scanner object
          String readLine;                       // Input from file
          File readFile = null;                  // Input file
          int fileChooserVal;                    // File chooser
          
          // Open file chooser dialog and get the file to open
          fileChooserVal = fileChooser.showOpenDialog(this);
    
          // Check if file was selected
          if (fileChooserVal == JFileChooser.APPROVE_OPTION) {
             readFile = fileChooser.getSelectedFile();
             
             // Update selected file field
             selectedFileField.setText(readFile.getName());
             
             // Ensure file is valid
             if (readFile.canRead()) {
                try {
                   readBuffer = new BufferedReader(new FileReader(readFile));
                   //inFS = new Scanner(fileByteStream);
                   readBuffer.readLine();
                   // Clear output area
                   outputArea.setText(""); 
                   while ((readLine=readBuffer.readLine())!=null) {
                      studentData studentObj=new studentData(
                      (CSVtoArrayList(readLine).get(0)),Integer.parseInt
                      (CSVtoArrayList(readLine).get(1)),Double.parseDouble
                      (CSVtoArrayList(readLine).get(2)),Integer.parseInt
                      (CSVtoArrayList(readLine).get(3)));
                      sData.add(studentObj);
                      outputArea.append(readLine + "\n");
                   }


                } catch (IOException e) {
                   outputArea.append("\n\nError occurred while creating file stream! " + e.getMessage());
                }
             }
             else { // Can't read file
                // Show failure dialog
                JOptionPane.showMessageDialog(this, "Can't read file!");
             }
          }
       }
       
           
       
       //teacher button
       if(event.getSource()==teacher){
          student.setVisible(false);
          
          BufferedReader readBuffer = null; // File input stream
          Scanner inFS = null;                   // Scanner object
          String readLine;                       // Input from file
          File readFile = null;                  // Input file
          int fileChooserVal;                    // File chooser
          
          // Open file chooser dialog and get the file to open
          fileChooserVal = fileChooser.showOpenDialog(this);
    
          // Check if file was selected
          if (fileChooserVal == JFileChooser.APPROVE_OPTION) {
             readFile = fileChooser.getSelectedFile();
             
             // Update selected file field
             selectedFileField.setText(readFile.getName());
             
             // Ensure file is valid
             if (readFile.canRead()) {
                try {
                   readBuffer = new BufferedReader(new FileReader(readFile));
                   //inFS = new Scanner(fileByteStream);
                   readBuffer.readLine();
                   // Clear output area
                   outputArea.setText(""); 
                   while ((readLine=readBuffer.readLine())!=null) {
                      teacherData teacherObj=new teacherData(
                      (CSVtoArrayList(readLine).get(0)),Integer.parseInt
                      (CSVtoArrayList(readLine).get(1)),Integer.parseInt
                      (CSVtoArrayList(readLine).get(2)),
                      (CSVtoArrayList(readLine).get(3)));
                      tData.add(teacherObj);
                      outputArea.append(readLine + "\n");
                   }


                } catch (IOException e) {
                   outputArea.append("\n\nError occurred while creating file stream! " + e.getMessage());
                }
             }
             else { // Can't read file
                // Show failure dialog
                JOptionPane.showMessageDialog(this, "Can't read file!");
             }
          }
       }
       //search button
       if(event.getSource()==search){
           if(stuMode==true){
               outputArea.append(studentSearching(sData,target.getText()));
           }
           
       
       }
   }
   public static ArrayList<String> CSVtoArrayList(String CSVFileName) {
        
        //TO DO: ensure this arraylist can handle integers, not only strings
        ArrayList<String> arrlist = new ArrayList<String>();
        
        
        if (CSVFileName != null) {
            String[] splitData = CSVFileName.split("\\,", -1); //the -1 helps handle the null values
            
            for (int i = 0; i < splitData.length; i++) {
                //if it is null, replace it with a 0
                if (splitData[i].length() == 0) {
                    splitData[i] = "0";
                }
                //as long as it is not null and the length is not 0, trim the value and add it to the arraylist
                if (!(splitData[i] == null) || !(splitData[i].length() == 0)) {
                    arrlist.add(splitData[i].trim());
                }
            }
         }
     return arrlist;
   }
   public static <TheType extends Comparable<TheType>> TheType studentSearching(ArrayList<studentData> data
   , TheType target){
           for(studentData i: data){
               if(target.compareTo((TheType)i.name)==0){
                   return (TheType)i.gpa.toString();
               }
           }
           return (TheType)"Not Found";
      
   }
}