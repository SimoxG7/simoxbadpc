# Reti di petri di alto livello o estensioni di reti di petri

Invece che avere gettoni anonimi aggiungono ai gettoni delle informazioni in varie maniere (reti colorate: gettoni con colori diversi). Le transizioni lavorano sui gettoni (quando genero devo dire il colore e abilitata solo con un gettone rosso e uno verde, aggiungere colori permette di avere più facilità di espressione di condizioni e situazioni).

L'altra sono le reti temporizzate in cui c'è un informazione aggiunta al gettone: il timestamp in cui il gettone è stato prodotto e si possono abilitare le transizione se il gettone è stato creato da almeno X ma è più giovane di Y.

< img cerchi con testo dentro collegate da linee nere continue L21P2 >

Quelle che interessano di più al prof sono le reti temporizzate (non quelle stocastiche BUUU ne quelle genericamente di alto livello [quelle che aggiungono informazioni ai gettoni OK MOLTO INTERESSANTI, CI PERMETTONO DI MODELLARE SISTEMI REALI CON PIÙ FACILITÀ]). Quello che interessa la prof è quando queste informazioni aggiuntive hanno carattere temporale, allora si complica notevolmente tutto quello che è l'evoluzione, perchè il tempo è su un piano diverso, non è locale alla singola transizione, è trasversale e sotto certe ipotesi possiamo ancora riuscire a fare un evoluzione locale (fare un analisi locale) però alcune volte dovremo invece portarci ad una visione complessiva (l'influenza che lo scatto di una transizione ha sull'altra). Questa transizione deve scattare entro questo tempo, non può capitare che non scatti e faccia scattare qualcosa che avviene ad un tempo successivo, prima di quel tempo deve scattare. Se ci sono certe condizioni la centrale nucleare esplode, non è che se guardo altro allora non esplode e posso andare nel tempo senza che lei esploda, quella comunque scatta. Quindi devo modellare sia sistemi con semantica debole (in cui riesco a fare l'analisi locale) che con semantica forte (dare una caratteristica di globalità tra gli scatti delle varie transizioni, d'influenza che possono avere, tu transizione non puoi scattare adesso perchè prima devo scattare io, tu puoi scattare solo in un tempo successivo a quello che è il mio massimo tempo di scatto). Qui si introducono dei problemi nell'analisi e nello sviluppo delle reti.


## Modellare sistemi hard real-time

Questo ci serve per andare a modellare i sistemi real time, sono sistemi molto importanti per quanto riguarda l'informatica e da cui l'informatica riceve delle richieste molto difficili da soddisfare. Sono tra i sistemi critici da affrontare per l'ingegneria del sw in particolare che deve garantire la qualita dello sviluppo.

In questi casi l'anilisi stocastica non è che serva (la centrale può scoppiare una volta su un milione, va bene fino a un certo punto però non ci soddisfa pienamente) quindi bisogna avere dei modelli deterministici per controbilanciare quelli che sono con i sistemi stocastici solo dei discorsi prestazionali. Non ci interessa sapere che in media non esplode, vogliamo che non esploda mai.

Esistono diversi modi che sono stati proposti per aggiungere il tempo (deterministico) alle reti di petri:
- aggiungere dei ritardi sui posti: quando un gettone viene creato in un posto, prima di essere preso e consumato deve passare almeno tot tempo.
- ritardo sulle transizioni: nel momento in cui è abilitata una certa transizione prende i gettoni ma prima di produrli (separa il momento di distruzione e creazione dei gettoni). Da una durata all'esecuzione della transizione.
- aggiungere dei tempi di scatto: dal momento in cui è abilitata una certa transizione può scattare da 5 secondi dopo a 20 secondi dopo, quindi definire una funzione che decide in funzione di quando è stata abilitata quando può scattare ma nel frattempo può essere disabilitata. Questi tempi possono essere:
    - unici: esattamente 5 secondi dopo che è stata abilitata.
    - multipli: un intervallo tra 5 e 9, un insieme {5,8,33}.  
    Possono anche essere
    - fissi
    - variabili:  
    E anche:
    - assoluti: il 4 febbraio 2023 
    - relativi: essere espressi in termini dei gettoni

## Tempo sui posti

Se guardiamo i tempi associati ad un posto è un informazione aggiuntiva (come era la capacità) e questo va a influenzare quello che è l'evoluzione, l'abilitazione e le regole di scatto della nostra rete. Tutte queste estensioni cambiano ed espandono queste regole aggiungendo nuovi vincoli. Queste sono cose poco intuitive per il prof, nel senso che hanno un mapping con la realtà ma quando guardiamo la rete, la guardi disegnata e non hai abbastanza informazioni (magari sei a metà di uno scatto quindi non hai gettoni ma tra un attimo verranno creati dal nulla piuttosto che vedi i gettoni li ma non li puoi usare).

Se abbiamo i tempi sui posti posso pensare di spostare il tempo sui posti e metterlo sulle transizoni semplicemente aggiungendo un posto di start e un posto di end e frapponendo la transizione che ha la durata. Diciamo che il gettone che arriva qua non lo posso consumare, non può abilitare T2 e T3 finchè non è passato $\Delta$, posso dire "benissimo, arriva, viene subito consumato dalla transizione che ha un ritardo, li genera dopo $\Delta$ e a questo punto sono disponibili per T2 e T3, esattamente equivalenti.

Posso pensare di avere una rete espressa con tempo sui posti e spostare il tempo sulle transizioni con questa costruzione.

Posso anche fare in modo che quando ho il tempo sulle transizioni (ho una certa durata), posso dire "benissimo, guarda che tempi fissi sugli scatti" vuol dire appena arriva 00 da quando è abilitata scatta e dopo delta unitità di tempo entro delta unità di tempo deve scattare dopo che è abilitata. Qui sto definiendo i tempi di scatto come un intervallo, in realtà addirittura come dei numeri fissi, ho messo minimo e massimo uguali (0,0 e $\Delta$,$\Delta$) però riesco a rimappare i tempi di durata delle transizioni sui tempi di scatto delle transizioni.

## Momenti di scatto unici o multipli

Nel momento in cui i tempi di scatto delle transizioni hanno tempi multipli chiaramente ho una possibilità in più rispetto a quella dei tempi di scatto unico (che è un sottocaso). Stessa cosa con i tempi costanti piuttosto che variabili.

Se il nostro formalismo ci permette di esprimere vincoli non solo in termini relativi dei gettoni già esistenti ma anche in termini assoluti comprende quello precedente. Tutto questo epr dire che parleremo di:

## Time Basic Nets (TB)

Sono reti che hanno il tempo associato alle transizioni in cui vengono assegnati degli insiemi di tempi di scatto possibili definiti in maniera dinamica come funzioni che possono fare riferimento a tempi assoluti o ai tempi dei singoli gettoni. Per cui tutto. Non ha anche altri tipi di informazioni.

Sono reti definite nel 1989 al politecnico di milano e rispetto alle caratteristiche accennate l'altra volta (era lez precedente) quello che caratterizza queste reti è il tempo associato alle transizioni ed in particolare vengono associati a transizioni degli insiemi di tempi di scatto possibili che possono essere definiti in maniera dinamica dove possono far riferimento a tempi assoluti o relativi alle (incomprensibile).

### Definizione informale

È come una rete normale in cui però i gettoni non sono più anonimi, hanno un informazione collegata a loro (timestamp creazione), i gettoni sono distinguibili.

< img >

In $P_0$ c'è un gettone creato in tempo 3 e uno creato in tempo 5, mentre in $P_1$ c'è un gettone creato in tempo 4. Possono esistere gettoni con lo stesso timestamp (se sono generati dalla stessa transizione, due scatti in parallelo).
Quando parliamo di **tempo d'abilitazione** parliamo del massimo tra i tempi dei gettoni che vanno a comporre la **tupla abilitante** (anabling tuple) di gettoni che abilita la transizione (vuol dire che una transizione non può essere abilitata prima che esista il gettone che la abiliti): $T_0$ è abilitata dal tempo 4 dalla tupla (3,4) ma nel tempo 5 è abilitata anche dalla tupla (4,5). Quale gettone scelgo dal posto $P_0$ ha un significato, dato che sono diversi è differente l'effetto che produrranno, devo considerare entrambe le possibili evoluzioni.

Data questa definizione di tempo'abilitazione l'altra cosa che dobbiamo chiarire è il fatto che c'è un insieme di tempi possibili di scatto collegati ad ogni transizione abilitata. O meglio ad ogni tansizione abilitata con la tupla che la sta abilitando. Questo insieme di valori dipende non dipende dalla tupla.

Comunque c'è sempre da mettere implicitamente nll'insieme che andiamo a definire il fatto che il tempo di abilitazione è il minimo tempo in cui può scattare (non può scattare prima di essere abilitata).

Spesso daremo l'insieme di tempo, lo semplifichiamo in un intervallo da un tempo minimo (il massimo tra enab e il tempo che c'è in P1 più 2) a un tempo massimo (tempo che c'è in P0 più 5) considerando quale dei due gettoni sto considerando in quel momento. Ho due maniere di poterlo far scattare per cui può scattare con la tupla rossa tra 4+2 (6) e 8 oppure quella blu può scattare tra 6 e 10.

Queste sono sono formule che valgono sempre, devo associare ad ogni transizione una regola di calcolo delli tempi di scatto, in questo caso se associamo come regola minimo tempo di scatto questa formula che dipende dai gettoni in ingresso e come tempo massimo di scatto della transizione l'altra regola che sono funzioni del timestamp collegati ai gettoni che andrò a consumare. t(nome posto) significa il timestamp del gettone che mi compone la tupla abilitante che sto considerando in quel momento. Avendo identificato due tuple abilitanti ho due modi di calcolare queste due formule.

Il tempo di scatto della transizione potrà essere scelto arbitrariamente tra i tempi appartenenti all'insieme identificato precedentemente.

Lo scatto genererà due gettoni in $P_2$ e $P_3$ con associati un timestamp che è il tempo della loro creazione.

### Definizione formale

Una rete TB è una sestupla <$P,T,\Theta ,F,tf,m_0$>
P,T ed F sono sempre insiemi dei posti, transizioni e flussi.
Nelle reti TB non ci sono pesi, si ragiona in maniera diversa.
Le reti TB sono la priezione di una classe più grande di reti di alto livello che ha lasciato solo l'informazione temportale per studiare i problemi specifici legati all'informazione temporale.
$\Theta$ (theta) è un insieme numerico (il dominio temporale) che può avere diverse caratteristiche (dominio degli interi se sto facendo un sistema discreto, reali, topologie circolari per fare sistemi periodici). Le reti TB sono dei generici rispetto al tipo con cui vado a rappresentare il tempo, quello che sappiamo è che esiste una funzione $tf$ (time function) che associa ad ogni transizione una funzione temporale $tf_t$ che è quella che restituisce l'insieme dei tempi dei possibili scatti di quella transizione. Nel nostro caso $tf_t$ è stata spezzata in $tf_{t_{min}}$ e $tf_{t_{max}}$. Applicare la funzione $tf_t$ alla tupla abilitante mi deve fornire un sottoinsieme di theta ($tf_t(en) \sube \Theta$ dove $en$ è una tupla abilitante).
$m_0$ cambia leggermente rispetto a prima perchè non può associare (???) a un naturale perchè non è il numero di gettoni che caratterizza totalmente queste informazioni, non è neanche un insieme di valori di theta perchè come abbiamo osservato prima posso avere più valori identici anche nello stesso posto. $m$ associa a $P$ un multiset (un valore x con una certa molteplicità) ($m_0: P \rightarrow {(\theta, mul(\theta))\:|\:\theta \in \Theta}$). $m_0$ indica la marcatura iniziale.

Evoluzione: per ora abbiamo detto "associa tempi di possibili scatti" quando scatta crea gettoni con quel tempo ma ci manca un pezzettino, ed è "associato un insieme di ??? di scatto cosa mi dice se veramente può scattare oppure no?". Ci sono diverse scuole di pensiero:

## Semantica temporale debole (weak time semantic)

Una transizione può scattare solo in uno degli istanti identificati dalla sua funzione temporale, se applicando la funzione temporale ottengo un insieme vuoto la transizione non è abilitata (se non c'è neanche un istante temporale non può scattare (5,3) è una transizione morta). Se per ogni posto nel preset non ho almeno un gettone è disabilitata, se ne ho almeno uno faccio tutte le combinazioni possibili e per ognuna valuto la funzione temporale e per tutte quelle per cui restituisce un insieme vuoto non possono scattare, le altre invece si. Se non ho esplicitato dentro alla funzione $max(enab, E)$ lo aggiungo a questo punto (non può scattare prima di essere abilitata)
della transizione non soddisfa i pesi degli archi. Anche in questo caso anche se una transizione è abilitata non è costretta a scattare (utile per modellare eventi solo parzialmente definiti come una decisione umana, un guasto, ...).

Esempio: contraerea

< img >

S'ingaggia la battaglia nel momento in cui l'obiettivo è inquadrato e il sistema dell'antierea è pronto, la battaglia inizia quando tra il tempo di abilitazione (sono successe il massimo dei due eventi) e un tempo X1 successivo all'inquadramento dell'obiettivo.

A questo punto possono accadere due cose:
1. Entro X2 tempo decido di sparare
1. Tra il tempo di abilitazione e l'infinito posso rinunciare

L'obiettivo rimane inquadrato per un massimo di X2 unità di tempo, in questo lasso di tempo POSSO sparare ma non sono costretto, se passa X2 però non posso più farlo.

Avrei anche potuto togliere il gettone dopo X2 unità di tempo.
Quello fatto è il modo migliore per espriemere chiaramente la dipendenza da un vincolo temporale.

Esempio: boiler

< img >

Quando la fiamma viene fatta accendere c'è un momento in cui il colpo d'aria che si auto genera la fa spegnere, una volta che la fiamma si è stabilizzata la probabilità che si spenga è quasi nulla (se continua ad arrivare il gas).

Quelle 10 unità di tempo è il tempo in cui potrebbe spegnersi però se non si spegne dopo quel tempo posso far finta che non si spenga mai.

Attenzione! Non ci interessa modellare la probabilità che si spenga, ma solo che può accadere.

Modellerò il sistema per fare in modo che se la fiamma si spegne non accade nulla di male (non accumulo gas incombusto).

Per dirlo in modo formale usiamo degli assiomi:
1. Tutti i tempi di scatto di una sequenza di scatto devono essere non minori di uno qualunque dei timestamp della marcatura iniziale. Non è possibile che le mie regole di scatto ammettano che facci scattare la transizione ad un tempo precedente a quello espresso da uno dei gettoni della mia marcatura. La marcatura dev'essere consistente: non deve contenere gettoni prodotti nel futuro rispetto a ciò che faccio avvenire nella mia sequenza.
1. **Monotonicità dei tempi di scatto di una sequenza**: tutti i tempi di scatto di una sequenza di scatti devono essere ordinati nella sequenza in maniera monotonicamente non decrescente. Non si torna indietro nel tempo ma possono scattare molte transizioni nello stesso istante (azioni simultanee o molto più veloci rispetto alla mia granularità del tempo: secondi ma fanno in microsecondi, per me sono allo stesso tempo).
1. **Divergenza del tempo (non-zenonicità)**: non è possibile avere un numero infinito di scatti in un tempo finito (non posso essere bloccato in un tempo) ed il tempo non è suddivisibile in infinitesimi. Se la rete può rimanere fissa in un punto sarebbe impossibile fare qualunque analisi.

Formalmente si distingue in Weak Time Semantic (WTS) e Monotonic Weak Time Semantic (MWTS): quelle che soddisfano il primo e il terzo è quello che mi serve per definire la WTS, se aggiungiamo anche il secondo assioma otteniamo MWTS. La cosa interessante è che queste due semantiche che sembrano diverse (quella monotonica ci obbliga quando esaminiamo una transizione a dire "aspetta, non è che nell'altro angolo della rete c'è qualcuno che ha fatto scattare una transizione in un tempo successivo rispetto a quello in cui vorrei farla scattare io?").

La semantica debole è assolutamente distributia come valutazione (analisi locale possibile) mentre quella monotonica presuppone una conoscenza minima dell'ultimo evento accaduto, devo guardarmi in giro, prendere un lock e aggiornarla, ho detto tempo 8 non potete più far scattare qualcosa prima (informazione condivisa globale).

Teorema WTS $\equiv$ MWTS
Per ogni sequenza di scatti debole $s$ esiste una sequenza di scatti monotonica debole ottenibile per semplice permutazione (riordinando) delle occorrenze degli scatti. Ho preso nota prima dello scatto a tempo 8 e poi quello 6 ma basta ordinare per ottenere quella monotonica, posso ordinare a posteriori, non è necessario che l'ordinamento sia forzato durante la definizione della sequenza, posso fare analisi locale e ordinare dopo.

Non è che una sequenza non monotonica è una sequenza monotica, ma ha una corrispondente.

Dato che nella WTS posso ancora fare l'analisi locale, il fatto che abbia aggiunto il tempo come informazione temporale piuttosto che come numero da elaborare è indifferente, se avessimo delle tecniche per reti di alto livello con cui posso associare un intero ai gettoni (non è un timestamp, è un intero) e poi ho dei predicati e delle azioni che lavorano localmente sui gettoni in ingresso e casualmente attuano le stesse politiche che stiamo defininendo per le reti temporizzate la cosa andrebbe liscia (posso farlo, non ci sono aspetti particolari che le differenzino).

Esempio di equivalenza:

< img >

Sequenza WTS:
- $T_1$ scatta al tempo 12
- $T_3$ scatta al tempo 14
- $T_2$ scatta al tempo 4

Sequenza MWTS:
- $T_2$ scatta al tempo 4
- $T_1$ scatta al tempo 12
- $T_3$ scatta al tempo 14
$T_3$ risulta abilitata tra 5 e 7 ma non scatta, il gettone che rimane in $P_2$ è morto, non può più essere consumato da nessuno.

## Semantica temporale forte (strong time semantic)

Continua a valere che una transizione può scattare solo in uno degli istanti identificati dalla sua funzione temporale e che una transizione non può scattare prima di essere stata abilitata.
Cambia il tezo punto: una transizione DEVE scattare entro il suo tempo massimo di scatto a meno che non venga disabilitata prima del proprio massim tempo di scatto ammissibile.

Questa è la semantica più diffusa perchè è quella che [caraterizza???(non si capisce)] i sistemi deterministici (le TPN sono uno dei formalismi più comuni che associa due numeri $enab+x_1$ ed $enab+x_2$ per cui la funzione che associa i tempi è definito come un intervallo minimo e massimo dopo il tempo di abilitazione, è una delle cose che posso fare con le reti time basic ma non l'unica, sono meno potenti delle reti time basic) in quel caso la semantica è questa, se è tra 3 e 5, entro 5 deve scattare.

Come assioma questo porta a 2 cose:
1. **Marcatura forte iniziale**: una complicazione di quello che era (incomprensibile) sulla marcatura iniziale. La macarcatura iniziale deve soddisfare un vincolo aggiuntivo: il massimo tempo di scatto di tutte le abilitazioni nella marcatura iniziale deve essere maggiore o uguale del massimo timestamp associato ad uno dei gettoni della marcatura. Vuol dire che se il tempo associato ad un gettone marcatura corrisponde ad uno scatto avvenuto a quel tempo ma io ho un altra transizione abilitata che diceva che doveva scattare prima di quel tempo come ho fatto a far scattare la transizione che mi ha generato quel gettone? È impossibile! Doveva prima avvenire lo scatto della transizione abilitata. Non può essere avvenuto nulla in un tempo successivo ad un tempo in cui doveva avvenire qualcos'altro.
1. **Sequenza di scatti forte (STS)**: una sequenza di scatti monotonica debole (MWTS) che parta da una marcatura forte iniziale (che rispetti l'assioma precedente) è una sequenza di scatti forte se per ogni scatto il tempo di scatto della transizione non è maggiore del massimo tempo di scatto di un  altra transizione abilitata. Vuol dire che non posso più ragionare solo in termini localeìi, se guardo la mia transizione scopro che può scattare tra 7 e 23, non posso farla scattare a 7 o a 19 se c'è altro che deve scattare al tempo 5. Una transizione DEVE scattare entro il suo massimo tempo di scatto a meno che non venga disabilitata prima da un altro scatto.

Le sequenze che soddisfano gli assiomi 1-5 sono dette sequenze ammissibili in semantica forte (STS).

$STS \not= MWTS$
< immagine esempio >

Sequenza MWTS:
- $T_2$ scatta al tempo 6
- $T_1$ scatta al tempo 12
- $T_3$ scatta al tempo 14
Non è una sequenza STS perchè in $m_0$ è abilitata solo $T_2$, topologicamente ci sono i gettoni per abilitare anche $T_1$ (la funzione temporale restituisce un insieme non vuoto) però anche il più piccolo tempo di scatto possibile per la transizione $T_1$ è maggiore del massimo tempo possibile di scatto della transizione $T_2$. Non posso superare la soglia del 6 senza che avvenga lo scatto di $T_2$. Lo scatto di $T_2$ genera un gettone in $P_2$ con tempo 6. Quindi $T_3$ è abilitata tra 7 e 9, tempo entro cui deve scattare. Solo dopo può scattare $T_1$.

La sequenza non ha una corrispondente in semantica forte, sono due cose diverse, mentre invece una qualunque sequenza in semantica forte è anche una sequenza monotonica debole.

## Mixed time semantics

Perchè non definisco la semantica a livello di transizioni? Cioè separo/partiziono le transizioni della mia rete in due insiemi: quelli che sono associati alla semantica forte e quelli associati alla semantica debole.

Le transizioni forti devono scattare entro il loro tempo massimo a meno che non vengano disabilitate prima, le transizioni deboli possono scattare entro il loro insieme di tempi di scatto ma non sono forzate, una transizione forte può disabilitarne una debole perchè deve scattare prima lei ma una transizione debole non può disabilitare una transizione forte o un altra transizione debole perchè per lei scattare o non scattare è abbastanza indifferente.

Esempio: gas

< img >

Il fault di spegnimento è WEAK, il resto sono tutte cose FORTI (se tolgo il gas si ferma). Ci sono azioni deterministiche mentre il fault può accadere ma anche non accadere.

Esempio: analisi di una rete

< img >

Abbiamo tre transizioni: $T_1$ a semantica debole, $T_2$ e $T_3$ a semantica forte. La marcatura iniziale ha un gettone con tempo 1 in $P_1$ e un gettone con tempo 0 sia in $P_2$ che $P_3$.

$$m(P_1) = \{1\}; m(P_2) = \{0\}; m(P_3) = \{0\}$$

Le funzioni temporali associate alle transizioni sono (i tempi $\tau$ tau compresi tra il minimo e il massimo):

$$tf_{T_1}(P_1, P_2) = \{\tau\:|\:max(P_1, P_2) \leq \tau \leq P_2 + 5\}$$
$$tf_{T_2}(P_1, P_2) = \{\tau\:|\:max(P_1, P_2) + 8 \leq \tau \leq P_2 + 10\}$$
$$tf_{T_3}(P_3) = \{\tau\:|\:P_3 + 3 \leq \tau \leq P_3 + 15\}$$

Simuliamo:

$T_1$: tempo minimo di scatto = 1, tempo massimo di scatto = 5.
$T_2$: tempo minimo di scatto = 9, tempo massimo di scatto = 10.
$T_3$: tempo minimo di scatto = 3, tempo massimo di scatto = 15.

< img >

Posso far scattare $T_2$ al tempo 9 perchè $T_1$ è a semantica debole, NON HA INFLUENZA SULLE ALTRE. In questo momento non può accadere nulla che sia successivo al tempo 10. Il massimo tempo entro cui deve accadere qualcosa, non può non accadere nulla e superare il tempo 10, è il mio orizzonte temporale.

< img aggiornata >

Non è che $T_3$ non può scattare al tempo 14, ma non può essere il prossimo evento che percepisco. Nulla le proibisce di scattare al tempo 14 ma non è la prossima cosa che posso vedere.

Questa situazione sembra semplice ma si complica quando dobbiamo analizzarla simbolicamente.

Modellare passaggio a livello con reti TB.
Vacanza di natale

Reti di petri serve a modellare cosa devono fare i sistemi senza entrare in dettagli implementantivi e avere l'eseguibilità (possono essere simulate) che possono essere di aiuto nello spiegare le specifiche al cliente.

Abbiamo parlato di due famiglie di approcci alle specifiche:
- dichiarativi: linguaggi logici e altri linguaggi che indicano direttamente le proprietà che deve avere il sistema
- operazionali: tipi di linguaggi che vanno a modellare fisicamente una possibile rappresentazione del sistema, sezna voler vincolare la struttura, dev'essere interpretata come una modellizzazione di riferimento (agli effetti esterni per quanto riguarda le proprietà si dovrà comportare come questo modello) che sia poi una rete di petri con 10 piuttosto che 50 stati non vuol dire che nel codice ci deve essere la corrispondenza di una classe con un sottoinsimee degli stati/posti. Il programma si deve comportare come.

Qual'è l'utilità di queste specifiche scritte in modo formale (logico o operazionale, entrambi vanno bene) oltre che dialogare con il cliente, posso ragionarci sopra in modo più formale e quindi anche più automatica, posso avviare delle fasi di verifica e convalida per la specifica ancora prima di iniziare a codificare, i pericoli di frintendimenti (interpretato male o non aver capito) possono essere affrontati ancora prima di iniziare a codificare. Questo  però ha un costo (la definizione iniziale è veloce, sono solo posti, transizioni e qualche regola di abilitazione) tutto il resto è costruito sulla base di questo. Tutto il lavoro fatto sulle reti plain ci ha fatto capire che si possono fare tanti ragionamenti per analizzare come evolve una rete, dall'albero di raggiungibilità, all'albero di copertura, alle tecniche statiche (P-invarianti, T-invarianti) MODI DI RAGIONARE SULLA RAPPRESNTAZIONE DI UN SISTEMA.

Quello che abbiamo fatto sulle reti base vorremmo cercare di farlo anche sulle reti temporizzate.

Correzione compito modellazione passaggio a livello con reti TB.

< img >

Il treno può entrare nella zona $R$ triggerando il primo sensore, ad un certo punto entra nella zona $L$ (quella del passaggio a livello) poi esce da $L$ triggerando il secondo sensore, poi è uscito.

La parte di passaggio a livello è aperto, quando do il comando "chiudi" è in chiusura, poi si ferma la chiusura quando arriva giù, quando arriva il comando di apertura inizia ad aprirsi e si ferma quando è sù.

Queste due parti vengono messe insieme dal fatto che per aprire dobbiamo essere sicuri che sia uscito il treno e che per chiudere deve essere entrato il treno in $R$. Per ora non abbiamo parlato di tempi, è una modellizzazione dal punto di vista logico/d'interazione e di segnalazioni.

Per com'è ora la rete (mettere gettone nel posto Treno) il treno può entrare in R per poi entrare in L senza aver dato il comando di chiusura, senza aggiungere informazioni temporali non è sufficiente.

Aggiungiamo i tempi T1 (il tempo necessario a fare il primo pezzo alla velocità massima, deve passare almeno questo tempo e probabilmente sarà fatta in modo da dare un sufficiente preavviso [non sarà il metro prima]) e T2 (seconda zone).

COn queste informazioni possiamo arricchire la nostra specifica. Il treno può entrare in R in qualuque momento (0,$\infin$) ed in minimo T1 tempo entrerà nella zona L (T1, T1+X) il T1+X è perchè magari viaggia più lento ma prima o poi arriva (escludendo guasti).

Sapendo che entra in L dopo T1 e che ci metterò un certo tempo a chiudere (chiamiamolo G), dovrò dare il comando di chiusura almeno prima di T1-G (T1, T1-G). Il valore minimo potrebbe essere 0 iniziamo non appena passa ma magari blocchiamo il traffico per 10 minuti inutilmente (non cambia la correttezza, funziona con più margine).

Ferma chiusura scatta esattamente dopo G unità di tempo.

Poi il treno esce da L, sappiamo che ci mette minimo T2 tempo e massimo T2+Y se va più lento.

T2 è un dato che abbiamo inserito perchè l'abbiamo visto nelle specifiche ma non è così significativo per la correttezza del sistema mentre T1 era fondamentale.

Quando ho ricevuto il segnale che è uscito posso iniziare a rialzare la sbarra (transizione apri (0,0)) e ci metterà G tempo.

In questo abbiamo fatto alcune assunzioni:
- T1-G deve essere positivo, se arriva prima il treno la cosa non funziona (il suo tempo di scatto dovrebbe precedere l'entrata del treno).

Questa soluzione è corretta? Stiamo usando la semantica temporale forte. Potremmo anche pensarla debole per il treno (si ferma in R o in L perchè ha un guasto), sul controllo non ha senso ragionare in semantica debole (nel momento in cui controllo un sistema non posso dire magari reagisce, se non reagisce è un guasto, diciamo cosa vorremmo che acada non cosa può accadere [il cosa può accadere lo modelliamo quando assieme al sistema c'è anche l'ambiente]).

Stiamo assumendo che passi solo un treno alla volta, assunzione rischiosa. È interpretabile come un errore di specifiche (non mi è stato detto cosa dovevo fare) o errore di cose non espresse (non può entrare un secondo treno finchè non è uscito il primo). Una zona di mutua esclusione tra i treni.

C'è un altro problema: per dare il comando di chiusura dev'essere entrato un treno e le sbarre devono essere alzate.

Se entra un treno prima che io abbia finito la riapertura non faccio in tempo ad abbassare la sbarra prima che il treno arrivi al passaggio a livello. Bisogna proibire che entri nella zona critica fintanto che la sbarra non è completamente aperta o che se entra un treno e la barra non è completamente alzata inizua ugualmente ad abbassarsi.

Dire che non può entrare è la cosa che spesso viene fatta, ci prendiamo un tempo cuscinetto per portare ad una situazione stabile.

Questa rete avrei potuto simularla, guardare, costruire l'albero di raggiungibilità. Ma il tempo proprio ci serviva? Avere una rete di alto livello con dei gettoni che hanno un contenuto informativo arbitrario non era sufficiente? Era possibile trattare il tempo come un concetto derivato (uno dei tanti domini a cui può appartenere il valore di un gettone)?

I gettoni li distruggo e li creo: la transizone deve guardare dentro i dati del gettone per fare qualcosa (guardie della transizione), anche i gettoni che creo dovranno avere dei valori magari dipendenti da quelli in ingresso, devo avere un modo per creare un certo numero di gettoni con un certo contenuto informativo che può essere funzione del contenuto dei gettoni in ingresso.

Quindi ho una guardia (predicato che aggiunge una condizione di abilitazione) ed un azione che viene attuata quando scatta la transizione e calcola i valori dei gettoni in uscita a partire dai valori dei gettoni in ingresso.

Dire che una rete di questo livello può rappresentare il tempo vuol dire che tra i campi contenuti dal gettone c'è anche un campo chiamato "tempo". Tutti i gettoni devono avere quest'informazione temporizzata. I predicati lavoreranno anche sul tempo.

Le azioni calcoleranno i tempi dei gettoni in uscita, l'unica avvertenza è che devo essere sicuro che tutte le transizioni producano per tutti i gettoni in uscita esattamente lo stesso tempo e che questo valore non sia inferiore a nessuno dei tempi in ingresso e non può scattare prima di essere stata abilitata, questo può essere forzato in qualche maniera. Sintatticamente la parte di avere una rete con un certo contenuto informativo, la mia quintupla che è stata espansa, il dominio temporale, ... lo posso rappresentare.

Passiamo alla parte dinamica, cioè alle semantiche. Andare a dire che posso modellare come concetto secondario il tempo in semantica debole vuol dire che deve rispettare il primo e il terzo assioma e definizione dei tempi di scatto (la guardia) non c'è problema a rappresentare WTS come concetto derivato.

Complichiamo passando alla MWTS, aggiungiamo il 2 assioma (sempre avanzare mai tornare indietro) possiamo prendere un posto e darlo in ingresso/uscita a tutte le transizioni e otteniamo che in quel posto c'è sempre un gettone con un tempo che è l'ultimo scatto, a questo punto dato che tutte le transizioni sono obbligate a non scattare in un tempo precedente a uno dei gettoni che hanno in ingresso se hanno tutte in ingresso un gettone che rappresenta l'ultimo scatto abbiamo già forzato la monotonicità.

Aggiungiamo anche il quarto ed il quinto assioma (STS), già quando l'abbiamo presentato ci eravamo accorti che la difficoltà di rappresentazione (influenza che una transizione può avere su un altra a lei topologicamente scollegata è molto più forte, può dire "ti disabilito perchè devo scattare io prima") se uno guarda gli articoli scientifici dicono "è possibile" ma a patto che ci sia un posto in ingresso e in uscita ad ogni transizione in cui l'informazione collegata a quel gettone è l'intera topologia della rete e l'intero stato della rete. È fattibile ma non ha senso farlo. La semantica forte va trattata separatamente, non possiamo pensare di trattarlo come tutti gli altri dati, non possiamo gestire esplicitamente.

Questo non vuol dire che non sia utile mettere assieme le due cose:

## High Level Time Petri Net (HLTPN) e Time Environment Relationship Net (TER net)

In questo caso abbiamo degli aspetti funzionali tipici delle reti di alto livello (possiamo lavorare sulla classe c++ e in più abbiamo la parte temporizzata, timestamp gestito tramite le funzioni $t_{min}$ e $t_{max}$). Questo vuol dire che rispetto a quello che abbiamo visto fino adesso è possibile far dipendere aspetti funzionali da aspetti temporali e viceversa. Cioè prima abbiamo detto "le guardie possono ragionare sul tempo" benissimo, e il tempo può ragionare sui valori diversi (assieme al tempo posso calcolare la nuova velocità e stabilire di conseguenza la nuova posizione [informazione mista spazio e tempo])

Esempio: contraerea

< img >

Aggiungiamo un campo velocità e dire "il tempo che ho a disposizione per decidere se sparare oppure no non è semplicemente X ma dipende dalla velocità relativa dei due oggetti".

Queste reti sono ancora più complesse però si vede che in realtà la parte complessa veramente è ancora quella temporale e non ha cambiato complessità rispetto al dire "guardo solo la parte temporale" i problemi sono rimasti gli stessi.

Se definiamo tecniche di analisi per le reti time basic che ragionano sulla parte temporale, queste sono mutuabili in maniera identica sulle reti di alto livello temporizzate perchè sono due aspetti anche se possono interagire (??? SOMMATO ORTOGONALI??? non si capisce). La complessità della parte temporale rimane nei due casi ed è la parte più complessa delle due, l'altra è più facile perchè è più simile a quello a cui siamo abituati di programmazione (fare un programma sequenziale molto complesso è più facile che fare un programma real time più semplice perchè è qualcosa di più "viscido" sfugge tra le mani, è difficilmente simulabile, spesso i sistemi real time sono anche più critici di per sè [hard real time]).

## Analisi di reti temporizzate

Nasce abbastanza immediata la richiesta di analizzare reti time basic.

Quali sono i problemi?

Parte statica:
Analisi di raggiungibilità: consiste nell'enumerazione degli stati finiti raggiungibili, li costruisco finchè non arrivo in fondo. Abbiamo visto che è possibile dominare anche le reti non limitate tramite l'albero di copertura, perdendo un po d'informazione ma mantenendo una struttura capace di rispondere a molte domande interessanti.

Quali sono i problemi che saltano fuori quando la rete è temporizzata?

1. Lo scatto di una singola transizione può produrre infiniti stati (magari abbastanza simili perchè hanno lo stesso numero di gettoni nei vari posti però il contenuto informativo legato ai gettoni creati può essere con infiniti valori diversi) scatta tra 3 e 5 può scattare a 3.5373673822... (valori reali). Possiamo prendere un theta degli interi e avere un sistema finito ma se scatta tra 0 e 8 miliardi genera comunque un numero abbastanza grande di stati da dare problemi. Nel caso generale il tempo sarà reale per cui continuo (o contrario?).
1. La rete molto spesso evolve all'infinito, il tempo avanza, vuol dire che gli stati sono diversi (se ho uno stesso stato al tempo 5 e al tempo 18 possono essere evoluzioni diverse, non è lo stesso stato, ha informazioni diverse).

L'albero di raggiungibilità è per definizione infinito, quidni diventa un grande problema pensare di poterlo usare per (???). Inoltre non è trattabile nello stesso modo con cui abbiamo trattato l'infinitezza dell'albero delle reti non limitate perchè li il concetto di copertura è che avevo dei gettoni tra di loro non distinguibili (anonimi) e perciò rappresentabili come "sono più di" "sono grandi a piacere". Qui invece abbiamo gettoni che si distinguono l'uno dall'altro, non basta dire quanti sono.

Cerchiamo di costruire pian piano una tecnica che abbia senso.
La prima cosa da fare è definire cos'è per noi uno stato raggiungibile. Possiamo pensare che sia una marcatura in termini di reti temporizzate, un multiset di valori assegnati ad ogni posto, possiamo pensare di fare qualcosa di simile perchè uno scatto rappresenta infinite marcature che si differenziano per il valore associato, allora pensare di rappresentare tutte separatamente sarebbe rognoso. Uno **stato simbolico** (non uno stato concreto/numerico) della nostra rete è un insieme di possibili stati con in comune la numerosità dei gettoni nei vari posti ed è rappresentato da una funzione mu $\mu$ che mi associa un multiset di identificatori/simboli ai vari gettoni per cui invece che assegnare dei naturali assegno degli identificatori (tau 1, tau 2, tau 3 nel posto P1 e tau 5, tau 7, tau 1 al posto P2 [possono avere lo stesso valori due gettoni in posti diversi]).

Oltre a questo insieme di simboli dei vincoli $C$, delle disequazioni che mi danno dei rapporti, delle relazioni tra i vari simboli. tau 1 è compreso tra 0 e 23, tau 2 è compreso tra tau 1 + 8 e tau 4 - 22, ecc ecc

Un assegnamento di simboli e dei vincoli su questi simboli, questo è quello che mi definirà la cosa. Essendo che assegno un numero fissato di simboli ad ogni posto sto rispettando il fatto che tutti gli stati rappresentati da uno stato simbolico hanno lo stesso numero di gettoni nei vari posti.

Per le funzioni temporali ci semplifichiamo un po la generalità, fiassiamo una funzione che dice il tempo minimo $tmin_t$ e una che dice il tempo massimo $tmax_t$, un intervallo invece che un insieme generico anche discontinuo o sparso a piacere di valori che corrisponde ad avere una funzione $tf_t = \{X\:|\:X \geq tmin_t \land X \leq tmax_t\}$.

Esempio: albero di raggiungibilità

La nostra bella rete che non ha senso, una transizione weak e 2 strong.
Similmente alle reti plain c'è una prima fase semplice: l'inizializzazione (si inizia a costruire il nodo radice del nostro albero che corrisponde alla marcatura iniziale).
Quando la facciamo simbolica possiamo permetterci una cosa più ampia, non è una marcatura ma un insieme di marcature tra loro simili, ad esempio:
$\mu(P_1) = \{\tau_1\}; \mu(P_2) = \{\tau_0\}; \mu(P_3) = \{\tau_0\};$ dove  $0 < \tau_0 < 10$ e $\tau_0 < \tau_1 < \tau_0 + 15$
La marcatura 100 è una delle infinite marcature rappresentata da questo stato simbolico.
Abbiamo uno stato marcato nuovo, creato con una sua definizione, ora "finchè ci sono degli stati marcati come nuovi prendine uno e guarda le possibili evoluzioni" finchè era numerica lo sapevo fare ma ora è simbolica, cambia la regola di abilitazione, deve essere coerente con le reti time basic ma non ragiona su dei valori numerici, ragiona su espressioni simboliche. Iniziamo a dire che la marcatura 100 tra quelle rappresentata con questi vincoli e sapevamo che erano tutte e tre abilitate queste transizioni allora siamo sicuri che da $S_0$ ci sono tutte e 3 le possibili evoluzioni. Come facciamo a capirlo e come calcoliamo lo stato raggiunto?
Rispondiamo prima alla seconda domanda. Creare i nodi che vengono creati seguendo quelle possibili evoluzioni aggiornando marcatura $\mu$ e costraint $C$ (vincoli).
Costraint C dice che in questo momento $\tau_0$ è tra 0 e 10 e che $\tau_1$ è tra $\tau_0$ e $\tau_0 + 15$, questo non cambia, fa parte della nostra storia (cosa faccio domani non può influenzare cosa ho fatto ieri). Il nuovo costraint sarà formato da dove vengo + qualcosa di nuovo che predica sul tempo di scatto di $T_1$. Dire calcolo come evolve da $S_0$ nel sistema facendo scattare $T_1$ vuol dire i miei gettoni dico consumo un gettone in P1 e uno in P2 (rimangono senza gettoni), in P3 rimane lui e in P4 ci metto un gettone con un $\tau_2$. Rimane il costraint che c'era prima più qualcosa du $\tau_2$, io so che i possibili tempi di scatto di $T_1$ sono compresi tra [tempo di abilitazione, cioè il massimo tra $\tau_0$ e $\tau_1$ e il tempo del gettone in P2+5, quindi posso ipotizzare che sia quello di prima a cui aggiungo $\tau_2 \geq \tau_1$]. Ho costruito un nuovo stato ponendo/calcolando/istanziando quelle funzioni temporali sui valori simbolici che ho e amplio in questa maniera il constraint. Facciamo la stessa cosa con S2 e S3.
Per S2: questo l'ho visto come aggiornamento di marcatura ma in realtà è lo stesso ragionamento che faccio per identificare gli enabling, se definirmi ??? marcatura questo è un vincolo soddisfacibile, vuol dire che esiste un possibile assegnamento a quei valori tale che posso farlo scattare, se questo vincolo non è soddisfacibile significa che non c'è nessun tempo $\tau_3$ possibile coerente con la funzione di scatto, quindi quella transizione è disabilitata temporalmente. Diventa anche la condizione di abilitazione. La costruisco in questa maniera e se è soddistafacibile la transizione è abilitata e genera quella marcatura simbolica, se non è soddisfacibile significa che quella transizione non era abilitata. Noi sapevamo già che era bilitata perchè nella marcatura 100 era abilitata.

Piccolo colpo di scena sullo stato S3 (è tondo e non quadrato): C3 è più lungo e ci sono degli OR oltre che degli AND. Si è complicata un po.  
$C_0$ deve continuare ad esserci.  
$\tau_4 \geq \tau_0 + 3 \land \tau_4 \leq \tau_0 + 15$ queste sono le condizioni di abilitazione della transizione.  
$\tau_4 \geq \tau_1$ è la monotonicità dello scatto (non può essere inferiore all'ultimo tempo di scatto).  

Ora torniamo indietro al grafico barchart orizzontale. T3 può scattare tra 3 e 10 ma applicando la funzione temporale veniva fuori 3 e 15, avevamo perso questi valori perchè c'era l'influenza della transizone a semantica forte T2 per cui in questa marcatura non può accadere nulla oltre al tempo 10 perchè deve scattare. T3 può scattare prima di 10 ma se non scatta deve aspettare prima T2. Dobbiamo aggiungere qualcosa anche per questo.

$\tau_4$ deve essere minore o uguale al massimo tempo di scatto ($\tau_0 + 10$) [della transizione T2] a meno che 


In quelle marcature simboliche che sto rappresentando c'è la marcatura 100 e T2 è abilitata ma oltre a questa c'è anche la 200, la 2.5-0-0, la 3-0-0, la 11-10-10 ce ne sono tante.
Prendiamo come esempio questa 300. Come la analizziamo? T1 quando è abilitata? Tra 3 e 5, che è già diverso da prima (era tra 1 e 5). T2 risulta abilitata se >= 11 e <= 10 NON C'È NULLA, quindi non è abilitata e se non è abilitata non ha alcuna influenza sulle altre transizioni e perciò la T3 può scattare tra 3 e 15.

Quindi per certe marcature rappresentate da quel constraint S0 sono abilitate T1, T2 e T3 che deve scattare al massimo entro un certo tempo. Per altre marcature sono abilitate solo T1 e T3 e quindi dobbiamo dire "guarda si dev'essere minore di tau +10 che è il massimo tempo di scatto di un altra transizione forte oppure il suo tempo (inytervallo dei possibili scatti) è vuoto" e da dove arriva allora questo $\tau_1 > \tau_0 + 2$? Vuol dire che il minimo tempo di scatto è maggiore del massimo tempo di scatto, insieme vuoto.

$C_n = C_p \land t_n \geq maxT \land t_n \geq tmin \land t_n \leq tmax \prod_{t_s}(tmax_s < tmin_s \lor tmax_s < maxT \lor tmax_s \geq t_n)$

Constraint nuovo = constraint precedente messo in AND con il nuvto t >= maxt (il massimo tempo della rete) AND tn >= tmin e tn <= tmax messo in AND con la produttoria del tempo massimo di una transizione forte abilitata (per ogni transizone forte abilitata) minore del suo tempo minimo O tempo masismo (copia).

Questa è la vera formula che stabilisce l'abilitazione della transizione.

Facendo le cose complete:

< img >

in S1 le parti colorate sono già implicate in quella nera. Stesso discroso per S2.
In S3 solo una parte rossa sparisce.
È solo come seplificare l'espressione.


--- Ultima lezione ---


Dopo che scatta T3 non può più scattare (topologicamente), rimangono solo T1 e T2 e T1 aggiunge come vincolo ... e T2 aggiunge come vincolo ... .

Possiamo notare che T1 è abilitata solo se $\tau_4 \leq \tau_0 + 5$, interessante questa espressione perchè non dipende dal nuovo ma sto ponendo una condizione sulla storia (non dipende da Tn, siccome tn > tau4 e tn <= tau0 + 5 allora anche tau4 < tau0 + 5). È vero sempre? In alcuni casi non è vero. Similmente vale anche per T2.

Questo ci porta ad interpretare il grafo in maniera più elaborata (infatti è tondo e non quadrato). Possiamo trovare un caso in cui è abilitata solo T1 (formula) o solo T2 (formula), sono abilitate entrambe (formula) o sono entrambe disabilitate (formula).

Si mette un pallino all'orgine della freccia: nero se esco da tutti gli stati (tutti mi permettono di seguire quella freccia) e bianco se solo una sottoparte degli stati rappresentati mi permettono di seguire quella freccia.

T3-[T1] e T3-[T2] hanno pallini bianchi t1 e t2. e anche su S0-[T2].

Cosa abbiamo / non abbiamo?
- Non abbiamo una forma normale con cui esprimere questi stati. Li abbiamo costuiti aggiungendo al costraint degli altri costraint poi però abbiamo semplificato, non abbiamo trovato un modo per dire "questo costraint lo esprimerò sempre in questa maniera oppure in quest'altra maniera che identifica le stesse cose ma ha un <= o >= dall'altra parte invece che l'=". Non abbiamo fatto lo sforzo di uniformare e normalizzare l'espressione, questo vuol dire che non ha molto senso pensare di confrontare gli stati (perchè nell'albero di raggiungibilità una delle prime operazioni che facevamo era "se il nodo è uguale al precedente [confronto>]" qui non lo possiamo confrontare perchè potrebbero essere uguali ma non abbiamo sintatticamente non lo è, possiamo verificare se sono uguali ma non per uguaglianza sintattica, potremmo fare se si implicano a vicenda i due costraint allora si e gli assegnamenti sono uguali a meno di permutazioni però comincia ad essere complesso).
- L'albero è infinito, cresce. In parte come conseguenza come conseguenza del non riconoscerlo ma non solo. Un albero infinito porta anche a pensare che sia completamente inutile. A cosa mi serve costruire un albero che non arriva a termine? Intanto può capitare che faccia simultaneamente la costruzione e la verifica (man mano che costruisco i nodi li controllo) per verificare certe proprità potrebbe non essere necessario costruire tutto prima, basta costruirlo fino al punto che mi interessa (voglio controllare se è raggiungibile una certa marcatura, comnicio a costruire e controllo se quella nuova è la marcatura che mi interessa e se non è raggiungibile vado avanti all'infinito di costruire l'albero). Qual'è il trucco usato in questi casi? Non vengono definite proprietà che non sono (bounded LIVENESS e BOUNDed invariance) la vivezza e la limitatezza di certe situazioni (??? il prof si era confuso), il fatto che raggiungo una certa situazione o che non la raggiungo (SAFETY) ma:
    - Bounded invariance: c'è qualcosa che non cambia, è sempre vera.
    - Bounded liveness: è sempre vero che raggiungo una certa situazione (non è sempre stata vera ma la raggiungo)

Ma il bounded è quello che mi aggiunge rispetto alle proprietà assolute, limitata nel tempo (entro un certo tempo). Non dico la nostra centrale nucleare non esploderà ma che non esploderà nelle prossime due ore, oppure so che se riesco a non farla esplodere per due ore i miei sistemi di sicurezza entrano in funzione quindi non ho più paura che esploda. Mi interessa verficiare su dei transienti che accada/non accada qualcosa e il fatto che accada troppo tardi o che non accada per (il prof ha detto "coso") non mi interessa. Diventano significative delle proprietà che mi dicono "non devi costruire tutto l'albero, devi costruirlo fino a un certo tempo" e allora qui capiamo forse anche il motivo per cui abbiamo fatto l'assioma 3 (non ci possono essere infiniti eventi in un singolo istante). Senza questo potremmo avere infiniti alberi che non arrivano al tempo limite.

Dato che molte delle proprietà dei sistemi real time possono essere espresse come bounded questa tecnica ha un qualche senso, riesco a dare risposte a certe domande che normalmente ci poniamo e che sono difficili da rispondere.

Però mi piacerebbe arrivare a dei grafi (come fatto per l'altro coso), dove il primo passo di solito è fare grafi aciclici e poi magari riuscire a condensare con grafi ciclici.

La prima chiave da considerare per poter ambire a fare questa cosa è scordarci la storia, cosa vuol dire? Scordarci come siamo arrivati ad una certa marcatura E mantnere solo le informaizoni necessarie per far evolvere il sistema da li in avanti.(es semplice: ho 2 transizioni che possono scattare indipendentemente, in concorrenza, ad un unico tempo ed esattamente lo stesso tempo, ho un evoluzione in cui scatta prima t1 poi t2, vicevera o entrambe allo stesso tempo, per la mia tecnica di costruzione la prima scatterà a tau1 e la seconda a tau2 e un altra in cui scatta prima t2 a tau3 e poi t1 a tau4, non avendo modo di normalizzare queste storie le interpreto come stati diversi anche se sono esattamente equivalenti a meno della permutazione, se non mi interessa come ci sono arrivato ma solo come procedo da li in avanti quei due stati sono esattamente equivalenti).

Se riusciamo a fare questa cosa cosa riusciamo a fare? Dimenticarsi della storia non vuol dire dimenticarsi tutto, mi ricordo che sono arrivato in uno stato all'istante 5, 8 o 12 (il tempo avanza). COmunque questo progredire del tempo mi dice che non riuscirò a trovare dei cicli se non dei cicli a tempo 0. però c'è qualcosa che mi esclude poter avere dei cicli a tempo 0 perchè se avessi cicli a tempo avrei infiniti eventi in tempo finito, proibito dal terzo assioma. Quindi sono sicuro che non posso avere un grafo aciclico (mi sembra strano controlla bene).

Cominiciamo a fare questa cosa qua: grafi aciclici cioè cercare di normalizzare e riconoscere come poter esprimere un costraint solo in termini dei gettoni presenti in un certo momento, dimenticandosi quelli che sono dei timestamp non più associati a nulla (es: marcatura dello stato S6 ho un getotne con tempo tau4 e uno con tempo tau7 ma capire in che relazione sono dal costraint non è immediato, mi sono portato dietro una storia che mi complica la visione, impedisce il riconoscimento di situazioni simili e non solo, dato che si continuano ad aggiungere man mano che espanderò i livelli successivi anche la verifica di "è soddisfacibile quest'espressione" sarà sempre più lenta perchè si aggiungeranno sempre nuove variabili). se riesco invece a pensare "a me serve solo tau4 e tau7" questa stessa cosa è come dire (rinomino tau4 in tau1 e ...) $\tau_1 \geq 3 \land \tau_1 \leq \tau_2 \land \tau_2 \leq \tau_1 + 2 \land \tau_2 \leq 15$. Metto in relazione gli unici due simboli che esitono nella mia marcatura rinominandoli in modo da non avere vuoti. Devo riportare gli effetti indiretti delle variabili che voglio eliminare su quelle che invece voglio tenere.

Questa cosa è abbastanza semplice, è praticamente un algoritmo di Floyd, 3 cicli for.

Matrice in cui vado a rappresentare i vincoli e dico "ho dei vincoli tra A e B  e  tra B e C, B lo voglio buttare via, riesco a capire quali sono i vincoli che ho tra A e C. Riesco a scrivere delle disequazioni per cui valgono ancora esattamente le stesse combinazioni possibili tra A e C pur non scrivendo più B?
È come se avessimo tante città su un unica autostrada e noi conosciamo alcune distanze tra coppie di città, però sappiamo non esattamente quando è ma maggiore di minore di. Riusciamo a fare la chiusura transitiva di queste relazioni? Floyd. In pratica quello che sto dicendo è "prendiamo (o costruiamo?) la matrice < immagine >". Dopodichè voglio scoprie la relazione chec'è tra A e C, prendo:
$$a + 2 \leq b$$
$$b \leq c$$
diventano
$$a - b \leq 2$$
$$b - c \leq 0$$
le sommo: a-b e b-c se le sommo +b e -b sparisce, a - c è uguale alla somma dei due valori. Per cui quello che faccio è tre volte il ciclo $m[ij] \leq m[ik] + m[kj]$?
Se si sotituisci, chiudo 3 volte e garantito che ?ternico? converge perciò scopro meccanicamente che $a-c \leq 2$ quindi $a < c+2$ e $c < a + 11$.

Io però posso prendere le mie 4 variabili che avevo nel costraint precedente, sono tutte equazioni ?? adifferenza?? eventualmente con l'aggiunta della colonna "tempo assoluto" (perchè c'è tau0 all'inizio che è il mio zero). Lo prendo come tempo assoluto, come se tau0 fosse C+0 o C+10 e ho questo riferimento sempre relativo, prendo calcolo floyd riempiendo la mia matrice, butto via le colonne e le righe delle variabili che non mi interessano più, ho un insieme di nuove equazioni che posso prendere come forma normale (forma completa) e poi da quella posso controllare se le singole espressioni sono implicate da altre cose per semplificare ma avere una procedura ordianata fissa come fare queste semplificazione anche questa può essere chiamata una forma normale. E ottenere questa. Per cui è un modo per scrivere i miei stati in modo più sintetico, a non portarmi dietro variabili che non mi interessano (quindi renderla più veloce) e contemporaneamente avere l'ambizione di poter fare ogni tanto dei ricongiungimenti (scoprire degli stati che sono uguali pur avendo storie diverse).

Questo però non è sufficiente per rendere fruibile/analizzabile una rete pur semplice.

< img gas burner >

Prendiamo una sottoparte e vediamo che già solo questa ha stati infiniti, non riesco a verificare nulla. La prima cosa su cui si è ragionato è "abbiamo semplificato e riconosciuto delle uguaglianze tra stati, non è che riusciamo a fare qualcos'altro di utile per condensare il grafo e che ci viene abbastanza immediatamente in mente. È quello di 'uno stato contiene l'altro'". C'erano degli stati da cui partiva un evoluzione che però valeva solo per una sottoparte, per cui già quello stato non era una cosa così atomica, era già da considerare in certi casi come somma di diverse situazioni, allora posso scoprirla non solo quando parto ma anche quando arrivo in uno stato? Posso pormi la domanda "definire come inclusione/contenuto": uno stato A è contenuto nello stato B se e solo se tutte le marcature rappresentate da A sono rappresentate anche da B. Ho due stati, uno più generale e uno più particolare, uno rappresenta 50.000 situazioni e l'altro 100.000 di cui 50.000 sono quelle dell'altro stato. È inutile averli tutti e due perchè in quello dei 100.000 le eoluzioni rappresentano anche quelle possibili in quello da 50.000. È inutile averle entrambi. O tolgo quei 50.000 dai 100.000 e faccio due stati partizionati (altra idea che poteva venire in mente) oppure li metto insieme e ne tengo solo uno, quello che contiene tutto.
Questo deve avere:
- stesso assegnamento di timestamp: ad ogni posto deve assegnare un numro uguale di gettoni e con lo stesso timestamp
- $C_A \rightarrow C_B$ che costraint di quello contenuto implichi il costraint di quello contenente, tutte le volte che è vero questo è vero anche l'altro, tutte le situazioni che mi rappresenta A sono rappresentate anche da B. Non richiede l'uguaglianza, uno contenuto nell altro.

Vediamo un esempio molto semplice:

< img >

Può scattare solo T1 quindi il costraint diventa $T0 \geq 1$ poi maggiore di 2, 3, 4, ...
Con le regole di semplificazione che abbiamo visto sarebbe stato $T0 \geq 0 \land T1 \geq T0 + 1$ semplificato diventa, prendo solo il T1 che diventa >= 1 e siccome c'è solo lui lo rinomino in t0.
Questo vuol dire che così come l'abbiamo fatto adesso con la semplificazione ho comunque infiniti stati che hanno la stessa marcatura e ogni volta un vincolo che cresce in questa costante. $C_n: T0 \geq n$. però se è maggiore di n è maggiore anche di 1, se è maggiore di 5 è maggiore di 2, c'è l'implicazione dei costraint, c'è l'uguaglianza delle marcature, è la perfetta definizione di inclusione che abbiamo fatto prima.
Possiamo presentare dicendo "da S0 può scattare T1 e si va in un sottoinsieme (freccia bianca) degli stati rappresentati da questo" non tutti quelli maggiori di 0 ma quelli maggiori uguali a 1, ma tanto erano già rappresentati prima e sapevo già che poteva scattare T1 quindi l'evoluzione è quella.
Con questa piccola verifica aggiuntiva siamo riusciti a condensare un albero infinito in un grafo infinito.

< img >

Non è ancora sufficiente per il gas burner. Se lo guardiamo da più vicino notiamo che ci sono dei ricongiungimenti però ci sono anche delle costanti un pochino alte (scale diverse) ed in particolare ... (è passato ad altro poi ritorna) ... ho dei riferimenti a tempi assoluti, ho un riferimento storico che mi impedisce di riconoscere stati simili rispetto ad un osservazione relativa. Le nostre funzioni temporali avendo fatto tutte equazioni differenze abbiamo eliminato i riferimenti assoluti possibili della rete TB. Per capire come evolve una rete da un certo punto in poi non ci interessa sapere il tempo assoluto, ci interessa sapere le relazioni tra un timestamp e l'altro (i vincoli relativi) perdere i riferimenti ai tempi assoluti.
Pezzo salto prof: C'è un posto in cui ci va un gettone ma che nessuno consuma (sottorete tagliata) questo gettone rimane li in eterno una volta che c'è. Se rimane li ma tutti gli altri vanno avanti è un po come uno zero assoluto che ci impedisce di riconoscere situazioni simili ... ah no è andato troppo avanti.
In $0 \leq T0 \leq 10$ la rete evolve $1 \leq T0 \leq 11$ ...
C'è un overlap parziale ma non completo, questo non include il precedente. Però se mi accorgo che alla mia funzione non interessa sapere se siamo all'istante 50 o 80, interessa rispetto al gettone che c'è già, allora questo vuol dire togliere riferimenti ai tempi assoluti, considerare queste costanti come se fossero un altra variabile che dopo aver applicato gli effetti ad ??altro??? posso eliminare. True include true a differenza delle cose di prima.

< img >

Questo da una bella botta, un lato è ciclico e uno va avanti. È quello che diceva prima, c'è uno zero relativo, un posto in cui T0 e nessuno toccava più.

Ora non vorrei far pensare che questo valga solo per i gettoni morti, può valere anche per altri tipi di gettoni che abbiamo chiamato "timestamp anonimizzabili". Se il timestamp associato ad un gettone di una marcatura M non verrà mai usato per stabilire come evolverà la rete a partire da quella marcatura allora è possibile dimenticarsi il suo valore (non dimenticarci che c'è un gettone ma il suo timestamp tanto nessuno ne avrà mai bisogno).
Scrittura più formale (slide).

< img >

Il continuo scatto di T1 continua a creare nuovi stati che si differenziano per la distanza con il tempo di P2 che non consumo mai. L'accorgermi che nessuna delle due utilizzerà il tempo del gettone in P2 (che so a priori essere minore dell'altro) mi fa dire "dimentica cosa c'era in P2, chiamalo TA".

< img >

Infiniti scattid i T1 finchè non decido di far scattare T2. Esistono delle euristiche che ci dicono quando un gettone è anonimizzabile.

< img >

Otteniamo un grafo che è finito (gas burner). Non è garantito che sia finito per ogni rete. Nella maggior parte riusciamo a costruire dei grafi ampi su cui riusciamo a dare delle risposte.

Abbiamo perso però delle informazioni:
- inclusione: facendo le inclusioni noi abbiamo che non solo la freccia d'uscita può essere bianca ma anche quella di entrata può andare solo ad un sottoinsieme degli stati.
Cosa succede se vado nella metà sotto entrando ed esco solo dalla metà sopra? Vuol dire che il cammino di enrtrare e uscire non è garantito (possibile presenza di cammini non percorribili).
- relazioni precise tra gli stati: ho una relazione all'interno dello stato so come si relazionano ma il tempo di questo gettone rispetto al tempo di quest'altro gettone l'ho perso. Quello che si fa è "si etichettano gli archi dicendo rispetto alla marcatura di partenza l'intervallo dei tempi possibili" si arricchisce l'informazioni sugli archi in modo che tra due stati consecutivi sia ricostruibile quest'informazione da un lato e dall'altra sia possibile comporla eventualmente ripercorrendo un certo cammino e ricostruendo le cose che ci interessano. Però di per se guardano i singoli stati devo fare un lavoro aggiuntivo per avere quest'informazione.
- tempi anonimi: "voglio raggiungere una certa marcatura in cui nel posto P4 c'è un gettone che è generato al massimo 3 secondi dopo il gettone nel posto P1" se nel posto P1 ho reso un tempo anonimo non ho più qualcosa da confrontare quindi di nuovo posso rieseguire il cammino che mi ha portato fin li senza anonimizzarlo e guardo cosa succede.

Copertura temporale
Visto che abbiamo parlato di albero di copertura come cosa così bella per le reti piane non possiamo pensare all'albero di copertura qua? È che i gettoni hanno un informazione che non li rende distinguibili. In certi casi i gettoni TA (anonimi), posso dire quanti gettoni TA ho in quel posto. Cosa succede se ad un certo punto mi accorgo che ritorno nella stessa situazione che cambia dalla precedente solo perchè ho un gettone TA in più. Probabilmente quella situazione vuol dire che è qualcosa che posso ripetere il cammino che mi ha fatto fare questa trasformazione aggiungendo un altro TA, aggiungendo un altro TA, ... generano un omega TA, un numero grande a piacere di gettoni TA.
Un impotesi non dimostrata è che nelle reti temporizzate se sono non limitate in realtà sono non limitate sul numero di gettoni TA. Perchè posso accumulare inifiniti gettoni solo se non mi interessa il loro tempo, se mi interessasse vuol dire che dipenderei da cose arbitariamente del passato, per capire cosa non posso fare adesso per le evoluzioni infiniti gettoni del passato che distanto infinito tempo nel passato, una rete così è molto improbabile che esista.
Questo ci porta a dire che anche reti non limitate temporizzate proabilmente potrebbero essere trattate con un albero di copertura temporizzato perchè riconoscerebbe questa illimitatezza dei gettoni tramite limitatezza dei gettoni TA.