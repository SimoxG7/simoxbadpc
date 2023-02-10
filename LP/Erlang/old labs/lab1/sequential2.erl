-module(sequential2).
-export([is_palindrome/1, is_an_anagram/2, factors/1, is_proper/1, generate_propers/1]).

is_palindrome(Str) -> filter_alpha(string:lowercase(Str)) == string:reverse(filter_alpha(string:lowercase(Str))).

filter_alpha(Str) -> [X || X <- Str, is_alpha(X)].

%should be already lowercase
is_alpha(C) -> (C >= 97) and (C =< 122).

is_an_anagram(_, []) -> false;
is_an_anagram(Str, [H|T]) -> is_an_anagram(is_anagram(Str, H), Str, T).  

is_an_anagram(true, _, _) -> true;
is_an_anagram(false, Str, T) -> is_an_anagram(Str, T).

is_anagram(Str1, Str2) -> lists:sort(string:lowercase(Str1)) == lists:sort(string:lowercase(Str2)).


factors(N) when N < 2 -> [];
factors(N) -> factors(N, [], 2).

factors(1, Acc, _) -> lists:reverse(Acc);
factors(N, Acc, D) -> factors((N rem D == 0), N, Acc, D).

factors(true, N, Acc, D) -> factors((N div D), [D|Acc], D);
factors(false, N, Acc, D) -> factors(N, Acc, D+1).

is_proper(N) -> is_perfect(N, [X || X <- lists:seq(1, N-1), (N rem X == 0)], 0).

is_perfect(N, [], Acc) -> N == Acc;
is_perfect(N, [H|T], Acc) -> is_perfect(N, T, Acc+H).

generate_propers(N) -> [X || X <- lists:seq(1, N), is_proper(X)].


%is_prime(N) -> [X || X <- lists:seq(2, X)];






