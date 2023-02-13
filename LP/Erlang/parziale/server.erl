-module(server).
-compile(export_all).

start_server() -> 
  group_leader(whereis(user), self()),
  server_loop(false, false, [], []).

server_loop(true, true, L1, L2) -> 
  List1 = reorder_list(L1),
  List2 = reorder_list(L2),
  io:format("The palindromity of ~p is ~p~n", [List1++List2, is_palindrome(List1, List2)]);
server_loop(F1, F2, L1, L2) -> 
  receive 
    {part, 1, {Char, Pos}} -> 
      io:format("Server received ~p in pos ~p from mm1 ~n", [Char, Pos]),
      server_loop(F1, F2, [{Char, Pos}|L1], L2);
    {part, 2, {Char, Pos}} -> 
      io:format("Server received ~p in pos ~p from mm2 ~n", [Char, Pos]),
      server_loop(F1, F2, L1, [{Char, Pos}|L2]);
    {finished_part, 1, _} -> 
      io:format("Server received finished from mm1~n", []),
      server_loop(true, F2, L1, L2);
    {finished_part, 2, _} -> 
      io:format("Server received finished from mm2~n", []),
      server_loop(F1, true, L1, L2);
    Other -> io:format("Server received other: ~p~n", [Other]),
    server_loop(F1, F2, L1, L2)
  end.

reorder_list(L) -> 
  List = lists:sort(fun(A, B) -> {_, X} = A, {_, Y} = B, X =< Y end, L),
  lists:map(fun(A) -> {X, _} = A, X end, List).

is_palindrome(Str1, Str2) -> Str1 == string:reverse(Str2).