# Debugging

Insieme delle tecniche che mirano a trovare e correggere l'anomalia che causa un malfunzionamento noto.
Queste tecniche **non** devono essere usate per trovare malfunzionamenti.
L'attività è definita dato un programma e un insieme di input per cui so che non funziona, si verifica un malfunzionamento. Si basa sulla riproducibilità del malfunzionamento, se non è riproducibile non si può fare debugging, sarebbe un attività infinita.

La fonte principale di errori nei programmi è il fraintendimento nelle specifiche. Dato che fare debug è più difficile che scrivere codice, se scrivi codice il più intelligente possibile non sei ababstanza intelligente per farne debugging per definizione. Scrivere codice stupido per essere in grado di verificarne la correttezza.

Problemi:
- Il legame anomalia-malfunzionamento non è bananle, non è sempre diretto (può essere molto lontano, prima che il malfunzionamento si manifesti possono essere state eseguite molte altre operazioni).
- Non è detto che un malfunzionamento sia dovuto ad una singola anomalia. Penso di aver risolto ma in realtà ho trovato solo uno dei fattori che causano il problema.

    10 little bugs were in the code.
    Take one down, patch it around.
    27 little bugs were in the code.

C'è chi dice che la fonte principale di bachi è la correzione di altri bachi, ne correggi 1 e ne inserisci 18. NOn è garantito che otterrò software migliore, posso fare danni arbitrari. Se fai una riparazione stupida invece di corrggere inserisci errori.

Tecniche che abbiamo a disposizione:

# Tecnica Naïve

Stampare con una print lo stato è un mezzo molto debole. Perchè non solo bisogna ricompilare ogni volta, una volta ci voleva molto e capitava di mandare in produzione cose con messaggi di debug che ci si era scordati di rimuovere. Può andare bene se hai scritto del codice talmente isolato talmente tanto bene che sei sicuro che capisci tutto con una println (scenario un po'ideale).

Stato incapsulato, cosa sto testando? Il programma che mando in produzione o un programma diverso che ha delle funzioni in piu e quindi un codice binario/bytecode diverso? Capita in C di aggiungere una println ed il baco non si presenta, la togli e crasha di nuovo (mettere questa ispeziona fa funzionare ma non posso lasciarla, non ho corretto nulla).

# Tecnica Naïve avanzata

Possiamo migliroare usando funzionalità del linguaggio o degli altri tool (in C era comodo usare il preprocessore oppure #ifdef e -D nel comando, se in debug stampa altrimenti no, in java non c'è un preprocessore ma ci sono librerie di logging che hanno vari livelli waring,info,debug,error).
Oppure le asserzioni, ci sono in sviluppo ma non in produzione. Sono più carine perchè non sono la presentazione di uno stato ma la valutazione di uno stato. Segnalo uno stato che non mi piace e mi blocco invece che solo stampare. Non essendo un if esplicito posso lascirlo e non ci sarà in produzione.

# Dump di memoria

Scrivo su un file il contenuto intero della memoria (32bit = 4gb memoria) e non mi dice dove c'è una variabile, sono tutti bit. Prima di crashare il programma 'segmentation fault, core dumped'. Ho scritto sul disco tutto quello che c'era in memoria, guardatelo. QUello che si faceva era 'remove core dumped', guardare quel file enorme con tutte quelle informazioni era impossibile e inutile. A questo punto dell'esecuzione genera dump e prosegui. Potevi ricostruire ma che fatica. Era impossibile capire e usare questi dati, inoltre non ottengo informazioni allo stesso livello di astrazione del linguaggio con cui le ho scritte ma rispetto all'assembler di cui non conoscevo la traduzione.

# Debugging simbolico

Fare debug fornendo le informazioni sullo stato del programma con gli stessi simboli con cui gli hai definiti (la variabile D contiene ...) da le informazioni allo stesso livello d'astrazione con cui le abbiamo scritte. Diventa veramente utile guardare lo stato in cui ci si trova, di cosa c'è sullo stack ...
In aggiunta ci sono dei mezzi con cui possiamo andare a fare un esecuzione segmentnata decidendo dei punti di interruzione (breakpoint) eventualmente condizionali (watchpoint). Fermati in questa posizione se questa variabile, fermati ovunque tu sia se viene modificato il valore di questa cella. Non è qualcosa che scrivo nel codice ma nell'esecutore, strumenti esterni che senza modificare il codice cambiano il modo con cui lo eseguono.

# Debugging per prova

Continuo a monitorare gli stati (indici di un array interni a un certo intervallo), rallenta l'esecuzione del codice per fare dei controlli che normalmente non farebbero.

# Altre funzionalità debugger

Possono gestire la granularità del passo di esecuzione:
- singolo passo (decidiamo noi)
- entrata in una funzione
- drop reset del frame (singifica tornare indietro, le variabili locali che avevo sullo stack di quella procedura cancellale, e ricomincia ad eseguirla da capo, va bene se le modifiche effettuate erano tutte sullo stack, se cambio qualcosa all'esterno quelli non li resetto)

Possiamo modificare il valore delle variabili (o di zone di memoria) posso modificare il codice.
Possiamo avere rappresentazioni grafiche dei dati (intellij debugger degli stream, possiamo vedere la pipeline degli stream, su varie colonne lo stream primario, trasofmrizone cosa diventa e la mappatura degli elementi).

È possibile automatizzare il debugging?

Due approcci:
- shrinking ???: ho un grosso input che fa fallire/crashare il programma, quello che fa è va a modificare in modo automatico questo input in modo da ridurne sempre più la dimensione e caratterizzare maggiormente quello che è la fonte del problema.
- piccole mutazioni di codice: stesso input ma esplorano gli stati in modo da identificare il punto in cui 

Partizionamento del codice o dell'input.