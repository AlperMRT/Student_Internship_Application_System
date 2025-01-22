import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Student {
    // implement Student class
    public static ArrayList<Student> studentList = new ArrayList<>();
    public ArrayList appliedCompanyList = new ArrayList();
    public ArrayList addmitedCompanyList = new ArrayList();


    String id;
    String name;
    String surname;
    int year;
    String gpa;
    boolean isAdmitted = false;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    private int numberOfApplication = 0;
    public int getNumberOfApplication(){
        return numberOfApplication;
    }

    public static void studentFileReader(String file){
        try {


            for (int i = 1; i <Files.lines(Paths.get(file)).count(); i++){
                Student student = new Student();
                student.id = (Files.readAllLines(Paths.get(file)).get(i).split("\t")[0]);

                student.name = Files.readAllLines(Paths.get(file)).get(i).split("\t")[1];
                student.surname = Files.readAllLines(Paths.get(file)).get(i).split("\t")[2];
                student.year = Integer.parseInt(Files.readAllLines(Paths.get(file)).get(i).split("\t")[3]);
                student.gpa = String.format((Files.readAllLines(Paths.get(file)).get(i).split("\t")[4]));


                studentList.add(student);
                }
            }catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        public void increaseApplicationNumber(){
            if (numberOfApplication<5){
                numberOfApplication+=1;
            }else{
                notify();
            }
        }

    }

