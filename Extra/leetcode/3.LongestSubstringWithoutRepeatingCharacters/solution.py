class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        best = 0
        cont = 0
        curr = []
  
        for c in s:
          if c in curr:
            if cont > best: best = cont 
            cont = 0
            curr = []
          else:
            cont += 1
            curr.append(c)

        return best