-module(distrevstr).
-compile(export_all).

start() -> 
  register(server, spawn(server@simoxbapc, distrevstr, create_slaves, [])).

create_slaves() ->
  group_leader(whereis(user), self()), 
  create_aux(10),
  server_loop().

server_loop() -> %todo
  receive 
    {service, Str} -> false 
  end,
  server_loop().

create_aux(0) -> true;
create_aux(N) -> 
  register(list_to_atom("slave" ++ integer_to_list(N)), spawn_link(server@simoxbadpc, distrevstr, slave_loop(N))),
  create_aux(N-1).

slave_loop(N) -> 
  receive 
    {rev, Str} -> 
      %todo




