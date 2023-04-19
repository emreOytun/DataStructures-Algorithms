package List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListInterface {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(10);
        
        // boolean Add(Object): Sona ekler -> O(1)
        // Her zaman true dondurur.
        list1.add(3);
        list1.add(5);
        list1.add(7);
        System.out.println(list1);

        // void Add(index, Object): Indeksten once ekler -> O(N)
        // NOT**: Index'e dikkat edilmeli, index<=size olabilir.
        list1.add(0, 99);
        System.out.println(list1);

        // E Set(index, Object): Var olan elemani degistirir. -> O(1)
        // Eski degeri dondurur.
        // NOT**: Index'e dikkat edilmeli, index<size olabilir.
        System.out.println(list1.set(3, 98));
        System.out.println(list1);

        // boolean Remove(Object): Girilen eleman varsa siler. -> O(N)
        // Silinip silinmedigini dondurur.
        // NOT**: Verilen eleman bulunamazsa hicbir sey yapmaz.
        // NOT**: Integer degerler tutarken iceriye obje olarak Integer vermelisin yoksa index olarak algilar !!!!!
        list1.remove(new Integer(5));
        list1.remove(new Integer(7));
        System.out.println(list1);

        // E Remove(index): Girilen indeksteki elemani siler. -> O(N)
        // NOT**: Index'e dikkat edilmeli, index<size olabilir.
        list1.remove(0);
        System.out.println(list1);

        // E Get(index): Random-access saglar. -> O(1)
        System.out.println(list1.get(0));

        // boolean Contains(Object): O(N)
        list1.contains(4);

        // int indexOf(Object): Eleman bulunursa index'i, bulunmazsa -1 dondurur.
        System.out.println(list1.indexOf(4));
        System.out.println(list1.indexOf(98));

        int idx = list1.indexOf(4);
        if (idx == -1) System.out.println("4 is not found");
        else System.out.println("4 is found idx: " + idx);

        // Clear the list
        list1.clear();
    
    }

}