#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SIZE_INPUT 264

/* @IgnatSilik Hi, I would like to buy your A Forest of False Idols listed for 14 chaos in Standard */
/* Read the string above and save the relevant information to the txt-file "history.txt */
/* First line in "history.txt" is the next line to be printet */

/* Make a function that searches for a specific word or spaces */
/* Make a function that copies a specific number of chars from one array to the other */
/* MAKE FILENAME GLOBAL */
/* MAKE IT CHECK FOR FILE EXISTENCE, IF NO, CREATE FILE */


/* DELETE Read specific line */
int trash()
{
    static const char filename[] = "history.txt"; /* Making array that contains the filename */
    FILE *file = fopen(filename, "r"); /* ??Opens the file in read-only mode??? */
    int count = 0;

    if(file != NULL)
    {
        char line[256]; /* Array that cointains a line?? */
        while(fgets(line, sizeof line, file) != NULL)
        {
            if(count == 0) /* What line number do you want?*/
            {
                printf(line);
            }
            else
                count++;
        }
        fclose(file);
    }
    else
    {
        printf("The file does not exist!");
    }
}

/* DELETE NOT USEFULL, FOUND ATOI() */
int convertCharToInt(char *str)
{
    /* Getting the length of the string, and the number */
    int lengthString = 0;
    while(str[lengthString] != '\0')
        lengthString++;

    int i, intNumber = 0;

    intNumber = atoi(str);
    printf("%d", intNumber);



    return 0;
}



int getNextLineToWrite()
{
    static const char filename[] = "history.txt"; /* Making array that contains the filename */
    FILE *file = fopen(filename, "r"); /* ??Opens the file in read-only mode??? */
    char nextLineToWriteC[5]; /* Char array to contain the number, 5 = 4 digits */
    if(file != NULL)
    {
        fgets(nextLineToWriteC, sizeof nextLineToWriteC, file); /* Gets the first line and saves it into nextLineToWriteC*/
        fclose(file);
    }
    else
    {
        printf("The file does not exist!\n");
        return 0;
    }

    int nextLineToWriteInt = atoi(nextLineToWriteC); /* Converting from char to int (the number from the file) */


    if(nextLineToWriteInt < 1)
    {
        printf("Could not read the NextLineToWrite!");
        return 0;
    }

    return nextLineToWriteInt; /* Converting from char to int (the number from the file) */

}

/* returns 0 if error */
int cutTheString(char *str) /* Get the relevant data from string */
{
    //printf("Before: %s\n", str); //TEMP
    int localSize = MAX_SIZE_INPUT;
    char item[MAX_SIZE_INPUT];
    char price[MAX_SIZE_INPUT];
    char league[MAX_SIZE_INPUT];

    /* Cut the first useless part (counting 8 spaces) */
    const char first = ' ';
    int i = 0, countChar = 0;
    while(i < 8)
    {
        if(str[countChar] == ' ')
            i++; /* How many spaces that we have found. */
        countChar++; /* Where we are in the array */
    }

    str = str + countChar; /* Move the pointer to the start of item name */
    //localSize -= countChar; reconfigure the size
    /* Save the item name and cut it out (search for word "listed") */
    int trigger = 1;
    countChar = 0;

    while(trigger) //make so that it does not go beyond the array
    {
        if(str[countChar] == 'l')
        {
            //printf("FOUND 'l'"); //TEMP
            if(str[countChar+1] == 'i')
            {
                //printf("FOUND 'i'"); //TEMP
                if(str[countChar+2] == 's')
                {
                    if(str[countChar+3] == 't')
                    {
                        if(str[countChar+4] == 'e')
                        {
                            if(str[countChar+5] == 'd')
                            {
                                //printf("FOUND word!"); //TEMP
                                trigger = 0; /* Found word! */
                            }
                            else
                                countChar++;
                        }
                        else
                            countChar++;
                    }
                    else
                        countChar++;
                }
                else
                    countChar++;
            }
            else
                countChar++;
        }
        else
        {
            //printf("ONE UP!"); //TEMP
            countChar++;
        }

    }

    /* At this point the countchar is placed on 'l' */

    /* Copy item from str to item */
    for(i=0; i< countChar; i++)
    {
        item[i] = str[i];
    }
    item[i-1] = '\0'; /* Ending the string*/

    str = str + countChar; /* Moves the *str so it is on 'l' in 'listed' */
    countChar = 0;
    /* Get the price (search for an integer) */
    trigger = 1;
    while(trigger)
    {
        if(isdigit(str[countChar]))
            trigger = 0; /* found the price*/
        else
            countChar++;
    }

    str = str + countChar; /* Move the *str to start at the price */
    countChar = 0;

    /* Find the length of the price (search for 2 spaces) (DUPLICATE) */
    i = 0;
    while(i < 2)
    {
        if(str[countChar] == ' ')
            i++; /* How many spaces that we have found. */
        countChar++; /* Where we are in the array */
    }

    /* Copy the price, countChar is on end price char (DUPLICATE) */
    for(i=0; i< countChar; i++)
    {
        price[i] = str[i];
    }
    price[i-1] = '\0'; /* Ending the string*/

    str = str + countChar; /* Moving the *str past the price */
    countChar = 0; /* resetting the counter */


    /* Find start of the league, count 2 spaces */
    i = 0;
    while(i < 1)
    {
        if(str[countChar] == ' ')
            i++; /* How many spaces that we have found. */
        countChar++; /* Where we are in the array */
    }
    str = str + countChar; /* Moving the *str to the start of league */

    /* Copy the league from *str to league */
    strcpy(league, str); /* Coping the league from *str to league */
    league[strlen(str)-1] = '\0'; /* Ending the league string */

    //printf("End league?: %c\n", league[8]); //TEMP
    //printf("Did it move: %s\n", str); //TEMP
    printf("Item name: %s\n", item); //TEMP
    printf("Price: %s\n", price); //TEMP
    printf("League: %s\n", league); //TEMP

    /* CALL SAVE TO FILE function */
    writeInformationToFile(item, price, league);

    return 1;
}

int writeInformationToFile(char *itemName, char *price, char *league)
{
    int nextLineToWrite = getNextLineToWrite();
    printf("%d\n", nextLineToWrite);
    static const char filename[] = "history.txt"; /* Making array that contains the filename */
    FILE *file = fopen(filename, "a"); /*  */
    if(file != NULL) /*  */
    {
        fputs(itemName, file); /* DO SOMETHING */
        fputs(" ", file);
        fputs(price, file); /* DO SOMETHING */
        fputs(" ", file);
        fputs(league, file); /* DO SOMETHING */
        fputs("\n", file);

        fclose(file);
    }
    else
    {
        printf("The file does not exist!\n");
        return 0;
    }

    return 1;
}

int main()
{
    /* Get the nextLineToWrite */
    int nextLineToWrite = getNextLineToWrite();


    /* Get user input, if user enters 0 = program closes */
    int trigger = 1;
    char input[MAX_SIZE_INPUT];

    do{
        fgets(input, MAX_SIZE_INPUT, stdin); /* Gets the input from user and saved the line in input*/


        cutTheString(input);


    }while(1); //exit parameter


    return 0;
}
