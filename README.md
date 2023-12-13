# A1 - Piraten Karpen

  * Author: Ursula Chui
  * Email: chuiu@mcmaster.ca

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 
  * To run it with tracing:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar (number of games) (player 1 strategy) (player 2 strategy) (trace)`

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * The programmed feature delivers and runs as it is described without error and is incorporated with the other features.
   * The feature adds business value to the program and aligns with the user requirements.
   * There is no runtime or compile error when feature is delivered.

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll a dice | D | 01/01/23 | 01/12/23 |
| x   | F02 | Roll eight dices | D | 01/16/23 | 01/16/23 |
|     | F03 | Select how many games as command-line arg. | D | 01/16/23 | 01/16/23 |
| x   | F04 | End of game with three cranes | D | 16/01/23 | 17/01/23 |
| x   | F05 | Player keeping random dice at their turn | D | 19/01/23 | 21/01/23 |
|     | F06 | Score points: 3-of-a-kind | D | 25/01/23 | 26/01/23 | 
| x   | F07 | Score points: Diamond or Gold | D | 18/01/23 | 19/01/23 |
| x   | F08 | Rotated turns between both players | D | 18/01/23 | 19/01/23 |
| x   | F09 | Random strategy established | D | 25/01/23 | 25/01/23 |
|     | F10 | Score points: 4- to 8-of-a-kind | D | 25/01/23 | 26/01/23 |
|     | F11 | Combo strategy established | D | 24/01/23 | 26/01/23 |
|     | F12 | Sea battle strategy | D | 26/01/23 | 27/01/23 |
|     | F13 | Monkey parrot strategy | D | 26/01/23 | 27/01/23 |