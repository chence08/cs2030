# Being an efficient coder

## vim

> How to do Lab 1 like a pro? (This is the way you're expected to work through your labs and eventually your practical exams.)

1. We read through the *entire* problem statement and notice we need 2 java and 1 jsh file.

2. We can quickly set up our tabbed vim environment.

	```bash
	vim -p Point.java Circle.java maxDiscCoverage.jsh
	```

3. Use `gt` to move to the right tab, `gT` to move left.

4. Saving files

   | Action                                            | Keystroke            |
   | ------------------------------------------------- | -------------------- |
   | Save current file                                 | `:w`                 |
   | Save current file and quit                        | `:wq` OR `:x` OR `ZZ` |
   | Quit current file without saving                  | `:q!`                |
   | Save all files (that are opened in multiple tabs) | `:wa`                |
   | Save all files and quit                           | `:wqa`               |
   | Quit all files without saving any                 | `:qa!`               |

   *Note: If a new file is empty, it will not be created the first time you try to save it. For that you will need

   ```bash
   touch emptyfile
   ```

5. I want to create another file, how to do it without leaving vim?

   ```
   :tabnew AnotherClass.java
   ```
   
6. I want to open all my existing java files into tabs immediately!

   ```bash
   vim -p *.java
   ```

## testing with jshell

```
jshell> /open Point.java 

jshell> /open Circle.java 

jshell> /open maxDiscCoverage.jsh 

jshell> List<Point> points = List.of(new Point(0, 0), new Point(1, 0))
points ==> [point (0.000, 0.000), point (1.000, 0.000)]

jshell> findMaxDiscCoverage(points)
$.. ==> 2

jshell> points = List.of(new Point(0, -1), new Point(1, 0), new Point(0, 1), new Point(-1, 0))
points ==> [point (0.000, -1.000), point (1.000, 0.000), poi ... 0), point (-1.000, 0.000)]

jshell> findMaxDiscCoverage(points)
$.. ==> 4

jshell> /exit
```

You probably saw this in the Level 5 jshell testcase which was not readily provided to you as a jsh file. You can make your own level5.jsh by removing all the `jshell> ` in vim. Then, you can automate testing using:

```bash
jshell < level5.jsh
```

Notice the tests always begin with the `/open` command, if you don't load your classes **in order (superclass before subclass)**, things may go wrong that was not a result of an error in your code but rather JShell misunderstanding your input.

You can do testing without the hassle of typing out any jshell commands like this:

Your level5.jsh will now look like this without the extra commands:

```jsh
List<Point> points = List.of(new Point(0, 0), new Point(1, 0))
findMaxDiscCoverage(points)
points = List.of(new Point(0, -1), new Point(1, 0), new Point(0, 1), new Point(-1, 0))
findMaxDiscCoverage(points)
```

```bash
jshell *.java maxDiscCoverage.jsh < level5.jsh
```

------

- [Link to the official course repository](https://github.com/nus-cs2030/2122-s2), where you earn participation through contributions to Issues and [**Wiki**](https://github.com/nus-cs2030/2122-s1/wiki), the instructions are pretty comprehensive on how to setup your local environment!

- Always take note of LumiNUS Announcements for updates!

------

# Setting up Java locally

- Linux Environment for **Windows** (WSL): https://docs.microsoft.com/en-us/windows/wsl/install

- [Official Java 11 Installation Guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)

- (macOS) Install Java 11 using [Homebrew](https://brew.sh/)

  - ```bash
    $ brew update
    $ brew tap homebrew/cask-versions
    $ brew cask install java11
    ```

  - ```bash
    $ java --version
    openjdk 11.0.9.1 2020-11-04
    OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.9.1+1)
    OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.9.1+1, mixed mode)
    ```

# Useful resources

- (Best) Java IDE: https://www.jetbrains.com/idea/

> Only use IDE for Projects, you need ample practice on vim for PAs.

- Java11: https://docs.oracle.com/en/java/javase/11/docs/api/
- [vim guide](vimkeys.pdf)
- If you want autocomplete in [neovim (a more robust version of vim)](https://neovim.io/), try https://github.com/neoclide/coc.nvim
- Terminal Replacement for macOS: https://iterm2.com
- Make your zsh (macOS) look pretty: https://dev.to/abdfnx/oh-my-zsh-powerlevel10k-cool-terminal-1no0
- [Extra practice](https://www.codewars.com/r/sMeGPg) 💫 

# Shortcut for easy ssh and scp*

Prerequisite: [expect](https://core.tcl-lang.org/expect/index) (this should be preinstalled in most linux distributions)

> scp is used for file transfers and should be unnecessary for this course.

1. Download [wrapper](wrapper) into the directory you want.

2. ```bash
   chmod +x wrapper
   ```

3. Usage

   ```bash
   <path to wrapper>/wrapper <stu password> <plab password> <stu server> <plab server>
   ```
   
4. Create alias in `~/.bashrc` or `~/.zshrc` file

   ```bash
   alias plab="./wrapper pass1 pass2 e1234567@stu.comp.nus.edu.sg plab1234@123.456.78.900"
   ```
   
   > Use absolute path to wrapper instead of `./`
   
5. Restart terminal or `source` your configuration file.

**Login to plab**

```bash
plab
```

**Download `Point.java` from plab server into your current folder**

```bash
plab -d ~/Point.java .
```

**Upload your local `Point.java` into plab** (why are you using this function?)

```bash
plab -u Point.java ~
```