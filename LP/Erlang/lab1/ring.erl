% Write a program that will create N processes connected in a ring. Once started, these processes will send M number of messages around the ring and then terminate gracefully when they receive a quit message. You can start the ring with the call ring:start(M, N, Message).

% There are two basic strategies to tackling this exercise. The first one is to have a central process that sets up the ring and initiates sending the message. The second strategy consists of the new process spawning the next process in the ring. With this strategy, you have to find a method to connect the first process to the second process.

% Try to solve the exercise in both manners. Note, when writing your program, make sure your code has many io:format statements in every loop iteration; this will give you a complete overview of what is happening (or not happening) and should help you solve the exercise.

-module(ring).
-compile(export_all).

start(NumMessages, NumProcesses, Message) -> 
  spawn(ring, start_processes, [NumMessages, NumProcesses, Message]).

start_processes(NumMessages, NumProcesses, Message) -> 
  io:format("Spawning first process, which is ~p~n", [self()]),
  NextPid = spawn_processes(self(), self(), NumProcesses-1),
  LastPid = receive 
    {Pid, complete} -> io:format("Ring completed~n"), Pid
  end,
  start_loop(NextPid, LastPid, NumMessages, Message). 

spawn_processes(FirstPid, PrevPid, 0) -> 
  io:format("Reached last process, which is ~p~n", [self()]),
  FirstPid ! {self(), complete};
  %loop(PrevPid, FirstPid);
spawn_processes(FirstPid, PrevPid, NumProcesses) -> 
  io:format("Spawning process ~p~n", [self()]),
  NextPid = spawn(ring, spawn_processes, [FirstPid, self(), NumProcesses-1]).
  %loop(PrevPid, NextPid).

start_loop(NextPid, LastPid, 0, _) -> 
  NextPid ! {self(), stop},
  receive
    {LastPid, stop} -> 
      io:format("Stopped processes~n")
  end
;
start_loop(NextPid, LastPid, NumMessages, Message) ->
  io:format("Starting cycle. ~p more to end~n", [NumMessages]),
  NextPid ! {self(), Message},
  receive 
    {LastPid, Message} -> 
      io:format("Completed one cycle~n"),
      start_loop(NextPid, LastPid, NumMessages-1, Message)
  end
.

loop(PrevPid, NextPid) -> 
  receive 
    {PrevPid, Message} -> 
      io:format("Process ~p received the message ~p from the process ~p~n", [self(), Message, PrevPid]),
      NextPid ! {self(), Message}
  end
. 
  











% create_ring(_, 0, _, Father) ->
%   io:format("Reached end of ring.~n"),
%   await_message(Father, whereis(godfather));
% create_ring(NumMessages, NumProcesses, Message, Father) -> 
%   Next = spawn_link(ring, create_ring, [NumMessages, NumProcesses-1, Message, self( )]),
%   await_message(Father, Next).

% await_message(Father, Next) -> 
%   receive 
%     {Father, Message} -> io:format("process ~p received \"~p\" from ~p. Inoltrating to ~p~n", [self(), Message, Father, Next]),
%     Next ! Message
%   end
% .





% https://gist.github.com/stiiifff/3928673

% -module (ring).
% -export ([start/3, first_proc/3, first_loop/4, proc_start/3, proc_loop/2]).

% start(MsgCount, ProcCount, Msg) ->
% 	spawn(ring, first_proc, [MsgCount, ProcCount, Msg]).

% first_proc(MsgCount, ProcCount, Msg) ->
% 	io:format("First process ~w created, setting up the rest of the ring ...~n", [self()]),
% 	NextPid = spawn(ring, proc_start, [self(), self(), ProcCount-1]),
% 	LastPid = receive
% 				{Pid, ready} -> Pid
% 			  end,
% 	io:format("Received ready message from last process ~w, about to send messages around the ring ...~n", [LastPid]),
% 	first_loop(NextPid, LastPid, MsgCount, Msg).

% first_loop(NextPid, LastPid, MsgCount, Msg) ->
% 	case MsgCount of
% 		MsgCount when MsgCount > 0 ->
% 			NextPid ! {self(), Msg},
% 			receive
% 				{LastPid, Msg} ->
% 					first_loop(NextPid, LastPid, MsgCount-1, Msg)
% 			end;
% 		0 ->
% 			NextPid ! {self(), stop},
% 			receive
% 				{LastPid, stop} -> ok
% 			end
% 	end.

% proc_start(FirstPid, PrevPid, ProcCount) when ProcCount > 0 ->
% 	NextPid = spawn(ring, proc_start, [FirstPid, self(), ProcCount-1]),
% 	io:format("Created process ~w, ~w processes to go.~n", [NextPid, ProcCount]),
% 	proc_loop(PrevPid, NextPid);

% proc_start(FirstPid, PrevPid, 0) ->
% 	io:format("Last process ~w reached, linking back to first process ~w.~n", [self(), FirstPid]),
% 	FirstPid ! {self(), ready},
% 	proc_loop(PrevPid, FirstPid).

% proc_loop(PrevPid, NextPid) ->
% 	receive
% 		{PrevPid, Msg} ->
% 			NextPid ! {self(), Msg},
% 			io:format("Forwarded msg ~w to process ~w.~n", [Msg, NextPid]),
% 			proc_loop(PrevPid, NextPid);
% 		{PrevPid, stop} ->
% 			NextPid ! {self(), stop},
% 			ok
% 	end.





% start_loop(M, N, Message) ->
%   New = spawn_link(ring, loop, [M, N, Message, self(), self()]),
%   New ! Message,
%   receive 
%     Message -> io:format("Godfather received message, exiting~n")
%   end.


% loop(_, 0, Message, Godfather, _) -> 
%   io:format("Reached end of ring, linking to father?~n"),
%   Godfather ! Message;
% loop(M, N, Message, Godfather, Father) -> 
%   %io:format("process: ~p, father: ~p, godfather: ~p~n", [self(), Father, Godfather]),
%   New = spawn_link(ring, loop, [M, N-1, Message, Godfather, self()]),
%   receive 
%     Message -> io:format("process: ~p, father: ~p, godfather: ~p, message: ~p~n", [self(), Father, Godfather, Message]),
%     New ! Message,
%     exit("did my job")
%   end
% .





  
  


