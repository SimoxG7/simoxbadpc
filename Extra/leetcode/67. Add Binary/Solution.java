public class Solution {
  public String addBinary(String a, String b) {
    StringBuilder sum = new StringBuilder();
    int la = a.length() - 1;
    int lb = b.length() - 1;
    int carry = 0;
    while (la >= 0 || lb >= 0 || carry == 1) {
      int digitA = 0;
      int digitB = 0;
      if (la >= 0) {
        digitA = a.charAt(la--) - '0';
      }
      if (lb >= 0) {
        digitB = b.charAt(lb--) - '0';
      }      
      sum.insert(0, (digitA + digitB + carry) % 2);
      carry = (digitA + digitB + carry) / 2;
    }
    return sum.toString();
  }
}