char * intToRoman(int num)
{
    char symbols[4][10][5] = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MM", "MMM"}};
  
    char *res = calloc(sizeof(char), 16);

    for (int i = 0; num > 0; num = num / 10, i++) {
        int d = num % 10;
        char *tmp = strdup(res);
        sprintf(res, "%s%s", symbols[i][d], tmp);
        free(tmp);
    }

    return res;
}
