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

bool is_cell_ok(int** table, int x, int y, int num);
bool is_row_ok(int** table, int row, int num);
bool is_column_ok(int** table, int column, int num);
bool is_square_ok(int** table, int squarex, int squarey, int num);
bool is_solved(int** table);


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

  //accessing with pointers: *(*(matrix + row) + column)); //ranging from 0 to 8

  int table_copy[9][9];

  for (int i = 0; i < 9; i++) {
    for (int j = 0; j < 0; j++) {
      *(*(table_copy + i) + j) = *(*(table + i) + j);
    }
  }

  printf("isrowokay: %d", is_row_ok(table, 0, 3));

  if(is_solved(table)) {
    printf("Found solution!\n");
  } else {
    printf("There is no solution for the table.\n");
  }
 return 0; 
}

bool is_cell_ok(int** table, int x, int y, int num) {
  return (is_row_ok(table, x, num) && is_column_ok(table, y, num) && is_square_ok(table, squarex, squarey));
}

bool is_row_ok(int** table, int row, int num) {
  for (int i = 0; i < 9; i++) {
    if (table + (sizeof(int) * row * 9) + i == num) return false;
  }
  return true;
}

bool is_column_ok(int** table, int column, int num) {
  for (int i = 0; i < 9; i++) {
    return false;
  }
}

bool is_square_ok(int** table, int squarex, int squarey, int num) {
  return false;
}

bool is_solved(int** table) {
  return false;
}

