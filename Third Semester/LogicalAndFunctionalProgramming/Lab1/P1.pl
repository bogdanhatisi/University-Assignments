%------------LAB-1-----------
 
%a)Sa se scrie un predicat care intoarce intersectia a doua multimi
 
 
%searchElemntInSet(E element,L lista)
%E element
%L lista
%searchElementInSet(i,i)
%Modelul Matematic
% True, daca E apartine L
% Fals, daca E nu apartine L
% searchElementsInSet(E,L2...LN),altfel
searchElementInSet(E, [E_])-!.
 
searchElementInSet(E, [_T])-
    searchElementInSet(E, T).
 
%intersectSets(L lista,L2 lista,R lista)
%L - lista 1
%L2 - lista 2
%R- lista rezulatata
%intersectSets(i,i,o)
%Modelul Matematic
% [], n = 0
% L1 U intersectSets(L2..LN,L21...L2n,R), daca L1 apartine L2
% intersectSets(L2..LN,L21...L2n,R), altfel
 
intersectSets([], _, []).
intersectSets([HT], S2, RS)-
    searchElementInSet(H, S2),
    intersectSets(T, S2, R),
    RS = [HR],
    !.
 
intersectSets([_T], S2, RS)-
    intersectSets(T, S2, RS).
 
%b)Sa se scrie un predicat ce intoarce o lista cu numerele de la m la n
 
%list(M valoare,N valoare,R lista rezultata)
%M valoare
%N valoare
%R lista rezultata
%list(i,i,o)
%Modelul Matematic
%[], daca M N
%M U list(M+1,N,R),altfel
 
list(M,N,[])- M  N, !.
list(M,N,[MR])-
    NewM is (M + 1),
    list(NewM,N,R).