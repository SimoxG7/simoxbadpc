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
    {'EXIT', OldPid, die} -> 
      io:format("Slave process with pid ~p died~n", [OldPid]),
      spawn_link(?MODULE, slave_func, [N]),
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
  global:register_name(N, S),
  io:format("Slave process ~p, registered as ~p~n", [S, N]),
  loop().

loop() -> 
  receive 
    die ->
      io:format("Slave ~p dies~n", [self()]), 
      global:unregister_name(global:whereis_name(self())), 
      exit(die);
    Other -> io:format("Slave ~p received ~p~n", [self(), Other]), loop()
  end.

to_slave(Msg, N) -> 
  global:whereis_name(N) ! Msg.

stop() -> 
  master_proc ! {stop}.






