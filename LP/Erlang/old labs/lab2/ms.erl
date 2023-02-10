-module(ms).
-export([start/1, to_slave/2, create_n/2, slave_func/1, stop/0]).

start(N) -> 
  register(master_proc, spawn(?MODULE, create_n, [N, N])).

create_n(0, _) -> io:format("Created the slave processes.~n", []);
create_n(Curr, N) -> 
  process_flag(trap_exit, true),
  spawn_link(?MODULE, slave_func, [Curr]),
  create_n(Curr-1, N),
  master_loop(N+1).

master_loop(N) -> 
  receive 
    {'EXIT', OldPid, {die, Name}} -> 
      io:format("Slave process with pid ~p died, restarting~n", [OldPid]),
      spawn_link(?MODULE, slave_func, [Name]),
      master_loop(N+1);
    {stop} -> 
      unregister(master_proc),
      exit(normal);
    Other -> 
      io:format("Master received other: ~p~n", [Other]),
      master_loop(N)
  end.

slave_func(N) -> 
  S = self(),
  global:re_register_name(N, S),
  io:format("Slave process ~p, registered as ~p~n", [S, N]),
  loop(N).

loop(N) -> 
  receive 
    die ->
      io:format("Slave ~p dies~n", [self()]), 
      exit({die, N});
    Other -> io:format("Slave ~p received ~p~n", [self(), Other]), loop(N)
  end.

to_slave(Msg, N) -> 
  global:whereis_name(N) ! Msg.

stop() -> 
  master_proc ! {stop}.






