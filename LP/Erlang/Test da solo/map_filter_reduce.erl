-module(map_filter_reduce).
-export([map/2, filter/2, reduce/2]).

map(_ , []) -> []; %primo argomento è funzione, secondo lista
map(F, [H|T]) -> [F(H)|map(F, T)]. 

filter(_, []) -> [];
filter(P, [H|T]) -> filter(P(H), P, H, T).

filter(true, P, H, T) -> [H|filter(P, T)];
filter(false, P, _, T) -> filter(P, T).

reduce(F, [H|T]) -> reduce(F, H, T).

reduce(_, H, []) -> H;
reduce(F, H, [HN|T]) -> F(H, reduce(F, HN, T)).

% %map_filter_reduce:map(fun(X) -> X*X end, [1,2,3,4,5,6,7]). 
% %map_filter_reduce:filter(fun(X) -> (X rem 2)==0 end, [1,2,3,4,5,6,7]).
% %map_filter_reduce:reduce(fun(X, Y) -> X+Y end, [1,2,3,4,5,6,7]).











% -module(map_filter_reduce).
% -export([map/2,filter/2,reduce/2]).

% %TODO

% map(_,[]) -> [];
% map(F, [H|TL]) -> [F(H)|map(F, TL)].

% filter(_, []) -> [];
% filter(P, [H|TL]) -> filter(P(H), P, H, TL).

% filter(true, P, H, L) -> [H|filter(P, L)];
% filter(false, P, H, L) -> filter(P, L).

% reduce(F, [H|TL]) -> reduce(F, H, TL).

% reduce(_, Q, []) -> Q;
% reduce(F, Q, [H|TL]) -> reduce(F, F(Q, H), TL).

% %map_filter_reduce:map(fun(X) -> X*X end, [1,2,3,4,5,6,7]). 
% %map_filter_reduce:filter(fun(X) -> (X rem 2)==0 end, [1,2,3,4,5,6,7]).
% %map_filter_reduce:reduce(fun(X, Y) -> X+Y end, [1,2,3,4,5,6,7]).


