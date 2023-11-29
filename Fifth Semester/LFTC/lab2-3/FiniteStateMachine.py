class FiniteStateMachine:
    def __init__(self, states, alphabet, transitions, initial_state, final_states):
        self.states = states
        self.alphabet = alphabet
        self.transitions = transitions
        self.initial_state = initial_state
        self.final_states = final_states

        self.letters = set("abcdefghijklmnopqrstuvwxyz")
        self.digits = set("0123456789")
        self.non_zero_digits = set("123456789")
        self.binary_digits = set("01")
        self.octal_digits = set("01234567")
        self.hex_digits = set("0123456789abcdef")

    def check_compliance(self, sequence):
        return self.get_offset(sequence) == sequence

    def get_offset(self, sequence):
        result = ""
        temporary = ""

        actual_state = self.initial_state
        for index in range(len(sequence)):
            valid_transitions = [transition for transition in self.transitions if
                                 transition.get_initial_state() == actual_state]

            valid_transition = [transition for transition in valid_transitions if
                                transition.get_value() == sequence[index]]

            if len(valid_transition) != 1:
                global_valid = False
                for transition in valid_transitions:
                    if len(transition.get_value()) == 1:
                        continue

                    valid = False
                    if transition.get_value() == "dd":
                        valid = sequence[index] in self.digits
                    elif transition.get_value() == "d*":
                        valid = sequence[index] in self.non_zero_digits
                    elif transition.get_value() == "xd":
                        valid = sequence[index] in self.hex_digits
                    elif transition.get_value() == "bd":
                        valid = sequence[index] in self.binary_digits
                    elif transition.get_value() == "od":
                        valid = sequence[index] in self.octal_digits
                    elif transition.get_value() == "let":
                        valid = sequence[index] in self.letters
                    else:
                        print("ERROR! Invalid state machine")
                        exit(0)

                    if valid and not global_valid:
                        valid_transition.append(transition)
                        global_valid = True

                if not global_valid:
                    return result

            temporary += sequence[index]

            actual_state = valid_transition[0].get_final_state()

            if actual_state in self.final_states:
                result = temporary

        return result

    def is_not_determinist(self):
        return any(transition1.get_initial_state() == transition.get_initial_state() and
                   transition1.get_value() == transition.get_value() and
                   transition1.get_final_state() != transition.get_final_state()
                   for transition in self.transitions
                   for transition1 in self.transitions)



    def check_validity(self, sequence):
        return self.get_offset(sequence) == sequence

    def check_prefix(self, sequence):
        return self.get_offset(sequence)

    def get_states(self):
        return self.states

    def set_states(self, states):
        self.states = states

    def get_alphabet(self):
        return self.alphabet

    def set_alphabet(self, alphabet):
        self.alphabet = alphabet

    def get_transitions(self):
        return self.transitions

    def set_transitions(self, transitions):
        self.transitions = transitions

    def get_initial_state(self):
        return self.initial_state

    def set_initial_state(self, initial_state):
        self.initial_state = initial_state

    def get_final_states(self):
        return self.final_states

    def set_final_states(self, final_states):
        self.final_states = final_states

    def __str__(self):
        return f"FiniteStateMachine(states={self.states}, alphabet={self.alphabet}, " \
               f"transitions={self.transitions}, initial_state='{self.initial_state}', " \
               f"final_states={self.final_states}, letters={self.letters}, digits={self.digits})"