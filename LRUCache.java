
/**
 * Basic LRU Cache Simulator
 */

import java.util.*;
public class LRUCache
{
    Set<Integer> cache;
    int capacity;
    double hits=0.0;
    double accesses =0.0;
    public LRUCache(int size) {
        cache = new LinkedHashSet<Integer>(capacity);
        capacity = size;
    }
    public boolean get(int key) {
        //Accesses to find if a key is present already, "hits"
        if (!cache.contains(key))
            return false;
        cache.remove(key);
        cache.add(key);
        hits++;
        return true;
    }
    public void refer(int key)
    {
        //Access a key, if it's not present put()
        accesses++;
        if (get(key) == false)
           put(key);
    }
    public void display()
    {
        Iterator<Integer> itr = cache.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");

        }
        System.out.println();
        System.out.println("hitrate " + hits/accesses);
        System.out.println("hits: " + hits);
        System.out.println("Accesses: " + accesses);
    }
    public void put(int key)
    {
        // If already present, then
        // remove it first. Note that
        // we are going to add later

        if (cache.contains(key))
        {

            cache.remove(key);
        }

        // If cache size is full, remove the least
        // recently used.
        else if (cache.size() == capacity) {
            int firstKey = cache.iterator().next();
            cache.remove(firstKey);
        }

        cache.add(key);
    }
    public static void main(String[] args)
    {
        LRUCache ca = new LRUCache(3);
        ca.refer(0);
        ca.refer(1);
        ca.refer(2);
        ca.refer(0);
        ca.refer(1);
        ca.refer(3);
        ca.refer(0);
        ca.refer(3);
        ca.refer(1);
        ca.refer(2);
        ca.refer(1);

        ca.display();
    }
}
