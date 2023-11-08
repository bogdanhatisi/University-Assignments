from FiniteStateAutomation import FiniteStateAutomation
from Transition import Transition

#  EBNF
#
#  finite_state_automation = state_list, initial_state, final_state, transitions.
#  state_list & final_state, = state {" " , state}.
#  initial_state = state
#  transitions = transition { "\n", transition  }
#  state = q,CONST
#  transition  = state CONST state
#  CONST = digit{digit}
#  digit = [0-9]
#  sign = [+-]
#  CONST= sign{digit}

def read_file(file):
    try:
        with open(file, 'r') as f:
            states = set(f.readline().strip().split())
            initial_state = f.readline().strip()
            final_states = set(f.readline().strip().split())
            alphabet = set(f.readline().strip().split())

            transitions = []
            for line in f:
                elements = line.strip().split()
                if len(elements) == 3:
                    transitions.append(Transition(elements[0], elements[2], elements[1]))
                else:
                    break

            af = FiniteStateAutomation(states, alphabet, transitions, initial_state, final_states)

            print("Finite state machine successfully read from file")
            return af
    except IOError as e:
        raise RuntimeError(e)


def read_console():
    states = set(input("Give the possible states: ").split())
    initial_state = input("Give the initial state: ")
    final_states = set(input("Give the final states: ").split())
    alphabet = set(input("Give the alphabet: ").split())

    print("Introduce the transition and then press Enter")
    transitions = []
    while True:
        transition = input()
        if not transition:
            break
        elements = transition.split()
        if len(elements) == 3:
            transitions.append(Transition(elements[0], elements[2], elements[1]))
        else:
            print("Invalid transition format. Exiting...")
            return None

    af =  FiniteStateAutomation(states, alphabet, transitions, initial_state, final_states)
    print("Finite state automation successfully read from console")
    return af


def run_main():
    print("Choose your input:")
    print("\t1. File")
    print("\t2. Console")
    print("\t3. Exit")

    command = input()

    if command == "1":
        af = read_file("input.txt")
    elif command == "2":
        af = read_console()
    elif command == "3":
        print("Have a nice day!")
        return
    else:
        print("Error! Going to sleep")
        return

    done = False

    while not done:
        print("----------Functionalities------------------")
        print("\t1. States")
        print("\t2. Input alphabet")
        print("\t3. Transitions")
        print("\t4. Final states")
        print("\t5. Validate sequence")
        print("\t6. Determine the longest prefix")
        print("\t0. Exit")

        command = input()

        if command == "1":
            print(af.get_states())
        elif command == "2":
            print(af.get_alphabet())
        elif command == "3":
            for transition in af.get_transitions():
                print(transition)
        elif command == "4":
            for final_state in af.get_final_states():
                print(final_state)
        elif command == "5":
            sequence = input("Introduce the sequence: ")
            if af.check_validity(sequence):
                print("The sequence is valid")
            else:
                print("The sequence is not valid")
        elif command == "6":
            sequence = input("Introduce the sequence: ")
            result = af.check_prefix(sequence)
            print(result if result is not None else sequence)
        elif command == "0":
            print("Nb!")
            done = True
        else:
            print("Error! Code exploded")
            return


if __name__ == "__main__":
    run_main()
