-module(prime).
-export([is_prime/1, primes/1]).

is_prime(N) when N>1 -> length([Y || Y <- lists:seq(2, trunc(math:sqrt(N))), ((N rem Y) == 0)]) == 0;
is_prime(_) -> false.

primes(N) when N>1 -> [X || X <- lists:seq(2, N), is_prime(X)];
primes(_) -> [].
