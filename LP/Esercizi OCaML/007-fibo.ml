let rec fibo = fun n -> if n <= 1 then 1 else fibo(n-1) + fibo(n-2);;

let main() =
  let input = 5;;
  print_endLine(fibo input);;

main();;

(**tail recursion:*)
let rec trfiboaux n m fib_m' fib_m = 
  if (n=m) then fib_m 
  else (trfiboaux n (m+1) fib_m (fib_m' + fib_m));;
let fibo n = if n<= 1 then 1 else trfiboaux n 1 0 1;;
