open Contiguous_region;;
open Printf
let a = contiguous_region [1;2;3;4;5];;
let () = List.iter (printf "%d ") a;;
