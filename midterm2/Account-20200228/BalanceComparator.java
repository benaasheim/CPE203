import java.util.Comparator;
/*
* TO DO:
*This class should compare all accounts by balance only (in ascending order)
 */
public class BalanceComparator implements Comparator<Account> {

    public int compare(Account a1, Account a2) {
        return (a1.getBalance() <  a2.getBalance() ? -1 : a1.getBalance() == a2.getBalance() ? 0 : 1);
    }

}
