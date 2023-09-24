# **5. Source Code Management (SCM)<br>[[ST 5](../Slide%20teoria/05.pdf?page=6)] ([VL 9](https://www.youtube.com/watch?v=VLsr5DZ2L_I&t=1240))**

Il Configuration Management nasce nell'industria aerospaziale negli anni '50, ma inizia ad essere applicato nella produzione del software solo alla fine degli anni '70. Lo si può definire come:

    Un insieme di pratiche che hanno l'obiettivo di rendere sistematico il processo di sviluppo, tenendo traccia dei cambiamenti in modo che il prodotto sia in ogni istante in uno stato (configurazione) ben definito.

Gli strumenti di source code management permettono di:
- avere uno spazio in cui memorizzare i progetti mentre evolvono, consentendo di richiamare velocemente una qualunque versione memorizzata. In questo modo si possono creare facilmente delle patch che risolvono dei problemi molto critici nelle versioni precedenti.
- condividere i progetti con altre persone gestendo gli accessi contemporanei e aiutando a gestire i conflitti.
- tracciare le modifiche, in modo che sia sempre possibile sapere chi le ha create, quando sono state realizzate e quando sono state integrate.

La struttura di questi strumenti è cambiata molto nel corso del tempo:
- negli anni '80 lavoravano esclusivamente in locale, permettendo la collaborazione solo tra gli utenti che usavano fisicamente la stessa macchina (SCSS, Rcs, ...).
- negli anni '90 si è passati ad un architettura centralizzata (client-server) in cui esiste una singola copia dell'intero progetto memorizzata su un server (CVS, Subversion, ...).
- negli anni 2000 l'architettura è diventata distribuita (peer-to-peer) per supportare il processo di sviluppo open source (Git, Mercurial, Bazaar, ...).

## **5.1. Manufatti e configurazioni**

Gli "oggetti" di cui si controlla l'evoluzione sono detti **configuration item**, o in ambito software **artifact** (che significa **manufatto** e NON artefatto). Inizialmente questi strumenti versionavano un singolo **file contenente delle righe di testo**, senza tenere conto dell'esistenza degli altri. Ogni file aveva le sue versioni e modificandone uno si faceva crescere solo la sua versione.

Venivano poi create delle **configurazioni** che definivano quali file a quale versione componevano il progetto in un certo istante di tempo.

![Img](../Appunti/img/Configurazione%20manufatti.webp)

Questo approccio è stato usato per molto tempo, ma attualmente gli strumenti di versioning lavorano sull'intero progetto o su un albero di sottodirectory (una cartella e tutto quello che contiene). Versionando le directory è possibile far progredire la storia di un file anche nel caso in cui questo venga rinominato, con il vecchio approccio si sarebbe interrotta.

Il flusso di sviluppo può anche non essere lineare, si possono creare delle diramazioni (**branch**) che eventualmente si riuniscono, rendendolo di fatto un grafo aciclico.

Indipendentemente dallo strumento di SCM che si sceglie di utilizzare, bisogna decidere se tracciare anche i componenti esterni al progetto (librerie, compilatori, ...) e i file non sorgenti che costituiscono il prodotto. Questa è una scelta molto importante perchè influenza la **replicabilità** della produzione. In genere si sceglie di NON tracciare questi file perchè è scomodo e antieconomico, anche se così facendo sorgono problemi di perfetta replicabilità.

## **5.2. Repository**

Il meccanismo usato per controllare l'evoluzione delle versioni è regolato da due operazioni:
- **check-out**: serve a dichiarare di voler lavorare a partire da una particolare versione di un manufatto o configurazione di diversi manufatti.
- **check-in** (o **commit**): serve a registrare una nuova versione (anche detta **change-set**).

Queste operazioni effettuano uno spostamento tra il **repository**, ovvero lo spazio in cui sono mantenute tutte le configurazioni e le metainformazioni legate ai manufatti (date, etichette, ...), ed il **workspace**, ovvero l'ambiente in cui si lavora nel filesystem locale.

![Img](../Appunti/img/Repository.webp)

Quando si vogliono memorizzare delle modifiche, i repository salvano solo il **delta** (la differenza con la versione precedente) per risparmiare spazio. In realtà spesso fanno l'opposto: memorizzano solo l'ultima versione e tutte quelle precedenti vengono calcolate applicando vari delta. In questo modo è possibile accedere rapidamente all'ultima versione, dato che è quella utilizzata più frequentemente.

I repository possono essere:
- **centralizzati**: il repository è memorizzato su un singolo server o duplicato su più macchine (mirror) che usano dei meccanismi di sincronizzazione automatica, in modo da poter accedere a quella più comoda. Include anche la clusterizzazione (file non duplicati ma memorizzati su macchine diverse per ridurre il carico).
- **distribuiti**: può esistere un repository di riferimento memorizzato su un server, ma ogni sviluppatore ha una copia sulla propria macchina, questo rende possibile il lavoro offline. Nel caso in cui non si abbia a disposizione una connessione ad internet per diverso tempo è possibile creare dei commit locali ed eseguire un singolo push quando la connessione viene ristabilita. Con un repository remoto, sarebbe stato necessario effettuare un singolo commit molto grosso. Questi repository funzionano più velocemente dato che la maggior parte delle operazioni lavorano localmente, poi quando necessario sono in grado di comunicare tra loro e scambiarsi delle parti (non devono per forza sincronizzarsi per essere identici).

L'approccio peer-to-peer è sempre distribuito ma più particolare, nessuno è più importante di qualcun altro e non c'è coordinazione. In questo caso non è possibile confrontare degli oggetti citando il loro URL (localizzazione), serve un modo per verificare l'uguaglianza del contenuto basato sul contenuto stesso, questo viene fatto usando gli **hash**.

Rappresentando gli oggetti con un hash il confronto è immediato. In questo modo è possibile confrontare velocemente anche le directory (se due directory hanno lo stesso hash significa che il loro sottoalbero è identico).

I repository permettono di lavorare in diversi modi:
- una singola persona li può utilizzare per effettuare dei backup, versionare i suoi progetti ed effettuare velocemente un rollback. Questo scenario può essere più o meno centralizzato e non necessariamente remoto.
- due peer possono collaborare facendo dialogare direttamente i loro repository, in genere questo viene fatto esponendoli momentaneamente tramite dei server https e dando l'indirizzo all'altra persona.
- diverse persone possono lavorare usando una gerarchia a più livelli in cui esistono vari gradi di autorevolezza a cui sono associati permessi diversi.

## **5.3. Accessi concorrenti e conflitti**

Dato che un repository viene condiviso da più persone che lavorano concorrentemente sugli stessi file, è necessario gestire dei problemi d'accesso.

In passato questo veniva fatto con dei lock analoghi a quelli dei sistemi operativi: i check-out sui singoli file potevano essere di lettura o di scrittura (**modello pessimistico**, si prevengono i conflitti). Quelli di lettura sono sempre garantiti, mentre quelli di scrittura sono disponibili solo se la coda degli utenti che ne hanno richiesto uno è vuota, altrimenti bisogna aspettare il proprio turno. Questa soluzione funziona solo in ambienti centralizzati, con l'open source sorgono troppi problemi.

Oggi si preferisce usare un **modello ottimistico**. È possibile lavorare su qualsiasi file senza problemi perchè si suppone che nessun altro voglia fare lo stesso. Poi quando si vogliono integrare le modifiche, si verifica se il file di partenza è rimasto invariato. Se così fosse, le modifiche possono essere applicate, in caso contrario è necessario adattare il codice al nuovo file o fare un rollback e ricominciare da capo. Questo meccanismo funziona bene finchè i conflitti effettivi (sulle stesse zone di codice) sono pochi.

Serve quindi un meccanismo che segnali gli eventuali conflitti e che aiuti ad unire due evoluzioni parallele. Quest'operazione prende il nome di **merge**. Negli strumenti di SCM distribuiti gli alboritmi di merge hanno subito uno sviluppo sempre maggiore perchè sono fondamentali, per sincronizzare dei repository si esegue il merge dei due rami coinvolti.

---

Torna all'[indice degli argomenti](../README.md#indice-degli-argomenti)  
Capitolo successivo: [Git](6%20Git.md)  
Capitolo precedente: [Open source process](4%20Open%20source%20process.md)