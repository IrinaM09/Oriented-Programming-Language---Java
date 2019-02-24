# Oriented-Programming-Language---Java


					     DESCRIERE PROIECT
				   	         
	Dintr-un fisier, se citesc:
	 - un tip de CACHE si capacitatea  -- NoCache, LRU, LFU
	 - un tip de scheduler -- Random, RoundRobin, Weigthed 
	 - un numar de procese, fiecare cu tipul si cota sa -- CheckPrime, NextPrime, Cube, Square, Sqrt, Factorial, Fibonacci
	 - un numar de valori care trebuie procesate.

	Fiecare rezultat e scris intr-un fisier de output, linie cu linie.

	--- Pentru fiecare numar:
			- in functie de tipul de scheduler:
				- se alege un tip din vectorul de procese 
				- se calculeaza rezultatul si se stocheaza intr-un tip de cache
				- se scrie in fisierul de ouput

Legatura dintre clase:

	Process :	---------------------------------------
				| int number;						  |
				| abstract int calculate(int number); |
				---------------------------------------


											Process (parent class)
			 								    |
	    -----------------------------------------------------------------------------------------
		|		 	 	|				|		  	  |				|			|				|
	CheckPrime	 	NextPrime	   	  Cube			Square	   	  Sqrt		Factorial		Fibonacci	(child class)




	Scheduler :
											Scheduler (parent class)
												|
    				----------------------------------------------------------
    				|							|							 |
    			  Random   					RoundRobin        			  Weighted   (child class)


    Fiecare Scheduler foloseste un tip de cache:
	|
	|
	|
    |										 Cache
    |											|
    |				--------------------------------------------------------------
    |				|							|							     |
    |--->		 NoCache			LRU(Least recently used)			LFU(Least frequently used)


NoCache:
	In functie de tipul procesului, calculeaza rezultatul si il returneaza.

LruCache & LfuCache:
	Folosesc un vector de obiecte CacheProcessStructure[] (cu informatii despre un proces) care e parcurs de la coada la cap.
	Primesc un proces si pentru acesta verifica daca:
	  - exista in vector => set_time_used / used++
	  - daca nu exista => stocare 
	  - daca s-a depasit capacitatea => cautam in cache indexul cu procesul cel mai vechi / cel mai putin folosit si il stergem.


RandomScheduler:
 	Selecteaza random cate un proces.

RoundRobin:
	Alege un proces a.i. timpul de executie dintre doua procese sa nu depaseasca o unitate

Weighted:
	Alege un proces a.i. la un moment de timp multiplu de t, cotele impuse de administrator sa fie respectate.
