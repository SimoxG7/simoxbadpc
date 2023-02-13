-module(client).
-compile(export_all).

start() -> 
  global:register_name(server, spawn_link('server@simoxbadpc', server, start_server, [])),
  global:register_name(mm1, spawn_link('mm1@simoxbadpc', mm, start_mm, [1])),
  global:register_name(mm2, spawn_link('mm2@simoxbadpc', mm, start_mm, [2])),
  global:register_name(client, self()),
  group_leader(whereis(user), self()),
  io:format("Client starting loop~n"),
  client_loop().

client_loop() -> 
  receive 
    {service, Str} -> 
      io:format("Client received service request for <~p>~n", [Str]),   
      {Str1, Str2} = split_string(Str, string:length(Str) rem 2 == 0),
      send_string(global:whereis_name(mm1), Str1, 0),
      io:format("Client sent to mm1 <~p>~n", [Str1]),
      send_string(global:whereis_name(mm2), Str2, 0),
      io:format("Client sent to mm2 <~p>~n", [Str2]);
    Other -> 
      io:format("Client received other: ~p~n", [Other])
  end,
  client_loop().

close() -> 
  exit(normal).

is_palindrome(Str) -> 
  global:whereis_name(client) ! {service, Str}.

send_string(Dest, [], Cont) -> 
  Dest ! {finished_part, Cont};
send_string(Dest, [H|T], Cont) -> 
  Dest ! {part, H, Cont},
  send_string(Dest, T, Cont+1).

split_string(Str, true) -> 
  {string:slice(Str, 0, string:length(Str) div 2), string:slice(Str, string:length(Str) div 2)};
split_string(Str, false) -> 
  {string:slice(Str, 0, (string:length(Str) div 2)+1), string:slice(Str, string:length(Str) div 2)}.
  
connect_all() -> 
  net_adm:ping(client@simoxbadpc),
  net_adm:ping(mm1@simoxbadpc),
  net_adm:ping(mm2@simoxbadpc),
  net_adm:ping(server@simoxbadpc).





