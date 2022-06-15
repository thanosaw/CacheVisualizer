
/**
 * Basic LRU Cache Simulator
 */

import java.util.*;
import java.util.Arrays;

public class ARCCache
{
    Set<Integer> T1;
    Set<Integer> T2;
    Set<Integer> B1;
    Set<Integer> B2;
    int          capacity;
    int[]        FRQ      = new int[10];
    double       hits     = 0.0;
    double       accesses = 0.0;


    public ARCCache(int size)
    {
        capacity = size;
        T1 = new LinkedHashSet<Integer>(capacity);
        T2 = new LinkedHashSet<Integer>(capacity);
        B1 = new LinkedHashSet<Integer>(capacity);
        B2 = new LinkedHashSet<Integer>(capacity);
    }


    public boolean get(int key)
    {
        // Accesses to find if a key is present already, "hits"
        if (!T1.contains(key))
            return false;
// if (!B1.isEmpty()) {
// if (!B1.contains(key))
// B1.add(key);
// }

        T1.remove(key);
        T1.add(key);
        hits++;
        return true;
    }


    public void setT2()
    {
// System.out.println("SETT2");
        int[] temp = new int[10];
        for (int i=0;i<10;i++) {
          temp[i]=FRQ[i];
        }
        for (int i = 0; i < capacity; i++)
        {
            //System.out.println("Size: " + T2.size());


                System.out.println(getIndexOfLargest(temp));
                if (!T2.contains(getIndexOfLargest(temp)));
                {
                    T2.add(getIndexOfLargest(temp));
                }

                //System.out.println("Largest: " + getIndexOfLargest(nums));
                temp[getIndexOfLargest(temp)] = 0;

                if (T2.size() > capacity)
                {
                    int firstKey = T2.iterator().next();
                    //System.out.println(firstKey);
                    T2.remove(firstKey);
                    if (!B2.contains(firstKey) && B2.size() == capacity)
                    {
                        int firstKey1 = B2.iterator().next();
                        B2.remove(firstKey1);
                        B2.add(firstKey);

                    }
                    else if (!B2.contains(firstKey))
                    {
                        B2.add(firstKey);
                    }
                }

        }

    }


    public int getIndexOfLargest(int[] array)
    {
        // Find the most common numbers
        if (array == null || array.length == 0)
            return -1; // null or empty

        int largest = 1;
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] >= array[largest])
                largest = i;
        }
        return largest; // position of the first largest found
    }


    public void refer(int key)
    {
        // Access a key, if it's not present put()
        accesses++;
        FRQ[key]++;
//        for (int i=0;i<10;i++) {
//            System.out.print(FRQ[i] + " ");
//        }
        //System.out.print(key + " ");
        setT2();
        if (get(key) == false)
            put(key);
    }


    public void display()
    {
        Iterator<Integer> itr = T1.iterator();
        Iterator<Integer> itr2 = T2.iterator();
        Iterator<Integer> itr3 = B1.iterator();
        Iterator<Integer> itr4 = B2.iterator();

        System.out.print("T1: ");
        while (itr.hasNext())
        {
            System.out.print(itr.next() + " ");

        }
        System.out.println();
        System.out.println("T2: ");
        while (itr2.hasNext())
        {
            System.out.print(itr2.next() + " ");

        }
        System.out.println();
        System.out.println("B1: ");
        while (itr3.hasNext())
        {
           System.out.print(itr3.next() + " ");

        }
        System.out.println();
        System.out.println("B2: ");
        while (itr4.hasNext())
        {
             System.out.print(itr4.next() + " ");

        }
//        System.out.println("hitrate " + hits / accesses);
//        System.out.println("hits: " + hits);
//        System.out.println("Accesses: " + accesses);

    }


    public void put(int key)
    {
        // If already present, then
        // remove it first. Note that
        // we are going to add later

        if (T1.contains(key))
        {

            T1.remove(key);

        }

        // If cache size is full, remove the least
        // recently used.
        else if (T1.size() == capacity)
        {
            int firstKey = T1.iterator().next();
            T1.remove(firstKey);
            if (!B1.contains(firstKey) && B1.size() == capacity)
            { // Testing whether to add the key to B1, don't add if full or
              // already present
                int firstKey1 = B1.iterator().next();
                B1.remove(firstKey1);
                B1.add(firstKey);

            }
            else if (!B1.contains(firstKey))
            {
                B1.add(firstKey);
            }
        }

        T1.add(key);
    }


    public static void main(String[] args)
    {
        ARCCache ca = new ARCCache(3);


        ca.refer(7);
        ca.refer(6);
        ca.refer(5);
        ca.refer(2);
        ca.refer(4);
        ca.refer(3);
        ca.refer(0);
        ca.refer(8);
        ca.refer(8);

        ca.refer(7);
        ca.refer(6);
        ca.refer(5);
        ca.refer(2);
        ca.refer(4);
        ca.refer(3);
        ca.refer(0);
        ca.refer(8);
        ca.refer(8);





        ca.display();
    }
}
