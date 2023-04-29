import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Services {
    static Scanner ip = new Scanner(System.in);
    static List<customer> custdetails = new ArrayList<>();

    static customer getCustomer(String name , String pass){
        for(customer c:custdetails){
            String encryptedPass = PasswordManager.getInstance().doEncrypt(pass);
            if(c.name.equals(name) && c.encpass.equals(encryptedPass)){
                return c;
            }
        }
        return null;
    }

    static int getInt(String data){
        return Integer.parseInt(data);
    }

    static void InitializeTheData() throws FileNotFoundException{
            File file = new File("CustomerDetails.txt");
            Scanner filesdata = new Scanner(file);
            while(filesdata.hasNextLine()){
                String datas[] = filesdata.nextLine().split(" ");
                customer cust = new customer(getInt(datas[0]),getInt(datas[1]),datas[2],getInt(datas[3]),datas[4]);
                custdetails.add(cust);
            }
            filesdata.close();
            
    }

    static void AddToFile(customer data) throws IOException{
        FileWriter file = new FileWriter("CustomerDetails.txt",true);
        String datatoBeinserted = String.valueOf(customer.custid+" "+data.accno+" "+data.name+" "+data.balance+" "+data.encpass+"\n");
        file.write(datatoBeinserted);
        file.close();

    }

    static void dologin(){
            System.out.println("Enter your name");
            String inputname = ip.nextLine();
            System.out.println("Enter your password");
            String passwrd = ip.nextLine();
            customer logged = getCustomer(inputname, passwrd);
            if(logged!=null){
                BankingService.initiateService(logged);
            }else{
                System.out.println("Account is not valid..!! \nPlease check the credentials..");
            }

    }
    public static List<customer> fetchTopNCustomers(int count) {
        List<customer> topNCustomers = new ArrayList<>();
        Collections.sort(custdetails , new Comparator<customer>(){
            @Override
            public int compare(customer c1, customer c2) {
                // if positive value then swaps , else not swaps.
                // if the second customer has more balance than 1st one , then swap both.
               if(c1.balance < c2.balance){
                    return 1;
               }
               return -1;
            }
        });

        for(int index=0;index<count;index++){
            topNCustomers.add(custdetails.get(index));
        }
        return topNCustomers;
    }

    public static void main(String[] args) throws IOException {
        
        InitializeTheData();
        
        boolean flag=true;
        while(flag){
        System.out.println("MENU \n1-Add new customer \n2-login \n3-See top N customers 4-exit");
        int choice = ip.nextInt();
        ip.nextLine();  // for bypass
        switch(choice){
            case 1:
                    customer newcust = new customer();
                    custdetails.add(newcust);
                    AddToFile(newcust);
                    break;
            case 2:
                   dologin();
                   break;
            case 3:
                    System.out.println("Enter the Customer count to fetch according to account balance : ");
                    int nCustomer = ip.nextInt();  // considering the input is less than the custDetails.
                    List<customer> list = fetchTopNCustomers(nCustomer);
                    System.out.println("The details requested as follows :: ");
                    for(customer c : list)c.print();
                    break;
            case 4:
                    flag = false;
                    break;
           }
        }  // end of while loop
    } // end of main function
}
