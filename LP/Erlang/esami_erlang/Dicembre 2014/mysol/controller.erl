-module(controller).
-compile(export_all).
%-export([start/1, generate_ring/3, loop/2, loop_last/2]).

% fix ring

start(N) -> 
  register(ring_first, spawn(?MODULE, generate_ring, [N, first_n_primes(N), self()])),
  io:format("start ~p~n", [self()]),
  io:format("server ~p~n", [whereis(server)]),
  io:format("first ~p~n", [whereis(ring_first)]),
  receive 
    ready -> 
      io:format("received ready~n")
    after 5000 -> io:format("Didnt receive ready response.~n")
  end,
  register(server, spawn(?MODULE, main_loop, [])).

main_loop() -> 
  io:format("main loop is ~p~n", [self()]),
  receive
    {start, N} -> 
      ring_first ! {N, true},
      main_loop();
    {die} -> 
      ring_first ! {die};
    {N, true} -> 
      io:format("The number ~p is prime ~n", [N]),
      main_loop();
    {N, false} -> 
      io:format("The number ~p is not prime ~n", [N]),
      main_loop();
    {N, toobig} -> 
      io:format("The number ~p is too big~n", [N]),
      main_loop();
    Other -> 
      io:format("Received other: ~p~n [main_loop]", [Other]),
      main_loop()
  end.


generate_ring(0, [], Starter) -> 
  io:format("server is ~p~n", [whereis(server)]),
  Starter ! ready;
generate_ring(N, [H|T], Starter) -> 
  Next = spawn(?MODULE, generate_ring, [N-1, T, Starter]),
  loop(N, H, Next).


loop(1, Prime, _) -> 
  receive 
    {Num, false} -> 
      io:format("Num ~p, Prime ~p~n", [Num, Prime]),
      server ! {Num, false};
    {Num, true} when Num > Prime -> 
      io:format("Num ~p, Prime ~p [toobig]~n", [Num, Prime]),
      server ! {Num, toobig};
    {Num, true} -> 
      io:format("Num ~p, Prime ~p~n", [Num, Prime]),
      server ! {Num, Num rem Prime == 0};
    Other -> 
      io:format("Received other: ~p~n", [Other])
  end; 
loop(_, Prime, Next) -> 
  receive 
    {Num, false} -> 
      io:format("Num ~p, Prime ~p~n", [Num, Prime]),
      server ! {Num, false};
    {Num, true} -> 
      io:format("Num ~p, Prime ~p~n", [Num, Prime]),
      Next ! {Num, not(Num rem Prime == 0)};
    Other -> 
      io:format("Received other: ~p~n", [Other])
  end.

test(N) ->
  server ! {start, N}.








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




