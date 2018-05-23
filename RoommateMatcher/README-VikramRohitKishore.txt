Roommate Finder README
Vikram Shirsat, Rohit Ghosh, and Kishore Srinivas

Introduction: 
Instead of a usual roommate finding app or even just a questionnaire, this application has been built to minimize
the possible interference of wanting to look like a good person to allow someone to find a roommate who truly is like 
them. In this application, a user is thrown into a first person game in which their inherent decisions that are made 
expose some of their inner qualities as a person. This app will force users to make decisions that relate to possible 
roommate qualities such as if a person is a late night or early morning person or even if they are super clean.

Instructions:
Welcome to the Roommate Matcher!
In this game you can speed through the process of finding a roommate by playing a game! Instead of a boring 
questionnaire where you craft responses to look like an angel, let out your inner gamer and show your true qualities off!
With every step of the game and fight with another character, you will be matched using our current databases to another
person in the class who fits your qualities!
First, type in your name and the name of your opponent. Then click the play button. You can select a map to fight in as 
well as character for both you and your opponent. Once you enter the game, try your best to defeat your opponent. The
 game ends when either you or your opponent lose all their health. 

Controls: 
Player 1 controls: Arrow Keys to move, Period to shoot, Comma to toggle gun between rifle and shotgun, M to toggle blade 
between knife and sword.
Player 2 controls: WASD to move, F to shoot, Q to toggle gun between rifle and shotgun, E to toggle blade between knife 
and sword.

Must-have:
Game will have 3 options for arenas to fight in
2 different guns (with attributes)
2 different blades (with attributes)
10 profiles to compare to (Storing data in text files to reload and store data)
Ability to move
Data analytics (very basic just to determine some similar people)
Basic interaction with field elements (jumping on a box)
Basic Person upgrades/attributes (speed, jump height, money)

Want-to-have:
Multiple complex stages
Database storage
Larger datasets 
Semi-realistic physics


Stretch:
Multiple players with comparisons
3D graphics
Advanced interaction (picking up and throwing objects in the arena)


Class List:
Blade: This class models a blade which is extended by different types of blades
Knife: This class models a knife which is a type of blade
Sword: This class models a sword which is a type of blade
HealthBar: This class models the health bar that appears above each player
Hitbox: This creates a box around each character so that when a weapon hits that area they lose health
Player: This class models a playable character
MatchMaker: This class matches players to stored users in the list of users
User: This class models a user and stores their characteristics
Button: This class models a button which is used to switch between stages and select characters and weapons
Couch: This class draws hitboxes around the couch in the living room map
FieldElement: This class represents an object that may be present on a map
Ladder: This class models a ladder present in the Library and LivingRoom stages
LivingRoomLadder: This class models a ladder present in the living room stage
PowerUp: This class models a power up which falls down randomly 
PowerUpBulletSpeed: This class models a power up which makes the player's bullets faster for a short period of time
PowerUpFireRate: This class models a power up which makes the player shoot at a faster rate for a period of time
PowerUpHealth: This class models a power up which gives the player 20 extra health
TextBox: This class models a textbox which the user can input text into. The data can be passed to other classes.
Tree: This class models a tree which is present in the Outdoor Field stage
Bullet: This class models a bullet which does damage to the opposing player when shot
Firearm: This class models a firearm which is extended by the various types of guns available in the game
Rifle: This class models a rifle, which is a type of gun and shoots bullets at a faster rate than a shotgun
Shotgun: This class models a shotgun which shoots bullets at a slower rate than a rifle
DrawingSurface: This class models a surface on which the game is drawn
RoommateMatcher: This class runs all the components of the program
Instructions: This class models the screen which displays the instructions
Library: This class models the library stage
LivingRoom: This class models the Living Room stage
MainMenu: This class models the Main Menu which first appears when the program starts
OutdoorField: This class models the Outdoor Field stage that the players can play in
PlayerMenu: This class models the menu in which the users enter their names
PlayerOneSelect: This class models the stage in which the first user selects his or her character
PlayerTwoSelect: This class models the screen in which the second user choose his or her character
Stage: This class is used to control the stage backdrop being displayed
StageMenu: This class models the menu in which the users select which stage they wish to play in
StageType: This class is used as a basis for all the playable stages


Responsibilities:
Vikram - UI, design the game
Kishore - Data comparison, knife and sword
Rohit - Design the player, gun, bullets, interaction with tree 
Feedback given by - Helen Wang, Anushka Saran, Lily Li
Textbox code - https://amnonp5.wordpress.com/2012/01/28/25-life-saving-tips-for-processing/ (tip #13)
Sprite Animation - https://www.youtube.com/watch?v=xvjrsBC9Vnc

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