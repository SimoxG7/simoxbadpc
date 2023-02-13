-module(mm).
-compile(export_all).

start_mm(N) -> 
  group_leader(whereis(user), self()),
  mm_loop(N).

mm_loop(N) -> 
  receive 
    {part, Char, Pos} -> 
      global:whereis_name(server) ! {part, N, {Char, Pos}},
      io:format("Mm~p received ~p with pos ~p~n", [N, Char, Pos]);
    {finished_part, Cont} -> 
      global:whereis_name(server) ! {finished_part, N, Cont},
      io:format("Mm~p received finished part with cont ~p~n", [N, Cont]);
    Other -> 
      io:format("Mm~p received other: ~p~n", [N, Other])
  end, 
  mm_loop(N).




