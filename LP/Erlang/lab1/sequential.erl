% is_palindrome: string → bool that checks if the string given as input is palindrome, a string is palindrome when the represented sentence can be read the same way in either directions in spite of spaces, punctual and letter cases, e.g., detartrated, "Do geese see God?", "Rise to vote, sir.", ...;

% is_an_anagram : string → string list → boolean that given a dictionary of strings, checks if the input string is an anagram of one or more of the strings in the dictionary;

% factors: int → int list that given a number calculates all its prime factors;

% is_proper: int → boolean that given a number calculates if it is a perfect number or not, where a perfect number is a positive integer equal to the sum of its proper positive divisors (excluding itself), e.g., 6 is a perfect number since 1, 2 and 3 are the proper divisors of 6 and 6 is equal to 1+2+3;

-module(sequential).
-export([is_palindrome/1, is_an_anagram/2, factors/1, is_proper/1, generate_propers/1]).

is_palindrome(S) -> filter_alpha(S) == lists:reverse(filter_alpha(S)).

is_alpha(C) -> ((C >= 65) and (C =< 90)) or ((C >= 97) and (C =< 122)).

filter_alpha(S) -> string:lowercase([X || X <- S, (is_alpha(X))]).

is_an_anagram(S, Slist) -> loop(S, Slist).

match_strings(S, H) -> lists:sort(string:lowercase(S)) == lists:sort(string:lowercase(H)).

% loop(_, []) -> false;
% loop(S, [H|T]) -> 
%   case (match_strings(S, H)) of 
%     true -> true; 
%     false -> loop(S, T) 
%   end
% .

loop(_, []) -> false;
loop(S, [H|T]) -> loop(match_strings(S, H), S, T).

loop(true, _, _) -> true;
loop(false, S, T) -> loop(S, T).

factors(N) when N < 2 -> [];
factors(N) -> scompose(N, 2).

scompose(N, D) when (N >= D) -> scompose((N rem D == 0), N, D);
scompose(_, _) -> [].

scompose(true, N, D) -> [D|scompose(N div D, D)];
scompose(false, N, D) -> scompose(N, D+1).

is_proper(N) -> perfect_number(N, [X || X <- lists:seq(1, N-1), (N rem X == 0)], 0). %is a perfect number? -> integer sum of its positive divisors, 6 divisibile per 1,2,3 e la somma di 1,2,3 è 6

perfect_number(N, [], Acc) -> N == Acc;
perfect_number(N, [H|T], Acc) -> perfect_number(N, T, Acc + H).

generate_propers(N) -> [X || X <- lists:seq(2, N), is_proper(X)].










