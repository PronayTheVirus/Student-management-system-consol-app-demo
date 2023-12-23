import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class projectfileone {
    public static void main(String[] args) {
        login();
    }

    static void isAdmin(){
        ArrayList<Integer> studentid = new ArrayList<>();
        HashMap<Integer, String> studentName = new HashMap<>();
        HashMap<Integer, String> program = new HashMap<>();
        HashMap<Integer, String> password = new HashMap<>();
        HashMap<Integer, Integer> batch = new HashMap<>();
        HashMap<Integer, Double> cgpa = new HashMap<>();
        HashMap<Integer, String> courses = new HashMap<>();


        while(true)
        {
            System.out.println("\n\nCHOOSE YOUR OPTIONS FROM BELOW\n_______________________________________________");
            System.out.println("| 1. Add student \n" +
                    "| 2. Search for a student by his ID \n" +
                    "| 3. To assign student a course " +
                    "\n| 4. view information of all students\n" +
                    "| 5. See assigned coursed by ID\n" +
                    "| 6. Save changes\n" +
                    "| 7. exit \n" +
                    "| 8. exit");
            System.out.println("_______________________________________________");
            Scanner sc = new Scanner(System.in);
            int c = sc.nextInt();
            if(c==6){
                saveIntoFile(studentid,studentName,program,password,batch,cgpa,courses);
            }
            if( c ==7){
                break;
            }
            if(c==3){
                //assign courses to students by their id
                System.out.println("Enter student id to assign them courses");
                int idForCourses = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter courses ( seperate them using , ");
                String courses2 = sc.nextLine();

                courses.put(idForCourses,courses2);
            }
//        student ID,
//    name, program, batch, password and CGPA.
//        int idArr[] = new int[1001];
//        String nameArr[] = new String[1001];

            // to search for a student by his id
            if (c == 2) {
                System.out.println("Enter studentID to search for the student");
                int searchbyid = sc.nextInt();

                if (studentid.contains(searchbyid)) {

                    System.out.println("Search results");
                    System.out.print(searchbyid);
                    System.out.print(",");
                    System.out.print(studentName.get(searchbyid));
                    System.out.print(",");
                    System.out.print(program.get(searchbyid));
                    System.out.print(",");
                    System.out.print(batch.get(searchbyid));
                    System.out.print(",");
                    System.out.print(cgpa.get(searchbyid));
                    System.out.print(",");
                    System.out.println(courses.get(searchbyid));

                    // field for assigned courses
                }

                else {
                    System.out.println("No details found");
                }
            }
            if( c== 4){
                // To view the information of all students
                int length = studentid.toArray().length;

                for (int i = 0; i < length; i++) {
                    int stid = studentid.get(i);
                    System.out.println("ID: " + stid + ", Name: " + studentName.get(stid) + ", Program: " + program.get(stid) + ", Batch" +
                            " : " + batch.get(stid) + ", CGPA: "+ cgpa.get(stid) + ", Courses: " + courses.get(stid) + "\n");
                }

            }

            if (c == 1) { // to add student
                System.out.println("Enter student ID");
                Integer getid = sc.nextInt();

                sc.nextLine(); // to consume the nextLine error!

                System.out.println("Enter student name");
                String names = sc.nextLine();
                studentid.add(getid);
                studentName.put(getid, names);

                System.out.println("Enter program name");
                String p = sc.nextLine();
                program.put(getid, p);

                System.out.println("Enter password");
                String pass = sc.next();
                password.put(getid, pass);

                System.out.println("Enter batch");
                Integer b = sc.nextInt();
                batch.put(getid, b);

                System.out.println("Enter cgpa of the student");
                double gpa = sc.nextDouble();
                cgpa.put(getid, gpa);

                System.out.println("Student Info added successfully");
            }
            if(c==5){
                System.out.println("Enter student ID: ");
                int idscan = sc.nextInt();

                System.out.println("Assigned courses are " + courses.get(idscan));
            }
        }
    }
    static void saveIntoFile(ArrayList<Integer> idVaulte, HashMap<Integer, String> studentName, HashMap<Integer, String> program,  HashMap<Integer, String> password,  HashMap<Integer, Integer> batch, HashMap<Integer, Double> cgpa, HashMap<Integer, String> courses
    ){
        int arrlength = idVaulte.toArray().length;
        System.out.println(arrlength);
        System.out.println("reached save to file method");
        try {
            FileWriter writter = new FileWriter("studentdata.txt");

            for (int i = 0; i < arrlength; i++) {
                int id = idVaulte.get(i);
                writter.write(id + "/" + studentName.get(id) + "/" + program.get(id) + "/" + password.get(id)  + "/" + batch.get(id) + "/" + cgpa.get(id) + "(" + courses.get(id)+ ")"+ "\n");
            }
            writter.close();
            System.out.println("Saved successfully");
        }
        catch (IOException e){
            System.out.println("An error occured");
        }
    }
    static void login() {
        Scanner sc = new Scanner(System.in) ;
        System.out.println("Enter your USER ID (can't contain space) : ID is 'test' pass is 'admin' ");
        String name = sc.next();
        System.out.println("Enter your password");
        String password = sc.next();

        namePassExtract(name,password);
    }

    static boolean adminInfoCheck(String name,String password, int namelength, int passlength, char[] extractName, char[] extractPass){
          boolean nameVerified = false;
          boolean passverify = false;

          int temppasslength = passlength-1;
          if(namelength != name.length() || temppasslength != password.length()){
              return false;
          }
          int verifyValue =0;
          // name verification
        //comparing each and every character with each other
        for (int i = 0; i < namelength; i++) {
            if(name.charAt(i) == extractName[i]){
                verifyValue++;
            }
        }
        if(verifyValue == namelength){
            nameVerified = true;
        }

        // password verification
        // going to check with every single charater
        //using charAt and the for loop
        verifyValue = 0;
        for (int i = 0; i < temppasslength; i++) {
            if(password.charAt(i) == extractPass[i+1]){
                verifyValue++;
            }
        }
        if((verifyValue) == temppasslength){
            passverify = true;
        }
        // In this line of code we are going to check
        // if the both the username and the password is correct
        if( nameVerified && passverify){
            return true;
        }
        else
            return false;
    }

    static void namePassExtract(String name,String password){

        char extractName[] = new char[30];
        char extractPass[] = new char[30];

        boolean donewithname = false;

        int userlength = 0;
        int passLength = 0;

        try {
            FileReader reader = new FileReader("admin.txt");
            int value = reader.read(), i= 0;
            while (value != -1) {

                // to check for space, which is going to seperate the username and the password
                if ((char)value == ' ') {
                    donewithname = true;
                    i = 0;
                }
                else if(!donewithname){
                    extractName[i] = (char) value;
                    userlength = i;
                }
                else{
                    extractPass[i] = (char) value;
                    passLength = i;
                }
                value = reader.read();
                i++;

            }
            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found exception found");
        } catch (IOException e) {
            System.out.println("Runtime Error!");
            throw new RuntimeException(e);
        }
        userlength++;

        if(!adminInfoCheck(name,password,userlength,passLength,extractName,extractPass)){
            System.out.println("Didn't match, try again");
            login();
        }
        else{
            System.out.println("Welcome, you can use the admin commands now");
            isAdmin();
        }
    }
}
