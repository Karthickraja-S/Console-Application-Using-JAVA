public class TransHistory {
   // static int id=1;   if i keep like this , 1st cust Opening TransId=1 , 2nd cust TransId will become 2
   // in order to avoid this kind of Id , the TransId belongs to Customer so adding into customerClass.
    int trans_id;
    String trans_type="";
    int amt;
    int balance;
    TransHistory(int id,String mode , int amt , int bal){
        this.trans_id=id;
        this.trans_type = mode;
        this.amt = amt;
        this.balance = bal;
    }
}
