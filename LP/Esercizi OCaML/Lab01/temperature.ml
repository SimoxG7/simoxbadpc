(* definisco le varianti *)
type t_unit = Celsius|Fahrenheit|Kelvin|Rankine|Delisle|Newtown|Reaumur|Romer;;
type temperature = { value: float; tu: t_unit };;

let any2c t =
  (*differenzio sul tipo della temperatura tu*)
  match t.tu with 
    | Celsius -> t
    | Fahrenheit -> { value = (t.value -. 32.) *. 5. /. 9.; tu = Celsius }
    | Kelvin -> { value = t.value -. 273.15 ; tu = Celsius }
    | Rankine -> { value = (t.value -. 491.67) *. 5. /. 9. ; tu = Celsius }
    | Delisle -> { value = 100. -. t.value *. 2. /. 3. ; tu = Celsius }
    | Newtown -> { value = t.value *. 100. /. 33. ; tu = Celsius }
    | Reaumur -> { value = t.value *. 5. /. 4. ; tu = Celsius }
    | Romer -> { value = (t.value -. 7.5) *. 40. /. 21. ; tu = Celsius };;

let c2any t u =
  match u with 
    | Celsius -> t
    | Fahrenheit -> { value = t.value *. 9. /. 5. +. 32.; tu = Celsius }
    | Kelvin -> { value = t.value +. 273.15 ; tu = Celsius }
    | Rankine -> { value = t.value *. 9. /. 5. +. 491.67 ; tu = Celsius }
    | Delisle -> { value = (100. -. t.value) *. 3. /. 2. ; tu = Celsius }
    | Newtown -> { value = t.value *. 33. /. 100. ; tu = Celsius }
    | Reaumur -> { value = t.value *. 4. /. 5. ; tu = Celsius }
    | Romer -> { value = t.value *. 21. /. 40. +. 7.5 ; tu = Celsius };;

(* per stampare con format *)
let p_temp ppf t =
  match t.tu with 
    | Celsius -> Format.fprintf ppf "%6.1f°C" t.value
    | Fahrenheit -> Format.fprintf ppf "%6.1f°F" t.value
    | Kelvin -> Format.fprintf ppf "%6.1f°K" t.value
    | Rankine -> Format.fprintf ppf "%6.1f°R" t.value
    | Delisle -> Format.fprintf ppf "%6.1f°De" t.value
    | Newtown -> Format.fprintf ppf "%6.1f°N" t.value
    | Reaumur -> Format.fprintf ppf "%6.1f°Re" t.value
    | Romer -> Format.fprintf ppf "%6.1f°Ro" t.value;;

let rec p_temp_list ppf =
  function 
    [] -> Format.fprintf ppf "]\n"
    | hd::[] -> Format.fprintf ppf "%a ]\n" p_temp hd
    | hd::tl -> Format.fprintf ppf "%a, " p_temp hd; p_temp_list ppf tl;;

let rec p_temp_table ppf = 
  function 
    [] -> Format.fprintf ppf "]\n"
    | hd::[] -> Format.fprintf ppf "%a ]\n" p_temp_list hd
    | hd::tl -> Format.fprintf ppf "%a, \n" p_temp_list hd; p_temp_table ppf tl;;

let cons = [Celsius; Fahrenheit; Kelvin; Rankine; Delisle; Newtown; Reaumur; Romer];;

let others t = 
  let rec others c res = function 
    (* List.rev fa il reverse di una lista generica *)
    [] -> List.rev res 
    | a_unit::tl when a_unit = t.tu -> others c res tl
    | a_unit::tl -> others c ((c2any c a_unit)::res) tl
in others (any2c t) [] cons;;

Format.printf "%a :- [ %a " p_temp { value = 18. ; tu = Celsius } p_temp_list (others {value = 18.; tu = Celsius});;














