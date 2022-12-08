-module(remoting).
-export([start/2, create/1, send_msg/2, stop/1]).

% per creare macchine:
% erl -sname amora
% erl -sname sif 
% nl(remoting). 
% remoting:start(amora@hymir, mjolnir).
% messaggi risulteranno però su questa macchina, non sull'altra, perchè l'output sarà associato alla shell da cui è iniziato. Il motivo è che esiste un concetto di group leader in erlang, in cui viene associato l'output di un gruppo al group leader. Cambiamolo cambiando la format.
% Sul server poi devo fare: global:register_name('remote_host', group_leader()). 
% 
% nodes() -> fa vedere il cluster di nodi. La visibilità di un nodo è legata all'aver fatto un tentativo di connessione con una macchina remota. Un modo per farlo è pingare la macchina remota facendo: net_adm: ping('amora@hymir').
% Ora eseguendo lo start l'output viene effettivamente dall'altro host. 

%processo remoto su N, L è informazione passata
start(N, L) -> spawn(N, remoting, create, [L]).

%non posso registrare la label, perchè siamo su macchine diverse
create(L) -> 
  group_leader(whereis(user), self()),
  global:register_name(L, self()),
  %io:format("I'm the actor <~p> created on ~p and registered as ~p~n", [self(), node(), L]),
  %devo usare una format che dica chi è il group leader
  io:format(global:whereis_name('remote-host'), "I'm the actor <~p> created on ~p and registered as ~p~n", [self(), node(), L]), 
  wait().

wait() -> 
  io:format(global:whereis_name('remote-host'), "Waiting... ~n", []),
  receive 
    {msg, M} -> io:format(global:whereis_name('remote-host'), "Here it is ~p~n", [M]), wait();
    {stop} -> io:format(global:whereis_name('remote-host'), "Stopping~n");
    Other -> io:format(global:whereis_name('remote-host'), "I got this: ~p~n", [Other])
  end.

send_msg(L, M) -> 
  global:whereis_name(L) ! {msg, M}.

stop(L) -> 
  global:whereis_name(L) ! {stop}.




