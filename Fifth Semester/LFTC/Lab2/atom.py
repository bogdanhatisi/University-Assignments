import re

class Atom:
    def __init__(self, input):
        self.input = input
        self.id = r"^[a-z][a-z0-9]{0,250}"
        self.constant = r"^[0-9]\.*[0-9]*"
        self.boolean_operators = ["<", ">", "<=", ">=", "==", "!="]
        self.operators = ["+", "-", "*", "/", "="]
        self.separators = [",", ";", "{", "}", "<<", ">>", "[", "]"]
        self.keywords = ["#include<iostream>", "struct", "main()", "using", "namespace", "std", "return", "cin>>", "cout<<", "if", "int", "char", "float"]

    def set_input(self, input):
        self.input = input

    def is_id(self):
        return bool(re.match(self.id, self.input))

    def is_const(self):
        return bool(re.match(self.constant, self.input))

    def is_bool_operator(self):
        return self.input in self.boolean_operators

    def is_operator(self):
        return self.input in self.operators

    def is_separator(self):
        return self.input in self.separators

    def is_keyword(self):
        return self.input in self.keywords

    def __str__(self):
        return self.input


