-module(spawnlist).
-export([spawns/1]).

myfunc(X) -> timer:sleep(10), io:format("~p~n", [X]).

spawns(N) -> [spawn(myfunc(X)) || X <- lists:seq(1, N)].








