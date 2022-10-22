let safe_div x y = 
  try x / y with (* pattern matching con eccezioni *) (* se non fa match risolleva l'eccezione *)
    | Division_by_zero -> 0
;;

safe_div 10 0;;

