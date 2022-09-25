
# <p align="center">GUI Calculator</p>
   
<p align="center">
  <img src="https://user-images.githubusercontent.com/80555925/192124043-2cccf40d-8896-4bfa-a9b6-d25d43571787.PNG">
</p>

-------------------------------------------------------------------------------------------------------------------------------
## Description
This is a calculator that can do addition, subtraction, multiplication, division, and modulo on unsigned integers. It was created using Java and its GUI so that you can interact with it.

Negative insertions and negative results aren't allowed.

The integers can be up to 40 digits long.

There are exceptions built into the program (no dividing by zero, no negative results, overflow, etc.), but the program might also have bugs that I wasn't able to find during testing.

--------------------------------------------------------------------------------------------------------------------------------
## Running the Program

Running the program is simple:
1. Make sure to have Java SE 11, Visual Studio Code, and Java Extension Pack installed.
   - If you don't have them installed, links can be found here:
      - Java SE 11: https://www.oracle.com/java/technologies/downloads/#java11 
      - VSCode: https://code.visualstudio.com/download
      - Java Extention Pack: Go to VSCode Extensions (Ctrl + Shift + X), search up "Extension Pack for Java", and hit install on the one made by Microsoft
2. Go to Main.java and hit run.
3. Press whatever buttons you'd like. 
    - Numbers will insert numbers onto the screen
    - Operators will decide on the operation to be performed on the numbers
    - Clear will clear the screen
    - The equals button (=) will do the current operation with the numbers on the screen (as long as there is an operation specified)
    
4. To end the program, click on the 'X' at the top right corner.
--------------------------------------------------------------------------------------------------------------------------------
### *Sidenote
The calculator does **NOT** follow PEMDAS. It'll always do the operation you specified first.

    Ex: 1 + 2 * 3
        If you followed PEMDAS, you'd first do 2 * 3 and then add 1 to that
        
        But this calculator works from left to right:
        1 + 2 then multiply by 3
        
