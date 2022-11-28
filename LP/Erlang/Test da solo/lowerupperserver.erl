-module(lowerupperserver).
-export([start/0, loop/0]).

start() -> 
  spawn(lowerupperserver, loop, []).

loop() -> receive
  {Client, {Msg, lower}} -> 
    Client ! {self(), string:lowercase(Msg)};
  {Client, {Msg, upper}} -> 
    Client ! {self(), string:uppercase(Msg)}
  end,
  loop().

