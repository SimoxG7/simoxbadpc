-module(localdisttest).
-export([start/0, store/2, lookup/1]).

start() -> 
  register(server, spawn(fun () -> loop() end)).

loop() -> 
  receive 
    {Pid, {store, Key, Value}} ->
      put(Key, {ok, Value}), 
      Pid ! {server, true},
      loop();
    {Pid, {lookup, Key}} -> 
      Pid ! {server, get(Key)},
      loop()
  end.

rpc(Q) -> 
  server ! {self(), Q},
  receive 
    {server, Reply} -> Reply
  end.

store(Key, Value) -> 
  rpc({store, Key, Value}).

lookup(Key) -> 
  rpc({lookup, Key}). 

%per chiamare da un'altra macchina:
% rpc:call(nomemacchina@simoxbadpc, localdisttest, store, [key, value]).
%being: rpc:call(nomemacchina@macchina, module, func, args).