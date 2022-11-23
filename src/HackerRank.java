import java.util.*;
import java.util.stream.Collectors;

public class HackerRank {

    public static void closestNumbers(List<Integer> numbers) {
        // Write your code here
        if (numbers.size()<2) return;

        int minDifference = Integer.MAX_VALUE;
        List<Integer> numberData = new ArrayList<>();
        List<String> pairs = new ArrayList<>();

        //convert list to set
        Set<Integer> numberSet = new HashSet<>();
        numberSet.addAll(numbers);

        for (Integer integer : numberSet.stream().sorted().collect(Collectors.toList())) {
            numberData.add(integer);
            int size = numberData.size();

            if(size>1){
                int i = numberData.get(size-2);
                int j = numberData.get(size-1);
                int min = j-i;
                if(min<minDifference){
                    minDifference = min;
                    pairs.clear();
                    pairs.add(i+","+j);
                }else if(min == minDifference){
                    pairs.add(i+","+j);
                }
            }
        }

        for (String pair : pairs) {
            String[] data = pair.split(",");
            System.out.println(data[0]+" "+data[1]);
        }
    }

    public static String palindromeChecker(String s, List<Integer> startIndex, List<Integer> endIndex, List<Integer> subs) {
        // Write your code here
        StringBuilder result = new StringBuilder();

        for(int i=0; i<startIndex.size(); i++){
            String subString = s.substring(startIndex.get(i), endIndex.get(i));
            int substitutions = subs.get(i);

            if(isPalyndrone(subString, substitutions)){
                result.append("1");
            }else {
                result.append("0");
            }
        }

        return result.toString();
    }

    static boolean isPalyndrone(String s, int subs) {

        String original = s;
        // Finding the length of the string
        int length = s.length();

        int changes = 0;

        for (int i = 0; i < length / 2; i++) {

            // If the characters at location
            // i and length-i-1 are same then
            // no change is required
            if (s.charAt(i) == s.charAt(length - i - 1))
                continue;

            // Counting one change operation
            changes += 1;
            if (changes>subs) return false;

            // Changing the character with higher
            // ascii value with lower ascii value
            if (s.charAt(i) < s.charAt(length - i - 1))
                s = s.replace(s.charAt(length - i - 1), s.charAt(i));
            else
                s = s.replace(s.charAt(length - 1), s.charAt(length - i - 1));
        }
        if(s.equals(original)){
            return true;
        }else {
            return false;
        }
    }

    public static String findNumber(List<Integer> arr, int k) {
        // Write your code here
        String response = "NO";
        for (Integer integer : arr) {
            if(integer == k) {
                response = "YES";
                break;
            }
        }

        return response;
    }

    public static List<Integer> oddNumbers(int l, int r) {
        // Write your code here
        List<Integer> oddNumbers = new LinkedList<>();
        for(l=l; l<=r ;l++){
            if((l%2)>0){
                oddNumbers.add(l);
            }
        }

        return oddNumbers;
    }

    public static String getString(String s) {
        // Write your code here
        StringBuilder emptyString = new StringBuilder();
        PriorityQueue<Character> priorityQueue = new PriorityQueue<>();
        int i;

        for(i=0; i<s.length(); i++){
            priorityQueue.add(s.charAt(i));
        }

        while(!priorityQueue.isEmpty()){
            emptyString.append(priorityQueue.peek());
            priorityQueue.remove();

            if(i<s.length()){
                priorityQueue.add(s.charAt(i));
            }
            i++;
        }

        return emptyString.toString();
    }

    public static void main(String[] args){
        //closestNumbers(Arrays.asList(4,4,-2,-1,3));
        System.out.println(findNumber(Arrays.asList(1,2,3,4,5), 1));
    }

    class Chat{
        private List<String> members;
        private List<Integer> files;
        private Map<Integer, String> message;

        public Chat(){
            this.members = new ArrayList<>();
            this.files = new ArrayList<>();
            this.message = new HashMap<>();
        }

        public void add(String[] names){
            this.members.addAll(Arrays.asList(names));
        }
        public void add(int[] ids){
            for (int id : ids) {
                this.files.add(id);
            }
        }
        public void add(int id, String message){
            this.message.put(id, message);
        }

        public void remove(String[] names){
            for (String name : names) {
                boolean isDeleted = false;

                isDeleted = this.members.remove(name);
                if(!isDeleted){
                    System.out.printf("Member with name %s does not exist%n", name);
                }
            }
        }
        public void remove(int[] ids){
            for (int id : ids) {
                boolean isDeleted = false;

                isDeleted = this.files.remove(Integer.valueOf(id));
                if(!isDeleted){
                    System.out.printf("File with id %d does not exist%n", id);
                }
            }
        }
        public void remove(int id){
            Optional<String> value = Optional.ofNullable(this.message.remove(id));
            if (!value.isPresent()){
                System.out.printf("Message with id %d does not exist%n", id);
            }
        }

        void printConversation(){
            System.out.printf("Total number of members in the conversation are %d%n", this.members.size());
            StringBuilder names = new StringBuilder();
            for (String member : this.members) {
                names.append(member).append(" ");
            }
            System.out.printf("The names of these members are %s%n", names);

            System.out.printf("Total number of files in the conversation are %d %n", this.files.size());
            StringBuilder messages = new StringBuilder();
            for (String msg : this.message.values()) {
                messages.append(String.format("'%s'", msg)).append(" ");
            }
            System.out.printf("The messages in the conversation are %s%n", messages);
        }
    }
}
