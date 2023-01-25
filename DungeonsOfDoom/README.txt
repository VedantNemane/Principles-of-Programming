#Name: Dungeon_of_Doom

#Description:
	Game where player has to collect enough gold to successfully leave dungeon.
	You can use the commands:
		- hello
		- gold
		- move (n/s/e/w)
		- pickup
		- look
		- quit
#Running:
	Start command prompt and go to file location and inside "src" folder; then type "javac GameLogic.java", then press enter to compile.
	Proceed to compile both of the other classes this way.
	Type "java GameLogic" to run program.

#Implementation:
	Implementation uses OOP style programming to establish player, map and logic for the game.
	Each object has attributes: these are tested and changed according to needs.
	Player class contains attributes such as location, gold count and implements methods
	to read inputs and call other functions accordingly. Map class contains the value
	of the tile player stands on and of course the map name, gold required, and map two dimensional
	array; also implements methods to read map files. GameLogic contains all logic and code
	for objects to interact and as such, contains an infinite while loop to keep reading user input
	and process it.

#Author: Vedant Nemane vn295@bath.ac.uk

#Javadoc:
	Contained inside doc file. Find and run "index.html".

P.S- Beware of sarcastic remarks within program.