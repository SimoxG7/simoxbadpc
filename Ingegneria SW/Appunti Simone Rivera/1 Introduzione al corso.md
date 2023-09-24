# **1. Introduzione al corso<br>[[ST 1](../Slide%20teoria/01.pdf)] ([VL 1](https://www.youtube.com/watch?v=YafUoaeXOdU) e [VL 2](https://www.youtube.com/watch?v=Drv68X65Z8U))**

L'ingegneria del software nasce negli anni '50-'60, quando i computer iniziarono a diffondersi nel mondo accademico. Già allora ci si rese conto che non è possibile sviluppare software in modo artigianale, lavorare senza criterio fa sorgere numerosi problemi. Negli anni '70 si iniziarono a studiare metodi, processi e strumenti che potessero aiutare ad assicurare una buona qualità del software.

## **1.1. Approccio ingegneristico**

![Img](img/Schema%20approccio%20ingegneristico.webp)

1\. Definizione del target, ovvero l'obiettivo che si desidera raggiungere. Questo dev'essere un qualcosa di misurabile, in genere i target sono la *risoluzione di problemi presenti nello sviluppo del software* o *il raggiungimento di un certo livello nelle qualità del software*.

2\. Definizione della metrica utilizzata per misurare il raggiungimento del target.

3\. Scelta dei metodi, dei processi e degli strumenti che si pensa possano permettere di avvicinarsi maggiormente al target.

4\. Esecuzione di esperimenti controllati (molto difficili e costosi) in cui vengono presi vari gruppi di sviluppatori e gli si chiede di svolgere dei task con diversi metodi, processi e strumenti. Si misura quindi con la metrica stabilita quanto ogni gruppo si è avvicinato al target.

5\. Il sistema proposto risulta migiore se il gruppo che l'ha attuato ha ottenuto incrementi statisticamente significativi nel raggiungimento del target. Nel caso in cui non si verifichi tale incremento potrebbe essere stata utilizzata una metrica non adatta, però cambiarla a posteriori può sembrare un tentativo di falsare il risultato dell'esperimento.

## **1.2. Problemi pricipali dello sviluppo del software**

- **Numero e tipo di persone coinvolte**: spesso i programmatori non hanno una gran conoscenza del dominio applicativo in cui dovrà operare l'applicazione da realizzare, quindi diventa fondamentale capire correttamente le spiegazioni degli esperti. I *problemi di comunicazione* sono frequenti anche tra sviluppatori (si veda il caso del [Mars Climate Orbiter](https://it.wikipedia.org/wiki/Mars_Climate_Orbiter)).
- **Dimensioni del software**: alcuni software moderni hanno milioni di righe di codice e richiedono migliaia di anni uomo per la realizzazione.
- **Software malleabile**: le richieste dei clienti cambiano nel tempo, anche il software dev'essere predisposto ad *adattarsi ai cambiamenti*.

Quello che si cerca di fare è mitigare le conseguenze di questi tre aspetti con un buon processo di sviluppo, nell'ambito ingegneristico c'è la convinzione che un buon **processo** porti ad un buon **risultato**/**prodotto**.

## **1.3. Qualità del software**

Possiamo definire come **qualità** qualsiasi cosa che comporta un valore per le persone, spesso è il cliente (*qualità esterne*) ma in alcuni casi può anche essere lo stesso sviluppatore (*qualità interne*). Per essere definito "di qualità" un software deve:

- **funzionare**, nello specifico vuol dire che:

  - produce il risultato corretto (**correttezza**), aderisce alle **specifiche** (quello che crediamo il cliente voglia). Spesso il cliente non sa esattamente cosa vuole fin dall'inizio, lo capirà progressivamente durante lo sviluppo, inoltre potrebbe dare indicazioni sbagliate in buona fede. É quindi necessario avere interazioni costanti con il cliente durtante lo sviluppo. Se ci si accorge che quello che ci viene chiesto è realizzabile ma non ha senso è meglio discuterne subito con il cliente per evitare cause legali.

        [L1] R.Glass's law: "Requirements deficiencies are the prime source of project failure"

        Le difficoltà del cogliere completamente e correttamente i requisiti posti dal cliente è la prima principale fonte di fallimento dei progetti.

  - è **affidabile** (reliable), aderisce ai **requisiti** (quello che il cliente vuole). Un programma che aderisce a delle specifiche che però sono sbagliate è corretto ma non affidabile, da questo possiamo capire che l'affidabilità non implica la correttezza e che la correttezza non implica l'affidabilità.
  - ha un comportamento **sicuro** e **robusto** anche a fronte di situazioni non espressamente preventivate.

- essere **bello**, è una questione soggettiva, le accezioni più comuni comprendono:

  - è **facilmente utilizzabile**. L'**usabilità** può essere misurata tramite esperimenti controllati durante i quali vengono registrate misure *qualitative* (tracce telecamere eye tracking, descrizioni delle azioni che gli utenti stavano cercando di compiere, ...) e *quantitative* (numero di task svolti, tempo impiegato, frequenza degli errori, numero di richieste d'aiuto, ...).

        [L26] Nielsen-Normal's law: "Usability is quantifiable"

  - è **veloce** (non inteso come tempo assoluto ma come **efficiente nell'uso delle risorse**). Questo aspetto è uno degli ultimi che si prende in considerazione durante lo sviluppo perchè non ha senso spendere tempo per ottimizzare delle funzionalità che magari verranno rimosse.
  - è **pulito** (chiaro, intuitivo, facilmente leggibile, verificabile), ovvero che ha tutte le qualità fondamentali per l'*evolvibilità*. Per ottenere questa qualità possono essere definiti degli standard da rispettare a livello di team (parentesi if sempre a capo, convenzione nomi variabili, ...)

- **farmi diventare ricco**, questa qualità può essere ottenuta:

  - non spendendo inutilmente per rifare cose già fatte (**riusabilità dei componenti**). Il riuso del software riduce il tempo necessario per completare il progetto, incrementa la produttività e la qualità. É però necessario verificare che il codice si adatti perfettamente al nuovo progetto, potrebbe essere necessario fare degli adattamenti (si veda il caso dell'[Ariane 5](https://it.wikipedia.org/wiki/Ariane_5) [[Video]](https://www.youtube.com/watch?v=5tJPXYA0Nec)).

        [L15] McIlroy's Law: "Software reuse reduces cycle time and increases productivity and quality"

  - semplificando gli interventi post-consegna (**manutenibilità**). Questi includono la correzione di errori (**riparabilità**, misura quanto è facile modificare certi aspetti, tarare parametri o cambiare algoritmi), l'aggiunta di nuove funzionalità e l'adattamento a nuove situazioni (**evolvibilità**/**estendibilità**).
  Queste proprietà possono essere ottenute modularizzando e parametrizzando.

        [L27] M.Lehman's Law: "A system that is used will be changed"

        Un programma che non viene costantemente aggiornato non può essere utilizzato per sempre.
      ###
        [L28] M.Lehman's Law: "An evolving system increases its complexity unless work is done to reduce it"

        Un software troppo complesso perde tutte le altre qualità, è necessario lavorare per semplificarlo.

## **1.4. Processo di produzione del software**

Un buon processo di produzione del software deve:

- essere **robusto**, nel senso che resiste agli imprevisti (cambiamenti delle specifiche, disponibilità delle risorse umane, ...). Questo influenza l'idea di affidabilità che i clienti si fanno dell'azienda.
- essere **produttivo**, è difficile trovare una metrica adatta a misurare la produttività.
- permettere di andare a coprire per primi una certa necessità nel mercato. Questo è possibile utilizzando un modello di **sviluppo incrementale** (a volte è difficile arrivare in tempo a causa del cambio dei requisiti), di contro questo modello ha oneri di organizzazione maggiori.

Quando si inizia a produrre del software con un qualsiasi processo bisogna riconoscere che:
- la produzione di software non comprende eclusivamente la scrittura di codice (è la parte più semplice, infatti viene comunemente affidata a figure junior).
- è necessario risolvere i *problemi di comunicazione* (ci sono varie figure, ognuna con le sue specializzazioni, che devono comunicare per far funzionare il tutto).
- è necessario un certo **rigore**, una certa **formalità** (in realtà questo punto è ancora discusso).

      [H3] Bauer-Zemanek's Hypotesis: "Formal methods significantly reduce design errors, or eliminate them early"

      Anche se si riducono gli errori, è molto difficile essere rigorosi e formali.

- ci sono molti aspetti da considerare singolarmente (ruoli delle figure coinvolte, ordine delle fasi, come si dividono le funzionalità in moduli, ...). Esistono linguaggi di programmazione orientati agli aspetti (AOP, aspect oriented programming).

## **1.5. Modellare il cliclo di vita del software**

Modellare il ciclo di vita del software significa identificare le attività necessarie e i soggetti che dovranno realizzarle per poi stabilire le precedenze temporali e le varie durate.

### **1.5.1. Studio di fattibilità**

Inizialmente si cerca di capire se è possibile realizzare il progetto e quali concorrenti ci sono nel mercato, quindi si studiano diversi scenari di realizzazione per capire qual'è quello più conveniente (scelte architetturali, sviluppo interno o delega all'esterno, ...). Per ogni possibile soluzione individuata si effettua una stima dei costi, tempi, risorse necessarie e benefici.

Fare un analisi del genre è difficile, stimare correttamente i costi richiede esperienza, infatti queste analisi vegnono spesso commissionate all'esterno. Bisogna notare che questa fase non può prendere molto tempo, altrimenti si rischia di arrivare sul mercato troppo tardi.

Come risultato si ottiene un documento molto sostanzioso scritto in linguaggio natuale (perchè dev'essere letto dal manger) che serve per capire se ha senso avviare il progetto e quale approccio conviene utilizzare.

### **1.5.2. Analisi e specifica dei requisiti**

In questa fase si cerca di comprendere il dominio applicativo e di identificare gli *stakeholders*, ovvero tutte le figure interessate al progetto. Molto spesso queste figure daranno priorità a qualità diverse, andando in contrasto tra di loro.

In questo momento è fondamentale concentrarsi nel capire il *cosa* ci viene chiesto e non il *come* realizzarlo, mettendo troppi vincoli nella specifica si rischia di bloccare la possibilità di realizzare soluzioni migliori.

I possibili documenti prodotti in questa fase sono:
- **documento di specifica**, base contrattuale che si impugna davanti al giudice per stabilire se è stato realizzato quanto richiesto, è quello in base a cui andiamo a giudicare la correttezza del software.
In questo documento è molto importante essere formali al fine di evitare ambiguità ed essere più chiari in caso di contestazioni contrattuali.
- manuale utente, si può scrivere anche se il software non esiste ancora ma non deve andare a vincolare troppo lo sviluppo.
- piano di test e collaudi (anche detti **test di accettazione**) che verranno effettuati dal cliente per verificare il sistema realizzato. Vengono comunicati all'inizio del progetto e se non passano il cliente può rifiutare il prodotto.

      [L4] Davis' Law: "The value of models depends on the view taken, but none is best for all purposes"

### **1.5.3. Progettazione (design)**

In questa fase si cerca di capire come realizzare le specifiche nel modo più opportuno: si definisce l'architettura del sistema, la scomposizione in moduli/oggetti e si identificano i **pattern** da utilizzare (soluzioni generali a problemi frequenti che soddisfano e valorizzano certe qualità).

Come risultato si ottiene un **documento di specifica**.

### **1.5.4. Programmazione e test di unità**

I moduli/oggetti definiti precedentemente vengono realizzati, poi si controlla individualmente la loro correttezza con dei test. Effettuare i test solo dopo aver finito il modulo è molto pericoloso, i feedback arrivano così tardi che diventa molto oneroso correggere gli errori.

A volte per poter testare un componente in modo isolato è necessario costruire dei moduli **driver** o **stub** con cui farlo comunicare in modo fittizio, anche se questi moduli non sono stati espressamente richiesti dal cliente sono molto utili per verificare la correttezza del software.

Come risultato si ottengono diversi moduli sviluppati e verificati separatamente con interfaccie di comunicazione concordate.

### **1.5.5. Integrazione e test di sistema**

In questa fase tutti i componenti vengono uniti progressivamente effettuando dei **test d'integrazione** (si verifica che l'unione dei moduli funzioni correttamente), è possibile seguire un approccio top-down o bottom-up.

### **1.5.6. Manutenzione**

Gli interventi di manutenzione che possono essere effettuati si dividono in:

- *correttivi*: correggono degli errori.
- *adattativi*: adattano una o più funzionalità alle esigenze più recenti.
- perfettivi: rendono il codice più leggibile e/o più efficiente, il cliente non nota differenze ma permettono allo sviluppatore di risparmiare tempo, risorse e fatica in futuro.

Come risultato si ottiene un prodotto migliore.

### **1.5.7. Altre attività**

Le ulteriori attività che possono essere svolte sono:

- la scrittura della documentazione: è un attività poco amata dai programmatori, infatti spesso è realizzata da scrittori tecnici (questo comporta una maggior probabilità di incoerenza tra il software e la documentazione). In genere è una delle ultime attività svolte perchè ogni volta che si cambiano i requisiti va cambiata anche la documentazione.
- la verifica e il controllo della qualità (QA, quality assurance).
- la gestione del processo (gestione incenticvi, formazione nuovo personale, ...).
- la gestione delle configurazioni (parti di codice modificate contemporaneamente da più progetti).

---

Torna all'[indice degli argomenti](../README.md#indice-degli-argomenti)  
Capitolo successivo: [Modelli di ciclo di vita del software](2%20Modelli%20di%20ciclo%20di%20vita%20del%20software.md)