import java.util.Arrays;
import java.util.Scanner;

class RadixSort2
{

    // Using counting sort to sort the elements in the basis of significant places
    void countingSort2(float[] array, float[] list, int size, int place)
    {
        float[] output = new float[size];
        int[] count = new int[10];

        // Calculate count of elements
        for (int i = 0; i < size; i++)
            count[(int) (array[i] / place) % 10]++;

        // Calculate cumulative count
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Place the elements in sorted order
        for (int i = size - 1; i >= 0; i--)
        {
            list[count[(int) (array[i] / place) % 10] - 1] = array[i];
            count[(int) (array[i] / place) % 10]--;
        }

        //Copy the array output into a separate array
        System.arraycopy(output, 0, array, 0, size);
    }

    // Function to get the largest element from an array
    float getMax2(float[] array, int n)
    {
        float max = array[0];
        for (int i = 1; i < n; i++)
            if (array[i] > max)
                max = array[i];
        return max;
    }

    // Main function to implement radix sort
    void radixSort2(float[] array, int size)
    {
        // Get maximum element
        float max = getMax2(array, size);

        float[] copy = new float[size];
        System.arraycopy(array, 0, copy, 0, size);

        //remove decimal point by multiplying
        for (int i = 0; i < size; i++)
            copy[i] = copy[i] * 100000;

        float[] pass1 = new float[size];
        float[] pass2 = new float[size];

        // Using System.arraycopy() method to copy array elements
        System.arraycopy(copy, 0, pass1, 0, size);

        boolean checkEven = false;

        // Apply counting sort to sort elements based on place value.
        for (int place = 1; (int) (max * place) > 0; place *= 10)
        {
            if(!checkEven)
            {
                countingSort2(pass1, pass2, size, place);
                checkEven = true;
            }
            else
            {
                countingSort2(pass2, pass1, size, place);
                checkEven = false;
            }
        }

        if(!checkEven) {
            for (int i = 0; i < size; i++)
                pass1[i] = pass1[i] / 100000;
            System.arraycopy(pass1, 0, array, 0, size);
        }else {
            for (int i = 0; i < size; i++)
                pass2[i] = pass2[i] / 100000;
            System.arraycopy(pass2, 0, array, 0, size);
        }
    }

    public static int highestDecimalPoint(float[] arr)
    {
        int maxDecimalPoints = 0;
        for (float f : arr)
        {
            String[] parts = String.valueOf(f).split("\\.");
            if (parts.length > 1)
            {
                int numDecimalPoints = parts[1].length();
                if (numDecimalPoints > maxDecimalPoints)
                {
                    maxDecimalPoints = numDecimalPoints;
                }
            }
        }
        return maxDecimalPoints;
    }

    public static int countDecimalPlaces(float num)
    {
        String[] parts = String.valueOf(num).split("\\.");
        if (parts.length == 1)
        {
            return 0;
        }
        else
        {
            return parts[1].length();
        }
    }

    // Driver code
    public static void main(String[] args)
    {
//        Scanner input = new Scanner(System.in);
//        int N;
//
//        System.out.print("==============================\n\n");
//        System.out.print("Welcome to RADIX lol\n\n");
//        System.out.print("This is a program that'll help sort\n");
//        System.out.print("numbers using Radix Sort lol.\n\n");
//        System.out.print("==============================\n\n");
//
//        System.out.print("Before we begin, please enter the size\n");
//        System.out.print("of your array to be sorted.\n\n");
//        System.out.print("\t=>  ");
//        N = input.nextInt();
//
//        float [] data = new float[N];
//
//        System.out.print("Enter the numbers to be stored in the array\n");
//
//        for(int i = 0; i < N; i++)
//        {
//            System.out.print("Position " + (i+1) + "\t=>  ");
//            data[i] = input.nextFloat();
//        }
//
//        RadixSort2 rs2 = new RadixSort2();
//        rs2.radixSort2(data, N);
//        System.out.println("Sorted Array in Ascending Order: ");
//        System.out.println(Arrays.toString(data));

        float[] data = {21.44445f, 432.8f, 21.44444f, 1.743f, 2111.43562f, 3.3f, 455.9f};
        //float[] data = {123.22f, 32.42349876f,15.77f};
        int size = data.length;
        RadixSort2 rs2 = new RadixSort2();
        rs2.radixSort2(data, size);
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));

        /*
            int maxDecimalPlaces = highestDecimalPoint(data);
            System.out.println("Max decimal places: " + maxDecimalPlaces);
            float num = 123.654454853f;
            int decimalPlaces = countDecimalPlaces(num);
            System.out.println(num); */
    }
}
