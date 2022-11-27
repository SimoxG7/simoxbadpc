-module(sequential).
-export([is_palindrome/1, is_an_anagram/2]).

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


%usiamo i generatori, thunks





