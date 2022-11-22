import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

class IdException extends Exception {

    public IdException(String error) {

        super(error);

    }

}

class IdChecker {

    public static boolean isValidId(String id) {

        if (id.length() != 6) {

            return false;

        }

        else if (Character.isLetter(id.charAt(0)) && Character.isLetter(id.charAt(1)) && Character.isDigit(id.charAt(2))
                && Character.isDigit(id.charAt(3)) && Character.isDigit(id.charAt(4)) && Character.isDigit(id.charAt(5))) {

            return true;

        }

        return false;

    }

}

public class Main {

    public static void main(String[] args) {

        final int numPeople = 100;

        Scanner scan = new Scanner(System.in);

        String response;
        String fullName;
        String id;
        String department;
        String rank;
        String status;
        Person people;
        double gpa;
        int creditHours;
        Personnel list = new Personnel();

        System.out.println("Choose one of the options:\n");

        while(true) {

            System.out.println("""
                    1 - Enter the information a faculty
                    2 - Enter the information of a student
                    3 - Print tuition invoice for a student
                    4 - Print faculty information
                    5 - Enter the information of a staff member
                    6 - Print the information of a staff member
                    7 - Exit Program
                    """);

            System.out.print("Enter your selection: ");
            response = scan.nextLine();
            System.out.print("\n");

            if (!(response.matches("[1234567]"))) {

                System.out.println("Invalid entry - Please try again\n");
                continue;

            }

            switch (response) {

                case "1":

                    System.out.println("Enter the faculty info:");
                    System.out.print("\tName of the faculty member: ");
                    fullName = scan.nextLine();

                    while (true) {

                        System.out.print("\tID: ");

                        try {

                            id = scan.nextLine();
                            id = id.toLowerCase();

                            if (!IdChecker.isValidId(id)) {

                                throw new IdException("\t\tInvalid ID format: Must be LetterLetterDigitDigitDigitDigit");

                            }

                            else if (list.searchID(id) != null) {

                                throw new IdException("\t\tInvalid ID: ID already exists");

                            }

                        } catch (IdException error) {

                            System.out.println(error.getMessage());
                            continue;

                        }

                        break;

                    }

                    while (true) {

                        System.out.print("\tRank: ");
                        rank = scan.nextLine();

                        if (!(rank.equalsIgnoreCase("Professor") || rank.equalsIgnoreCase("Adjunct"))) {

                            System.out.println("\t\t\"" + rank + "\" is invalid");
                            continue;

                        }

                        rank = rank.toLowerCase();
                        rank = rank.substring(0, 1).toUpperCase() + rank.substring(1);
                        break;

                    }

                    while (true) {

                        System.out.print("\tDepartment: ");
                        department = scan.nextLine();

                        if (!(department.equalsIgnoreCase("Mathematics") || department.equalsIgnoreCase("Engineering") || department.equalsIgnoreCase("Sciences"))) {

                            System.out.println("\t\t\"" + department + "\" is invalid");
                            continue;

                        }

                        department = department.toLowerCase();
                        department = department.substring(0, 1).toUpperCase() + department.substring(1);
                        break;

                    }

                    System.out.println("\nFaculty member added!\n");

                    people = new Faculty(fullName, id, department, rank);
                    list.addPerson(people);
                    break;

                case "2":

                    System.out.println("Enter the student info:");
                    System.out.print("\tName of the student: ");
                    fullName = scan.nextLine();

                    while (true) {

                        System.out.print("\tID: ");

                        try {

                            id = scan.nextLine();
                            id = id.toLowerCase();

                            if (!IdChecker.isValidId(id)) {

                                throw new IdException("\t\tInvalid ID format: Must be LetterLetterDigitDigitDigitDigit");

                            }

                            else if (list.searchID(id) != null) {

                                throw new IdException("\t\tInvalid ID: ID already exists");

                            }

                            break;

                        } catch (IdException error) {

                            System.out.println(error.getMessage());

                        }

                    }

                    while (true) {

                        System.out.print("\tGPA: ");

                        try {

                            gpa = Double.parseDouble(scan.nextLine());
                            break;

                        } catch (Exception error) {

                            System.out.println("\t\tNot a double, try again!");

                        }

                    }

                    while (true) {

                        System.out.print("\tCredit hours: ");

                        try {

                            creditHours = Integer.parseInt(scan.nextLine());
                            break;

                        } catch (Exception error) {

                            System.out.println("\t\tNot an integer, try again!");

                        }

                    }

                    System.out.println("\nStudent added!\n");

                    people = new Student(fullName, id, gpa, creditHours);
                    list.addPerson(people);
                    break;

                case "3":

                    while (true) {

                        System.out.print("\tEnter the student's ID: ");

                        try {

                            id = scan.nextLine();
                            id = id.toLowerCase();

                            if (!IdChecker.isValidId(id)) {

                                throw new IdException("\t\tInvalid ID format: Must be LetterLetterDigitDigitDigitDigit");

                            }

                            break;

                        } catch (IdException error) {

                            System.out.println(error.getMessage());

                        }

                    }

                    System.out.print("\n");

                    people = list.searchID(id);

                    if (!(people instanceof Student)) {

                        System.out.println("No student matched!\n");
                        break;

                    }

                    people.print();
                    break;

                case "4":

                    while (true) {

                        System.out.print("\tEnter the faculty's ID: ");

                        try {

                            id = scan.nextLine();
                            id = id.toLowerCase();

                            if (!IdChecker.isValidId(id)) {

                                throw new IdException("\t\tInvalid ID format: Must be LetterLetterDigitDigitDigitDigit");

                            }

                            break;

                        } catch (IdException error) {

                            System.out.println(error.getMessage());

                        }

                    }

                    System.out.print("\n");

                    people = list.searchID(id);

                    if (!(people instanceof Faculty)) {

                        System.out.println("No faculty member matched!\n");
                        break;

                    }

                    people.print();
                    break;

                case "5":

                    System.out.println("Enter the staff info:");
                    System.out.print("\tName of the staff member: ");
                    fullName = scan.nextLine();

                    while (true) {

                        System.out.print("\tID: ");

                        try {

                            id = scan.nextLine();
                            id = id.toLowerCase();

                            if (!IdChecker.isValidId(id)) {

                                throw new IdException("\t\tInvalid ID format: Must be LetterLetterDigitDigitDigitDigit");

                            }

                            else if (list.searchID(id) != null) {

                                throw new IdException("\t\tInvalid ID: ID already exists");

                            }

                        } catch (IdException error) {

                            System.out.println(error.getMessage());
                            continue;

                        }

                        break;

                    }

                    while (true) {

                        System.out.print("\tDepartment: ");
                        department = scan.nextLine();

                        if (!(department.equalsIgnoreCase("Mathematics") || department.equalsIgnoreCase("Engineering") || department.equalsIgnoreCase("Sciences"))) {

                            System.out.println("\t\t\"" + department + "\" is invalid");
                            continue;

                        }

                        department = department.toLowerCase();
                        department = department.substring(0, 1).toUpperCase() + department.substring(1);
                        break;

                    }

                    while (true) {

                        System.out.print("\tStatus, Enter P for Part Time, or enter F for Full Time: ");
                        status = scan.nextLine();

                        if (!(status.equalsIgnoreCase("P") || status.equalsIgnoreCase("F"))) {

                            System.out.println("\t\t\"" + status + "\" is invalid");
                            continue;

                        }

                        break;

                    }

                    if (status.equalsIgnoreCase("P")) {

                        status = "Part Time";

                    }

                    else {

                        status = "Full Time";

                    }

                    System.out.println("\nStaff member added!\n");

                    people = new Staff(fullName, id, department, status);
                    list.addPerson(people);
                    break;

                case "6":

                    while (true) {

                        System.out.print("\tEnter the staff's ID: ");

                        try {

                            id = scan.nextLine();
                            id = id.toLowerCase();

                            if (!IdChecker.isValidId(id)) {

                                throw new IdException("\t\tInvalid ID format: Must be LetterLetterDigitDigitDigitDigit");

                            }

                            break;

                        } catch (IdException error) {

                            System.out.println(error.getMessage());

                        }

                    }

                    System.out.print("\n");

                    people = list.searchID(id);

                    if (!(people instanceof Staff)) {

                        System.out.println("No staff member matched!\n");
                        break;

                    }

                    people.print();
                    break;

                case "7":

                    String response1;

                    while(true) {

                        System.out.print("Would you like to create the report? (Y/N): ");
                        response1 = scan.nextLine();

                        if (!(response1.equalsIgnoreCase("y") || response1.equalsIgnoreCase("n"))) {

                            System.out.println("\tNot valid response, please try again!");
                            continue;

                        }

                        break;

                    }

                    if (response1.equalsIgnoreCase("y")) {

                        while (true) {

                            System.out.print("Would you like to sort your students by (1) GPA or (2) credit hours: ");
                            response1 = scan.nextLine();

                            if (!(response1.equalsIgnoreCase("1") || response1.equalsIgnoreCase("2"))) {

                                System.out.println("\tNot valid response, please try again!");
                                continue;

                            }

                            break;

                        }

                        System.out.println("\nReport created and saved on your hard drive!");
                        list.printPersonnel(response1);

                    }

                    System.out.println("\nGoodbye!");

                    break;

            }

            if (response.equals("7")) {

                break;

            }

        }

    }

}

abstract class Person {

    String fullName;
    String id;

    public Person(String fullName, String id) {

        this.fullName = fullName;
        this.id = id;

    }

    public Person() {

    }

    public void setFullName(String newFullName) {

        fullName = newFullName;

    }

    public String getFullName() {

        return fullName;

    }

    public void setId(String newId) {

        id = newId;

    }

    public String getId() {

        return id;

    }

    public abstract void print();

}

abstract class Employee extends Person {

    String department;

    public Employee(String fullName, String id, String department) {

        super(fullName, id);
        this.department = department;

    }

    public Employee() {

    }

    public void setDepartment(String newDepartment) {

        department = newDepartment;

    }

    public String getDepartment() {

        return department;

    }

}

class Faculty extends Employee {

    String rank;

    public Faculty(String fullName, String id, String department, String rank) {

        super(fullName, id, department);
        this.rank = rank;

    }

    public Faculty() {

    }

    public void setRank(String newRank) {

        rank = newRank;

    }

    public String getRank() {

        return rank;

    }

    @Override
    public void print() {

        System.out.println("---------------------------------------------------------------------------");
        System.out.println(super.getFullName() + "\n" + super.getId() + "\n" + super.getDepartment() + " Department, " + rank);
        System.out.println("---------------------------------------------------------------------------\n");

    }

}

class Staff extends Employee {

    String status;

    public Staff(String fullName, String id, String department, String status) {

        super(fullName, id, department);
        this.status = status;

    }

    public Staff() {

    }

    public void setStatus(String newStatus) {

        status = newStatus;

    }

    public String getStatus() {

        return status;

    }

    @Override
    public void print() {

        System.out.println("---------------------------------------------------------------------------");
        System.out.println(super.getFullName() + "\n" + super.getId() + "\n" + super.getDepartment() + " Department, " + status);
        System.out.println("---------------------------------------------------------------------------\n");

    }

}

class Student extends Person {

    double gpa;
    int creditHours;
    double discount = 0;

    public Student(String fullName, String id, double gpa, int creditHours) {

        super(fullName, id);
        this.gpa = gpa;
        this.creditHours = creditHours;

    }

    public Student() {

    }

    public void setGpa(double newGpa) {

        gpa = newGpa;

    }

    public double getGpa() {

        return gpa;

    }

    public void setCreditHours(int newCreditHours) {

        creditHours = newCreditHours;

    }

    public int getCreditHours() {

        return creditHours;

    }

    public void setDiscount(double gpa) {

        if (gpa >= 3.85) {

            discount = 0.25;

        }

    }

    @Override
    public void print() {

        setDiscount(gpa);

        System.out.println("---------------------------------------------------------------------------");
        System.out.println(super.getFullName() + "\n" + super.getId() + "\n" + "Credit Hours: " +  creditHours + " ($236.45/credit hour)\nFees: $52");
        System.out.println("Total payment: $" + Math.round(((236.45 * creditHours) + 52) * (1 - discount) * 100)/100.0 +  " (" + (int)(discount * 100) + "% discount applied)" );
        System.out.println("---------------------------------------------------------------------------\n");

    }

}

class Personnel {

    private List<Person> list;

    public Personnel() {

        list = new ArrayList<>();

    }

    public Personnel(int size) {

    }

    public void addPerson(Person people) {

        list.add(people);

    }

    public Person getPerson(int index) {

        return list.get(index);

    }

    public Person searchID(String id) {

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getId().equals(id)) {

                return list.get(i);

            }

        }

        return null;

    }

    public void printPersonnel(String option) {

        PrintWriter writer = null;

        try {

            File output = new File("report.txt");
            writer = new PrintWriter(output);

        } catch (IOException error) {

            System.out.println(error.getMessage());

        }

        int count = 1;

        writer.println("Faculty Members\n---------------");

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i) instanceof Faculty) {

                writer.println("\t" + count + ". " + list.get(i).getFullName());
                writer.println("\tID: " + list.get(i).getId());
                writer.println("\t" + ((Faculty) list.get(i)).getRank() + ", " + ((Faculty) list.get(i)).getDepartment() + "\n");
                count++;

            }

        }

        count = 1;

        writer.println("Staff Members\n-------------");

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i) instanceof Staff) {

                writer.println("\t" + count + ". " + list.get(i).getFullName());
                writer.println("\tID: " + list.get(i).getId());
                writer.println("\t" + ((Staff) list.get(i)).getDepartment() + ", " + ((Staff) list.get(i)).getStatus() + "\n");
                count++;

            }

        }

        count = 1;

        writer.println("Students (Sorted by GPA)\n------------------------");

        List<Student> studentList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i) instanceof Student) {

                studentList.add((Student) list.get(i));

            }

        }

        if (option.equals("1")) {

            Collections.sort(studentList, new sortByGPA());

        }

        else if (option.equals("2")) {

            Collections.sort(studentList, new sortByCreditHours());

        }

        for (int i = 0; i < studentList.size(); i++) {

            writer.println("\t" + count + ". " + studentList.get(i).getFullName());
            writer.println("\tID: " + studentList.get(i).getId());
            writer.println("\tGPA: " + studentList.get(i).getGpa());
            writer.println("\tCredit hours: " + studentList.get(i).getCreditHours() + "\n");
            count++;

        }

        writer.close();

    }

}

class sortByGPA implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {

        if (((Student)o1).getGpa() < ((Student)o2).getGpa()) {

            return 1;

        }

        return -1;

    }

}

class sortByCreditHours implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {

        if (((Student)o1).getCreditHours() < ((Student)o2).getCreditHours()) {

            return 1;

        }

        return -1;

    }

}