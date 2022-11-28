% Write a program that will create N processes connected in a ring. Once started, these processes will send M number of messages around the ring and then terminate gracefully when they receive a quit message. You can start the ring with the call ring:start(M, N, Message).

% There are two basic strategies to tackling this exercise. The first one is to have a central process that sets up the ring and initiates sending the message. The second strategy consists of the new process spawning the next process in the ring. With this strategy, you have to find a method to connect the first process to the second process.

% Try to solve the exercise in both manners. Note, when writing your program, make sure your code has many io:format statements in every loop iteration; this will give you a complete overview of what is happening (or not happening) and should help you solve the exercise.

-module(ring).
-export([start/3, create_ring/5, await_message/2]).

start(M, N, Message) -> 
  create_ring(M, N, Message, self(), self()).


create_ring(_, 0, _, Father, Godfather) -> 
  io:format("Reached end of ring.~n"),
  await_message(Father, Godfather);
create_ring(M, N, Message, Father, Godfather) -> 
  Next = spawn_link(ring, create_ring, [M, N-1, Message, Father, Godfather]),
  await_message(Father, Next).

await_message(Father, Next) -> 
  receive 
    {Father, Message} -> io:format("process ~p received \"~p\" from ~p. Inoltrating to ~p~n", [self(), Message, Father, Next]),
    Next ! Message
  end
.












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





  
  


