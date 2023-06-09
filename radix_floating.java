/* 
Assignment 1 CPT212 
This program is the radix sort of floating-point numbers, specifically for Specification 2 in Assignment 1.
Group members:
1. MOHAMAD AMIRUL KHAIRI BIN HASAAN 
2. MIOR MUHAMMAD IRFAN BIN MIOR LATFEE
3. MOHAMAD NAZMI BIN HASHIM
*/

import java.util.Arrays; //Importing the Arrays class to use different array methods
import java.util.Scanner; //Importing the Scanner class for user input

//Class declaration
class RadixSort2
{

    private int operationCounter; // Counter to track the number of operations

    // Constructor to initialize the counter
    public RadixSort2() {
        operationCounter = 0;
    }

    // Using counting sort to sort the elements in the basis of significant places
    void countingSort2(float[] array, float[] list, int size, int place)
    {
        float[] output = new float[size]; //An array to store the sorted values temporarily
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
    float getMax2(float[] array, int n)
    {
        float max = array[0]; //Variable to store the maximum value
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
    void radixSort2(float[] array, int size)
    {
        // Get maximum element
        float max = getMax2(array, size);
        float[] copy = new float[size]; //An array to create a copy of the original

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
        // if it is 1st,3rd,5th... time to sort, pass1 is participated
        // if it is 2nd,4th, 6th... time to sort, pass2 is participated
        for (int place = 1; (int) (max * place) > 0; place *= 10)
        {
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

    //A method to count the number of decimal places
    public static int countDecimalPlaces(float[] array, int size)
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
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in); //Creating a Scanner object for user input
        int N; //Size of the array

        System.out.print("==============================\n\n");
        System.out.print("Welcome to Radix Float Number\n\n");
        System.out.print("This is a program that'll help sort\n");
        System.out.print("numbers using Radix Sort.\n\n");
        System.out.print("==============================\n\n");

        //The number of size array need to be entered before processing the radix sort
        System.out.print("Before we begin, please enter the size\n");
        System.out.print("of your array to be sorted.\n\n");
        System.out.print("Enter here =>  ");
        N = input.nextInt();

        float [] data = new float[N];

        System.out.print("\nEnter the numbers to be stored in the array\n\n");

        //Insert the values into the array
        for(int i = 0; i < N; i++)
        {
            System.out.print("Position " + (i+1) + "\t=>  ");
            data[i] = input.nextFloat();
        }

        RadixSort2 rs2 = new RadixSort2(); //Creating object to call the respective methods
        
        //Display output size of array, sorted array and number of operations
        System.out.print("\n==============================\n\n");
        System.out.println("Size of Array: " + N);

        System.out.println("\nUnsorted Array Before Sort: ");
        System.out.println(Arrays.toString(data) + "\n");

        rs2.radixSort2(data, N); //Call upon the radix sort method

        System.out.println("\nSorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));

        System.out.println("\nNumber of Operations: " + rs2.getOperationCount());
        System.out.print("\n==============================\n");
    }
}
