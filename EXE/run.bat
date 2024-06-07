@echo off

cd ../FONTS/

javac capaDomini/excepcions/*.java^
 capaDomini/utils/*.java^
 capaDomini/usuaris/*.java^
 capaDomini/jocs/*.java^
 capaDomini/kenkens/*.java^
 capaDomini/operacions/*.java^
 capaDomini/rankings/*.java^
 capaDomini/estadistiques/*.java^
 capaDomini/controladors/*.java^
 capaDomini/drivers/*.java^
 capaPresentacio/*.java^
 capaPresentacio/views/*.java^
 capaPresentacio/views/*.form

java capaPresentacio/Main
pause