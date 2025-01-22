import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Company {
    String id;
    String name;
    int admissionQuota;
    int applicationQuota;
    int minimumAcceptedYear;
    String minimumAcceptedGpa;
    int applicationNumber;
    int admissionNumber;
    public static ArrayList<Company> companyList = new ArrayList<>();
    public ArrayList<Student> appliedStudents = new ArrayList<>();
    public  ArrayList<Student> admittedStudents = new ArrayList<>();
    // implement Company class
    public static void companyFileReader(String file){

        try {
            for (int i = 1; i < Files.lines(Paths.get(file)).count(); i++){
                Company company = new Company();
                company.id = (Files.readAllLines(Paths.get(file)).get(i).split("\t")[0]);
                company.name = Files.readAllLines(Paths.get(file)).get(i).split("\t")[1];
                company.admissionQuota = Integer.parseInt(Files.readAllLines(Paths.get(file)).get(i).split("\t")[2]);
                company.applicationQuota = Integer.parseInt(Files.readAllLines(Paths.get(file)).get(i).split("\t")[3]);
                company.minimumAcceptedYear = Integer.parseInt(Files.readAllLines(Paths.get(file)).get(i).split("\t")[4]);
                company.minimumAcceptedGpa = String.format(Files.readAllLines(Paths.get(file)).get(i).split("\t")[5]);
                //company.minimumAcceptedGpa = String.format("%.2f",Float.parseFloat(company.minimumAcceptedGpa));

                companyList.add(company);
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
}






