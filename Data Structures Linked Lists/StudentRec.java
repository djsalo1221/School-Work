//  StudentRec.java

/**
 *  This class defines a student record class.
 *  Each student record contains a name filed and a grade field.
 *
 *  The class contains the following methods:
 *
 *   public StudentRec(String someone, String degree)
 *        constructor
 *
 *   public StudentRec(String someone, String degree, float grade)
 *        constructor
 *
 *   public StudentRec(StudentRec anotherStudent)
 *        copy constructor
 *
 *   public String toString()
 *        Displays the object in proper format.
 *        It is called by print or println methods.
 *
 *   public void changeGrade(int score)
 *        Used to change the grade of student record.
 *
 *   public String getName()
 *        Returns the name of the student record
 *
 *   public String getMajor()
 *        Returns the major of the student record
 *
 *   public int getGPA()
 *        Returns the gpa of the student record
 *
 *   public void changeMajor()
 *        Changes major of the student record.
 *
 *   public void changeGPA()
 *        Changes gpa of the student record.
 *
 *   public boolean equals(Object anotherStudent)
 *        Determines whether another student object has the
 *        same contents as this student.
 *
 *   public Object clone()
 *        Creates a deep copy (with the same contents, but in
 *        different memory location) of the current object.
 *
 */




public class StudentRec
{
     private String  name;  //  Name will be stored in the format
                            //  Last-name, First-Name Middle-initial
                            //  such as:     Busha, Michael M
     private String  major;
     private float   gpa;



     public StudentRec(String someone, String degree)
     {
          name = someone;
          major = degree;
          gpa = 0;
     }



     public StudentRec(String someone, String degree, float grade)
     {
          name = someone;
          major = degree;
          gpa = grade;
     }



     /**
      *   copy constructor
      */

     public StudentRec(StudentRec anotherStudent)
     {
          name = new String(anotherStudent.name);
          major = new String(anotherStudent.major);
          gpa = anotherStudent.gpa;
     }



     /**
      *   This method overrides Object's toString method.  It
      *   is invoked by all print methods when printing a
      *   StudentRec object.
      */

     public String toString()
     {
          String  outputFormat;
          int     len1 = name.length();
          int     len2 = major.length();

          outputFormat = new String(name);
          
          for (int i = len1; i < 20; ++i)
               outputFormat += ' ';      // pad with trailing blanks

          outputFormat += major;
          for (int i = len2; i < 4; ++i)
               outputFormat += ' ';      // pad with trailing blanks

          outputFormat += "    " + gpa;
 
          return outputFormat;

     }


    
     /**
      *   This method returns name of the student record.
      */

     public String getName()
     {
          return name;
     }


     /**
      *   This method returns major of the student record.
      */

     public String getMajor()
     {
          return major;
     }



     /**
      *   This method returns the grade of the student record.
      */

     public float getGPA()
     {
          return gpa;
     }


     /**
      *   This method changes the major of the student record.
      */

     public void changeMajor(String newMajor)
     {
          major = newMajor;
     }


     /**
      *   This method changes gpa of the student record.
      */

     public void changeGPA(float newGrade)
     {
          gpa = newGrade;
     }

     /**
      *   This method overrides Object's equals method.  It
      *   determines whether another student object has the
      *   same contents as this student.
      */

     public boolean equals(Object anotherStudent)
     {
          StudentRec pupil = (StudentRec) anotherStudent;

          return ((name.equals(pupil.name))
                    && (major.equals(pupil.major))
                    && (gpa == pupil.gpa));
     }



     /**
      *   This method overrides Object's Clone method.  It
      *   creates a deep copy (with the same contents, but in
      *   different memory location) of the current object.
      */

     public Object clone()
     {
          StudentRec copy = new StudentRec(this);

          return copy;
     }


}
