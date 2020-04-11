/**
 *
 *  @author Majewski Mateusz S18391
 *
 */

package zad2;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CustomersPurchaseSortFind {
    
    ArrayList<Purchase> purchases = new ArrayList<>();
    
    void readFile(String path) {
        try{
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            while(sc.hasNextLine())
                purchases.add(new Purchase(sc.nextLine()));
            
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        
    }
    void showAll(){
        System.out.println(purchases);
    }
    void showSortedBy(String sortBy){
        StringBuilder sb = new StringBuilder(sortBy+"\n");
        List<Purchase> list;
        
        if(sortBy.equals("Nazwiska")){
            list = purchases.stream().sorted(Comparator.comparing(Purchase::getLname)
                    .thenComparing(Purchase::getId)).collect(Collectors.toList());
            list.forEach(v -> sb.append(v).append("\n"));
        }

        if(sortBy.equals("Koszty")){
            list = purchases.stream().sorted(Comparator.comparing(Purchase::getTotal).reversed()
                    .thenComparing(Purchase::getId)).collect(Collectors.toList());
            list.forEach(v -> sb.append(v)
                    .append(" (koszt: ")
                    .append(v.getTotal())
                    .append(")")
                    .append("\n"));
        }
        System.out.println(sb);
    }
    void showPurchaseFor(String id){
    
    }
    
    
}
