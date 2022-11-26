-module(spawntest).
-export([startn/1, start/2, loop/2]).

startn(1) -> start(1, 1);
startn(N) -> start(N, N), startn(N-1).

start(N, A) -> spawn(spawntest, loop, [N, A]).

loop(0, A) -> io:format("Name: ~p, Pid: ~p, Value: ~p~n", [A, self(), stops]);
loop(N, A) -> io:format("Name: ~p, Pid: ~p, Value: ~p~n", [A, self(), N]), loop(N-1, A).


