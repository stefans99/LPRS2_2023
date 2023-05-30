const express = require('express');
const fs = require('fs');
const app = express();
const port = 3000;

app.get('/temperature', (req, res) => {
    fs.readFile('data.txt', 'utf8' , (err, data) => {
        if (err) {
            console.error(err);
            res.status(500).send("An error occurred while reading the file.");
            return;
        }
        const temperature = data.trim();
        if (isNaN(temperature)) {
            res.status(400).send("The file does not contain a valid number.");
            return;
        }
        res.send(`The temperature is ${temperature} degrees.`);
    })
})

app.listen(port, () => {
    console.log(`Server listening at http://localhost:${port}`)
})
