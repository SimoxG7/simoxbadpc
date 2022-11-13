(*liste sono omogenee, @ per concatenare, :: cons operator, altre dal modulo List*)
[1; 1+1; 3];;
let l1 = [1;2;3] and l2 = [4;5;6];;
let lf = l1@l2;;

let l3 = 0::l1;;
List.nth l3 5;; 

(*per vedere se elemento è nella lista*)
let alist = [0;1;2;3;4;5;6;7;8;9;10];;
let rec is_in l x = if (l == []) then false else x == List.hd(l) || is_in(List.tl l) x;;
is_in alist 5;; (*true*)
is_in alist 12;; (*false*)

(*o usare List.mem*)
List.mem 8 alist;;

(*numero di occorrenze in lista*)
let count x l 
  let rec count tot x ? function 
    [] -> tot
    | h::tl -> if (h==x) then count (tot+1) x tl else count tot x tl 
in count 0 x l 


count 7 alist;;

