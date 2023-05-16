import java.util.Arrays; //Importing the Arrays class for various array methods
import java.util.Scanner; //Importing the Scanner class for input

class RadixSort //Class declaration
{
    private int operationCounter; // Counter to track the number of operations

    // Constructor to initialize the counter
    public RadixSort() {
        operationCounter = 0;
    }

    // Using counting sort to sort the elements in the basis of significant places
    void countingSort(int[] array, int[] list, int size, int place)
    {
        int[] output = new int[size]; //An array to store all the sorted values temporarily
        int[] count = new int[10];

        for (int i = 0; i < 10; ++i)
        {
            count[i] = 0; // fill array with zeros
            operationCounter++;

            //if statement to calculate number of operations
            if (i >= 1)
                operationCounter += 3;
            else
                operationCounter += 2;
        }

        // Calculate count of elements
        for (int i = 0; i < size; i++)
        {
            //if statement to calculate number of operations
            if (i >= 1)
                operationCounter += 3;
            else
                operationCounter += 2;

            count[(array[i] / place) % 10]++;
            operationCounter++; // Increment the counter for each count operation
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
            operationCounter++; // Increment the counter for each cumulative count operation
        }

        // Place the elements in sorted order
        for (int i = size - 1; i >= 0; i--)
        {
            //if statement to calculate number of operations
            if (i > 1)
                operationCounter += 3;
            else
                operationCounter += 2;

            output[count[(array[i] / place) % 10] - 1] = array[i];
            count[(array[i] / place) % 10]--;
            operationCounter += 2; // Increment the counter for each output and count update
        }

        // Copy the sorted elements back to the original array
        System.arraycopy(output, 0, list, 0, size);
        operationCounter += size; // Increment the counter for each array copy operation
    }

    // Function to get the largest element from an array
    int getMax(int[] array, int n)
    {
        int max = array[0]; //Variable to store the maximum value
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
            operationCounter++; // Increment the counter for each comparison operation
        }
        operationCounter++;
        return max;
    }

    // Main function to implement radix sort
    void radixSort(int[] array, int size)
    {
        // Get maximum element
        int max = getMax(array, size);

        int[] pass1 = new int[size]; //first array
        int[] pass2 = new int[size]; //second array

        operationCounter +=3 ;

        // Using System.arraycopy() method to copy array elements
        System.arraycopy(array, 0, pass1, 0, size);
        operationCounter += size; // Increment the counter for each array copy operation

        boolean checkEven = false;
        operationCounter++;

        // Apply counting sort to sort elements based on place value.
        // if it is 1st,3rd,5th... time to sort, pass1 is participated
        // if it is 2nd,4th, 6th... time to sort, pass2 is participated
        for (int place = 1; max / place > 0; place *= 10)
        {
            if (!checkEven)
            {
                countingSort(pass1, pass2, size, place);
                checkEven = true;
                operationCounter++;
            }
            else
            {
                countingSort(pass2, pass1, size, place);
                checkEven = false;
                operationCounter++;
            }
            operationCounter++;
        }
        if (!checkEven) {
            System.arraycopy(pass1, 0, array, 0, size);
            operationCounter += size; // Increment the counter for each array copy operation
        }
        else {
            System.arraycopy(pass2, 0, array, 0, size);
            operationCounter += size; // Increment the counter for each array copy operation
        }
    }

    // Method or function to retrieve the operation counter value
    public int getOperationCount() {
        return operationCounter;
    }

    // Driver code
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in); //Creating a scanner object for user input
        int N; //The size of an array

        //Menu to greet the user
        System.out.print("==============================\n\n");
        System.out.print("Welcome to Radix Integer\n\n");
        System.out.print("This is a program that'll help sort\n");
        System.out.print("numbers using Radix Sort.\n\n");
        System.out.print("==============================\n\n");

        //The number of size array need to be entered before processing the radix sort
        System.out.print("Before we begin, please enter the size\n");
        System.out.print("of your array to be sorted.\n\n");
        System.out.print("Enter here =>  ");
        N = input.nextInt();

        int [] data = new int[N];

        System.out.print("\nEnter the numbers to be stored in the array\n\n");

        //Insert value or elements into array
        for(int i = 0; i < N; i++)
        {
            System.out.print("Position " + (i+1) + "\t=>  ");
            data[i] = input.nextInt();
        }

        RadixSort rs = new RadixSort(); //Creating object to call the respective methods

        //Display output size of array, sorted array and number of operations
        System.out.print("\n==============================\n\n");
        System.out.println("Size of Array: " + N);

        System.out.println("\nUnsorted Array Before Sort: ");
        System.out.println(Arrays.toString(data));

        rs.radixSort(data, N); //Call upon the radix sort method

        System.out.println("\nSorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));

        System.out.println("\nNumber of Operations: " + rs.getOperationCount());
        System.out.print("\n==============================\n");
    }
}

