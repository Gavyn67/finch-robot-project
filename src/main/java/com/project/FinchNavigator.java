package com.example;

import com.birdbrain.Finch;

public class FinchNavigator {
    
    // Hard-coded values to traverse the maze
    int SMALL_TURNS_INDEX = 11;
    String SEQUENCE = "LLRRLLRRRLLRRRRRRRR";

    // Instance variables
    int checkDistance = 10; // Distance to check for obstacles
    int currentTurn = 0; // Index to track the current turn in the sequence
    boolean smallTurns = false; // When to start using 45 degree turns in the narrow parts of the maze
    Finch finch; // Finch

    // Default constructor to initialize the Finch object
    public FinchNavigator() {
        finch = new Finch();
    }

    // Turns based on the current turn direction in the sequence
    public void turn() {
        String letter = SEQUENCE.substring(currentTurn, currentTurn + 1);
        /* If not smallTurns, then it just turns 90 degrees. Else, it will do two 45 degree turns
        with a small forward movement in between to navigatethe narrow parts of the maze. */
        if (!smallTurns) {
            finch.setTurn(letter, 90, 100);
        } else {
            finch.setTurn(letter, 45, 100);
            finch.setMove("F", 7, 100);
            finch.setTurn(letter, 45, 100);
        }
        currentTurn++;
    }

    /* Moves forward until it detects a wall within the checkDistance. 
    Breaks the loop when the wall is detected, allowing the robot to turn and continue. */
    public void moveTillObstacle() {
        while (true) {
            finch.setMove("F", 20, 100);
            int dist = finch.getDistance();
            if (dist <= checkDistance) {
                finch.setMove("B", checkDistance - dist, 100);
                break;
            }
        }
    }

    // Main loop to navigate the maze
    public void start() {
        // Will repeat until end of sequence
        while (true) {
            // Checks if the current turn index has reached the end of the sequence, and breaks the loop if it did
            if (currentTurn >= SEQUENCE.length()) {
                break;
            }

            // Increases check distance and turns on small turns during the narrow parts of the maze
            if (currentTurn == SMALL_TURNS_INDEX) {
                smallTurns = true;
                checkDistance = 15;
            }

            // Moves till wall is reached and turns
            moveTillObstacle();
            turn();
        }
    }
}