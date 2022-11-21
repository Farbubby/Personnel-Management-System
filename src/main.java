import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        final int numPeople = 100;

        Scanner scan = new Scanner(System.in);

        String response;
        String fullName;
        String id;
        String department;
        String rank;
        String status;
        double gpa;
        int creditHours;
        Person people;
        Personnel list = new Personnel(numPeople);


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

            if (!(response.matches("1|2|3|4|5|6|7"))) {

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
                        id = scan.nextLine();
                        id = id.toLowerCase();

                        if (list.searchID(id) != null) {

                            System.out.println("\t\tID already exists");
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
                    list.addPerson(people, Faculty.getNumType() + Staff.getNumType() + Student.getNumType() - 1);
                    break;

                case "2":

                    System.out.println("Enter the student info:");

                    System.out.print("\tName of the student: ");
                    fullName = scan.nextLine();

                    while (true) {

                        System.out.print("\tID: ");
                        id = scan.nextLine();
                        id = id.toLowerCase();

                        if (list.searchID(id) != null) {

                            System.out.println("\t\tID already exists");
                            continue;

                        }

                        break;

                    }

                    System.out.print("\tGPA: ");
                    gpa = Double.parseDouble(scan.nextLine());

                    System.out.print("\tCredit hours: ");
                    creditHours = Integer.parseInt(scan.nextLine());

                    System.out.println("\nStudent added!\n");

                    people = new Student(fullName, id, gpa, creditHours);
                    list.addPerson(people, Faculty.getNumType() + Staff.getNumType() + Student.getNumType() - 1);
                    break;

                case "3":

                    System.out.print("Enter the student's ID: ");
                    id = scan.nextLine();
                    id = id.toLowerCase();
                    System.out.print("\n");

                    people = list.searchID(id);

                    if (!(people instanceof Student)) {

                        System.out.println("No student matched!\n");
                        break;

                    }

                    people.print();
                    break;

                case "4":

                    System.out.print("Enter the faculty's ID: ");
                    id = scan.nextLine();
                    id = id.toLowerCase();
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
                        id = scan.nextLine();
                        id = id.toLowerCase();

                        if (list.searchID(id) != null) {

                            System.out.println("\t\tID already exists");
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
                    list.addPerson(people, Faculty.getNumType() + Staff.getNumType() + Student.getNumType() - 1);
                    break;

                case "6":

                    System.out.print("Enter the staff's ID: ");
                    id = scan.nextLine();
                    id = id.toLowerCase();
                    System.out.print("\n");

                    people = list.searchID(id);

                    if (!(people instanceof Staff)) {

                        System.out.println("No staff member matched!\n");
                        break;

                    }

                    people.print();
                    break;

                case "7":

                    System.out.println("Goodbye!");
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
    private static int numType = 0;

    public Faculty(String fullName, String id, String department, String rank) {

        super(fullName, id, department);
        this.rank = rank;
        numType++;

    }

    public Faculty() {

    }

    public void setRank(String newRank) {

        rank = newRank;

    }

    public String getRank() {

        return rank;

    }

    public static int getNumType() {

        return numType;

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
    private static int numType = 0;

    public Staff(String fullName, String id, String department, String status) {

        super(fullName, id, department);
        this.status = status;
        numType++;

    }

    public Staff() {

    }

    public void setStatus(String newStatus) {

        status = newStatus;

    }

    public String getStatus() {

        return status;

    }

    public static int getNumType() {

        return numType;

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
    private static int numType = 0;

    public Student(String fullName, String id, double gpa, int creditHours) {

        super(fullName, id);
        this.gpa = gpa;
        this.creditHours = creditHours;
        numType++;

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

    public static int getNumType() {

        return numType;

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

    Person[] list;

    public Personnel(int size) {

        list = new Person[size];

    }

    public Personnel() {

    }

    public void addPerson(Person people, int index) {

        list[index] = people;

    }

    public Person getPerson(int index) {

        return list[index];

    }

    public Person searchID(String id) {

        for (int i = 0; i < Student.getNumType() + Staff.getNumType() + Faculty.getNumType(); i++) {

            if (list[i].getId().equals(id)) {

                return list[i];

            }

        }

        return null;

    }

}