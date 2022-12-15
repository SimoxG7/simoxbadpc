/*
Created by:                                                                  
                                                                                      
   SSSSSSSSSSSSSSS   iiii                                                             
 SS:::::::::::::::S i::::i                                                            
S:::::SSSSSS::::::S  iiii                                                             
S:::::S     SSSSSSS                                                                   
S:::::S            iiiiiii    mmmmmmm    mmmmmmm      ooooooooooo xxxxxxx      xxxxxxx
S:::::S            i:::::i  mm:::::::m  m:::::::mm  oo:::::::::::oox:::::x    x:::::x 
 S::::SSSS          i::::i m::::::::::mm::::::::::mo:::::::::::::::ox:::::x  x:::::x  
  SS::::::SSSSS     i::::i m::::::::::::::::::::::mo:::::ooooo:::::o x:::::xx:::::x   
    SSS::::::::SS   i::::i m:::::mmm::::::mmm:::::mo::::o     o::::o  x::::::::::x    
       SSSSSS::::S  i::::i m::::m   m::::m   m::::mo::::o     o::::o   x::::::::x     
            S:::::S i::::i m::::m   m::::m   m::::mo::::o     o::::o   x::::::::x     
            S:::::S i::::i m::::m   m::::m   m::::mo::::o     o::::o  x::::::::::x    
SSSSSSS     S:::::Si::::::im::::m   m::::m   m::::mo:::::ooooo:::::o x:::::xx:::::x   
S::::::SSSSSS:::::Si::::::im::::m   m::::m   m::::mo:::::::::::::::ox:::::x  x:::::x  
S:::::::::::::::SS i::::::im::::m   m::::m   m::::m oo:::::::::::oox:::::x    x:::::x 
 SSSSSSSSSSSSSSS   iiiiiiiimmmmmm   mmmmmm   mmmmmm   ooooooooooo xxxxxxx      xxxxxxx

This program is a sudoku solver. Insert the sudoku puzzle in the matrix below. 
When I'll have time I'll add:
- An option to read the matrix from file.
- An option to parameterize the dimension of the sudoku.

ASCII Art generated from: https://patorjk.com/software/taag/#p=display&f=Doh&t=Simox

*/


#include <stdio.h>
#include <stdbool.h>
#include <time.h>

#define ANSI_COLOR_RED     "\x1b[31m"
#define ANSI_COLOR_YELLOW  "\x1b[33m"
#define ANSI_COLOR_BLUE    "\x1b[34m" 
#define ANSI_COLOR_RESET   "\x1b[0m"

#define MAX_LENGTH 16
#define SQUARE_SIZE 4

//each function has a small description in their implementantions (under the main)

bool is_cell_ok(int table[MAX_LENGTH][MAX_LENGTH], int x, int y, int num);
bool is_row_ok(int table[MAX_LENGTH][MAX_LENGTH], int row, int num);
bool is_column_ok(int table[MAX_LENGTH][MAX_LENGTH], int column, int num);
bool is_square_ok(int table[MAX_LENGTH][MAX_LENGTH], int squarex, int squarey, int num);
bool is_solved(int table[MAX_LENGTH][MAX_LENGTH], int* cont);
bool find_not_valued_cell(int table[MAX_LENGTH][MAX_LENGTH], int* row, int* col);
void pretty_printer_before(int table[MAX_LENGTH][MAX_LENGTH]);
void pretty_printer_after(int table_before[MAX_LENGTH][MAX_LENGTH], int table_after[MAX_LENGTH][MAX_LENGTH]);
void basic_printer(int table[MAX_LENGTH][MAX_LENGTH]);
void test(int table[MAX_LENGTH][MAX_LENGTH]);
int num_length(int num);
void pretty_printer_before_no_squares(int table[MAX_LENGTH][MAX_LENGTH]);
void pretty_printer_after_no_squares(int table_before[MAX_LENGTH][MAX_LENGTH], int table_after[MAX_LENGTH][MAX_LENGTH]);
//void replace_with_0(int table[MAX_LENGTH][MAX_LENGTH]);


int main(int argc, char** argv) {

  //the pretty printers are thought for the 3x3 version, with other versions they 
  //don't print a perfect output.
  //insert the puzzle here

  /*
  //i do not suggest to run this, it takes up to hours to finish the computation
  //(even on good machines).
  //5x5
  int table[MAX_LENGTH][MAX_LENGTH] = {
    {16, 23, 7, 0, 0, 24, 0, 4, 0, 0, 0, 10, 0, 0, 0, 1, 0, 18, 0, 0, 8, 21, 14, 0, 17},
    {0, 0, 20, 0, 0, 19, 15, 16, 0, 0, 0, 0, 0, 5, 24, 4, 0, 2, 14, 23, 0, 0, 18, 0, 7},
    {9, 2, 12, 0, 0, 0, 0, 0, 20, 11, 13, 0, 0, 7, 0, 0, 0, 0, 0, 6, 0, 0, 10, 25, 1},
    {4, 0, 0, 0, 19, 0, 0, 0, 14, 0, 8, 0, 0, 23, 21, 10, 0, 9, 7, 17, 0, 0, 0, 0, 0},
    {18, 0, 0, 0, 0, 0, 1, 17, 10, 0, 11, 15, 19, 0, 0, 12, 0, 20, 0, 0, 0, 13, 0, 0, 0},
    {0, 7, 1, 3, 0, 0, 12, 0, 0, 0, 0, 0, 16, 0, 0, 8, 20, 11, 0, 0, 0, 0, 0, 9, 21},
    {0, 6, 0, 10, 0, 0, 2, 21, 18, 0, 12, 19, 23, 0, 0, 0, 0, 0, 24, 16, 1, 0, 0, 14, 0},
    {8, 20, 0, 18, 16, 11, 0, 0, 24, 0, 9, 0, 0, 0, 3, 0, 0, 0, 22, 0, 12, 0, 0, 10, 4},
    {0, 0, 0, 0, 0, 1, 0, 0, 9, 22, 4, 0, 0, 0, 0, 0, 17, 23, 2, 0, 24, 8, 13, 0, 0},
    {15, 21, 0, 17, 9, 8, 0, 0, 0, 0, 0, 18, 7, 2, 0, 0, 1, 0, 0, 0, 0, 0, 19, 0, 0},
    {0, 4, 0, 16, 0, 0, 0, 14, 0, 0, 0, 22, 0, 10, 0, 0, 11, 17, 8, 0, 21, 24, 9, 0, 0},
    {0, 10, 11, 22, 0, 0, 0, 0, 0, 21, 24, 3, 0, 17, 1, 7, 0, 0, 18, 0, 5, 0, 0, 0, 14},
    {0, 0, 0, 0, 17, 10, 4, 0, 0, 20, 0, 0, 0, 0, 0, 25, 0, 0, 9, 5, 16, 0, 0, 0, 0},
    {25, 0, 0, 0, 6, 0, 16, 0, 0, 19, 14, 13, 0, 8, 9, 23, 0, 0, 0, 0, 0, 12, 4, 18, 0},
    {0, 0, 23, 21, 20, 0, 7, 18, 13, 0, 0, 4, 0, 6, 0, 0, 0, 3, 0, 0, 0, 17, 0, 19, 0},
    {0, 0, 10, 0, 0, 0, 0, 0, 17, 0, 0, 7, 14, 12, 0, 0, 0, 0, 0, 4, 25, 16, 0, 22, 19},
    {0, 0, 14, 11, 13, 0, 10, 19, 12, 0, 0, 0, 0, 0, 16, 18, 15, 0, 0, 7, 0, 0, 0, 0, 0},
    {5, 16, 0, 0, 24, 0, 14, 0, 0, 0, 17, 0, 0, 0, 11, 0, 19, 0, 0, 1, 6, 10, 0, 4, 18},
    {0, 18, 0, 0, 3, 21, 11, 0, 0, 0, 0, 0, 6, 13, 22, 0, 25, 24, 10, 0, 0, 5, 0, 23, 0},
    {12, 19, 0, 0, 0, 0, 0, 8, 2, 23, 0, 0, 9, 0, 0, 0, 0, 6, 0, 0, 7, 15, 11, 0},
    {0, 0, 0, 9, 0, 0, 0, 12, 0, 7, 0, 0, 10, 24, 14, 0, 5, 19, 1, 0, 0, 0, 0, 0, 13},
    {0, 0, 0, 0, 0, 22, 23, 24, 0, 14, 21, 12, 0, 0, 17, 0, 9, 0, 0, 0, 10, 0, 0, 0, 3},
    {23, 25, 18, 0, 0, 4, 0, 0, 0, 0, 0, 9, 0, 0, 20, 6, 24, 0, 0, 0, 0, 0, 12, 1, 16},
    {14, 0, 19, 0, 0, 15, 3, 1, 0, 9, 7, 5, 0, 0, 0, 0, 0, 8, 11, 12, 0, 0, 17, 0, 0},
    {11, 0, 16, 5, 1, 0, 0, 13, 0, 8, 0, 0, 0, 25, 0, 0, 0, 10, 0, 14, 0, 0, 24, 2, 23}
  };
  */


  //this specific table makes 14579935 recursive calls. 
  //takes around 12 seconds on a Ryzen 5 3600 machine to finish.
  //takes around 27 seconds on a Intel Core Duo machine to finish.
  //4x4
  int table[MAX_LENGTH][MAX_LENGTH] = {
    {0, 15, 0, 1, 0, 2, 10, 14, 12, 0, 0, 0, 0, 0, 0, 0},
    {0, 6, 3, 16, 12, 0, 8, 4, 14, 15, 1, 0, 2, 0, 0, 0},
    {14, 0, 9, 7, 11, 3, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    {4, 13, 2, 12, 0, 0, 0, 0, 6, 0, 0, 0, 0, 15, 0, 0},
    {0, 0, 0, 0, 14, 1, 11, 7, 3, 5, 10, 0, 0, 8, 0, 12},
    {3, 16, 0, 0, 2, 4, 0, 0, 0, 14, 7, 13, 0, 0, 5, 15},
    {11, 0, 5, 0, 0, 0, 0, 0, 0, 9, 4, 0, 0, 6, 0, 0},
    {0, 0, 0, 0, 13, 0, 16, 5, 15, 0, 0, 12, 0, 0, 0, 0},
    {0, 0, 0, 0, 9, 0, 1, 12, 0, 8, 3, 10, 11, 0, 15, 0},
    {2, 12, 0, 11, 0, 0, 14, 3, 5, 4, 0, 0, 0, 0, 9, 0},
    {6, 3, 0, 4, 0, 0, 13, 0, 0, 11, 9, 1, 0, 12, 16, 2},
    {0, 0, 10, 9, 0, 0, 0, 0, 0, 0, 12, 0, 8, 0, 6, 7},
    {12, 8, 0, 0, 16, 0, 0, 10, 0, 13, 0, 0, 0, 5, 0, 0},
    {5, 0, 0, 0, 3, 0, 4, 6, 0, 1, 15, 0, 0, 0, 0, 0},
    {0, 9, 1, 6, 0, 14, 0, 11, 0, 0, 2, 0, 0, 0, 10, 8},
    {0, 14, 0, 0, 0, 13, 9, 0, 4, 12, 11, 8, 0, 0, 2, 0}
  };
  
  
  /*
  //this specific table makes 119595 recursive calls.
  //takes around 0.0352 seconds on a Ryzen 5 3600 machine.
  //takes around 0.1121 seconds on a Intel Core Duo machine.
  //3x3
  int table[MAX_LENGTH][MAX_LENGTH] = {
    { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
    { 0, 0, 0, 0, 0, 5, 7, 0, 0 },
    { 0, 0, 0, 0, 3, 0, 0, 9, 1 },
    { 5, 0, 0, 0, 0, 3, 0, 0, 2 },
    { 0, 3, 0, 6, 0, 8, 0, 0, 0 },
    { 0, 0, 0, 0, 9, 0, 0, 0, 8 },
    { 0, 2, 7, 5, 0, 0, 9, 0, 0 },
    { 0, 1, 0, 0, 0, 4, 0, 0, 0 },
    { 0, 0, 3, 1, 0, 0, 0, 5, 0 }
  };
  */

  int cont = 0, *cont_ptr;
  cont_ptr = &cont;

  //accessing with pointers: *(*(matrix + row) + column)); //ranging from 0 to 8

  int table_copy[MAX_LENGTH][MAX_LENGTH];

  for (int i = 0; i < MAX_LENGTH; i++) {
    for (int j = 0; j < MAX_LENGTH; j++) {
      *(*(table_copy + i) + j) = *(*(table + i) + j);
    }
  }

  //test(table);

  //replace_with_0(table);

  printf("Trying to solve this table: \n");
  pretty_printer_before_no_squares(table_copy);
  printf("Computing the solution...\n\n");

  clock_t begin = clock();
  double time_spent;

  if(is_solved(table, cont_ptr)) {
    clock_t end = clock();
    time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    printf("Found solution!\n\n");
    pretty_printer_after_no_squares(table_copy, table);
  } else {
    clock_t end = clock();
    time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    printf("There is no solution for the table.\n\n");
  }
  printf("The program made %d attempts to find values for the table and ran for %f seconds.\n\n", *cont_ptr, time_spent);
 return 0; 
}


//functions

//checks if num can be put in the given cell of coordinates x, y 
bool is_cell_ok(int table[MAX_LENGTH][MAX_LENGTH], int x, int y, int num) {
  int squarex = x - (x % SQUARE_SIZE);
  int squarey = y - (y % SQUARE_SIZE);
  return (is_row_ok(table, x, num) && is_column_ok(table, y, num) && is_square_ok(table, squarex, squarey, num));
}

//checks if num can be put in the given row 
bool is_row_ok(int table[MAX_LENGTH][MAX_LENGTH], int row, int num) {
  for (int i = 0; i < MAX_LENGTH; i++) {
    if (*(*(table + row) + i) == num) return false;
  }
  return true;
}

//checks if num can be put in the given column
bool is_column_ok(int table[MAX_LENGTH][MAX_LENGTH], int column, int num) {
  for (int i = 0; i < MAX_LENGTH; i++) {
    if (*(*(table + i) + column) == num) return false;
  }
  return true;
}

//checks if the num can be put in the given square ((squarex;squarey) to (squarex+2;squarey+2))
bool is_square_ok(int table[MAX_LENGTH][MAX_LENGTH], int squarex, int squarey, int num) {
  for (int i = 0; i < SQUARE_SIZE; i++) {
    for (int j = 0; j < SQUARE_SIZE; j++) {
      if (*(*(table + squarex + i) + squarey + j) == num) return false;
    }
  }
  return true;
}

//recursive function used to apply the backtracking and solving the puzzle
bool is_solved(int table[MAX_LENGTH][MAX_LENGTH], int* cont_ptr) {
  int row, col, num;
  (*cont_ptr)++;
  
  //if there is no cell with a 0, then the puzzle is solved. The call to 
  //this function modifies the row and col values through pointers, so that 
  //they point to the row and col of the cell not valued (has a 0).
  if (!find_not_valued_cell(table, &row, &col)) {
    return true;
  } 

  //recursion here; gives the value to the unvalued cell, then calls itself 
  //which finds the next not valued cell and gives it a value, exc... 
  for (num = 1; num < MAX_LENGTH+1; num++) {
    if (is_cell_ok(table, row, col, num)) {
      //cell in row and col appears to be ok for num 
      *(*(table + row) + col) = num;
      //if it returns true, break the recursion (the true is given from the 
      //previous lines of code, in which no unvalued cell is found)
      if (is_solved(table, cont_ptr)) return true;
    }
    //the cell is not ok for the number, resetting to 0. With the use of 
    //pointers this makes the backtracking reset the previous attemps of 
    //giving values to a cell aswell.
    *(*(table + row) + col) = 0;
  }
  //only reached if exhausted and sudoku isn't solved
  return false;
}

//checks if there is a not valued cell and returns its coordinates through pointers
bool find_not_valued_cell(int table[MAX_LENGTH][MAX_LENGTH], int *row, int *col) {
  for (*row = 0; *row < MAX_LENGTH; (*row)++) {
    for (*col = 0; *col < MAX_LENGTH; (*col)++) {
      if (*(*(table + *row) + *col) == 0) return true;
    }
  }
  return false;
}


//pretty printer for unsolved table
void pretty_printer_before(int table[MAX_LENGTH][MAX_LENGTH]) {
  int spaces_needed = num_length(MAX_LENGTH);
  for (int x = 0; x < MAX_LENGTH; x++) {
    for (int y = 0; y < MAX_LENGTH; y++) {
      if (*(*(table + x) + y) == 0) {
        printf(ANSI_COLOR_RED "?" ANSI_COLOR_RESET);
      } else {
        printf(ANSI_COLOR_YELLOW "%d" ANSI_COLOR_RESET, *(*(table + x) + y));
      }
      for (int k = 0; k < (spaces_needed - num_length(*(*(table + x) + y))); k++) {
        printf(" ");
      }
      if (y != MAX_LENGTH-1) printf(" | ");
    }
    printf("\n");
    if (x != MAX_LENGTH-1) {
      for (int k = 0; k < MAX_LENGTH-1; k++) {
        if ((k + 1) % SQUARE_SIZE == 0) {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf(" ■ ");  
        } else if ((x + 1) % SQUARE_SIZE == 0 && x != MAX_LENGTH-1) {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf(" ■ "); 
        } else {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf("   ");
        }
      } 
      for (int h = 0; h < spaces_needed; h++) printf("-");
      printf("\n");
    }
  }
}


//pretty printer for solved table
void pretty_printer_after(int table_before[MAX_LENGTH][MAX_LENGTH], int table_after[MAX_LENGTH][MAX_LENGTH]) {
  int spaces_needed = num_length(MAX_LENGTH);
  for (int x = 0; x < MAX_LENGTH; x++) {
    for (int y = 0; y < MAX_LENGTH; y++) {
      
      if (*(*(table_before + x) + y) == *(*(table_after + x) + y)) {
        printf(ANSI_COLOR_YELLOW "%d" ANSI_COLOR_RESET, *(*(table_after + x) + y));
      } else if (*(*(table_before + x) + y) != *(*(table_after + x) + y)) {
        printf(ANSI_COLOR_BLUE "%d" ANSI_COLOR_RESET, *(*(table_after + x) + y));
      } else { //should never go in the else branch
        printf(ANSI_COLOR_RED "?" ANSI_COLOR_RESET);
      }
      for (int k = 0; k < (spaces_needed - num_length(*(*(table_after + x) + y))); k++) {
        printf(" ");
      }
      if (y != MAX_LENGTH-1) printf(" | ");
    }
    printf("\n");
    if (x != MAX_LENGTH-1) {
      for (int k = 0; k < MAX_LENGTH-1; k++) {
        if ((k + 1) % SQUARE_SIZE == 0) {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf(" ■ ");
        } else if ((x + 1) % SQUARE_SIZE == 0 && x != MAX_LENGTH-1) {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf(" ■ ");
        } else {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf("   ");
        }
      } 
      for (int h = 0; h < spaces_needed; h++) printf("-");
      printf("\n");
    }
  }
}

//basic printer created for debugging
void basic_printer(int table[MAX_LENGTH][MAX_LENGTH]) {
  for (int i = 0; i < MAX_LENGTH; i++) {
    for (int j = 0; j < MAX_LENGTH; j++) {
      printf("%d ", *(*(table + i) + j));
    }
    printf("\n");
  }
  return;
}

//used to test the functions
void test(int table[MAX_LENGTH][MAX_LENGTH]) {
  for (int i = 0; i < MAX_LENGTH; i++) {
    printf("isrow0okay num: %d, res: %d\n", i+1, is_row_ok(table, 0, i+1));
  }

  for (int i = 0; i < MAX_LENGTH; i++) {
    printf("iscolumn0okay num: %d, res: %d\n", i+1, is_column_ok(table, 0, i+1));
  }

  for (int i = 0; i < SQUARE_SIZE; i++) {
    for (int j = 0; j < SQUARE_SIZE; j++) {
      printf("issquareok sqx: %d, sqy: %d, num: %d, res: %d\n", 6+i, 0+j, i*3 + j + 1, is_square_ok(table, 6, 0, i*3 + j +1));
    }
  }

  for (int i = 0; i < SQUARE_SIZE; i++) {
    for (int j = 0; j < SQUARE_SIZE; j++) {
      for (int k = 1; k < MAX_LENGTH+1; k++) {
        if (!is_cell_ok(table, i, j, k)) printf("iscellok cell: %d-%d, num: %d, res: %d\n", i, j, k, is_cell_ok(table, i, j, k));
      }
    }
  }

  basic_printer(table);

  printf("Numlength: %d, %d\n", 18, num_length(18));
  printf("Numlength: %d, %d\n", 9, num_length(9));
  printf("Numlength: %d, %d\n", 180, num_length(180));


  return;
}

int num_length(int num) {
  int cifre = 1;
  while(num/10 > 0) {
    cifre++;
    num /= 10;
  }
  return cifre; 
}

//pretty printer for unsolved table
void pretty_printer_before_no_squares(int table[MAX_LENGTH][MAX_LENGTH]) {
  int spaces_needed = num_length(MAX_LENGTH);
  for (int x = 0; x < MAX_LENGTH; x++) {
    for (int y = 0; y < MAX_LENGTH; y++) {
      if (*(*(table + x) + y) == 0) {
        printf(ANSI_COLOR_RED "?" ANSI_COLOR_RESET);
      } else {
        printf(ANSI_COLOR_YELLOW "%d" ANSI_COLOR_RESET, *(*(table + x) + y));
      }
      for (int k = 0; k < (spaces_needed - num_length(*(*(table + x) + y))); k++) {
        printf(" ");
      }
      if (y != MAX_LENGTH-1) printf(" | ");
    }
    printf("\n");
    if (x != MAX_LENGTH-1) {
      for (int k = 0; k < MAX_LENGTH-1; k++) {
        if ((k + 1) % SQUARE_SIZE == 0) {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf(" ║ ");  
        } else if ((x + 1) % SQUARE_SIZE == 0 && x != MAX_LENGTH-1) {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf(" ═ "); 
        } else {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf("   ");
        }
      } 
      for (int h = 0; h < spaces_needed; h++) printf("-");
      printf("\n");
    }
  }
}


//pretty printer for solved table
void pretty_printer_after_no_squares(int table_before[MAX_LENGTH][MAX_LENGTH], int table_after[MAX_LENGTH][MAX_LENGTH]) {
  int spaces_needed = num_length(MAX_LENGTH);
  for (int x = 0; x < MAX_LENGTH; x++) {
    for (int y = 0; y < MAX_LENGTH; y++) {
      
      if (*(*(table_before + x) + y) == *(*(table_after + x) + y)) {
        printf(ANSI_COLOR_YELLOW "%d" ANSI_COLOR_RESET, *(*(table_after + x) + y));
      } else if (*(*(table_before + x) + y) != *(*(table_after + x) + y)) {
        printf(ANSI_COLOR_BLUE "%d" ANSI_COLOR_RESET, *(*(table_after + x) + y));
      } else { //should never go in the else branch
        printf(ANSI_COLOR_RED "?" ANSI_COLOR_RESET);
      }
      for (int k = 0; k < (spaces_needed - num_length(*(*(table_after + x) + y))); k++) {
        printf(" ");
      }
      if (y != MAX_LENGTH-1) printf(" | ");
    }
    printf("\n");
    if (x != MAX_LENGTH-1) {
      for (int k = 0; k < MAX_LENGTH-1; k++) {
        if ((k + 1) % SQUARE_SIZE == 0) {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf(" ║ ");  
        } else if ((x + 1) % SQUARE_SIZE == 0 && x != MAX_LENGTH-1) {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf(" ═ "); 
        } else {
          for (int h = 0; h < spaces_needed; h++) {
            printf("-");
          }
          printf("   ");
        }
      } 
      for (int h = 0; h < spaces_needed; h++) printf("-");
      printf("\n");
    }
  }
}

/*
replace_with_0(int table[MAX_LENGTH][MAX_LENGTH]) {
  for (int i = 0; i < MAX_LENGTH; i++) {
    printf("{");
    for (int j = 0; j < MAX_LENGTH; j++) {
      if (*(*(table + i) + j) == '*') {
        if (j != MAX_LENGTH-1) printf("0, ");
        else printf("0");
      } else {
        if (j != MAX_LENGTH-1) printf("%d, ", *(*(table + i) + j));
        else printf("%d", *(*(table + i) + j));
      }
    }
    printf("},\n");
  }
}
*/
