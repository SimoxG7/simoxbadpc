from typing import List

class Solution:
  
  def search(self, nums, target, first):
    s:int = 0
    e:int = len(nums)-1
    res:int = -1 
    while (s <= e):
      m:int = s + (e - s)//2
      if (nums[m] < target): s = m+1 
      elif (nums[m] > target): e = m-1
      else:
        res = m
        if (first): e = m-1
        else: s = m+1 
    return res 
  
  def searchRange(self, nums: List[int], target: int) -> List[int]:
    res:List[int] = [-1, -1]
    s:int = self.search(nums, target, True)
    e:int = self.search(nums, target, False)
    res[0] = s
    res[1] = e 
    return res 
  
  

