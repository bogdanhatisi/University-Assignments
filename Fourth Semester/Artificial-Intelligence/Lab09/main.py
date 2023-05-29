import sys
import numpy as np
import matplotlib as plt
import nnfs
from nnfs.datasets import spiral_data

import os
import cv2
import numpy as np
from keras import Sequential
from keras.layers import Flatten, Dense
from sklearn.model_selection import train_test_split
# np.random.seed(0)

nnfs.init()

X, y = spiral_data(100, 3)
print(X)


class Layer_Dense:
    def __init__(self, n_inputs, n_neurons):
        self.weights = 0.10 * np.random.randn(n_inputs, n_neurons)
        self.biases = np.zeros((1, n_neurons), dtype=float)

    def forward(self, inputs):
        self.output = np.dot(inputs, self.weights) + self.biases


class Activation_ReLU:
    def forward(self, inputs):
        self.output = np.maximum(0, inputs)

