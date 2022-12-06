char * intToRoman(int num){
    char* ans = (char*)malloc(20 * sizeof(char));
    for (int i = 0; i< 20; i++)
    {
        ans[i] = '\0';
    }    
    
    char I = 'I';
    char V = 'V';
    char X = 'X';
    char L = 'L';
    char C = 'C';
    char D = 'D';
    char M = 'M';
  
    int temp = num;
    int i = 0;
    while(temp!=0){
        if(temp >= 1000){
            strncat(ans, &M, 1);
            temp-=1000;
        }
        else if(temp >= 900){
            strncat(ans, &C, 1);
            strncat(ans, &M, 1);
            temp-=900;
        }
        else if(temp >= 500){
            strncat(ans, &D, 1);
            temp-=500;
        }
        else if(temp >= 400){
            strncat(ans, &C, 1);
            strncat(ans, &D, 1);
            temp -=400;
        }
        else if(temp >= 100){
            strncat(ans, &C, 1);
            temp -=100;
        }
        else if(temp >= 90){
            strncat(ans, &X, 1);
            strncat(ans, &C, 1);
            temp -=90;
        }
        else if(temp >= 50){
            strncat(ans, &L, 1);
            temp -=50;
        }
        else if(temp >= 40){
            strncat(ans, &X, 1);
            strncat(ans, &L, 1);
            temp -=40;
        }
        else if(temp >= 10){
            strncat(ans, &X, 1);
            temp -= 10;
        }
        else if(temp >= 9){
            strncat(ans, &I, 1);
            strncat(ans, &X, 1);
            temp -= 9;
        }
        else if(temp >= 5){
            strncat(ans, &V, 1);
            temp -= 5;
        }
        else if(temp >= 4){
            strncat(ans, &I, 1);
            strncat(ans, &V, 1);
            temp -=4;
        }
        else if(temp >= 1){
            strncat(ans, &I, 1);
            temp -=1;
        }
        i++;
    }
    return ans;
}

// char * intToRoman(int num)
// {
//     char symbols[4][10][5] = {
//                 {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
//                 {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
//                 {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
//                 {"", "M", "MM", "MMM"}};
  
//     char *res = calloc(sizeof(char), 16);

//     for (int i = 0; num > 0; num = num / 10, i++) {
//         int d = num % 10;
//         char *tmp = strdup(res);
//         sprintf(res, "%s%s", symbols[i][d], tmp);
//         free(tmp);
//     }

//     return res;
// }
