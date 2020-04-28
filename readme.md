# Minesweeper Project
- This project will create and display a Minesweeper game in the command line

- To run the file:
    java -jar target/*.jar

- To set up the database:
    sudo docker run -d --name minesweeper -p 5432:5432 --rm -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password postgres
    sudo docker exec -it minesweeper psql -U user