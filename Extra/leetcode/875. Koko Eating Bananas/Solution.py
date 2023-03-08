from typing import List 

class Solution:
  def minEatingSpeed(self, piles: List[int], h: int) -> int:
    left:int = 1
    right:int = max(piles)
    
    def canEatAllBananas(amountperhour):
      time = 0
      for pile in piles:
        time += (pile + amountperhour - 1) // amountperhour
      return time <= h 
    
    while(left < right):
      mid = (left + right) // 2 
      if canEatAllBananas(mid):
        right = mid 
      else: 
        left = mid + 1
    
    return left




