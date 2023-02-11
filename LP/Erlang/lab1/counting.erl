-module(counting).
-compile(export_all).

start() -> 
  register(server, spawn(counting, setup, [])).

setup() -> 
  put(tot, 0),
  put(dummy1, 0),
  put(dummy2, 0), 
  put(dummy3, 0),
  serverloop().

serverloop() -> 
  receive 
    {exit} -> 
      unregister(server),
      exit(normal);
    {tot} -> 
      put(tot, get(tot)+1),
      io:format("Dictionary: ~p~n", [get()]);
    {S} -> 
      put(S, get(S)+1),
      io:format("Incremented count for ~p to ~p~n", [S, get(S)]);
    Other -> 
      io:format("Server received other: ~p~n", [Other])
  end,
  serverloop().

dummy1() -> 
  whereis(server) ! {dummy1}.

dummy2() -> 
  whereis(server) ! {dummy2}.

dummy3() -> 
  whereis(server) ! {dummy3}.

tot() -> 
  whereis(server) ! {tot}.

exit() -> 
  whereis(server) ! {exit}.

test() -> 
  start(),
  dummy1(),
  dummy2(),
  dummy2(),
  tot(),
  dummy3(),
  dummy3(),
  dummy3(),
  tot(),
  exit().









