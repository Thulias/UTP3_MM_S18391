/**
 *
 *  @author Majewski Mateusz S18391
 *
 */

package zad2;


public class Purchase{
    
    private String id;
    private String name;
    private String lname;
    private String product;
    private double price;
    private double amount;
    private double total;
    
    public String getId() {
        return id;
    }
    public String getLname() {
        return lname;
    }
    public double getTotal(){
        return total;
    }
    
    public String toString(){
        return id+";"+lname+" "+name+";"+product+";"+price+";"+amount;
    }
    Purchase(String rawString){
        String[] arr = rawString.split(";");
        String[] firstLastName = arr[1].split(" ");
        
        id=arr[0];
        name=firstLastName[1];
        lname=firstLastName[0];
        product=arr[2];
        price=Double.parseDouble(arr[3]);
        amount=Double.parseDouble(arr[4]);
        total=amount*price;
    }
}
