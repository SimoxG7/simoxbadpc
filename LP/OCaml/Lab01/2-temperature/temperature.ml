type temp_unit = Celsius | Fahrenheit | Kelvin;;
type temperature = {tu:temp_unit; value:float};;

let convert_to_all_from_celsius num = 
  [
    {tu = Celsius ; value = num };
    {tu = Fahrenheit ; value = num *. 1.8 +. 32. };
    {tu = Kelvin ; value = num +. 273.15 }
  ]
;;

let convert_from_tu_to_c tu num = match tu with 
  | Celsius -> {tu = Celsius ; value = num} 
  | Fahrenheit -> { tu = Celsius ; value = (num -. 32.) *. 1.8}
  | Kelvin -> { tu = Celsius ; value = num -. 273.15 }
;;

let convert_from_tu_to_all tu num = convert_to_all_from_celsius (convert_from_tu_to_c tu num).value;;

let a = convert_from_tu_to_all Celsius 15.;;

  