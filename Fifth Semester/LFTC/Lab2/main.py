from atom import Atom
from pathlib import Path

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
    with open(program_file, 'r') as reader:
        line_number = 1
        for line in reader:
            if not process(line, line_number):
                return
            line_number += 1

if __name__ == "__main__":
    main()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
