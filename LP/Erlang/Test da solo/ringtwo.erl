-module(ringtwo).
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
    {send, Msg} -> 
      first ! {Msg},
      io:format("Sending first process the message <~p>~n", [Msg]),
      receive 
        completed -> 
          io:format("All ring processes received the message~n")
        after 5000 -> 
          io:format("Timeout")
      end,
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
    {Msg} -> 
      io:format("Process ring ~p received message <~p>~n", [self(), Msg]),
      Next ! {Msg},
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
    {Msg} -> 
      io:format("Process ring ~p received message <~p>~n", [self(), Msg]),
      io:format("Ring passage completed~n"),
      server ! completed,
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






