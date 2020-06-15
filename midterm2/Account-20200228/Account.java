import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public final class Account implements Comparable<Account> {

    private String firstName;
    private String lastName;
    private int accountNumber;
    private double balance;
    private boolean isNewAccount;


    public Account(
            String firstName,
            String lastName,
            int accountNumber,
            double balance,
            boolean isNewAccount
    ) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.isNewAccount = isNewAccount;

    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isNewAccount() {
        return isNewAccount;
    }

    /**
     * TO DO: override equals
     */
    @Override
    public boolean equals(Object other) {
        return (other != null) && (other instanceof Account) &&
                (this.firstName == ((Account) other).firstName &&
                this.lastName == ((Account) other).lastName &&
                this.accountNumber == ((Account) other).accountNumber &&
                this.balance == ((Account) other).balance &&
                this.isNewAccount == ((Account) other).isNewAccount);

    }

    /**
     * TO DO: override hashCode here
     */
    @Override
    public int hashCode() {
        return (this.firstName.length()*this.firstName.length()) + (this.lastName.length() * this.lastName.length()) - accountNumber * (int)balance;
    }

    /**
     * TO DO:
     * Write compareTo (natural ordering of class):
     * 1. accountNumber in ascending order
     *      If same, break ties:
     * 2. old Accounts before new accounts
     *      If same, break ties
     * 3. LastName
     *      If same, break ties
     * 4. FirstName
     *      If same, break ties
     * 5. Lower Balance before higher balance
     */
    public static int Atest(Account a1, Account a2) {
        if (a1.isNewAccount()) {
            if (a2.isNewAccount()) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else {
            if (a2.isNewAccount()) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
    @Override
    public int compareTo(Account other) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;
        Comparator<Account> accountComparator1 = Comparator.comparing(Account::getAccountNumber);
        Comparator<Account> accountComparator2 = Comparator.comparing(Account::getLastName);
        Comparator<Account> accountComparator3 = Comparator.comparing(Account::getFirstName);
        BalanceComparator balanceComparator4 = new BalanceComparator();
        Comparator<Account> accountComparator4 = (a1, a2) -> Atest(a1, a2);
        Comparator<Account> accountComparator = accountComparator1.thenComparing(accountComparator4).thenComparing(accountComparator2).thenComparing(accountComparator3).thenComparing(balanceComparator4);

        return accountComparator.compare(this, other);

    }


}
