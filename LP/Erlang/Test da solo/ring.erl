-module(ring).
-compile(export_all).
% -export([]).


start(N) -> 
  register(rring_fst, spawn(?MODULE, create, [N, self()])), %registro il primo nodo creato, che creerà gli altri
  receive 
    ready -> ok
    after 5000 -> exit(timeout)
  end,
  %wait for command 
  register(server, spawn(?MODULE, commandloop, []))
.

commandloop() -> 
  io:format("Server: ~p~n", [whereis(server)]),
  receive 
    {send, Msg} -> 
      rring_fst ! {Msg},
      commandloop();
    stop -> 
      rring_fst ! stop ,
      exit(normal)
  end. 

create(1, Starter) -> 
  Starter ! ready,
  loop_last(rring_fst);
create(N, Starter) -> 
  Next = spawn(?MODULE, create, [N-1, Starter]),
  loop(Next).


loop(Next) -> 
  receive 
    {Msg} -> 
      io:format("Process ~p received message ~p~n", [self(), Msg]),
      Next ! {Msg},
      loop(Next);
    stop ->
      Next ! stop,
      exit(normal);
    Other -> io:format("*** received other stuff: ~p ***~n", [Other])
  end.

loop_last(Next) -> 
  receive 
    {Msg} -> 
      io:format("Process ~p received message ~p~n", [self(), Msg]),
      %Next ! {Msg},
      io:format("Ring completed~n"),
      loop_last(Next);
    stop -> 
      exit(normal),
      unregister(rring_fst);
    Other -> io:format("*** received other stuff: ~p ***~n", [Other])
  end.

test(Msg) -> 
  server ! {send, Msg}.

stop() -> 
  unregister(server),
  unregister(rring_fst),
  server ! stop.


















% start(N) -> 
%   io:format("Start: ~p~n", [self()]),
%   register(first, spawn(?MODULE, create, [N])).

% create(0) -> 
%   io:format("Ring completed~n"),
%   spawn(?MODULE, loop_last, []);
% create(N) -> 
%   io:format("Ring process: ~p~n", [self()]),
%   Next = spawn(?MODULE, create, [N-1]),
%   spawn(?MODULE, loop, [Next]).

% loop(Next) -> 
%   receive 
%     {Msg} -> 
%       io:format("Process ~p received message <~p>~n", [self(), Msg]),
%       Next ! Msg,
%       loop(Next);
%     Other -> 
%       io:format("Process ~p received message <~p>~n", [self(), Other]),
%       loop(Next)
%   end. 

% loop_last() ->
%   receive 
%     {Msg} -> 
%       io:format("Process ~p received message <~p>~n", [self(), Msg]),
%       loop_last();
%     Other -> 
%       io:format("Process ~p received message <~p>~n", [self(), Other]),
%       loop_last()
%   end. 

















% start(N) -> 
%   register(server, spawn(?MODULE, create_ring, [N])).

% create_ring(N) ->
%   register(first_ring, spawn(?MODULE, generate, [N, self()])),
%   first_loop(first_ring).


% generate(0, Starter) -> 
%   Starter ! ready,
%   loop(Starter);
% generate(N, Starter) ->
%   io:format("Spawned process ~p~n", [self()]), 
%   Next = spawn(?MODULE, generate, [N-1, Starter]),
%   loop(Next).


% first_loop(Next) -> 
%   receive 
%     ready -> ok,
%       first_loop(Next);
%     {Msg} -> 
%       io:format("Sending msg to next ~p from ~p [first_loop]  ~n", [Next, self()]),
%       Next ! Msg,
%       first_loop(Next);
%     stop ->
%       Next ! stop, 
%       exit(normal);
%     Other -> 
%       io:format("Received other: ~p [first_loop]~n", [Other]),
%       first_loop(Next)
%   end.

% loop(Next) -> 
%   receive 
%     {Msg} -> 
%       io:format("Sending msg to next ~p from ~p~n", [Next, self()]),
%       Next ! Msg,
%       loop(Next);
%     stop -> 
%       Next ! stop,
%       exit(normal);
%     Other -> 
%       io:format("Received other: ~p [loop ~p]~n", [Other, self()]),
%       loop(Next)
%   end.

% test(Msg) -> 
%   server ! {Msg}.

