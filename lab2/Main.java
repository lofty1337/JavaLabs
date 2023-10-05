// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println("Longest substring: ");
        System.out.println(longestSubstring("zxcqwexxxabcdefgh"));

        int[] arr1={0,13,1337,9939};
        int[] arr2={1,14,1437,9999};
        System.out.println("Merged array: ");
        int[] mergedArray=mergeSortedArrays(arr1, arr2);
        for (int i=0; i<mergedArray.length; i++)
            System.out.println(mergedArray[i]);

        System.out.println("Max subarray sum: ");
        int[] arr3={0,1,-2,333,999,1222,-13,4444,-200};
        System.out.println(maxSubarraySum(arr3));

        System.out.println("Matrix: ");
        int[][] matrix = new int[3][3];
        int k = 1;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                matrix[i][j]=k*k;
                k++;
                System.out.print(matrix[i][j]);
                System.out.print(' ');
                if ((k%3)==1)
                    System.out.println('\n');
            }
        }
        k=1;
        System.out.println('\n');
        int[][] rotatedClockwise = rotateClockwise(matrix);
        System.out.println("Rotated clockwise matrix: ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                k++;
                System.out.print(rotatedClockwise[i][j]);
                System.out.print(' ');
                if ((k%3)==1)
                    System.out.println('\n');
            }
        }
        System.out.println('\n');
        System.out.println("Rotated counter clockwise matrix again: ");
        int[][] reversed = rotateCounterClockwise(rotatedClockwise);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                k++;
                System.out.print(reversed[i][j]);
                System.out.print(' ');
                if ((k%3)==1)
                    System.out.println('\n');
            }
        }

        int arr4[] = {1,2,3,4,5};
        int pairArray[] = findPair(arr4, 7);
        System.out.println("Target elements: ");
        System.out.println(pairArray[0]);
        System.out.println(pairArray[1]);

        System.out.println("Sum of elements: ");
        System.out.println(sum2DArray(matrix));

        System.out.println("Max elements: ");
        int[] maxElements = maxElements(matrix);
        for (int i = 0;i<maxElements.length; i++)
            System.out.println(maxElements[i]);
    }
    //1
    public static String longestSubstring(String str) {
        int n = str.length();
        int maxLength = 0;
        String longestSubstring = "";

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substring = str.substring(i, j);
                if (isUnique(substring) && substring.length() > maxLength) {
                    maxLength = substring.length();
                    longestSubstring = substring;
                }
            }
        }

        return longestSubstring;
    }

    public static boolean isUnique(String str) {
        int[] charCount = new int[256];

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (charCount[c] > 0) {
                return false;
            }
            charCount[c]++;
        }

        return true;
    }
    //2
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;
        int[] mergedArray = new int[n1 + n2];

        int i = 0, j = 0, k = 0;
        while (i < n1 && j < n2) {
            if (arr1[i] <= arr2[j]) {
                mergedArray[k] = arr1[i];
                i++;
            } else {
                mergedArray[k] = arr2[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            mergedArray[k] = arr1[i];
            i++;
            k++;
        }

        while (j < n2) {
            mergedArray[k] = arr2[j];
            j++;
            k++;
        }

        return mergedArray;
    }
    //3
    public static int maxSubarraySum(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
    //4
    public static int[][] rotateClockwise(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - 1 - i] = matrix[i][j];
            }
        }

        return rotated;
    }
    //8
    public static int[][] rotateCounterClockwise(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[n - 1 - j][i] = matrix[i][j];
            }
        }

        return rotated;
    }
    //5
    public static int[] findPair(int[] arr, int target) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[] {arr[i], arr[j]};
                }
            }
        }
        return null;
    }
    //6
    public static int sum2DArray(int[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }
    //7
    public static int[] maxElements(int[][] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int max = arr[i][0];
            for (int j = 1; j < arr[i].length; j++) {
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
            result[i] = max;
        }
        return result;
    }
}
