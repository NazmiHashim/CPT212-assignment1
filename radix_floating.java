import java.util.Arrays;
import java.util.Scanner;

class RadixSort2 {

    private int operationCounter; // Counter to track the number of operations

    // Constructor to initialize the counter
    public RadixSort2() {
        operationCounter = 0;
    }

    // Using counting sort to sort the elements in the basis of significant places
    void countingSort2(float array[], float list[], int size, int place) {
        float[] output = new float[size];
        int[] count = new int[10];

        // Calculate count of elements
        for (int i = 0; i < size; i++)
        {
            //if statement to calculate number of operations
            if (i >= 1)
                operationCounter += 3;
            else
                operationCounter += 2;

            count[(int) (array[i] / place) % 10]++;
            operationCounter++;
        }

        // Calculate cumulative count
        for (int i = 1; i < 10; i++)
        {
            //if statement to calculate number of operations
            if (i > 1)
                operationCounter += 3;
            else
                operationCounter += 2;

            count[i] += count[i - 1];
            operationCounter++;
        }

        // Place the elements in sorted order
        for (int i = size - 1; i >= 0; i--)
        {
            //if statement to calculate number of operations
            if (i > 1)
                operationCounter += 3;
            else
                operationCounter += 2;

            output[count[(int) (array[i] / place) % 10] - 1] = array[i];
            count[(int) (array[i] / place) % 10]--;
            operationCounter += 2;
        }

        for (int i = 0; i < size; i++)
        {
            //if statement to calculate number of operations
            if (i >= 1)
                operationCounter += 3;
            else
                operationCounter += 2;

            list[i] = output[i];
            operationCounter++;
        }
    }

    // Function to get the largest element from an array
    float getMax2(float array[], int n)
    {
        float max = array[0];
        operationCounter++;

        for (int i = 1; i < n; i++)
        {
            //if statement to calculate number of operations
            if (i > 1)
                operationCounter += 3;
            else
                operationCounter += 2;

            if (array[i] > max)
            {
                max = array[i];
                operationCounter += 2;
            }
            else
                operationCounter++;
        }
        operationCounter++;
        return max;
    }

    // Main function to implement radix sort
    void radixSort2(float array[], int size) {
        // Get maximum element
        float max = getMax2(array, size);
        float[] copy = new float[size];

        int maxDecimalPoints = countDecimalPlaces(array, size); //new code
        System.out.println(maxDecimalPoints); //new code

        operationCounter += 2;

        System.arraycopy(array, 0, copy, 0, size);
        operationCounter += size; // Increment the counter for each array copy operation

        for (int i = 0; i < size; i++)
        {
            //if statement to calculate number of operations
            if (i >= 1)
                operationCounter += 3;
            else
                operationCounter += 2;

            copy[i] = copy[i] * (float) Math.pow(10, maxDecimalPoints); //new code
            operationCounter++;
        }

        float[] pass1 = new float[size];
        float[] pass2 = new float[size];

        operationCounter += 2;

        // Using System.arraycopy() method to copy array elements
        System.arraycopy(copy, 0, pass1, 0, size);
        operationCounter += size; // Increment the counter for each array copy operation

        boolean checkEven = false;
        operationCounter++;

        // Apply counting sort to sort elements based on place value.
        for (int place = 1; (int) (max * place) > 0; place *= 10){
            if(!checkEven)
            {
                countingSort2(pass1, pass2, size, place);
                checkEven = true;
                operationCounter++;
            }
            else
            {
                countingSort2(pass2, pass1, size, place);
                checkEven = false;
                operationCounter++;
            }
        }


        if(!checkEven)
        {
            for (int i = 0; i < size; i++)
            {
                //if statement to calculate number of operations
                if (i >= 1)
                    operationCounter += 3;
                else
                    operationCounter += 2;

                pass1[i] = pass1[i] / (float) Math.pow(10, maxDecimalPoints); //new code
                operationCounter++;
            }
            System.arraycopy(pass1, 0, array, 0, size);
            operationCounter += size; // Increment the counter for each array copy operation
        }
        else
        {
            for (int i = 0; i < size; i++)
            {
                //if statement to calculate number of operations
                if (i >= 1)
                    operationCounter += 3;
                else
                    operationCounter += 2;

                pass2[i] = pass2[i] / (float) Math.pow(10, maxDecimalPoints); //new code
                operationCounter++;
            }
            System.arraycopy(pass2, 0, array, 0, size);
            operationCounter += size; // Increment the counter for each array copy operation
        }
    }

    public static int countDecimalPlaces(float array[],int size)
    {
        int maxDecimalPoints = 0;
        for (int i = 0; i < size; i++) {
            String numberD = String.valueOf(array[i]);
            numberD = numberD.substring(numberD.indexOf("."));
            if(maxDecimalPoints < numberD.length())
                maxDecimalPoints = numberD.length();
        }
        return maxDecimalPoints-1;
    }

    // Method or function to retrieve the operation counter value
    public int getOperationCount() {
        return operationCounter;
    }

    // Driver code
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int N;

        System.out.print("==============================\n\n");
        System.out.print("Welcome to RADIX lol\n\n");
        System.out.print("This is a program that'll help sort\n");
        System.out.print("numbers using Radix Sort lol.\n\n");
        System.out.print("==============================\n\n");

        System.out.print("Before we begin, please enter the size\n");
        System.out.print("of your array to be sorted.\n\n");
        System.out.print("\t=>  ");
        N = input.nextInt();

        float [] data = new float[N];

        System.out.print("Enter the numbers to be stored in the array\n");

        for(int i = 0; i < N; i++)
        {
            System.out.print("Position " + (i+1) + "\t=>  ");
            data[i] = input.nextFloat();
        }

        RadixSort2 rs2 = new RadixSort2();
        rs2.radixSort2(data, N);
        
        //Display output size of array, sorted array and number of operations
        System.out.println("Size of Array: " + N);
        System.out.println("Unsorted Array Before Sort: \n" + Arrays.toString(data));
        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));
        System.out.println("Number of Operations: " + rs2.getOperationCount());
    }
}
