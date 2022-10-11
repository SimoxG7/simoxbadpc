1+2*3;;
let pi = 4.0 * atan 1.0;;
let pi = 4.0 *. atan 1.0;; (**uso *. per moltiplicazione con decimali*)
let square x = x*.x;;
square 5;; (**errore perchè ha int*)
square 5.;; (*funziona*)

true;;
false;;
true || false;;
not true;; 
true && false;;
true == false;;
true <> false;;
1 < 2;;
1 <= 1;;
1 > 3;;
4 >= 2;;

(*concatenazione stringa ^ *)
let nome = "Simone" and cognome = "Gioe";;
let completo = nome^" "^cognome;;
completo.[0];;
completo.[1];;
String.length(completo);;
completo.[7] <- 'x';;
completo;;

Bytes.set(completo 7 'x');;




