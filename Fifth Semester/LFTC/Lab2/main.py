import re

from atom import Atom
from pathlib import Path

from fip import FipElement
from ts import Ts


def process(line, line_number):
    line = line.strip()
    if line == "":
        return True
    elements = line.split()

    for element in elements:
        atom = Atom(element)

        if atom.is_keyword():
            element_print(f"{atom} - KEYWORD")
        elif atom.is_separator():
            element_print(f"{atom} - SEPARATOR")
        elif atom.is_id():
            element_print(f"{atom} - ID")
        elif atom.is_const():
            element_print(f"{atom} - CONSTANT")
        elif atom.is_bool_operator():
            element_print(f"{atom} - BOOLEAN OPERATOR")
        elif atom.is_operator():
            element_print(f"{atom} - OPERATOR")
        else:
            print(f"Error on line {line_number}. Aborting!")
            return False

    return True

def element_print(s):
    print(s)

def main():
    program_file = Path(__file__).with_name('code.txt')

    atoms = {
        "ID": 0,
        "CONST": 1,
        "#include<iostream>": 2,
        "struct": 3,
        "main()": 4,
        "using": 5,
        "namespace": 6,
        "std": 7,
        "return": 8,
        "cin>>": 9,
        "cout<<": 10,
        "if": 11,
        "int": 12,
        "char": 13,
        "float": 14,
        ";": 15,
        ",": 16,
        "[": 17,
        "]": 18,
        "{": 19,
        "}": 20,
        "-": 21,
        "+": 22,
        "/": 23,
        "*": 24,
        "=": 25,
        ">>": 26,
        "<<": 27,
        ">": 28,
        "<": 29,
        ">=": 30,
        "<=": 31,
        "==": 32,
        "!=": 33
    }

    fip = []
    tsID = Ts()
    tsConst = Ts()

    with open(program_file) as program:
        lineNumber = 1
        for line in program:
            line = line.strip()
            if not line:
                continue

            elements = line.split()
            for element in elements:
                fipElement = None
                if isConst(element):
                    pos = tsConst.findAtom(element)
                    fipElement = FipElement(element, atoms["CONST"], tsConst.addAtom(element) if pos is None else pos)
                elif element in atoms:
                    fipElement = FipElement(element, atoms[element], -1)
                elif isId(element):
                    pos = tsID.findAtom(element)
                    fipElement = FipElement(element, atoms["ID"], tsID.addAtom(element) if pos is None else pos)
                else:
                    print(f"An error occurred on line {lineNumber}")
                    return
                fip.append(fipElement)
            lineNumber += 1

    print("FORMA INTERNA A PROGRAMULUI")
    for index, fipElement in enumerate(fip):
        print(f"Index: {index}; cod atom: {fipElement.codAtom}; cod in TS: {fipElement.codInTs}")

    print("TABELA DE SIMBOLURI ID-URI")
    print(tsID.printTable())

    print("TABELA DE SIMBOLURI CONSTANTE")
    print(tsConst.printTable())


def isId(atom):
    return bool(re.match("^[a-z][a-z0-9]{0,250}$", atom))


def isConst(atom):
    return bool(re.match("^[0-9]\\.*[0-9]*$", atom))


if __name__ == "__main__":
    main()

