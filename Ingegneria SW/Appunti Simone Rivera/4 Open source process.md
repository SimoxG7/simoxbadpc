# **4. Open source process<br>[[ST 4](../Slide%20teoria/04.pdf#page=17) e [ST 5](../Slide%20teoria/05.pdf)] ([VL 8](https://www.youtube.com/watch?v=Ij31BHZzuB0) e [VL 9](https://www.youtube.com/watch?v=VLsr5DZ2L_I))**

## **4.1. La cattedrale e il bazaar ([Articolo](http://www.catb.org/~esr/writings/cathedral-bazaar/))**

In questo articolo scritto da Eric Steven Raymond, viene usata una metafora che paragona due modelli di sviluppo a due modelli di costruzione civile:
- la cattedrale è la realizzazione dell'idea di una singola persona: l'architetto. In questo modello l'architetto assegna a diverse persone dei compiti precisi da eseguire seguendo esattamente le sue istruzioni, si ha quindi una figura centralizzata ed una forte gerarchizzazione.
- il bazaar invece nasce da un gruppo di beduini che inizia a stabilirsi in una certa zona, nel tempo altri commercianti decideranno autonomamente di unirsi ingrandendo il mercato. In questo modello non esiste una persona a capo del progetto, lo sviluppo si basa esclusivamente su contributi volontari.

Pur essendo sostanzialmente diversi, entrambi i modelli portano ad un ottimo risultato (bello e funzionale). Nella maggior parte dei casi il modello adottato dai progetti open source è quello del bazaar.

Secondo Raymond è possibile dividere la storia di molti progetti open source in una serie di fasi che ricalcano il modello del bazaar:
1. Si parte sempre dalla frenesia di un singolo sviluppatore. Questo ha elaborato un'idea che risolve un suo problema e vorrebbe realizzarla (la motivazione non è il denaro ma la gioia di farlo).
1. Questo sviluppatore chiede ai suoi conoscenti se sanno qualcosa sull'argomento, se hanno il suo stesso problema e se sono a conoscenza di una soluzione già esistente.
1. Le persone interessate al progetto si raggruppano ed iniziano a scambiarsi pareri e conoscenze sull'argomento.
1. Alcuni degli interessati iniziano a spendere il proprio tempo libero per realizzare la soluzione al problema, dando il via ad un progetto informale (in questo momento non si può ancora parlare di open source, è solo un progetto tra amici).
1. I membri del progetto lavorano al problema fino a che non raggiungono un risultato presentabile pubblicamente. Nel momento in cui viene messo a disposizione del codice si può iniziare a parlare di open source. Per assurdo, si dice che sia meglio che la prima versione rilasciata contenga tanti bug, questo perchè nessuno guarderà il codice di un programma già funzionante, magari verrà utilizzato per un certo periodo ma poi verrà abbandonato. La presenza di diversi errori stimola lo studio del codice, facendo così appassionare nuove persone al progetto (si crea una comunità).
1. Si inizia a pubblicizzare il lavoro svolto per raccogliere pareri da persone esterne al gruppo (comunicazione bidirezionale).
1. L'interazione tra i vecchi e i nuovi membri porta alla nascita di nuove idee.
1. Le nuove informazioni vengono acquisite e si ritorna al punto 5.

### **4.1.1. Frasi importanti**

    Se dai a tutti il codice sorgente, ognuno di essi diventerà un tuo ingegnere.

In genere le persone che collaborano a progetti open source sono informatici che usano il progetto per risolvere un loro problema e ottenere un beneficio personale, ma non tutte le persone coinvolte devono conoscere l'informatica. Anche chi svolge mansioni diverse può aiutare dando pareri, individuando errori, scrivendo parti del manuale, gestendo i gruppi e i forum della comunità, ... .

    Linus's law: "Given enough eyeballs, all bugs are shallow"

    Se ci sono abbastanza occhi che cercano errori, gli errori diventeranno di poco conto.

Il problema principale sta nel trovare e correggere i bug, in genere questo viene fatto con il testing (la fase in cui si scopriono i malfunzionamenti) e con il debugging (la fase in cui si scoprono le cause che generano i malfunzionamenti e le si correggono). Dato che il codice sorgente è pubblico, chiunque può leggerlo per cercare dei bug e le loro cause, rendendo più veloce la loro individuazione e garantendo una maggior correttezza.

    Se tratti i tuoi beta tester come se fossero la tua risorsa più importante, essi risponderanno diventando la tua risorsa più importante.

Insultare e trattare male gli altri membri della comunità sarà solo controproducente, in questo modo si perdono preziosi collaboratori e sarà più difficile trovarne di nuovi.

    Quando hai perso interesse in un programma, l'ultimo tuo dovere è passarlo ad un successore competente.

Sicuramente ad un certo punto il primo sviluppatore dovrà abbandonare il progetto per dare la precedenza ad altri impegni (famiglia, lavoro, ...), è quindi fondamentale che si preoccupi per tempo ed istruisca una persona che possa amministrare il progetto nel futuro.

### **4.1.2. Confronto tra modelli**

| |Tradizionale|Agile|Open source
---|---|---|---
**Documentazione**|Enfatizzata come mezzo di controllo della qualità e come strumento di gestione del processo.|Non è importante.|Qualunque file condiviso pubblicamente diventa documentazione (roadmap, idee, ...).
**Requisiti**|L'analista traduce i requisiti in specifiche.|L'utente fa parte del team di sviluppo (requisiti viventi).|In genere gli sviluppatori sono anche gli utenti, quindi sanno già cosa vogliono realizzare.
**Modello di gestione dello staff**|Spesso gli sviluppatori sono assegnati ad un singolo progetto (ci sono eccezioni).|In genere gli sviluppatori sono assegnati ad un singolo progetto.|In genere gli sviluppatori lavorano a diversi progetti nel loro tempo libero, quindi non è possibile fare stime sul tempo necessario per realizzare una certa funzionalità, dipende dal tempo che i membri dedicheranno al progetto.
**Ispezione del codice (peer review)**|È apprezzata ma essendo pesante e costosa non tutti la praticano.|Il pair programming è una forma ridotta di peer review.|È il modo principale con cui si gestisce la qualità, avendo rilasciato i sorgenti avviene in continuazione.
**Pianificazione dei rilasci**|Tante funzionalità introdotte in pochi rilasci infrequenti.|Tanti rilasci frequenti di dimensioni ridotte.|I rilasci ufficiali non sono frequenti, però per usare la versione più recente si possono scaricare i sorgenti dal repository ufficiale e compilarli. Alcuni progetti compilano e rilasciano ogni notte il codice contenuto nel repository ufficiale (nightly).
**Management**|I team sono governati dall'alto.|I team si auto-organizzano.|Le persone che lavorano al progetto potrebbero non conoscersi, ci dev'essere un forte legame quando si inizia ad organizzare veramente il progetto. Mettere delle linee guida troppo stringenti potrebbe far perdere membri preziosi.
**Testing**|Il testing è gestito dal team di Quality Assurance (QA), questa scelta ha dei vantaggi e degli svantaggi.|Il testing è parte dello sviluppo.|Chiunque può fare testing.
**Distribuzione del lavoro**|Ad ogni sviluppatore viene assegnata una parte di codice su cui ha la piena responsabilità.|Chiunque può modificare qualunque parte del codice (proprietà collettiva).|Chiunque può modificare il codice, ma solo i *committers* possono rendere ufficiali questi cambiamenti.

## **4.2. The care and feeding of FOSS ([Articolo](https://web.archive.org/web/20081015010505/http://www.moonviewscientific.com/essays/software_lifecycle.htm))**

In questo articolo si analizzano le fasi vissute da qualsiasi tipo di applicazione (sistemi operativi, web server, ...) con il passare del tempo.
1. **Invenzione**: qualcuno ha un idea e la fa funzionare (può anche essere una startup).
2. **Espansione ed innovazione**: quando l'idea viene resa pubblica nascono molte altre idee. Molte persone vengono pagate dalle aziende per ideare e realizzare delle funzionalità esclusive da aggiungere al proprio prodotto, in modo da battere la concorrenza e vendere di più.
3. **Consolidamento**: dopo alcuni anni poche aziende domineranno il mercato, le altre verranno comprate e assorbite da quelle più grandi. Questo non è un buon momento per far partire un progetto open source perchè c'è gente pagata che sviluppa tanto e non è possibile stare al loro passo.
4. **Maturità**: alcune aziende fanno molti soldi, molte altre invece falliscono (si riducono i competitor). A questo punto è chiaro cosa devono fare i programmi, l'innovazione rallenta e si raggiunge la fase di maturità. Questo è un buon momento per iniziare con un progetto open source.
5. **FOSS domination**: il prodotto open source raggiunge e sorpassa quelli commerciali. In questo momento le aziende hanno gia recuperato l'investimento originario e possono quindi pensare di trasformare il loro modello di business. Alcune smettono di sviluppare ed iniziano a vendere servizi, altre invece rendono open source i loro prodotti (se fatto al momento sbagliato gli altri progetti copieranno le parti di codice utili causando la morte del prodotto).
6. **FOSS era**: alla fine rimarranno solo i prodotti open source, salvo cose molto particolari.

## **4.3. The emerging economic paradigm of open source ([Articolo](https://web.archive.org/web/20120724095330/http://perens.com/works/articles/Economic.html))**

In questo articolo si tratta un controsenso del mondo open source: dietro allo sviluppo della maggior parte dei progetti open source che hanno avuto successo ci sono delle aziende commerciali che pagano degli sviluppatori.

Questa è una buona scelta nel caso in cui per l'azienda:
- il software non è un prodotto da vendere ma è uno strumento che usa (una **tecnologia abilitante**).
- il software non è un **fattore differenziante**, ovvero non offre alcun vantaggio competitivo.

In questi casi è insensato farsi carico dei costi di sviluppo di un proprio sistema software, conviene contribuire ad un progetto open source introducendo le funzionalità che servono e sfruttando il lavoro fatto da altre persone. A volte è anche possibile riuscire ad indirizzare lo sviluppo del progetto in direzioni favorevoli.

### **4.3.1. Confronto tra modelli di sviluppo organizzativi**

Paradigma|Descrizione|Efficienza (quantità di soldi che va agli sviluppatori)|Probabilità di fallimento|Distribuzione dei costi nel tempo|Rischio distribuito|Fattore differenziante per il cliente|Fattore differenziante per il venditore|Dimensione del mercato richiesta
--|--|--|--|--|--|--|--|--
Vendita al dettaglio (retail)|Si mette il prodotto sul mercato, non c'è un singolo cliente ma tante persone interessate|< 10%, la maggior parte si spende in pubblicità|50%|Tardi, a volte anche dopo l'inizio delle vendite|No|No|Si|Mercato di massa
In house e con contratto|Un singolo cliente commissiona il prodotto, è l'unico a pagare|60% - 80%|50%|No|No|Si|A volte|1 cliente
Consorzio e collaborazione non open source|Collaborazione tra più aziende, spesso fallisce perchè sono comunque in concorrenza tra di loro|60% - 80%|90%, troppo alta|Si|Si|A volte|A volte|5+ clienti
Open source|Codice sorgente pubblicamente disponibile, chiunque può contribuire allo sviluppo|60% - 100%|50%|Presto, durante lo sviluppo|Si|No|No|5+ clienti

## **4.4. An empirical study of open source and closed source software products ([Articolo](https://ieeexplore.ieee.org/abstract/document/1274044))**

Questo studio empirico abbastanza rigoroso ha controllato la veridicità di alcune frasi comuni sull'open source valutando alcuni aspetti di diversi prodotti open e closed source.

Ricerca|Metrica|Risultati
--|--|--
Lo sviluppo open source favorisce una crescita del sistema più rapida.|Crescita complessiva del progetto in funzione del tempo e crescita complessiva del progetto in linee di codice (LOC, line of code) nel tempo.|**Non supportano l'ipotesi**: il tasso di crescita è simile in tutti i progetti analizzati, quelli open source non hanno mostrato tassi di crescita superiori.
I progetti open source favoriscono una maggior creatività.|Funzioni aggiunte nel tempo.|**Supportano l'ipotesi**: i tassi di crescita dei progetti open source sono superiori a quelli closed source.
I progetti open source hanno successo a causa della loro semplicità.|Complessità generale del progetto, complessità media di tutte le funzioni e complessità media delle funzioni aggiunte.|**Non supportano l'ipotesi**: i progetti closed source analizzati sono più semplici di quelli open source.
Generalmente i progetti open source hanno meno difetti rispetto a quelli closed source perchè vengono scoperti e sistemati rapidamente.|Funzioni modificate nel tempo e percentuale delle funzioni modificate rispetto a quelle totali.|**Supportano l'ipotesi**: il numero di funzioni modificate nel tempo e la percentuale delle funzioni modificate rispetto al totale sono maggiori nei progetti open source.
I progetti open source sono più modulari rispetto a quelli closed source.|Correlazione tra le funzioni aggiunte e quelle modificate.|**Non supportano l'ipotesi**: nei progetti open source è stata trovata una forte correlazione tra il tasso di crescita e il tasso di modifica, invece nei progetti closed source non è stata trovata.

## **4.5. Le sfide dell'open source**

Dato che nei progetti open source non esiste un vero e proprio team, ma una comunità di sviluppo che si può sfaldare, è necessario utilizzare dei metodi efficienti per comunicare, coordinarsi e trovare nuovi membri in modo da non far morire il progetto.

Per permettere a chiunque di lavorare sul progetto, servono dei processi automatici che consentano di ricreare facilmente l'intero ambiente di sviluppo (download automatico delle dipendenze, task automatici richiamabili facilmente, ...).

In questi progetti l'integrazione è letteralmente continua, chiunque può sottoporre le sue modifiche in qualsiasi momento e questo comporta una serie di problemi. Servono degli strumenti che sincronizzino per tutti i membri il lavoro di versioning del codice e della documentazione.

Anche il bug tracking è un problema, spesso le segnalazioni sono poco chiare o duplicate: bisogna educare ed istruire i reporter, inoltre serve un posto in cui pubblicare i problemi noti. Il ciclo di vita di un bug dev'essere governato e gestito il più possibile (segnalato -> verificato -> dato in gestione a qualcuno -> risolto -> integrato nella versione ufficiale).

I problemi di comunicazione sono stati risolti prevalentemente con l'uso dei forum online, mentre per la gestione del codice sono stati creati diversi software di Source Code Management (SCM).

---

Torna all'[indice degli argomenti](../README.md#indice-degli-argomenti)  
Capitolo successivo: [Source Code Management (SCM)](5%20Source%20code%20management.md)  
Capitolo precedente: [eXtreme Programming (XP)](3%20Extreme%20programming.md)