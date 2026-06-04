package com.example;

import com.birdbrain.Finch;

public class FinchNavigator {
    
    int SMALL_TURNS_INDEX = 11;

    int checkDistance = 10;
    int currentTurn = 0;
    boolean smallTurns = false;
    String sequence = "LLRRLLRRRLLRRRRRRRR";
    Finch finch;

    public FinchNavigator() {
        finch = new Finch();
    }

    public void turn() {
        String letter = sequence.substring(currentTurn, currentTurn + 1);
        if (!smallTurns) {
            finch.setTurn(letter, 90, 100);
        } else {
            finch.setTurn(letter, 45, 100);
            finch.setMove("F", 7, 100);
            finch.setTurn(letter, 45, 100);
        }
        currentTurn++;
    }

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

    public void start() {
        while (true) {
            if (currentTurn >= sequence.length()) {
                break;
            }

            if (currentTurn == SMALL_TURNS_INDEX) {
                smallTurns = true;
            }

            moveTillObstacle();
            turn();
        }
    }
}