MITOCARU IRINA
324CB
						TEMA3 - POO
					    
Main <class>:
 - se citeste inputul si se salveaza intr-un obiect de tipul Maze;
 - se apeleaza taskul ales.

Maze <class>:
 - constructorul salveaza inputul intr-un obiect de tipul maze;
 - un obiect de tipul Maze contine:
		- o matrice de tipul Cell;
		- inaltimea si lungimea;
		- coordonatele inceputului;
		- coordonatele sfarsitului;
		- orientarea unei celulei.
-----------------------------------------------------------------------------------------------

Cell:
    |_ ICell
    |_ OCell
    |_ RoadCell
    |_ WallCell

Cell <abstract class>:
 - un obiect de tipul Cell contine:
 		- coordonatele celulei;
		- o metoda care returneaza numarul de vizite a celulei;
		- o metoda care seteaza daca poate fi vizitata(true) sau nu(false).

ICell / OCell / RoadCell <class> extends Cell:
 - salveaza coordonatele;
 - aceasta celula poate fi vizitata.

WallCell <class> extends Cell:
 - aceasta celula nu poate fi vizitata.

-----------------------------------------------------------------------------------------------

Exception:
	 |_ CannotMoveIntoWallsException 
	 |_ HeroOutOfGroundException 

CannotMoveIntoWallsException <class> extends Exception:
 - arunca o exceptie cand eroul incearca sa paseasca pe o celula zid.

HeroOutOfGroundException <class> extends Exception:
 - arunca o exceptie cand eroul incearca sa paseasca in afara labirintului.

-----------------------------------------------------------------------------------------------

Pair <class>:
 - un obiect de tipul Pair contine:
		- de cate ori a fost vizitata celula;
		- pozitia sa (E / S / V / N).

CompareNeighbours <class>:
 - compara doua obiecte de tipul Pair:
 - returneaza:
		- (-1): prima celula a fost vizitata de mai putine ori decat a doua;
		- (1) : prima celula a fost vizitata de mai mute ori decat a doua;
		- daca prima celula a fost vizitata la fel de multe ori ca si a doua:
			- compara dupa pozitie, astfel:
*pentru eficacitate, am atribuit pozitiilor caracterele:
 pentru 
	E -> A, 
	S -> B,
	V -> C,
 	N -> D.
 Astfel, se compara codul ASCII a caracterelor A, B, C si D ( numere consecutive -> comparatie imediata).

Orientation <class>:
- rightOrientation :
	- returneaza orientarea pentru E S V N index : 0 : dreapta, 1 : sus, 2: stanga, 3 : jos

- leftOrientation: 
	- returneaza orientarea pentru V S E N index : 0 : stanga, 1 : sus, 2: dreapta, 3 : jos

- reverseOrientation: 
	- returneaza orientarea inversa.

Antrenament1 <class>:
 -algoritm:
	- Cat timp celula curenta nu este cea de iesire:
	  |_ tratam exceptiile;
	  |_ ne uitam in dreapta, deasupra, stanga si dedesubt si, in functie de vederea curenta:
		- salvam coordonatele si pozitia;
		- verificam daca este iesirea.
	  |_ sortam daca nu este iesirea si daca avem minim doua elemente
	    *sortarea se face prin Collection.sort si sorteaza ascendent, dupa codul ASCII
	     		 A, B, C, D.
			(E, S, V, N)
	  |_ daca nu este iesirea si nu avem ce sorta, pozitia e data de singurul element
	  |_ salvam celula curenta,incrementam numarul de vizite si facem urmatoarea miscare,
	      tinand cont de vedere.
	- Scriem in fisier. 

Antrenament2 <class>:
 - algoritm:
	- Cream doua matrici cu :
	 	-1 : unde avem zid
		0 : unde se poate merge , pentru labirint, respectiv distanta fata de celula start
	- Apelam bfsPath si verificam daca drumul este valid;
	- Scriem in fisier drumul.

  - algoritm  BFS - shortestPath:

    Pentru cautarea celulei de iesire:
	  - Cream o lista cu drumul curent si adaugam startul.
	  - Cat timp avem cel putin o miscare in drum: 
		- Cream vectorii pentru schimbarea orientarii E S V N.
		- Luam o pereche de coordonate din drrum.
		- Tratam exceptiile.
		- Daca am ajuns la iesire, ne oprim.
		- Verificam in toate cele 4 directii pentru coordonatele curente in functie de vederea E S V N.
		- Salvam orientarea curenta a coordonatelor curente si ne mutam orientarea.
		- Daca iesirea e celula vecina, adaugam orientarea intr-un vector.
		- Daca incercam sa accesam in afara perimetrului, continuam cautarea.
		- Daca celula este valida, o adaugam la drum si salvam distanta ei fata de start.
	  	- Tratam exceptia : daca nivelul iesirii a ramas 0.

   1. Distanta minima e data de nivelul celulei cu iesirea.
   2. Fiecare celula valida va avea distanta ei fata de celula start.
   3. Drumul se reconstruieste decrementam nivelul celulei curente fata de start cu 1, luand si vederile in calcul.

  Pentru construirea drumului final:
	- Incepem de la iesire si luam directia prioritara dintre E S V N.
	- Orientarea va fi inversa fata de cea cu care s-a ajuns la iesire.
	- Cat timp nu am ajuns la celula start:
		- In functie de pozitia iesirii fata de start, ne miscam V S E N sau E S V N
	 pentru iend < istart && jend <= jstart sau iend >= istart && jend < jstart sau iend < istart && jend > jstart:
		Ne miscam V S E N
	pentru iend >= istart && jend >= jstart:
		Ne miscam E S V N
		- Verificam in toate cele 4 directii pentru coordonatele curente in functie de vedere.
		- Daca distanta celulei vecine fata de cea la care se verifica vecinii este aceeasi, ne mutam pe acea celula
		si adaugam celula curenta la drumul final.
 
  La final, inainte de scrierea in fisier, adaugam si startul.