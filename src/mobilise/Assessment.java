package mobilise;

public class Assessment {
    public static void main(String[] args) {
        int[] ints = {-9, 14, 37, 102};
        //System.out.println(exists(ints, 102)); // true
        System.out.println(exists(ints, 36)); // false

        // 12 23 65 273 75 34 45645: find 23
    }


    static boolean binary_search_recursive(int A[], int key, int low, int high) {
        if (low > high) {
            return false;
        }

        int mid = low + ((high - low) / 2);
        if (A[mid] == key) {
            return true;
        } else if (key < A[mid]) {
            return binary_search_recursive(A, key, low, mid - 1);
        }

        return binary_search_recursive(A, key, mid + 1, high);
    }

    static boolean exists(int[] ints, int k) {
        boolean isFound = binary_search_recursive(ints, k, 0, ints.length-1);
        return isFound;
    }

    static boolean findNumber(int[] ints, int k, int start, int end) {
        boolean result = false;
        int mid = (int) Math.ceil(start + (double) end / 2);

        if(ints.length == 0) return false;
        if(start > end) return false;

        if(ints[mid] > k && mid < ints.length-1){
            result = findNumber(ints, k, start, mid++);

        } else if(ints[mid] < k && mid > 0) {
            result = findNumber(ints, k, mid--, end);

        } else if(ints[mid] == k){
            return true;
        }

        return result;
    }

    public static int calculateTotalPrice(int[] prices, int discount) {
        // Write your code here
        // To debug: System.err.println("Debug messages...");

        int expensivePrice= Integer.MIN_VALUE;
        int total = 0;
        double percDiscount = (double) discount / 100;

        for(int price : prices){
            if(price > expensivePrice){
                expensivePrice = price;
            }
            total += price;
        }
        total -= expensivePrice;
        int discountPrice = (int) (expensivePrice - (expensivePrice * percDiscount));

        return total + discountPrice;
    }
}
