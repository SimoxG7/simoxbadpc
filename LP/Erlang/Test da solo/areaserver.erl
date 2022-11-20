-module(areaserver).
-export([server/0]).

server() -> 
  receive 
    {rectangle, W, H} -> io:format("The area of a ~p having width ~p and height ~p is ~p.~n", [rectangle, W, H, W*H]), 
    server();
    
    {circle, R} -> io:format("The area of a ~p having radius ~p is ~p.~n", [circle, R, R * R * math:pi()]),
    server();
    
    {stop} -> io:format("Stopping.~n");

    Other -> io:format("Unknown shape: ~p.~n", [Other]), 
    server()
  end 
.