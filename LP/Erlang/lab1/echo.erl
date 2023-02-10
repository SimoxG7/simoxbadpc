-module(echo).
-compile(export_all).

start() -> 
  register(server, spawn(echo, serverloop, [self()])).

print(T) -> 
  whereis(server) ! {print, T},
  receive 
    {printed, M} -> io:format("Echo completed for: ~p~n", [M]);
    Other -> io:format("Echo completed with other: ~p~n", [Other])
  end.

serverloop(D) -> 
  receive
    {print, M} -> 
      io:format("Server received print: ~p~n", [M]),
      D ! {printed, M};
    {stop} -> 
      io:format("Server stopping~n"),
      unregister(server),
      exit(normal);
    Other -> io:format("Server received other: ~p~n", [Other])
  end,
  serverloop(D).

stop() -> 
  whereis(server) ! {stop}.





