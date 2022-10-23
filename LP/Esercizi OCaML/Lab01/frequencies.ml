open Str 

let filename = "prova.txt";;

let channel = open_in filename in 
  let word_list = ref [] in 
    try 
      while true do
        let line = input_line channel in 
          word_list := reader line ! word_list
      done 
    with 
      End_of_file -> print_list !word_list

  




