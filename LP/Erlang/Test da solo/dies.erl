-module(dies).
-export([on_exit/2]).

% per vedere come muore un processo
on_exit(Pid, Fun) -> 
  spawn(fun() -> 
    process_flag(trap_exit, true),
    link(Pid),
    receive
      {'EXIT', Pid, Why} -> Fun(Why)
    end
  end)
.

