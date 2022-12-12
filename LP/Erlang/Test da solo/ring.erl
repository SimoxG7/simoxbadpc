-module(ring).
-export([]).

start(N, M, Msg) ->
  register(first, spawn(?MODULE, create_n, [N, whereis(first)])).


create_n(0, Prev) -> 
  Next = spawn(?MODULE, loop, [Prev]),
  register(last, Next);
create_n(N, Prev) -> 
  Next = spawn(?MODULE, loop, [Prev]),
  create_n(N-1, Next).



