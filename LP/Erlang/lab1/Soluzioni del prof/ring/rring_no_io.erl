%recursive ring 
-module(rring_no_io).
-export([start/3, create/3]).

%numero di messaggi, numero di nodi, messaggio
start(M, N, Msg) -> 
  %diamo un ruolo speciale al primo creato e all'ultimo creato, poichè si devono connettere
  register(rring_fst, spawn(?MODULE, create, [N, 1, self()])), %registro il primo nodo creato, che creerà gli altri
  %andiamo in attesa
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
  %looplast prende identificativo del next e identificato del prossimo processo
  loop_last(rring_fst, Who);
%Who è il progressivo che gli abbiamo dato.
create(N, Who, Starter) -> 
  Next = spawn(?MODULE, create, [N-1, Who+1, Starter]),
  loop(Next, Who).


loop(Next, Who) -> 
  receive 
    %N è numero del messaggio, Pass è quante volte è già passato
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
      %tolgo dai registrati il ring, non devo neanche dare lo stop perchè il primo nodo 
      %teoricamente è già uscito
      unregister(rring_fst);
    Other -> io:format("*** received other stuff: ~p ***~n", [Other])
  end.

% ho mandato M messaggi, dunque mando l'ultimo
msg_dispatcher(M, M, Msg) -> rring_fst ! {Msg, M, 1};
msg_dispatcher(M, N, Msg) -> rring_fst ! {Msg, N, 1}, msg_dispatcher(M, N+1, Msg).

















