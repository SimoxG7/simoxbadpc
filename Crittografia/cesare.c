#include <stdio.h>
#include <ctype.h>
#include <string.h>

void force(char* filename);

int main(void) {
    FILE *fp;
    char *filename = "data.txt";
    fp = fopen(filename, "w");

    if (fp == NULL) {
        printf("Error opening the file");
    }

    fprintf(fp, "Hello Simox!\nThis is a secret message for you using the Cesar encryption method.\n");

    fclose(fp);

    force(filename);

    return 0;
}

void force(char* filename) {
    for (int i = 0; i < 26; i++) {
        FILE *original;
        original = fopen(filename, "r");
        
        FILE *new;
        char* newfilename = strcat("new", (char)i);
        new = fopen(newfilename, "w");

        char c = fgetc(original);
        while (c != EOF) {
            if (isalpha(c)) {
                if (islower(c)) {
                    c += i;
                    if (c > 122) {
                        c -= 26;
                    }
                } else if (isupper(c)) {
                    c += i;
                    if (c > 90) {
                        c -= 26;
                    }
                }
            }
            fprintf(new, c);
            c = original++;
        }
    }
}