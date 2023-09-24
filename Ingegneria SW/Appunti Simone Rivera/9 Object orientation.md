# **9. Object orientation<br>[[ST 7](../Slide%20teoria/07.pdf?page=7) e [ST 8](../Slide%20teoria/08.pdf)] ([VL 13](https://youtu.be/8vTSssPmUt0?t=2074) e [VL 15](https://www.youtube.com/watch?v=oI6kqwNmIpU))**

## **9.1. Caratteristiche dell'object orientation**

La programmazione orientata agli oggetti (OOP, Object Oriented Programming) è un paradigma di programmazione che permette di definire degli **oggetti** che sono in grado d'interagire tra loro. Questi sono composti da dati (*attributi*, le proprie caratteristiche) e pezzi di codice (*metodi*, le azioni che possono compiere o che è possibile compiere su di essi). 

Affinchè un linguaggio o un approccio possa essere definito object oriented è necessario che abbia tre caratteristiche: **ereditarietà**, **polimorfismo** e **collegamento dinamico**.

### **9.1.1. Ereditarietà**

L'ereditarietà è un meccanismo che permette di definire una nuova classe (*sottoclasse*) a partire da un altra classe già esistente (*superclasse*). La sottoclasse eredita tutti gli attributi ed i metodi della superclasse, inoltre può estenderne le funzionalità definendone di nuovi e/o sovrascrivendo quelli ereditati.

Alcuni linguaggi implementano l'ereditarietà multipla (una classe può ereditare da più classi), ma questo fa sorgere dei problemi nel caso in cui più superclassi abbiano dei metodi che hanno lo stesso nome. Java ha scelto d'utilizzare l'ereditarietà singola.

Un altra possibile forma di ereditarietà è l'implementation inheritance, in questo caso non si definisce un vero sottotipo ma semplicemente gli si fanno ereditare dei metodi per non riscriverli.

### **9.1.2. Polimorfismo**

A volte non interessa vedere una classe con tutte le sue capacità ma è sufficiente averne una visione più ristretta. Grazie al polimorfismo, quando una classe implementa diverse **interfacce** può essere vista in modi diversi. Dichiarando una variabile cha ha come tipo un interfaccia è possibile assegnargli una qualsiasi classe che la implementa, in questo modo s'ignora quale sia l'oggetto reale per vederlo solo attraverso le capacità dichiarate nell'interfaccia (è anche possibile assegnare delle sottoclassi ad una superclasse per vederle uniformemente attraverso le sue competenze). Questa caratteristica risulta particolarmente utile quando si creano collezioni e quando si usano i generici.

### **9.1.3. Collegamento dinamico**

Grazie al polimorfismo è possibile vedere uniformemente oggetti diversi che hanno le stesse capacità, però quando si esegue un metodo di una variabile che ha come tipo un interfaccia o una superclasse si potrebbe voler  chiamare quello del sottotipo che le è stato assegnato concretamente. Il collegamento dinamico permette di verificare qual'è il tipo concreto di una variabile durante l'esecuzione in modo da poter andare a chiamare il metodo dell'oggetto reale.

Il collegamento dinamico potrebbe sembrare una parte del polimorfismo, ma in realtà è una caratteristica separata. Infatti esistono dei linguaggi che hanno il polimorfismo ma che non hanno il collegamento dinamico, oppure linguaggi che permettono di scegliere funzione per funzione se usare il collegamento dinamico o quello statico (ad esempio C++). In Java si è preferito usare sempre il collegamento dinamico al fine di evitare alcuni errori che possono commettere i programmatori.

## **9.2. Principi SOLID**

I principi SOLID sono dei principi che aiutano ad avere una conoscenza comune del design, se rispettati permettono di comprendere la struttura del software più facilmente.

L'acronimo SOLID significa:
1. **Single responsability**: una classe si deve occupare di uno ed un solo compito, questo per due motivi:
    - diventa più *semplice da scrivere* e quindi meno soggetta ad errori.
    - diventa più *riusabile*, difficilmente si riusa una classe che svolge molti compiti quando si è interessati solo a pochi di questi.
    
    È possibile capire se una classe si occupa di più compiti semplicemente guardandone il codice e seguendo alcune regole callisteniche di progettazione, ovvero delle forzature che portano ad avere un codice più pulito (Es: mai mettere più di due attributi perchè al massimo si mettono in relazione due concetti, se è una collezione deve contenere solo gli elementi, ...).
1. **Open-closed principle**: le classi devono essere *aperte ai cambiamenti* (permettere l'inserimento di nuove funzionalità) pur rimanendo *chiuse* (senza essere modificate). Quando si ha consegnato una classe ed è già in uso, si preferisce lasciarla invariata (si può fare refactoring) ed aggiungere altre funzonalità sfruttando l'ereditarietà, il polimorfismo ed il collegamento dinamico.
1. **Lyskov substitution principle**: questo principio è nato perchè i linguaggi di programmazione ad oggetti non garantiscono che le sottoclassi siano sempre utilizzabili al posto delle superclassi, dev'essere il programmatore ad accertarsene.

    Se un metodo di una superclasse ha certe precondizioni e postcondizioni, allora queste devono valere anche per lo stesso metodo delle sue sottoclassi (possono essere più stringenti ma mai meno). In realtà bisognerebbe verificare anche altre cose più complicate che però non si vedranno in questo corso (covarianza dei metodi, ...), ma già questo garantisce che la sostituzione si possa effettuare veramente.

    Nel momento in cui si effettua un cast si vanno a bypassare tutte queste regole, il programmatore si assume la responsabilità della conversione.
1. **Interface segregation**: piuttosto che usare direttamente una classe è meglio dividere le sue capacità in tante interfacce sensate (che siano tra loro coese) e vederla attraverso questi filtri. Più si separano e rendono indipendenti le capacità di una classe e più sarà facile che questa sia riutilizzabile in diversi contesti.

1. **Dependency inversion**: creare una classe concreta che fa riferimento ad un altra classe concreta rende il design restrittivo. È meglio far riferimento ad un astrazione del funzionamento del componente di basso livello, che poi verrà implementatato da varie classi.

    Questo principio è riassumibile in due punti:
    - I moduli di alto livello non devono dipendere da quelli di basso livello, entrambi devono dipendere da astrazioni (tutto quello che è concreto deve dipendere da qualcosa di astratto).
    - Le astrazioni non devono dipendere dai dettagli, sono i dettagli che dipendono dalle astrazioni.

## **9.3. Encapsulation e infomation hiding**

    Parnas [L8]
    Solo quello che è nascosto può essere cambiato liberamente e senza pericoli

Quando una classe espone degli attributi e/o dei metodi, questi vengono resi disponibili ai suoi utilizzatori, che li possono sfruttare per realizzare altre funzionalità. Proprio perchè altri sviluppatori potrebbero averci fatto affidamento non sarà più possibile riumoverli in futuro.

Java nel tempo ha rimosso diverse funzioni, ma molte persone le hanno usate nei loro programmi e quindi prima di farlo è stato necessario fargli attraversare una fase di deprecation.

Nascondendo i dettagli di una classe con l'incapsulamento e interagendo con essa solo mediante l'interfaccia si possono cambiare liberamente e trasparentemente i suoi dettagli concreti. Non si nascondono le informazioni per il gusto di nasconderle ma per avere la libertà di cambiarle senza compromettere il resto del programma. Questo *facilita la comprensione del codice* e *diminuisce il rischio di causare danni* quando si effettua una modifica.

## **9.4. Reference escaping**

Il reference escaping è una violazione dell'incapsulamento, avviene quando dei dettagli interni che si pensava fossero nascosti sono in realtà accessibili dall'esterno.

Questo può avvenire per diversi motivi:
- un metodo getter restituisce il riferimento ad un attributo interno che si sta nascondendo.
- un metodo setter assegna ad un attributo interno che si sta nascondendo un riferimento che gli viene passato come parametro.
- un costruttore assegna ad un attributo interno che si sta nascondendo un riferimento che gli viene passato come parametro.

Questo non vuol dire che non si possono assegnare attributi per riferimento, ma se lo si fà non si può pensare che siano protetti. Se questa conoscenza è diffusa solo a livello di package perchè serve per far collaborare alcune classi va bene.

Una buona strategia per gestire le visibilità degli attributi e dei metodi può essere la seguente: si creano privati e nel momento in cui sorge la necessità di aumentare la loro visibilità per effettuare delle modifiche lo si fà, una volta completate ci si chiede se è possibile restingere nuovamente la visibilità ed eventualmente la si modifica.

## **9.5. Immutabilità**

Una classe si dice immutabile se non è possibile modificare il suo stato dopo l'inizializzazione (neanche per se stessa). In questo modo si ha una minor flessibilità ma si possono condividere liberamente le sue istanze perchè tanto non possono cambiare.

## **9.6. Design by contract vs defensive programming**

Spesso i metodi che si definiscono sono parziali, questo significa che accettano solo un sottoinsieme degli input possibili. L'insieme delle condizioni che devono essere verificate affinchè questi funzionino correttamente prende il nome di precondizioni.

Esistono due diverse scelte di design che riguardano la verifica del rispetto delle precondizioni:

### **9.6.1. Design by contract**

Nel design by contract (anche detto contract based design) chi scrive il metodo specifica anche le precondizioni attraverso dei commenti (Javadoc) o delle asserzioni. Nel corpo del metodo le precondizioni non verranno controllate, se non sono state rispettate ed il programma crasha o ha comportamenti imprevisti è colpa di chi ha chiamato la funzione.

Praticamente si stabilisce un contratto, chi scrive il metodo garantisce che questo produrrà il risultato corretto a patto che chi lo chiama rispetti le precondizioni, nel caso in cui queste non siano state rispettate il metodo può comportarsi in modi imprevisti e a volte difficilmente predicibili.

Usare le asserzioni ha il vantaggio di poterle controllare esclusivamente in fase di sviluppo, quando il sorgente verrà compilato per produrre il programma eseguibile queste non verranno tradotte.

### **9.6.2. Defensive programming**

Nella programmazione difensiva i metodi controllano sempre esplcitamente tutte le precondizioni prima d'iniziare con l'esecuzione vera e propria. Se viene trovata una precondizione non rispettata, il metodo intraprenderà delle azioni finalizzate alla risoluzione del problema (generalmente solleva un eccezione).

Verificare esplicitamente tutte le condizioni fa calare leggermente le prestazioni ma assicura che il programma sia più affidabile e sicuro, inoltre è più facile predirne il comportamento.

## **9.7. Esempi pratici**

### **9.7.1. Interface segregation**

```java
public static List<Card> drawCards(Deck deck, int number) {
    List<Card> result = new ArrayList<>();
    for(int i = 0; i < number && !deck.isEmpty(); i++){
        result.add(deck.draw());
    }
    return result;
}
```

Questo metodo permette di estrarre N carte da un mazzo implementato dalla classe Deck, però per effettuare questa operazione non serve sapere concretamente quale mazzo sia o quante e quali carte contenga (non si sta rispettando l'interface segregation). Le uniche cose importanti che deve sapper fare l'oggetto con cui si lavora sono: saper dire se è vuoto e restituire una carta rimuovendola da se stesso.

Questo suggerisce che è possibile realizzare una vista delle capacità di Deck utile per questo metodo. Definendo un interfaccia CardSource che ha questi due metodi e facendo in modo che Deck la implementi, è possibile sostituire il tipo Deck con CardSource (grazie al polimorfismo).

In altri linguaggi non sarebbe neanche stato necessario dichiarare esplicitamente che Deck implementa CardSource, è sufficiente che queste abbiano gli stessi metodi (duck typing). Basarsi esclusivamente sul nome dei metodi è rischioso perchè potrebbero esserci casi di omonimia (il metodo play di un tennista fa cose diverse rispetto al metodo play di un violinista).

![Img](img/Interface%20segregation.webp)

Si è quindi ottenuta una classe Deck che implementa tre diverse interfacce, ed è quindi utilizzabile da diversi client interessati solo ad alcune sue specifiche competenze. Inoltre la generalizzazione permette ai client di lavorare con altre classi che implementano le stesse interfacce.

### **9.7.2. Polimorfismo e loose coupling (accoppiamento lasco)**

```java
Deck deck = new Deck();
CardSource source = deck;
```

Grazie al polimorfismo è possibile assegnare un istanza di una classe (Deck) ad una variabile che ha come tipo un suo supertipo, una classe astratta che estende o un interfaccia che implementa (CardSource).

Ogni volta che si crea un astrazione s'inserisce un indirettezza, questo peggiora leggermente le prestazioni ma nella maggior parte dei casi i vantaggi dovuti alla generalità li compensano abbondantemente (è peggio avere problemi d'espandibilità ed evolvibilità dovuti alla mancanza dell'interfaccia).

Nel TDD può capitare di non aver pensato da subito alla creazione di un interfaccia, ma quando ci si è accorge che è utile è bene estrarla.

### **9.7.3. Collegamento dinamico ed estendibilità**

```java
public static List<Card> drawCards(CardSource cardSource, int number) {
    List<Card> result = new ArrayList<>();
    for(int i = 0; i < number && !cardSource.isEmpty(); i++){
        result.add(cardSource.draw());
    }
    return result;
}
```

Quando si esegue il metodo drawCards, verrà chiamato anche il metodo draw di CardSource, a questo però non è associato alcun codice concreto (CardSource è un interfaccia senza implementazioni di default). Bisognerà chiamare il codice della classe concreta contenuta dalla variabile cardSource, ma durante la compilazione il compilatore non è in grado di stabilire quale sia. Il collegamento dinamico serve proprio a realizzare quest'indirettezza, durante l'esecuzione verrà controllato il tipo concreto dell'oggetto contenuto nella variabile in modo da poter chiamare il suo codice.

    Il collegamento dinamico permette di chiamare del codice che non è ancora stato scritto

Quando è stato scritto il codice del metodo drawCards, poteva non esistere una classe che dava un implementazione concreta all'interfaccia CardSource, eppure basta passargli come parametro una classe che la implementi e tutto funziona perfettamente, senza aver avuto bisogno di modificare il suo codice.

Questo principio è alla base dell'open-closed principle, si possono aggiungere funzionalità creando nuove classi il cui codice viene richiamato da quello già esistente, senza questo meccanismo sarebbe necessario modificare le classi già scritte. Grazie al polimorfismo ed al collegamento dinamico si può realizzare uno scheletro espansibile con nuove funzionalità.

## **9.8. Accenni a UML (Unified Modeling Language)**

Un modello realizzato con UML 2.0 può comprendere fino a 14 diversi diagrammi, durante il corso non li vedremo tutti però conoscerli è importante perchè fanno parte del linguaggio di comunicazione usato tra sviluppatori.

### **9.8.1. Diagramma delle classi**

![Img](img/Esempio%20UML.webp)

Il diagramma delle classi nasce con lo scopo di rappresentare le classi e le associazioni che le coinvolgono.

Per indicare che una classe o un metodo è astratto lo si scrive in corsivo, mentre per indicare che è statico lo si scrive sottolineato. Se un metodo che viene ereditato è scritto anche nella classe che lo eredita significa che questo viene sovrascritto.

L'implementazione si indica con una freccia bianca tratteggiata mentre l'estensione si indica con una freccia bianca continua.

### **9.8.2. Relazioni tra classi**

Una relazione generica tra classi (**dipendenza**) si indica con una freccia tratteggiata che ne evidenzia la direzione di percorrenza, questa può anche essere bidirezionale. Nella pratica significa che quello che è scritto in una classe dipende da quello che è scritto nell'altra, modificando la prima potrebbe essere necessario modificare anche la seconda.

È possibile attribuire un significato più chiaro alle relazioni specializzandole in:
- **Associazione**: indica che entrambi gli oggetti non sono definiti grazie all'altro, sono due concetti distinti che sono in associazione tra loro (una classe contiene un attributo dell'altra classe). Si indica con una freccia senza rombo. Se non si è sicuri su quale relazione usare questa è la migliore perchè è la più generica.
- **Aggregazione**: le istanze di una classe fanno parte della definizione di un istanza dell'altra classe (Deck e Card), il contenimento è virtuale perchè le istanze contenute possono essere indipendenti dalla classe che le contiene. Si indica con una freccia con rombo bianco.
- **Composizione**: le vite delle due istanze sono strettamente legate tra loro (nascono e muoiono assieme), c'è una situazione di contenimento fisico tra i due (Aereo e Motore). Si indica con una freccia con rombo nero.

Vicino alle frecce delle relazioni si scrive la loro cardinalità (anche questa può essere bidirezionale). Dalla cardinalità è possibile capire qualcosa in più riguardo alla tipologia dell'attributo (se 0..1 è un attributo di quel tipo, se 0..N è un contenitore).

---

Torna all'[indice degli argomenti](../README.md#indice-degli-argomenti)  
Capitolo successivo: [Design patterns](10%20Design%20patterns.md)  
Capitolo precedente: [Refactoring](8%20Refactoring.md)