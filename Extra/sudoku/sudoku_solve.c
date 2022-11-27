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
(Will work on a filereader when I'll have time)

ASCII Art generated from: https://patorjk.com/software/taag/#p=testall&f=Crawford2&t=Simox

*/


#include <stdio.h>
#include <stdbool.h>
#include <time.h>

#define ANSI_COLOR_RED     "\x1b[31m"
#define ANSI_COLOR_YELLOW  "\x1b[33m"
#define ANSI_COLOR_BLUE    "\x1b[34m" 
#define ANSI_COLOR_RESET   "\x1b[0m"

//each functions has a small description in their implementantions (under the main)

bool is_cell_ok(int table[9][9], int x, int y, int num);
bool is_row_ok(int table[9][9], int row, int num);
bool is_column_ok(int table[9][9], int column, int num);
bool is_square_ok(int table[9][9], int squarex, int squarey, int num);
bool is_solved(int table[9][9], int* cont);
bool find_not_valued_cell(int table[9][9], int* row, int* col);
void pretty_printer_before(int table[9][9]);
void pretty_printer_after(int table_before[9][9], int table_after[9][9]);
void basic_printer(int table[9][9]);
void test(int table[9][9]);

int main(void) {

  //insert the puzzle here
  int table[9][9] = {
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

  int cont = 0, *cont_ptr;
  cont_ptr = &cont;

  //accessing with pointers: *(*(matrix + row) + column)); //ranging from 0 to 8

  int table_copy[9][9];

  for (int i = 0; i < 9; i++) {
    for (int j = 0; j < 9; j++) {
      *(*(table_copy + i) + j) = *(*(table + i) + j);
    }
  }

  //test(table);

  printf("Trying to solve this table: \n");
  pretty_printer_before(table_copy);
  printf("\nStarting to compute the solution...\n\n");

  clock_t begin = clock();
  double time_spent;

  if(is_solved(table, cont_ptr)) {
    clock_t end = clock();
    time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    printf("Found solution!\n\n");
    pretty_printer_after(table_copy, table);
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
bool is_cell_ok(int table[9][9], int x, int y, int num) {
  int squarex = x - (x % 3);
  int squarey = y - (y % 3);
  return (is_row_ok(table, x, num) && is_column_ok(table, y, num) && is_square_ok(table, squarex, squarey, num));
}

//checks if num can be put in the given row 
bool is_row_ok(int table[9][9], int row, int num) {
  for (int i = 0; i < 9; i++) {
    if (*(*(table + row) + i) == num) return false;
  }
  return true;
}

//checks if num can be put in the given column
bool is_column_ok(int table[9][9], int column, int num) {
  for (int i = 0; i < 9; i++) {
    if (*(*(table + i) + column) == num) return false;
  }
  return true;
}

//checks if the num can be put in the given square ((squarex;squarey) to (squarex+2;squarey+2))
bool is_square_ok(int table[9][9], int squarex, int squarey, int num) {
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      if (*(*(table + squarex + i) + squarey + j) == num) return false;
    }
  }
  return true;
}

//recursive function used to apply the backtracking and solving the puzzle
bool is_solved(int table[9][9], int* cont_ptr) {
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
  for (num = 1; num < 10; num++) {
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
bool find_not_valued_cell(int table[9][9], int *row, int *col) {
  for (*row = 0; *row < 9; (*row)++) {
    for (*col = 0; *col < 9; (*col)++) {
      if (*(*(table + *row) + *col) == 0) return true;
    }
  }
  return false;
}


//pretty printer for unsolved table
void pretty_printer_before(int table[9][9]) {
  for (int x = 0; x < 9; x++) {
    for (int y = 0; y < 9; y++) {
      if (*(*(table + x) + y) == 0) {
        printf(ANSI_COLOR_RED "?" ANSI_COLOR_RESET);
      } else {
        printf(ANSI_COLOR_YELLOW "%d" ANSI_COLOR_RESET, *(*(table + x) + y));
      }
      if (y != 8) printf(" | ");
    }
    printf("\n");
    if (x != 8) {
      for (int k = 0; k < 8; k++) {
        if ((k + 1) % 3 == 0) printf("- ■ ");
        else if ((x + 1) % 3 == 0 && x != 8) printf("- ■ "); //todo? fix?
        else printf("-   ");
      } 
      printf("- \n");
    }
  }
}


//pretty printer for solved table
void pretty_printer_after(int table_before[9][9], int table_after[9][9]) {
  for (int x = 0; x < 9; x++) {
    for (int y = 0; y < 9; y++) {
      
      if (*(*(table_before + x) + y) == *(*(table_after + x) + y)) {
        printf(ANSI_COLOR_YELLOW "%d" ANSI_COLOR_RESET, *(*(table_after + x) + y));
      } else if (*(*(table_before + x) + y) != *(*(table_after + x) + y)) {
        printf(ANSI_COLOR_BLUE "%d" ANSI_COLOR_RESET, *(*(table_after + x) + y));
      } else { //should never go in the else branch
        printf(ANSI_COLOR_YELLOW "%d" ANSI_COLOR_RESET, *(*(table_after + x) + y));
        printf(ANSI_COLOR_RED "?" ANSI_COLOR_RESET);
      }
      if (y != 8) printf(" | ");
    }
    printf("\n");
    if (x != 8) {
      for (int k = 0; k < 8; k++) {
        if ((k + 1) % 3 == 0) printf("- ■ ");
        else if ((x + 1) % 3 == 0 && x != 8) printf("- ■ "); //todo? fix?
        else printf("-   ");
      } 
      printf("- \n");
    }
  }
}

//basic printer created for debugging
void basic_printer(int table[9][9]) {
  for (int i = 0; i < 9; i++) {
    for (int j = 0; j < 9; j++) {
      printf("%d ", *(*(table + i) + j));
    }
    printf("\n");
  }
  return;
}

//used to test the functions
void test(int table[9][9]) {
  for (int i = 0; i < 9; i++) {
    printf("isrow0okay num: %d, res: %d\n", i+1, is_row_ok(table, 0, i+1));
  }

  for (int i = 0; i < 9; i++) {
    printf("iscolumn0okay num: %d, res: %d\n", i+1, is_column_ok(table, 0, i+1));
  }

  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      printf("issquareok sqx: %d, sqy: %d, num: %d, res: %d\n", 6+i, 0+j, i*3 + j + 1, is_square_ok(table, 6, 0, i*3 + j +1));
    }
  }

  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      for (int k = 1; k < 10; k++) {
        if (!is_cell_ok(table, i, j, k)) printf("iscellok cell: %d-%d, num: %d, res: %d\n", i, j, k, is_cell_ok(table, i, j, k));
      }
    }
  }

  basic_printer(table);

  return;
}