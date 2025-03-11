#  Arithmetic Card Game - A Fun Math Challenge 

##  Overview
This JavaFX-based game brings a **fun math challenge** where players must create an arithmetic equation using **four randomly drawn numbers** to **make 24**!   
If you're stuck, **AI-powered hints** will help you figure it out! 

##  Features
- **User-friendly JavaFX interface** with soft pastel colors 
- **Four random card numbers** in every round 
- **AI-generated hints** via Gemini API for assistance 
- **Smooth fade animations** when shuffling 
- **Instant equation validation** to check if the result is 24 
- **Non-intrusive ads** displayed at the top and bottom 

---

###  Prerequisites
Before running the game, ensure you have:
- **Java 17 or later** installed 
- **Maven** for dependency management 
- **IntelliJ IDEA** (recommended for best experience) 

## Project Structure
CardGameIntelliJ [Card game24]/
│── src/main/java/application/
│   ├── Main.java           
│   ├── CardController.java  
│   ├── Evaluate.java  
│   ├── OpenAPI.java      
│── src/main/resources/
│   ├── mystyles.css      
│── pom.xml                  
│── README.md   

## How to Play
1. Start the game and receive four random numbers 
2. Use math operations ( +, -, ×, ÷ ) to create a valid equation
3. Your goal: The equation must equal 24!
4. Click "Evaluate" to check your solution
5. Need help? Click "Hint" to get an AI-generated tip 
6. Want a fresh challenge? Click "Shuffle Cards" for new numbers
7. ## Watch Me Play
https://youtu.be/OZOZoZWQ_jc

## Explanation of Files
 ## Main.java
 Launches the game window with a stylish, soft pastel UI
 Applies custom CSS for an aesthetic design
 Calls CardGenerator.generateRandomCards() to begin
 ## CardController.java
 Builds the game layout, including cards and buttons
 Handles smooth shuffle animations for new cards
 Manages AI hints for players who need help
 ## Evaluate.java
 Interprets the user’s equation
 Checks if the result matches 24
 Follows standard math operator precedence
 ## OpenAPI.java
 Sends requests to Gemini AI for hints
 Extracts helpful clues from API responses
 Watch the Game in Action
 Want to see how it works? Check out the full gameplay video here:
 [Your YouTube Link Here]

## Troubleshooting
 AI Hint Not Working?
 If the Hint button gives an error:
 Ensure your API key in GeminiAPI.java is correct
 Double-check your internet connection

## Cards Not Displaying?
 Verify that the UI elements are loading properly
 Restart the game if needed

## Future Enhancements
 Multiplayer Mode – Play with friends in real-time!
 High Score Leaderboard – Track the best times!
 Smarter AI Hints – More detailed solutions!


## License
 This project is licensed under the MIT License.

