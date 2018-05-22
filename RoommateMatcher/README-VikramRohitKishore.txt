Roommate Finder README
Vikram Shirsat, Rohit Ghosh, and Kishore Srinivas

Introduction: 
Instead of a usual roommate finding app or even just a questionnaire, this application has been built to minimize the possible interference of wanting to look like a good person to allow someone to find a roommate who truly is like them. In this application, a user is thrown into a first person game in which their inherent decisions that are made expose some of their inner qualities as a person. This app will force users to make decisions that relate to possible roommate qualities such as if a person is a late night or early morning person or even if they are super clean.

Instruction:
Welcome to the Roommate Matcher!
In this game you can speed through the process of finding n a roommate by playing a game! Instead of a boring questionnaire where you craft responses to look like an angel, let out your inner gamer and show your true qualities off! With every step of the game and fight with another character, you will be matched using our current databases to another character who fits your qualities!
Click the MENU button to leave the instructions screen!
Pick the arena you would like to fight in
Pick the user you would like to fight with
Controls: 
Player 1 controls: Arrowkeys to move, down arrow to shoot and shift to use melee weapon 
Player 2 controls: WAD to move, S to shoot and F to use melee weapon 

Starting Screen
Instructions button in top right corner allows the user to view these instructions
ESC button to leave the game

Instructions:
ESC to leave instructions screen
WASD/arrow keys to move around
Some key (R?) to record the game data and use it for the roommate finding thing

Preparation of data:
Send out a survey to the class to collect data about what they would choose in specific situations that might occur in the game
This data will then be used to compare players in the game to real people in hopes of making roommate matching more personalized.


Game:

Moving around in a world with specific prescribed moves

Must-have:
Game will have 3 options for arenas to fight in
2 different guns (with attributes)
2 different swords (with attributes)
10 profiles to compare to (Storing data in text files to reload and store data)
Ability to move
Data analytics (very basic just to determine some similar people)
Basic interaction with field elements (jumping on a box)
Basic Person upgrades/attributes (speed, jump height, money)

Want-to-have:
Multiple complex stages
Database storage
larger datasets 
semi-realistic physics


Stretch:
Multiple players with comparisons
3D graphics
Advanced interaction (picking up and throwing objects in the arena)


Class List:
Player: the person on the screen
Field: the field
Multiple field classes for each arena of the game
Main class:
	contains the compare people method
	reads and writes to files
	creates Users
DrawingSurface: the surface on which the game is played
Blade: models a blade object that has x, y, length, and swing() method
Sword: extends Blade, has different length, models a larger weapon
Knife: extends Blade, has different length, models a smaller weapon
Firearm: models a gun object with x, y, width, and height
Rifle: extends Firearm, models a larger weapon
Shotgun: extends Firearm, models a smaller weapon
Hitbox: models a box that surrounds every character and weapon and checks for collisions with other Hitboxes

Responsibilities:
Vikram - UI, design the game
Kishore - Data comparison, knife and sword
Rohit - Design the player, gun, bullets, interaction with tree 
Feedback given by - Helen Wang, Anushka Saran, Lily Li
textbox code - https://amnonp5.wordpress.com/2012/01/28/25-life-saving-tips-for-processing/ (tip #13)

Feedback:
1.  Gun1 and Gun2 extends Gun too (same thing for Sword), where Gun1 and Gun2 is not connected to Person (just like Shelby's example)
3.  Where is your code for the roommate finding algorithm? In which class?
	 - How are y'all gonna do that?
4.  README is very vague (features list)
5.  When someone re-opens your application, does their previous information get stored? Or do the player start over?
6.  How many people (or players) are you playing against? Or are you playing against an AI?
7.  Is there 10 computer generated profiles that you are comparing against? Because it is very vague right now
8.  This game does not make any sense... is there a map, to help the player navigate around?
9.  What is the point of finding a computer generated roommate...?
10. If you play again, does your algorithm guarantee that you will be matched with the same roommate?