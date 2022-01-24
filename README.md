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
- If you want autocomplete in vim, try https://github.com/neoclide/coc.nvim
- Terminal Replacement for macOS: https://iterm2.com
- Make your zsh (macOS) look pretty: https://github.com/romkatv/powerlevel10k
- [Extra practice](https://www.codewars.com/r/sMeGPg) 💫 

# Shortcut for easy ssh

1. Download [wrapper](wrapper) into the directory you want.

2. ```bash
   chmod +x wrapper
   ```

3. Usage

   ```bash
   ./wrapper <password> ssh <server>
   ```
   
4. Create alias in `~/.bashrc` or `~/.zshrc` file

   ```bash
   alias cs2030ssh="./wrapper myPassword ssh e1234567@stu.comp.nus.edu.sg"
   ```
   
5. Restart terminal or `source` your configuration file.

Note: This cannot be deployed within `stu.comp.nus.edu.sg`. If you need this to work with your plab account, you would need to use the [SoC VPN](https://webvpn.comp.nus.edu.sg/).