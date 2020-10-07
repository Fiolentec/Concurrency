package myhw.ex95;

public class Main {

    public static void main(String[] args) {
        SavingAccount savingAccount = new SavingAccount();
        for (int i = 1; i < 11; i++) {
            if (i%3==0){
                createThreadAndDeposit(savingAccount);
            }else if( i%5 ==0){
                createThreadAndPreferredWithdraw(savingAccount);
            } else {
                createThreadAndWithdraw(savingAccount);
            }
        }
    }

    public static void createThreadAndWithdraw(SavingAccount savingAccount){
        new Thread(() -> {
            savingAccount.withdraw(100);
        }).start();
    }
    public static void createThreadAndDeposit(SavingAccount savingAccount){
        new Thread(() -> {
            savingAccount.deposit(300);
        }).start();
    }
    public static void createThreadAndPreferredWithdraw(SavingAccount savingAccount){
        new Thread(() -> {
            savingAccount.withdrawPreferred(400);
        }).start();
    }
}
