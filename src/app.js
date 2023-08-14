const puppeteer = require('puppeteer');
const express = require('express');
const app = express();

//Routes
const index = require('./routes/index');
const web = require('./routes/web');

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use('/', index);
app.use('/web', web);

const port = process.env.PORT || 3000;

app.listen(port, () => {
    console.log(`Listening on :${port}!`);
});

module.exports = app;