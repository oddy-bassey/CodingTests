package coderbyte;

public class CoderByte {

    public static String WordSplit(String[] strArr) {
        // code goes here
        StringBuilder firstWord = new StringBuilder("");
        StringBuilder secondWord = new StringBuilder("");

        String splitString = strArr[0], searchString = strArr[1];
        int startIndex = 0;
        int finalIndex = splitString.length()-1;

        for (int i=0; i<=finalIndex; i++){
            firstWord.append(splitString.charAt(i));
            if(searchString.contains(firstWord.toString())){
                secondWord.setLength(0);
                secondWord.append(splitString.substring(i+1, finalIndex+1));
                if (searchString.contains(secondWord.toString())){
                    return String.format("%s,%s", firstWord, secondWord);
                }
            }
        }

        return "not possible";
    }


    public static String MovingMedian(int[] arr) {
        // code goes here
        int total = arr[1];
        int nutSize = 1;
        System.out.print(arr[1]);
        for (int i=2; i<=arr.length-1; i++){
            nutSize+=1;
            total = (total+arr[i]);
            System.out.print(","+(int)Math.ceil(total/nutSize));
        }
        return "";
    }

    public static void main(String[] args){
        //System.out.println(WordSplit(new String[] {"baseball", "a,all,b,ball,bas,base,cat,code,d,e,quit,z"}));
        System.out.println(MovingMedian(new int[] {3, 0, 0, -2, 0, 2, 0, -2}));
    }
}
