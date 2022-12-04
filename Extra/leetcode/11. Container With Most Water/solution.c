int maxArea(int* heights, int n) {
  int maxarea = 0;
  int* i = heights;
  int* j = i + n - 1;
  
  while (i < j) {
    int h;
    
    if (*i < *j) h = *i;
    else h = *j;
    
    int area = (j - i) * h;
    
    if (area > maxarea) maxarea = area;

    while (*i <= h && i < j) i++;
    while (*j <= h && i < j) j--;
  }
  return maxarea;
}