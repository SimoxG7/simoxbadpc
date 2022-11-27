-module(thunks).
-export([from/1, first/1, is_prime/1, primes/0]).

%generatori di numeri -> lista potenzialmente infinita, equivalente di range
from (K) -> [K|fun() -> from(K+1) end]. %thunk è la funzione che genera

%per scorrere la lista usiamo la chiamata, quindi dobbiamo reimplementarci le funzioni usate per liste 

filter(_, []) -> [];
filter(P, [H|T]) -> 
  case P(H) of
    true -> [H|fun() -> filter(P, T()) end]; %richiamo il generatore dalla tail
    false -> filter(P, T())
  end.

sift(P, L) -> filter(fun(N) -> N rem P /= 0 end, L). %setaccio
sieve([H|T]) -> [H|fun()-> sieve(sift(H, T())) end].

primes() -> sieve(from(2)).

is_prime(N) -> is_prime(N, primes()).

is_prime(N, [N|_]) -> true;
is_prime(N, [M|T]) when M < N -> is_prime(N, T());
is_prime(_, _) -> false.

first(N) -> first(N, primes()).

first(0, _) -> [];
first(N, [X|P]) -> [X|first(N-1, P())].

%55:23






