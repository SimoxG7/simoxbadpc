from typing import List

class Solution:
  def pivotIndex(self, nums: List[int]) -> int:
    leftSum = 0
    rightSum = sum(nums)
    for idx, ele in enumerate(nums):
      rightSum -= ele
      if leftSum == rightSum:
        return idx
      leftSum += ele
    return -1

# class Solution:
# 	def pivotIndex(self, nums: List[int]) -> int:
# 		left:int = 0
# 		right:int = len(nums)-1
# 		leftsum:int = 0
# 		rightsum:int = 0

# 		while (right - left != 0):
# 			if (leftsum <= rightsum):
# 				leftsum += nums[left]
# 				left += 1
# 			else:
# 				rightsum += nums[right]
# 				right -= 1

# 		if (rightsum != leftsum): return -1
# 		return left
