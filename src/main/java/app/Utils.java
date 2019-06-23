package app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static double getRoot(double base, double n) {
        return Math.pow(Math.E, Math.log(base)/n);
    }

    public static boolean correspondingElementsNotEquals(List<String> list1, List<String> list2){
        for (int i = 0; i < list1.size(); i++) {
            if(list1.get(i).equals(list2.get(i))){
                return false;
            }
        }
        return true;
    }

    public static Set<String> findDuplicates(List<String> listContainingDuplicates)
    {
        final Set<String> setToReturn = new HashSet<>();
        final Set<String> set1 = new HashSet<>();

        for (String s : listContainingDuplicates)
        {
            if (!set1.add(s))
            {
                setToReturn.add(s);
            }
        }
        return setToReturn;
    }

    public static boolean hasDuplicates(List<String> list){
        return findDuplicates(list).size()>0;
    }
}
