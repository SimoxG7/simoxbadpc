# **11. Mocking<br>[[ST 10](../Slide%20teoria/10.pdf?page=11) e [ST 12](../Slide%20teoria/12.pdf?page=4)] ([VL 20](https://www.youtube.com/watch?v=ctaxLGPvSWw) e [VL 22](https://youtu.be/rvJ_wYT1SYw?t=390))**

## **11.1. Problemi nel testing**

Normalmente quando si testa un componente (chiamato System/Subject Under Test o SUT) si scrivono diversi casi di test che lo stimolano e in cui si fanno delle asserzioni sui risultati prodotti (se il metodo testato non ritorna nulla s'ispeziona lo stato con un getter o un toString).

Il testing diventa problematico se:
1.  il SUT deve elaborare dei dati che richiede ad un altro componente (chiamato Dependent On Component o DOC). Il DOC potrebbe produrre dei risultati scorretti che fanno sembrare scorretto anche il SUT quando in realtà non lo è.

    ![img](../Appunti/img/SUT%20get%20data.webp)

1. il SUT interagisce con il DOC chiamando dei metodi che non restituiscono niente, diventa quindi necessario ispezionare lo stato del DOC per capire se il SUT si è comportato correttamente. Così facendo si rischia di considerare il SUT scorretto a causa di errori del DOC.

    ![img](../Appunti/img/SUT%20make%20call.webp)

1. il DOC non è stato ancora realizzato. In questo caso è necessario attendere la sua realizzazione prima di poter procedere con il test del SUT.

Il **mocking** permette di cambiare approccio e risolvere i problemi legati a queste tre situazioni:
1. Si sostituisce il DOC con una sua versione fittizia che è in grado di restituire valori preimpostati quando si chiamano i suoi metodi. Dato che nei casi di test le stimolazioni che verranno effettuate sono poche e prestabilite si può simulare facilmente il funzionamento di quello vero.
1. Si sostituisce il DOC con una sua versione fittizia che registra le chiamate ricevute, interrogandola si può verificare se il SUT ha effettuato le chiamate corrette. In questo modo si separa nettamente il test del SUT (ha fatto la chiamata corretta) dal test del DOC (il metodo chiamato è corretto).
3. È possibile creare degli oggetti fittizi basati su interfacce e classi astratte (ma anche concrete) non ancora implementate che possono essere utilizzati in attesa che queste vengano realizzate.

## **11.2. Mockito**

Uno dei framework (librerie) che permettono di fare mocking è [**Mockito**](https://site.mockito.org/) ([documentazione Javadoc](https://javadoc.io/doc/org.mockito/mockito-core/latest/index.html) e [tutorial](https://dzone.com/refcardz/mockito)). Questo permette di costruire [diverse tipologie](http://xunitpatterns.com/Test%20Double.html) di oggetti mockati in modo semplice e veloce.

### **11.2.1. Dummy Object**

Spesso i metodi da testare hanno tra i parametri anche degli oggetti di tipi molto complicati che hanno molte dipendenze. Altre volte invece i loro tipi sono interfacce che non hanno ancora implementazioni, quindi bisognerebbe definire delle classi che le implementino o creare degli oggetti anonimi solo per fare il test. In entrambi i casi si vuole evitare di creare oggetti così complicati solo per passarli come parametro e non farci niente.

I **dummy object** sono oggetti che non sono in grado di fare niente ma possono essere creati facilmente e utilizzati al posto di quelli reali.

```java
@Test
public void testDummy(){
    MyClass dummy = mock(MyClass.class);
    List<MyClass> SUT = new MyList<>();
    SUT.add(dummy);
    assertThat(SUT.size()).isEqualTo(1);
}
```

La funzione ```mock``` può ricevere come parametro sia interfacce che classi astratte o concrete.

### **11.2.2. Stub Object**

In alcuni casi usare un dummy object non è sufficiente perchè il SUT deve invocare più volte alcuni dei metodi dell'oggetto mockato per ricevere dei dati, se poi questi devono cambiare in base al numero di chiamate effettuate la cosa inizia ad essere complicata (bisognerebbe creare una classe dedicata che abbia uno stato interno).

Gli **stub object** sono oggetti fittizi creabili semplicemente e sostituibili a quelli reali che hanno anche la capacità di fingere di saper svolgere certi compiti: quando vengono interrogati forniscono uno o più risultati prefissati.

```java
@Test
public void testStub(){
    MyClass stub = mock(MyClass.class);
    when(stub.getValue(0)).thenReturn(4);
    when(stub.getValue(1)).thenReturn(7,3);
    List<Integer> SUT = new MyList<>();
    SUT.add(stub.getValue(0)); // restituisce 4
    SUT.add(stub.getValue(1)); // restituisce 7
    SUT.add(stub.getValue(1)); // restituisce 3
    assertThat(SUT.somma()).isEqualTo(14);
}
```

Usando ```when``` è possibile aggiungere dei comportamenti fittizi agli oggetti creati con la funzione ```mock``` in modo semplice e veloce. Se viene impostato un unico risultato verrà restituito sempre quello, se invece si definisce una sequenza verrano restituti in quell'ordine (una volta raggiunto l'ultimo verrà restituito sempre quello).

```java
when(mockedObj.methodName(args)).thenXXX(values);
```

- args: values | matchers | argumentCaptor
- matchers: anyInt(), argThat(is(closeTo(1.0, 0.001)))
- thenXXX: thenReturn | thenThrows | thenAnswer | thenCallRealMethod

```when``` non funziona quando i metodi non ritornano nulla, in questi casi bisogna usare:

```java
doXXX(values).when(mockedObj).methodName(args);
```

### **11.2.3. Mock objects**

Se le chiamate ai metodi di uno stub object sono nascoste perchè interne ad altri metodi, bisogna assicurarsi che queste siano state effettuate correttamente.

I **mock object** sono oggetti fittizi creabili semplicemente e sostituibili a quelli reali che hanno la capacità di fornire dei risultati prefissati e di memorizzare le chiamate che hanno ricevuto in modo da poter verificare che il SUT abbia interagito correttamente con il DOC.

```java
@Test
public void testMock(){
    MyClass mock = mock(MyClass.class);
    when(mock.getValue(0)).thenReturn(4);
    when(mock.getValue(1)).thenReturn(7,3);
    List<Integer> SUT = new MyList<>();
    assertThat(SUT.somma(mock)).isEqualTo(14);
    verify(mock).getValue(0);
    verify(mock, times(2)).getValue(1);
}
```

Con ```verify``` è possibile verificare che un certo mock abbia ricevuto una o più chiamate ad un certo metodo con i parametri specificati.

```java
verify(mockedObj, howMany).methodName(args);
```

- howMany: times(n) | never | atLeast(n) | atMost(n)

Per verificare che non avvengano più interazioni con un mock dopo un certo punto si può usare:

```java
verifyNoMoreInteractions(mockedObj);
```

Per verificare che le chiamate avvengano in un certo ordine si può usare ```inOrder```:

```java
InOrder io = inOrder(mockedObj1, mockedObj2, ...);
io.verify(mockedObj1, howMany).methodName(args);
```

È anche possibile catturare un parametro per farci asserzioni:

```java
ArgumentCaptor<Person> arg = ArgumentCaptor.forClass(Person.class);
verify(mockedObj).doSomething(arg.capture());
assertEquals("John", arg.getValue().getName());
```

### **11.2.4. Spy Object**

Gli **spy object** sono oggetti reali a cui vengono aggiunte le stesse primitive d'interrograzione che hanno i mock objects in modo da poter verificare quali chiamate hanno ricevuto. Essendo oggetti reali già implementati, non è necessario definire delle risposte preimpostate che dovranno fornire i loro metodi.

```java
@Test
public void testSpy(){
    MyClass spy = spy(new MyClass());
    List<Integer> SUT = new MyList<>();
    assertThat(SUT.somma(spy)).isEqualTo(14);
    verify(spy).getValue(0);
    verify(spy, times(2)).getValue(1);
}
```

### **11.2.5. Fake objects**

I **fake objects** sono oggetti che implementano il DOC in modo non realistico o non stabile, questi non possono essere usati in produzione ma è accettabile usarli per fare testing. Ad esempio è possibile utilizzare un componente open source esclusivamente in fase di testing senza pubblicare il prodotto con una licenza open, oppure usare un database locale in RAM invece che uno reale.

## **11.3. Esempio di mocking nel pattern observer**

Il mocking permette di testare gli Observable (aggiunta osservatori e notifica cambiamento) ancora prima di aver creato gli Observer.

```java
@Test
void modelTest {
    //SETUP
    ObservableModel SUT = new ObservableModel();
    Observer obs = mock(Observer.class);
    Observer obs1 = mock(Observer.class);
    //EXERCISE
    SUT.addObserver(obs);
    SUT.addObserver(obs1);
    SUT.setTemp(42.0);
    //VERIFY
    verify(obs).update(eq(SUT), eq("42.0"));
    verify(obs1).update(eq(SUT), eq("42.0"));
}
```

Permette anche di testare gli Observer senza aver ancora a disposizione gli Observable.

```java
@Test
void observerTest{
    //SETUP
    Observer SUT = new Observer();
    ObservableModel model = mock(ObservableModel.class);
    when(model.getTemp()).thenReturn(42.42);
    //EXERCISE
    SUT.update(model);
    //VERIFY
    verify(model).getTemp();
    assertThat(SUT.getVal()).isCloseTo(42.42, Offset.offset(.01));
}
```

## **11.4. Testing e mocking**

Quando si effettua il test d'integrazione si usano tutti i componenti reali, ma nei test d'unità ci dovrebbe essere un unico componente reale (una sola new che crea il SUT) e tutti i DOC con cui interagisce dovrebbero essere mockati (ad esclusione delle librerie standard). Nella pratica si possono usare anche oggetti reali, ma questi devono essere semplici e già testati.

In base alla necissità si può partire da qualcosa di vuoto e aggiungere (mockare un interfaccia e definirne i comportamenti) oppure partire da qualcosa di pieno e togliere (creare lo spy di una classe e ridefinire un comportamento).

## **11.5. Dependency injection**

Quando un oggetto reale ha al suo interno un DOC che si crea in fase di costruzione con una ```new```, il testing diventa complicato, perchè quel DOC non può essere mockato.

Per risolvere il problema si potrebbe aggiungere un costruttore o un setter visibile solo a livello di package che permette di modificare il valore dell'attributo che contiene il riferimento al DOC. Se non si vogliono aggiungere questi metodi usati solo in fase di testing si può usare il **dependency injection**, questo consiste nell'iniettare un oggetto mockato dentro ad un oggetto reale.

Mockito non è uno strumento di dependency injection, però ha un piccolo costrutto utile per svolgere questo compito nei casi più semplici. Per usarlo è necessario aggiungere una dipendenza nel file ```build.gradle```.

```gradle
dependency{
    testImplementation "org.mockito:mockito-junit-jupiter:4.8.0"
}
```

Una volta aggiunta questa dipendenza bisogna usare l'annotazione ```@ExtendWith(MockitoExtension.class)``` per estendere la classe di test e poter utilizzare i costrutti necessari. Dopodichè bisogna dichiarare i DOC che si desidera mockare come attributi della classe di test usando le annotazioni ```@Mock``` e ```@Spy```. Infine si crea un attributo per il SUT con l'annotazione ```@InjectMocks```.

In questo modo prima dell'esecuzione di ogni caso di test verrà ricreata una versione mockata di ogni DOC e una nuova istanza del SUT, poi tutti questi oggetti mockati verranno iniettati dentro al SUT. Per fare questo, Mockito prova diverse strategie finchè non ne trova una che funziona (costruttore, setter o reflection). Nel caso in cui ci siano più attributi con lo stesso tipo viene fatto il matching tra il nome dell'attributo e quello dell'oggetto mockato.

Esempio: iniezione di un mazzo mockato in un mazziere reale.

```java
@ExtendWith(MockitoExtension.class)

class MazziereTest {

    @Mock DeckInterface mazzo;
    @InjectMocks Mazziere mazziere;

    @Test
    void test(){
        when(mazzo.draw()).thenReturn(Card.get(Rank.ACE, Suit.CLUBS));
        mazziere.carteIniziali();
        assertThat(mazziere.getPunti()).isEqualTo(11);
    }

}
```

Mockito permette di fare solo un iniezione superficiale, con altri framework dedicati (ad esempio Juice) si possono configurare gli attributi degli oggetti reali in un file separato di configurazione. Il dependency injection viene usato molto in ambiente Android.

---

Torna all'[indice degli argomenti](../README.md#indice-degli-argomenti)  
Capitolo successivo: [Progettazione](12%20Progettazione.md)  
Capitolo precedente: [Design patterns](10%20Design%20patterns.md)