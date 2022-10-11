let f x = 5;;
let f x = 7;;
f 1;;
let y = 5;;
let addy = fun x -> x+y;;
addy 8;;
let y = 10;;
addy 8;;
(fun x -> x+y) 8;;