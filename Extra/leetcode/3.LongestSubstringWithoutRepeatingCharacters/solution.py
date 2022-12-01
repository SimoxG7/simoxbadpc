class Solution:
    
  
  
  def lengthOfLongestSubstring(self, s: str) -> int:
    def aux(s: str)  -> int:
      best = 0
      cont = 0
      curr = []
      for c in s:
        #print(c + " ")
        #print(curr)
        if c in curr:
          if cont > best: best = cont 
          cont = 1
          if curr[len(curr)-2] == c and curr[len(curr)-1] != c : 
            temp = curr[len(curr)-1]
            curr.clear()
            curr.append(temp)
            cont += 1
          else :
            curr.clear()
          curr.append(c)
        else:
          cont += 1
          curr.append(c)
      if cont > best: return cont
      return best

    order = aux(s)
    rev = aux(s[::-1])
    if order > rev: return order
    return rev
    