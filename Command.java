import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class Command {
    public static void giveStudentInfo(int index){
        Main.outputList.add("");
        Main.outputList.add("### START OF STUDENT INFO ###");
        String wantedID = ((Main.commandList.get(index).toString().split(" ")[2]));
        Student student = Student.studentList.stream().filter(s -> s.id.equals(wantedID)).findAny().orElse(null);
        Main.outputList.add(student.name + " " + student.surname + " " + "(" + student.id + ")" + " " + "-"
                + " " + "Year:" + " " + student.year + "," + " " + "GPA:" + " " + student.gpa);
        Main.outputList.add("### END OF STUDENT INFO ###");
    }
    public static void giveCompanyInfo(int index){
        Main.outputList.add("");
        Main.outputList.add("### START OF COMPANY INFO ###");
        String wantedID = (Main.commandList.get(index).toString().split(" ")[2]);
        Company company = Company.companyList.stream().filter(s -> s.id.equals(wantedID)).findAny().orElse(null);
        Main.outputList.add(company.name +  " " + "(" +company.id + "):");
        Main.outputList.add("Min. Accepted GPA:" + " " + String.format(Locale.ENGLISH,"%.2f",Float.valueOf(company.minimumAcceptedGpa)));
        Main.outputList.add("Min. Accepted Year:" + " " + company.minimumAcceptedYear);
        Main.outputList.add("Applications:" + " " + company.applicationNumber + "/" + company.applicationQuota);
        Main.outputList.add("Admissions:" + " " + company.admissionNumber + "/" + company.admissionQuota);
        Main.outputList.add("### END OF COMPANY INFO ###");
    }
    public static void apply(int index){
        String companyID = Main.commandList.get(index).toString().split(" ")[2];
        String studentID = (Main.commandList.get(index).toString().split(" ")[4]);
        Company company = Company.companyList.stream().filter(c ->c.id.equals(companyID)).findAny().orElse(null);
        Student student = Student.studentList.stream().filter(s ->s.id.equals(studentID)).findAny().orElse(null);
        if (student.getNumberOfApplication()<5 && company.applicationQuota>company.appliedStudents.size() &&
                student.year>=company.minimumAcceptedYear && Float.valueOf(company.minimumAcceptedGpa)<=Float.valueOf(student.gpa)){
            company.appliedStudents.add(student);
            student.appliedCompanyList.add(company);
            company.applicationNumber+=1;
            Main.outputList.add("Application from" + " " + student.name + " " + student.surname + " "
                    + "(" + student.id + ")" + " " + "to" + " " + company.name + " " +
                    "(" + company.id + ")" + " " + "was registered successfully.");
            student.increaseApplicationNumber();
        }else {
            Main.outputList.add("Application from" + " " + student.name + " " + student.surname + " "
                    + "(" + student.id + ")" + " " + "to" + " " + company.name + " " +
                    "(" + company.id + ")" + " " + "was failed." );
        }
    }
    public static void admit(int index){
        String studentID = (Main.commandList.get(index).toString().split(" ")[1]);
        String companyID = Main.commandList.get(index).toString().split(" ")[3];
        Company company = Company.companyList.stream().filter(c ->c.id.equals(companyID)).findAny().orElse(null);
        Student student = Student.studentList.stream().filter(s ->s.id.equals(studentID)).findAny().orElse(null);
        if (company.appliedStudents.contains(student)&&student.isAdmitted==false&&company.admissionQuota>=company.admittedStudents.size()) {
            company.admittedStudents.add(student);
            student.addmitedCompanyList.add(company);
            student.isAdmitted=true;
            company.admissionNumber+=1;
            Main.outputList.add(company.name + " " + "(" + company.id + ")" + " " + "admitted" + " " + student.name + " " +
                    student.surname + " " + "(" + student.id + ")." );
            student.isAdmitted = true;
        }else {
            Main.outputList.add(company.name + " " + "(" + company.id + ")" + " " + "COULD NOT admit" + " " + student.name + " " +
                    student.surname + " " + "(" + student.id + ").");
        }
    }
    public static void companyApplicationsLister(int index){
        Main.outputList.add("");
        String companyID = Main.commandList.get(index).toString().split(" ")[3];
        Company company = Company.companyList.stream().filter(c ->c.id.equals(companyID)).findAny().orElse(null);
        Main.outputList.add("### START OF COMPANY APPLICATIONS ###");
        Main.outputList.add("Admitted Students:");
        Collections.sort(company.appliedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (Float.valueOf(o1.gpa)>Float.valueOf(o2.gpa)){
                    return -1;
                } else if (Float.valueOf(o2.gpa)>Float.valueOf(o1.gpa)) {
                    return 1;
                }else {
                    if (o1.year>o2.year){
                        return -1;
                    } else if (o2.year>o1.year) {
                        return 1;
                    }else {
                        return 0;
                    }
                }
            }
        });
        if (company.admittedStudents.size()==0){
            Main.outputList.add("None");
        }else {
            for (Student student:company.admittedStudents){
                Main.outputList.add(student.name + " " + student.surname + " " + "(" + student.id + ")" + " "
                        + "-" + " " + "Year:" + " " + student.year + "," + " " + "GPA:" + " " +student.gpa);
            }
        }
        Main.outputList.add("");
        Main.outputList.add("Applied Students:");
        if (company.appliedStudents.size()==0){
            Main.outputList.add("None");
        }else {

            for (Student student:company.appliedStudents){
                Main.outputList.add(student.name + " " + student.surname + " " + "(" + student.id + ")" + " "
                        + "-" + " " + "Year:" + " " +student.year + "," + " " + "GPA:" + " " +student.gpa);
            }
        }
        Main.outputList.add("### END OF COMPANY APPLICATIONS ###");
    }
    public static void studentApplicationLister(int index){
        Main.outputList.add("");
        String studentID = (Main.commandList.get(index).toString().split(" ")[3]);
        Student student = Student.studentList.stream().filter(s ->s.id.equals(studentID)).findAny().orElse(null);
        Main.outputList.add("### START OF STUDENT APPLICATIONS ###");
        Main.outputList.add("Admitted Company:");
        if (student.addmitedCompanyList.size()==0){
            Main.outputList.add("None");
        }else {
            Main.outputList.add(((Company) student.addmitedCompanyList.get(0)).name + " " + "(" +((Company) student.addmitedCompanyList.get(0)).id+ ")");
        }
        Main.outputList.add("");
        Main.outputList.add("Applied Companies:");
        if (student.appliedCompanyList.size()==0){
            Main.outputList.add("None");
        }else {
            for (int i = 0; i<student.appliedCompanyList.size();i++) {
                Main.outputList.add(((Company) student.appliedCompanyList.get(i)).name + " " + "(" +((Company) student.appliedCompanyList.get(i)).id + ")");
            }
        }
        Main.outputList.add("### END OF STUDENT APPLICATIONS ###");
    }
    public static void writeToOutputFile(){
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(Main.outputFilepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i =0;i<Main.outputList.size();i++) {
            try {
                bufferedWriter.write(String.valueOf(Main.outputList.get(i)));
                bufferedWriter.write("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
