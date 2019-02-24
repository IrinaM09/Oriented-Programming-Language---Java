NUME: MITOCARU IRINA 
GRUPA: 324CB
			
 					____ ____ ___  ___    ___      __  
 					|_  _ |  ___|     \ /     |  /  _    \  /     | 
  					  | |  |  |__ |     .  .    |/   /_ \   \ ` |  | 
   					  | |  |   __||   | \ / |   |    _       |  |  | 
  					  | |  |  |__| |  |     |   |   |    |    |_|  |_
  					 \_/  \____/\_|     |_/\_ |    |_ / \___/
                                
                                
Fisiere ajutatoare:
	- HomeworkReader
	- HomerorkWriter
	- ProcessStructure
	- ProblemData
				   	        DESCRIERE CLASE

MainClass [main]:
 - este fisierul cu main
 - in functie de tipul de Scheduler dat:
	se creeaza un obiect de acest tip, care apeleaza metoda respectiva.

CacheProcessStructure:
 - contine structura unui obiect de tipul Cache, respectiv:
	tipul de scheduler
	numarul procesat
	rezultatul numarului procesat
	de cate ori a fost folosit procesul dat
	timpul folosirii procesului dat

Cache:
- clasa parinte
- contine metoda NoCache
	NoCache: 
	 - primeste:
	 	tipul procesului
	        numarul care trebuie procesat
	 - returneaza:
		rezultatul procesului dat

[extends Cache]:
   |---->LruCache:
	- extinde clasa Cache
	- primeste:
		un obiect de tipul CacheProcessStructure
		capacitatea
		numarul procesat
		timpul curent 
		fisierul de output
	- algoritm:
		se parcurge cache-ul, de la capacitatea maxima, pana la [baza -1]
	
		caz 1- daca la indexul curent nu se afla nimic:
			se apeleaza NoCache din Cache si se salveaza rezultatul 
			se completeaza cache[index] cu rezultatul dat, timpul folosirii, tipul de cache etc
			se printeaza ceea ce se cere + "Computed"
	
		caz 2- daca tipul de scheduler coincide cu cel de la indexul curent:
		            daca si tipul procesului coincid:
			se seteaza timpul curent  
			se printeaza ceea ce se cere + "FromCache"
	
		caz 3- daca indexul curent = -1, inseamna ca s-a umplut cache-ul:
			timpul minim = timpul ultimului proces din cache
			  se salveaza indexul procesului care trebuie inlocuit
			  se verifica daca exista un timp mai mic:
				 daca da, se salveaza noul timp si indexul
			 se sterge vechea informatie
			 se inlocuieste cu cea noua: intra in cazul 1

   |---->LfuCache:
	- extinde clasa Cache
	- primeste:
		un cache initializat pentru capacitatea data
		capacitatea
		numarul procesat
		timpul curent 
		fisierul de output
	- algoritm:
		acelasi ca la LruCache:
		(adaugare) la cazul 2-
			se incrementeaza numarul de folosire al procesului
		(modificare) la cazul 3-
			numarul minim de folosire = cel al ultimului proces din cache
		
Scheduler:
- clasa parinte
- contine metoda randomScheduler:
	randomScheduler:
	 - primeste:
		un obiect de tipul ProblemData
		fisierul de output
	 - algoritm:
		se parcurge lista cu numere
		pentru fiecare numar:
		  -se genereaza un index random pentru a alege un proces
		  -se apeleaza tipul de Cache dat in fisierul de input
		se incrementeaza timpul curent

[extends Scheduler]:
   |---->RoundRobinScheduler: 
	- extinde clasa Scheduler
	- contine metoda roundrobinScheduler:
		 - primeste:
			un obiect de tipul ProblemData
			fisierul de output
		 - algoritm:
			se parcurge lista cu numere
			pentru fiecare numar:
			  - un proces este ales astfel:
		  	  	 pas1- se iau de la primul la ultimul, asa cum sunt date in fisierul de input
		  	 	 daca numarul de procese se imparte la numarul curent, se reia pasul 1
		 	 - se apeleaza tipul de Cache dat in fisierul de input
			 - se incrementeaza timpul curent
 
   |---->WeightedScheduler:
	- extinde clasa Scheduler
	- contine metoda weightedScheduler:
		- primeste:
			un obiect de tipul ProblemData
			fisierul de output
	 	- algoritm:
			se calculeaza timpul comun = suma cote/cmmdc dintre procese
			se parcurge lista cu numere
			pentru fiecare numar:
		  	 - un proces:
			             - este executat de un nr de ori = (cota lui * timpul comun/ suma cote) 
		 	             - daca numarul curent se imparte la timpul comun, se reia cu primul proces dat
		                   - se apeleaza tipul de Cache dat in fisierul de input
			 - se incrementeaza timpul curent
			 - se scade numarul de executii al procesului curent

Process [abstract]:
- clasa abstracta; 
- contine metoda: calculate(number);

[extends Process]:
   |---->CheckPrime 
   |---->NextPrime   
   |---->Fibonacci   							             |
   |---->Factorial    
   |---->Square      
   |---->Cube       
   |---->Sqrt      

Gcd:
- calculeaza cel mai mare divizor comun
