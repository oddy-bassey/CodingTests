import java.util.ArrayList;
import java.util.List;

public class Luxoft {

    // {2,3,5,7,9,10} {1,2,4,6,7,10,20,22,24}
    public static void main(String[] args){
        int[]A = new int[]{2,3,5,7,9,10};

        int[]B = new int[] {1,2,4,6,7,10,20,22,24};

        for (Integer integer : mergerray(A, B)) {
            System.out.print(integer+", ");
        }

    }

    static Integer[] mergerray(int[]A, int[]B){

        List<Integer> list = new ArrayList<>();
        for (int i : A) {
            list.add(i);
        }
        for (int i : B) {
            list.add(i);
        }
        Integer[] C  = list.stream().sorted().toArray(Integer[] :: new);

        return C;
    }
}
