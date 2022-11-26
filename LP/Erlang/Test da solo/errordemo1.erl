-module(errordemo1).
-export([start/2]).


a() -> process_flag(trap_exit, true), wait(a).

b(A, Bool) -> process_flag(trap_exit, Bool), link(A), wait(b).

c(B, Message) -> link(B),
  case Message of 
    {die, Reason} -> exit(Reason);
    {divide, N} -> _ = 1/N, wait(c);
    normal -> true
  end
.

start(Bool, Message) -> 
  A = spawn(fun() -> a() end),
  B = spawn(fun() -> b(A, Bool) end),
  C = spawn(fun() -> c(B, Message) end),
  sleep(1000), status(b, B), status(c, C)
.

wait(Prog) -> receive
  Any -> io:format("Process ~p received ~p~n", [Prog, Any]), wait(Prog)
end.

sleep(T) -> 
  receive 
    after T -> true 
end.

status(Name, Pid) -> 
  case erlang:is_process_alive(Pid) of 
    true -> io:format("process ~p (~p) is alive~n", [Name, Pid]);
    false -> io:format("process ~p (~p) is dead~n", [Name, Pid])
  end.  



