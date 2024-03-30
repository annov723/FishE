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
- designing a user-friendly interface by adding selfmade graphics and editing all interactive elements
- implementing a scrollbar
- utilizing a .txt file (dat.txt) to dynamically modify the number of quizzes, their titles and associated questions -> this way we can easily upload more and more quizzes to the app :)

## Description:
#### Menu class
- generates the game menu with 3 interactive buttons: "quiz", "FishE" and "exit"
- menu frame is static so it is generated only once through the all gameplay

#### Data class
- handles all operations related to accessing quiz data from a dat.txt file
- ensures proper file formatting for smooth program eecution
- all gathered data is stored in ArrayLists and passed to the Quiz and FishE objects in a randomized order

#### FishePick class
- includes a scrollbar with all quiz titles provided by the data object
- when the mouse enters the area of any title, it changes the color to encourage the user to click it

#### QuizPick class
- includes a scrollbar with all quiz titles provided by the data object
- enables to pick the amount of time per question (5 sec, 10 sec or 15 sec) and the number of questions (5, 10, 20), the buttons used for this purpose can be unclicked and the decision which option to choose can be changed several times
- when all options are selected, the quiz data is provided by the Data object and then sent to the Quiz object constructor

#### Fishe class
- enables iteration through all the questions and the correct answers for the specific topic
- each time the same topic is displayed, the questions are randomized
- includes a "back" button for returning to the main menu

#### Quiz class
- iterates through all questions provided by the data object
- the user starts with 3 "hearts" and losing all of them  results in the quiz ending immidiately with poor results
- the user can set the number of questions and the timer in the PickQuiz object
- during the game it is possible to go back to the main menu by clicking the "back" button at the bottom of the screen



## Solutions I am proud of:
- searching for the quiz chosen by the user in th dat.txt method, every quiz is saved in the same amount of lines so the program can iterate through the quizzes
- in flashcards mode, questions and correct answers are changing in a circle, the user can navigate right and left indefinitely
- questions for each quiz are randomly selected and presented in a random order
- if the user selects the same topic for the second time in a row, the program does not reload data from the file. Instead, the data from the previous game is used as it is still stored in the Data object in ArrayLists

## Things to work on:
- when clicking the FishE logo an Ester egg feature is activated -> a screen with jumping fishes appears and users can tap the fishes to make them "pop". To return to the menu screen, users must "pop" all the fishes
