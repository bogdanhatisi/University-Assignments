import csv
import os
import matplotlib.pyplot as plt
import numpy as np
from sklearn import linear_model
from sklearn.metrics import mean_squared_error


def loadData(filename, inputsNames, outputsNames):
    data = []
    dataNames = []
    with open(filename) as csv_file:
        csv_reader = csv.reader(csv_file, delimiter=',')
        line_count = 0
        for row in csv_reader:
            if line_count == 0:
                dataNames = row
            else:
                data.append(row)
            line_count += 1

    selectedVariables = [dataNames.index(variable) for variable in inputsNames]
    inputs = []
    for variable in selectedVariables:
        newData = []
        for index in range(len(data)):
            if data[index][variable] != '':
                newData.append(data[index][variable])
            else:
                newData.append(0)

        inputs.append(newData)

    outputSelected = dataNames.index(outputsNames)
    outputs = [data[index][outputSelected] for index in range(len(data))]

    return inputs, outputs


