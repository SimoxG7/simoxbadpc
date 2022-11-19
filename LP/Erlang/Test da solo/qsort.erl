-module(sort).
-export([qsort/2]).

qsort(_, []) -> [];
qsort(P, [Pivot|T]) -> qsort(P, [X||X <- T, P(X, Pivot)]) ++ [Pivot] ++ qsort(P, [X||X <- T, not P(X, Pivot)]).



