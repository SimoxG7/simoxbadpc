-module(exp).
-export([pow/2]).

pow(N, E) when E > 0 -> N*(pow(N, E-1));
pow(_, E) when E == 0 -> 1;
pow(_, _) -> 'cannot use negative as exp.'.

