import java.util.Arrays;

class RadixSort {

    // Using counting sort to sort the elements in the basis of significant places
    void countingSort(int array[], int size, int place) {
        int[] output = new int[size];

        //int[] count = new int[max + 1];
        int[] count = new int[10];

        for (int i = 0; i < 10; ++i)
            count[i] = 0; // fill array with zeros

        // Calculate count of elements
        for (int i = 0; i < size; i++)
            count[(array[i] / place) % 10]++;

        // Calculate cumulative count
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Place the elements in sorted order
        for (int i = size - 1; i >= 0; i--) {
            output[count[(array[i] / place) % 10] - 1] = array[i];
            System.out.println(Arrays.toString(output));
            count[(array[i] / place) % 10]--;
        }


        for (int i = 0; i < size; i++)
            array[i] = output[i];
    }

    // Function to get the largest element from an array
    int getMax(int array[], int n) {
        int max = array[0];
        for (int i = 1; i < n; i++)
            if (array[i] > max)
                max = array[i];
        return max;
    }

    // Main function to implement radix sort
    void radixSort(int array[], int size) {
        // Get maximum element
        int max = getMax(array, size);

        // Apply counting sort to sort elements based on place value.
        for (int place = 1; max / place > 0; place *= 10) {
            countingSort(array, size, place);
            System.out.println(Arrays.toString(array));
        }
    }

    // Driver code
    public static void main(String args[]) {
        int[] data = { 275, 87, 426, 61, 409, 170, 677, 503,  };
        //int[] data = { 9,5,7,12,2 };
        int size = data.length;
        System.out.println("Array Before Sorted: \n" + Arrays.toString(data));
        RadixSort rs = new RadixSort();
        rs.radixSort(data, size);
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));
    }
}
