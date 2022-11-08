-module(primes).
-export([primes/1]).

primes(N) when N > 1 -> [X|| X <- lists:seq(2,N)], (length([Y || Y <- lists:seq(2, N), ((X rem Y)== 0)]));
primes(_) -> [].

