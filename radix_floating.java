import java.util.Arrays;

class RadixSort2 {

    // Using counting sort to sort the elements in the basis of significant places
    void countingSort2(float array[], float list[], int size, int place) {
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
            list[count[(int) (array[i] * place) % 10] - 1] = array[i];
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

        float[] pass1 = new float[size];
        float[] pass2 = new float[size];

        // Using System.arraycopy() method to copy array elements
        System.arraycopy(array, 0, pass1, 0, size);

        boolean checkEven = false;

        // Apply counting sort to sort elements based on place value.
        for (int place = 1; (int) (max * place) > 0; place *= 10){
            if(!checkEven){
                countingSort2(pass1, pass2, size, place);
                checkEven = true;
            } else{
                countingSort2(pass2, pass1, size, place);
                checkEven = false;
            }
        }

        if(!checkEven)
            System.arraycopy(pass1, 0, array, 0, size);
        else
            System.arraycopy(pass2, 0, array, 0, size);
    }

    public static int highestDecimalPoint(float[] arr) {
        int maxDecimalPoints = 0;
        for (float f : arr) {
            String[] parts = String.valueOf(f).split("\\.");
            if (parts.length > 1) {
                int numDecimalPoints = parts[1].length();
                if (numDecimalPoints > maxDecimalPoints) {
                    maxDecimalPoints = numDecimalPoints;
                }
            }
        }
        return maxDecimalPoints;
    }

    public static int countDecimalPlaces(float num) {
        String[] parts = String.valueOf(num).split("\\.");
        if (parts.length == 1) {
            return 0;
        } else {
            return parts[1].length();
        }
    }

    // Driver code
    public static void main(String args[]) {
        float[] data = {121.5f, 432.8f, 564.1f, 23.7f, 1.3f, 45.9f, 788.2f};
        //float[] data = {123.22f, 32.42349876f,15.77f};
        int size = data.length;
        RadixSort2 rs2 = new RadixSort2();
        rs2.radixSort2(data, size);
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));

//        int maxDecimalPlaces = highestDecimalPoint(data);
//        System.out.println("Max decimal places: " + maxDecimalPlaces);
//        float num = 123.654454853f;
//        int decimalPlaces = countDecimalPlaces(num);
//        System.out.println(num);
    }
}