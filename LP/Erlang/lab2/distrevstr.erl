-module(distrevstr).
-compile(export_all).

start(N) -> 
  register(server, spawn(server@simoxbapc, distrevstr, create_slaves, [N])).

create_slaves(N) ->
  group_leader(whereis(user), self()), 
  create_aux(N),
  server_loop(N).

server_loop(N) -> 
  receive 
    {service, Str} -> 
      split_string_and_send(N, Str);
    {exit} -> 
      unregister(server),
      exit(normal)
  end,
  server_loop(N).

create_aux(0) -> true;
create_aux(N) -> 
  register(list_to_atom("slave" ++ integer_to_list(N)), spawn_link(server@simoxbadpc, distrevstr, slave_loop, [N])),
  create_aux(N-1).

slave_loop(N) -> 
  receive 
    {rev, Str} -> 
      {server, 'server@simoxbapdc'} ! {reversed, string:reverse(Str), N};
    Other -> 
      io:format("Slave ~p received other: ~p~n", [N, Other])
  end,
  slave_loop(N).


split_string_and_send(N, Str) -> 
  List = list_of_splits(N, Str),
  send(N, List),
  Res = collect(N, 0, []),
  io:format("Result: ~p~n", [Res]).


list_of_splits(0, _) -> [];
list_of_splits(N, Str) -> 
  NewStr = string:slice(Str, 0, string:length(Str) div N),
  %whereis(list_to_atom("slave" ++ integer_to_list(N))) ! {rev, NewStr},
  [NewStr|list_of_splits(N-1, string:slice(Str, string:length(Str) div N))].

send(0, []) -> ok;
send(N, [H|T]) -> 
  whereis(list_to_atom("slave" ++ integer_to_list(N))) ! {rev, H},
  send(N-1, T).


collect(N, N, List) -> List;
collect(N, M, List) -> 
  receive 
    {reversed, Str, Pos} -> 
      collect(N, M+1, [{Pos, Str}|List]);
    Other -> 
      io:format("Received other in collection: ~p~n", [Other])
  end. 




 



