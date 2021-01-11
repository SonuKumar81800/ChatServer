# ChatServer
This application can be used for chating in a Network.
## Usage:
- Start Server with `java ChatServer <port>`
  `java ChatServer 12345`
- Start a Client with `java ChatClient <ip/hostname>`
  `java ChatClient locahost 12345`
## ArgsValidation
- port should:
   1. only contain digits
   2. should not be a well-known port i.e. 0-1023
     
- ip/hostname should:
   1. either contain digits and '.'
   2. or be localhost
