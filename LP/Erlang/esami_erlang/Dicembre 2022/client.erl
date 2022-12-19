-module(client).
% -export([]).
-compile(export_all).

start() -> 
  global:register_name(client, spawn(client, loop, [])),
  global:register_name(server, spawn(server, start_server, [])),
  global:register_name(mm1, spawn(mm, start_mm, [])),
  global:register_name(mm2, spawn(mm, start_mm, [])).

stop() -> 
  global:whereis_name(client) ! stop,
  global:whereis_name(server) ! stop,
  global:whereis_name(mm1) ! stop,
  global:whereis_name(mm2) ! stop.

to_reverse(String) -> 
  global:whereis(client) ! {toreverse, String}.

loop() -> 
  receive 
    {toreverse, String} -> 
      send_strings(String),
      loop();
    {server, Answer} -> 
      io:format("The string reverse is ~p~n", [Answer]),
      loop();
    stop -> 
      io:format("Stopping client~n");
    Other ->
      io:format("Client received other: ~p~n", [Other])
  end. 

send_strings(String) -> 
  send_strings(String, length(String) rem 2 == 1).

send_strings(String, true) -> 
  send_chars(string:slice(String, 0, (length(String) div 2)+1), string:slice(String, length(String) div 2), true);
send_strings(String, false) -> 
  send_chars(string:slice(String, 0, (length(String) div 2)), string:slice(String, length(String) div 2), false).

send_chars([], [], Odd) -> 
  global:whereis_name(mm1) ! {client, mm1, Odd, eof},
  global:whereis_name(mm2) ! {client, mm2, Odd, eof},
  loop();
send_chars([H1|T1], [H2|T2], Odd) -> 
  global:whereis_name(mm1) ! {client, mm1, Odd, H1},
  global:whereis_name(mm2) ! {client, mm2, Odd, H2},
  send_chars(T1, T2, Odd).




