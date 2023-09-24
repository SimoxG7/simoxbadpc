# Reti di Petri

Il linguaggio delle reti di petri è un linguaggio che serve per raccogliere le specifiche, ha diversi dialetti (di cui uno riguarda i sistemi realitme [sistemi sincronizzati] in cui ci sono crititcità, lo sforzo di andare a formalizzare la descrizione dei requsiti può valerne la pena).

Sono in parte simili alle macchine a stati finiti, nascono specificatamente per parlare di sistemi concorrenti (anche distribuiti e realtime). Cambiano però sia il concetto di stato che di transizione in quanto lo stato non è più un informazione atomica vista a livello di sistema ma spezzetta lo stato in parti diverse e la composizione avverrà tramite la visione di questi stati parziali. Permette di rappresentare entità distinte, ognuna con il proprio stato, ma di avere un evoluzione globale del sistema. Le transizioni non avverranno più sullo stato globale ma sullo stato parziale.

### Informalmente

Può essere utile per dialogare con il cliente. Possiamo definire una rete di petri molto informalmente dicendo che è composta da dei **posti** [place] (rappresentati con dei cerchi), delle **transizioni** [transition] (rappresentate con una barra, un rettangolo, un quadrato, qualcosa di spigoloso) e degli **archi** [arcs] che connettono posti a transizioni e transizioni a posti (un posto non può essere connesso ad un altro posto, lo stesso vale per le transizioni). È un **grafo bipartito**, ovvero composto da nodi che entrano in relazione solo con nodi dell'altro tipo.

I **gettoni** [tokens] (rappresentati come dei pallini) assegnati ai posti, possono essere da 0 a """infinito""" ed è questa disposizione di gettone nei vari posti che determina qual'è lo stato attuale della rete.

< img rete petri >

Il fatto che il posto P1 P2 e P3 rappresentano una parte del sistema e P0 e T0 un altra parte concorrente è una cosa lecita, queste vanno ad interagire perchè sono collegate da degli archi. Se senso guardare solo un pezzo per vedere in che stato quella parte del sistema piuttosto che un altra.

I gettoni devono poter cambiare, perchè la loro posizione determina lo stato e lo stato evolve. Una transizione abilitata (può fare qualcosa) quando avrà nei posti collegati a lei in ingresso (archi che vanno dal posto alla transizione) un certo numero di ghettoni. Il fatto che questa transizione scatti [fire] produce il fatto che certi gettoni vengono distrutti nei posti in ingresso e vengono generati dei gettoni nei posti in uscita. I gettoni NON VENGONO SPOSTATI. Il numero complessivo di gettoni presenti può cambiare.

Spiegare al cliente che è successo qualcosa e cambiato lo stato glielo si fa vedere su un po di gettoni, lo si può far simulare, farlo giocare, è una cosa abbastanza intuitiva rispetto ad un linguaggio logico. Lo svantaggio dei modelli operativi è che non dicono solo cosa deve fare ma un po'anche come lo fa.

Il trucco per cui lo può chiamare specifica è che si dice al cliente che deve comportarsi nello stesso modo, avere le stesse qualità, non che deve essere fatto così. È un implementazione di riferimento con cui ci si può confrontare per vedere se i risultati che il nostro sw ha sono corretti. Nel programma non è necessario avere qualcosa che rimappa i posti della rete.

### Definzione formale di rete di petri

Classicamente (perchè ci sono tanti dialetti), noi consideriamo la visione chiamata Posti-Transizioni (PT Net) la definizione più comune viene data attraverso la seguente quintupla: $[P, T, F, W, M_0]$.

- P = insieme dei posti
- T = insieme delle transizioni. Deve valere $P \cap T = \varnothing$ e $P \cup T \not= \varnothing$ almeno un posto o una transizione ci devono essere (casi di dubbia utilità comunque).
- F = relazione di flusso: è un sottoinsieme del prodotto cartesiano ordinato di posti-transizioni e transizioni-posti $F \subseteq (P \times T) \cup (T \times P)$. Essendo un sottoinsieme ci sono solo alcune relazioni, non tutte, quelle esistenti sono indicate dagli archi (nella rappresentazione grafica).
- W = funzione che associa un peso ad ognuno degli elementi del flusso, il peso è un intero positivo escluso lo 0. $W: F \rightarrow N-\{0\}$ (o $N+$).
- M_0 = marcatura iniziale, è l'assegnamento iniziale dei gettoni, è una funzione che va dai posti ad un numero naturale con anche zero. $M_0: p \rightarrow N$.

Non è necessario dal punto di vista della definizone, ma useremo alcune scorciatoie che sono:
- Il preset di un nodo 'a' è l'insieme degli elementi 'd' appartenenti a P o a T tale che esista un flusso \<d,a> \<coso, a>: $Pre(a) = \{d \in (P \cup T) | <d,a> \in F\}$.
Può essere un preset sia di un posto che di una transizione, è l'insieme degli antecedenti del nodo.
- postset in modo simmetrico: $Post(a) = \{d \in (P \cup T) | <a,d> \in F\}$.

Avendo questi elementi è possibile realizzare una rappresentazione grafica e viceversa. C'è una corrispondenza biunivoca.

Useremo M per un assegnamento di numeri naturali ai posti (corrisponde al numero di gettoni in quel posto) in uno stato qualsiasi della nostra evoluzione.

### Comportamento dinamico

Cosa vuol dire che una transazione abilitata e che può scattare?

una transizione t appartenete all'insieme delle transizioni è abilitata in una particolare marcatura (quella iniziale o una delle sue evoluzioni) se e solo se per ogni posto appartenente al preset della transizione la marcatura di quel posto è maggiore o uguale al peso di quell'arco. Ogni posto in ingresso deve avere sufficienti gettoni per farla scattare. $t \in T abilitata se e solo se \forall p \in Pre(t) M(p) \geq W(<p,t>)$. Non stiamo ragionando su tutti i posti ma solo su quelli collegati in ingresso alla transizione, posso guardare esclusivamente una zona e non tutto il sistema (**località dell'analisi**). Si indica con la notazione M \[ t > dentro la marcatura M la transizione t è abilitata.

Una transizione abilitata [enabled] è abilitata a **scattare**.

Uno scatto fa cambiare la marcatura, cambia la funzione M, nel momento X+1 abbiamo che c'è un diverso assegnamento dei gettoni nei posti e questa nuova marcatura M' viene definita come: per tutti i posti che appartengono al preset ma non al postset della transazione considerata (solo quelli collegati in ingresso) il numero di gettoni nella nuova marcatura sarà uguale al numero di gettoni che c'era prima meno il peso dell'arco che collega il posto alla transizione. Quando c'erano sufficienti gettoni (arco di peso 4 = 4 gettoni richiesti per abilitare), per toglierli ci devono essere.
Poi ci sono i posti che sono nel postset ma non sono nel preset, in questo caso i gettoni sono quelli di prima + quelli del peso dell'arco.
Poi ci sono quelli che appartengono ad entrambi - uni + gli altri.
Si poteva evitare questa definizione per casi? È una definizione che va eseguita in parallelo e non ci può essere sovrapposizione altrimenti avrei 2 definizioni della stessa funzione e non saprei quale applicare.
Per tutti gli altri la marcatura non cambia (è questa la località, lo scatto non influenza la marcatura di tutti i posti che non sono collegati a tale transizione).

$$\forall p \in Pre(t) - Post(t)      M'(p) = M(p) - W(<p,t>)$$
$$\forall p \in Post(t) - Pre(t)      M'(p) = M(p) + W(<t,p>)$$
$$\forall p \in Pre(t) \cap Post(t)      M'(p) = M(p) - W(<p,t>) + W(<t,p>)$$
$$\forall p \in P - (Pre(t) \cup Post(t))      M'(p) = M(p)$$

M [ t > M'  significa che lo scatto di t in M produce M'.

## Esempi

Produttore-consumatore e buffer

La traduzione da MsF a RdP è abbastanza automatica, basta avere un posto per ogni stato della MsF, una transizione per ogni arco e un gettone unico per tutta la rete che indica lo stato.

Componiamo queste 3 reti.
Nell'immagine modificare 'deposita' in 'deposita1' e 'deposita2' (sono identificatori di un insieme, non possono essere uguali). Abbiamo fuso le precondizioni delle varie parti di sistema e le postcondizioni che operavano sullo stesso evento.

In questo caso mettiamo 3 gettoni (1 per ogni MsF). Tutti e 3 sono pronti.

Come evolve?
Se non c'è un numero sull'arco significa che è 1. Una transizione abilitatà PUÒ scattare ma non è obbligata a farlo.
Produci è l'unica transizione abilitata all'inizio. I gettoni sono anonimi, non importa che gettoni sono (sono tutti uguali), importa quanti sono. A questo punto l'unica cosa che può accadere è deposita. Ora il produttore può produrre e il consumatore può consumare (2 transizioni abilitate). In queste reti di petri può avvenire solo uno scatto alla volta. Non è stato detto cosa succede se scattano due transizione contemporaneamente. Se scatta produci abilita deposita, altro caso.
Quale scelgo? Sono entrambe ammissibili, non ho nulla che mi impegni a fare una di queste cose. O scatta una o scatta l'altra o restiamo fermi. Se fosse stato necessario specificare un ordine con cui devono scattare significa che la rete di petri è scorretta, ammette un comportamento non ammissible, la rete non indica tutti i vincoli del sistema, esiste un altra rete in cui riesco a forzare un ordine di esecuzione.

In questo caso non stiamo sfruttando molto la rete di petri, questo è dovuto al fatto che è una semplice traduzione di automi a stati finiti.

Vediamo una versione alternativa. Ora ci sono posti in meno ma ho più di un gettone dentro al buffer (B0), il numero di gettoni indica quante posizioni sono libere, per quante volte posso far scatatre deposita dopo che il produttore ha prodotto. COn 20 gettoni è un buffer con 20 posizioni libere. Senza avere molti stati o fare il prodotto vettoriale degli stati con il produttore e consumatore, rimane la stessa rete, cambia solo la marcatura.

Se aumentiamo il numero di gettoni del produttore e del consumatore stiamo dicendo che ci sono più entità in grado di produrre/consumare che usano lo stesso buffer. Ho moltiplicato gli elementi del sistema di cui sto tracciando l'esecuzione. La stessa cosa in una macchina a stati finiti iniziava già ad esplodere.

Altre modifiche

Possiamo modellare un buffer di capacità infinita? I gettoni non possono essere infiniti (può essere un numero grande a picere ma non l'infinito). Si, basta rimuovere il posto B0 e le sue transizioni, in questo modo posso conumare solo il numero di depositi effettuati (non possiamo prelevare più dei depositi).
Se volessi fare infiniti produttori tolgo il posto P0 e la transizione produci non ha posti in ingresso, è sempre abilitata.

Cambiando i pesi degli archi diciamo che un produttore deposita 3 elementi alla volta e i consumatori li prelevano 2 alla volta (unità di produzione/consumo non equivalente).

## Relazioni: sequenza

Siccome ragioniamo su sistemi concorrenti, riconoscere certe situazioni legate ai sistemi concorrenti può essere utile. Dobbiamo definire alcuni termini: cosa definiamo per 'sequenza' all'interno di una rete di petri a questo livello?

Una transizione $t_1$ è in sequenza con una transizione $t_2$ in una marcatura $M$ se e solo se: $t_1$ è abilitata nella marcatura $M$ mentre $t_2$ no e "scattando t1 in M t2 diventa abilitata" : $M [ t_1 > \land \neg M [ t_2 > \land M [ t_1 t_2 >$.

C'è una relazione di sequenza tra t1 e t2, t1 è condizione sufficiente affinche possa scattare t2 (relazione d'ordine).

Esempio:

< img >

$T_0$ e $T_1$ oppure $T_0$ e $T_2$ oppure $T_3$ e $T_2$

## Relazione di conflitto

Due transizioni $t_1$ e $t_2$ sono in conflitto (questa è simmetrica, l'altra no):
- **strutturale** si verifica se e solo se l'intersezione dei due preset non è vuota: $Pre(t_1) \cap Pre(t_2) \not= \varnothing$. Non hanno dei posti in comune quindi non si possono dare fastidio nelle condizioni di abilitazione di una dell'altra perchè ragionano su informazioni diverse. Questo dipende solo dalla topologia della rete, non da una specifica marcatura. Se due transizioni non hanno dei posti in comune nei loro preset sicuramente non potranno darsi fastidio l'un l'altra quando ragioneremo sulle abilitazioni.
- **effettivo in una marcatura M** se e solo se entrambe sono abilitate nella marcatura M e esiste un posto in comune ai due preset tale per cui il numero di gettoni in quel posto è minore della somma delle richieste dei due scatti (minore della somma dei pesi dei due flussi che vanno dal posto alla transizione) allora sono in conflitto effettivo in quella marcatura. $M [ t_1 > \land M [ t_2 > \land \exists p \in Pre(t_1) \cap Pre(t_2) | (M(p) < W(<p,t_1>) + W(<p,t_2>))$


Altra versione rilassata di conflitto: $M [ t_1 > \land M [ t_2 > \land \neg M [t_1 t_2 >$ sono entrambe abilitate in M e non è possibile la sequenza $t_1 t_2$ a partire da M. Differenze con quella prima NON È SIMMETRICA (ma potrei aggiungerlo). Questo era solo per essere sicuro che non avessimo capito questo al posto della definizione effettiva.

Esempio del prof "posso modificare questa rete per farla rimanere in conflitto secondo la prima definizione ma non per la seconda". Aggiungiamo un arco in uscita da una transizione che rimette il gettone tolto al posto iniziale. La prima definizione non tiene conto degli archi in uscita dalla transizione, la seconda ragionando sugli effetti dello scatto ragiona sia su quello che tolgo che quello che metto.

Perchè viene usata la prima come definizione di conflitto?
Non hanno identità i gettoni, queste transizioni sarebbero potute scattare nello stesso istante NO, ce l'hanno ma in momenti diversi (uno dopo l'altro) non nello stesso momento. Nel momento in cui genero un altro gettone non è che non sono stato in conflitto su quello ma uno l'ho consumato e ora ce n'è un altro ed è un altra situazione, un conflitto di tipo diverso.

C'è una relazione tra conflitto strutturale e conflitto effettivo? Se c'è conflitto strutturale c'è conflitto effettivo? NO, perchè l'effettiva deve soddisfare una condizione in più. Il contrario invece vale perchè se esiste un nodo in comune che ... (ha saltato il prof) significa che l'insieme non è vuoto.

Esempio: conflitto

< img >

Strutturale: $T_2$ e $T_6$ e anche $T_3$ e $T_4$.
Effettivo: $T_3$ e $T_4$

## Relazioni: concorrenza

È l'opposto del conflitto, è quando due transizioni non si danno fastidio.

Due transizioni $t_1$ e $t_2$ sono in concorrenza:
- **strutturale** se $Pre(t_1) \cap Pre(t_2) = \varnothing$. Se non hanno alcun posto in comune non si danno fastidio.
- **effettivo in una marcatura M** se e solo se sono entrambe abilitate e per ogni posto p la marcatura è maggiore uguale alla somma dei pesi. $M [ t_1 > \land M [ t_2 > \land \forall p \in Pre(t_1) \cap Pre(t_2) | (M(p) \geq W(<p,t_1>) + W(<p,t_2>))$. Se per tutti i posti che hanno in comune c'è un numero di gettoni sufficiente a far scattare entrambe.

Implicazioni: lo strutturale non implica l'effettivo, devono anche essere abilitate!!! Se sono nella strutturale chi mi dice che sono entrambe abilitate? Non è vero che se è effettivo è strutturale, perchè potrei avere posti in comune con abbastanza gettoni per far scattare entrambe.

Però siamo che sicuri che se sono in conorrenza non possono essere in conflitto e viceversa, c'è una terza possibilità: ne conflitto ne concorrenza.

Esempio:

< img >

Strutturale: tutto quello che non è in conflitto strutturale è in concorrenza strutturale.
Effettiva: nessuno.

## Insieme raggiungibilità

Nella definzione della rete di petri abbiamo un punto di partenza (la marcatura iniziale) da qui non sono libero di cambiare gli assegnamenti dei gettoni, devo seguire le regole di abilitazione e scatto per far evolvere la rete. Devo ragionare sulle marcature raggioungibili a partire dalla marcatura iniziale.

L'inisieme di raggiungibilità di una rete a partire da una marcatura M è il più piccolo insieme di marcature tale che:
- $M \in R(P/T, M)$: la marcatura M appartiene a quelle raggiungibili.
- $(M' \in R(P/T, M) \land \exists t \in T   M'[t> M'') \rightarrow M'' \in R(P/T, M)$: se M' appartiene all'iniseme di raggiungibilità nella rete posti-transizioni a partire da M ed esiste una transizione tale per cui è abilitata in M e porta in M'' (posso raggiungere con un singolo scatto M'' da M') allora anche M'' è raggiungibile. Ricorsivamente faccio la chiusura transitiva della relazione e ottengo tutte le marcature raggiungilibi. R è l'insimee di raggiungibilità.

## Proprietà: limitatezza

Una proprietà utile perchè ci rende più facile il lavoro di capire cosa sta succedendo è dire le possibili evoluzioni sono finite o infinite? Gli stati raggiungibili sono finiti o infiniti?

Diremo che la rete è limitata (quindi gode della proprietà di limitatezza) se e solo se $\exists k \in N     \forall M' \in R(P/T, M) \forall p \in P M'(p) \leq k$. Se riesco a porre un limite (un numero finito) pari per cui sono sicuro che qualunque cosa succeda non c'è un posto che ha più di quei gettoni allora posso dire con sicurezza che la mia rete è limitata, c'è un numero di configurazioni limitate data dalla combinazione dei vari stati. Se viceversa non riesco a garantire questa caratterestica, significa che c'è almeno un posto in cui posso far crescere in modo non limitato il numero di gettoni. Non riesco a trovare un upper bound.

Esempio:

< img >

Ci sono due marcature raggiungibili, quella attuale e quella con 0 gettoni in P1 e un gettone in P0. Poi si raggiunge il deadlock (un blocco).

Se a questa rete aggiungo un altra freccia, la rete diventa illimitata. Quando scatta T0 in P1 viene consumato e generato un gettone e in P0 viene generato un gettone. T0 può scattare sempre aggiungendo un gettone in P0.

Togliendo P0 la rete ritorna limitata, avrei una sola marcatura con un gettone in P1, T0 può scattare infinite volte ma rimane sempre nello stesso stato. Scollegare infiniti eventi da infinite situazioni diverse.

## Reti di Petri $\rightarrow$ Automi a stati finiti

Dato un automa riesco sempre a ottenere una rete di petri ma vale il contrario?
- se la rete è limitata allora l'insieme di raggiungibilità è finito perchè ci sarà un numero di marcature raggiungibili enumerabile, allora è possibile definire un automa a stati finiti che prende ognuno di questi stati raggiungibili come un suo stato e traccia le transizioni di stato dell'automa così come sono conseguenti a qual'è la transizione che è scattata nella rete di petri. Gli stati sono le possibili marcature che fanno parte dell'insieme di raggiungibilità, le transizioni corrispondono agli eventi che vanno a portare da una certa configurazione alla successiva.

Questo è interessante perchè c'è molta teoria che è stata fatta sugli automi a stati finiti, cose che si sono imparate, come anlizzare, ecc sono dei bei risultati, se posso pensare di scrivere una rete di petri e far generare automaticamente l'albero di raggiungibilità che corrisponde ad una macchina a stati finiti su cui poi faccio l'analisi vuol dire che mantengo un modo per scrivere le cose più sintetico e poi lo do in pasto ad un tool di analisi che sfrutta le cose che già essitono per gli automi.

## Vitalità di una transizione

Una transizione $t$ in una marcatura $M$ è detta viva con un certo grado se:
- grado 0: non è abilitata in nessuna marcatura appartenente all'insieme di raggiungibilità a partire da $M$, qualunque cosa accada alla mia rete, qualunque evoluzione vada a fare quella transizione non sarà mai abilitata (è **morta**). Se la transizione era "esplode la centrale nucleare" mi va bene che sia di grado 0. QUalunque cosa succeda non esplode la centrale nucleare. La morte non è sempre negativa.
- grado 1: esiste almeno una marcatura raggiungibile in cui è abilitata (potrebbe anche essere quella iniziale).
- grado 2: per ogni numero $n$ naturale esiste almeno una sequenza ammissibile in cui la transizione scatta almeno n volte. Posso farla scattare un numero grande a piacere di volte.
- grado 3: esiste una sequenza di scatti ammissibile in cui scatta infinite volte. Se il prof ci chiede un caso in cui è grande a piacere ma non infinito avremmo qualche difficoltà. SIIIII!!!!
- grado 4: in qualunque marcatura raggiungibile esiste una sequenza ammissibile in cui scatta (è **viva**).

Una rete è viva se tutte le sue transizioni sono vive (di grado 4).

Esempio:

< img >

T0 è morta.

< img >

T0 è ancora morta e T1 è viva di grado 1.

< img >

Se la guido posso far scattare T3 all'infinito ma se scatta prima T1 non posso più farlo (o comunque se scatta abbastnza volte da svuotare P0). Può scattare infinite volte ma non sempre, T3 è viva di grado 3.

< img >

T2 è di grado 2 perchè: numero illimitato ma non infinito. 
Non possono coesistere faccio scattare T2 e genero inifiniti gettoni però se voglio farla scattare un milione di volte faccio scattare un milione di volte T3, faccio scattare una volta T1 e poi un milione di volte T2.

< img >

T4 è di grado 4 (è viva) qualunque sia la marcatura io riesco a guidarla (non vuol dire "non esistono infinite sequenze in cui non scatta" perchè T3 può scattare infinite volte e quindi T4 non scatta). Per ogni marcatura raggiungibile da quella corrente prendo il controllo ed esiste una sequenza in cui T4 scatta infinite volte.

## Capacità dei posti

Ci sono vari dialetti, uno molto comune è quello in cui oltre ai pesi sugli archi c'è anche una capacità massima legata ai posti (in quel posto non possono esserci più di K gettoni, K può essere diverso per ogni posto). Zone critiche, zone in cui bisogna escludersi a vicenda: c'è una situazione di K-compatibilità per cui posso avere K lettori contemporaneamente ma non più di K. Con una capacità si forza la limitatezza della rete (prendo il massimo delle capacità dei posti e senz'altro non c'è nessun posto che può avere più di quei gettoni). Posso anche non mettere la capacità in alcuni posti e allora la rete può essere ancora illimitata.

Le transizioni sono abilitate se ci sono sufficienti gettoni in ingresso ma anche se ci sono sufficientemente pochi gettoni in uscita in modo che l'aggiunta di gettoni che farà non fa superare la capacità del posto.

È un estensione propria (che ci serve, che aumenta la potenza esprevvia del linguaggio) o una cosa di cui tutto sommato posso anche farne a meno (si sopravvive anche senza le reti di petri, si intende: riesco a dire le stesse cose con poche complicazioni in più o addirittura trasformazioni automatiche, usando le reti senza capacità dei posti come se avessi la capacità dei posti). Se faccio una rete con capacità dei posti posso trasformarla automaticamente in una rete senza capacità dei posti esplicita? NO, per lo meno non sempre. Si può fare senza ma complica un po.

Devo avere qualcosa che sia nel preset della transizione perchè l'unica cosa che può influenzare l'abiltazione di una transizione è il preset, significa che bisogna aggiungere un **posto complementare**: è un posto che ha in uscita le transizioni del preset del mio posto, un arco dello stesso peso ma in direzione opposta e viceversa le transizioni che sono collegate in uscita dal posto devono essere nel preset del posto complementare.

< img >

Ogni volta che si cerca di mettere un gettone in P0 ci dev'essere almeno un gettone nel posto complementare, ogni volta che tolgo un gettone da P0 lo metto nel posto complementare. Così facendo il numero di gettoni P0+P0comp è costante. Se faccio in modo che la somma sia pari alla capacità del posto ottengo che è uguale alla rete con capacità.

Avendo costruito un posto complementare con queste caratteristiche mi va a gestire le cose più o meno correttamente. Questo vale solo per le reti pure (quelle che hanno per ogni transizione preset e postset disgiunti).

Un posto $pc$ è complementare di $p$ se e solo se:
- formula: per ogni transizione collegata al posto in uscita (flusso da p a ???) allora deve esistere un flusso che va al posto complementare di eugual peso. $\forall t \in T (\exists <p,t> \in F \leftrightarrow \exists <t,pc> \in F    W(<p,t>) = W(<t,pc>))$
- formula: stessa cosa in ingresso. $\forall t \in T (\exists <p,t> \in F \leftrightarrow \exists <pc,t> \in F    W(<pc,t>) = W(<t,p>))$

Definizione di abilitazione in reti con capacità sui posti:
$t \in T$ è abilitata in $M$ se e solo se:
- $\forall p \in Pre(t)    M(p) \geq W(<p,t>)$
- $\forall p \in Post(t) - Pre(t)     M(p) + W(<t,p>) \leq C(p)$
- $\forall p \in Post(t) \cap Pre(t)     M(p) - W(<p,t>) + W(<t,p>) \leq C(p)$

< img pipe prof >

SX: abilitata perchè metto 2 e tolgo 1 = metto 1 OK
DX: mettendo 2 superi il limite disabilitata (anche se poi togli 1)

Con il posto complementare non si riesce a tener conto di questo effetto (è come aver spezzato in due momenti l'esecuzione dello scatto: prima tolgo i gettoni e poi li rimetto). Invece nell'altro valuto tutto nello stesso istante.

Allora sono capace di generare l'equivalente di una marcatura di una rete con capacità usando una rete posti-transizioni normale? Non sono capace di farlo solo con il posto complementare, vale per le reti pure, quando un posto è collegato sia in ingresso che in uscita alla stessa transizione non riesco. Possiamo affrontare questo problema in due modi:
1. cercare di dimostrare che possiamo avere un altro approccio al posto del posto complementare per risolvere il problema
1. possiamo dimostrare che le reti non pure possono sempre essere tradotte in reti pure (senza "loop" sia in che out). Basta aggiungere una transizione e un posto rimuovendo il loop.

Nella teoria è banale, creo la rete pura equivalente e aggiungo i posti complementari, ma nella pratica non è cosi semplice come sembra. Allungare i cicli crea marcature intermedie in cui sono ammissibili certe evoluzioni che non sarebbero possibili in quella base, non trovo più lo stato esattamente uguale, ci sono marcature non equivalenti.

Comunque è sempre possibile trovare una traduzione a reti con capacità in una senza capacità dei posti.

# Altra estensione: archi inibitori

Al prof non piace molto ma in alcuni casi è molto comoda. Dice che in un posto non ci devono essere gettoni affinchè la transizione sia abilitata (o che devono essere meno di X se c'è il peso).

< img >

$T0$ per essere abilitata ci dev'essere almeno un gettone in $P0$ e non ci devono essere gettoni in $P1$.

In caso di rete limitata (sappiamo che ha un massimo di gettoni) non cambia la potenza perchè possiamo fissare un K per ogni posto che non verrà mai superato allora posso costruitre un posto complementare per ogni posto e dire "guarda che affinhè t0 posso scattare basta che ci sia un arco di ingresso/uscita di K gettoni dal posto complementare, tutti i gettoni possibili devono essere nel posto complementare, quindi non ce n'è neanche uno nel posto P0. Posto P1 in ingresso T0 con un arco inibiiore: se sappiamo a priori che la rete è limitata o addirittura che quel posto è limitabile (non perchè gli mettiamo una capacità ma per come evolve non ci saranno mai essere più di K gettoni, creo un posto complementare di P1 e facciamo in modo che nella marcatura iniziale i gettoni in P1c siano K - gettoni in P1 e traduciamo l'arco inibitore facendo in modo che tutti i 10 gettoni devono essere nel posto complementare: un arco di peso K che mette e toglie). Se l'arco inibitore ha peso X allora basta che il posto complemenatre abbia K-X gettoni.

Questo posso farlo se quel posto è limitato. In caso di rete non limitata invece aumenta la potenza espressiva, non è detto che ci sia una traduzione equivalente.

Al prof non piace perchè alcune tecniche di analisi che vedremo non sono così facilmente estendibili considerando anche l'arco inibitore, certe tecniche di analisi che vedremo non funzionano perchè imponiamo "meno di tot gettoni".

# Altro: eliminazione dei pesi archi

Nella teoria si dice che per ogni rete posti-transizioni con pesi sugli archi ne esiste una senza pesi sugli archi.
Può diventare difficile fare questa conversione.

Dobbiamo considerare due casi:
1. peso in uscita da una transizione
1. peso in ingresso in una transizione

Faccio sparire il peso (ma non basta) ora siccome una rete senza pesi sugli archi ogni scatto può creare al massimo un gettone, l'unico modo per mettere un secondo gettone è lo scatto di un altra transizione, bisogna aggiungere una parte di rete:

< img >

Un posto ed una transizione tale per cui quando scatta T0 non solo genra un gettone in P1 ma anche uno in P0bis che farà scattare una transizione T0bis che crea il secondo gettone. Anche in questo caso c'è il problema che aggiunge marcature intermedie , il resto della rete può evolvere senza che scatti T0bis (che potrebbe non scattare mai).

Per risolvere dovremmo creare un posto globale collegato in ingresso/uscita a tutte le transizioni della rete con pesi e poi la mettiamo in ingresso a T0 e in uscita a T0bis si crea un lock sulla possibilità di scatto. C'è un gettone nel posto globale che viene tolto da T0 e rimesso da T0bis (si rende lo scatto delle transizioni T0 e T0bis atomico rispetto al resto della rete). Se scatta T0 la rete è bloccata finchè non scatta T0bis.
Formalemnte ho ancora la località però è brutto.

Nel caso in cui il peso sia in ingresso bisogna sempre creare un posto e una transizione per ogni gettone in più da rimuovere. Va bene ma c'è lo stesso problema di concorrenza, serve ancora un posto globale che renda atomica l'operazione rispetto al resto della rete. Problema: se ho un solo gettone in P8 può scattare T8 che blocca la rete, T1 non può scattare e non posso mettere un altro gettone in P0, sono in deadlock. Dobbiamo fare in modo che T8 sia abilitato solo se ci sono due gettoni. Creiamo un posto P0bis e diciamo che T8 saràa bilitata solo se un altra parte della rete ha calcolato che ci sono almeno due gettoni. È un casino! La rete diventa enorme!

Riuscirei a farlo anche senza pesi ma è talmente complicato che viene più facile pensarla in altra maniera. Non ho aumentato la potenza di quello che voglio rappresentare ma è difficile trovare una traduzione automatica.

# Reti condizioni eventi (C/E)

C'è chi odia talmente tanto i pesi che ha creato le reti condizioni eventi. Sono molto più semplici perchè tutti gli archi hanno peso 1 e come ulteriore semplificazione tutti i posti hanno capacità 1, in questo modo ogni posto è come una variabile booleana (condizioni), gli eventi esprimono cosa può accadere quando sono vere certe tuple di condizioni. Mette dei limiti ma è più chiaro da capire, ogni rete posti transizioni si può tradurre in una rete condizioni eventi (con più posti e più transizioni).

Le reti condizioni eventi sono limitate per definizione (tutti i posti hanno capacità 1) quindi non si può tradurre una rete P/T illimitata in una rete C/E.

Piccolo salto al capitolo dopo :D

# Conservatività

È relativa di una certa funzione di peso, per ogni posto della nostra rete definiamo un peso e questa funzione che assegna i pesi ai vari posti (tutti != 0, tutti positivi, naturali). Una rete si dice conservativa rispetto a questa funzione se e solo se per ogni marcatura M' raggiungibile dalla marcatura iniziale, data una certa marcatura e data una certa funzione di assegnamento dei pesi se per ogni marcatura raggiungibile la somma pesata (rispetto a questa funzione) dei gettoni nei posti è costante per qualunque marcatura raggiungibile.
$$\forall M' \in R(P/T, M) \sum_{p \in P} H(p) M'(p) = \sum_{p \in P} H(p) M(p)$$

C'è un legame con la limitatezza: una rete conservativa è sicuramente limitata ma non è detto il contrario. Se esiste una funzione di questo genere posso dire che è limitata, la limitatezza è necessaria per la conservatività ma non è sufficiente.

Caso speciale di conservatività è la rete strettamente conservativa: assegna pesi tutti uguali a 1, il numero totale di gettoni nella rete non cambia mai.
$$\forall M' \in R(P/T, M) \sum_{p \in P} M'(p) = \sum_{p \in P} M(p)$$

Si può formulare in un altro modo: ogni transizione deve distruggere e creare lo stesso numero di gettoni.
$$\forall t \in T \sum_{p \in Pre(t)} W(<p,t>) = \sum_{p \in Post(t)} W(<t,p>)$$

Una ragiona sugli stati, l'altra sui pesi. La prima è analisi dinamica, la seconda analisi statica. Per analizzare la prima devo calcolare tutti gli stati raggiungibili, la seconda basta guardare topologicamente il numero limitato di archi e sommo i pesi e ottengo (prof si interrompe) Possibile che sia equivalente? Siamo sicuri?

Spesso quando facciamo le cose statiche riusciamo a farle molto più efficientemente però ci perdiamo i valori effettivi dei gettoni, i valori della rete dei dati, ecc...
Ci perdiamo qualcosa o abbiamo mantenuto tutto?

Una a paritre dalla marcatura M, l'altro per tutte, vuol dire che questa è ancora più potente giusto? Piu generale. Però se è piu generale vuol dire che mangari qui c'è una versione più specifica che qui non vale. Magari ci può essere che è conservativa rispetto a una funzione in una certa marcatura iniziale ma non lo è per tutte. Siamo d'accordo? Non abbiamo ancora trovato la differenza ma siamo molto vicini.

Siamo sicuri che abbiamo bisogno che per tutte le transizioni valga questo? Beh si perchè se le transaizione toglie un numero di gettoni diverso da quello che genera (parole incomprensibili). Le transizioni devono essere almeno vive di grado 1, se sono morte non potranno mai scattare e non ci importano, se non potranno mai scattare possono anche avere pesi che +1 -3 non importa non può scattare MAI.
$\forall t$ non morta $\in T \displaystyle\sum_{p \in Pre(t)} W(<p,t>) = \sum_{p \in Post(t)} W(<t,p>)$

Caso limite: qualunque rete è strettamente conservativa per una certa funzione, quella in cui la funzione marcatura iniziale assegna 0 gettoni a tutto. In realtà no! Ci potrebbero essere transizioni che non hanno posti in ingresso.

# Stato base e rete reversibile

Una marcatura M' viene detta **stato base** (home state) se per ogni marcatura M in $R(M_0)$, M' è raggiungibile da M. È uno stato che indipendentemente dallo stato della rete è raggiungibile.
Una rete di petri è detta **reversibile** se per ogni marcatura M in $R(M_0)$, $M_0$ è raggiungibile da M. Cioè se si può sempre riportare nel suo stato iniziale (lo stato iniziale è uno stato base).