echo "$0: Files:"
echo
cat src_files.txt
echo
javac -d ChatServer/out/ @src_files.txt
echo "$0: Completed"
