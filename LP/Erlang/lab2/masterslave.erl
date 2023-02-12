-module(masterslave).
-compile(export_all).

start_service(N) -> 
  %register(server, spawn(server@simoxbadpc, masterslave, create_slaves, [N])).
  spawn(server@simoxbadpc, masterslave, create_slaves, [N]).

kill(N) -> 
  {server, 'server@simoxbadpc'} ! {kill, rand:uniform(N)}.

create_slaves(N) -> 
  register(server, self()),
  group_leader(whereis(user), self()),
  process_flag(trap_exit, true),
  create_aux(N),
  server_loop(lists:seq(1, N)).

create_aux(0) -> true;
create_aux(N) -> 
  register(list_to_atom([N]), spawn_link(server@simoxbadpc, masterslave, slave_loop, [N])),
  create_aux(N-1).

check_processes([]) -> io:format("Checks done~n", []);
check_processes([H|T]) ->
  check_processes(H, T, is_process_alive(whereis(list_to_atom([H])))).

check_processes(_, T, true) -> check_processes(T);
check_processes(H, T, false) -> 
  register(list_to_atom([H]), spawn(masterslave, slave_loop, [H])),
  io:format("Recreated process ~p~n", [H]),
  check_processes(T).
  
server_loop(List) -> 
  receive 
    {kill, A} -> 
      io:format("Server received kill slave ~p~n", [A]),
      whereis(list_to_atom([A])) ! {exit};
    {exit} -> 
      io:format("Server exiting~n", []),
      unregister(server),
      exit(normal);
    {'EXIT', Pid, normal} -> 
      spawn_link(server@simoxbadpc, masterslave, slave_loop, []),
      io:format("Server recreated process");
    Other -> 
      io:format("Server received other: ~p~n", [Other])
    after 10000 -> 
      check_processes(List)
  end,
  server_loop(List).

slave_loop(N) -> 
  receive 
    {exit} ->
      unregister(list_to_atom([N])), 
      exit(normal);
    Other -> 
      io:format("Slave ~p received other: ~p~n", [self(), Other])
  end,
  slave_loop(N).

pings() -> 
  net_adm:ping(server@simoxbadpc).

pingc() -> 
  net_adm:ping(client@simoxbadpc).





