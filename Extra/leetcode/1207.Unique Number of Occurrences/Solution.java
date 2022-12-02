import java.util.ArrayList;
import java.util.Arrays;


class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Arrays.sort(arr);
        ArrayList<Integer> occ = new ArrayList<>();

        int cont = 1;
        int prev = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == prev) cont++;
            else {
                prev = arr[i];
                if (occ.contains(cont)) return false;
                else {
                    occ.add(cont);
                    cont = 1;
                }
            }
        }
      
        if (arr.length == 2) return arr[0] == arr[1];
        return true;
    }
}


//VERY SLOW

/* 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
      
        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            
            if (map.containsKey(val)) map.put(val, map.get(val)+1);
            else map.put(val, 1);
        }

        List<Integer> values = new ArrayList<>();

        for (Integer i : map.keySet()) {
            values.add(map.get(i));
        }

        Collections.sort(values);

        System.out.println(values.toString());

        //if (values.size() <= 2) return values.get(0) != values.get(1);
        
        for (int i = 0; i < values.size()-1; i++) {
            if (values.get(i) == values.get(i+1)) return false;
        }
        return true;
    }
}
*/
