# TIC4305 Project: Caesar Cipher

This is a project submission for TIC4305. By: A077809U.

[Github.](https://github.com/Nuri-Irfani/TIC4305_Project)

## Compiling
caesarenc, caesardec and cracker are separate programs and thus compiles separately 
```bash
javac caesarenc.java
javac caesardec.java
javac cracker.java
```

## Usage

To run any of the programs
```java
java caesarenc
java caesardec
java cracker
```
For all three programs, you'll be prompted to choose between terminal input or file select
```
For manual text input, enter 0. If you'd like to select a text file, enter 1.
```
Choosing manual input will prompt you to input the key and plaintext/ciphertext into the terminal separately. Choosing to give the program text file will prompt you with a pop-up dialog box.

For caesarenc and caesardec, the text file MUST include the key on the first line. For the cracker, there MUST be some integer on the first line. This is due to the fact that the code was tested with sample data provided by the school, and the data was formatted that way.
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
