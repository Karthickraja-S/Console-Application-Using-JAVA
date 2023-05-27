import java.util.*;
public class BankingService {

    static Scanner ip = new Scanner(System.in);
    private static customer accountuser=null;

    private static void withdrawl(){
        System.out.println("Enter amount to withdraw");
        int withdraw_amt = ip.nextInt();
        if(accountuser.balance > withdraw_amt){
                    accountuser.balance = accountuser.balance - withdraw_amt;
                    accountuser.history.add(new TransHistory(++accountuser.transId,"WITHDRAW",withdraw_amt,accountuser.balance));
                    System.out.println("DEBITTED Success , BALANCE : "+accountuser.balance);
            }
        else{
                System.out.println("Insufficient balance");
            }
    }

    private static void cashDeposit(){
        System.out.println("Enter amount to deposit to your account : ");
        int amt_to_deposit = ip.nextInt();
        if(amt_to_deposit>0){
            accountuser.balance = accountuser.balance+amt_to_deposit;
            accountuser.history.add(new TransHistory(++accountuser.transId,"CashDeposit",amt_to_deposit,accountuser.balance));
        }
    }
    
    private static void transHistory(){
        List<TransHistory> th = accountuser.history;
        System.out.println("NAME : "+accountuser.name);
        System.out.println("ACCOUNT NO : "+accountuser.accno);
        System.out.println("----------------- history ---------------------");
        System.out.println("Trans Id        TransType          Amount         Balance");
        for(TransHistory hist : th){
            System.out.println(hist.trans_id+"          "+hist.trans_type+"            "+hist.amt+"            "+hist.balance);
        }

    }

    private static customer getcust(int accNo){
        for(customer cust : Services.custdetails){
            if(cust.accno == accNo){
                return cust;
            }
        }
        return null;
    }

    private static void accountTransfer(){
        System.out.println("Enter Account Number to Transfer the Amount : ");
        int Acc_No = ip.nextInt();
        customer dest = getcust(Acc_No);
        if(dest!=null){
            System.out.println("Enter Amount to transfer : ");
            int amounttoBeTransferred = ip.nextInt();
            if(amounttoBeTransferred < accountuser.balance){
                    accountuser.balance = accountuser.balance - amounttoBeTransferred;
                    dest.balance = dest.balance + amounttoBeTransferred;
                    accountuser.history.add(new TransHistory(++accountuser.transId,String.valueOf("TransferTo"+Acc_No),amounttoBeTransferred,accountuser.balance));
                    dest.history.add(new TransHistory(++dest.transId,String.valueOf("TransferFrom"+accountuser.accno),amounttoBeTransferred,dest.balance));
            }else{
                System.out.println("The balance is not sufficient to transfer. \nYour current balance : "+accountuser.balance);
            }
            if(amounttoBeTransferred > 5000){
                // Charge a operational fee
                accountuser.balance -=10; 
                accountuser.history.add(new TransHistory(++accountuser.transId, "Operational Fee", 10, accountuser.balance));
            }
        }
        else{
            System.out.println("No customer Found . Check the Account Number given");
        }
    }

    public static void initiateService(customer current_user){
        accountuser = current_user;
        boolean flag = true;
        boolean transactionDone = false;
        while(flag){
            System.out.println("OPTIONS : \n1-withdrawl \n2-deposit \n3-transfer \n4-history \n5-logout");
            int choice = ip.nextInt();
        //    ip.nextLine();
            switch(choice){
                case 1 : 
                        withdrawl();
                        transactionDone=true;
                        break;
                case 2 : 
                        cashDeposit();
                        transactionDone=true;
                        break;
                case 3 : 
                        accountTransfer();
                        transactionDone=true;
                        break;
                case 4 : 
                        transHistory();
                        break;
                case 5 : 
                        flag=false;
                        break;
            }
            // charge a maintenance fee .. but if he is in top3customer then , no need.
            if(accountuser.transId % 10 == 0 && !Services.fetchTopNCustomers(3).contains(accountuser) && transactionDone){
                accountuser.balance -= 100;
                accountuser.history.add(new TransHistory(++accountuser.transId, "Maintenance Fee", 100, accountuser.balance));
            }
            // need to neglete the transaction of account opening.
            // reason for transactionDone :: if the user gives 4 , then below executes again 
            // if we dont have this boolean. ( THINK! )
            if((accountuser.transId-1)%5==0 && transactionDone){   // TASK 10 : Force Password Change .
                transactionDone = false;
                System.out.println("For Security Reasons , after doing 5 transactions , It is ");
                System.out.println("better to change the password ");
                accountuser.createOrUpdatePassword();
            }  
        }  // while loop ends.
    } 
}
