-module(factring).
-compile(export_all).
% -export([]).

% starts a ring of N processes
start(N) -> 
  register(first, spawn(?MODULE, create_ring, [N])),
  register(server, spawn(?MODULE, commandloop, [])).

commandloop() -> 
  receive 
    ready -> 
      io:format("Process ring ready~n"),
      commandloop();
    {send, Num} -> 
      first ! {Num},
      io:format("Sending first process the num <~p>~n", [Num]),
      commandloop();
    stop -> 
      first ! stop, 
      io:format("Stopping server~n"),
      unregister(server),
      exit(normal)
  end. 

create_ring(0) -> 
  io:format("Ring completed~n"),
  server ! ready,
  loop_last();
create_ring(N) -> 
  Next = spawn(?MODULE, create_ring, [N-1]),
  loop(Next).

loop(Next) -> 
  receive 
    {Num} -> 
      io:format("Process ring ~p received message <~p>~n", [self(), Num]),
      Next ! {myfunc(Num)},
      loop(Next);
    stop -> 
      io:format("Stopping process ring ~p~n", [self()]),
      Next ! stop, 
      exit(normal);
    Other ->
      io:format("Received other: ~p~n", [Other]),
      loop(Next)
  end.

loop_last() -> 
  receive 
    {Num} -> 
      io:format("Process ring ~p received message <~p>~n", [self(), Num]),
      io:format("Ring passage completed~n"),
      server ! completed,
      server ! {send, myfunc(Num)},
      loop_last();
    stop -> 
      io:format("Stopping process ring ~p (last)~n", [self()]),
      io:format("Ring stopping completed~n"),
      exit(normal);
    Other ->
      io:format("Received other: ~p~n", [Other]),
      loop_last()
  end.

send(Msg) -> 
  server ! {send, Msg}.

stop() -> 
  server ! stop.

myfunc(N) -> 
  fact(N).

fact(1) -> 1;
fact(N) -> 
  N*fact(N-1).





