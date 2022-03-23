pretty:
	prettier --plugin ~/node_modules/prettier-plugin-java/ --write **/*.java

clean:
	find . -name '*.class' -delete
