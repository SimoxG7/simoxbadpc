%recursive ring 
-module(rring_no_io).
-export([start/3, create/3]).

start(M, N, Msg) -> 
  register(rring_fst, spawn(?MODULE, create, [N, 1, self()])), %registro il primo nodo creato, che creerà gli altri
  receive 
    ready -> ok
    after 5000 -> exit(timeout)
  end,
  msg_dispatcher(M, 1, Msg), 
  rring_fst ! stop, 
  ok
.

create(1, Who, Starter) -> 
  Starter ! ready,
  loop_last(rring_fst, Who);
create(N, Who, Starter) -> 
  Next = spawn(?MODULE, create, [N-1, Who+1, Starter]),
  loop(Next, Who).


loop(Next, Who) -> 
  receive 
    {Msg, N, Pass} -> 
      Next ! {Msg, N, Pass},
      loop(Next, Who);
    stop ->
      Next ! stop;
    Other -> io:format("*** received other stuff: ~p ***~n", [Other])
  end.

loop_last(Next, Who) -> 
  receive 
    {Msg, N, Pass} -> 
      Next ! {Msg, N, Pass+1},
      loop_last(Next, Who);
    stop -> 
      exit(normal),
      unregister(rring_fst);
    Other -> io:format("*** received other stuff: ~p ***~n", [Other])
  end.

msg_dispatcher(M, M, Msg) -> rring_fst ! {Msg, M, 1};
msg_dispatcher(M, N, Msg) -> rring_fst ! {Msg, N, 1}, msg_dispatcher(M, N+1, Msg).

















