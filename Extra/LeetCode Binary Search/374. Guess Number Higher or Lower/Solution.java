/**
 * Forward declaration of guess API.
 * 
 * @param num your guess
 * @return -1 if num is higher than the picked number
 *         1 if num is lower than the picked number
 *         otherwise return 0
 *         int guess(int num);
 */

public class Solution extends GuessGame {
  public int guessNumber(int n) {
    int left = 0;
    int right = n;
    int mid = right - (right - left)/2;
    while (left < right) {
      int guessed = guess(mid);
      if (guessed == 0) return mid;
      else if (guessed == 1) left = mid;
      else right = mid-1;
      mid = right - (right - left)/2;
    }
    return mid;
  }
}