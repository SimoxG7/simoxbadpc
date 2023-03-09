from typing import List

class Solution:
  def runningSum(self, nums: List[int]) -> List[int]:
    s:int = 0
    index:int = 0
    for n in nums:
      nums[index] = nums[index] + s 
      s = nums[index]
      index += 1
    return nums