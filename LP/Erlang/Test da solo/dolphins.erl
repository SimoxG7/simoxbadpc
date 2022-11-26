-module(dolphins).
-compile(export_all).

dolphin1() ->
  receive
    do_a_flip -> io:format("Doing a flip!~n"), dolphin1();
    fish -> io:format("Thank you for the fish!~n"), dolphin1();
    kill_dolphin -> io:format("Why u killing me nword~n"); 
    _ -> io:format("Dafuq you sent me, dumbass~n"), dolphin1()
end.

% Dolphin = spawn(dolphins, dolphin1, []).
% Dolphin ! "oh, hello dolphin!".

