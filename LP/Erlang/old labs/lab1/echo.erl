-module(echo).
-export([start/0, print/1, stop/0, loop/0, myexit/0]).

loop() -> 
  % process_flag(trap_exit, true),
  receive 
    {true, stop} -> io:format("Stopping server with pid [~p]~n", [whereis(server)]), exit(normal);
    {Stuff} -> io:format("Printing: ~p~n", [Stuff]), loop();
    {'EXIT', From, Reason} -> io:format("Exiting, reason: '~p', from: ~p~n", [Reason, From]);
    Other -> io:format("Received: ~p~n", [Other]), loop()
  end. 

start() -> 
  Server = spawn(?MODULE, loop, []),
  io:format("Created a server with pid [~p]~n", [Server]),
  register(server, Server).

print(Stuff) -> 
  server ! {Stuff}.

stop() -> 
  %io:format("Stopped the server with pid [~p]~n", [whereis(server)]),
  %server ! {true, stop}.
  spawn(?MODULE, myexit, []).

myexit() -> 
  link(whereis(server)),
  exit("stopping").




