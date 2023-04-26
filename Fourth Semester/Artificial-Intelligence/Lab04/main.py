import random
from statistics import mean
import networkx as nx
import numpy as np
import matplotlib.pyplot as plt
from random import uniform

class Chromosome:
    def __init__(self, problemsParameters=None):
        self.__problemsParameters = problemsParameters
        self.__fitness = 0.0
        self.__representation = []
        self.__init_representation()

    @property
    def representation(self):
        return self.__representation

    @property
    def fitness(self):
        return self.__fitness

    @representation.setter
    def representation(self, chromosome=None):
        if chromosome is None:
            chromosome = []
        self.__representation = chromosome

    @fitness.setter
    def fitness(self, fit=0.0):
        self.__fitness = fit

    def crossover(self, c):
        cut = random.randint(0, len(self.__representation) - 1)
        new_representation = [None] * len(self.__representation)
        for i in range(cut):
            new_representation[i] = self.__representation[i]
        position = 0
        for i in range(cut, len(self.__representation)):
            while c.__representation[position] in new_representation:
                position += 1
            new_representation[i] = c.__representation[position]
        offspring = Chromosome(c.__problemsParameters)
        offspring.representation = new_representation
        return offspring

    def mutation(self):
        first_position = random.randint(0, len(self.__representation) - 1)
        second_position = random.randint(0, len(self.__representation) - 1)
        while self.__representation[first_position] != self.__representation[second_position]:
            second_position = random.randint(0, len(self.__representation) - 1)
        first_value = self.__representation[first_position]
        self.__representation[first_position] = self.__representation[second_position]
        self.__representation[second_position] = first_value

    def __str__(self):
        return 'Chromosome: ' + str(self.__representation) + ' has fit: ' + str(self.__fitness) + '.'

    def __repr__(self):
        return self.__str__()

    def __eq__(self, c):
        return self.__representation == c.__representation and self.__fitness == c.__fitness

    def __init_representation(self):
        self.__representation = [i for i in range(1, self.__problemsParameters['noNodes'] + 1)]
        random.shuffle(self.__representation)




class GA:
    def __init__(self, param=None, problem_parameters=None):
        self.__param = param
        self.__problem_parameters = problem_parameters
        self.__population = []

    @property
    def population(self):
        return self.__population

    def initialisation(self):
        for _ in range(0, self.__param['popSize']):
            c = self.__param["chromosome"](self.__problem_parameters)
            self.__population.append(c)

    def evaluation(self):
        for c in self.__population:
            c.fitness = self.__problem_parameters['function'](self.__problem_parameters, c.representation)

    def bestChromosome(self):
        best = self.__population[0]
        for c in self.__population:
            if c.fitness < best.fitness:
                best = c
        return best

    def worstChromosome(self):
        best = self.__population[0]
        for c in self.__population:
            if c.fitness > best.fitness:
                best = c
        return best

    def selection(self):
        pos1 = random.randint(0, self.__param['popSize'] - 1)
        pos2 = random.randint(0, self.__param['popSize'] - 1)
        if self.__population[pos1].fitness < self.__population[pos2].fitness:
            return pos1
        else:
            return pos2

    def oneGeneration(self):
        newPop = []
        for _ in range(self.__param['popSize']):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            off = p1.crossover(p2)
            off.mutation()
            newPop.append(off)
        self.__population = newPop
        self.evaluation()

    def oneGenerationElitism(self):
        newPop = [self.bestChromosome()]
        for _ in range(self.__param['popSize'] - 1):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            off = p1.crossover(p2)
            off.mutation()
            newPop.append(off)
        self.__population = newPop
        self.evaluation()

    def oneGenerationSteadyState(self):
        for _ in range(self.__param['popSize']):
            p1 = self.__population[self.selection()]
            p2 = self.__population[self.selection()]
            off = p1.crossover(p2)
            off.mutation()
            off.fitness = self.__problem_parameters['function'](off.repres)
            worst = self.worstChromosome()
            if off.fitness < worst.fitness:
                worst = off


def read_graph_from_file(file_name):
    with open(file_name, "r") as f:
        n = int(f.readline())
        matrix = []
        for i in range(n):
            line = f.readline()
            elems = list(map(int, line.split(",")))
            matrix.append(elems)
        no_edges = sum(matrix[i][j] != 0 and j > i for i in range(n) for j in range(n))
        for i in range(n):
            for j in range(n):
                if matrix[i][j] == 0:
                    matrix[i][j] = float('inf')
        graph = {
            'noNodes': n,
            'matrix': matrix,
            'noEdges': no_edges,
            'source': int(f.readline()),
            'destination': int(f.readline())
        }
    return graph

def calculate_distance(graph, path):
    matrix = graph['matrix']
    length = sum(matrix[path[i]-1][path[i+1]-1] for i in range(len(path)-1))
    length += matrix[path[-1]-1][path[0]-1]  # Add the last edge from the last node to the first node.
    return length



def shortestPath(graph, pop_size=50,generations=50,eval_function=calculate_distance,chromosome=Chromosome):
    genetic_params = {
        "popSize":pop_size,
        "noGen":generations,
        "function":eval_function,
        "chromosome": chromosome
    }

    problem_params= graph
    problem_params["function"] = eval_function
    ga = GA(genetic_params,problem_params)
    ga.initialisation()
    ga.evaluation()
    best = []
    last = []


    for generation in range(genetic_params["noGen"]):
        ga.oneGenerationElitism()
        best_chromo = ga.bestChromosome()
        last = ga.population
        best.append(best_chromo)

    the_best_chromo = best[0]
    for x in best:
        if x.fitness > the_best_chromo.fitness:
            the_best_chromo = x

    the_best_chromos = [the_best_chromo]

    for chromo in last:
        if chromo.fitness == the_best_chromo.fitness and chromo not in the_best_chromos:
            the_best_chromos.append(chromo)

        return the_best_chromos



if __name__ == "__main__":
    graph = read_graph_from_file("medium01.txt")
    result = shortestPath(graph, 500, 100)
    for res in result:
        print(res)