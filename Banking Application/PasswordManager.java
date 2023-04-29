class PasswordManager{
    private static PasswordManager pswMgr = null;
    private PasswordManager(){}

    public static PasswordManager getInstance(){
        if(pswMgr==null){
            pswMgr = new PasswordManager();
        }
        return pswMgr;
    }

 public String doEncrypt(String a){     // works for any shifting numbers . 
    int shift=1;
    String alp = "abcdefghijklmnopqrstuvwxyz";
    String ans="";
    for(int i=0;i<a.length();i++){
        char ch = a.charAt(i);
        if(Character.isDigit(ch)){
            int val = Integer.parseInt(ch+"");
            val++;
            val = val%10;    // if 9 then , 9+1 = 10 , replace to 0.
            ans = ans + String.valueOf(val);
        }
        else if(Character.isLowerCase(ch)){
        ans = ans + alp.charAt((alp.indexOf(ch)+shift)%26);
        }
        else{
            ch = Character.toLowerCase(ch);
            ans = ans + Character.toUpperCase(alp.charAt((alp.indexOf(ch)+shift)%26));
        }
    }
    return ans;
  }

  public boolean doValidate(String pass , String retypePass){
    int lowerCase=0,upperCase=0,numberCount=0;
    for(char ch : pass.toCharArray()){
        if(Character.isLowerCase(ch)){
            lowerCase++;
        }
        else if(Character.isUpperCase(ch)){
            upperCase++;
        }
        else if(Character.isDigit(ch)){
            numberCount++;
        }
    }
    if(lowerCase>=2 && upperCase>=2 && numberCount>=2){
            if(pass.equals(retypePass)){
                return true;
        }
            else{
                System.out.println("Password Incorrect , Please check the password you given");
                return false;
        }
    }
    System.out.println("Weak password , \nYour password must have atleast 2 LowerCase 2 UpperCase 2 Numerical .");
    return false;
  }
  
}
