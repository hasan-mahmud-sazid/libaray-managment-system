import java.util.Scanner;

class Book {
    public int sNo;
    public String bookName;
    public String authorName;
    public int bookQty;
    public int bookQtyCopy;

    Scanner input = new Scanner(System.in);

    public Book() {
        System.out.print("Enter Serial No of Book:");
        this.sNo = input.nextInt();
        input.nextLine();

        System.out.print("Enter Book Name:");
        this.bookName = input.nextLine();

        System.out.print("Enter Author Name:");
        this.authorName = input.nextLine();

        System.out.print("Enter Quantity of Books:");
        this.bookQty = input.nextInt();
        this.bookQtyCopy = this.bookQty;
    }
}

class Books {
    Book[] theBooks = new Book[50];
    public static int count = 0;

    Scanner input = new Scanner(System.in);

    public int compareBookObjects(Book b1, Book b2) {
        if (b1.bookName.equalsIgnoreCase(b2.bookName)) {
            System.out.println("Book of this Name Already Exists.");
            return 0;
        }
        if (b1.sNo == b2.sNo) {
            System.out.println("Book of this Serial No Already Exists.");
            return 0;
        }
        return 1;
    }

    public void addBook(Book b) {
        for (int i = 0; i < count; i++) {
            if (this.compareBookObjects(b, this.theBooks[i]) == 0) return;
        }
        if (count < 50) {
            theBooks[count] = b;
            count++;
        } else {
            System.out.println("No Space to Add More Books.");
        }
    }

    public void searchBySno() {
        System.out.println("\t\tSEARCH BY SERIAL NUMBER\n");
        System.out.println("Enter Serial No of Book:");
        int sNo = input.nextInt();

        int flag = 0;
        System.out.println("S.No\tName\tAuthor\tAvailable Qty\tTotal Qty");
        for (int i = 0; i < count; i++) {
            if (sNo == theBooks[i].sNo) {
                System.out.println(theBooks[i].sNo + "\t" + theBooks[i].bookName + "\t" + theBooks[i].authorName + "\t" + theBooks[i].bookQtyCopy + "\t" + theBooks[i].bookQty);
                flag++;
                return;
            }
        }
        if (flag == 0) System.out.println("No Book for Serial No " + sNo + " Found.");
    }

    public void searchByAuthorName() {
        System.out.println("\t\tSEARCH BY AUTHOR'S NAME");
        input.nextLine();

        System.out.println("Enter Author Name:");
        String authorName = input.nextLine();

        int flag = 0;
        System.out.println("S.No\tName\tAuthor\tAvailable Qty\tTotal Qty");
        for (int i = 0; i < count; i++) {
            if (authorName.equalsIgnoreCase(theBooks[i].authorName)) {
                System.out.println(theBooks[i].sNo + "\t" + theBooks[i].bookName + "\t" + theBooks[i].authorName + "\t" + theBooks[i].bookQtyCopy + "\t" + theBooks[i].bookQty);
                flag++;
            }
        }
        if (flag == 0) System.out.println("No Books of " + authorName + " Found.");
    }

    public void showAllBooks() {
        System.out.println("\t\tSHOWING ALL BOOKS\n");
        System.out.println("S.No\tName\tAuthor\tAvailable Qty\tTotal Qty");
        for (int i = 0; i < count; i++) {
            System.out.println(theBooks[i].sNo + "\t" + theBooks[i].bookName + "\t" + theBooks[i].authorName + "\t" + theBooks[i].bookQtyCopy + "\t" + theBooks[i].bookQty);
        }
    }

    public void upgradeBookQty() {
        System.out.println("\t\tUPGRADE QUANTITY OF A BOOK\n");
        System.out.println("Enter Serial No of Book:");
        int sNo = input.nextInt();
        for (int i = 0; i < count; i++) {
            if (sNo == theBooks[i].sNo) {
                System.out.println("Enter No of Books to be Added:");
                int addingQty = input.nextInt();
                theBooks[i].bookQty += addingQty;
                theBooks[i].bookQtyCopy += addingQty;
                return;
            }
        }
    }

    public void dispMenu() {
        System.out.println("---------------------------------------------------");
        System.out.println("1: Add Book\n2: Upgrade Quantity of a Book");
        System.out.println("3: Search a Book\n4: Show All Books");
        System.out.println("5: Register Student\n6: Show All Students");
        System.out.println("7: Check Out Book\n8: Check In Book\n0: Exit");
        System.out.println("---------------------------------------------------");
    }

    public int isAvailable(int sNo) {
        for (int i = 0; i < count; i++) {
            if (sNo == theBooks[i].sNo) {
                if (theBooks[i].bookQtyCopy > 0) {
                    System.out.println("Book is Available.");
                    return i;
                }
                System.out.println("Book is Unavailable");
                return -1;
            }
        }
        System.out.println("No Book of Serial Number Available in Library.");
        return -1;
    }

    public Book checkOutBook() {
        System.out.println("Enter Serial No of Book to be Checked Out:");
        int sNo = input.nextInt();
        int bookIndex = isAvailable(sNo);
        if (bookIndex != -1) {
            theBooks[bookIndex].bookQtyCopy--;
            return theBooks[bookIndex];
        }
        return null;
    }

    public void checkInBook(Book b) {
        for (int i = 0; i < count; i++) {
            if (b.equals(theBooks[i])) {
                theBooks[i].bookQtyCopy++;
                return;
            }
        }
    }
}

// Class to represent a student
class Student {
    String studentName;
    String regNum;
    Book[] borrowedBooks = new Book[3];
    int booksCount = 0;

    Scanner input = new Scanner(System.in);

    public Student() {
        System.out.println("Enter Student Name:");
        this.studentName = input.nextLine();

        System.out.println("Enter Registration Number:");
        this.regNum = input.nextLine();
    }
}

// Class to manage students
class Students {
    Student[] theStudents = new Student[50];
    public static int count = 0;

    Scanner input = new Scanner(System.in);

    public void addStudent(Student s) {
        for (int i = 0; i < count; i++) {
            if (s.regNum.equalsIgnoreCase(theStudents[i].regNum)) {
                System.out.println("Student of Reg Num " + s.regNum + " is Already Registered.");
                return;
            }
        }
        if (count <= 50) {
            theStudents[count] = s;
            count++;
        }
    }

    public void showAllStudents() {
        System.out.println("Student Name\tReg Number");
        for (int i = 0; i < count; i++) {
            System.out.println(theStudents[i].studentName + "\t" + theStudents[i].regNum);
        }
    }

    public int isStudent() {
        System.out.println("Enter Reg Number:");
        String regNum = input.nextLine();
        for (int i = 0; i < count; i++) {
            if (theStudents[i].regNum.equalsIgnoreCase(regNum)) return i;
        }
        System.out.println("Student is not Registered. Get Registered First.");
        return -1;
    }

    public void checkOutBook(Books book) {
        int studentIndex = this.isStudent();
        if (studentIndex != -1) {
            System.out.println("Checking out:");
            book.showAllBooks();
            Book b = book.checkOutBook();
            if (b != null) {
                if (theStudents[studentIndex].booksCount < 3) {
                    theStudents[studentIndex].borrowedBooks[theStudents[studentIndex].booksCount] = b;
                    theStudents[studentIndex].booksCount++;
                    return;
                }
                System.out.println("Student Cannot Borrow More than 3 Books.");
            }
        }
    }

    public void checkInBook(Books book) {
        int studentIndex = this.isStudent();
        if (studentIndex != -1) {
            Student s = theStudents[studentIndex];
            System.out.println("S.No\tBook Name\tAuthor Name");
            for (int i = 0; i < s.booksCount; i++) {
                System.out.println(s.borrowedBooks[i].sNo + "\t" + s.borrowedBooks[i].bookName + "\t" + s.borrowedBooks[i].authorName);
            }
            System.out.println("Enter Serial Number of Book to be Checked In:");
            int sNo = input.nextInt();
            for (int i = 0; i < s.booksCount; i++) {
                if (sNo == s.borrowedBooks[i].sNo) {
                    book.checkInBook(s.borrowedBooks[i]);
                    s.borrowedBooks[i] = null;
                    s.booksCount--;
                    return;
                }
            }
            System.out.println("Book of Serial Number Not Found.");
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Books bookObj = new Books();
        Students studentObj = new Students();

        int choice;
        do {
            bookObj.dispMenu();
            choice = input.nextInt();
            switch (choice) {
                case 1 -> {
                    Book b = new Book();
                    bookObj.addBook(b);
                }
                case 2 -> bookObj.upgradeBookQty();
                case 3 -> {
                    System.out.println("1: Search by Serial Number");
                    System.out.println("2: Search by Author's Name");
                    int searchChoice = input.nextInt();
                    switch (searchChoice) {
                        case 1 -> bookObj.searchBySno();
                        case 2 -> bookObj.searchByAuthorName();
                    }
                }
                case 4 -> bookObj.showAllBooks();
                case 5 -> {
                    Student s = new Student();
                    studentObj.addStudent(s);
                }
                case 6 -> studentObj.showAllStudents();
                case 7 -> studentObj.checkOutBook(bookObj);
                case 8 -> studentObj.checkInBook(bookObj);
                case 0 -> System.out.println("Exiting Program. Goodbye!");
                default -> System.out.println("Invalid Option! Try Again.");
            }
        } while (choice != 0);
        input.close();
    }
}
