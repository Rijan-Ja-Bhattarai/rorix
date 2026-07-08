# Rorix

Rorix is a Java implementation of the Lox programming language from
[Crafting Interpreters](https://craftinginterpreters.com/), renamed as a
personal learning project.

The goal of this repository is to follow the book while gradually building a
small interpreted programming language from scratch. For now, Rorix is in the
early front-end stage: it can scan source text into tokens and print those
tokens from either a file or an interactive prompt.

## Current Status

Implemented so far:

- Command-line entry point: `com.interpreters.rorix.Rorix`
- File execution mode
- Interactive prompt mode
- Scanner / lexer
- Token and token type definitions
- Basic literals, operators, identifiers, keywords, and comments

Not implemented yet:

- Parser
- AST generation
- Resolver
- Runtime interpreter
- Functions, classes, and full language behavior

## Project Structure

```text
src/
  com/
    interpreters/
      rorix/
        Rorix.java       # Program entry point
        Scanner.java     # Converts source text into tokens
        Token.java       # Token data structure
        TokenType.java   # Token type enum
```

## Requirements

- Java Development Kit (JDK) 8 or newer

This project currently does not use Gradle, Maven, or another build tool. It can
be compiled directly with `javac`.

## Build

From the repository root:

```bash
mkdir -p out
javac -d out src/com/interpreters/rorix/*.java
```

## Run

Start the interactive prompt:

```bash
java -cp out com.interpreters.rorix.Rorix
```

Run a Rorix source file:

```bash
java -cp out com.interpreters.rorix.Rorix path/to/file.rorix
```

At the current stage, running code prints the tokens produced by the scanner.
For example:

```lox
var language = "Rorix";
print language;
```

will print token information rather than execute the program.

## Example

Create a small file:

```bash
mkdir -p examples
printf 'var language = "Rorix";\nprint language;\n' > examples/hello.rorix
```

Compile and run it:

```bash
mkdir -p out
javac -d out src/com/interpreters/rorix/*.java
java -cp out com.interpreters.rorix.Rorix examples/hello.rorix
```

## Learning Notes

Rorix follows the structure of the Java tree-walk interpreter from Crafting
Interpreters. The language is still Lox-compatible while the implementation is
being built, but the project name, package name, and command-line identity have
been changed to Rorix.

As the course progresses, this README can be updated with parser, interpreter,
language syntax, and example program documentation.

## Acknowledgements

This project is based on the book
[Crafting Interpreters](https://craftinginterpreters.com/) by Robert Nystrom.
