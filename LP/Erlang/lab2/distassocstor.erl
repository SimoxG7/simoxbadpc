-module(distassocstor).
-compile(export_all).


%dividere in più file
start_client() -> 
  register(client, spawn(client@simoxbadpc, distassocstor, loop, [])),
  group_leader(whereis(user), self()).
  %register(client, spawn(client@simoxbadpc, distassocstor, loop, [])),

start_server1() -> 
  register(server1, spawn(server1@simoxbadpc, distassocstor, serverloop1, [])),
  group_leader(whereis(user), self()).
  %register(server1, spawn(server1@simoxbadpc, distassocstor, serverloop1, [])),
  
start_server2() -> 
  register(server2, spawn(server2@simoxbadpc, distassocstor, serverloop2, [])),
  group_leader(whereis(user), self()).
  %register(server2, spawn(server2@simoxbadpc, distassocstor, serverloop2, [])).

send1(K, V) -> 
  {client, 'client@simoxbadpc'} ! {send1, K, V}.

send2(K, V) -> 
  {client, 'client@simoxbadpc'} ! {send2, K, V}.

get1(K) -> 
  {client, 'client@simoxbadpc'} ! {get1, K}.

get2(K) -> 
  {client, 'client@simoxbadpc'} ! {get2, K}.

exit() -> 
  {client, 'client@simoxbadpc'} ! {exit}.

loop() -> 
  receive 
    {get1, K} -> {server1, 'server1@simoxbadpc'} ! {get, K};
    {get2, K} -> {server2, 'server2@simoxbadpc'} ! {get, K};
    {send1, K, V} -> {server1, 'server1@simoxbadpc'} ! {put, K, V};
    {send2, K, V} -> {server2, 'server2@simoxbadpc'} ! {put, K, V};
    {getresult, R} -> io:format("Got result ~p~n", [R]);
    {exit} -> 
      {server1, 'server1@simoxbadpc'} ! {exit},
      {server2, 'server2@simoxbadpc'} ! {exit},
      unregister(client),
      exit(normal)
  end,
  loop().

serverloop1() -> 
  receive 
    {exit} -> 
      unregister(server1),
      exit(normal);
    {put, K, V} -> 
      put(K, V),
      io:format("Server1 updated ~p with ~p~n", [K, V]),
      {server2, 'server2@simoxbadpc'} ! {update, K, V};
    {update, K, V} -> 
      put(K, V),
      io:format("Server1 updated ~p with ~p~n", [K, V]);
    {get, K} -> 
      io:format("Server1 received get request for ~p~n", [K]),
      {client, 'client@simoxbadpc'} ! {getresult, get(K)};
    Other -> io:format("Server1 received other: ~p~n", [Other])
  end,
  serverloop1().

serverloop2() -> 
  receive 
    {exit} -> 
      unregister(server2),
      exit(normal);
    {put, K, V} -> 
      put(K, V),
      io:format("Server2 updated ~p with ~p~n", [K, V]),
      {server1, 'server1@simoxbadpc'} ! {update, K, V};
    {update, K, V} -> 
      put(K, V),
      io:format("Server2 updated ~p with ~p~n", [K, V]);
    {get, K} -> 
      io:format("Server2 received get request for ~p~n", [K]),
      {client, 'client@simoxbadpc'} ! {getresult, get(K)};
    Other -> io:format("Server2 received other: ~p~n", [Other])
  end,
  serverloop2().







