from Ts import Ts as ts
from Transition import Transition
from FiniteStateMachine import FiniteStateMachine
from FipElement import FipElement
import os

def read_from_file(file_path):
    with open(file_path, 'r') as file:
        states = set(file.readline().split())
        initial_state = file.readline().strip()
        final_states = set(file.readline().split())
        alphabet = set(file.readline().split())

        transitions = []
        for line in file:
            elements = line.split()
            transitions.append(Transition(elements[0], elements[2], elements[1]))

        return FiniteStateMachine(states, alphabet, transitions, initial_state, final_states)

def main():
    __location__ = os.path.realpath(
        os.path.join(os.getcwd(), os.path.dirname(__file__)))
    af_id = read_from_file(os.path.join(__location__, 'identifiers.txt'))
    af_int = read_from_file(os.path.join(__location__, 'integers.txt'))
    af_real = read_from_file(os.path.join(__location__, 'real-numbers.txt'))
    fip = []

    program_path = os.path.join(__location__, 'program.txt')
    with open(program_path, 'r') as reader:
        init_atoms_map()

        line_number = 1
        for line in reader:
            line = line.strip()
            if not line:
                continue

            error = False
            finished = False
            while not finished:
                element = af_real.get_offset(line)

                if element:
                    pos = ts.find_atom(element)
                    fip_element = FipElement(element, atoms.get("CONST"), pos if pos is not None else ts.add_atom(element))
                    fip.append(fip_element)
                else:
                    element = af_int.get_offset(line)
                    if element:
                        pos = ts.find_atom(element)
                        fip_element = FipElement(element, atoms.get("CONST"), pos if pos is not None else ts.add_atom(element))
                        fip.append(fip_element)
                    else:
                        element = af_id.get_offset(line)
                        if element:
                            if atoms.get(element) is not None:
                                fip_element = FipElement(element, atoms.get(element), -1)
                                fip.append(fip_element)
                            else:
                                pos = ts.find_atom(element)
                                fip_element = FipElement(element, atoms.get("ID"), pos if pos is not None else ts.add_atom(element))
                                fip.append(fip_element)
                        else:
                            print(f"An error occurred on line: {line_number}")
                            error = True

                if error:
                    break

                line = line[len(element):].strip()
                finished = not line

            if error:
                break

            line_number += 1

    print("--------------- Forma interna a programului ------------------")
    for index, fip_element in enumerate(fip):
        print(f"Index: {index}; cod atom: {fip_element.get_cod_atom()}; cod in TS: {fip_element.get_cod_in_ts()}")

    print("--------------- Tabela de simboluri -------------------------")
    print(ts.print_table())

def init_atoms_map():
    global atoms
    atoms = {
        "ID": 0, "CONST": 1, "#include<iostream>": 2, "struct": 3, "main": 4, "using": 5, "namespace": 6,
        "std": 7, "return": 8, "cin": 9, "cout": 10, "if": 11, "int": 12, "char": 13, "float": 14, ";": 15,
        ",": 16, "[": 17, "]": 18, "{": 19, "}": 20, "-": 21, "+": 22, "/": 23, "*": 24, "=": 25, ">>": 26,
        "<<": 27, ">": 28, "<": 29, ">=": 30, "<=": 31, "==": 32, "!=": 33, "#": 34, "(": 35, ")": 36,
        "include": 37, "iostream": 38
    }

if __name__ == "__main__":
    main()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
