-module(actors).
-export([start/2, loop/2]).

start(N, A) -> spawn(actors, loop, [N, A]). %funzione spawn usata per creare nuovi attori
%spawn(MODULO, NOME, ARGOMENTI)

loop(0, A) -> io:format("~p(~p) ~p~n", [A, self(), stops]);
loop(N, A) -> io:format("~p(~p) ~p~n", [A, self(), N]), loop(N-1, A).

%exe: actors:start(7, a), actors:start(5, b), actors:start(3, c).

%ogni attore ha un pid che lo identifica e una mailbox in cui memorizzare i messaggi non ancora letti (sortati in ordine di arrivo). 

%per mandare un messaggio a un attore è necessario: conoscere il pid del target, mandare il proprio pid con il messaggio se è necessaria una reply, usare la primitiva send ! . 

%Exp1 ! Exp2, dove Expr1 deve identificare un attore, Exp2 deve essere una espressione erlang valida, il risultato della send è quello di Exp2. 

% %ricezione si fa con pattern matching:
% receive 
%   Any -> do_something(Any)
% end 

% receive 
%   {Pid, something} -> do_something(Pid)
% end 

% receive 
%   Pattern1 [when GuardSeq1] -> Body1;
%     ...
%   Pattern1 [when GuardSeqn] -> Bodyn
% [after Exprt -> Bodyt] %la clausola after viene usata per evitare che l'attore aspetti sempre, perchè se non viene matchato nessun pattern, attore rimane in attesa.
% end 



