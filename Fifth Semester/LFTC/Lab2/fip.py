class FipElement:
    def __init__(self, atom, codAtom, codInTs):
        self.atom = atom
        self.codAtom = codAtom
        self.codInTs = codInTs

    def getAtom(self):
        return self.atom

    def setAtom(self, atom):
        self.atom = atom

    def getCodAtom(self):
        return self.codAtom

    def setCodAtom(self, codAtom):
        self.codAtom = codAtom

    def getCodInTs(self):
        return self.codInTs

    def setCodInTs(self, codInTs):
        self.codInTs = codInTs

    def __str__(self):
        return f'FipElement{{codAtom={self.codAtom}, codInTs={self.codInTs}}}'

