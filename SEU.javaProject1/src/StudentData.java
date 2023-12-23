public class StudentData extends Main {
//    Once successfully authenticated, the officer gains access to functionalities such as adding
//    and viewing student information. The officer can input details like student ID,
//    name, program, batch, password and CGPA. Additionally, the officer
//    can search for student information using their ID and view all details except the password.
    public long id;
    public String name;
    public String program;
    public String password;
    public String cgpa;

    public StudentData() {
    }
    public StudentData(long id, String name, String program, String password, String cgpa) {
        this.id = id;
        this.name = name;
        this.program = program;
        this.password = password;
        this.cgpa = cgpa;
    }

//    public long setName(name){
//        this.name = name;
//    }

}
