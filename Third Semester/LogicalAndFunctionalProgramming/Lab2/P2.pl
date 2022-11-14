%------------LAB-2-----------
 
 
% a)Definiti un predicat care determina produsul unui numar reprezentat 
%cifra cu cifra intr-o lista cu o anumita cifra. De ex: [1 9 3 5 9 9] * 2 =>[3 8 7 1 9 8]
 
%newAppend(L:Lista, E: Element, R: rezultat)
%newAppends(i,i,o)
%L: lista
%E: element
%R: rezultat
% Modelul Matematic
% [E], daca n = 0
% L1 U newAppend(L2...Ln,E,R)
 
newAppend([], E, [E]).
newAppend([H|T], E, [H|R]) :-
    newAppend(T, E, R).
 
 
%revList(L: Lista,R: Lista Rezultata)
%L: lista
%R: lista rezultata
%revList(i,o)
%Modelul Matematic
% [], n = 0
% revList(L2...Ln,R) U L1, altfel
 
revList([], []).
revList([H|T], R) :-
    revList(T, RI),
    newAppend(RI, H, R).
 
 
%product(L: lista, E: valoare, C: valoare, R: rezultat)
%L: lista
%E: valoare
%C: valoare
%R: rezultat
%product(i,i,i,o)
%Modelul Matematic
%[], daca n = 0 si catul = 0
% [C], daca n = 0 si catul != 0
% (L1*E+C)%10 U (L2...Ln,E,(H*E+C)/10),R), altfel
 
product([], _, 0, []).
product([], _, C, [C]) :- C =\= 0.
product([H|T], E, C, [HR|R]) :-
    HR is (H * E + C) mod 10,
    NC is (H * E + C - HR) / 10,
    product(T, E, NC, R).
 
 
productList(L, E, R) :- 
    revList(L, LI),
    product(LI, E, 0, RP),
    revList(RP, R).
 
%b)Se da o lista eterogena, formata din numere intregi si maximum 9 liste de numere  intregi.
%Sa  se  inlocuiasca  fiecare  sublista  cu  rezultatul inmultirii sublistei cu numarul de ordine al sublistei
% (prima sublista cu 1, a 2-a cu 2, etc.). 
% De ex:[1, [2, 3], [4, 1, 4], 3, 6, [7, 5, 1, 3, 9], 5, [1, 1, 1], 7] =>[1, [2, 3], [8, 2, 8], 3, 6, [2, 2, 5, 4,1, 7], 5, [4, 4, 4], 7]
 
 
 
 
%heterList(L1: list, R:  result list)
%L1: list
%L2: result list
%heterList(i,o)
%Modelul Matematic
%[], cand n = 0
% L1*2 U heterList(L2...Ln,R), daca L1 e lista
% heterList(L2...Ln,R),altfel
 
 
heterList([],[]).
heterList([H|T],[HR|R]) :- is_list(H), !,
    productList(H,4,HR),
    heterList(T,R).
heterList([H|T],[H|R]) :-
    heterList(T,R).