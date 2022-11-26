% Write a program that will create N processes connected in a ring. Once started, these processes will send M number of messages around the ring and then terminate gracefully when they receive a quit message. You can start the ring with the call ring:start(M, N, Message).

% There are two basic strategies to tackling this exercise. The first one is to have a central process that sets up the ring and initiates sending the message. The second strategy consists of the new process spawning the next process in the ring. With this strategy, you have to find a method to connect the first process to the second process.

% Try to solve the exercise in both manners. Note, when writing your program, make sure your code has many io:format statements in every loop iteration; this will give you a complete overview of what is happening (or not happening) and should help you solve the exercise.

-module(ring).
-export([start1/3, start2/3]).

start1(N, M, Message) -> 
  FirstPid = spawn(ring, generate_next, [N-1, M, Message]), false.

generate_loop(0, LastPid) -> LastPid;
generate_loop(N, LastPid) -> 
  io:format("Spawning ~p~n", [N]),
  Pid = spawn(ring, initiate, []),
  Pid ! {LastPid, spawn},
  generate_loop(N-1, Pid).

initiate() -> receive
  {LastPid,spawn} -> spawn_link(ring, spawn, Arg3)  

generate_next(0, M, Message) -> LastPid;
generate_next(N, M, Message) -> spawn(ring, generate_next, [N-1, M, Message]), generate_next(N-1, M, Message).

start2(N, M, Message) -> false.
