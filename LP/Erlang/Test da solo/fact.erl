-module(fact).
-export([fact/1]).

fact(0) -> 1;
fact(N) -> N * fact (N-1).

%exec: erl, poi c(fact).
%fact:fact(7) 





