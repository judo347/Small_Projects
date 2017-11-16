#include <stdio.h>
#include <stdlib.h>

/* @IgnatSilik Hi, I would like to buy your A Forest of False Idols listed for 14 chaos in Standard */
/* Read the string above and save the relevant information to the txt-file "history.txt */
/* First line in "history.txt" is the next line to be printet */

int main()
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



    /*
    int trigger = 1;
    char input[];
    int nextLineToWrite = ;
    do{
        scanf();
        printf(input);

    }while(trigger);
    */

    return 0;
}
