%recursive ring 
-module(rring).
-export([start/3, create/3]).

create(A, A, A) -> false.

start(M, N, Msg) -> 




