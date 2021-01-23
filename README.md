# ChatServer
This application can be used for chating in a Network.

## Usage:
- Compile all files by running `./compile.sh`
- Start a Server with `./server.sh`
- Start clients with `./client.sh`

## Changing Arguments:
- Change server port in `server.sh`
- Change client ip/port in `client.sh`

## Configuration Files:
- `src_files.txt`: contains path to src files to be compiled
- `compile.sh`: script to compile all src files
- `server.sh`: start server on port 12345
  (default, see [Changing Arguments](#changing-arguments))
- `client.sh`: start client on ip/port 127.0.0.1/12345
  (default, see [Changing Arguments](#changing-arguments))

## Argument Validation
- port should:
   1. only contain digits
   2. should not be a well-known port i.e. 0-1023
- ip/hostname should:
   1. either contain digits and '.'
   2. or be localhost
