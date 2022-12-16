-module(controller).
-compile(export_all).
%-export([start/1, generate_ring/3, loop/2, loop_last/2]).

% fix ring

start(N) -> 
  register(ring_first, spawn(?MODULE, generate_ring, [N, first_n_primes(N), self()])),
  receive 
    ready -> ok 
  end,
  receive 
    {Num, true} -> 
      io:format("~p s prime~n", [Num]);
    {Num, false} -> 
      io:format("~p is not prime~n", [Num]);
    {toobig} -> 
      io:format("Can't tell, too big of a number~n");
    stop -> 
      exit(normal)
  end.

generate_ring(1, [H], Starter) -> 
  Starter ! ready,
  loop_last(ring_first, H);
generate_ring(N, [H|T], Starter) ->
  Next = spawn(?MODULE, generate_ring, [N-1, T, Starter]),
  io:format("~p~n", [Starter]),
  loop(Next, H).

loop(Next, N) -> 
  receive 
    {Num, true} -> 
      Next ! {Num, (Num rem N == 0)},
      loop(Next, N);
    {Num, false} -> 
      ring_first ! {Num, false},
      loop(Next, N);
    stop -> 
      Next ! stop,
      exit(normal);
    Other -> io:format("Received other: ~p~n", [Other])
  end. 

loop_last(Next, N) -> 
  receive  
    {Num, true} when Num > N -> 
      ring_first ! {toobig},
      loop(Next, N);
    {Num, true} -> 
      ring_first ! {Num, (Num rem N == 0)},
      loop(Next, N);
    {Num, false} -> 
      ring_first ! {Num, false},
      loop(Next, N);
    stop -> 
      unregister(ring_first),
      exit(normal);
    Other -> io:format("Received other: ~p~n", [Other])
  end. 

first_n_primes(N) -> 
  first_n_primes([], N, 2).

first_n_primes(Acc, 0, _) -> 
  lists:reverse(Acc); 
first_n_primes(Acc, Cont, ToTest) -> 
  first_n_primes(Acc, Cont, ToTest, is_prime(ToTest)).

first_n_primes(Acc, Cont, ToTest, true) -> 
  first_n_primes([ToTest|Acc], Cont-1, ToTest+1); 
first_n_primes(Acc, Cont, ToTest, false) -> 
  first_n_primes(Acc, Cont, ToTest+1).

is_prime(N) when N > 1 -> 
  [X || X <- lists:seq(2, N-1), (N rem X) == 0] == [];
is_prime(_) -> false.




