
# dog_ninja

### Overview for Charactor Template
`Charactor_Template` is an abstract class that provides a framework for characters with basic properties like position and speed.


### Fields
- `protected int x`: x-coordinate of the character.
- `protected int y`: y-coordinate of the character.
- `protected int speed`: Speed of the character.

### Abstract Methods
- `void setX(int x)`: Sets x-coordinate.
- `void setY(int y)`: Sets y-coordinate.
- `void setSpeed(int speed)`: Sets speed.
- `int getX()`: Returns x-coordinate.
- `int getY()`: Returns y-coordinate.
- `int getSpeed()`: Returns speed.

### Overview for Method Template
The `Method_Template` interface defines a contract for character objects that require movement functionality. Classes implementing this interface must provide an implementation for the `move` method.

### Interface: `Method_Template`

#### Methods
- **`void move(JPanel page)`**: An abstract method that specifies how the character or environment object should move within a given `JPanel`.

### Overview for Dog
The `Dog` class extends `Charactor_Template` and implements `Method_Template`. It defines specific properties and behaviors for a dog character, including health, movement, and image rendering.

### Class: `Dog`

#### Fields
- **`private int health`**: Health of the dog, initialized to 180.
- **`private int speed`**: Jump height/speed, initialized to 120.

#### Constructor
- **`Dog(int x, int y)`**: Initializes the dog’s position using `x` and `y` coordinates.

#### Methods
- **`void setX(int x)`**: Sets the x-coordinate.
- **`void setY(int y)`**: Sets the y-coordinate.
- **`int getX()`**: Returns the x-coordinate.
- **`int getY()`**: Returns the y-coordinate.
- **`void setHealth(int health)`**: Sets the health value.
- **`int getHealth()`**: Returns the health value.
- **`int getSpeed()`**: Returns the speed (jump height).
- **`void setSpeed(int speed)`**: Sets the speed (jump height).

#### Movement
- **`void move(JPanel page)`**: Calls `jump()` to make the dog jump.
- **`void jump(JPanel page)`**: Simulates a jump by moving the dog upward and then falling back after a delay, using a `Timer`.

#### Image Rendering
- **`BufferedImage getImage()`**: Loads and returns the image of the dog from `img/dog.png`.


### Overview for Environment
The `Environment` class extends `Charactor_Template` and implements `Method_Template`. It defines environmental objects like clouds or buildings, each with a type, position, and movement behavior.

### Class: `Environment`

#### Static Constants
- **`CLOUD`**: Integer constant for cloud type (0).
- **`BUILDING`**: Integer constant for building type (1).

#### Fields
- **`private int startX`**: Initial x-coordinate of the environment object.
- **`private int speed`**: Speed at which the object moves.
- **`private int eType`**: Type of the environment object (cloud or building).
- **`private Timer timeMove`**: Timer to manage continuous movement.

#### Constructor
- **`Environment(int x, int y, JPanel page, int eType, int speed)`**: Initializes the environment object’s position, type, and speed, and starts its movement on the given `JPanel`.

#### Methods
- **`void setX(int x)`**: Sets the x-coordinate.
- **`void setY(int y)`**: Sets the y-coordinate.
- **`int getX()`**: Returns the x-coordinate.
- **`int getY()`**: Returns the y-coordinate.
- **`int getStartX()`**: Returns the initial x-coordinate.
- **`void setStartX(int startX)`**: Sets the initial x-coordinate.
- **`int getSpeed()`**: Returns the speed of the object.
- **`void setSpeed(int speed)`**: Sets the speed of the object.
- **`int geteType()`**: Returns the type of the object.
- **`void seteType(int eType)`**: Sets the type of the object.
- **`Timer getTimeMove()`**: Returns the movement timer.
- **`void setTimeMove(Timer timeMove)`**: Sets the movement timer.

#### Movement
- **`void move(JPanel page)`**: Continuously moves the object left across the panel. If it moves out of view, it resets to the starting x-coordinate. Uses a `Timer` to update movement every 10 milliseconds.
- **`void stop()`**: Stops the movement timer.

#### Image Rendering
- **`String getEvType(int eType)`**: Returns the image file name based on the environment type.
- **`BufferedImage getImage()`**: Loads and returns the image for the environment object from `img/cloud.png` or `img/building.png`.


### Overview for wave
The `Wave` class extends `Charactor_Template` and implements `Method_Template`. It represents a wave-like environmental object that moves horizontally across a `JPanel` with a specified speed.

### Class: `Wave`

#### Fields
- **`private int speed`**: Speed at which the wave moves.
- **`private Timer timeMove`**: Timer to manage continuous movement of the wave.

#### Constructor
- **`Wave(int x, int y, int speed, JPanel page)`**: Initializes the wave’s position and speed, and starts its movement on the given `JPanel`.

#### Methods
- **`void setX(int x)`**: Sets the x-coordinate.
- **`void setY(int y)`**: Sets the y-coordinate.
- **`int getX()`**: Returns the x-coordinate.
- **`int getY()`**: Returns the y-coordinate.
- **`int getSpeed()`**: Returns the speed of the wave.
- **`void setSpeed(int speed)`**: Sets the speed of the wave.
- **`Timer getTimeMove()`**: Returns the movement timer.
- **`void setTimeMove(Timer timeMove)`**: Sets the movement timer.

#### Movement
- **`void move(JPanel page)`**: Moves the wave left across the panel. If it moves out of view (x-coordinate ≤ 0), it resets to a new random position off-screen. Uses a `Timer` to update movement at the specified speed.

#### Image Rendering
- **`BufferedImage getImage()`**: Loads and returns the image of the wave from `img/tree.png`.


### Overview for display
The `Display` class extends `JFrame` and serves as the main window for the "Dog Ninja" game. It manages the initial game setup, transitions between game states, and user interactions through button clicks.

### Class: `Display`

#### Fields
- **`private static final long serialVersionUID`**: Serial version ID for the class.
- **`private Dimension size`**: The size of the game window, set to 1000x600 pixels.
- **`private JButton start`**: A button to start the game, labeled "Game Start".

#### Constructor
- **`Display()`**: Calls the `setting()` method to configure the game window.

#### Methods
- **`private void setting()`**: Configures the main game window, including the title, size, default close operation, location, and adding the start button.
- **`private void removeContent()`**: Removes all content from the frame and repaints it, clearing the window.
- **`public void endGame(long point)`**: Clears the window and displays a `Menu` with the final score (`point`) and a reference to the `Display` instance.

#### Action Handling
- **`public void actionPerformed(ActionEvent e)`**: Handles button click events:
  - **Restart Action**: Clears the content and starts a new `Game` instance if the action command is "restart".
  - **Start Game**: Removes the start button and adds a new `Game` instance when the start button is clicked.

#### Notes
- **`Game` and `Menu`**: The `Game` and `Menu` classes are referenced but not defined in this code snippet. They handle the main gameplay and the game menu, respectively.
- **Window Settings**: The game window opens at a specified location (280, 100) on the screen.


### Overview for Game
The `Game` class extends `JPanel` and implements `KeyListener`. It serves as the main gameplay panel for the "Dog Ninja" game, managing game rendering, events, and interactions. The game features a dog character that jumps to avoid waves, with environmental elements such as clouds and buildings in the background. Player points are accumulated as the game progresses, and the dog’s health decreases upon collisions.

### Class: `Game`

#### Constants
- **`SPEED`**: The speed at which the wave elements move (50).
- **`DOG_SIZE`**: Size of the dog character (60 pixels).
- **`WAVE_HEIGHT`**: Height of the wave elements (50 pixels).
- **`BASE`**: The y-coordinate base level for elements (400 pixels).
- **`X_START`**: Initial x-coordinate for placing wave and environmental elements (1000 pixels).

#### Fields
- **`private long points`**: Player's score, incremented during gameplay.
- **`private long lastPressTime`**: Tracks the time of the last key press for jump rate control.
- **`private Dog dog`**: The dog character object.
- **`static Display display`**: The main display window of the game.
- **`private Wave[] waveSet`**: An array of wave elements for the game.
- **`private Environment[] envSet`**: An array of environmental elements (clouds).
- **`private Environment building`**: A building element in the game environment.

#### Constructor
- **`Game()`**: Initializes the game panel and sets up key listening and absolute positioning.

#### Methods

##### Panel Setup
- **`private void setupPanel()`**: Configures the game panel's size, layout, and focusability for key events.

##### Drawing Methods
- **`public void paint(Graphics g)`**: Main rendering method that draws all game components and increments the player's score.
- **`private void drawBackground(Graphics2D g2)`**: Draws the game background, building, ground, and clouds.
- **`private void drawScore(Graphics2D g2)`**: Displays the player's current score.
- **`private void drawDog(Graphics2D g2)`**: Draws the dog character and its health bar.
- **`private void drawDogHealth(Graphics2D g2)`**: Draws the health bar for the dog character.
- **`private void drawWaves(Graphics2D g2)`**: Draws the wave elements and handles collision detection.

##### Wave and Environment Setup
- **`private Wave[] makeWave(int size)`**: Creates an array of wave objects and positions them.
- **`private Environment[] makeEnv(int size, int eType)`**: Creates an array of environmental objects (clouds) and positions them.

##### Collision Handling
- **`private void handleWaveCollision(Graphics2D g2, Wave wave)`**: Checks for collisions between the dog and waves. If a collision occurs, reduces the dog’s health and ends the game if health is depleted.
- **`private void resetGame()`**: Resets the dog’s health and the player's score for a new game session.

##### Key Listener Implementation
- **`public void keyPressed(KeyEvent e)`**: Handles jump actions when the space bar (32) or up arrow (38) is pressed, with a cooldown to prevent continuous jumping.
- **`public void keyTyped(KeyEvent e)`**: No action needed.
- **`public void keyReleased(KeyEvent e)`**: No action needed.

#### Main Method
- **`public static void main(String[] arg)`**: Initializes the game by creating a `Display` object.

#### Notes
- **Game Loop**: The game uses `paint` to handle drawing, with periodic updates for animation.
- **Collision Detection**: Uses an `Event` utility class to check for collisions between the dog and wave elements.
- **Image Loading**: Loads images from the `img` directory for various game components, such as the sky, building, and dog.

### Overview for Menu
The `Menu` class extends `JPanel` and represents the game-over screen in the "Dog Ninja" game. It displays the player's score and provides a button to restart the game.

### Class: `Menu`

#### Fields
- **`private static final long serialVersionUID`**: Serial version ID for the class.
- **`public long point`**: The score achieved by the player, displayed on the menu.

#### Constructor
- **`Menu(long point, ActionListener main)`**: Initializes the game-over menu panel with the player’s score and a restart button.
  - **Parameters**:
    - `point`: The total score the player achieved.
    - `main`: The `ActionListener` that handles the restart button click.

#### Components
- **`EleLabel status`**: A label displaying "You Died!" to indicate the end of the game.
- **`EleLabel showPoint`**: A label displaying the total score achieved by the player.
- **`EleButton restart`**: A button labeled "restart" that allows the player to restart the game. It is connected to the provided `ActionListener`.

#### Layout and Styling
- The panel has a background color of `Color(241, 98, 69)`.
- The layout is set to `null` for absolute positioning of elements.
- Labels and buttons are styled with specified colors, fonts, and positions.

#### Notes
- **Custom Elements**: The class uses `EleLabel` and `EleButton`, which are custom components from the `Element` package. These elements handle label and button creation with specific styles and properties.
- **Error Handling**: The constructor includes a try-catch block to handle potential exceptions, printing the stack trace if an error occurs.
