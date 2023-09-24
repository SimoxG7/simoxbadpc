# **8. Refactoring<br>[[ST 7](../Slide%20teoria/07.pdf)] ([VL 13](https://www.youtube.com/watch?v=8vTSssPmUt0))**

```c
#include <stdio.h>
main(t,_,a)
char *a;
{
return!0<t?t<3?main(-79,-13,a+main(-87,1-_,main(-86,0,a+1)+a)):
1,t<_?main(t+1,_,a):3,main(-94,-27+t,a)&&t==2?_<13?
main(2,_+1,"%s %d %d\n"):9:16:t<0?t<-72?main(_,t,
"@n'+,#'/*{}w+/w#cdnr/+,{}r/*de}+,/*{*+,/w{%+,/w#q#n+,/#{l+,/n{n+,/+#n+,/#\
;#q#n+,/+k#;*+,/'r :'d*'3,}{w+K w'K:'+}e#';dq#'l \
q#'+d'K#!/+k#;q#'r}eKK#}w'r}eKK{nl]'/#;#q#n'){)#}w'){){nl]'/+#n';d}rw' i;# \
){nl]!/n{n#'; r{#w'r nc{nl]'/#{l,+'K {rw' iK{;[{nl]'/w#q#n'wk nw' \
iwk{KK{nl]!/w{%'l##w#' i; :{nl]'/*{q#'ld;r'}{nlwb!/*de}'c \
;;{nl'-{}rw]'/+,}##'*}#nc,',#nw]'/+kd'+e}+;#'rdq#w! nr'/ ') }+}{rl#'{n' ')# \
}'+}##(!!/")
:t<-50?_==*a?putchar(31[a]):main(-65,_,a+1):main((*a=='/')+t,_,a+1)
:0<t?main(2,2,"%s"):*a=='/'||main(0,main(-61,*a,
"!ek;dc i@bK'(q)-[w]*%n+r3#l,{}:\nuwloca-O;m .vpbks,fxntdCeghiry"),a+1);
}
```

Questo programma scritto in C è stato realizzato volontariamente in modo poco comprensibile per partecipare all'International Obfuscated C Contest del 1988 (1° posto), quando viene eseguito stampa le strofe della canzone "Twelve Days of Christmas".

Lo stesso comportamento può essere ottenuto con un solo print nel main che stampa l'intero testo, tuttavia questi due programmi hanno qualità molto diverse. Quello offuscato fattorizza parti del testo, mentre quello che stampa direttamente l'intero testo è più veloce ma meno modificabile ed evolvibile (se si vuole aggiungere/rimuovere una strofa o tradurre il testo serve più lavoro e fatica).

Per migliorare il design e rendere il programma più facilmente modificabile si possono introdurre dei metodi che lavorano su delle parti comuni:

```java
static String[] days = {"first", "second", ..., "twelfth"};
static String[] gifts = {"a partridge in a pear tree", "two turtle doves", ...};

static String firstLine(int day){
    return "On the " + days[day] + " day of Christmas my true love gave to me:";
}

static String allGifts(int day){
    if(day == 0){
        return " and " + gifts[0];
    }else{
        return gifts[day] + "\n" + allGifts(day - 1);
    }
}

public static void main(String[] args){
    System.out.println(firstLine(0));
    System.out.println(gifts[0]);
    for(int day = 1; day < 12; day++){
        System.out.println(firstLine(day));
        System.out.println(allGifts(day));
    }
}
```

Questo processo di modifica prende il nome di **refactoring**. In generale il refactoring consiste nel modificare il codice per migliorarne le qualità interne e ridurne la complessità, il cliente non dovrebbe notare questi cambiamenti perchè le funzionalità restano invariate (non devono passare test in più).

    Refactoring: improving the design of code without changing its functionality

È difficile che questa fase venga eseguita in modo spontaneo, però è importante costringersi a farla. Quando ci si accorge che per introdurre una nuova funzionalità si userebbe la maggior parte del tempo per sistemare il codice già esistente è meglio tornare indietro, effettuare il refactoring come fase a se stante e poi riprendere lo sviluppo della nuova funzionalità.

Anche il refactoring dovrebbe durare al massimo 10 minuti, se dura di più probabilmente è meglio effettuare un rollback, riragionare su quello che si stava cercando di fare ed eventualmente rifarlo meglio (do it twice).

Il refactoring può essere effettuato per diversi motivi:
- Migliorare un design inizialmente "semplice" (fatto solo per passare il test).
- Preparare il design all'introduzione di una funzionalità che allo stato corrente non s'integrerebbe facilmente.
- Eliminare debolezze (debiti tecnici).

## **8.1. Code smell**

Con l'espressione **code smell** s'intendono tutte quelle debolezze che pur non causando errori riducono le qualità del software e che vanno rimosse in fase di refactoring.

Alcune di queste sono:
- *codice duplicato*: va bene copiare e incollare pezzi di codice nella prima soluzione per far passare un test ma poi è necessario fattorizzarlo.
- *metodo troppo lungo o complesso*: quando i metodi sono molto lunghi o hanno molti livelli d'annidamento diventano poco leggibili e riusabili, probabilmente è possibile trovare delle parti che hanno un significato a se stante e si possono estrarre. Per misurare la complessità del codice si usa il *numero ciclomatico* (o *complessità ciclomatica*).
- *classe con tanti attributi o metodi*: probabilmente la classe sta svolgendo più di un compito, è meglio separare le sue funzionalità in più classi.
- *lunghe sequenze di if-else o switch*: sono eliminabili utilizzando il polimorfmismo e l'inversione.
- *lista di parametri troppo lunga*.
- *numeri magici*: sono tutte le costanti numeriche scritte direttamente nel codice. Non avendogli dato un nome è difficile ricordare cosa rappresentano, inoltre potrebbero esserci dei numeri uguali che hanno un significato diverso, quindi fare una sostituzione globale causerebbe problemi. Quando il loro significato non è banale, è meglio dichiararli come costanti, in questo modo hanno un nome che indica il loro significato ed è possibile modificare velocemente tutte le occorrenze che hanno lo stesso significato.
- *commenti*: se si sente la necessità di scrivere un commento significa che il codice non è abbastanza chiaro, è necessario migliorarlo.
- *codice morto*: è tutto quel codice che non può essere eseguto perchè è commentato oppure perchè si trova dopo un return o dopo una condizione sempre vera (*codice irraggiungibile*), spesso i compilatori sono in grado di rilevarlo staticamente. Se è presente un pezzo di codice inutilizzato si può rimuovere tranquillamente, se più avanti ci si rende conto che serve ancora lo si può recuperare dal sistema di versioning.
- *getter e setter*: questi metodi vanno eliminati dal codice, bisognerebbe dire agli oggetti cosa devono fare, non chiedere le loro informazioni (*tell-don't-ask principle*).

Molti di questi code smell si possono correggere in modo stupido, bisogna sempre verificare che i cambiamenti introdotti siano sensati.

Per un elenco più completo di code smell e di tecniche di refactoring guardare l'elenco su [Refactoring Guru](https://refactoring.guru/refactoring/smells).

## **8.2. Design knowledge**

Dato che il design può cambiare continuamente, dove si deposita la sua conoscenza?

In genere questa viene mantenuta:
- nella memoria personale (non facilmente disponibile al resto del team e con il tempo si dimentica).
- in documenti di design scritti in linguaggio naturale o in diagrammi (è facile che si disallineino dal codice, avere documentazione disallineata è peggio che non averla).
- all'interno di piattaforme di discussione, di bug tracking e di versioning (si può fare ma risulta troppo sparpagliata).
- in modelli specializzati (**UML**). Questi possono portare al *Model Driven Development* (consite nello scrivere le specifiche in modo sufficientemente dettagliato per poi generare automaticamente il codice). Per diverso tempo gli ingegneri si erano illusi di poter fare *Round-Trip Engineering* (consiste nel fare in modo che i diagammi e il codice rimangano sempre allineati mediante l'uso di alcuni meccanismi automatici) ma nella realtà si è visto che non funziona.
- nel codice, però anche se il codice è stato scritto benissimo risulta difficile rappresentare le **ragioni** delle scelte.

Scegliere una strategia da utilizzare non è semplice, una buona soluzione può prevedere l'immagazzinamento del minimo necessario riguardo alla parte di **rational** (le motivazioni che riguardano le architetture di grande livello) e lasciare i dettagli direttamente nel codice.

Esistono diversi modi per condividere le scelte di design:
- *Metodi* (agile, object orientation, ...).
- **Design pattern**: sono soluzioni generali a problemi ricorrenti (comprendono sia la specifica che l'implementazione di riferimento), essendo applicabili in molti contesti non stressano l'ottimizzazione delle prestazioni (ma per l'appunto la generalità).
- *Principi* (prinicipi SOLID, ...).

---

Torna all'[indice degli argomenti](../README.md#indice-degli-argomenti)  
Capitolo successivo: [Object orientation](9%20Object%20orientation.md)  
Capitolo precedente: [Build automation](7%20Build%20automation.md)