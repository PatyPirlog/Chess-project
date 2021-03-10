# Chess-project
Chess game implemented in Java using Xboard 4.9; artificial intelligence capable of playing chess  
  
     
# Compilation Instructions

make build: compiles the code using javac *.java  
make run: opens the Xboard with the started engine (prior make build command required)  
make clean: removes all the *.class files  

# Project Description

From main.java the engine starts (Engine.java) in normalMode. An infinite loop it's used to make the engine wait for commands from the user to play. When it receives the "force" command it enters in a separate method in which it can receive moves and from which exits with the "white", "block" + "go" or "new" command(it is in normalMode once again). The Engine.java makes the communication between xboard and the engine possible and also executes the moves received from the xboard and sends its moves to the xboard.
The chess table is implemented in Board.java as a matrix of both positive and negative numbers, where the positive ones are the white pieces, the negative ones are the black pieces and the 0's are the blank spaces on the chess table. The variable "color" represents the color the engine plays(positive value: white, negative value: black). 
The abstract class Piece is used in modeling the chess pieces, using the method availableMoves. The Pawn class, and also the other classes which extend the Piece, implement the availableMoves method which returns an array that contains the available moves of a specific piece regarding its color and its placement on the chess table. The Move class is used for implementing the movement on the chess table, storing the coordinates of the new position after moving a piece. 

# Authors and their responsabilities
  Anghel Cristiana  
  Barbu Andreea  
  Pirlog Patricia  
  Engine.java - we all contributed to the implementation of it(including debugging).  
  Piece.java, Move.java, Pawn.java and the rest of the pieces classes - Barbu Andreea and Pirlog Patricia (including comments and coding style)  
  Board.java, Main.java, Makefile - Anghel Cristiana   

# Testing the application
xboard -debug -fcp "java Main" -scp "fairymax" -secondInitString "new\nrandom\nsd 2\n" -tc 5 -inc 2 -autoCallFlag true -mg 10 -sgf partide.txt -reuseFirst false
  requires the Xboard 4.9 platform installed!
