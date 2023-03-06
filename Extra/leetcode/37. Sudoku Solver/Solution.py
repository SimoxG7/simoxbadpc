from typing import List

class Solution:

  def isValid(self, board, row, col, ch):
      row, col = int(row), int(col)        
      for i in range(9):        
        if board[i][col] == ch:
          return False
        if board[row][i] == ch:
          return False
        if board[3*(row//3) + i//3][3*(col//3) + i%3] == ch:
          return False    
      return True

  def solve(self, board, row, col, n):
      if row == n:
        return True
      if col == n:
        return self.solve(board, row+1, 0, n)      
      if board[row][col] == ".":
        for i in range(1, 10):
          if self.isValid(board, row, col, str(i)):
            board[row][col] = str(i)
            if self.solve(board, row, col + 1, n):
              return True
            else:
              board[row][col] = "."
        return False
      else:
        return self.solve(board, row, col + 1, n)  
    
  def solveSudoku(self, board: List[List[str]]) -> None:
    self.solve(board, 0, 0, 9)
    
    
            
          
        