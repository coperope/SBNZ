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
- Podaci o korisniku kao i njegove preferencije i eventualna istorija korišćenja
- Povratna informacija korisnika
- Oznake pri pretrazi (npr. jeftin, porodični, niska potrošnja)

#### Očekivani izlazi sistema:

- Oznake nastale pri kategorizaciji automobila
- Predlog automobila krajnjem korisniku
- Predlog biznis plana izdavačima oglasa

### Baza znanja:

- Pravila za kategorizaciju i dodavanje oznaka vozilima
- Pravila za predlog biznis plana (predlog za postavljanje određenih parametara oglasa na osnovu
uspešnosti drugih oglasa koji su ih koristili)
- Pravila za predlaganje automobila

Baza znanja će se popunjavati delimično automatski na osnovu implicitne i eksplicitne aktivnosti
korisnika. Implicitno podrazumeva pasivno praćenje aktivnosti korisnika odnosno statistiku
posećivanja oglasa i izvođenje zaključaka na tim osnovama. Podaci se mogu prikupiti i eksplicitnim
navođenjem preferencija i želja od strane korisnika.

### Primeri rezonovanja:

1. Prilikom kreiranja oglasa za automobil unose se njegovi osnovni podaci na osnovu kojih mu se
dodeljuju oznake (npr. ekonomska klasa, niska potrošnja, veličina prtljažnika, prostor za
putnike...) i na osnovu toga se vrši kategorizacija istog.
2. Korisnik unosi preferencije i želje prilikom registracije (moguće naknadne izmene) na osnovu
kojih sistem predlaže oglase. Nakon izvesne aktivnosti korisnika kreiraju se događaji koji utiču
na korekciju predloga oglasa.
3. U određenim vremenskim intervalima ponuđivači oglasa koji žele mogu dobiti predloge u vezi
sa poboljšanjem parametara njihovih oglasa što bi rezultovalo povećanjem profita. Sistem prati
uspešnost oglasa iz iste ili slične kategorije i zaključuje koji njihovi parametri su doveli do
većeg profita.
Takođe se prikazuju kategorije i oznake oglasa koji su najpretraženiji i/ili najuspešniji.

### Pravila:
#### Tagovi:
High fuel consumption ->= 12 litres per 100km <br />
Medium fuel consumption -> 8 - 12 litres per 100km <br />
Low fuel consumption -> <= 8 litres per 100km <br />
Sport engine power -> > 174 W/kg <br />
High engine power -> >= 114 W/kg <br />
Medium engine power -> 49 > x < 114 W/kg <br />
Low engine power -> <= 49 W/kg <br />
Family car -> seats >= 5 <br />
Big family car -> Family car AND combined passenger and cargo volume >= 3086 L  <br />
Small family car -> Family car AND combined passenger and cargo volume < 3086 L  <br />
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
### Literatura:

[1] [The Design of Web Based Car Recommendation System using Hybrid Recommender Algorithm](https://www.researchgate.net/publication/332683028_The_Design_of_Web_Based_Car_Recommendation_System_using_Hybrid_Recommender_Algorithm)

[2] [Integrating Knowledge-based and Collaborative-filtering Recommender Systems](https://www.researchgate.net/publication/2418883_Integrating_Knowledge-based_and_Collaborative-filtering_Recommender_Systems)
