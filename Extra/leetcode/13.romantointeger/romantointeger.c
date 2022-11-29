int romanToInt(char * s){
  int res = 0;
  
  for (int i = 0; *(s + i) != '\0'; i++) {
    char c1 = *(s + i);
    char c2 = *(s + i +1);
    
    switch (c1) {
      case 'I':
        if (c2 == 'V' || c2=='X') res -= 1;
        else res += 1;
        break;
      case 'V':
        res += 5;
        break;
      case 'X':
        if (c2 == 'L' || c2=='C') res -= 10;
        else res += 10;
        break;
      case 'L':
        res += 50;
        break;
      case 'C':
        if (c2 == 'D' || c2=='M') res -= 100;
        else res += 100;
        break;
      case 'D':
        res += 500;
        break;
      case 'M':
        res += 1000;
        break;
      default:
        printf("Unrecognized input\n");
    }
  }
  
  return res;
}