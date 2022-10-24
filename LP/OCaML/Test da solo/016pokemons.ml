type pokemontype = TNormal | TFire | TWater

type pokemoneff = ENormal | ENotVery | ESuper 

let mult_of_eff = function 
  | ENormal -> 1.
  | ENotVery -> 0.5
  | ESuper -> 2. 

let eff = function
  | (TFire, TFire) | (TWater, TWater) | (TFire, TWater) -> ENotVery
  | (TWater, TFire) -> ESuper
  | _ -> ENormal
  




