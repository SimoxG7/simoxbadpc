-module(listcomp).
-export([squared_int/1, intersect/2, symmetric_difference/2]).

squared_int(List) -> square([X || X <- List, is_integer(X)], []).

square([], Acc) -> lists:reverse(Acc); 
square([H|T], Acc) -> square(T, [(H*H)|Acc]).

intersect(L1, L2) -> [X || X <- L1, lists:member(X, L2)].

symmetric_difference(L1, L2) -> [X || X <- L1 ++ L2, not(lists:member(X, L2) and lists:member(X, L1))].
