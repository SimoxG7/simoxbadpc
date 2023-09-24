# Verifica e convalida

Precisione terminologia diventa importante per capirsi in modo sicuro. La prima cosa che viene in mente sulle differenza tra questi due termini è:
- Convalida: confronto il software con i **requisiti** (informali) del cliente [quello che ha in testa, le sue necessità].
- Verifica: confronto del software con le **specifiche** (formali) scritte dall'analista [comunicato dal cliente al team di sviluppo].
Fase di raccolta dei requisiti con scrittura delle specifiche è il passaggio dall'uno all'altro.
Usiamo parole diverse per due motivi:
1. Spesso sono formulati in modo diverso, i requisiti sono espressi in maniera informale (espressi dal cliente con il liguaggio del suo dominio che spesso non è di conoscenza dello sviluppatore), le specifiche essendo scritte dall'analista o da sviluppatori sono scirtte in un linguaggio piu vicino, che piace agli sviluppatori, spesso piu formale (anche dev detestano linguaggi formali per scrivere, per leggere preferiamo meno ambiguità + semplice da capire). 
1. I requisiti è facile che cambino, le specfiche di solito sono un po' più congelate (contratto).

Rispetto alle qualità del sw come ci relazioniamo?
La verifica è facile, confronto rispetto alle specifiche l'abbiamo chiamato **correttezza** e farò la verifica della correttezza del sw.
Nella convalida non si parla di specifiche ma delle aspettative del cliente, si parla dell'**affidabilità** (è quello che volevo o ha uno scostamento da quello desiderato che posso sopportare).

Nella terminologia ??? di solito si usa la parola **errore**, l'attività di verifica e voncalida va a cercare degli errori, però la parole errore ha molti significati (almeno 4 accezioni diverse) e quando diventa importante capire di cosa stiamo parlando diventa importante distinguere queste accezioni con termini diversi per evitare fraintendimenti.

Possiamo distinguere in:
- **Malfunzionamento** o **guasto** (**failure**): è qualcosa che non dipende dal codice ma è uno scostamento dal comportamento corretto del programma. Detto meglio un malfunzionamento rispetto alle specifiche (correttezza) rispetto alle aspettative.

    ```java
    static int raddoppia (int num) {
        return num * num;
    }
    ```

    Invocando la funzione con parametro 3 ritorna 9, questo è un malfunzionamento del programma raddoppia. Ovvero un qualocsa che percepisco dall'esterno senza guardare il codice ma solo dall'uso del programma, un funzionamento non coerente con le aspettative derivate dalla conoscenza di ciò che volevo o dalle specifiche di correttezza.
    
    Non può esserci un malfunzionamento senza un errore a livello di codice, deve esserci un punto in cui il mio codice crea un problema.

- **Anomalia** o **difetto** (**fault**) è il punto del codice in cui si crea il problema. Non è detto che sia un unico punto ma se c'è un malfunzionamento allora c'è almeno un anomalia. La presenza di un anomalia è una condizione necessaria ma non sufficiente per far verificare un malfunzionamento.

    Nell'esempio l'anomalia è alla riga 2 dove si moltiplica il parametro per se stesso, genero uno stato inconsistente con la mia apsettativa. Se eseguo raddoppia dandogli come parametro 0 o 2 questa funziona, non sempre causa un malfunzionamento. Questo può succedere anche se ci sono più anomalie che si compensano.
    Vedi Mars Climate Orbiter per problema di conversioni (se due conversioni sbagliano allo stesso modo ed io uso sempre il valore sulla stessa scala non mi accorgero mai dell'errore). O magari è un anomalia che si verifica in un caso specifico che è difficile andare a provare. Anche se è presente non è detto che si manifesti.

- **Sbaglio** (**Mistake**): è la causa di un anomalia, è legato ad un essere umano.

    Gli sbagli possono avere diverse cause:
    - Battitura: scritto * invece che +. Sono una grossa fetta degli errori che si commettono e non hanno una gran ragionevolezza, gli errori si annidano negli angoli perchè dove meno guardo (cammini fatti meno frequentemente) è più probabile che ci sia un errore che non viene scoperto. L'esecuzione normale è quello che ho in mente e che sto cercando di verificare ma quelli eccezionali sono quelli che non ho tenuto conto. (posso ordinare la lista vuota?) + legge di murphy (quel cammino che è improbabile e non ho curato bene verrà usato per dimostrare agli altri che ho programmato bene).
    - Concettuale: non so cosa voglia dire raddoppiare. Non ho capito bene cosa mi è stato chiesto, non sapevo bene come si dovesse fare, non l'avevo capito. Dominio diverso dal mio = fraintendimenti.
    - Padronanza del linguaggio: credevo che in Java * fosse il simbolo dell'addizione.

    A cosa serve fare queste distinzioni? Se capisco perchè ho inserito quell'errore posso intraprendere azioni correttive per il futuro: scarsa conoscenza del linguaggio $\rightarrow$ ti faccio fare un corso, non hai capito bene il concetto $\rightarrow$ ti faccio dialogare ancora con il cliente, fai sempre errori di battitura $\rightarrow$ ti licenzio perchè non stai attento.

    Capire la motivazione dell'errore permette di educarmi e migliorarmi in modo da non ripeterlo, non sempre si capisce, può essere un po'borderline ma comunque mandiamolo a fare un corso di JavaFX. Accusarsi d'ignoranza è la cosa che facciamo di meno (no no io il linguaggio lo domino perfettamente). Le azioni che seguono possono variare.

    Caso Ariane5: lancio il razzo e lo vedo esplodere dopo 40 secondi (malfunzionamento, non è il comportamento voluto), successivamente indago e scopro che il sistema di controllo inerziale ha smesso di funzionare, questo è dovuto all'eccezione overflow non gestita di un numero int64 a int16 (anomalia). Lo sbaglio è stato riusare del codice dell'Ariane4 per l'ariane5, non hanno capito bene le precondizioni del codice e non hanno fatto testing.
    
    Criticità nel processo di sviluppo, no test, mancanza specificazione requisiti.

# Tecniche statiche vs tecniche dinamiche

Non ce n'è uno da preferire, hanno potenza e applicabilità diversa. È più facile ideare tecniche dinamiche (basate sull'esecuzione, come il testing), tecniche statiche più difficle ma hanno un grosso vantaggio, quanto sono complesse una volta ??ideate?? cioè su cosa lavorano? Lavora sul codice, la sua complessità dipenderà dagli elementi sintattici del codice (es. ?). Il fatto che un programma abbia dei cicli che vengono eseguiti all'inifinito e producono infiniti stati/configurazioni possibili questo l'analisi statica non lo considera.

Ho 3 righe faccio presto ad analizzarle, magari eseguendo non terminerà mai. Tecniche dinamiche invece di lavorare sugli elementi sintattici lavorano sugli stati raggiungibili durante l'esecuzione e lo state explosion (esplosione dello spazio degli stati) è uno dei problemi principali di qualunque tecnica di analisi.

Spesso si cerca di construire l'intero dominio degli stati raggiungibili per capire come può evolvere il sistema.
Teniche dinamiche includono tutto quello che è basato sull'esecuzione come testing e debugging.

### CLassificicazione delle tecniche

< img piramide >

Ci sono 3 dimensioni con cui posso caratterizzare quello che stò facendo, il punto ideale è la cima della piramide = sono perfettamente in grado di dimostrare una proprietà arbitraria ad esempio mediante una dimostrazione logica o un testing esaustivo (provo tutti i casi possibili e funziona con tutti). Il problema è che quella cima è praticamente sempre impossibile da raggiungere, bisogna trovare un modo per avvicinarsi il piu possibile.

- **Propetà semplificate**: Io non volevo veramente quella proprietà ma un altra più semplice, mi accontento di qualcosa di piu banale ma che riesco a verificare (non più una propietà arbitraria). Es: Verificare che uso sempre i puntatori in modo corretto (impossibile) voglio controllare di non riferirmi mai ad un puntatore non inizializzato (quando accedo non è null), questo posso dimostrarlo.
- **Inaccuratezza pessimistica**: ad esempio metodi formali (analisi statica), analizziamo il problema e cerco di dimostrare l'aassenza del problema, se non ci riesco allora potrebbe esserci. e costringo a cambiare. La centrale può esplodere. Magari non poteva ma se non risco a dimostrarlo non va bene. Non semplifico la domanda ma cambio il codice per rendere dimostrabile. Soprattuto utile nei casi in cui le conseguenze dell'essere ottimisti sono gravi.
- **Inaccuratezza ottimistica**: è quello che si fa con il testing, cerco dei malfunzionamenti stimolando con alcune esecuzioni, finchè trovo malfunzionamenti ho fatto una buona attività di testing (sono felice) ad un certo punto vorrei che l'attività di testing fallisse (non trovo malfunzionamenti). Il fatto di non trovare piu nulla non vuol dire che il programma funziona, vul dire che non sono stato abbastanza bravo a trovare degli errori o magari in qualche caso davvero non c'erano. Aumento la fiducia del corretto funzionamento del programma ma non mi da la certezza.

Le tecnche di testing possono essere molto varie:
- white box: possiamo guadare il codice (scatola trasparente) quindi possiamo decidere come testare il codice guardando cosa c'è scritto. Test strutturale: ragioniamo il testing avendo a disposizione la struttura del codice invece che solo le specifiche (sarà testing funzionale).
- black box: eredito un componente di altri e non posso guardarci dentro ma posso testarlo dall'esterno, guardo dal punto di vista esterno stimolo -> risultato senza sapere cosa c'è dentro (non sto cercando anomalie ma malfunzionamenti).
- gray box: non conosco il codice ma piu o meno so come è fatto (usa model view controller, immagino db interrograto dal modello e che certe stimolazioni facciano query al database ed altre no). Nel momento in cui decido come testare se faccio solo stimolazioni che non arrivano al db non ho testato bene tutto. COnoscere i compoentni/architettura del sistema mi può dare delle idee su come testarlo.

Il TDD non è white box, si scrive prima il test non c'è codice! è black box.

## Debugging

Ha come compito: dato un programma e un malfunzionamento noto scoprire qual'è l'anomalia. Usare tecniche di debugging su un programma che non sappiamo per quali valori fallisce è un attività dispendiosissima perchè non è fatto per la grande esecuzione ma per esaminare nei particolari un piccolo punto dell'esecuzione. Produzione degli stati intermedi, guardo passo passo (decido io: singola istruzione piuttosto che la chiamata ad una funzione) e guardo come cambia la rappresentazione in memoria alla ricerca di uno stato inconsitente rispetto alla mia aspettativa. Questa velocità ridotta mi impedisce di fare una ricerca sensata in generale, bisogna partire da un malfunzionamento noto e riproducibile posso perdere tempo a cercare in un caso in cui non si verifica.

Programma che crasha ancora prima di eseguire un print, problema è nelle librerie importate e nella loro inizializzazione (non il mio codice), unico modo è fare versioni che rimuovo libreria una alla volta finchè non trovo.

## Correttezza

Spesso il problema che voglio risolvere è di comunicazione tra noi e cliente o analista e sviluppatore o sviluppaotre e Quality Assurance?non so se scritto bene, sono tutti problemi di comunicaizone. In quelli con il cliente siamo noi che ci adeguiamo a lui però man mano che ci spostiamo internamente possiamo richiederci uno sforzo maggiore di formalità e chiarezza, disambiguazione dei termini (piu siamo chiari e meno problemi).

Consideriamo un programma $P$ (lo vediamo come qualcosa di isoltato dal resto e che lavora solo sui parametri che gli passiamo, no stato oggetto) come una funzione che va da un dominio $D$ (insieme dei dati) a un codominio $R$ (indieme dei dati).
$P(d)$ indica l'esecuzione del programma sull'insieme di dati specifico $d \in D$.
Il risultato $P(d) \in R$ è corretto se rispetta le specifiche dato, altrimenti è scorretto.
$ok(P,d)$ indica la correttezza di $P$ per il dato $d$.
$P$ è corretto solo se $\forall$ $d$ $\in$ $D$ $ok(P,d)$ un programma è corretto quando per ogni dato del dominio è ok, si indica con $ok(P,D)$.

Un test $T$ (diverso da caso di test) è un sottoinsieme del dominio $D$, un elemnto $t$ di $T$ è detto caso di test, la singola stimolazione. Eseguire un test significa eseguire tutti i casi di test.
Un programma passa o supera un test $ok(P,T) \leftrightarrow \exists t \in T \neg ok(P,t)$.
Un test ha successo se trova dei malfunzionamenti nel programma. $successo(T,P) \leftrightarrow \exists t \in T \neg ok(P,t)$.
Alcuni la fanno piu stringente: devo scoprire un malfunzionamento legato ad un anomalia non ancora scoperta.

## Test ideale

la cosa piu bella che potrebbe accadere è che se il programma passa il test (il test non ha successo, non trovo malfunzionamenti) se questa cosa implicasse che il programma è corretto sarei felicisismo. (formula)

Nella realtà non è possibile, il test è un attività intrsinsecamente ottimistica, il fatto che ok(P,t) non vuol dire che è ok anche su tutto il dominio.

Un ponte se regge 4000 chili regge anche un peso minore ma nel software questo non vale. Sui valori che non ho provato non posso dire nulla.
Tesi di Djiskstra: il test di un programma può rilevare la presenza di malfunzionamenti ma mai dimostrarne l'assenza. Non esiste un algoritmo che dato un programma P generi un test ideale finito (il caso T=D non va considerato). In realtà se sono pochi (solo un booleano) ok ma non in generale. NOn si può fare test esaustivo.

Testo in modo esaustivo la somma di due numeri:
in Java un int ha 32bit, quindi il dominio ha cardinalità $2^{32} * 2^{32} = 2^{64} \approx 2 * 10^{19}$.

Una CPU da 1Ghz ci mette 1 nanosecondo a fare una somma, quindi ci volgiono $2 * 10^{19}$ secondi, ovvero più di 600 anni. Ci sono CPU piu veloci ma ci sarebbe l'inizializzazione dell'ambiente, la verifica, ecc ecc potrei mettere piu computer in parallelo a verificare ma ci vuole comunque tanto.

COme verifico il risultato giusto? (problema dell'oracolo) per ogni somma devo sapere qual'è il risultato corretto con cui confrontarmi, serve qualcuno che me lo dica, può essere un altro programma dimostrato corretto o qualcuno che li fa a mano.

In questi 600 anni cambierà anche la tecnologia, cosa ci importa dismostrare qualcosa di un sw vecchio, diventa ingestibile. Il test esaustivo nella pratica non è possibile. Bisogna riunciare in partenza.

## Criterio di selezione

Diventa importante capire come selezionare il sottoinsieme (quali casi di test prendiamo?) cercheremo di farlo in modo intelligente, alcune caratteristiche auspicabili dei casi di test.

In letteratura si parla di **criterio affidabile** è una proprità che può avere un criterio che dice è affidabile se presi due test in base al criterio allora o entrambi hanno successo o entrambi falliscono.

Posso fidarmi di qualsiasi test selezioni in base al criterio mi dia un risultato uguale a quello che mi avrebbe dato qualunque altro test.

Un criterio è affidabile su uno specifico programma (non posso dirlo in assoluto) se $\forall T1 \in C, \forall T2 \in C successo(T1,P) \leftrightarrow successo(T2,P)$. (se il successo del primo coimplica il successo del secondo).

Altra caratterstica validità, un criterio si dice valido se il programma non è corretto allora esiste almeno un test selezionato in base al criterio che mi trova l'errore.
$valido(C,P) \leftrightarrow (\neg ok(P,D) \rightarrow \exists T \in C successo(T,P))$.

Uno potrebbe volere un criterio valido e affidabile.

Esempio su raddoppia:
- criterio che seleziona sottoinsiemi di {0,2} è affidabile ma non valido.
- criterio che seleziona sottoinsiemi di {0,1,2,3,4} è valido ma non affidabile
- sottoinsimi finiti di D con valore > 18 è affidabile e valido

I criteri si scelgono prima di conoscere i malfunzionamenti.

Se un test selezionato da un criterio valido e affidabile non trova errori, dato che è affidabile anche tutti gli altri test non troveranno errori e dato che è valido se c'era un errore almeno uno dei test l'avrebbe dovuto trovare IMPLICA che il test è corretto.

Il problema è che non riusciamo a trovare nessun criterio che sia contemporaneamente valido e affidabile, NON ESISTE.

Immagine disegno
D è insieme dei dati, i test sono dei suoi sottoinsiemi composti da vari casi di test. Se vogliamo che un criterio sia affidabile = presi 2 test il fallimento di uno comporta il fallimento dell'altro e stesso su corretezza (senza conoscere il programma è difficile da ottenere).
L'unico caso in cui ho un criterio di selezione affidabile è il mio criterio seleziona un unico test.
Affidabilità è un idealizzazione, posso molto probabile, che mi illuda di averla ma non riesco ad ottenerla per qualunque programma.
Rinunciamo all'affidabilità, proviamo la validità.

img disegno 2

Sottinsimi di D e un unico caso di test per cui il programma non funziona, come definiamo un ciriterio prenda un test che sicuramente comprenda quel valore?
L'unico modo è prendere tutto D ma è infinito, per definizione i test sono finiti quindi non posso coprirlo tutto. Posso coprirlo con infiniti test composti da un numero finito di casi di test.

Un criterio con infiniti test che hanno un singolo caso di test e questo è valido.
Però non ci guadagnamo nulla, la probabilità di trovare malfunzioanemnto era 1/infinito ora è 1/infinito.

In un caso devo avere infiniti test, nell'altro 1 solo le due cose non stanno assieme. Serve un unico test che copre l'intero dominio.

Questo è ideale lasciamo perdere.

COme ragionare allora? Identificare caratteristiche utili (che rendano probabile o possibile al caso di test di trovare degli errori). Piu che rendere possibile so quando un caso di test non rende possibile trovare l'errore perchè posso fissare delle condizioni necessarie.

1. eseguire il comando che contiene l'anomalia (se non lo eseguo il programma non mostrerà alcun difetto).
2. ci sono valori per cui anche se eseguita la riga non si manifesta, deve produrre uno stato scorretto
3. lo stato scorretto deve propagarsi in modo da produrre un output diverso da quello atteso.

Ogni criterio che definiremo avrà anche una metrica di soddisfacimento, dato che non possiamo avere affidabilità e validità poniamo il caso di non poter raggiungere il crieterio completamente, definiamo una metrica l'ho coperto/soddisfatto al 90% 70%, colleghiamo una metrica che ci serve a diverse cose:

1. decidere quando smettere, potenzialmente l'attività di testing non finisce mai, posso sempre aggiungere casi di test per centinaia di anni ma devo pur consegnare qualcosa, ad un certo punto bisogna fermarsi, cosa mi dice che posso fermarmi? Il manager dice quando finisco le risorse/il tempo a disposizione ma l'ingegnere del sw dice quando la metrica che voglio soddisfare ha superato una soglia decente. Misura ottimistica che dice che la mia attività di testing è stata sufficiente.
2. decidere un caso utile da aggiungere (se non aumenta non ha senso aggiungerlo)
3. confrontare test diversi

Primo criterio:

# Copertura dei comandi

Devo eseguire la riga che contiene l'anomalia -> dato che non so qual'è devo eseguirle tutte, devo coprire i comandi del prgoramma.

    Un test T soddisfa il criterio di copertura dei comandi se e solo se ogni comando eseguibile del programma è eseguito in corrispondenza di almeno un caso di test t contenuto in T

Comandi eseguibili = escluse istruzioni non raggiungibili

Esempio prof. divisione per 0, trasformo in un diagramma a cui ogni comando corrisposnde un nodo, dire che voglio eseguire tutti i comandi vuol dire voglio passare per tutti i nodi del grafo. Voglio trovare un insisme di casi di test in cui passo per ogni nodo almeno 1 volta.
Ad esempio <3,7> copre tutti i cmandi ma non mi garantisce che il programma è corretto, <0,7> eseguirebbe una divisione per zero.

Qualunque criterio che selezioneremo non sarà in grado di trovare tutti i malfunzioanmenti, si bilancia fatica e fiducia.


Perchè non troviamo il malfunzonamento nell'esempio? Il comando viene eseguito, far si che generi uno stato sbagliato (nel nostro caso con 0,2 non si manifesta) allora devo eseguire con tutti i possibili valori (visione pessimistica) oppure so che è impossibiile e so che in questo comando qua ci posso arrivare da due frecce.

Se questo ha senso non possiamo accontentarci di coprire i nodi.

# Copertura delle decisioni

Dobbiamo coprire tutte le frecce del nostro grafo (nodo con piu frecce che escono = possibile diramazione = decisione = if o ciclo).

    Un test T soddisfa il criterio di copertura delle decisioni se e solo se ogni decisione effettiva viene resa sia vera che falsa in corrispondenza di almeno un caso di test t contenuto in T

Ci dev'essere una situazione in cui viene eseguito il ramo vero e una il ramo falso.
Effettiva perchè non è detto che sia una decisione vera che posso prendere, se sempre vera o falsa non posso farla fattualmente, if(x>0){ if(x>2){...} } sempre vero il secondo if.

La copertura delle decisioni implica la copertura dei comandi, se prendo un test che copre le decisioni sono sicuro che copre anche i comandi.

In questo caso per coprire al 100% le decisioni dell'esempio ho bisogno di almeno 2 casi di test: ad esempio <3,7> e <0,5>.
Trova tutti i malfunzionamenti? NO.

Cambiamo leggermente il programma e aggiungiamo un ramo else. Vediamo se il criterio di copertura lavora bene o possiamo fare altri ragionamenti.

Anche in questo caso bastano 2 casi di test: <3,7> e <3,-2>. Cambio il valore della decisione senza cambiare la prima parte. Non trova tutti i malfunzionamenti (qualunque caso <0,X>).

Il problema è nel fatto che anche seguendo le frecce, il fatto che ci arrivo con la prima parte vera o falsa è signficativo, non basta la decisione nel suo complesso ma anche perchè era vera/falsa (cosa l'ha resa vera/falsa).

# Copertura delle condizioni

    Un test T soddisfa il criterio di copertura delle condizioni se e solo se ogni singola condizione (effettiva) viene resa sia vera che falsa in corrispondenza di almeno un caso di test t contenuto in T

Non implica le due precedenti.

X | Y | Decisione
---|---|---
0 (F)|5 (T)|T
5 (T)|-5 (F)|T

Pur coprendo le condizioni non copro le decisioni e i comandi.

# Criterio di copertura delle decisioni e delle condizioni

    Un test T soddisfa il criterio di copertura delle decisioni e delle condizioni se e solo se ogni decisione vale sia vero che falso e ogni singola condizione che compare nelle decisioni del programma vale sia vero che falso per diversi casi di test in T

X | Y | Decisione
---|---|---
0 (F)|-5 (F)|F
5 (T)|5 (T)|T

Copro sia le condizioni che le decisioni, al lato pratico mi accorgo che non basta ancora. Non trova divisione 0 alla riga 6.

# Criterio di copertura delle condizioni composte

    Un test T soddisfa il criterio di copertura delle condizioni composte se e solo se ogni possibile composizione delle condizioni base vale sia vero che falso per diversi casi di test in T

X | Y | Decisione
---|---|---
0 (F)|-5 (F)|F
0 (F)|5 (T)|T
5 (T)|-5 (F)|T
5 (T)|5 (T)|T

Questa cosa esplode molto rapidamente. Il numero di casi di test necessari a coprire questo criterio in programmi reali cresce troppo rapidamente.

Diventa piu difficile capire quali combinazioni sono fattibili, senza considerare la valutazione cortocircuitata. CI servono tutte queste combinazioni oppure ho un modo per riconoscerne alcune piu significative di altre?

Ragionandoci su ci si è accorti che alcune combinazioni sono piu vicine/lontane alle altre, se lavorando toccando solo una condizione riesco a modificare l'effetto della decizione allora quella modifica è molto significativa. Se invece toccando una condizione la decisione rimane uguale il cambiamento è meno significativo. SI da importanza nella selezione delle combinazioni che vogliamo effetture al fatto che la modifica di una singola condizione base porti a modificare la decisione nel suo complesso.

Si può dimostrare che date N condizioni base servono solo N+1 casi di test.

X | Y | Decisione
---|---|---
0 (F)|-5 (F)|F
0 (F)|5 (T)|T
5 (T)|-5 (F)|T

CI sono casi in cui quella rimossa è esattamente quella che avrebbe permesso di scoprire l'anomalia. Questi sono euristiche, sono tentativi che non possono per definizione andare bene/essere ottimali per qualunque programma.

Fissato un programma si può sempre trovare un programma che su quel criterio si comporta peggio di qualcosa di piu leggero e semplice.

immagine implicazioni criteri di copertura + riga applicabile per progetti reali

Allora applichiamo sempre il criterio delle decisioni/condizioni modificate, cosi testiamo tutto perchè è applicabile bellissimo. Poi guardiamo un programma leggermente diverso che ha i cicli, in questo momento non ho fatto nulla di specifico per i cicli.

Quando ho fatto la scelta una volta sono contento, se trasformo il while in un if per il criterio è la stessa cosa. Se avessimo fatto solo dei test che pur aderendo ai criteri visti fino adesso stimolavano con delle condizioni [] non mi accorgevo che il while era sbagliato. Nella maggior parte dei casi i test stimolerebbero il ciclo per più di un interazione ma non è richiesto, non è una cosa su cui si pone attenzione. Non stanno verificando l'essenza del concetto di ciclo, ripetere gli stessi blocchi di istruzioni. Spesso non è alla prima iterazione che ho il problema. I test precedenti fanno NON CI ENTRO / CI ENTRO ALMENO UNA VOLTA ma entrarci piu di una volta non è necessario.

# Criterio di copertura dei cammini

    Un test T soddisfa il criterio di copertura dei cammini se e solo se ogni cammino del grafo di controllo del programma viene percorso per almeno un caso di test in T

Coprire tutti i cammini topologici del grafo, in un if-else sono 2 mentre nel while sono di più e stabilirli a priori non è cosi facile. Molto generale ma spesso impraticabile anche per programmi semplici.

# Criterio di N-copertura dei cicli

    Un test soddisfa il criterio di n-copertura se e solo se ogni cammino del grafo contenente al massimo un numero di iterazioni di ogni ciclo non superiore a N viene percorso per almeno un caso di test

Si limita il numero massimo di percorrenze dei cicli, N non è devo farlo N volte ma "per ogni numero tra 0 e N deve esserci un caso di test che esegue il ciclo quel numero di volte". Al crescere di N esplode abbastanza rapidamente, fissare N a livello di programma può essere difficile, potrebbero esserci dei cicli che arrivano fino a 4 altri fino a 10000000, se metto 8 quelli 4 non posso soddisfarli. DOvrei fare il numero minore tra il mio e quello possibile. Siccome esplode con N il tentativo è cercare di tenerlo basso.

Specie di mantra nel testing "0 1 molti" tra questi molti il piu piccolo è 2.

### Caso n = 2

È la minima che permette di testare le caratteristiche del ciclo. COsi controlliamo:
- casi in cui non devo entrare (n=0)
- casi in cui entro una volta (n=1)
- casi in cui rimango piu di una volta (n=2)

Questi casi sono anche rimappabili sul discorso precondizioni postcondizioni invarianti di ciclo (mezzo automatico con cui si cerca di fare una dismostrazione di correttezza delle funzioni).

Immagine aggiornata delle implicazioni tra criteri

Rimanendo nel testing strutturale (con il codice a disposizione) cosa possiamo fare ancora?
Possiamo fare considerazioni sul flusso dei dati? dovremmo eseguire il programma non si può fare staticamente.

Entriamo nel campo dell'**analisi statica**: lavora sugli elementi sintattici del programma (hanno numerosità finita molto piu limitata dell'analisi dinamica -> stati raggiungibili).
Molto meno costosa del testing. La useremo per guidare l'attività di testing vedremo una tecnica di analisi statica che ci da degli elementi per decidere altre caratteristiche sintattiche del nostro codice che vogliamo coprire e che ha senso coprire. Di per se l'analsi statica non può trovare anomalie dovute ad un valore specifico perchè non ragiona su valori specifici delle variabili.

In realtà la usiamo tutti i giorni:

Compilatore:
- analisi lessicale: identificazione token del linguaggio
- analisi sintattica: identifica relazione tra token
- controllo dei tipi: verifica violazioni regole uso tipi
- analisi flusso dei dati: rileva problemi relativi all'evoluzione del valore associato alle variabili

## Analisi data flow

I primi risultati sono stati ottenuti nel campo dell'ottimizzazione dei compilatori, quello che possiamo fare è identificare una serie di operazioni rilevabili a livello di codice sintattico e legami di compatibilità tra queste operazioni, regole di uso di queste operazioni. Le operazioni possono essere anche molto semplici. Identifichiamo 3 situazioni:
- definizione: non è solo l'assegnamento, passaggio parametro ad una funzione per riferimento (la funzione può cambiarlo)
- uso: quando leggo il contenuto di una variabile, può essere espressione lato destro assegnamento, parametro, espressione dentro un parametro, ...
- annullamento: se al termine dell'esecuzione dell'istruzione il valore della variabile non è più significativo/affidabile, quando non posso sapere cosa c'è dentro, non posso contare su quello che c'è dentro. Quando dichiaro una variabile in C "int a;" l'ho dichiarata ma non l'ho inizializzata. Molto spesso cosi si prendeva un seme per una sequenza pseudocasuale. Non so cosa c'è = casuale SBAGLIATO. non affidabile non vuol dire casuale ma che non so cosa c'è, magari c'è sempre 0. Non ho chiesto al compilatore di fare un operazione specifica, lui può fare quello che vuole.

Premessa falsa -> qualsiasi cosa va bene.

Magari compilati con alcuni compilatori alcuni programmi si comportavano in modo più o meno casuale, compilati con altri fanno sempre la stessa cosa. Non posso dire sono sicuro che c'è 0.

Un altro caso in cui si annulla è quando si esce dallo scope di visibilità di una funzione, lo spazio di memoria delle variabili locali cosa contiene? Non lo so. QUando esco dallo scope le variabili locali vengono deallocate, quindi quella zona di memoria può essere riallocata ad altre cose.

L'analisi del data flow è quella in cui per ogni variabile del mio programma si crea/si va a guardare le sequenze di istruzioni tradotte in annullamenti definizione e usi della variabili.

Vado a cercare di capire se ci sono delle situazioni anomale/strane/pericolose.

Regole:
- L'uso di una variabile deve sempre essere preceduto in ogni sequenza da una definizione senza annullamenti intermedi. QUesto si traduce come il cercare nella nostra sequenza una coppia di lettere che identifica questa situazione "au" (annullamento-uso).
- La definizione di una variabile deve sempre essere seguita da un uso prima di un annullamento o definizione. (int a = 5; a = 7; ... uso a). (int a = 5 ... annullamento senza averla usata).
- L'annullamento di una variabile deve essere sempre seguito da una una definizione prima di un uso o di un altro annullamento. (vieto aa = variabile non usata = annullo dichiarazione e annullo dealloc).

.|a|d|u
---|---|---|---
a|ERR||ERR
d|ERR|ERR|
u||

< codice in C >

void swap(int &x1, int &x2){
    int x;
    x2 = x;
    x2 = x1;
    x1 = x;
}

variabile|sequenza
---|---
x|auua
x1|...dud... (no annullamento perchè è riferimento ha scope esterno)
x2|...ddd...

Anomalie rilevabili:
- x viene usata due volte senza essere prima definita
- x1 ok?
- x2 viene definita più volte senza essere usata nel frattempo

Tutto questo viene scoperto senza eseguire il codice ma solo ragiondaci su.

Ci sono algoritmi che permettono di trovare queste caratteristiche in modo molto efficiente con 1 solo passaggio o nella maggior parte al massimo 2 passaggi.

Esempio:

< codice >

riga|annullamento|definizione|uso
---|---|---|---
1|x y a b
2|||x
3|||y
4|||a|x
5|||b|y
6||||a b
7||||a b
8|||a'|a b
9||||a b
10|||b'|a b
11||||a
12|x y a b
13

Può essere importante distnguere se l'uso è di tipo predicato o di tipo comando (interno di condizione che fa scegliere cammini diversi o è assegnamento variabile?).

Riga 9 la 'a' viene sia usata che definita, se mettiamo entrambe queste operazioni in riga 9 non riusciamo a testare se prima dell'uso c'è una definizione .... quidni bisogna disambiguare (il prof l'ha fatto con a' che viene dopo 'a' normale).

Cosa faccio di questo calcolo?

Mi interessa guardare su una specifica sequenza di comandi una specifica variabile, poi lo farò per tutte me non tutto assieme. Incichiamo con $P(p,a)$ la sequenza ottenuta per la variabile $a$ eseguendo il cammino $p$.
$P([1,2,3,4,5,6,7,8,9,7,12,13],a) = a_2 d_5 u_7 u_8 u_9 d_9 u_7 u_{12} a_{13}$

Cosi lo facciamo per delle esecuzioni definite, eseguiamo un certo cammino, sappiamo che questi cammini sono potenzialmente infiniti quindi corrisponderebbe a qualcosa tipo testing. Quello che ci può venire in mente è di usare delle espressioni regolari.

Partendo da 1 e andando avanti, tutte le possibili evoluzioni partendo dal nodo 1.
$P([1 \rightarrow], a)$
Il corpo del ciclo posso avere N esecuzioni.

... traduzione

Era l'unica traduzione possibile? Riesco a fare controllo senza grosso problemi ma a differenza di prima esprime tutti i possibili cammini.

In generale nei programmi una rappresentazione di questo genere non garantisce che siano tutti e solo, è impossibile pensare ad un algoritmo che dato un programma arbitrario riesca a costruire un espressione del genere che faccia tutti e soli se non andando a guardare dentro ai valori (cosa che non vogliamo fare). Per sua natura è un astrazione pessimistica, richiedo che su questo modello che comprende tutte le esecuzioni ma ce ne sono anche delle altre non si presenti il problema. Se si presenta nel modello ma in casi che non sono fattibili la mia anlisi dice comunque che il programma ha problemi anche se in effetti non ce l'ha. Questo non stupisce perchè analisi statica = tecniche di inaccuratezza pessimistica.

Prendiamo un modello che approssima ma togliendo i particolari, è piu importante che tratti tutto piuttosto che il contrario, dice è perfetto e in quelli non rappresentati ci sono problemi.

È l'unica rappresentazione? Posso farla in modo piu ragionato o meno ragionato?

Rappresentazioni troppo astratte non ci servono a nulla [ (dua)* ], non servono a nulla anche quelle che dato il problema non riescono a dire cosa nel codice/in quale punto del codice si predenta quel problema.

Questo modo visto prima permette di tracciare e mantenere la corrispondenza tra operazioni ed eventuali astrazioni delle operazioni dell'analisi statica.

Perchè ci serve l'analisi statica per il testing?
- deve essere eseguita un operazione che generi uno stato inconsitente, possiamo dire qualcosa di piu preciso, se l'anomalia è in una riga in cui si fa una definizione, affinche l'errore si propaghi e si diffonda all'esterno questo valore deve essere letto. Se non uso il valore definito erroneamente non me ne accorgerò mai.
- Un ciclo dovrebbe essere ripetuto N volte, proviamo a ragionare sui singoli cicli e non a livello di programma potrebbe aver senso rieseguire un altra volta se verrà usato all'interno del ciclo un valore che ho appena definito nel ciclo (uso di un valore dall'iterazione all'altra) altrimenti se entrando riuso le stesse cose dell'iterazione precedente posso evitare di farlo un altra volta.

L'idea è: selezioniamo casi di test tenendo conto di queste sequenze definizione-uso, gli annullamenti ci interessano meno.

Definizioni:
- $def(i)$ è l'insieme delle variabili che sono definite in $i$ ($i$ è un comando)
- $du(x,i)$ è l'insieme dei nodi $j$ tali che:
    - $x \in def(i)$
    - $x$ usato in $j$
    - esiste un cammino da $i$ a $j$, libero da definizioni di $x$

# Criterio di copertura delle definizioni

    Un test T soddisfa il criterio di copertura delle definizioni se e solo se per ogni nodo i e ogni variabile x appartenente a def(i) T include un caso di test che esegue un cammino libero da definizioni da i ad almeno uno degli elementi di du(i,x)

Per ogni assegnamento che faccio ci dev'essere almeno un uso.

< formula logica >

du(5,a) = {7,8,9,11,12}
du(9,a) = {7,8,9,11,12}

In questo caso gli usi sono uguali (non fatto apposta), per ognuna delle due definizioni avere un uso di quella definizione.

$d_5 u_7$ viene gratis
$d_9 u_7$ basta entrare una volta nel ciclo ($T = \{ <8,4> \}$).

Bastano questi due per soddisfare il criterio.

Questo criterio implica:
- copertura dei comandi NO da un alto va più in profondità chiede qualcosa di più intelligente dall'altro lascia passare altre cose, copre le definizioni non sto coprendo tutto.
Vediamo di coprire qualcosa in più.

# Criterio di copertura degli usi

    Un test T soddisfa il criterio di copertura degli usi se e solo se per ogni nodo i e ogni variabile x appartenente a def(i) T include un caso di test che esegue un cammino libero da definizioni da i ad ogni elemento di du(i,x)

Ho definito in un certo punto una variabile, ho fatto una definizione, tutti i possibili usi di quella definizione devono essere coperti

< formula logica >

È utile perchè, abbiamo detto nodo i, nodo j qui viene definita la x, qui viene letta la x e qui c'è un cammino che passa da altri nodi, l'importante è che non ci sia la definizione. Questo è quello immaginato da chi ha detto che non è utile.

Chi ha detto che è utile (era domanda del prof a noi) aspetta ma se ci fosse anche un altro cammino in cui ridefinisco la x e anche lui poi arriva a j, j appartiene all'insieme ma se non dico che il test deve stimolare quel cammino che ha quella caratteristica potrei avere un caso di test che va da i a j passando da w che la ridefinisce.

Una cosa è dire che è una condizione necessaria per ppartenere ma che non è detto che venga sempre soddisfatta dati solo gli estremi.

Altra domanda, include il precedente? NO se non ci sono J non devo soddisfare nulla. Se ho una definizione che non ha nessun uso, qui sono obbligato a coprire tutti gli usi della definizione (qua non chiede nulla) ma nell'altro no (pongo l'insieme non vuoto).

Anche questo a maggior ragione non è detto che copra i comandi.

Esempio: fallo tu poi che sono le 22:22 (sono serio) e sono stanco

Meglio minimizzare il numero dei casi di test o delle iterazioni per caso? Il testing serve per trovare malfunzionamenti, questo ci serve per iniziare l'attività di debugging, se ho un unico caso di test che dura 1 ora che poi mi dice sbagliato! riuscire a trovare in questa ora di esecuzione cosa è andato male, quando ha iniziato ad andare male è più difficile che un caso di test che esegue 10 istruzioni. Partiziona e permette di identificare più facilmente la cosa. TEMPO

Ci metto di piu a eseguire tanti casi semplici o uno complicato? Difficile da stabilire. È vero che il singolo caso dura di più ma eseguire N volte significa riavviare il programma N volte, se volessimo fare le cose bene Junit dovrebbe far ripartire una JVM N volte (1 per test) ed è un tempo inaccettabile, si accettano dei compromessi per l'efficienza. L'esecuzione di tanti casi di test è più oneroso di farne solo uno anche se ha più istruzioni perchè c'è molto più overhead però è difficile da misurare.

Su un obiettivo che prestazioni che è dubbio, e le prestazioni in generale vabbe tra un po' sarà piu veloce, tenendo ragionevolmente piccola la cosa (invece che 1 facciamo 4) posso pensare di farlo, dall'altro invece ho lo scopo del test

# Criterio di copertura dei cammini DU

Abbiamo detto che ci sono piu cammini, devo coprirli tutti. Aumento e diventa impraticabile (sopra la linea rossa).

Oltre alle variabili il ragionamento si può fare per altri "oggetti"? SI, per qualunque concetto su cui posso identificare certe tipologie di operazioni e delle regole che valgono su queste operazioni posso ragionare in questi termini.

Esempi: file
- **a**pertura
- **c**hiusura
- **l**ettura
- **s**crittura
Poi le regole.... non ho voglia, copia tu ti prego

Altri esempi: semafori e monitor, hanno un insieme finito di operazioni in cui ha senso ragionare su certe sequenze. SUona familiare questa cosa rispetto alle lezioni di questo corso? Quando abbiamo ragionato sul diagramma degli stati non abbiamo detto qualcosa di simile? Un oggetto risponde a certe tipologie di eventi (non ci siamo occupati dei valori effettivi dei parametri) e abbiamo detto "ci porta in certi stati e in certi stati non sono ammesse alcune operazioni".

Avrei potuto scrivere delle mie variabili definizione-uso digramma a stati finiti di cosa poteva accadere in quelle situazioni e poi guardarlo sulle evoluzioni, su come veniva usato [parola incomprensibile INSTALLAMENTE???]. La definzione della macchina a stati finiti mi dice operazioni/regole, il programma in base a come lo usavo controllavo che fosse coerente con il diagramma a stati finiti, non è una cosa campata per aria, possiamo ragionarci anche a livelli più astratti.

Spesso l'unico criterio usato per decidere quando smettere è il criterio di copertura del budget, quando sono finiti i soldi e il tempo.

Cos'altro possiamo fare?

# Beebugging

Se l'organizzaione lo permette (c'è personale dedicato al testing post sviluppo) allora c'è il grosso pericolo che chi riceve qualcosa da testare si ritrovi nella situazione di non trovare errori (capita poco spesso). Non ci sono bug o è lui che non è in grado di trovarli? Un po di errori vengono aggiunti e diciamo quanti sono, se non si dice quanti sono potrebbero esserne trovati meno e dire "il resto è ok", sapere quanti bug ci sono finchè non sono stati trovati tutti non si smette, si continuano ad aggiungere test. Mentre faccio la fatica di trovare questi errori magari ne trovo alcuni che non sono stati messi apposta. È uno stimolo per fare bene l'attività di ricerca di errori e permette di trovare anche errori non inseriti apposta.
Se sono 10 errori, ne trovo 10 e vado a far vedere, 8 sono nostri e 2 no, questo dice che ce ne sono ancora 2 miei da trovare e permette di ragionare sulla qualità del software (hai trovato l'80% dei miei errori quindi hai trovato l'80% degli errori non miei però magari la tecnica di testing è adatta a trovare i tuoi errori e non quelli veri, non regge al 100% ma meglio di niente). Magari si può analizzare anche la tipologia degli errori trovati.

# Analisi mutazionale

Parto da un programma e potenzialmente almeno un test (composto da piu casi) con cui sto provando il mio programma. Potrei anche partire da zero e costruire il primo test ma è più comune usarla come metro di giudizio, come criterio di copertura per capire quanto è buono il test,se è soddisfacente o se aggiungere altri casi di test. Prende il programma $P$ e genera un insieme di programmi $\pi$ aggiungendo degli errori a quello originale, il test li dovrebbe trovare. Genera delle mutazioni/varianti del programma, mi aspetto che il mio programma è corretto e quindi quello generato è scorretto e allora il test se ne deve accorgere. Se $P$ è corretto allora i programmi $\in \pi$ son sbagliati quindi almeno un caso di test deve produrre un risultato diverso.

    Un test T soddisfa il criterio di copertura dei mutanti (o delle mutazioni) se e solo se per ogni mutante π ∈ π-maiuscolo esiste almeno un caso di test in T la cui esecuzione produca per π un risultato diverso da quello prodotto da P.

Non ho un programma con tante differenze ma diversi che hanno una sola differenza. Sono capace di riconoscere che sono due cose distinte, una sbagliata e un altra si spera che sia giusta ma non è detto (il nostro programma), comunque diverse per almeno un caso di test.

La metrica è la frazione dei mutanti riconosciuta come diversa da P sul totale di mutanti generati.

Aspetti da tenere in conto:
- analizzare le classi e generare i mutanti
- selezione dei casi di test
- esecuzione dei test

Nel momento in cui copro i mutanti al 70% come migliroo la metrica? Come eseguo? Se non consideriamo performance approccio non fattibile.

### Generare mutanti

Nel nostro caso ideale vorremmo avere programmi simili ma distinguibili (basta fare un diff per distinguerli). Simili vuol dire che non è che uno calcola la rotta del razzo e uno fa il caffè, devono essere identici tranne in un errore piccolo (ho scritto < invece che <=, un errore di battitura) più sono simili e più è difficile trovarli e quindi è più significativo. In teoria ci può essere un mutante per ogni possibile anomalia che può essere inserita nel codice, siccome le anomalie sono infinite abbiamo potenzialmente infiniti mutanti. Questo non è buono perchè la nostra attività di testing è gia complicata, riduciamo il test a 100 casi e devo eseguire 100 casi per infiniti mutanti (infiniti no ma comunque molti) è un fattore moltiplicativo che va a invalidare i vantaggi di eseguibilità raggiunti dai tempi ragionevoli, qualche centinaio-migliano bene, decine di migliaia inizia ad essere faticoso.

Bisogna generare quelli possibili ma che siano sensati, che abbiano una possibilità di mappare quello che può essere veramente un errore non qualunque cosa, vero che anche gli errori possono essere qualunque cosa ma qui cerchiamo di generarli in modo più sensato.

Quello che si fa è definire degli operatori mutanti, delle funzioni che dato un programma generano uno o piu mutanti facendo midifiche sintattiche minuscole che devono modificare la semantica del programma senza renderlo non compilabile.

Per ogni mutante ho la modifica del sorgente, la ricompilazione e il test, controllare che non si sovrapponga a quelli fatti prima, problemi di prestazioni. Quello che accade è che la maggior parte dei tool lavora già sull'eseguibile (in Java sul bytecode) cosi lo riesegue e basta senza recompilarlo.

Ci sono vari approcci, creare una sola classe e condizionalmente crea mutazioni oppure fare tante esecuzioni separate. L'importante è che lavorano facendo una singola modifica, farlo sul bytecode ha vantaggi di prestazioni ma ha anche un problema, potrei fare una modifica che non sarebbe stata generata da alcun sorgente Java, non è detto che quella modifica sia generabile in natura (compilando).

Ci sono studi più recenti che parlano di high order mutation, non c'è un unica modifica ma possiamo fare modifiche di codice modificato (applicare 2,3,... modifiche) e contrariamente a quello che si ipotizzava ci sono dei casi in cui trovare gli errori applicandone 2 è più difficile che quando ne applico una sola (uno maschera almeno parzialmente l'altro e quindi è piu difficile trovare il malfunzioanmento, richiede più fatica e quindi è più significativo riuscire a trovarli).

Dice se l'attività di testing è approfondita non se è corretta. I risultati dei test sono corretti o scorretti, più sono quelli corretti e più ho fiducia nel programma, qui non si fa questo, il mio discriminante è se ho un risultato diverso da quello di prima, non se è corretto, entrambi potrebbero darlo sbagliato. Quello che importa è che sia riuscito a distinguere, quanto è buona l'attività di testing non se il programma è corretto. Ha un effetto molto positivo, per fare il testing potrei non sapere qual'è il risultato corretto (storia dei 600 anni), ho u pogramma che esegue e una mutazione che esegue, se danno lo stesso risultato problema perchè il mio test non è stato capace di distinguerli, altrimenti sono contento, ma sapere che era corretto non mi interessa. Questo può essere usato per automatizzare parte del lavoro pesante del test.

Classi di operatori mutanti: si distinguono in base all'oggetto su cui operano.
- costanti e variabili (scambia occorrenze)
- operatori ed espressioni (< in <=, condizione diventa true o false)
- comandi (while in if)

Possono essere specifici di alcuni tipi di applicazioni:
- sistemi concorrenti (operano prevalentemente sulle primitive di sincronizzazione)
- sistemi object oriented (operano principalmente sulle interfacce dei moduli)

< img schemino >

Arricchisco la suite di test per renderla capace di distinguere più situazioni. Questo schema non garantisce la terminazione (estraggo test a caso che genera sempre lo stesso o devo essere particolamente fortuna per trovare quello corretto (10/100.000.000 abbastanza improbabile) non garantisce un risultato, al massimo si mette un timeout di un ora, quelli che trovo in un ora ok oppure solo 100.000 estrazioni se dopo 100.000 non trovo mi fermo, ogni volta che aggiungo test ripristino timer o contatore).

Magari non sono io ad essere sfortunato, è che i due programmi sono uguali (con una differenza sintattica ma uguali dal punto di vista funzionale). Ci possono essere infiniti programmi diversi ma corretti, problema di verificare se due programmi calcolano la stessa funzione è indecidibile nel caso generale. Per farlo dovrei eseguire l'intero dominio su entrambi e verificare che i valori prodotti siano sempre uguali (altro che 600 anni).

La modfica che ho inserito potrebbe non generare differenze. Nel momento in cui mi sono stancato vediamo i mutanti che non sono riuscito a distinguere, sono il 95% il test fa schifo c'è qualcosa che non va, sono il 5% già ragioniamo, arrivo ad una percentuale per cui i rimasugli sono pochi. Posso scegliere di nasconderli sotto il tappeto (se è richiesto al 100% significa che il programma è critico meglio di no) o se richiesto al 90% ok a posto posso dichiarare che non ho distinto ma se richiesta coeprtura totale non posso, devo andare a guardare io, se il programma è scritto bene la modifica sintattica deve avere conseguenze che si ripercuotono nell'intorno in modo distinguibile, posso dimostrare io che sono uguali come effetti.

Cosa cambia con l'object orientation? Nei linguaggi object oriented il fatto che l'oggetto sia l'unione dei metodi e stato è qualcosa di buona programmazione, se testo solo il metodo perdo il contesto in cui opera e in base al momento in cui lo chiamo mi da risultati diversi, devo aggiungere qualcosa per considerare anche lo stato.

Se prima testavamo dei metodi ora testiamo l'oggetto/classe??, testiamo i metodi nelle varie situazioni. Questo ha delle conseguenze, quando ci domandiamo cosa andiamo a testare diciamo la classe, quando diciamo la classe c'è ereditarietà. Ha senso testare l'interfaccia con metdo di default? Meglio testarlo in isolamento o testarlo dentro una classe concreta? Normalmente si dice che va ritestato perchè c'è il collegamento dinamico, eseguirlo per la classe base non esegue le stesse istruzioni che eseguito nella classe che eredita. Se ho mockato tutto si però potrebbero esserci metodi ridefiniti dalla classe sotto che non posso testare direttamente ma attraverso questo metodo. In generale non eredito l'attività di testing ma magari eredito lo stesso caso di test e l'oracolo, mi aspetto che mi dia ancora lo stesso risualtato ma devo rieseguirlo. A questo punto va testato anche nell'interfaccia. Se comunqne devo testare sulla classe concreta e non potrò mai avere un istanza dell'interfaccia pura posso non testare. In base a come ci si pone questa domande ha risposte diverse. Il mocking aiuta ma ci deve ricordare che non stiamo testando la dipendenza, deve essere testata a parte. Sul collegamento dinamico non è più determinabile staticamente quali sono i cammini, non so neanche più quanti sono.

### Class testing

Quando vogliamo fare class testing isoliamo la classe, costruiamo quante più classi stub possibili per renderla eseguibile indipendentemente dal contesto, implementiamo metodi astratti (metodo stub), aggiungiamo una funzione che permetta di estrarre ed esaminare lo stato dell'oggetto per bypassare l'incapsulamento. Poi costruiamo una classe driver che permetta di istanziare oggetti e chiamare i metodi secondo il criterio di copertura scelto (quale? alcuni di quelli che abbiamo detto non li possiamo usare).

Quali criteri di copertura della classe possiamo considerare?
Copertura dei comandi lo facciamo perchè è basilare ma non sufficiente. Dobbiamo considerare lo stato dell'oggetto, una definizione "statica" dello stato dell'oggetto potrebbe esistere nella documentazione come macchina a stati dell'oggetto che dice gli stati e le transizioni (chiamate di metodi che cambiano lo stato).

Se abbiamo un diagramma degli stati con transizioni non sempre deterministiche, possiamo:
- pensare "definisco come criterio di copertura la copertura di tutti i nodi" devo avere almeno un caso di test che mi porti nei vari stati, se nessun caso di test mi porta nello stato X c'è qualcosa che non va, non ho mai provato quella configurazione.
- copertura di tutti gli archi: coprire l'invocazione dei metodi all'interno di una particolare configurazione dello stato del mio sistema, ho chiamato il metodo M1 però è diverso chiamarlo quando sono nello stato A piuttosto che nello stato B.
- coprire tutte le coppie di archi input/output, considero anche come sono arrivato in uno stato e come lo lascio.
Queste sono quelle attuabili, poi si potrebbe coprire tutti i cammini del grafo e qui siamo nell'impossibile.

Che tipo di test è? È black box perchè il diagrama arriva dalle specifiche, rappresenta il codice ma non la sua implementazione. È possibile cercare di ricavare un diagramma (illegibile con 700 stati invece che solo 3) dal codice ma che è comunque una sua astrazione che dice quali stati bisogna coprire, a questo punto è white box perchè siamo partiti dal codice.