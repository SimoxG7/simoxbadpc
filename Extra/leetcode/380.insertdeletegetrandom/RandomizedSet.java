import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

class RandomizedSet {
    
    private final List<Integer> set;
    private final Map<Integer, Boolean> map;
    private final Random random;
    private int size;

    public RandomizedSet() {
        set = new ArrayList<>();
        random = new Random();
        size = 0;
        map = new TreeMap<>();
    }
    
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, true);
        set.add(val);
        size++;
        return true;
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        map.remove(val, true);
        set.remove((Integer)val);
        size--;
        return true;
    }
    
    public int getRandom() {
        if (size > 1) return set.get(random.nextInt(size));
        else return set.get(0);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */