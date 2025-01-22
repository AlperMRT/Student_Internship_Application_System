This is an OOP project. In this project, I programmed an internship application system. This system allows software companies to declareinternship postings where they can define how many interns are needed, and at least which minimum GPA and
year are required to apply to their internship program. Also, students can apply to current internship postings
if their qualifications are matching with that posting’s minimal requirements. Students are represented with
their student IDs, names, surnames, GPAs and their years.

txt files include student information, company information and commands. There are total 4 main operations: Apply, Admit, Info and List:

APPLY TO <COMPANY ID> BY <STUDENT ID>
When you take this command, you should register an application from a student with <STUDENT ID> to the
company with <COMPANY ID>. Before registering, you should check these conditions:
1. The number of current applicants to that company are less than application quota.
2. The applicant student should not have reached 5 applications limit.
3. The GPA of student should be at least the company’s min accepted gpa.
4. The student’s current year in university should be at least the company’s min accepted year.
• If these conditions hold, register the student’s application to the company’s application list. Then,
write to the output.txt file the following line: Application from <STUDENT NAME <STUDENT SURNAME>
(<STUDENT ID>) to <COMPANY NAME> (<COMPANY ID>) was registered successfully. For example if the command was APPLY TO C179 BY 22054860 and if the application was successful, output line
should be Application from Metin Doruk (22054860) to Evantage Software (C179) was registered
successfully..
• If these conditions do not hold and application is unsuccessful, then write to the output.txt
file the following line: Application from <STUDENT NAME> <STUDENT SURNAME> (<STUDENT ID>) to
<COMPANY NAME> (<COMPANY ID>) was failed. For example if the command was APPLY TO C890 BY
22054860, then the output line should be Application from Metin Doruk (22054860) to Macro
Mobile Solution (C890) was failed.

ADMIT <STUDENT ID> TO <COMPANY ID>
For this command, you should admit a previously registered application from a student with <STUDENT ID>
to the company with <COMPANY ID>. That is, a company cannot admit a student unless he/she is registered
in application list of the company. Also, each student may apply up to 5 companies, but only one company
can admit him/her. After a student is admitted by a company, other companies cannot perform admission
command for that student anymore. Before admitting, you should check these conditions:
1. The student should exist in that company’s applications list. A student who is not registered in the
company’s applications cannot be admitted.
2. The number of admitted students at that company should not have reached the company’s admission quota.
3. The student to be admitted should not have been admitted by another company previously. That is,
each student can only be admitted by a single company.
• If these conditions hold, add the student to the company’s admission list. Then, write to the
output.txt file the following line: <COMPANY NAME> (<COMPANY ID>) admitted <STUDENT NAME> <STUDENT SURNAME>
(<STUDENT ID>). For example if the command was ADMIT 22023721 TO C179 and if the admission
was successful, output line should be Evantage Software (C179) admitted Abdulkadir Babaoglu
(22023721).
• If these conditions do not hold and application is unsuccessful, then write to the output.txt
file the following line: <COMPANY NAME> (<COMPANY ID>) COULD NOT admit <STUDENT NAME> <STUDENT SURNAME>
(<STUDENT ID>). For example if the command was ADMIT 22023721 TO C179 and if the admission
was unsuccessful, output line should be Evantage Software (C179) COULD NOT admit Abdulkadir
Babaoglu (22023721).

INFO COMPANY <COMPANY ID>
This command writes to output.txt file the basic information of the company

LIST APPLICATIONS TO <COMPANY ID>
This command writes to output.txt file the applications made to the company with <COMPANY ID> and also
admissions.

INFO STUDENT <STUDENT ID>
This command writes to output.txt file the basic information of the student with <STUDENT ID>

LIST APPLICATIONS BY <STUDENT ID>
This command writes to output.txt file the applications made by the student with <STUDENT ID> 

Constraints
1. A student can apply up to 5 companies at maximum.
2. A company could receive application quota number of applications at maximum.
3. A company could admit admission quota number of applications at most.
4. A company cannot receive an application from a student whose GPA is lower than the company’s
min accepted gpa.
5. A company cannot receive an application from a student whose year is lower than the company’s
min accepted year.
