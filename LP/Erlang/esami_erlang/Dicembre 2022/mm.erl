-module(mm).
%-export([]).
-compile(export_all).

mm_start() -> 
  loop().

loop() -> 
  receive 
    {client, mm1, Odd, eof} -> 
      global:whereis_name(server) ! {mm1, Odd, eof},
      loop();
    {client, mm2, Odd, eof} ->
      global:whereis_name(server) ! {mm2, Odd, eof},
      loop();
    {client, mm1, Odd, Char} -> 
      global:whereis_name(server) ! {mm1, Odd, Char},
      loop();
    {client, mm2, Odd, Char} ->
      global:whereis_name(server) ! {mm2, Odd, Char},
      loop();
    stop -> 
      exit(normal);
    Other -> 
      io:format("mm received other: ~p~n", [Other]),
      loop()
  end. 



