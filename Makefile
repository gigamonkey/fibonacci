sources = $(wildcard *.java)

all:
	javac $(sources)

pretty:
	prettier --plugin ~/node_modules/prettier-plugin-java/ --write **/*.java

clean:
	find . -name '*.class' -delete
