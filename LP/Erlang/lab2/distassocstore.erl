% kinda works, but prob it wanted something else aswell

% didnt test on multiple hosts

-module(distassocstore).
-export([start_server/0, loop/1, store/2, lookup/1, stop_server/0]).

start_server() -> 
  register(server, spawn(?MODULE, loop, [dict:new()])).

loop(Dict) -> 
  receive 
    {Pid, store, {Key, Val}} -> 
      io:format("Stored key ~p associated with val ~p~n", [Key, Val]), 
      Pid ! {stored},
      loop(dict:store(Key, Val, Dict));
    {Pid, lookup, {Key}} -> 
      Pid ! dict:find(Key, Dict),
      io:format("find: ~p~n", [dict:find(Key, Dict)]),
      loop(Dict); %lookup here
    {stop} -> 
      io:format("Exiting ~n"),
      unregister(server),
      exit(normal);
    Other -> io:format("Received other: ~p~n", [Other]), loop(Dict)
  end
. 

store(Key, Val) -> 
  server ! {self(), store, {Key, Val}},
  receive 
    {stored} -> io:format("Client received store confirmation~n", []);
    Other -> io:format("Client did not receive store confirmation. Received: ~p~n", [Other])
  end.

lookup(Key) -> 
  server ! {self(), lookup, {Key}},
  receive 
    {ok, Val} -> io:format("Client received lookup confirmation: ~p~n", [Val]);
    error -> io:format("No such key stored in the server. Key inserted: ~p~n", [Key]);
    Other -> io:format("Client did not receive lookup confirmation. Received: ~p~n", [Other])
  end.
  
stop_server() ->
  server ! {stop}.

