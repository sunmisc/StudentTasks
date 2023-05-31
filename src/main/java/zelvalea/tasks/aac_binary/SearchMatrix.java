package zelvalea.tasks.aac_binary;

import java.util.Map;

public class SearchMatrix {

    public static void main(String[] args) {
        Integer[][] matrix = new Integer[][]{
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,15,16},
                {17,18,19,20,21,22},
        };

        System.out.println(binarySearchMatrix(matrix, 21));
    }

    // O(log(m*n))
    public static
    <E extends Comparable<? super E>> Map.Entry<Integer,Integer>
    binarySearchMatrix(E[][] matrix, E key) {
        int cols = matrix[0].length;
        int low = 0, high = matrix.length * cols - 1;

        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            int r = mid / cols, c = mid % cols;

            E midVal = matrix[r][c];

            int cmp = midVal.compareTo(key);

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return Map.entry(r,c);
        }

        return null;
    }
}
