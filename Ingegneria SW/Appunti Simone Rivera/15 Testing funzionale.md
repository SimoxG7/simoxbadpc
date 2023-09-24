# Testing funzionale

Definizione del testing black box è che non c'è o non si sfrutta per fare testing la conoscenza del codice, lo guardiamo dal punto di vista esterno. Può essere l'unico approccio possibile (impossibilità di accedere ai sorgenti) ma delle volte è una scelta perchè fa delle cose diverse dal testing strutturale, ci sono degli errori che non noteremmo con tutti i criteri del mondo. Ad esempio: mi sono dimenticato di implementare una funzionalità, se non l'ho scritta nel codice posso coprire quello che voglio, non c'è. Partire dalle specifiche serve, e siamo ancora verifica (rispetto alle specifiche, non rispetto alle idee del cliente che possono essere cambiate).

Magari alcuni casi devono essere trattati particolamente (numero negativi hanno caratteristiche particolari ad esempio) non l'ho scritto nel codice ma se copro solo il codice potrei non selezionare mai un numero negativo perchè non è evidente, non lo guardo, posso pensare che nelle specifiche è scritto che non può essere un numero negativo ma poi in realtà non c'era.

Permette di trovare problemi di prestazioni o d'interfaccia tra sistemi diversi, anche di usabilità volendo.

Teniche:
- metodi basati sui grafi (possiamo ragionare anche sugli altri diagrammi che abbiamo visto come quello di sequenza).
- suddivisione del dominio in classi di equivalenza, nel momento in cui riconosciamo dalle specifiche una serie di tuple in ingresso (caratterizziamo il dominio di ingresso) possiamo ragionarci sopra per capire se è stato capace di trattare 36 sarà anche in grado di trattare 25 per la radice quadrata, sono equivalenti per me dal punto di vista di come devono essere trattati dal programma, non serve testarli tutti ma basta un esemplare per ogni classe individuata. Magari anche solo informalmente. A volte non ci si accorge che si fanno casi di test tutti nella stessa classe trascurandone altre. Vedremo la category partition.
- analisi dei valori limite (test di frontiera): si riconosce che all interno dei possibili valori quelli che sono tra una categoria e l'altra possono essere più facilmente essere trattati in maniera erronea. Ha più senso testare lo 0 e il -1 negli interi piuttosto che l'ultimo elemento di un array per la dimensione array perchè si rischia di andare oltre.
- collaudo per confronto: confrontiamo con versioni precedenti che potevano essere scritte in un altro linguaggio piuttosto che la versione precedente del software che sta evolvendo, test di non regressione, quello che sapeva fare prima deve saperlo fare anche ora, non deve tornare indietro. Questo test può essere pensato non solo rispetto ad un eseguibile ma anche a delle specifiche eseguibili. Spesso cercheremo di usare dei linguaggi di specifica formali che possano essere simulati ed eseguiti. identifichino le caratteristiche peculiari del sw e siano eseguibili (linguaggi operazionali formali eseguibili).

### Testing delle interfacce

Non è solo la signature (""""interfaccia della funzione"""") e non è assolutamente l'interfaccia Java ma come dialogano i diversi sistemi:
- invocazione con parametri
- condivisione di memoria
- metodi sincroni
- passaggio di messaggi (mailbox)

Qualunque metodo con cui si possono sincronizzare e collaborare a svolgere qualocsa.

### Tipi di errori

- sbaglio nell'uso dell'interfaccia (ordine dei parametri, assunzioni sbagliate su ciò che viene fatto, tutti errori che il testing funzionale dovrà andare a tenere in considerazione)
- errori di tempistica o di sincronizzazione (non solo nei sistemi real time ma soprattutto li)

### Classi di equivalenza

Devo suddividere il dominio in insiemi di valori in ingresso, in modo in cui questi dovrebbero comportarsi nella stessa maniera pur dando risultati diversi (se calcolato correttamente per un dato dovrebbe essere corretto anche il calcolo di tutti gli altri). Oltre che parlare di classi di input si può parlare anche di categorie di output, magari caratterizzando gli errori, quali sono i valori che mi forniscono un errore di un certo tipo, dovo averli caratterizzati basta prenderne uno. Di solito le classi di equivalenza degli errori sono ancora più stabili proprio perchè da lo stesso risultato per tutti i valori.

Chiameremo classe di equivalenza un insieme di stati validi o non validi per i dati di input, oltre che gli errori in output ci saranno anche gli errori in input, mi aspetto in input un certo tipo di dato e mi arriva qualcosa di un tipo diverso (non appartenente al dominio, se posso fornirglielo ovviamente, posso fare cast comunque). Se l'input arriva dall'utente bisogna sempre controllare che non ci sia nulla di sbagliato.

Esempio: codice pin di 4 cifre, almeno la classe dei pin validi e pin non validi. Oppure se ci aspetta un valore tra 100 e 700 possiamo distinguere in: valore tra 100 e 700, valore minore di 100 e valore maggiore di 700.

Il test di frontiera è complementare a questa cosa, tra 100 e 700 quale scelgo, i valori agli estremi sono quelli più rischiosi nel trattamento o almeno anche quelli, posso decidere che per ogni categoria prendo un valore a caso dell'insieme + tutti quelli che rappresentano delle frontiere rispetto a qualcos altro.

### Category partition

È un modo di cominciare a ragionare/porsi una serie di passi da cominciare a svolgere per identificare le classi di equivalenza, può essere usato a vari livelli noi adesso lo vediamo a livello di testing funzionale se non testing di sistema.

1. Analizzare le specifiche per identificare le **unità funzionali individuali** che possono essere verificate singolarmente, un piccolo pezzo che è testabile separatamente, non per forza una classe può essere anche una funzione complessa di un modulo complesso ma facilmente individuabile sia nel codice che nelle specifiche. Per ognuna di queste unità devo definire le categorie che sono parametri o dell'ambiente in cui lavora l'unità testabile. Per queste categorie dovrò andare a scegliere quali sono le classi di valori sensati, determinare gli eventuali vincoli tra queste scelte poi scrivere i test (e la documentazione).

Esempio: il comando 'find \<pattern\> \<file\>'
cerca del testo specificato dal pattern all'interno di un file. COpia specifiche dalla slide.

Passo 1: analizzare le specifiche. FInd è una funzione individuale che può essere verificata separatamente.
Parametri: pattern e file
Caratteristiche parametro pattern: esplicite (lunghezza del pattern, pattern tra doppi apici, pattern contenete spazi, pattern contenete apici) e implicite (pattern tra apici con/senza spazi, più apici successivi inclusi nel pattern).

File è parametro o ambiente? Il nome del file è un parametro ma il suo contenuto è ambiente.

Se ci concentriamo sul parametro nome file non viene esplicitato nulla ma implicitamente so che alcuni caratteri non sono ammissibili e che il file deve essere esitente e con permessi di lettura.

Caratteristiche dell'ambiente file: numero occorrenze pattern nel file, massimo numero di occorrenze del pattern in una linea, massima lunghezza linea e implicite(pattern sovrapposti, tipo del file).

Passo 2: date queste specifiche quali sono i valori o le classi di equivalenza dei valori?
Dimensione del pattern: vuoto, un carattere, più caratteri, più lungo di almeno una linea del file.
Presenza di apici: pattern senza apici, pattern con apici, pattern con apici errati.
Presenza di spazi: nessuno, uno, molti.
Presenza apici interni: nessuno, uno, molti.
Per ogni caratteristica identificata precedentemente diciamo quali sono i valori (generici). Quando lo faccio per tutte le caratteristiche, poi dovrei andare a testare tutte le combinazioni (prodotto cartesiano), questa cosa esplode facilmente. Sarebbero troppi, come ridurre?

Alpha testing: usato internamente all'azienda che produce il sw.
Beta testing: nell'azienda probabilmente si hanno pc tutti uguali con lo stesso so e le stesse applicazioni alle stesse versioni, quando viene dato limitatamente ad alcune aziende o al grande publico (accordo di non diffusione).

Invece di essere interessato ad ogni combinazione mi accorgo che classicamente i fattori che determinano il verficarsi di un anomalia che porta ad un malfunzionamento sono o una caratteristica o l'accoppiamento specifico di 2 caratteristiche. Se testo solo le coppie di ogni singolo valore mi ritengo già abbastanza soddisfatto. Ci sono diversi modi ed euristiche per costruirli.

Per determinare i vincoli per rimuovere alcune combinazioni poco significative si possono:
- definire delle proprietà (Empty, Quoted, ...) e metterle in relazione (alcune proprietà sono incompatibili tra loro Empty e Quoted).
- definire delle condizioni
- single: fissata una certa caratteristica ho già fissato il valore, fissato che il pattern non c'è nel file comunque non stampa nessuna riga.
- error: se ho la caratteristica con apici errati qualunque cosa ci sia in tutti gli altri valori sarà comunque errore, è inutile dirgli altro.

È inutile fare tutte le combinazioni, posso semplificare.

Passo 4: fissati questi vincoli uno può fare il pre-calcolo, quanti casi di test verrebbero fuori? Se sono troppi iterativamente continuo a ridurre. Date queste caratteristiche trovare dei valori aderenti a queste caratteristiche con la combinazione diventa quasi automatico.

Cosa rimane di problematico? Tutta la parte di classificazione che potrei aver sbagliato, se ho detto la carattertica del pattern invece che essere 0,1,molti dico che è 5,8 (li ho scelti io i valori per le caratteristiche, se non sono significativi posso fare tutti i calcoli che vofglio non troverò mai l'errore, seconda cosa ho detto "ah benissmo creo automaticamente le combinazioni e posso automatizzare il test" ??problema??     vado a testare delle proprietà dle sistema, delle proprietà che devono comunque valere piuttosto che usando la funzione inversa andare a trovare e sapere cosa testare ES: invece che usare un oracolo prendi n moltiplicalo per se stesso e dai in input questo numero, non voglio la radice di 25 ma la radice di 5*5, l'oracolo è intrinseco nella funzione inversa di quello che sto cercando).

### Testing strutturale e object orientation

Visto che non si basa sul codice perchè dovrebbe interessarmi se è object oriented oppure no, tanto non lo so com'è il codice. In prima istanza si è vero ma (noi lo facciamo quando facciamo test d'integrazione) causa problemi nella fase d'integrazione (non c'è una guida bottom-up o top-down, il main che chiama sottomoduli che chiamano sottomoduli... non c'è più), le relazioni tra le classi spesso sono cicliche (gerarchiche con l'ereditarietà) sono più le associazioni, le dipendenze che ci interessano, la differenza è sul come vado ad identificare i sottoinsiemi di classi che posso andare a testare dal punto di vista funzionale (non ricompongo ma parto da quello che ho, prendo la scheda degli use cases "quando interagisco con il sistema in questo modo..." se cita già i componenti bene, le altre posso anche non averle).
Il fatto di non basare la conoscenza sul codice ci permette di non avere grosse differenze.

### Software inspection

Un altra classe di tecniche di verifica e convalida diversa da quelle che abbiamo visto fin ora (nel pair programmin c'era qualcun altro che dovevi convincere, inoltre l'altro guarda il codice e controlla, fa una discussione del codice, un walkthrough mentale e facendolo fa in modo embrionale alcune cose che vengono fatte nell'ispezione del codice).

È una tecnica che ha bisogno di pochissimi requisiti, non ho bisogno di tool (magari qualcuno di assistenza lo accettiamo ma potremmo fare senza) perchè sono tecniche manuali umane, si guarda il codice in delle riunioni con 5-6 persone: la Fagan Code Inspection è la persona che per primo in modo piu rigoroso. Essendo umane possono essere fatte non solo sul programma eseguibile (codice che ha generato quel programma) ma non solo su un programma che funziona ma anche su uno che non riesce a compilare, posso farlo su delle specifiche più o meno formali (è flessibile perchè l'interprete è il cervello umano rispetto alle tecniche di testing [programma che compila, accetta gli input e mi da un risultato]).

Questo si può fare a diversi livelli: fase di raccolta di specifiche, fase progettazione, fase implementazione, su un singolo componente, intero sistema HMMM NO ci torniamo.

Ruoli:
- moderatore: è il capo, non è un dittatore ma nelle situazioni in cui bisogna decidere qualcosa è colui che ha la responsabilità di mantenere e far rispettare le regole. Di solito è preso da un altro processo per fare in modo che non abbia già delle opinioni sul progetto ed è colui che coordina i meeting e sceglie chi sono i partecipanti che saranno esterni al gruppo di sviluppo in modo da distribuire le competenze in base a quelle richieste dal progetto.
- readers e testers: sono i partecipanti generici alla riunione (non sono due figure diverse, è che in base al momento una stessa persona copre questi due ruoli: il primo legge il codice facendo una specie di parafrasi cercando di spiegarlo e il secondo possono fare delle piccole esecuzioni manuali, se gli passo 7 simulo la loro esecuzione [senza pc fanno a mano]).
- autore: un partecipante passivo, se fosse attivo avrebbe troppo interesse a guidare in una certa direzione la riunione. È li pronto a rispondere a qualunque domanda in modo da acellerare il lavoro degli altri (gli altri probabimente sono capi progetto di altri team, persone che in poco tempo riescono a capire cosa sta succedendo e quali possono essere i problemi, persone che costano, il loro tempo è prezioso, non stanno producendo software devo velocizzare queste riunioni).

### Software inspection process

Al di la delle persone come si articola il processo?
1. planning: ho anticipato al moderatore, sceglie i partecipanti, sceglie le date (fa un po di logistica).
1. overview: prima della riunione viene dato a tutti i partecipanti del materiale del progetto e si assegnano i ruoli.
1. preparation: ognuno offline per conto proprio cerca di capire e farsi un idea del sistema.
1. inspection: la rinunione.
1. rework: l'autore può mettere mano al codice per sistemare i problemi che sono emersi.
1. eventualmente un follow up: re-ispezione, se hai fatto le cose che avevamo detto e cosa c'è adesso che non va.

### Ispezione

La cosa a cui stare più attenti e che il moderatore deve garantire è il rispetto del **goal**: trovare e registrare i difetti, non corregerli. La tentazione dei partecipanti di dire la loro c'è ma non è compito loro programmare la soluzione. Si possono fare al massimo 2 sessioni di 2 ore al giorno su circa (dipende dal linguaggio e dal tipo di applicazione) 150 code l'ora.
Approccio: parafrasare linea per linea (risalire allo scopo del codice a partire dal sorgente) possibile fare test a mano e infine checklist (una serie di cose che devo controllare). Stiamo cercando di trovare difetti, errori dentro al codice senza eseguirlo (non è testing), si punta direttamente al trovare un difetto partendo da [malfunzionamento -> difetto -> sbaglio, quali sono gli sbagli comuni? controlliamo di non averli fatti] li prende dall'altro lato, invece che guardare il manifestarsi si guarda perchè ho messo l'anomalia e si controlla. Si prende dall'esperienza (dal passato) della conoscenza che porta a non ripetere gli stessi errori (sbagliavo una cosa e la metto nelle checklist per controlalrla in futuro).

Questa conoscenza può essere congelata a livello planetario (la prendo dalla letteratura) quindi ho checklist comuni di cose da controllare e in più imparo dalla MIA esperienza, viene costruita sulla mia azienda perchè evolvono col tempo, per questo è un ottimo metodo.

Le checklist non sono cose complicate da verificare.

### Incentive structure

Affinchè funzioni questa cosa devono esserci delle dinamiche positive, il gruppo deve lavorare bene senza darsi fastidio a vicenda. Devono esserci degli incentivi e cose non disturbanti: i difetti trovati non devono essere usati per la valutazione del personale perchè il programmatore non deve essere incentivato a nascondere i difetti. I difetti trovati dal test dopo l'ispezione sono usati dalla valutazione del personale, in questo modo il programmatore è incentivato a fare in modo che gli altri lavorino bene. Dare dei premi a chi trova tanti errori non funziona, ci si metterebbe d'accordo per far insrire tanti errori.

Variante: active design reviews
Nel precedente per far funzionare le cose le persone devono partecipare attivamente, un revisore impreparato potrebbe star seduto e non dire niente. In questa versione l'autore può fare domande al revisore (checklist) e loro sono tutti costretti a rispondere.

Sebbene sia una tecnica manuale esitono alcuni tool di supporto: alcune checklist possono essere controllate automaticamente (es. formattazione), aiuti alla comprensione del codice.

Funziona? Gli studi dicono di si, spesso le tecniche di verifica e convalida che vengono proposte vengono confrontate come baseline rispetto all'ispezione del codice.

Non è incrementale, ogni volta si ricomincia da zero, quello che ho trovato prima devo ricontrollarlo anche ora.

C'è chi ha preso gli "studi", prendono vari articoli che confrontano ispezione del codice, testing funzionale e testing strutturale che volevano capire quale di queste si comporta meglio. Gli studi non concorano su quale sia il migliore. Probabilmente dipende dalla tipologia dei progetti, non è detto che siano gli stessi errori che vado a trovare (nello stesso studio). QUesto confronto vale ma lascia un po'il tempo che trova. Non tutti hanno fatto lo stesso tipo di esperimento (progetti diversi, team diverso). Questo serve solo per dire che non c'è una risposta facile, dipende. Ci sono situazioni in cui alcune tecniche si comportano meglio di altre.

Allora se le mettessimo tutte assieme?

Un testing informale permette di trovare il 30-50% di errori.
Se faccio solo una di queste 4 cose sono nella zona grigia. QUella migliore è la formal design inspection perchè gli errori trovati prima mi mettono al riparo da conseguenze successive. Comunque qualsiasi è meglio di non fare nulla.

Se si iniziano ad accoppiare si somma (nel caso in cui non trovino gli stessi errori) le prestazioni globali migliorano quando ogni tecnica trova cose diverse. Più ne prendo e più migliora, se le prendo tutte siamo al 95% - 99.99%.

Nell'intuizione sembra ragionevole il risultato però si considerano queste 4 attività fatte bene. Spesso per farli tutti e 4 non possiamo quadruplicare il tempo a disposizione, probabilmente le farai tutte ma un po meno bene.

Facendo testing strutturale, che parte dal codice, gli errori piu comuni li trovo già con i primi due criteri di copertura, per cose particolari devo fare cose molto particolari ma le cose più evidenti le trovo già con il primo livello e queste sono diverse da quello che trovo al primo livello del test funzionale che sono diverse dal primo livello dell'ispezione del codice. Pur tenendo un livello più superficiale di queste tecniche se invece che far benissimo una cosa sola faccio peggio tutto allora probabilmente questo cocktail di tecniche è molto più performante.

    Hetzel-Myers's Law (L20): A combination of different V&V methods outperforms any single method alone.

# Vantaggi gruppo di test autonomo

Chi fa il testing? C'è una convinzione generale che colui che ha sviluppato il codice sia la persona meno adatta a testarlo.

    Weinberg's Law (L23): A developer is unsuited to test their code

Programma che faccio partire e crasha, al tester non va ma allo sviluppatore si cosa si fa? Senza neanche accorgersene c'era una tabella, bisognava cliccare su una riga della tabella, lui selezionava sempre la prima colonna il prof cliccava sulle successive ma c'era l'event handler solo sulla prima colonna. Per sua abitudine di utilizzo cliccava sempre la prima colonna, per un mese non si è accorto, il prof in 5 minuti ha scoperto. QUando voglio che venga fatto prima A e poi B ma il programma non forza questa sequenza ma invece non funziona.

Ci sono anche aspetti più tecnici e psicologici, chi fa il tester di professione ha acquisito compotenze specifiche sull'uso di tool complessi di testing. Chi fa quel lavoro si può specializzare per avrere competenze migliori.

Aspetti psicologici:
- distacco dal codice (sia nel senso, non so come funziona quindi non ho un modello mentale su cui operarci e aspetti dimenticati, non conosco il codice ma so cosa voglio fargli fare, controllo che lo faccia).
- indipendenza dalla valutazione (quando uno testa il suo programma vorrebbe che funzioni, il tester invece è contento se TROVA gli errori).
Per questi motivi un gruppo di test autonomo è una buona scelta. Ma ci sono anche svantaggi.

Quando si è specializzati in una cosa si perde conoscenza ed esperienza, capacità di valutare il progetto, inoltre non hai conoscenza dei requisiti, non sai il programma cosa doveva fare, parti da 0 con la conoscenza.
Aspetti psicologici: possono crearsi dei circoli vizioni, miglior test = più errori ancora -> dedicare più solidi al testing ha portato a prestazioni migliori in terminini di numero di errori presenti nel codice. Uno dei problemi è la responsabilità, si è sempre in strettezze di tempo, non è che prima di avere un team dedicato molto forte ..., lo sviluppatore fa testing di unità, i tester fanno più testing funzionale, d'integrazione. Succede che a fronte di aumento personale testing trovano errori quindi gli sviluppatori consegnano al team di testing senza controllare troppo, spostano la responsabilità di trovare errori sul team di testing. La qualità del sw consegnato è minore. Il team trova X errori, ridaà indietro e dice di riparare però intanto si è perso tempo, si è accumulato ritardo.
Lo scaricabarile è pericolosissimo ma ci può essere pressione, gli altri sono quelli che ti giudicano, lui mi dice che lavoro male ma io non posso dirgli che lavora male.

Alternative?
- Fare una rotazione del personale, a seconda dei progetti posso ricoprire il ruolo di tester o developer (meno specializzazione, meno svuotamento dei ruoli, aumenta i costi per la formazione, devo far fare a tutti sia i corsi da tester che developer e aumento le difficoltà di pianificazione, non posso testare quello perchè sto ancora sviluppando altro e sono in ritardo).
- All'interno del team a seconda dei momenti copro diversi ruoli, tutti conoscono il progetto ma aumenta la difficoltà di gestione dei ruoli.

# Modelli statistici

Quando è statistico al prof viene l'orticaria ("non è il mio mondo. ne parlo male ma potrbbe essere una cosa bella"). Ci sono dei tentativi di misurare e trovare correlazioni tra metriche e presenza di errori, lunghezza del codice <-> presenza di errori, tipo di linguaggio ed errori, tipo di processo ed errori. QUalunque cosa che posso misurare e vedere come si comporta.
I moduli che rpesentano errori come sono fatti? QUal'è la probabilità che in questo modulo ci siano errori. Bisogna stare attenti a cosa viene usata quest'indicazione, se viene usata per scegliere a cosa dedicare più attenzione nella fase di verifica e convalida va a finire che testo di più alcune cose ma dopo averle testate di più le metriche mi dicono ancora che la probabilità di presenza errori è ancora maggiore (non cambia con il testing). In questi moduli è piu probabile che ci siano errori, buttali e rifalli, sono nati male.

Tra le cose più facili da sentire in questo ambito:

    Pareto-Zipf-type Laws (L24): Approximately 80 percent of defects come from 20 percent of modules

Parteto non era un informatico, era un economista e zipf era un linguista(??? il prof non ricorda), si sta rimappando nell'informatica qualcosa che deriva da tutt'altro mondo. Non è una cosa che può essere fruttata per farci qualcosa. Il prof non ha avuto prove di grosso valore di questi modelli statistici.