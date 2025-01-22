import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Main {
    static String companiesFilepath;
    static String studentsFilepath;
    static String commandsFilepath;
    static String outputFilepath;
    public static ArrayList commandList = new ArrayList<>();
    public static ArrayList outputList = new ArrayList();

    public static void main(String[] args) {
        //String currentWorkingDir = System.getProperty("user.dir");

        companiesFilepath = args[0]; // companies.txt filepath
        studentsFilepath = args[1]; // students.txt filepath
        commandsFilepath = args[2]; // commands.txt filepath
        outputFilepath =  args[3]; // output.txt filepath

        // TODO: Read input files and generate output file according to the commands

        Student.studentFileReader(studentsFilepath);
        Company.companyFileReader(companiesFilepath);
        try {
            for (int i = 0; i < Files.lines(Paths.get(commandsFilepath)).count(); i++){
                commandList.add(Files.readAllLines(Paths.get(commandsFilepath)).get(i));
            }
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int j = 0; j<commandList.size(); j++){
            if (commandList.get(j).toString().startsWith("INFO STUDENT")){
                Command.giveStudentInfo(j);
                continue;

            } else if(commandList.get(j).toString().startsWith("INFO COMPANY")) {
                Command.giveCompanyInfo(j);
                continue;

            } else if (commandList.get(j).toString().startsWith("APPLY TO")) {
                Command.apply(j);
                continue;

            } else if (commandList.get(j).toString().startsWith("ADMIT")) {
                Command.admit(j);
                continue;

            } else if (commandList.get(j).toString().startsWith("LIST APPLICATIONS TO")) {
                Command.companyApplicationsLister(j);
                continue;
            
            } else if (commandList.get(j).toString().startsWith("LIST APPLICATIONS BY")) {
                Command.studentApplicationLister(j);
                continue;
            }
        }

        Command.writeToOutputFile();
        }
    }
