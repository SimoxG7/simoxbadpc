-module(sequential). 
-compile(export_all).
-export([]).

is_palindrome(Str) -> 
  lists:reverse(string:lowercase(lists:filter((fun(X) -> is_char(X) end), Str))) == string:lowercase(lists:filter((fun(X) -> is_char(X) end), Str)). 

is_char(C) -> ((C >= 97) and (C =< 122)) or ((C >= 65) and (C =< 90)).

% my_filter([]) -> [];
% my_filter([H|_]=L) -> my_filter(is_char(H), L).

% my_filter(true, [H|T]) -> [H|my_filter(T)];
% my_filter(false, [_|T]) -> my_filter(T).


is_an_anagram(Str, StrList) -> check_anagram(lists:sort(Str), StrList).

check_anagram(_, []) -> false;
check_anagram(Str, [H|T]) -> check_anagram(Str == lists:sort(H), Str, T).

check_anagram(true, _, _) -> true;
check_anagram(false, Str, T) -> check_anagram(Str, T).
 

factors(1) -> [1];
factors(N) -> factors(N, 2).

factors(1, _) -> [];
factors(N, D) -> factors(N rem D == 0, N, D).

factors(true, N, D) -> [D|factors(N div D, D)];
factors(false, N, D) -> factors(N, D+1).


% is_proper(N) -> lists:foldl(fun(X, Y) -> X + Y end, 0, [1|factors(N)]) == N.
is_proper(N) -> is_proper(N, 0, lists:seq(1, N div 2)).

is_proper(N, Acc, []) -> N == Acc;
is_proper(N, Acc, [H|T]) -> is_proper(N rem H == 0, N, H, Acc, T).

is_proper(true, N, H, Acc, T) -> is_proper(N, Acc + H, T);
is_proper(false, N, _, Acc, T) -> is_proper(N, Acc, T).

generate_propers(N) -> [X || X <- lists:seq(1, N+1), is_proper(X)].








