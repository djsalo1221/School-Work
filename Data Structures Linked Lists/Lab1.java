/**
 *   Lab1.java
 */


import java.io.*;
import java.util.*;

/**
 *  This program tests your completed SortedStudentList class.
 */

public class Lab1
{

     /**
      *   Program execution starts from this main program
      */

     public static void main(String[] args)
     {
          Scanner            keyboard = new Scanner(System.in);
          String             fileName;
          SortedStudentList  class1 = new SortedStudentList();
          SortedStudentList  class2 = new SortedStudentList();
          SortedStudentList  class3;
          StudentRec         aStudent;
          int                n;
          float              max;

          if (args.length == 1)
               fileName = args[0];
          else
          {
               System.out.print("Please enter the data file name:  ");
               fileName = keyboard.next();
          }

          readData(fileName, class1, class2);

          if (class1.equals(class2))
               System.out.println("\n\nClass lists 1 and 2 have "
                                  + "the same contents");
          else
               System.out.println("Class lists 1 and 2 do NOT "
                                  + "have the same contents");

          if (class1 == class2)
               System.out.println("Class lists 1 and 2 have "
                                  + "the same address");
          else
               System.out.println("Class lists 1 and 2 do NOT "
                                  + "have the same address\n\n");

          class3 = (SortedStudentList) class1.clone();

          if (class1.equals(class3))
               System.out.println("Class lists 1 and 3 have "
                                  + "the same contents");
          else
               System.out.println("Class lists 1 and 3 do NOT "
                                  + "have the same contents");

          if (class1 == class3)
               System.out.println("Class lists 1 and 3 have "
                                  + "the same address");
          else
               System.out.println("Class lists 1 and 3 do NOT "
                                  + "have the same address\n\n");

          System.out.println("This is the original class list 1:\n");
          class1.print();

          System.out.println("\n\nThis is the class list 3:\n");
          class3.print();

          n = class1.csMajors();
          System.out.println("\n\nThere are " + n + " CS majors in the class list 1.\n");

          try
          {
               max = class1.highestGPA();
               System.out.println("\n\nThe highest GPA in list 1 is " + max + "\n");
          }
          catch (Exception e)
          {
               System.out.println("\n\nThe list is empty, cannot find highest GPA.\n");
          }

          try
          {
               aStudent = class1.findFirst();
               System.out.println("\n\nThe first student in list 1 is " + aStudent + "\n");
          }
          catch (Exception e)
          {
               System.out.println("\n\nThe list is empty, it does not have the first element.\n");
          }

          try
          {
               aStudent = class1.findSecond();
               System.out.println("\n\nThe second student in list 1 is " + aStudent + "\n");
          }
          catch (Exception e)
          {
               System.out.println("\n\nThe list does not have second element.\n");
          }

          try
          {
               aStudent = class1.findLast();
               System.out.println("\n\nThe last student in list 1 is " + aStudent + "\n");
          }
          catch (Exception e)
          {
               System.out.println("\n\nThe list is empty, it does not have the last element.\n");
          }

          try
          {    aStudent = class1.findSecondLast();
               System.out.println("\n\nThe second last student in list 1 is " + aStudent + "\n");
          }
          catch (Exception e)
          {
               System.out.println("\n\nThe list does not have second last element.\n");
          }

          try
          {
               class1.deleteFirst();
               class1.deleteSecond();
               class1.deleteLast();
               class1.deleteSecondLast();
          }
          catch (Exception e)
          {
               System.out.println("\n\nError occurred during the deletion.\n");
          }

          System.out.println("This is the list 1 after deletions:\n");
          class1.print();

          class3.removeDismissed();
          System.out.println("\n\nThis is the list 3 after all students with GPA < 2.0 deleted\n");
          class3.print();

     }



     /**
      *   This method reads data from data file, and store the
      *   records in two SortedStudentList objects.
      *   @param fileName The input data file.
      *   @param list1 Sorted linked list read form the data file
      *   @param list2 Another sorted list of the same data file
      */

     static void readData(String fileName, SortedStudentList list1,
                                           SortedStudentList list2)
     {
          Scanner       inputFile;
          String        firstName, lastName, name, major;
          char          middleInitial;
          float         gpa;
          StudentRec    aStudent;

          try
          {
               inputFile = new Scanner(new File(fileName));

               while (inputFile.hasNext())
               {
                    firstName = inputFile.next();
                    middleInitial = inputFile.next().charAt(0);
                    lastName = inputFile.next();
                    name = lastName.substring(0,1).toUpperCase()
                           + lastName.substring(1).toLowerCase()
                           + ", "
                           + firstName.substring(0,1).toUpperCase()
                           + firstName.substring(1).toLowerCase()
                           + ' ' + middleInitial;

                    major = inputFile.next().toUpperCase();
                    gpa = inputFile.nextFloat();
                    aStudent = new StudentRec(name, major, gpa);

                    list1.insert(aStudent);
                    list2.insert(aStudent);
               }

               inputFile.close();
          }
          catch (FileNotFoundException e)
                                 // Be sure to put most specific
                                 // exception first, then followed by
                                 // more general exception.  (Why?)
          {
               System.out.print("File \"" + fileName
                                         +"\" is not found\n\n");
               System.exit(1);
          }
          catch (Exception e)
		  {
               System.out.print("Abort:  Some error occurred!\n\n");
               System.exit(1);
          }

     }


}
