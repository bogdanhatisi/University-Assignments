class Ts:
    def __init__(self):
        self.table = []

    def add_atom(self, element):
        self.table.append(element)
        self.table.sort()

        return self.table.index(element)

    def find_atom(self, element):

        try:
            index = self.table.index(element)
            return index
        except ValueError:
            return None

    def __str__(self):
        return str(self.table)

    def print_table(self):
        result = ""
        pattern = "cod TS: %d; atom: %s\n"
        for i in range(len(self.table)):
            result += pattern % (i, self.table[i])

        return result