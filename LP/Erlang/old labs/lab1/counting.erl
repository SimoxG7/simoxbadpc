-module(counting).
-export([startserver/0, dummy1/0, dummy2/0, dummy3/0, loop/0, tot/0]).

startserver() -> 
  register(server, spawn(?MODULE, loop, [])).

dummy1() ->
  server ! {dummy1}. 

dummy2() -> 
  server ! {dummy2}.

dummy3() -> 
  server ! {dummy3}.

tot() -> 
  server ! {tot}.


loop() -> loop(0).

loop(N) -> 
  receive 
    {dummy1} -> io:format("Dummy1~n"), loop(N+1);
    {dummy2} -> io:format("Dummy2~n"), loop(N+1);
    {dummy3} -> io:format("Dummy3~n"), loop(N+1);
    {tot} -> io:format("Totale services done: ~p~n", [N]), loop(N+1);
    Other -> io:format("Received strange stuff: ~p~n", [Other])
  end.


