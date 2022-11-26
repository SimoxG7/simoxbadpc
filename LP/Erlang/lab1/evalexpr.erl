% This exercise asks you to build a collection of functions that manipulate arithmetical expressions. Start with an expression such as the following: ((2+3)-4), 4 and ~((2*3)+(3*4)) which is fully bracketed and where you use a tilde (~) for unary minus.

% First, write a parser for these, turning them into Erlang representations, such as the following: {minus, {plus, {num, 2}, {num,3}}, {num, 4}} which represents ((2+3)-4). We call these exp s. Now, write an evaluator, which takes an exp and returns its value.

% You can also extend the collection of expressions to add conditionals: if ((2+3)-4) then 4 else ~((2*3)+(3*4)) where the value returned is the “then” value if the “if” expression evaluates to 0, and it is the “else” value otherwise.

% evalexpr:eval("~((2*3)+(3*4))").

-module(evalexpr).
-export([eval/1, pop/1, is_open/1, is_op/1]).

eval(Expr) -> parse(Expr, []).

create_stack() -> [].

push(Value, Stack) -> [Value|Stack].

pop([]) -> empty;
pop([Value|Stack]) -> {Value, Stack}.

parse([H|T], Acc) -> parse(is_op(H), is_num(H), is_open(H), is_close(H), H, T, Acc).

parse(_, _, _, _, _, _, _) -> false.

charlist_to_num([H|T]) -> [H|T].

is_close(C) -> C == ')'.
is_open(C) -> C == '('.

is_num(C) -> (C >= 48) and (C =< 57).

is_op(C) -> lists:member(C, ['+', '-', '/', '*', '~']).

% member(_, []) -> false;
% member(C, [H|T]) -> member(C == H, C, T).

% member(true, _, _) -> true;
% member(false, C, T) -> member(C, T).



% trovo parentesi aperta, parso finchè non trovo operatore, poi il resto è elemento finchè non diventa parentesi chiusa.


% add_to_stack([H|T], []) -> add_to_stack(T, push(H, create_stack()));
% add_to_stack([], Stack) -> Stack;
% add_to_stack([H|T], Stack) -> add_to_stack(T, push(H, Stack)).












%todo: usare find






