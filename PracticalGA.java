import java.util.Arrays;
import java.util.Comparator;

public class PracticalGA {

    static final String TARGET = "HELLO WORLD";
    static char[] alphabet = new char[27];

    static int popSize = 100;
    static double mutationRate = 0.05;
    static double crossoverRate = 0.65;
    static final int MAX_GENERATIONS = 10000;

    public static void main(String[] args) {

        fillAlphabet();

        Individual[] population = new Individual[popSize];

        for (int i = 0; i < popSize; i++) {
            char a[] = new char[TARGET.length()];
            for (int j = 0; j < TARGET.length(); j++) {
                a[j] = randomLetter();
            }
            population[i] = new Individual(a);
            population[i].setFitness(fitnessScore(a));
        }

        int generation = 0;
        boolean found = false;

        while (!found && generation < MAX_GENERATIONS) {

            for (int i = 0; i < popSize; i++) {
                population[i].setFitness(fitnessScore(population[i].getChromosome()));
            }

            Arrays.sort(population, Comparator.comparingDouble(Individual::getFitness));

            Individual best = population[0];
            Individual secondBest = population[1];
            Individual thirdBest = population[2];

            System.out.println("Generation #: " + generation);
            System.out.println("The best individual is: " + best.genoToPhenotype() + " fitness: " + best.getFitness());
            System.out.println("The second best individual is: " + secondBest.genoToPhenotype() + " fitness: "
                    + secondBest.getFitness());
            System.out.println("The third best individual is: " + thirdBest.genoToPhenotype() + " fitness: "
                    + thirdBest.getFitness());
            System.out.println();

            if (best.getFitness() == 0) {
                found = true;
                System.out.println("Solution found: " + best.genoToPhenotype());
            }

            population = makeNewPopulation(population);

            generation++;
        }
    }
    
    static void fillAlphabet() {
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet[c - 'A'] = c;
        }

        alphabet[26] = ' ';
    }

    static char randomLetter() {
        int index = (int) (Math.random() * alphabet.length);
        return alphabet[index];

    }

    static double fitnessScore(char a[]) {
        double sum = 0;
        for (int i = 0; i < TARGET.length(); i++) {
            sum += Math.pow(a[i] - TARGET.charAt(i), 2);
        }
        return sum;
    }

    static Individual[] selection(Individual[] population) {
        Individual parents[] = new Individual[2];
        do {
            parents[0] = tournament(population);
            parents[1] = tournament(population);
        } while(parents[0] == parents[1]);

        return parents;
    }

    static Individual tournament(Individual[] population) {
        int index1;
        int index2;
        int index3;
        do{
            index1= (int)(Math.random()*popSize);
            index2= (int)(Math.random()*popSize);
            index3 = (int) (Math.random() * popSize);
        } while (index1 == index2 || index1 == index3 || index3 == index2);

        Individual a = population[index1];
        Individual b = population[index2];
        Individual c = population[index3];

        if(a.getFitness() <= b.getFitness() && a.getFitness() <= c.getFitness()){
            return a;
        } else if(b.getFitness() <= a.getFitness() && b.getFitness() <= c.getFitness()){
            return b;
        } else {
            return c;
        }
    }

    static void crossover(Individual a, Individual b) {
        if (Math.random() <= crossoverRate) {
            int index = (int) (Math.random() * a.chromosome.length);
            char[] c1 = a.getChromosome();
            char[] c2 = b.getChromosome();
            char[] temp = new char[a.getChromosome().length];
            for(int i = 0; i < a.getChromosome().length; i++){
                temp[i]=c2[i];
            }
            for (int i = index; i < a.getChromosome().length; i++) {
                c2[i] = c1[i];
            }
            for (int i = index; i < a.getChromosome().length; i++) {
                c1[i] = temp[i];
            }
        }
    }

    static void mutation(Individual individual) {
        if (Math.random() <= mutationRate) {
            char[] chromosome = individual.getChromosome();
            int index = (int) (Math.random() * TARGET.length());
            char newLetter;
            do{
                newLetter = randomLetter();
            } while (newLetter == chromosome[index]);
            chromosome[index] = newLetter;
        }
    }

    static Individual[] makeNewPopulation(Individual[] oldPopulation) {
        Individual newPopulation[] = new Individual[oldPopulation.length];
        for (int i = 0; i < oldPopulation.length; i++) {
            Individual parents[] = selection(oldPopulation);
            Individual child1 = parents[0].clone();
            Individual child2 = parents[1].clone();
            crossover(child1, child2);
            mutation(child1);
            newPopulation[i] = child1;;
        }
        return newPopulation;
    }
}