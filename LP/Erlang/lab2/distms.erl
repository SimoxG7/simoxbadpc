-module(distms).
%-export([]).
-compile(export_all).

start(N) -> 
  create_slaves(N, 1),
  register(master, spawn(?MODULE, master_loop, [])).
  

create_slaves(N, Number) -> 
  Next = spawn(?MODULE, create_slaves, [N-1, Number+1]),
  slave_func()
  




