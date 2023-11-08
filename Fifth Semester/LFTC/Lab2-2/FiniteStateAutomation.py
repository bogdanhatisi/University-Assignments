class FiniteStateAutomation:
    def __init__(self, states, alphabet, transitions, initial_state, final_states):
        self.states = states
        self.alphabet = alphabet
        self.transitions = transitions
        self.initial_state = initial_state
        self.final_states = final_states

        self.letters = set("abcdefghijklmnopqrstuvwxyz")
        self.digits = set("0123456789")
        self.sign=set('+-')

    def match_expression(self, sequence):
        result = []
        actual_state = self.initial_state

        for char in sequence:
            valid_transitions = [transition for transition in self.transitions if transition.get_initial_state() == actual_state]

            valid_transition = None
            for transition in valid_transitions:
                if transition.get_value() == char:
                    valid_transition = transition
                    break

            if valid_transition is None:
                return ''.join(result)

            result.append(char)
            actual_state = valid_transition.get_final_state()

        if actual_state in self.final_states:
            return ''.join(result)
        else:
            return None

    def check_validity(self, sequence):
        return self.match_expression(sequence) == sequence

    def check_prefix(self, sequence):
        return self.match_expression(sequence)

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
        return f"FiniteStateMachine{{states={self.states}, alphabet={self.alphabet}, transitions={self.transitions}, initial_state='{self.initial_state}', final_states={self.final_states}, letters={self.letters}, digits={self.digits},sing={self.sign} }}"
