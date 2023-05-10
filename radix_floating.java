import java.util.Arrays;

class RadixSort2 {

    // Using counting sort to sort the elements in the basis of significant places
    void countingSort2(float array[], int size, int place) {
        float[] output = new float[size];
        int max = (int) array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] > max)
                max = (int) array[i];
        }
        int[] count = new int[10];

        // Calculate count of elements
        for (int i = 0; i < size; i++)
            count[(int) (array[i] * place) % 10]++;

        // Calculate cumulative count
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Place the elements in sorted order
        for (int i = size - 1; i >= 0; i--) {
            output[count[(int) (array[i] * place) % 10] - 1] = array[i];
            count[(int) (array[i] * place) % 10]--;
        }

        for (int i = 0; i < size; i++)
            array[i] = output[i];
    }

    // Function to get the largest element from an array
    float getMax2(float array[], int n) {
        float max = array[0];
        for (int i = 1; i < n; i++)
            if (array[i] > max)
                max = array[i];
        return max;
    }

    // Main function to implement radix sort
    void radixSort2(float array[], int size) {
        // Get maximum element
        float max = getMax2(array, size);

        // Apply counting sort to sort elements based on place value.
        for (int place = 1; (int) (max * place) > 0; place *= 10)
            countingSort2(array, size, place);
    }

    // Driver code
    public static void main(String args[]) {
        float[] data = {121.5f, 432.8f, 564.1f, 23.7f, 1.3f, 45.9f, 788.2f};
        int size = data.length;
        RadixSort2 rs2 = new RadixSort2();
        rs2.radixSort2(data, size);
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));
    }
}