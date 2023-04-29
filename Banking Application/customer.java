import java.util.*;

class customer{ 
    static int custid;   // for each customer initialised , custId need to change
    int accno;            // each customer have to be unique account number.
    String name;            // not related to class , related to object (means change w.r.to person)
    int balance;
    String encpass;
    List<TransHistory> history = new ArrayList<>();  // every cust has individual history , belongs to object , so no-static here
    int transId=0;
    private Scanner ip = new Scanner(System.in);

    private Queue<String> recentPassword = new ArrayDeque<>();  // TASK 9 : PASSWORD HISTORY

    public void createOrUpdatePassword(){

        if(recentPassword.size()>3){
            recentPassword.poll();
        }

        String pass,pass1;
        do{
            System.out.println("Enter password : ");
            pass = ip.nextLine();
            System.out.println("Re type password : ");
            pass1 = ip.nextLine();
        } while(!PasswordManager.getInstance().doValidate(pass , pass1) || recentPassword.contains(pass));  
        this.encpass = PasswordManager.getInstance().doEncrypt(pass); 
        recentPassword.add(pass);
    }
   
    customer(){

        customer.custid = customer.custid+11;   // for new customer prevcustid+11.
        this.accno = customer.custid*1000+customer.custid;   // if id-55 , accno-55055

        this.balance = 10000;   // initial balance..
        System.out.println("Enter name : ");
        this.name = ip.nextLine();

        createOrUpdatePassword();
        history.add(new TransHistory(++transId,"Opening",10000,10000));
    }

    customer(int cid , int ano , String name , int bal , String enc){

        customer.custid=cid;
        this.accno=ano;
        this.name=name;
        this.balance=bal;
        this.encpass = enc;
        this.recentPassword.add(this.encpass);
        history.add(new TransHistory(++transId,"Opening",10000,10000));

    }
    public void print(){
        System.out.println("Name : "+this.name+" Account no : "+this.accno+" balance : "+this.balance);
    }
}