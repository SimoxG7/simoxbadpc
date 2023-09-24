# **Domande e risposte prova orale**

## **1. Quali sono le qualità del software?**

Le qualità del software sono caratterisitche che comportano valore per le persone (sia il cliente che lo sviluppatore). Per essere definito di qualità un software deve:
- **funzionare**: ovvero essere *corretto* (aderisce alle specifiche, quello che crediamo il cliente voglia), *affidabile* (aderisce ai requisiti, quello che il cliente vuole) e avere un comportamento sicuro e robusto a fronte di situazioni non espressamente preventivate.
- **essere bello**: ovvero essere facilmente utilizzabile (l'usabilità è misurabile con esperimenti controllati), essere veloce (efficiente nell'uso delle risorse) ed essere evolvibile.
- **farmi diventare ricco**: nello specifico non spendere per rifare cose già fatte (risusabilità dei componenti) e semplificando gli interventi di manutenzione post-consegna.

## **2. Parlare del ciclo di vita del software.**

Il ciclo di vita del software è composto da varie fasi che prevedono l'identificazione delle attività necessarie, i soggetti coinvolti, le precedenze temporali e le varie durate. Generalmente si può suddividere in 6 fasi:
1. **Studio di fattibilità**: si cerca di capire se il progetto è realizzabile e quali concorrenti ci sono, quindi si studiano diversi scenari per individuare quello più conveniente. Queste analisi sono generalmente commissionate all'esterno perchè sono complesse e non possono richiedere troppo tempo. Come risultato si ottiene un documento per i manager che serve per capire se ha senso avviare il progetto e in che modo procedere.
1. **Analisi e specifica dei requisiti**: si cerca di comprendere il dominio applicativo e di identificare gli stakeholders. In questa fase bisogna capire il COSA viene chiesto e non il COME realizzarlo. Come risultato vengono prodotti dei documenti di specifica scritti in linguaggio formale che fungono da base conrattuale, ed eventualmente anche il manuale utente e i test d'accettazione.
1. **Progettazione**: si cerca di capire come realizzare le specifiche nel modo più opportuno (si definisce l'architettura del sistema, la scomposizione in moduli/oggetti e si identificano i pattern da utilizzare).
1. **Programmazione e test d'unità**: i moduli/oggetti definiti precedentemente vengono realizzati, poi si controlla individualmente la loro correttezza con dei test. Come risultato si ottengono dei moduli verificati singolarmente che usano le interfacce di comunicazione concordate.
1. **Integrazione e test di sistema**: tutti i componenti vengono uniti progressivamente effettuando dei test d'integrazione.
1. **Manutenzione**: si effettuano degli interventi post-consegna che permettono di ottenere un prodotto migliore. Questi possono essere correttivi (correggono degli errori), adattativi (adattano una o più funzionalità alle esigenze più recenti) o perfettivi (rendono il codice più leggibile e/o più efficiente).

## **3. Da dove arrivano e chi scrive le specifiche? Dopo averle raccolte cosa devi fare prima di implementarle?**

In un modello "classico" le specifiche vengono realizzate a partire dalle spiegazioni che danno i clienti o gli esperti del dominio applicativo a cui si rivolge. Prima d'implementarle bisogna progettare il sistema, scomporre in moduli/classi ed identificare i pattern più opportuni.

## **4. Come nasce il modello a cascata? Parlare dei suoi aspetti positivi.**

Il modello a cascata nacque alla fine degli anni '50 ma acquisì notorietà solo negli anni '70 grazie ad un articolo scientifico a cura del Dr. Winston W. Royce, un progettista che lavorava nell'industria aerospaziale. Anche se molte persone hanno erroneamente interpretato le sue parole, in quest'articolo diceva di credere nel concetto di processo di sviluppo sequenziale ma riconobbe la necessità di poter iterare tra le varie fasi, anzi diede lui stesso 5 raccomandazioni "iterative", la più citata di queste è quella che dice di realizzare un prototipo (Do It Twice).

Nel modello a cascata "classico" le fasi sono in sequenza e bisogna obbligatoriamente seguire una progressione lineare da una fase alla successiva, senza possibilità di tornare indietro. Le varie fasi comunicano attraverso dei semilavorati (è un modello document based), questo permette di **separare i compiti** (gli addetti ad una certa fase possono dedicarsi ad un altro progetto mentre avvengono quelle precedenti) e di **evitare fraintendimenti** (è tutto scritto nei documenti). Inoltre, dato che non è possibile tornare ad una fase precedente si possono **pianificare i tempi** e **monitorare lo stato di avanzamento**. La variante con singola retroazione permette di tornare indietro di una fase nel caso in cui si accorga di un errore.

## **Parlare del modello a spirale.**

Il modello a spirale è stato ideato da Boehm nel 1988. È un modello basato sui **rischi**, l'attenzione viene posta su quello che può andar male, nella realtà i progetti falliscono o possono essere interrotti. Ad ogni "giro" deglla spirale abbiamo un momento di decisione in cui si cerca di capire quali rischi si presenteranno in futuro, se è possibile superarli ed eventualmente a valutare le possibili alternative. La spirale che si allarga sempre di più simboleggia l'aumentare del costo complessivo.

Successivamente è nato il modello a spirale win-win, questo modello mette in evidenza il fatto che il rapporto tra lo sviluppatore ed il cliente non è pacifico, si hanno obiettivi contrastanti: gli sviluppatori vorrebbero essere pagati tanto per fare poco e il cliente vorrebbe pagare poco per avere tanto. Ad ogni iterazione bisogna trovare un punto d'equilibrio (win-win) in cui entrambe le parti vincono o pensano d'aver vinto, altrimenti lo sviluppatore lavorerà male (perchè pagato poco) o il cliente non commissionerà ulteriori lavori (perchè costano troppo). Questo contrasto tra le parti è una delle principali fonti di fallimento, senza considerare il fatto che anche il cliente può avere divergenze interne.

## **Parlare dei modelli iterativi, dei modelli sequenziali e dei modelli prototipali.**

Nei **modelli iterativi non incrementali** si raccolgono tutte le specifiche, si progetta l'intero sistema, lo si sviluppa iterativamente, si integrano tutti i componenti per ottenere il sistema finale ed infine lo si consegna.

Nei **modelli incrementali** invece l'iterazione viene estesa a tutte le fasi, anche alla raccolta dei requisiti. Questo porta a due possibilità:
- si raccolgono pochi requisiti, li si realizzano, si consegna e se ne chiedono altri.
- si raccolgono subito tutti i requisiti e si stabilisce un ordine di priorità in modo da realizzare prima quelli più importanti. Al termine di ogni consegna si permette al cliente di rinegoziare le richieste sia aggiungendo che togliendo funzionalità.

Integrare la variabilità delle richieste del cliente all'interno del processo di sviluppo lo rende più robusto rispetto a questo imprevisto. La manutenzione come fase a se stante sparisce perchè viene integrata nell'iterazione successiva. Di contro diventa più complicato pianificare e monitorare lo stato di avanzamento.

I **modelli prototipali** pervedono la realizzazione di prototipi usa e getta (throwaway) da mostrare al cliente per chiedergli pareri e conferme, se questi vengono validati li si butta via e si ricomincia lo sviluppo. Di contro, avendo realizzato un prototipo senza pensare a farlo bene, non si sà dire quanto sarà faticoso modificarlo per adattarlo a particolari esigenze del cliente che potrebbero sorgere in futuro, si potrebbe introdurre un debito tecnico. I prototipi possono essere:
- **pubblici** o **esterni**: si mostrano al cliente per essere sicuri di aver capito correttamente i requisiti.
- **privati** o **interni**: servono agli sviluppatori per capire se una soluzione architetturale può andare bene (linguaggi, ...).

Un altra possibilità consiste nel realizzare il programma quasi completo per poi buttarlo via e ricominciare da capo (do it twice), questo perchè la progettazione viene considerata un **wicked problem**, ovvero uno di quei problemi che si sanno risolvere bene solo dopo aver provato a risolverlo.

## **Quali regole XP possono essere consigliate ai colleghi?**

Alcune delle regole XP che possono essere consigliate ai colleghi sono:
- Sviluppare usando il TDD in modo da scoprire tempestivamente la presenza di errori e rimediarvi facilmente. Questo permette di aumentare la fiducia che i clienti ripongono nel programma.
- Programmare in coppia (pair programming) permette di migliorare le qualità del codice scritto. Ci si controlla a vicenda per non distrarsi, mentre uno scrive l'altro inizia a pensare alla fase di refactoring, inoltre cambiando ciclicamente le coppie tutti avranno la conoscenza per lavorare su tutto e non ci si blocca se manca una persona.
- Effettuare spesso il refactoring in modo da migliorare la leggibilità e le altre qualità del software.
- Considerare il codice di proprietà collettiva, se tutti si sentono autorizzati a mettere mano a qualunque parte del programma, alla fine questo conterrà meno errori perchè è nell'interesse di tutti ottenere un buon prodotto.
- Usare uno standard di codifica scelto a livello di team in modo da uniformare lo "stile" di scrittura del codice (parentesi a capo, tab di 4 spazi, ...).

## **Perchè un azienda dovrebbe scegliere l'open source?**

Un azienda dovrebbe scegliere di usare programmi open source nel caso in cui il software non sia un prodotto da vendere ma uno strumento che usa (tecnologia abilitante), oppure nel caso in cui il software non sia un fattore differenziante, ovvero non offra alcun vantaggio competitivo. In questi casi è insensato farsi carico dei costi di sviluppo di un proprio sistema software, conviene contribuire ad un progetto open source introducendo le funzionalità che servono e sfruttando il lavoro fatto da altre persone. A volte è anche possibile riuscire ad indirizzare lo sviluppo del progetto in direzioni favorevoli.

## **Parlare del polimorfismo e del collegamento dinamico.**

Il polimorfismo permette di vedere una classe in più modi diversi. Dichiarando una variabile che ha come tipo un interfaccia è possibile assegnargli un istanza di una qualsiasi classe che implementi tale interfaccia. In questo modo si sceglie di ignorare quale sia l'oggetto reale per vederlo solo attraverso le capacità dichiarate dall'interfaccia. Questa caratteristica risulta particolarmente utile quando si creano collezioni e quando si usano i generici.

Il collegamento dinamico permette di verificare qual'è il tipo concreto di una variabile durante l'esecuzione in modo da poter andare a chiamare il metodo dell'oggetto reale invece che quello dell'interfaccia o della superclasse. Il collegamento dinamico potrebbe sembrare una parte del polimorfismo, ma in realtà è una caratteristica separata. Infatti esistono dei linguaggi che hanno il polimorfismo ma che non hanno il collegamento dinamico, oppure linguaggi che permettono di scegliere funzione per funzione se usare il collegamento dinamico o quello statico (ad esempio C++). In Java si è preferito usare sempre il collegamento dinamico al fine di evitare alcuni errori che possono commettere i programmatori.

## **Parlare della chain of responsibility.**

La chain of responsibility permette di definire una catena di potenziali gestori di una richiesta di cui non si sà a priori chi sarà in grado di gestirla effettivamente. Ogni gestore della catena cerca di determinare il risultato, nel caso in cui questo non sia possibile cede il compito al prossimo gestore (delega). Questo pattern permette d'avere un enorme flessibilità a discapito delle prestazioni, prima di trovare il risultato potrebbe essere necessario passare attraverso molte chiamate. Per mitigare questo calo di prestazioni si consiglia di creare catene corte o di effettuare test immediati per capire se la richiesta è gestibile. Un altro vantaggio offerto da questo pattern è la riconfigurabilità della catena durante l'esecuzione, si possono usare algoritmi di apprendimento che modificano la catena per migliorarne l'efficacia. 

## **Cos'è la nullability? Perchè è importante? Quale caratteristica del valore null da fastidio?**

La nullability è una proprietà che hanno alcuni tipi di dato, questa gli permette di assumere il valore particolare NULL oltre a tutti i suoi possibili valori. Il problema è che ogni volta che si vuole usare uno di questi oggetti è necessario controllare che questo non sia NULL, perchè nel caso verrebbe sollevata una NullPointerException. La cosa che da più fastidio è che anche se un valore è NULL, non ci dice il perchè è NULL (potrebbe essere perchè si è verificato un errore, perchè il valore è assente, perchè è un riferimento che temporaneamente non punta a niente, ...). Il Null Object Pattern serve proprio a rimvuovere i test == NULL, consiste nel incapsulare una logica che non fa nulla all'interno di un oggetto del tipo che ci si aspetta di ottenere invece che ritornare il valore NULL (Es: comparator che restiruisce sempre 0).

## **Quando si inserisce un valore scorretto usando il pattern model-view-controller, dove si limita l'inserimento?**

Nel model perchè boh.

## **Parlare della terminologia di base della verifica e convalida.**

La prima distinzione che bisogna fare è tra i termini verifica e convalida:
- La **verifica** è il confronto del software con le specifiche formali scritte dall'analista o dal team in XP (**correttezza**).
- La **convalida** è il confronto del software con i requisiti informali del cliente (**affidabilità**).

Un altra distinzione importante che bisogna fare riguarda le varie accezioni della parola "**errore**":
- **Malfunzionamento** (guasto o failure): è uno scostamento dal comportamento corretto del programma, non venogno rispettate le specifiche. Esempio: la funzione raddoppia(5) restituisce 25.
- **Anomalia** (difetto o fault): è il punto del codice in cui ha origine il malfunzionamento. Se c'è un malfunzionamento allora c'è almeno un anomalia, ma se c'è un anomalia non è detto che ci sia un malfunzionamento (potrebbe annullarsi con altre anomalie). Esempio: nel codice di raddoppia(num) c'è la riga `return num * num`;
- **Sbaglio**: è la causa di un anomalia, è legata ad un essere umano (Esempi: errore di battitura, concettuale, conoscenza del linguaggio, distrazione, ...). Capire l'origine di un errore permette d'intraprendere azioni correttive per fare in modo che non si verifichi più.

## **Fare un esempio di caso in cui si verifica un anomalia ma non un malfunzionamento.**

Un anomalia non causa un malfunzionamento quando il suo effetto viene annullato ad un altra anomalia. Se le funzione raddoppia restituisce il quadrato e la funzione dimezza restituisce la radice, un dato che attraversa entrambe le funzioni risulterà corretto.

## **Cos'è un test ideale?**

Un test $T$ per un programma $P$ che ha come dominio dei dati in ingresso $D$ si dice test ideale se vale $ok(P,T) \rightarrow ok(P,D)$, ovvero se **il superamento del test** (non sono stati trovati malfunzionamenti) **implica la correttezza del programma**. In generale è impossibile trovare un test ideale, un test può rilevare la presenza di malfunzionamenti ma non dimostrarne l'assenza. Per effettuare quest'implicazione bisognerebbe testare ogni singolo valore appartenente a $D$ (test esaustivo, $T = D$), ma questo richiede troppo tempo. I test ideali verrebbero selezionati da un criterio di selezione affidabile e valido.

## **Cos'è un criterio di selezione? Perchè nei test dobbiamo scegliere un sottoinsieme del dominio del programma?**

Un criterio di selezione dà delle regole che permettono di selezionare un sottoinsieme di $D$ da usare come test $T$, nella speranza che questo approssimi il più possibile il test ideale.

È necessario scegliere un sottoinsieme di $D$ perchè eseguire un test per ogni singolo valore appartenente a $D$ richiederebbe troppo tempo, fare il testing esaustivo anche solo sulla somma richiederebbe diverse centinaia di anni (varia in base alle prestazioni dell'hardware). Oltre a questo serve qualcuno che scriva un test per ogni singolo valore e che esista un oracolo che fornisca la risposta corretta.

## **Cos'è un test valido/criterio di selezione valido? Fare un esempio di criterio che seleziona test validi.**

**Un criterio $C$ si dice valido se, qualora il programma $P$ non sia corretto esiste almeno un test $T$, selezionato in base a $C$ che ha successo per il programma $P$** (trova almeno un malfunzionamento).
$$valido(C,P) \leftrightarrow (\neg(ok(P,D)) \rightarrow \exists\, T\in C\; successo(T,P))$$

Considerando il seguente programma Java:

```java
public int raddoppia(int num){
    return num * num;
}
```

Un criterio che seleziona test validi è quello che seleziona sottoinsiemi finiti di $D$ che comprendono almeno un valore diverso da $0$ e $2$. 

## **Parlare della copertura dei comandi e della copertura delle decisioni. Quando si soddisfa solo una delle due?**

Il criterio di copertura dei comandi prevede che ogni comando del programma venga eseguito in corrispondenza di almeno un caso di test $t$ contenuto in $T$ (le righe non raggiungibili sono escluse), in questo modo si eseguono sicuramente anche le righe contenenti anomalie. Vedendo il flusso del programma come un grafo si vuole trovare un insieme di casi di test che consentono di passare attraverso tutti i nodi almeno una volta.

Il criterio di copertura delle decisioni prevede che ogni decisione effettiva venga resa sia vera che falsa in corrispondenza di almeno un caso di test $t$ contenuto in $T$ (le decisioni sempre vere o sempre false sono escluse). Vedendo il flusso del programma come un grafo si vuole trovare un insieme di casi di test che consentono di passare attraverso tutti i nodi e tutti gli archi almeno una volta.

La copertura delle decisioni implica la copertura dei comandi, quindi è possibile soddisfare la copertura dei comandi senza soddisfare la copertura delle decisioni ma non viceversa. Questo avviene nel caso in cui vengano eseguiti tutti i comandi del programma senza rendere sia vere che false tutte le decisioni.

## **Parlare del beebugging e dell'analisi mutazionale.**

Può accadere che chi testa un programma non trovi errori, in questo caso è difficile capire se il programma non contiene errori o se il tester non è in grado di trovarli. Per evitare questa situazione **possono essere aggiunte volontariamente delle anomalie** (beebugging), **il tester è a conoscenza del numero di anomalie inserite e ha il compito di trovarle tutte. Quando il tester ha finito va a mostrare le anomalie trovate, tra queste potrebbero essercene anche alcune che non sono state inserite volontariamente**. Si potrebbe dire che avendo trovato l'80% dei bug finti sono stati trovati anche l'80% dei bug reali ma questo ragionamento non funziona molto, gli errori possono essere di tipologie diverse e la tecnica usata potrebbe essere efficace nel trovare quelli finti ma non quelli veri.

**L'analisi mutazionale viene comunemente usata per capire quanto è approfondito un test. Usando delle funzioni mutanti vengono generati automaticamente dei programmi sbagliati che differiscono da quello originale solo per una piccola modifica sintattica** che mantiene il programma compilabile (un minore diventa minore uguale, si invertono le occorrenze di due variabili, un if diventa while, ...). **Se il test è stato fatto bene si deve accorgere della differenza producendo almeno un risultato diverso in un caso di test**. La metrica si calcola facendo: $\frac{\text{numero mutanti riconosciuti}}{\text{numero mutanti generati}}$, questo valore permette di capire se il testing è approfondito, non se è corretto. Eventualmente è possibile generare dei test usando i mutanti: per ogni mutante che non viene riconosciuto si estrae casualmente un caso di test che riconosca la differenza e lo si aggiunge al test. Questo processo può andare avanti all'infinito senza raggiungere il 100% di copertura ma se non è esplicitamente richiesto ci si può fermare prima.

## **Parlare delle reti di petri di alto livello per il tempo.**

## **Dimostrare come una rete di petri conservativa è anche limitata.**

## **Parlare degli assiomi delle time basic net.**

## **Parlare delle relazioni UML tra classi e istanze.**

## **Scrivere la relazione di conflitto e relazione di concorrenza. Evidenziare strutturale/effettivo.**

Conflitto strutturale: $Pre(t_1) \cap Pre(t_2) \not= \emptyset $  
Conflitto effettivo: $M[t_1> \;\;\land\;\; M[t_2> \;\;\land\;\; \exists p \in Pre(t_1) \cap Pre(t_2) \;|\; M(p) < W(<p,t_1>) + W(<p,t_2>)$

Concorrenza strutturale: $Pre(t_1) \cap Pre(t_2) = \emptyset $  
Concorrenza effettiva: $M[t_1> \;\;\land\;\; M[t_2> \;\;\land\;\; \forall p \in Pre(t_1) \cap Pre(t_2) \;\; M(p) \geq W(<p,t_1>) + W(<p,t_2>)$

## **1. Esempio di rete limitata.**
1. Albero raggiungibilità vs albero copertura. COsa rappresenta omega? Vari esempi?



















## **Cos'è l'interface segregation?**


## **Cos'è il TDD? È una tecnica specifica?**

Il TDD (Test Driven Development) è una metodologia di progettazione che guida verso la soluzione più semplice possibile. Questo prevede la ripetizione ciclica delle seguenti fasi:
- **Rosso**: si scrive un test che va a controllare la correttezza della funzionalità che si vuole realizzare, inizialmente fallirà perchè la funzionalità non è stata ancora implementata.
- **Verde**: si cerca di far passare il test con la soluzione più semplice possibile, in questo momento non è particolarmente importante scrivere un buon codice. 
- **Refactoring**: si migliora il codice precedente rendendolo più leggibile, manutenibile e/o efficiente senza far fallire i test scritti precedentemente.

Queste tre fasi dovrebbero durare al massimo 10 minuti, se richiedono più tempo è un segnale di allarme, non si sta realizzando la soluzione più semplice o si sta realizzando qualcosa che può essere scomposto in parti più piccole. Il programma ottenuto sarà sicuramente testabile e avere un feedback immediato permette di accorgersi velocemente di problemi e fa risparmiare tempo. Inoltre negli IDE moderni scrivere prima i test permette di far generare automaticamente parti di codice.

1. Differenza tra merge e pull request.
1. Testing funzionale
1. COsa sono gli stub? Quando devo mockare? Perchè non voglio usare le altre classi già presenti? Quando non posso usare le classi già presenti? Problemi di uso mock (prestazioni, non simula risposta in tempo reale). Dumming/stubbing. Che funzioni usi per stubbing con mockito?

---

Torna all'[indice degli argomenti](../README.md#indice-degli-argomenti)  