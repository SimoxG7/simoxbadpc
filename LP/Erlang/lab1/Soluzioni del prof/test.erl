-module(test).
-export([test_is_palindrome/0, test_is_an_anagram/0, test_factors/0, test_is_perfect/0]).

test(F, L) -> 
  lists:foreach( %foreach vuole procedura, non funzione. E' una map praticamente 
    fun(X) -> io:format("~p :- ~p~n", [X, F(X)]) end,
    L
  ).

test_is_palindrome() -> 
  test(
    fun sequential:is_palindrome/1,
    ["hello", "Simox", "SimoxXomis", "Do geese see God?", "Rise to vote, sir.", "porcodio"]
  ).

test_is_an_anagram() -> 
  test(
    fun(X) -> sequential:is_an_anagram(X,
    ["Xomis", "hello", "porcodio", "diocane"]) end,
    ["Simox", "dioporco", "cane"]
  ).  

test_factors() ->
  test(
    fun sequential:factors/1, 
    [25, 400, 1970, 32542, 7, 23812491]
  ).

test_is_perfect() -> 
  test(
    fun sequential:is_perfect/1,
    [6, 7, 28, 41, 100]
  ).















