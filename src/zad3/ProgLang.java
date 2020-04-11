/**
 *
 *  @author Majewski Mateusz S18391
 *
 */

package zad3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class ProgLang {
    
    Map<String, ArrayList<String>> mapAll = new HashMap<>();
    
    Map<String, ArrayList<String>> getLangsMap(){
        return mapAll;
        // retMap = <nazwaJezyka, programisci tego jezyka ArrayList>
        
    }
    
    
    ProgLang(String path){
        try{
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            List<String> list = new ArrayList<>();
            while(sc.hasNextLine())
                list.add(sc.nextLine());
            
            Map<String,ArrayList<String>> plMap = new HashMap<>();
            for(String s : list){
                String[] arr = s.split("\t");
                ArrayList<String> programmers = new ArrayList<>();
                for(int i = 1; i < arr.length; i++) {
                    programmers.add(arr[i]);
                }
                plMap.put(arr[0],programmers);
            }
            mapAll = plMap;
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
}
