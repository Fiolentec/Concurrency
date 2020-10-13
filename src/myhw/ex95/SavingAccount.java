package myhw.ex95;

public class SavingAccount {
    int balance;
    int numPreferred;

    public SavingAccount() {
        balance = 0;
        numPreferred = 0;
    }

    public void deposit(int k) {
        synchronized (this) {
            balance += k;
            System.out.println("Added " + k + " to balance, and now the balance is " + balance);
            System.out.println();
            this.notifyAll();
        }
    }

    public void withdraw(int k) {
        synchronized (this) {
            while (balance < k || numPreferred > 0) {
                sleep();
            }
            balance -= k;
            System.out.println("subtracted " + k + " from balance, and now the balance is " + balance);
            System.out.println();
        }
    }

    public void withdrawPreferred(int k) {
        synchronized (this) {
            numPreferred++;
            while (balance < k) {
                sleep();
            }
            balance -= k;
            numPreferred--;
            System.out.println("PREFERED!!! subtracted " + k + " from balance, and now the balance is " + balance);
            System.out.println();
        }
    }

    private void sleep() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
