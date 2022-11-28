-module(lowerupperclient).
-export([changecase/3]).

changecase(Server, Msg, Command) -> 
  Server ! {self(), {Msg, Command}},
  receive 
    {Server, Resultstring} -> 
      Resultstring
  end.
