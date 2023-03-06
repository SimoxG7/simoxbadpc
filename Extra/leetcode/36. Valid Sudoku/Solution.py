from typing import List

class Solution:
  def isValidSudoku(self, board: List[List[str]]) -> bool:
    rows = [set() for _ in range(9)]
    cols = [set() for _ in range(9)]
    block = [[set() for _ in range(3)] for _ in range(3)]

    for i in range(9):
      for j in range(9):
        curr = board[i][j]
        if curr == '.':
          continue
        if (curr in rows[i]) or (curr in cols[j]) or (curr in block[i // 3][j // 3]):
          return False
        rows[i].add(curr)
        cols[j].add(curr)
        block[i // 3][j // 3].add(curr)
    return True


