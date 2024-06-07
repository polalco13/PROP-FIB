# Rutas específicas para Java 11
#JAVA_HOME = C:\Program Files\Java\jdk-11
#JAVAC = "$(JAVA_HOME)\bin\javac"
#JAR = "$(JAVA_HOME)\bin\jar"
#JAVA = "$(JAVA_HOME)\bin\java"

CLASS_INPUT =	./capaPersistencia/*.java \
				./capaDomini/jocs/*.java \
				./capaDomini/usuaris/*.java \
				./capaDomini/kenkens/*.java \
				./capaDomini/rankings/*.java \
				./capaDomini/operacions/*.java \
				./capaDomini/excepcions/*.java \
				./capaDomini/controladors/*.java \
				./capaDomini/estadistiques/*.java \
				./capaPresentacio/*.java \
				./capaPresentacio/views/*.java \

CLASS_OUTPUT = 	..\\EXE\\out\\

JAR_OUTPUT = 	..\\EXE\\

all:
	@echo Compilant...
	@javac -encoding UTF-8 -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	@echo Compilat correctament
	@echo Creant JAR...
	@jar cfm $(JAR_OUTPUT)KenKen.jar MANIFEST.MF -C $(CLASS_OUTPUT) .
	@echo JAR creat correctament
	@echo Finalitzat

run:
	@echo Executant...
	@java -jar $(JAR_OUTPUT)KenKen.jar
	@echo Finalitzat

clean:
	@if exist $(CLASS_OUTPUT) rd /s /q $(CLASS_OUTPUT)
	@if exist $(JAR_OUTPUT)KenKen.jar del /f $(JAR_OUTPUT)KenKen.jar
	@echo Netejat correctament
