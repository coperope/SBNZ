# SBNZ - Predlog projekta

## Sistem za preporuku vozila za iznajmljivanje

#### Članovi tima:

- Petar Bašić - RA105/2016
- Damjan Pantić - RA7/2016
- Mihajlo Perendija - RA87/2016

### Motivacija i pregled problema:

Tema projekta je sistem zasnovan na pravilima za preporuku automobila na rent a car web sajtu. Sistem
bi koristilo dva tipa korisnika. U prvi tip spadaju oni koji žele da iznajme automobil kojima bi sistem
na osnovu lokacije, sklonosti, želja i istorije korišćenja predlagao isti. Drugi tip čine korisnici koji
oglašavaju automobile. Sistem bi njima pomogao u poslovima kategorizacije, tagovanja i formiranja
cenovnika. Takođe bi im predlagao tipove automobila koji su najaktuelniji na web sajtu kao i biznis
taktiku.

#### Prednosti implementacije projekta koristeći sistem zasnovan na pravilima u odnosu na konvencionalno programiranje:

- Mogućnost olakšane adaptacije sistema samo izmenom produkcionih pravila u odnosu na ponovnu
implementaciju algoritama i posledično zaustavljanje rada celog sistema. Ovako konstruisanim
sistemom omogućeno je održavanje i izmena istog od strane osoba koje nisu stručne za razvoj softvera.
Ovo je navedeno kao problem i u [2].
- Na pocetku rada aplikacije, ne postoji dovoljno podataka kako bi se iz njih izvele preporuke kao ni
klasifikacije uz pomoć tehnika mašinskog učenja. Po prirodi, sistem je takav da mu velika učestalost
korišćenja nije jedna od karakteristika te se podaci potrebni za preciznije predloge sporo generišu. U
ovakvim situacijama je pogodniji sistem u kojem se pravila unapred definišu na osnovu znanja i
menjaju na osnovu iskustava.
- Sistem nudi mogućnost objašnjenja o načinu nastanka preporuke.
Postojeća rešenja [1] inkorporiraju unapred definisane algoritme u slabo izmenjiv sistem ili koriste
određene tehnike mašinskog učenja za koje je unapred potreban glomazan skup podataka. Prilikom
potrebe za izmenom ovakva rešenja zahtevaju veće izmene od strane softverskih stručnjaka, ponovnu
izgradnju algoritama, implementaciju istih ili obučavanje modela i to uz određen period neaktivnosti
sistema. Kako su izmene u automobilskoj industriji učestale potrebno je imati mogućnost brze
adaptacije sistema.

### Metodologija rada:
#### Očekivani ulazi u sistem:

- Podaci o vozilu
- Korisničke preferencije
- Podaci o iznajmljivanju vozila i istorija pretraživanja.
- Oznake pri pretrazi (npr. jeftin, porodični, niska potrošnja)

#### Očekivani izlazi sistema:

- Oznake i kategorije koje se dodeljuju automobilu prilikom njegovog kreiranja.
- Predlog automobila krajnjem korisniku

### Baza znanja:

- [Pravila za kategorizaciju i dodavanje oznaka vozilima](#kategorizacija-i-dodeljivanje-oznaka-vozilu)
- [Pravila za predlaganje automobila](#opis-koncepta-i-pravila-za-predlaganja-vozila-korisniku)
- [Pravila za rukovanje događajima](#događaji)

Baza znanja će se popunjavati delimično automatski na osnovu implicitne i eksplicitne aktivnosti
korisnika. Implicitno podrazumeva pasivno praćenje aktivnosti korisnika odnosno statistiku
posećivanja oglasa i izvođenje zaključaka na tim osnovama. Podaci se mogu prikupiti i eksplicitnim
navođenjem preferencija i želja od strane korisnika.

### Primeri rezonovanja:

1. Prilikom kreiranja oglasa za automobil unose se njegovi osnovni podaci na osnovu kojih mu se
dodeljuju oznake (npr. niska potrošnja, velika snaga motora, porodični auto...) kao i kategorije (npr. kupe, sportski, luksuzni...) -> videti konkretne uslove za dodeljivanje kategorije ili oznake u sekciji [Kategorizacija i dodeljivanje oznaka vozilu](#kategorizacija-i-dodeljivanje-oznaka-vozilu).
2. Korisnik unosi preferencije i želje prilikom registracije (moguće naknadne izmene) na osnovu
kojih sistem predlaže oglase. Nakon izvesne aktivnosti korisnika kreiraju se događaji koji utiču
na korekciju predloga oglasa.
3. Prilikom pretraživanja i rentiranja vozila podaci o tome se čuvaju u bazi za svakog pojedinačnog korisnika i koriste se za predlaganje novih vozila tom korisniku. Detaljnije u sekcijama [Događaji](#događaji) i [Opis koncepta i pravila za predlaganja vozila korisniku](#opis-koncepta-i-pravila-za-predlaganja-vozila-korisniku).



### Kategorizacija i dodeljivanje oznaka vozilu:
#### Oznake:
Vozila dobijaju oznake pri dodavanju u sistem na osnovu sledećih pravila:

High fuel consumption -> >= 12 litres per 100km <br />
Medium fuel consumption -> 8 - 12 litres per 100km <br />
Low fuel consumption -> <= 8 litres per 100km <br />
Sport engine power -> > 174 W/kg <br />
High engine power -> >= 114 W/kg <br />
Medium engine power -> 49 > x < 114 W/kg <br />
Low engine power -> <= 49 W/kg <br />
Family car -> seats >= 5 <br />
Big family car -> *Family car tag* AND combined passenger and cargo volume >= 3086 L  <br />
Small family car -> *Family car tag* AND combined passenger and cargo volume < 3086 L  <br />
Range: 100km / (liters per km) * tank space (in liters) <br />
Long range -> >= 900 km per tank <br />
Medium range -> 400 km per tank > x < 900 km per tank <br />
Short range -> <= 400km per tank <br />
Unlimited mileage -> mileageLimit ==  0 <br />
High mileage price -> mileageLimit > 0, mileagePrice >= 1.2 <br />
Medium mileage price -> mileageLimit > 0, mileagePrice > 0.5, mileagePrice < 1.2 <br />
Low mileage price -> mileageLimit > 0, mileagePrice <= 0.5 <br />
Transmission automatic -> transmission == Automatic <br />
Transmission manual -> transmission != Automatic <br />

#### Kategorije:
Vozila dobijaju kategorije pri dodavanju u sistem na osnovu sledećih pravila:

Economy -> <= 8 litres per 100km and l < 4m and w < 1.9m and h < 1.6m and cargoVolume < 450l and seatsNo <= 5 and doorNo <= 4 and pricePerDay < 25  <br />
Compact -> <= 9 litres per 100km and l < 3.2m and w < 1.9m and h < 1.6m and cargoVolume + passengerAreaVolume < 3086 and seatsNo <= 5 and doorNo <= 4 and 20 < pricePerDay < 60  <br />
Standard -> <= 11 litres per 100km and l > 4m and w < 1.9m and h < 1.6m and cargoVolume + passengerAreaVolume > 3400 and seatsNo == 5 and doorNo == 4 and 40 < pricePerDay < 120  <br />
Intermediate -> <= 11 litres per 100km and l > 4.1m and w < 1.9m and h < 1.6m and cargoVolume + passengerAreaVolume > 3400 and cargoVolume + passengerAreaVolume < 3086 and seatsNo == 5 and doorNo == 4 and 40 < pricePerDay < 100  <br />
Full size -> <= 12 litres/100km and >= 10 litres/100km and l > 4.3m and w < 1.95m and h < 1.7m and cargoVolume + passengerAreaVolume > 3450 and seatsNo >= 5 and doorNo >= 4 and 45 < pricePerDay < 300  <br />
Luxury -> <= 14 litres/100km and >= 10 litres/100km and l > 4.3m and w < 1.95m and h < 1.7m and cargoVolume + passengerAreaVolume > 3300 and seatsNo >= 5 and doorNo >= 4 and 300 < pricePerDay and No of features < 2 <br />
Premium -> <= 14 litres/100km and >= 10 litres/100km and l > 4.3m and w < 1.95m and h < 1.7m and seatsNo >= 5 and doorNo >= 4 and 80 < pricePerDay < 400 and No of features > 2 <br />
Sport -> <= 14 litres/100km and >= 10 litres/100km and l < 5m and w < 1.9m and h < 1.3m and seatsNo >= 2 and seatsNo <= 4 and doorNo == 2 and 80 < pricePerDay < 400 and (power x 1000/weight) >= 174 <br />
Coupe -> <= 12 litres/100km and >= 10 litres/100km and l < 5m and w < 2m and h < 1.6m and seatsNo >= 2 and cargoVolume + passengerAreaVolume < 2200 and seatsNo == 2 and doorNo == 2 and 80 < pricePerDay < 400 <br />
Passenger van -> <= 20 litres/100km and >= 15 litres/100km and l < 5.5m  and h > 2m and seatsNo >= 10 and seatsNo <= 15 and cargoVolume + passengerAreaVolume > 5000 and doorNo == 4 and 80 < pricePerDay < 400 <br />
Pick up -> <= 20 litres/100km and >= 15 litres/100km and l < 5.5m  and h > 2m and seatsNo == 5 and cargoVolume > 2000 and doorNo == 4 <br />


#### Čuvanje istorije korisnikovog rentiranja i pretraživanja oglasa.
Prilikom rentiranja vozila čuvaju se svi podaci vozila na osnovu čega se popunjava istorija rentiranja korisnika.

Kao i kod rentiranja tako i kod pretrage nakon sto korisnik pretrazi ili udje u oglas/vozilo podaci pretrage se vezuju za korisnika i cuvaju u istoriji pretraga.

### Događaji:
1. Dodavanje novog vozila - Kada se doda novo vozilo kreira se događaj koji se ubacuje u sesiju koja je stalno aktivna.

    - Ukoliko vlasnik tog vozila ima kategoriju "GOLD" njegovo vozilo biva obrađeno svim pravilima za preporuku (preference, rentali, istorija pretrage) za svakog korisnika u sistemu. Tek kada se izvrše sve tri grupe pravila, izmene se čuvaju a činjenice izbacuju iz sesije.
    - Ukoliko vlasnik tog vozila nema kategoriju "GOLD", pravila za preporuku se ne pokreću odmah. Čeka se da se na sistemu kreira određen broj novih oglasa vozila i tek tada se pokreće gore opisan proces.
    

2. Pretraživanje i pregledanje vozila - Kada korisnik obavi pretragu automobila kreira se događaj koji se ubacuje u sesiju koja je stalno aktivna. 
Kada se skupi određen broj tih događaja vezanih za tog korisnika, a u koliko se nisu desili u periodu od 15 sekundi, dobavljaju se sva vozila iz baze i pokreću se pravila za prilagođavanje preporuka vozila na osnovu istorije pretrage istih.
Ova restrikcija je postavljena kako ne bi moglo doći do zloupotrebe veoma učestalim zahtevima za pretragom vozila u kratkom vremenskom periodu.

3. Novo iznajmljivanje vozila - Kada korisnik iznajmi novo vozilo kreira se događaj koji se ubacuje u sesiju koja je stalno aktivna.
Nakon toga, dobavljaju se sva vozila iz baze i pokreću pravila za prilagođavanje preporuka na osnovu prethodnih iznajmljivanja vozila.
U ovom skupu pravila se takođe vrši i kategorizacija vlasnika automobila. Ukoliko je njegovo vozilo iznajmljeno određen broj puta on dobija status GOLD;

4. Izmena korisničkih preferencija - Kada korisnik izmeni svoje preferencije kreira se događaj koji se ubacuje u sesiju koja je stalno aktivna.
Nakon toga, dobavljaju se sva vozila iz baze i pokreću pravila za kreiranje preporuka na osnovu preferencija korisnika.

*S obzirom da pravila koja obrađuju ove događaje ubacuju veliki broj činjenica u sesiju, nakon izvršavanja pravila za preporuku izmene se čuvaju u bazi a obrađene činjenice izbacuju iz sesije.


### Opis koncepta i pravila za predlaganja vozila korisniku
#### Personalizovani predlog vozila korisniku na osnovu njegovih preferencija.
Korisnik unosi sledeće podatke (prilikom registracije) koji najbolje opisuju njegov željeni automobil:

- Listu kategorija
- Listu oznaka
- Moguću količinu pređenih kilometara bez dodatnog plaćanja
- Listu brendova automobila
- Listu tipova goriva
- Listu tipova izmenjivog prenosa snage (menjača)
- Broj sedišta
- Broj vrata
- Potrošnju

Za svaki od prethodno navedenih podataka postoji pravilo koje pronalazi vozila koja se podudaraju. Vozila koja imaju podudaranja se pamte i sortiraju prema broju podudaranja čime dobijamo listu onih koja su najsličnija korisnikovim željama. 
Sistem na zahtev korisnika (potencijalno i automatski), obrađuje unete podatke i pronalazi automobile sa najviše podudaranja. Takvi automobili se onda prosleđuju korisniku ("Recommended for you, based on your preferences").

#### Personalizovani predlog vozila korisniku na osnovu njegovog ranijeg pretraživanja vozila.
Korisnik upotrebom opcije pretrage šalje podatke:

- Listu kategorija
- Listu oznaka
- Listu brendova automobila
- Listu modela automobila
- Listu tipova goriva
- Listu tipova izmenjivog prenosa snage (menjača)
- Listu željenih brojeva sedišta
- Listu željenih brojeva vrata
- Listu željene potrošnju

Ovi podaci se čuvaju u bazi za svakog pojedinačnog korisnika i to u obliku ključ-vrednost. Ključ predstavlja jednu instancu entiteta pretrage (npr. model "Zastava") dok je vrednost broj koji predstavlja koliko puta se taj entitet pretražio. Takođe se vodi evidencija i o zahtevu za detaljnim pregledom vozila. Ta akcija je značajnija te se polje vrednosti povećava sa određenim faktorom skaliranja.

Za svaki od prethodno navedenih podataka postoji pravilo koje pronalazi vozila koja se podudaraju sa korisničkim pretragama. Vozila koja imaju podudaranja se pamte i sortiraju prema ukupnom broju broju pretraga svih entiteta vozila koja se podudaraju sa korisničkim pretragama. Time dobijamo listu vozila koja su najsličnija onima koje je korisnik ranije pretraživao ili pregledao.
Sistem na zahtev dostavlja korisniku automobile sa najviše podudaranja.

#### Personalizovani predlog vozila korisniku na osnovu njegovog ranijeg rentiranja vozila.
Prilikom rentiranja vozila, za korisnika se vezuje istorija rentiranja i čuvaju svi podaci vozila.

Ovi podaci se čuvaju u bazi za svakog pojedinačnog korisnika i to u obliku ključ-vrednost. Ključ predstavlja jednu instancu entiteta pretrage (npr. model "Zastava") dok je vrednost broj koji predstavlja koliko puta se vozilo sa tim entitetom rentiralo.

Za svaki od prethodno navedenih podataka postoji pravilo koje pronalazi vozila koja poseduju entitete sačuvane u prethodno navedenom obliku. Vozila koja imaju podudaranja se pamte i sortiraju prema ukupnom broju broju podudaranja. Time dobijamo listu vozila koja su najsličnija onima koje je korisnik ranije rentirao. Sistem na zahtev dostavlja korisniku automobile sa najviše podudaranja.


### Literatura:

[1] [The Design of Web Based Car Recommendation System using Hybrid Recommender Algorithm](https://www.researchgate.net/publication/332683028_The_Design_of_Web_Based_Car_Recommendation_System_using_Hybrid_Recommender_Algorithm)

[2] [Integrating Knowledge-based and Collaborative-filtering Recommender Systems](https://www.researchgate.net/publication/2418883_Integrating_Knowledge-based_and_Collaborative-filtering_Recommender_Systems)
