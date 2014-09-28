Hungry Mouse
===============

Hungry Mouse is a 2D Android Game Application that utilize the advance features of modern smartphones.

Its making use of AndEngine 2D OpenGL Game Engine. It utilizes the following features:

- Multi Touch Screen
- Gyroscope
- Sound Reproduction
- Modern Animations
- Data Storage 

Game Characters, Objects &Purpose
-------------

**Main character & Purpose**
There is a Mouse which try and eat as many pieces of cheese in order to
satisfy its greed.

**Obstacles**
The Mouse face obstacles like bombs that must be avoided, and gaps that
must jump over.

**Enemies**
There are also some other mice that hold detonating bombs.

**Enemies Purpose**
Their purpose is to kill the main character, hungry mouse by killing
themselves in the process.

Screenshots
-------------

*The main menu Screen*

![enter image description here](http://i.imgur.com/EhYfCxy.png?1)

*Game play screen*

![enter image description here](http://i.imgur.com/CNhFXHn.png?1)

Usage
-------------
How to switch between interfaces

    if (inBounds(event, 688, 368, 96, 96)) {
    	game.setScreen(new AboutScreen(game));
    }

Read and Write Data

    in = new BufferedReader(new
    InputStreamReader(files.readFile(".savethedataHungryMouse")));
    
    soundEnabled = Boolean.parseBoolean(in.readLine());
    gyroscopeEnabled = Boolean.parseBoolean(in.readLine());

Sound Reproduction

    if (Settings.soundEnabled)
        Assets.jump.play(1);

Graphics Animation

    anim = new Animation();
	anim.addFrame(character, 1250);
	anim.addFrame(character2, 50);
	anim.addFrame(character3, 50);
	anim.addFrame(character2, 50);

Requirements
-------------
Android SDK 15

Contributing
-------------
Please fork this repository and contribute back using pull requests.
