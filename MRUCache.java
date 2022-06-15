import java.util.*;
public class MRUCache
{
    Set<Integer> cache;
    int capacity;
    double hit=0.0;
    double access =0.0;
    public MRUCache(int size) {
        cache = new LinkedHashSet<Integer>(capacity);
        capacity = size;
    }
    public boolean get(int key) {

        if (!cache.contains(key))
            return false;
        hit++;
        cache.remove(key);
        cache.add(key);
        return true;
    }
    public void refer(int key)
    {
        access++;
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
        System.out.println("hitrate " + hit/access);
        System.out.println("hits: " + hit);
        System.out.println("Accesses: " + access);
    }
    public void put(int key)
    {
        int lastkey=0;
        Iterator<Integer> iterator = cache.iterator();
        // If already present, then
        // remove it first. Note that
        // we are going to add later
        if (cache.contains(key)) {

            cache.remove(key);

        // If cache size is full, remove the least
        // recently used.

        }
        else if (cache.size() == capacity) {

            Iterator<Integer> itr = cache.iterator();
            while (itr.hasNext()) {
                lastkey = itr.next();

            }
            cache.remove(lastkey);
        }

        cache.add(key);
    }
    public static void main(String[] args)
    {
        MRUCache ca = new MRUCache(3);
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
