% group_leader(whereis(user), self()) -> leader di te stesso sul tuo user (terminale?)
-module(server).
%-export([]).
-compile(export_all).

start_server() -> 
  loop([], [], false, false, false).

loop(Acc1, Acc2, true, true, Odd) -> 
  do_reverse(Acc1, Acc2, Odd);
loop(Acc1, Acc2, Eof_mm1, Eof_mm2, _) -> 
  receive 
    {mm1, Odd, eof} -> 
      loop(Acc1, Acc2, true, Eof_mm2, Odd);
    {mm2, Odd, eof} -> 
      loop(Acc1, Acc2, Eof_mm1, true, Odd);
    {mm1, Odd, Char} -> 
      loop([Char|Acc1], Acc2, Eof_mm1, Eof_mm2, Odd);
    {mm2, Odd, Char} -> 
      loop(Acc1, [Char|Acc2], true, Eof_mm2, Odd);
    stop -> 
      io:format("Stopping server ~n"),
      exit(normal);
    Other -> 
      io:format("Server received other: ~p~n", [Other])
  end.

% Simox -> 
%   Sim | mox
%   miS | xom
%   str2 + str1 da 1 in poi
do_reverse(Acc1, Acc2, true) -> 
  global:whereis_name(client) ! {server, string:concat(lists:reverse(Acc2), string:slice(lists:reverse(Acc1), 1))};
do_reverse(Acc1, Acc2, false) ->
  global:whereis_name(client) ! {server, string:concat(lists:reverse(Acc2), lists:reverse(Acc1))}.




