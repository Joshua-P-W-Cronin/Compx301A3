# Compx301A3

Joshua Cronin 1212942
Luke Weston 1336265
_______
**Assignment description:**

The task was to create a regular expression compiler, which validates and compiles a regular expression into a finte state machine, which is output through std.out, and passed into a Regular Expression text searcher, which uses a dequeue.


https://www.cs.waikato.ac.nz/~tcs/COMPX301/assign3-2019.html

_______
**Usage:**

java REcompiler "<<YourRegEX>>" | java REsearcher <<YourtextFileGoesHere>>

Where <<YourRegEx>> is of the form of a regular expression

Which is well formed according to the following Grammar
_______

**Context Free Grammar**

- E -> T
- E -> T E
- E -> T | E

- T -> F
- T -> F ?
- F -> F *

- F -> \ w
- F ->(E)
- F ->^[L]
- F ->[L]
- F -> .
- F -> v

- L -> wL              
- L-> w                

L = list
w = any character
v = any vocabular item which is not a special symbol
_______
