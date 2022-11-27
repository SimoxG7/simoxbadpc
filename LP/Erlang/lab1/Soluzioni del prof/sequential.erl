-module(sequential).
-export([is_palindrome/1, is_an_anagram/2, factors/1, is_perfect/1]).

% case insensitive, togliamo anche la punteggiatura. 

% stringhe in erlang sono liste, internamente sono memorizzate con il numero ordinale ascii 
% member controlla che un certo carattere sia o meno un membro della stringa

is_palindrome(S) -> 
  L = string:casefold(lists:filter(fun(X) -> not lists:member(X, [$., $,, $:, $;, $?, $!, $ ])  end, S)),
  string:equal(lists:reverse(L), L).

% erlc *.erl

% data stringa e dizionario di stringhe, vedere se la stringa è anagramma di una nel dizionario.
is_an_anagram(_, []) -> false;
is_an_anagram(S, [H|T]) -> 
  is_an_anagram(string:casefold(lists:sort(S)), string:casefold(lists:sort(H)), T).

is_an_anagram(A, A, _) -> true; %caso in cui sono lo stesso oggetto
is_an_anagram(_, _, []) -> false;
is_an_anagram(S, _, [H|T]) -> is_an_anagram(S, string:casefold(lists:sort(H)), T).

%usiamo i generatori, thunks -> thunks.erl

factors(N) -> factors(N, [], thunks:primes()).

factors(N, R, _) when N =< 1 -> lists:reverse(R);
factors(N, R, [N|_]) -> lists:reverse([N|R]);
factors(N, R, [H|_]=L) when (N rem H == 0) -> factors(N div H, [H|R], L);
factors(N, R, [_|T]) -> factors(N, R, T()).

is_perfect(N) -> lists:foldr(fun(X, Y) -> X + Y end, 0, divisors(N, [], thunks:from(1))) == N.

divisors(N, Acc, [N|_]) -> lists:reverse(Acc);
divisors(N, Acc, [H|T]) when (N rem H == 0) -> divisors(N, [H|Acc], T());
divisors(N, Acc, [_|T]) -> divisors(N, Acc, T()).





















