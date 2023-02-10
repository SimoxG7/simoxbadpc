-module(ring).
-compile(export_all).

% N processi, M messaggi 
start(N, M, Message) -> 
  spawn(ring, generate_ring, [N, M, Message]).

generate_ring(N, M, Message) -> 
  register(generator, self()),
  register(first, spawn(ring, generate, [N-1, M, Message])),
  receive
    {ring_completed, LastPid} -> io:format("Ring completed, LastPid is ~p~n", [LastPid]);
    Other -> io:format("Received other: ~p~n", [Other])
  end,
  generate_loop(whereis(last), M, Message).

generate(0, M, Message) -> 
  register(last, self()), 
  io:format("Generated process ~p [last]~n", [self()]),
  whereis(generator) ! {ring_completed, self()},
  loop(whereis(generator), M, Message);
generate(N, M, Message) -> 
  io:format("Generated process ~p~n", [self()]),
  NextPid = spawn(ring, generate, [N-1, M, Message]),
  loop(NextPid, M, Message).

loop(Next, 0, Message) -> 
  Next ! {self(), Message},
  io:format("Process ~p terminates~n", [self()]),
  exit(normal);
loop(Next, M, Message) -> 
  receive 
    {Prev, Message} -> io:format("Process ~p received message <~p> from ~p~n", [self(), Message, Prev]),
    Next ! {self(), Message},
    loop(Next, M-1, Message);
    Other -> io:format("Process ~p received other: ~p~n", [self(), Other])
  end.

generate_loop(LastPid, 0, Message) -> 
  LastPid ! {self(), Message},
  io:format("Generator terminates~n", []);
generate_loop(LastPid, M, Message) -> 
  whereis(first) !  {self(), Message},
  receive 
    {Last, Message} -> io:format("Generator ~p received message <~p> from ~p~n", [self(), Message, Last]),
    generate_loop(LastPid, M-1, Message);
    Other -> io:format("Process ~p received other: ~p~n", [self(), Other])
  end.



