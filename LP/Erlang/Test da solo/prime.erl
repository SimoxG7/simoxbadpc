-module(prime).
-export([primes/1, is_prime/1]).

is_prime(N, curr) when N < curr -> if (N rem curr == 0) -> false true -> is_prime(N, curr+1);
is_prime(_, _) -> false.
    
is_prime(N) -> is_prime(N, 2).

primes(N) when N > 1 -> [X || X <- is_prime(N)];
primes(_) -> [].