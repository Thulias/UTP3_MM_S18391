/**
 *
 *  @author Majewski Mateusz S18391
 *
 */

package zad1;


import java.io.File;
import java.io.IOException;
import java.util.*;

public class Anagrams {
    
    private final List<String> words = new ArrayList<>();
    
    public List<List<String>> getSortedByAnQty(){
        List<List<String>> retLists = new ArrayList<>();
        List<String> tmpWords = new ArrayList<>(words);
    
        for(int i = 0; i < tmpWords.size() ; i++) {
            List<String> toRet = new ArrayList<>();
            toRet.add(tmpWords.get(i));
            for(int j = 1; j < tmpWords.size(); j++) {
                if(isAnagram(tmpWords.get(i), tmpWords.get(j))){
                    toRet.add(tmpWords.get(j));
                    tmpWords.remove(j--);
                }
            }
            retLists.add(new ArrayList<>(toRet));
            tmpWords.remove(i--);
        }
        retLists.sort((o1, o2) -> Integer.compare(o2.size(), o1.size()));
        
        for(int i = 0; i < retLists.size() ; i++) {
            ArrayList<String> arr = new ArrayList<>(retLists.get(i));
            
            String[] tab = new String[arr.size()];
            for(int j = 0; j < arr.size() ; j++)
                tab[j] = arr.get(j);
            Arrays.sort(tab);
            
            retLists.set(i,new ArrayList<>(Arrays.asList(tab)));
        }
        return retLists;
    }
    public String getAnagramsFor(String word){
        StringBuilder sb = new StringBuilder(word+": [");
        int wordsInSb = 0;
        for(String s : words){
            if(isAnagram(word,s) && !word.equals(s)){
                sb.append(s).append(", ");
                wordsInSb++;
            }
        }
        if(wordsInSb>0){
            sb.deleteCharAt(sb.length()-1).deleteCharAt(sb.length()-1);
        }
        sb.append("]");
        return sb.toString();
    }
    private boolean isAnagram(String s1, String s2){
        if(s1.length()!=s2.length()){
            return false;
        }else{
            ArrayList<Character> letts1 = new ArrayList<>();
            ArrayList<Character> letts2 = new ArrayList<>();
            char[] ctab1 = s1.toCharArray();
            char[] ctab2 = s2.toCharArray();
            Arrays.sort(ctab1);
            Arrays.sort(ctab2);
            for(char c : ctab1) letts1.add(c);
            for(char c : ctab2) letts2.add(c);
            
            return letts1.equals(letts2);
        }
        
    }
    
    // Constructors:
    
    Anagrams(String path){
        try{
        Scanner sc = new Scanner(new File(path));
        while(sc.hasNext()){
            words.add(sc.next());
        }
        sc.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    
}  
