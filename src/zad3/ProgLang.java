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
    
    public Map<String, ArrayList<String>> mapAll = new LinkedHashMap<>();
    
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
        //  UPDATE: moved ^ to constructor
        //programmers = programmers.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        
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
    Map<String, ArrayList<String>> getLangsMapSortedByNumOfProgs(){
        
        LinkedHashMap retMap = new LinkedHashMap<>(mapAll);
        //ArrayList<Map.Entry<String, ArrayList<String>>> list = new ArrayList<>(mapAll.entrySet());
        // removing duplicate last names moved to constructor
        /*
        list.forEach(entry -> {
            ArrayList<String> tmp = new ArrayList<>(entry.getValue());
            ArrayList<String> tmpDistinct = tmp.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
            entry.setValue(tmpDistinct);
        });
        
         */
        
        retMap = sorted(retMap, Collections.reverseOrder((Comparator<Map.Entry<String, ArrayList<String>>>) (a, b) -> {
            int i = Integer.compare(a.getValue().size(),b.getValue().size());
            if (i != 0) {
                return i;
            }else{
                List<String> list1 = new ArrayList<>(Arrays.asList(a.getKey(),b.getKey()));
                list1.sort(Comparator.comparing(Object::toString));
    
                if(list1.get(0).equals(a.getKey()))
                    return 1;
                else if(list1.get(0).equals(b.getKey()))
                    return -1;
                else
                    return 0;
            }
        }));
        
        // Before i read about methods sorted(..) and filtered(...)
        /*
        list.sort(Collections.reverseOrder((a, b) -> {
            int i = Integer.compare(a.getValue().size(),b.getValue().size());
            if (i != 0) {
                return i;
            }else{
                List<String> list1 = new ArrayList<>(Arrays.asList(a.getKey(),b.getKey()));
                list1.sort(Comparator.comparing(Object::toString));
                
                if(list1.get(0).equals(a.getKey()))
                    return 1;
                else if(list1.get(0).equals(b.getKey()))
                    return -1;
                else
                    return 0;
            }
        }));
        
        list.forEach(e -> retMap.put(e.getKey(),e.getValue()));
         */
        
        return retMap;
     }
    Map<String,ArrayList<String>> getProgsMapSortedByNumOfLangs(){
        LinkedHashMap retMap = new LinkedHashMap<>(getProgsMap());
        //ArrayList<Map.Entry<String, ArrayList<String>>> list = new ArrayList<>(getProgsMap().entrySet());
        
        retMap = sorted(retMap, Collections.reverseOrder((Comparator<Map.Entry<String, ArrayList<String>>>) (a, b) -> {
                    int i = Integer.compare(a.getValue().size(),b.getValue().size());
                    if (i != 0)
                        return i;
                    else
                        return Character.compare(b.getKey().charAt(0), a.getKey().charAt(0));
                }));
        // Before sorted():
        /*
        list.sort(Collections.reverseOrder((a, b) -> {
            int i = Integer.compare(a.getValue().size(),b.getValue().size());
            if (i != 0)
                return i;
            else
                return Character.compare(b.getKey().charAt(0), a.getKey().charAt(0));
            
        }));
    
        list.forEach(e -> retMap.put(e.getKey(),e.getValue()));
        
         */
    
        return retMap;
    }
    Map<String,ArrayList<String>> getProgsMapForNumOfLangsGreaterThan(int n){
        LinkedHashMap<String, ArrayList<String>> retMap = new LinkedHashMap<>();
        ArrayList<Map.Entry<String, ArrayList<String>>> list = new ArrayList<>(getProgsMap().entrySet());
        
        list = list.stream().filter(e -> e.getValue().size() > n).collect(Collectors.toCollection(ArrayList::new));
        
        list.forEach(e -> retMap.put(e.getKey(),e.getValue()));
        
        return retMap;
    }
    
    static LinkedHashMap filter(Map map, Comparator comparator){
    
    }
    
    static LinkedHashMap sorted(Map map, Comparator comparator){
        LinkedHashMap linkedHashMap = new LinkedHashMap<>();
        ArrayList<Map.Entry> arrayList = new ArrayList<>(map.entrySet());
        arrayList.sort(comparator);
        arrayList.forEach(e -> linkedHashMap.put(e.getKey(),e.getValue()));
        return linkedHashMap;
    }
    
    ProgLang(){}
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
                programmers = programmers.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
                plMap.put(arr[0],programmers);
            }
            mapAll = plMap;
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
