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
    Map<String, ArrayList<String>> getProgsMap(){
        System.out.println("***********************************");
        Map<String, ArrayList<String>> retMap = new HashMap<>();
        for(int i = 0; i < mapAll.size(); i++) {
            ArrayList<String> programmers = new ArrayList<>();
            ArrayList<String> langs = new ArrayList<>();
            for(Map.Entry<String,ArrayList<String>> entry : mapAll.entrySet()){
                programmers.addAll(entry.getValue());
                langs.add(entry.getKey());
                programmers.forEach(v -> {
                    if(retMap.containsKey(v)){
                        if(!retMap.get(v).contains(langs.get(langs.size()-1))){
                            retMap.get(v).add(langs.get(langs.size()-1));
                        }
                    }else{
                        ArrayList<String> prevValue = new ArrayList<>();
                        prevValue.add(entry.getKey());
                        retMap.put(v,prevValue);
                    }
                });
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
            
            Map<String,ArrayList<String>> plMap = new HashMap<>();
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
