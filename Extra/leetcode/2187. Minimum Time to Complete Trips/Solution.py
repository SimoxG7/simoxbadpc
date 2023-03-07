from typing import List

class Solution:
  def minimumTime(self, time: List[int], totalTrips: int) -> int: 
    l = 0
    r = min(time) * totalTrips
    while l < r:
      m = (l + r) // 2
      if sum(m // t for t in time) < totalTrips:
        l = m + 1
      else:
        r = m
    return l
  












