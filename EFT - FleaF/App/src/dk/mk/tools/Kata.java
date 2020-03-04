package dk.mk.tools;

public class Kata
{
    public static int TripleDouble(long num1, long num2)
    {
        String string = String.valueOf(num1);
        String string2 = String.valueOf(num2);
        System.out.println("STring: " + string);

        //Find three of the same char in a row
        int numberOfSameInRow = 0;
        int i;
        char rowChar = 'x';
        for(i = 0; i < string.length(); i++){

            if(numberOfSameInRow == 0){
                numberOfSameInRow++;
            }else if(numberOfSameInRow == 3){
                System.out.println("Found three in a row!: " + string.charAt(i-1));
                rowChar = string.charAt(i-1);
                i = string.length();
            }else if(numberOfSameInRow > 0){
                if(string.charAt(i) == string.charAt(i-1))
                    numberOfSameInRow++;
                else
                    numberOfSameInRow = 1;
            }

            System.out.println("Same number in row: " + numberOfSameInRow);
        }

        System.out.println("Same number found!: " + rowChar);


        //If yes, check next number for the same char, two in a row.
        //Find three of the same char in a row
        numberOfSameInRow = 0;
        rowChar = 'x';
        for(i = 0; i < string2.length(); i++){

            if(numberOfSameInRow == 0){
                numberOfSameInRow++;
            }else if(numberOfSameInRow == 3){
                System.out.println("Found three in a row!: " + string2.charAt(i-1));
                rowChar = string.charAt(i-1);
                i = string.length();
            }else if(numberOfSameInRow > 0){
                if(string.charAt(i) == string.charAt(i-1))
                    numberOfSameInRow++;
                else
                    numberOfSameInRow = 1;
            }

            System.out.println("Same number in row: " + numberOfSameInRow);
        }

        return 0;
    }
}