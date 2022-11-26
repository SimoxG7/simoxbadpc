-module(spawnchain).
-export([spawn_n/2, propagate/2, receiver/0]).

spawn_n(N, Father) -> spawn_loop(N, Father).

spawn_loop(0, LastPid) -> LastPid ! exit(normal);
spawn_loop(N, LastPid) -> 
  Pid = spawn_link(spawnchain, receiver(), [N, LastPid]), 
  propagate(N, Pid).

propagate(Message, LastPid) -> io:format("Process ~p propagating \"~p\" to ~p~n", [self(), Message, LastPid]), receiver(), LastPid ! Message, io:format("here").

receiver() -> receive

  {From, Message} -> io:format("Process ~p received: ~p from ~p~n", [self(), Message, From]), receiver()
end.



