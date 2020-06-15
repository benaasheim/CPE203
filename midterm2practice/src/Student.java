public class Student extends Person {
    private double gpa;
    private int studentLoans;

    public Student(double gpa, int studentLoans, String name,
                   int account, int legs) {
        super(name, account, legs);
        this.gpa = gpa;
        this.studentLoans = studentLoans;
    }

    public String toString() {
        return super.toString() + " and a Student object with a " + gpa
                + " gpa and " + studentLoans + " dollars of student loans.";
    }

    public boolean equals(Object o) {
        return super.equals(o) && gpa == ((Student) o).gpa &&
                studentLoans == ((Student) o).studentLoans;
    }

    public String greet(Professor p) {
        return "What's the answer?";
    }

    public String greet(Student s) {
        return "Yo!";
    }

    public String greet(Person s) {
        return "Hey.";
    }

    public void getPaid(int amount) {
        int loanPayment = (int) (amount * .10);
        if (loanPayment > studentLoans) {
            loanPayment = studentLoans;
        }
        studentLoans -= loanPayment;
        super.getPaid(amount - studentLoans);
    }
}




