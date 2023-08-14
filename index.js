const puppeteer = require('puppeteer');
const express = require('express');
const app = express();

app.get('/', async (req, res) => {
    const browser = await puppeteer.launch({
        args: [
            '--no-sandbox',
            '--disable-setuid-sandbox'
        ],
    });

    const page = await browser.newPage();
    await page.goto('https://example.com');
    const text = await page.$eval('h1', el => el.textContent);
    res.send(text);

    await browser.close();
});

app.listen(process.env.PORT || 3000, () => {
    console.log('Listening on :' + (process.env.PORT || 3000));
});