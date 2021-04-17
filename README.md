# Cardgame 

[![GitHub release](https://img.shields.io/github/v/release/Josephbisso/Cardgame.svg)](https://github.com/JosephBisso/Cardgame/releases)
![tested-with-JUnit](https://img.shields.io/badge/Tested%20with-JUnit-6e5494.svg)

Fun app to play your favorite card game against computer opponents **(Beta)**

## Description

- Cardgame is a implementation of a physical Card game. For now there is only one pre-programmed Game but with this programm you can play a large number of card games if you know the rule and want to create a new Game with existing rules. But more (popular) Games are definitling in the way. Stay tunned!

- Your opponents will be AIs. The last Player with remaining Card loose the Game. For now neither the number of Ais nor their Level can be changed, but I played a couple hundred games and sometimes I win and sometimes I loose. Either way I had a lot of fun

- There are still some bugs regarding certain rules, but I'm still working on it.

<img src="https://github.com/JosephBisso/Cardgame/blob/main/preview/StartScreen_newStyle.png" width="700">

## The Game

### Run the Game

To run the game, (preferently) ***open the project with Intellij IDEA*** and then run the main Method of ***Cardgame.Main***. That's all!.

### Create new Game

To create a new Game you just have to either: 
1. create a new game using the Game GUI by clicking on the button **create Game** and add the cards of your game with their rules. The so created Game wil be saved in `app/src/test/resources/`

<details><summary> Preview Create Game </summary><p>
  
<img src="https://github.com/JosephBisso/Cardgame/blob/main/preview/CreateNewGameScreen.png" width="700">

</p></details>

2. cd `app/src/main/resources/Games` and paste here a normal ***text*** or ***csv*** file, then replace their extension with the extension `.spiel`. The Format for a `.spiel` file is as follow :

```
GAME;J Comands
CARD;4;Ace
Rule for Card Ace;skip
CARD;4;2
Rule for Card 2;transparent
CARD;4;3
CARD;4;4
CARD;4;5
CARD;4;6
CARD;4;7
Rule for Card 7;add2
CARD;4;8
CARD;4;9
CARD;4;10
Rule for Card 10;reverse;
CARD;4;Jack
Rule for Card Jack;command;stopAdd
CARD;4;Queen
CARD;4;King
CARD;2;Joker
Rule for Card Joker;add4
```

   - in this Example, there are 54 Cards. if `4` stay before a Card name, e.g. `7`, thre will be **4** Card `7`: 2 in **color red** (***hearts*** and ***daimonds***) und 2 in **color black** (***spades***, ***clubs***). If `2` stay before a Card Name, the Card will be a **Joker**. Each Color wil have one Joker. 

   - For now it is not possible to add less than 2 or more than 4 Cards.

### Load a Game;

If a game, that you created isn't show in the comboBow, you can load it manually by clicking the button **load Game**. A window will pop up and you will have to choose a `.spiel` file.

### Pre-programmed Game ("J Commands")

You can see all the Cards and their Rules in the example above. Playing this Game can help you understand the mechanics of the game.

### Turn

#### Interactions between AI and you

- During the game you will habe to interact with the AIs. Some examples are when you or one of the AIs "commands" a Card or when you got stopped ("skipped") by of the AIs. There are still minor bugs but all works pretty fine for now. 

- You are not able to see the AIs cards!

<details><summary> Preview Interaction AI-Player</summary><p>
  
<img src="https://github.com/JosephBisso/Cardgame/blob/main/preview/GamePlayScreen_interaction%20with_AI.png" width="700">

<img src="https://github.com/JosephBisso/Cardgame/blob/main/preview/GamePlayScreen_playerLosseToAIs.png" width="700">

</p></details>

#### Interactions between AIs

If you finished your cards and more than one AI still have at least one Card, they will have to play against each other until there is just one AI with Card remaining. Normally this last as long as if there were normal people playing. That means you will be able to see each move of each of the remaining AIs untill the game is finished. 

<details><summary> Preview Interaction AI-AI</summary><p>
  
<img src="https://github.com/JosephBisso/Cardgame/blob/main/preview/GamePlayScreen_AIvsAivsAI.png" width="700">

<img src="https://github.com/JosephBisso/Cardgame/blob/main/preview/GamePlayScreen_AI_1_loose_aigainstAIs.png" width="700">

</p></details>

## Used Software / Librairies

- Intellij IDEA

- Gradle

- JUnit

- Java Swing

# HAVE FUN !!!


