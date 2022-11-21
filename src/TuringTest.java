import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TuringTest {

    public static int calcPoints(String[] ops){
        int result = Integer.MIN_VALUE;
        int score = 0, tempTotalScore = 0;
        /*
         * OPS:
         * x : add point
         * + : add last 2 points
         * C : terminate last recent point
         * D : double last point
         */
        List<Integer> scores = new ArrayList<>();
        for (String op : ops) {
            switch (op){
                case "+" :
                    score = scores.get(scores.size()-1) + scores.get(scores.size()-2);
                    scores.add(score);
                    tempTotalScore+=score;
                    break;
                case "D":
                    score = scores.get(scores.size()-1)*2;
                    scores.add(score);
                    tempTotalScore+=score;
                    break;
                case "C":
                    tempTotalScore -= scores.get(scores.size()-1);
                    scores.remove(scores.size()-1);
                    break;
                default:
                    int value = Integer.valueOf(op);
                    scores.add(value);
                    tempTotalScore += value;
            }
        }
        result = tempTotalScore;

        return result;
    }// A nifi processor which checks to see if a media file retrieved from the media metadata exists on the s# bucket and then updates the metadata with url

    public static void main(String[] args){
        Optional<String> data = Optional.of("");

        System.out.println(TuringTest.calcPoints(new String[] {"5", "2", "C", "D", "+"}));;
    }

    public static boolean binarySearch(int arr[], int first, int last, int key){
        if (last>=first){
            int mid = first + (last - first)/2;
            if (arr[mid] == key){
                return true;
            }
            if (arr[mid] > key){
                return binarySearch(arr, first, mid-1, key);//search in left subarray
            }else{
                return binarySearch(arr, mid+1, last, key);//search in right subarray 2144086898
            }
        }
        return false;
    }
}
