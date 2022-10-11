(* aliasing per definire un nuovo tipo da un tipo esistente *)
type int_pair = int*int;;
let a : int_pair = (1,3);;
fst a;;

(* variants: lista tutte le possibili forme per i valori di quel tipo *)
type int_option = Nothing | Aninteger of int;;
Nothing;;
Aninteger 7;;

(* tipi mutualmente ricorsiiv devono essere dichiarati usando la keyword and *)
type card = Card of regular | Joker
  and regular = { suit : card_suit; name : card_name; }
  and card_suit = Heart | Club | Spade | Diamond 
  and card_name = Ace | King | Queen | Jack | Simple of int;;
let value = function
  Joker -> 0
  | Card {name = Ace} -> 11
  | Card {name = King} -> 10
  | Card {name = Queen} -> 9
  | Card {name = Jack} -> 8
  | Card {name = Simple n} -> n;;


(* varianti sono l'equivalente di una gerarchia di classi composta da una classe astratta di base (o interfaccia) rappresentante il tipo e le classi derivanti rappresentanti ciascuna i costruttori di tipo della variante *)
type state= On | Off;;
let turn = function
  On -> Off
  | Off -> On
;;



