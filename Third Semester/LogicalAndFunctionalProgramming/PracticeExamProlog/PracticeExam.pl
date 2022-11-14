%Se da o lista formata din numere intregi. Se cere ca toate aparitiile elementului maxim sa se inlocuiasca cu o valoare x data.
%De exemplu:
%[3 6 2 4 6 1] 11 -> [ 3 11 2 4 11 1]


%
%maxim(L: Lista,R: numar)
%maxim(i,o)
%L: lista
%R: raspuns
%    Modelul Matematic
%  R , daca n = 0
% maxim(L2...Ln,L1), daca L1 > R
%maxim(L2....Ln,R), altfel

maxim([],R,R).
maxim([H|T],R,Max):- H > R,!,
    maxim(T,H,Max).
maxim([|T],R,Max):-
    maxim(T,R,Max).




%
%inlocuieste(L: lista,M: maxim, X: valoare,R: lista noua)
%L: lista
%M: maxim
%X: valoare
%R: lista noua
%inlocuieste(i,i,o)
% Modelul matematic
% [], daca n = 0
% X U inlocuieste(L2..Ln,M, X, R), daca L1 = M
% L1 U inlocuieste(L2...Ln, M, X, R), altfel

inlocuieste([],,_,[]).
inlocuieste([H|T],M,X,[X|R]):- H=M,!,
    inlocuieste(T,M,X,R).
inlocuieste([H|T],M,X,[H|R]):-
    inlocuieste(T,M,X,R).

inlocuiesteFinal(L,R):-
    maxim(L,0,Max),
    write(Max),
    inlocuieste(L,Max,5,R).

inlocuiesteFinal([1,4,2,4,3],R)