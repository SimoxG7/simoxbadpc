-module(ring).
-compile(export_all).
% -export([]).

start(N) -> 
  register(server, spawn(?MODULE, create_ring, [N])).

create_ring(N) ->
  register(first_ring, generate(N, self())),
  first_loop(first_ring).


generate(0, Starter) -> 
  Starter ! ready,
  loop(Starter);
generate(N, Starter) -> 
  Next = spawn(?MODULE, generate, [N-1, Starter]),
  loop(Next).


first_loop(Next) -> 
  receive 
    ready -> ok,
      first_loop(Next);
    {Msg} -> 
      io:format("Sending msg to next ~p from ~p~n", [Next, self()]),
      Next ! Msg,
      first_loop(Next);
    stop ->
      Next ! stop, 
      exit(normal);
    Other -> 
      io:format("Received other: ~p [first_loop]~n", [Other]),
      first_loop(Next)
  end.

loop(Next) -> 
  receive 
    {Msg} -> 
      io:format("Sending msg to next ~p from ~p~n", [Next, self()]),
      Next ! Msg,
      loop(Next);
    stop -> 
      Next ! stop,
      exit(normal);
    Other -> 
      io:format("Received other: ~p [loop]~n", [Other]),
      loop(Next)
  end.

test(Msg) -> 
  whereis(first_ring) ! {Msg}.

