-module(converter).
-export([t_converter/0]).

t_converter() ->
  receive 
    {toF, C} -> io:format("~p °C -> ~p °F ~n", [C, to_F(C)]), t_converter();
    {toC, F} -> io:format("~p °F -> ~p °C ~n", [F, to_C(F)]), t_converter();
    {stop} -> io:format("Stopping. ~n");
    Other -> io:format("Unknown command: ~p~n", [Other]), t_converter()
  end
.

to_F(C) -> 32 + (C * 1.8).

to_C(F) -> (F - 32) / 1.8.

%execute with: Pid = spawn(converter, t_converter, []).
%to send messages: Pid ! {msg}. 

%una volta fermato i messaggi al processo vengono ignorati. 

