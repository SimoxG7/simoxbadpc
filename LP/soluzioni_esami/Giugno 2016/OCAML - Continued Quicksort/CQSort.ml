let cqsort (>:) l =
  let rec cqsort l k = match l with
  | ([] | [_]) -> k l
  | h :: t -> cqsort (List.filter (fun x -> h >: x) t) (fun ll -> k (cqsort (List.filter (fun x -> x >: h) t) (fun gl -> (ll @ [h]) @ gl)))
  in cqsort l (fun x -> x)
;;