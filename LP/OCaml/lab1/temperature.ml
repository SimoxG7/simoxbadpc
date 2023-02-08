type scale = 
| Celsius 
| Fahrenheit
| Kelvin 
;;

type temperature = {scalename:scale; value:float}

let to_all_from_celsius num = 
  [
    {scalename = Celsius; value = num};
    {scalename = Fahrenheit; value = (num *. 1.8) +. 32.};
    {scalename = Kelvin; value = num +. 273.15}
  ]
;;

let to_celsius_from_tu temp = match temp.scalename with 
| Celsius -> temp.value
| Fahrenheit -> (temp.value -. 32.) *. (5. /. 9.)
| Kelvin -> temp.value -. 273.15
;;

let from_scale_to_all temp = to_all_from_celsius (to_celsius_from_tu temp);;
