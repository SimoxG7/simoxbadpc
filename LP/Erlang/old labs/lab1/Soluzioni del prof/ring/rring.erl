%recursive ring 
-module(rring).
-export([start/3, create/3]).

%numero di messaggi, numero di nodi, messaggio
start(M, N, Msg) -> 
  %diamo un ruolo speciale al primo creato e all'ultimo creato, poichè si devono connettere
  register(rring_fst, spawn(?MODULE, create, [N, 1, self()])), %registro il primo nodo creato, che creerà gli altri
  %andiamo in attesa
  
  io:format("*** [rring_fst] is at ~p~n", [rring_fst]),
  
  receive 
    ready -> ok
    after 5000 -> exit(timeout)
  end,
  %una volta che il ring è pronto, iniziamo a far mandare i messaggi, dopodichè fermiamo.
  msg_dispatcher(M, 1, Msg), 
  rring_fst ! stop, 
  ok
.

%caso in cui ho creato tutti i processi
create(1, Who, Starter) -> 
  Starter ! ready,
  io:format("*** created [~p] as ~p connected to ~p~n", [self(), Who, rring_fst]),
  %looplast prende identificativo del next e identificato del prossimo processo
  loop_last(rring_fst, Who);
%Who è il progressivo che gli abbiamo dato.
create(N, Who, Starter) -> 
  Next = spawn(?MODULE, create, [N-1, Who+1, Starter]),
  io:format("*** created [~p] as ~p connected to ~p~n", [self(), Who, Next]),
  loop(Next, Who).


loop(Next, Who) -> 
  receive 
    %N è numero del messaggio, Pass è quante volte è già passato
    {Msg, N, Pass} -> 
      io:format("[~p] got {~p ~p} for the ~p time ~n", [Who, Msg, N, Pass]),
      io:format("*** [~p] is ~p alive? ~p~n", [Who, Next, erlang:is_process_alive(Next)]),
      Next ! {Msg, N, Pass},
      io:format("*** [~p] sent ~p to [~p]~n", [Who, N, Next]),
      loop(Next, Who);
    stop ->
      io:format("[~p] got {stop}~n", [Who]),
      io:format("*** [~p] is ~p alive? ~p~n", [Who, Next, erlang:is_process_alive(Next)]),
      Next ! stop,
      io:format("*** [~p] sent {stop} to [~p]~n", [Who, Next]),
      io:format("# Actor ~p stops ~n", [Who]);
    Other -> io:format("*** received other stuff: ~p ***~n", [Other])
  end.

loop_last(Next, Who) -> 
  receive 
    {Msg, N, Pass} -> 
      io:format("[~p] got {~p ~p} for the ~p time [loop_last] ~n", [Who, Msg, N, Pass]),
      io:format("*** [~p] is ~p alive? ~p [loop_last]~n", [Who, Next, erlang:is_process_alive(whereis(Next))]),
      Next ! {Msg, N, Pass+1},
      io:format("*** [~p] sent ~p to [~p] [loop_last]~n", [Who, N, Next]),
      loop_last(Next, Who);
    stop -> 
      io:format("[~p] got {stop} [loop_last]~n", [Who]),
      exit(normal),
      %tolgo dai registrati il ring, non devo neanche dare lo stop perchè il primo nodo 
      %teoricamente è già uscito
      unregister(rring_fst),
      io:format("*** [~p] unregistered [~p]~n", [Who, rring_fst]),
      io:format("# Actor ~p stops [loop_last]~n", [Who]);
    Other -> io:format("*** received other stuff: ~p ***~n", [Other])
  end.

% ho mandato M messaggi, dunque mando l'ultimo
msg_dispatcher(M, M, Msg) -> rring_fst ! {Msg, M, 1};
msg_dispatcher(M, N, Msg) -> rring_fst ! {Msg, N, 1}, msg_dispatcher(M, N+1, Msg).

















