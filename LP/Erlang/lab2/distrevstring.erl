-module(distrevstring).
-export([long_revstring/2, generate_split_indexes/2]).


long_revstring(Str, N) -> false.


generate_split_indexes(N, L) -> 
  [X || X <- lists:seq(0, L-1), X rem (L div N) == 0].

