module FileRead = 
  struct
    let ic_from_path path = open_in path
    let read_line_from_ic ic = input_line ic
    let read_char_from_ic ic = input_char ic
 
    let read_all_lines path =
      let ic = ic_from_path path in
      let rec aux acc =
        try aux (read_line_from_ic ic :: acc) with
          End_of_file -> close_in ic; acc
      in List.rev (aux [])
 
    let read_all_chars path =
      let ic = ic_from_path path in
      let rec aux acc =
        try aux (read_char_from_ic ic :: acc) with
          End_of_file -> close_in ic; acc
      in List.rev (aux [])
 
    let read_all_letters path =
      let ic = ic_from_path path in
      let rec aux acc =
        try
          match read_char_from_ic ic with
            'a'..'z' | 'A'..'Z' as c -> aux (c :: acc)
          | _                        -> aux acc
        with End_of_file -> close_in ic; acc
      in List.rev (aux [])
 
    let read_chars_filtered path f =
      let ic = ic_from_path path in
      let rec aux acc =
        try 
          let c = read_char_from_ic ic in
          if f c then aux (c :: acc) else aux acc
        with End_of_file -> close_in ic; acc
      in List.rev (aux []) 
  end
 
let fp = "test_file.txt";;
FileRead.read_all_lines fp;; (* legge tutte le righe e le mette in una lista di stringhe *)
FileRead.read_all_chars fp;; (* legge tutti i caratteri e li mette in una lista di char *)
FileRead.read_all_letters fp;; (* legge tutte le lettere e li mette in una lista di char *)
let numbers_as_chars = 
  FileRead.read_chars_filtered fp (fun c -> c >= '0' && c <= '9');; (* legge tutti i numeri e li mette in una lista di char *)
 
List.map (fun c -> int_of_char c - 48) numbers_as_chars (* converte la lista di caratteri in una lista di interi *) 