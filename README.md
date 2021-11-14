# TIC4305 Project: Caesar Cipher

This is a project submission for TIC4305. 
Nuri Irfani binte Masri, A077809U.
Monterosa username: e0261617

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
```bash
java caesarenc
java caesardec
java cracker
```

caesarenc : Program for encryption.

caesardec : Program for decryption.

cracker   : Program for cracking.


For all three programs, you'll be prompted to choose between terminal input or text file input.
```
Text or process from a text file? Enter 0 if text, or 1 if file.
```

Choosing manual input will prompt you to input the key and plaintext/ciphertext into the terminal separately. Choosing to give the program text file will prompt you to enter a file path (.txt files only).

For caesarenc and caesardec, the text file **MUST** include the **key** on the **first line**. For the cracker, there **MUST** be some **integer** on the **first line**. This is due to the fact that the code was tested with sample data provided by the school, and the data was formatted that way. thus it is assumed that the programs will be tested with text files that are formatted similarly.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
