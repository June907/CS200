import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
/**
 * Write a description of class FinalProject here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FinalProject
{
    // instance variables - replace the example below with your own
    public static void main(String[] args) {
      // Creates FileReadFrame and its components
      FileReadFrame myFrame = new FileReadFrame();

      myFrame.setSize(800,800);
      
      myFrame.setLocationRelativeTo(null);
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      myFrame.setVisible(true);
   }

}
