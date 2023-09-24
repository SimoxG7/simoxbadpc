# UML

## **Digramma Use case**

Deriva da jacopsen + fuffoso piu dialogante.
Parlare con i clienti è un arte che va imparata e coltivata, parlando con i clienti le cose formali sono difficili da usare, bisogna essere piu informali.

Uno use case è un astrazione di un insieme di scenari (scenario = circa una user story di xp). Se faccio questo in questa situazione accade questa cosa (descrivo un interazione). Lo use case invece è mettere insieme gli scenari che sono tra di loro correlati, le situazioni in cui può capitare di chiedere di svolgere un certo compito con esisti diversi (fallimenti e successi) (Es: chiedere in prestito un libro può fallire perchè hai gia il numero massimo di libri, perchè è già in prestito, libro troppo usurato, ... tutto ricade nello use case "voglio prendere in prestito un libro" ma poi ci sono i vari scenari che spiegano cosa può accadere nelle diverse situazioni).

Il digramma degli use case rappresenterà tutte le interazioni in un unico diagramma (livello d'astrazione molto alto), infatti ognuno degli usecase (insieme di possibili scenari) è una bolla con un nome, collegato ad ogni bolla abbiamo delle infromazioni con precondizioni postocndizioni flusso normale di esecuzione, flussi eccezionali, aree di testo in cui posso scrivere testo libero che il cliente legge e aiuta a scrivere. Modo piu strutturato di suddividere un documento di specifiche.

UP (modello sictato prima tagliato) si definisce USe case driven, come xp guidato da user stories.
A differenza di xp in UP gli use case cambiano ed evolvono nel tempo. se nei primi istanti dialogo con il cliente e l'use case interazione con il sistema (scatola nera al cliente non interessa conoscere l'interno) man mano che questi use case passano avanti fasi vengono modificati con la parte di sistema, componente, li si dettaglia di piu perchè l'audience che li utilizza a quel punto è cambiata, diventa parte di sviluppo e test.

Flessibilità del linguaggio naturale permette a seconda della fase del processo di sviluppo in cui siamo di tarare anche il tipo di linguaggio sulle persone che stanno leggendo queste cose.

Questi digrammi sono correlati l'uno con l'altro, in UML 1.0 era un accrocchio di diagrammi (troppo scollegati che potevano dire cose in contrasto, nessun controllo di consistenza tra i vari diagrammi) cosa migliorata con maggior formalizzazione in UML 2.0.

Questi digrammi vengono usati assieme per descrivere le loro funzionalità, cose piu interessanti, se il flusso normale viene spiegato a livello testuale nel momento in cui coinvolge 10 componenti ed è complicato posso aggiungere un sequence diagram che me ne spieghi meglio piu formalmente il comportamento. Nulla vieta di usare un diagramma piu adatto piu espressivo se ne sento la necessità.

### scenari

Il prof ha saltato

### Use cases

omino
ovale
linea che li collega: indica che l'attore partecipa allo use case, può partecipare, non è detto che in tutti gli scenari compaia, potrebbero essere solo alcuni.

- Attori: il simbolo omino fa pensare che siano persone ma in relatà non devono per forza essere persone, le cfaratteristiche che deve soddisfare qualcosa per essere considerato un attore all'interno di un diagramma degli use case è che sia **esterno al sistema** (se è un componente del sistema è un componente, non un attore) **interagisce *direttamente* con il sistema** (è fonte e/o destinatario di informazioni) **non è detto che sia una persona** (è un ruolo di una persona).

ALcuni attori hanno caratteristiche piu importanti da sottolineare, in particolare l'**attore beneficiario** (colui che tra beneficio da questa interazione che stiamo facendo, per lui è importante che ci sia questa funzionalità, poi ci sono altri al servizio di questa persona per fornire quella funzionalità). È importante distinguere queste due cose perchè il modo con cui implementiamo la funzionalità può cambiare nel tempo, chi è interessato alla funzionalità rimane (punto fisso). Il beneficiario rimane gli altri potrebbero cambiare nel tempo.

### identificazione use cases

Posso partire dalle funzionalità del sistema, le specifiche scritte in qulahe maniera o raccogliere direttamente in qyesto modo, c'è qualcuno che me le dice, il cliente, chi è il cliente? il beneficiario.

Man mano che qualuno è interessato ad una funzionalità me la proporrà come richiesta, partire dai beneficiari quando ho già le specifiche scritte è un coinvolgere le persone adatte, cosa fanno, cosa vogliono fare, ....

**Attore primario** non è detto sia coincidente con il beneficiario, a volte si, è quello che da il via all'interazione, la funzionalità. Spesso nei diagrammi di sequenza nella prima colonna è un attore.

- Associazioni: affinchè uno schema abbia senso ogni use case deve essere collegato almeno ad un attore, per ogni attore deve esserci almeno uno use case a cui partecipa. Poi ci possono essere relazioni tra diverse use case:
    - Inclusione: uno use case può essere incluso in altri usecase (serve a fattorizzare delle parti per non ripeterle) e può essere dovuto al riuso di un componente che esisteva già quindi ho gia specificato cosa faceva come use case, può essere perchè lo ricavo dopo aver scritto diversi use case (autenticazione inclusa come primo pezzettino di tanti altri use case), chi include sa chi è incluso, chi è incluso non sa da chi è incluso, la perte fattorizzata può essere riutilizzata da tanti altri.
    L'esecuzione di una parte inclusa non è facoltativa, se lo includo vuol dire che c'è.
    - Extend: piu simile al polimorfismo, dentro alle use case base prevendo dei punti di estensione che posso ridefinire. In quel punto potresti aggiungere qualcosa, aggiungo questo comportamento (es: non sono riuscito a fare una certa cosa, fallimento dovuto a qualcosa di particolare).
    La parte base conosce che ci potrà essere qualcosa ma non sa esattamente cosa.
    - Generalizzazione: permette di esplicitare relazioni tra ruoli, tra attori non cosi utile. Tra use case invece è molto simile ad extends ma meno pulita,  

        racconto prof tool grafici vecchi: doppio clic su una bolla e venivano fuori 4 aree di testo, situazione iniziale, svolgimento normale, eccezioni, postcondizioni.

    estensione vuol dire eredito tutte e 4 a meno che non ci riscriva dentro, 3/4 andavano bene le lascio vuote e una la riscrivo.
    Dire che nel testo normale posso cancellare tutto e sostituire qualcos'altro è una responsabilità grossa da dare, è una trasformazione che non gafrantisce le proprietà di quello sopra, è solo implementativa per non riscrivere qualche frase, poi la coerenza è difficile. Motivo per cui è stato tolto.

## Esempi (spostare sopra)

< Img > prestito libro o estensione prestito
< Img > estensione

## **Activity diagram**

Simile al diagramma degli stati (ha gli stessi elementi sintattici) ma gli stati vengono chiamati **attività**, ci sono delle box quando ho finito l'attività esco, non esco quando accade qualcosa dall'esterno ma quando accade qualcosa dall'interno (finisco attività). Le transizioni erano etichettate con gli eventi ora non sono quasi mai etichettate perchè sono implicitamente quando ho finito attività e passo alla successiva. Difficilmente ci sono dei momenti di fermo, di solito non mi fermo mai, magari mi fermo se devo sincronizzarmi con qualun altro (ci sono ancora le barre di sincronizzazione). Le azioni invece di essere sugli archi sono interne agli stati stessi, le attività.

Particolarità: può parlare di cose esterne al sistema, fa da collante tra diversi use case. Dopo aver fatto qualcosa posso farne un altra. I blocchi di sincronizzazione e la concorrenza non sono un eccezione.

I livelli d'astrazione a cui possiamo gestire l'activity diagram sono molto diversi tra loro:
- Logica di processo del business model: prendo la cosa piu generale, piu manageriale e visto che ho il testo libero risco a far vedere un evoluzione che deve essere parallela e concorrente perchè coinvolgerà diverse persone e sistemi, è ovvio che non può essere tralasciato l'aspetto di concorrenza. Potrei anche dire che prendo diversi flussi use case e li chiarisco meglio in cui magari la concorrenza non è importante quindi ricavare un sequence diagram è piu complicato.
Posso portarlo a livello di metodo (una singola operazione).

FORK/JOIN delle attività

Quel box ci dice che quando ho finito l'attività 1 posso partire in parallelo (senza specificare l'ordine ) att2 e att3, poi la riunione, quando entrambe hanno completato il loro compito si può andare avanti. Potrei porre vincoli (2/3, or, ...) su quali sono i criteri di soddisfacimento della barra di sincronizzazione.

Un altra cosa che c'è sono le decisioni (condizione), 1 mutua eslcusione tra i diversi archi e 2 l'unione dev'essero vero. Dovrebbe essere una decisione umana (non il sistema se sono in questo stato faccio questo altrimento altro) un momento di vera decisione che non si sa quale scelta si farà. Momento di decisione non di valutazione di una condizione.

Swim lane

Simile agli superstati ma concettualemtne dierso, ogni corsia correisponde ad un ruolo, responsabilità, attore (anche esterno al sistema) sono peculiari gli actitivity diagram permettono di partizionare uno shcema per dire quall'attività da chi viene svolta

< img swim >

# COmponent diagram

Componenti sono componenti fisici che si contrappongono ai raggruppamenti logici fatti dai package, il package in UML esiste ed è un costrutto utilizzabile in qualunque diagramma per raggruppare, astrarre e dare un nome ad un sottoinsieme del digramma stesso. QUando ragioniamo sui componenti, tipologie di componenti (file, libraries, codice eseguibile, table database, documenti in linguaggio natutrale) e penso che dentro il mio sistema ci sono delle parti aderenti ad una certa (che implementano una certa libreria/un certo eseguibile) e voglio iniziare a pensare a come si relazionano queste parti ho bisogno di un diagramma a parte, non sono più classi, ognuno di questi componenti è implementato da varie classi.
< img >
Simbolo dice scatola è componente e questo crea linea con pallino è un modo per identificare il fatto che la libreria implementa una certa interfaccia, classicamente se le interfacce le definisco io le inserisco come box perchè è qualcosa di non noto di cui devo parlare, se interfaccia di libreria è inutile dedicargli una scatola apposta (moltissime classi dello schema implementeranno quell'interfaccia). Quello che si fa si da solo un punto di aggancio dopodichè se un componente interagisce con un altro sfruttandolo e conoscendo solo una certa interfaccia esplicito questa situazione dicendo "l'eseguibile usa il componente 1 limitatamente alle funzioni fornite con l'interfaccia 2" (piu specifico di dire "usa il componente"). Quello che accade è che potrebbe essere un pattern facade, un modo abbastanza chiaro per implementarlo. COmponente che ha all'interno una serie di classi ma che esporta verso l'esterno solo un certo numero di interfacce. Può essere una cosa molto complessa all'interno ma di cui fornisco interfacce semplificate.

Sono tipologie di componenti che il sistema avrà, quello che è tra parentesi angolari UML lo chiama stereotipo (interfaccia è uno stereotipo del concetto di classe, è una classe con alcune caratteristiche sintattiche e semantiche particolari, non può avere implementazioni non puo essere istanziata,...) un componente di tipologia library non ha un main eseguibile ma una serie di funzioni che possono essere invocate. Dico qualcosa sulle caratteristiche di alto livello del componente.

Quali sono le caratteristiche di un componente? Quando identifico un componente? Quali sono le cose che deve garantire?

La cosa piu importante dal punto di vista 
La cosa fondamentale che deve avere un componente rispetto alla classe deve essere qualcosa di riumpiazzabile/sostituibile A LIVELLO DI INSTALLAZIONE. Se ho una parte del sistema che può avere un versionamento separato è un componente (può essere rimpiazzato sull installazione solo una parte del sistema). Hanno un deployment separato dal resto del sistema.

Seconda cosa: deve svolgere una funzione ben determinata. Può esserci una gerarchia di componenti, un componente che raggruppa tanti componenti e dire posso darti la versione globale (fatta da 10 componenti) o posso sostituire un singolo componente al suo interno. Prendo questo macrocomponente (contenitore di componenti) che è un componente a sua volta perchè può esportare delle interfacce diverse da quelle che vengono fornite internamente. Bisogna indicare chiaramente quali interfacce utilizza e le relazioni di dipendenza e composizione con gli altri componenti. RICORDA: TIPI DI COMPONENTI.

# Diagramma di deployment

Parla di istanze di componenti, magari il componente server web faccio 20 istanze su 20 macchine per fare load balancing. Parlando di componenti era 1 qua invece sono interessato a come disloco i componenti (eventualmente ennuplicati) sulle varie macchine. È un diagframma che serve all'installatore che fa il deployment vero e proprio del programma sulle macchine del cliente, quindi ha bisogno di una visione. I nodi del sistema (macchine fisiche o virtuali e cosa c'è sul cloud invece di una macchina fisica).

C'è un cubo che da l'idea di qualcosa di fisico o nuvoletta quando è su cloud, posso specificare nei nodi quali istanze dei componenti che metto, come dialogano i vari nodi e le dipendenze tra i componenti. Questo però ha più particolari, dice molte piu cose. Rispetto a prima si eliminano le interfacce (sarebbero troppo duplicate + distraente che utile).

# Ci sono altri diagrammi

Time diagram = da una caratterizzazione temporale, segnali nel tempo, sistemi realtime in cui il tempo è fondamentale. Ha delle criticità difficilmente simulabili, fare test su cose isolate ok ma poi devo anche dire doveva rispondere in 10ns su una macchina con un certo carico ... questo è molto piu difficile da controllare, ogni volta che ispeziono lo stato cambiano i tempi, se prima aggiungo una funzione in test che serve per ispezionare lo stato tanto so che non cambia il comportamento delle altre giunge una chimata ad una funzione che in produzione non ci sarà mi cambia tutta la tempistica. Sono sistemi molto piu difficili, questi diagrammi appositi indicano com'è l'andamento temporale di alcune funzioni che ci serve per ragionare su quello.

Molti altri