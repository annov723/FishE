<p align="center">
  <img src="https://github.com/annov723/FishE/blob/master/quiz-game/logo.png" width=300>
</p>

# FishE - Quizzes & Flashcards
FishE is an easy-to-use quizzes and flashcards app built with Java Swing:
- utilizes ArrayList to extract content from a .txt file, which includes quiz titles and questions
- users can choose the duration and number of questions for each game
- the flashcards mode enables users to review all the questions within a selected topic.

The how-to-go-through-the-questions idea is based on the Code Bro YouTube tutorial, which inspired me to make the whole app ;)

## Main objectives: <img align="right" img src="https://github.com/annov723/FishE/blob/master/quiz-game/mini.png" width=220>
- designing a user-friendly interface by adding selfmade graphicsa and editing all interactive elements
- implementing a scrollbar
- utilizing a .txt file (dat.txt) to dynamically modify the number of quizzes, their titles and associated questions -> this way we can easily upload more and more quizzes to the app :)

## Description:
#### Menu Class
-

#### Data Class
-

#### FishePick Class
-

#### QuizPick Class
-

#### Fishe Class
-

#### Quiz Class
-


## Solutions I am proud of:
- searching for the quiz chosen by the user in th dat.txt method, every quiz is saved in the same amount of lines so the program can iterate through the quizzes
- in flashcards mode, questions and correct answers are changing in a circle, the user can navigate right and left indefinitely
- questions for each quiz are randomly selected and presented in a random order
- if the user selects the same topic for the second time in a row, the program does not reload data from the file. Instead, the data from the previous game is used as it is still stored in the Data object in ArrayLists

## Things to work on:
- when clicking the FishE logo an Ester egg feature is activated -> a screen with jumping fishes appears and users can tap the fishes to make them "pop". To return to the menu screen, users must "pop" all the fishes
