from typing import List

class Solution:
  def search(self, nums: List[int], target: int) -> int:
    s:int = 0
    e:int = len(nums)-1
    m:int = 0
    while (s <= e):
      m = s + (e-s)//2
      if (nums[m] == target): return m
      if (nums[m] >= nums[s]):
        if (nums[m] >= target and nums[s] <= target): e = m-1
        else: s = m+1
      else: 
        if (nums[m] <= target and nums[e] >= target): s = m+1
        else: e = m-1
    return -1


# try:
#   return nums.index(target) 
# except:
#   return -1


