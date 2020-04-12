/**
 *
 *  @author Majewski Mateusz S18391
 *
 */

package zad3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class ProgLang {
    
    Map<String, ArrayList<String>> mapAll = new LinkedHashMap<>();
    
    Map<String, ArrayList<String>> getLangsMap(){
        return mapAll;
    }
    Map<String, ArrayList<String>> getProgsMap(){
        
        Map<String, ArrayList<String>> retMap = new LinkedHashMap<>();
        ArrayList<String> programmers = new ArrayList<>();
        // adding programmers and languages into arrayLists:
        for(HashMap.Entry<String,ArrayList<String>> entry : mapAll.entrySet()){
            programmers.addAll(entry.getValue());
        }
        // making sure there are no duplicates:
        programmers = programmers.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        
        for(String programmer : programmers) {
            // filling return Map with programmers and empty Lists
            retMap.put(programmer, new ArrayList<>());
            
            for(Map.Entry<String,ArrayList<String>> mapEntry : mapAll.entrySet()){
                if(mapEntry.getValue().contains(programmer))
                    retMap.get(programmer).add(mapEntry.getKey());
            }
        }
        return retMap;
    }
    
    
    ProgLang(String path) {
        try{
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            List<String> list = new ArrayList<>();
            while(sc.hasNextLine())
                list.add(sc.nextLine());
            
            Map<String,ArrayList<String>> plMap = new LinkedHashMap<>();
            for(String s : list){
                String[] arr = s.split("\t");
                ArrayList<String> programmers = new ArrayList<>(Arrays.asList(arr).subList(1, arr.length));
                plMap.put(arr[0],programmers);
            }
            mapAll = plMap;
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
}
