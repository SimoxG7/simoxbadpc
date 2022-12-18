-module(moretest).
-compile(export_all).
%-export([]).

start() -> 
  register(server, spawn(?MODULE, loop, [])).

loop() ->
  receive 
    {Client, {store, Key, Value}} -> 
      put(Key, Value),
      Client ! {server, ok},
      loop();
    {Client, {lookup, Key}} -> 
      Client ! {server, get(Key)},
      loop();
    stop -> 
      unregister(server),
      exit(normal);
    Other -> 
      io:format("Received other: ~p~n", [Other]),
      loop()
  end.

rpc(Q) -> 
  server ! {self(), Q},
  receive 
    {server, Reply} -> 
      Reply;
    Other -> io:format("rpc received other: ~p~n", [Other])
  end.

store(Key, Value) -> 
  rpc({store, Key, Value}).

lookup(Key) -> 
  rpc({lookup, Key}).

stop() -> 
  server ! stop.

test() -> 
  start(),
  store("Simox", "God"),
  lookup("Simox").


