# Genetic Algorithms - KEN1210 Practical Assignment 1 

## About This Project

This project implements a Genetic Algorithm from scratch in Java to solve the classic "HELLO WORLD" optimization problem. Starting from a population of randomly generated strings, the algorithm evolves increasingly fit solutions until it converges to the target phrase.

The project was originally developed as part of the KEN1210 course on Genetic Algorithms at Maastricht University. While the problem statement was provided by the course, all core evolutionary components were implemented independently.

The objective of the algorithm is to discover the target phrase through evolutionary search rather than direct programming, demonstrating how complex solutions can emerge from simple selection and variation mechanisms.

## Features

Random population initialization
Fitness-based evaluation
Tournament selection
One-point crossover
Mutation operator
Generation replacement
Evolutionary optimization loop
Convergence tracking
Configurable population size, mutation rate, and crossover rate

## Concept Demonstrated

Genetic Algorithms
Evolutionary Computation
Population-Based Optimization
Fitness Functions
Tournament Selection
One-Point Crossover
Mutation Operators
Stochastic Search
Convergence Analysis
Search Space Exploration
Object-Oriented Programming (Java)

## Technical Skills

Algorithm Design
Evolutionary Optimization
Heuristic Search Methods
Software Development

## How It Works

The algorithm begins with a randomly generated population of candidate solutions. Each individual is evaluated according to its similarity to the target phrase "HELLO WORLD".

At each generation:

Individuals are evaluated using a fitness function.
Parents are selected using tournament selection.
Offspring are generated through one-point crossover.
Random mutations introduce diversity.
A new population is created.
The process repeats until the target solution is found or the maximum number of generations is reached.

The best-performing individuals of each generation are displayed, allowing the evolutionary process to be observed in real time.

## Academic Context

This repository is shared for educational and portfolio purposes to demonstrate my understanding of evolutionary algorithms, optimization techniques, and algorithm implementation in Java.

For the public GitHub version, third-party sorting code distributed with the assignment has been removed and replaced with standard Java functionality.
