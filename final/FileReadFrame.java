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
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;

public class FileReadFrame extends JFrame implements ActionListener {
   private JScrollPane scrollPane;       // Container adds scroll bar
   private JTextArea outputArea;         // Holds file output
   private JLabel selectedFileLabel;     // Label for file name
   private JLabel outputLabel;           // Label for file contents
   private JLabel nameLabel, ageLabel, gpaLabel, gradeLabel;   //student and generics label
   private JLabel tNameLabel, tAgeLabel, tSalaryLabel, tSubLabel; //teacher label
   private JTextField selectedFileField; // Holds name of file
   private JFileChooser fileChooser;     // Enables user to select file
   private JButton search;       
   private JButton student, SAvgAge, SAvgGrade;
   private JButton teacher, tAvgAge, tAvgSalary;
   private JButton addStudent, addTeacher, clearStudent, clearTeacher, clearMainBox;
   private JTextField target;
   private JTextField nameField, ageField, gpaField, gradeField, SAvgAgeField, SAvgGradeField; //student text field
   private JTextField tNameField, tAgeField, tSalaryField, tSubField, tAvgAgeField, tAvgSalaryField; //teacher text field
   private boolean stuMode=false;
   private boolean teaMode=false;
   ArrayList<studentData> sData=new ArrayList<studentData>();
   ArrayList<teacherData> tData=new ArrayList<teacherData>();
   
   File applause = new File("applause_y.wav"); //add button
   File blip = new File("blip.wav"); // clear button
   File gong = new File("peeeooop_x.wav"); //avage button
   File boxing = new File("boxing_bell.wav"); // teacher, student, search button
   
   /* Constructor creates GUI components and adds GUI components
      using a GridBagLayout. */
   FileReadFrame() {
       
       
      GridBagConstraints layoutConst = null; // GUI component layout

      // Set frame's title
      setTitle("Final Project");

      outputLabel = new JLabel("File contents:");
      selectedFileLabel = new JLabel("Selected file:");
      //Student labels
      nameLabel = new JLabel("Name: ");
      ageLabel = new JLabel("Age: ");
      gpaLabel = new JLabel("GPA: ");
      gradeLabel = new JLabel("Grade(9-12):");
      //Teacher Label
      tNameLabel = new JLabel("Name: ");
      tAgeLabel = new JLabel("Age: ");
      tSalaryLabel = new JLabel("Salary/yr: ");
      tSubLabel = new JLabel("Subject:");

      selectedFileField = new JTextField(20);
      selectedFileField.setEditable(false);
      selectedFileField.setText("...");
      //teacher average age text box
      tAvgAgeField = new JTextField(15);
      tAvgAgeField.setEditable(false);
      tAvgAgeField.setText("0");
      
      //teacher average salary text box
      tAvgSalaryField = new JTextField(20);
      tAvgSalaryField.setEditable(false);
      tAvgSalaryField.setText("0");
      
      //student average age text box
      SAvgAgeField = new JTextField(15);
      SAvgAgeField.setEditable(false);
      SAvgAgeField.setText("0");
      
      //student average grade text box
      SAvgGradeField = new JTextField(10);
      SAvgGradeField.setEditable(false);
      SAvgGradeField.setText("0");
      
      //student text
      nameField = new JTextField(20);
      nameField.setEditable(true);
      nameField.setText("");
      nameField.addActionListener(this);
      
      ageField = new JTextField(5);
      ageField.setEditable(true);
      ageField.setText("0");
      ageField.addActionListener(this);
      
      gpaField = new JTextField(5);
      gpaField.setEditable(true);
      gpaField.setText("0");
      gpaField.addActionListener(this);
      
      gradeField = new JTextField(5);
      gradeField.setEditable(true);
      gradeField.setText("0");
      gradeField.addActionListener(this);
      
      //teacher text
      tNameField = new JTextField(20);
      tNameField.setEditable(true);
      tNameField.setText(" ");
      tNameField.addActionListener(this);
      
      tAgeField = new JTextField(5);
      tAgeField.setEditable(true);
      tAgeField.setText("0");
      tAgeField.addActionListener(this);
      
      tSalaryField = new JTextField(10);
      tSalaryField.setEditable(true);
      tSalaryField.setText("0");
      tSalaryField.addActionListener(this);
      
      tSubField = new JTextField(20);
      tSubField.setEditable(true);
      tSubField.setText("  ");
      tSubField.addActionListener(this);

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
      target.setText(" ");
      //target.setColumns(15);
      addStudent = new JButton("Add Student"); // student button
      addStudent.addActionListener(this);
      
      addTeacher = new JButton("Add Teacher"); // teacher button
      addTeacher.addActionListener(this);
      tAvgAge = new JButton("Teacher Avg. Age"); // teacher average age button
      tAvgAge.addActionListener(this);
      tAvgSalary = new JButton("Teacher Avg. Salary"); // teacher average salary button
      tAvgSalary.addActionListener(this);
      SAvgAge = new JButton("Student Avg. Age"); // student average age button
      SAvgAge.addActionListener(this);
      SAvgGrade = new JButton("Student Avg. Grade"); // student average grade button
      SAvgGrade.addActionListener(this);
      
      clearStudent = new JButton("Clear"); //clear button
      clearTeacher = new JButton("Clear");
      clearMainBox = new JButton("Clear");
      clearStudent.addActionListener(this);
      clearTeacher.addActionListener(this);
      clearMainBox.addActionListener(this);
      
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
      //main text box
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(1, 10, 10, 10);
      layoutConst.fill = GridBagConstraints.LINE_END;
      layoutConst.gridx = 0;
      layoutConst.gridy = 10;
      layoutConst.gridheight = 2;
      layoutConst.gridwidth = 5;
      add(scrollPane, layoutConst);
      //clear Main Box Button
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 0;
      layoutConst.gridy = 11;
      add(clearMainBox, layoutConst);
      
      //add student button
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 0;
      layoutConst.gridy = 3;
      add(addStudent, layoutConst);
      // clear student button
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 1;
      layoutConst.gridy = 3;
      add(clearStudent, layoutConst);
      
      //add teacher button
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 0;
      layoutConst.gridy = 6;
      add(addTeacher, layoutConst);
      //clear teacher button
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 1;
      layoutConst.gridy = 6;
      add(clearTeacher, layoutConst);
      //teacher average age/salary text/button
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 4;
      layoutConst.gridy = 6;
      add(tAvgAge, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 5;
      layoutConst.gridy = 6;
      layoutConst.insets = new Insets(2,2,2,2);
      add(tAvgAgeField, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 4;
      layoutConst.gridy = 7;
      add(tAvgSalary, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.gridx = 5;
      layoutConst.gridy = 7;
      add(tAvgSalaryField, layoutConst);
      
      //student average age/grade text/button
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 4;
      layoutConst.gridy = 8;
      add(SAvgAge, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 5;
      layoutConst.gridy = 8;
      layoutConst.insets = new Insets(2,2,2,2);
      add(SAvgAgeField, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.fill = GridBagConstraints.HORIZONTAL;
      layoutConst.gridx = 4;
      layoutConst.gridy = 9;
      add(SAvgGrade, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(10, 10, 5, 5);
      layoutConst.gridx = 5;
      layoutConst.gridy = 9;
      add(SAvgGradeField, layoutConst);
      
      /////Student 
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(2,2,2,2);
      layoutConst.gridx = 0;
      layoutConst.gridy = 2;
      add(nameLabel, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 1;
      layoutConst.gridy = 2;
      layoutConst.insets = new Insets(2,2,2,2);
      add(nameField, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(2,2,2,2);
      layoutConst.gridx = 2;
      layoutConst.gridy = 2;
      add(ageLabel, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 3;
      layoutConst.gridy = 2;
      layoutConst.insets = new Insets(2,2,2,2);
      add(ageField, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(2,2,2,2);
      layoutConst.gridx = 4;
      layoutConst.gridy = 2;
      add(gpaLabel, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 5;
      layoutConst.gridy = 2;
      layoutConst.insets = new Insets(2,2,2,2);
      add(gpaField, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(2,2,2,2);
      layoutConst.gridx = 6;
      layoutConst.gridy = 2;
      add(gradeLabel, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 7;
      layoutConst.gridy = 2;
      layoutConst.insets = new Insets(2,2,2,2);
      add(gradeField, layoutConst);
      
      //////Teacher
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(2,2,2,2);
      layoutConst.fill = GridBagConstraints.LINE_END;
      layoutConst.gridx = 0;
      layoutConst.gridy = 5;
      add(tNameLabel, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 1;
      layoutConst.gridy = 5;
      layoutConst.insets = new Insets(2,2,2,2);
      add(tNameField, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(2,2,2,2);
      layoutConst.gridx = 2;
      layoutConst.gridy = 5;
      add(tAgeLabel, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 3;
      layoutConst.gridy = 5;
      layoutConst.insets = new Insets(2,2,2,2);
      add(tAgeField, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(2,2,2,2);
      layoutConst.gridx = 4;
      layoutConst.gridy = 5;
      add(tSalaryLabel, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 5;
      layoutConst.gridy = 5;
      layoutConst.insets = new Insets(2,2,2,2);
      add(tSalaryField, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.insets = new Insets(2,2,2,2);
      layoutConst.gridx = 6;
      layoutConst.gridy = 5;
      add(tSubLabel, layoutConst);
      
      layoutConst = new GridBagConstraints();
      layoutConst.gridx = 7;
      layoutConst.gridy = 5;
      layoutConst.insets = new Insets(2,2,2,2);
      add(tSubField, layoutConst);
      //initialize all teacher labels to be invisible for cleanliness
          addTeacher.setVisible(false);
          tNameLabel.setVisible(false);
          tNameField.setVisible(false);
          tAgeLabel.setVisible(false);
          tAgeField.setVisible(false);
          tSalaryLabel.setVisible(false);
          tSalaryField.setVisible(false);
          tSubLabel.setVisible(false);
          tSubField.setVisible(false);
          clearTeacher.setVisible(false);
          tAvgSalary.setVisible(false);
          tAvgSalaryField.setVisible(false);
          tAvgAge.setVisible(false);
          tAvgAgeField.setVisible(false);
          //initialize all student labels to be invisible for cleanliness
          addStudent.setVisible(false);
          nameLabel.setVisible(false);
          nameField.setVisible(false);
          ageLabel.setVisible(false);
          ageField.setVisible(false);
          gpaLabel.setVisible(false);
          gpaField.setVisible(false);
          gradeLabel.setVisible(false);
          gradeField.setVisible(false);
          clearStudent.setVisible(false);
          SAvgGrade.setVisible(false);
          SAvgGradeField.setVisible(false);
          SAvgAge.setVisible(false);
          SAvgAgeField.setVisible(false);
      
   }

   /* Called when openFileButton is pressed. */
   @Override
   public void actionPerformed(ActionEvent event) {
       //student button
       if(event.getSource()==student){
           playSound(boxing);
           //once student is click by the user, students actions and option will appear
          teacher.setVisible(false);
          addStudent.setVisible(true);
          nameLabel.setVisible(true);
          nameField.setVisible(true);
          ageLabel.setVisible(true);
          ageField.setVisible(true);
          gpaLabel.setVisible(true);
          gpaField.setVisible(true);
          gradeLabel.setVisible(true);
          gradeField.setVisible(true);
          clearStudent.setVisible(true);
          SAvgGrade.setVisible(true);
          SAvgGradeField.setVisible(true);
          SAvgAge.setVisible(true);
          SAvgAgeField.setVisible(true);
          
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
           playSound(boxing);
           //once teacher is click by the user, options for the student will disappear
          student.setVisible(false);
          addTeacher.setVisible(true);
          tNameLabel.setVisible(true);
          tNameField.setVisible(true);
          tAgeLabel.setVisible(true);
          tAgeField.setVisible(true);
          tSalaryLabel.setVisible(true);
          tSalaryField.setVisible(true);
          tSubLabel.setVisible(true);
          tSubField.setVisible(true);
          clearTeacher.setVisible(true);
          tAvgSalary.setVisible(true);
          tAvgSalaryField.setVisible(true);
          tAvgAge.setVisible(true);
          tAvgAgeField.setVisible(true);
          
          
          
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
           playSound(boxing);
           outputArea.setText(" ");
           if(stuMode==true){
               
               studentSearching(sData,target.getText());
               
           }
           if(teaMode==true){
               
               teacherSearching(tData,target.getText());
               
           }
           
       
       }
       if(event.getSource()==addStudent){
           // The name of the file to open.
                    String fileName = selectedFileField.getText();    
                    try {
                        playSound(applause);
                        // Assume default encoding.
                        FileWriter fileWriter =
                            new FileWriter(fileName, true);

                        // Always wrap FileWriter in BufferedWriter.
                        BufferedWriter bufferedWriter =
                            new BufferedWriter(fileWriter);

                        bufferedWriter.write(nameField.getText());
                        bufferedWriter.write(",");
                        bufferedWriter.write(ageField.getText());
                        bufferedWriter.write(",");
                        bufferedWriter.write(gpaField.getText());
                        bufferedWriter.write(",");
                        bufferedWriter.write(gradeField.getText());
                        bufferedWriter.write("\n");
                        // Always close files.
                        bufferedWriter.close();
                        outputArea.append("Added Student To "+ fileName+"\n");
                    }
                    catch(IOException ex) {
                        outputArea.append(
                            "Error writing to file '"
                            + fileName + "' Make sure your file is selected\n");
                    }
           
        }
        if(event.getSource()==clearStudent){
            playSound(blip); 
            nameField.setText("");
            ageField.setText("0");
            gpaField.setText("0");
            gradeField.setText("0");
            SAvgGradeField.setText("0");
            SAvgAgeField.setText("0");
        }
        if(event.getSource()==clearTeacher){
            playSound(blip); 
            tNameField.setText("");
            tAgeField.setText("0");
            tSalaryField.setText("0");
            tSubField.setText(" ");
            tAvgSalaryField.setText("0");
            tAvgAgeField.setText("0");
        }
        if(event.getSource()==clearMainBox){
            playSound(blip); 
            outputArea.setText(" ");
        }
       
       
       if(event.getSource()==addTeacher){
           // The name of the file to open.
                    String fileName = selectedFileField.getText();    
                    try {
                        playSound(applause);
                        // Assume default encoding.
                        FileWriter fileWriter =
                            new FileWriter(fileName, true);

                        // Always wrap FileWriter in BufferedWriter.
                        BufferedWriter bufferedWriter =
                            new BufferedWriter(fileWriter);

                        bufferedWriter.write(tNameField.getText());
                        bufferedWriter.write(",");
                        bufferedWriter.write(tAgeField.getText());
                        bufferedWriter.write(",");
                        bufferedWriter.write(tSalaryField.getText());
                        bufferedWriter.write(",");
                        bufferedWriter.write(tSubField.getText());
                        bufferedWriter.write("\n");
                        // Always close files.
                        bufferedWriter.close();
                        outputArea.append("Added Teacher To "+ fileName+"\n");
                    }
                    catch(IOException ex) {
                        outputArea.append(
                            "Error writing to file '"
                            + fileName + "' Make sure your file is selected\n");
                        
                    }
       }
       if(event.getSource()==tAvgAge){
           playSound(gong);
           tAvgAgeField.setText(Integer.toString(teacherAgeAverage(tData)));
        }
        if(event.getSource()==tAvgSalary){
            playSound(gong);
           tAvgSalaryField.setText(Double.toString(teacherSalAverage(tData)));
        }
        if(event.getSource()==SAvgAge){
            playSound(gong);
           SAvgAgeField.setText(Integer.toString(studentAgeAverage(sData)));
        }
        if(event.getSource()==SAvgGrade){
            playSound(gong);
           SAvgGradeField.setText(Integer.toString(studentGradeAverage(sData)));
        }
   }
   //sound method
   public static void playSound(File Sound) {
        
        try {
            //creating object
            Clip minute = AudioSystem.getClip();
            //read the sound file
            minute.open(AudioSystem.getAudioInputStream(Sound));
            minute.start();
            Thread.sleep(1000);
            minute.stop();
        }
        catch (Exception e ) 
        {
            
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
   public <TheType extends Comparable<TheType>> void studentSearching(ArrayList<studentData> data
   , TheType target){
       boolean found=false;
       String info;
       for(studentData i: data){
           if(target.compareTo((TheType)i.name)==0){
               info=("Name: "+i.name+" "+"Age: "+i.age.toString()+" "
               +"GPA: "+i.gpa.toString()+" "+"Grade: "+i.grade.toString());
               found=true;
               outputArea.append(info+"\n");
           }

           else if(target.compareTo((TheType)i.age.toString())==0){
               info=("Name: "+i.name+" "+"Age: "+i.age.toString()+" "
               +"GPA: "+i.gpa.toString()+" "+"Grade: "+i.grade.toString());
               found=true;
               outputArea.append(info+"\n");
           }
           else if(target.compareTo((TheType)i.gpa.toString())==0){
               info=("Name: "+i.name+" "+"Age: "+i.age.toString()+" "
               +"GPA: "+i.gpa.toString()+" "+"Grade: "+i.grade.toString());
               found=true;
               outputArea.append(info+"\n");
           }
           else if(target.compareTo((TheType)i.grade.toString())==0){
               info=("Name: "+i.name+" "+"Age: "+i.age.toString()+" "
               +"GPA: "+i.gpa.toString()+" "+"Grade: "+i.grade.toString());
               found=true;
               outputArea.append(info+"\n");
           }
       }
       if(found==false){
           outputArea.append("Not Found");
       }
   }
   public int teacherAgeAverage(ArrayList<teacherData> data){
        int average = 0;
        int age = 0;
        int count = 0;
        for (teacherData i: data){
            age += i.age;
            count ++;
        }
        average = age/count;
        return average;
    }
    public Double teacherSalAverage(ArrayList<teacherData> data){
        double average = 0.0;
        int salary = 0;
        int count = 0;
        for (teacherData i: data){
            salary += i.salary;
            count ++;
        }
        average = (double)salary/(double)count;
        return average;
    }
    public int studentAgeAverage(ArrayList<studentData> data){
        int average = 0;
        int age = 0;
        int count = 0;
        for (studentData i: data){
            age += i.age;
            count ++;
        }
        average = age/count;
        return average;
    }
    public int studentGradeAverage(ArrayList<studentData> data){
        int average = 0;
        int grade = 0;
        int count = 0;
        for (studentData i: data){
            grade += i.grade;
            count ++;
        }
        average = grade/count;
        return average;
    }
   public <TheType extends Comparable<TheType>> void teacherSearching(ArrayList<teacherData> data
   , TheType target){
       boolean found=false;
       String info;
       for(teacherData i: data){
           if(target.compareTo((TheType)i.name)==0){
               info=("Name: "+i.name+" "+"Age: "+i.age.toString()+" "
               +"Salary: "+i.salary.toString()+" "+"Subject: "+i.subject);
               found=true;
               outputArea.append(info+"\n");
           }

           if(target.compareTo((TheType)i.age.toString())==0){
                info=("Name: "+i.name+" "+"Age: "+i.age.toString()+" "
               +"Salary: "+i.salary.toString()+" "+"Subject: "+i.subject);
               found=true;
               outputArea.append(info+"\n");
           }
           if(target.compareTo((TheType)i.salary.toString())==0){
                info=("Name: "+i.name+" "+"Age: "+i.age.toString()+" "
               +"Salary: "+i.salary.toString()+" "+"Subject: "+i.subject);
               found=true;
               outputArea.append(info+"\n");
           }
           if(target.compareTo((TheType)i.subject)==0){
               info=("Name: "+i.name+" "+"Age: "+i.age.toString()+" "
               +"Salary: "+i.salary.toString()+" "+"Subject: "+i.subject);
               found=true;
               outputArea.append(info+"\n");
           }

       }
       if(found==false){
           outputArea.append("Not Found");
       }
   }
}