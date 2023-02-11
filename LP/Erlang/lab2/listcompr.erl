-module(listcompr).
-compile(export_all).

squared_int(L) -> 
  lists:map(fun(Y) -> Y*Y end, [X || X <- L, is_integer(X)]).

intersect(L1, L2) -> 
  [X || X <- L1, lists:member(X, L2)].

symmetric_difference(L1, L2) -> 
  [X || X <- L1 ++ L2, not(lists:member(X, L1) and lists:member(X, L2))].






